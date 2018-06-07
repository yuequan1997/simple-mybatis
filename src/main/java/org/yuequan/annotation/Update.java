package org.yuequan.annotation;

import java.lang.annotation.*;

/**
 * The interface Update.
 * @author yuequan
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Update {
    /**
     * Sql string.
     *
     * @return the string
     */
    String sql();

    /**
     * Result class.
     *
     * @return the class
     */
    Class<?> result();
}
