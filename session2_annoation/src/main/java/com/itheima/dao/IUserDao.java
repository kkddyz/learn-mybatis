package com.itheima.dao;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * The interface User dao.
 *
 * @author kkddyz
 */
@CacheNamespace(blocking = true)
public interface IUserDao {
    /**
     * 查询所有
     */
    @Select("select * from user")
    List<User> findAll();

    /**
     * 保存用户,直接在接口上使用注解,就不需要resultType与propertyType
     */
    @Insert("insert into user(id,username,address,sex,birthday) values (#{id},#{username},#{address},#{sex},#{birthday})")
    void saveUser(User user);

    @Update("update user " +
            "set username=#{username},address=#{address},sex=#{sex},birthday=#{birthday} " +
            "where id = #{id}")
    void updateUser(User user);

    @Delete("delete from user where id = #{uid}")
    void deleteUser(int id);
}
