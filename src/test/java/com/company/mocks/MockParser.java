package com.company.mocks;

import com.company.parsing.Parser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MockParser implements Parser
{
    @Override
    public void parse(File file) throws IOException
    {
        files.add(file);
    }

    @Override
    public void parse(URL url) throws IOException
    {
        urls.add(url);
    }

    public List<File> files = new ArrayList<>();
    public List<URL> urls = new ArrayList<>();
}
