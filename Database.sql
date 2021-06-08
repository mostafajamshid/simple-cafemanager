        CREATE TABLE IF NOT EXISTS `role`
        (
            `id`   int(11) NOT NULL AUTO_INCREMENT,
            `role` varchar(255) DEFAULT NULL,
            PRIMARY KEY (`id`)
        ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


        CREATE TABLE IF NOT EXISTS `cafe_table`
        (
            `id`           int(11) NOT NULL AUTO_INCREMENT,
            `name`         varchar(45) DEFAULT NULL,
            `description`  varchar(45) DEFAULT NULL,
            `date_created` datetime    DEFAULT NULL,
            `closed`       bit(1)      DEFAULT b'0',
            PRIMARY KEY (`id`)
        ) ENGINE=INNODB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

        CREATE TABLE IF NOT EXISTS `product`
        (
            `id`           int(11) NOT NULL AUTO_INCREMENT,
            `name`         varchar(45) DEFAULT NULL,
            `description`  varchar(45) DEFAULT NULL,
            `date_created` datetime    DEFAULT NULL,
            `price`        double (11,2) NOT NULL,
            PRIMARY KEY (`id`)
        ) ENGINE=INNODB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

        CREATE TABLE IF NOT EXISTS `cafe_order`
        (
            `id`           int(11) NOT NULL AUTO_INCREMENT,
            `name`         varchar(45) DEFAULT NULL,
            `description`  varchar(45) DEFAULT NULL,
            `is_open`       bit(1)      DEFAULT b'1',
            `date_created` datetime    DEFAULT NULL,
            `total_amount`        double (11,2) NOT NULL,
            PRIMARY KEY (`id`)
        ) ENGINE=INNODB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;


        CREATE TABLE IF NOT EXISTS `user`
        (
            `id`       int(11) NOT NULL AUTO_INCREMENT,
            `active`   int(11) DEFAULT NULL,
            `email`    varchar(255) NOT NULL,
            `name`     varchar(255) NOT NULL,
            `password` varchar(255) NOT NULL,
            `role_id`  int(11) DEFAULT NULL,
            PRIMARY KEY (`id`),
            KEY        `id_role_id` (`role_id`),
            CONSTRAINT `id_role_id` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
        ) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;


        CREATE TABLE IF NOT EXISTS   `cafe_order_products` (
            `order_id` int(11) NOT NULL,
            `products_id` int(11) NOT NULL,
            KEY `products_list_id` (`products_id`),
            KEY `products_order_id` (`order_id`)
            ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

        CREATE TABLE IF NOT EXISTS `product_order`
        (
            `id`         int(11) not NULL AUTO_INCREMENT,
            `product_id` int(11) NOT NULL,
            `quantity`   int(11) NOT NULL,
            `amount`        double (11,2) NOT NULL,
            PRIMARY KEY (`id`),
            KEY          `id_productorder_product_id` (`product_id`),
            CONSTRAINT `id_productorder_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
        ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

        CREATE TABLE IF NOT EXISTS `waiter_table`
        (
            `id`            int(11) not NULL AUTO_INCREMENT,
            `user_id`       int(11) NOT NULL,
            `cafe_table_id` int(11) NOT NULL,
            PRIMARY KEY (`id`),
            KEY             `id_waitertable_user_id` (`user_id`),
            KEY             `id_waitertable_table_id` (`cafe_table_id`),
            CONSTRAINT `id_waitertable_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
            CONSTRAINT `id_waitertable_table_id` FOREIGN KEY (`cafe_table_id`) REFERENCES `cafe_table` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
        ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

        INSERT INTO `role`
        VALUES (1, 'MANAGER');
        INSERT INTO `role`
        VALUES (2, 'WAITER');
