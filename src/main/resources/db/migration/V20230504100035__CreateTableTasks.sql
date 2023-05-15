CREATE TABLE tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    note VARCHAR(255),
    assignment_date DATETIME NOT NULL,
    due_date DATETIME NOT NULL,
    create_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    update_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    user_id VARCHAR(255),
    class_bootcamp_id VARCHAR(255),
    CONSTRAINT fk_tasks_user FOREIGN KEY (user_id) REFERENCES user(id),
    CONSTRAINT fk_tasks_class FOREIGN KEY (class_bootcamp_id) REFERENCES school(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;