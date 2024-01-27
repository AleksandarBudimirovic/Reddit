$(document).ready(function () {

 

    $("#addBan").click(function (e) {
        var banTimestamp = $("#banTimestamp").val();
        var communityId = $("#communityId").val();
        var userId = $("#userId").val();

        var newBan = {
            timestamp: banTimestamp,
            Community: { id: communityId },
            User: { id: userId }
        };

        $.ajax({
            url: '/api/bans',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(newBan),
            success: function (data) {
                console.log('Ban added successfully:', data);
            },
            error: function (error) {
                console.error('Error adding ban:', error);
            }
        });
    });

    function listBans() {
        $.ajax({
            url: '/api/bans',  // Replace with your actual backend endpoint for listing bans
            type: 'GET',
            success: function (data) {
                console.log('List of bans:', data);
                // Handle displaying the list on the UI if needed
            },
            error: function (error) {
                console.error('Error listing bans:', error);
            }
        });
    }

    // Function to handle editing a ban
    function editBan(banId) {
        // Assume there's a form with fields for editing, replace with actual field IDs
        var updatedTimestamp = $("#updatedBanTimestamp").val();

        var updatedBan = {
            timestamp: updatedTimestamp
        };

        $.ajax({
            url: '/api/bans/' + banId,  // Replace with your actual backend endpoint for updating bans
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(updatedBan),
            success: function (data) {
                console.log('Ban updated successfully:', data);
                // You might want to update the UI or do something else after updating
            },
            error: function (error) {
                console.error('Error updating ban:', error);
            }
        });
    }

    // Function to handle deleting a ban
    function deleteBan(banId) {
        $.ajax({
            url: '/api/bans/' + banId,  // Replace with your actual backend endpoint for deleting bans
            type: 'DELETE',
            success: function (data) {
                console.log('Ban deleted successfully:', data);
                // You might want to update the UI or do something else after deleting
            },
            error: function (error) {
                console.error('Error deleting ban:', error);
            }
        });
    }

    // Example usage of the functions
    listBans();

});
