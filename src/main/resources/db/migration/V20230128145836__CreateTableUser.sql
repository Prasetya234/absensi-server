CREATE TABLE user (
    id                  VARCHAR(255) PRIMARY KEY,
    no_siswa            INT,
    first_name          VARCHAR(255) NOT NULL,
    last_name           VARCHAR(255) NOT NULL,
    password            VARCHAR(255) NOT NULL,
    email               VARCHAR(255) NOT NULL,
    avatar_url          VARCHAR(255),
    school_class        VARCHAR(255),
    batch               INT,
    gender              ENUM('MALE', 'FEMALE'),
    birth_date          DATE,
    favorite            TINYINT,
    role_id             INT,
    description         TEXT,
    class_bootcamp_id   VARCHAR(255),
    create_at           TIMESTAMP    NOT NULL,
    update_at           TIMESTAMP    NOT NULL
);