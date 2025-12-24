package com.holis.san01.db;

public class DbContext {
    private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

    public static String getDb() {
        return CONTEXT.get();
    }

    public static void setDb(String db) {
        CONTEXT.set(db);
    }

    public static void clear() {
        CONTEXT.remove();
    }
}
