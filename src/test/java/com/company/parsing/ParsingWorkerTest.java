package com.company.parsing;

import com.company.mocks.MockParser;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

public class ParsingWorkerTest
{
    @Test
    public void workTest() throws MalformedURLException
    {
        MockParser parser = new MockParser();
        File file = new File("src/main/resources/input.html");
        URL url = new URL("http://52.136.215.164/broken-links/");
        ParsingSettings settings = new ParsingSettings();
        settings.getInputFiles().add(file);
        settings.getLinks().add(url);
        ParsingWorker parsingWorker = new ParsingWorker(parser, settings);

        parsingWorker.work();

        assertEquals(file, parser.files.get(0));
        assertEquals(url, parser.urls.get(0));
    }
}
