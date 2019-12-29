package com.company.commandanalysis;

import com.company.parsing.ParsingSettings;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;

public class CommandLineAnalyzer
{
    public ParsingSettings getParsingSettings(String[] args)
    {
        settings = new ParsingSettings();
        setStartState();
        for (String command : args)
        {
            state.processCommand(command);
        }

        if (!thereIsOutputFile())
        {
            throw new IllegalArgumentException("Not all arguments are specified");
        }
        return settings;
    }

    void setStartState()
    {
        state = startState;
    }

    void setFilesState()
    {
        if (!isNoInputFiles())
        {
            throw new IllegalArgumentException("You set the flag \"--files\" for the second time");
        }
        state = filesState;
    }

    void setLinksState()
    {
        if (!isNoLinks())
        {
            throw new IllegalArgumentException("You set the flag \"--links\" for the second time");
        }
        state = linksState;
    }

    void setOutState()
    {
        state = outState;
    }

    boolean isNoInputFiles()
    {
        return settings.getInputFiles().isEmpty();
    }

    boolean isNoLinks()
    {
        return settings.getLinks().isEmpty();
    }

    boolean thereIsOutputFile()
    {
        return settings.getWriter() != null;
    }

    void addInputFile(File file)
    {
        settings.getInputFiles().add(file);
    }

    void addLink(URL link)
    {
        settings.getLinks().add(link);
    }

    void setOutputFile(FileWriter file)
    {
        settings.setWriter(file);
    }

    private final State startState = new StartState(this);
    private final State filesState = new FilesState(this);
    private final State linksState = new LinksState(this);
    private final State outState = new OutState(this);

    private State state;
    private ParsingSettings settings;
}
