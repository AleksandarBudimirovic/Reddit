// JavaScript file: community-actions.js

// Function to edit a community
function editCommunity(communityId) {
    window.location.href = '/communities/edit/' + communityId;
}

// Function to delete a community
function deleteCommunity(communityId) {
    // Example: Confirm deletion
    if (confirm('Are you sure you want to delete this community?')) {
        // Replace with your delete logic (could be AJAX request, etc.)
        console.log('Deleting community with ID:', communityId);
        // Example: Redirect after deletion
        // window.location.href = '/communities/delete/' + communityId;
    } else {
        console.log('Deletion canceled.');
    }
}

// Function to create a new post
function createNewPost(communityId) {
    window.location.href = '/posts/add?communityId=' + communityId;
}

