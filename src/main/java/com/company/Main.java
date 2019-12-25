package com.company;

import com.company.commandanalysis.CommandLineAnalyzer;
import com.company.parsing.ParsingSettings;
import com.company.parsing.ParsingWorker;

import java.io.*;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            CommandLineAnalyzer analyzer = new CommandLineAnalyzer();
            ParsingSettings settings = analyzer.getParsingSettings(args);

            ParsingWorker parsingWorker = new ParsingWorker(settings);
            List<Link> brokenLinks = parsingWorker.work();

            LinksToCSVSaver linksSaver = new LinksToCSVSaver();
            linksSaver.save(brokenLinks, settings.getOutputFile());
        }
        catch (IllegalArgumentException|IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
