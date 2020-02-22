package org.apache.commons.net.nntp;

import java.util.Calendar;

public final class NewGroupsOrNewsQuery
{
  private String __date;
  private StringBuffer __distributions = null;
  private boolean __isGMT;
  private StringBuffer __newsgroups = null;
  private String __time;

  public NewGroupsOrNewsQuery(Calendar paramCalendar, boolean paramBoolean)
  {
    this.__isGMT = paramBoolean;
    StringBuilder localStringBuilder = new StringBuilder();
    String str = Integer.toString(paramCalendar.get(1));
    int i = str.length();
    if (i >= 2)
      localStringBuilder.append(str.substring(i - 2));
    else
      localStringBuilder.append("00");
    str = Integer.toString(paramCalendar.get(2) + 1);
    i = str.length();
    if (i == 1)
    {
      localStringBuilder.append('0');
      localStringBuilder.append(str);
    }
    else if (i == 2)
    {
      localStringBuilder.append(str);
    }
    else
    {
      localStringBuilder.append("01");
    }
    str = Integer.toString(paramCalendar.get(5));
    i = str.length();
    if (i == 1)
    {
      localStringBuilder.append('0');
      localStringBuilder.append(str);
    }
    else if (i == 2)
    {
      localStringBuilder.append(str);
    }
    else
    {
      localStringBuilder.append("01");
    }
    this.__date = localStringBuilder.toString();
    localStringBuilder.setLength(0);
    str = Integer.toString(paramCalendar.get(11));
    i = str.length();
    if (i == 1)
    {
      localStringBuilder.append('0');
      localStringBuilder.append(str);
    }
    else if (i == 2)
    {
      localStringBuilder.append(str);
    }
    else
    {
      localStringBuilder.append("00");
    }
    str = Integer.toString(paramCalendar.get(12));
    i = str.length();
    if (i == 1)
    {
      localStringBuilder.append('0');
      localStringBuilder.append(str);
    }
    else if (i == 2)
    {
      localStringBuilder.append(str);
    }
    else
    {
      localStringBuilder.append("00");
    }
    paramCalendar = Integer.toString(paramCalendar.get(13));
    i = paramCalendar.length();
    if (i == 1)
    {
      localStringBuilder.append('0');
      localStringBuilder.append(paramCalendar);
    }
    else if (i == 2)
    {
      localStringBuilder.append(paramCalendar);
    }
    else
    {
      localStringBuilder.append("00");
    }
    this.__time = localStringBuilder.toString();
  }

  public void addDistribution(String paramString)
  {
    if (this.__distributions != null)
      this.__distributions.append(',');
    else
      this.__distributions = new StringBuffer();
    this.__distributions.append(paramString);
  }

  public void addNewsgroup(String paramString)
  {
    if (this.__newsgroups != null)
      this.__newsgroups.append(',');
    else
      this.__newsgroups = new StringBuffer();
    this.__newsgroups.append(paramString);
  }

  public String getDate()
  {
    return this.__date;
  }

  public String getDistributions()
  {
    if (this.__distributions == null)
      return null;
    return this.__distributions.toString();
  }

  public String getNewsgroups()
  {
    if (this.__newsgroups == null)
      return null;
    return this.__newsgroups.toString();
  }

  public String getTime()
  {
    return this.__time;
  }

  public boolean isGMT()
  {
    return this.__isGMT;
  }

  public void omitNewsgroup(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("!");
    localStringBuilder.append(paramString);
    addNewsgroup(localStringBuilder.toString());
  }
}