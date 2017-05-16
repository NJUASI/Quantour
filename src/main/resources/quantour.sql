/*
Navicat MySQL Data Transfer

Source Server         : Quantour
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : quantour

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-05-16 12:59:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `basestock`
-- ----------------------------
DROP TABLE IF EXISTS `basestock`;
CREATE TABLE `basestock` (
  `code` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `date` date NOT NULL,
  `close` double NOT NULL,
  `fluctuation` double NOT NULL,
  `high` double NOT NULL,
  `increaseMargin` double NOT NULL,
  `low` double NOT NULL,
  `market` int(11) DEFAULT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `open` double NOT NULL,
  `preClose` double NOT NULL,
  `transactionAmount` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `volume` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`code`,`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of basestock
-- ----------------------------

-- ----------------------------
-- Table structure for `privatestock`
-- ----------------------------
DROP TABLE IF EXISTS `privatestock`;
CREATE TABLE `privatestock` (
  `stockCode` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `userName` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`stockCode`,`userName`)
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
  `circulationMarketValue` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `close` double NOT NULL,
  `fluctuation` double NOT NULL,
  `high` double NOT NULL,
  `increaseMargin` double NOT NULL,
  `low` double NOT NULL,
  `market` int(11) DEFAULT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `open` double NOT NULL,
  `preClose` double NOT NULL,
  `totalValue` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `transactionAmount` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `turnoverRate` double NOT NULL,
  `volume` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
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
  `code` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `market` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `firstLetters` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`code`,`market`,`name`)
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
  `volume` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of stocksituation
-- ----------------------------

-- ----------------------------
-- Table structure for `tracebackstockpool`
-- ----------------------------
DROP TABLE IF EXISTS `tracebackstockpool`;
CREATE TABLE `tracebackstockpool` (
  `stockCode` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `userName` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `stockName` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`stockCode`,`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tracebackstockpool
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userName` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `headPotrait` blob,
  `password` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('61990', null, 'b7bfe0c070d6fb3d9acc375d317dcfb5');
INSERT INTO `user` VALUES ('ByronDong', null, '8e523cd5ef475ab6834f0598f4a502f8');
INSERT INTO `user` VALUES ('CharlesFeng47', null, 'e78a98a93547e180cc7bf5323f1b6b66');
INSERT INTO `user` VALUES ('Harvey Gong', null, '2fbed987fcaedad037df73d70cd5e422');
