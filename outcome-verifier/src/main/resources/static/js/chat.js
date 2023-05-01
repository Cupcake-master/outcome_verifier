function sendMessage(email, text) {
    let body = {
        email: email,
        text: text
    };

    $.ajax({
        url: "/BulSchool_war//messages",
        method: "POST",
        data: JSON.stringify(body),
        contentType: "application/json",
        dataType: "json",
        complete: function () {
            if (text === 'Login') {
                receiveMessage(email)
            }
        }
    });
}

// LONG POLLING
function receiveMessage(email) {
    $.ajax({
        url: "/BulSchool_war//messages?email=" + email,
        method: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function (response) {
            $('#messages').first().after('<li>' + response[0]['text'] + '</li>');
            receiveMessage(email);
        }
    })
}