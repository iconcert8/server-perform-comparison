create table IF NOT EXISTS users (
    id bigint auto_increment,
    password varchar(255),
    username varchar(255),
    primary key (id)
);