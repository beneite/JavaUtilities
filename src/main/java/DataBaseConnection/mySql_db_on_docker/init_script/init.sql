-- Create the database first
CREATE DATABASE IF NOT EXISTS students_db;

-- Create the user (if it doesn't exist)
CREATE USER IF NOT EXISTS 'ashish'@'%' IDENTIFIED BY 'ashish@123';

-- Grant privileges to the user on all databases
GRANT ALL PRIVILEGES ON *.* TO 'ashish'@'%' WITH GRANT OPTION;

-- Switch to the database
USE students_db;

-- Create the table if it doesn't exist
CREATE TABLE IF NOT EXISTS student_record (
    id SERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    doj VARCHAR(20)
);

-- Insert initial data
INSERT INTO student_record (id, name, doj)
VALUES
    (1, 'Ashish', '16/12/1997'),
    (2, 'Mohit', '01/08/2000'),
    (3, 'Rohit', '04/01/1998');

-- Flush privileges to ensure the user is recognized
FLUSH PRIVILEGES;