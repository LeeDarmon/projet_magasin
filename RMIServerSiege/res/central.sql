-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : lun. 16 mai 2022 à 16:52
-- Version du serveur : 5.7.36
-- Version de PHP : 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `central`
--

-- --------------------------------------------------------

--
-- Structure de la table `article`
--

DROP TABLE IF EXISTS `article`;
CREATE TABLE IF NOT EXISTS `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reference` varchar(50) NOT NULL,
  `famille` varchar(50) NOT NULL,
  `prix_unitaire` float NOT NULL,
  `nb_exemplaire` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `article`
--

INSERT INTO `article` (`id`, `reference`, `famille`, `prix_unitaire`, `nb_exemplaire`) VALUES
(1, 'Ballon', 'Loisir', 24, 10),
(2, 'Spaghetti ', 'Alimentaire', 8, 50),
(3, 'Filet de poulet', 'Alimentaire', 40, 10),
(4, 'Crème fraiche 12%', 'Alimentaire', 8, 30),
(5, 'Terreau', 'Jardin', 80, 5),
(6, 'Console Nintendo Switch', 'Loisir', 2392, 2),
(7, 'Voiture Hotwheels', 'Loisir', 232, 0);

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_emission` date NOT NULL,
  `methode_paiement` varchar(50) NOT NULL,
  `numticket` varchar(255) NOT NULL,
  `prix_total` float NOT NULL,
  `ticket_de_caisse` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `numticket` (`numticket`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`id`, `date_emission`, `methode_paiement`, `numticket`, `prix_total`, `ticket_de_caisse`) VALUES
(13, '2022-05-05', 'CB', 'pCZ2HogOzR', 3, '{\"listArticle\":[{\"id\":1,\"nb_exemplaire\":3,\"reference\":\"Ballon\",\"famille\":\"Loisir\",\"prix_unitaire\":3}],\"date_emission\":\"May 5, 2022, 9:57:08 AM\",\"prixtotal\":3,\"methode_paiement\":\"CB\",\"numticket\":\"pCZ2HogOzR\"}'),
(12, '2022-05-16', 'CB', 'Sr1Du6eogW', 6, '{\"listArticle\":[{\"id\":1,\"nb_exemplaire\":6,\"reference\":\"Ballon\",\"famille\":\"Loisir\",\"prix_unitaire\":6}],\"date_emission\":\"May 16, 2022, 2:50:49 PM\",\"prixtotal\":6,\"methode_paiement\":\"CB\",\"numticket\":\"Sr1Du6eogW\"}');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
