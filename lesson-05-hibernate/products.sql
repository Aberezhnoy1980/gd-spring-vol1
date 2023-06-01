DROP TABLE IF EXISTS `products` CASCADE;
CREATE TABLE IF NOT EXISTS `products` (
	`id` BIGINT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(30) NOT NULL UNIQUE,
    `description` VARCHAR(100),
    `price` DECIMAL(10, 2) NOT NULL
    );
    
    INSERT INTO `products` (`name`, `description`, `price`) VALUES
    ('Product 1', 'description 1', 12.54),
    ('Product 2', 'description 2', 134.76),
    ('Product 3', 'description 4', 3456.11),
    ('Product 4', 'description 4', 36.25),
    ('Product 5', 'description 5', 336.99);
    
    UPDATE `products` SET `description` = 'description 3' WHERE `name` = 'Product 3';