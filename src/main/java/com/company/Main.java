package com.company;

import com.company.commandanalysis.CommandLineAnalyzer;
import com.company.parsing.brokenlinks.BrokenLinksParser;
import com.company.parsing.ParsingSettings;
import com.company.parsing.ParsingWorker;
import com.company.saving.LinkToCSVSaver;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            CommandLineAnalyzer analyzer = new CommandLineAnalyzer();
            ParsingSettings settings = analyzer.getParsingSettings(args);

            BrokenLinksParser parser = new BrokenLinksParser();
            LinkToCSVSaver saver = new LinkToCSVSaver(settings.getWriter());
            ParsingWorker<Link> parsingWorker = new ParsingWorker<>(settings, parser, saver);

            parsingWorker.work();
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
