package com.company.parsing;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ParsingWorker
{
    public ParsingWorker(Parser parser, ParsingSettings settings)
    {
        this.settings = settings;
        this.parser = parser;
    }

    public void work()
    {
        for (File file : settings.getInputFiles())
        {
            try
            {
                parser.parse(file);
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
                parser.parse(url);
            }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

    private ParsingSettings settings;
    private Parser parser;
}
