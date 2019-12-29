package com.company;

import java.net.URL;

public class Link
{
    public Link(String url, int responseCode, String responseMessage)
    {
        this.url = url;
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    public String getUrl()
    {
        return url;
    }

    public int getResponseCode()
    {
        return responseCode;
    }

    public String getResponseMessage()
    {
        return responseMessage;
    }

    private String url;
    private int responseCode;
    private String responseMessage;
}
