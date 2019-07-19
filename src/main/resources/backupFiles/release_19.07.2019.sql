# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 10.1.0.45 (MySQL 5.5.60-MariaDB)
# Database: Release
# Generation Time: 2019-07-19 11:59:04 +0000
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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

LOCK TABLES `lease` WRITE;
/*!40000 ALTER TABLE `lease` DISABLE KEYS */;

INSERT INTO `lease` (`id`, `begin_date`, `billing_type`, `code_type`, `contract_type`, `expiration_date`, `lease_code`, `lease_group1`, `lease_name`, `lease_status`, `lease_type`, `property_code`, `property_name`)
VALUES
	(1,'01/01/2017','Expense','DEFAULT','Real Estate Contract','12/31/2020','Reg2ndRevEx4135','01','Reg2ndRevEx4135','Active','Base Year','Reg2ndRevision','Reg2ndRevision'),
	(2,'01/01/2017','Expense','DEFAULT','Real Estate Contract','12/31/2020','Reg2ndRevEx4136','01','Reg2ndRevEx4136','Active','Base Year','Reg2ndRevision','Reg2ndRevision'),
	(3,'01/01/2017','Expense','DEFAULT','Real Estate Contract','12/31/2020','Reg2ndRevEx4137','01','Reg2ndRevEx4137','Active','Base Year','Reg2ndRevision','Reg2ndRevision'),
	(4,'01/01/2017','Expense','DEFAULT','Real Estate Contract','12/31/2020','Reg2ndRevEx4138','01','Reg2ndRevEx4138','Active','Base Year','Reg2ndRevision','Reg2ndRevision'),
	(5,'01/01/2017','Expense','DEFAULT','Real Estate Contract','12/31/2020','Reg2ndRevEx4139','01','Reg2ndRevEx4139','Active','Base Year','Reg2ndRevision','Reg2ndRevision'),
	(6,'01/01/2017','Expense','DEFAULT','Real Estate Contract','12/31/2020','Reg2ndRevEx4140','01','Reg2ndRevEx4140','Active','Base Year','Reg2ndRevision','Reg2ndRevision'),
	(7,'01/01/2017','Expense','DEFAULT','Real Estate Contract','12/31/2020','Reg2ndRevEx4141','01','Reg2ndRevEx4141','Active','Base Year','Reg2ndRevision','Reg2ndRevision'),
	(8,'01/01/2017','Expense','DEFAULT','Real Estate Contract','12/31/2020','Reg2ndRevEx4142','01','Reg2ndRevEx4142','Active','Base Year','Reg2ndRevision','Reg2ndRevision'),
	(9,'01/01/2017','Expense','DEFAULT','Real Estate Contract','12/31/2020','Reg2ndRevEx4158','01','Reg2ndRevEx4158','Active','Base Year','Reg2ndRevision','Reg2ndRevision'),
	(10,'01/01/2017','Expense','DEFAULT','Real Estate Contract','12/31/2020','Reg2ndRevEx4159','01','Reg2ndRevEx4159','Active','Base Year','Reg2ndRevision','Reg2ndRevision'),
	(11,'01/01/2017','Expense','DEFAULT','Real Estate Contract','12/31/2020','2ndRevEx4160','01','2ndRevEx4160','Active','Base Year','Reg2ndRevision','Reg2ndRevision'),
	(12,'01/01/2017','Expense','DEFAULT','Real Estate Contract','12/31/2020','Reg2ndRevEx4162','01','Reg2ndRevEx4162','Active','Base Year','Reg2ndRevision','Reg2ndRevision'),
	(13,'01/01/2017','Expense','DEFAULT','Real Estate Contract','12/31/2020','Reg2ndRevEx4161','01','Reg2ndRevEx4161','Active','Base Year','Reg2ndRevision','Reg2ndRevision'),
	(14,'01/01/2017','Expense','DEFAULT','Real Estate Contract','12/31/2020','Reg2ndRevEx4163','01','Reg2ndRevEx4163','Active','Base Year','Reg2ndRevision','Reg2ndRevision'),
	(15,'01/01/2017','Expense','DEFAULT','Real Estate Contract','12/31/2020','Reg2ndRevEx4164','01','Reg2ndRevEx4164','Active','Base Year','Reg2ndRevision','Reg2ndRevision'),
	(16,'01/01/2017','Expense','DEFAULT','Real Estate Contract','12/31/2020','Reg2ndRevEx4165','01','Reg2ndRevEx4165','Active','Base Year','Reg2ndRevision','Reg2ndRevision'),
	(17,'01/01/2017','Expense','DEFAULT','Real Estate Contract','12/31/2020','Reg2ndRevEx4150','01','Reg2ndRevEx4150','Active','Base Year','Reg2ndRevision','Reg2ndRevision'),
	(18,'01/01/2017','Expense','DEFAULT','Real Estate Contract','12/31/2020','Reg2ndRevEx4151','01','Reg2ndRevEx4151','Active','Base Year','Reg2ndRevision','Reg2ndRevision'),
	(19,'01/01/2017','Expense','DEFAULT','Real Estate Contract','12/31/2020','Reg2ndRevEx4152','01','Reg2ndRevEx4152','Active','Base Year','Reg2ndRevision','Reg2ndRevision'),
	(20,'01/01/2017','Expense','DEFAULT','Real Estate Contract','12/31/2020','Reg2ndRevEx4153','01','Reg2ndRevEx4153','Active','Base Year','Reg2ndRevision','Reg2ndRevision'),
	(21,'01/01/2017','Expense','DEFAULT','Real Estate Contract','12/31/2020','Reg2ndRevEx4154','01','Reg2ndRevEx4154','Active','Base Year','Reg2ndRevision','Reg2ndRevision'),
	(22,'01/01/2017','Expense','DEFAULT','Real Estate Contract','12/31/2020','Reg2ndRevEx4155','01','Reg2ndRevEx4155','Active','Base Year','Reg2ndRevision','Reg2ndRevision'),
	(23,'01/01/2017','Expense','DEFAULT','Real Estate Contract','12/31/2020','Reg2ndRevEx4156','01','Reg2ndRevEx4156','Active','Base Year','Reg2ndRevision','Reg2ndRevision'),
	(24,'01/01/2017','Expense','DEFAULT','Real Estate Contract','12/31/2020','Reg2ndRevEx4157','01','Reg2ndRevEx4157','Active','Base Year','Reg2ndRevision','Reg2ndRevision'),
	(25,'01/01/2017','Income','DEFAULT','Real Estate Contract','12/31/2020','lessor_6624202','01','lessor_6624202','Active','Base Year','Reg2ndRevisionCal3','Reg2ndRevisionCal3'),
	(26,'01/01/2017','Income','DEFAULT','Real Estate Contract','12/31/2020','lessor_6624203','01','lessor_6624203','Active','Base Year','Reg2ndRevisionCal3','Reg2ndRevisionCal3'),
	(27,'01/01/2017','Income','DEFAULT','Real Estate Contract','12/31/2020','lessor_6624204','01','lessor_6624204','Active','Base Year','Reg2ndRevisionCal3','Reg2ndRevisionCal3'),
	(28,'01/01/2017','Income','DEFAULT','Real Estate Contract','12/31/2020','lessor_6624205','01','lessor_6624205','Active','Base Year','Reg2ndRevisionCal3','Reg2ndRevisionCal3'),
	(29,'01/01/2017','Income','DEFAULT','Real Estate Contract','12/31/2020','lessor_6624206','01','lessor_6624206','Active','Base Year','Reg2ndRevisionCal3','Reg2ndRevisionCal3'),
	(30,'01/01/2017','Income','DEFAULT','Real Estate Contract','12/31/2020','lessor_6624207','01','lessor_6624207','Active','Base Year','Reg2ndRevisionCal3','Reg2ndRevisionCal3'),
	(31,'01/01/2017','Income','DEFAULT','Real Estate Contract','12/31/2020','lessor_6624200','01','lessor_6624200','Active','Base Year','Reg2ndRevisionCal3','Reg2ndRevisionCal3'),
	(32,'01/01/2017','Income','DEFAULT','Real Estate Contract','12/31/2020','lessor_6624201','01','lessor_6624201','Active','Base Year','Reg2ndRevisionCal3','Reg2ndRevisionCal3'),
	(33,'01/01/2017','Expense','DEFAULT','Real Estate Contract','12/31/2020','Reg2ndRevEx4168','01','Reg2ndRevEx4168','Active','Base Year','Reg2ndRevision','Reg2ndRevision'),
	(34,'01/01/2017','Income','DEFAULT','Real Estate Contract','12/31/2020','lessor_6624177','01','lessor_6624177','Active','Base Year','Reg2ndRevisionCal3','Reg2ndRevisionCal3'),
	(35,'01/01/2017','Income','DEFAULT','Real Estate Contract','12/31/2020','lessor_6624178','01','lessor_6624178','Active','Base Year','Reg2ndRevisionCal3','Reg2ndRevisionCal3'),
	(36,'01/01/2017','Income','DEFAULT','Real Estate Contract','12/31/2020','lessor_6624179','01','lessor_6624179','Active','Base Year','Reg2ndRevisionCal3','Reg2ndRevisionCal3'),
	(37,'01/01/2017','Income','DEFAULT','Real Estate Contract','12/31/2020','lessor_6624180','01','lessor_6624180','Active','Base Year','Reg2ndRevisionCal3','Reg2ndRevisionCal3'),
	(38,'01/01/2017','Income','DEFAULT','Real Estate Contract','12/31/2020','lessor_6624181','01','lessor_6624181','Active','Base Year','Reg2ndRevisionCal3','Reg2ndRevisionCal3'),
	(39,'01/01/2017','Income','DEFAULT','Real Estate Contract','12/31/2020','lessor_6624182','01','lessor_6624182','Active','Base Year','Reg2ndRevisionCal3','Reg2ndRevisionCal3'),
	(40,'01/01/2017','Income','DEFAULT','Real Estate Contract','12/31/2020','lessor_6624183','01','lessor_6624183','Active','Base Year','Reg2ndRevisionCal3','Reg2ndRevisionCal3'),
	(41,'01/01/2017','Income','DEFAULT','Real Estate Contract','12/31/2020','lessor_6624184','01','lessor_6624184','Active','Base Year','Reg2ndRevisionCal3','Reg2ndRevisionCal3');

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

LOCK TABLES `preq_execution_history` WRITE;
/*!40000 ALTER TABLE `preq_execution_history` DISABLE KEYS */;

INSERT INTO `preq_execution_history` (`id`, `client_id`, `creation_time`, `environment`, `is_passed`, `preq_id`)
VALUES
	(1,'201480','2019-07-16 17:35:32','uat',b'1',1),
	(2,'201480','2019-07-16 17:38:35','uat',b'1',2),
	(3,'201480','2019-07-16 17:40:54','uat',b'1',26),
	(4,'201480','2019-07-16 17:44:34','uat',b'1',50),
	(5,'201480','2019-07-16 19:00:16','uat',b'1',3),
	(6,'201480','2019-07-16 19:02:43','uat',b'1',27),
	(7,'201480','2019-07-16 19:06:25','uat',b'1',51),
	(8,'201480','2019-07-16 20:15:22','uat',b'1',4),
	(9,'201480','2019-07-16 20:17:44','uat',b'1',28),
	(10,'201480','2019-07-16 20:21:18','uat',b'1',52),
	(11,'201480','2019-07-16 20:28:03','uat',b'1',74),
	(21,'201480','2019-07-17 12:21:20','uat',b'1',75),
	(26,'201480','2019-07-17 13:07:42','uat',b'1',83),
	(27,'201480','2019-07-17 13:11:37','uat',b'1',91),
	(15,'201480','2019-07-17 10:59:31','uat',b'1',5),
	(16,'201480','2019-07-17 11:01:52','uat',b'1',29),
	(17,'201480','2019-07-17 11:05:30','uat',b'1',53),
	(18,'201480','2019-07-17 11:42:56','uat',b'1',6),
	(19,'201480','2019-07-17 11:45:19','uat',b'1',30),
	(20,'201480','2019-07-17 11:48:58','uat',b'1',54),
	(23,'201480','2019-07-17 12:34:41','uat',b'1',7),
	(24,'201480','2019-07-17 12:37:06','uat',b'1',31),
	(28,'201480','2019-07-17 13:14:18','uat',b'1',55),
	(29,'201480','2019-07-17 14:36:26','uat',b'1',8),
	(31,'201480','2019-07-17 14:54:17','uat',b'1',32),
	(32,'201480','2019-07-17 14:57:58','uat',b'1',56),
	(33,'201480','2019-07-17 15:30:36','uat',b'1',9),
	(35,'201480','2019-07-17 15:39:12','uat',b'1',33),
	(36,'201480','2019-07-17 15:43:02','uat',b'1',57),
	(37,'201480','2019-07-17 16:16:34','uat',b'1',10),
	(40,'201480','2019-07-17 16:34:41','uat',b'1',34),
	(41,'201480','2019-07-17 16:38:22','uat',b'1',58),
	(90,'201480','2019-07-18 10:38:51','uat',b'1',11),
	(91,'201480','2019-07-18 10:41:07','uat',b'1',35),
	(92,'201480','2019-07-18 10:44:41','uat',b'1',59),
	(114,'201480','2019-07-19 15:20:51','uat',b'1',12),
	(115,'201480','2019-07-19 15:24:37','uat',b'1',36),
	(116,'201480','2019-07-19 15:31:02','uat',b'0',60),
	(48,'201480','2019-07-17 17:52:37','uat',b'1',14),
	(49,'201480','2019-07-17 17:54:58','uat',b'1',38),
	(50,'201480','2019-07-17 17:58:27','uat',b'1',62),
	(51,'201480','2019-07-17 18:17:27','uat',b'1',99),
	(52,'201480','2019-07-17 18:19:51','uat',b'1',100),
	(53,'201480','2019-07-17 18:21:05','uat',b'1',13),
	(54,'201480','2019-07-17 18:23:22','uat',b'1',37),
	(55,'201480','2019-07-17 18:23:26','uat',b'1',101),
	(93,'201480','2019-07-18 12:38:24','uat',b'1',61),
	(57,'201480','2019-07-17 18:31:27','uat',b'1',15),
	(58,'201480','2019-07-17 18:33:48','uat',b'1',39),
	(59,'201480','2019-07-17 18:37:23','uat',b'1',63),
	(60,'201480','2019-07-17 18:57:04','uat',b'1',16),
	(61,'201480','2019-07-17 18:59:24','uat',b'1',40),
	(62,'201480','2019-07-17 19:06:40','uat',b'0',64),
	(63,'201480','2019-07-17 19:09:43','uat',b'1',17),
	(64,'201480','2019-07-17 19:12:00','uat',b'1',41),
	(65,'201480','2019-07-17 19:19:16','uat',b'0',65),
	(66,'201480','2019-07-17 19:22:17','uat',b'1',18),
	(67,'201480','2019-07-17 19:24:38','uat',b'1',42),
	(68,'201480','2019-07-17 19:28:11','uat',b'1',66),
	(69,'201480','2019-07-17 19:34:53','uat',b'1',19),
	(70,'201480','2019-07-17 19:37:10','uat',b'1',43),
	(71,'201480','2019-07-17 19:40:42','uat',b'1',67),
	(72,'201480','2019-07-17 20:01:02','uat',b'1',20),
	(73,'201480','2019-07-17 20:03:16','uat',b'1',44),
	(74,'201480','2019-07-17 20:06:45','uat',b'1',68),
	(75,'201480','2019-07-17 20:29:47','uat',b'1',21),
	(76,'201480','2019-07-17 20:32:04','uat',b'1',45),
	(77,'201480','2019-07-17 20:35:36','uat',b'1',69),
	(78,'201480','2019-07-17 20:53:09','uat',b'1',22),
	(79,'201480','2019-07-17 20:55:22','uat',b'1',46),
	(80,'201480','2019-07-17 20:58:53','uat',b'1',70),
	(81,'201480','2019-07-17 21:17:28','uat',b'1',23),
	(82,'201480','2019-07-17 21:19:44','uat',b'1',47),
	(83,'201480','2019-07-17 21:23:16','uat',b'1',71),
	(84,'201480','2019-07-17 21:43:33','uat',b'1',24),
	(85,'201480','2019-07-17 21:45:47','uat',b'1',48),
	(104,'201480','2019-07-18 18:05:26','uat',b'1',72),
	(87,'201480','2019-07-17 21:53:54','uat',b'1',25),
	(88,'201480','2019-07-17 21:56:13','uat',b'1',49),
	(89,'201480','2019-07-17 21:59:42','uat',b'1',73),
	(94,'201480','2019-07-18 14:15:29','uat',b'1',77),
	(95,'201480','2019-07-18 14:17:45','uat',b'1',85),
	(96,'201480','2019-07-18 14:21:18','uat',b'1',93),
	(97,'201480','2019-07-18 15:40:52','uat',b'1',81),
	(98,'201480','2019-07-18 15:43:10','uat',b'1',89),
	(99,'201480','2019-07-18 15:46:49','uat',b'1',97),
	(101,'201480','2019-07-18 17:36:02','uat',b'1',82),
	(102,'201480','2019-07-18 17:39:30','uat',b'1',90),
	(103,'201480','2019-07-18 17:43:05','uat',b'1',98),
	(123,'201480','2019-07-19 16:17:10','uat',b'1',104),
	(124,'201480','2019-07-19 16:19:33','uat',b'1',112),
	(107,'201480','2019-07-18 20:39:50','uat',b'0',120),
	(108,'201480','2019-07-18 20:43:31','uat',b'1',78),
	(109,'201480','2019-07-18 20:47:30','uat',b'1',86),
	(110,'201480','2019-07-18 20:56:31','uat',b'1',94),
	(111,'201480','2019-07-19 15:13:03','uat',b'1',76),
	(112,'201480','2019-07-19 15:15:27','uat',b'1',84),
	(113,'201480','2019-07-19 15:19:55','uat',b'1',92),
	(117,'201480','2019-07-19 15:47:34','uat',b'1',102),
	(118,'201480','2019-07-19 15:50:02','uat',b'1',110),
	(119,'201480','2019-07-19 15:53:37','uat',b'1',118),
	(120,'201480','2019-07-19 16:03:01','uat',b'0',103),
	(121,'201480','2019-07-19 16:06:11','uat',b'1',111),
	(122,'201480','2019-07-19 16:09:47','uat',b'1',119),
	(125,'201480','2019-07-19 16:30:47','uat',b'1',105),
	(126,'201480','2019-07-19 16:33:11','uat',b'1',113),
	(127,'201480','2019-07-19 16:56:47','uat',b'1',121),
	(128,'201480','2019-07-19 17:19:43','uat',b'1',106),
	(129,'201480','2019-07-19 17:22:03','uat',b'1',114),
	(130,'201480','2019-07-19 17:25:34','uat',b'1',122);

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

LOCK TABLES `prequisite_data` WRITE;
/*!40000 ALTER TABLE `prequisite_data` DISABLE KEYS */;

INSERT INTO `prequisite_data` (`preq_id`, `data_id`, `type`)
VALUES
	(1,1,'property'),
	(2,1,'lease'),
	(3,2,'lease'),
	(4,3,'lease'),
	(5,4,'lease'),
	(6,5,'lease'),
	(7,6,'lease'),
	(8,7,'lease'),
	(9,8,'lease'),
	(10,9,'lease'),
	(11,10,'lease'),
	(12,11,'lease'),
	(13,12,'lease'),
	(14,13,'lease'),
	(15,14,'lease'),
	(16,15,'lease'),
	(17,16,'lease'),
	(18,17,'lease'),
	(19,18,'lease'),
	(20,19,'lease'),
	(21,20,'lease'),
	(22,21,'lease'),
	(23,22,'lease'),
	(24,23,'lease'),
	(25,24,'lease'),
	(26,1,'space'),
	(27,2,'space'),
	(28,3,'space'),
	(29,4,'space'),
	(30,5,'space'),
	(31,6,'space'),
	(32,7,'space'),
	(33,8,'space'),
	(34,9,'space'),
	(35,10,'space'),
	(36,11,'space'),
	(37,12,'space'),
	(38,13,'space'),
	(39,14,'space'),
	(40,15,'space'),
	(41,16,'space'),
	(42,17,'space'),
	(43,18,'space'),
	(44,19,'space'),
	(45,20,'space'),
	(46,21,'space'),
	(47,22,'space'),
	(48,23,'space'),
	(49,24,'space'),
	(50,1,'rpr'),
	(51,2,'rpr'),
	(52,3,'rpr'),
	(53,4,'rpr'),
	(54,5,'rpr'),
	(55,6,'rpr'),
	(56,7,'rpr'),
	(57,8,'rpr'),
	(58,9,'rpr'),
	(59,10,'rpr'),
	(60,11,'rpr'),
	(61,12,'rpr'),
	(62,13,'rpr'),
	(63,14,'rpr'),
	(64,15,'rpr'),
	(65,16,'rpr'),
	(66,17,'rpr'),
	(67,18,'rpr'),
	(68,19,'rpr'),
	(69,20,'rpr'),
	(70,21,'rpr'),
	(71,22,'rpr'),
	(72,23,'rpr'),
	(73,24,'rpr'),
	(74,2,'property'),
	(75,25,'lease'),
	(76,26,'lease'),
	(77,27,'lease'),
	(78,28,'lease'),
	(79,29,'lease'),
	(80,30,'lease'),
	(81,31,'lease'),
	(82,32,'lease'),
	(83,25,'space'),
	(84,26,'space'),
	(85,27,'space'),
	(86,28,'space'),
	(87,29,'space'),
	(88,30,'space'),
	(89,31,'space'),
	(90,32,'space'),
	(91,25,'rpr'),
	(92,26,'rpr'),
	(93,27,'rpr'),
	(94,28,'rpr'),
	(95,29,'rpr'),
	(96,30,'rpr'),
	(97,31,'rpr'),
	(98,32,'rpr'),
	(99,33,'lease'),
	(100,33,'space'),
	(101,33,'rpr'),
	(102,34,'lease'),
	(103,35,'lease'),
	(104,36,'lease'),
	(105,37,'lease'),
	(106,38,'lease'),
	(107,39,'lease'),
	(108,40,'lease'),
	(109,41,'lease'),
	(110,34,'space'),
	(111,35,'space'),
	(112,36,'space'),
	(113,37,'space'),
	(114,38,'space'),
	(115,39,'space'),
	(116,40,'space'),
	(117,41,'space'),
	(118,34,'rpr'),
	(119,35,'rpr'),
	(120,36,'rpr'),
	(121,37,'rpr'),
	(122,38,'rpr'),
	(123,39,'rpr'),
	(124,40,'rpr'),
	(125,41,'rpr');

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

LOCK TABLES `property` WRITE;
/*!40000 ALTER TABLE `property` DISABLE KEYS */;

INSERT INTO `property` (`id`, `address`, `auto_manage`, `chart_type`, `city`, `code_type`, `country`, `currency`, `postal`, `property_code`, `property_group1`, `property_group2`, `property_group3`, `property_name`, `sq_ft_rentable`, `state`, `status`)
VALUES
	(1,'Add2','TRUE','QA Testing','test','DEFAULT','Togo','USD','12230','Reg2ndRevision','Building List','Central','India','Reg2ndRevision','100000000','Kara','Active'),
	(2,'Add2','TRUE','QA Testing','test','DEFAULT','Togo','USD','12230','Reg2ndRevisionCal3','Building List','Central','India','Reg2ndRevisionCal3','100000000','Kara','Active');

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

LOCK TABLES `rpr` WRITE;
/*!40000 ALTER TABLE `rpr` DISABLE KEYS */;

INSERT INTO `rpr` (`id`, `amount`, `charge_name`, `charge_type`, `effective_date`, `end_date`, `escalation_type`, `frequency`, `lease_name`, `lease_term_defined`, `lease_term_year`, `space_name`, `fiscal_year`)
VALUES
	(1,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Annual','Reg2ndRevEx4135','Calendar Year','On LCD','Reg2ndRevExSp4135',''),
	(2,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Annual','Reg2ndRevEx4136','Calendar Year','On LCD','Reg2ndRevExSp4136',''),
	(3,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Annual','Reg2ndRevEx4137','Calendar Year','On LCD','Reg2ndRevExSp4137',''),
	(4,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Annual','Reg2ndRevEx4138','Calendar Year','On LCD','Reg2ndRevExSp4138',''),
	(5,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Semi-Annual','Reg2ndRevEx4139','Calendar Year','On LCD','Reg2ndRevExSp4139',''),
	(6,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Semi-Annual','Reg2ndRevEx4140','Calendar Year','On LCD','Reg2ndRevExSp4140',''),
	(7,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Semi-Annual','Reg2ndRevEx4141','Calendar Year','On LCD','Reg2ndRevExSp4141',''),
	(8,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Semi-Annual','Reg2ndRevEx4142','Calendar Year','On LCD','Reg2ndRevExSp4142',''),
	(9,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Semi-Annual','Reg2ndRevEx4158','Calendar Year','On LCD','Reg2ndRevExSp4158',''),
	(10,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Semi-Annual','Reg2ndRevEx4159','Calendar Year','1st of Month After','Reg2ndRevExSp4159',''),
	(11,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Semi-Annual','2ndRevEx4160','Calendar Year','On LCD','2ndRevExSp4160',''),
	(12,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Semi-Annual','Reg2ndRevEx4162','Lease Year','On LCD','Reg2ndRevExSp4162',''),
	(13,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Semi-Annual','Reg2ndRevEx4161','Calendar Year','On LCD','Reg2ndRevExSp4161',''),
	(14,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Semi-Annual','Reg2ndRevEx4163','Lease Year','On LCD','Reg2ndRevExSp4163',''),
	(15,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Semi-Annual','Reg2ndRevEx4164','Fiscal Year','On LCD','Reg2ndRevExSp4164','06/01/2017'),
	(16,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Semi-Annual','Reg2ndRevEx4165','Fiscal Year','On LCD','Reg2ndRevExSp4165','06/01/2017'),
	(17,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Quarterly','Reg2ndRevEx4150','Calendar Year','On LCD','Reg2ndRevExSp4150',''),
	(18,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Quarterly','Reg2ndRevEx4151','Calendar Year','On LCD','Reg2ndRevExSp4151',''),
	(19,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Quarterly','Reg2ndRevEx4152','Calendar Year','On LCD','Reg2ndRevExSp4152',''),
	(20,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Quarterly','Reg2ndRevEx4153','Calendar Year','On LCD','Reg2ndRevExSp4153',''),
	(21,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','One-Time','Reg2ndRevEx4154','Calendar Year','On LCD','Reg2ndRevExSp4154',''),
	(22,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','One-Time','Reg2ndRevEx4155','Calendar Year','On LCD','Reg2ndRevExSp4155',''),
	(23,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','One-Time','Reg2ndRevEx4156','Calendar Year','On LCD','Reg2ndRevExSp4156',''),
	(24,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','One-Time','Reg2ndRevEx4157','Calendar Year','On LCD','Reg2ndRevExSp4157',''),
	(25,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Semi-Annual','lessor_6624202','Calendar Year','On LCD','splessor_6624202','06/01/2017'),
	(26,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Semi-Annual','lessor_6624203','Calendar Year','On LCD','splessor_6624203','06/01/2017'),
	(27,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Semi-Annual','lessor_6624204','Lease Year','On LCD','splessor_6624204','06/01/2017'),
	(28,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Semi-Annual','lessor_6624205','Lease Year','On LCD','splessor_6624205','06/01/2017'),
	(29,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Semi-Annual','lessor_6624206','Fiscal Year','On LCD','splessor_6624206','06/01/2017'),
	(30,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Semi-Annual','lessor_6624207','Fiscal Year','On LCD','splessor_6624207','06/01/2017'),
	(31,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Semi-Annual','lessor_6624200','Calendar Year','On LCD','splessor_6624200','06/01/2017'),
	(32,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Semi-Annual','lessor_6624201','Calendar Year','1st of Month After','splessor_6624201','06/01/2017'),
	(33,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Semi-Annual','Reg2ndRevEx4168','Calendar Year','On LCD','Reg2ndRevExSp4168',''),
	(34,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Annual','lessor_6624177','Calendar Year','On LCD','splessor_6624177','06/01/2017'),
	(35,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Annual','lessor_6624178','Calendar Year','On LCD','splessor_6624178','06/01/2017'),
	(36,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Annual','lessor_6624179','Calendar Year','On LCD','splessor_6624179','06/01/2017'),
	(37,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Annual','lessor_6624180','Calendar Year','On LCD','splessor_6624180','06/01/2017'),
	(38,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Semi-Annual','lessor_6624181','Calendar Year','On LCD','splessor_6624181','06/01/2017'),
	(39,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Semi-Annual','lessor_6624182','Calendar Year','On LCD','splessor_6624182','06/01/2017'),
	(40,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Semi-Annual','lessor_6624183','Calendar Year','On LCD','splessor_6624183','06/01/2017'),
	(41,'10000.00','FASB','FASB - Charge','01/01/2017','12/31/2020','No Increase','Semi-Annual','lessor_6624184','Calendar Year','On LCD','splessor_6624184','06/01/2017');

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

LOCK TABLES `space` WRITE;
/*!40000 ALTER TABLE `space` DISABLE KEYS */;

INSERT INTO `space` (`id`, `end_date`, `floor`, `lease_name`, `property_code`, `property_name`, `space_name`, `start_date`, `rentable_lease`)
VALUES
	(1,'12/31/2020','1','Reg2ndRevEx4135','Reg2ndRevision','Reg2ndRevision','Reg2ndRevExSp4135','01/01/2017','132'),
	(2,'12/31/2020','1','Reg2ndRevEx4136','Reg2ndRevision','Reg2ndRevision','Reg2ndRevExSp4136','01/01/2017','132'),
	(3,'12/31/2020','1','Reg2ndRevEx4137','Reg2ndRevision','Reg2ndRevision','Reg2ndRevExSp4137','01/01/2017','132'),
	(4,'12/31/2020','1','Reg2ndRevEx4138','Reg2ndRevision','Reg2ndRevision','Reg2ndRevExSp4138','01/01/2017','132'),
	(5,'12/31/2020','1','Reg2ndRevEx4139','Reg2ndRevision','Reg2ndRevision','Reg2ndRevExSp4139','01/01/2017','132'),
	(6,'12/31/2020','1','Reg2ndRevEx4140','Reg2ndRevision','Reg2ndRevision','Reg2ndRevExSp4140','01/01/2017','132'),
	(7,'12/31/2020','1','Reg2ndRevEx4141','Reg2ndRevision','Reg2ndRevision','Reg2ndRevExSp4141','01/01/2017','132'),
	(8,'12/31/2020','1','Reg2ndRevEx4142','Reg2ndRevision','Reg2ndRevision','Reg2ndRevExSp4142','01/01/2017','132'),
	(9,'12/31/2020','1','Reg2ndRevEx4158','Reg2ndRevision','Reg2ndRevision','Reg2ndRevExSp4158','01/01/2017','132'),
	(10,'12/31/2020','1','Reg2ndRevEx4159','Reg2ndRevision','Reg2ndRevision','Reg2ndRevExSp4159','01/01/2017','132'),
	(11,'12/31/2020','1','2ndRevEx4160','Reg2ndRevision','Reg2ndRevision','2ndRevExSp4160','01/01/2017','132'),
	(12,'12/31/2020','1','Reg2ndRevEx4162','Reg2ndRevision','Reg2ndRevision','Reg2ndRevExSp4162','01/01/2017','132'),
	(13,'12/31/2020','1','Reg2ndRevEx4161','Reg2ndRevision','Reg2ndRevision','Reg2ndRevExSp4161','01/01/2017','132'),
	(14,'12/31/2020','1','Reg2ndRevEx4163','Reg2ndRevision','Reg2ndRevision','Reg2ndRevExSp4163','01/01/2017','132'),
	(15,'12/31/2020','1','Reg2ndRevEx4164','Reg2ndRevision','Reg2ndRevision','Reg2ndRevExSp4164','01/01/2017','132'),
	(16,'12/31/2020','1','Reg2ndRevEx4165','Reg2ndRevision','Reg2ndRevision','Reg2ndRevExSp4165','01/01/2017','132'),
	(17,'12/31/2020','1','Reg2ndRevEx4150','Reg2ndRevision','Reg2ndRevision','Reg2ndRevExSp4150','01/01/2017','132'),
	(18,'12/31/2020','1','Reg2ndRevEx4151','Reg2ndRevision','Reg2ndRevision','Reg2ndRevExSp4151','01/01/2017','132'),
	(19,'12/31/2020','1','Reg2ndRevEx4152','Reg2ndRevision','Reg2ndRevision','Reg2ndRevExSp4152','01/01/2017','132'),
	(20,'12/31/2020','1','Reg2ndRevEx4153','Reg2ndRevision','Reg2ndRevision','Reg2ndRevExSp4153','01/01/2017','132'),
	(21,'12/31/2020','1','Reg2ndRevEx4154','Reg2ndRevision','Reg2ndRevision','Reg2ndRevExSp4154','01/01/2017','132'),
	(22,'12/31/2020','1','Reg2ndRevEx4155','Reg2ndRevision','Reg2ndRevision','Reg2ndRevExSp4155','01/01/2017','132'),
	(23,'12/31/2020','1','Reg2ndRevEx4156','Reg2ndRevision','Reg2ndRevision','Reg2ndRevExSp4156','01/01/2017','132'),
	(24,'12/31/2020','1','Reg2ndRevEx4157','Reg2ndRevision','Reg2ndRevision','Reg2ndRevExSp4157','01/01/2017','132'),
	(25,'12/31/2020','1','lessor_6624202','Reg2ndRevisionCal3','Reg2ndRevisionCal3','splessor_6624202','01/01/2017','132'),
	(26,'12/31/2020','1','lessor_6624203','Reg2ndRevisionCal3','Reg2ndRevisionCal3','splessor_6624203','01/01/2017','132'),
	(27,'12/31/2020','1','lessor_6624204','Reg2ndRevisionCal3','Reg2ndRevisionCal3','splessor_6624204','01/01/2017','132'),
	(28,'12/31/2020','1','lessor_6624205','Reg2ndRevisionCal3','Reg2ndRevisionCal3','splessor_6624205','01/01/2017','132'),
	(29,'12/31/2020','1','lessor_6624206','Reg2ndRevisionCal3','Reg2ndRevisionCal3','splessor_6624206','01/01/2017','132'),
	(30,'12/31/2020','1','lessor_6624207','Reg2ndRevisionCal3','Reg2ndRevisionCal3','splessor_6624207','01/01/2017','132'),
	(31,'12/31/2020','1','lessor_6624200','Reg2ndRevisionCal3','Reg2ndRevisionCal3','splessor_6624200','01/01/2017','132'),
	(32,'12/31/2020','1','lessor_6624201','Reg2ndRevisionCal3','Reg2ndRevisionCal3','splessor_6624201','01/01/2017','132'),
	(33,'12/31/2020','1','Reg2ndRevEx4168','Reg2ndRevision','Reg2ndRevision','Reg2ndRevExSp4168','01/01/2017','132'),
	(34,'12/31/2020','1','lessor_6624177','Reg2ndRevisionCal3','Reg2ndRevisionCal3','splessor_6624177','01/01/2017','132'),
	(35,'12/31/2020','1','lessor_6624178','Reg2ndRevisionCal3','Reg2ndRevisionCal3','splessor_6624178','01/01/2017','132'),
	(36,'12/31/2020','1','lessor_6624179','Reg2ndRevisionCal3','Reg2ndRevisionCal3','splessor_6624179','01/01/2017','132'),
	(37,'12/31/2020','1','lessor_6624180','Reg2ndRevisionCal3','Reg2ndRevisionCal3','splessor_6624180','01/01/2017','132'),
	(38,'12/31/2020','1','lessor_6624181','Reg2ndRevisionCal3','Reg2ndRevisionCal3','splessor_6624181','01/01/2017','132'),
	(39,'12/31/2020','1','lessor_6624182','Reg2ndRevisionCal3','Reg2ndRevisionCal3','splessor_6624182','01/01/2017','132'),
	(40,'12/31/2020','1','lessor_6624183','Reg2ndRevisionCal3','Reg2ndRevisionCal3','splessor_6624183','01/01/2017','132'),
	(41,'12/31/2020','1','lessor_6624184','Reg2ndRevisionCal3','Reg2ndRevisionCal3','splessor_6624184','01/01/2017','132');

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
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

LOCK TABLES `test_data_map` WRITE;
/*!40000 ALTER TABLE `test_data_map` DISABLE KEYS */;

INSERT INTO `test_data_map` (`id`, `preq_id`, `test_case_id`)
VALUES
	(1,1,'6624135'),
	(2,1,'6624136'),
	(3,1,'6624137'),
	(4,1,'6624138'),
	(5,1,'6624139'),
	(6,1,'6624140'),
	(7,1,'6624141'),
	(8,1,'6624142'),
	(9,1,'6624158'),
	(10,1,'6624159'),
	(11,1,'6624160'),
	(12,1,'6624162'),
	(13,1,'6624161'),
	(14,1,'6624163'),
	(15,1,'6624164'),
	(16,1,'6624165'),
	(17,1,'6624150'),
	(18,1,'6624151'),
	(19,1,'6624152'),
	(20,1,'6624153'),
	(21,1,'6624154'),
	(22,1,'6624155'),
	(23,1,'6624156'),
	(24,1,'6624157'),
	(25,2,'6624135'),
	(26,3,'6624136'),
	(27,4,'6624137'),
	(28,5,'6624138'),
	(29,6,'6624139'),
	(30,7,'6624140'),
	(31,8,'6624141'),
	(32,9,'6624142'),
	(33,10,'6624158'),
	(34,11,'6624159'),
	(35,12,'6624160'),
	(36,13,'6624162'),
	(37,14,'6624161'),
	(38,15,'6624163'),
	(39,16,'6624164'),
	(40,17,'6624165'),
	(41,18,'6624150'),
	(42,19,'6624151'),
	(43,20,'6624152'),
	(44,21,'6624153'),
	(45,22,'6624154'),
	(46,23,'6624155'),
	(47,24,'6624156'),
	(48,25,'6624157'),
	(49,26,'6624135'),
	(50,27,'6624136'),
	(51,28,'6624137'),
	(52,29,'6624138'),
	(53,30,'6624139'),
	(54,31,'6624140'),
	(55,32,'6624141'),
	(56,33,'6624142'),
	(57,34,'6624158'),
	(58,35,'6624159'),
	(59,36,'6624160'),
	(60,37,'6624162'),
	(61,38,'6624161'),
	(62,39,'6624163'),
	(63,40,'6624164'),
	(64,41,'6624165'),
	(65,42,'6624150'),
	(66,43,'6624151'),
	(67,44,'6624152'),
	(68,45,'6624153'),
	(69,46,'6624154'),
	(70,47,'6624155'),
	(71,48,'6624156'),
	(72,49,'6624157'),
	(73,50,'6624135'),
	(74,51,'6624136'),
	(75,52,'6624137'),
	(76,53,'6624138'),
	(77,54,'6624139'),
	(78,55,'6624140'),
	(79,56,'6624141'),
	(80,57,'6624142'),
	(81,58,'6624158'),
	(82,59,'6624159'),
	(83,60,'6624160'),
	(84,61,'6624162'),
	(85,62,'6624161'),
	(86,63,'6624163'),
	(87,64,'6624164'),
	(88,65,'6624165'),
	(89,66,'6624150'),
	(90,67,'6624151'),
	(91,68,'6624152'),
	(92,69,'6624153'),
	(93,70,'6624154'),
	(94,71,'6624155'),
	(95,72,'6624156'),
	(96,73,'6624157'),
	(97,74,'6624202'),
	(98,74,'6624203'),
	(99,74,'6624204'),
	(100,74,'6624205'),
	(101,74,'6624206'),
	(102,74,'6624207'),
	(103,74,'6624200'),
	(104,74,'6624201'),
	(105,75,'6624202'),
	(106,76,'6624203'),
	(107,77,'6624204'),
	(108,78,'6624205'),
	(109,79,'6624206'),
	(110,80,'6624207'),
	(111,81,'6624200'),
	(112,82,'6624201'),
	(113,83,'6624202'),
	(114,84,'6624203'),
	(115,85,'6624204'),
	(116,86,'6624205'),
	(117,87,'6624206'),
	(118,88,'6624207'),
	(119,89,'6624200'),
	(120,90,'6624201'),
	(121,91,'6624202'),
	(122,92,'6624203'),
	(123,93,'6624204'),
	(124,94,'6624205'),
	(125,95,'6624206'),
	(126,96,'6624207'),
	(127,97,'6624200'),
	(128,98,'6624201'),
	(129,1,'6624168'),
	(130,99,'6624168'),
	(131,100,'6624168'),
	(132,101,'6624168'),
	(133,74,'6624177'),
	(134,74,'6624178'),
	(135,74,'6624179'),
	(136,74,'6624180'),
	(137,74,'6624181'),
	(138,74,'6624182'),
	(139,74,'6624183'),
	(140,74,'6624184'),
	(141,102,'6624177'),
	(142,103,'6624178'),
	(143,104,'6624179'),
	(144,105,'6624180'),
	(145,106,'6624181'),
	(146,107,'6624182'),
	(147,108,'6624183'),
	(148,109,'6624184'),
	(149,110,'6624177'),
	(150,111,'6624178'),
	(151,112,'6624179'),
	(152,113,'6624180'),
	(153,114,'6624181'),
	(154,115,'6624182'),
	(155,116,'6624183'),
	(156,117,'6624184'),
	(157,118,'6624177'),
	(158,119,'6624178'),
	(159,120,'6624179'),
	(160,121,'6624180'),
	(161,122,'6624181'),
	(162,123,'6624182'),
	(163,124,'6624183'),
	(164,125,'6624184');

/*!40000 ALTER TABLE `test_data_map` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
