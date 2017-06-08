package com.edu.nju.asi.crawler.StoreDataHelper.financialStatement;

import com.csvreader.CsvReader;
import com.edu.nju.asi.model.BasicData;
import com.edu.nju.asi.model.BasicDataID;
import com.edu.nju.asi.utilities.util.JDBCUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Byron Dong on 2017/6/5.
 */
public class ProfitStatement implements Statement{

    public void write(List<BasicData> profitStatement) {
        System.out.println("-----------------利润表组合数据开始--------------------");
        for (int i = 0; i < profitStatement.size(); i++) {
            profitStatement.get(i).setTotalAssets("0");
            profitStatement.get(i).setTotalLiabilities("0");
            profitStatement.get(i).setTotalEquityAttributableToShareholdersOfTheParentCompany("0");
            profitStatement.get(i).setTotalOwnerEquity("0");
            profitStatement.get(i).setNetCashFlowsFromOperatingActivities("0");
            profitStatement.get(i).setAssetLiabilityRatio(0);
            profitStatement.get(i).setReturnOnEquity(0);
        }
        System.out.println("-----------------利润表组合数据结束--------------------");
        System.out.println("-----------------利润表写入数据开始--------------------");
        this.update(profitStatement);
        System.out.println("-----------------利润表写入数据结束--------------------");
    }

    public List<BasicData> get(String path) {
        File dir = new File(path);
        File files[] = dir.listFiles();
        List<BasicData> result = new ArrayList<>();

        System.out.println("-----------开始读入数据-------------");
        for (File file : files) {
            System.out.println("----------开始读入" + file.getName() + "-----------");
            List<List<String>> info = new ArrayList<>();
            try {
                CsvReader reader = new CsvReader(path + File.separator + file.getName(), ',', Charset.forName("GBK"));
                while (reader.readRecord()) {
                    if (reader.get(0).equals("报告日期") || reader.get(0).equals("营业总收入(万元)") ||
                            reader.get(0).equals("营业总成本(万元)") || reader.get(0).equals("营业利润(万元)") ||
                            reader.get(0).equals("利润总额(万元)") || reader.get(0).equals("净利润(万元)") ||
                            reader.get(0).equals("归属于母公司所有者的净利润(万元)") || reader.get(0).equals("基本每股收益")) {
                        info.add(StatementUtil.readInfo(reader, reader.getColumnCount()));
                    }
                }
                System.out.println("-----------读入" + file.getName() + "数据完成-----------");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("-----------开始转载" + file.getName() + "数据-----------");
            for (int i = 0; i < info.get(0).size(); i++) {
                BasicData basicData = new BasicData();
                LocalDate localDate = LocalDate.parse(StatementUtil.format(info.get(0).get(i)));
                if (!StatementUtil.isTrueDate(localDate)) {
                    continue;
                }
                String totalBusinessIncome = info.get(1).get(i);
                String totalOperatingCost = info.get(2).get(i);
                String operatingProfit = info.get(3).get(i);
                String totalProfit = info.get(4).get(i);
                String netProfit = info.get(5).get(i);
                String netProfitAttributableToTheOwnerOfTheParentCompany = info.get(6).get(i);
                String basicIncomePerStock = info.get(7).get(i);
                basicData.setBasicDataID(new BasicDataID(file.getName().substring(0, 6),localDate));
                basicData.setTotalBusinessIncome(totalBusinessIncome);
                basicData.setTotalOperatingCost(totalOperatingCost);
                basicData.setOperatingProfit(operatingProfit);
                basicData.setTotalProfit(totalProfit);
                basicData.setNetProfit(netProfit);
                basicData.setNetProfitAttributableToTheOwnerOfTheParentCompany(netProfitAttributableToTheOwnerOfTheParentCompany);
                basicData.setBasicIncomePerStock(Double.parseDouble(basicIncomePerStock));
                result.add(basicData);
                System.out.println("转载：" + localDate.toString() + " " + totalBusinessIncome + " " + totalOperatingCost + " " +
                        operatingProfit + " " + totalProfit + " " + netProfit + " " + netProfitAttributableToTheOwnerOfTheParentCompany + " " + basicIncomePerStock);
            }
            System.out.println("-----------转载" + file.getName() + "数据结束-----------");
        }
        return result;
    }

    public boolean update(List<BasicData> basicDataList){
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE basicdata SET totalBusinessIncome = ?,totalOperatingCost = ?," +
                "operatingProfit=?,totalProfit=?,netProfit=?," +
                "netProfitAttributableToTheOwnerOfTheParentCompany=?,basicIncomePerStock=? " +
                "WHERE code=? AND date=?";
        boolean result = true;

        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            for (BasicData basicData : basicDataList) {
                preparedStatement.setString(1, basicData.getTotalBusinessIncome());
                preparedStatement.setString(2, basicData.getTotalOperatingCost());
                preparedStatement.setString(3, basicData.getOperatingProfit());
                preparedStatement.setString(4, basicData.getTotalProfit());
                preparedStatement.setString(5, basicData.getNetProfit());
                preparedStatement.setString(6, basicData.getNetProfitAttributableToTheOwnerOfTheParentCompany());
                preparedStatement.setDouble(7, basicData.getBasicIncomePerStock());
                preparedStatement.setString(8, basicData.getBasicDataID().getCode());
                preparedStatement.setObject(9, basicData.getBasicDataID().getDate());
                System.out.println(basicData.getBasicDataID().getCode() + " " + basicData.getBasicDataID().getDate().toString());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            result = false;
        } finally {
            JDBCUtil.close(preparedStatement, connection);
        }
        return result;
    }
}
