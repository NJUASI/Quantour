package utilities;

import java.io.*;
import java.time.LocalDate;

/**
 * Created by cuihua on 2017/4/16.
 *
 * 基准股票数据放在以filePath为父目录的文件夹下
 */
public class BaseStockCSVStandardizer {

    final String fileSeparator = System.getProperty("file.separator");
    final String lineSeparator = System.getProperty("line.separator");

    final LocalDate start = LocalDate.of(2005, 2,1);
    final LocalDate end = LocalDate.of(2014, 4, 29);

    String filePath;

    StringBuffer result = new StringBuffer();


    public boolean standardize(String filePath) throws IOException {
        this.filePath = filePath;
        File parentFile = new File(filePath);
        for (String s : parentFile.list()) {
            if (!s.equals(".DS_Store")) {
                myStandard(filePath + fileSeparator + s);
            }
        }

        return write();
    }

    private boolean myStandard(String filaParentPath) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filaParentPath), "GBK"));

        String line = null;
        int serial = 0;
        while ((line = br.readLine()) != null) {
            if (line.equals("日期,股票代码,名称,收盘价,最高价,最低价,开盘价,前收盘,涨跌额,涨跌幅,成交量,成交金额")) {
                continue;
            }
            String[] parts = line.split(",");

            LocalDate tempDate = convertLocalDate(parts[0]);

            if (!isDateWithinWanted(start, end, tempDate)) {
                continue;
            }

            String date = parts[0];
            String[] dateParts = date.split("-");
            date = Integer.parseInt(dateParts[1]) + "/" + Integer.parseInt(dateParts[2]) + "/" + dateParts[0].substring(2);

            result.append(serial + "\t" + date + "\t" + parts[6] + "\t" + parts[4] + "\t" + parts[5] + "\t" + parts[3] + "\t" + parts[10] + "00" + "\t"
                    + parts[3] + "\t" + parts[1].substring(1) + "\t" + parts[2] + "\t" + "SZ" + lineSeparator);
        }
        return true;
    }

    private boolean write() throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
                filePath + fileSeparator + "base_stocks.csv", false), "UTF-8"));
        bw.write(result.toString());
        bw.flush();
        bw.close();
        return true;
    }

    private LocalDate convertLocalDate(String formated) {
        // formated as 2011-01-18.txt
        String[] parts = formated.split("-");
        return LocalDate.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2].substring(0, parts[2].length())));
    }

    private boolean isDateWithinWanted(LocalDate start, LocalDate end, LocalDate now) {
        if (now.isEqual(start) || now.isEqual(end)) {
            return true;
        }
        if (now.isAfter(start) && now.isBefore(end)) {
            return true;
        }
        return false;
    }
}
