// Function to get community ID from URL
function getCommunityIdFromUrl() {
    const urlParts = window.location.pathname.split('/');
    return urlParts[urlParts.length - 1];
}

// Function to fetch and display community details
function fetchCommunityDetails(id) {
    fetch(`/api/communities/details/${id}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            updateCommunityDetails(data);
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
        });
}

// Event listener using event delegation for community links
$(document).on('click', 'a.community-link', function(e) {
    e.preventDefault(); // Prevent default anchor behavior
    
    var communityId = $(this).data('community-id'); // Get community ID from data attribute
    fetchCommunityDetails(communityId); // Fetch community details
});

// Update HTML with community details
function updateCommunityDetails(data) {
    document.getElementById('communityId').innerText = data.id;
    document.getElementById('communityName').innerText = data.description;
    document.getElementById('creationDate').innerText = data.creationDate;
    document.getElementById('isSuspended').innerText = data.isSuspended;
    document.getElementById('suspendedReason').innerText = data.suspendedReason;
    document.getElementById('creator').innerText = data.user.username;
}

// Event listener for fetching community details when a community link is clicked
$(document).on('click', 'a.community-link', function(e) {
    e.preventDefault(); // Prevent the default anchor behavior
    
    var url = $(this).attr('href'); // Get the href attribute of the clicked link
    var id = getCommunityIdFromUrl(url); // Extract the community ID from the URL
    
    fetchCommunityDetails(id); // Fetch community details based on the ID
});

// Event listener for adding a new community
$("#addCommunity").click(function (e) {
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
        success: function (data) {
            console.log('Community added successfully:', data);
            // Optionally, fetch updated list of communities or perform other actions
        },
        error: function (error) {
            console.error('Error adding community:', error);
        }
    });
});

// Function to list communities
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

// Function to format date to a readable format
function formatDate(dateString) {
    var date = new Date(dateString);
    return date.toLocaleDateString();
}

// Function to display communities in the table
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
            '<a class="btn btn-info community-link" href="/community/details/' + community.id + '" role="button">Details</a>' +
            '</td>' +
            '</tr>';
        tbody.append(row);
    });
}

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
        success: function (data) {
            console.log('Community updated successfully:', data);
            // Optionally, update UI or perform other actions
        },
        error: function (error) {
            console.error('Error updating community:', error);
        }
    });
}

// Event listener for edit community form submission
$("#editCommunityForm").submit(function (event) {
    event.preventDefault(); // Prevent form submission
    
    var communityId = getCommunityIdFromUrl();
    editCommunity(communityId); // Edit community with the retrieved ID
});

// Function to delete a community
function deleteCommunity(communityId) {
    $.ajax({
        url: `/api/communities/${communityId}`,
        type: 'DELETE',
        success: function (data) {
            console.log('Community deleted successfully:', data);
            // Optionally, update UI or perform other actions
        },
        error: function (error) {
            console.error('Error deleting community:', error);
        }
    });
}

// Event listener for delete community button click
$("#deleteCommunity").click(function () {
    var userConfirmed = window.confirm("Are you sure you want to delete this community?");
    if (userConfirmed) {
        var communityId = getCommunityIdFromUrl();
        deleteCommunity(communityId); // Delete community with the retrieved ID
    }
});

// Example: Trigger initial listing of communities
listCommunities();
