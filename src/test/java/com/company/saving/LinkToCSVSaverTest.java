package com.company.saving;

import com.company.entities.Link;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

public class LinkToCSVSaverTest
{
    @Test
    public void saveTest()
    {
        StringWriter writer = new StringWriter();
        LinkToCSVSaver saver = new LinkToCSVSaver(writer);
        String url = "http://52.136.215.164/links/return500bad.php";
        int responseCode = 500;
        String responseMessage = "Internal Server Error";

        saver.save(new Link(url, responseCode, responseMessage));

        assertEquals("Url,Response code,Response message\n" + url + "," + responseCode + "," + responseMessage + "\n", writer.toString());
    }
}
