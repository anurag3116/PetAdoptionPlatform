CREATE DATABASE IF NOT EXISTS pet_adoption;
USE pet_adoption;

CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(20) NOT NULL,
    status VARCHAR(20) DEFAULT 'ACTIVE'
);

CREATE TABLE IF NOT EXISTS pets (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    breed VARCHAR(100),
    age INT,
    shelter_id INT,
    status VARCHAR(20) DEFAULT 'PENDING'
);

CREATE TABLE IF NOT EXISTS applications (
    id INT PRIMARY KEY AUTO_INCREMENT,
    pet_id INT,
    adopter_id INT,
    message VARCHAR(500),
    status VARCHAR(20) DEFAULT 'PENDING'
);

CREATE TABLE IF NOT EXISTS messages (
    id INT PRIMARY KEY AUTO_INCREMENT,
    sender_id INT,
    receiver_id INT,
    message VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS settings (
    id INT PRIMARY KEY AUTO_INCREMENT,
    setting_name VARCHAR(100),
    setting_value VARCHAR(255)
);

INSERT INTO users (name,email,password,role,status)
VALUES ('Admin','admin@gmail.com','admin123','ADMIN','ACTIVE')
ON DUPLICATE KEY UPDATE email=email;
