package com.company.commandanalyzing;

import com.company.parsing.ParsingSettings;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandLineAnalyzerTest
{
    @Test
    public void getParsingSettingsFirstFiles()
    {
        CommandLineAnalyzer analyzer = new CommandLineAnalyzer();
        String inputFileName = "src/main/resources/input.html";
        String link = "http://52.136.215.164/broken-links/";
        String outputFileName = "src/main/resources/report.csv";

        ParsingSettings settings = analyzer.getParsingSettings(new String[]{"--files", inputFileName, "--links", link, "--out", outputFileName});

        assertEquals(1, settings.getInputFiles().size());
        assertEquals(1, settings.getLinks().size());
        assertNotNull(settings.getWriter());
    }

    @Test
    public void getParsingSettingsFirstLinks()
    {
        CommandLineAnalyzer analyzer = new CommandLineAnalyzer();
        String inputFileName = "src/main/resources/input.html";
        String link = "http://52.136.215.164/broken-links/";
        String outputFileName = "src/main/resources/report.csv";

        ParsingSettings settings = analyzer.getParsingSettings(new String[]{"--links", link, "--files", inputFileName, "--out", outputFileName});

        assertEquals(1, settings.getInputFiles().size());
        assertEquals(1, settings.getLinks().size());
        assertNotNull(settings.getWriter());
    }
}
