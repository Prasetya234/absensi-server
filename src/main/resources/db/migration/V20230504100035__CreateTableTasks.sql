CREATE TABLE tasks (
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    note                VARCHAR(255),
    assignment_date     DATE,
    due_date            DATE,
    task_type           ENUM('JOINTLY', 'INDIVIDUAL'),
    is_closed           TINYINT,
    user_id             VARCHAR(255),
    class_bootcamp_id   VARCHAR(255),
    create_at           TIMESTAMP NOT NULL DEFAULT current_timestamp(),
    update_at           TIMESTAMP NOT NULL DEFAULT current_timestamp(),
    CONSTRAINT fk_tasks_user FOREIGN KEY (user_id) REFERENCES user(id),
    CONSTRAINT fk_tasks_class FOREIGN KEY (class_bootcamp_id) REFERENCES school(id)
) ;