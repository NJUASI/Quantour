package utilities.enums;

/**
 * Created by Harvey on 2017/4/19.
 *
 * 回测时， 形成期中的选择策略
 */
public enum PickType {

    RANK_Max_Percent("排名%最大"),
    RANK_Max("排名最大");

    private String repre;

    PickType(String repre) {
        this.repre = repre;
    }

    /**
     *
     * @return 该枚举相对应的文件中形式
     *
     * enum TO String
     * 便于写入数据库
     */
    public String getRepre() {
        return repre;
    }

    /**
     *
     * @return 该类型对应的枚举代码
     *
     * String TO enum
     * 便于从数据库读入
     */
    public static PickType getEnum(String a) {
        for (PickType thisEnum : PickType.values()){
            if (thisEnum.repre.equals(a)){
                return thisEnum;
            }
        }
        return null;
    }

}
