package com.company.commandanalysis;

import java.net.MalformedURLException;
import java.net.URL;

class LinksState implements State
{
    public LinksState(CommandLineAnalyzer analyzer)
    {
        this.analyzer = analyzer;
    }

    @Override
    public void processCommand(String command)
    {
        if (command.equals("--files"))
        {
            setFilesState();
        }
        else if (command.equals("--out"))
        {
            setOutState();
        }
        else
        {
            addLink(command);
        }
    }

    private static final String NO_LINKS_SPECIFIED_MESSAGE = "You have not specified any links after the flag \"--links\"";

    private CommandLineAnalyzer analyzer;

    private void setFilesState()
    {
        if (analyzer.isNoLinks())
        {
            throw new IllegalArgumentException(NO_LINKS_SPECIFIED_MESSAGE);
        }
        analyzer.setLinksState();
    }

    private void setOutState()
    {
        if (analyzer.isNoLinks())
        {
            throw new IllegalArgumentException(NO_LINKS_SPECIFIED_MESSAGE);
        }
        analyzer.setOutState();
    }

    private void addLink(String link)
    {
        try
        {
            URL url = new URL(link);
            analyzer.addLink(url);
        }
        catch (MalformedURLException e)
        {
            throw new IllegalArgumentException("Argument \"" + link + "\" not a url");
        }
    }
}
