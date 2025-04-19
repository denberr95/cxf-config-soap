package com.cfx.setup.soap.util;

public class Constants {

    private Constants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    
    public static final String DEFAULT_USERNAME = "ANONYMOUS";
    public static final String DEFAULT_PASSWORD = "ANONYMOUS";
    public static final String DEFAULT_URL = "";
    public static final long DEFAULT_RECEIVE_TIMEOUT = 30000L;
    public static final long DEFAULT_CONNECTION_TIMEOUT = 10000L;
    public static final boolean DEFAULT_ALLOW_CHUNKING = false;

}
