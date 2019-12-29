package com.company.saving;

import java.io.IOException;
import java.io.Writer;

public interface Saver<T>
{
    void save(T source, Writer writer) throws IOException;
}
