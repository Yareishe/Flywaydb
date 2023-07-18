INSERT INTO worker (NAME, BIRTHDAY, LEVEL, SALARY)
VALUES
    (CONCAT('John', FLOOR(RAND()*100)), '1990-05-15', 'Senior', FLOOR(RAND()*100000)),
    (CONCAT('Mary', FLOOR(RAND()*100)), '1995-08-21', 'Middle', FLOOR(RAND()*100000)),
    (CONCAT('David', FLOOR(RAND()*100)), '1988-11-02', 'Junior', FLOOR(RAND()*100000)),
    (CONCAT('Emma', FLOOR(RAND()*100)), '1992-03-10', 'Trainee', FLOOR(RAND()*100000)),
    (CONCAT('Michael', FLOOR(RAND()*100)), '1985-09-28', 'Middle', FLOOR(RAND()*100000));
INSERT INTO client (ID, NAME)
VALUES
    (1, 'John Smith'),
    (2, 'Jane Doe'),
    (3, 'Michael Johnson'),
    (4, 'Sarah Williams'),
    (5, 'Robert Davis');
INSERT INTO project (ID, CLIENT_ID, START_DATE, FINISH_DATE, PROJECT_NAME)
VALUES (1,1, '2023-07-01', '2023-07-31', 'Проект A'),
       (2,2, '2023-08-01', '2023-08-31', 'Проект B'),
       (3,3, '2023-09-01', '2023-09-30', 'Проект C');
INSERT INTO project_worker (PROJECT_ID, WORKER_ID)
VALUES
    (6, 6),
    (7, 7),
    (8, 8),
    (9, 9);
