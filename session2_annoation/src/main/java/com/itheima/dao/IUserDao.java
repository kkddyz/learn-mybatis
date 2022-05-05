package com.itheima.dao;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * The interface User dao.
 *
 * @author kkddyz
 */

public interface IUserDao {
    /**
     * 查询所有
     *
     * @return the list
     */
    @Select("select * from user")
    List<User> findAll();

    /**
     * 保存用户
     * 由于直接在接口上使用注释,就不需要resultType与propertyType
     * @param user
     */
    @Insert("insert into user(username,address,sex,birthday) values(#{username},#{address}),#{sex},#{birthday}")
    void saveUser(User user);
}
