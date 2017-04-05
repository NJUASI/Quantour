package dataHelper.dataHelperImpl;

import dataHelper.StockDataHelper;
import po.StockPO;
import utilities.StockCodeHelper;
import utilities.enums.Market;

import java.io.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Byron Dong on 2017/3/5.
 * Last updated by cuihua
 * Update time 2017/3/18
 *
 * 对getFirstDay接口理解错误，重新实现
 */
public class StockDataHelperImpl implements StockDataHelper {

    private final static String stockRecordByCodePathPre = "stock_records_by_code/";
    private final static String stockRecordByDatePathPre = "stock_records_by_date/";
    private final static String stockRecordPathPost = ".txt";

    private BufferedReader br;

    static int count = 0;

    /**
     * 获取指定股票所有数据
     *
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/9
     * @param stockCode  指定股票代码
     * @return 指定股票所有数据
     * @throws IOException IO
     */
    @Override
    public List<StockPO> getStockRecords(String stockCode) throws IOException {
        return getStockByPath(stockRecordByCodePathPre + StockCodeHelper.simplify(stockCode) + stockRecordPathPost);
    }

    /**
     * 获取指定日期的所有股票数据
     *
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/9
     * @param date  指定日期
     * @return 指定日期的所有股票数据
     * @throws IOException IO
     */
    @Override
    public List<StockPO> getStockRecords(LocalDate date) throws IOException {
        return getStockByPath(stockRecordByDatePathPre + date.getYear() + "/" + date.toString() + stockRecordPathPost);
    }

    /**
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/9
     * @param stockCode 股票代码
     * @return 数据库中股票存在记录的起讫时间，List.get(0)为第一天，List.get(1)为最后一天
     * @throws IOException IO
     */
    @Override
    public List<LocalDate> getFirstAndLastDay(String stockCode) throws IOException {
        br = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().
                getResourceAsStream(stockRecordByCodePathPre + StockCodeHelper.simplify(stockCode) + stockRecordPathPost)));

        List<StockPO> allResult = getStockRecords(stockCode);

        List<LocalDate> result = new LinkedList<>();
        result.add(allResult.get(allResult.size()-1).getDate());
        result.add(allResult.get(0).getDate());
        return result;
    }

    /**
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/23
     * @param stockCode 股票代码
     * @return 此年份此股票需要被剔除的所有日期
     */
    @Override
    public List<LocalDate> getDateWithoutData(String stockCode) throws IOException {
        List<LocalDate> dates = new LinkedList<>();
        LocalDate temp = getFirstAndLastDay(stockCode).get(0);
        LocalDate end = getFirstAndLastDay(stockCode).get(1);

        // 先加入所有目标可能的日期
        while (!dateEquals(temp, end)) {
            dates.add(temp);
            temp = temp.plusDays(1);
        }

        // 再剔除有数据的日期
        List<StockPO> result = getStockRecords(stockCode);
        for (StockPO stock : result) {
            dates.remove(stock.getDate());
        }

        return dates;
    }

    /**
     * 若参照日期为交易日，则返回参照日期;否则返回参照日期前的一个交易日
     *
     * @param date 参照日期
     * @param stockCode
     * @return LocalDate
     */
    @Override
    public LocalDate getLastTradingDay(LocalDate date, String stockCode) throws IOException {

        //默认值为参照日期
        LocalDate result = date;

        br = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().
                getResourceAsStream(stockRecordByCodePathPre + StockCodeHelper.simplify(stockCode) + stockRecordPathPost)));

        List<StockPO> allResult = getStockRecords(stockCode);

        List<LocalDate> firstAndLastDay = getFirstAndLastDay(stockCode);
        LocalDate firstDay = firstAndLastDay.get(0);
        LocalDate lastDay = firstAndLastDay.get(1);

        //第一天到参照日期的距离近
        if(firstDay.until(date).getDays() < lastDay.until(date).getDays()){
            for (int i = allResult.size()-1; i >= 0; i--){
                if(allResult.get(i).getDate().isEqual(date)){
                    break;
                }
                else if(allResult.get(i).getDate().isAfter(date)){
                    result = allResult.get(i-1).getDate();
                    break;
                }
            }
        }
        else{
            for(int i = 0; i < allResult.size(); i++){
                if(allResult.get(i).getDate().isEqual(date)){
                    break;
                }
                else if(allResult.get(i).getDate().isBefore(date)){
                    result = allResult.get(i).getDate();
                    break;
                }
            }
        }
        return result;
    }

    /**
     * 根据路径读取stock_records_by_code/date中的数据
     *
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/9
     * @param path 要读取的数据源
     * @return 根据俄参数路径读取到的所有股票数据
     * @throws IOException IO
     */
    private List<StockPO> getStockByPath(String path) throws IOException {
        br = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().
                getResourceAsStream(path), "UTF-8"));

        List<StockPO> result = new LinkedList<StockPO>();

        String line = null;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\t");
            int year = Integer.parseInt(parts[1].split("-")[0]);
            int month = Integer.parseInt(parts[1].split("-")[1]);
            int day = Integer.parseInt(parts[1].split("-")[2]);
            LocalDate thisDate = LocalDate.of(year, month, day);

            result.add(new StockPO(Integer.parseInt(parts[0]), thisDate, Double.parseDouble(parts[2]),
                    Double.parseDouble(parts[3]), Double.parseDouble(parts[4]), Double.parseDouble(parts[5]), parts[6],
                    Double.parseDouble(parts[7]), parts[8], parts[9], Market.getEnum(parts[10]),
                    Double.parseDouble(parts[11]), Double.parseDouble(parts[12])));
        }

        // 测试在项目jar包外部署路径
//        String kkk = System.getProperty("user.dir");
//        count++;
//        String k = kkk + "/test/" + count + ".txt";
//        File file = new File(k);
//        String parentPath = kkk + "/test";
//        File parent = new File(parentPath);
//        if (!parent.exists()) {
//            parent.mkdirs();
//        }
//
//        file.createNewFile();


        return result;
    }


    private boolean dateEquals(LocalDate date1, LocalDate date2) {
        if (date1.getYear() == date2.getYear() && date1.getDayOfYear() == date2.getDayOfYear()) return true;
        else return false;
    }
}
