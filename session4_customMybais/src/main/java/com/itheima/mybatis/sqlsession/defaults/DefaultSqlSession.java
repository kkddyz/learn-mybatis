package com.itheima.mybatis.sqlsession.defaults;

import com.itheima.mybatis.cfg.Configuration;
import com.itheima.mybatis.sqlsession.SqlSession;
import com.itheima.mybatis.sqlsession.proxy.MapperProxy;

import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * @author kkddyz
 */
public class DefaultSqlSession implements SqlSession {
    // 为什么要让session感知到Configuration的存在??
    private final Configuration cfg;
    private final Connection conn;

    public DefaultSqlSession(Configuration cfg, Connection conn) {
        this.cfg = cfg;
        this.conn = conn;
    }

    @Override
    public <T> T getMapper(Class<T> daoInterfaceClass) {
        Object mapper = Proxy.newProxyInstance(
                daoInterfaceClass.getClassLoader(),
                new Class[]{daoInterfaceClass},
                new MapperProxy(cfg.getMappers(), conn)
        );

        return (T) mapper;

    }

    @Override
    public void close() {
        if (conn != null) {
            try {
                conn.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
