package org.yuequan.config;

import org.yuequan.annotation.Select;
import org.yuequan.binding.MapperProxy;
import org.yuequan.session.SqlSession;
import org.yuequan.util.ClassUtil;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Set;


/**
 * The type Configuration.
 * @author yuequan
 */
public class Configuration {

    private String driverClass;
    private String url;
    private String username;
    private String password;
    private MapperRegistry mapperRegistry;

    /**
     * Instantiates a new Configuration.
     *
     * @param driverClass the driver class
     * @param url         the url
     * @param username    the username
     * @param password    the password
     * @param mapperPath  the mapper path
     */
    public Configuration(String driverClass, String url, String username, String password, String... mapperPath) {
        this.driverClass = driverClass;
        this.url = url;
        this.username = username;
        this.password = password;
        mapperRegistry = new MapperRegistry();
        registryMapper(mapperPath);
    }

    /**
     * Gets driver class.
     *
     * @return the driver class
     */
    public String getDriverClass() {
        return driverClass;
    }

    /**
     * Gets url.
     *
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets mapper.
     *
     * @param <T>        the type parameter
     * @param clazz      the clazz
     * @param sqlSession the sql session
     * @return the mapper
     */
    public <T> T getMapper(Class<T> clazz, SqlSession sqlSession) {
        return (T) Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{clazz}, new MapperProxy(sqlSession));
    }

    /**
     * Gets mapper registry.
     *
     * @return the mapper registry
     */
    public MapperRegistry getMapperRegistry() {
        return mapperRegistry;
    }

    private  void registryMapper(String ... mapperPath){
        Set<Class<?>> classes = ClassUtil.getClasses(mapperPath);
        classes.forEach(clazz -> {
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println(method);
                if(method.getAnnotation(Select.class) != null){
                    Select select = method.getAnnotation(Select.class);
                    mapperRegistry.addMapper(method.getDeclaringClass().getName() + "." + method.getName(), select.sql(), select.result());
                }
                //TODO: DELETE UPDATE INSERT
            }
        });
   }
}
