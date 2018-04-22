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
-- Table structure for table `aluno`
--

DROP TABLE IF EXISTS `aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aluno` (
  `idaluno` int(11) NOT NULL AUTO_INCREMENT,
  `curso_idcurso` int(11) NOT NULL,
  `matricula` varchar(45) NOT NULL,
  `rg` varchar(45) NOT NULL,
  `cpf` varchar(45) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `mae` varchar(45) NOT NULL,
  `naturalidade` varchar(45) DEFAULT NULL,
  `endereco` varchar(45) NOT NULL,
  `telefone` varchar(45) NOT NULL,
  `ano` varchar(45) NOT NULL,
  `periodo` varchar(45) NOT NULL,
  `senha` varchar(200) NOT NULL,
  PRIMARY KEY (`idaluno`),
  UNIQUE KEY `id_UNIQUE` (`idaluno`),
  KEY `fk_aluno_curso1_idx` (`curso_idcurso`),
  CONSTRAINT `fk_aluno_curso1` FOREIGN KEY (`curso_idcurso`) REFERENCES `curso` (`idcurso`) ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aluno`
--

LOCK TABLES `aluno` WRITE;
/*!40000 ALTER TABLE `aluno` DISABLE KEYS */;
/*!40000 ALTER TABLE `aluno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curso`
--

DROP TABLE IF EXISTS `curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curso` (
  `idcurso` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `tipoNivel` enum('GRADUACAO','ESPECIALIZACAO','MESTRADO','DOUTORADO') NOT NULL,
  `area` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idcurso`),
  UNIQUE KEY `idCurso_UNIQUE` (`idcurso`)
) ENGINE=InnoDB AUTO_INCREMENT=186 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso`
--

LOCK TABLES `curso` WRITE;
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcionario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `tipo_funcionario` enum('ADMINISTRADOR','OPERADOR') NOT NULL,
  `cpf` varchar(45) NOT NULL,
  `rg` varchar(45) NOT NULL,
  `naturalidade` varchar(45) DEFAULT NULL,
  `endereco` varchar(45) DEFAULT NULL,
  `telefone` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `usuario` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idFuncionario_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `iditem` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_item` enum('ANAIS','JORNAL','LIVRO','MIDIA','REVISTA','TRABALHOCONCLUSAO') DEFAULT NULL,
  `isbn` varchar(45) DEFAULT NULL,
  `titulo` varchar(45) DEFAULT NULL,
  `tipo_anais` enum('ANAIS','POSTER','RESUMO','NONE') DEFAULT NULL,
  `tipo_midia` enum('CD','DVD','NONE') DEFAULT NULL,
  `tipo_trabalho_conclusao` enum('MONOGRAFIA','DISSERTACAO','TESE','NONE') DEFAULT NULL,
  `autor` varchar(45) DEFAULT NULL,
  `congresso` varchar(45) DEFAULT NULL,
  `ano_publicacao` varchar(45) DEFAULT NULL,
  `local` varchar(45) DEFAULT NULL,
  `editora` varchar(45) DEFAULT NULL,
  `edicao` varchar(45) DEFAULT NULL,
  `numero_pagina` int(11) DEFAULT NULL,
  `area` varchar(45) DEFAULT NULL,
  `tema` varchar(45) DEFAULT NULL,
  `data_gravacao` varchar(45) DEFAULT NULL,
  `orientador` varchar(45) DEFAULT NULL,
  `data` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`iditem`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-21 23:36:53
