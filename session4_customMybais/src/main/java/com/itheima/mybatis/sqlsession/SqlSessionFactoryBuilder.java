package com.itheima.mybatis.sqlsession;

import com.itheima.mybatis.cfg.Configuration;
import com.itheima.mybatis.sqlsession.defaults.DefaultSqlSessionFactory;
import com.itheima.mybatis.utils.XMLConfigBuilder;

import java.io.InputStream;

/**
 * @author kkddyz
 * 创建SqlsessionFactory对象
 * factoryBuilder set the fileds for factory 通过读取xml
 */
public class SqlSessionFactoryBuilder {
    /**
     * 根据参数的输入流创建SqlSessionFactory
     * @param config 配置文件流
     * @return SqlSessionFactory
     */
    public SqlSessionFactory build(InputStream config){
        // 使用XMLConfigBuilder工具类帮助我们从XML获取配置,并封装到Configuration对象
        Configuration cfg = XMLConfigBuilder.loadConfiguration(config);



        // builder将factory需要的材料(Configuration,conn)交给他之后,factory就可以开始生产了.
        return new DefaultSqlSessionFactory(cfg);


    }
}
