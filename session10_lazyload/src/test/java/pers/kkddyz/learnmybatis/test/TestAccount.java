package pers.kkddyz.learnmybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pers.kkddyz.learnmybatis.dao.IAccountDao;
import pers.kkddyz.learnmybatis.domain.Account;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author kkddyz
 */
public class TestAccount {

    private InputStream in; // init close都需要
    private SqlSession sqlSession; // init close都需要
    private IAccountDao mapper;// 某些测试方法需要

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
        mapper = sqlSession.getMapper(IAccountDao.class);

    }

    @After
    public void destory() throws IOException {


        // 释放资源
        sqlSession.close();
        in.close();
    }


    /**
     * 查询account关联的users
     */
    @Test
    public void testFindAll() {
        List<Account> accounts = mapper.findAll();

        for (Account account : accounts) {
            System.out.println(account);
        }
    }
}
