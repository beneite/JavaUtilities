-- Create the database if not already created (optional since it is specified in the environment variable)
CREATE DATABASE IF NOT EXISTS students_db;

-- Switch to the database (optional)
-- USE students_db;

-- creating table students_table
CREATE TABLE IF NOT EXISTS students_table(
    reg_no INT PRIMARY KEY,
    roll_no INTEGER NOT NULL,
    full_name VARCHAR(20) NOT NULL,
    class VARCHAR(20) NOT NULL
);

-- creating table students_marks
CREATE TABLE IF NOT EXISTS students_marks(
    reg_no INTEGER,
    percentage VARCHAR(4),
    CONSTRAINT fk_reg_no FOREIGN KEY (reg_no) REFERENCES students_table(reg_no)
);

-- inserting records in students_table
INSERT INTO students_table(reg_no, roll_no, full_name, class)
VALUES
(1, 12, 'Kamlesh', '10th'),
(2, 28, 'Umesh', '9th');

-- inserting records in students_marks
INSERT INTO students_marks (reg_no, percentage)
VALUES
(1, '89%'),
(2, '92%');