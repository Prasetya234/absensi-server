CREATE TABLE going_home_early (
    id INT AUTO_INCREMENT PRIMARY KEY,
    note TEXT,
    date DATE NOT NULL,
    mark_by VARCHAR(255),
    class_bootcamp_id VARCHAR(255),
    CONSTRAINT fk_going_home_early_class FOREIGN KEY (class_bootcamp_id) REFERENCES class_bootcamp(id)
);