var stompClient = null;

function setConnected(connected) {
  if (connected) {
    $("#uriStats").show();
    $("#disconnectAlert").hide();
  }
  else {
    $("#uriStats").hide();
    $("#disconnectAlert").show();
  }
  $("#greetings").html("");
}

function connect() {
  var socket = new SockJS('/gs-guide-websocket'); // TODO: Change "/gs-guide-websocket"
  stompClient = Stomp.over(socket);
  stompClient.connect({}, function (frame) {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/greetings', function (greeting) { // TODO: Change "/topic/greetings"
      updateStats(JSON.parse(greeting.body).content);
    });
  });
}

function disconnect() {
  if (stompClient !== null) {
    stompClient.disconnect();
  }
  setConnected(false);
  console.log("Disconnected");
}

function updateStats(stats) { // TODO: Implement with stats JSON values
  $("#accessedURI").val("accURI");

  $("#shortenedURI").val("shoURI");
  $("#deletedURI").val("delURI");
  $("#workingURI").val("worURI");

  $("#generatedQR").val("genQR");
  $("#deleteQR").val("delQR");
  $("#workingQR").val("worQR");
}

$(function () {
  $("form").on('submit', function (e) {
    e.preventDefault();
  });
  $( "#connect" ).click(function() { connect(); });
  $( "#disconnect" ).click(function() { disconnect(); });
  $( "#send" ).click(function() { sendName(); });
});