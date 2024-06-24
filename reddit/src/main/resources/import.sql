USE redditbase;

INSERT INTO user (id, avatar, description, display_name, password, registration_date, role, username) VALUES (1, 'user3_avatar.jpg', 'Description for User 3', 'User 3', 'password789', '2022-01-03', 'ROLE_USER', 'user3');
INSERT INTO user (id, avatar, description, display_name, password, registration_date, role, username) VALUES (2, 'user4_avatar.jpg', 'Description for User 4', 'User 4', 'password101', '2022-01-04', 'ROLE_USER', 'user4');
INSERT INTO user (id, avatar, description, display_name, password, registration_date, role, username) VALUES (3, 'user5_avatar.jpg', 'Description for User 5', 'User 5', 'password112', '2022-01-05', 'ROLE_USER', 'user5');
INSERT INTO user (id, avatar, description, display_name, password, registration_date, role, username) VALUES (4, 'user6_avatar.jpg', 'Description for User 6', 'User 6', 'password123', '2022-01-06', 'ROLE_USER', 'user6');

INSERT INTO community(id, creation_date, description, is_suspended, suspended_reason, user_id) VALUES (1, '2008-02-10', 'Garden of Eve', 0, NULL, 1);
INSERT INTO community(id, creation_date, description, is_suspended, suspended_reason, user_id) VALUES (2, '2008-02-10', 'Cat memes', 0, NULL, 1);
INSERT INTO community(id, creation_date, description, is_suspended, suspended_reason, user_id) VALUES (3, '2008-02-10', 'More cat memes', 0, NULL, 2);
INSERT INTO community(id, creation_date, description, is_suspended, suspended_reason, user_id) VALUES (4, '2008-02-10', 'Cat memes but fast', 0, NULL, 2);
INSERT INTO community(id, creation_date, description, is_suspended, suspended_reason, user_id) VALUES (5, '2008-02-10', 'Programming Wizards', 0, NULL, 1);
INSERT INTO community(id, creation_date, description, is_suspended, suspended_reason, user_id) VALUES (6, '2008-02-10', 'Tech Enthusiasts', 0, NULL, 1);

INSERT INTO post(id, creation_date, image_path, title, text, community_id, user_id) VALUES (1, '2022-01-20', NULL, 'Pretty_nature', 'An image of pretty nature hopefully', 1, 1);
INSERT INTO post(id, creation_date, image_path, title, text, community_id, user_id) VALUES (2, '2022-01-20', NULL, 'Cat video number 9487', 'New phone who dis', 2, 1);
INSERT INTO post(id, creation_date, image_path, title, text, community_id, user_id) VALUES (3, '2022-01-20', NULL, 'Pawsies', 'I like trains', 2, 2);
INSERT INTO post(id, creation_date, image_path, title, text, community_id, user_id) VALUES (4, '2022-01-20', NULL, 'Cat video number 9488', 'An image of a cat hopefully', 3, 2);
INSERT INTO post(id, creation_date, image_path, title, text, community_id, user_id) VALUES (5, '2022-01-21', NULL, 'Sunset Views', 'Beautiful sunset views from my balcony', 1, 1);
INSERT INTO post(id, creation_date, image_path, title, text, community_id, user_id) VALUES (6, '2022-01-21', NULL, 'Coding Life', 'Late-night coding session. Anyone else awake?', 2, 2);

INSERT INTO comment(id, is_deleted, text, timestamp, post_id, user_id) VALUES (1, 0, 'some text', '2012-02-10', 1, 1);
INSERT INTO comment(id, is_deleted, text, timestamp, post_id, user_id) VALUES (2, 0, 'some other text', '2012-02-10', 1, 1);
INSERT INTO comment(id, is_deleted, text, timestamp, post_id, user_id) VALUES (3, 0, 'another text', '2012-02-10', 1, 1);
INSERT INTO comment(id, is_deleted, text, timestamp, post_id, user_id) VALUES (4, 0, 'more text', '2012-02-10', 1, 1);
INSERT INTO comment(id, is_deleted, text, timestamp, post_id, user_id) VALUES (5, 0, 'additional text', '2012-02-10', 1, 1);
INSERT INTO comment(id, is_deleted, text, timestamp, post_id, user_id) VALUES (6, 0, 'extra text', '2012-02-10', 1, 1);

INSERT INTO reaction(id, timestamp, type, comment_id, post_id, user_id) VALUES (1, '2022-01-20', 'type1', 1, 1, 1);
INSERT INTO reaction(id, timestamp, type, comment_id, post_id, user_id) VALUES (2, '2022-01-20', 'type2', 2, 2, 2);
INSERT INTO reaction(id, timestamp, type, comment_id, post_id, user_id) VALUES (3, '2022-01-21', 'type1', 1, 2, 1);
INSERT INTO reaction(id, timestamp, type, comment_id, post_id, user_id) VALUES (4, '2022-01-21', 'type2', 1, 2, 2);
INSERT INTO reaction(id, timestamp, type, comment_id, post_id, user_id) VALUES (5, '2022-01-22', 'type1', 1, 2, 2);
INSERT INTO reaction(id, timestamp, type, comment_id, post_id, user_id) VALUES (6, '2022-01-22', 'type2', 2, 1, 1);

INSERT INTO ban(id, timestamp, community_id, user_id) VALUES(1, '2022-01-20', 2, 3);
INSERT INTO ban(id, timestamp, community_id, user_id) VALUES(2, '2022-01-20', 2, 4);

INSERT INTO report(id, accepted, reason, timestamp, comment_id, post_id) VALUES(1, false, 'False accusation', '2022-01-20', 1, 1);
INSERT INTO report(id, accepted, reason, timestamp, comment_id, post_id) VALUES(2, false, 'False accusation', '2022-01-20', 2, 2);
INSERT INTO report(id, accepted, reason, timestamp, comment_id, post_id) VALUES(3, true, 'Spam', '2022-01-21', 3, 2);
INSERT INTO report(id, accepted, reason, timestamp, comment_id, post_id) VALUES(4, true, 'Inappropriate content', '2022-01-21', 2, 1);
INSERT INTO report(id, accepted, reason, timestamp, comment_id, post_id) VALUES(5, false, 'False accusation', '2022-01-22', 1, 1);
INSERT INTO report(id, accepted, reason, timestamp, comment_id, post_id) VALUES(6, false, 'False accusation', '2022-01-22', 2, 2);

INSERT INTO flair (id, name, community_id) VALUES (1, 'Flair 1', 1);
INSERT INTO flair (id, name, community_id) VALUES (2, 'Flair 2', 2);
INSERT INTO flair (id, name, community_id) VALUES (3, 'Flair 3', 1);
INSERT INTO flair (id, name, community_id) VALUES (4, 'Flair 4', 2);
