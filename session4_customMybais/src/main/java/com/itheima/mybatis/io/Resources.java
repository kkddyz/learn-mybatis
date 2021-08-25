package com.itheima.mybatis.io;

import java.io.InputStream;

/**
 * @author kkddyz
 * 使用类加载器读取配置文件
 */
public class Resources {
    /**
     *
     * @param filePath
     * @return
     */
    public static InputStream getResourceAsStream(String filePath) {


        return Resources.class.getClassLoader().getResourceAsStream(filePath);
    }

    public static void main(String[] args) {
        // sun.misc.Launcher$AppClassLoader
        System.out.println(Resources.class.getClassLoader());
    }
}
