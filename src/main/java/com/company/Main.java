package com.company;

import com.company.commandanalysis.CommandLineAnalyzer;
import com.company.parsing.BrokenLinksParser;
import com.company.parsing.ParsingSettings;
import com.company.parsing.ParsingWorker;
import com.company.saving.LinksToCSVSaver;

import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        try
        {
            CommandLineAnalyzer analyzer = new CommandLineAnalyzer();
            ParsingSettings settings = analyzer.getParsingSettings(args);

            ParsingWorker<List<Link>> parsingWorker = new ParsingWorker<>(settings, new BrokenLinksParser(), new LinksToCSVSaver());
            parsingWorker.work();
        }
        catch (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
