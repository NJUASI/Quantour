package dataHelper.dataHelperImpl.DataSourceDataHelper;

import utilities.IDReserve;
import utilities.StockCodeHelper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建stock_records_by_code文件夹，同时创建数据源附属信息
 */
public class CodeDirCreator {

    BufferedReader br;

    String sourceFile;
    private List<String> codes = new ArrayList<>();

    final boolean isBaseStock;
    final String codeDirParent;

    public CodeDirCreator(String sourceFile, boolean isBaseStock) throws FileNotFoundException, UnsupportedEncodingException {
        if (isBaseStock) codeDirParent = "base_stocks";
        else codeDirParent = "stocks";

        this.isBaseStock = isBaseStock;
        this.sourceFile = sourceFile;
        br = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFile), "UTF-8"));
    }

    public boolean createDir() throws IOException {
        // 创建目录
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
        final String parent = System.getProperty("user.dir") + separator + ".attachments" + separator + codeDirParent + separator;
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

        if (!isBaseStock) {
            // 创建数据源附属信息
            File dataSourceInfo = new File(parent + "info" + post);
            dataSourceInfo.createNewFile();
            File sourceFile = new File(this.sourceFile);
            long timeDistance = sourceFile.lastModified();

            BufferedWriter bw = new BufferedWriter(new FileWriter(dataSourceInfo, false));
            bw.write(this.sourceFile + '\t' + timeDistance + '\t' + IDReserve.getInstance().getUserID());
            bw.flush();
            bw.close();
        }
    }
}