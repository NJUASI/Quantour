package dataHelper.dataHelperImpl.DataSourceDataHelper;

import com.github.stuxuhai.jpinyin.ChineseHelper;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import utilities.IDReserve;
import utilities.StockCodeHelper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建stockName-Code.properties
 */
public class StockNameToCodeCreator {

    final String nameCodeFileParent;

    final String separator = System.getProperty("file.separator");
    final String userID = IDReserve.getInstance().getUserID();

    final String parent;

    private List<String> nameCodeProperties = new ArrayList<>();
    private List<String> shortPinyinTxt = new ArrayList<>();

    public StockNameToCodeCreator(boolean isBaseStock) {
        if (isBaseStock) nameCodeFileParent = "base_stocks";
        else nameCodeFileParent = "stocks";

        parent = System.getProperty("user.dir") + separator + ".attachments" + separator + userID + separator + nameCodeFileParent;

    }

    public void handle() throws IOException, PinyinException {
        reader();
        writerProperties();
        writerTxt();
    }

    private void reader() throws IOException, PinyinException {
        String readerPath = parent + separator + "stock_records_by_code" + separator;

        File parentFile = new File(readerPath);
        String[] fileName = parentFile.list();

        for (String fileNameForRead : fileName) {
            File temp = new File(readerPath + fileNameForRead);


            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(temp), "UTF-8"));
            String line = reader.readLine();

            // 删除StockName中的空格（包括S ST 被替换为SST）
            String tempName = line.split("\t")[9].replace(" ", "");
            String tempCode = fileNameForRead.substring(0, fileNameForRead.length() - 4);

            // 特别处理中小板P为中小板指
            if (tempName.equals("中小板P")) tempName = "中小板指";

            nameCodeProperties.add(tempName + "=" + StockCodeHelper.simplify(tempCode));
            shortPinyinTxt.add(convertShortPinyin(tempName) + "\t" + tempCode + "\t" + tempName);
            reader.close();

        }
    }

    private void writerProperties() throws IOException {
        // 新建相关文件夹
        File writeDesParentPath = new File(parent + separator + "stockName-code");
        writeDesParentPath.mkdirs();

        File file = new File(parent + separator + "stockName-code" + separator + "stockName-code" + ".properties");
        file.createNewFile();

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
        for (String temp : nameCodeProperties) {
            writer.write(temp);
            writer.newLine();
        }
        writer.flush();
        writer.close();
    }

    private void writerTxt() throws IOException {
        File file = new File(parent + separator + "stockName-code" + separator + "shortPinyin" + ".txt");
        file.createNewFile();

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
        for (String temp : shortPinyinTxt) {
            writer.write(temp);
            writer.newLine();
        }
        writer.flush();
        writer.close();
    }

    /**
     * @param stockName 股票名称
     * @return 小写拼音字母
     */
    private String convertShortPinyin(String stockName) throws PinyinException {
        StringBuffer sb = new StringBuffer();

        for (char c : stockName.toCharArray()) {
            if (ChineseHelper.isChinese(c)) sb.append(PinyinHelper.getShortPinyin(String.valueOf(c)));
            else sb.append(c);
        }

        return new String(sb).toLowerCase();
    }
}