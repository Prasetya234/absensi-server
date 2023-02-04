CREATE TABLE calender(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name TEXT NOT NULL,
    date DATE NOT NULL,
    is_holiday TINYINT,
    class_bootcamp_id VARCHAR(255),
    maker VARCHAR(255),
    CONSTRAINT fk_calender_class_bootcamp FOREIGN KEY (class_bootcamp_id) REFERENCES class_bootcamp(id)
);