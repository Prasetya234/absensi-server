CREATE TABLE operational_class (
    id INT AUTO_INCREMENT PRIMARY KEY,
    entry_time VARCHAR(255) NOT NULL,
    dismissal_time VARCHAR(255) NOT NULL,
    duration_learn int NOT NULL,
    admission_day int NOT NULL,
    mark_by VARCHAR(255)
);

ALTER TABLE class_bootcamp ADD COLUMN operational_class_id INT;
ALTER TABLE class_bootcamp ADD CONSTRAINT fk_class_operational FOREIGN KEY(operational_class_id) REFERENCES operational_class(id);

