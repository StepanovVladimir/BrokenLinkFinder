package com.company.parsing;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public interface Parser
{
    void parse(File file) throws IOException;
    void parse(URL url) throws IOException;
}
