
insert into korisnik (id, korisnicko_ime, lozinka,uloga) values (1,'admin', '123', 'ADMIN' );

use redditbase;

insert into ban (id, timestamp, community, user) values(1, '2022-01-20', 2, 3);
insert into ban (id, timestamp, community, user) values(2, '2022-01-20', 2, 4);

use redditbase;

insert into comment(id, is_deleted, text, timestamp, post, user, main_comment) values (1, false, 'some text', '2012-02-10', 1, 1, 'This is a main comment');
insert into comment(id, is_deleted, text, timestamp, post, user, main_comment) values (2, false, 'some other text', '2012-02-10', 1, 1, 'This is also a main comment');

use redditbase;

insert into community(id, creation_date, description, is_suspended, suspended_reason, user) values (1, '2008-02-10', 'Garden of Eve', false, NULL, 1);
insert into community(id, creation_date, description, is_suspended, suspended_reason, user) values (1, '2008-02-10', 'Cat memes', false, NULL, 1);
insert into community(id, creation_date, description, is_suspended, suspended_reason, user) values (1, '2008-02-10', 'More cat memes', false, NULL, 2);
insert into community(id, creation_date, description, is_suspended, suspended_reason, user) values (1, '2008-02-10', 'Cat memes but fast', false, NULL, 2);

user redditbase;

insert into flair(id, name, community, post) values (1, 'like', 1, 1);
insert into flair(id, name, community, post) values (2, 'like', 2, 1);
insert into flair(id, name, community, post) values (3, 'like', 3, 1);
insert into flair(id, name, community, post) values (4, 'like', 4, 1);

user redditbase;

insert into post(id, creation_date, image_path, title, text, community, user) values (1, '2022-01-20', NULL, 'Pretty_nature', 'An image of pretty nature hopefully', 1, 1);
insert into post(id, creation_date, image_path, title, text, community, user) values (2, '2022-01-20', NULL, 'Cat video number 9487', 'New phone who dis', 2, 1);
insert into post(id, creation_date, image_path, title, text, community, user) values (3, '2022-01-20', NULL, 'Pawsies', 'I like trains', 2, 2);
insert into post(id, creation_date, image_path, title, text, community, user) values (4, '2022-01-20', NULL, 'Cat video number 9488', 'An image of a cat hopefully', 3, 2);

user redditbase;

insert into reaction(id, timestamp, type, comment, post, user) values (1, '2022-01-20', 'type1', 1, 1, 1);
insert into reaction(id, timestamp, type, comment, post, user) values (1, '2022-01-20', 'type2', 2, 2, 2);

user redditbase;

insert into report(id, accepted, reason, timestamp, comment, post) values(1, false, 'False accusation', '2022-01-20', 1, 1);
insert into report(id, accepted, reason, timestamp, comment, post) values(2, false, 'False accusation', '2022-01-20', 2, 2);

user redditbase;

insert into user(id, avatar, description, display_name, password, registration_date, role, username) values(1, NULL, 'Number 1 admin around', 'Admin007', 'admin', '2008-02-9', 'ADMIN', 'Admin007');
insert into user(id, avatar, description, display_name, password, registration_date, role, username) values(2, NULL, 'Something definitely not weird', 'Jared', 'jerry', '2008-02-9', 'USER', 'jerry');
insert into user(id, avatar, description, display_name, password, registration_date, role, username) values(3, NULL, 'The Jorris experience', 'Jorris', 'bonson', '2008-02-9', 'USER', 'Jorris');







