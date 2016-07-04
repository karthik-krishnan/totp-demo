$("#register").click(function() {
    $("#register_result").html("Please wait...");
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/register",
        data: "email=" + $("#new_email").val()
    }).then(function(data) {
        $("#register_result").html(data);
    });
});

$("#validate").click(function() {
    $("#validate_result").html("Please wait...");
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/validate",
        data: "email=" + $("#email").val() + "&otp=" + $("#totp").val()
    }).then(function(data) {
        if (data == true)
            $("#validate_result").html("Authentication Success!!!");
        else
            $("#validate_result").html("Authentication Failed!!!");
    });
});
