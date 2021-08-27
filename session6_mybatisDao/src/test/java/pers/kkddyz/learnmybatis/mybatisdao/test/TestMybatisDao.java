package pers.kkddyz.learnmybatis.mybatisdao.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pers.kkddyz.learnmybatis.mybatisdao.dao.impl.IUserDaoImpl;
import pers.kkddyz.learnmybatis.mybatisdao.domain.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author kkddyz
 * 由于修改了User的属性,需要这里需要修改为重新生成的getter,setter
 */
public class TestMybatisDao {

    private InputStream in; // init close都需要
    private IUserDaoImpl userDao;

    @Before
    public void init() throws IOException {
        // 1. 读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");

        // 2. 创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);

        // 3. 创建实现类
        userDao = new IUserDaoImpl(factory);
    }

    @After
    public void destory() throws IOException {


        // 释放资源
        in.close();
    }

    /**
     * 测试查询所有
     */
    @Test
    public void testFindAll() {


        //  使用代理对象执行方法
        List<User> users = userDao.findAll();

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
        user.setUsername("test saveUser111");
        user.setAddress("test saveUser111");
        user.setSex("女");
        user.setBirthday(new Date());


        System.out.println(user); // order = "AFTER" 所以在saveUser()之前是不会保存id的

        userDao.saveUser(user);

        System.out.println(user); // 使用selectKey标签后,会自动将服务器生成的id,封装会传入的user对象


    }


    @Test
    public void testDeleteUser() {
        userDao.deleteUser(16);
    }

    @Test
    public void testUpdateUser() {
        // 准备测试的用户
        User user = new User();
        user.setUsername("test update");
        user.setAddress("test update");
        user.setSex("男");
        user.setBirthday(new Date());
        user.setId(16); // 指定id

        // 更新用户信息
        userDao.updateUser(user);
    }

    @Test
    public void testFindOne() {
        User user = userDao.findById(1);
        System.out.println(user);
    }

    @Test
    public void testFindByName() {
        List<User> users = userDao.findByName("%三%");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindTotal(){
        int total = userDao.findTotal();
        System.out.println(total);
    }

}
