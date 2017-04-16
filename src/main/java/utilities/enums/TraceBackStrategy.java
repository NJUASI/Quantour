package utilities.enums;

import utilities.exceptions.NoMatchEnumException;

/**
 * Created by harvey on 17-4-2.
 *
 * 回测策略的类型
 */
public enum TraceBackStrategy {

    MS("动量策略"),
    MR("均值回归");

    private String repre;

    TraceBackStrategy(String repre) {
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
    public static TraceBackStrategy getEnum(String a) throws NoMatchEnumException {
        for (TraceBackStrategy thisEnum : TraceBackStrategy.values()){
            if (thisEnum.repre.equals(a)){
                return thisEnum;
            }
        }
        throw new NoMatchEnumException();
    }
}
