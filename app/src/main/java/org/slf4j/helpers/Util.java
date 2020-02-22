package org.slf4j.helpers;

import java.io.PrintStream;

public class Util
{
  public static final void report(String paramString)
  {
    PrintStream localPrintStream = System.err;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SLF4J: ");
    localStringBuilder.append(paramString);
    localPrintStream.println(localStringBuilder.toString());
  }

  public static final void report(String paramString, Throwable paramThrowable)
  {
    System.err.println(paramString);
    System.err.println("Reported exception:");
    paramThrowable.printStackTrace();
  }
}