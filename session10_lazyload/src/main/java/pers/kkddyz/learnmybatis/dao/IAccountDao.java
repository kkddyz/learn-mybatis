package pers.kkddyz.learnmybatis.dao;

import pers.kkddyz.learnmybatis.domain.Account;


import java.util.List;

/**
 * The interface Account dao.
 *
 * @author kkddyz
 */
public interface IAccountDao {

    /**
     * 查询所有账户信息,并且同时获取当前账户的附属用户信息
     *
     * @return list list
     */
    List<Account> findAll();


    /**
     * 用于延迟查询时,找到user的account封装为list
     * 不论是account找对应的user,还是user找属于他的account,都是通过uid;
     * 因为uid是外键,account通过uid与user关联
     *
     * @param uid the uid
     * @return account list
     */
    List<Account> findAccountByUid(Integer uid);
}
