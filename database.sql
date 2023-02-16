-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: db_absensi
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `calender`
--

DROP TABLE IF EXISTS `calender`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `calender` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` text NOT NULL,
  `date` date NOT NULL,
  `is_holiday` tinyint DEFAULT NULL,
  `class_bootcamp_id` varchar(255) DEFAULT NULL,
  `maker` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_calender_class_bootcamp` (`class_bootcamp_id`),
  CONSTRAINT `fk_calender_class_bootcamp` FOREIGN KEY (`class_bootcamp_id`) REFERENCES `class_bootcamp` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calender`
--

LOCK TABLES `calender` WRITE;
/*!40000 ALTER TABLE `calender` DISABLE KEYS */;
/*!40000 ALTER TABLE `calender` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class_bootcamp`
--

DROP TABLE IF EXISTS `class_bootcamp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class_bootcamp` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `address` text NOT NULL,
  `avatar_url` varchar(255) DEFAULT NULL,
  `lead_instructor` varchar(255) NOT NULL,
  `foundation` varchar(255) NOT NULL,
  `number_phone` varchar(255) DEFAULT NULL,
  `total_student` int DEFAULT NULL,
  `create_at` timestamp NOT NULL,
  `update_at` timestamp NOT NULL,
  `background_profile` text,
  `operational_class_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_class_operational` (`operational_class_id`),
  CONSTRAINT `fk_class_operational` FOREIGN KEY (`operational_class_id`) REFERENCES `operational_class` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_bootcamp`
--

LOCK TABLES `class_bootcamp` WRITE;
/*!40000 ALTER TABLE `class_bootcamp` DISABLE KEYS */;
INSERT INTO `class_bootcamp` VALUES ('jahdidhiay1328yisahdiuhdudaddoasjfkcknzkj','Bootcamp Binus Semarang','Jl Koptusuyono Rt 2/ Rw 4, Kec Ngaliyan, Semarang Barat',NULL,'Royan Abdullah','Yayasan Bina Nusantara',NULL,0,'2023-02-03 07:36:05','2023-02-03 07:36:11',NULL,NULL);
/*!40000 ALTER TABLE `class_bootcamp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `face_user`
--

DROP TABLE IF EXISTS `face_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `face_user` (
  `id` varchar(255) NOT NULL,
  `avatar_url` varchar(255) DEFAULT NULL,
  `detector_score` text NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT NULL,
  `update_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_face_user` (`user_id`),
  CONSTRAINT `fk_face_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `face_user`
--

LOCK TABLES `face_user` WRITE;
/*!40000 ALTER TABLE `face_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `face_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `flyway_schema_history`
--

DROP TABLE IF EXISTS `flyway_schema_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `flyway_schema_history` (
  `installed_rank` int NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flyway_schema_history`
--

LOCK TABLES `flyway_schema_history` WRITE;
/*!40000 ALTER TABLE `flyway_schema_history` DISABLE KEYS */;
INSERT INTO `flyway_schema_history` VALUES (1,'20230128145836','CreateTableUser','SQL','V20230128145836__CreateTableUser.sql',555556408,'prasetya','2023-02-03 07:24:32',42,1),(2,'20230128152214','CreateTableClassBootcamp','SQL','V20230128152214__CreateTableClassBootcamp.sql',984597425,'prasetya','2023-02-03 07:24:32',45,1),(3,'20230128152916','CreateTableRole','SQL','V20230128152916__CreateTableRole.sql',359568915,'prasetya','2023-02-03 07:24:32',57,1),(4,'20230202125525','CreateTableTemporaryToken','SQL','V20230202125525__CreateTableTemporaryToken.sql',-1576875820,'prasetya','2023-02-03 08:27:28',52,1),(5,'20230204151014','CreateTableCalender','SQL','V20230204151014__CreateTableCalender.sql',-1111133074,'prasetya','2023-02-12 03:59:58',143,1),(6,'20230210161608','AddColumnTableClassBootcamp','SQL','V20230210161608__AddColumnTableClassBootcamp.sql',-923497444,'prasetya','2023-02-12 04:46:56',252,1),(7,'20230211115157','AddColumnTableUser','SQL','V20230211115157__AddColumnTableUser.sql',-1182165434,'prasetya','2023-02-12 04:46:56',37,1),(8,'20230211132645','DropColumnTableUser','SQL','V20230211132645__DropColumnTableUser.sql',2140391027,'prasetya','2023-02-12 04:46:56',16,1),(9,'20230211133631','AddColumnTableUser','SQL','V20230211133631__AddColumnTableUser.sql',-1182165434,'prasetya','2023-02-12 04:46:56',19,1),(10,'20230212174513','CreateColumnMassages','SQL','V20230212174513__CreateColumnMassages.sql',78025951,'prasetya','2023-02-12 14:08:06',115,1),(11,'20230215010739','CreateTableOperationalClass','SQL','V20230215010739__CreateTableOperationalClass.sql',1341283626,'prasetya','2023-02-14 18:31:14',280,1),(12,'20230215011140','CreateTableGoingHomeEarly','SQL','V20230215011140__CreateTableGoingHomeEarly.sql',-478234535,'prasetya','2023-02-14 18:31:15',54,1),(13,'20230215011425','CreateTableFaceUser','SQL','V20230215011425__CreateTableFaceUser.sql',1601547017,'prasetya','2023-02-14 18:31:15',46,1),(14,'20230215011620','CreateTableReason','SQL','V20230215011620__CreateTableReason.sql',582031556,'prasetya','2023-02-14 18:31:15',32,1),(15,'20230215011902','CreateTablePresensi','SQL','V20230215011902__CreateTablePresensi.sql',1743394153,'prasetya','2023-02-14 18:31:15',50,1),(16,'20230215091634','AlterTableAddCreateAndUpdate','SQL','V20230215091634__AlterTableAddCreateAndUpdate.sql',783384522,'prasetya','2023-02-16 07:04:24',147,1);
/*!40000 ALTER TABLE `flyway_schema_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `going_home_early`
--

DROP TABLE IF EXISTS `going_home_early`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `going_home_early` (
  `id` int NOT NULL AUTO_INCREMENT,
  `note` text,
  `date` date NOT NULL,
  `mark_by` varchar(255) DEFAULT NULL,
  `class_bootcamp_id` varchar(255) DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT NULL,
  `update_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_going_home_early_class` (`class_bootcamp_id`),
  CONSTRAINT `fk_going_home_early_class` FOREIGN KEY (`class_bootcamp_id`) REFERENCES `class_bootcamp` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `going_home_early`
--

LOCK TABLES `going_home_early` WRITE;
/*!40000 ALTER TABLE `going_home_early` DISABLE KEYS */;
/*!40000 ALTER TABLE `going_home_early` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `messages` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sender_name` varchar(255) DEFAULT NULL,
  `receiver_name` varchar(255) DEFAULT NULL,
  `message` text,
  `date` varchar(255) DEFAULT NULL,
  `status` enum('JOIN','MESSAGE','LEAVE') DEFAULT NULL,
  `school_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (1,'Prasetya Dhany',NULL,'ses','2023-02-12T16:46:02.833Z','MESSAGE','jahdidhiay1328yisahdiuhdudaddoasjfkcknzkj'),(2,'Prasetya Dhany',NULL,'sasageo','2023-02-12T16:47:54.598Z','MESSAGE','jahdidhiay1328yisahdiuhdudaddoasjfkcknzkj'),(3,'Prasetya Dhany',NULL,'nana','2023-02-12T16:48:16.494Z','MESSAGE','jahdidhiay1328yisahdiuhdudaddoasjfkcknzkj'),(4,'Prasetya Dhany',NULL,'kok\n','2023-02-12T16:48:19.999Z','MESSAGE','jahdidhiay1328yisahdiuhdudaddoasjfkcknzkj'),(5,'Prasetya Dhany',NULL,'yea','2023-02-12T16:52:33.355Z','MESSAGE','jahdidhiay1328yisahdiuhdudaddoasjfkcknzkj'),(6,'Prasetya Dhany',NULL,'na','2023-02-12T16:52:50.380Z','MESSAGE','jahdidhiay1328yisahdiuhdudaddoasjfkcknzkj');
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operational_class`
--

DROP TABLE IF EXISTS `operational_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `operational_class` (
  `id` int NOT NULL AUTO_INCREMENT,
  `entry_time` varchar(255) NOT NULL,
  `dismissal_time` varchar(255) NOT NULL,
  `duration_learn` int NOT NULL,
  `admission_day` int NOT NULL,
  `mark_by` varchar(255) DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT NULL,
  `update_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operational_class`
--

LOCK TABLES `operational_class` WRITE;
/*!40000 ALTER TABLE `operational_class` DISABLE KEYS */;
/*!40000 ALTER TABLE `operational_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `presensi`
--

DROP TABLE IF EXISTS `presensi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `presensi` (
  `id` varchar(255) NOT NULL,
  `face_number` varchar(255) NOT NULL,
  `date_submit` datetime NOT NULL,
  `latitude` varchar(255) DEFAULT NULL,
  `longitude` varchar(255) DEFAULT NULL,
  `is_late` tinyint DEFAULT NULL,
  `permission_attend` tinyint DEFAULT '0',
  `note` varchar(255) DEFAULT NULL,
  `reason_id` int DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `class_bootcamp_id` varchar(255) DEFAULT NULL,
  `create_at` timestamp NULL DEFAULT NULL,
  `update_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_presensi_reason` (`reason_id`),
  KEY `fk_presensi_user` (`user_id`),
  KEY `fk_presensi_class` (`class_bootcamp_id`),
  CONSTRAINT `fk_presensi_class` FOREIGN KEY (`class_bootcamp_id`) REFERENCES `class_bootcamp` (`id`),
  CONSTRAINT `fk_presensi_reason` FOREIGN KEY (`reason_id`) REFERENCES `reason` (`id`),
  CONSTRAINT `fk_presensi_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presensi`
--

LOCK TABLES `presensi` WRITE;
/*!40000 ALTER TABLE `presensi` DISABLE KEYS */;
/*!40000 ALTER TABLE `presensi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reason`
--

DROP TABLE IF EXISTS `reason`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reason` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reason`
--

LOCK TABLES `reason` WRITE;
/*!40000 ALTER TABLE `reason` DISABLE KEYS */;
INSERT INTO `reason` VALUES (1,'SICK'),(2,'FAMILY_EVENTS'),(3,'OTHERS');
/*!40000 ALTER TABLE `reason` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'STUDENT'),(2,'INSTRUCTOR'),(3,'ADMIN');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token_temporary`
--

DROP TABLE IF EXISTS `token_temporary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `token_temporary` (
  `id` int NOT NULL AUTO_INCREMENT,
  `token` varchar(255) NOT NULL,
  `expired_date` datetime DEFAULT NULL,
  `validity_period` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_temporary_token_user` (`user_id`),
  CONSTRAINT `fk_temporary_token_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token_temporary`
--

LOCK TABLES `token_temporary` WRITE;
/*!40000 ALTER TABLE `token_temporary` DISABLE KEYS */;
INSERT INTO `token_temporary` VALUES (8,'3BLFOLHprxnYhS#D&Tz3J!K#tDRkiAFe7hkaVCH1yOBUNObJwe$cEV2Hg!%!0IYpZvh1ViwMOVJa1bBiiA8EyM%!qXdUq6C0KDm&ZCVjXTp6GZZfby#njmOuk#F4fqQc56#PLLW3i2ywIlmguEwt5$','2023-02-12 17:05:33','30 Minute','1d20c392-422a-4773-8f1b-97c501544f07'),(10,'$bwW579AKEwHY%uCHgq%umtn4$I@QRMNW1#HbNjoFq8x6nbphFY#K8TFRcHnsfAKg4Wg5WeasD5%7sQtrGDfHkXNGGDRDs#@E52&4ErAr6IlLDgNfk0itO14c4@i7K4jYmNg781lWUvI3!dhUyRo@q','2023-02-12 17:23:25','30 Minute','ba8aa5aa-d7bd-45eb-93cc-a020bfeaa0bc'),(11,'cqyt9r1LT%&!pODHMCBrMUDxAV#xfEsLXuf9h!YGE0rowrI8r2Eu%fcnwA2rinQ#a&KormroHkv2YZ%9AO5d@%DlX0OS!@GI5wND!HyZ5ydQYItLkbYqHvr#yCZjbWHwUjmNYdwlmhAbcQ4%z7sPqg','2023-02-14 17:47:54','30 Minute','df58f9ae-6db2-4f5a-a4b5-b71e36fa8349');
/*!40000 ALTER TABLE `token_temporary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` varchar(255) NOT NULL,
  `no_siswa` int DEFAULT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `avatar_url` varchar(255) DEFAULT NULL,
  `school_class` varchar(255) DEFAULT NULL,
  `batch` int DEFAULT NULL,
  `gender` enum('MALE','FEMALE') DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `favorite` tinyint DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  `description` text,
  `class_bootcamp_id` varchar(255) DEFAULT NULL,
  `create_at` timestamp NOT NULL,
  `update_at` timestamp NOT NULL,
  `viewers` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_class_bootcamp` (`class_bootcamp_id`),
  KEY `fk_user_role` (`role_id`),
  CONSTRAINT `fk_user_class_bootcamp` FOREIGN KEY (`class_bootcamp_id`) REFERENCES `class_bootcamp` (`id`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('1d20c392-422a-4773-8f1b-97c501544f07',1,'Siswa','Bootcamp','$2a$10$7ydd/fxEkR8QL0s0GZLy9.XsUOlTgqTE55Sduy7CrKlGEbMYw.tG.','siswabootcamp1@google.com','null','XII TKJ 2',1,'MALE','2005-06-09',0,1,'test','jahdidhiay1328yisahdiuhdudaddoasjfkcknzkj','2023-02-03 01:15:13','2023-02-03 01:15:13',NULL),('ba8aa5aa-d7bd-45eb-93cc-a020bfeaa0bc',2,'Prasetya','Dhany','$2a$10$SAg3YP1br4JCx3dzUKMZqOIQ24/Nk1/2y02TlVhE.s/Qnh3dxCyRW','putraprasetyadhany@gmail.com','','XII TKJ 2',3,'MALE','2005-06-06',0,1,'','jahdidhiay1328yisahdiuhdudaddoasjfkcknzkj','2023-02-12 03:23:20','2023-02-12 03:23:20',NULL),('df58f9ae-6db2-4f5a-a4b5-b71e36fa8349',0,'Instructor','Binus','$2a$10$vd6rqxsjXJ0GH6bLOXkINOFAbEKSjlOv0F6kRMpG.Hi2kpk6ptQoi','instructorbinusa@gmail.com','','',0,'MALE','1989-01-14',1,2,'','jahdidhiay1328yisahdiuhdudaddoasjfkcknzkj','2023-02-14 10:09:42','2023-02-14 10:09:42',0);
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

-- Dump completed on 2023-02-16 14:11:44
