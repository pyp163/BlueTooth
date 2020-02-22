package org.apache.commons.net.ftp.parser;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.apache.commons.net.ftp.FTPFileEntryParserImpl;

public abstract class RegexFTPFileEntryParserImpl extends FTPFileEntryParserImpl
{
  protected Matcher _matcher_ = null;
  private Pattern pattern = null;
  private MatchResult result = null;

  public RegexFTPFileEntryParserImpl(String paramString)
  {
    setRegex(paramString);
  }

  public int getGroupCnt()
  {
    if (this.result == null)
      return 0;
    return this.result.groupCount();
  }

  public String getGroupsAsString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 1;
    while (i <= this.result.groupCount())
    {
      localStringBuilder.append(i);
      localStringBuilder.append(") ");
      localStringBuilder.append(this.result.group(i));
      localStringBuilder.append(System.getProperty("line.separator"));
      i += 1;
    }
    return localStringBuilder.toString();
  }

  public String group(int paramInt)
  {
    if (this.result == null)
      return null;
    return this.result.group(paramInt);
  }

  public boolean matches(String paramString)
  {
    this.result = null;
    this._matcher_ = this.pattern.matcher(paramString);
    if (this._matcher_.matches())
      this.result = this._matcher_.toMatchResult();
    return this.result != null;
  }

  public boolean setRegex(String paramString)
  {
    try
    {
      this.pattern = Pattern.compile(paramString);
      return this.pattern != null;
      label19: StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Unparseable regex supplied: ");
      localStringBuilder.append(paramString);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    catch (PatternSyntaxException localPatternSyntaxException)
    {
      break label19;
    }
  }
}