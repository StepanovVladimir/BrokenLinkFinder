package com.company.parsing;

import org.junit.jupiter.api.Test;

import java.io.StringWriter;
import java.io.Writer;

import static org.junit.jupiter.api.Assertions.*;

public class ParsingSettingsTest
{
    @Test
    public void constructorTest()
    {
        ParsingSettings settings = new ParsingSettings();

        assertTrue(settings.getInputFiles().isEmpty());
        assertTrue(settings.getLinks().isEmpty());
        assertNull(settings.getWriter());
    }

    @Test
    public void setWriterTest()
    {
        ParsingSettings settings = new ParsingSettings();
        Writer writer = new StringWriter();

        settings.setWriter(writer);

        assertEquals(writer, settings.getWriter());
    }
}
