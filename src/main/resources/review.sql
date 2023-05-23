use enjoyTrip;

create table review (
	id bigint unsigned auto_increment NOT NULL,
	userId bigint unsigned NOT NULL,
	content_id int NOT NULL,
	rate INT unsigned,
	PRIMARY KEY (`id`),
	foreign key (userId) references user(id),
	foreign key (content_id) references attraction_info(content_id)
);
insert into review (userId, content_id, rate) values (1, 125266, 1);
insert into review (userId, content_id, rate) values (2, 125266, 2);
insert into review (userId, content_id, rate) values (3, 125266, 5);
insert into review (userId, content_id, rate) values (4, 125266, 5);
insert into review (userId, content_id, rate) values (5, 125266, 4);
insert into review (userId, content_id, rate) values (6, 125266, 4);
insert into review (userId, content_id, rate) values (7, 125266, 3);
