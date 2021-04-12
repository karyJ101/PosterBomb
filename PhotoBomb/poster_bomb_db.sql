-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 10, 2021 at 10:29 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 7.3.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `poster_bomb_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `comment_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `comment` varchar(120) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `comments`
--

INSERT INTO `comments` (`comment_id`, `user_id`, `post_id`, `comment`) VALUES
(1, 15, 9, 'this is the first comment\r\n'),
(2, 24, 9, 'This is another comment'),
(3, 24, 10, 'this is a comment test'),
(4, 25, 11, 'this is the first comment\r\n'),
(5, 25, 10, 'this is another comment'),
(6, 15, 11, 'time to make another comment'),
(7, 15, 4, 'these are comments\r\n'),
(8, 24, 12, 'this is the first comment'),
(9, 15, 10, 'this is a comment'),
(10, 24, 9, 'commenting again'),
(11, 15, 10, 'lets test some more comments\r\n'),
(12, 15, 13, 'commenting on this post'),
(13, 24, 4, 'this is another comment'),
(16, 15, 14, 'this is a comment'),
(17, 24, 15, 'im making a comment'),
(18, 15, 17, 'this is a comment'),
(19, 15, 11, 'more comments\r\n'),
(23, 15, 18, 'thanks fr commenting');

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

CREATE TABLE `post` (
  `post_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `post` varchar(120) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`post_id`, `user_id`, `post`) VALUES
(3, 15, 'this is a post'),
(4, 15, 'this is another post'),
(5, 15, 'Yest another post'),
(7, 15, 'another post to test the application'),
(8, 15, 'this is alot of posts'),
(9, 15, 'these are some crazy posts'),
(10, 24, 'THis is this account\'s first post'),
(11, 24, 'testing some more posts'),
(12, 15, 'this some more posts'),
(13, 24, 'some more post tests'),
(14, 15, 'thisis another post'),
(15, 15, 'this is some more posts'),
(17, 24, 'this is a post'),
(18, 15, 'helloooo');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `username` varchar(55) NOT NULL,
  `password` longtext NOT NULL,
  `account_type` varchar(25) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username`, `password`, `account_type`) VALUES
(15, 'testuser', '0ed542b815a0d4b0c457ad9def1a21d2', NULL),
(16, 'testuser2', '5f4dcc3b5aa765d61d8327deb882cf99', NULL),
(24, 'anotheruser', '5f4dcc3b5aa765d61d8327deb882cf99', NULL),
(25, 'testuser4', '5f4dcc3b5aa765d61d8327deb882cf99', NULL),
(26, 'anothertester', '5f4dcc3b5aa765d61d8327deb882cf99', NULL),
(28, 'adminuser', '5f4dcc3b5aa765d61d8327deb882cf99', 'admin'),
(38, 'kary.johnson@hotmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user_profile`
--

CREATE TABLE `user_profile` (
  `profile_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `firstname` varchar(55) DEFAULT NULL,
  `lastname` varchar(55) DEFAULT NULL,
  `email` varchar(55) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_profile`
--

INSERT INTO `user_profile` (`profile_id`, `user_id`, `firstname`, `lastname`, `email`) VALUES
(2, 15, 'Testing', 'Guuy', 'test@gmail.com'),
(3, 16, 'Jeff', 'Jeff', 'test2@gmail.com'),
(5, 24, 'Another', 'Tester', 'test3@gmail.com'),
(6, 25, 'Test', 'Guy', 'testing54@gmail.com'),
(7, 26, 'Another', 'Tester', 'tester5@gmail.com'),
(9, 28, 'admin', 'administrator', 'admin@gmail.com'),
(19, 38, 'Kary', 'Johnson', 'kary.johnson@hotmail.com');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`comment_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `post_id` (`post_id`);

--
-- Indexes for table `post`
--
ALTER TABLE `post`
  ADD PRIMARY KEY (`post_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- Indexes for table `user_profile`
--
ALTER TABLE `user_profile`
  ADD PRIMARY KEY (`profile_id`),
  ADD KEY `user_id` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `comment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- AUTO_INCREMENT for table `post`
--
ALTER TABLE `post`
  MODIFY `post_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `user_profile`
--
ALTER TABLE `user_profile`
  MODIFY `profile_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `comment_post_id` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `comment_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `post`
--
ALTER TABLE `post`
  ADD CONSTRAINT `post_user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `user_profile`
--
ALTER TABLE `user_profile`
  ADD CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
