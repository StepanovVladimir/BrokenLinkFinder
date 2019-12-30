package com.company.parsing.brokenlinks;

import com.company.Link;
import com.company.parsing.Parser;
import com.company.saving.Saver;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class BrokenLinksParser implements Parser<Link>
{
    public BrokenLinksParser()
    {
        try
        {
            FileInputStream fileInputStream = new FileInputStream("src/main/resources/config.properties");
            Properties properties = new Properties();
            properties.load(fileInputStream);
            threadsCount = Integer.parseInt(properties.getProperty("threadsCount"));
        }
        catch (IOException|NumberFormatException ignored){}
    }

    public void parse(File file, Saver<Link> linkSaver) throws IOException
    {
        Document document = Jsoup.parse(file, String.valueOf(StandardCharsets.UTF_8));
        parseBrokenLinks(document.getAllElements(), linkSaver);
    }

    public void parse(URL url, Saver<Link> linkSaver) throws IOException
    {
        Document document = Jsoup.connect(url.toString()).get();
        parseBrokenLinks(document.getAllElements(), url, linkSaver);
    }

    private int threadsCount = 0;

    private void parseBrokenLinks(Elements elements, Saver<Link> linkSaver)
    {
        elements.forEach(element -> {
            saveBrokenLink(element.attr("href"), linkSaver);
            saveBrokenLink(element.attr("src"), linkSaver);
        });
    }

    private void parseBrokenLinks(Elements elements, URL baseUrl, Saver<Link> linkSaver)
    {
        elements.forEach(element -> {
            saveBrokenLink(element.attr("href"), baseUrl, linkSaver);
            saveBrokenLink(element.attr("src"), baseUrl, linkSaver);
        });
    }

    private void saveBrokenLink(String attr, Saver<Link> linkSaver)
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

    private void saveBrokenLink(String attr, URL baseUrl, Saver<Link> linkSaver)
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
