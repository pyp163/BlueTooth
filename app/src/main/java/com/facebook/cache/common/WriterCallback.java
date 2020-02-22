package com.facebook.cache.common;

import java.io.IOException;
import java.io.OutputStream;

public abstract interface WriterCallback
{
  public abstract void write(OutputStream paramOutputStream)
    throws IOException;
}