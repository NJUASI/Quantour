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
public class RepaymentAbility implements Statement{

    public void write(List<BasicData> repaymentAbility) {
        System.out.println("-----------------偿还能力组合数据开始--------------------");
        for (int i = 0; i < repaymentAbility.size(); i++) {
            repaymentAbility.get(i).setTotalAssets("0");
            repaymentAbility.get(i).setTotalLiabilities("0");
            repaymentAbility.get(i).setTotalEquityAttributableToShareholdersOfTheParentCompany("0");
            repaymentAbility.get(i).setTotalOwnerEquity("0");
            repaymentAbility.get(i).setTotalBusinessIncome("0");
            repaymentAbility.get(i).setTotalOperatingCost("0");
            repaymentAbility.get(i).setOperatingProfit("0");
            repaymentAbility.get(i).setTotalProfit("0");
            repaymentAbility.get(i).setNetProfit("0");
            repaymentAbility.get(i).setNetProfitAttributableToTheOwnerOfTheParentCompany("0");
            repaymentAbility.get(i).setBasicIncomePerStock(0);
            repaymentAbility.get(i).setNetCashFlowsFromOperatingActivities("0");
            repaymentAbility.get(i).setNetDebtRatio(0);
        }
        System.out.println("-----------------偿还能力组合数据结束--------------------");
        System.out.println("-----------------偿还能力写入数据开始--------------------");
        this.update(repaymentAbility);
        System.out.println("-----------------偿还能力写入数据结束--------------------");
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
                    if (reader.get(0).equals("报告日期") || reader.get(0).equals("资产负债率(%)")) {
                        info.add(this.readInfo(reader, reader.getColumnCount()));
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
                LocalDate localDate = LocalDate.parse(format(info.get(0).get(i)));
                if (!isTrueDate(localDate)) {
                    continue;
                }
                String assetLiabilityRatio = info.get(1).get(i);

                basicData.setBasicDataID(new BasicDataID(getQuarter(localDate.getMonthValue()),
                        localDate.getYear(), file.getName().substring(0, 6)));
                basicData.setAssetLiabilityRatio(Double.parseDouble(assetLiabilityRatio));
                result.add(basicData);
                System.out.println("转载：" + localDate.toString() + " " + assetLiabilityRatio);
            }
            System.out.println("-----------转载" + file.getName() + "数据结束-----------");
        }
        return result;
    }

    public boolean update(List<BasicData> basicDataList) {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE basicdata SET assetLiabilityRatio=? WHERE code=? AND year=? AND quarter=?";
        boolean result = true;

        try {
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);
            for (BasicData basicData : basicDataList) {
                preparedStatement.setDouble(1, basicData.getAssetLiabilityRatio());
                preparedStatement.setString(2, basicData.getBasicDataID().getCode());
                preparedStatement.setInt(3, basicData.getBasicDataID().getYear());
                preparedStatement.setInt(4, basicData.getBasicDataID().getQuarter());
                System.out.println(basicData.getBasicDataID().getCode() + " " + basicData.getBasicDataID().getYear() + " " + basicData.getBasicDataID().getQuarter());
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

    private List<String> readInfo(CsvReader reader, int n) {
        List<String> result = new ArrayList<>();
        for (int i = 1; i < n - 1; i++) {
            try {
                if (reader.get(i).equals("--")) {
                    result.add("0");
                } else {
                    result.add(reader.get(i));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private int getQuarter(int num) {
        int month = num;

        if (month >= 1 && month <= 3) {
            return 1;
        } else if (month >= 4 && month <= 6) {
            return 2;
        } else if (month >= 7 && month <= 9) {
            return 3;
        } else if (month >= 10 && month <= 12) {
            return 4;
        }
        return 0;
    }

    private static String format(String date) {
        if (date.indexOf("/") != -1) {
            String temp[] = date.split("/");
            if (temp[1].length() == 1) {
                temp[1] = "0" + temp[1];
            }

            if (temp[2].length() == 1) {
                temp[2] = "0" + temp[1];
            }
            return temp[0] + "-" + temp[1] + "-" + temp[2];
        }
        return date;
    }

    private boolean isTrueDate(LocalDate localDate) {
        if (localDate.isEqual(LocalDate.of(localDate.getYear(), 12, 31)) ||
                localDate.isEqual(LocalDate.of(localDate.getYear(), 9, 30)) ||
                localDate.isEqual(LocalDate.of(localDate.getYear(), 6, 30)) ||
                localDate.isEqual(LocalDate.of(localDate.getYear(), 3, 31))) {
            return true;
        } else {
            return false;
        }
    }
}
