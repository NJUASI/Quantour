package po;

import java.util.List;

/**
 * Created by cuihua on 2017/3/12.
 * Last updated by cuihua
 * Update time 2017/3/12
 */
public class PrivateStockPO {

    // 用户名
    private String userName;

    // 用户的自选股列表
    private List<Integer> privateStocks;

    public PrivateStockPO(String userName, List<Integer> privateStocks) {
        this.userName = userName;
        this.privateStocks = privateStocks;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Integer> getPrivateStocks() {
        return privateStocks;
    }

    public void setPrivateStocks(List<Integer> privateStocks) {
        this.privateStocks = privateStocks;
    }
}
