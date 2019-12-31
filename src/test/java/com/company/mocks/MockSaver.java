package com.company.mocks;

import com.company.entities.Link;
import com.company.saving.Saver;

import java.util.ArrayList;
import java.util.List;

public class MockSaver implements Saver<Link>
{
    @Override
    public synchronized void save(Link link)
    {
        links.add(link);
    }

    public List<Link> links = new ArrayList<>();
}
