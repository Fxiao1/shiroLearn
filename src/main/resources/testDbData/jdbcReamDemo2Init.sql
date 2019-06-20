-- MySQL dump 10.16  Distrib 10.3.9-MariaDB, for Win64 (AMD64)
--
-- Host: 192.168.187.18    Database: fx_db1
-- ------------------------------------------------------
-- Server version	5.7.25-0ubuntu0.18.04.2

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
-- Table structure for table `test_roles_permissions`
--

DROP TABLE IF EXISTS `test_roles_permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_roles_permissions` (
  `permission` varchar(100) DEFAULT NULL COMMENT '权限',
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名',
  `id` int(11) NOT NULL COMMENT '主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_roles_permissions`
--

LOCK TABLES `test_roles_permissions` WRITE;
/*!40000 ALTER TABLE `test_roles_permissions` DISABLE KEYS */;
INSERT INTO `test_roles_permissions` VALUES ('test:user:delete','admin',1),('test:user:update','admin',2),('test:user:update','user',3);
/*!40000 ALTER TABLE `test_roles_permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_user_roles`
--

DROP TABLE IF EXISTS `test_user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_user_roles` (
  `role_name` varchar(100) NOT NULL COMMENT '角色名',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `id` int(11) NOT NULL COMMENT '主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_user_roles`
--

LOCK TABLES `test_user_roles` WRITE;
/*!40000 ALTER TABLE `test_user_roles` DISABLE KEYS */;
INSERT INTO `test_user_roles` VALUES ('admin','fxiao',1),('user','dachui',2);
/*!40000 ALTER TABLE `test_user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test_users`
--

DROP TABLE IF EXISTS `test_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `test_users` (
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `id` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='shiro的表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test_users`
--

LOCK TABLES `test_users` WRITE;
/*!40000 ALTER TABLE `test_users` DISABLE KEYS */;
INSERT INTO `test_users` VALUES ('fxiao','1234','1'),('dachui','123','2');
/*!40000 ALTER TABLE `test_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'fx_db1'
--
