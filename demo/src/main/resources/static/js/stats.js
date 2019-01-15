String.prototype.replaceAll = function(search, replacement) {
  var target = this;
  return target.split(search).join(replacement);
}

function updateField(json, key, multiplier, decimals){
  if(decimals === undefined) decimals = 0;
  if(multiplier === undefined) multiplier = 1;
  var htmlID = key.replaceAll(".", "-");
  $("#" + htmlID).text((multiplier * Number(json[key])).toLocaleString(undefined,{minimumFractionDigits:decimals}));
}

function startSockJS(){
  var socket = new SockJS('/live-stats');
  var stompClient = Stomp.over(socket);
  var sessionId = "";

  stompClient.connect({}, function (frame) {
    var url = stompClient.ws._transport.url;
    url = url.replace(
        "ws://" + window.location.origin + "/live-stats",  "");
    url = url.replace("/websocket", "");
    url = url.replace(/^[0-9]+\//, "");
    console.log("Your current session is: " + url);
    sessionId = url;

    stompClient.subscribe('/stats/live', function (msgOut) {
      //handle messages
      var json = $.parseJSON(msgOut.body);
      updateField(json, "uris.created");
      updateField(json, "uris.now");
      updateField(json, "uris.accessed");
      updateField(json, "uris.removed");
      updateField(json, "qr.created");
      updateField(json, "qr.now");
      updateField(json, "qr.accessed");
      updateField(json, "qr.removed");
      updateField(json, "cpu.usage", 100, 2);
      updateField(json, "system.cpu.usage", 100, 2);
      updateField(json, "system.memory.usage", 1/(1048576), 2);
      $("#disconnectAlert").hide();
    });
  });

}

$(document).ready(
    function () {
      $("#disconnectAlert").hide();
      startSockJS();
    });