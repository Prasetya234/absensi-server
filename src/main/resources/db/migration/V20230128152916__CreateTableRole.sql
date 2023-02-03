CREATE TABLE role
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255)
);
ALTER TABLE user
    ADD CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES role (id);

INSERT INTO role
VALUES (1, 'STUDENT'),
       (2, 'INSTRUCTOR'),
       (3, 'ADMIN');