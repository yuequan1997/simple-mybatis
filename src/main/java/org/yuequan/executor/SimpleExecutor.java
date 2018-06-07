package org.yuequan.executor;

import org.yuequan.config.Configuration;
import org.yuequan.config.MapperRegistry;
import org.yuequan.statement.StatementHandler;


/**
 * The type Simple executor.
 * @author yuequan
 */
public class SimpleExecutor implements Executor {
    private Configuration configuration;

    /**
     * Instantiates a new Simple executor.
     *
     * @param configuration the configuration
     */
    public SimpleExecutor(Configuration configuration){
        this.configuration = configuration;
    }
    @Override
    public <T> T query(MapperRegistry.Mapper mapper, Object[] parameter) {
        //TODO
        StatementHandler statementHandler = new StatementHandler(configuration);
        return statementHandler.query(mapper, parameter);
    }
}
