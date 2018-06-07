package org.yuequan.config;

import java.util.HashMap;
import java.util.Map;


/**
 * The type Mapper registry.
 * @author yuequan
 */
public class MapperRegistry {
    private static final Map<String, Mapper> mapperMapping = new HashMap<>();

    /**
     * Add mapper.
     *
     * @param reference the reference
     * @param sql       the sql
     * @param type      the type
     */
    public  void addMapper(String reference, String sql, Class type){
        if(!mapperMapping.containsKey(reference)){
            mapperMapping.put(reference, new Mapper(sql, type));
        }
    }

    /**
     * Get mapper mapper.
     *
     * @param reference the reference
     * @return the mapper
     */
    public Mapper getMapper(String reference){
        return mapperMapping.get(reference);
    }

    /**
     * The type Mapper.
     *
     * @param <T> the type parameter
     */
    public class Mapper<T> {
        private String sql;
        private Class<T> type;

        /**
         * Instantiates a new Mapper.
         *
         * @param sql  the sql
         * @param type the type
         */
        public Mapper(String sql, Class<T> type) {
            this.sql = sql;
            this.type = type;
        }

        /**
         * Gets sql.
         *
         * @return the sql
         */
        public String getSql() {
            return sql;
        }

        /**
         * Sets sql.
         *
         * @param sql the sql
         */
        public void setSql(String sql) {
            this.sql = sql;
        }

        /**
         * Gets type.
         *
         * @return the type
         */
        public Class<T> getType() {
            return type;
        }

        /**
         * Sets type.
         *
         * @param type the type
         */
        public void setType(Class<T> type) {
            this.type = type;
        }
    }
}
