CREATE DATABASE IF NOT EXISTS `wishlist`;
USE `wishlist`;

CREATE TABLE IF NOT EXISTS `clients` (
  `email` varchar(55) NOT NULL,
  `name` varchar(110) NOT NULL,
  `password` varchar(110) NOT NULL,
  `role` varchar(55) NOT NULL,
  PRIMARY KEY (`email`)
);

CREATE TABLE IF NOT EXISTS `whishlist_itens` (
  `email` varchar(55) NOT NULL,
  `product_id` varchar(36) NOT NULL,
  PRIMARY KEY (`product_id`, `email`),
  FOREIGN KEY (`email`) REFERENCES `clients` (`email`) ON DELETE CASCADE
);

INSERT IGNORE INTO `clients`
VALUES ('admin@admin.com', 'Admin para exemplicar casos de autenticacao e autorizacao', '$2a$10$4pFAl91QUAUqZSn04UBLl.qpn2e3VkVKLmFv./arqkLV/UzVb.MIq', 'admin');