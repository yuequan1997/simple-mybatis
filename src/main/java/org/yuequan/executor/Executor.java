package org.yuequan.executor;

import org.yuequan.config.MapperRegistry;


/**
 * The interface Executor.
 * @author yuequan
 */
public interface Executor {
    /**
     * Query t.
     *
     * @param <T>       the type parameter
     * @param mapper    the mapper
     * @param parameter the parameter
     * @return the t
     */
    <T> T query(MapperRegistry.Mapper mapper, Object[] parameter);
}
