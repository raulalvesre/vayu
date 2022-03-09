CREATE TABLE IF NOT EXISTS category
(
    id          INT          NOT NULL AUTO_INCREMENT,
    code        VARCHAR(255) NOT NULL,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    study_guide VARCHAR(1024),
    active      BIT,
    `order`     TINYINT,
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
    `order`     TINYINT,

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
    estimated_hours_to_finish TINYINT      NOT NULL,
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
    `order`   TINYINT,
    active    BIT,
    test      BIT,

    PRIMARY KEY (id),
    FOREIGN KEY (course_id)
        REFERENCES course (id)
);

CREATE TABLE IF NOT EXISTS explanation
(
    id         INT          NOT NULL AUTO_INCREMENT,
    section_id INT          NOT NULL,
    code       VARCHAR(255) NOT NULL,
    title      VARCHAR(255) NOT NULL,
    active     BIT,
    `order`    TINYINT,
    text       VARCHAR(255) NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (section_id)
        REFERENCES section (id)
);

CREATE TABLE IF NOT EXISTS video
(
    id                  INT          NOT NULL AUTO_INCREMENT,
    section_id          INT          NOT NULL,
    code                VARCHAR(255) NOT NULL,
    title               VARCHAR(255) NOT NULL,
    active              BIT,
    `order`             TINYINT,
    url                 VARCHAR(512) NOT NULL,
    duration_in_minutes TINYINT,
    transcription       TEXT,

    PRIMARY KEY (id),
    FOREIGN KEY (section_id)
        REFERENCES section (id)
);

CREATE TABLE IF NOT EXISTS question_type
(
    id   TINYINT PRIMARY KEY,
    name VARCHAR(128) NOT NULL
);

CREATE TABLE IF NOT EXISTS question
(
    id            INT          NOT NULL AUTO_INCREMENT,
    section_id    INT          NOT NULL,
    code          VARCHAR(255) NOT NULL,
    question_type TINYINT      NOT NULL,
    title         VARCHAR(255) NOT NULL,
    active        BIT,
    `order`       TINYINT,
    wording       VARCHAR(512) NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (section_id)
        REFERENCES section (id),
    FOREIGN KEY (question_type)
        REFERENCES question_type (id)
);

CREATE TABLE IF NOT EXISTS alternative
(
    text          VARCHAR(512) NOT NULL,
    `order`       TINYINT,
    correct       BIT,
    justification VARCHAR(512),
    question_id   INT,

    FOREIGN KEY (question_id)
        REFERENCES question (id)
)