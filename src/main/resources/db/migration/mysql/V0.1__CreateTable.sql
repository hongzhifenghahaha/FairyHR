CREATE
DATABASE IF NOT EXISTS fairyhr;

CREATE
USER IF NOT EXISTS 'admin'@'localhost' IDENTIFIED BY 'admin';

GRANT ALL
ON fairyhr.* to 'admin'@'localhost';

USE
fairyhr;

CREATE TABLE IF NOT EXISTS department
(
    id        VARCHAR(30) PRIMARY KEY,
    d_name    VARCHAR(30) NOT NULL,
    d_id      VARCHAR(30),
    deleted   BOOLEAN     NOT NULL,
    FOREIGN KEY (d_id) REFERENCES department(id)
) DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS schedule
(
    id         VARCHAR(30) PRIMARY KEY,
    start_time TIME NOT NULL,
    end_time   TIME NOT NULL,
    start_date DATE,
    end_date   DATE,
    frequency  INTEGER UNSIGNED CHECK (0 <= frequency AND frequency <= 4) NOT NULL,
    frequency_value INTEGER UNSIGNED
) DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS user
(
    id           VARCHAR(30) PRIMARY KEY,
    user_name    VARCHAR(30) NOT NULL,
    passwd       VARCHAR(30) NOT NULL,
    phone_number VARCHAR(20),
    resident_id  VARCHAR(20),
    email_addr   VARCHAR(50),
    address      TEXT,
    position     VARCHAR(50),
    deleted      BOOLEAN     NOT NULL
) DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS leave_request
(
    id            VARCHAR(30) PRIMARY KEY,
    user_id       VARCHAR(30) not null,
    start_time    DATETIME    not null,
    end_time      DATETIME    not null,
    summit_time   DATETIME    not null,
    reason        TEXT,
    status        TINYTEXT,
    checker_id    VARCHAR(30),
    check_time    DATETIME,
    check_opinion TEXT,
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (checker_id) REFERENCES user (id)
) DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS department_user
(
    user_id VARCHAR(30) NOT NULL,
    d_id    VARCHAR(30) NOT NULL,
    PRIMARY KEY (user_id, d_id),
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (d_id) REFERENCES department (id)
) DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS department_manager
(
    manager_id VARCHAR(30) NOT NULL,
    d_id       VARCHAR(30) NOT NULL,
    PRIMARY KEY (manager_id, d_id),
    FOREIGN KEY (manager_id) REFERENCES user (id),
    FOREIGN KEY (d_id) REFERENCES department (id)
) DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS department_leave_request
(
    request_id VARCHAR(30) NOT NULL,
    d_id       VARCHAR(30) NOT NULL,
    PRIMARY KEY (request_id, d_id),
    FOREIGN KEY (request_id) REFERENCES leave_request (id),
    FOREIGN KEY (d_id) REFERENCES department (id)
) DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS user_attendance_schedule
(
    user_id     VARCHAR(30) NOT NULL,
    schedule_id VARCHAR(30) NOT NULL,
    PRIMARY KEY (user_id, schedule_id),
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (schedule_id) REFERENCES schedule (id)
) DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS user_attendance_time
(
    user_id VARCHAR(30) NOT NULL,
    time    DATETIME    NOT NULL,
    PRIMARY KEY (user_id, time),
    FOREIGN KEY (user_id) REFERENCES user (id)
) DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS user_attendance_leave
(
    user_id     VARCHAR(30) NOT NULL,
    schedule_id VARCHAR(30) NOT NULL,
    PRIMARY KEY (user_id, schedule_id),
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (schedule_id) REFERENCES schedule (id)
) DEFAULT CHARSET=utf8;
