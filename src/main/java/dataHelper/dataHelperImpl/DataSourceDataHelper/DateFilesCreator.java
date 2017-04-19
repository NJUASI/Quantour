package dataHelper.dataHelperImpl.DataSourceDataHelper;

import utilities.IDReserve;

import java.io.*;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

/**
 * 创建stock_records_by_date、stock_situation文件夹，将stock_records_by_code中数据按照日期再写一遍
 */
public class DateFilesCreator {

    BufferedReader br;

    final boolean isBaseStock;
    final String dateFilesParent;

    final String fileSeparator = System.getProperty("file.separator");
    final String lineSeparator = System.getProperty("line.separator");
    final String userID = IDReserve.getInstance().getUserID();
    final String post = ".txt";

    final String parent;
    final String sourcePath;
    final String dateDesFileParentPath;
    final String situationDesFileParentPath;

    Map<LocalDate, StringBuffer> results = new TreeMap<>();

    public DateFilesCreator(boolean isBaseStock) {
        if (isBaseStock) dateFilesParent = "base_stocks";
        else dateFilesParent = "stocks";

        this.isBaseStock = isBaseStock;
        parent = System.getProperty("user.dir") + fileSeparator + ".attachments" + fileSeparator + userID + fileSeparator + dateFilesParent + fileSeparator;
        sourcePath = parent + "stock_records_by_code";
        dateDesFileParentPath = parent + "stock_records_by_date";
        situationDesFileParentPath = parent + "stock_situation";

    }

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

            if (isBaseStock) {
                // 建立by_date目录
                File date = new File(dateDesFileParentPath + fileSeparator + key.getYear() + fileSeparator + key.toString() + post);
                if (!date.exists()) {
                    File filePath2 = new File(dateDesFileParentPath + fileSeparator + key.getYear());
                    if (!filePath2.exists()) {
                        filePath2.mkdirs();
                    }
                    date.createNewFile();
                }
            } else {
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