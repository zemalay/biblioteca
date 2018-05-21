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
-- Table structure for table `Divida`
--

DROP TABLE IF EXISTS `Divida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Divida` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aluno_id` int(11) NOT NULL,
  `emprestimo_id` int(11) NOT NULL,
  `saldo` float DEFAULT NULL,
  `pago` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Divida_aluno1_idx` (`aluno_id`),
  KEY `fk_Divida_emprestimo1_idx` (`emprestimo_id`),
  CONSTRAINT `fk_Divida_aluno1` FOREIGN KEY (`aluno_id`) REFERENCES `aluno` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Divida_emprestimo1` FOREIGN KEY (`emprestimo_id`) REFERENCES `emprestimo` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Divida`
--

LOCK TABLES `Divida` WRITE;
/*!40000 ALTER TABLE `Divida` DISABLE KEYS */;
/*!40000 ALTER TABLE `Divida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aluno`
--

DROP TABLE IF EXISTS `aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aluno` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `curso_id` int(11) NOT NULL,
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
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_aluno_curso1_idx` (`curso_id`),
  CONSTRAINT `fk_aluno_curso1` FOREIGN KEY (`curso_id`) REFERENCES `curso` (`id`) ON DELETE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aluno`
--

LOCK TABLES `aluno` WRITE;
/*!40000 ALTER TABLE `aluno` DISABLE KEYS */;
INSERT INTO `aluno` VALUES (49,88,'EDI-181001','v8255106','70269755446','cesarina Guterres Do Rego','Maria  Do Rego','Timorense','Peregrino  de Carvalho','96952246','2018','1','asd','cesaiku@gmail.com');
/*!40000 ALTER TABLE `aluno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curso`
--

DROP TABLE IF EXISTS `curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curso` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `tipoNivel` enum('GRADUACAO','ESPECIALIZACAO','MESTRADO','DOUTORADO') NOT NULL,
  `area` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idCurso_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso`
--

LOCK TABLES `curso` WRITE;
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
INSERT INTO `curso` VALUES (83,'Direito','ESPECIALIZACAO','Juridica'),(88,'Forense','ESPECIALIZACAO','Criminologia ');
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emprestimo`
--

DROP TABLE IF EXISTS `emprestimo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `emprestimo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `funcionario_id` int(11) NOT NULL,
  `aluno_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `data_cadastrado` varchar(45) NOT NULL,
  `data_devolucao` varchar(45) NOT NULL,
  `renovacao` int(11) DEFAULT NULL,
  `entregou` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_emprestimo_funcionario1_idx` (`funcionario_id`),
  KEY `fk_emprestimo_aluno1_idx` (`aluno_id`),
  KEY `fk_emprestimo_item1_idx` (`item_id`),
  CONSTRAINT `fk_emprestimo_aluno1` FOREIGN KEY (`aluno_id`) REFERENCES `aluno` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_emprestimo_funcionario1` FOREIGN KEY (`funcionario_id`) REFERENCES `funcionario` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_emprestimo_item1` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emprestimo`
--

LOCK TABLES `emprestimo` WRITE;
/*!40000 ALTER TABLE `emprestimo` DISABLE KEYS */;
INSERT INTO `emprestimo` VALUES (8,32,49,42,'20/05/2018','16/11/2018',5,0);
/*!40000 ALTER TABLE `emprestimo` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` VALUES (30,'Iron Man','ADMINISTRADOR','7777','3635','brasil','NYork','445353','vinhasgeovannio@gmail.com','iron','asd'),(32,'cezi','ADMINISTRADOR','12345','6789','Timorense','Peregrino  de Carvalho','96952246','cezi@gmai.com','cery',''),(33,'Joao','OPERADOR','123','123','brasil','centro','8922','email@email.com','joao','123'),(34,'Fulano','OPERADOR','123','123','brasil','centro','21','email@email.com','fulano','asd');
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
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
  `quantidade` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (35,'JORNAL',NULL,'Titulo Jornal1','NONE','NONE','NONE',NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,'01/02/2013',2),(36,'REVISTA',NULL,'titulo revista','NONE','NONE','NONE',NULL,NULL,NULL,NULL,'III','4',27,NULL,NULL,NULL,NULL,'03/02/2013',6),(38,'MIDIA','','Titulo Midia123','NONE','CD','NONE','','','','','','',0,'','','12/02/2009','','',2),(39,'LIVRO','324HDBSN','Logica da Computacao','NONE','NONE','NONE','Edson','','2013','','Casa Codigo','1',40011,'Tecnologia','','','','',4),(40,'TRABALHOCONCLUSAO','','Como fazer TCC','NONE','NONE','DISSERTACAO','Fulano','','2013','UEPB','','',0,'Tecnologia','IT','','Maria','23/08/2018',3),(41,'REVISTA','','Lafaek','NONE','NONE','NONE','','','2012','','someeditor','1',15,'','','','','',2),(42,'TRABALHOCONCLUSAO','','Delimtação da fronteira maritima','RESUMO','NONE','MONOGRAFIA','Zaqueu Maria','','2017','Joao pessoa','','',0,'relacoes internacionais','Delimtação da fronteira maritima','','Geovannio','15/12/2017',2);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserva`
--

DROP TABLE IF EXISTS `reserva`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reserva` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aluno_idaluno` int(11) NOT NULL,
  `item_iditem` int(11) NOT NULL,
  `data_reservado` varchar(45) NOT NULL,
  `data_pegar` varchar(45) NOT NULL,
  `email` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reserva_aluno1_idx` (`aluno_idaluno`),
  KEY `fk_reserva_item1_idx` (`item_iditem`),
  CONSTRAINT `fk_reserva_aluno1` FOREIGN KEY (`aluno_idaluno`) REFERENCES `aluno` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_reserva_item1` FOREIGN KEY (`item_iditem`) REFERENCES `item` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserva`
--

LOCK TABLES `reserva` WRITE;
/*!40000 ALTER TABLE `reserva` DISABLE KEYS */;
/*!40000 ALTER TABLE `reserva` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `universidade`
--

DROP TABLE IF EXISTS `universidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `universidade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `endereco` varchar(45) NOT NULL,
  `periodo` varchar(45) NOT NULL,
  `data_inicioPeriodo` varchar(45) NOT NULL,
  `data_fimPeriodo` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `universidade`
--

LOCK TABLES `universidade` WRITE;
/*!40000 ALTER TABLE `universidade` DISABLE KEYS */;
INSERT INTO `universidade` VALUES (1,'UEPB','bodocongo','1','17/05/2018','20/05/2018'),(4,'UEPB','campina','1','17/05/2018','22/10/2018');
/*!40000 ALTER TABLE `universidade` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-20 23:58:53
