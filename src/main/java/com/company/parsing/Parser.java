package com.company.parsing;

import com.company.saving.Saver;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public interface Parser<T>
{
    void parse(File file, Saver<T> saver) throws IOException;
    void parse(URL url, Saver<T> saver) throws IOException;
}
