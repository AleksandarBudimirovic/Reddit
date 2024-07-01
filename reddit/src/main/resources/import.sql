USE redditbase;

-- Ensure the 'user' table is created first before referencing it in other tables.
INSERT INTO user (id, avatar, description, display_name, password, registration_date, role, username, banned) 
VALUES 
(1, 'user3_avatar.jpg', 'Description for User 3', 'User 3', 'password789', '2022-01-03', 'ROLE_USER', 'user3', false),
(2, 'user4_avatar.jpg', 'Description for User 4', 'User 4', 'password101', '2022-01-04', 'ROLE_USER', 'user4', false),
(3, 'user5_avatar.jpg', 'Description for User 5', 'User 5', 'password112', '2022-01-05', 'ROLE_USER', 'user5', false),
(4, 'user6_avatar.jpg', 'Description for User 6', 'User 6', 'password123', '2022-01-06', 'ROLE_USER', 'user6', false);

INSERT INTO community (id, creation_date, name, description, is_suspended, suspended_reason, user_id)
VALUES 
(1, '2008-02-10', 'Garden of Eve', 'A community for garden enthusiasts.', 0, NULL, 1),
(2, '2008-02-10', 'Cat memes', 'A place to share and enjoy cat memes.', 0, NULL, 1),
(3, '2008-02-10', 'More cat memes', 'More cat memes for the cat meme lovers.', 0, NULL, 2),
(4, '2008-02-10', 'Cat memes but fast', 'Quick cat memes for your daily dose.', 0, NULL, 2),
(5, '2008-02-10', 'Programming Wizards', 'A community for programming enthusiasts and wizards.', 0, NULL, 1),
(6, '2008-02-10', 'Tech Enthusiasts', 'A hub for tech enthusiasts and professionals.', 0, NULL, 1);

INSERT INTO post(id, creation_date, image_path, title, text, community_id, user_id) VALUES 
(1, '2022-01-20', NULL, 'Pretty_nature', 'An image of pretty nature hopefully', 1, 1),
(2, '2022-01-20', NULL, 'Cat video number 9487', 'New phone who dis', 2, 1),
(3, '2022-01-20', NULL, 'Pawsies', 'I like trains', 2, 2),
(4, '2022-01-20', NULL, 'Cat video number 9488', 'An image of a cat hopefully', 3, 2),
(5, '2022-01-21', NULL, 'Sunset Views', 'Beautiful sunset views from my balcony', 1, 1),
(6, '2022-01-21', NULL, 'Coding Life', 'Late-night coding session. Anyone else awake?', 2, 2);

INSERT INTO comment(id, is_deleted, text, timestamp, post_id, user_id) VALUES 
(1, 0, 'some text', '2012-02-10', 1, 1),
(2, 0, 'some other text', '2012-02-10', 1, 1),
(3, 0, 'another text', '2012-02-10', 1, 1),
(4, 0, 'more text', '2012-02-10', 1, 1),
(5, 0, 'additional text', '2012-02-10', 1, 1),
(6, 0, 'extra text', '2012-02-10', 1, 1);

INSERT INTO reaction(id, timestamp, type, comment_id, post_id, user_id) VALUES 
(1, '2022-01-20', 'type1', 1, 1, 1),
(2, '2022-01-20', 'type2', 2, 2, 2),
(3, '2022-01-21', 'type1', 1, 2, 1),
(4, '2022-01-21', 'type2', 1, 2, 2),
(5, '2022-01-22', 'type1', 1, 2, 2),
(6, '2022-01-22', 'type2', 2, 1, 1);

INSERT INTO ban(id, timestamp, community_id, user_id) VALUES
(1, '2022-01-20', 2, 3),
(2, '2022-01-20', 2, 4);

INSERT INTO report(id, accepted, reason, timestamp, comment_id, post_id, reported_user_id) VALUES 
(1, false, 'False accusation', '2022-01-20', 1, null, null),
(2, false, 'False accusation', '2022-01-20', 2, null, null),
(3, true, 'Spam', '2022-01-21', null, 2, null),
(4, true, 'Inappropriate content', '2022-01-21', null, 1, null),
(5, false, 'False accusation', '2022-01-22', 1, null, null),
(6, false, 'False accusation', '2022-01-22', 2, null, null);

INSERT INTO flair (id, name, community_id) VALUES 
(1, 'Flair 1', 1),
(2, 'Flair 2', 2),
(3, 'Flair 3', 1);
