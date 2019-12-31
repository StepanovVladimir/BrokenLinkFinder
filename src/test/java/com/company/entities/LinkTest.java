package com.company.entities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkTest
{
    @Test
    public void constructorTest()
    {
        String url = "http://52.136.215.164/links/return500bad.php";
        int responseCode = 500;
        String responseMessage = "Internal Server Error";

        Link link = new Link(url, responseCode, responseMessage);

        assertEquals(url, link.getUrl());
        assertEquals(responseCode, link.getResponseCode());
        assertEquals(responseMessage, link.getResponseMessage());
    }
}
