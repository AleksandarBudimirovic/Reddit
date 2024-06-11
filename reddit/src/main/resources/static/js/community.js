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
            url: '/api/communities/addCommunity',  
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
        url: '/api/communities/getCommunities',
        type: 'GET',
        success: function (data) {
            console.log('List of communities:', data);
            displayCommunities(data); // Call displayCommunities with the received data
        },
        error: function (error) {
            console.error('Error listing communities:', error);
        }
    });
}

function formatDate(dateString) {
    var date = new Date(dateString);
    return date.toLocaleDateString();
}

function displayCommunities(communities) {
    var tbody = $('#communityTableBody');
    tbody.empty();

    communities.forEach(function (community) {
        var row = '<tr>' +
            '<td>' + community.id + '</td>' +
            '<td>' + formatDate(community.creationDate) + '</td>' +
            '<td>' + community.description + '</td>' +
            '<td>' + community.isSuspended + '</td>' +
            '<td>' + community.suspendedReason + '</td>' +
            '<td>' + community.user.username + '</td>' +
            '<td>' +
            '<a class="btn btn-info" href="/community/details/' + community.id + '" role="button">Details</a>' +
            '</td>' +
            '</tr>';
        tbody.append(row);
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
            url: '/api/communities/' + communityId,
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
        event.preventDefault();

        var communityId = getCommunityIdForEdit(); 
        editCommunity(communityId);
    });

    // Function to handle deleting a community
    function deleteCommunity(communityId) {
        $.ajax({
            url: '/api/communities/' + communityId,
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
        var userConfirmed = window.confirm("Are you sure you want to delete this community?");

        if (userConfirmed) {
            var communityId = getCommunityIdForDelete(); 
            deleteCommunity(communityId);
        }
    });

    listCommunities();

});
