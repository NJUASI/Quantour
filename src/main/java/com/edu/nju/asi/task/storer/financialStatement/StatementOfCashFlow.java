package com.edu.nju.asi.task.storer.financialStatement;

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
public class StatementOfCashFlow implements Statement{

    public void write(List<BasicData> statementOfCashFlow) {
        System.out.println("-----------------现金流量表组合数据开始--------------------");
        for (int i = 0; i < statementOfCashFlow.size(); i++) {
            statementOfCashFlow.get(i).setTotalAssets("0");
            statementOfCashFlow.get(i).setTotalLiabilities("0");
            statementOfCashFlow.get(i).setTotalEquityAttributableToShareholdersOfTheParentCompany("0");
            statementOfCashFlow.get(i).setTotalOwnerEquity("0");
            statementOfCashFlow.get(i).setTotalBusinessIncome("0");
            statementOfCashFlow.get(i).setTotalOperatingCost("0");
            statementOfCashFlow.get(i).setOperatingProfit("0");
            statementOfCashFlow.get(i).setTotalProfit("0");
            statementOfCashFlow.get(i).setNetProfit("0");
            statementOfCashFlow.get(i).setNetProfitAttributableToTheOwnerOfTheParentCompany("0");
            statementOfCashFlow.get(i).setBasicIncomePerStock(0);
            statementOfCashFlow.get(i).setAssetLiabilityRatio(0);
            statementOfCashFlow.get(i).setReturnOnEquity(0);
        }
        System.out.println("-----------------现金流量表组合数据结束--------------------");
        System.out.println("-----------------现金流量表写入数据开始--------------------");
        this.update(statementOfCashFlow);
        System.out.println("-----------------现金流量表写入数据结束--------------------");
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
                    if (reader.get(0).equals("报告日期") || reader.get(0).equals("经营活动产生的现金流量净额(万元)")) {
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

                String netCashFlowsFromOperatingActivities = info.get(1).get(i);
                basicData.setBasicDataID(new BasicDataID(file.getName().substring(0, 6),localDate));
                basicData.setNetCashFlowsFromOperatingActivities(netCashFlowsFromOperatingActivities);
                result.add(basicData);
                System.out.println("转载：" + localDate.toString() + " " + netCashFlowsFromOperatingActivities);
            }
            System.out.println("-----------转载" + file.getName() + "数据结束-----------");
        }
        return result;
    }

    public boolean update(List<BasicData> basicDataList){
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE basicdata SET netCashFlowsFromOperatingActivities = ? WHERE code=? AND date=?";
        boolean result = true;

        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            for (BasicData basicData : basicDataList) {
                preparedStatement.setString(1, basicData.getNetCashFlowsFromOperatingActivities());
                preparedStatement.setString(2, basicData.getBasicDataID().getCode());
                preparedStatement.setObject(3, basicData.getBasicDataID().getDate());
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
