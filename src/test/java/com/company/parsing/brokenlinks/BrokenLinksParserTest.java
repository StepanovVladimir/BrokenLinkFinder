package com.company.parsing.brokenlinks;

import com.company.mocks.MockSaver;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

public class BrokenLinksParserTest
{
    @Test
    public void parseFile() throws IOException
    {
        MockSaver saver = new MockSaver();
        BrokenLinksParser parser = new BrokenLinksParser(saver, 0, 10000);

        parser.parse(new File("src/main/resources/input.html"));

        assertEquals(7, saver.links.size());
    }

    @Test
    public void parseUrl() throws IOException
    {
        MockSaver saver = new MockSaver();
        BrokenLinksParser parser = new BrokenLinksParser(saver, 7, 10000);

        parser.parse(new URL("http://52.136.215.164/broken-links/"));

        assertEquals(3, saver.links.size());
    }
}
