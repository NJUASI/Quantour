package dataHelper.dataHelperImpl;

import dataHelper.StockSituationDataHelper;
import po.StockSituationPO;

import java.io.*;
import java.time.LocalDate;

/**
 * Created by Byron Dong on 2017/3/5.
 */
public class StockSituationDataHelperImpl implements StockSituationDataHelper {

    //未添加日期的文件路径
    private String path ;

    //文件后缀名
    private String suffix ;


    /**
     * @author Byron Dong
     * @updateTime 2017/3/5 构造函数，初始化成员变量path,suffix
     */
    public StockSituationDataHelperImpl(){
        path = "";
        suffix = ".txt";
    }

    /**
     * @author Byron Dong
     * @updateTime 2017/3/5 构造函数，初始化成员变量path,suffix(自定义)
     */
    public StockSituationDataHelperImpl(String path, String suffix) {
        this.path = path;
        this.suffix = suffix;
    }

    /**
     * 获取指定日期所有数据
     *
     * @author Byron Dong
     * @updateTime 2017/3/5
     * @param date  指定日期
     * @return StockSituationPO 指定市场温度计数据
     */
    @Override
    public StockSituationPO getStockSituation(LocalDate date) {
        String resultPath = this.path+date.toString()+this.suffix;

        return this.reader(resultPath);
    }

    /**
     * 从文件中获取指定日期市场温度计的数据
     *
     * @author Byron Dong
     * @updateTime 2017/3/5
     * @param path 文件路径
     * @return StockSituationPO 指定市场温度计数据
     */
    private StockSituationPO reader(String path){
        File file = new File(path);
        StockSituationPO result = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = reader.readLine();
            reader.close();
            result = new StockSituationPO(line.split("   "));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File is not existed");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO exception");
        }

        return result;
    }
}
