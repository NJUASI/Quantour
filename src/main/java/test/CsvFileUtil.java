package test;

import java.io.*;

/**
 * Created by cuihua on 2017/3/26.
 */
public class CsvFileUtil {

    public void handle(String source) {

//        final String sourceFilePath = "/Users/cuihua/Documents/大学学习/大二/软件工程与计算三/数据/股票历史数据ALL.csv";

//         第一次遍历：建立by_code, by_date, situation目录，写入by_code
        try {
            Task1 task1 = new Task1(source);
            task1.handle();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 第二次遍历：处理数据，增加冗余信息（昨日昨日收盘价、昨日复权收盘价），写入by_date，重新写入by_code
//        try {
//            Task2 task2 = new Task2();
//            task2.handle();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        // 第三次遍历：形成股票市场温度计

    }
}

/**
 * 第一次遍历：建立by_code, by_code2, by_date, situation目录，写入by_code
 */
class Task1 {

    private BufferedReader br;
    private BufferedWriter bw;

    private String desCode = "";
    private String codeDesFile = null;
    private String codeDesFile2 = null;

//    int count = 1;


    public Task1(String sourceFilePath) throws IOException {
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFilePath), "UTF-8"));
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean handle() throws Exception {
        String thisLine = null;
        while ((thisLine = readLine()) != null) {
            if (thisLine.equals("Serial\tDate\tOpen\tHigh\tLow\tClose\tVolume\tAdj Close\tcode\tname\tmarket")) continue;
            write(thisLine);
        }
        return true;
    }

    private String readLine() throws Exception {
        return br.readLine();
    }

    private void write(String line) throws IOException {
        // 计算总条数验证
//        count++;

        String[] parts = line.split("\t");

        // 去除停牌日的脏数据
        if (parts[6].equals("0")) {
            return;
        }


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

        // 转化股票交易量单位为手（1手=100股）
        parts[6] = String.valueOf((int)Double.parseDouble(parts[6]) / 100);


        final String parent = System.getProperty("user.dir") + "\\attachments\\";
        final String post = ".txt";
        final String codeDesFileParent = parent + "stock_records_by_code";
        final String codeDesFileParent2 = parent + "stock_records_by_code2";
        final String dateDesFileParent = parent + "stock_records_by_date";
        final String situationDesFileParent = parent + "stock_situation";

        if (!parts[8].equals(desCode)) {
            desCode = parts[8];
            codeDesFile = codeDesFileParent + "\\" + desCode + post;
            codeDesFile2 = codeDesFileParent2 + "\\" + desCode + post;
        }

        // 建立by_code, by_code2目录
        File codeParent = new File(codeDesFileParent);
        File codeParent2 = new File(codeDesFileParent2);
        if (!codeParent.exists()) {
            codeParent.mkdirs();
            codeParent2.mkdirs();
        }
        File file = new File(codeDesFile);
        File file2 = new File(codeDesFile2);
        if (!file.exists()) {
            file.createNewFile();
            file2.createNewFile();
        }

        // 建立situation目录，同时建立by_date目录
        File fileName1 = new File(situationDesFileParent + "\\" + year + "\\" + parts[1] + post);
        File fileName2 = new File(dateDesFileParent + "\\" + year + "\\" + parts[1] + post);
        if (!fileName1.exists()) {
            File filePath1 = new File(situationDesFileParent + "\\" + year);
            File filePath2 = new File(dateDesFileParent + "\\" + year);
            if (!filePath1.exists()) {
                filePath1.mkdirs();
                filePath2.mkdirs();
            }
            fileName1.createNewFile();
            fileName2.createNewFile();
        }


        bw = new BufferedWriter(new FileWriter(codeDesFile, true));
        String result = parts[0] + "\t" + parts[1] + "\t" + parts[2] + "\t" + parts[3] + "\t" + parts[4] + "\t" + parts[5] + "\t" + parts[6]
                + "\t" + parts[7] + "\t" + parts[8] + "\t" + parts[9] + "\t" + parts[10];

        bw.write(result);
        bw.newLine();
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
}


/**
 * 第二次遍历：处理数据，增加冗余信息（昨日昨日收盘价、昨日复权收盘价），写入by_date，重新写入by_code
 */
class Task2 {

    // 读取by_code
    private BufferedReader br;

    // 写入by_code2
    private BufferedWriter bw;

    // 写入by_date
    private BufferedWriter bww;

    private String code = "";

    final String parent = System.getProperty("user.dir") + "/attachments/";
    final String sourceFilePath = parent + "stock_records_by_code";
    final String codeDesFilePath = parent + "stock_records_by_code2";

    private String previousRecord;
    private String currentRecord;

    private StringBuffer result = new StringBuffer();


    public boolean handle() throws Exception {
        for (String s : getFileList()) {
            if (!s.equals(".DS_Store")) {
                addDuplication(s);
            }
        }
        br.close();
        return true;
    }


    private String[] getFileList() {
        return new File(sourceFilePath).list();
    }

    private boolean addDuplication(String name) throws Exception {
        previousRecord = readLine(name);

        while ((currentRecord = readLine(name)) != null) {
            String[] parts = currentRecord.split("\t");
            result.append(previousRecord + "\t" + parts[5] + "\t" + parts[7] + "\t");
            result.append('\n');
            previousRecord = currentRecord;
        }

        // 最后一条数据，以-1表示不存在比此更早的数据源
        result.append(previousRecord + "\t" + "-1" + "\t" + "-1" + "\t");
        result.append('\n');
        bw.write(result.toString());
        bw.flush();
        bw.close();

        // reset result
        result.setLength(0);
        return true;
    }


    private String readLine(String name) throws Exception {
        if (!name.equals(code)) {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFilePath + "/" + name), "UTF-8"));
            bw = new BufferedWriter(new FileWriter(codeDesFilePath + "/" + name));
            code = name;
        }
        String readLine = br.readLine();
        return readLine;
    }
}

/**
 * 第三次遍历：形成股票市场温度计
 * TODO ST股票以5%计算
 */
class Task3 {

    // 读取stock_records2里面以股票代码为区分的数据
    private BufferedReader br;

    // 写入stock_situation
    private BufferedWriter bw;

    // 读取stock_situation
    private BufferedReader brr;


    private String code = "";

    final String sourceFilePath = "/Users/cuihua/Documents/大学学习/大二/软件工程与计算三/数据/stock_records2";

    final String desFilePre = "/Users/cuihua/Documents/大学学习/大二/软件工程与计算三/数据/stock_situation";
    final String desFilePost = ".txt";

    public String[] getFileList() {
        return new File(sourceFilePath).list();
    }


    public String readLine() throws Exception {
        String readLine = br.readLine();
        return readLine;
    }


    public boolean handle(String name) throws Exception {
        String thisLine = null;
        while ((thisLine = readLine(name)) != null) {
            write(thisLine);
        }
        return true;
    }

    private String readLine(String name) throws Exception {
        if (!name.equals(code)) {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFilePath + "/" + name), "UTF-8"));
            code = name;
        }

        String readLine = br.readLine();
        return readLine;
    }

    private void write(String line) throws IOException, UnhandleException {
        String thisDate = getDate(line);
        String year = thisDate.split("-")[0];

        File fileName = new File(desFilePre + "/" + year + "/" + thisDate + ".txt");
//		System.out.println("codeDesFilePath: " + codeDesFilePre + "/" + year + "/" + thisDate + ".txt");

        String[] parts = line.split("\t");
        String volume = parts[6];
        double adjRise = Double.valueOf(parts[5]) / Double.valueOf(parts[12]) - 1;
        double rise = (Double.valueOf(parts[2]) - Double.valueOf(parts[5])) / Double.valueOf(parts[11]);

        String wanted = null;

        // 先读取其中文件数据，再进行计算。判断是否符合其中条件
        brr = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));
        String previousStockSituation = brr.readLine();
        brr.close();

        // 涨停股票数，涨幅超过5%的股票数，开盘‐收盘小于‐5% * 上一个交易日收盘价的股票个数
        int a1 = 0, a2 = 0, a3 = 0;
        // 跌停股票数，跌幅超过5%的股票数，开盘‐收盘大于5% * 上一个交易日收盘价的股票个数
        int b1 = 0, b2 = 0, b3 = 0;
        if (previousStockSituation == null) {
            // 其中原无数据
            if (adjRise >= 0.1 && rise < -0.05) {
                a1 = 1;
                a2 = 1;
                a3 = 1;
                b1 = 0;
                b2 = 0;
                b3 = 0;
            } else if (adjRise > 0.1 && rise > 0.05) {
                a1 = 1;
                a2 = 1;
                a3 = 0;
                b1 = 0;
                b2 = 0;
                b3 = 1;
            } else if (adjRise > 0.05 && adjRise < 0.1 && rise < -0.05) {
                a1 = 0;
                a2 = 1;
                a3 = 1;
                b1 = 0;
                b2 = 0;
                b3 = 0;
            } else if (adjRise > 0.05 && adjRise < 0.1 && rise > 0.05) {
                a1 = 0;
                a2 = 1;
                a3 = 0;
                b1 = 0;
                b2 = 0;
                b3 = 1;
            } else if (adjRise <= -0.1 && rise < -0.05) {
                a1 = 0;
                a2 = 0;
                a3 = 1;
                b1 = 1;
                b2 = 1;
                b3 = 0;
            } else if (adjRise <= -0.1 && rise > 0.05) {
                a1 = 0;
                a2 = 0;
                a3 = 0;
                b1 = 1;
                b2 = 1;
                b3 = 1;
            } else if (adjRise < -0.05 && adjRise > -0.1 && rise < -0.05) {
                a1 = 0;
                a2 = 0;
                a3 = 1;
                b1 = 0;
                b2 = 1;
                b3 = 0;
            } else if (adjRise < -0.05 && adjRise > -0.1 && rise > -0.05) {
                a1 = 0;
                a2 = 0;
                a3 = 0;
                b1 = 0;
                b2 = 1;
                b3 = 1;
            } else {
                // 均不需要增加，保持原数据
            }

            wanted = volume + "\t" + a1 + "\t" + b1 + "\t" + a2 + "\t" + b2 + "\t" + a3 + "\t" + b3;
        } else {
            // 其中原有数据，读取后计算后再进行写入
            String[] stockSituationParts = previousStockSituation.split("\t");
            long nowVolume = Long.parseLong(stockSituationParts[0]) + Long.parseLong(volume);

            a1 = Integer.parseInt(stockSituationParts[1]);
            b1 = Integer.parseInt(stockSituationParts[2]);
            a2 = Integer.parseInt(stockSituationParts[3]);
            b2 = Integer.parseInt(stockSituationParts[4]);
            a3 = Integer.parseInt(stockSituationParts[5]);
            b3 = Integer.parseInt(stockSituationParts[6]);

            if (adjRise >= 0.1 && rise < -0.05) {
                a1++;
                a2++;
                a3++;
            } else if (adjRise > 0.1 && rise > 0.05) {
                a1++;
                a2++;
                b3++;
            } else if (adjRise > 0.05 && adjRise < 0.1 && rise < -0.05) {
                a2++;
                a3++;
            } else if (adjRise > 0.05 && adjRise < 0.1 && rise > 0.05) {
                a2++;
                b3++;
            } else if (adjRise <= -0.1 && rise < -0.05) {
                a3++;
                b1++;
                b2++;
            } else if (adjRise <= -0.1 && rise > 0.05) {
                b1++;
                b2++;
                b3++;
            } else if (adjRise < -0.05 && adjRise > -0.1 && rise < -0.05) {
                a3++;
                b2++;
            } else if (adjRise < -0.05 && adjRise > -0.1 && rise > -0.05) {
                b2++;
                b3++;
            } else {
                // 均不需要增加，保持原数据
            }

            wanted = nowVolume + "\t" + a1 + "\t" + b1 + "\t" + a2 + "\t" + b2 + "\t" + a3 + "\t" + b3;
        }
        bw = new BufferedWriter(new FileWriter(fileName));
        bw.write(wanted);
        bw.newLine();
        bw.flush();
        bw.close();
    }


    private String getDate(String record) {
        return record.split("\t")[1];
    }
}