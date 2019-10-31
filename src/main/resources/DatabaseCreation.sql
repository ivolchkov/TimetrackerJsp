CREATE SCHEMA IF NOT EXISTS `timetracking` DEFAULT CHARACTER SET utf8;

CREATE TABLE IF NOT EXISTS `timetracking`.`backlogs`
(
    `backlog_id`           INT           NOT NULL AUTO_INCREMENT,
    `backlog_project_name` VARCHAR(45)   NOT NULL,
    `backlog_description`  VARCHAR(1000) NULL,
    PRIMARY KEY (`backlog_id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `timetracking`.`goals`
(
    `goal_id`    INT         NOT NULL AUTO_INCREMENT,
    `goal_name`  VARCHAR(45) NOT NULL,
    `backlog_id` INT         NOT NULL,
    PRIMARY KEY (`goal_id`),
    INDEX `fk_goals_backlogs1_idx` (`backlog_id` ASC),
    CONSTRAINT `fk_goals_backlogs1`
        FOREIGN KEY (`backlog_id`)
            REFERENCES `timetracking`.`backlogs` (`backlog_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `timetracking`.`users`
(
    `user_id`       INT         NOT NULL AUTO_INCREMENT,
    `user_name`     VARCHAR(45) NOT NULL,
    `user_surname`  VARCHAR(45) NOT NULL,
    `user_email`    VARCHAR(60) NOT NULL,
    `user_password` VARCHAR(60) NOT NULL,
    `user_role`     VARCHAR(25) NOT NULL,
    `backlog_id`    INT         NULL,
    PRIMARY KEY (`user_id`),
    INDEX `fk_users_backlogs1_idx` (`backlog_id` ASC),
    CONSTRAINT `fk_users_backlogs1`
        FOREIGN KEY (`backlog_id`)
            REFERENCES `timetracking`.`backlogs` (`backlog_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `timetracking`.`sprints`
(
    `sprint_id`          INT           NOT NULL AUTO_INCREMENT,
    `sprint_name`        VARCHAR(45)   NOT NULL,
    `sprint_start`       DATE          NOT NULL,
    `sprint_end`         DATE          NOT NULL,
    `sprint_description` VARCHAR(1000) NULL,
    PRIMARY KEY (`sprint_id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `timetracking`.`stories`
(
    `story_id`          INT           NOT NULL AUTO_INCREMENT,
    `story_name`        VARCHAR(30)   NOT NULL,
    `story_spent_time`  TIME(0)       NOT NULL,
    `story_description` VARCHAR(1000) NULL,
    `story_status`      VARCHAR(20)   NOT NULL,
    `goal_id`           INT           NOT NULL,
    `user_id`           INT           NULL,
    `sprint_id`         INT           NULL,
    PRIMARY KEY (`story_id`),
    INDEX `fk_stories_goals1_idx` (`goal_id` ASC),
    INDEX `fk_stories_users1_idx` (`user_id` ASC),
    INDEX `fk_stories_sprints1_idx` (`sprint_id` ASC),
    CONSTRAINT `fk_stories_goals1`
        FOREIGN KEY (`goal_id`)
            REFERENCES `timetracking`.`goals` (`goal_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_stories_users1`
        FOREIGN KEY (`user_id`)
            REFERENCES `timetracking`.`users` (`user_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `fk_stories_sprints1`
        FOREIGN KEY (`sprint_id`)
            REFERENCES `timetracking`.`sprints` (`sprint_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;
