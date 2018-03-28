-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: biblioteca
-- ------------------------------------------------------
-- Server version	5.7.21

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
-- Table structure for table `anaiscongresso`
--

DROP TABLE IF EXISTS `anaiscongresso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `anaiscongresso` (
  `idanais` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(100) NOT NULL,
  `tipo` enum('ARTIGO','POSTER','RESUMO') NOT NULL,
  `congresso` varchar(45) NOT NULL,
  `anoPublicacao` int(11) NOT NULL,
  `local` varchar(45) NOT NULL,
  PRIMARY KEY (`idanais`),
  UNIQUE KEY `id_UNIQUE` (`idanais`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anaiscongresso`
--

LOCK TABLES `anaiscongresso` WRITE;
/*!40000 ALTER TABLE `anaiscongresso` DISABLE KEYS */;
INSERT INTO `anaiscongresso` VALUES (1,'Artigo1','ARTIGO','ENEC',2009,'UEPB'),(2,'Poster1','POSTER','ENEC',2003,'UNTL'),(3,'Poster1','POSTER','ENEC',2003,'UEPB'),(4,'Android','POSTER','BrazilJs',2001,'UFC'),(5,'iOS','ARTIGO','Campus Party',2001,'UFRN'),(6,'Windows','ARTIGO','Campus Party',2001,'UFRN'),(7,'IOT para Saude','ARTIGO','Campus Party',2001,'UFRN'),(8,'IOT','ARTIGO','Campus Party',2001,'UFRN');
/*!40000 ALTER TABLE `anaiscongresso` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-28 10:59:25
