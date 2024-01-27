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

    $(document).ready(function () {

        // ... Your existing code ...

        // Function to handle listing all comments
        function listComments() {
            $.ajax({
                url: '/api/comments',  // Replace with your actual backend endpoint for listing comments
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
            // Assume there's a form with fields for editing, replace with actual field IDs
            var updatedText = $("#updatedCommentText").val();

            var updatedComment = {
                text: updatedText
            };

            $.ajax({
                url: '/api/comments/' + commentId,  // Replace with your actual backend endpoint for updating comments
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

        // Function to handle deleting a comment
        function deleteComment(commentId) {
            $.ajax({
                url: '/api/comments/' + commentId,  // Replace with your actual backend endpoint for deleting comments
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

        // ... Similar functions for other entities ...

        // Example usage of the functions
        listComments();

        // ... Other functionalities ...

    });

});
