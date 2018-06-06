package org.yuequan.config;

import org.yuequan.mapper.MapperProxy;
import org.yuequan.session.SqlSession;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class Configuration {
    public <T> T getMapper(Class<T> clazz, SqlSession sqlSession) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{clazz}, new MapperProxy(sqlSession));
    }

    public static class DemoMapper {
        public static final String namespace = "org.yuequan.mapper.DemoMapper";
        public static Map<String, String> methodSqlMapping = new HashMap<>();
        static {
            methodSqlMapping.put("findById", "select * from demo");
        }
    }
}
