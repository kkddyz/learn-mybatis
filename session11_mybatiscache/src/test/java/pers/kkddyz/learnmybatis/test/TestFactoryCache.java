package pers.kkddyz.learnmybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pers.kkddyz.learnmybatis.dao.IUserDao;
import pers.kkddyz.learnmybatis.domain.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestFactoryCache {

    private InputStream in; // init close都需要
    SqlSessionFactory factory;
    private IUserDao mapper;// 某些测试方法需要

    @Before
    public void init() throws IOException {

        // 1. 读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");

        // 2. 创建工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        factory = builder.build(in);


    }

    @After
    public void destory() throws IOException {


        // 释放资源
        in.close();
    }

    @Test
    public void testFactoryCache(){

        // 使用不同的session对象去查询user

        SqlSession session1 = factory.openSession();
        IUserDao mapper1 = session1.getMapper(IUserDao.class);
        User user1 = mapper1.findById(41);
        System.out.println(user1);
        session1.clearCache(); // 清除session1的缓存

        SqlSession session2 = factory.openSession();
        IUserDao mapper2 = session2.getMapper(IUserDao.class);
        User user2 = mapper2.findById(41);
        System.out.println(user2);
        session2.close(); // 清除session2的缓存

        System.out.println(user1 == user2);






    }

}
