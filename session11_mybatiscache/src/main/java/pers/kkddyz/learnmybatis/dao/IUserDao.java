package pers.kkddyz.learnmybatis.dao;

import pers.kkddyz.learnmybatis.domain.User;

import java.util.List;

/**
 * @author kkddyz
 */
public interface IUserDao {

    /**
     * 查询所有
     *
     * @return the list
     */
    List<User> findAll();

    /**
     * 根据uid查询到用户
     * @param uid
     * @return
     */
    User findById(Integer uid);

}
