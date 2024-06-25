document.addEventListener("DOMContentLoaded", function() {
    console.log('DOM fully loaded and parsed');

    document.querySelectorAll('.like-btn, .dislike-btn').forEach(button => {
        button.addEventListener('click', function() {
            console.log('Button clicked:', this.textContent);

            const commentId = parseInt(this.closest('tr').getAttribute('data-comment-id'), 10);
            const postId = parseInt(this.closest('tr').getAttribute('data-post-id'), 10);
            const userId = parseInt(this.closest('tr').getAttribute('data-user-id'), 10);
            const type = this.classList.contains('like-btn') ? 'like' : 'dislike';

            console.log('Comment ID:', commentId);
            console.log('Post ID:', postId);
            console.log('User ID:', userId);
            console.log('Type:', type);

            // Prepare the data object to send to the server
            const requestData = {
                postId: postId,
                commentId: commentId,
                type: type
            };

            // Send the POST request using fetch
            fetch('/reactions/add', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(requestData)  // Convert requestData to JSON string
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                console.log('Reaction request sent successfully');
            })
            .catch(error => {
                console.error('Failed to add reaction:', error);
            });
        });
    });
});
