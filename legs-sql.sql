-- MySQL dump 10.13  Distrib 8.4.2, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: legs
-- ------------------------------------------------------
-- Server version	8.4.2

create database legs if not exists
    default character set utf8mb3
    default collate utf8mb3_unicode_ci;

use legs;

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `task` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `sender` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `receiver` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `date` date DEFAULT NULL,
  `state` int DEFAULT NULL COMMENT '订单',
  `price` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES 
('2eea8fab-17e5-462e-8fb7-44cc2b37af8c','工作','sender','receiver','2024-09-27',1,'100'),('3','学习','2108710217','2108710210','2023-11-05',1,'10'),('384a5361-b006-4e4d-9e37-4936fea2e921','考试','2108710209','','2023-11-10',0,'50'),('4','占位','2108710217',NULL,'2023-11-07',0,'5'),('4125b79b-9dc2-490c-83e2-844fde24362c','学习','2108710209','','2023-11-10',0,'1000'),('5','买饭','2108710217','2108710216','2023-11-09',1,'16'),('6','充电','2108710217','2108710210','2023-11-04',1,'4'),('7','学习','2108710217','2108710211','2023-11-10',1,'100'),('82e6762e-2792-420d-b810-1251b83a36a1','金铲铲','2108710217','receiver','2023-11-10',1,'200'),('cbc63d2d-fd60-4cf8-b014-41d9800022c3','谈恋爱','2108710209','nobody','2023-11-10',0,'100'),('dwd','测试2','2108710217','2108710217','2023-02-14',1,'223000');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `username` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL,
  `phone` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_unicode_ci DEFAULT NULL COMMENT '用户表',
  `type` int DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('2108710205','111111','韩博丞','2108710205',0),('2108710209','111111','刘存豪','218710209',1),('2108710210','111111','刘明达','2108710210',2),('2108710211','111111','刘宇飞','110',2),('2108710216','111111','孙东平','120',0),('2108710217','111111','孙帅','2108710217',1),('admin','admin','管理员','11111',0),('Carol','123456','test','13316451954',1),('Jose','123456','test','18151233321',1),('receiver','receiver','跑腿小哥','121231',2),('sender','sender','普通用户','12312313',1);
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

-- Dump completed on 2024-09-27 10:37:24
