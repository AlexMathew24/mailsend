package com.data.mailssend;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtil {
    private static final String JDBC_URL="jdbc:mysql://localhost:3306/data";
    private static  final String USERNAME="root";
    private static final String PASSWORD="123456";
    public static Connection getConnection() throws SQLException{
        Properties props =new Properties();
        props.setProperty("user",USERNAME);
        props.setProperty("password",PASSWORD);
        return DriverManager.getConnection(JDBC_URL,props);
    }
}
