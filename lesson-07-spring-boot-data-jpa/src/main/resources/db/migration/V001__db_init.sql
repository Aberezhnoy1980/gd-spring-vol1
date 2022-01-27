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
    `id`          BIGINT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name`        VARCHAR(30)                 NOT NULL UNIQUE,
    `description` VARCHAR(100),
    `price`       DECIMAL(10, 2)              NOT NULL,
    `category_id` BIGINT UNSIGNED             NOT NULL,
    #     `categoryName` VARCHAR(30)                 NOT NULL,
    FOREIGN KEY (category_id) REFERENCES `categories` (id)
) ENGINE = INNODB
  CHARACTER SET UTF8MB4;
INSERT INTO `products` (`name`, `description`, `price`, `category_id`)
VALUES ('Product 1', 'Description 1', 86.30, 1),
       ('Product 2', 'Description 2', 8123.45, 2),
       ('Product 3', 'Description 3', 213.76, 3),
       ('Product 4', 'Description 4', 342.98, 1),
       ('Product 5', 'Description 5', 12.32, 2),
       ('Product 6', 'Description 6', 186.76, 3),
       ('Product 7', 'Description 7', 890.20, 2),
       ('Product 8', 'Description 8', 345.65, 3),
       ('Product 9', 'Description 9', 5467.99, 1),
       ('Product 10', 'Description 10', 1186.54, 2),
       ('Product 11', 'Description 11', 250.00, 3),
       ('Product 12', 'Description 12', 10123.35, 1),
       ('Product 13', 'Description 13', 990.65, 2),
       ('Product 14', 'Description 14', 100.89, 3),
       ('Product 15', 'Description 15', 678.55, 1),
       ('Product 16', 'Description 16', 1200.50, 2),
       ('Product 17', 'Description 17', 350.90, 3),
       ('Product 18', 'Description 18', 3990.50, 1),
       ('Product 19', 'Description 19', 266.90, 2),
       ('Product 20', 'Description 20', 12500.99, 3),
       ('Product 21', 'Description 22', 112500.99, 3),
       ('Product 22', 'Description 22', 212500.99, 3),
       ('Product 23', 'Description 23', 2212500.99, 1);

