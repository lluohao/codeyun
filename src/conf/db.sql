CREATE TABLE USER(
	id int primary key auto_increment,
	name varchar(100) unique,
	password varchar(64) not null,
	email varchar(128) unique,
	phone varchar(11) unique not null,
	time Timestamp
)charset='utf8'

CREATE TABLE PHONECODE(
	cid int primary key auto_increment,
	cphone varchar(20) not null,
	ctime Date not null,
	cvalue varchar(20) not null,
	ckey varchar(100)
)charset='utf8';