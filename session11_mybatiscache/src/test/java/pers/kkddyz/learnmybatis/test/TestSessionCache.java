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

/**
 * @author kkddyz
 */
public class TestSessionCache {

    private InputStream in; // init close都需要
    private SqlSession sqlSession; // init close都需要
    private IUserDao mapper;// 某些测试方法需要

    @Before
    public void init() throws IOException {

        // 1. 读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");

        // 2. 创建工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);

        // 3. 创建SqlSession
        sqlSession = factory.openSession(true);

        // 4. 创建代理对象
        mapper = sqlSession.getMapper(IUserDao.class);

    }

    @After
    public void destory() throws IOException {


        // 释放资源
        sqlSession.close();
        in.close();
    }

    @Test
    public void testFindAll() {
        System.out.println("print user信息");
        List<User> users = mapper.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSqlSessionCache() {
        //System.out.println();
        //System.out.println("获取uid==41对象");
        //User user1 = mapper.findById(41);
        //System.out.println(user1);
        //
        //System.out.println("再次获取uid==41对象");
        //User user2 = mapper.findById(41);
        //System.out.println(user2);
        //
        //System.out.println("判断获取的是否是相同的对象:" + (user1 == user2));



        System.out.println("获取uid==41对象");
        User user1 = mapper.findById(41);
        System.out.println(user1);

        System.out.println("清除缓存");
        sqlSession.clearCache();

        System.out.println("再次获取uid==41对象");
        User user2 = mapper.findById(41);
        System.out.println(user2);

        // 判断获取的是否是相同的对象
        System.out.println("判断获取的是否是相同的对象:" + (user1 == user2)); // false
    }
}
