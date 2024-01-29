$(document).ready(function () {

    

    $("#addCommunity").click(function (e) {
        var creationDate = $("#creationDate").val();
        var description = $("#description").val();
        var isSuspended = $("#isSuspended").val();
        var suspendedReason = $("#suspendedReason").val();
        var userId = $("#userId").val();

        var newCommunity = {
            creationDate: creationDate,
            description: description,
            isSuspended: isSuspended,
            suspendedReason: suspendedReason,
            user: { id: userId }
        };

        $.ajax({
            url: '/api/communities',  // Replace with your actual backend endpoint
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(newCommunity),
            success: function (data) {
                console.log('Community added successfully:', data);
            },
            error: function (error) {
                console.error('Error adding community:', error);
            }
        });
    });

    function listCommunities() {
        $.ajax({
            url: '/api/communities',  // Replace with your actual backend endpoint for listing communities
            type: 'GET',
            success: function (data) {
                console.log('List of communities:', data);
                // Handle displaying the list on the UI if needed
            },
            error: function (error) {
                console.error('Error listing communities:', error);
            }
        });
    }

    // Function to handle editing a community
    function editCommunity(communityId) {
        // Assume there's a form with fields for editing, replace with actual field IDs
        var updatedDescription = $("#updatedCommunityDescription").val();
        var updatedIsSuspended = $("#updatedCommunityIsSuspended").val();
        var updatedSuspendedReason = $("#updatedCommunitySuspendedReason").val();

        var updatedCommunity = {
            description: updatedDescription,
            isSuspended: updatedIsSuspended,
            suspendedReason: updatedSuspendedReason
        };

        $.ajax({
            url: '/api/communities/' + communityId,  // Replace with your actual backend endpoint for updating communities
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(updatedCommunity),
            success: function (data) {
                console.log('Community updated successfully:', data);
                // You might want to update the UI or do something else after updating
            },
            error: function (error) {
                console.error('Error updating community:', error);
            }
        });
    }
    
    $("#editCommunityForm").submit(function (event) {
        // Prevent the default form submission
        event.preventDefault();

        // Get the communityId from wherever it comes from (e.g., a data attribute or hidden input)
        var communityId = getCommunityIdForEdit(); // Replace with the appropriate method to get the communityId

        // Call the editCommunity function with the retrieved communityId
        editCommunity(communityId);
    });

    // Function to handle deleting a community
    function deleteCommunity(communityId) {
        $.ajax({
            url: '/api/communities/' + communityId,  // Replace with your actual backend endpoint for deleting communities
            type: 'DELETE',
            success: function (data) {
                console.log('Community deleted successfully:', data);
                // You might want to update the UI or do something else after deleting
            },
            error: function (error) {
                console.error('Error deleting community:', error);
            }
        });
    }
    
    $("#deleteCommunity").click(function () {
        // Display a confirmation prompt
        var userConfirmed = window.confirm("Are you sure you want to delete this community?");

        // Check if the user confirmed
        if (userConfirmed) {
            // Retrieve the communityId from wherever it comes from (e.g., a data attribute or hidden input)
            var communityId = getCommunityIdForDelete(); // Replace with the appropriate method to get the communityId

            // Call the deleteCommunity function with the retrieved communityId
            deleteCommunity(communityId);
        }
    });

    // ... Similar functions for BanDTO, ReactionDTO, ReportDTO ...

    // Example usage of the functions
    listCommunities();

});
