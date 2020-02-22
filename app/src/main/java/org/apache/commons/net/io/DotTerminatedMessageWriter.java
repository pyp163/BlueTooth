package org.apache.commons.net.io;

import java.io.IOException;
import java.io.Writer;

public final class DotTerminatedMessageWriter extends Writer
{
  private static final int __LAST_WAS_CR_STATE = 1;
  private static final int __LAST_WAS_NL_STATE = 2;
  private static final int __NOTHING_SPECIAL_STATE = 0;
  private Writer __output;
  private int __state;

  public DotTerminatedMessageWriter(Writer paramWriter)
  {
    super(paramWriter);
    this.__output = paramWriter;
    this.__state = 0;
  }

  public void close()
    throws IOException
  {
    synchronized (this.lock)
    {
      if (this.__output == null)
        return;
      if (this.__state == 1)
        this.__output.write(10);
      else if (this.__state != 2)
        this.__output.write("\r\n");
      this.__output.write(".\r\n");
      this.__output.flush();
      this.__output = null;
      return;
    }
  }

  public void flush()
    throws IOException
  {
    synchronized (this.lock)
    {
      this.__output.flush();
      return;
    }
  }

  public void write(int paramInt)
    throws IOException
  {
    Object localObject1 = this.lock;
    if ((paramInt == 10) || ((paramInt == 13) || (paramInt == 46)));
    try
    {
      if (this.__state == 2)
        this.__output.write(46);
      this.__state = 0;
      this.__output.write(paramInt);
      return;
      this.__state = 1;
      this.__output.write(13);
      return;
      if (this.__state != 1)
        this.__output.write(13);
      this.__output.write(10);
      this.__state = 2;
      return;
      label112: Object localObject2;
      throw localObject2;
    }
    finally
    {
      break label112;
    }
  }

  public void write(String paramString)
    throws IOException
  {
    write(paramString.toCharArray());
  }

  public void write(String paramString, int paramInt1, int paramInt2)
    throws IOException
  {
    write(paramString.toCharArray(), paramInt1, paramInt2);
  }

  public void write(char[] paramArrayOfChar)
    throws IOException
  {
    write(paramArrayOfChar, 0, paramArrayOfChar.length);
  }

  public void write(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    Object localObject = this.lock;
    while (true)
    {
      if (paramInt2 > 0);
      try
      {
        write(paramArrayOfChar[paramInt1]);
        paramInt1 += 1;
        paramInt2 -= 1;
        continue;
        return;
        label35: throw paramArrayOfChar;
      }
      finally
      {
        break label35;
      }
    }
  }
}