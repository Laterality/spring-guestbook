DROP TABLE GUESTBOOK_POST IF EXISTS;
CREATE TABLE GUESTBOOK_POST
(
    id      bigint auto_increment not null,
    content varchar(255)          not null,
    primary key (id)
);
