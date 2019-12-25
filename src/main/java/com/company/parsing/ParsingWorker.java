package com.company.parsing;

import com.company.Link;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ParsingWorker
{
    public ParsingWorker(ParsingSettings settings)
    {
        this.settings = settings;
    }

    public List<Link> work()
    {
        List<Link> brokenLinks = new ArrayList<>();
        BrokenLinksParser parser = new BrokenLinksParser();

        for (File file : settings.getInputFiles())
        {
            try
            {
                brokenLinks.addAll(parser.parse(file));
            }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
        }

        for (URL url : settings.getLinks())
        {
            try
            {
                brokenLinks.addAll(parser.parse(url));
            }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
        }

        return brokenLinks;
    }

    private ParsingSettings settings;
}
