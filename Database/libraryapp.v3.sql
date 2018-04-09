-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Apr 09, 2018 at 02:53 PM
-- Server version: 10.1.31-MariaDB
-- PHP Version: 7.2.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `libraryapp`
--

-- --------------------------------------------------------

--
-- Table structure for table `m_settings`
--

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
-- Dumping data for table `m_settings`
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
-- Table structure for table `m_user`
--

CREATE TABLE `m_user` (
  `user_id` int(11) NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `phone_num` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `avatar` text CHARACTER SET utf8,
  `user_role` int(1) DEFAULT '2' COMMENT '1: super admin 2: admin',
  `remember_token` text COLLATE utf8_unicode_ci,
  `deleted_flag` int(1) DEFAULT NULL,
  `created_user` int(11) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `updated_user` int(11) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `m_user`
--

INSERT INTO `m_user` (`user_id`, `email`, `phone_num`, `name`, `password`, `avatar`, `user_role`, `remember_token`, `deleted_flag`, `created_user`, `created_time`, `updated_user`, `updated_time`) VALUES
(1, 'anhtan3396@gmail.com', '0907172440', 'Kaffeines', '$2y$10$kMcpoQb3IP/B.Np/uv2G.eQEEGcT1UAQ/5xPpHTYaDdaIP6wgxxj2', 'nulla', 0, 'aaa', 0, 1, '2017-07-11 00:00:00', 1, '2017-07-11 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `t_category`
--

CREATE TABLE `t_category` (
  `c_type_id` int(11) NOT NULL,
  `c_type_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `t_product`
--

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
-- Indexes for dumped tables
--

--
-- Indexes for table `m_settings`
--
ALTER TABLE `m_settings`
  ADD PRIMARY KEY (`setting_id`);

--
-- Indexes for table `m_user`
--
ALTER TABLE `m_user`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `UC_Users_Email` (`email`);

--
-- Indexes for table `t_category`
--
ALTER TABLE `t_category`
  ADD PRIMARY KEY (`c_type_id`);

--
-- Indexes for table `t_product`
--
ALTER TABLE `t_product`
  ADD PRIMARY KEY (`p_id`),
  ADD KEY `t_product` (`c_type_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `m_user`
--
ALTER TABLE `m_user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `t_category`
--
ALTER TABLE `t_category`
  MODIFY `c_type_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `t_product`
--
ALTER TABLE `t_product`
  MODIFY `p_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `t_product`
--
ALTER TABLE `t_product`
  ADD CONSTRAINT `t_product` FOREIGN KEY (`c_type_id`) REFERENCES `t_category` (`c_type_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
