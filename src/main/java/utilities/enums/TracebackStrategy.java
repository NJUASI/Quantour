package utilities.enums;

/**
 * Created by harvey on 17-4-2.
 *
 * 回测策略的类型
 */
public enum TracebackStrategy {

    MS("动量策略"),
    MR("均值回归");

    private String repre;

    TracebackStrategy(String repre) {
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
    public static TracebackStrategy getEnum(String a) {
        for (TracebackStrategy thisEnum : TracebackStrategy.values()){
            if (thisEnum.repre.equals(a)){
                return thisEnum;
            }
        }
        return null;
    }
}
