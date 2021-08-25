package com.itheima.dao.impl;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author kkddyz
 */
public class UserDaoImpl implements IUserDao {

    private SqlSessionFactory factory;

    /**
        为啥传递factory,而不是直接传递SqlSession
     */
    public UserDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<User> findAll() {
        // 1. 使用工厂创建SqlSession
        SqlSession sqlSession = factory.openSession();

        // 2. 通过SqlSession 执行查询 通过全类名.方法名指定配置的SQL语句
        List<User> users = sqlSession.selectList(".ithecomima.dao.IUserDao.findAll");

        // 3. 关闭资源
        sqlSession.close();

        // 4. 返回查询结果
        return users;
    }
}
