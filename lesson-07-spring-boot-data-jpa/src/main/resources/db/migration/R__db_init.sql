DROP DATABASE IF EXISTS `geekBrains_spring_boot_data_jpa_local`;
CREATE DATABASE `geekBrains_spring_boot_data_jpa_local`;
USE `geekBrains_spring_boot_data_jpa_local`;

DROP TABLE IF EXISTS `categories` CASCADE;
CREATE TABLE `categories`
(
    `id`   BIGINT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(30)                 NOT NULL
) ENGINE = INNODB
  CHARACTER SET UTF8MB4;
INSERT INTO `categories` (`name`)
VALUES ('Category 1'),
       ('Category 2'),
       ('Category 3');

DROP TABLE IF EXISTS `products` CASCADE;
CREATE TABLE `products`
(
    `id`           BIGINT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name`         VARCHAR(30)                 NOT NULL UNIQUE,
    `description`  VARCHAR(100),
    `price`        DECIMAL(10, 2)              NOT NULL,
    `categoryId`   BIGINT UNSIGNED             NOT NULL,
    `categoryName` VARCHAR(30)                 NOT NULL,
    FOREIGN KEY (categoryId) REFERENCES `categories` (id)
) ENGINE = INNODB
  CHARACTER SET UTF8MB4;
INSERT INTO `products` (`name`, `description`, `price`, `categoryId`, `categoryName`)
VALUES ('Product 1', 'Description 1', 86.30, 1, 'Category 1'),
       ('Product 2', 'Description 2', 86.30, 2, 'Category 2'),
       ('Product 3', 'Description 3', 86.30, 3, 'Category 3'),
       ('Product 4', 'Description 4', 86.30, 1, 'Category 1'),
       ('Product 5', 'Description 5', 86.30, 2, 'Category 2'),
       ('Product 6', 'Description 6', 86.30, 3, 'Category 3');

