package utilities.enums;

import utilities.exceptions.NoMatchEnumException;

/**
 * Created by harvey on 17-4-2.
 *
 * 版块的类型
 */
public enum  BlockType {

    ZB("主板"),
    CYB("创业板"),
    ZXB("中小板"),
    ALL("全部");

    private String repre;

    BlockType(String repre) {
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
    public static BlockType getEnum(String a) throws NoMatchEnumException {
        for (BlockType thisEnum : BlockType.values()){
            if (thisEnum.repre.equals(a)){
                return thisEnum;
            }
        }
        throw new NoMatchEnumException();
    }
}
