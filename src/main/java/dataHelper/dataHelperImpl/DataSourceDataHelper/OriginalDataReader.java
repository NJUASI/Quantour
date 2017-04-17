package dataHelper.dataHelperImpl.DataSourceDataHelper;

import utilities.StockCodeHelper;

import java.io.*;

/**
 * 读取上传数据源中的数据并写入stock_records_by_code文件夹
 */
public class OriginalDataReader {

    BufferedReader br;
    BufferedWriter bw;

    final String codeDirParent;

    final String fileSeparator = System.getProperty("file.separator");
    final String post = ".txt";

    final String parent;
    final String codeDesFileParentPath;

    private String desCode = "";
    private String codeDesFilePath = null;

    public OriginalDataReader(String sourceFile, boolean isBaseStock) throws FileNotFoundException, UnsupportedEncodingException {
        if (isBaseStock) codeDirParent = "base_stocks";
        else codeDirParent = "stocks";

        br = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFile), "UTF-8"));

        parent = System.getProperty("user.dir") + fileSeparator + ".attachments"  + fileSeparator + codeDirParent+ fileSeparator;
        codeDesFileParentPath = parent + "stock_records_by_code";
    }

    public boolean handle() throws IOException {
        final String lineSeparator = System.getProperty("line.separator");

        String thisLine = null;

        StringBuffer temp = new StringBuffer();
        while ((thisLine = br.readLine()) != null) {
            if (thisLine.equals("Serial\tDate\tOpen\tHigh\tLow\tClose\tVolume\tAdj Close\tcode\tname\tmarket"))
                continue;

            String[] parts = thisLine.split("\t");

            // 去除停牌日的脏数据
            if (parts[6].equals("0")) continue;

            // 转化日期格式
            String thisDate = parts[1];
            String[] dateMsg = thisDate.split("/");
            String originalYear = dateMsg[2];
            String originalMonth = dateMsg[0];
            String originalDay = dateMsg[1];

            String year = convertYear(originalYear);
            String month = formalMonthDay(originalMonth);
            String day = formalMonthDay(originalDay);
            parts[1] = year + "-" + month + "-" + day;

            // 因为项目数据源有问题，这几天的数据其实是没有的，所以需要剔除
            if (isWrongDate(parts[1])) continue;


            // 转化股票交易量单位为手（1手=100股）
            parts[6] = String.valueOf(Long.parseLong(parts[6]) / 100);

            // 转化股票代码为6位标准形式
            parts[8] = StockCodeHelper.format(parts[8]);

            if (!parts[8].equals(desCode)) {
                if (!desCode.equals("")) {
                    write(temp);
                    temp.setLength(0);
                }
                desCode = parts[8];
                codeDesFilePath = codeDesFileParentPath + fileSeparator + desCode + post;
            }

            temp.append(parts[0] + "\t" + parts[1] + "\t" + parts[2] + "\t" + parts[3] + "\t" + parts[4] + "\t" + parts[5] + "\t" + parts[6]
                    + "\t" + parts[7] + "\t" + parts[8] + "\t" + parts[9] + "\t" + parts[10] + lineSeparator);
        }

        // 写入最后一条
        write(temp);

        return true;
    }


    private void write(StringBuffer sb) throws IOException {
        bw = new BufferedWriter(new FileWriter(codeDesFilePath, true));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }


    private String convertYear(String year) {
        // 限于到今年2017年为止100年的数据
        if (Integer.parseInt(year) <= 17) return "20" + year;
        else return "19" + year;
    }

    private String formalMonthDay(String s) {
        if (s.length() == 1) {
            return "0" + s;
        }
        return s;
    }

    private boolean isWrongDate(String dateString) {
        if (dateString.equals("2010-02-15") || dateString.equals("2010-02-16") || dateString.equals("2010-02-17")
                || dateString.equals("2010-02-18") || dateString.equals("2012-01-02") || dateString.equals("2012-01-03")) {
            return true;
        }
        return false;
    }
}