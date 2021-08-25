package com.itheima.mybatis.sqlsession.proxy;

import com.itheima.mybatis.cfg.Mapper;
import com.itheima.mybatis.utils.Executor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Map;

/**
 * @author kkddyz
 */
public class MapperProxy implements InvocationHandler {

    private Map<String, Mapper> mappers;
    private Connection conn;

    public MapperProxy(Map<String, Mapper> mappers,Connection conn) {
        this.mappers = mappers;
        this.conn = conn;
    }

    /**
     * 对方法增强
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        // 1. 获取 className + "." + methodName;
        String methodName = method.getName();
        String className = method.getDeclaringClass().getName();
        String key = className + "." + methodName;

        // 2. 获取映射配置信息,如果失败:抛出异常
        Mapper mapper = mappers.get(key);
        if (mapper == null){
            throw new IllegalArgumentException("传入参数异常,找不到methodName对应的Mapper");
        }

        // 3. 使用工具类执行sql语句,并返回封装好的结果集
        // 为什么需要传入conn对象,因为这个属于getMapper();此外sqlSession还有close()方法
        // conn对象对于这两个方法都是不透明的,所以conn应该作为sqlSession的成员,通过传参给两个方法使用
        return new Executor().selectList(conn,mapper);
    }
}
