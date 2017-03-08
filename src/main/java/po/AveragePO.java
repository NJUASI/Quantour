package po;

/**
 * Created by Byron Dong on 2017/3/5.
 */
public class AveragePO {

    // 收盘指数
    private double close;

    // 股票代码
    private int code;

    // 股票名称
    private String name;

    public AveragePO(double close, int code, String name) {
        this.close = close;
        this.code = code;
        this.name = name;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
