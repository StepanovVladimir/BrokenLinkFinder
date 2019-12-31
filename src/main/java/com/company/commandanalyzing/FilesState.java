package com.company.commandanalyzing;

import java.io.File;

class FilesState implements State
{
    public FilesState(CommandLineAnalyzer analyzer)
    {
        this.analyzer = analyzer;
    }

    @Override
    public void processCommand(String command)
    {
        if (command.equals("--links"))
        {
            setLinksState();
        }
        else if (command.equals("--out"))
        {
            setOutState();
        }
        else
        {
            addInputFile(command);
        }
    }

    private static final String NO_FILES_SPECIFIED_MESSAGE = "You have not specified any input files after the flag \"--files\"";

    private CommandLineAnalyzer analyzer;

    private void setLinksState()
    {
        if (analyzer.isNoInputFiles())
        {
            throw new IllegalArgumentException(NO_FILES_SPECIFIED_MESSAGE);
        }
        analyzer.setLinksState();
    }

    private void setOutState()
    {
        if (analyzer.isNoInputFiles())
        {
            throw new IllegalArgumentException(NO_FILES_SPECIFIED_MESSAGE);
        }
        analyzer.setOutState();
    }

    private void addInputFile(String fileName)
    {
        File file = new File(fileName);
        if (!file.isFile())
        {
            throw new IllegalArgumentException("Non-existent file \"" + fileName + "\"");
        }
        analyzer.addInputFile(file);
    }
}
