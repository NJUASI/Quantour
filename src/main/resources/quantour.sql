/*
Navicat MySQL Data Transfer

Source Server         : Quantour
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : quantour

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-05-14 18:21:24
*/

SET FOREIGN_KEY_CHECKS=0;

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
INSERT INTO `privatestock` VALUES ('000001', 'ByronDong');
INSERT INTO `privatestock` VALUES ('000001', 'CharlesFeng');
INSERT INTO `privatestock` VALUES ('000002', 'ByronDong');
INSERT INTO `privatestock` VALUES ('000002', 'Harvey');
INSERT INTO `privatestock` VALUES ('000003', '61990');
INSERT INTO `privatestock` VALUES ('000011', 'ByronDong');
INSERT INTO `privatestock` VALUES ('000011', 'Harvey');
INSERT INTO `privatestock` VALUES ('011111', 'CharlesFeng');
INSERT INTO `privatestock` VALUES ('030020', '高源');
INSERT INTO `privatestock` VALUES ('040078', '高源');

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
INSERT INTO `stock` VALUES ('000001', '1017-01-01', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000002', '1017-01-02', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000003', '1017-01-03', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000004', '1017-01-04', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000005', '1017-01-05', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000006', '1017-01-06', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000007', '1017-01-07', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000008', '1017-01-08', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000009', '1017-01-09', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000010', '1017-01-10', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000011', '1017-01-11', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000012', '1017-01-12', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000013', '1017-01-13', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000014', '1017-01-14', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000015', '1017-01-15', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000016', '1017-01-16', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000017', '1017-01-17', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000018', '1017-01-18', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000019', '1017-01-19', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000020', '1017-01-20', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000021', '1017-01-21', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000022', '1017-01-22', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000023', '1017-01-23', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000024', '1017-01-24', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000025', '1017-01-25', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000026', '1017-01-26', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000027', '1017-01-27', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000028', '1017-01-28', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000029', '1017-01-29', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000030', '1017-01-30', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000031', '1017-01-31', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000032', '1017-02-01', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000033', '1017-02-02', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000034', '1017-02-03', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000035', '1017-02-04', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000036', '1017-02-05', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000037', '1017-02-06', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000038', '1017-02-07', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000039', '1017-02-08', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000040', '1017-02-09', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000041', '1017-02-10', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000042', '1017-02-11', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000043', '1017-02-12', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000044', '1017-02-13', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000045', '1017-02-14', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000046', '1017-02-15', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000047', '1017-02-16', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000048', '1017-02-17', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000049', '1017-02-18', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000050', '1017-02-19', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000051', '1017-02-20', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000052', '1017-02-21', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000053', '1017-02-22', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000054', '1017-02-23', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000055', '1017-02-24', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000056', '1017-02-25', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000057', '1017-02-26', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000058', '1017-02-27', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000059', '1017-02-28', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000060', '1017-03-01', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000061', '1017-03-02', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000062', '1017-03-03', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000063', '1017-03-04', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000064', '1017-03-05', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000065', '1017-03-06', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000066', '1017-03-07', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000067', '1017-03-08', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000068', '1017-03-09', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000069', '1017-03-10', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000070', '1017-03-11', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000071', '1017-03-12', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000072', '1017-03-13', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000073', '1017-03-14', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000074', '1017-03-15', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000075', '1017-03-16', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000076', '1017-03-17', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000077', '1017-03-18', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000078', '1017-03-19', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000079', '1017-03-20', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000080', '1017-03-21', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000081', '1017-03-22', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000082', '1017-03-23', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000083', '1017-03-24', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000084', '1017-03-25', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000085', '1017-03-26', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000086', '1017-03-27', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000087', '1017-03-28', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000088', '1017-03-29', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000089', '1017-03-30', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000090', '1017-03-31', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000091', '1017-04-01', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000092', '1017-04-02', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000093', '1017-04-03', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000094', '1017-04-04', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000095', '1017-04-05', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000096', '1017-04-06', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000097', '1017-04-07', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000098', '1017-04-08', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000099', '1017-04-09', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');
INSERT INTO `stock` VALUES ('000100', '1017-04-10', '1.58', '3.26', '3.31', '3.23', '0', '景兴纸业', '3.29', '1.6', '3.29', '60536');

-- ----------------------------
-- Table structure for `stocksearch`
-- ----------------------------
DROP TABLE IF EXISTS `stocksearch`;
CREATE TABLE `stocksearch` (
  `code` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `firstLetters` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of stocksearch
-- ----------------------------
INSERT INTO `stocksearch` VALUES ('000001', 'sfza', '深发展A');
INSERT INTO `stocksearch` VALUES ('000002', 'hsag', '沪深A股');
INSERT INTO `stocksearch` VALUES ('000003', 'njbg', '南京B股');
INSERT INTO `stocksearch` VALUES ('000011', 'sbbag', '深宝宝A股');

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
INSERT INTO `stocksituation` VALUES ('2015-07-16', '10', '20', '10', '20', '20', '100', '900');
INSERT INTO `stocksituation` VALUES ('2016-02-16', '10', '60', '10', '20', '20', '100', '600');
INSERT INTO `stocksituation` VALUES ('2017-02-12', '10', '20', '10', '20', '20', '100', '100');
INSERT INTO `stocksituation` VALUES ('2017-03-12', '10', '20', '10', '20', '20', '100', '130');

-- ----------------------------
-- Table structure for `tracebackstockpool`
-- ----------------------------
DROP TABLE IF EXISTS `tracebackstockpool`;
CREATE TABLE `tracebackstockpool` (
  `stockCode` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `userName` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`stockCode`,`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of tracebackstockpool
-- ----------------------------
INSERT INTO `tracebackstockpool` VALUES ('000001', 'ByronDong');
INSERT INTO `tracebackstockpool` VALUES ('000001', 'CharlesFeng');
INSERT INTO `tracebackstockpool` VALUES ('000002', 'ByronDong');
INSERT INTO `tracebackstockpool` VALUES ('000002', 'HarveyGong');
INSERT INTO `tracebackstockpool` VALUES ('000003', '61990');
INSERT INTO `tracebackstockpool` VALUES ('000011', 'ByronDong');
INSERT INTO `tracebackstockpool` VALUES ('000011', 'HarveyGong');
INSERT INTO `tracebackstockpool` VALUES ('011111', 'CharlesFeng');
INSERT INTO `tracebackstockpool` VALUES ('030020', '高源');
INSERT INTO `tracebackstockpool` VALUES ('040078', '高源');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userName` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('61990', '345678');
INSERT INTO `user` VALUES ('ByronDong', '123456');
INSERT INTO `user` VALUES ('CharlesFeng47', '901234');
INSERT INTO `user` VALUES ('Harvey Gong', '789012');
