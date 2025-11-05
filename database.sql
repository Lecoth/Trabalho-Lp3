-- MySQL dump 10.13  Distrib 8.0.43, for Linux (x86_64)
--
-- Host: localhost    Database: genshin_db
-- ------------------------------------------------------
-- Server version	8.0.43-0ubuntu0.24.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `arma`
--

DROP TABLE IF EXISTS `arma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `arma` (
  `id_arma` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `tipo_arma` varchar(50) DEFAULT NULL,
  `estrelas` int DEFAULT NULL,
  `base_atk` int DEFAULT NULL,
  `sub_status` varchar(50) DEFAULT NULL,
  `efeito` text,
  `imagem` varchar(255) DEFAULT NULL,
  `origem` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_arma`),
  CONSTRAINT `arma_chk_1` CHECK ((`estrelas` between 1 and 5))
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `arma`
--

LOCK TABLES `arma` WRITE;
/*!40000 ALTER TABLE `arma` DISABLE KEYS */;
INSERT INTO `arma` VALUES (1,'Skyward Blade','Espada',5,608,'Recarga de Energia 55,1%','Taxa crit + 4%. Ganha poder perfurante ao usar burst: aumenta a velocidade de movimento em 10%, aumenta a velocidade de ataque em 10% e aumenta o dano de ataques normais e carregados em 20% por 12s.','images/armas/skywardblade.png',NULL);
/*!40000 ALTER TABLE `arma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `artefato`
--

DROP TABLE IF EXISTS `artefato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `artefato` (
  `id_artefato` int NOT NULL AUTO_INCREMENT,
  `nome_set` varchar(100) NOT NULL,
  `tipo_artefato` varchar(50) DEFAULT NULL,
  `estrelas` int DEFAULT NULL,
  `efeito_2pecas` text,
  `efeito_4pecas` text,
  `imagem` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_artefato`),
  CONSTRAINT `artefato_chk_1` CHECK ((`estrelas` between 1 and 5))
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artefato`
--

LOCK TABLES `artefato` WRITE;
/*!40000 ALTER TABLE `artefato` DISABLE KEYS */;
INSERT INTO `artefato` VALUES (1,'Noblesse Oblige',NULL,5,'Dano da burst +20%','Usar a burst aumenta o atk de todos os membros da equipe em 20% por 12s. Este efeito não acumula.','images/artefatos/noblesse.png');
/*!40000 ALTER TABLE `artefato` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `build_guia`
--

DROP TABLE IF EXISTS `build_guia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `build_guia` (
  `id_build_guia` int NOT NULL AUTO_INCREMENT,
  `id_personagem` int NOT NULL,
  `id_arma_ideal` int DEFAULT NULL,
  `pq_arma_ideal` text,
  `id_art_set` int DEFAULT NULL,
  `main_sands` varchar(50) DEFAULT NULL,
  `main_goblet` varchar(50) DEFAULT NULL,
  `main_circlet` varchar(50) DEFAULT NULL,
  `substats` text,
  `ideal_status` text,
  PRIMARY KEY (`id_build_guia`),
  KEY `id_personagem` (`id_personagem`),
  KEY `id_arma_ideal` (`id_arma_ideal`),
  KEY `id_art_set` (`id_art_set`),
  CONSTRAINT `build_guia_ibfk_1` FOREIGN KEY (`id_personagem`) REFERENCES `personagem` (`id_personagem`),
  CONSTRAINT `build_guia_ibfk_2` FOREIGN KEY (`id_arma_ideal`) REFERENCES `arma` (`id_arma`),
  CONSTRAINT `build_guia_ibfk_3` FOREIGN KEY (`id_art_set`) REFERENCES `artefato` (`id_artefato`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `build_guia`
--

LOCK TABLES `build_guia` WRITE;
/*!40000 ALTER TABLE `build_guia` DISABLE KEYS */;
INSERT INTO `build_guia` VALUES (1,1,1,'Tem um atk base alto e o substatus de energia deixa a burst do Bennett, que é o talento mais importante dele, acontecer mais facilmente. Melhorando o buff e cura continua do time.',1,'Recarga de Energia%','HP%','Bônus de cura%','Energia, HP','220% de recarga de energia');
/*!40000 ALTER TABLE `build_guia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `build_usuario`
--

DROP TABLE IF EXISTS `build_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `build_usuario` (
  `id_build_user` int NOT NULL AUTO_INCREMENT,
  `id_usuario` int NOT NULL,
  `id_personagem` int NOT NULL,
  `id_arma_usada` int DEFAULT NULL,
  `id_art_set` int DEFAULT NULL,
  `nome_build` varchar(100) DEFAULT NULL,
  `privada` tinyint(1) DEFAULT '0',
  `sands_main` varchar(50) DEFAULT NULL,
  `goblet_main` varchar(50) DEFAULT NULL,
  `circlet_main` varchar(50) DEFAULT NULL,
  `descricao` text,
  `data_criacao` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_build_user`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_personagem` (`id_personagem`),
  KEY `id_arma_usada` (`id_arma_usada`),
  KEY `id_art_set` (`id_art_set`),
  CONSTRAINT `build_usuario_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`idUser`),
  CONSTRAINT `build_usuario_ibfk_2` FOREIGN KEY (`id_personagem`) REFERENCES `personagem` (`id_personagem`),
  CONSTRAINT `build_usuario_ibfk_3` FOREIGN KEY (`id_arma_usada`) REFERENCES `arma` (`id_arma`),
  CONSTRAINT `build_usuario_ibfk_4` FOREIGN KEY (`id_art_set`) REFERENCES `artefato` (`id_artefato`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `build_usuario`
--

LOCK TABLES `build_usuario` WRITE;
/*!40000 ALTER TABLE `build_usuario` DISABLE KEYS */;
/*!40000 ALTER TABLE `build_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personagem`
--

DROP TABLE IF EXISTS `personagem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personagem` (
  `id_personagem` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `elemento` varchar(50) NOT NULL,
  `tipo_arma` varchar(50) DEFAULT NULL,
  `estrelas` int DEFAULT NULL,
  `talentos` text,
  `imagem` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_personagem`),
  CONSTRAINT `personagem_chk_1` CHECK ((`estrelas` between 4 and 5))
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personagem`
--

LOCK TABLES `personagem` WRITE;
/*!40000 ALTER TABLE `personagem` DISABLE KEYS */;
INSERT INTO `personagem` VALUES (1,'Bennett','Pyro','Espada',4,'Um dos melhores buffer de ATK no jogo, dando também cura e recarga de​\n​energia rapidamente a um custo baixo. Sua energia recarrega rápido, fazendo sua​\n​burst ser realizada repetidamente. Aplica pyro razoavelmente. Priorizar evoluir a​ burst dele.','images/personagens/bennett.png');
/*!40000 ALTER TABLE `personagem` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `idUser` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `senha` varchar(100) DEFAULT NULL,
  `id_genshin` varchar(50) DEFAULT NULL,
  `is_admin` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`idUser`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'leticia','leticia@gmail.com','231610',NULL,0),(3,'lele','lele@gmail.com','senha',NULL,0),(5,'lele','lele23@gmail.com','teste',NULL,0);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'genshin_db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-04 23:04:45
