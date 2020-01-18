package com.company.propertiesworking;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesWorker
{
    public PropertiesWorker(String path) throws IOException
    {
        FileInputStream fileInputStream = new FileInputStream(path);
        properties.load(fileInputStream);
    }

    public int getThreadsCount()
    {
        try
        {
            return Integer.parseInt(properties.getProperty("threadsCount"));
        }
        catch (NumberFormatException e)
        {
            return 0;
        }
    }

    public int getConnectTimeout()
    {
        try
        {
            return Integer.parseInt(properties.getProperty("connectTimeout"));
        }
        catch (NumberFormatException e)
        {
            return 10000;
        }
    }

    Properties properties = new Properties();
}
