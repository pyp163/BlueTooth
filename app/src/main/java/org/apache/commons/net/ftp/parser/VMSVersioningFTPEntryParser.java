package org.apache.commons.net.ftp.parser;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.apache.commons.net.ftp.FTPClientConfig;

public class VMSVersioningFTPEntryParser extends VMSFTPEntryParser
{
  private static final String PRE_PARSE_REGEX = "(.*);([0-9]+)\\s*.*";
  private final Pattern _preparse_pattern_;

  public VMSVersioningFTPEntryParser()
  {
    this(null);
  }

  public VMSVersioningFTPEntryParser(FTPClientConfig paramFTPClientConfig)
  {
    configure(paramFTPClientConfig);
    try
    {
      this._preparse_pattern_ = Pattern.compile("(.*);([0-9]+)\\s*.*");
      return;
      label19: throw new IllegalArgumentException("Unparseable regex supplied:  (.*);([0-9]+)\\s*.*");
    }
    catch (PatternSyntaxException paramFTPClientConfig)
    {
      break label19;
    }
  }

  protected boolean isVersioning()
  {
    return true;
  }

  public List<String> preParse(List<String> paramList)
  {
    HashMap localHashMap = new HashMap();
    ListIterator localListIterator = paramList.listIterator();
    Object localObject1;
    Object localObject2;
    while (localListIterator.hasNext())
    {
      localObject1 = ((String)localListIterator.next()).trim();
      localObject1 = this._preparse_pattern_.matcher((CharSequence)localObject1);
      if (((Matcher)localObject1).matches())
      {
        localObject2 = ((Matcher)localObject1).toMatchResult();
        localObject1 = ((MatchResult)localObject2).group(1);
        localObject2 = Integer.valueOf(((MatchResult)localObject2).group(2));
        Integer localInteger = (Integer)localHashMap.get(localObject1);
        if ((localInteger != null) && (((Integer)localObject2).intValue() < localInteger.intValue()))
          localListIterator.remove();
        else
          localHashMap.put(localObject1, localObject2);
      }
    }
    while (localListIterator.hasPrevious())
    {
      localObject1 = ((String)localListIterator.previous()).trim();
      localObject1 = this._preparse_pattern_.matcher((CharSequence)localObject1);
      if (((Matcher)localObject1).matches())
      {
        localObject2 = ((Matcher)localObject1).toMatchResult();
        localObject1 = ((MatchResult)localObject2).group(1);
        localObject2 = Integer.valueOf(((MatchResult)localObject2).group(2));
        localObject1 = (Integer)localHashMap.get(localObject1);
        if ((localObject1 != null) && (((Integer)localObject2).intValue() < ((Integer)localObject1).intValue()))
          localListIterator.remove();
      }
    }
    return paramList;
  }
}