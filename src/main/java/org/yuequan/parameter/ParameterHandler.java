package org.yuequan.parameter;

import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * The type Parameter handler.
 * @author yuequan
 */
public class ParameterHandler {
    /**
     * Set parameters.
     *
     * @param statement the statement
     * @param params    the params
     */
    public void setParameters(PreparedStatement statement, Object[] params){
        try {
            for (int i = 0; i < params.length; i++) {
                if(params[i] instanceof String){
                    statement.setString(i+1, String.valueOf(params[i]));
                }else if(params[i] instanceof Integer){
                    statement.setInt(i+1, (Integer) params[i]);
                }else if(params[i] instanceof  Double){
                    statement.setDouble(i+1, (Double) params[i]);
                }else{
                    throw new IllegalArgumentException("Not support type: " + params[i]);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
