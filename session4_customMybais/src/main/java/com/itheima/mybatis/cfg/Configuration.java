package com.itheima.mybatis.cfg;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kkddyz
 * 自定义mybatis配置类
 * 连接信息 + 映射信息对象引用
 */
public class Configuration {
    private String driver;
    private String url;
    private String username;

    public Map<String, Mapper> getMappers() {
        return mappers;
    }

    public void setMappers(Map<String, Mapper> mappers) {
        // 使用追加的方式将不同的Mapper文件中的配置的不同接口函数映射信息存储到mappers中
        this.mappers.putAll(mappers);
    }

    private Map<String,Mapper> mappers = new HashMap<>();



    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String password;

}
