package org.yuequan.statement;

import org.yuequan.reflection.factory.DefaultObjectFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * The type Result set handler.
 * @author yuequan
 */
public class ResultSetHandler {
    /**
     * Handle result set t.
     *
     * @param <T>       the type parameter
     * @param resultSet the result set
     * @param clazz     the clazz
     * @return the t
     * @throws SQLException the sql exception
     */
    public <T> T handleResultSet(ResultSet resultSet, Class clazz) throws SQLException {
        Object result = new DefaultObjectFactory().create(clazz);
        while(resultSet.next()){
            for (Field field : result.getClass().getDeclaredFields()) {
                setValue(result, field, resultSet);
            }
        }

        return (T) result;
    }

    /**
     * Set value.
     *
     * @param resultObject the result object
     * @param field        the field
     * @param resultSet    the result set
     */
    public void setValue(Object resultObject, Field field, ResultSet resultSet){
        try {
            Method setMethod = resultObject.getClass().getMethod("set" + upperCase(field.getName()), field.getType());
            setMethod.invoke(resultObject, getResult(field, resultSet));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Object getResult(Field field, ResultSet resultSet) throws SQLException {
        Class<?> type =  field.getType();
        if(Integer.class == type || int.class == type){
            return resultSet.getInt(field.getName());
        }
        if(Double.class == type || double.class == type){
            return resultSet.getDouble(field.getName());
        }
        return resultSet.getString(field.getName());
    }

    /**
     * Upper case string.
     *
     * @param str the str
     * @return the string
     */
    public String upperCase(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
