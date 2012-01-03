CREATE DATABASE  IF NOT EXISTS `test` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `test`;
-- MySQL dump 10.13  Distrib 5.1.40, for Win32 (ia32)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	5.1.53-community

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
-- Table structure for table `scbs_unidadeespecialidade`
--

DROP TABLE IF EXISTS `scbs_unidadeespecialidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scbs_unidadeespecialidade` (
  `CODIGOUNIDADESAUDE` varchar(50) NOT NULL,
  `CODIGOESPECIALIDADE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`CODIGOUNIDADESAUDE`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scbs_unidadeespecialidade`
--

LOCK TABLES `scbs_unidadeespecialidade` WRITE;
/*!40000 ALTER TABLE `scbs_unidadeespecialidade` DISABLE KEYS */;
INSERT INTO `scbs_unidadeespecialidade` VALUES ('1','1');
/*!40000 ALTER TABLE `scbs_unidadeespecialidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scbs_tipodoencasintoma`
--

DROP TABLE IF EXISTS `scbs_tipodoencasintoma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scbs_tipodoencasintoma` (
  `CODIGOTIPODOENCA` varchar(50) NOT NULL,
  `CODIGOSINTOMA` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`CODIGOTIPODOENCA`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scbs_tipodoencasintoma`
--

LOCK TABLES `scbs_tipodoencasintoma` WRITE;
/*!40000 ALTER TABLE `scbs_tipodoencasintoma` DISABLE KEYS */;
INSERT INTO `scbs_tipodoencasintoma` VALUES ('1','1');
/*!40000 ALTER TABLE `scbs_tipodoencasintoma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scbs_queixa`
--

DROP TABLE IF EXISTS `scbs_queixa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scbs_queixa` (
  `ts` varchar(50) DEFAULT NULL,
  `CODIGO` varchar(50) NOT NULL,
  `TIPOQUEIXA` varchar(50) DEFAULT NULL,
  `SOLICITANTE` varchar(50) DEFAULT NULL,
  `DESCRICAO` varchar(50) DEFAULT NULL,
  `OBSERVACAO` varchar(50) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `FUNCIONARIO` varchar(50) DEFAULT NULL,
  `SITUACAO` varchar(50) DEFAULT NULL,
  `dataPARECER` varchar(50) DEFAULT NULL,
  `dataQUEIXA` varchar(50) DEFAULT NULL,
  `ENDERECOSOLICITANTE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`CODIGO`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scbs_queixa`
--

LOCK TABLES `scbs_queixa` WRITE;
/*!40000 ALTER TABLE `scbs_queixa` DISABLE KEYS */;
INSERT INTO `scbs_queixa` VALUES ('1','1','1','Name','This is a test.','solution','email','phil','3','19/8/3906','19/07/2006','1'),('15','10','1','','version 4 food complaint','blah','','default','1','8/8/2006','07/08/2006','19'),('0','11','1','','version 4 complaint 2','','','NULL','1','NULL','07/08/2006','21'),('1','12','1','','update complaint test version 2','update test version 2','','default','3','13/8/2006','13/08/2006','23'),('1','13','2','','complaint update test version 2','update test version 2','','default','3','13/8/2006','13/08/2006','25'),('1','14','1','','new test version 2','update test new version 2','','default','3','13/8/2006','13/08/2006','27'),('1','15','2','','AO version test 1','ao version test 1','','default','3','13/9/2006','13/08/2006','29'),('1','16','1','','ao version 2 test','ao version 2 test','','default','3','13/9/2006','13/09/2006','31'),('1','17','1','','final test version 1','final update test version 1','','default','3','14/8/2006','14/08/2006','33'),('1','18','1','','final test version 2','update final test version 2','','default','3','14/8/2006','14/08/2006','35'),('1','19','2','','final insert test version 3','update final test version 3','','default','3','14/8/2006','14/08/2006','37'),('1','2','1','blah','blah','blah','blah','phil','1','NULL','21/07/2006','3'),('48','20','3','','insert final test version 4','this will now work','','default','3','14/8/2006','14/08/2006','39'),('1','21','1','','last last insert version 4 test','update version 4','','default','1','14/8/2006','14/08/2006','41'),('2','22','2','','final insert test version 5','final update test version 5','','default','3','14/8/2006','14/08/2006','43'),('2','23','3','','final insert test version 6','final update test version 6','','default','3','14/8/2006','14/08/2006','45'),('2','24','1','','final insert test version 7','final update test version 7','','default','3','14/8/2006','14/08/2006','47'),('2','25','2','','final insert test version 8','final update test version 8','','default','3','14/8/2006','14/08/2006','49'),('1','26','1','','final insert test AO version 1','final update test AO version 1','','default','3','15/9/2006','15/09/2006','51'),('1','27','2','','final insert test AO version 2','final update test AO version 2','','default','3','15/9/2006','15/08/2006','53'),('1','28','3','','final insert test AO version 3','update test','','default','3','15/9/2006','15/09/2006','55'),('0','29','1','','final insert test AO version 4','','','NULL','1','NULL','15/09/2006','57'),('1','3','2','blah 2','blah 2','new observation 3','v','phil','3','25/7/2006','21/07/2006','5'),('1','30','1','','final update test AO version 3a','updated','','default','3','15/9/2006','15/09/2006','59'),('1','31','2','','final insert test version 4a','final update test version 4a','','default','3','15/9/2006','15/08/2006','61'),('1','32','3','','final insert test version 5','final update test version 5','','default','3','15/9/2006','15/09/2006','63'),('1','33','1','','final insert test AO version 6','final update test ao version 6','','default','3','15/9/2006','15/09/2006','65'),('1','34','2','','final insert test ao version 7','final update test version 8','','default','3','15/9/2006','15/08/2006','67'),('1','35','3','','final insert test version 8','final update ao test version 8','','default','3','15/9/2006','15/09/2006','69'),('0','36','1','','test','','','NULL','3','NULL','18/09/2006','71'),('0','37','1','','test state complaint','','','NULL','3','NULL','18/09/2006','73'),('1','38','1','','complainy test 38','test','','default','3','18/9/2006','18/09/2006','75'),('1','39','1','','new observer test','observer test','','default','3','18/9/2006','18/09/2006','77'),('2','4','1','Bob Smith','This is a new food complaint','observation for complaint 4','a@b.com','phil','3','30/10/5806','08/08/2006','7'),('4','40','1','','update test version 6 version 2','y','','default','3','18/9/2006','18/09/2006','79'),('4','41','1','','update test 101','test','','default','3','18/9/2006','18/09/2006','81'),('0','42','1','blah','blah','blah','','NULL','1','NULL','06/10/2006','83'),('0','43','1','test','test','test','','NULL','1','NULL','06/10/2006','85'),('1','44','1','A 2008 test','A 2008 test','A 2008 test','A 2008 test','test','3','24/0/2008','24/00/2008','87'),('0','45','1','Ratos','Ratos na Cosinha','','','NULL','1','NULL','26/10/2010','89'),('1','5','1','oo complainer name','oo complaint description','complaint 5 observation','oo complainer email','phil','3','30/7/2006','25/07/2006','9'),('4','6','2','oo animal complainer name','oo animal description','update complaint version 3 test ','oo animal complainer email','default','3','6/8/2006','25/07/2006','11'),('0','7','3','oo special complainer name','oo special description ','oo special observations','oo special complainer email','NULL','1','NULL','25/07/2006','13'),('2','8','1','new name','new food complaint','new food complaint observation','new email','phil','3','30/8/3906','30/07/2006','15'),('1','9','2','','version 3 insert test','version 2 update test','','default','3','6/8/2006','06/08/2006','17');
/*!40000 ALTER TABLE `scbs_queixa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scbs_queixaanimal`
--

DROP TABLE IF EXISTS `scbs_queixaanimal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scbs_queixaanimal` (
  `CODIGO` varchar(50) NOT NULL,
  `QTDEANIMAIS` varchar(50) DEFAULT NULL,
  `DATAINCOMODO` varchar(50) DEFAULT NULL,
  `ANIMAL` varchar(50) DEFAULT NULL,
  `ENDERECOLOCALOCORRENCIA` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`CODIGO`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scbs_queixaanimal`
--

LOCK TABLES `scbs_queixaanimal` WRITE;
/*!40000 ALTER TABLE `scbs_queixaanimal` DISABLE KEYS */;
INSERT INTO `scbs_queixaanimal` VALUES ('13','1','13/9/2006','cat','26'),('15','2','1/1/2006','cat','30'),('19','4','1/1/2006','cat','38'),('22','4','1/1/2006','cats','44'),('25','3','1/1/2006','cats','50'),('27','1','1/1/2006','cat','54'),('3','2','1/1/1980','blah 2','6'),('31','1','1/1/2006','cats','62'),('34','1','1/1/2006','cats','68'),('6','1','25/8/2006','oo animal race','12'),('9','2','3/3/1980','cat','18');
/*!40000 ALTER TABLE `scbs_queixaanimal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scbs_funcionario`
--

DROP TABLE IF EXISTS `scbs_funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scbs_funcionario` (
  `LOGIN` varchar(50) NOT NULL,
  `NOME` varchar(50) DEFAULT NULL,
  `SENHA` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`LOGIN`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scbs_funcionario`
--

LOCK TABLES `scbs_funcionario` WRITE;
/*!40000 ALTER TABLE `scbs_funcionario` DISABLE KEYS */;
INSERT INTO `scbs_funcionario` VALUES ('2008','2008','2008'),('authen','authen','authen'),('default','default version 4','default4'),('joe','Joe Bloggs','bloggs'),('murphy','john','murphy'),('phil','test','test2'),('smith','John Smith','john'),('test','test','test'),('test2','test2','test2'),('version7','version 7','version7');
/*!40000 ALTER TABLE `scbs_funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scbs_sintoma`
--

DROP TABLE IF EXISTS `scbs_sintoma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scbs_sintoma` (
  `CODIGO` varchar(50) NOT NULL,
  `DESCRICAO` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`CODIGO`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scbs_sintoma`
--

LOCK TABLES `scbs_sintoma` WRITE;
/*!40000 ALTER TABLE `scbs_sintoma` DISABLE KEYS */;
INSERT INTO `scbs_sintoma` VALUES ('1','sintoma descricao'),('2','New Symptom');
/*!40000 ALTER TABLE `scbs_sintoma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scbs_queixadiversa`
--

DROP TABLE IF EXISTS `scbs_queixadiversa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scbs_queixadiversa` (
  `CODIGO` varchar(50) NOT NULL,
  `IDADE` varchar(50) DEFAULT NULL,
  `OCUPACAO` varchar(50) DEFAULT NULL,
  `INSTRUCAO` varchar(50) DEFAULT NULL,
  `ENDERECOOCORRENCIA` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`CODIGO`),
  KEY `IDADE` (`IDADE`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scbs_queixadiversa`
--

LOCK TABLES `scbs_queixadiversa` WRITE;
/*!40000 ALTER TABLE `scbs_queixadiversa` DISABLE KEYS */;
INSERT INTO `scbs_queixadiversa` VALUES ('20','26','','','40'),('23','26','','','46'),('28','26','','','56'),('32','26','','','64'),('35','26','','','70'),('7','12','oo special event occupation','oo special event level','14');
/*!40000 ALTER TABLE `scbs_queixadiversa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scbs_especialidade`
--

DROP TABLE IF EXISTS `scbs_especialidade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scbs_especialidade` (
  `CODIGO` varchar(50) NOT NULL,
  `DESCRICAO` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`CODIGO`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scbs_especialidade`
--

LOCK TABLES `scbs_especialidade` WRITE;
/*!40000 ALTER TABLE `scbs_especialidade` DISABLE KEYS */;
INSERT INTO `scbs_especialidade` VALUES ('1','description'),('2','New medical speciality');
/*!40000 ALTER TABLE `scbs_especialidade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scbs_tipodoenca`
--

DROP TABLE IF EXISTS `scbs_tipodoenca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scbs_tipodoenca` (
  `CODIGO` varchar(50) NOT NULL,
  `nome` varchar(50) DEFAULT NULL,
  `DESCRICAO` varchar(50) DEFAULT NULL,
  `manifestacao` varchar(50) DEFAULT NULL,
  `duracao` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`CODIGO`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scbs_tipodoenca`
--

LOCK TABLES `scbs_tipodoenca` WRITE;
/*!40000 ALTER TABLE `scbs_tipodoenca` DISABLE KEYS */;
INSERT INTO `scbs_tipodoenca` VALUES ('1','nome','descricoa','manifestacao','duracao'),('2','New Symptom','New Symptom Description','New Symptom Manifestation','New Symptom Duration');
/*!40000 ALTER TABLE `scbs_tipodoenca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scbs_queixaalimentar`
--

DROP TABLE IF EXISTS `scbs_queixaalimentar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scbs_queixaalimentar` (
  `CODIGO` varchar(50) NOT NULL,
  `QTDECOMENSAIS` varchar(50) DEFAULT NULL,
  `QTDEDOENTES` varchar(50) DEFAULT NULL,
  `QTDEINTERNACOES` varchar(50) DEFAULT NULL,
  `QTDEOBITOS` varchar(50) DEFAULT NULL,
  `LOCALATENDIMENTO` varchar(50) DEFAULT NULL,
  `REFEICAOSUSPEITA` varchar(50) DEFAULT NULL,
  `ENDERECODOENTE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`CODIGO`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scbs_queixaalimentar`
--

LOCK TABLES `scbs_queixaalimentar` WRITE;
/*!40000 ALTER TABLE `scbs_queixaalimentar` DISABLE KEYS */;
INSERT INTO `scbs_queixaalimentar` VALUES ('1','1','1','1','1','hospital','pizza','1'),('10','1','2','3','4','','','20'),('11','1','1','1','1','','','22'),('12','1','2','3','4','','','24'),('14','1','2','3','4','','','28'),('16','1','2','3','4','','','32'),('17','1','2','3','4','','','34'),('18','1','2','3','4','','','36'),('2','2','2','2','2','blah','blah','4'),('21','1','2','3','4','','','42'),('24','1','2','3','4','','','48'),('26','1','2','3','4','','','52'),('29','1','2','3','4','','','58'),('30','1','2','3','4','','','60'),('33','1','2','3','4','','','66'),('36','1','2','3','4','','','72'),('37','1','2','3','4','','','74'),('38','1','2','3','4','','','76'),('39','1','23','3','4','','','78'),('4','1','1','0','0','Lancaster Infirmary','McDondalds','8'),('40','1','2','3','4','','','80'),('41','1','2','3','4','','','82'),('42','1','2','3','4','','','84'),('43','99','99','99','99','','','86'),('44','1','1','1','1','A 2008 test','A 2008 test','88'),('45','3','2','1','0','1','hamburger','90'),('5','1','2','3','4','oo medical centre','oo meal','10'),('8','1','10','10','10','new centre','new meal','16');
/*!40000 ALTER TABLE `scbs_queixaalimentar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scbs_endereco`
--

DROP TABLE IF EXISTS `scbs_endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scbs_endereco` (
  `codigo` varchar(50) NOT NULL,
  `rua` varchar(50) DEFAULT NULL,
  `Complemento` varchar(50) DEFAULT NULL,
  `Cep` varchar(50) DEFAULT NULL,
  `Uf` varchar(50) DEFAULT NULL,
  `Fone` varchar(50) DEFAULT NULL,
  `Cidade` varchar(50) DEFAULT NULL,
  `Bairro` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `codigo` (`codigo`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scbs_endereco`
--

LOCK TABLES `scbs_endereco` WRITE;
/*!40000 ALTER TABLE `scbs_endereco` DISABLE KEYS */;
INSERT INTO `scbs_endereco` VALUES ('1','address','complement','zip','state','phone number','city','province'),('10','oo victim adress','oo victim complement','null','oo victim state','oo victim telephone','oo victim city','oo victim province'),('11','oo animal complainer address','oo animal complainer complement','null','oo animal complainer state','oo animal complainer phone','oo animal complainer city','oo animal complainer province'),('12','oo animal event address','oo animal event complement','null','oo animal event state','oo animal event phone','oo animal event city','oo animal event province'),('13','oo special complainer address','oo special complainer complement','null','oo special complainer state','oo special complainer phone','oo special complainer city','oo special complainer province'),('14','oo special event address','oo special event complement','null','oo special event state','oo special event phone','oo special event city','oo special event provicne'),('15','new address','new complement','null','new state','new phone number','new city','new province'),('16','new address','new complement','null','new state ','new phone','new closest city','new province'),('17','','','null','','','',''),('18','','','null','','','',''),('19','','','null','','','',''),('2','address','complement','zip','state','telephone','city','province'),('20','','','null','','','',''),('21','','','null','','','',''),('22','','','null','','','',''),('23','','','null','','','',''),('24','','','null','','','',''),('25','','','null','','','',''),('26','','','null','','','',''),('27','','','null','','','',''),('28','','','null','','','',''),('29','','','','','','',''),('3','blah','blah','blah','blah','blah','blah','blah'),('30','','','','','','',''),('31','','','','','','',''),('32','','','','','','',''),('33','','','null','','','',''),('34','','','null','','','',''),('35','','','null','','','',''),('36','','','null','','','',''),('37','','','null','','','',''),('38','','','null','','','',''),('39','','','null','','','',''),('4','blah','blah','blah','blah','v','blah','blah'),('40','','','null','','','',''),('41','','','null','','','',''),('42','','','null','','','',''),('43','','','null','','','',''),('44','','','null','','','',''),('45','','','null','','','',''),('46','','','null','','','',''),('47','','','null','','','',''),('48','','','null','','','',''),('49','','','null','','','',''),('5','blah 2','blah 2','blah 2','blah 2','blah 2','blah 2','blah 2'),('50','','','null','','','',''),('51','','','','','','',''),('52','','','','','','',''),('53','','','','','','',''),('54','','','','','','',''),('55','','','','','','',''),('56','','','','','','',''),('57','','','','','','',''),('58','','','','','','',''),('59','','','','','','',''),('6','blah 2','blah 2','blah 2','blah 2','blah 2','blah 2','blah 2'),('60','','','','','','',''),('61','','','','','','',''),('62','','','','','','',''),('63','','','','','','',''),('64','','','','','','',''),('65','','','','','','',''),('66','','','','','','',''),('67','','','','','','',''),('68','','','','','','',''),('69','','','','','','',''),('7','20 High Street','dunno','LA1 3QQ','','01524 343412','lancaster','lancashire'),('70','','','','','','',''),('71','','','','','','',''),('72','','','','','','',''),('73','','','','','','',''),('74','','','','','','',''),('75','','','','','','',''),('76','','','','','','',''),('77','','','','','','',''),('78','','','','','','',''),('79','','','','','','',''),('8','21 High Street','dunno','LA1 3QQ','','01524 6454564','Lancaster','Lancashire'),('80','','','','','','',''),('81','','','','','','',''),('82','','','','','','',''),('83','BLAh','','null','','','',''),('84','','','null','','','',''),('85','','','null','','','',''),('86','','','null','','','',''),('87','A 2008 test','A 2008 test','null','A 2008 test','A 2008 test','A 2008 test','A 2008 test'),('88','A 2008 test','A 2008 test','null','A 2008 test','A 2008 test','A 2008 test','A 2008 test'),('89','asd','','null','','','',''),('9','oo complainer address','oo complainer complement','null','oo complainer state','oo complainer phone number','oo complainer city','oo complainer province'),('90','renatoooo','renatoooo','null','adsf','1230as130','renatooo','renatooo');
/*!40000 ALTER TABLE `scbs_endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `scbs_unidadesaude`
--

DROP TABLE IF EXISTS `scbs_unidadesaude`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `scbs_unidadesaude` (
  `CODIGO` varchar(50) NOT NULL,
  `DESCRICAO` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`CODIGO`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `scbs_unidadesaude`
--

LOCK TABLES `scbs_unidadesaude` WRITE;
/*!40000 ALTER TABLE `scbs_unidadesaude` DISABLE KEYS */;
INSERT INTO `scbs_unidadesaude` VALUES ('1','health unit version 7ab'),('2','New Health Unit');
/*!40000 ALTER TABLE `scbs_unidadesaude` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2010-12-10 16:50:21
