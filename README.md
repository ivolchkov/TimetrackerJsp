# **Time Tracker**
Time-Tracking software development system,
based on the SCRUM framework. Scrum master can create
projects, goals, stories and sprints. The developer can take
story to execute and see all his stories. Admin access 
to all information about projects, goals, stories
sprints and users.
#
Система Time-Tracking разработки програмных продуктов,
основана на фреймворке SCRUM. Скрам-мастер может создавать
проекты, цели, сторисы и спринты. Разработчик может взять 
сторис на выполнение и посмотреть все его сторисы. Администратору
осуществляется доступ ко всей информации о проектах, целях, сторисах
, спринтах и пользователях.
## Instalation and running

### Prerequisites
- JDK, JRE 8 or later,
- Apache Maven,
- Apache Tomcat,
- MySQL.

## Set up
- Clone the project to local reposiroty and build .war using Maven command: mvn clean package -DskipTests.
- Create database using DatabaseCreation.sql file and insert data by executing DatabaseInsertion.sql. 
- Deploy .war file to Apache Tomcat.
