CREATE TABLE messages (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sender_name VARCHAR(255),
    receiver_name VARCHAR(255),
    message TEXT,
    date VARCHAR(255),
    status ENUM('JOIN', 'MESSAGE', 'LEAVE'),
    school_id VARCHAR(255)
);