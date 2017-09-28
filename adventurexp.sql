-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 28, 2017 at 08:58 AM
-- Server version: 10.1.26-MariaDB
-- PHP Version: 7.1.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `adventurexp`
--

-- --------------------------------------------------------

--
-- Table structure for table `activity`
--

CREATE TABLE `activity` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `price` int(11) NOT NULL,
  `age` int(11) NOT NULL,
  `height` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `activity`
--

INSERT INTO `activity` (`id`, `name`, `price`, `age`, `height`) VALUES
(1, 'activity2', 2, 20, 200),
(2, 'activity1', 90, 90, 9.9),
(3, 'lol', 69, 69, 69.9),
(4, 'activityjakub', 69, 69, 69.69);

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `id` int(11) NOT NULL,
  `date` date NOT NULL,
  `starttime` time NOT NULL,
  `endtime` time NOT NULL,
  `name` varchar(30) NOT NULL,
  `email` varchar(40) NOT NULL,
  `phonenr` varchar(20) NOT NULL,
  `participants` int(11) NOT NULL,
  `activity` int(11) NOT NULL,
  `instructorId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`id`, `date`, `starttime`, `endtime`, `name`, `email`, `phonenr`, `participants`, `activity`, `instructorId`) VALUES
(2, '2017-09-12', '07:00:36', '08:00:36', 'tyr', 'trt', 'tyr', 65, 1, 7),
(3, '2017-09-12', '07:00:41', '08:00:41', 'tyr', 'trt', 'tyr', 65, 2, 8),
(4, '2017-09-12', '07:00:43', '08:00:43', 'tyr', 'trt', 'tyr', 65, 3, 8),
(5, '2017-09-12', '07:00:46', '08:00:46', 'tyr', 'trt', 'tyr', 65, 4, 7),
(6, '2017-09-12', '07:08:15', '08:08:15', 'yutu80', 'trt', 'tyr', 65, 1, 8),
(7, '2017-09-12', '07:03:49', '08:03:49', 'yutuytuytuyt', 'trt', 'tyr', 65, 1, 8);

-- --------------------------------------------------------

--
-- Table structure for table `candysodas`
--

CREATE TABLE `candysodas` (
  `ID` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `candysodas`
--

INSERT INTO `candysodas` (`ID`, `Name`, `Price`) VALUES
(1, 'Popsi', 15),
(2, 'Snockers', 10),
(3, 'T-shirt - Small', 50),
(4, 'T-shirt - Medium', 50),
(5, 'T-shirt - Large', 50);

-- --------------------------------------------------------

--
-- Table structure for table `instructor`
--

CREATE TABLE `instructor` (
  `id` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `workdays` tinyint(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `instructor`
--

INSERT INTO `instructor` (`id`, `name`, `workdays`) VALUES
(7, 'fucktard', 0),
(8, 'fuuuuuuu', 0);

-- --------------------------------------------------------

--
-- Table structure for table `sweetsquantity`
--

CREATE TABLE `sweetsquantity` (
  `bookingid` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `sweetsid` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sweetsquantity`
--

INSERT INTO `sweetsquantity` (`bookingid`, `quantity`, `sweetsid`) VALUES
(2, 2, 4),
(2, 2, 2),
(2, 1, 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `activity`
--
ALTER TABLE `activity`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`id`),
  ADD KEY `activity` (`activity`),
  ADD KEY `instructorId` (`instructorId`);

--
-- Indexes for table `candysodas`
--
ALTER TABLE `candysodas`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `instructor`
--
ALTER TABLE `instructor`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sweetsquantity`
--
ALTER TABLE `sweetsquantity`
  ADD KEY `booking_sweetquan` (`bookingid`),
  ADD KEY `candysoda_sweetquan` (`sweetsid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `activity`
--
ALTER TABLE `activity`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `candysodas`
--
ALTER TABLE `candysodas`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `instructor`
--
ALTER TABLE `instructor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `booking`
--
ALTER TABLE `booking`
  ADD CONSTRAINT `booking_ibfk_1` FOREIGN KEY (`activity`) REFERENCES `activity` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `booking_ibfk_2` FOREIGN KEY (`instructorId`) REFERENCES `instructor` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
