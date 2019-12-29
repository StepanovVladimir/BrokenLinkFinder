package com.company.saving;

import com.company.Link;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class LinksToCSVSaver implements Saver<List<Link>>
{
    public void save(List<Link> links, Writer writer) throws IOException
    {
        for (Link link : links)
        {
            writer.write(link.getUrl() + ",");
            writer.write(link.getResponseCode() + ",");
            writer.write(link.getResponseMessage() + "\n");
        }
        writer.flush();
    }
}
