-- MySQL dump 10.13  Distrib 8.0.39, for Linux (x86_64)
--
-- Host: localhost    Database: hangman_db
-- ------------------------------------------------------
-- Server version	8.0.39-0ubuntu0.24.04.2

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `username` varchar(10) DEFAULT NULL,
  `score` int DEFAULT '0',
  `play_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `pid` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`pid`),
  KEY `username` (`username`),
  CONSTRAINT `account_ibfk_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `animal`
--

DROP TABLE IF EXISTS `animal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `animal` (
  `id` int NOT NULL AUTO_INCREMENT,
  `data` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `animal`
--

LOCK TABLES `animal` WRITE;
/*!40000 ALTER TABLE `animal` DISABLE KEYS */;
INSERT INTO `animal` VALUES (1,'giraffe'),(2,'deer'),(3,'wolf'),(4,'monkey'),(5,'octopus'),(6,'crocodile'),(7,'bear'),(8,'squireel'),(9,'cat'),(10,'rabbit'),(12,'Tiger'),(13,'Lion'),(14,'Zebra'),(15,'Horse'),(16,'Shark'),(17,'Whale'),(18,'Sheep'),(19,'Goose'),(20,'Koala'),(21,'Moose');
/*!40000 ALTER TABLE `animal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `body_part`
--

DROP TABLE IF EXISTS `body_part`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `body_part` (
  `id` int NOT NULL AUTO_INCREMENT,
  `data` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `body_part`
--

LOCK TABLES `body_part` WRITE;
/*!40000 ALTER TABLE `body_part` DISABLE KEYS */;
INSERT INTO `body_part` VALUES (1,'ear'),(2,'eyebrow'),(3,'mouth'),(4,'nose'),(5,'elbow'),(6,'shoulder'),(7,'finger'),(8,'kneel'),(9,'heel'),(10,'forehead'),(11,'arm'),(12,'leg'),(13,'hand'),(14,'foot'),(15,'chin'),(16,'wrist'),(17,'neck'),(18,'thigh'),(19,'ankle'),(20,'hip');
/*!40000 ALTER TABLE `body_part` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cloth`
--

DROP TABLE IF EXISTS `cloth`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cloth` (
  `id` int NOT NULL AUTO_INCREMENT,
  `data` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cloth`
--

LOCK TABLES `cloth` WRITE;
/*!40000 ALTER TABLE `cloth` DISABLE KEYS */;
INSERT INTO `cloth` VALUES (1,'Gloves'),(2,'Skirt'),(3,'Belt'),(4,'Hat'),(5,'Shoes'),(6,'Dress'),(7,'Shirt'),(8,'Shorts'),(9,'Trousers'),(10,'Socks'),(11,'Jacket'),(12,'Sweater'),(13,'Blouse'),(14,'Coat'),(15,'Scarf'),(16,'Jeans'),(17,'Vest'),(18,'Pajamas'),(19,'Hoodie'),(20,'Poncho');
/*!40000 ALTER TABLE `cloth` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `color`
--

DROP TABLE IF EXISTS `color`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `color` (
  `id` int NOT NULL AUTO_INCREMENT,
  `data` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `color`
--

LOCK TABLES `color` WRITE;
/*!40000 ALTER TABLE `color` DISABLE KEYS */;
INSERT INTO `color` VALUES (1,'Red'),(2,'Green'),(3,'Blue'),(4,'Black'),(5,'Olive'),(6,'Cyan'),(7,'Pink'),(8,'Purple'),(9,'Gold'),(10,'Grey'),(11,'Yellow'),(12,'Orange'),(13,'Brown'),(14,'Violet'),(15,'Maroon'),(16,'Indigo'),(17,'Beige'),(18,'Teal'),(19,'Navy'),(20,'Silver');
/*!40000 ALTER TABLE `color` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `country` (
  `id` int NOT NULL AUTO_INCREMENT,
  `data` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'america'),(2,'england'),(3,'spain'),(4,'korea'),(5,'japan'),(6,'canada'),(7,'poland'),(8,'australia'),(9,'belgium'),(10,'iceland'),(11,'brazil'),(12,'france'),(13,'germany'),(14,'india'),(15,'italy'),(16,'maxico'),(17,'norway'),(18,'portugal'),(19,'sweden'),(20,'thailand');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `electronic`
--

DROP TABLE IF EXISTS `electronic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `electronic` (
  `id` int NOT NULL AUTO_INCREMENT,
  `data` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `electronic`
--

LOCK TABLES `electronic` WRITE;
/*!40000 ALTER TABLE `electronic` DISABLE KEYS */;
INSERT INTO `electronic` VALUES (1,'Smartphone'),(2,'Laptop'),(3,'Tablet'),(4,'Smartwatch'),(5,'Television'),(6,'Camera'),(7,'Oven'),(8,'Earbuds'),(9,'Printer'),(10,'Speaker'),(11,'Radio'),(12,'Router'),(13,'Monitor'),(14,'Freezer'),(15,'Scanner'),(16,'Toaster'),(17,'Blender'),(18,'Keyboard'),(19,'Projector'),(20,'Console');
/*!40000 ALTER TABLE `electronic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food`
--

DROP TABLE IF EXISTS `food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `food` (
  `id` int NOT NULL AUTO_INCREMENT,
  `data` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` VALUES (1,'pizza'),(2,'burger'),(3,'sushi'),(4,'ramen'),(5,'steak'),(6,'croissant'),(7,'baguette'),(8,'donut'),(9,'pasta'),(10,'sandwich'),(11,'salad'),(12,'muffin'),(13,'waffle'),(14,'pancake'),(15,'biscuit'),(16,'tempura'),(17,'brownie'),(18,'tacos'),(19,'noodle'),(20,'popcorn');
/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fruit`
--

DROP TABLE IF EXISTS `fruit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fruit` (
  `id` int NOT NULL AUTO_INCREMENT,
  `data` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fruit`
--

LOCK TABLES `fruit` WRITE;
/*!40000 ALTER TABLE `fruit` DISABLE KEYS */;
INSERT INTO `fruit` VALUES (1,'Apple'),(2,'Banana'),(3,'Orange'),(4,'Strawberry'),(5,'Grape'),(6,'Mango'),(7,'Pineapple'),(8,'Blueberry'),(9,'Watermelon'),(10,'Peach'),(11,'Kiwi'),(12,'Lemon'),(13,'Lime'),(14,'Papaya'),(15,'Cherry'),(16,'Plum'),(17,'Melon'),(18,'Fig'),(19,'Pear'),(20,'Guava');
/*!40000 ALTER TABLE `fruit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job`
--

DROP TABLE IF EXISTS `job`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `job` (
  `id` int NOT NULL AUTO_INCREMENT,
  `data` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job`
--

LOCK TABLES `job` WRITE;
/*!40000 ALTER TABLE `job` DISABLE KEYS */;
INSERT INTO `job` VALUES (1,'Architect'),(2,'Doctor'),(3,'Dentist'),(4,'Chef'),(5,'Lawyer'),(6,'Cashier'),(7,'Sailor'),(8,'Actor'),(9,'Dancer'),(10,'Nurse'),(11,'Engineer'),(12,'Teacher'),(13,'Pilot'),(14,'analyst'),(15,'Baker'),(16,'Author'),(17,'Painter'),(18,'Plumber'),(19,'Welder'),(20,'Farmer');
/*!40000 ALTER TABLE `job` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sport`
--

DROP TABLE IF EXISTS `sport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sport` (
  `id` int NOT NULL AUTO_INCREMENT,
  `data` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sport`
--

LOCK TABLES `sport` WRITE;
/*!40000 ALTER TABLE `sport` DISABLE KEYS */;
INSERT INTO `sport` VALUES (1,'Swimming'),(2,'Badminton'),(3,'Basketball'),(4,'Boxing'),(5,'Football'),(6,'Judo'),(7,'Archery'),(8,'Tennis'),(9,'Golf'),(10,'Cycling'),(11,'Baseball'),(12,'Cricket'),(13,'Curling'),(14,'Fencing'),(15,'Hockey'),(16,'Rugby'),(17,'Rowing'),(18,'Surfing'),(19,'Wresting'),(20,'Skiing');
/*!40000 ALTER TABLE `sport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `username` varchar(10) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-11 18:00:09
