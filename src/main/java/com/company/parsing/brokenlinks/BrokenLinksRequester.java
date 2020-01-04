package com.company.parsing.brokenlinks;

import com.company.entities.Link;
import com.company.saving.Saver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class BrokenLinksRequester implements Runnable
{
    public BrokenLinksRequester(URL url, Saver<Link> linkSaver)
    {
        this.url = url;
        this.linkSaver = linkSaver;
    }

    @Override
    public void run()
    {
        try
        {
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            int responseCode = connection.getResponseCode();
            if (responseCode / 100 != 2)
            {
                linkSaver.save(new Link(url.toString(), responseCode, connection.getResponseMessage()));
            }
        }
        catch (IOException e)
        {
            linkSaver.save(new Link(url.toString(), 504, e.getMessage()));
        }
    }

    private URL url;
    private Saver<Link> linkSaver;
}
