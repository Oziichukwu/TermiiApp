create database termii_db;

create user 'termii_user'@'localhost'identified by 'password';

grant all privileges on termii_db.* to 'termii_user'@'localhost';

flush privileges;