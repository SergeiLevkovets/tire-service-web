DROP SCHEMA IF EXISTS `tire_service_db`;

CREATE SCHEMA IF NOT EXISTS `tire_service_db`
    CHARACTER SET `utf8`;

USE `tire_service_db`;

CREATE TABLE `tire_service_db`.`users`
(
    `id`       INTEGER      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `name`     VARCHAR(255) NOT NULL,
    `email`    VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `phone`    VARCHAR(255) NOT NULL,
    `role`     VARCHAR(255) NULL
);

CREATE TABLE `tire_service_db`.`widths`
(
    `id`    INTEGER      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `width` VARCHAR(255) NOT NULL
);

CREATE TABLE `tire_service_db`.`heights`
(
    `id`     INTEGER      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `height` VARCHAR(255) NOT NULL
);

CREATE TABLE `tire_service_db`.`diameters`
(
    `id`       INTEGER      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `diameter` VARCHAR(255) NOT NULL
);

CREATE TABLE `tire_service_db`.`types`
(
    `id`   INTEGER      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `type` VARCHAR(255) NOT NULL
);

CREATE TABLE `tire_service_db`.`service_items`
(
    `id`         INTEGER      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `name`       VARCHAR(255) NOT NULL
);

CREATE TABLE `tire_service_db`.`tires`
(
    `id`             INTEGER   NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `fk_width_id`    INTEGER   NOT NULL,
    `fk_height_id`   INTEGER   NOT NULL,
    `fk_diameter_id` INTEGER   NOT NULL,
    `date`           TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `fk_to_width` FOREIGN KEY (`fk_width_id`) REFERENCES widths (`id`),
    CONSTRAINT `fk_to_height` FOREIGN KEY (`fk_height_id`) REFERENCES heights (`id`),
    CONSTRAINT `fk_to_diameter` FOREIGN KEY (`fk_diameter_id`) REFERENCES diameters (`id`)
);

CREATE TABLE `tire_service_db`.`service_item_prices`
(
    `id`                    INTEGER        NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `fk_service_item_id`    INTEGER        NOT NULL,
    `fk_type_id`            INTEGER        NOT NULL,
    `fk_diameter_id`        INTEGER        NULL,
    `price`                 NUMERIC(10, 2) NOT NULL,
    CONSTRAINT `fk_to_items` FOREIGN KEY (fk_service_item_id) REFERENCES service_items (`id`),
    CONSTRAINT `fk_to_types` FOREIGN KEY (fk_type_id) REFERENCES types (`id`),
    CONSTRAINT `fk_to_diameters` FOREIGN KEY (`fk_diameter_id`) REFERENCES diameters (`id`)
);

CREATE TABLE `tire_service_db`.`orders`
(
    `id`         INTEGER   NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `fk_user_id` INTEGER   NOT NULL,
    `fk_tire_id` INTEGER   NOT NULL,
    `fk_type_id` INTEGER   NOT NULL,
    `date`       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `fk_to_user` FOREIGN KEY (`fk_user_id`) REFERENCES users (`id`),
    CONSTRAINT `fk_to_tire` FOREIGN KEY (`fk_tire_id`) REFERENCES tires (`id`),
    CONSTRAINT `fk_to_type` FOREIGN KEY (fk_type_id) REFERENCES types (`id`)

);

CREATE TABLE `tire_service_db`.`orders_to_service_item_prices`
(
    `id`                       INTEGER   NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `fk_orders_id`             INTEGER NOT NULL,
    `fk_service_item_price_id` INTEGER NOT NULL,
    CONSTRAINT `fk_to_order` FOREIGN KEY (`fk_orders_id`) REFERENCES `orders` (`id`),
    CONSTRAINT `fk_to_service_item_price` FOREIGN KEY (`fk_service_item_price_id`) REFERENCES `service_item_prices` (`id`)
);

CREATE TABLE `tire_service_db`.`tire_storage`
(
    `id`         INTEGER      NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `name`       VARCHAR(255) NOT NULL,
    `fk_tire_id` INTEGER      NOT NULL,
    `date_end`   TIMESTAMP    NOT NULL,
    `fk_user_id` INTEGER      NOT NULL,
    CONSTRAINT `fk_to_tires` FOREIGN KEY (`fk_tire_id`) REFERENCES tires (`id`),
    CONSTRAINT `fk_to_users` FOREIGN KEY (`fk_user_id`) REFERENCES users (`id`)
);

################################################


INSERT INTO `tire_service_db`.diameters (diameter)
VALUES ('r13'),
       ('r14'),
       ('r14c'),
       ('r15'),
       ('r15c'),
       ('r16'),
       ('r16c'),
       ('r17'),
       ('r17c'),
       ('r17.5'),
       ('r18'),
       ('r19'),
       ('r19.5'),
       ('r20'),
       ('r21'),
       ('r22'),
       ('r22.5');

INSERT INTO tire_service_db.heights (height)
VALUES ('15'),
       ('20'),
       ('25'),
       ('30'),
       ('35'),
       ('40'),
       ('45'),
       ('50'),
       ('55'),
       ('60'),
       ('65'),
       ('70'),
       ('75'),
       ('80'),
       ('85'),
       ('90'),
       ('95'),
       ('100'),
       ('105');

INSERT INTO tire_service_db.widths (width)
VALUES ('115'),
       ('120'),
       ('125'),
       ('130'),
       ('135'),
       ('140'),
       ('145'),
       ('150'),
       ('155'),
       ('160'),
       ('165'),
       ('170'),
       ('175'),
       ('180'),
       ('185'),
       ('190'),
       ('195'),
       ('200'),
       ('205'),
       ('215'),
       ('220'),
       ('225'),
       ('230'),
       ('235'),
       ('240'),
       ('245'),
       ('250'),
       ('255'),
       ('260'),
       ('265'),
       ('270'),
       ('275'),
       ('280'),
       ('285'),
       ('290'),
       ('295'),
       ('300'),
       ('305'),
       ('310'),
       ('315'),
       ('320'),
       ('325'),
       ('330'),
       ('335'),
       ('340'),
       ('345'),
       ('350'),
       ('355'),
       ('360'),
       ('365'),
       ('370'),
       ('375'),
       ('380'),
       ('385'),
       ('390'),
       ('395'),
       ('400'),
       ('405');

INSERT INTO `tire_service_db`.types (type)
VALUES ('universal'),
       ('car'),
       ('suv'),
       ('bus'),
       ('dual'),
       ('truck'),
       ('ring'),
       ('heavy'),
       ('patch'),
       ('valve');

INSERT INTO `tire_service_db`.service_items (name)
VALUES ('mounting'),
       ('balancing'),
       ('wheel_remove'),
       ('valve_replacement'),
       ('balance_weights_adhesive'),
       ('balance_weights_add'),
       ('cleaning'),
       ('camera_insert'),
       ('diagnostic'),
       ('puncture_repair'),
       ('cut_repair'),
       ('big_cut_repair'),
       ('vertical_cut_repair'),
       ('sealing'),
       ('using_key_jack'),
       ('pumping'),
       ('explosive_pumping'),
       ('patch_up40'),
       ('patch_up50'),
       ('patch_up6'),
       ('patch_rad11'),
       ('patch_rad19'),
       ('patch_rad20'),
       ('patch_rad40'),
       ('patch_rs35'),
       ('patch_r35'),
       ('patch_tl110'),
       ('patch_tl115'),
       ('valve_tr413'),
       ('valve_tr413c'),
       ('valve_tk'),
       ('valve_gk135');


INSERT INTO tire_service_db.service_item_prices (fk_service_item_id, fk_type_id, fk_diameter_id, price)
VALUES
       ((SELECT id from tire_service_db.service_items where name = 'mounting' ),(Select id from tire_service_db.types where type = 'truck'), null, 5.00),
       ((SELECT id from tire_service_db.service_items where name = 'mounting' ),(Select id from tire_service_db.types where type = 'ring'), null, 8.00),
       ((SELECT id from tire_service_db.service_items where name = 'mounting' ),(Select id from tire_service_db.types where type = 'heavy'), null, 17.00),
       ((SELECT id from tire_service_db.service_items where name = 'balancing' ),(Select id from tire_service_db.types where type = 'truck'), null, 5.00),
       ((SELECT id from tire_service_db.service_items where name = 'sealing' ),(Select id from tire_service_db.types where type = 'truck'), null, 2.00),
       ((SELECT id from tire_service_db.service_items where name = 'pumping' ),(Select id from tire_service_db.types where type = 'truck'), null, 1.00),
       ((SELECT id from tire_service_db.service_items where name = 'pumping' ),(Select id from tire_service_db.types where type = 'heavy'), null, 1.50),
       ((SELECT id from tire_service_db.service_items where name = 'wheel_remove' ),(Select id from tire_service_db.types where type = 'truck'), null, 3.00),
       ((SELECT id from tire_service_db.service_items where name = 'wheel_remove' ),(Select id from tire_service_db.types where type = 'heavy'), null, 5.00),
       ((SELECT id from tire_service_db.service_items where name = 'mounting' ),(Select id from tire_service_db.types where type = 'car'), (Select id from tire_service_db.diameters where diameter = 'r13'), 3.00),
       ((SELECT id from tire_service_db.service_items where name = 'mounting' ),(Select id from tire_service_db.types where type = 'car'), (Select id from tire_service_db.diameters where diameter = 'r14'), 3.00),
       ((SELECT id from tire_service_db.service_items where name = 'mounting' ),(Select id from tire_service_db.types where type = 'car'), (Select id from tire_service_db.diameters where diameter = 'r15'), 4.00),
       ((SELECT id from tire_service_db.service_items where name = 'mounting' ),(Select id from tire_service_db.types where type = 'car'), (Select id from tire_service_db.diameters where diameter = 'r16'), 4.00),
       ((SELECT id from tire_service_db.service_items where name = 'mounting' ),(Select id from tire_service_db.types where type = 'car'), (Select id from tire_service_db.diameters where diameter = 'r17'), 5.00),
       ((SELECT id from tire_service_db.service_items where name = 'mounting' ),(Select id from tire_service_db.types where type = 'car'), (Select id from tire_service_db.diameters where diameter = 'r18'), 5.00),
       ((SELECT id from tire_service_db.service_items where name = 'mounting' ),(Select id from tire_service_db.types where type = 'car'), (Select id from tire_service_db.diameters where diameter = 'r19'), 6.00),
       ((SELECT id from tire_service_db.service_items where name = 'mounting' ),(Select id from tire_service_db.types where type = 'car'), (Select id from tire_service_db.diameters where diameter = 'r20'), 6.00),
       ((SELECT id from tire_service_db.service_items where name = 'mounting' ),(Select id from tire_service_db.types where type = 'car'), (Select id from tire_service_db.diameters where diameter = 'r21'), 7.00),
       ((SELECT id from tire_service_db.service_items where name = 'mounting' ),(Select id from tire_service_db.types where type = 'car'), (Select id from tire_service_db.diameters where diameter = 'r22'), 7.00),
       ((SELECT id from tire_service_db.service_items where name = 'balancing' ),(Select id from tire_service_db.types where type = 'car'), (Select id from tire_service_db.diameters where diameter = 'r13'), 3.00),
       ((SELECT id from tire_service_db.service_items where name = 'balancing' ),(Select id from tire_service_db.types where type = 'car'), (Select id from tire_service_db.diameters where diameter = 'r14'), 3.00),
       ((SELECT id from tire_service_db.service_items where name = 'balancing' ),(Select id from tire_service_db.types where type = 'car'), (Select id from tire_service_db.diameters where diameter = 'r15'), 3.00),
       ((SELECT id from tire_service_db.service_items where name = 'balancing' ),(Select id from tire_service_db.types where type = 'car'), (Select id from tire_service_db.diameters where diameter = 'r16'), 4.00),
       ((SELECT id from tire_service_db.service_items where name = 'balancing' ),(Select id from tire_service_db.types where type = 'car'), (Select id from tire_service_db.diameters where diameter = 'r17'), 4.00),
       ((SELECT id from tire_service_db.service_items where name = 'balancing' ),(Select id from tire_service_db.types where type = 'car'), (Select id from tire_service_db.diameters where diameter = 'r18'), 5.00),
       ((SELECT id from tire_service_db.service_items where name = 'balancing' ),(Select id from tire_service_db.types where type = 'car'), (Select id from tire_service_db.diameters where diameter = 'r19'), 6.00),
       ((SELECT id from tire_service_db.service_items where name = 'balancing' ),(Select id from tire_service_db.types where type = 'car'), (Select id from tire_service_db.diameters where diameter = 'r20'), 6.00),
       ((SELECT id from tire_service_db.service_items where name = 'balancing' ),(Select id from tire_service_db.types where type = 'car'), (Select id from tire_service_db.diameters where diameter = 'r21'), 7.00),
       ((SELECT id from tire_service_db.service_items where name = 'balancing' ),(Select id from tire_service_db.types where type = 'car'), (Select id from tire_service_db.diameters where diameter = 'r22'), 7.00),
       ((SELECT id from tire_service_db.service_items where name = 'wheel_remove' ),(Select id from tire_service_db.types where type = 'car'), null, 2.00),
       ((SELECT id from tire_service_db.service_items where name = 'mounting' ),(Select id from tire_service_db.types where type = 'suv'), (Select id from tire_service_db.diameters where diameter = 'r14c'), 4.00),
       ((SELECT id from tire_service_db.service_items where name = 'mounting' ),(Select id from tire_service_db.types where type = 'suv'), (Select id from tire_service_db.diameters where diameter = 'r15c'), 4.00),
       ((SELECT id from tire_service_db.service_items where name = 'mounting' ),(Select id from tire_service_db.types where type = 'suv'), (Select id from tire_service_db.diameters where diameter = 'r16c'), 5.00),
       ((SELECT id from tire_service_db.service_items where name = 'mounting' ),(Select id from tire_service_db.types where type = 'suv'), (Select id from tire_service_db.diameters where diameter = 'r17c'), 6.00),
       ((SELECT id from tire_service_db.service_items where name = 'mounting' ),(Select id from tire_service_db.types where type = 'suv'), null, 7.00),
       ((SELECT id from tire_service_db.service_items where name = 'mounting' ),(Select id from tire_service_db.types where type = 'bus'), null, 8.00),
       ((SELECT id from tire_service_db.service_items where name = 'balancing' ),(Select id from tire_service_db.types where type = 'suv'), (Select id from tire_service_db.diameters where diameter = 'r14c'), 4.00),
       ((SELECT id from tire_service_db.service_items where name = 'balancing' ),(Select id from tire_service_db.types where type = 'suv'), (Select id from tire_service_db.diameters where diameter = 'r15c'), 4.00),
       ((SELECT id from tire_service_db.service_items where name = 'balancing' ),(Select id from tire_service_db.types where type = 'suv'), (Select id from tire_service_db.diameters where diameter = 'r16c'), 5.00),
       ((SELECT id from tire_service_db.service_items where name = 'balancing' ),(Select id from tire_service_db.types where type = 'suv'), (Select id from tire_service_db.diameters where diameter = 'r17c'), 5.00),
       ((SELECT id from tire_service_db.service_items where name = 'balancing' ),(Select id from tire_service_db.types where type = 'suv'), null, 6.00),
       ((SELECT id from tire_service_db.service_items where name = 'balancing' ),(Select id from tire_service_db.types where type = 'bus'), null, 7.00),
       ((SELECT id from tire_service_db.service_items where name = 'wheel_remove' ),(Select id from tire_service_db.types where type = 'suv'), null, 3.00),
       ((SELECT id from tire_service_db.service_items where name = 'wheel_remove' ),(Select id from tire_service_db.types where type = 'bus'), null, 4.00),
       ((SELECT id from tire_service_db.service_items where name = 'wheel_remove' ),(Select id from tire_service_db.types where type = 'dual'), null, 6.00),
       ((SELECT id from tire_service_db.service_items where name = 'valve_replacement' ),(Select id from tire_service_db.types where type = 'universal'), null, 1.00),
       ((SELECT id from tire_service_db.service_items where name = 'balance_weights_adhesive' ),(Select id from tire_service_db.types where type = 'universal'), null, 1.00),
       ((SELECT id from tire_service_db.service_items where name = 'balance_weights_add' ),(Select id from tire_service_db.types where type = 'universal'), null, 2.00),
       ((SELECT id from tire_service_db.service_items where name = 'mounting' ),(Select id from tire_service_db.types where type = 'universal'), null, 2.00),
       ((SELECT id from tire_service_db.service_items where name = 'camera_insert' ),(Select id from tire_service_db.types where type = 'universal'), null, 2.00),
       ((SELECT id from tire_service_db.service_items where name = 'cleaning' ),(Select id from tire_service_db.types where type = 'universal'), null, 1.00),
       ((SELECT id from tire_service_db.service_items where name = 'diagnostic' ),(Select id from tire_service_db.types where type = 'universal'), null, 2.00),
       ((SELECT id from tire_service_db.service_items where name = 'puncture_repair' ),(Select id from tire_service_db.types where type = 'universal'), null, 3.00),
       ((SELECT id from tire_service_db.service_items where name = 'cut_repair' ),(Select id from tire_service_db.types where type = 'universal'), null, 4.00),
       ((SELECT id from tire_service_db.service_items where name = 'big_cut_repair' ),(Select id from tire_service_db.types where type = 'universal'), null, 5.00),
       ((SELECT id from tire_service_db.service_items where name = 'vertical_cut_repair' ),(Select id from tire_service_db.types where type = 'universal'), null, 7.00),
       ((SELECT id from tire_service_db.service_items where name = 'sealing' ),(Select id from tire_service_db.types where type = 'universal'), null, 5.00),
       ((SELECT id from tire_service_db.service_items where name = 'using_key_jack' ),(Select id from tire_service_db.types where type = 'universal'), null, 1.00),
       ((SELECT id from tire_service_db.service_items where name = 'diagnostic' ),(Select id from tire_service_db.types where type = 'universal'), null, 2.00),
       ((SELECT id from tire_service_db.service_items where name = 'pumping' ),(Select id from tire_service_db.types where type = 'universal'), null, 0.50),
       ((SELECT id from tire_service_db.service_items where name = 'explosive_pumping' ),(Select id from tire_service_db.types where type = 'universal'), null, 2.00),
       ((SELECT id from tire_service_db.service_items where name = 'patch_up40' ),(Select id from tire_service_db.types where type = 'patch'), null, 4.00),
       ((SELECT id from tire_service_db.service_items where name = 'patch_up50' ),(Select id from tire_service_db.types where type = 'patch'), null, 4.00),
       ((SELECT id from tire_service_db.service_items where name = 'patch_up6' ),(Select id from tire_service_db.types where type = 'patch'), null, 5.00),
       ((SELECT id from tire_service_db.service_items where name = 'patch_rad11' ),(Select id from tire_service_db.types where type = 'patch'), null, 8.00),
       ((SELECT id from tire_service_db.service_items where name = 'patch_rad19' ),(Select id from tire_service_db.types where type = 'patch'), null, 10.00),
       ((SELECT id from tire_service_db.service_items where name = 'patch_rad20' ),(Select id from tire_service_db.types where type = 'patch'), null, 11.00),
       ((SELECT id from tire_service_db.service_items where name = 'patch_rad40' ),(Select id from tire_service_db.types where type = 'patch'), null, 35.00),
       ((SELECT id from tire_service_db.service_items where name = 'patch_rs35' ),(Select id from tire_service_db.types where type = 'patch'), null, 45.00),
       ((SELECT id from tire_service_db.service_items where name = 'patch_r35' ),(Select id from tire_service_db.types where type = 'patch'), null, 40.00),
       ((SELECT id from tire_service_db.service_items where name = 'patch_tl110' ),(Select id from tire_service_db.types where type = 'patch'), null, 9.00),
       ((SELECT id from tire_service_db.service_items where name = 'patch_tl115' ),(Select id from tire_service_db.types where type = 'patch'), null, 11.00),
       ((SELECT id from tire_service_db.service_items where name = 'valve_tr413' ),(Select id from tire_service_db.types where type = 'valve'), null, 1.00),
       ((SELECT id from tire_service_db.service_items where name = 'valve_tr413c' ),(Select id from tire_service_db.types where type = 'valve'), null, 2.00),
       ((SELECT id from tire_service_db.service_items where name = 'valve_tk' ),(Select id from tire_service_db.types where type = 'valve'), null, 35.00),
       ((SELECT id from tire_service_db.service_items where name = 'valve_gk135' ),(Select id from tire_service_db.types where type = 'valve'), null, 25.00);



