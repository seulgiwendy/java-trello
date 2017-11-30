const fetchMode = {
    method: 'GET',
    mode: 'CORS'
}


const getUserInfo = () => {
    $.get("/api/legacy/user", function(data) {
        console.log(data);
        $("#username").html("Signed-in Facebook User: " + data.userAuthentication.details.name);
        $(".unauthenticated").hide()
        $(".authenticated").show()
    });
}

window.onload = () => {
    getUserInfo();
}