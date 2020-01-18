package com.company;

import com.company.commandanalyzing.CommandLineAnalyzer;
import com.company.parsing.brokenlinks.BrokenLinksParser;
import com.company.parsing.ParsingSettings;
import com.company.parsing.ParsingWorker;
import com.company.propertiesworking.PropertiesWorker;
import com.company.saving.LinkToCSVSaver;

import java.io.IOException;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            CommandLineAnalyzer analyzer = new CommandLineAnalyzer();
            ParsingSettings settings = analyzer.getParsingSettings(args);

            LinkToCSVSaver saver = new LinkToCSVSaver(settings.getWriter());
            BrokenLinksParser parser;
            try
            {
                PropertiesWorker propertiesWorker = new PropertiesWorker("src/main/resources/config.properties");
                parser = new BrokenLinksParser(saver, propertiesWorker.getThreadsCount(), propertiesWorker.getConnectTimeout());
            }
            catch (IOException e)
            {
                parser = new BrokenLinksParser(saver, 0, 10000);
            }

            ParsingWorker parsingWorker = new ParsingWorker(parser, settings);
            parsingWorker.work();
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
