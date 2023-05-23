create table blog (
	id bigint unsigned auto_increment primary key,
	userId bigint unsigned not null,
	userName VARCHAR(50) not null,
	title VARCHAR(50) not null,
    content json NOT NULL DEFAULT ('{}'),
    	foreign key (userId) references user(id)

);
insert into blog (userId, userName, title) values (2, 'ssafy', 'The Road to Glory');
insert into blog (userId, userName, title) values (2, 'ssafy', 'Harry potter');
