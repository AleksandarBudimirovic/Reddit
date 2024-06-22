    // Function to format date to a readable format
    function formatDate(dateString) {
        var date = new Date(dateString);
        return date.toLocaleDateString();
    }
    
 

document.addEventListener('DOMContentLoaded', function() {
    const addButton = document.getElementById('addCommunityModal');
    
    addButton.addEventListener('click', function() {
        // Redirect to addCommunity.html
        window.location.href = '/addCommunity.html';
    });
});

    