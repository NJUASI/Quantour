package test2;

import java.io.*;

/**
 * Created by cuihua on 2017/3/28.
 */
public class CsvFileUtil {

    String sourceFile;

    public CsvFileUtil(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    public boolean handle() {

//        DirCreator creator = null;
//        try {
//            creator = new DirCreator(sourceFile);
//            creator.createDir();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        try {
            DirCreator2 creator2 = new DirCreator2(sourceFile);
            creator2.createDir();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }
}

class DirCreator {

    BufferedReader br;

    String sourceFile;

    final String separator = System.getProperty("file.separator");
    final String parent = System.getProperty("user.dir") + separator + "attachments" + separator;
    final String post = ".txt";
    final String codeDesFileParentPath = parent + "stock_records_by_code";

    private String desCode = "";
    private String codeDesFilePath = null;

    public DirCreator(String sourceFile) throws FileNotFoundException, UnsupportedEncodingException {
        this.sourceFile = sourceFile;
        br = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFile), "UTF-8"));
    }

    public boolean createDir() throws IOException {
        String thisLine = null;
        while ((thisLine = br.readLine()) != null) {
            if (thisLine.equals("Serial\tDate\tOpen\tHigh\tLow\tClose\tVolume\tAdj Close\tcode\tname\tmarket")) continue;
            write(thisLine);
        }
        return true;
    }

    private void write(String line) throws IOException {
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

        if (!parts[8].equals(desCode)) {
            desCode = parts[8];
            codeDesFilePath = codeDesFileParentPath + separator + desCode + post;
        }

        // 建立by_code目录
        File codeParent = new File(codeDesFileParentPath);
        if (!codeParent.exists()) {
            codeParent.mkdirs();
        }
        File file = new File(codeDesFilePath);
        if (!file.exists()) {
            file.createNewFile();
        }
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


class DirCreator2 {

    BufferedReader br;

    String sourceFile;

    final String separator = System.getProperty("file.separator");
    final String parent = System.getProperty("user.dir") + separator + "attachments" + separator;
    final String post = ".txt";
    final String codeDesFileParentPath = parent + "stock_records_by_code";
    final String dateDesFileParentPath = parent + "stock_records_by_date";
    final String situationDesFileParentPath = parent + "stock_situation";

    private String desCode = "";
    private String codeDesFilePath = null;

    public DirCreator2(String sourceFile) throws FileNotFoundException, UnsupportedEncodingException {
        this.sourceFile = sourceFile;
        br = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFile), "UTF-8"));
    }

    public boolean createDir() throws IOException {
        String thisLine = null;
        while ((thisLine = br.readLine()) != null) {
            if (thisLine.equals("Serial\tDate\tOpen\tHigh\tLow\tClose\tVolume\tAdj Close\tcode\tname\tmarket"))
                continue;
            write(thisLine);
        }
        return true;
    }

    private void write(String line) throws IOException {
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

        if (!parts[8].equals(desCode)) {
            desCode = parts[8];
            codeDesFilePath = codeDesFileParentPath + separator + desCode + post;
        }

        // 建立by_code目录
        File codeParent = new File(codeDesFileParentPath);
        if (!codeParent.exists()) {
            codeParent.mkdirs();
        }
        File file = new File(codeDesFilePath);
        if (!file.exists()) {
            file.createNewFile();
        }

        // 建立situation目录，同时建立by_date目录
        File fileName1 = new File(situationDesFileParentPath + separator + year + separator + parts[1] + post);
        File fileName2 = new File(dateDesFileParentPath + separator + year + separator + parts[1] + post);
        if (!fileName1.exists()) {
            File filePath1 = new File(situationDesFileParentPath + separator + year);
            File filePath2 = new File(dateDesFileParentPath + separator + year);
            if (!filePath1.exists()) {
                filePath1.mkdirs();
                filePath2.mkdirs();
            }
            fileName1.createNewFile();
            fileName2.createNewFile();
        }

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