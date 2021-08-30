package pers.kkddyz.learnmybatis.oneToMany.dao;

import pers.kkddyz.learnmybatis.oneToMany.domain.User;
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
     * id查询
     *
     * @param integer the integer
     * @return user
     */
    User findById(Integer integer);
}
