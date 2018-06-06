package org.yuequan.mapper;

import org.yuequan.config.Configuration;
import org.yuequan.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MapperProxy implements InvocationHandler {
    private SqlSession sqlSession;

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(Configuration.DemoMapper.namespace.equals(method.getDeclaringClass().getName())){
            // TODO: 由于还没有定义SELECT DELETE INSERT之类的先写死在完善
            String sql = Configuration.DemoMapper.methodSqlMapping.get(method.getName());
            return sqlSession.findOne(sql, String.valueOf(args[0]));
        }
        return method.invoke(this, args);
    }

    public MapperProxy(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
}
