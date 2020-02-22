package org.apache.http.io;

import java.io.IOException;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;

public abstract interface HttpMessageParser
{
  public abstract HttpMessage parse()
    throws IOException, HttpException;
}