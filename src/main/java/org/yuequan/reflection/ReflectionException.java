package org.yuequan.reflection;


/**
 * The type Reflection exception.
 * @author yuequan
 */
public class ReflectionException extends RuntimeException {
    /**
     * Instantiates a new Reflection exception.
     */
    public ReflectionException() {
    }

    /**
     * Instantiates a new Reflection exception.
     *
     * @param message the message
     */
    public ReflectionException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Reflection exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public ReflectionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new Reflection exception.
     *
     * @param cause the cause
     */
    public ReflectionException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new Reflection exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public ReflectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
