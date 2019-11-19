CREATE SCHEMA IF NOT EXISTS `timetracking_spring` DEFAULT CHARACTER SET utf8;
USE `timetracking_spring`;

-- -----------------------------------------------------
-- Table `timetracking_spring`.`backlogs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `timetracking_spring`.`backlogs`
(
    `backlog_id`           INT(11)                            NOT NULL AUTO_INCREMENT,
    `backlog_description`  VARCHAR(1000) CHARACTER SET 'utf8' NULL DEFAULT NULL,
    `backlog_project_name` VARCHAR(45) CHARACTER SET 'utf8'   NOT NULL,
    PRIMARY KEY (`backlog_id`)
)
    ENGINE = MyISAM
    AUTO_INCREMENT = 3
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `timetracking_spring`.`goals`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `timetracking_spring`.`goals`
(
    `goal_id`    INT(11)                          NOT NULL AUTO_INCREMENT,
    `goal_name`  VARCHAR(45) CHARACTER SET 'utf8' NOT NULL,
    `backlog_id` INT(11)                          NOT NULL,
    PRIMARY KEY (`goal_id`),
    INDEX `FKh2g60c0udi1mvgwub8dr6n3qq` (`backlog_id` ASC)
)
    ENGINE = MyISAM
    AUTO_INCREMENT = 11
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `timetracking_spring`.`sprints`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `timetracking_spring`.`sprints`
(
    `sprint_id`          INT(11)                            NOT NULL AUTO_INCREMENT,
    `sprint_description` VARCHAR(1000) CHARACTER SET 'utf8' NULL DEFAULT NULL,
    `sprint_end`         DATE                               NOT NULL,
    `sprint_name`        VARCHAR(45) CHARACTER SET 'utf8'   NOT NULL,
    `sprint_start`       DATE                               NOT NULL,
    PRIMARY KEY (`sprint_id`)
)
    ENGINE = MyISAM
    AUTO_INCREMENT = 5
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `timetracking_spring`.`stories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `timetracking_spring`.`stories`
(
    `story_id`          INT(11)                            NOT NULL AUTO_INCREMENT,
    `story_description` VARCHAR(1000) CHARACTER SET 'utf8' NULL DEFAULT NULL,
    `story_name`        VARCHAR(45) CHARACTER SET 'utf8'   NOT NULL,
    `story_spent_time`  TIME                               NOT NULL,
    `story_status`      VARCHAR(255) CHARACTER SET 'utf8'  NOT NULL,
    `goal_id`           INT(11)                            NOT NULL,
    `sprint_id`         INT(11)                            NULL DEFAULT NULL,
    `user_id`           INT(11)                            NULL DEFAULT NULL,
    PRIMARY KEY (`story_id`),
    INDEX `FKbfioq4ffu3w2sg42e8hie472m` (`goal_id` ASC),
    INDEX `FKldus9trpp2ay4f5pwvth76b69` (`sprint_id` ASC),
    INDEX `FKshv2ytgbsn9w9mpu43mc6ln6j` (`user_id` ASC)
)
    ENGINE = MyISAM
    AUTO_INCREMENT = 33
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `timetracking_spring`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `timetracking_spring`.`users`
(
    `user_id`       INT(11)                           NOT NULL AUTO_INCREMENT,
    `user_email`    VARCHAR(320) CHARACTER SET 'utf8' NOT NULL,
    `user_name`     VARCHAR(45) CHARACTER SET 'utf8'  NOT NULL,
    `user_password` VARCHAR(100) CHARACTER SET 'utf8' NOT NULL,
    `user_role`     VARCHAR(45) CHARACTER SET 'utf8'  NOT NULL,
    `user_surname`  VARCHAR(45) CHARACTER SET 'utf8'  NOT NULL,
    `backlog_id`    INT(11)                           NULL DEFAULT NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE INDEX `UK_33uo7vet9c79ydfuwg1w848f` (`user_email` ASC),
    INDEX `FKsqp5o0rqge2x5f6hhobfstxcg` (`backlog_id` ASC)
)
    ENGINE = MyISAM
    AUTO_INCREMENT = 15
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_bin;

