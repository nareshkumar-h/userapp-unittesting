create table users ( id int primary key auto_increment, name varchar(100) not null,
email varchar(100) not null, password varchar(100) not null, unique(email));
