USE geekbrains_spring_hibernate_local;

DROP TABLE IF EXISTS `products` CASCADE;
CREATE TABLE `products`
(
    `id`    BIGINT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(30)                 NOT NULL UNIQUE,
    `price` DECIMAL(10, 2)              NOT NULL
)
    ENGINE = InnoDB
    CHARACTER SET UTF8MB4;
    
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
)                      
ENGINE = InnoDB CHARACTER SET UTF8MB4;

INSERT INTO `customers` (`name`)
VALUES ('Bob'),
       ('John'),
       ('Bill'),
       ('Jack'),
       ('Alex'),
       ('Max'),
       ('KakoytaMuzhik');

DROP TABLE IF EXISTS `customers_products` CASCADE;
CREATE TABLE `customers_products` (
    `customer_id` BIGINT UNSIGNED NOT NULL,
    `product_id` BIGINT UNSIGNED NOT NULL,        
    FOREIGN KEY (`customer_id`)
        REFERENCES `customers` (id),
    FOREIGN KEY (`product_id`)
        REFERENCES `products` (id)
)  ENGINE=INNODB CHARACTER SET UTF8MB4;

INSERT INTO `customers_products` (`customer_id`, `product_id`)
VALUES (1, 1),
       (1, 3),
       (1, 5),
       (1, 7),
       (2, 2),
       (2, 4),
       (2, 6),
       (3, 3),
       (3, 5),
       (3, 7),
       (4, 4),
       (4, 6),
       (5, 1),
       (5, 3),
       (5, 5),
       (5, 7),
       (6, 2),
       (6, 4),
       (6, 6),
       (7, 3),
       (7, 5),
       (7, 7);
DROP TABLE IF EXISTS `orderList` CASCADE;
CREATE TABLE `orderList`
(
    `order_id` BIGINT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `customer_id` BIGINT UNSIGNED NOT NULL,
    `product_id` BIGINT UNSIGNED NOT NULL,
    `price` DECIMAL(10, 2),
    `quantity` INTEGER,      
    FOREIGN KEY (`customer_id`)
        REFERENCES `customers` (id),
    FOREIGN KEY (`product_id`)
        REFERENCES `products` (id)
)
ENGINE = InnoDB CHARACTER SET UTF8MB4;

INSERT INTO `orderList` (`customer_id`, `product_id`, `price`, `quantity`)
VALUES (1, 1, 86.30, 2),
       (1, 3, 184.50, 2),
       (1, 5, 180.90, 2),
       (1, 7, 358.40, 2),
       (2, 2, 27.90, 2),
       (2, 4, 285.60, 2),
       (2, 6, 400.00, 2),
       (3, 3, 184.50, 2),
       (3, 5, 180.90, 2),
       (3, 7, 358.4, 2),
       (4, 4, 285.60, 2),
       (4, 6, 400.00, 2),
       (5, 1, 86.30, 2),
       (5, 3, 184.50, 2),
       (5, 5, 180.90, 2),
       (5, 7, 358.40, 2),
       (6, 2, 27.90, 2),
       (6, 4, 285.60, 2),
       (6, 6, 400.00, 2),
       (7, 3, 184.50, 2),
       (7, 5, 180.90, 2),
       (7, 7, 358.4, 2),
       (1, 1, 186.30, 2),
       (1, 3, 1184.50, 2),
       (1, 5, 1180.90, 2),
       (1, 7, 1358.40, 2),
       (2, 2, 127.90, 2),
       (2, 4, 1285.60, 2),
       (2, 6, 1400.00, 2),
       (3, 3, 1184.50, 2),
       (3, 5, 1180.90, 2),
       (3, 7, 1358.4, 2),
       (4, 4, 1285.60, 2),
       (4, 6, 1400.00, 2),
       (5, 1, 186.30, 2),
       (5, 3, 1184.50, 2),
       (5, 5, 1180.90, 2),
       (5, 7, 1358.40, 2),
       (6, 2, 127.90, 2),
       (6, 4, 1285.60, 2),
       (6, 6, 1400.00, 2),
       (7, 3, 1184.50, 2),
       (7, 5, 1180.90, 2),
       (7, 7, 1358.4, 2);
