package com.holis.san01.security;

public class SecurityConstants {

    public static final long JWT_EXPIRATION = 70000;

    /* cada byte = 6 bits */
    public static final String JWT_SECRET = "q3t6w9zCAFtJBNcQfTjWnZr4u7xWAFDrGgKaPdSgUkXp2s5v8yyB2EoH4MbQeThXq3t6w9zCAFtJBNcQfTjWCASiss";
    public static final String JWT_HEADER = "Authorization";
    public static final int TOKEN_INDEX = 7;
    public static final String TOKEN_PREFIX = "Bearer ";
}
