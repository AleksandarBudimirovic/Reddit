$(document).ready(function() {
    

    // Function to list communities
    function listCommunities() {
        $.ajax({
            url: '/api/communities/all',
            type: 'GET',
            success: function(data) {
                //console.log('List of communities:', data);
                displayCommunities(data); // Call displayCommunities with the received data
            },
            error: function(error) {
                console.error('Error listing communities:', error);
            }
        });
    }

    // Function to format date to a readable format
    function formatDate(dateString) {
        var date = new Date(dateString);
        return date.toLocaleDateString();
    }

    // Function to display communities in the table


	
	


	/*
	$(document).on('click', 'a.community-link', function(e) {
	    e.preventDefault(); // Prevent default anchor behavior
	    
	    var communityId = $(this).data('community-id'); // Get community ID from data attribute
	    console.log('Id = ', communityId);
	    window.location.href = `/community/detailsCommunity?id=${communityId}`; // Redirect to the details page with the community ID
	});
	*/

    // Update HTML with community details
 


    // Event listener for adding a new community
    $("#addCommunity").click(function(e) {
        e.preventDefault(); // Prevent form submission
        
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
            url: '/api/communities/addCommunity',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(newCommunity),
            success: function(data) {
                console.log('Community added successfully:', data);
                // Optionally, fetch updated list of communities or perform other actions
                listCommunities(); // Refresh the community list after adding a new community
            },
            error: function(error) {
                console.error('Error adding community:', error);
            }
        });
    });

    // Function to edit a community
    function editCommunity(communityId) {
        var updatedDescription = $("#updatedCommunityDescription").val();
        var updatedIsSuspended = $("#updatedCommunityIsSuspended").val();
        var updatedSuspendedReason = $("#updatedCommunitySuspendedReason").val();

        var updatedCommunity = {
            description: updatedDescription,
            isSuspended: updatedIsSuspended,
            suspendedReason: updatedSuspendedReason
        };

        $.ajax({
            url: `/api/communities/${communityId}`,
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(updatedCommunity),
            success: function(data) {
                console.log('Community updated successfully:', data);
                // Optionally, update UI or perform other actions
                listCommunities(); // Refresh the community list after updating a community
            },
            error: function(error) {
                console.error('Error updating community:', error);
            }
        });
    }

    // Event listener for edit community form submission
    $("#editCommunityForm").submit(function(event) {
        event.preventDefault(); // Prevent form submission
        
        var communityId = getCommunityIdFromUrl();
        editCommunity(communityId); // Edit community with the retrieved ID
    });

    // Function to delete a community
    function deleteCommunity(communityId) {
        $.ajax({
            url: `/api/communities/${communityId}`,
            type: 'DELETE',
            success: function(data) {
                console.log('Community deleted successfully:', data);
                // Optionally, update UI or perform other actions
                listCommunities(); // Refresh the community list after deleting a community
            },
            error: function(error) {
                console.error('Error deleting community:', error);
            }
        });
    }

    // Event listener for delete community button click
    $("#deleteCommunity").click(function() {
        var userConfirmed = window.confirm("Are you sure you want to delete this community?");
        if (userConfirmed) {
            var communityId = getCommunityIdFromUrl();
            deleteCommunity(communityId); // Delete community with the retrieved ID
        }
    });

    // Function to get community ID from URL
    function getCommunityIdFromUrl(url) {
        const urlParts = url ? new URL(url).pathname.split('/') : window.location.pathname.split('/');
        return urlParts[urlParts.length - 1];
    }
});
