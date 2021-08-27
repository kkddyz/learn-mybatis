package pers.kkddyz.learnmybatis.mybatisdao.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import pers.kkddyz.learnmybatis.mybatisdao.dao.IUserDao;
import pers.kkddyz.learnmybatis.mybatisdao.domain.User;

import java.util.List;

/**
 * @author kkddyz
 */
public class IUserDaoImpl implements IUserDao {
    /**
     * 为啥需要factory而不是session;
     * 每个函数的执行都是是完整的session创建与销毁.
     * 每个函数都需要获取session连接然后关闭
     * 在session5的test中,看似所有类使用同一个session的mapper,
     * 但是每次test前init都会创建一个新的session对象,引用变量是一样,但是引用的对象每一都不一样的
     */
    private SqlSessionFactory factory;

    public IUserDaoImpl(SqlSessionFactory factory) {
        this.factory = factory;


    }

    /*
        namespace="pers.kkddyz.learnmybatis.mybatisdao.dao.IUserDao"
     */

    @Override
    public List<User> findAll() {


        // 获取session
        SqlSession session = factory.openSession();

        // namespace.methodName 获取封装配置信息的mapper对象
        List<User> users = session.selectList("pers.kkddyz.learnmybatis.mybatisdao.dao.IUserDao.findAll");

        // 返回结果集
        return users;

    }

    @Override
    public void saveUser(User user) {
        // 获取session
        SqlSession session = factory.openSession();

        // 插入操作 提交事务
        session.insert("pers.kkddyz.learnmybatis.mybatisdao.dao.IUserDao.saveUser", user);
        session.commit();

        //释放资源
        session.close();


    }


    @Override
    public void updateUser(User user) {
        // 获取session
        SqlSession session = factory.openSession();

        // 插入操作 提交事务
        session.update("pers.kkddyz.learnmybatis.mybatisdao.dao.IUserDao.updateUser", user);
        session.commit();

        //释放资源
        session.close();
    }


    @Override
    public void deleteUser(Integer id) {
        // 获取session
        SqlSession session = factory.openSession();

        // 操作
        session.delete("pers.kkddyz.learnmybatis.mybatisdao.dao.IUserDao.deleteUser",id);
        session.commit();

        //释放资源
        session.close();
    }


    @Override
    public User findById(Integer integer) {
        // 获取session
        SqlSession session = factory.openSession();

        // 查询
        User user = session.selectOne("pers.kkddyz.learnmybatis.mybatisdao.dao.IUserDao.findById",integer);

        //释放资源
        session.close();

        return user;



    }



    @Override
    public List<User> findByName(String name) {
        // 获取session
        SqlSession session = factory.openSession();

        // 查询
        List<User> users = session.selectList("pers.kkddyz.learnmybatis.mybatisdao.dao.IUserDao.findByName",name);

        //释放资源
        session.close();

        return users;
    }


    @Override
    public int findTotal() {
        // 获取session
        SqlSession session = factory.openSession();

        Integer total = session.selectOne("pers.kkddyz.learnmybatis.mybatisdao.dao.IUserDao.findTotal");

        session.close();

        return total;

    }



}
