package dataHelper.dataHelperImpl;

import dataHelper.UserDataHelper;
import po.UserPO;
import utilities.enums.ResultMessage;

/**
 * Created by Byron Dong on 2017/3/5.
 */
public class UserDataHelperImpl implements UserDataHelper {

    @Override
    public boolean add(UserPO userPO) {
        return true;
    }

    @Override
    public UserPO get(String username) {
        return null;
    }

    @Override
    public boolean modify(UserPO userPO) {
        return true;
    }
}
