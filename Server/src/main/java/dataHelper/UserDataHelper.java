package dataHelper;

import po.UserPO;

/**
 * Created by cuihua on 2017/3/4.
 */
public interface UserDataHelper {

    /**
     * 添加用户信息
     *
     * @author Byron Dong
     * @updateTime 2017/3/5
     * @param userPO 用户信息载体
     * @return ResultMessage 是否成功添加用户
     */
    boolean add(UserPO userPO);

    /**
     * 获取用户信息
     *
     * @author Byron Dong
     * @updateTime 2017/3/5
     * @param username 用户账号
     * @return UserPO 用户信息载体
     */
    UserPO get(String username);

    /**
     * 修改用户信息
     *
     * @author Byron Dong
     * @updateTime 2017/3/5
     * @param userPO 用户信息载体
     * @return ResultMessage 是否修改添加用户
     */
    boolean modify(UserPO userPO);

}
