CREATE TABLE token_temporary
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    token           VARCHAR(255) NOT NULL,
    expired_date    DATETIME,
    validity_period VARCHAR(255),
    user_id         VARCHAR(255),
    CONSTRAINT fk_temporary_token_user FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE
);