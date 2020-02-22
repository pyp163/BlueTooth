package org.apache.commons.net.nntp;

import java.io.PrintStream;
import java.util.ArrayList;

public class Article
  implements Threadable
{
  private String articleId;
  private long articleNumber = -1L;
  private String date;
  private String from;
  private boolean isReply = false;
  public Article kid;
  public Article next;
  private ArrayList<String> references;
  private String simplifiedSubject;
  private String subject;

  private void flushSubjectCache()
  {
    this.simplifiedSubject = null;
  }

  public static void printThread(Article paramArticle, int paramInt)
  {
    int i = 0;
    while (i < paramInt)
    {
      System.out.print("==>");
      i += 1;
    }
    PrintStream localPrintStream = System.out;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramArticle.getSubject());
    localStringBuilder.append("\t");
    localStringBuilder.append(paramArticle.getFrom());
    localPrintStream.println(localStringBuilder.toString());
    if (paramArticle.kid != null)
      printThread(paramArticle.kid, paramInt + 1);
    if (paramArticle.next != null)
      printThread(paramArticle.next, paramInt);
  }

  private void simplifySubject()
  {
    String str = getSubject();
    int m = str.length();
    int j = 0;
    int i = 0;
    while (j == 0)
    {
      while ((i < m) && (str.charAt(i) == ' '))
        i += 1;
      j = m - 2;
      if ((i < j) && ((str.charAt(i) == 'r') || (str.charAt(i) == 'R')))
      {
        k = i + 1;
        if ((str.charAt(k) == 'e') || (str.charAt(k) == 'E'))
        {
          k = i + 2;
          if (str.charAt(k) == ':')
            i += 3;
          while (true)
          {
            j = 0;
            break label245;
            if ((i >= j) || ((str.charAt(k) != '[') && (str.charAt(k) != '(')))
              break;
            j = i + 3;
            while ((j < m) && (str.charAt(j) >= '0') && (str.charAt(j) <= '9'))
              j += 1;
            if ((j >= m - 1) || ((str.charAt(j) != ']') && (str.charAt(j) != ')')) || (str.charAt(j + 1) != ':'))
              break;
            i = j + 2;
          }
        }
      }
      j = 1;
      label245: if ("(no subject)".equals(this.simplifiedSubject))
        this.simplifiedSubject = "";
      int k = m;
      while ((k > i) && (str.charAt(k - 1) < ' '))
        k -= 1;
      if ((i == 0) && (k == m))
        this.simplifiedSubject = str;
      else
        this.simplifiedSubject = str.substring(i, k);
    }
  }

  @Deprecated
  public void addHeaderField(String paramString1, String paramString2)
  {
  }

  public void addReference(String paramString)
  {
    if (paramString != null)
    {
      if (paramString.length() == 0)
        return;
      if (this.references == null)
        this.references = new ArrayList();
      this.isReply = true;
      paramString = paramString.split(" ");
      int j = paramString.length;
      int i = 0;
      while (i < j)
      {
        Object localObject = paramString[i];
        this.references.add(localObject);
        i += 1;
      }
      return;
    }
  }

  public String getArticleId()
  {
    return this.articleId;
  }

  @Deprecated
  public int getArticleNumber()
  {
    return (int)this.articleNumber;
  }

  public long getArticleNumberLong()
  {
    return this.articleNumber;
  }

  public String getDate()
  {
    return this.date;
  }

  public String getFrom()
  {
    return this.from;
  }

  public String[] getReferences()
  {
    if (this.references == null)
      return new String[0];
    return (String[])this.references.toArray(new String[this.references.size()]);
  }

  public String getSubject()
  {
    return this.subject;
  }

  public boolean isDummy()
  {
    return this.articleNumber == -1L;
  }

  public Threadable makeDummy()
  {
    return new Article();
  }

  public String messageThreadId()
  {
    return this.articleId;
  }

  public String[] messageThreadReferences()
  {
    return getReferences();
  }

  public void setArticleId(String paramString)
  {
    this.articleId = paramString;
  }

  @Deprecated
  public void setArticleNumber(int paramInt)
  {
    this.articleNumber = paramInt;
  }

  public void setArticleNumber(long paramLong)
  {
    this.articleNumber = paramLong;
  }

  public void setChild(Threadable paramThreadable)
  {
    this.kid = ((Article)paramThreadable);
    flushSubjectCache();
  }

  public void setDate(String paramString)
  {
    this.date = paramString;
  }

  public void setFrom(String paramString)
  {
    this.from = paramString;
  }

  public void setNext(Threadable paramThreadable)
  {
    this.next = ((Article)paramThreadable);
    flushSubjectCache();
  }

  public void setSubject(String paramString)
  {
    this.subject = paramString;
  }

  public String simplifiedSubject()
  {
    if (this.simplifiedSubject == null)
      simplifySubject();
    return this.simplifiedSubject;
  }

  public boolean subjectIsReply()
  {
    return this.isReply;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.articleNumber);
    localStringBuilder.append(" ");
    localStringBuilder.append(this.articleId);
    localStringBuilder.append(" ");
    localStringBuilder.append(this.subject);
    return localStringBuilder.toString();
  }
}