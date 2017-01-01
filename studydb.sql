-- MySQL dump 10.13  Distrib 5.6.35, for Linux (x86_64)
--
-- Host: localhost    Database: studydb
-- ------------------------------------------------------
-- Server version	5.6.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `MEMBERS`
--

DROP TABLE IF EXISTS `MEMBERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MEMBERS` (
  `MNO` int(11) NOT NULL AUTO_INCREMENT COMMENT 'user number',
  `EMAIL` varchar(40) NOT NULL COMMENT 'email',
  `PWD` varchar(100) NOT NULL COMMENT 'password',
  `MNAME` varchar(50) NOT NULL COMMENT 'user name',
  `CRE_DATE` datetime NOT NULL COMMENT 'register date',
  `MOD_DATE` datetime NOT NULL COMMENT 'last mod time of passwd',
  PRIMARY KEY (`MNO`),
  UNIQUE KEY `uix_members` (`EMAIL`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MEMBERS`
--

LOCK TABLES `MEMBERS` WRITE;
/*!40000 ALTER TABLE `MEMBERS` DISABLE KEYS */;
INSERT INTO `MEMBERS` VALUES (3,'dhsim86@gmail.com','1234','Dongho Sim','2016-12-27 23:50:48','2016-12-27 23:50:48'),(8,'dhsim86@gmail.com2','1234','심동호2','2016-12-31 23:07:24','2016-12-31 23:07:24');
/*!40000 ALTER TABLE `MEMBERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `board` (
  `no` int(11) NOT NULL AUTO_INCREMENT COMMENT 'post number',
  `mod_date` datetime NOT NULL COMMENT 'last modification time',
  `user_no` int(11) NOT NULL COMMENT 'user number',
  `contents` text COMMENT 'post contents',
  `title` varchar(100) NOT NULL,
  PRIMARY KEY (`no`),
  KEY `user_no` (`user_no`),
  CONSTRAINT `board_ibfk_1` FOREIGN KEY (`user_no`) REFERENCES `users` (`no`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (12,'2017-01-01 22:40:13',31,' 안녕하세요.   22','hi'),(13,'2017-01-01 22:44:44',31,'hi. my name is dongho sim','hello'),(14,'2017-01-01 22:45:45',31,'  11','test00'),(15,'2017-01-01 22:45:51',31,'  ','test01'),(16,'2017-01-01 22:45:55',31,'  ','test02'),(17,'2017-01-01 22:45:59',31,'  ','test03'),(18,'2017-01-01 22:46:02',31,'  ','test04'),(19,'2017-01-01 22:46:07',31,'  ','test05'),(20,'2017-01-01 22:46:11',31,'  ','test06');
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test01`
--

DROP TABLE IF EXISTS `test01`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test01` (
  `tno` int(11) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test01`
--

LOCK TABLES `test01` WRITE;
/*!40000 ALTER TABLE `test01` DISABLE KEYS */;
/*!40000 ALTER TABLE `test01` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `no` int(11) NOT NULL AUTO_INCREMENT COMMENT 'user number',
  `email` varchar(40) NOT NULL COMMENT 'email',
  `pwd` varchar(100) NOT NULL COMMENT 'password',
  `name` varchar(50) NOT NULL COMMENT 'user name',
  `cre_date` datetime NOT NULL COMMENT 'register date',
  `mod_date` datetime NOT NULL COMMENT 'last modification time',
  PRIMARY KEY (`no`),
  UNIQUE KEY `uix_members` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (31,'dhsim86@gmail.com','1234','심동호','2017-01-01 22:39:22','2017-01-01 22:39:22');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-01-01 22:56:19
