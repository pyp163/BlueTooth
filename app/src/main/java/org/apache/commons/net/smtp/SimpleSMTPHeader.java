package org.apache.commons.net.smtp;

public class SimpleSMTPHeader
{
  private StringBuffer __cc;
  private String __from;
  private StringBuffer __headerFields;
  private String __subject;
  private String __to;

  public SimpleSMTPHeader(String paramString1, String paramString2, String paramString3)
  {
    this.__to = paramString2;
    this.__from = paramString1;
    this.__subject = paramString3;
    this.__headerFields = new StringBuffer();
    this.__cc = null;
  }

  public void addCC(String paramString)
  {
    if (this.__cc == null)
      this.__cc = new StringBuffer();
    else
      this.__cc.append(", ");
    this.__cc.append(paramString);
  }

  public void addHeaderField(String paramString1, String paramString2)
  {
    this.__headerFields.append(paramString1);
    this.__headerFields.append(": ");
    this.__headerFields.append(paramString2);
    this.__headerFields.append('\n');
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (this.__headerFields.length() > 0)
      localStringBuilder.append(this.__headerFields.toString());
    localStringBuilder.append("From: ");
    localStringBuilder.append(this.__from);
    localStringBuilder.append("\nTo: ");
    localStringBuilder.append(this.__to);
    if (this.__cc != null)
    {
      localStringBuilder.append("\nCc: ");
      localStringBuilder.append(this.__cc.toString());
    }
    if (this.__subject != null)
    {
      localStringBuilder.append("\nSubject: ");
      localStringBuilder.append(this.__subject);
    }
    localStringBuilder.append('\n');
    localStringBuilder.append('\n');
    return localStringBuilder.toString();
  }
}