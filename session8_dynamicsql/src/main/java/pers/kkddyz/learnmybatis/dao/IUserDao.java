package pers.kkddyz.learnmybatis.dao;

import pers.kkddyz.learnmybatis.domain.QueryVo;
import pers.kkddyz.learnmybatis.domain.User;

import java.util.List;

/**
 * @author kkddyz
 * 动态sql只涉及查询
 */
public interface IUserDao {


    /**
     * 将查询的条件使用User封装
     * @param user
     * @return
     */
    List<User> findByCondition(User user);

    /**
     * 根据vo中的Integer list集合查询Users
     * @param queryVo
     * @return
     */
    List<User> findUserInIds(QueryVo queryVo);
}
