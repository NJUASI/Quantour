/*
Navicat MySQL Data Transfer

Source Server         : Quantour
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : quantour

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-05-11 20:34:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `privatestock`
-- ----------------------------
DROP TABLE IF EXISTS `privatestock`;
CREATE TABLE `privatestock` (
  `stcokCode` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `userName` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`stcokCode`,`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of privatestock
-- ----------------------------

-- ----------------------------
-- Table structure for `stock`
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock` (
  `code` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `date` date NOT NULL,
  `adjClose` double NOT NULL,
  `close` double NOT NULL,
  `high` double NOT NULL,
  `low` double NOT NULL,
  `market` int(11) DEFAULT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `open` double NOT NULL,
  `preAdjClose` double NOT NULL,
  `preClose` double NOT NULL,
  `volume` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
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
INSERT INTO `stocksearch` VALUES ('000001', 's', '深发展A');
INSERT INTO `stocksearch` VALUES ('000002', 'h', '沪深A股');
INSERT INTO `stocksearch` VALUES ('000003', 'n', '南京B股');
INSERT INTO `stocksearch` VALUES ('000011', 's', '深宝宝A股');

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
  `userName` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
