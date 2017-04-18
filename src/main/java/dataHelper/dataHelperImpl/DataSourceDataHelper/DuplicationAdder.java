package dataHelper.dataHelperImpl.DataSourceDataHelper;

import java.io.*;

/**
 * 为stock_records_by_code文件夹中数据写入冗余信息昨日收盘价、昨日复权收盘价
 * <p>
 * TODO 冯俊杰：待涨跌的公式正式确定之后，再次考虑涨跌幅，涨跌额
 */
public class DuplicationAdder {

    private BufferedReader br;
    private BufferedWriter bw;

    private String code = "";

    final String adderParent;

    final String fileSeparator = System.getProperty("file.separator");
    final String lineSeparator = System.getProperty("line.separator");
    final String parent;
    final String sourceFilePath;

    private String previousRecord;
    private String currentRecord;

    private StringBuffer result = new StringBuffer();

    public DuplicationAdder(boolean isBaseStock) {
        if (isBaseStock) adderParent = "base_stocks";
        else adderParent = "stocks";

        parent = System.getProperty("user.dir") + fileSeparator + ".attachments" + fileSeparator + adderParent + fileSeparator;
        sourceFilePath = parent + "stock_records_by_code";
    }

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
        // 数据源中第一条数据
        previousRecord = readLine(name);
        result.append(previousRecord + "\t" + "-1" + "\t" + "-1" + "\t" + lineSeparator);

        while ((currentRecord = readLine(name)) != null) {
            String[] parts = previousRecord.split("\t");
            result.append(currentRecord + "\t" + parts[5] + "\t" + parts[7] + "\t" + lineSeparator);
            previousRecord = currentRecord;
        }


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