$(document).ready(function () {

    // ... Your existing code ...

    $("#addReaction").click(function (e) {
        var timestamp = $("#reactionTimestamp").val();
        var type = $("#reactionType").val();
        var commentId = $("#reactionCommentId").val();
        var postId = $("#reactionPostId").val();
        var userId = $("#reactionUserId").val();

        var newReaction = {
            timestamp: timestamp,
            type: type,
            comment: { id: commentId },
            post: { id: postId },
            user: { id: userId }
        };

        $.ajax({
            url: '/api/reactions',  // Replace with your actual backend endpoint
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(newReaction),
            success: function (data) {
                console.log('Reaction added successfully:', data);
            },
            error: function (error) {
                console.error('Error adding reaction:', error);
            }
        });
    });

    function listReactions() {
        $.ajax({
            url: '/api/reactions',  // Replace with your actual backend endpoint for listing reactions
            type: 'GET',
            success: function (data) {
                console.log('List of reactions:', data);
                // Handle displaying the list on the UI if needed
            },
            error: function (error) {
                console.error('Error listing reactions:', error);
            }
        });
    }

    // Function to handle editing a reaction
    function editReaction(reactionId) {
        // Assume there's a form with fields for editing, replace with actual field IDs
        var updatedTimestamp = $("#updatedReactionTimestamp").val();
        var updatedType = $("#updatedReactionType").val();

        var updatedReaction = {
            timestamp: updatedTimestamp,
            type: updatedType
        };

        $.ajax({
            url: '/api/reactions/' + reactionId,  // Replace with your actual backend endpoint for updating reactions
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(updatedReaction),
            success: function (data) {
                console.log('Reaction updated successfully:', data);
                // You might want to update the UI or do something else after updating
            },
            error: function (error) {
                console.error('Error updating reaction:', error);
            }
        });
    }

    // Function to handle deleting a reaction
    function deleteReaction(reactionId) {
        $.ajax({
            url: '/api/reactions/' + reactionId,  // Replace with your actual backend endpoint for deleting reactions
            type: 'DELETE',
            success: function (data) {
                console.log('Reaction deleted successfully:', data);
                // You might want to update the UI or do something else after deleting
            },
            error: function (error) {
                console.error('Error deleting reaction:', error);
            }
        });
    }

    // Example usage of the functions
    listReactions();

});
