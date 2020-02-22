package org.apache.http.io;

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;

public abstract interface HttpMessageWriter
{
  public abstract void write(HttpMessage paramHttpMessage)
    throws IOException, HttpException;
}