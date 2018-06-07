package org.yuequan.binding;

import org.yuequan.config.Configuration;
import org.yuequan.config.MapperRegistry;
import org.yuequan.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * The type Mapper proxy.
 * @author yuequan
 */
public class MapperProxy implements InvocationHandler {
    private SqlSession sqlSession;

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MapperRegistry.Mapper mapper = sqlSession.getConfiguration().getMapperRegistry().getMapper(method.getDeclaringClass().getName() + "." + method.getName());
        if(mapper != null){
            return sqlSession.findOne(mapper, args);
        }
//        if(Configuration.DemoMapper.namespace.equals(method.getDeclaringClass().getName())){
//            // TODO: 由于还没有定义SELECT DELETE INSERT之类的先写死在完善
//            String sql = Configuration.DemoMapper.methodSqlMapping.get(method.getName());
//            return sqlSession.findOne(sql, String.valueOf(args[0]));
//        }
        return method.invoke(this, args);
    }

    /**
     * Instantiates a new Mapper proxy.
     *
     * @param sqlSession the sql session
     */
    public MapperProxy(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }
}
