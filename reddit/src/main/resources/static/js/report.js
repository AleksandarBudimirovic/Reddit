$(document).ready(function () {



    $("#addReport").click(function (e) {
        var accepted = $("#reportAccepted").val();
        var reason = $("#reportReason").val();
        var timestamp = $("#reportTimestamp").val();
        var commentId = $("#reportCommentId").val();
        var postId = $("#reportPostId").val();

        var newReport = {
            accepted: accepted,
            reason: reason,
            timestamp: timestamp,
            Comment: { id: commentId },
            Post: { id: postId }
        };

        $.ajax({
            url: '/api/reports',  // Replace with your actual backend endpoint
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(newReport),
            success: function (data) {
                console.log('Report added successfully:', data);
            },
            error: function (error) {
                console.error('Error adding report:', error);
            }
        });
    });

    function listReports() {
        $.ajax({
            url: '/api/reports',  // Replace with your actual backend endpoint for listing reports
            type: 'GET',
            success: function (data) {
                console.log('List of reports:', data);
                // Handle displaying the list on the UI if needed
            },
            error: function (error) {
                console.error('Error listing reports:', error);
            }
        });
    }

    // Function to handle editing a report
    function editReport(reportId) {
        // Assume there's a form with fields for editing, replace with actual field IDs
        var updatedAccepted = $("#updatedReportAccepted").val();
        var updatedReason = $("#updatedReportReason").val();
        var updatedTimestamp = $("#updatedReportTimestamp").val();

        var updatedReport = {
            accepted: updatedAccepted,
            reason: updatedReason,
            timestamp: updatedTimestamp
        };

        $.ajax({
            url: '/api/reports/' + reportId,  // Replace with your actual backend endpoint for updating reports
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(updatedReport),
            success: function (data) {
                console.log('Report updated successfully:', data);
                // You might want to update the UI or do something else after updating
            },
            error: function (error) {
                console.error('Error updating report:', error);
            }
        });
    }

    // Function to handle deleting a report
    function deleteReport(reportId) {
        $.ajax({
            url: '/api/reports/' + reportId,  // Replace with your actual backend endpoint for deleting reports
            type: 'DELETE',
            success: function (data) {
                console.log('Report deleted successfully:', data);
                // You might want to update the UI or do something else after deleting
            },
            error: function (error) {
                console.error('Error deleting report:', error);
            }
        });
    }

    // Example usage of the functions
    listReports();

});
