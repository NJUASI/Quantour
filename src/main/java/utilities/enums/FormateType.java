package utilities.enums;

/**
 * Created by Harvey on 2017/4/19.
 *
 * 回测时， 形成期的形成策略， 暂定只有涨幅、乖离率 分别对应 动量策略和均值回归策略
 */
public enum FormateType {

    INCEREASE_AMOUNT("涨幅"),
    BIAS("乖离率"),
    VOLUME("成交量");

    private String repre;

    FormateType(String repre) {
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
    public static FormateType getEnum(String a) {
        for (FormateType thisEnum : FormateType.values()){
            if (thisEnum.repre.equals(a)){
                return thisEnum;
            }
        }
        return null;
    }

}
