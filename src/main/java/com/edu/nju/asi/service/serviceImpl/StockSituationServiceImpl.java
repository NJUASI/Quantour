package com.edu.nju.asi.service.serviceImpl;

import com.edu.nju.asi.dao.StockSituationDao;
import com.edu.nju.asi.dao.daoImpl.StockSituationDaoImpl;
import com.edu.nju.asi.service.StockSituationService;
import com.edu.nju.asi.utilities.exceptions.NoSituationDataException;
import com.edu.nju.asi.vo.PriceRiseOrFallVO;
import com.edu.nju.asi.vo.StockSituationVO;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuihua on 2017/3/4.
 * Last updated by Harvey
 * Update time 2017/3/5
 *
 * 市场温度计信息获取
 */
public class StockSituationServiceImpl implements StockSituationService {

    StockSituationDao stockSituationDao;

    public StockSituationServiceImpl() {
        stockSituationDao = new StockSituationDaoImpl();
    }

    /**
     * 显示市场情况温度计
     *
     * @auther cuihua
     * @lastUpdatedBy Harvey
     * @updateTime 2017/3/5
     * @param date 用户选择的日期
     * @return 所选当日的市场情况温度计的信息
     * @throws IOException IO
     */
    private StockSituationVO getStockSituation(LocalDate date) throws IOException, NoSituationDataException {
       return new StockSituationVO(stockSituationDao.getStockSituation(date));
    }

    /**
     * Gets stock stituation data. 获取市场温度计的情况
     *
     * @param date the date
     * @return the stock stituation data
     */
    @Override
    public List<PriceRiseOrFallVO> getStockStituationData(LocalDate date) throws NoSituationDataException {
        StockSituationVO vo = null;
        try {
            vo = getStockSituation(date);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<PriceRiseOrFallVO> list = new ArrayList<PriceRiseOrFallVO>();

        System.out.println(vo.limitUpNum+"  "+ vo.limitDownNum);

        list.add(new PriceRiseOrFallVO("涨停",vo.limitUpNum,date));
        list.add(new PriceRiseOrFallVO("跌停",vo.limitDownNum,date));
        list.add(new PriceRiseOrFallVO("涨幅超过5%",vo.surgingNum,date));
        list.add(new PriceRiseOrFallVO("跌幅超过5%",vo.slumpingNum,date));
        list.add(new PriceRiseOrFallVO("开盘-收盘小于-5%*上一个交易日收盘价",vo.climbingNum,date));
        list.add(new PriceRiseOrFallVO("开盘-收盘大于+5%*上一个交易日收盘价",vo.slipingNum,date));
        list.add(new PriceRiseOrFallVO("总交易量",Integer.parseInt(vo.volume), date));

        return list;
    }
}
