$(document).ready(function () {

    $("#addComment").click(function (e) {
        var commentText = $("#commentText").val();
        var userId = $("#userId").val();
        var postId = $("#postId").val();

        var newComment = {
            text: commentText,
            User: { id: userId }, 
            Post: { id: postId }  
        };

        $.ajax({
            url: '/api/comments',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(newComment),
            success: function (data) {
                console.log('Comment added successfully:', data);
            },
            error: function (error) {
                console.error('Error adding comment:', error);
            }
        });
    });

    // Function to handle listing all comments
    function listComments() {
        $.ajax({
            url: '/api/comments',
            type: 'GET',
            success: function (data) {
                console.log('List of comments:', data);
                // Handle displaying the list on the UI if needed
            },
            error: function (error) {
                console.error('Error listing comments:', error);
            }
        });
    }

    // Function to handle editing a comment
    function editComment(commentId) {
        var updatedText = $("#updatedCommentText").val();

        var updatedComment = {
            text: updatedText
        };

        $.ajax({
            url: '/api/comments/' + commentId,
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(updatedComment),
            success: function (data) {
                console.log('Comment updated successfully:', data);
                // You might want to update the UI or do something else after updating
            },
            error: function (error) {
                console.error('Error updating comment:', error);
            }
        });
    }

    $("#editCommentForm").submit(function (event) {
        event.preventDefault();
        var commentId = getCommentIdForEdit();
        editComment(commentId);
    });

    // Function to handle deleting a comment
    function deleteComment(commentId) {
        $.ajax({
            url: '/api/comments/' + commentId,
            type: 'DELETE',
            success: function (data) {
                console.log('Comment deleted successfully:', data);
                // You might want to update the UI or do something else after deleting
            },
            error: function (error) {
                console.error('Error deleting comment:', error);
            }
        });
    }
    
    $("#deleteComment").click(function () {
        // Display a confirmation prompt
        var userConfirmed = window.confirm("Are you sure you want to delete this comment?");

        // Check if the user confirmed
        if (userConfirmed) {
            // Retrieve the commentId from wherever it comes from (e.g., a data attribute or hidden input)
            var commentId = getCommentIdForDelete(); // Replace with the appropriate method to get the commentId

            // Call the deleteComment function with the retrieved commentId
            deleteComment(commentId);
        }
    });

    // ... Similar functions for other entities ...

    // Example usage of the functions
    listComments();

    // ... Other functionalities ...
});
