package utilities;

/**
 * Created by 61990 on 2017/3/5.
 */
public class IDReserve {
    private static IDReserve idReserve = new IDReserve();

    private String userID;
    private String password;

    /**
     * 获得单件IDReserve
     *
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public static IDReserve getInstance(){
        return idReserve;
    }

    /**
     * 获得ID
     *
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public String getUserID() {
        return userID;
    }

    /**
     * 设置ID
     *
     * @author 61990
     * @updateTime 2017/3/5
     */
    public void setIDReserve(String userID, String password) {
        this.userID = userID;
        this.password = password;
    }
}
