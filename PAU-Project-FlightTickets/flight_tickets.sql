-- phpMyAdmin SQL Dump
-- version 4.8.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 20, 2018 at 10:51 AM
-- Server version: 10.1.33-MariaDB
-- PHP Version: 7.2.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `flight_tickets`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `username` text COLLATE utf8_turkish_ci NOT NULL,
  `password` text COLLATE utf8_turkish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Dumping data for table `admins`
--

INSERT INTO `admins` (`username`, `password`) VALUES
('admin', 'admin');

-- --------------------------------------------------------

--
-- Table structure for table `customers`
--

CREATE TABLE `customers` (
  `id` int(11) NOT NULL,
  `name` text COLLATE utf8_turkish_ci NOT NULL,
  `surname` text COLLATE utf8_turkish_ci NOT NULL,
  `email` text COLLATE utf8_turkish_ci NOT NULL,
  `password` text COLLATE utf8_turkish_ci NOT NULL,
  `birthdate` date NOT NULL,
  `address` text COLLATE utf8_turkish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Dumping data for table `customers`
--

INSERT INTO `customers` (`id`, `name`, `surname`, `email`, `password`, `birthdate`, `address`) VALUES
(1, 'admin', 'admin', 'admin', 'admin', '1997-01-01', 'admin'),
(2, 'Bora', 'Tanrikulu', 'boratanrikulu@gmail.com', 'asd', '1997-04-11', 'Izmir'),
(3, 'Asya', 'Turk', 'asyaturk@gmail.com', 'asd', '1998-05-23', 'Izmir'),
(4, 'Lagertha', 'Ragnar', 'nord@gmail.com', 'asd', '1100-01-01', 'Norway'),
(5, 'Ahmet', 'Mehmet', 'ahmetmehmet@gmail.com', 'asd', '1999-01-01', 'Turkey'),
(6, 'Ayşe', 'Fatma', 'aysefatma@gmail.com', 'asd', '1999-01-01', 'Turkey'),
(7, 'Bahar', 'Kartal', 'bahar@gmail.com', 'asd', '1997-01-01', 'Istanbul'),
(8, 'Kuzey', 'Batı', 'kuzeybati@gmail.com', 'asd', '1990-01-01', 'Kuzey Batı'),
(9, 'Einar', 'Selvik', 'einarselvik@gmail.com', 'asd', '1979-01-01', 'Bergen, Norway'),
(10, 'Mehmet', 'Yücel', 'mehmetyucel@gmail.com', 'asd', '1995-01-01', 'Turkey'),
(11, 'Osman', 'Dede', 'osmandede@gmail.com', 'asd', '1889-05-23', 'Turkey'),
(12, 'Yılmaz', 'Aslan', 'yilmazaslan@gmail.com', 'asd', '1885-05-05', 'Izmir, Turkey'),
(13, 'Varol', 'Kaya', 'varolkaya@gmail.com', 'asd', '1975-04-23', 'Ankara, Turkey'),
(14, 'Ali', 'Veli', 'aliveli@gmail.com', 'asd', '1960-01-01', 'Turkey'),
(15, 'Goerge', 'Orwell', 'orwell@gmail.com', 'asd', '1950-06-21', 'Oxfordshire, England'),
(16, 'Bilge', 'Çetin', 'bilgecetin@gmail.com', 'asd', '1988-07-29', 'Turkey'),
(17, 'Onur', 'Cengiz', 'onurcengiz@gmail.com', 'asd', '1997-06-06', 'Izmir, Turkey'),
(18, 'Mustafa', 'Tüfekçi', 'tufekci@gmail.com', 'asd', '1977-06-21', 'Turkey');

-- --------------------------------------------------------

--
-- Table structure for table `flights`
--

CREATE TABLE `flights` (
  `id` int(11) NOT NULL,
  `fromWhere` text COLLATE utf8_turkish_ci NOT NULL,
  `destination` text COLLATE utf8_turkish_ci NOT NULL,
  `date` date NOT NULL,
  `time` time NOT NULL,
  `seatCapacity` int(11) NOT NULL DEFAULT '159',
  `firstClassPrice` int(11) NOT NULL DEFAULT '300',
  `premiumClassPrice` int(11) NOT NULL DEFAULT '200',
  `mainCabinPrice` int(11) NOT NULL DEFAULT '100'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Dumping data for table `flights`
--

INSERT INTO `flights` (`id`, `fromWhere`, `destination`, `date`, `time`, `seatCapacity`, `firstClassPrice`, `premiumClassPrice`, `mainCabinPrice`) VALUES
(1, 'Izmir - ADB', 'Istanbul - SAW', '2018-05-23', '18:00:00', 159, 300, 200, 100),
(2, 'Izmir - ADB', 'Antalya - AYT', '2018-05-23', '21:00:00', 159, 300, 200, 100),
(3, 'Izmir - ADB', 'Ankara - ESB', '2018-05-23', '15:00:00', 159, 300, 200, 100),
(4, 'Istanbul - SAW', 'Izmir - ADB', '2018-05-23', '10:00:00', 159, 300, 200, 100),
(5, 'Istanbul - IST', 'Antalya - AYT', '2018-05-23', '13:00:00', 159, 300, 200, 100),
(6, 'Istanbul - SAW', 'Ankara - ESB', '2018-05-23', '16:00:00', 159, 300, 200, 100),
(7, 'Antalya - AYT', 'Izmir - ADB', '2018-05-23', '12:00:00', 159, 300, 200, 100),
(8, 'Antalya - AYT', 'Istanbul - IST', '2018-05-23', '18:00:00', 159, 300, 200, 100),
(9, 'Ankara - ESB', 'Izmir - ADB', '2018-05-23', '08:00:00', 159, 300, 200, 100),
(10, 'Ankara - ESB', 'Istanbul - SAW', '2018-05-23', '11:00:00', 159, 300, 200, 100),
(11, 'Ankara - ESB', 'Antalya - AYT', '2018-05-23', '14:00:00', 159, 300, 200, 100);

-- --------------------------------------------------------

--
-- Table structure for table `sold_tickets`
--

CREATE TABLE `sold_tickets` (
  `planeId` int(11) NOT NULL,
  `customerId` int(11) NOT NULL,
  `seatNumber` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_turkish_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`username`(100));

--
-- Indexes for table `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`(200));

--
-- Indexes for table `flights`
--
ALTER TABLE `flights`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `sold_tickets`
--
ALTER TABLE `sold_tickets`
  ADD UNIQUE KEY `planeId` (`planeId`,`seatNumber`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customers`
--
ALTER TABLE `customers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT for table `flights`
--
ALTER TABLE `flights`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
