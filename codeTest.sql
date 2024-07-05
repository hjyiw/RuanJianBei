-- MySQL dump 10.13  Distrib 8.0.35, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: codetest
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class` (
  `c_id` int NOT NULL AUTO_INCREMENT,
  `c_name` varchar(30) NOT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='班级';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `content`
--

DROP TABLE IF EXISTS `content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `content` (
  `p_id` int unsigned NOT NULL COMMENT '题目id',
  `pro_con` varchar(500) DEFAULT NULL,
  `in_detail` varchar(300) NOT NULL COMMENT '输入描述',
  `out_detail` varchar(300) NOT NULL COMMENT '输出描述',
  `in_test` varchar(100) DEFAULT NULL COMMENT '输入测试',
  `out_test` varchar(100) NOT NULL COMMENT '测试输出',
  PRIMARY KEY (`p_id`),
  CONSTRAINT `Content_problems_p_id_fk` FOREIGN KEY (`p_id`) REFERENCES `problems` (`p_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `content`
--

LOCK TABLES `content` WRITE;
/*!40000 ALTER TABLE `content` DISABLE KEYS */;
INSERT INTO `content` VALUES (1,'输入两个整数，求这两个整数的和是多少。','','',NULL,'');
/*!40000 ALTER TABLE `content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `label`
--

DROP TABLE IF EXISTS `label`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `label` (
  `l_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '标签id',
  `l_name` varchar(15) NOT NULL COMMENT '标签名',
  PRIMARY KEY (`l_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='算法标签';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `label`
--

LOCK TABLES `label` WRITE;
/*!40000 ALTER TABLE `label` DISABLE KEYS */;
INSERT INTO `label` VALUES (1,'模拟'),(2,'数学'),(3,'BFS'),(4,'DFS'),(5,'背包'),(6,'二分');
/*!40000 ALTER TABLE `label` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lp_relation`
--

DROP TABLE IF EXISTS `lp_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lp_relation` (
  `l_id` int unsigned NOT NULL COMMENT '标签id',
  `p_id` int unsigned NOT NULL COMMENT '题目id',
  PRIMARY KEY (`l_id`,`p_id`),
  KEY `lp_relation_problems_p_id_fk` (`p_id`),
  CONSTRAINT `lp_relation_label_l_id_fk` FOREIGN KEY (`l_id`) REFERENCES `label` (`l_id`),
  CONSTRAINT `lp_relation_problems_p_id_fk` FOREIGN KEY (`p_id`) REFERENCES `problems` (`p_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='所属表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lp_relation`
--

LOCK TABLES `lp_relation` WRITE;
/*!40000 ALTER TABLE `lp_relation` DISABLE KEYS */;
INSERT INTO `lp_relation` VALUES (1,1),(1,2),(5,2),(1,3);
/*!40000 ALTER TABLE `lp_relation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `problems`
--

DROP TABLE IF EXISTS `problems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `problems` (
  `p_name` varchar(20) NOT NULL COMMENT '题目名字',
  `state` int unsigned DEFAULT '0' COMMENT '状态',
  `p_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '题号',
  `difficulty` varchar(10) DEFAULT NULL COMMENT '难度',
  `ac_num` int DEFAULT '0' COMMENT '通过数',
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='题库';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `problems`
--

LOCK TABLES `problems` WRITE;
/*!40000 ALTER TABLE `problems` DISABLE KEYS */;
INSERT INTO `problems` VALUES ('A+B',0,1,'简单',0),('01背包问题',0,2,'简单',0),('数值的整数次方',0,3,'中等',0);
/*!40000 ALTER TABLE `problems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teach`
--

DROP TABLE IF EXISTS `teach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teach` (
  `t_id` int NOT NULL COMMENT '老师id',
  `s_id` int NOT NULL COMMENT '学生id',
  PRIMARY KEY (`t_id`,`s_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='老师学生关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teach`
--

LOCK TABLES `teach` WRITE;
/*!40000 ALTER TABLE `teach` DISABLE KEYS */;
INSERT INTO `teach` VALUES (5,6);
/*!40000 ALTER TABLE `teach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `testdata`
--

DROP TABLE IF EXISTS `testdata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `testdata` (
  `t_id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '测试数据id',
  `p_id` int unsigned NOT NULL COMMENT '题目id',
  `input` varchar(300) NOT NULL COMMENT '输入',
  `output` varchar(300) NOT NULL COMMENT '输出',
  `answer` varchar(300) NOT NULL COMMENT '正确输出',
  PRIMARY KEY (`t_id`,`p_id`),
  KEY `testData_problems_p_id_fk` (`p_id`),
  CONSTRAINT `testData_problems_p_id_fk` FOREIGN KEY (`p_id`) REFERENCES `problems` (`p_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `testdata`
--

LOCK TABLES `testdata` WRITE;
/*!40000 ALTER TABLE `testdata` DISABLE KEYS */;
/*!40000 ALTER TABLE `testdata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `phone` varchar(12) DEFAULT '' COMMENT '电话',
  `nickname` varchar(20) DEFAULT '' COMMENT '昵称',
  `password` varchar(20) NOT NULL COMMENT '密码',
  `signature` varchar(30) DEFAULT '' COMMENT '签名',
  `picture` varchar(128) DEFAULT '' COMMENT '头像',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `email` varchar(20) DEFAULT '' COMMENT '邮箱',
  `role` varchar(30) NOT NULL DEFAULT 'student',
  `c_id` int NOT NULL COMMENT '班级id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_uindex` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('张三',1,'19932802','zhangsan','123456','','',NULL,NULL,'193284@Gmail','student',1),('李四',2,'13298823','lisi','234567','','',NULL,NULL,'','student',2),('王五',3,'23345','wangwu','1234567','','',NULL,NULL,'','student',1),('王老师',5,'1997463','MS.Wang','123456','','',NULL,NULL,'','teacher',1),('马六',6,'1344445','maliu','123456','','',NULL,NULL,'','student',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `AfterStudentInsert` AFTER INSERT ON `user` FOR EACH ROW BEGIN
    -- 声明变量
    DECLARE done INT DEFAULT 0;
    DECLARE t_id INT;

    -- 声明游标
    DECLARE cur CURSOR FOR
        SELECT id FROM user
        WHERE c_id = NEW.c_id AND role = 'teacher';

    -- 声明处理程序
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

    -- 打开游标
    OPEN cur;

    -- 循环读取游标数据
    read_loop: LOOP
        FETCH cur INTO t_id;
        IF done = 1 THEN
            LEAVE read_loop;
        END IF;

        -- 插入逻辑，这里需要检查是否已经存在关系
        -- 以下为伪代码，需要根据实际情况调整
        IF NOT EXISTS (
            SELECT 1 FROM teach
            WHERE t_id = t_id AND s_id = NEW.id
        ) THEN
            INSERT INTO teach (t_id, s_id) VALUES (t_id, NEW.id);
        END IF;
    END LOOP;

    -- 关闭游标
    CLOSE cur;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-05 14:57:46
