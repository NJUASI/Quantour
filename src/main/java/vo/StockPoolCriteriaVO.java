package vo;

/**
 * Created by harvey on 17-3-31.
 *
 * 当需要增加板块的时候，可以在这里面增加参数，随之增加策略即可
 */
public class StockPoolCriteriaVO {

    /**
     * 是否包含st,全部为1;剔除st为0;只要st为-1
     */
    int hasSt;

    /**
     * 基准对比股票代码,可根据此确定股票的板块
     */
    String baseStockCode;


}
