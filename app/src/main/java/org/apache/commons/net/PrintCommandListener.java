package org.apache.commons.net;

import java.io.PrintStream;
import java.io.PrintWriter;

public class PrintCommandListener
  implements ProtocolCommandListener
{
  private final boolean __directionMarker;
  private final char __eolMarker;
  private final boolean __nologin;
  private final PrintWriter __writer;

  public PrintCommandListener(PrintStream paramPrintStream)
  {
    this(new PrintWriter(paramPrintStream));
  }

  public PrintCommandListener(PrintStream paramPrintStream, boolean paramBoolean)
  {
    this(new PrintWriter(paramPrintStream), paramBoolean);
  }

  public PrintCommandListener(PrintStream paramPrintStream, boolean paramBoolean, char paramChar)
  {
    this(new PrintWriter(paramPrintStream), paramBoolean, paramChar);
  }

  public PrintCommandListener(PrintStream paramPrintStream, boolean paramBoolean1, char paramChar, boolean paramBoolean2)
  {
    this(new PrintWriter(paramPrintStream), paramBoolean1, paramChar, paramBoolean2);
  }

  public PrintCommandListener(PrintWriter paramPrintWriter)
  {
    this(paramPrintWriter, false);
  }

  public PrintCommandListener(PrintWriter paramPrintWriter, boolean paramBoolean)
  {
    this(paramPrintWriter, paramBoolean, '\000');
  }

  public PrintCommandListener(PrintWriter paramPrintWriter, boolean paramBoolean, char paramChar)
  {
    this(paramPrintWriter, paramBoolean, paramChar, false);
  }

  public PrintCommandListener(PrintWriter paramPrintWriter, boolean paramBoolean1, char paramChar, boolean paramBoolean2)
  {
    this.__writer = paramPrintWriter;
    this.__nologin = paramBoolean1;
    this.__eolMarker = paramChar;
    this.__directionMarker = paramBoolean2;
  }

  private String getPrintableString(String paramString)
  {
    if (this.__eolMarker == 0)
      return paramString;
    int i = paramString.indexOf("\r\n");
    if (i > 0)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramString.substring(0, i));
      localStringBuilder.append(this.__eolMarker);
      localStringBuilder.append(paramString.substring(i));
      return localStringBuilder.toString();
    }
    return paramString;
  }

  public void protocolCommandSent(ProtocolCommandEvent paramProtocolCommandEvent)
  {
    if (this.__directionMarker)
      this.__writer.print("> ");
    if (this.__nologin)
    {
      String str = paramProtocolCommandEvent.getCommand();
      if ((!"PASS".equalsIgnoreCase(str)) && (!"USER".equalsIgnoreCase(str)))
      {
        if ("LOGIN".equalsIgnoreCase(str))
        {
          paramProtocolCommandEvent = paramProtocolCommandEvent.getMessage();
          paramProtocolCommandEvent = paramProtocolCommandEvent.substring(0, paramProtocolCommandEvent.indexOf("LOGIN") + "LOGIN".length());
          this.__writer.print(paramProtocolCommandEvent);
          this.__writer.println(" *******");
        }
        else
        {
          this.__writer.print(getPrintableString(paramProtocolCommandEvent.getMessage()));
        }
      }
      else
      {
        this.__writer.print(str);
        this.__writer.println(" *******");
      }
    }
    else
    {
      this.__writer.print(getPrintableString(paramProtocolCommandEvent.getMessage()));
    }
    this.__writer.flush();
  }

  public void protocolReplyReceived(ProtocolCommandEvent paramProtocolCommandEvent)
  {
    if (this.__directionMarker)
      this.__writer.print("< ");
    this.__writer.print(paramProtocolCommandEvent.getMessage());
    this.__writer.flush();
  }
}