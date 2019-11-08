INSERT INTO timetracking.backlogs (backlog_project_name, backlog_description)
VALUES ('Barbershop', 'Create web project about barbershop service'),
       ('Online-shop', 'Create web project of online shop, which sell new year`s gift');

INSERT INTO timetracking.goals (goal_name, backlog_id)
VALUES ('Create model of barbershop', 1),
       ('Create entities which depend on model', 1),
       ('Make some services', 1),
       ('Create tests of barbershop services', 1),
       ('Make web-site of the project', 1),
       ('Create model of online-shop', 2),
       ('Create entities which depend model', 2),
       ('Make some services', 2),
       ('Create tests of online-shop services', 2),
       ('Make web-site of the project', 2);

INSERT INTO timetracking.stories (story_name, story_spent_time, story_description, story_status, goal_id)
VALUES ('User table', '03:00:00', 'Create table of users', 'To do', 1),
       ('Order table', '02:00:00', 'Create table of orders', 'To do', 1),
       ('Comment table', '01:30:00', 'Create table of comments', 'To do', 1),
       ('UserEntity', '02:00:00', 'Create user entity', 'To do', 2),
       ('OrderEntity', '02:30:00', 'Create order entity', 'To do', 2),
       ('CommentEntity', '02:00:00', 'Create comment entity', 'To do', 2),
       ('UserService', '04:00:00', 'Create user service', 'To do', 3),
       ('OrderService', '04:00:00', 'Create order service', 'To do', 3),
       ('CommentService', '04:00:00', 'Create comment service', 'To do', 3),
       ('UserTest', '02:00:00', 'Create tests for user service', 'To do', 4),
       ('OrderService', '02:00:00', 'Create tests for order service', 'To do', 4),
       ('CommentService', '02:00:00', 'Create tests for comment service', 'To do', 4),
       ('Main page', '04:00:00', 'Create main page', 'To do', 5),
       ('Service pages', '10:00:00', 'Create service pages', 'To do', 5),
       ('Auth service', '04:00:00', 'Create authentication service page', 'To do', 5),
       ('Barbers service', '06:00:00', 'Create service for client', 'To do', 5),
       ('User table', '03:00:00', 'Create table of users', 'To do', 6),
       ('Order table', '02:00:00', 'Create table of orders', 'To do', 6),
       ('Comment table', '01:30:00', 'Create table of comments', 'To do', 6),
       ('UserEntity', '02:00:00', 'Create user entity', 'To do', 7),
       ('OrderEntity', '02:30:00', 'Create order entity', 'To do', 7),
       ('CommentEntity', '02:00:00', 'Create comment entity', 'To do', 7),
       ('UserService', '04:00:00', 'Create user service', 'To do', 8),
       ('OrderService', '04:00:00', 'Create order service', 'To do', 8),
       ('CommentService', '04:00:00', 'Create comment service', 'To do', 8),
       ('UserTest', '02:00:00', 'Create tests for user service', 'To do', 9),
       ('OrderService', '02:00:00', 'Create tests for order service', 'To do', 9),
       ('CommentService', '02:00:00', 'Create tests for comment service', 'To do', 9),
       ('Main page', '04:00:00', 'Create main page', 'To do', 10),
       ('Service pages', '10:00:00', 'Create service pages', 'To do', 10),
       ('Auth service', '04:00:00', 'Create authentication service page', 'To do', 10),
       ('Client service', '06:00:00', 'Create service for client', 'To do', 10);

INSERT INTO timetracking.sprints (sprint_name, sprint_start, sprint_end, sprint_description)
VALUES ('Creating DB', '2019-11-1', '2019-11-10', 'Creating DB entities and product repository'),
       ('Creating Services', '2019-11-11', '2019-11-18', 'Creating services and test them'),
       ('Creating Controllers', '2019-11-11', '2019-11-18', 'Creating Controllers and start to create view'),
       ('Creating View', '2019-11-19', '2019-11-28', 'Finished creating the view and end the project');


INSERT INTO timetracking.users (user_name, user_surname, user_email, user_password, user_role)
VALUES ('Ihor', 'Volchkov', 'igorik@gmail.com', 'pass', 'Admin');

INSERT INTO timetracking.users (user_name, user_surname, user_email, user_password, user_role, backlog_id)
VALUES ('Fred', 'Smith', 'fred@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'Scrum master', 1),
       ('John', 'Johnson', 'john@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'Developer', 1),
       ('Michael', 'Morty', 'michael@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'Developer', 1),
       ('Robert', 'Bukovsky', 'robert@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'Developer', 1),
       ('Andrew', 'Martin', 'martin@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'Developer', 1),
       ('Luke', 'Skywalker', 'luke@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'Developer', 1),
       ('Steve', 'Rogers', 'captain@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'Scrum master', 2),
       ('Backie', 'Barnes', 'barnes@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'Developer', 2),
       ('Tony', 'Stark', 'stark@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'Developer', 2),
       ('Peter', 'Parker', 'spider@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'Developer', 2),
       ('Chris', 'Hermsword', 'tor@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'Developer', 2),
       ('Steven', 'Strange', 'doctor@gmail.com', '1a1dc91c907325c69271ddf0c944bc72', 'Developer', 2);



