package dataHelper.dataHelperImpl;

import dataHelper.DataSourceDataHelper;
import po.StockSituationPO;
import utilities.StockCodeHelper;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by cuihua on 2017/3/30.
 */
public class DataSourceDataHelperImpl implements DataSourceDataHelper {

    @Override
    public boolean upload(String filePath) throws IOException {
        CodeDirCreator creator = new CodeDirCreator(filePath);
        creator.createDir();

        OriginalDataReader reader = new OriginalDataReader(filePath);
        reader.handle();

        DuplicationAdder adder = new DuplicationAdder();
        adder.handle();

        DateFilesCreator creator2 = new DateFilesCreator();
        creator2.handle();

        SituationCreator creator3 = new SituationCreator();
        creator3.handle();
        return true;
    }
}

/**
 * 创建stockName-Code.properties
 */
class StockNameToCodeCreator {

    private String readerPath = "";
    private String writerDesPath = "";
    private String post = ".properties";

    private File[] fileName;
    private ArrayList<String> key = new ArrayList<String>();

    public StockNameToCodeCreator(String readerPath, String writerDesPath) {
        this.readerPath = readerPath;
        this.writerDesPath = writerDesPath;
    }

    public void handle() {
        this.reader();
        this.writer();
    }

    private void writer() {
        File file = new File(writerDesPath + post);
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            for (String temp : this.key) {
                writer.write(temp);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void reader() {
        File tempFile = new File(readerPath);
        fileName = tempFile.listFiles();

        for (File file : fileName) {

            File temp = new File(readerPath + file.getName());

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(temp), "UTF-8"));
                String line = reader.readLine();

                String[] tempString = line.split("\t");

                String tempF = file.getName().substring(0, file.getName().length() - 4);

                this.key.add(tempString[9] + "=" + tempF);
                reader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}


/**
 * 创建stock_records_by_code文件夹
 */
class CodeDirCreator {

    BufferedReader br;

    private List<String> codes = new ArrayList<>();

    public CodeDirCreator(String sourceFile) throws FileNotFoundException, UnsupportedEncodingException {
        br = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFile), "UTF-8"));
    }

    public boolean createDir() throws IOException {
        String thisLine = null;

        while ((thisLine = br.readLine()) != null) {
            if (thisLine.equals("Serial\tDate\tOpen\tHigh\tLow\tClose\tVolume\tAdj Close\tcode\tname\tmarket"))
                continue;
            judgeLine(thisLine);
        }

        create();
        return true;
    }

    private void judgeLine(String line) throws IOException {
        String[] parts = line.split("\t");

        // 去除停牌日的脏数据
        if (parts[6].equals("0")) {
            return;
        }

        // 转化股票代码为6位标准形式
        parts[8] = StockCodeHelper.format(parts[8]);

        if (!codes.contains(parts[8])) {
            codes.add(parts[8]);
        }
    }

    private void create() throws IOException {
        final String separator = System.getProperty("file.separator");
        final String parent = System.getProperty("user.dir") + separator + "attachments" + separator;
        final String post = ".txt";
        final String codeDesFileParentPath = parent + "stock_records_by_code";

        // 建立by_code目录
        for (String thisCode : codes) {
            String codeDesFilePath = codeDesFileParentPath + separator + thisCode + post;
            File codeParent = new File(codeDesFileParentPath);
            if (!codeParent.exists()) {
                codeParent.mkdirs();
            }
            File file = new File(codeDesFilePath);
            if (!file.exists()) {
                file.createNewFile();
            }
        }
    }
}

/**
 * 读取上传数据源中的数据并写入stock_records_by_code文件夹
 */
class OriginalDataReader {

    BufferedReader br;
    BufferedWriter bw;

    final String fileSeparator = System.getProperty("file.separator");
    final String parent = System.getProperty("user.dir") + fileSeparator + "attachments" + fileSeparator;
    final String post = ".txt";
    final String codeDesFileParentPath = parent + "stock_records_by_code";

    private String desCode = "";
    private String codeDesFilePath = null;

    public OriginalDataReader(String sourceFile) throws FileNotFoundException, UnsupportedEncodingException {
        br = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFile), "UTF-8"));
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

            // 转化股票交易量单位为手（1手=100股）
            parts[6] = String.valueOf((int) Double.parseDouble(parts[6]) / 100);

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
}

/**
 * 为stock_records_by_code文件夹中数据写入冗余信息昨日收盘价、昨日复权收盘价
 * <p>
 * TODO 冯俊杰：待涨跌的公式正式确定之后，写入涨跌幅，涨跌额
 */
class DuplicationAdder {

    private BufferedReader br;
    private BufferedWriter bw;

    private String code = "";

    final String fileSeparator = System.getProperty("file.separator");
    final String lineSeparator = System.getProperty("line.separator");
    final String parent = System.getProperty("user.dir") + fileSeparator + "attachments" + fileSeparator;
    final String sourceFilePath = parent + "stock_records_by_code";

    private String previousRecord;
    private String currentRecord;

    private StringBuffer result = new StringBuffer();

    public boolean handle() throws IOException {
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

    private boolean addDuplication(String name) throws IOException {
        previousRecord = readLine(name);

        while ((currentRecord = readLine(name)) != null) {
            String[] parts = currentRecord.split("\t");
            result.append(previousRecord + "\t" + parts[5] + "\t" + parts[7] + "\t" + lineSeparator);
            previousRecord = currentRecord;
        }

        // 最后一条数据，以-1表示不存在比此更早的数据源
        result.append(previousRecord + "\t" + "-1" + "\t" + "-1" + "\t" + lineSeparator);

        bw = new BufferedWriter(new FileWriter(sourceFilePath + fileSeparator + code, false));
        bw.write(result.toString());
        bw.flush();
        bw.close();

        // reset result
        result.setLength(0);
        return true;
    }


    private String readLine(String name) throws IOException {
        if (!name.equals(code)) {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFilePath + fileSeparator + name), "UTF-8"));
            code = name;
        }
        return br.readLine();
    }

}

/**
 * 创建stock_records_by_date、stock_situation文件夹，将stock_records_by_code中数据按照日期再写一遍
 */
class DateFilesCreator {

    BufferedReader br;

    final String fileSeparator = System.getProperty("file.separator");
    final String lineSeparator = System.getProperty("line.separator");
    final String parent = System.getProperty("user.dir") + fileSeparator + "attachments" + fileSeparator;
    final String post = ".txt";
    final String sourcePath = parent + "stock_records_by_code";
    final String dateDesFileParentPath = parent + "stock_records_by_date";
    final String situationDesFileParentPath = parent + "stock_situation";

    Map<LocalDate, StringBuffer> results = new TreeMap<>();

    public boolean handle() throws IOException {
        for (String s : getFileList()) {
            if (!s.equals(".DS_Store")) {
                br = new BufferedReader(new InputStreamReader(new FileInputStream(sourcePath + fileSeparator + s), "UTF-8"));

                String thisLine = null;
                while ((thisLine = br.readLine()) != null) {
                    LocalDate temp = getLocalDateOfRecord(thisLine);
                    if (!results.keySet().contains(temp)) {
                        results.put(temp, new StringBuffer(thisLine + lineSeparator));
                    } else {
                        results.get(temp).append(thisLine + lineSeparator);
                    }
                }
            }
        }

        for (Map.Entry<LocalDate, StringBuffer> entry : results.entrySet()) {
            LocalDate key = entry.getKey();

            // 建立situation和by_date目录
            File situation = new File(situationDesFileParentPath + fileSeparator + key.getYear() + fileSeparator + key.toString() + post);
            File date = new File(dateDesFileParentPath + fileSeparator + key.getYear() + fileSeparator + key.toString() + post);
            if (!situation.exists()) {
                File filePath1 = new File(situationDesFileParentPath + fileSeparator + key.getYear());
                File filePath2 = new File(dateDesFileParentPath + fileSeparator + key.getYear());
                if (!filePath1.exists()) {
                    filePath1.mkdirs();
                    filePath2.mkdirs();
                }
                situation.createNewFile();
                date.createNewFile();
            }

            // 写入数据
            StringBuffer value = entry.getValue();
            BufferedWriter bw = new BufferedWriter(new FileWriter(dateDesFileParentPath + fileSeparator + key.getYear() + fileSeparator + key.toString() + post, true));
            bw.write(value.toString());
            bw.flush();
            bw.close();
        }
        return true;
    }


    private String[] getFileList() {
        return new File(sourcePath).list();
    }

    private LocalDate getLocalDateOfRecord(String thisLine) {
        String[] dateMsg = thisLine.split("\t")[1].split("-");
        return LocalDate.of(Integer.parseInt(dateMsg[0]), Integer.parseInt(dateMsg[1]), Integer.parseInt(dateMsg[2]));
    }
}

/**
 * 为stock_situation文件夹中写入当日的市场温度计
 */
class SituationCreator {

    final String fileSeparator = System.getProperty("file.separator");
    final String parent = System.getProperty("user.dir") + fileSeparator + "attachments" + fileSeparator;
    final String sourcePath = parent + "stock_records_by_date";

    private String[] getFileList() {
        return new File(sourcePath).list();
    }

    public boolean handle() throws IOException {
        // 读取stock_records_by_date里面以股票代码为区分的数据
        BufferedReader br = null;

        for (String s : getFileList()) {
            if (!s.equals(".DS_Store")) {
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