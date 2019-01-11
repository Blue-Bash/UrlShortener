$(document).ready(
    function() {
        $("#shortener").submit(
            function(event) {
                event.preventDefault();
                $.ajax({
                    type : "PUT",
                    url : "/uri",
                    contentType: "application/json",
                    data: JSON.stringify({"uri":$("#urlInput").val()}), // access in body
                    success : function(msg) {
                        $("#shortenerResult").html(
                            "<div class='alert alert-success lead'><a target='_blank' href='"
                            + window.location.origin + "/uri/" + msg.id
                            + "'>"
                            + window.location.origin + "/uri/" + msg.id
                            + "</a></div>");

                            var width = $("#qrWidth").val();
                            var height = $("#qrHeight").val();

                            $.ajax({
                                type : "GET",
                                url : "/qr/" + msg.id + "?width=" + width + "&height=" + height,
                                contentType: "application/json",
                                success : function(msg) {
                                    $("#QRResult").html(
                                        "<img src=\"data:image/jpeg;base64," + msg.qr + "\">");
                                },
                                error : function() {
                                    $("#QRResult").html(
                                        "<div class='alert alert-danger lead'>Error retrieving QR Code</div>");
                                }
                            });

                    },
                    error : function() {
                        $("#shortenerResult").html(
                            "<div class='alert alert-danger lead'>ERROR: \"" + $("#urlInput").val() + "\" isn't a valid URI.</div>");
                    }
                });
            });
    });