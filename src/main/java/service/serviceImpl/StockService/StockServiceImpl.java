package service.serviceImpl.StockService;

import dao.StockDao;
import dao.daoImpl.StockDaoImpl;
import po.StockPO;
import po.StockSearchPO;
import service.StockService;
import service.serviceImpl.StockService.StockPoolFilters.BlockCriteriaFilter;
import service.serviceImpl.StockService.StockPoolFilters.StCriteriaFilter;
import utilities.StockCodeHelper;
import utilities.exceptions.*;
import vo.StockPoolCriteriaVO;
import vo.StockPoolVO;
import vo.StockSearchVO;
import vo.StockVO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

/**
 * 股票信息查看、自选股操作
 *
 * Created by Harvey on 2017/3/5.
 * Last updated by cuihua
 * Update time 2017/3/12
 * 因修改下层接口而修改
 */
public class StockServiceImpl implements StockService {

    StockDao stockDao;
    private StockPoolFilter stockPoolFilter;

    public StockServiceImpl() {
        stockDao = new StockDaoImpl();
        stockPoolFilter = new StockPoolFilter();
    }

    /**
     * 显示所有股票信息的列表
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @params date 用户选择日期
     * @return 股票信息列表
     * @throws IOException IO
     */
    @Override
    public List<StockVO> getAllStocks(LocalDate date) throws IOException {

        List<StockVO> stockVOList = new ArrayList<StockVO>();
        try {
            for (StockPO po:stockDao.getStockData(date)) {
                stockVOList.add(new StockVO(po));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stockVOList;
    }

    /**
     * 显示用户的自选股信息列表
     *
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param userName the user name 用户名称
     * @param date 用户选择日期
     * @return the iterator 自选股信息列表
     */
    @Override
    public List<StockVO> getPrivateStocks(String userName, LocalDate date) throws IOException, PrivateStockNotFoundException {
        List<StockVO> stockVOList = new ArrayList<StockVO>();
        for (StockPO po:stockDao.getPrivateStockData(userName,date)) {
            stockVOList.add(new StockVO(po));
        }
        return stockVOList;
    }

    /**
     * 获取用户的自选股票池
     *
     * @param userName 用户名称
     * @return 指定用户的自选股
     * @author Byron Dong
     * @lastUpdatedBy Byron Dong
     * @updateTime 2017/4/17
     */
    @Override
    public List<String> getPrivateStockCodes(String userName) throws PrivateStockNotFoundException {
        return stockDao.getPrivateStockCodes(userName);
    }

    /**
     * 用户添加自选股
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param userName 用户名称
     * @param stockCode 股票代码
     * @return 是否添加成功
     */
    @Override
    public boolean addPrivateStock(String userName, String stockCode) throws PrivateStockExistedException, PrivateStockNotFoundException {
        return stockDao.addPrivateStock(userName, stockCode);
    }

    /**
     * 用户删除自选股
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param userName 用户名称
     * @param stockCode 股票代码
     * @return 是否删除成功
     */
    @Override
    public boolean deletePrivateStock(String userName, String stockCode) throws PrivateStockNotExistException, PrivateStockNotFoundException {
        return stockDao.deletePrivateStock(userName, stockCode);
    }

    /**
     * 用户输入代码或者股票首字母，查找符合条件的股票
     *
     * @param searchString 代码或股票首字母
     * @return List<StockSearchVO> 符合条件的股票简要信息
     * @auther Harvey
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/14
     */
    @Override
    public List<StockSearchVO> searchStock(String searchString) throws IOException {

        List<StockSearchVO> stockSearchVOs = new ArrayList<StockSearchVO>();

        //通过匹配股票的代码来查询
        if(searchString.matches("[0-9]+")){
            searchString =StockCodeHelper.simplify(searchString);
            Map<String,String> codeAndName = stockDao.getAllStocksCode();
            Set<String> codes = codeAndName.keySet();
            for (String code:codes) {
                if (code.startsWith(searchString)) {
                    StockSearchVO vo = new StockSearchVO(StockCodeHelper.format(code), codeAndName.get(code));
                    stockSearchVOs.add(vo);
                }
            }
        }

        //通过名称匹配股票
        else {
            Map<String, String> namesAndCode = stockDao.getAllStocksName();
            Set<String> names = namesAndCode.keySet();
            for (String name : names) {
                if (name.contains(searchString)) {
                    StockSearchVO vo = new StockSearchVO(StockCodeHelper.format(namesAndCode.get(name)), name);
                    stockSearchVOs.add(vo);
                }
            }
        }

        //通过匹配股票的首字母来查询
        if(searchString.matches("[a-zA-Z]+")){
            List<StockSearchPO> firstLetters = stockDao.getAllStocksFirstLetters();
            for (StockSearchPO po : firstLetters) {
                if (po.getFirstLetters().contains(searchString)){
                    stockSearchVOs.add(new StockSearchVO(po));
                }
            }
        }
        return  stockSearchVOs;
    }

    /**
     * 根据股票名称，起始日期，结束日期，获得该股票在此期间的数据
     *
     * @param stockCode 股票代码
     * @param start     起始日期
     * @param end       结束日期
     * @return List<StockVO> 该股票信息的列表
     */
    @Override
    public Map<LocalDate, StockVO> getOneStockDateAndData(String stockCode, LocalDate start, LocalDate end) throws DateNotWithinException, NoDataWithinException, IOException {
        Map<LocalDate, StockVO> dateAndData = new TreeMap<LocalDate, StockVO>();
        List<StockVO> stockVOS = convertStockPO2VO(stockDao.getStockData(stockCode,start,end));
        for(int i = 0; i < stockVOS.size(); i++){
            dateAndData.put(stockVOS.get(i).date,stockVOS.get(i));
        }
        return dateAndData;
    }

    /**
     * 根据股票代码，起始日期，结束日期，获得该股票在此期间的数据
     *
     * @param stockCode 股票代码
     * @param start     起始日期
     * @param end       结束日期
     * @return List<StockVO> 该股票信息的列表
     */
    @Override
    public List<StockVO> getOneStockData(String stockCode, LocalDate start, LocalDate end) throws DateNotWithinException, NoDataWithinException, IOException {
        List<StockPO> stockPOS = stockDao.getStockData(stockCode,start,end);
        return convertStockPO2VO(stockPOS);
    }

    /**
     * 根据基准股票名称，起始日期，结束日期，获得该基准股票在此期间的数据
     *
     * @param stockName 股票名称
     * @param start     起始日期
     * @param end       结束日期
     * @return List<StockVO> 基准股票信息的列表
     */
    @Override
    public List<StockVO> getBaseStockData(String stockName, LocalDate start, LocalDate end) throws IOException, NoDataWithinException, DateNotWithinException {
        Map<String, String> map = stockDao.getAllStocksName();
        String baseStockCode = StockCodeHelper.format(map.get(stockName));
        return getOneStockData(baseStockCode,start,end);
    }

    /**
     * 根据股票池的选择标准，选择符合标准的股票池 非自选股调用此方法
     *
     * @param stockPoolCriteriaVO 股票池的选择标准
     * @return List<String> 符合标准的股票池中所有股票的股票代码
     */
    @Override
    public List<String> getStockPool(StockPoolCriteriaVO stockPoolCriteriaVO) throws IOException, UnhandleBlockTypeException {

        //新建所有filter对象
        StockPoolFilter stockPoolFilter = new StockPoolFilter();
        StockPoolFilter blockCriteriaFilter = new BlockCriteriaFilter();
        StockPoolFilter stCriteriaFilter = new StCriteriaFilter();

        //设置责任链
        stockPoolFilter.setNextFilter(blockCriteriaFilter);
        blockCriteriaFilter.setNextFilter(stCriteriaFilter);


        List<String> stockPoolCodes = new ArrayList<String>();
        //筛选股票
        List<StockPoolVO> allStockPool = stockPoolFilter.meetCriteria(stockDao.getAllStockPool(),stockPoolCriteriaVO);

        //只需要股票池所有股票的股票代码
        for(StockPoolVO vo : allStockPool){
            stockPoolCodes.add(vo.stockCode);
        }

        return stockPoolCodes;

    }

    /**
     * 获取该股票代码对应的所有数据
     *
     * @param stockCode 股票代码
     * @return
     */
    @Override
    public List<StockVO> getStockData(String stockCode) throws IOException {
        return convertStockPO2VO(stockDao.getStockData(stockCode));
    }

    /**
     * 转换stockPO to stockVO
     * @param stockPOS
     * @return
     */
    private List<StockVO> convertStockPO2VO(List<StockPO> stockPOS) {
        List<StockVO> stockVOS = new ArrayList<StockVO>();
        for(int i = 0; i < stockPOS.size(); i++){
            stockVOS.add(new StockVO(stockPOS.get(i)));
        }
        return stockVOS;
    }
}
