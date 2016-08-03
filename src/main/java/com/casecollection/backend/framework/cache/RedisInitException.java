package com.casecollection.backend.framework.cache;

public class RedisInitException extends RuntimeException {

    private static final long serialVersionUID = 8749261219309443873L;

    public RedisInitException() {
        super();
    }

    public RedisInitException(String message) {
        super(message);
    }

    public RedisInitException(String message, Throwable cause) {
        super(message, cause);
    }

}