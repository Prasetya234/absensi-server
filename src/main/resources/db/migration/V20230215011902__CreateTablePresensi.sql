CREATE TABLE presensi(
    id VARCHAR(255)  PRIMARY KEY,
    face_number VARCHAR(255) NOT NULL,
    date_submit DATETIME NOT NULL,
    latitude VARCHAR(255),
    longitude VARCHAR(255),
    is_late TINYINT,
    permission_attend TINYINT DEFAULT 0,
    note VARCHAR(255),
    reason_id int,
    user_id VARCHAR(255),
    class_bootcamp_id VARCHAR(255),
    CONSTRAINT fk_presensi_reason FOREIGN KEY (reason_id) REFERENCES reason(id),
    CONSTRAINT fk_presensi_user FOREIGN KEY (user_id) REFERENCES user(id),
    CONSTRAINT fk_presensi_class FOREIGN KEY (class_bootcamp_id) REFERENCES class_bootcamp(id)
);