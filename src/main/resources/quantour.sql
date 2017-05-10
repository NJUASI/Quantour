/*
Navicat MySQL Data Transfer

Source Server         : Quantour
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : quantour

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-05-10 14:39:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `datasourceinfo`
-- ----------------------------
DROP TABLE IF EXISTS `datasourceinfo`;
CREATE TABLE `datasourceinfo` (
  `userName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `fileSize` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `uploadTime` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of datasourceinfo
-- ----------------------------
INSERT INTO `datasourceinfo` VALUES ('61990', '200', '2017-05-10T14:38:05.320');
INSERT INTO `datasourceinfo` VALUES ('ByronDong', '100', '2017-05-10T14:38:04.403');
INSERT INTO `datasourceinfo` VALUES ('CharlesFeng', '400', '2017-05-10T14:38:05.707');
INSERT INTO `datasourceinfo` VALUES ('cuihua', '50', '2017-05-10T14:38:05.451');
INSERT INTO `datasourceinfo` VALUES ('HarveyGong', '120', '2017-05-10T14:38:05.593');

-- ----------------------------
-- Table structure for `stock`
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock` (
  `code` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `date` date NOT NULL,
  `adjClose` double NOT NULL,
  `close` double NOT NULL,
  `high` double NOT NULL,
  `low` double NOT NULL,
  `market` int(11) DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `open` double NOT NULL,
  `preAdjClose` double NOT NULL,
  `preClose` double NOT NULL,
  `volume` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`code`,`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of stock
-- ----------------------------

-- ----------------------------
-- Table structure for `stocksearch`
-- ----------------------------
DROP TABLE IF EXISTS `stocksearch`;
CREATE TABLE `stocksearch` (
  `code` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `firstLetters` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of stocksearch
-- ----------------------------

-- ----------------------------
-- Table structure for `stocksituation`
-- ----------------------------
DROP TABLE IF EXISTS `stocksituation`;
CREATE TABLE `stocksituation` (
  `date` date NOT NULL,
  `climbingNum` int(11) NOT NULL,
  `limitDownNum` int(11) NOT NULL,
  `limitUpNum` int(11) NOT NULL,
  `slipingNum` int(11) NOT NULL,
  `slumpingNum` int(11) NOT NULL,
  `surgingNum` int(11) NOT NULL,
  `volume` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of stocksituation
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------

-- ----------------------------
-- Table structure for `user_stock`
-- ----------------------------
DROP TABLE IF EXISTS `user_stock`;
CREATE TABLE `user_stock` (
  `user_userName` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `privateStock_code` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `privateStock_date` date NOT NULL,
  KEY `FKf4wpmanftaqfc381r5k7boqwt` (`privateStock_code`,`privateStock_date`),
  KEY `FKkd4wmxmohn8rk8pee5sndmo8i` (`user_userName`),
  CONSTRAINT `FKf4wpmanftaqfc381r5k7boqwt` FOREIGN KEY (`privateStock_code`, `privateStock_date`) REFERENCES `stock` (`code`, `date`),
  CONSTRAINT `FKkd4wmxmohn8rk8pee5sndmo8i` FOREIGN KEY (`user_userName`) REFERENCES `user` (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_stock
-- ----------------------------
