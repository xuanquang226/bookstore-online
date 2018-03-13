-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th3 13, 2018 lúc 02:13 PM
-- Phiên bản máy phục vụ: 10.1.25-MariaDB
-- Phiên bản PHP: 7.1.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `libraryapp`
--
CREATE DATABASE IF NOT EXISTS `libraryapp` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `libraryapp`;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `t_category`
--

DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `c_type_id` int(11) NOT NULL,
  `c_type_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `t_product`
--

DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product` (
  `p_id` int(11) NOT NULL,
  `c_type_id` int(11) DEFAULT NULL,
  `p_name` varchar(255) DEFAULT NULL,
  `p_price` int(100) DEFAULT NULL,
  `p_quantity` int(10) DEFAULT NULL,
  `p_desc` varchar(100) DEFAULT NULL,
  `p_new_flag` int(1) DEFAULT NULL,
  `p_img` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `t_user`
--

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `u_id` int(11) NOT NULL,
  `u_name` varchar(255) DEFAULT NULL,
  `u_pass` varchar(255) DEFAULT NULL,
  `u_mail` varchar(255) DEFAULT NULL,
  `u_coin` int(10) DEFAULT NULL,
  `u_phone` int(30) DEFAULT NULL,
  `u_address` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `t_category`
--
ALTER TABLE `t_category`
  ADD PRIMARY KEY (`c_type_id`);

--
-- Chỉ mục cho bảng `t_product`
--
ALTER TABLE `t_product`
  ADD PRIMARY KEY (`p_id`),
  ADD KEY `t_product` (`c_type_id`);

--
-- Chỉ mục cho bảng `t_user`
--
ALTER TABLE `t_user`
  ADD PRIMARY KEY (`u_id`),
  ADD UNIQUE KEY `UC_MAIL_USER` (`u_mail`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `t_category`
--
ALTER TABLE `t_category`
  MODIFY `c_type_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT cho bảng `t_product`
--
ALTER TABLE `t_product`
  MODIFY `p_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT cho bảng `t_user`
--
ALTER TABLE `t_user`
  MODIFY `u_id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `t_product`
--
ALTER TABLE `t_product`
  ADD CONSTRAINT `t_product` FOREIGN KEY (`c_type_id`) REFERENCES `t_category` (`c_type_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
