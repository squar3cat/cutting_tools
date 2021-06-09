package com.app.tools;

public class Profiles {

    public static final String
            POSTGRES_DB = "postgres",
            HSQL_DB = "hsqldb";

    public static String getActiveDbProfile() {
        if (isClassExists("org.postgresql.Driver")) {
            return POSTGRES_DB;
        } else if (isClassExists("org.hsqldb.jdbcDriver")) {
            return HSQL_DB;
        } else {
            throw new IllegalStateException("Could not find DB driver");
        }
    }

    private static boolean isClassExists(String name) {
        try {
            Class.forName(name);
            return true;
        } catch (ClassNotFoundException ex) {
            return false;
        }
    }
}
