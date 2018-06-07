package org.yuequan.statement;

import org.yuequan.config.Configuration;
import org.yuequan.config.MapperRegistry;
import org.yuequan.parameter.ParameterHandler;

import java.sql.*;


/**
 * The type Statement handler.
 * @author yuequan
 */
public class StatementHandler {
    /**
     * The Configuration.
     */
    Configuration configuration;

    /**
     * Instantiates a new Statement handler.
     *
     * @param configuration the configuration
     */
    public StatementHandler(Configuration configuration){
        this.configuration = configuration;
    }

    /**
     * Get connection connection.
     *
     * @return the connection
     */
    public Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName(configuration.getDriverClass());
            connection = DriverManager.getConnection(configuration.getUrl(), configuration.getUsername(), configuration.getPassword());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Query t.
     *
     * @param <T>       the type parameter
     * @param mapper    the mapper
     * @param parameter the parameter
     * @return the t
     */
    public <T> T query(MapperRegistry.Mapper mapper, Object[] parameter){
        String sql = mapper.getSql();
        Connection connection = getConnection();
        try {
            PreparedStatement statement =  connection.prepareStatement(sql);
            ParameterHandler parameterHandler = new ParameterHandler();
            parameterHandler.setParameters(statement, parameter);
            ResultSet resultSet = statement.executeQuery();
            ResultSetHandler resultSetHandler = new ResultSetHandler();
            return resultSetHandler.handleResultSet(resultSet, mapper.getType());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
