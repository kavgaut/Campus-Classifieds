package com.my.friends;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
 
/**
 * A utility class that reads/saves SMTP settings from/to a properties file.
 * @author www.codejava.net
 *
 */
public class ConfigUtility {
    private File configFile = new File("/Users/kavyagautam/JAVA/FriendsApplication/src/com/my/friends/smtp.properties");
    private Properties configProps;
     
    public Properties loadProperties() throws IOException {
        Properties defaultProps = new Properties();
        // sets default properties
        defaultProps.setProperty("mail.smtp.host", "smtp.gmail.com");
        defaultProps.setProperty("mail.smtp.port", "587");
        defaultProps.setProperty("mail.user", "scu.ooadproj@gmail.com");
        defaultProps.setProperty("mail.password", "jorpdaoo");
        defaultProps.setProperty("mail.smtp.starttls.enable", "true");
        defaultProps.setProperty("mail.smtp.auth", "true");
         
        configProps = new Properties(defaultProps);
         
        // loads properties from file
        if (configFile.exists()) {
            InputStream inputStream = new FileInputStream(configFile);
            configProps.load(inputStream);
            inputStream.close();
        }
         
        return configProps;
    }
     
    public void saveProperties(String host, String port, String user, String pass) throws IOException {
        configProps.setProperty("mail.smtp.host", "smtp.gmail.com");
        configProps.setProperty("mail.smtp.port", "587");
        configProps.setProperty("mail.user", "scu.ooadproj@gmail.com");
        configProps.setProperty("mail.password", "jorpdaoo");
        configProps.setProperty("mail.smtp.starttls.enable", "true");
        configProps.setProperty("mail.smtp.auth", "true");
         
        OutputStream outputStream = new FileOutputStream(configFile);
        configProps.store(outputStream, "host setttings");
        outputStream.close();
    }  
}