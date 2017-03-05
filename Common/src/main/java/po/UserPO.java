package po;

import vo.UserVO;

import java.util.Iterator;

/**
 * Created by Byron Dong on 2017/3/5.
 */
public class UserPO {

    //用户姓名
    private String userName;

    //用户密码
    private String password;

    public UserPO(String userName, String password) {
        this.userName = userName;
        this.password = password;

    }

    public UserPO(UserVO userVO) {
        this.userName = userVO.userName;
        this.password = userVO.password;
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
