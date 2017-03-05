package po;

import java.util.Iterator;

/**
 * Created by Byron Dong on 2017/3/5.
 */
public class UserPO {

    //用户姓名
    private String userName;

    //用户密码
    private String password;

    //用户的自选股（用户未选择时为null）
    private Iterator<StockPO> optionalStock;

    public UserPO(String userName, String password, Iterator<StockPO> optionalStock) {
        this.userName = userName;
        this.password = password;
        optionalStock = optionalStock;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Iterator<StockPO> getOptionalStock() {
        return optionalStock;
    }

    public void setOptionalStock(Iterator<StockPO> optionalStock) {
        this.optionalStock = optionalStock;
    }

}
