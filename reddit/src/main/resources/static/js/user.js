$(document).ready(function () {

 

    $("#addUser").click(function (e) {
        var avatar = $("#userAvatar").val();
        var description = $("#userDescription").val();
        var displayName = $("#userDisplayName").val();
        var password = $("#userPassword").val();
        var registrationDate = $("#userRegistrationDate").val();
        var role = $("#userRole").val();
        var username = $("#userUsername").val();

        var newUser = {
            avatar: avatar,
            description: description,
            displayName: displayName,
            password: password,
            registrationDate: registrationDate,
            role: role,
            username: username
        };

        $.ajax({
            url: '/api/users',  // Replace with your actual backend endpoint
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(newUser),
            success: function (data) {
                console.log('User added successfully:', data);
            },
            error: function (error) {
                console.error('Error adding user:', error);
            }
        });
    });
    
    function listUsers() {
        $.ajax({
            url: '/api/users',  // Replace with your actual backend endpoint for listing users
            type: 'GET',
            success: function (data) {
                console.log('List of users:', data);
                // Handle displaying the list on the UI if needed
            },
            error: function (error) {
                console.error('Error listing users:', error);
            }
        });
    }

    // Function to handle editing a user
    function editUser(userId) {
        // Assume there's a form with fields for editing, replace with actual field IDs
        var updatedAvatar = $("#updatedUserAvatar").val();
        var updatedDescription = $("#updatedUserDescription").val();
        var updatedDisplayName = $("#updatedUserDisplayName").val();

        var updatedUser = {
            avatar: updatedAvatar,
            description: updatedDescription,
            displayName: updatedDisplayName
        };

        $.ajax({
            url: '/api/users/' + userId,  // Replace with your actual backend endpoint for updating users
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(updatedUser),
            success: function (data) {
                console.log('User updated successfully:', data);
                // You might want to update the UI or do something else after updating
            },
            error: function (error) {
                console.error('Error updating user:', error);
            }
        });
    }

    // Function to handle deleting a user
    function deleteUser(userId) {
        $.ajax({
            url: '/api/users/' + userId,  // Replace with your actual backend endpoint for deleting users
            type: 'DELETE',
            success: function (data) {
                console.log('User deleted successfully:', data);
                // You might want to update the UI or do something else after deleting
            },
            error: function (error) {
                console.error('Error deleting user:', error);
            }
        });
    }
    
    function performLogin() {
	    var username = document.getElementById('username').value;
	    var password = document.getElementById('password').value;

	    
	    if (username === 'demo' && password === 'password') {
	        alert('Login successful!');
	       
	    } else {
	        alert('Invalid username or password. Please try again.');
	    }
	}

    // Example usage of the functions
    listUsers();



});
