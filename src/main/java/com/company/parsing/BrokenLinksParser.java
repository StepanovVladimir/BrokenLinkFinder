package com.company.parsing;

import com.company.Link;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class BrokenLinksParser implements Parser<List<Link>>
{
    public List<Link> parse(File file) throws IOException
    {
        Document document = Jsoup.parse(file, String.valueOf(StandardCharsets.UTF_8));
        return parseBrokenLinks(document.getAllElements());
    }

    public List<Link> parse(URL url) throws IOException
    {
        Document document = Jsoup.connect(url.toString()).get();
        return parseBrokenLinks(document.getAllElements(), url);
    }

    private List<Link> parseBrokenLinks(Elements elements)
    {
        List<Link> brokenLinks = new ArrayList<>();

        elements.forEach(element -> {
            addBrokenLinkToList(element.attr("href"), brokenLinks);
            addBrokenLinkToList(element.attr("src"), brokenLinks);
        });

        return brokenLinks;
    }

    private List<Link> parseBrokenLinks(Elements elements, URL baseUrl)
    {
        List<Link> brokenLinks = new ArrayList<>();

        elements.forEach(element -> {
            addBrokenLinkToList(element.attr("href"), baseUrl, brokenLinks);
            addBrokenLinkToList(element.attr("src"), baseUrl, brokenLinks);
        });

        return brokenLinks;
    }

    private void addBrokenLinkToList(String attr, List<Link> brokenLinks)
    {
        if (!attr.isEmpty() && !attr.equals("#"))
        {
            try
            {
                URL url = new URL(attr);

                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                int responseCode = connection.getResponseCode();
                if (responseCode != 200)
                {
                    brokenLinks.add(new Link(url.toString(), responseCode, connection.getResponseMessage()));
                }
            }
            catch (IOException e)
            {
                brokenLinks.add(new Link(attr, -1, e.getMessage()));
            }
        }
    }

    private void addBrokenLinkToList(String attr, URL baseUrl, List<Link> brokenLinks)
    {
        if (!attr.isEmpty() && !attr.equals("#"))
        {
            try
            {
                URL url = new URL(baseUrl, attr);

                HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                int responseCode = connection.getResponseCode();
                if (responseCode != 200)
                {
                    brokenLinks.add(new Link(url.toString(), responseCode, connection.getResponseMessage()));
                }
            }
            catch (IOException e)
            {
                brokenLinks.add(new Link(attr, -1, e.getMessage()));
            }
        }
    }
}
