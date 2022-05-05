package com.itheima.test;


import com.itheima.dao.IUserDao;
import com.itheima.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.DataInput;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author kkddyz
 */
public class MybatisTest {

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
    public void testFindAll(){
        List<User> users = mapper.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSaveUser(){
        User user = new User();
        user.setId(1);
        user.setUsername("test username");
        user.setAddress("test address");
        user.setBirthday(new Date());
        user.setSex("女");

        mapper.saveUser(user);
    }
}