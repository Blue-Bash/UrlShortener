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
                    "#urlInput").val() + "\" isn't a valid URI.</div>");
              }
            });
          });
      $("#nameEdit").submit( // Implemented as API but not working
          function (event) {
            event.preventDefault();
            $.ajax({
              type: "PUT",
              url: "/uri/" + $("#name_shortUriInput").val(),
              contentType: "application/json",
              data: JSON.stringify({"new-name": $("#name_newNameInput").val(),
                                    "hashpass": $("#name_hashPassInput").val()}), // access in body
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
              url: "/uri/" + $("#delete_shortUriInput").val(),
              headers: {
                URIHashPass: $("#delete_hashPassInput").val()
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
    });