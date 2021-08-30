package pers.kkddyz.learnmybatis.oneToMany.dao;

import pers.kkddyz.learnmybatis.oneToMany.domain.Account;
import pers.kkddyz.learnmybatis.oneToMany.domain.AccountUser;

import java.util.List;

/**
 * @author kkddyz
 */
public interface IAccountDao {

    /**
     * 查询所有账户信息,并且同时获取当前账户的附属用户信息
     * @return
     */
    List<Account> findAll();

    /**
     * 查询所有账户信息,并且带有用户名和地址信息.
     * @return
     */
    List<AccountUser> findAllAccount();
}
