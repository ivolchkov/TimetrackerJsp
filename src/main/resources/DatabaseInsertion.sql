INSERT INTO timetracking.users (user_name, user_surname, user_email, user_password, user_role)
VALUES ('Ihor', 'Volchkov', 'igorik@gmail.com', 'pass', 'Admin');

INSERT INTO timetracking.users (user_name, user_surname, user_email, user_password, user_role, backlog_id)
VALUES ('Fred', 'Smith', 'fred@gmail.com', 'pass', 'Scrum master', 1),
       ('John', 'Smith', 'john@gmail.com', 'pass', 'Developer', 1),
       ('Michael', 'Smith', 'michael@gmail.com', 'pass', 'Developer', 1),
       ('Robert', 'Smith', 'robert@gmail.com', 'pass', 'Developer', 1);