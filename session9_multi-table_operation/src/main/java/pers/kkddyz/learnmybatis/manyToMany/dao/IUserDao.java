package pers.kkddyz.learnmybatis.manyToMany.dao;

import pers.kkddyz.learnmybatis.manyToMany.domain.User;

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

}
