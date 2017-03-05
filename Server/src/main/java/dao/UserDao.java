package dao;

import po.UserPO;
import utilities.enums.ResultMessage;

/**
 * Created by cuihua on 2017/3/4.
 */
public interface UserDao {

    ResultMessage add(UserPO userPO);

    UserPO get(String username);

    ResultMessage modify(UserPO userPO);

}
