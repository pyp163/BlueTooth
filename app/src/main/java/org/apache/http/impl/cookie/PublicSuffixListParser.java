package org.apache.http.impl.cookie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.http.annotation.Immutable;

@Immutable
public class PublicSuffixListParser
{
  private static final int MAX_LINE_LEN = 256;
  private final PublicSuffixFilter filter;

  PublicSuffixListParser(PublicSuffixFilter paramPublicSuffixFilter)
  {
    this.filter = paramPublicSuffixFilter;
  }

  private boolean readLine(Reader paramReader, StringBuilder paramStringBuilder)
    throws IOException
  {
    boolean bool = false;
    paramStringBuilder.setLength(0);
    int i = 0;
    int j;
    do
    {
      j = paramReader.read();
      if (j == -1)
        break;
      char c = (char)j;
      if (c == '\n')
        break;
      if (Character.isWhitespace(c))
        i = 1;
      if (i == 0)
        paramStringBuilder.append(c);
    }
    while (paramStringBuilder.length() <= 256);
    throw new IOException("Line too long");
    if (j != -1)
      bool = true;
    return bool;
  }

  public void parse(Reader paramReader)
    throws IOException
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    BufferedReader localBufferedReader = new BufferedReader(paramReader);
    StringBuilder localStringBuilder = new StringBuilder(256);
    boolean bool1 = true;
    while (bool1)
    {
      bool1 = readLine(localBufferedReader, localStringBuilder);
      Object localObject = localStringBuilder.toString();
      if ((((String)localObject).length() != 0) && (!((String)localObject).startsWith("//")))
      {
        paramReader = (Reader)localObject;
        if (((String)localObject).startsWith("."))
          paramReader = ((String)localObject).substring(1);
        boolean bool2 = paramReader.startsWith("!");
        localObject = paramReader;
        if (bool2)
          localObject = paramReader.substring(1);
        if (bool2)
          localArrayList2.add(localObject);
        else
          localArrayList1.add(localObject);
      }
    }
    this.filter.setPublicSuffixes(localArrayList1);
    this.filter.setExceptions(localArrayList2);
  }
}