CREATE DATABASE IF NOT EXISTS complaint_management_system;
USE complaint_management_system;

CREATE TABLE IF NOT EXISTS users (
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role ENUM('employee','admin') NOT NULL,
    PRIMARY KEY (id)
    );

INSERT INTO users (id, username, password, role) VALUES
    (1, 'tharusha', 'tharu', 'admin'),
    (2, 'sachini', '1234', 'employee');

CREATE TABLE IF NOT EXISTS complaints (
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    subject VARCHAR(255),
    description TEXT,
    status ENUM('pending','in_progress','resolved') DEFAULT 'pending',
    remarks TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
    );

INSERT INTO complaints (id, user_id, subject, description, status, remarks, created_at) VALUES
    (1, 1, 'Network Issue', 'Internet is down in my department.', 'resolved', 'Issue fixed on 13 June', '2025-06-13 14:27:20'),
    (13, 2, 'Management', 'manager is really bad', 'resolved', 'we will change the manager', '2025-06-14 12:48:30');
