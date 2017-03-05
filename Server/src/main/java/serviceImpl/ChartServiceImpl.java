package serviceImpl;

import exceptions.DateShortException;
import service.ChartService;
import vo.ChartShowCriteriaVO;
import vo.StockVO;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDate;
import java.util.Iterator;

/**
 * Created by cuihua on 2017/3/4.
 */
public class ChartServiceImpl extends UnicastRemoteObject implements ChartService {

    ChartDao chartDao;

    /**
     * Instantiates a new Chart service.
     *
     * @throws RemoteException the remote exception
     */
    protected ChartServiceImpl() throws RemoteException {
        chartDao = new ChartDaoImpl();
    }

    /**
     * 获取单支股票的一段日期内的信息
     *
     * @param chartShowCriteriaVO 股票的选择标准
     * @return 特定股票的所有交易信息
     * @throws RemoteException RMI
     */
    @Override
    public Iterator<StockVO> getSingleStockRecords(ChartShowCriteriaVO chartShowCriteriaVO) throws RemoteException {
        return null;
    }

    /**
     * 获取单支股票一段日期内，用户所选天数的均线图的平均值.
     *
     * @param chartShowCriteriaVO the chart show criteria vo 股票的选择标准
     * @param days
     * @return the ave data 用户所选天数的均线图的平均值
     * @throws RemoteException the remote exception
     */
    @Override
    public Iterator<Iterator<Double>> getAveData(ChartShowCriteriaVO chartShowCriteriaVO, int[] days) throws RemoteException, DateShortException {
        for(int i=0;i<days.length;i++){
            if(isDateTooShort(chartShowCriteriaVO,days[i])){
                throw new DateShortException();
            }
        }
        return null;
    }


    /**
     * 判断所选日期是否小于等于均线图的时间.
     *
     * @param chartShowCriteriaVO the chart show criteria vo
     * @param day                 the day
     * @return the boolean
     */
    private boolean isDateTooShort(ChartShowCriteriaVO chartShowCriteriaVO,int day) {
        LocalDate begin = chartShowCriteriaVO.start;
        LocalDate end = chartShowCriteriaVO.end;
        int interval = begin.until(end).getDays();
        if(interval<=day){
            return false;
        }
        return true;
    }
}
