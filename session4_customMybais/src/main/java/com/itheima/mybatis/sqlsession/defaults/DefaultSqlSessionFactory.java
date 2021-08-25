package com.itheima.mybatis.sqlsession.defaults;

import com.itheima.mybatis.cfg.Configuration;
import com.itheima.mybatis.sqlsession.SqlSession;
import com.itheima.mybatis.sqlsession.SqlSessionFactory;
import com.itheima.mybatis.utils.DataSourceUtil;

import java.sql.Connection;

/**
 * @author kkddyz
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    private Configuration cfg;
    private Connection conn;

    public DefaultSqlSessionFactory(Configuration cfg) {
        this.cfg = cfg;

        // conn对象,显然是一个SqlSession的内嵌对象
        // 根据cfg中的参数创建Conn
        conn = DataSourceUtil.getConnection(cfg);
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(cfg,conn);
    }
}
