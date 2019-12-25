package com.company.parsing;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ParsingSettings
{
    public List<File> getInputFiles()
    {
        return inputFiles;
    }

    public List<URL> getLinks()
    {
        return links;
    }

    public FileWriter getOutputFile()
    {
        return outputFile;
    }

    public void setOutputFile(FileWriter outputFile)
    {
        this.outputFile = outputFile;
    }

    private List<File> inputFiles = new ArrayList<>();
    private List<URL> links = new ArrayList<>();
    private FileWriter outputFile;
}
