package dataHelper.dataHelperImpl;

import dataHelper.SearchDataHelper;
import dataHelper.StockDataHelper;
import po.StockPO;
import utilities.*;
import utilities.enums.BlockType;
import utilities.enums.DataSourceState;
import utilities.enums.Market;
import utilities.exceptions.UnhandleBlockTypeException;
import vo.StockPoolVO;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Byron Dong on 2017/3/5.
 * Last updated by cuihua
 * Update time 2017/3/18
 * <p>
 * 对getFirstDay接口理解错误，重新实现
 */
public class StockDataHelperImpl implements StockDataHelper {

    private static final String separator = System.getProperty("file.separator");

    private static final String notBaseStockParent = "stocks";
    private static final String isBaseStockParent = "base_stocks";

    private static final String stockRecordByCodePathPre = separator + "stock_records_by_code" + separator;
    private static final String stockRecordByDatePathPre = separator + "stock_records_by_date" + separator;
    private static final String stockRecordPathPost = ".txt";

    private BufferedReader br;
    private SearchDataHelper searchDataHelper;

    private final List<String> baseStocks;

    public StockDataHelperImpl() {
        searchDataHelper = new SearchDataHelperImpl();

        // 格式化为6位标准形式
        baseStocks = searchDataHelper.getAllBaseStockCodes();
        for (int i = 0; i < baseStocks.size(); i++) {
            baseStocks.set(i, StockCodeHelper.format(baseStocks.get(i)));
        }
    }

    /**
     * 获取指定股票所有数据
     *
     * @param stockCode 指定股票代码
     * @return 指定股票所有数据
     * @throws IOException IO
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/9
     */
    @Override
    public List<StockPO> getStockRecords(String stockCode) throws IOException {
        if (isBaseStock(stockCode))
            return getStockByPath(isBaseStockParent + stockRecordByCodePathPre + stockCode + stockRecordPathPost);
        else return getStockByPath(notBaseStockParent + stockRecordByCodePathPre + stockCode + stockRecordPathPost);
    }

    /**
     * 获取指定日期的所有股票数据
     *
     * @param date 指定日期
     * @return 指定日期的所有股票数据
     * @throws IOException IO
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/9
     */
    @Override
    public List<StockPO> getStockRecords(LocalDate date) throws IOException {
        List<StockPO> result = getStockByPath(notBaseStockParent + stockRecordByDatePathPre + date.getYear() + separator + date.toString() + stockRecordPathPost);
        result.addAll(getStockByPath(isBaseStockParent + stockRecordByDatePathPre + date.getYear() + separator + date.toString() + stockRecordPathPost));
        return result;
    }

    /**
     * @param stockCode 股票代码
     * @return 数据库中股票存在记录的起讫时间，List.get(0)为第一天，List.get(1)为最后一天
     * @throws IOException IO
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/9
     */
    @Override
    public List<LocalDate> getFirstAndLastDay(String stockCode) throws IOException {
        List<StockPO> allResult = getStockRecords(stockCode);

        List<LocalDate> result = new LinkedList<>();
        result.add(allResult.get(0).getDate());
        result.add(allResult.get(allResult.size() - 1).getDate());
        return result;
    }

    /**
     * @param stockCode 股票代码
     * @return 此年份此股票需要被剔除的所有日期
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/23
     */
    @Override
    public List<LocalDate> getDateWithoutData(String stockCode) throws IOException {
        List<StockPO> result = getStockRecords(stockCode);

        List<LocalDate> dates = new LinkedList<>();

        LocalDate temp = result.get(0).getDate();
        LocalDate end = result.get(result.size() - 1).getDate();

        // 先加入所有目标可能的日期
        while (!temp.isEqual(end)) {
            dates.add(temp);
            temp = temp.plusDays(1);
        }

        // 再剔除有数据的日期
        for (StockPO stock : result) {
            dates.remove(stock.getDate());
        }

        return dates;
    }

    @Override
    public List<LocalDate> getDateWithData() throws IOException {
        List<LocalDate> dates = new LocalDateList();

        final String directPath = isBaseStockParent + separator + "stock_records_by_date" + separator + "all_dates" + stockRecordPathPost;
        BufferedReader br = null;
        if (DataSourceStateKeeper.getInstance().getState() == DataSourceState.ORIGINAL) {
            br = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().
                    getResourceAsStream(directPath), "UTF-8"));
        } else if (DataSourceStateKeeper.getInstance().getState() == DataSourceState.USER) {
            final String path = System.getProperty("user.dir") + separator + ".attachments" + separator +
                    IDReserve.getInstance().getUserID() + separator + directPath;
            br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));

        }

        String line;
        while ((line = br.readLine()) != null) {
            dates.add(convertLocalDate(line));
        }

        dates.sort(new LocalDateComparator());
        return dates;
    }

    @Override
    public List<StockPoolVO> getAllStockPool() throws IOException, UnhandleBlockTypeException {
        List<StockPoolVO> result = new LinkedList<>();

        Map<String, String> codeName = searchDataHelper.getAllStocksCode();
        List<String> stockCodes = new ArrayList<>(codeName.keySet());
        List<String> stockNames = new ArrayList<>(codeName.values());

        stockCodes.removeAll(searchDataHelper.getAllBaseStockCodes());

        for (int i = 0; i < stockCodes.size(); i++) {
            String tempCode = StockCodeHelper.format(stockCodes.get(i));

            BlockType thisBlockType = null;
            if (tempCode.startsWith("001") || tempCode.startsWith("000")) {
                thisBlockType = BlockType.ZB;
            } else if (tempCode.startsWith("002")) {
                thisBlockType = BlockType.ZXB;
            } else if (tempCode.startsWith("300")) {
                thisBlockType = BlockType.CYB;
            } else {
                System.out.println("未处理股票板块：" + tempCode);
                throw new UnhandleBlockTypeException();
            }

            boolean isSt = stockNames.get(i).contains("ST");

            result.add(new StockPoolVO(tempCode, thisBlockType, isSt));
        }

        return result;
    }

    /**
     * 根据路径读取stock_records_by_code/date中的数据
     *
     * @param path 要读取的数据源
     * @return 根据俄参数路径读取到的所有股票数据
     * @throws IOException IO
     * @author cuihua
     * @lastUpdatedBy cuihua
     * @updateTime 2017/3/9
     */
    private List<StockPO> getStockByPath(String path) throws IOException {
        if (DataSourceStateKeeper.getInstance().getState() == DataSourceState.ORIGINAL) {
            br = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader().
                    getResourceAsStream(path), "UTF-8"));
        } else if (DataSourceStateKeeper.getInstance().getState() == DataSourceState.USER) {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(
                    System.getProperty("user.dir") + separator + ".attachments" + separator +
                            IDReserve.getInstance().getUserID() + separator + path), "UTF-8"));
        }

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


    private LocalDate convertLocalDate(String formated) {
        // formated as 2011-01-18.txt
        String[] parts = formated.split("-");
        return LocalDate.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
    }

    private boolean isBaseStock(String stockCode) {
        for (String s : baseStocks) {
            if (stockCode.equals(s)) return true;
        }
        return false;
    }
}
