/*
Navicat MySQL Data Transfer

Source Server         : Quantour
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : quantour

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-04-25 09:01:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `datasourceinfo`
-- ----------------------------
DROP TABLE IF EXISTS `datasourceinfo`;
CREATE TABLE `datasourceinfo` (
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `filesize` varchar(255) COLLATE utf8_unicode_ci DEFAULT '0',
  `uploadTime` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of datasourceinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `privatestock`
-- ----------------------------
DROP TABLE IF EXISTS `privatestock`;
CREATE TABLE `privatestock` (
  `username` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `privateStockCode` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of privatestock
-- ----------------------------

-- ----------------------------
-- Table structure for `stock`
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock` (
  `code` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `date` date DEFAULT NULL,
  `open` double DEFAULT '0',
  `high` double DEFAULT '0',
  `low` double DEFAULT '0',
  `close` double DEFAULT '0',
  `volume` varchar(255) COLLATE utf8_unicode_ci DEFAULT '0',
  `adjclose` double DEFAULT '0',
  `preclose` double DEFAULT '0',
  `preadjclose` double DEFAULT '0',
  `market` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of stock
-- ----------------------------

-- ----------------------------
-- Table structure for `stocksituation`
-- ----------------------------
DROP TABLE IF EXISTS `stocksituation`;
CREATE TABLE `stocksituation` (
  `date` date NOT NULL,
  `volume` varchar(255) COLLATE utf8_unicode_ci DEFAULT '0',
  `limitUpNum` int(11) DEFAULT '0',
  `limitDownNum` int(11) DEFAULT '0',
  `surgingNum` int(11) DEFAULT '0',
  `slumpingNum` int(11) DEFAULT '0',
  `climbingNum` int(11) DEFAULT '0',
  `slipingNum` int(11) DEFAULT '0',
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
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Guest',
  `password` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
