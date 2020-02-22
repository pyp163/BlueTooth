package org.apache.commons.net.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public final class DotTerminatedMessageReader extends BufferedReader
{
  private static final char CR = '\r';
  private static final int DOT = 46;
  private static final char LF = '\n';
  private boolean atBeginning = true;
  private boolean eof = false;
  private boolean seenCR;

  public DotTerminatedMessageReader(Reader paramReader)
  {
    super(paramReader);
  }

  public void close()
    throws IOException
  {
    synchronized (this.lock)
    {
      while ((!this.eof) && (read() != -1));
      this.eof = true;
      this.atBeginning = false;
      return;
    }
  }

  public int read()
    throws IOException
  {
    synchronized (this.lock)
    {
      if (this.eof)
        return -1;
      int i = super.read();
      if (i == -1)
      {
        this.eof = true;
        return -1;
      }
      if (this.atBeginning)
      {
        this.atBeginning = false;
        if (i == 46)
        {
          mark(2);
          i = super.read();
          if (i == -1)
          {
            this.eof = true;
            return 46;
          }
          if (i == 46)
            return i;
          if (i == 13)
          {
            i = super.read();
            if (i == -1)
            {
              reset();
              return 46;
            }
            if (i == 10)
            {
              this.atBeginning = true;
              this.eof = true;
              return -1;
            }
          }
          reset();
          return 46;
        }
      }
      if (this.seenCR)
      {
        this.seenCR = false;
        if (i == 10)
          this.atBeginning = true;
      }
      if (i == 13)
        this.seenCR = true;
      return i;
    }
  }

  public int read(char[] paramArrayOfChar)
    throws IOException
  {
    return read(paramArrayOfChar, 0, paramArrayOfChar.length);
  }

  // ERROR //
  public int read(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 33	org/apache/commons/net/io/DotTerminatedMessageReader:lock	Ljava/lang/Object;
    //   4: astore 7
    //   6: aload 7
    //   8: monitorenter
    //   9: iload_3
    //   10: iconst_1
    //   11: if_icmpge +8 -> 19
    //   14: aload 7
    //   16: monitorexit
    //   17: iconst_0
    //   18: ireturn
    //   19: aload_0
    //   20: invokevirtual 37	org/apache/commons/net/io/DotTerminatedMessageReader:read	()I
    //   23: istore 6
    //   25: iload 6
    //   27: iconst_m1
    //   28: if_icmpne +61 -> 89
    //   31: aload 7
    //   33: monitorexit
    //   34: iconst_m1
    //   35: ireturn
    //   36: iload 5
    //   38: iconst_1
    //   39: iadd
    //   40: istore 6
    //   42: aload_1
    //   43: iload 5
    //   45: iload_3
    //   46: i2c
    //   47: castore
    //   48: iload 4
    //   50: iconst_1
    //   51: isub
    //   52: istore 4
    //   54: iload 4
    //   56: ifle +16 -> 72
    //   59: aload_0
    //   60: invokevirtual 37	org/apache/commons/net/io/DotTerminatedMessageReader:read	()I
    //   63: istore_3
    //   64: iload_3
    //   65: iconst_m1
    //   66: if_icmpne +35 -> 101
    //   69: goto +3 -> 72
    //   72: aload 7
    //   74: monitorexit
    //   75: iload 6
    //   77: iload_2
    //   78: isub
    //   79: ireturn
    //   80: aload 7
    //   82: monitorexit
    //   83: aload_1
    //   84: athrow
    //   85: astore_1
    //   86: goto -6 -> 80
    //   89: iload_3
    //   90: istore 4
    //   92: iload_2
    //   93: istore 5
    //   95: iload 6
    //   97: istore_3
    //   98: goto -62 -> 36
    //   101: iload 6
    //   103: istore 5
    //   105: goto -69 -> 36
    //
    // Exception table:
    //   from	to	target	type
    //   14	17	85	finally
    //   19	25	85	finally
    //   31	34	85	finally
    //   59	64	85	finally
    //   72	75	85	finally
    //   80	83	85	finally
  }

  public String readLine()
    throws IOException
  {
    Object localObject2 = new StringBuilder();
    synchronized (this.lock)
    {
      while (true)
      {
        int i = read();
        if (i == -1)
          break;
        if ((i == 10) && (this.atBeginning))
        {
          localObject2 = ((StringBuilder)localObject2).substring(0, ((StringBuilder)localObject2).length() - 1);
          return localObject2;
        }
        ((StringBuilder)localObject2).append((char)i);
      }
      ??? = ((StringBuilder)localObject2).toString();
      if (((String)???).length() == 0)
        return null;
      return ???;
    }
  }
}