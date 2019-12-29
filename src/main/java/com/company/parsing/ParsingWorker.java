package com.company.parsing;

import com.company.saving.Saver;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ParsingWorker<T>
{
    public ParsingWorker(ParsingSettings settings, Parser<T> parser, Saver<T> saver)
    {
        this.settings = settings;
        this.parser = parser;
        this.saver = saver;
    }

    public void work()
    {
        for (File file : settings.getInputFiles())
        {
            try
            {
                T result = parser.parse(file);
                saver.save(result, settings.getWriter());
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
                saver.save(parser.parse(url), settings.getWriter());
            }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
        }

        try
        {
            settings.getWriter().close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private ParsingSettings settings;
    private Parser<T> parser;
    private Saver<T> saver;
}
