-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: testpa
-- ------------------------------------------------------
-- Server version	8.0.28

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


CREATE DATABASE testpa;

USE testpa;

--
-- Table structure for table `customerorders`
--

DROP TABLE IF EXISTS `customerorders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customerorders` (
  `id` varchar(36) NOT NULL COMMENT 'Primary Key',
  `cartItems` json DEFAULT NULL COMMENT 'cartItems',
  `firstName` varchar(255) DEFAULT NULL COMMENT 'firstname',
  `lastName` varchar(255) DEFAULT NULL COMMENT 'lastname',
  `phoneNumber` varchar(255) DEFAULT NULL COMMENT 'phoneNumber',
  `shippingAddress` varchar(255) DEFAULT NULL COMMENT 'shipping',
  `deliveryMethod` varchar(255) DEFAULT NULL COMMENT '3options',
  `creditCard` int DEFAULT NULL COMMENT 'credit card',
  `email` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COMMENT='newTable';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customerorders`
--

LOCK TABLES `customerorders` WRITE;
/*!40000 ALTER TABLE `customerorders` DISABLE KEYS */;
INSERT INTO `customerorders` VALUES ('26fec453d756476197a1d0648df7be22','[8]','111','tester','6969696969','ayo','expedited',1223123,'ayo@ayo.com'),('c2fd4afe5285495186651dc9f9ceb551','[8, 8]','aaay','tester','6969696969','ayo','grounded',111,'ayo@ayo.com');
/*!40000 ALTER TABLE `customerorders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `frog_list`
--

DROP TABLE IF EXISTS `frog_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `frog_list` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `description` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `name` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `price` float DEFAULT NULL,
  `image` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3 COMMENT='newTable';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `frog_list`
--

LOCK TABLES `frog_list` WRITE;
/*!40000 ALTER TABLE `frog_list` DISABLE KEYS */;
INSERT INTO `frog_list` VALUES (1,'The wood frog is a frog species that has a broad distribution over North America, extending from the boreal forest of the north to the southern Appalachians, with several notable disjunct populations including lowland eastern North Carolina.  ','Wood Frog',500,'images/woodFrog.jpg'),(2,'The green-and-black poison dart frog, also known as the green-and-black poison arrow frog and green poison frog, is a brightly colored member of the order Anura native to Central America and northwestern parts of South America. This species has also been introduced to Hawaii.  ','Green & Black Poison Frog',200,'images/gbpoison.jpg'),(3,'The fire-bellied toads are a group of six species of small frogs belonging to the genus Bombina. The name \"fire-bellied\" is derived from the brightly colored red- or yellow-and-black patterns on the toads\' ventral regions, which act as aposematic coloration, a warning to predators of the toads\' reputedly foul taste.  ','FireBelly Toad',500,'images/firebelly.jpg'),(4,'Lepidobatrachus laevis, widely known as Budgett\'s frog, is a species of frog in the family Ceratophryidae, discovered by John Samuel Budgett. It is often kept as a pet. It has acquired a number of popular nicknames, including hippo frog, Freddy Krueger frog, and escuerzo de agua.  ','Budgett Frog',480,'images/budgettFrog.jpg'),(5,'The banded bullfrog is a species of frog in the narrow-mouthed frog family Microhylidae. Native to Southeast Asia, it is also known as the Asian painted frog, digging frog, Malaysian bullfrog, common Asian frog, and painted balloon frog. In the pet trade, it is sometimes called the chubby frog.  ','Kaloula Frog',350,'images/kaloula.jpg'),(6,'Mantella are a prominent genus of aposematic frogs in the family Mantellidae, endemic to the island of Madagascar. The members of the genus are diurnal and terrestrial in behaviour, exhibiting bright colouration or cryptic markings which are species specific.  ','Mantella Frog',500,'images/mantella.jpg'),(7,'Ceratophrys is a genus of frogs in the family Ceratophryidae. They are also as Pacman frogs due to their characteristic round shape and large mouth, reminiscent of the video game character Pac-Man.  ','S. American Horned Frog',400,'images/southHorned.jpg'),(8,'Tomato frogs are any of the three species of genus Dyscophus: D. antongilii, D. insularis, or D. guineti. Dyscophus is the only genus in subfamily Dyscophinae. They are endemic to Madagascar. The common name comes from D. antongilii\'s bright red color. When threatened, a tomato frog puffs up its body.  ','Tomato Frog',200,'images/tomatoFrog.jpg'),(9,'A tree frog is any species of frog that spends a major portion of its lifespan in trees, known as an arboreal state. Several lineages of frogs among the Neobatrachia have given rise to treefrogs, although they are not closely related to each other.  ','Tree Frog',430,'images/treeFrog.jpg'),(10,'A true toad is any member of the family Bufonidae, in the order Anura. This is the only family of anurans in which all members are known as toads, although some may be called frogs. The bufonids now comprise more than 35 genera, Bufo being the best known.  ','True Toad',100,'images/trueToad.jpg');
/*!40000 ALTER TABLE `frog_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `username` varchar(256) DEFAULT NULL COMMENT 'usersname',
  `item_id` int DEFAULT NULL COMMENT 'itemid',
  `rating` float DEFAULT NULL COMMENT 'rating',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3 COMMENT='newOrders';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'defaultAndy',1,1),(2,'defaultAndy',2,5),(3,'defaultAndy',3,2.5),(10,'defaultAndy',10,5);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'Primary Key',
  `username` varchar(256) DEFAULT NULL COMMENT 'username',
  `password` varchar(256) DEFAULT NULL,
  `orders` json DEFAULT NULL COMMENT 'orders',
  `email` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COMMENT='newUsers table\n';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'defaultAndy','password','[\"1\", \"2\", \"5\", \"10\", \"3\"]',NULL),(2,'notAndy','passw','[\"1\", \"2\", \"3\", \"4\", \"5\"]',NULL);
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

-- Dump completed on 2022-05-04 14:58:47
