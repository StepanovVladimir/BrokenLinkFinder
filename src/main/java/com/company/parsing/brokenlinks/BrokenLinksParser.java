package com.company.parsing.brokenlinks;

import com.company.entities.Link;
import com.company.parsing.Parser;
import com.company.saving.Saver;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class BrokenLinksParser implements Parser
{
    public BrokenLinksParser(Saver<Link> linkSaver, int threadsCount)
    {
        this.linkSaver = linkSaver;
        this.threadsCount = threadsCount;
    }

    public void parse(File file) throws IOException
    {
        Document document = Jsoup.parse(file, String.valueOf(StandardCharsets.UTF_8));
        parseBrokenLinks(document.getAllElements());
    }

    public void parse(URL url) throws IOException
    {
        Document document = Jsoup.connect(url.toString()).get();
        parseBrokenLinks(document.getAllElements(), url);
    }

    private Saver<Link> linkSaver;
    private int threadsCount;

    private void parseBrokenLinks(Elements elements)
    {
        elements.forEach(element -> {
            saveBrokenLink(element.attr("href"));
            saveBrokenLink(element.attr("src"));
        });
    }

    private void parseBrokenLinks(Elements elements, URL baseUrl)
    {
        elements.forEach(element -> {
            saveBrokenLink(element.attr("href"), baseUrl);
            saveBrokenLink(element.attr("src"), baseUrl);
        });
    }

    private void saveBrokenLink(String attr)
    {
        if (!attr.isEmpty() && !attr.equals("#"))
        {
            try
            {
                URL url = new URL(attr);
                makeRequest(url, linkSaver);
            }
            catch (IOException e)
            {
                linkSaver.save(new Link(attr, -1, e.getMessage()));
            }
        }
    }

    private void saveBrokenLink(String attr, URL baseUrl)
    {
        if (!attr.isEmpty() && !attr.equals("#"))
        {
            try
            {
                URL url = new URL(baseUrl, attr);
                makeRequest(url, linkSaver);
            }
            catch (IOException e)
            {
                linkSaver.save(new Link(attr, -1, e.getMessage()));
            }
        }
    }

    private void makeRequest(URL url, Saver<Link> linkSaver)
    {
        if (Thread.activeCount() < threadsCount)
        {
            Thread thread = new Thread(new BrokenLinksRequester(url, linkSaver));
            thread.start();
        }
        else
        {
            BrokenLinksRequester requester = new BrokenLinksRequester(url, linkSaver);
            requester.run();
        }
    }
}
