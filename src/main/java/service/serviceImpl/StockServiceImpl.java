package service.serviceImpl;

import com.github.stuxuhai.jpinyin.ChineseHelper;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.github.stuxuhai.jpinyin.PinyinResource;
import dao.StockDao;
import dao.daoImpl.StockDaoImpl;
import po.StockPO;
import service.StockService;
import utilities.exceptions.MatchNothingException;
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

    public StockServiceImpl() {
        stockDao = new StockDaoImpl();
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
    public Iterator<StockVO> getPrivateStocks(String userName, LocalDate date) throws IOException {
        List<StockVO> stockVOList = new ArrayList<StockVO>();
        for (StockPO po:stockDao.getPrivateStockData(userName,date)) {
            stockVOList.add(new StockVO(po));
        }
        return stockVOList.iterator();
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
    public boolean addPrivateStock(String userName, String stockCode) {
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
    public boolean deletePrivateStock(String userName, String stockCode) {
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
    public List<StockSearchVO> searchStock(String searchString) throws MatchNothingException {

        List<StockSearchVO> stockSearchVOs = new ArrayList<StockSearchVO>();

        //通过匹配股票的拼音来查询
        if(searchString.matches("[0-9]+")){
            Map<String,String> codeAndName = stockDao.getAllStocksCode();
            Set<String> codes = codeAndName.keySet();
            for (String code:codes) {
                if(code.startsWith(searchString)){
                    StockSearchVO vo = new StockSearchVO(code,codeAndName.get(code));
                    stockSearchVOs.add(vo);
                }
            }
            //判断查询结果是否为0
            if(stockSearchVOs.size() == 0){
                throw new MatchNothingException();
            }
        }

        //通过名称匹配股票
        else{
            Map<String,String> namesAndCode = stockDao.getAllStocksName();
            Set<String> names = namesAndCode.keySet();
            for(String name:names){
                if(name.startsWith(searchString)){
                    StockSearchVO vo = new StockSearchVO(namesAndCode.get(name),name);
                    stockSearchVOs.add(vo);
                }
            }
        }

        //通过匹配股票的首字母来查询
//        else if(searchString.matches("[a-zA-Z]+")){
//            Map<String,String> firstLettersAndNames = stockDao.getAllStocksFirstLetters();
//            Set<String> firstLetters = firstLettersAndNames.keySet();
//            List<String> names = new ArrayList<String>();
//            for(String letters: firstLetters){
//               if(letters.startsWith(searchString)){
//                   names.add(firstLettersAndNames.get(letters));
//               }
//            }
//            //判断查询结果是否为0
//            if(stockSearchVOs.size() == 0){
//                throw new MatchNothingException();
//            }
//        }
//        //未查询到结果，抛出异常
//        else{
//            throw new MatchNothingException();
//        }
        return  stockSearchVOs;
    }
}
