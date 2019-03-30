package com.wangyulong.fxmall.commons.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisSessionFactoryUtils {
    /**
     * 饿汉单例
     */
    public static SqlSessionFactory sqlSessionFactory;
    //threadlocal在数据不共享的情况下保证线程安全
    private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<>();
    static{
        initSessionFactory();
    }
    public static void initSessionFactory(){
        try {
            //1配置文件只需加载一次
            InputStream resourceAsStream = Resources.getResourceAsStream("mybatis.cfg.xml");
            //全局唯一
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static SqlSessionFactory getSqlSessionFactory(){
        if(sqlSessionFactory == null){
            initSessionFactory();
        }
        return sqlSessionFactory;
    }
    public static SqlSession getSession(){
        SqlSession session = threadLocal.get();
        if (session == null) {
            session = sqlSessionFactory.openSession();
            threadLocal.set(session);
        }
        return session;
    }
}
