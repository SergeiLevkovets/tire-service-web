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
    `role`     VARCHAR(255) NOT NULL
);

CREATE TABLE `tire_service_db`.`service_price_truck`
(
    `id`    INTEGER        NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `name`  VARCHAR(255)   NOT NULL,
    `price` NUMERIC(10, 2) NOT NULL
);

CREATE TABLE `tire_service_db`.`service_price_car`
(
    `id`    INTEGER        NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `name`  VARCHAR(255)   NOT NULL,
    `price` NUMERIC(10, 2) NOT NULL
);

CREATE TABLE `tire_service_db`.`service_price_suv`
(
    `id`    INTEGER        NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `name`  VARCHAR(255)   NOT NULL,
    `price` NUMERIC(10, 2) NOT NULL
);

CREATE TABLE `tire_service_db`.`service_price`
(
    `id`    INTEGER        NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `name`  VARCHAR(255)   NOT NULL,
    `price` NUMERIC(10, 2) NOT NULL
);

CREATE TABLE `tire_service_db`.`materials_patch`
(
    `id`    INTEGER        NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `name`  VARCHAR(255)   NOT NULL,
    `count` INTEGER        NOT NULL,
    `price` NUMERIC(10, 2) NOT NULL
);

CREATE TABLE `tire_service_db`.`materials_valve`
(
    `id`    INTEGER        NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `name`  VARCHAR(255)   NOT NULL,
    `count` INTEGER        NOT NULL,
    `price` NUMERIC(10, 2) NOT NULL
);

CREATE TABLE `tire_service_db`.tires
(
    `id`       INTEGER   NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `width`    INTEGER   NOT NULL,
    `height`   INTEGER   NOT NULL,
    `diameter` INTEGER   NOT NULL,
    `date`     TIMESTAMP NOT NULL
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

--     CONSTRAINT `fk_to_user` FOREIGN KEY (`fk_user_id`) REFERENCES user (`user_id`)

INSERT INTO `tire_service_db`.service_price_truck (name, price)
VALUES ('truck_mounting', 5.00),
       ('truck_mounting_r20k', 8.00),
       ('truck_mounting_heavy', 17.00),
       ('truck_balancing', 5.00),
       ('truck_sealing', 2.00),
       ('truck_pumping', 1.00),
       ('truck_pumping_heavy', 1.50),
       ('truck_wheel_remove', 3.00),
       ('truck_wheel_remove_heavy', 5.00),
       ('truck_wheel_install', 3.00),
       ('truck_wheel_install_heavy', 5.00),

       ('truck_full_service', 19.00),
       ('truck_full_service_r20k', 26.00),
       ('truck_full_service_heavy', 45.50),
       ('truck_full_service_with_balancing', 24.00);

INSERT INTO `tire_service_db`.service_price_car (`name`, `price`)
VALUES ('car_mounting_r13_r14', 3.00),
       ('car_mounting_r15_r16', 4.00),
       ('car_mounting_r17_r18', 5.00),
       ('car_mounting_r19_r20', 6.00),
       ('car_mounting_r21_r22', 7.00),
       ('car_balancing_r13_r14_r15', 3.00),
       ('car_balancing_r16_r17', 4.00),
       ('car_balancing_r18', 5.00),
       ('car_balancing_r19_r20', 6.00),
       ('car_balancing_r21_r22', 7.00),
       ('car_wheel_remove', 2.00);



INSERT INTO `tire_service_db`.service_price_suv (`name`, `price`)
VALUES ('suv_mounting_r14c_r15c', 4.00),
       ('suv_mounting_r16c', 5.00),
       ('suv_mounting_r17c', 6.00),
       ('suv_mounting_suv', 7.00),
       ('suv_mounting_bus', 8.00),
       ('suv_balancing_r14c_r15c', 4.00),
       ('suv_balancing_r16c_r17c', 5.00),
       ('suv_balancing_suv', 6.00),
       ('suv_balancing_bus', 7.00),
       ('suv_wheel_remove', 3.00),
       ('suv_wheel_remove_bus', 4.00),
       ('suv_wheel_remove_dual', 6.00);


INSERT INTO `tire_service_db`.service_price (`name`, `price`)
VALUES ('valve_replacement', 1.00),
       ('balance_weights_adhesive', 1.00),
       ('balance_weights_add', 2.00),
       ('installation', 2.00),
       ('reinstalling', 2.00),
       ('camera_insert', 2.00),
       ('cleaning', 1.00),
       ('diagnostic', 2.00),
       ('puncture_repair', 3.00),
       ('cut_repair', 4.00),
       ('big_cut_repair', 5.00),
       ('vertical_cut_repair', 7.00),
       ('tire_sealing', 5.00),
       ('using_key_jack', 1.00),
       ('tire_diagnostic', 2.00),
       ('tire_pumping', 0.50),
       ('explosive_pumping', 2.00);

INSERT INTO `tire_service_db`.`materials_patch` (`name`, `count`, `price`)
VALUES ('patch_up40', 50, 4.00),
       ('patch_up50', 50, 4.00),
       ('patch_up6', 50, 5.00),
       ('patch_rad11', 50, 8.00),
       ('patch_rad19', 50, 10.00),
       ('patch_rad20', 50, 11.00),
       ('patch_rad40', 50, 35.00),
       ('patch_rs35', 50, 45.00),
       ('patch_r35', 50, 40.00),
       ('patch_tl110', 50, 9.00),
       ('patch_tl115', 50, 11.00);

INSERT INTO `tire_service_db`.`materials_valve` (`name`, `count`, `price`)
VALUES ('valve_tr413', 50, 2.00),
       ('valve_tr413c', 50, 3.00),
       ('valve_tk', 50, 35.00),
       ('valve_gk135', 50, 25.00);
