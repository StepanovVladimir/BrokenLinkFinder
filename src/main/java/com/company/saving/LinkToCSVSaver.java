package com.company.saving;

import com.company.entities.Link;

import java.io.IOException;
import java.io.Writer;

public class LinkToCSVSaver implements Saver<Link>
{
    public LinkToCSVSaver(Writer writer)
    {
        this.writer = writer;
        try
        {
            this.writer.write("Url,Response code,Response message\n");
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    public synchronized void save(Link link)
    {
        try
        {
            writer.write(link.getUrl() + ",");
            writer.write(link.getResponseCode() + ",");
            writer.write(link.getResponseMessage() + "\n");

            writer.flush();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private Writer writer;
}
