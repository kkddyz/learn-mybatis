package com.itheima.mybatis.sqlsession;

/**
 * @author kkddyz
 */
public interface SqlSessionFactory {

    /*
        打开新的Session对象
     */

    SqlSession openSession();
}
