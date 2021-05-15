-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 09, 2021 at 07:26 PM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `inputdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `evidencija`
--

CREATE TABLE `evidencija` (
  `id_evidencije` int(11) DEFAULT NULL,
  `sati` decimal(4,2) DEFAULT NULL,
  `opis_rada` text,
  `uposlenik_id` int(11) NOT NULL,
  `raspored_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `evidencija`
--

INSERT INTO `evidencija` (`id_evidencije`, `sati`, `opis_rada`, `uposlenik_id`, `raspored_id`) VALUES
(1, '2.50', 'Svasta radio', 1, 1),
(1, '2.50', 'Svasta radio', 1, 2),
(1, '1.00', 'sadsada', 4, 1),
(1, '2.50', 'Isto su radili', 4, 2);

-- --------------------------------------------------------

--
-- Table structure for table `raspored`
--

CREATE TABLE `raspored` (
  `raspored_id` int(11) NOT NULL,
  `datum` date DEFAULT NULL,
  `pocetak_rada` decimal(4,2) DEFAULT NULL,
  `kraj_rada` decimal(4,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `raspored`
--

INSERT INTO `raspored` (`raspored_id`, `datum`, `pocetak_rada`, `kraj_rada`) VALUES
(1, '2021-01-01', '16.30', '18.15'),
(2, '2021-03-21', '16.30', '18.15'),
(3, '2021-03-18', '17.00', '19.00');

-- --------------------------------------------------------

--
-- Table structure for table `tim`
--

CREATE TABLE `tim` (
  `tim_id` int(11) NOT NULL,
  `naziv` varchar(20) DEFAULT NULL,
  `tim_opis` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tim`
--

INSERT INTO `tim` (`tim_id`, `naziv`, `tim_opis`) VALUES
(1, 'Tim 1', 'Radi svasta nesto'),
(2, 'Tim 2', 'Pravi stranicu');

-- --------------------------------------------------------

--
-- Table structure for table `uposlenik`
--

CREATE TABLE `uposlenik` (
  `uposlenik_id` int(11) NOT NULL,
  `ime` varchar(20) NOT NULL,
  `prezime` varchar(30) NOT NULL,
  `spol` int(11) NOT NULL,
  `datum_rodjenja` date NOT NULL,
  `datum_zaposlenja` date NOT NULL,
  `grad` varchar(30) NOT NULL,
  `broj_telefona` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  `lozinka` varchar(20) NOT NULL,
  `tim_id` int(11) NOT NULL,
  `admin` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `uposlenik`
--

INSERT INTO `uposlenik` (`uposlenik_id`, `ime`, `prezime`, `spol`, `datum_rodjenja`, `datum_zaposlenja`, `grad`, `broj_telefona`, `email`, `lozinka`, `tim_id`, `admin`) VALUES
(1, 'Ali', 'Adilovic', 1, '0000-00-00', '0000-00-00', 'Zenica', '0603399764', 'koje@size.ba', 'Sifra123', 2, 1),
(4, 'Ivan', 'Ivanovic', 1, '0000-00-00', '0000-00-00', 'Zenica', '0603399764', 'moj@size.ba', 'Sifra123', 2, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `evidencija`
--
ALTER TABLE `evidencija`
  ADD PRIMARY KEY (`uposlenik_id`,`raspored_id`),
  ADD KEY `FK_evidencija_raspored` (`raspored_id`);

--
-- Indexes for table `raspored`
--
ALTER TABLE `raspored`
  ADD PRIMARY KEY (`raspored_id`);

--
-- Indexes for table `tim`
--
ALTER TABLE `tim`
  ADD PRIMARY KEY (`tim_id`);

--
-- Indexes for table `uposlenik`
--
ALTER TABLE `uposlenik`
  ADD PRIMARY KEY (`uposlenik_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `raspored`
--
ALTER TABLE `raspored`
  MODIFY `raspored_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tim`
--
ALTER TABLE `tim`
  MODIFY `tim_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `uposlenik`
--
ALTER TABLE `uposlenik`
  MODIFY `uposlenik_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `evidencija`
--
ALTER TABLE `evidencija`
  ADD CONSTRAINT `FK_evidencija_raspored` FOREIGN KEY (`raspored_id`) REFERENCES `raspored` (`raspored_id`),
  ADD CONSTRAINT `FK_evidencija_uposlenik` FOREIGN KEY (`uposlenik_id`) REFERENCES `uposlenik` (`uposlenik_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
