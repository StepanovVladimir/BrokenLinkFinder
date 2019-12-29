package com.company.parsing;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public interface Parser<T>
{
    T parse(File file) throws IOException;
    T parse(URL ur) throws IOException;
}
