-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1:3306
-- Thời gian đã tạo: Th6 10, 2019 lúc 01:26 PM
-- Phiên bản máy phục vụ: 5.7.24
-- Phiên bản PHP: 7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `ordercafe`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cartitem`
--

DROP TABLE IF EXISTS `cartitem`;
CREATE TABLE IF NOT EXISTS `cartitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idproduct` int(11) DEFAULT NULL,
  `totalPrice` decimal(19,2) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `idorder` int(11) DEFAULT NULL,
  `createdate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=45 DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `cartitem`
--

INSERT INTO `cartitem` (`id`, `idproduct`, `totalPrice`, `quantity`, `idorder`, `createdate`) VALUES
(35, 1, '160000.00', 8, 22, '2019-06-08'),
(36, 2, '140000.00', 7, 22, '2019-06-08'),
(37, 1, '80000.00', 4, 23, '2019-06-08'),
(38, 1, '100000.00', 5, 24, '2019-06-08'),
(39, 2, '20000.00', 1, 25, '2019-06-08'),
(40, 1, '20000.00', 1, 25, '2019-06-08'),
(41, 3, '80000.00', 5, 26, '2019-06-09'),
(42, 2, '20000.00', 1, 27, '2019-06-09'),
(43, 3, '32000.00', 2, 28, '2019-06-09'),
(44, 3, '80000.00', 5, 29, '2019-06-10');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `order`
--

DROP TABLE IF EXISTS `order`;
CREATE TABLE IF NOT EXISTS `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `supertotalPrice` decimal(19,2) DEFAULT NULL,
  `createDate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `order`
--

INSERT INTO `order` (`id`, `supertotalPrice`, `createDate`) VALUES
(29, '80000.00', '2019-06-10'),
(28, '32000.00', '2019-06-09'),
(27, '20000.00', '2019-06-09'),
(26, '80000.00', '2019-06-09'),
(25, '40000.00', '2019-06-08'),
(24, '100000.00', '2019-06-08'),
(23, '80000.00', '2019-06-08'),
(22, '300000.00', '2019-06-08');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) CHARACTER SET utf8 DEFAULT NULL,
  `price` double DEFAULT NULL,
  `active` int(11) DEFAULT NULL,
  `createdate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`id`, `name`, `price`, `active`, `createdate`) VALUES
(1, 'Cafe', 20000, 1, '2019-04-06'),
(2, 'Soda', 20000, 1, '2019-04-06'),
(3, 'Number One', 16000, 0, '2019-06-09');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `revenue`
--

DROP TABLE IF EXISTS `revenue`;
CREATE TABLE IF NOT EXISTS `revenue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idproduct` int(11) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `createdate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `revenue`
--

INSERT INTO `revenue` (`id`, `idproduct`, `quantity`, `createdate`) VALUES
(6, 3, 7, '2019-06-09'),
(5, 2, 8, '2019-06-08'),
(4, 1, 18, '2019-06-08'),
(8, 2, 1, '2019-06-09'),
(9, 3, 5, '2019-06-10');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`id`, `username`, `password`, `role`, `name`) VALUES
(1, 'xomrayno5', '12345', 'admin', 'nguyen tam'),
(2, 'xomrayno1', '12345', 'employee', 'nguyen chi tam');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
