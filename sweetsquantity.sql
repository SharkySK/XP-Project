-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 26, 2017 at 11:04 AM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
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
-- Table structure for table `sweetsquantity`
--

CREATE TABLE `sweetsquantity` (
  `bookingid` int(11) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `SweetsID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `sweetsquantity`
--
ALTER TABLE `sweetsquantity`
  ADD KEY `booking_sweetquan` (`bookingid`),
  ADD KEY `candysoda_sweetquan` (`SweetsID`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `sweetsquantity`
--
ALTER TABLE `sweetsquantity`
  ADD CONSTRAINT `booking_sweetquan` FOREIGN KEY (`bookingid`) REFERENCES `booking` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `candysoda_sweetquan` FOREIGN KEY (`SweetsID`) REFERENCES `candysodas` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
