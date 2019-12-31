package com.company.propertiesworking;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesWorker
{
    public int getThreadsCount()
    {
        try
        {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties");
            Properties properties = new Properties();
            properties.load(fileInputStream);
            return Integer.parseInt(properties.getProperty("threadsCount"));
        }
        catch (IOException|NumberFormatException e)
        {
            return 0;
        }
    }
}
