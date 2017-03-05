package dao.daoImpl;

import dao.UserDao;
import dataHelper.UserDataHelper;
import dataHelper.dataHelperImpl.UserDataHelperImpl;
import po.UserPO;
import utilities.enums.ResultMessage;

/**
 * Created by cuihua on 2017/3/4.
 */
public class UserDaoImpl implements UserDao {

    private UserDataHelper userHelper;

    public UserDaoImpl() {
        this.userHelper = new UserDataHelperImpl();
    }

    @Override
    public ResultMessage add(UserPO userPO) {
        return this.userHelper.add(userPO);
    }

    @Override
    public UserPO get(String username) {
        return this.userHelper.get(username);
    }

    @Override
    public ResultMessage modify(UserPO userPO) {
        return this.userHelper.modify(userPO);
    }
}
