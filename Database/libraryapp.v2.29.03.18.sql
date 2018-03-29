-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th3 29, 2018 lúc 05:06 AM
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
-- Cấu trúc bảng cho bảng `m_settings`
--

DROP TABLE IF EXISTS `m_settings`;
CREATE TABLE `m_settings` (
  `setting_id` int(11) NOT NULL COMMENT 'setting_id',
  `s_key` varchar(255) CHARACTER SET latin1 NOT NULL COMMENT 's_key  Setting key',
  `s_value` int(11) NOT NULL COMMENT 's_value  Setting value',
  `s_name` varchar(255) DEFAULT NULL COMMENT 's_name  Setting name',
  `deleted_flag` int(1) DEFAULT NULL,
  `created_user` int(11) DEFAULT NULL COMMENT 'created_user',
  `created_time` datetime DEFAULT NULL COMMENT 'created_time',
  `updated_user` int(11) DEFAULT NULL COMMENT 'updated_user',
  `udpated_time` datetime DEFAULT NULL COMMENT 'updated_time'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `m_settings`
--

INSERT INTO `m_settings` (`setting_id`, `s_key`, `s_value`, `s_name`, `deleted_flag`, `created_user`, `created_time`, `updated_user`, `udpated_time`) VALUES
(1, 'USED_FLAG', 0, 'Chưa sử dụng', 0, 9999, '2017-07-11 00:00:00', NULL, NULL),
(2, 'USED_FLAG', 1, 'Đã sử dụng', 0, 9999, '2017-07-11 00:00:00', NULL, NULL),
(3, 'USER_ROLE', 0, 'Super Admin', 0, 9999, '2017-07-11 00:00:00', NULL, NULL),
(4, 'USER_ROLE', 1, 'Admin', 0, 9999, '2017-07-11 00:00:00', NULL, NULL),
(5, 'STATUS', 0, 'Không công khai', 0, 9999, '2017-07-11 00:00:00', NULL, NULL),
(6, 'STATUS', 1, 'Công khai', 0, 9999, '2017-07-11 00:00:00', NULL, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `m_user`
--

DROP TABLE IF EXISTS `m_user`;
CREATE TABLE `m_user` (
  `user_id` int(11) NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `phone_num` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `avata` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `user_role` int(1) DEFAULT '2' COMMENT '1: super admin 2: admin',
  `remember_token` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `deleted_flag` int(1) DEFAULT NULL,
  `created_user` int(11) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_user` int(11) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `m_user`
--

INSERT INTO `m_user` (`user_id`, `email`, `phone_num`, `name`, `password`, `avata`, `user_role`, `remember_token`, `deleted_flag`, `created_user`, `created_time`, `updated_user`, `updated_time`) VALUES
(1, 'anhtan3396@gmail.com', '0907172440', 'Kaffeines', '$2y$10$kMcpoQb3IP/B.Np/uv2G.eQEEGcT1UAQ/5xPpHTYaDdaIP6wgxxj2', 'nulla', 0, 'aaa', 0, 1, '2017-07-11 00:00:00', 1, '2017-07-11 00:00:00');

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

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `m_settings`
--
ALTER TABLE `m_settings`
  ADD PRIMARY KEY (`setting_id`);

--
-- Chỉ mục cho bảng `m_user`
--
ALTER TABLE `m_user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `UC_Users_Email` (`email`);

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
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `m_user`
--
ALTER TABLE `m_user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
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
