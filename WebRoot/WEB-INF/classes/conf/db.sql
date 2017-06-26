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
CREATE TABLE `file` (
  `file_id` int(11) NOT NULL AUTO_INCREMENT,
  `file_name` varchar(200) CHARACTER SET latin1 NOT NULL,
  `file_md5` varchar(32) CHARACTER SET latin1 NOT NULL,
  `file_path` varchar(500) CHARACTER SET latin1 NOT NULL,
  `file_type` int(11) NOT NULL DEFAULT '0',
  `file_parent` int(11) DEFAULT NULL,
  `file_user` int(11) NOT NULL,
  `file_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`file_id`),
  KEY `file_parent_fk` (`file_parent`),
  KEY `file_user_fk` (`file_user`),
  CONSTRAINT `file_parent_fk` FOREIGN KEY (`file_parent`) REFERENCES `file` (`file_id`),
  CONSTRAINT `file_user_fk` FOREIGN KEY (`file_user`) REFERENCES `user` (`id`)
)CHARSET=utf8;
