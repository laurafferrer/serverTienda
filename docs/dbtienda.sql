-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: database:3306
-- Tiempo de generación: 25-01-2024 a las 17:41:56
-- Versión del servidor: 8.2.0
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `dbtienda`
--
CREATE DATABASE IF NOT EXISTS `dbtienda`;
USE `dbtienda`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `captcha`
--

CREATE TABLE `captcha` (
  `id` bigint NOT NULL,
  `text` varchar(255) CHARACTER SET utf16 COLLATE utf16_bin NOT NULL,
  `image` longblob
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cart`
--

CREATE TABLE `cart` (
  `id` bigint NOT NULL,
  `amount` int NOT NULL,
  `product_id` bigint NOT NULL,
  `user_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `category`
--

CREATE TABLE `category` (
  `id` bigint NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `category`
--

INSERT INTO `category` (`id`, `name`) VALUES
(1, 'Cartulina'),
(2, 'Libreta'),
(3, 'Boligrafo'),
(4, 'Mochila');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pendent`
--

CREATE TABLE `pendent` (
  `id` bigint NOT NULL,
  `timecode` timestamp NOT NULL,
  `token` varchar(512) COLLATE utf16_bin NULL,
  `captcha_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_bin;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `product`
--

CREATE TABLE `product` (
  `id` bigint NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `stock` int NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `category_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `product`
--

INSERT INTO `product` (`id`, `name`, `description`, `price`, `stock`, `image`, `category_id`) VALUES
(1, 'Bolígrafo azul', 'Bolígrafo azul marca Bic', 1, 150, NULL, 1),
(2, 'Bolígrafo rojo', 'Bolígrafo rojo marca Bic', 1, 150, NULL, 1),
(3, 'Libreta A4', 'Libreta A4 marca Oxford', 2.5, 100, NULL, 2),
(4, 'Lápiz 2HB', 'Lápiz marca staedtler ', 0.7, 200, NULL, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `purchase`
--

CREATE TABLE `purchase` (
  `id` bigint NOT NULL,
  `date_purchase` date NOT NULL,
  `num_bill` bigint NOT NULL,
  `date_bill` date DEFAULT NULL,
  `user_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `purchase`
--

INSERT INTO `purchase` (`id`, `date_purchase`, `num_bill`, `date_bill`, `user_id`) VALUES
(1, '2024-01-21', 1, '2024-01-23', 1),
(2, '2024-01-11', 2, '2024-01-23', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `purchase_detail`
--

CREATE TABLE `purchase_detail` (
  `id` bigint NOT NULL,
  `amount` int NOT NULL,
  `price` double NOT NULL,
  `product_id` bigint DEFAULT NULL,
  `purchase_id` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `id` bigint NOT NULL,
  `dni` varchar(9) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(512) NOT NULL,
  `token_password` varchar(256) CHARACTER SET utf16 COLLATE utf16_unicode_ci DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `birth_date` date NOT NULL,
  `phone_number` varchar(9) NOT NULL,
  `email` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `city` varchar(255) NOT NULL,
  `postal_code` int NOT NULL,
  `role` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id`, `dni`, `username`, `password`, `token_password`, `name`, `surname`, `last_name`, `birth_date`, `phone_number`, `email`, `address`, `city`, `postal_code`, `role`) VALUES
(1, '00000000A', 'marmarzo', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'NULL', 'Mario', 'Marzo', 'Cruz', '1990-10-23', '666666666', 'marmarzo@gmail.com', 'Calle Mayor 1A', 'Madrid', 52301, 0),
(2, '00000000B', 'pepperez', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', 'NULL', 'Pepe', 'Perez', 'Cara', '2000-06-13', '777777777', 'pepperez@gmail.com', 'Calle Menor 1A', 'Barcelona', 52051, 1),
(3, '00000000H', 'lauraferrer', 'e2cac5c5f7e52ab03441bb70e89726ddbd1f6e5b683dde05fb65e0720290179e', NULL, 'Laura', 'Ferrer', 'Esteve', '2003-10-10', '666666666', 'lferrer@gmail.com', 'Calle Tierra 1C', 'Valencia', 56530, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `captcha`
--
ALTER TABLE `captcha`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `pendent`
--
ALTER TABLE `pendent`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `purchase`
--
ALTER TABLE `purchase`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `purchase_detail`
--
ALTER TABLE `purchase_detail`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `captcha`
--
ALTER TABLE `captcha`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `cart`
--
ALTER TABLE `cart`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `category`
--
ALTER TABLE `category`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `pendent`
--
ALTER TABLE `pendent`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `product`
--
ALTER TABLE `product`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `purchase`
--
ALTER TABLE `purchase`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `purchase_detail`
--
ALTER TABLE `purchase_detail`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--