/*
SQLyog Community v13.1.1 (64 bit)
MySQL - 5.5.60-MariaDB : Database - testdata
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`testdata` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `testdata`;

/*Table structure for table `lease` */

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
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

/*Table structure for table `preq_execution_history` */

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
) ENGINE=MyISAM AUTO_INCREMENT=55 DEFAULT CHARSET=latin1;

/*Table structure for table `prequisite_data` */

DROP TABLE IF EXISTS `prequisite_data`;

CREATE TABLE `prequisite_data` (
  `preq_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data_id` bigint(20) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`preq_id`),
  UNIQUE KEY `UK8436jd2o9se74cuxgino7h3qv` (`type`,`data_id`)
) ENGINE=MyISAM AUTO_INCREMENT=61 DEFAULT CHARSET=latin1;

/*Table structure for table `property` */

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
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Table structure for table `rpr` */

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
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKk3f4v2x8ib68ynof0w608cv7v` (`space_name`,`charge_type`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

/*Table structure for table `space` */

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
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_fmlkog2odr5stpenaumhxbge0` (`space_name`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

/*Table structure for table `test_data_map` */

DROP TABLE IF EXISTS `test_data_map`;

CREATE TABLE `test_data_map` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `preq_id` bigint(20) DEFAULT NULL,
  `test_case_id` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6ndvuemrdieapfdwfosdp9k1s` (`preq_id`,`test_case_id`)
) ENGINE=MyISAM AUTO_INCREMENT=139 DEFAULT CHARSET=latin1;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
