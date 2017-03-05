package utilities;

/**
 * Created by 61990 on 2017/3/5.
 */
public class IDReserve {
    private static IDReserve idReserve = new IDReserve();

    private String userID;
    /**
     * 获得单件IDReserve
     *
     * @param
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
     * @param
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
     * @param
     * @return
     * @author 61990
     * @updateTime 2017/3/5
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }
}
