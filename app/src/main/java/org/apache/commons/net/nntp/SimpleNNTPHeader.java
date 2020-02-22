package org.apache.commons.net.nntp;

public class SimpleNNTPHeader
{
  private String __from;
  private StringBuilder __headerFields;
  private int __newsgroupCount;
  private StringBuilder __newsgroups;
  private String __subject;

  public SimpleNNTPHeader(String paramString1, String paramString2)
  {
    this.__from = paramString1;
    this.__subject = paramString2;
    this.__newsgroups = new StringBuilder();
    this.__headerFields = new StringBuilder();
    this.__newsgroupCount = 0;
  }

  public void addHeaderField(String paramString1, String paramString2)
  {
    this.__headerFields.append(paramString1);
    this.__headerFields.append(": ");
    this.__headerFields.append(paramString2);
    this.__headerFields.append('\n');
  }

  public void addNewsgroup(String paramString)
  {
    int i = this.__newsgroupCount;
    this.__newsgroupCount = (i + 1);
    if (i > 0)
      this.__newsgroups.append(',');
    this.__newsgroups.append(paramString);
  }

  public String getFromAddress()
  {
    return this.__from;
  }

  public String getNewsgroups()
  {
    return this.__newsgroups.toString();
  }

  public String getSubject()
  {
    return this.__subject;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("From: ");
    localStringBuilder.append(this.__from);
    localStringBuilder.append("\nNewsgroups: ");
    localStringBuilder.append(this.__newsgroups.toString());
    localStringBuilder.append("\nSubject: ");
    localStringBuilder.append(this.__subject);
    localStringBuilder.append('\n');
    if (this.__headerFields.length() > 0)
      localStringBuilder.append(this.__headerFields.toString());
    localStringBuilder.append('\n');
    return localStringBuilder.toString();
  }
}