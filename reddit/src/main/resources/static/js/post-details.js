// JavaScript file: post-actions.js

// Function to edit a post
function editPost(postId) {
    window.location.href = '/posts/edit/' + postId;
}

// Function to delete a post
function deletePost(postId) {
    // Example: Confirm deletion
    if (confirm('Are you sure you want to delete this post?')) {
        // Replace with your delete logic (could be AJAX request, etc.)
        console.log('Deleting post with ID:', postId);
        // Example: Redirect after deletion
        // window.location.href = '/posts/delete/' + postId;
    } else {
        console.log('Deletion canceled.');
    }
}

// Function to leave a comment
function leaveComment(communityId) {
    window.location.href = '/posts/add?communityId=' + communityId;
}

// JavaScript file: post-details.js

document.addEventListener('DOMContentLoaded', function() {
    // Edit comment buttons
    var editButtons = document.querySelectorAll('.edit-comment-btn');
    editButtons.forEach(function(button) {
        button.addEventListener('click', function() {
            var commentId = button.getAttribute('data-comment-id');
            editComment(commentId);
        });
    });

    // Delete comment buttons
    var deleteButtons = document.querySelectorAll('.delete-comment-btn');
    deleteButtons.forEach(function(button) {
        button.addEventListener('click', function() {
            var commentId = button.getAttribute('data-comment-id');
            deleteComment(commentId);
        });
    });
});

// Function to edit a comment
function editComment(commentId) {
    // Example: Redirect to edit comment page
    window.location.href = '/comments/edit/' + commentId;
}

// Function to delete a comment
function deleteComment(commentId) {
    // Example: Confirm deletion
    if (confirm('Are you sure you want to delete this comment?')) {
        // Replace with your delete logic (AJAX request)
        fetch('/comments/delete/' + commentId, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
                // Add any additional headers as needed
            },
            // You can include credentials: 'same-origin' or 'include' if needed
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // if response is JSON
        })
        .then(data => {
            console.log('Comment deleted successfully:', data);
            // Example: Reload page after deletion
            // location.reload();
        })
        .catch(error => {
            console.error('Error deleting comment:', error);
        });
    } else {
        console.log('Deletion canceled.');
    }
}


