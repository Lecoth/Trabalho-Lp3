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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `arma`
--

LOCK TABLES `arma` WRITE;
/*!40000 ALTER TABLE `arma` DISABLE KEYS */;
INSERT INTO `arma` VALUES (1,'Skyward Blade','Espada',5,608,'Recarga de Energia 55,1%','Taxa crit + 4%. Ganha poder perfurante ao usar burst: aumenta a velocidade de movimento em 10%, aumenta a velocidade de ataque em 10% e aumenta o dano de ataques normais e carregados em 20% por 12s.','images/armas/skywardblade.png',NULL),(2,'Mistsplitter Reforged','Espada',5,674,'Crit DMG 44.1%','Recebe um bônus de 12% de Dano Elemental para o tipo elemental do personagem. Com 1/2/3 acúmulos, o Dano Elemental é aumentado em 8/16/28%. Cada acúmulo tem uma duração individual e é obtido através de : Ataques Normais com Dano Elemental(dura 5s); Conjurar uma Burst(dura 10s); Ter menos de 100% de Energia(desaparece ao atingir Energia máxima).','images/armas/mistsplitter.png',NULL),(3,'Splendor of Tranquil Waters','Espada',5,542,'CRIT DMG 88.2%','Quando o HP do personagem equipado  aumenta um diminui, o Dano da Habilidade Elemental causado será aumentado em 8% por 6s. Máximo de 3 acúmulos. Este efeito pode ser ativado uma vez a cada 0,2s. Quando o HP dos membros da equipe aumenta ou diminui, o HP máximo do personagem que equipa a arma aumenta em 14% por 6s. Máx. de 2 acúmulos. Esse efeito pode ser acionado uma vez a cada 0,2s. Os efeitos mencionados podem ser ativados mesmo com o personagem fora de campo.','images/armas/splendor.png',NULL),(4,'Favonius Sword','Espada',4,454,'Recarga de Energia 61,3%','Acertos CRITs tem uma chance de 60% de generar uma pequena quantidade de Partículas Elementais, que vão regenerar 6 de Energia para o personagem. Só pode acontecer uma vez a cada 12s.','images/armas/favonius.png',NULL),(5,'Haran Geppaku Futsu','Espada',5,608,'CRIT Rate 33.1%','Obtém 12% de DMG bônus para todos os Elementos. Quando outros membros da equipe usam Habilidades Elementais, o personagem equipando a arma vai ganhar 1 acúmulo de Onda Espinhosa. Máximo de 2 acúmulos. Este efeito pode ser ativado uma vez a cada 0,3s. Quando o personagem equipado usar uma Habilidade Elemental, todos os acúmulos serão consumidos para obter Turbulência Ondulante: cada acúmulo de Onda Espinhosa consumido aumentará o dano do Ataque Normal em 20% or 8s.','images/armas/haran.png',NULL),(6,'Aqua Simulacra','Arco',5,542,'CRIT DMG 88.2%','Vida é aumentada em 16%. Quando tem oponentes perto, o DMG dado pelo personagem equipado é aumentado em 20%. Isso vai acontecer mesmo se o personagem equipado estiver fora de campo.','images/armas/aqua.png',NULL),(7,'The Stringless','Arco',4,510,'Maestria Elemental 165','Aumenta o Dano da Habilidade Elemental e da Burst em 24%','images/armas/stringless.png',NULL),(8,'A Thousand Floating Dreams','Catalisador',5,542,'Maestria Elemental 265','Personagem equipado ganha buffs baseado nos tipos elementais dos outros membros do time. Se se tipo elemental é igual ao do personagem equipado, Maestria Elemental é aumentada em 32. Se não, o personagem equipado elemental DMG bônus é aumentado em 10%. Cada efeito pode acumular em no máximo 3. Todos os membros próximos ganham mais 40 de Maestria Elemental.','images/armas/dreams.png',NULL),(9,'Sacrificial Fragments','Catalisador',4,454,'Maestria Elemental 221','Após causar dano em um oponente com uma Habilidade Elemental, a habilidade tem 40% de chance de acabar o cooldown dela. Só pode acontecer uma vez a cada 30s.','images/armas/sacrificialcatalist.png',NULL),(10,'Redhorn Stonethresher','Espadão',5,542,'CRIT DMG 88.2%','DEF é aumentada em 28%. Ataques Normais e Carregados são aumentados em 40% da DEF.','images/armas/redhorn.png',NULL),(11,'Whiteblind','Espadão',4,510,'DEF 51.7%','Ao acertar, ataques Normais e Carregados aumentam ATK e DEF em 6% por 6s. Máximo de 4 acúmulos. Esse efeito só pode ocorrer uma vez a cada 0.5s.','images/armas/whiteblind.png',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artefato`
--

LOCK TABLES `artefato` WRITE;
/*!40000 ALTER TABLE `artefato` DISABLE KEYS */;
INSERT INTO `artefato` VALUES (1,'Noblesse Oblige',NULL,5,'Dano da burst +20%','Usar a burst aumenta o atk de todos os membros da equipe em 20% por 12s. Este efeito não acumula.','images/artefatos/noblesse.png'),(2,'Scroll of the Hero of Cinder City',NULL,5,'Quando um membro do time aciona uma Burst de Nightsoul, o personagem equipado regenera 6 de Energia Elemental.','Depois do personagem equipado acionar uma reação, todos os membros próximos ganham 12% de Dano Elemental Bônus para os tipos envolvidos. Se o personagem equipado está no estado de Benção de Nightsoul quando acionar esse efeito, todos os membros da equipe ganham bônus de 28% de Dano Elemental adicional para os elementos envolvidos na reação por 20s.','images/artefatos/scroll.png'),(3,'Golden Troupe',NULL,5,'Dano da Habilidade Elemental +20%','Aumenta a Habilidade Elemental +25%. Quando o personagem equipado está fora de campo, Habilidade Elemental é aumentada em mais 25%. Esse efeito se desativa 2s após ele entrar em campo.','images/artefatos/golden.png'),(4,'Obsidian Codex',NULL,5,'Enquanto o personagem equipado está na Benção de Nightsoul e em campo, o DMG causado por eles aumentam em 15%.','Depois do personagem equipado consumir 1 ponto Nightsoul enquanto em campo, CRIT Rate é aumentado em 40% por 6s. Esse efeito pode ser ativado a cada segundo.','images/artefatos/obsidian.png'),(5,'Husk of Opulent Dreams',NULL,5,'DEF +30%','O personagem equipado vai obter o efeito Curiosidade nas condições: quando em campo, o personagem ganha 1 acúmulo depois de atingir um oponente com um ataque Geo, podendo ser atividado uma vez a cada 0.3s; Quando fora de campo, o personagem ganha um 1 acúmulo a cada 3s. Curiosidade pode ser acumulada até 4, com cada dando 6% DEF e 6% Geo DMG Bônus. Quando passam 6s sem ganhar Curiosidade, 1 acúmulo é perdido.','images/artefatos/opulent.png'),(6,'Deepwood Memories',NULL,5,'Dendro DMG bônus +15%','Depois que Habilidade Elemental ou Burst acertam um oponente, a RES Dendro do alvo diminui em 30% por 8s. Esse efeito pode ser ativado mesmo se o personagem equipado não estiver em campo.','images/artefatos/deepwood.png'),(7,'Silken Moon\'s Serenade',NULL,5,'Recarga de Energia +20%','Quando dando Dano Elemental, ganha o efeito Lua Brilhante: efeito de Devoção por 8s: aumenta a Maestria Elemental em 60/120 quando Moonsign is Nascent Gleam/Ascendant Gleam. O personagem equipado pode acionar esse efeito fora de campo. Todos os membros da equipe tem a Reação Lunar DMG aumentada por 10% de cada Gleaming Moon efeito que os membros tem. Efeitos da Gleaming Moon não podem acumular.','images/artefatos/silken.png'),(8,'Gilded Dreams',NULL,5,'Maestria Elemental +80','Dentro de 8s após ativar uma Reação Elemental, o personagem equipado receberá um bônus baseados no tipo elemental dos outros membros do time. O ATK aumenta em 14% para cada membro cujo Tipo Elemental seja o mesmo que o personagem que equipa, e a Maestria Elemental aumenta em 50 para cada membro com um tipo elemental diferente. Cada um dos bônus pode afetar até 3 personagens. Este efeito pode ser ativado uma vez a cada 8s, mesmo quando o personagem não estiver em campo.','images/artefatos/gilded.png'),(9,'Viridescent Venerer',NULL,5,'Anemo DMG Bônus +15%','Aumenta o Redemoinho DMG em 60%. Reduz a RES elemental do oponente ao elemento infudido no Redemoinho em 40% por 10s.','images/artefatos/viridescent.png');
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `build_guia`
--

LOCK TABLES `build_guia` WRITE;
/*!40000 ALTER TABLE `build_guia` DISABLE KEYS */;
INSERT INTO `build_guia` VALUES (1,1,1,'Tem um atk base alto e o substatus de energia deixa a burst do Bennett, que é o talento mais importante dele, acontecer mais facilmente. Melhorando o buff e cura continua do time.',1,'Recarga de Energia%','HP%','Bônus de cura%','Energia, HP','220% de recarga de energia'),(2,2,2,'Como um unidade orientada a dano, essa arma é a melhor para aumentar o dano pessoal.',2,'ATK% ou Recarga de Energia%','Bônus Pyro%','CRIT Rate&','Recarga de Energia, CRIT Rate, CRIT DMG, ATK%','220-235% de Recarga de Energia'),(3,3,3,'É a arma assinatura da Furina e balancea buffs com o aumento e diminuição da vida, que acontece na utilização da Habilidade Elemental da Furina.',3,'Recarga de Energia% ou HP%','HP%','CRIT Rate%','Recarga de Energia%, HP%, CRIT Rate, CRIT DMG','200% de Recarga de Energia; 40k de HP');
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `build_usuario`
--

LOCK TABLES `build_usuario` WRITE;
/*!40000 ALTER TABLE `build_usuario` DISABLE KEYS */;
INSERT INTO `build_usuario` VALUES (1,19,3,4,3,'Furina Support',0,'HP%','HP%','Dano Crítico',NULL,'2025-11-08 21:37:19');
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personagem`
--

LOCK TABLES `personagem` WRITE;
/*!40000 ALTER TABLE `personagem` DISABLE KEYS */;
INSERT INTO `personagem` VALUES (1,'Bennett','Pyro','Espada',4,'Um dos melhores buffer de ATK no jogo, dando também cura e recarga de​\n​energia rapidamente a um custo baixo. Sua energia recarrega rápido, fazendo sua​\n​burst ser realizada repetidamente. Aplica pyro razoavelmente. Priorizar evoluir a​ burst dele.','images/personagens/bennett.png'),(2,'Viajante','Pyro','Espada',5,'Aplica Pyro off-field e fornece buffs através da C1 (que pode ser obtida através das missões). Na C6 pode ser usado para a função de DPS. ','images/personagens/viajante.png'),(3,'Furina','Hydro','Espada',5,'Uma buffer completa, que supera muitas graças a sua capacidade de aumentar qualquer tipo de dano que os aliados podem causar. Podendo também ser uma Sub-DPS sem sacrificar suas capacidades de apoio.','images/personagens/furina.png'),(4,'Kaeya','Cryo','Espada',4,'Os cooldowns do Kaeya são curtos e a Recarga de Energia dele tem baixo custo, sendo capaz de ter uma boa aplicação de Cryo off-field melhorando sua transição em equipes.','images/personagens/kaeya.png'),(5,'Fischl','Electro','Arco',4,'O Oz ataca rapidamente, tendo assim uma alta aplicação electro. Usando sua Burst reseta o cooldown da Habilidade Elemenltal dela, tendo assim 100% de uptime no Oz com a Recarga de Energia ideal. Sendo mais importante elevar sua Habilidade Elemental.','images/personagens/fischl.png'),(6,'Sucrose','Anemo','Catalisador',4,'Por usar catalisador pode aplicar anemo facilmente facilitando o redemoinho, podendo aumentar a Maestria Elemental usando suas habilidades, dando prioridade na sua Habilidade Elemental.','images/personagens/sucrose.png'),(7,'Noelle','Geo','Espadão',4,'Aumentar a DEF dela aumenta o dano, o escudo e a cura dela ao mesmo tempo, tendo AoE ataques no ataque Carregado, cura todos os membros da equipe. Quando o personagem ativo no campo fica com a vida abaixo de 30% cria um escudo automaticamente.','images/personagens/noelle.png'),(8,'Lauma','Dendro','Catalisador',5,'Bom off-field aplicação Dendro, Dendro e Hydro RES reduzida e também habilita a reação Lunar-Bloom, que é basicamente um ataque em área nuke. Provide bons buffs, precisando de bastante de Maestria Elemental que aumenta o dano das reações lunares, focando em sua Habilidade Elemental.','images/personagens/lauma.png');
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
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'lele','lele@gmail.com','senha',NULL,1),(3,'giselle','kupac@ufrrj.br','1234',NULL,0),(19,'leticia','le23@gmail.com','123',NULL,0);
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

-- Dump completed on 2025-11-11 22:11:51
