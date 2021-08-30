package pers.kkddyz.learnmybatis.domain;

import java.io.Serializable;

/**
 * @author kkddyz
 * <p>
 * 账户的实体类
 */
public class Account implements Serializable {
    private Integer id;

    /**
     *  user表外键引用
     */
    private Integer uid;

    private Double money;

    /**
     * 一对一映射关系: 从表实体应该持有一个主表实体的对象引用
     */
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", uid=" + uid +
                ", money=" + money +
                ", user=" + user +
                '}';
    }
}
