package dataHelper.dataHelperImpl.DataSourceDataHelper;

import utilities.StockCodeHelper;

import java.io.*;
import java.util.ArrayList;

/**
 * 创建stockName-Code.properties
 */
public class StockNameToCodeCreator {

    final String nameCodeFileParent;

    final String separator = System.getProperty("file.separator");
    final String post = ".properties";

    final String parent;

    private ArrayList<String> key = new ArrayList<String>();

    public StockNameToCodeCreator(boolean isBaseStock) {
        if (isBaseStock) nameCodeFileParent = "base_stocks";
        else nameCodeFileParent = "stocks";

        parent = System.getProperty("user.dir") + separator + ".attachments" + separator + nameCodeFileParent;

    }

    public void handle() {
        this.reader();
        this.writer();
    }

    private void writer() {
        try {
            // 新建相关文件夹
            File writeDesParentPath = new File(parent + separator + "stockName-code");
            writeDesParentPath.mkdirs();

            String writerDesPath = parent + separator + "stockName-code" + separator + "stockName-code" + post;
            writeDesParentPath.createNewFile();

            File file = new File(writerDesPath);
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
        String readerPath = parent + separator + "stock_records_by_code" + separator;

        File parentFile = new File(readerPath);
        String[] fileName = parentFile.list();

        for (String fileNameForRead : fileName) {
            File temp = new File(readerPath + fileNameForRead);

            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(temp), "UTF-8"));
                String line = reader.readLine();

                // 删除StockName中的空格（包括S ST 被替换为SST）
                String tempName = line.split("\t")[9].replace(" ", "");
                String tempCode = fileNameForRead.substring(0, fileNameForRead.length() - 4);

                this.key.add(tempName + "=" + StockCodeHelper.simplify(tempCode));
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}