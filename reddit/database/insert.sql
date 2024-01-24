USE redditbase;

insert into ban(id, timestamp, community_id, user_id) values(1, '2022-01-20', 2, 3);
insert into ban(id, timestamp, community_id, user_id) values(2, '2022-01-20', 2, 4);

insert into comment(id, is_deleted, text, timestamp, post_id, user_id, main_comment_id) values (1, 0, 'some text', '2012-02-10', 1, 1, 1);
insert into comment(id, is_deleted, text, timestamp, post_id, user_id, main_comment_id) values (2, 0, 'some other text', '2012-02-10', 1, 1, 2);
insert into comment(id, is_deleted, text, timestamp, post_id, user_id, main_comment_id) values (3, 0, 'another text', '2012-02-10', 1, 1, 1);
insert into comment(id, is_deleted, text, timestamp, post_id, user_id, main_comment_id) values (4, 0, 'more text', '2012-02-10', 1, 1, 2);
insert into comment(id, is_deleted, text, timestamp, post_id, user_id, main_comment_id) values (5, 0, 'additional text', '2012-02-10', 1, 1, 1);
insert into comment(id, is_deleted, text, timestamp, post_id, user_id, main_comment_id) values (6, 0, 'extra text', '2012-02-10', 1, 1, 2);

insert into community(id, creationDate, description, isSuspended, suspendedReason, user_id) values (1, '2008-02-10', 'Garden of Eve', 0, NULL, 1);
insert into community(id, creationDate, description, isSuspended, suspendedReason, user_id) values (2, '2008-02-10', 'Cat memes', 0, NULL, 1);
insert into community(id, creationDate, description, isSuspended, suspendedReason, user_id) values (3, '2008-02-10', 'More cat memes', 0, NULL, 2);
insert into community(id, creationDate, description, isSuspended, suspendedReason, user_id) values (4, '2008-02-10', 'Cat memes but fast', 0, NULL, 2);
insert into community(id, creationDate, description, isSuspended, suspendedReason, user_id) values (5, '2008-02-10', 'Programming Wizards', 0, NULL, 1);
insert into community(id, creationDate, description, isSuspended, suspendedReason, user_id) values (6, '2008-02-10', 'Tech Enthusiasts', 0, NULL, 1);

insert into flair(id, name, community_id, post_id) values (1, 'like', 1, 1);
insert into flair(id, name, community_id, post_id) values (2, 'like', 2, 1);
insert into flair(id, name, community_id, post_id) values (3, 'like', 3, 1);
insert into flair(id, name, community_id, post_id) values (4, 'like', 4, 1);
insert into flair(id, name, community_id, post_id) values (5, 'dislike', 1, 1);
insert into flair(id, name, community_id, post_id) values (6, 'love', 2, 1);


insert into post(id, creationDate, imagePath, title, text, community_id, user_id) values (1, '2022-01-20', NULL, 'Pretty_nature', 'An image of pretty nature hopefully', 1, 1);
insert into post(id, creationDate, imagePath, title, text, community_id, user_id) values (2, '2022-01-20', NULL, 'Cat video number 9487', 'New phone who dis', 2, 1);
insert into post(id, creationDate, imagePath, title, text, community_id, user_id) values (3, '2022-01-20', NULL, 'Pawsies', 'I like trains', 2, 2);
insert into post(id, creationDate, imagePath, title, text, community_id, user_id) values (4, '2022-01-20', NULL, 'Cat video number 9488', 'An image of a cat hopefully', 3, 2);
insert into post(id, creationDate, imagePath, title, text, community_id, user_id) values (5, '2022-01-21', NULL, 'Sunset Views', 'Beautiful sunset views from my balcony', 1, 1);
insert into post(id, creationDate, imagePath, title, text, community_id, user_id) values (6, '2022-01-21', NULL, 'Coding Life', 'Late-night coding session. Anyone else awake?', 2, 2);


insert into reaction(id, timestamp, type, comment_id, post_id, user_id) values (1, '2022-01-20', 'type1', 1, NULL, 1);
insert into reaction(id, timestamp, type, comment_id, post_id, user_id) values (2, '2022-01-20', 'type2', 2, NULL, 2);
insert into reaction(id, timestamp, type, comment_id, post_id, user_id) values (3, '2022-01-21', 'type1', 1, NULL, 1);
insert into reaction(id, timestamp, type, comment_id, post_id, user_id) values (4, '2022-01-21', 'type2', NULL, 2, 2);
insert into reaction(id, timestamp, type, comment_id, post_id, user_id) values (5, '2022-01-22', 'type1', 1, NULL, 2);
insert into reaction(id, timestamp, type, comment_id, post_id, user_id) values (6, '2022-01-22', 'type2', 2, NULL, 1);

insert into report(id, accepted, reason, timestamp, comment_id, post_id) values(1, false, 'False accusation', '2022-01-20', 1, 1);
insert into report(id, accepted, reason, timestamp, comment_id, post_id) values(2, false, 'False accusation', '2022-01-20', 2, 2);
insert into report(id, accepted, reason, timestamp, comment_id, post_id) values(3, true, 'Spam', '2022-01-21', 3, NULL);
insert into report(id, accepted, reason, timestamp, comment_id, post_id) values(4, true, 'Inappropriate content', '2022-01-21', NULL, 1);
insert into report(id, accepted, reason, timestamp, comment_id, post_id) values(5, false, 'False accusation', '2022-01-22', 1, NULL);
insert into report(id, accepted, reason, timestamp, comment_id, post_id) values(6, false, 'False accusation', '2022-01-22', 2, 2);
