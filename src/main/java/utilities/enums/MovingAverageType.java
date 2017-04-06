package utilities.enums;

/**
 * Created by cuihua on 2017/4/5.
 *
 * 均线的种类
 */
public enum MovingAverageType {

    MA5(5),
    MA10(10),
    MA20(20),
    MA60(60),
    MA120(120),
    MA250(250);

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
