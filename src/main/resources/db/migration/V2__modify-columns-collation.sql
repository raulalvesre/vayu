ALTER TABLE category CHANGE `code`
    `code` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_bin;

ALTER TABLE category CHANGE `name`
    `name` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_bin;

ALTER TABLE subcategory CHANGE `code`
    `code` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_bin;

ALTER TABLE subcategory CHANGE `name`
    `name` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_bin;

ALTER TABLE course CHANGE `code`
    `code` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_bin;

ALTER TABLE course CHANGE `name`
    `name` VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_bin;