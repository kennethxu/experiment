package com.sharneng.algorithm;

/**
 * Utility class contains common methods.
 * 
 * @author Kenneth Xu
 * 
 */
public final class Utils {
    private Utils() {
    }

    /**
     * Ensure an object is not null. Otherwise the {@link NullPointerException} is thrown.
     * 
     * @param obj
     *            the object to check for null
     * @param message
     *            the message used in the exception if obj is null
     * @param <T>
     *            the type of the obj
     * @return the obj parameter when it is not null
     * @exception NullPointerException
     *                when obj is null
     */
    public static <T> T ensureNotNull(T obj, String message) {
        if (obj == null) throw new NullPointerException(message);
        return obj;
    }

    /**
     * Ensure the argument is not null. Otherwise the {@link NullPointerException} is thrown.
     * 
     * @param arg
     *            the object to check for null
     * @param name
     *            the argument name used in the exception if obj is null
     * @param <T>
     *            the type of the arg
     * @return the arg parameter when it is not null
     * @exception NullPointerException
     *                when arg is null
     */
    public static <T> T argumentNotNull(T arg, String name) {
        return ensureNotNull(arg, "Argument '" + name + "' must not be null");
    }
}
