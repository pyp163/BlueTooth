package org.apache.commons.net.nntp;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.apache.commons.net.io.DotTerminatedMessageReader;
import org.apache.commons.net.io.Util;

class ReplyIterator
  implements Iterator<String>, Iterable<String>
{
  private String line;
  private final BufferedReader reader;
  private Exception savedException;

  ReplyIterator(BufferedReader paramBufferedReader)
    throws IOException
  {
    this(paramBufferedReader, true);
  }

  ReplyIterator(BufferedReader paramBufferedReader, boolean paramBoolean)
    throws IOException
  {
    Object localObject = paramBufferedReader;
    if (paramBoolean)
      localObject = new DotTerminatedMessageReader(paramBufferedReader);
    this.reader = ((BufferedReader)localObject);
    this.line = this.reader.readLine();
    if (this.line == null)
      Util.closeQuietly(this.reader);
  }

  public boolean hasNext()
  {
    if (this.savedException != null)
      throw new NoSuchElementException(this.savedException.toString());
    return this.line != null;
  }

  public Iterator<String> iterator()
  {
    return this;
  }

  public String next()
    throws NoSuchElementException
  {
    if (this.savedException != null)
      throw new NoSuchElementException(this.savedException.toString());
    String str = this.line;
    if (str == null)
      throw new NoSuchElementException();
    try
    {
      this.line = this.reader.readLine();
      if (this.line == null)
      {
        Util.closeQuietly(this.reader);
        return str;
      }
    }
    catch (IOException localIOException)
    {
      this.savedException = localIOException;
      Util.closeQuietly(this.reader);
    }
    return str;
  }

  public void remove()
  {
    throw new UnsupportedOperationException();
  }
}