-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: calories_all
-- ------------------------------------------------------
-- Server version	8.0.20

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
-- Table structure for table `activities_met_values`
--

DROP TABLE IF EXISTS `activities_met_values`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activities_met_values` (
  `activity_id` int NOT NULL AUTO_INCREMENT,
  `activity_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `activity_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `activity_met_value` decimal(4,2) NOT NULL,
  `activity_level` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`activity_id`),
  UNIQUE KEY `activity_id_UNIQUE` (`activity_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activities_met_values`
--

LOCK TABLES `activities_met_values` WRITE;
/*!40000 ALTER TABLE `activities_met_values` DISABLE KEYS */;
INSERT INTO `activities_met_values` VALUES (1,'bicycling','leisure',4.00,'moderate'),(2,'pilates','conditioning',3.00,'moderate'),(3,'ironing','home',1.80,'light'),(4,'meditating','quiet',1.00,'light'),(5,'jogging','running',7.00,'vigorous'),(6,'bowling','sports',3.00,'moderate'),(7,'hiking','walking',6.00,'moderate'),(8,'swimming','water',5.30,'moderate'),(9,'skiing','winter',7.00,'vigorous'),(10,'yoga','conditioning',2.30,'light'),(11,'tabata','cardio',11.00,'vigorous'),(12,'trx','strength',5.80,'moderate'),(13,'zumba','cardio',4.10,'moderate'),(14,'water aerobics','conditioning',5.30,'moderate'),(15,'walking','leisure',2.50,'light'),(16,'basketball','sports',6.50,'vigorous'),(17,'dancing','dancing',7.80,'vigorous'),(18,'cleaning','home',3.00,'moderate'),(19,'cooking','home',3.50,'moderate'),(20,'sleeping','quiet',0.90,'light'),(21,'typing','work',1.80,'light'),(22,'resistance training','strength',3.50,'moderate'),(23,'badminton','sports',4.50,'moderate'),(24,'tennis','sports',7.00,'vigorous'),(25,'gardening','home',3.80,'moderate'),(26,'ice hockey','sports',12.90,'vigorous'),(27,'volleyball','sports',6.00,'moderate'),(28,'motorcycling','leisure',2.20,'light'),(29,'shopping','home',2.30,'light'),(30,'soccer','sports',10.00,'vigorous'),(31,'test','test',1.10,'test');
/*!40000 ALTER TABLE `activities_met_values` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `activities_with_met_values`
--

DROP TABLE IF EXISTS `activities_with_met_values`;
/*!50001 DROP VIEW IF EXISTS `activities_with_met_values`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `activities_with_met_values` AS SELECT 
 1 AS `activity_name`,
 1 AS `activity_met_value`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `activity_performed`
--

DROP TABLE IF EXISTS `activity_performed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity_performed` (
  `activity_performed_id` int NOT NULL,
  `activity_performed_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `activity_performed_met_value` decimal(4,2) NOT NULL,
  `activity_performed_user_weight` decimal(4,1) NOT NULL,
  `activity_performed_minutes` int NOT NULL,
  PRIMARY KEY (`activity_performed_id`),
  UNIQUE KEY `activity_performed_id_UNIQUE` (`activity_performed_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_performed`
--

LOCK TABLES `activity_performed` WRITE;
/*!40000 ALTER TABLE `activity_performed` DISABLE KEYS */;
/*!40000 ALTER TABLE `activity_performed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `activity_type`
--

DROP TABLE IF EXISTS `activity_type`;
/*!50001 DROP VIEW IF EXISTS `activity_type`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `activity_type` AS SELECT 
 1 AS `activity_type`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `food_calories`
--

DROP TABLE IF EXISTS `food_calories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `food_calories` (
  `food_id` int NOT NULL AUTO_INCREMENT,
  `food_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `food_type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `food_calories_per_100g` double NOT NULL,
  `food_carbohydrates_per_100_g` decimal(6,2) unsigned DEFAULT NULL,
  `food_fats_per_100_g` decimal(6,2) unsigned DEFAULT NULL,
  `food_protein_per_100_g` decimal(6,2) unsigned DEFAULT NULL,
  PRIMARY KEY (`food_id`),
  UNIQUE KEY `food_id_UNIQUE` (`food_id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food_calories`
--

LOCK TABLES `food_calories` WRITE;
/*!40000 ALTER TABLE `food_calories` DISABLE KEYS */;
INSERT INTO `food_calories` VALUES (1,'apple','fruits',52,13.81,0.17,0.26),(2,'banana','fruits',89,22.84,0.33,1.09),(3,'blueberries','berries',57,14.49,0.33,0.74),(4,'pear','fruits',58,15.46,0.12,0.38),(5,'grapefruit red','fruits',42,10.66,0.14,0.77),(6,'orange','fruits',47,11.75,0.12,0.94),(7,'almonds','nuts',578,19.74,50.64,21.26),(8,'walnuts','nuts',654,13.71,65.21,15.23),(9,'flax seeds','seeds',534,28.88,42.16,18.29),(10,'sunflower seeds','seeds',570,18.76,49.57,22.78),(11,'quinoa','grains',143,26.35,2.22,5.01),(12,'rice white','grains',129,27.90,0.28,2.66),(13,'cauliflower','vegetables',25,5.30,0.10,1.98),(14,'carrot','vegetables',41,9.58,0.24,0.93),(15,'potato','vegetables',70,15.71,0.10,1.68),(16,'tomato','vegetables',18,3.92,0.20,0.88),(17,'cucumber','vegetables',15,3.63,0.11,0.65),(18,'butter','dairy',717,0.06,81.11,0.85),(19,'milk whole','dairy',60,4.52,3.25,3.22),(20,'spaghetti','pasta',157,30.68,0.92,5.76),(21,'bread whole wheat','bread',259,47.14,4.11,9.13),(22,'salmon cooked','fish',139,0.00,4.30,23.45),(23,'shrimp','seafood',144,1.24,2.35,27.59),(24,'eel smoked','seafood',289,0.00,18.30,28.95),(25,'bacon','meat',541,1.43,41.78,37.04),(26,'beef','meat',288,0.00,19.54,26.33),(27,'chicken','meat',237,0.00,13.49,27.07),(28,'ice cream','desert',201,24.40,10.72,3.52),(29,'frozen yogurt','desert',107,19.62,1.47,4.70),(30,'sorbert citrus flavor','desert',95,23.17,0.00,0.50),(31,'test','test',0,5.00,5.00,5.00);
/*!40000 ALTER TABLE `food_calories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food_eaten`
--

DROP TABLE IF EXISTS `food_eaten`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `food_eaten` (
  `food_eaten_id` int NOT NULL AUTO_INCREMENT,
  `food_eaten_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `food_eaten_quantity` double NOT NULL,
  `food_eaten_calories` int NOT NULL,
  PRIMARY KEY (`food_eaten_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food_eaten`
--

LOCK TABLES `food_eaten` WRITE;
/*!40000 ALTER TABLE `food_eaten` DISABLE KEYS */;
INSERT INTO `food_eaten` VALUES (1,'apple',100,50),(2,'banana',50,50),(3,'pear',50,50),(16,'grapefruit red',58,50),(17,'milk whole',56,50),(18,'bread whole wheat',80,50),(19,'testFoodWithCalories',50,100),(20,'grapefruit red',100,100),(21,'bread whole wheat',100,100),(22,'bread whole wheat',80,100),(23,'milk whole',50,100),(24,'frozen yogurt',100,100),(25,'bread whole wheat',500,100),(26,'bread whole wheat',9,0),(27,'milk whole',8,0),(28,'salmon cooked',100,0);
/*!40000 ALTER TABLE `food_eaten` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `food_with_categories`
--

DROP TABLE IF EXISTS `food_with_categories`;
/*!50001 DROP VIEW IF EXISTS `food_with_categories`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `food_with_categories` AS SELECT 
 1 AS `food_type`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `activities_with_met_values`
--

/*!50001 DROP VIEW IF EXISTS `activities_with_met_values`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `activities_with_met_values` AS select `activities_met_values`.`activity_name` AS `activity_name`,`activities_met_values`.`activity_met_value` AS `activity_met_value` from `activities_met_values` order by `activities_met_values`.`activity_name` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `activity_type`
--

/*!50001 DROP VIEW IF EXISTS `activity_type`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `activity_type` AS select distinct `activities_met_values`.`activity_type` AS `activity_type` from `activities_met_values` order by `activities_met_values`.`activity_type` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `food_with_categories`
--

/*!50001 DROP VIEW IF EXISTS `food_with_categories`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `food_with_categories` AS select distinct `food_calories`.`food_type` AS `food_type` from `food_calories` order by `food_calories`.`food_type` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-08 11:42:06
