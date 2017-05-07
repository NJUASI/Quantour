package pojo;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Byron Dong on 2017/5/7.
 */
@Entity
@Table
public class User implements Serializable {

    // 用户姓名
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String userName;

    // 用户密码
    private String password;

    //有bug
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,targetEntity = Stock.class)
    private Set<Stock> privateStock =  new HashSet<Stock>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Stock> getPrivateStock() {
        return privateStock;
    }

    public void setPrivateStock(Set<Stock> privateStock) {
        this.privateStock = privateStock;
    }
}
