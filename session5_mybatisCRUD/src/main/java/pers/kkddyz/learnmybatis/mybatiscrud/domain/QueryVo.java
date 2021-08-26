package pers.kkddyz.learnmybatis.mybatiscrud.domain;

/**
 * @author kkddyz
 * QueryVo 是 query view object的缩写
 * vo对象(ViewObject - 表现层对象，pojo的属性一般为基本类型，vo的属性可以是引用类型)
 *
 * 这里只定义一个User就可以满足测试要求了
 */
public class QueryVo {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
