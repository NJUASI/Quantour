package com.edu.nju.asi.utilities.enums;

/**
 * Created by cuihua on 2017/3/4.
 *
 * 股票证券市场
 * 以防之后新的证券市场出现
 */
public enum Market {

    SZ("SZ");

    private String repre;

    Market(String repre) {
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
    public static Market getEnum(String a) {
        for (Market thisEnum : Market.values()){
            if (thisEnum.repre.equals(a)){
                return thisEnum;
            }
        }
        return null;
    }



//    private int representNum;
//
//    private Market(int representNum) {
//        this.representNum = representNum;
//    }
//
//    /**
//     *
//     * @return 该枚举相对应的汉字
//     *
//     * enum TO int
//     * 便于写入数据库
//     */
//    public int getRepresentNum() {
//        return representNum;
//    }
//
//    /**
//     *
//     * @return 该类型对应的枚举代码
//     *
//     * int TO enum
//     * 便于从数据库读入
//     */
//    public static Market getEnum(int a) {
//        for (Market thisEnum : Market.values()){
//            if (thisEnum.representNum == a){
//                return thisEnum;
//            }
//        }
//        return null;
//    }
}
