# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 10.1.0.45 (MySQL 5.5.60-MariaDB)
# Database: asif
# Generation Time: 2019-06-23 12:07:57 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table lease
# ------------------------------------------------------------

DROP TABLE IF EXISTS `lease`;

CREATE TABLE `lease` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `begin_date` varchar(255) DEFAULT NULL,
  `billing_type` varchar(30) DEFAULT NULL,
  `code_type` varchar(30) DEFAULT NULL,
  `contract_type` varchar(30) DEFAULT NULL,
  `expiration_date` varchar(255) DEFAULT NULL,
  `lease_code` varchar(255) DEFAULT NULL,
  `lease_group1` varchar(30) DEFAULT NULL,
  `lease_name` varchar(255) DEFAULT NULL,
  `lease_status` varchar(30) DEFAULT NULL,
  `lease_type` varchar(30) DEFAULT NULL,
  `property_code` varchar(30) DEFAULT NULL,
  `property_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pglpwkm8pdknhhcabg0pqqau0` (`lease_code`)
) ENGINE=MyISAM AUTO_INCREMENT=39 DEFAULT CHARSET=latin1;

LOCK TABLES `lease` WRITE;
/*!40000 ALTER TABLE `lease` DISABLE KEYS */;

INSERT INTO `lease` (`id`, `begin_date`, `billing_type`, `code_type`, `contract_type`, `expiration_date`, `lease_code`, `lease_group1`, `lease_name`, `lease_status`, `lease_type`, `property_code`, `property_name`)
VALUES
	(23,'01/01/2016','Expense','DEFAULT','Real Estate Contract','12/31/2021','ERLeaseEx','1','ERLeaseEx','Active','Base Year','EditRevisionProperty01','EditRevisionProperty01'),
	(24,'01/01/2016','Income','DEFAULT','Real Estate Contract','12/31/2021','ERLease1','01','ERLease1','Active','Base Year','EditRevisionProperty01','EditRevisionProperty01'),
	(25,'01/01/2016','Income','DEFAULT','Real Estate Contract','12/31/2021','ERLeaseInc','1','ERLeaseInc','Active','Base Year','EditRevisionProperty01','EditRevisionProperty01'),
	(26,'01/01/2016','Income','DEFAULT','Real Estate Contract','12/31/2021','ERLease2','01','ERLease2','Active','Base Year','EditRevisionProperty01','EditRevisionProperty01'),
	(27,'01/01/2016','Income','DEFAULT','Real Estate Contract','12/31/2021','LIn6628070','01','LIn6628070','Active','Base Year','EditRevisionProperty01','EditRevisionProperty01'),
	(28,'01/01/2016','Income','DEFAULT','Real Estate Contract','12/31/2021','LIn6628072','01','LIn6628072','Active','Base Year','EditRevisionProperty01','EditRevisionProperty01'),
	(29,'01/01/2016','Income','DEFAULT','Real Estate Contract','12/31/2021','LIn6628073','01','LIn6628073','Active','Base Year','EditRevisionProperty01','EditRevisionProperty01'),
	(30,'01/01/2016','Income','DEFAULT','Real Estate Contract','12/31/2021','LIn6628074','01','LIn6628074','Active','Base Year','EditRevisionProperty01','EditRevisionProperty01'),
	(31,'01/01/2016','Income','DEFAULT','Real Estate Contract','12/31/2021','LIn6628075','01','LIn6628075','Active','Base Year','EditRevisionProperty01','EditRevisionProperty01'),
	(32,'01/01/2016','Income','DEFAULT','Real Estate Contract','12/31/2021','LIn6628076','01','LIn6628076','Active','Base Year','EditRevisionProperty01','EditRevisionProperty01'),
	(33,'01/01/2016','Income','DEFAULT','Real Estate Contract','12/31/2021','LIn6628077','01','LIn6628077','Active','Base Year','EditRevisionProperty01','EditRevisionProperty01'),
	(34,'01/01/2016','Income','DEFAULT','Real Estate Contract','12/31/2021','LIn6628078','01','LIn6628078','Active','Base Year','EditRevisionProperty01','EditRevisionProperty01'),
	(35,'01/01/2016','Income','DEFAULT','Real Estate Contract','12/31/2021','LIn6628079','01','LIn6628079','Active','Base Year','EditRevisionProperty01','EditRevisionProperty01'),
	(36,'01/01/2016','Income','DEFAULT','Real Estate Contract','12/31/2021','LIn6628080','01','LIn6628080','Active','Base Year','EditRevisionProperty01','EditRevisionProperty01'),
	(37,'01/01/2016','Income','DEFAULT','Real Estate Contract','12/31/2021','LIn6628081','01','LIn6628081','Active','Base Year','EditRevisionProperty01','EditRevisionProperty01'),
	(38,'01/01/2016','Income','DEFAULT','Real Estate Contract','12/31/2021','LIn6628083','01','LIn6628083','Active','Base Year','EditRevisionProperty01','EditRevisionProperty01');

/*!40000 ALTER TABLE `lease` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table preq_execution_history
# ------------------------------------------------------------

DROP TABLE IF EXISTS `preq_execution_history`;

CREATE TABLE `preq_execution_history` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `client_id` varchar(10) DEFAULT NULL,
  `creation_time` datetime DEFAULT NULL,
  `environment` varchar(10) DEFAULT NULL,
  `is_passed` bit(1) NOT NULL,
  `preq_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKp000fgbih6db7gubyxicaewd3` (`preq_id`,`client_id`,`environment`)
) ENGINE=MyISAM AUTO_INCREMENT=68 DEFAULT CHARSET=latin1;

LOCK TABLES `preq_execution_history` WRITE;
/*!40000 ALTER TABLE `preq_execution_history` DISABLE KEYS */;

INSERT INTO `preq_execution_history` (`id`, `client_id`, `creation_time`, `environment`, `is_passed`, `preq_id`)
VALUES
	(23,'201480','2019-06-21 15:55:25','uat',b'1',74),
	(33,'201480','2019-06-21 16:40:52','uat',b'1',75),
	(34,'201480','2019-06-21 16:43:03','uat',b'1',78),
	(39,'201480','2019-06-21 18:04:22','uat',b'1',81),
	(27,'201480','2019-06-21 16:15:17','uat',b'1',76),
	(28,'201480','2019-06-21 16:17:29','uat',b'1',79),
	(29,'201480','2019-06-21 16:21:00','uat',b'1',82),
	(30,'201480','2019-06-21 16:25:17','uat',b'1',77),
	(31,'201480','2019-06-21 16:27:28','uat',b'1',80),
	(40,'201480','2019-06-21 18:09:47','uat',b'1',83),
	(41,'201480','2019-06-22 19:58:55','uat',b'1',84),
	(42,'201480','2019-06-22 20:02:26','uat',b'1',85),
	(43,'201480','2019-06-22 21:13:38','uat',b'1',86),
	(44,'201480','2019-06-22 21:15:47','uat',b'1',87),
	(45,'201480','2019-06-22 21:19:12','uat',b'1',88),
	(46,'201480','2019-06-23 11:55:33','uat',b'1',89),
	(47,'201480','2019-06-23 11:58:07','uat',b'1',95),
	(48,'201480','2019-06-23 12:06:51','uat',b'1',101),
	(49,'201480','2019-06-23 12:40:54','uat',b'1',90),
	(50,'201480','2019-06-23 12:43:06','uat',b'1',96),
	(51,'201480','2019-06-23 12:46:48','uat',b'1',102),
	(52,'201480','2019-06-23 12:53:13','uat',b'1',91),
	(53,'201480','2019-06-23 12:55:25','uat',b'1',97),
	(58,'201480','2019-06-23 15:29:10','uat',b'1',103),
	(59,'201480','2019-06-23 15:45:46','uat',b'1',92),
	(60,'201480','2019-06-23 15:47:54','uat',b'1',98),
	(61,'201480','2019-06-23 15:52:43','uat',b'1',104),
	(62,'201480','2019-06-23 16:04:36','uat',b'1',93),
	(63,'201480','2019-06-23 16:06:59','uat',b'1',99),
	(64,'201480','2019-06-23 16:10:27','uat',b'1',105),
	(65,'201480','2019-06-23 16:17:07','uat',b'1',94),
	(66,'201480','2019-06-23 16:19:15','uat',b'1',100),
	(67,'201480','2019-06-23 16:22:41','uat',b'1',106);

/*!40000 ALTER TABLE `preq_execution_history` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table prequisite_data
# ------------------------------------------------------------

DROP TABLE IF EXISTS `prequisite_data`;

CREATE TABLE `prequisite_data` (
  `preq_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_id` bigint(20) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`preq_id`),
  UNIQUE KEY `UK8436jd2o9se74cuxgino7h3qv` (`type`,`data_id`)
) ENGINE=MyISAM AUTO_INCREMENT=125 DEFAULT CHARSET=latin1;

LOCK TABLES `prequisite_data` WRITE;
/*!40000 ALTER TABLE `prequisite_data` DISABLE KEYS */;

INSERT INTO `prequisite_data` (`preq_id`, `data_id`, `type`)
VALUES
	(74,8,'property'),
	(75,23,'lease'),
	(76,24,'lease'),
	(77,25,'lease'),
	(78,23,'space'),
	(79,24,'space'),
	(80,25,'space'),
	(81,23,'rpr'),
	(82,24,'rpr'),
	(83,25,'rpr'),
	(84,26,'space'),
	(85,26,'rpr'),
	(86,26,'lease'),
	(87,27,'space'),
	(88,27,'rpr'),
	(89,27,'lease'),
	(90,28,'lease'),
	(91,29,'lease'),
	(92,30,'lease'),
	(93,31,'lease'),
	(94,32,'lease'),
	(95,28,'space'),
	(96,29,'space'),
	(97,30,'space'),
	(98,31,'space'),
	(99,32,'space'),
	(100,33,'space'),
	(101,28,'rpr'),
	(102,29,'rpr'),
	(103,30,'rpr'),
	(104,31,'rpr'),
	(105,32,'rpr'),
	(106,33,'rpr'),
	(107,33,'lease'),
	(108,34,'lease'),
	(109,35,'lease'),
	(110,36,'lease'),
	(111,37,'lease'),
	(112,38,'lease'),
	(113,34,'space'),
	(114,35,'space'),
	(115,36,'space'),
	(116,37,'space'),
	(117,38,'space'),
	(118,39,'space'),
	(119,34,'rpr'),
	(120,35,'rpr'),
	(121,36,'rpr'),
	(122,37,'rpr'),
	(123,38,'rpr'),
	(124,39,'rpr');

/*!40000 ALTER TABLE `prequisite_data` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table property
# ------------------------------------------------------------

DROP TABLE IF EXISTS `property`;

CREATE TABLE `property` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(30) DEFAULT NULL,
  `auto_manage` varchar(255) DEFAULT NULL,
  `chart_type` varchar(30) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  `code_type` varchar(30) DEFAULT NULL,
  `country` varchar(30) DEFAULT NULL,
  `currency` varchar(30) DEFAULT NULL,
  `postal` varchar(30) DEFAULT NULL,
  `property_code` varchar(255) DEFAULT NULL,
  `property_group1` varchar(30) DEFAULT NULL,
  `property_group2` varchar(30) DEFAULT NULL,
  `property_group3` varchar(30) DEFAULT NULL,
  `property_name` varchar(255) DEFAULT NULL,
  `sq_ft_rentable` varchar(30) DEFAULT NULL,
  `state` varchar(30) DEFAULT NULL,
  `status` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_e91yvjw8pwock8etqrptwwu6f` (`property_code`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

LOCK TABLES `property` WRITE;
/*!40000 ALTER TABLE `property` DISABLE KEYS */;

INSERT INTO `property` (`id`, `address`, `auto_manage`, `chart_type`, `city`, `code_type`, `country`, `currency`, `postal`, `property_code`, `property_group1`, `property_group2`, `property_group3`, `property_name`, `sq_ft_rentable`, `state`, `status`)
VALUES
	(8,'Add2','TRUE','QA Testing','test','DEFAULT','Togo','USD','12230','EditRevisionProperty01','Building List','Central','India','EditRevisionProperty01','100000000','Kara','Active');

/*!40000 ALTER TABLE `property` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table rpr
# ------------------------------------------------------------

DROP TABLE IF EXISTS `rpr`;

CREATE TABLE `rpr` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `amount` varchar(30) DEFAULT NULL,
  `charge_name` varchar(30) DEFAULT NULL,
  `charge_type` varchar(40) DEFAULT NULL,
  `effective_date` varchar(30) DEFAULT NULL,
  `end_date` varchar(30) DEFAULT NULL,
  `escalation_type` varchar(30) DEFAULT NULL,
  `frequency` varchar(30) DEFAULT NULL,
  `lease_name` varchar(30) DEFAULT NULL,
  `lease_term_defined` varchar(30) DEFAULT NULL,
  `lease_term_year` varchar(30) DEFAULT NULL,
  `space_name` varchar(30) DEFAULT NULL,
  `fiscal_year` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKk3f4v2x8ib68ynof0w608cv7v` (`space_name`,`charge_type`)
) ENGINE=MyISAM AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;

LOCK TABLES `rpr` WRITE;
/*!40000 ALTER TABLE `rpr` DISABLE KEYS */;

INSERT INTO `rpr` (`id`, `amount`, `charge_name`, `charge_type`, `effective_date`, `end_date`, `escalation_type`, `frequency`, `lease_name`, `lease_term_defined`, `lease_term_year`, `space_name`, `fiscal_year`)
VALUES
	(23,'1254','FASB','FASB - Charge','05/01/2016','04/30/2020','No Increase','Semi-Annual','ERLeaseEx','Fiscal Year','1st of Month After','ERLeaseExSpace','08/01/2016'),
	(24,'1254','FASB','FASB - Charge','05/01/2016','04/30/2020','No Increase','Monthly','ERLease1','Calendar Year','On LCD','ERLease1Space',NULL),
	(25,'1254','FASB','FASB - Charge','05/01/2016','04/30/2020','No Increase','Monthly','ERLeaseInc','Fiscal Year','1st of Month After','ERLeaseIncSpace','08/01/2016'),
	(26,'1254','FASB','FASB - Charge','05/01/2016','04/30/2020','No Increase','Monthly','ERLease1','Lease Year','On LCD','ERLease1Space2',NULL),
	(27,'1254','FASB','FASB - Charge','05/01/2016','04/30/2020','No Increase','Monthly','ERLease2','Lease Year','On LCD','ERLease2Space',NULL),
	(28,'1254','FASB','FASB - Charge','05/01/2016','04/30/2020','No Increase','One-Time','LIn6628070','Calendar Year','1st of Month After','LIn6628070Sp',NULL),
	(29,'1254','FASB','FASB - Charge','05/01/2016','04/30/2020','No Increase','One-Time','LIn6628072','Lease Year','1st of Month After','LIn6628072Sp',NULL),
	(30,'1254','FASB','FASB - Charge','05/01/2016','04/30/2020','No Increase','One-Time','LIn6628073','Fiscal Year','1st of Month After','LIn6628073Sp','08/01/2016'),
	(31,'1254','FASB','FASB - Charge','05/01/2016','04/30/2020','No Increase','One-Time','LIn6628074','Calendar Year','On LCD','LIn6628074Sp',NULL),
	(32,'1254','FASB','FASB - Charge','05/01/2016','04/30/2020','No Increase','One-Time','LIn6628075','Lease Year','On LCD','LIn6628075Sp',NULL),
	(33,'1254','FASB','FASB - Charge','05/01/2016','04/30/2020','No Increase','One-Time','LIn6628076','Fiscal Year','On LCD','LIn6628076Sp','08/01/2016'),
	(34,'1254','FASB','FASB - Charge','05/01/2016','04/30/2020','No Increase','Quarterly','LIn6628077','Calendar Year','1st of Month After','LIn6628077Sp',NULL),
	(35,'1254','FASB','FASB - Charge','05/01/2016','04/30/2020','No Increase','Quarterly','LIn6628078','Lease Year','1st of Month After','LIn6628078Sp',NULL),
	(36,'1254','FASB','FASB - Charge','05/01/2016','04/30/2020','No Increase','Quarterly','LIn6628079','Fiscal Year','1st of Month After','LIn6628079Sp','08/01/2016'),
	(37,'1254','FASB','FASB - Charge','05/01/2016','04/30/2020','No Increase','Quarterly','LIn6628080','Calendar Year','On LCD','LIn6628080Sp',NULL),
	(38,'1254','FASB','FASB - Charge','05/01/2016','04/30/2020','No Increase','Quarterly','LIn6628081','Lease Year','On LCD','LIn6628081Sp',NULL),
	(39,'1254','FASB','FASB - Charge','05/01/2016','04/30/2020','No Increase','Quarterly','LIn6628083','Fiscal Year','On LCD','LIn6628083Sp','08/01/2016');

/*!40000 ALTER TABLE `rpr` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table space
# ------------------------------------------------------------

DROP TABLE IF EXISTS `space`;

CREATE TABLE `space` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `end_date` varchar(255) DEFAULT NULL,
  `floor` varchar(255) DEFAULT NULL,
  `lease_name` varchar(30) DEFAULT NULL,
  `property_code` varchar(30) DEFAULT NULL,
  `property_name` varchar(30) DEFAULT NULL,
  `space_name` varchar(255) DEFAULT NULL,
  `start_date` varchar(255) DEFAULT NULL,
  `rentable_lease` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fmlkog2odr5stpenaumhxbge0` (`space_name`)
) ENGINE=MyISAM AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;

LOCK TABLES `space` WRITE;
/*!40000 ALTER TABLE `space` DISABLE KEYS */;

INSERT INTO `space` (`id`, `end_date`, `floor`, `lease_name`, `property_code`, `property_name`, `space_name`, `start_date`, `rentable_lease`)
VALUES
	(23,'12/31/2021','1','ERLeaseEx','EditRevisionProperty01','EditRevisionProperty01','ERLeaseExSpace','01/01/2016','125'),
	(24,'12/31/2021','1','ERLease1','EditRevisionProperty01','EditRevisionProperty01','ERLease1Space','01/01/2016','123'),
	(25,'12/31/2021','1','ERLeaseInc','EditRevisionProperty01','EditRevisionProperty01','ERLeaseIncSpace','01/01/2016','128'),
	(26,'12/31/2021','1','ERLease1','EditRevisionProperty01','EditRevisionProperty01','ERLease1Space2','01/01/2016','123'),
	(27,'12/31/2021','1','ERLease2','EditRevisionProperty01','EditRevisionProperty01','ERLease2Space','01/01/2016','123'),
	(28,'12/31/2021','1','LIn6628070','EditRevisionProperty01','EditRevisionProperty01','LIn6628070Sp','01/01/2016','123'),
	(29,'12/31/2021','1','LIn6628072','EditRevisionProperty01','EditRevisionProperty01','LIn6628072Sp','01/01/2016','123'),
	(30,'12/31/2021','1','LIn6628073','EditRevisionProperty01','EditRevisionProperty01','LIn6628073Sp','01/01/2016','123'),
	(31,'12/31/2021','1','LIn6628074','EditRevisionProperty01','EditRevisionProperty01','LIn6628074Sp','01/01/2016','123'),
	(32,'12/31/2021','1','LIn6628075','EditRevisionProperty01','EditRevisionProperty01','LIn6628075Sp','01/01/2016','123'),
	(33,'12/31/2021','1','LIn6628076','EditRevisionProperty01','EditRevisionProperty01','LIn6628076Sp','01/01/2016','123'),
	(34,'12/31/2021','1','LIn6628077','EditRevisionProperty01','EditRevisionProperty01','LIn6628077Sp','01/01/2016','123'),
	(35,'12/31/2021','1','LIn6628078','EditRevisionProperty01','EditRevisionProperty01','LIn6628078Sp','01/01/2016','123'),
	(36,'12/31/2021','1','LIn6628079','EditRevisionProperty01','EditRevisionProperty01','LIn6628079Sp','01/01/2016','123'),
	(37,'12/31/2021','1','LIn6628080','EditRevisionProperty01','EditRevisionProperty01','LIn6628080Sp','01/01/2016','123'),
	(38,'12/31/2021','1','LIn6628081','EditRevisionProperty01','EditRevisionProperty01','LIn6628081Sp','01/01/2016','123'),
	(39,'12/31/2021','1','LIn6628083','EditRevisionProperty01','EditRevisionProperty01','LIn6628083Sp','01/01/2016','123');

/*!40000 ALTER TABLE `space` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table test_data_map
# ------------------------------------------------------------

DROP TABLE IF EXISTS `test_data_map`;

CREATE TABLE `test_data_map` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `preq_id` bigint(20) DEFAULT NULL,
  `test_case_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6ndvuemrdieapfdwfosdp9k1s` (`preq_id`,`test_case_id`)
) ENGINE=MyISAM AUTO_INCREMENT=181 DEFAULT CHARSET=latin1;

LOCK TABLES `test_data_map` WRITE;
/*!40000 ALTER TABLE `test_data_map` DISABLE KEYS */;

INSERT INTO `test_data_map` (`id`, `preq_id`, `test_case_id`)
VALUES
	(101,74,'6530924'),
	(102,74,'6530956'),
	(103,74,'6530957'),
	(104,74,'6530958'),
	(105,75,'6530924'),
	(106,76,'6530956'),
	(107,77,'6530957'),
	(108,77,'6530958'),
	(109,78,'6530924'),
	(110,79,'6530956'),
	(111,80,'6530957'),
	(112,80,'6530958'),
	(113,81,'6530924'),
	(114,82,'6530956'),
	(115,83,'6530957'),
	(116,83,'6530958'),
	(117,74,'6628060'),
	(118,75,'6628060'),
	(119,78,'6628060'),
	(120,81,'6628060'),
	(121,74,'6628061'),
	(122,76,'6628061'),
	(123,79,'6628061'),
	(124,82,'6628061'),
	(125,74,'28062'),
	(126,76,'28062'),
	(127,84,'28062'),
	(128,85,'28062'),
	(129,74,'6628062'),
	(130,86,'6628062'),
	(131,87,'6628062'),
	(132,88,'6628062'),
	(133,74,'6628070'),
	(134,74,'6628072'),
	(135,74,'6628073'),
	(136,74,'6628074'),
	(137,74,'6628075'),
	(138,74,'6628076'),
	(139,89,'6628070'),
	(140,90,'6628072'),
	(141,91,'6628073'),
	(142,92,'6628074'),
	(143,93,'6628075'),
	(144,94,'6628076'),
	(145,95,'6628070'),
	(146,96,'6628072'),
	(147,97,'6628073'),
	(148,98,'6628074'),
	(149,99,'6628075'),
	(150,100,'6628076'),
	(151,101,'6628070'),
	(152,102,'6628072'),
	(153,103,'6628073'),
	(154,104,'6628074'),
	(155,105,'6628075'),
	(156,106,'6628076'),
	(157,74,'6628077'),
	(158,74,'6628078'),
	(159,74,'6628079'),
	(160,74,'6628080'),
	(161,74,'6628081'),
	(162,74,'6628083'),
	(163,107,'6628077'),
	(164,108,'6628078'),
	(165,109,'6628079'),
	(166,110,'6628080'),
	(167,111,'6628081'),
	(168,112,'6628083'),
	(169,113,'6628077'),
	(170,114,'6628078'),
	(171,115,'6628079'),
	(172,116,'6628080'),
	(173,117,'6628081'),
	(174,118,'6628083'),
	(175,119,'6628077'),
	(176,120,'6628078'),
	(177,121,'6628079'),
	(178,122,'6628080'),
	(179,123,'6628081'),
	(180,124,'6628083');

/*!40000 ALTER TABLE `test_data_map` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
