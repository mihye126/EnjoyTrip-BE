create table notice (
	id bigint unsigned auto_increment primary key,
	title VARCHAR(30) not null ,
	content VARCHAR(1000)not null  ,
	writeDate datetime default current_timestamp
);
insert into notice (title, content) values ('게시판테스트1', '게시판테스트1');
insert into notice (title, content) values ('게시판테스트2', '게시판테스트2');
insert into notice (title, content) values ('게시판테스트3', '게시판테스트2');
insert into notice (title, content) values ('게시판테스트4', '게시판테스트2');
insert into notice (title, content) values ('게시판테스트5', '게시판테스트2');
insert into notice (title, content) values ('게시판테스트6', '게시판테스트2');
insert into notice (title, content) values ('게시판테스트7', '게시판테스트2');
insert into notice (title, content) values ('게시판테스트8', '게시판테스트2');
insert into notice (title, content) values ('게시판테스트9', '게시판테스트2');
insert into notice (title, content) values ('게시판테스트10', '게시판테스트2');
insert into notice (title, content) values ('게시판테스트11', '게시판테스트2');
insert into notice (title, content) values ('게시판테스트12', '게시판테스트2');
insert into notice (title, content) values ('게시판테스트13', '게시판테스트2');
insert into notice (title, content) values ('게시판테스트14', '게시판테스트2');
insert into notice (title, content) values ('게시판테스트15', '게시판테스트2');
insert into notice (title, content) values ('게시판테스트16', '게시판테스트2');
insert into notice (title, content) values ('게시판테스트17', '게시판테스트2');
