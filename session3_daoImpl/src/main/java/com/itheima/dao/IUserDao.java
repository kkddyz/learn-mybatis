package com.itheima.dao;

import com.itheima.domain.User;
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
    List<User> findAll();
}
