CREATE TABLE class_bootcamp
(
    id              VARCHAR(255) PRIMARY KEY,
    name            VARCHAR(255) NOT NULL,
    address         TEXT         NOT NULL,
    avatar_url      VARCHAR(255),
    lead_instructor VARCHAR(255) NOT NULL,
    foundation      VARCHAR(255) NOT NULL,
    number_phone    VARCHAR(255),
    total_student   INT,
    create_at       TIMESTAMP    NOT NULL,
    update_at       TIMESTAMP    NOT NULL
);

ALTER TABLE user
    ADD CONSTRAINT fk_user_class_bootcamp FOREIGN KEY (class_bootcamp_id) REFERENCES class_bootcamp (id);
