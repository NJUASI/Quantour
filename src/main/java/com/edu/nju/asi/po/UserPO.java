package com.edu.nju.asi.po;

import com.edu.nju.asi.vo.UserVO;

/**
 * Created by Byron Dong on 2017/3/5.
 */
public class UserPO {

    // 用户姓名
    private String userName;

    // 用户密码
    private String password;

    public UserPO(String userName, String password) {
        this.userName = userName;
        this.password = password;

    }

    public UserPO(UserVO userVO) {
        this.userName = userVO.userName;
        this.password = userVO.password;
    }

    public UserPO() {

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

}
