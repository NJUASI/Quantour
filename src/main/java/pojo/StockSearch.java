package pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Byron Dong on 2017/5/7.
 *
 * 股票查找的信息携带者
 */
@Entity
@Table
public class StockSearch implements Serializable {

    // 股票代码
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String code;

    // 股票名称
    private String name;

    // 汉字首字母名称
    private String firstLetters;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstLetters() {
        return firstLetters;
    }

    public void setFirstLetters(String firstLetters) {
        this.firstLetters = firstLetters;
    }
}
