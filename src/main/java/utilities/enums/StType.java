package utilities.enums;

/**
 * Created by harvey on 17-4-2.
 */
public enum StType {

    INCLUDE("包含ST"),
    EXCLUDE("排除ST"),
    ONLY("仅有ST");

    private String repre;

    StType(String repre) {
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
    public static StType getEnum(String a) {
        for (StType thisEnum : StType.values()){
            if (thisEnum.repre.equals(a)){
                return thisEnum;
            }
        }
        return null;
    }


}
