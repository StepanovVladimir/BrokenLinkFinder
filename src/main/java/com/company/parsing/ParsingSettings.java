package com.company.parsing;

import java.io.File;
import java.io.Writer;
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

    public Writer getWriter()
    {
        return writer;
    }

    public void setWriter(Writer writer)
    {
        this.writer = writer;
    }

    private List<File> inputFiles = new ArrayList<>();
    private List<URL> links = new ArrayList<>();
    private Writer writer;
}
