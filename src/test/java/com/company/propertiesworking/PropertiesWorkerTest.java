package com.company.propertiesworking;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PropertiesWorkerTest
{
    @Test
    public void getThreadsCountTest()
    {
        PropertiesWorker propertiesWorker = new PropertiesWorker();

        int threadsCount = propertiesWorker.getThreadsCount();

        assertEquals(15, threadsCount);
    }
}
