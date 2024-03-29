CREATE TABLE todo_list (
    id INT AUTO_INCREMENT PRIMARY KEY,
    note VARCHAR(255),
    status BOOLEAN DEFAULT 0,
    create_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    update_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    user_id VARCHAR(255),
    CONSTRAINT fk_todo_list_user FOREIGN KEY (user_id) REFERENCES user(id)
);