package org.yuequan.session;

import org.yuequan.config.Configuration;
import org.yuequan.config.MapperRegistry;
import org.yuequan.executor.Executor;


/**
 * The type Sql session.
 * @author yuequan
 */
public class SqlSession {
    private Configuration configuration;
    private Executor executor;

    /**
     * Instantiates a new Sql session.
     *
     * @param configuration the configuration
     * @param executor      the executor
     */
    public SqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    /**
     * Get mapper t.
     *
     * @param <T>   the type parameter
     * @param clazz the clazz
     * @return the t
     */
    public <T> T getMapper(Class<T> clazz){
        return configuration.getMapper(clazz, this);
    }

    /**
     * Find one t.
     *
     * @param <T>       the type parameter
     * @param mapper    the mapper
     * @param parameter the parameter
     * @return the t
     */
    public <T> T findOne(MapperRegistry.Mapper mapper, Object[] parameter){
        return executor.query(mapper, parameter);
    }

    /**
     * Gets configuration.
     *
     * @return the configuration
     */
    public Configuration getConfiguration() {
        return configuration;
    }
}
