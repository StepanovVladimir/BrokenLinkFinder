package com.company;

import com.company.commandanalyzing.CommandLineAnalyzer;
import com.company.entities.Link;
import com.company.parsing.brokenlinks.BrokenLinksParser;
import com.company.parsing.ParsingSettings;
import com.company.parsing.ParsingWorker;
import com.company.propertiesworking.PropertiesWorker;
import com.company.saving.LinkToCSVSaver;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            CommandLineAnalyzer analyzer = new CommandLineAnalyzer();
            ParsingSettings settings = analyzer.getParsingSettings(args);

            PropertiesWorker propertiesWorker = new PropertiesWorker();
            int threadsCount = propertiesWorker.getThreadsCount();

            LinkToCSVSaver saver = new LinkToCSVSaver(settings.getWriter());
            BrokenLinksParser parser = new BrokenLinksParser(saver, threadsCount);

            ParsingWorker parsingWorker = new ParsingWorker(parser, settings);

            parsingWorker.work();
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
