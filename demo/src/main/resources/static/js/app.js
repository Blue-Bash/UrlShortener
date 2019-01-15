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
            "ws://localhost:8080/live-stats",  "");
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
        });
    });

}

$(document).ready(
    function () {
      $("#shortener").submit(
          function (event) {
            event.preventDefault();
            var name = $("#customName").val();
            $.ajax({
              type: "PUT",
              url: "/uri/" + name,
              contentType: "application/json",
              data: JSON.stringify({"uri": $("#urlInput").val()}), // access in body
              success: function (msg) {
                $("#shortenerResult").html(
                    "<div class='alert alert-success lead'><a target='_blank' href='"
                    + window.location.origin + "/uri/" + msg.id
                    + "'>"
                    + window.location.origin + "/uri/" + msg.id
                    + "</a>"
                    + "</br>"
                    + "<p>This is your HashPass; a private key for editing and delete your URI: " + msg.hashpass + "</p>"
                    + "</div>");

                var width = $("#qrWidth").val();
                var height = $("#qrHeight").val();

                $.ajax({
                  type: "GET",
                  url: "/qr/" + msg.id + "?width=" + width + "&height="
                  + height,
                  contentType: "application/json",
                  success: function (msg) {
                    $("#QRResult").html(
                        "<img src=\"data:image/jpeg;base64," + msg.qr + "\">");
                  },
                  error: function () {
                    $("#QRResult").html(
                        "<div class='alert alert-danger lead'>Error retrieving QR Code</div>");
                  }
                });

              },
              error: function () {
                $("#shortenerResult").html(
                    "<div class='alert alert-danger lead'>ERROR: \"" + $(
                    "#urlInput").val() + "\" isn't a valid URI or name is already taken.</div>");
                $("#QRResult").html("<div></div>");
              }
            });
          });

      $("#qrViewer").submit( // Implemented as API but not working
          function (event) {
            event.preventDefault();
            $.ajax({
              type: "GET",
              url: "/qr/" + $("#qrUriInput").val() + "?width=" + $("#qrWidthInput").val() + "&height="
              + $("#qrHeightInput").val(),
              contentType: "application/json",
              success: function (msg) {
                $("#qrViewerResult").html(
                    "<img src=\"data:image/jpeg;base64," + msg.qr + "\">");
              },
              error: function () {
                $("#qrViewerResult").html(
                    "<div class='alert alert-danger lead'>Error retrieving QR Code</div>");
              }
            });
          });

      $("#nameEdit").submit( // Implemented as API but not working
          function (event) {
            event.preventDefault();
            $.ajax({
              type: "PUT",
              url: "/uri/" + $("#nameShortUriInput").val(),
              contentType: "application/json",
              data: JSON.stringify({"new-name": $("#nameNewNameInput").val(),
                                    "hashpass": $("#nameHashPassInput").val()}), // access in body
              success: function (msg) {
                $("#nameEditResult").html(
                    "<div class='alert alert-success lead'><a target='_blank' href='"
                    + window.location.origin + "/uri/" + msg.id
                    + "'>"
                    + window.location.origin + "/uri/" + msg.id
                    + "</a></div>");
              },
              error: function () {
                $("#nameEditResult").html(
                    "<div class='alert alert-danger lead'>ERROR: Name or URI isn't valid.</div>");
              }
            });
          });

      $("#uriDelete").submit(
          function (event) {
            event.preventDefault();
            $.ajax({
              type: "DELETE",
              url: "/uri/" + $("#deleteShortUriInput").val(),
              headers: {
                URIHashPass: $("#deleteHashPassInput").val()
              },
              success: function () {
                $("#deleteUriResult").html(
                    "<div class='alert alert-success lead'><p>Deleted</p></div>");
              },
              error: function () {
                $("#deleteUriResult").html(
                    "<div class='alert alert-danger lead'>ERROR: ID or HashPass isn't valid.</div>");
              }
            });
          });
      startSockJS();
    });