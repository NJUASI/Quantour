package vo;

/**
 * Created by harvey on 17-4-6.
 */
public class FormativePeriodRateVO implements Comparable<FormativePeriodRateVO>{

    // 股票代码
    public String stockCode;

    // 一段时间内的累计收益率（MS）／相应的偏离度（MR）
    public double periodReturn;

    public FormativePeriodRateVO(String stockCode, double periodReturn) {
        this.stockCode = stockCode;
        this.periodReturn = periodReturn;
    }

    //默认按降序方式排序
    @Override
    public int compareTo(FormativePeriodRateVO o) {
        if(this.periodReturn > o.periodReturn){
            return -1;
        }
        else if(this.periodReturn == o.periodReturn){
            return 0;
        }
        else {
            return 1;
        }
    }
}
