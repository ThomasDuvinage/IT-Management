-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Jun 20, 2021 at 07:28 AM
-- Server version: 5.7.24
-- PHP Version: 7.2.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gest_parc`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_add_post` (IN `p_name` VARCHAR(255), IN `p_zone` VARCHAR(255), IN `p_building` VARCHAR(255), IN `p_class` VARCHAR(255))  INSERT INTO POST (name, zone, buikding, classroom, added_on, edited_on, booking)
    VALUES (p_name, p_zone, p_building, p_class, DATETIME.NOW(), NULL, NULL)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_add_teacher` (IN `p_name` VARCHAR(255), IN `p_username` VARCHAR(255), IN `p_email` VARCHAR(255), IN `p_password` VARCHAR(255), IN `p_role` VARCHAR(255), IN `p_department` VARCHAR(255))  INSERT INTO user(name, username, email, password, role, department, isCOnnected, added_on, edited_on)
    			VALUES(p_name, p_username, p_email, p_password, p_role, p_department, FALSE, DATETIME.NOW(), NULL)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_change_password` (IN `p_password` VARCHAR(255), IN `p_id` INT)  UPDATE user
    SET password = p_password
    WHERE id = p_id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_delete_post` (IN `p_id` INT)  DELETE FROM post
    WHERE id = p_id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_delete_teacher` (IN `p_id` INT)  DELETE FROM user
    WHERE id = p_id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_edit_a_teacher_department` (IN `p_id` INT, IN `p_department` VARCHAR(255))  UPDATE user
    SET department = p_department AND edited_on = DATETIME.NOW()
    WHERE id = p_id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_edit_a_teacher_name` (IN `p_id` INT, IN `p_name` VARCHAR(255))  UPDATE user
    SET name = p_name AND edited_on = DATETIME.NOW()
    WHERE id = p_id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_edit_post_building` (IN `p_building` VARCHAR(255), IN `p_id` INT)  UPDATE post
    SET building = p_building AND edited_on = DATETIME.NOW()
    WHERE id = p_id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_edit_post_class` (IN `p_class` VARCHAR(255), IN `p_id` INT)  UPDATE post
    SET classroom = p_class AND edited_on = DATETIME.NOW()
    WHERE id = p_id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_edit_post_name` (IN `p_name` VARCHAR(255), IN `p_id` INT)  UPDATE post
    SET name = p_name AND edited_on = DATETIME.NOW()
    WHERE id = p_id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_edit_post_zone` (IN `p_zone` VARCHAR(255), IN `p_id` INT)  UPDATE post
    SET zone = p_zone AND edited_on = DATETIME.NOW()
    WHERE id = p_id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_look_for_post_by_booking` (IN `p_booking` INT)  SELECT * 
    FROM post
    WHERE booking = p_booking$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_look_for_post_by_building` (IN `p_building` VARCHAR(255))  SELECT * 
    FROM post
    WHERE building = p_building$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_look_for_post_by_class` (IN `p_class` VARCHAR(255))  SELECT * 
    FROM post
    WHERE class = p_class$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_look_for_post_by_name` (IN `p_name` VARCHAR(255))  SELECT * 
    FROM post 
    WHERE name = p_name$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_look_for_post_by_zone` (IN `p_zone` VARCHAR(255))  SELECT * 
    FROM post
    WHERE zone = p_zone$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_look_for_teacher_by_department` (IN `p_department` VARCHAR(255))  SELECT *
    FROM user
    WHERE role = 'Teacher'
    AND department = p_department$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_look_for_teacher_by_name` (IN `p_name` VARCHAR(255))  SELECT *
    FROM user
    WHERE role = 'Teacher'
    AND name LIKE p_name$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_look_for_teacher_by_name_and_department` (IN `p_name` VARCHAR(255), IN `p_department` VARCHAR(255))  SELECT *
    FROM user
    WHERE role = 'Teacher'
    AND department = p_department
    AND name LIKE p_name$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_set_connected` (IN `p_id` INT)  UPDATE user
    SET isConnected = TRUE
    WHERE id = p_id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_set_disconnected` (IN `p_id` INT)  UPDATE user
    SET isConnected = FALSE
    WHERE id = p_id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_show_all_posts` ()  SELECT * 
    FROM post$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_show_all_teachers` ()  SELECT *
    FROM user
    WHERE role = 'Teacher'$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_show_a_post` (IN `p_id` INT)  SELECT *
    FROM post 
    WHERE id = p_id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_show_profile` (IN `p_id` INT)  SELECT name, username, email, department
    FROM user
    WHERE id = p_id$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_show_teacher_by_id` (IN `id` INT)  SELECT *
    FROM user
    WHERE role = 'Teacher'
    AND id = id$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `id` int(11) NOT NULL,
  `booking_date` datetime NOT NULL,
  `starting_date` datetime NOT NULL,
  `ending_date` datetime NOT NULL,
  `author` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `building`
--

CREATE TABLE `building` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `zone` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `building`
--

INSERT INTO `building` (`id`, `name`, `zone`) VALUES
(1, 'A', 2),
(2, 'B', 2),
(3, 'C', 2),
(4, 'D', 2),
(5, 'E', 2),
(6, 'F', 2),
(7, 'G', 2),
(8, 'H', 2),
(9, 'I', 2),
(10, 'M', 3),
(11, 'LM', 3),
(12, 'RU', 3),
(13, 'Bibliothèque', 3),
(33, 'Pont', 1),
(34, 'RU/MDE', 1),
(35, 'Plateforme Titan', 1),
(36, 'Tour', 1),
(37, 'Rotonde', 1),
(38, 'Pavillon S', 1);

-- --------------------------------------------------------

--
-- Table structure for table `material`
--

CREATE TABLE `material` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `isAvailable` tinyint(1) NOT NULL,
  `state` varchar(255) NOT NULL,
  `added_on` datetime NOT NULL,
  `edited_on` datetime NOT NULL,
  `post` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE `post` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `zone` varchar(255) NOT NULL,
  `building` varchar(255) NOT NULL,
  `added_on` datetime NOT NULL,
  `edited_on` datetime NOT NULL,
  `booking` int(11) NOT NULL,
  `classroom` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `name`) VALUES
(2, 'Administrator'),
(1, 'Teacher');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `isConnected` tinyint(1) NOT NULL,
  `department` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `added_on` datetime NOT NULL,
  `edited_on` datetime NOT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `zone`
--

CREATE TABLE `zone` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `zone`
--

INSERT INTO `zone` (`id`, `name`) VALUES
(2, 'Belfort'),
(3, 'Montbéliard'),
(1, 'Sevenans');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_user_booking` (`author`);

--
-- Indexes for table `building`
--
ALTER TABLE `building`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`),
  ADD KEY `fk_zone_building` (`zone`);

--
-- Indexes for table `material`
--
ALTER TABLE `material`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uc_material_name` (`name`),
  ADD KEY `fk_post_material` (`post`);

--
-- Indexes for table `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uc_post_name` (`name`),
  ADD KEY `fk_post_booking` (`booking`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uc_role_name` (`name`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uc_user_username` (`username`),
  ADD UNIQUE KEY `uc_user_email` (`email`) USING BTREE;

--
-- Indexes for table `zone`
--
ALTER TABLE `zone`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uc_zone_name` (`name`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `building`
--
ALTER TABLE `building`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT for table `material`
--
ALTER TABLE `material`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `post`
--
ALTER TABLE `post`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `zone`
--
ALTER TABLE `zone`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `booking`
--
ALTER TABLE `booking`
  ADD CONSTRAINT `fk_user_booking` FOREIGN KEY (`author`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `building`
--
ALTER TABLE `building`
  ADD CONSTRAINT `fk_zone_building` FOREIGN KEY (`zone`) REFERENCES `zone` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `material`
--
ALTER TABLE `material`
  ADD CONSTRAINT `fk_post_material` FOREIGN KEY (`post`) REFERENCES `post` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `fk_post_booking` FOREIGN KEY (`booking`) REFERENCES `booking` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
