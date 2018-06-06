package org.yuequan.session;

import org.yuequan.config.Configuration;
import org.yuequan.executor.Executor;

public class SqlSession {
    private Configuration configuration;
    private Executor executor;

    public SqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    public <T> T getMapper(Class<T> clazz){
        return configuration.getMapper(clazz, this);
    }

    public <T> T findOne(String statement, String paramter){
        return executor.query(statement, paramter);
    }
}
