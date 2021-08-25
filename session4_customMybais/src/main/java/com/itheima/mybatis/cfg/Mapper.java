package com.itheima.mybatis.cfg;

/**
 * @author kkddyz
 * 映射信息
 * 用于封装sql与resultType
 */
public class Mapper {
    private String queryString; //sql

    private String resultType;

    public String getQueryString() {
        return queryString;
    }

    public void setQueryString(String queryString) {
        this.queryString = queryString;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

}
