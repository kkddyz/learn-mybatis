package pres.kkddyz.learnmybatis.mybatiscrud.dao;

import pres.kkddyz.learnmybatis.mybatiscrud.domain.User;

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
