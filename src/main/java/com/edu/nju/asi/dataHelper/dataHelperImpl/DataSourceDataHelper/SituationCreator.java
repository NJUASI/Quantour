package com.edu.nju.asi.dataHelper.dataHelperImpl.DataSourceDataHelper;

import com.edu.nju.asi.po.StockSituationPO;
import com.edu.nju.asi.utilities.IDReserve;

import java.io.*;
import java.time.LocalDate;

/**
 * 为stock_situation文件夹中写入当日的市场温度计
 */
public class SituationCreator {

    final String fileSeparator = System.getProperty("file.separator");
    final String userID = IDReserve.getInstance().getUserID();
    final String parent = System.getProperty("user.dir") + fileSeparator + ".attachments" + fileSeparator + userID + fileSeparator +  "stocks" + fileSeparator;
    final String sourcePath = parent + "stock_records_by_date";

    private String[] getFileList() {
        return new File(sourcePath).list();
    }

    public boolean handle() throws IOException {
        // 读取stock_records_by_date里面以股票代码为区分的数据
        BufferedReader br = null;

        for (String s : getFileList()) {
            if (!s.equals(".DS_Store") && !s.equals("all_dates.txt")) {
                File source2 = new File(sourcePath + fileSeparator + s);
                for (String s2 : source2.list()) {
                    LocalDate nowLocalDate = getLocalDateOfFileName(s2);
                    StockSituationPO nowSituation = new StockSituationPO();

                    br = new BufferedReader(new InputStreamReader(new FileInputStream(sourcePath + fileSeparator + s + fileSeparator + s2), "UTF-8"));

                    String thisLine = null;
                    while ((thisLine = br.readLine()) != null) {
                        String[] parts = thisLine.split("\t");
                        nowSituation = nowSituation.plus(calculateRecord(thisLine));
                    }

                    write(nowLocalDate, nowSituation);
                }
            }
        }
        br.close();
        return true;
    }

    private StockSituationPO calculateRecord(String line) throws IOException {
        String[] parts = line.split("\t");

        if (parts[11].equals("-1")) {
            return new StockSituationPO(parts[6], 0, 0, 0, 0, 0, 0);
        }

        double adjRise = Double.valueOf(parts[7]) / Double.valueOf(parts[12]) - 1;
        double rise = (Double.valueOf(parts[2]) - Double.valueOf(parts[5])) / Double.valueOf(parts[11]);

        // 涨停股票数，涨幅超过5%的股票数，开盘‐收盘小于‐5% * 上一个交易日收盘价的股票个数
        int a1 = 0, a2 = 0, a3 = 0;
        // 跌停股票数，跌幅超过5%的股票数，开盘‐收盘大于5% * 上一个交易日收盘价的股票个数
        int b1 = 0, b2 = 0, b3 = 0;

        double margin = 0;
        if (parts[8].contains("ST")) {
            // ST股票涨跌幅超5%即涨跌停
            margin = 0.05;
        } else {
            margin = 0.1;
        }

        // 涨跌停
        if (adjRise >= margin) {
            a1 = 1;
        } else if (adjRise <= -margin) {
            b1 = 1;
        }

        // 涨跌幅超5%
        if (adjRise > 0.05) {
            a2 = 1;
        } else if (adjRise < -0.05) {
            b2 = 1;
        }

        // 开盘‐收盘小于‐5% * 上一个交易日收盘价的股票个数
        if (rise < -0.05) {
            a3 = 1;
        } else if (rise > 0.05) {
            b3 = 1;
        }

        return new StockSituationPO(parts[6], a1, b1, a2, b2, a3, b3);

    }

    private void write(LocalDate localDate, StockSituationPO po) throws IOException {
        // 写入stock_situation
        BufferedWriter bw;

        final String situationDesFileParentPath = parent + "stock_situation" + fileSeparator + localDate.getYear() + fileSeparator + localDate + ".txt";

        bw = new BufferedWriter(new FileWriter(situationDesFileParentPath));
        bw.write(po.getVolume() + "\t" + po.getLimitUpNum() + "\t" + po.getLimitDownNum() + "\t" + po.getSurgingNum() + "\t" +
                po.getSlumpingNum() + "\t" + po.getClimbingNum() + "\t" + po.getSlipingNum());
        bw.flush();
        bw.close();
    }

    private LocalDate getLocalDateOfFileName(String fileName) {
        String[] dateMsg = fileName.split("-");
        return LocalDate.of(Integer.parseInt(dateMsg[0]), Integer.parseInt(dateMsg[1]), Integer.parseInt(dateMsg[2].substring(0, 2)));
    }
}