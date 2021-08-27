package pers.kkddyz.learnmybatis.mybatiscrud.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pers.kkddyz.learnmybatis.mybatiscrud.dao.IUserDao;
import pers.kkddyz.learnmybatis.mybatiscrud.domain.QueryVo;
import pers.kkddyz.learnmybatis.mybatiscrud.domain.User;


import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author kkddyz
 * 由于修改了User的属性,需要这里需要修改为重新生成的getter,setter
 */
public class TestMybatisCRUD {

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
        sqlSession = factory.openSession();

        // 4. 使用SqlSession创建代理对象(在web filter屏蔽敏感词第一次学到动态创建代理对象)
        mapper = sqlSession.getMapper(IUserDao.class);
    }

    @After
    public void destory() throws IOException {
        System.out.println("destory");
        // 提交事务 (自动提交默认关闭,所以上一行代码最后会被回滚,相当于什么也没做)
        sqlSession.commit();

        // 6. 释放资源
        sqlSession.close();
        in.close();
    }

    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll() {


        //  使用代理对象执行方法
        List<User> users = mapper.findAll();

        for (User user : users) {
            System.out.println(user);

        }


    }

    /**
     * 测试保存用户
     */
    @Test
    public void testSaveUser() {
        // 准备测试的用户
        User user = new User();
        user.setUserName("test saveUser");
        user.setUserAddress("test saveUser");
        user.setUserSex("女");
        user.setUserBirthday(new Date());


        System.out.println(user); // order = "AFTER" 所以在saveUser()之前是不会保存id的

        mapper.saveUser(user);

        System.out.println(user); // 使用selectKey标签后,会自动将服务器生成的id,封装会传入的user对象


    }

    @Test
    public void testUpdateUser() {
        // 准备测试的用户
        User user = new User();
        user.setUserName("test username");
        user.setUserAddress("update address");
        user.setUserSex("男");
        user.setUserBirthday(new Date());
        user.setUserId(5); // 指定id

        // 更新用户信息
        mapper.updateUser(user);
    }

    @Test
    public void testDeleteUser() {
        mapper.deleteUser(5);
    }

    @Test
    public void testFindOne() {
        User user = mapper.findById(1);
        System.out.println(user);
    }

    @Test
    public void testFindByName() {
        List<User> users = mapper.findByName("%三%");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindTotal(){
        int total = mapper.findTotal();
        System.out.println(total);
    }

    @Test
    public void testFindByVo(){

        // 创建User
        User user = new User();
        user.setUserName("%三%"); // 只需要username就够了

        // 创建vo
        QueryVo vo = new QueryVo();
        vo.setUser(user);

        // 查询

        List<User> users = mapper.findUserByVo(vo);
        for (User user1 : users) {
            System.out.println(user1);
        }
    }
}
