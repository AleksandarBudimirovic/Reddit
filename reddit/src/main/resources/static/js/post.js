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

    // ... Similar functions for other entities ...

    // Example usage of the functions
    listPosts();

});
