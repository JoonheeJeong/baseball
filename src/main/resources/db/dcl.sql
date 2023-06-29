CREATE USER 'fastcampus'@'localhost' IDENTIFIED BY 'fastcampus';

CREATE DATABASE fastcampus_baseball;

GRANT ALL PRIVILEGES ON fastcampus_baseball.* TO 'fastcampus'@'localhost';

FLUSH PRIVILEGES;
