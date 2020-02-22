package org.apache.commons.net.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public final class CRLFLineReader extends BufferedReader
{
  private static final char CR = '\r';
  private static final char LF = '\n';

  public CRLFLineReader(Reader paramReader)
  {
    super(paramReader);
  }

  public String readLine()
    throws IOException
  {
    Object localObject2 = new StringBuilder();
    Object localObject1 = this.lock;
    int i = 0;
    while (true)
    {
      int j;
      try
      {
        j = read();
        if (j != -1)
        {
          if ((i == 0) || (j != 10))
            break label95;
          localObject2 = ((StringBuilder)localObject2).substring(0, ((StringBuilder)localObject2).length() - 1);
          return localObject2;
          ((StringBuilder)localObject2).append((char)j);
          continue;
        }
        localObject1 = ((StringBuilder)localObject2).toString();
        if (((String)localObject1).length() == 0)
          return null;
        return localObject1;
      }
      finally
      {
      }
      label95: if (j == 13)
        i = 1;
      else
        i = 0;
    }
  }
}