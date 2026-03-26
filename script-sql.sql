CREATE DATABASE seguranca;

USE seguranca;

CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100),
    password VARCHAR(255),
    role VARCHAR(20)
);

select * from seguranca.`user` u;

INSERT INTO seguranca.`user` (username, password, role)
VALUES (
  'admin',
  '$2a$10$7QJQp0S8zWzQz7x1YkJY2uF9uF8s1J6P6lFhZ7mG5Rz3YpQ5Kz9eG', // 1234
  'ROLE_ADMIN'
);