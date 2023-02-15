CREATE TABLE face_user (
    id VARCHAR(255) PRIMARY KEY,
    avatar_url VARCHAR(255),
    detector_score TEXT NOT NULL,
    user_id VARCHAR(255),
    CONSTRAINT fk_face_user FOREIGN KEY (user_id) REFERENCES user(id)
);