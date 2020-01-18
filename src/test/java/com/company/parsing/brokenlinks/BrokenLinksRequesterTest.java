package com.company.parsing.brokenlinks;

import com.company.mocks.MockSaver;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

public class BrokenLinksRequesterTest
{
    @Test
    public void runValidLink() throws IOException
    {
        MockSaver saver = new MockSaver();
        BrokenLinksRequester requester = new BrokenLinksRequester(new URL("http://52.136.215.164/broken-links/"), saver, 10000);

        requester.run();

        assertTrue(saver.links.isEmpty());
    }

    @Test
    public void runBrokenLink() throws MalformedURLException
    {
        MockSaver saver = new MockSaver();
        String url = "http://52.136.215.164/links/return500bad.php";
        int responseCode = 500;
        String responseMessage = "Internal Server Error";
        BrokenLinksRequester requester = new BrokenLinksRequester(new URL(url), saver, 10000);

        requester.run();

        assertEquals(url, saver.links.get(0).getUrl());
        assertEquals(responseCode, saver.links.get(0).getResponseCode());
        assertEquals(responseMessage, saver.links.get(0).getResponseMessage());
    }
}
