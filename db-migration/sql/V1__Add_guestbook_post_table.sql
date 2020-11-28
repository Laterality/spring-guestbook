CREATE TABLE guestbook_post
(
    id      bigint auto_increment not null,
    content varchar(255)          not null,
    primary key (id)
);
