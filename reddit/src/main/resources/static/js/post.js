$(document).ready(function () {



    $("#addPost").click(function (e) {
        var creationDate = $("#postCreationDate").val();
        var imagePath = $("#postImagePath").val();
        var title = $("#postTitle").val();
        var text = $("#postText").val();
        var communityId = $("#postCommunityId").val();
        var userId = $("#postUserId").val();

        var newPost = {
            creationDate: creationDate,
            imagePath: imagePath,
            title: title,
            text: text,
            Community: { id: communityId },
            User: { id: userId }
        };

        $.ajax({
            url: '/api/posts',  // Replace with your actual backend endpoint
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(newPost),
            success: function (data) {
                console.log('Post added successfully:', data);
            },
            error: function (error) {
                console.error('Error adding post:', error);
            }
        });
    });

    function listPosts() {
        $.ajax({
            url: '/api/posts',  // Replace with your actual backend endpoint for listing posts
            type: 'GET',
            success: function (data) {
                console.log('List of posts:', data);
                // Handle displaying the list on the UI if needed
            },
            error: function (error) {
                console.error('Error listing posts:', error);
            }
        });
    }

    // Function to handle editing a post
    function editPost(postId) {
        // Assume there's a form with fields for editing, replace with actual field IDs
        var updatedImagePath = $("#updatedPostImagePath").val();
        var updatedTitle = $("#updatedPostTitle").val();
        var updatedText = $("#updatedPostText").val();

        var updatedPost = {
            imagePath: updatedImagePath,
            title: updatedTitle,
            text: updatedText
        };

        $.ajax({
            url: '/api/posts/' + postId,  // Replace with your actual backend endpoint for updating posts
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(updatedPost),
            success: function (data) {
                console.log('Post updated successfully:', data);
                // You might want to update the UI or do something else after updating
            },
            error: function (error) {
                console.error('Error updating post:', error);
            }
        });
    }
    
    $("#editPostForm").submit(function (event) {
        // Prevent the default form submission
        event.preventDefault();

        // Get the postId from wherever it comes from (e.g., a data attribute or hidden input)
        var postId = getPostIdForEdit(); // Replace with the appropriate method to get the postId

        // Call the editPost function with the retrieved postId
        editPost(postId);
    });

    // Function to handle deleting a post
    function deletePost(postId) {
        $.ajax({
            url: '/api/posts/' + postId,  // Replace with your actual backend endpoint for deleting posts
            type: 'DELETE',
            success: function (data) {
                console.log('Post deleted successfully:', data);
                // You might want to update the UI or do something else after deleting
            },
            error: function (error) {
                console.error('Error deleting post:', error);
            }
        });
    }
    
    $("#deletePost").click(function () {
        // Display a confirmation prompt
        var userConfirmed = window.confirm("Are you sure you want to delete this post?");

        // Check if the user confirmed
        if (userConfirmed) {
            // Retrieve the postId from wherever it comes from (e.g., a data attribute or hidden input)
            var postId = getPostIdForDelete(); // Replace with the appropriate method to get the postId

            // Call the deletePost function with the retrieved postId
            deletePost(postId);
        }
    });

    // ... Similar functions for other entities ...

    // Example usage of the functions
    listPosts();

});
