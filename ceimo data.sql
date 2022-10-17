-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Lun 17 Octobre 2022 à 18:16
-- Version du serveur :  5.6.17
-- Version de PHP :  5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `ceimo`
--

-- --------------------------------------------------------

--
-- Structure de la table `avaliser`
--

CREATE TABLE IF NOT EXISTS `avaliser` (
  `id_emprunt` bigint(20) NOT NULL,
  `id_membre` bigint(20) NOT NULL,
  PRIMARY KEY (`id_emprunt`,`id_membre`),
  KEY `FK8oe3yaidecpsrh1lavvgqsf7m` (`id_membre`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE IF NOT EXISTS `compte` (
  `type` varchar(4) NOT NULL,
  `id_compte` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_creation` datetime DEFAULT NULL,
  `solde` double NOT NULL,
  `statut_compte` int(11) DEFAULT NULL,
  `montant_collation` double DEFAULT NULL,
  `taux_interet` double DEFAULT NULL,
  `solde_max` double DEFAULT NULL,
  `desription` varchar(255) DEFAULT NULL,
  `montant_min` double DEFAULT NULL,
  `membre_id_membre` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_compte`),
  KEY `FKluwo6rhkeaqyvt47m25vg8iw2` (`membre_id_membre`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `constat`
--

CREATE TABLE IF NOT EXISTS `constat` (
  `id_constat` bigint(20) NOT NULL AUTO_INCREMENT,
  `paye` bit(1) DEFAULT NULL,
  `id_membre` bigint(20) NOT NULL,
  `id_sanction` bigint(20) NOT NULL,
  `id_seance` bigint(20) NOT NULL,
  PRIMARY KEY (`id_constat`),
  KEY `FKn6479t3ikowg708l97yg6lamv` (`id_membre`),
  KEY `FKo39cs5611yarfpf6vakw0jrd4` (`id_sanction`),
  KEY `FK86r3n68yd7lw3cdcgokpvpqdg` (`id_seance`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `contribution`
--

CREATE TABLE IF NOT EXISTS `contribution` (
  `idcontribution` bigint(20) NOT NULL AUTO_INCREMENT,
  `montant_contribution` float NOT NULL,
  `id_membre` bigint(20) NOT NULL,
  `id_seance` bigint(20) NOT NULL,
  `id_tontine` bigint(20) NOT NULL,
  `id_contribution` bigint(20) NOT NULL,
  PRIMARY KEY (`idcontribution`),
  KEY `FKk3n4qqj4p6facqlk7l1pc8hse` (`id_membre`),
  KEY `FKek58dvbcvjhx5kewonmb7n8i` (`id_seance`),
  KEY `FKmmr1255rlj01jo47km1yc2u2d` (`id_tontine`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `demission`
--

CREATE TABLE IF NOT EXISTS `demission` (
  `id_demission` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_demission` date NOT NULL,
  `lettre_demission` varchar(50) NOT NULL,
  `id_demissionnaire` bigint(20) NOT NULL,
  PRIMARY KEY (`id_demission`),
  KEY `FKoxc571ihdg4djt1w84gpwmq1v` (`id_demissionnaire`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `elire`
--

CREATE TABLE IF NOT EXISTS `elire` (
  `id_elire` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_adhesion` date DEFAULT NULL,
  `montant_inscription` int(11) DEFAULT NULL,
  `id_exercice` bigint(20) NOT NULL,
  `id_membre` bigint(20) NOT NULL,
  `id_responsabilite` bigint(20) NOT NULL,
  PRIMARY KEY (`id_elire`),
  KEY `FKdqhn0qea5n8qcxdr7p7tcif44` (`id_exercice`),
  KEY `FK5hen5wds6ysk60ij624d2d0uc` (`id_membre`),
  KEY `FKcc4xiy14356bitctss7lxeqqy` (`id_responsabilite`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=22 ;

--
-- Contenu de la table `elire`
--

INSERT INTO `elire` (`id_elire`, `date_adhesion`, `montant_inscription`, `id_exercice`, `id_membre`, `id_responsabilite`) VALUES
(12, '2022-09-03', 0, 1, 1, 1),
(13, '2022-09-03', 0, 1, 2, 3),
(21, '2022-09-03', 500, 1, 3, 11),
(20, '2022-09-03', 500, 1, 2, 11),
(19, '2022-09-03', 500, 1, 1, 11);

-- --------------------------------------------------------

--
-- Structure de la table `emprunt`
--

CREATE TABLE IF NOT EXISTS `emprunt` (
  `id_emprunt` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_emprunt` date NOT NULL,
  `date_limite` date NOT NULL,
  `etat` bit(1) NOT NULL,
  `montant` float NOT NULL,
  `reste_a_payer` double NOT NULL,
  `taux_interet` double NOT NULL,
  `id_membre` bigint(20) NOT NULL,
  PRIMARY KEY (`id_emprunt`),
  KEY `FKhkbedlt2yc1r99hekmpgkjxsf` (`id_membre`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `exercice`
--

CREATE TABLE IF NOT EXISTS `exercice` (
  `id_exercice` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_debut_exercice` date NOT NULL,
  `date_fin_exercice` date NOT NULL,
  PRIMARY KEY (`id_exercice`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contenu de la table `exercice`
--

INSERT INTO `exercice` (`id_exercice`, `date_debut_exercice`, `date_fin_exercice`) VALUES
(1, '2022-09-03', '2023-09-02');

-- --------------------------------------------------------

--
-- Structure de la table `inscrire`
--

CREATE TABLE IF NOT EXISTS `inscrire` (
  `idinscription` bigint(20) NOT NULL AUTO_INCREMENT,
  `mois_gain` varchar(10) DEFAULT NULL,
  `id_membre` bigint(20) DEFAULT NULL,
  `id_tontine` bigint(20) DEFAULT NULL,
  `id_inscription` bigint(20) NOT NULL,
  PRIMARY KEY (`idinscription`),
  KEY `FK9ipvop9w8bfdkrb0ye3d8yx6d` (`id_membre`),
  KEY `FK5ul5q7gvflikt1u7fk7x2yutt` (`id_tontine`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `membre`
--

CREATE TABLE IF NOT EXISTS `membre` (
  `id_membre` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_first_inscription` date NOT NULL,
  `date_naissance` date NOT NULL,
  `demissionnaire` bit(1) NOT NULL,
  `login` varchar(255) DEFAULT NULL,
  `nom_membre` varchar(50) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `photo` varchar(100) DEFAULT NULL,
  `prenom_membre` varchar(50) DEFAULT NULL,
  `sexe_membre` char(1) NOT NULL,
  `statutgeographique` varchar(50) NOT NULL,
  PRIMARY KEY (`id_membre`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=8 ;

--
-- Contenu de la table `membre`
--

INSERT INTO `membre` (`id_membre`, `date_first_inscription`, `date_naissance`, `demissionnaire`, `login`, `nom_membre`, `password`, `photo`, `prenom_membre`, `sexe_membre`, `statutgeographique`) VALUES
(1, '2022-04-14', '2020-04-01', b'0', 'pik7', 'NGOM NGUE', 'michele', NULL, 'SERGE ALBERT', 'M', 'RESIDENT'),
(2, '2021-07-05', '2019-09-09', b'0', 'soniz', 'LONANG', 'michele', NULL, 'MICHELE SONIA', 'F', 'DIASPORA'),
(3, '2021-08-01', '2019-06-03', b'0', 'stady', 'DASSIE', 'pik7meuf', NULL, 'YVAN', 'M', 'RESIDENT'),
(4, '2022-09-03', '2020-08-09', b'0', 'peleton', 'EWANE', 'peleton', NULL, 'ERIC FRED', 'M', 'DIASPORA');

-- --------------------------------------------------------

--
-- Structure de la table `operation`
--

CREATE TABLE IF NOT EXISTS `operation` (
  `id_operation` varchar(255) NOT NULL,
  `date_operation` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `montant` double NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `compte_id_compte` bigint(20) DEFAULT NULL,
  `seance_id_seance` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_operation`),
  KEY `FKj7j5qsili7l8s0kxaogueykit` (`compte_id_compte`),
  KEY `FK1j540wn88kx6bfj91psv2g5o8` (`seance_id_seance`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `presents`
--

CREATE TABLE IF NOT EXISTS `presents` (
  `id_seance` bigint(20) NOT NULL,
  `id_membre` bigint(20) NOT NULL,
  PRIMARY KEY (`id_seance`,`id_membre`),
  KEY `FK8fjo8t9q6s2fl1q5cpx5h4481` (`id_membre`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `responsabilite`
--

CREATE TABLE IF NOT EXISTS `responsabilite` (
  `id_responsabilite` bigint(20) NOT NULL AUTO_INCREMENT,
  `duree_mandat` int(11) DEFAULT NULL,
  `intitule_responsabilite` varchar(255) NOT NULL,
  PRIMARY KEY (`id_responsabilite`),
  UNIQUE KEY `UK_ffspd8lbksf0rgr9fdjw5hkvd` (`intitule_responsabilite`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Contenu de la table `responsabilite`
--

INSERT INTO `responsabilite` (`id_responsabilite`, `duree_mandat`, `intitule_responsabilite`) VALUES
(1, 2, 'PRESIDENT'),
(2, 2, 'CENSEUR'),
(3, 2, 'SECRETAIRE'),
(4, 2, 'VICE_PRESIDENT'),
(5, 2, 'SECRETAIRE_ADJOINT'),
(6, 2, 'COMMISSAIRE_AU_COMPTE_1'),
(7, 2, 'COMMISSAIRE_AU_COMPTE_2'),
(10, 2, 'TRESORIER'),
(11, 2, 'MEMBRE');

-- --------------------------------------------------------

--
-- Structure de la table `sanction`
--

CREATE TABLE IF NOT EXISTS `sanction` (
  `id_sanction` bigint(20) NOT NULL AUTO_INCREMENT,
  `amende` int(11) DEFAULT NULL,
  `libelle_sanction` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id_sanction`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `seance`
--

CREATE TABLE IF NOT EXISTS `seance` (
  `id_seance` bigint(20) NOT NULL AUTO_INCREMENT,
  `compte_rendu` varchar(2000) DEFAULT NULL,
  `date_seance` date NOT NULL,
  `ordre_du_jour` varchar(255) NOT NULL,
  `type_seance` varchar(50) DEFAULT NULL,
  `id_exercice` bigint(20) NOT NULL,
  `id_president` bigint(20) NOT NULL,
  PRIMARY KEY (`id_seance`),
  KEY `FK3f9nobg5h5k2d4n6xko37gw08` (`id_exercice`),
  KEY `FKajkc0rk0821ube4bx234g3p4g` (`id_president`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `tontine`
--

CREATE TABLE IF NOT EXISTS `tontine` (
  `id_tontine` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL,
  `etat` bit(1) NOT NULL,
  `libelle_tontine` varchar(255) NOT NULL,
  PRIMARY KEY (`id_tontine`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
