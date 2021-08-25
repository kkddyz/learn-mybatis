package com.itheima.mybatis.sqlsession;

/**
 * @author kkddyz
 * 与数据库交互的核心类,创建代理对象
 */
public interface SqlSession {
    /**
     * 根据参数创建代理对象
     * @param daoInterfaceClass
     * @param <T>
     * @return
     */
    <T> T getMapper(Class<T> daoInterfaceClass);

    /**
     * 释放资源
     */
    void close();


}
