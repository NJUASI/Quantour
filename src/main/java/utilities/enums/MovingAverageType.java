package utilities.enums;

/**
 * Created by cuihua on 2017/4/5.
 *
 * 均线的种类
 */
public enum MovingAverageType {

    FIVE(5),
    TEN(10),
    TWENTY(20),
    SIXTY(60),
    ONE_HUNDRED_TWENTY(120),
    TWO_HUNDRED_FIFTY(250);

    private int repre;

    MovingAverageType(int repre) {
        this.repre = repre;
    }

    /**
     *
     * @return 该枚举相对应的文件中形式
     *
     * enum TO String
     */
    public int getRepre() {
        return repre;
    }

    /**
     *
     * @return 该类型对应的枚举代码
     *
     * String TO enum
     */
    public static MovingAverageType getEnum(int a) {
        for (MovingAverageType thisEnum : MovingAverageType.values()){
            if (thisEnum.repre == a){
                return thisEnum;
            }
        }
        return null;
    }
}
