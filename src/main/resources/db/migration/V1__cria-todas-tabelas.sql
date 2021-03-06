CREATE TABLE IF NOT EXISTS user_role
(
    id   INT         NOT NULL,
    name VARCHAR(50) NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user
(
    id       INT          NOT NULL AUTO_INCREMENT,
    role_id  INT          NOT NULL,
    name     VARCHAR(200) NOT NULL,
    email    VARCHAR(255) NOT NULL UNIQUE,
    username VARCHAR(16)  NOT NULL UNIQUE,
    password VARCHAR(500) NOT NULL,
    active   BIT          NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (role_id)
        REFERENCES user_role (id)
);

CREATE TABLE IF NOT EXISTS category
(
    id          INT          NOT NULL AUTO_INCREMENT,
    code        VARCHAR(255) NOT NULL,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    study_guide VARCHAR(1024),
    active      BIT,
    `order`     INT,
    icon_path   VARCHAR(512),
    color_code  VARCHAR(7),

    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS subcategory
(
    id          INT          NOT NULL AUTO_INCREMENT,
    category_id INT          NOT NULL,
    code        VARCHAR(255) NOT NULL,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    study_guide VARCHAR(1024),
    active      BIT,
    `order`     INT,

    PRIMARY KEY (id),
    FOREIGN KEY (category_id)
        REFERENCES category (id)
);

CREATE TABLE IF NOT EXISTS course
(
    id                        INT          NOT NULL AUTO_INCREMENT,
    subcategory_id            INT          NOT NULL,
    code                      VARCHAR(255) NOT NULL,
    name                      VARCHAR(255) NOT NULL,
    estimated_hours_to_finish INT          NOT NULL,
    visible                   BIT,
    target_audience           VARCHAR(256),
    instructor_name           VARCHAR(256),
    syllabus                  TEXT,
    developed_abilities       VARCHAR(512),

    PRIMARY KEY (id),
    FOREIGN KEY (subcategory_id)
        REFERENCES subcategory (id)
);

CREATE TABLE IF NOT EXISTS section
(
    id        INT          NOT NULL AUTO_INCREMENT,
    course_id INT          NOT NULL,
    code      VARCHAR(255) NOT NULL,
    name      VARCHAR(255) NOT NULL,
    `order`   INT,
    active    BIT,
    test      BIT,

    PRIMARY KEY (id),
    FOREIGN KEY (course_id)
        REFERENCES course (id)
);

CREATE TABLE IF NOT EXISTS activity
(
    id                  INT          NOT NULL AUTO_INCREMENT,
    activity_type       VARCHAR(255) NOT NULL,
    section_id          INT          NOT NULL,
    code                VARCHAR(255) NOT NULL,
    title               VARCHAR(255) NOT NULL,
    active              BIT,
    `order`             INT,
    text                VARCHAR(255) NOT NULL,
    url                 VARCHAR(512) NOT NULL,
    duration_in_minutes INT,
    transcription       TEXT,
    wording             VARCHAR(512) NOT NULL,
    question_type       VARCHAR(255) NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (section_id)
        REFERENCES section (id)
);

CREATE TABLE IF NOT EXISTS alternative
(
    id            INT          NOT NULL AUTO_INCREMENT,
    text          VARCHAR(512) NOT NULL,
    `order`       INT,
    correct       BIT,
    justification VARCHAR(512),
    question_id   INT,

    PRIMARY KEY (id),
    FOREIGN KEY (question_id)
        REFERENCES activity (id)
);
