create table users(username varchar_ignorecase(50) not null primary key,password varchar_ignorecase(500) not null,enabled boolean not null);
create table authorities (username varchar_ignorecase(50) not null,authority varchar_ignorecase(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authorities (username,authority);


INSERT IGNORE INTO users
VALUES('user', '{noop}nagendra@987', 1);

INSERT IGNORE INTO authorities
VALUES('user', 'read');

INSERT IGNORE INTO users
VALUES(
'admin',
'{bcrypt}$2a$12$LbQIq.P.XP7i.RFyA28HF.YAVX.PF1UNmPP5oASGbY3lxfmgFzB4i',
1
);

INSERT IGNORE INTO authorities
VALUES('admin', 'admin');


CREATE TABLE customer (
  id INT NOT NULL AUTO_INCREMENT,
  email VARCHAR(50) NOT NULL,
  pwd VARCHAR(300) NOT NULL,
  role VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

INSERT  INTO customer (email, pwd, role)
VALUES('example@gmail.com', '{noop}nagendra@987', 'read');

INSERT  INTO customer (email, pwd, role)
VALUES('admin@gmail.com', '{bcrypt}$2a$12$LbQIq.P.XP7i.RFyA28HF.YAVX.PF1UNmPP5oASGbY3lxfmgFzB4i', 'admin');