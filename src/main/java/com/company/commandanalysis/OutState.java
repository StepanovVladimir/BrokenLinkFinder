package com.company.commandanalysis;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class OutState implements State
{
    public OutState(CommandLineAnalyzer analyzer)
    {
        this.analyzer = analyzer;
    }

    @Override
    public void processCommand(String command)
    {
        if (analyzer.thereIsOutputFile())
        {
            throw new IllegalArgumentException("There can be only one output file");
        }

        try
        {
            FileWriter writer = new FileWriter(command);
            analyzer.setOutputFile(writer);
        }
        catch (IOException e)
        {
            throw new IllegalArgumentException("File \"" + command + "\" is non-existent and cannot be created");
        }
    }

    private CommandLineAnalyzer analyzer;
}
