package com.data.mailssend;

import java.io.IOException;
import java.util.Properties;

public class ApplicationProperties {
    private static final Properties properties;
    static {
        properties=new Properties();
        try{
            properties.load(MailssendApplication.class.getResourceAsStream("/application.properties"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}
