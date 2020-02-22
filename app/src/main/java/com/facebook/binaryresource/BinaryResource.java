package com.facebook.binaryresource;

import java.io.IOException;
import java.io.InputStream;

public abstract interface BinaryResource
{
  public abstract InputStream openStream()
    throws IOException;

  public abstract byte[] read()
    throws IOException;

  public abstract long size();
}