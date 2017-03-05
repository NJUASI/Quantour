package dao;

import po.UserPO;
import utilities.enums.ResultMessage;

/**
 * Created by cuihua on 2017/3/4.
 */
public interface UserDao {

    public ResultMessage add(UserPO userPO);

    public UserPO get(String username);

    public ResultMessage modify(UserPO userPO);

}
