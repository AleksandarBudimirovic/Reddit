$(document).ready(function() {
    var usernameInput = $('#username');
    var passwordInput = $('#password');

    $('#loginSubmit').on('click', function(event) {
        var username = usernameInput.val();
        var password = passwordInput.val();

        var params = {
            'username': username,
            'password': password
        };

        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/api/users/login',
            contentType: 'application/json',
            data: JSON.stringify(params),
            success: function(data) {
                console.log('Login successful!');
                console.log(data);

                // Redirect to the desired page on successful login
       
                window.location.href = 'http://localhost:8080/api/communities/listCommunities';
            },
            error: function(error) {
                console.error('Invalid username or password. Please try again.');
                console.error(error);

                // You might want to display an error message to the user
                alert('Invalid username or password. Please try again.');
            }
        });

        event.preventDefault();
        return false;
    });
});
