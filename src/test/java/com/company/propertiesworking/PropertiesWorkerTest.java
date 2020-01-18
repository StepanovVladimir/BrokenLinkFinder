package com.company.propertiesworking;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class PropertiesWorkerTest
{
    @Test
    public void getThreadsCountTest() throws IOException
    {
        PropertiesWorker propertiesWorker = new PropertiesWorker("src/main/resources/config.properties");

        int threadsCount = propertiesWorker.getThreadsCount();

        assertEquals(15, threadsCount);
    }
}
