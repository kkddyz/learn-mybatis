package pers.kkddyz.learnmybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pers.kkddyz.learnmybatis.dao.IUserDao;
import pers.kkddyz.learnmybatis.domain.QueryVo;
import pers.kkddyz.learnmybatis.domain.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author kkddyz
 * 由于修改了User的属性,需要这里需要修改为重新生成的getter,setter
 */
public class TestMybatis {

    private InputStream in; // init close都需要
    private SqlSession sqlSession; // init close都需要
    private IUserDao mapper; // 每个测试方法需要

    @Before
    public void init() throws IOException {

        System.out.println("init");
        // 1. 读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");

        // 2. 创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);

        // 3. 使用工厂创建SqlSession
        sqlSession = factory.openSession(true); // 打开自动提交

        // 4. 使用SqlSession创建代理对象
        mapper = sqlSession.getMapper(IUserDao.class);
    }

    @After
    public void destory() throws IOException {
        System.out.println("destory");

        // 6. 释放资源
        sqlSession.close();
        in.close();
    }

    /**
     * 测试if where标签
     */
    @Test
    public void testFindByCondition() {
        List<User> users;

        // 1. 传username 且存在
        System.out.println("1. 传username 且存在");
        System.out.println("---------------------------------------------------");

        User user1 = new User();
        user1.setUsername("张三");
        users = mapper.findByCondition(user1);

        for (User selectedUser : users) {
            System.out.println(selectedUser);
        }

        System.out.println("---------------------------------------------------");

        // 2. 传入username 不存在
        System.out.println("2. 传入username 不存在 ");
        System.out.println("---------------------------------------------------");

        User user2 = new User();
        user2.setUsername("不存在的名字");
        users = mapper.findByCondition(user2);

        for (User selectedUser : users) {
            System.out.println(selectedUser);
        }

        System.out.println("---------------------------------------------------");


        // 3. 不传入username
        System.out.println("3. 不传入username");
        System.out.println("---------------------------------------------------");


        users = mapper.findByCondition(new User());

        for (User selectedUser : users) {
            System.out.println(selectedUser);
        }

        System.out.println("---------------------------------------------------");

        // 如果不存在 sql =   select * from user where 1 = 1; 会查询所有的结果
        // 这是合理的,因为where条件是 && 关系 少一个条件,就少一个约束,符合的结果集就会更多

        // 4. 传入两个参数 username sex
        System.out.println("传入两个参数 username sex ");
        System.out.println("---------------------------------------------------");

        User user4 = new User();
        user4.setUsername("张三");
        user4.setSex("女");
        users = mapper.findByCondition(user4);

        for (User selectedUser : users) {
            System.out.println(selectedUser);
        }

        System.out.println("---------------------------------------------------");

    }

    /**
     * 测试foreach标签
     */
    @Test
    public void testFindUserInIds(){
        List<User> users = new ArrayList<>();

        // 设置vo中的ids
        QueryVo vo = new QueryVo();
        List<Integer> ids = new ArrayList<Integer>(Arrays.asList(1,2,3));
        vo.setIds(ids);

        users = mapper.findUserInIds(vo);

        for (User user : users) {
            System.out.println(user);
        }

    }

}
