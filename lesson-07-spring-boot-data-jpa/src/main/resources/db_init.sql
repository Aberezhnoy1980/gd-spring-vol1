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
    `category_id`   BIGINT UNSIGNED             NOT NULL,
    #     `categoryName` VARCHAR(30)                 NOT NULL,
    FOREIGN KEY (category_id) REFERENCES `categories` (id)
) ENGINE = INNODB
  CHARACTER SET UTF8MB4;
INSERT INTO `products` (`name`, `description`, `price`, `category_id`)
VALUES ('Product 1', 'Description 1', 86.30, 1),
       ('Product 2', 'Description 2', 86.30, 2),
       ('Product 3', 'Description 3', 86.30, 3),
       ('Product 4', 'Description 4', 86.30, 1),
       ('Product 5', 'Description 5', 86.30, 2),
       ('Product 6', 'Description 6', 86.30, 3),
       ('Product 7', 'Description 7', 86.30, 2),
       ('Product 8', 'Description 8', 86.30, 3),
       ('Product 9', 'Description 9', 86.30, 1),
       ('Product 10', 'Description 10', 86.30, 2),
       ('Product 11', 'Description 11', 86.30, 3),
       ('Product 12', 'Description 12', 86.30, 1),
       ('Product 13', 'Description 13', 86.30, 2),
       ('Product 14', 'Description 14', 86.30, 3),
       ('Product 15', 'Description 15', 86.30, 1),
       ('Product 16', 'Description 16', 86.30, 2),
       ('Product 17', 'Description 17', 86.30, 3),
       ('Product 18', 'Description 18', 86.30, 1),
       ('Product 19', 'Description 19', 86.30, 2),
       ('Product 20', 'Description 20', 86.30, 3);

