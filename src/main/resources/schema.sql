CREATE TABLE IF NOT EXISTS category
(
    code        VARCHAR(255) PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    study_guide VARCHAR(1024),
    active      BIT,
    icon_path   VARCHAR(512),
    color_code  VARCHAR(7)
);

CREATE TABLE IF NOT EXISTS subcategory
(
    code          VARCHAR(255) NOT NULL,
    category_code VARCHAR(255) NOT NULL,
    name          VARCHAR(255) NOT NULL,
    description   VARCHAR(255),
    study_guide   VARCHAR(1024),
    active        BIT,
    `order`       TINYINT,

    PRIMARY KEY (code),
    FOREIGN KEY (category_code)
        REFERENCES category (code)
);

CREATE TABLE IF NOT EXISTS course
(
    code                      VARCHAR(255) NOT NULL,
    subcategory_code          VARCHAR(255) NOT NULL,
    name                      VARCHAR(255) NOT NULL,
    estimated_hours_to_finish TINYINT      NOT NULL,
    visible                   BIT,
    target_audience           VARCHAR(128),
    instructor_name           VARCHAR(256),
    syllabus                  VARCHAR(512),
    developed_abilities       VARCHAR(256),

    PRIMARY KEY (code),
    FOREIGN KEY (subcategory_code)
        REFERENCES subcategory (code)
);

CREATE TABLE IF NOT EXISTS section
(
    code        VARCHAR(255) NOT NULL,
    course_code VARCHAR(255) NOT NULL,
    name        VARCHAR(255) NOT NULL,
    `order`     TINYINT,
    active      BIT,
    test        BIT,

    PRIMARY KEY (code),
    FOREIGN KEY (course_code)
        REFERENCES course (code)
);

CREATE TABLE IF NOT EXISTS explanation
(
    code         VARCHAR(255) NOT NULL,
    section_code VARCHAR(255) NOT NULL,
    title        VARCHAR(255) NOT NULL,
    active       BIT,
    `order`      TINYINT,
    text         VARCHAR(255) NOT NULL,

    PRIMARY KEY (code),
    FOREIGN KEY (section_code)
        REFERENCES section (code)
);

CREATE TABLE IF NOT EXISTS video
(
    code                VARCHAR(255) NOT NULL,
    section_code        VARCHAR(255) NOT NULL,
    title               VARCHAR(255) NOT NULL,
    active              BIT,
    `order`             TINYINT,
    url                 VARCHAR(512) NOT NULL,
    duration_in_minutes TINYINT,
    transcription       TEXT,

    PRIMARY KEY (code),
    FOREIGN KEY (section_code)
        REFERENCES section (code)
);

CREATE TABLE IF NOT EXISTS question_type
(
    code TINYINT PRIMARY KEY,
    name VARCHAR(128) NOT NULL
);

CREATE TABLE IF NOT EXISTS question
(
    code          VARCHAR(255) NOT NULL,
    section_code  VARCHAR(255) NOT NULL,
    question_type TINYINT      NOT NULL,
    title         VARCHAR(255) NOT NULL,
    active        BIT,
    `order`       TINYINT,
    wording       VARCHAR(512) NOT NULL,

    PRIMARY KEY (code),
    FOREIGN KEY (section_code)
        REFERENCES section (code),
    FOREIGN KEY (question_type)
        REFERENCES question_type (code)
);

CREATE TABLE IF NOT EXISTS alternative
(
    text          VARCHAR(512) NOT NULL,
    `order`       TINYINT,
    correct       BIT,
    justification VARCHAR(512),
    question_code VARCHAR(255),

    FOREIGN KEY (question_code)
        REFERENCES question (code)
)