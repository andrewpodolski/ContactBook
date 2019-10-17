drop database if exists andrew;
create database andrew CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci;
use andrew;
CREATE TABLE contact
(
    id             INT(11)                    NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name           VARCHAR(15)                NOT NULL,
    surname        VARCHAR(20)                NOT NULL,
    patronymic     VARCHAR(20)                NULL,
    birthday       DATE                       NULL,
    nationality    VARCHAR(25)                NULL,
    gender         ENUM ('male', 'female')    NULL,
    marital_status ENUM ('married', 'single') NULL,
    web_site       VARCHAR(45)                NULL,
    email          VARCHAR(45)                NOT NULL,
    place_of_work  VARCHAR(45)                NULL
);
CREATE TABLE address
(
    contact_id   INT(11)     NOT NULL PRIMARY KEY,
    country      VARCHAR(45) NULL,
    city         VARCHAR(45) NULL,
    street       VARCHAR(20) NULL,
    house_number VARCHAR(10) NULL,
    flat_number  VARCHAR(10) NULL,
    zip_code     VARCHAR(10) NULL,
    FOREIGN KEY (contact_id) REFERENCES contact (id) ON DELETE CASCADE
);

CREATE TABLE phone
(
    contact_id    INT(11)                 NOT NULL,
    country_code  INT(11)                 NULL,
    operator_code INT(11)                 NULL,
    phone_number  INT(11)                 NOT NULL PRIMARY KEY,
    type          ENUM ('home', 'mobile') NULL,
    comment       VARCHAR(45)             NULL,
    FOREIGN KEY (contact_id) REFERENCES contact (id) ON DELETE CASCADE
);

CREATE TABLE attachment
(
    attachment_id INT(11)                     NOT NULL PRIMARY KEY AUTO_INCREMENT,
    contact_id    INT(11)                     NOT NULL,
    state         ENUM ('old', 'edit', 'new') NOT NULL,
    file_name     VARCHAR(45)                 NOT NULL,
    loaded_date   DATE                        NULL,
    comment       VARCHAR(45)                 NULL,
    FOREIGN KEY (contact_id) REFERENCES contact (id) ON DELETE CASCADE
);
