$(document).ready(function () {

    // Function to get community ID from URL
    function getCommunityIdFromUrl() {
        const urlParts = window.location.pathname.split('/');
        return urlParts[urlParts.length - 1];
    }

    // Fetch and display community details
    function fetchCommunityDetails(id) {
        fetch(`/details/${id}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                updateCommunityDetails(data);
            })
            .catch(error => {
                console.error('There was a problem with the fetch operation:', error);
            });
    }

    // Update HTML with community details
    function updateCommunityDetails(data) {
        document.getElementById('communityName').innerText = data.name;
        document.getElementById('communityDescription').innerText = data.description;
        document.getElementById('communityCreationDate').innerText = 'Creation Date: ' + new Date(data.creationDate).toLocaleDateString();
        
        if (data.isSuspended) {
            document.getElementById('communitySuspendedReason').innerText = 'Suspended Reason: ' + data.suspendedReason;
            document.getElementById('communitySuspendedReason').style.display = 'block';
            document.getElementById('communityNotSuspended').style.display = 'none';
        } else {
            document.getElementById('communitySuspendedReason').style.display = 'none';
            document.getElementById('communityNotSuspended').style.display = 'block';
        }
        
        document.getElementById('communityMembers').innerText = 'Members: ' + data.members.length;
        
        if (data.isMember) {
            document.getElementById('joinButton').style.display = 'none';
            document.getElementById('leaveButton').style.display = 'block';
        } else {
            document.getElementById('joinButton').style.display = 'block';
            document.getElementById('leaveButton').style.display = 'none';
        }
        
        displayPosts(data.posts);
    }

    // Display posts
    function displayPosts(posts) {
        const postsContainer = document.getElementById('postsContainer');
        postsContainer.innerHTML = '';
        
        posts.forEach(post => {
            const postElement = document.createElement('div');
            postElement.className = 'post';
            postElement.innerHTML = `
                <h3>${post.title}</h3>
                <p>Author: ${post.user.username} | Date: ${new Date(post.creationDate).toLocaleDateString()}</p>
                <p>${post.content}</p>
                <a class="btn btn-secondary" href="/post/${post.id}">Read More</a>
                <button class="btn btn-success" onclick="likePost(${post.id})">Like</button>
                <button class="btn btn-danger" onclick="dislikePost(${post.id})">Dislike</button>
            `;
            postsContainer.appendChild(postElement);
        });
    }

    // Function to join community
    function joinCommunity(communityId) {
        // Implement AJAX call to join community
    }

    // Function to leave community
    function leaveCommunity(communityId) {
        // Implement AJAX call to leave community
    }

    // Function to like post
    function likePost(postId) {
        // Implement AJAX call to like post
    }

    // Function to dislike post
    function dislikePost(postId) {
        // Implement AJAX call to dislike post
    }

    const communityId = getCommunityIdFromUrl();
    if (communityId) {
        fetchCommunityDetails(communityId);
    }
});
