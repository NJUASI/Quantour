package vo;

import java.util.Iterator;

/**
 * Created by Byron Dong on 2017/3/5.
 */
public class UserVO {

    //用户姓名
    public String userName;

    //用户密码
    public String password;

    //用户的自选股（用户未选择时为null）
    public Iterator<StockVO> optionalStock;

}
