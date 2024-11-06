use dbCOnnection;

CREATE TABLE IF NOT EXISTS student_record (
    id SERIAL PRIMARY KEY,
    name VARCHAR(20) NOT NULL,
    doj VARCHAR(20)
);

INSERT INTO student_record (id, name, doj)
VALUES
    (1, 'Ashish', '16/12/1997'),
    (2, 'Mohit', '01/08/2000'),
    (3, 'Rohit', '04/01/1998');

select * from student_record;

SELECT * FROM student_record WHERE student_record.id = 3;
