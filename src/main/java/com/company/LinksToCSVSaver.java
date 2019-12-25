package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class LinksToCSVSaver
{
    public void save(List<Link> links, FileWriter writer) throws IOException
    {
        for (Link link : links)
        {
            writer.write(link.getUrl().toString() + ",");
            writer.write(link.getResponseCode() + ",");
            writer.write(link.getResponseMessage() + "\n");
        }
        writer.flush();
    }
}
