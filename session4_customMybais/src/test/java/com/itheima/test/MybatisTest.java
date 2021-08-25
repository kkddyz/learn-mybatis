package com.itheima.test;

import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import com.itheima.mybatis.io.Resources;
import com.itheima.mybatis.sqlsession.SqlSession;
import com.itheima.mybatis.sqlsession.SqlSessionFactory;
import com.itheima.mybatis.sqlsession.SqlSessionFactoryBuilder;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * mybatis 入门案例
 *
 * @author kkddyz
 */
public class MybatisTest {

    public static void main(String[] args) throws IOException {


        // 1. 读取配置文件
        System.out.println("读取配置文件...");
        InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");

        //2. 创建SqlSessionFactory工厂
        System.out.println("创建工厂");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);

        // 3. 使用工厂创建SqlSession
        System.out.println("创建SqlSession");
        SqlSession sqlSession = factory.openSession();

        // 4. 使用SqlSession创建代理对象(在web filter屏蔽敏感词第一次学到动态创建代理对象)\
        System.out.println("创建代理对象");
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);

        // 5. 使用代理对象执行方法
        System.out.println("invoke findAll");
        System.out.println(mapper.getClass().getName());
        List<User> users = mapper.findAll();

        for (User user : users) {
            System.out.println(user);

        }

        // 6. 释放资源
        sqlSession.close();
        in.close();
    }
}
