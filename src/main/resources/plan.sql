create table plan (
	id bigint unsigned auto_increment primary key,
	userName VARCHAR(50) not null,
	userId bigint unsigned not null,
	attactions JSON NOT NULL DEFAULT ('[]'),
	foreign key (userId) references user(id)
);
insert into plan (userName, userId, attactions) values ('ssafy', 2, '[125266, 125405, 125406, 125407, 125408]');
insert into plan (userName, userId, attactions) values ('ssafy', 2, '[125266, 125405, 125406]');
insert into plan (userName, userId, attactions) values ('ssafy', 2, '[125266, 125405, 125406, 125408]');
