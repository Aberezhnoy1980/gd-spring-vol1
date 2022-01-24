DROP TABLE IF EXISTS `products` CASCADE;
CREATE TABLE `products`
(
    `id`    BIGINT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(30)                 NOT NULL UNIQUE,
    `price` DECIMAL(10, 2)              NOT NULL
);
INSERT INTO `products` (`title`, `price`)
VALUES ('Milk', 86.30),
       ('Bread', 27.90),
       ('Cheese', 184.50),
       ('Sausages', 285.60),
       ('Eggs', 180.90),
       ('Fish', 400.00),
       ('Meat', 358.40);
DROP TABLE IF EXISTS `customers` CASCADE;
CREATE TABLE `customers`
(
    `id` BIGINT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(30) NOT NULL
);
INSERT INTO `customers` (`name`)
VALUES ('Bob'),
       ('John'),
       ('Bill'),
       ('Jack'),
       ('Alex'),
       ('Max'),
       ('KakoytaMuzhik');

