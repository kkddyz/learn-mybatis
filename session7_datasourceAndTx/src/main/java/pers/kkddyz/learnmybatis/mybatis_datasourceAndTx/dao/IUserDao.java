package pers.kkddyz.learnmybatis.mybatis_datasourceAndTx.dao;

import pers.kkddyz.learnmybatis.mybatis_datasourceAndTx.domain.User;

import java.util.List;

/**
 * @author kkddyz
 * 在dao接口或者是mapper接口定义的函数的命名是根据提供的业务功能来命名的
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
     * @return
     */
    User findById(Integer integer);

    /**
     * 根据name模糊查询
     * @param name
     * @return
     */
    List<User> findByName(String name);

    /**
     * 保存用户
     * @param user
     */
    void saveUser(User user);

    /**
     * 更新用户信息
     * @param user
     */
    void updateUser(User user);

    /**
     * 删除用户
     * @param id
     */
    void deleteUser(Integer id);

    /**
     * 查询总用户数
     * @return
     */
    int findTotal();


}