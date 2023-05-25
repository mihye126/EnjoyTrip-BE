-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: enjoytrip
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `notice`
--

DROP TABLE IF EXISTS `notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notice` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(30) NOT NULL,
  `content` varchar(1000) NOT NULL,
  `writeDate` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notice`
--

LOCK TABLES `notice` WRITE;
/*!40000 ALTER TABLE `notice` DISABLE KEYS */;
INSERT INTO `notice` VALUES (1,'게시판테스트1','게시판테스트1','2023-05-23 13:23:41'),(2,'게시판테스트1','게시판테스트1','2023-05-23 13:23:42'),(3,'게시판테스트2','게시판테스트2','2023-05-23 13:23:48'),(4,'게시판테스트3','게시판테스트2','2023-05-23 13:23:48'),(5,'게시판테스트4','게시판테스트2','2023-05-23 13:23:48'),(6,'게시판테스트5','게시판테스트2','2023-05-23 13:23:48'),(7,'게시판테스트6','게시판테스트2','2023-05-23 13:23:48'),(8,'게시판테스트7','게시판테스트2','2023-05-23 13:23:48'),(9,'게시판테스트8','게시판테스트2','2023-05-23 13:23:48'),(10,'게시판테스트9','게시판테스트2','2023-05-23 13:23:48'),(11,'게시판테스트10','게시판테스트2','2023-05-23 13:23:48'),(12,'게시판테스트11','게시판테스트2','2023-05-23 13:23:48'),(13,'게시판테스트12','게시판테스트2','2023-05-23 13:23:48'),(14,'게시판테스트13','게시판테스트2','2023-05-23 13:23:48'),(15,'게시판테스트14','게시판테스트2','2023-05-23 13:23:48'),(16,'게시판테스트15','게시판테스트2','2023-05-23 13:23:48'),(17,'게시판테스트16','게시판테스트2','2023-05-23 13:23:48'),(18,'게시판테스트17','게시판테스트2','2023-05-23 13:23:48');
/*!40000 ALTER TABLE `notice` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-26  8:42:47
