package org.apache.commons.net.nntp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Vector;
import org.apache.commons.net.MalformedServerReplyException;
import org.apache.commons.net.io.DotTerminatedMessageReader;
import org.apache.commons.net.io.DotTerminatedMessageWriter;
import org.apache.commons.net.io.Util;

public class NNTPClient extends NNTP
{
  private void __ai2ap(ArticleInfo paramArticleInfo, ArticlePointer paramArticlePointer)
  {
    if (paramArticlePointer != null)
    {
      paramArticlePointer.articleId = paramArticleInfo.articleId;
      paramArticlePointer.articleNumber = ((int)paramArticleInfo.articleNumber);
    }
  }

  private ArticleInfo __ap2ai(ArticlePointer paramArticlePointer)
  {
    if (paramArticlePointer == null)
      return null;
    return new ArticleInfo();
  }

  static Article __parseArticleEntry(String paramString)
  {
    Article localArticle = new Article();
    localArticle.setSubject(paramString);
    paramString = paramString.split("\t");
    if (paramString.length > 6);
    try
    {
      localArticle.setArticleNumber(Long.parseLong(paramString[0]));
      localArticle.setSubject(paramString[1]);
      localArticle.setFrom(paramString[2]);
      localArticle.setDate(paramString[3]);
      localArticle.setArticleId(paramString[4]);
      localArticle.addReference(paramString[5]);
      return localArticle;
    }
    catch (NumberFormatException paramString)
    {
    }
    return localArticle;
  }

  private void __parseArticlePointer(String paramString, ArticleInfo paramArticleInfo)
    throws MalformedServerReplyException
  {
    String[] arrayOfString = paramString.split(" ");
    if (arrayOfString.length >= 3);
    try
    {
      paramArticleInfo.articleNumber = Long.parseLong(arrayOfString[1]);
      paramArticleInfo.articleId = arrayOfString[2];
      return;
      label31: paramArticleInfo = new StringBuilder();
      paramArticleInfo.append("Could not parse article pointer.\nServer reply: ");
      paramArticleInfo.append(paramString);
      throw new MalformedServerReplyException(paramArticleInfo.toString());
    }
    catch (NumberFormatException paramArticleInfo)
    {
      break label31;
    }
  }

  private static void __parseGroupReply(String paramString, NewsgroupInfo paramNewsgroupInfo)
    throws MalformedServerReplyException
  {
    String[] arrayOfString = paramString.split(" ");
    if (arrayOfString.length >= 5);
    try
    {
      paramNewsgroupInfo._setArticleCount(Long.parseLong(arrayOfString[1]));
      paramNewsgroupInfo._setFirstArticle(Long.parseLong(arrayOfString[2]));
      paramNewsgroupInfo._setLastArticle(Long.parseLong(arrayOfString[3]));
      paramNewsgroupInfo._setNewsgroup(arrayOfString[4]);
      paramNewsgroupInfo._setPostingPermission(0);
      return;
      label56: paramNewsgroupInfo = new StringBuilder();
      paramNewsgroupInfo.append("Could not parse newsgroup info.\nServer reply: ");
      paramNewsgroupInfo.append(paramString);
      throw new MalformedServerReplyException(paramNewsgroupInfo.toString());
    }
    catch (NumberFormatException paramNewsgroupInfo)
    {
      break label56;
    }
  }

  static NewsgroupInfo __parseNewsgroupListEntry(String paramString)
  {
    paramString = paramString.split(" ");
    if (paramString.length < 4)
      return null;
    NewsgroupInfo localNewsgroupInfo = new NewsgroupInfo();
    localNewsgroupInfo._setNewsgroup(paramString[0]);
    try
    {
      long l1 = Long.parseLong(paramString[1]);
      long l2 = Long.parseLong(paramString[2]);
      localNewsgroupInfo._setFirstArticle(l2);
      localNewsgroupInfo._setLastArticle(l1);
      if ((l2 == 0L) && (l1 == 0L))
        localNewsgroupInfo._setArticleCount(0L);
      else
        localNewsgroupInfo._setArticleCount(l1 - l2 + 1L);
      switch (paramString[3].charAt(0))
      {
      default:
        localNewsgroupInfo._setPostingPermission(0);
        return localNewsgroupInfo;
      case 'Y':
      case 'y':
        localNewsgroupInfo._setPostingPermission(2);
        return localNewsgroupInfo;
      case 'N':
      case 'n':
        localNewsgroupInfo._setPostingPermission(3);
        return localNewsgroupInfo;
      case 'M':
      case 'm':
      }
      localNewsgroupInfo._setPostingPermission(1);
      return localNewsgroupInfo;
    }
    catch (NumberFormatException paramString)
    {
    }
    return null;
  }

  private NewsgroupInfo[] __readNewsgroupListing()
    throws IOException
  {
    Object localObject = new DotTerminatedMessageReader(this._reader_);
    Vector localVector = new Vector(2048);
    String str;
    while (true)
    {
      str = ((BufferedReader)localObject).readLine();
      if (str == null)
        break label65;
      NewsgroupInfo localNewsgroupInfo = __parseNewsgroupListEntry(str);
      if (localNewsgroupInfo == null)
        break;
      localVector.addElement(localNewsgroupInfo);
    }
    throw new MalformedServerReplyException(str);
    label65: int i = localVector.size();
    if (i < 1)
      return new NewsgroupInfo[0];
    localObject = new NewsgroupInfo[i];
    localVector.copyInto((Object[])localObject);
    return localObject;
  }

  private BufferedReader __retrieve(int paramInt, long paramLong, ArticleInfo paramArticleInfo)
    throws IOException
  {
    if (!NNTPReply.isPositiveCompletion(sendCommand(paramInt, Long.toString(paramLong))))
      return null;
    if (paramArticleInfo != null)
      __parseArticlePointer(getReplyString(), paramArticleInfo);
    return new DotTerminatedMessageReader(this._reader_);
  }

  private BufferedReader __retrieve(int paramInt, String paramString, ArticleInfo paramArticleInfo)
    throws IOException
  {
    if (paramString != null)
    {
      if (!NNTPReply.isPositiveCompletion(sendCommand(paramInt, paramString)))
        return null;
    }
    else if (!NNTPReply.isPositiveCompletion(sendCommand(paramInt)))
      return null;
    if (paramArticleInfo != null)
      __parseArticlePointer(getReplyString(), paramArticleInfo);
    return new DotTerminatedMessageReader(this._reader_);
  }

  private BufferedReader __retrieveArticleInfo(String paramString)
    throws IOException
  {
    if (!NNTPReply.isPositiveCompletion(xover(paramString)))
      return null;
    return new DotTerminatedMessageReader(this._reader_);
  }

  private BufferedReader __retrieveHeader(String paramString1, String paramString2)
    throws IOException
  {
    if (!NNTPReply.isPositiveCompletion(xhdr(paramString1, paramString2)))
      return null;
    return new DotTerminatedMessageReader(this._reader_);
  }

  public boolean authenticate(String paramString1, String paramString2)
    throws IOException
  {
    if ((authinfoUser(paramString1) == 381) && (authinfoPass(paramString2) == 281))
    {
      this._isAllowedToPost = true;
      return true;
    }
    return false;
  }

  public boolean completePendingCommand()
    throws IOException
  {
    return NNTPReply.isPositiveCompletion(getReply());
  }

  public Writer forwardArticle(String paramString)
    throws IOException
  {
    if (!NNTPReply.isPositiveIntermediate(ihave(paramString)))
      return null;
    return new DotTerminatedMessageWriter(this._writer_);
  }

  public Iterable<Article> iterateArticleInfo(long paramLong1, long paramLong2)
    throws IOException
  {
    Object localObject = retrieveArticleInfo(paramLong1, paramLong2);
    if (localObject == null)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("XOVER command failed: ");
      ((StringBuilder)localObject).append(getReplyString());
      throw new IOException(((StringBuilder)localObject).toString());
    }
    return new ArticleIterator(new ReplyIterator((BufferedReader)localObject, false));
  }

  public Iterable<String> iterateNewNews(NewGroupsOrNewsQuery paramNewGroupsOrNewsQuery)
    throws IOException
  {
    if (NNTPReply.isPositiveCompletion(newnews(paramNewGroupsOrNewsQuery.getNewsgroups(), paramNewGroupsOrNewsQuery.getDate(), paramNewGroupsOrNewsQuery.getTime(), paramNewGroupsOrNewsQuery.isGMT(), paramNewGroupsOrNewsQuery.getDistributions())))
      return new ReplyIterator(this._reader_);
    paramNewGroupsOrNewsQuery = new StringBuilder();
    paramNewGroupsOrNewsQuery.append("NEWNEWS command failed: ");
    paramNewGroupsOrNewsQuery.append(getReplyString());
    throw new IOException(paramNewGroupsOrNewsQuery.toString());
  }

  public Iterable<String> iterateNewNewsgroupListing(NewGroupsOrNewsQuery paramNewGroupsOrNewsQuery)
    throws IOException
  {
    if (NNTPReply.isPositiveCompletion(newgroups(paramNewGroupsOrNewsQuery.getDate(), paramNewGroupsOrNewsQuery.getTime(), paramNewGroupsOrNewsQuery.isGMT(), paramNewGroupsOrNewsQuery.getDistributions())))
      return new ReplyIterator(this._reader_);
    paramNewGroupsOrNewsQuery = new StringBuilder();
    paramNewGroupsOrNewsQuery.append("NEWGROUPS command failed: ");
    paramNewGroupsOrNewsQuery.append(getReplyString());
    throw new IOException(paramNewGroupsOrNewsQuery.toString());
  }

  public Iterable<NewsgroupInfo> iterateNewNewsgroups(NewGroupsOrNewsQuery paramNewGroupsOrNewsQuery)
    throws IOException
  {
    return new NewsgroupIterator(iterateNewNewsgroupListing(paramNewGroupsOrNewsQuery));
  }

  public Iterable<String> iterateNewsgroupListing()
    throws IOException
  {
    if (NNTPReply.isPositiveCompletion(list()))
      return new ReplyIterator(this._reader_);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("LIST command failed: ");
    localStringBuilder.append(getReplyString());
    throw new IOException(localStringBuilder.toString());
  }

  public Iterable<String> iterateNewsgroupListing(String paramString)
    throws IOException
  {
    if (NNTPReply.isPositiveCompletion(listActive(paramString)))
      return new ReplyIterator(this._reader_);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("LIST ACTIVE ");
    localStringBuilder.append(paramString);
    localStringBuilder.append(" command failed: ");
    localStringBuilder.append(getReplyString());
    throw new IOException(localStringBuilder.toString());
  }

  public Iterable<NewsgroupInfo> iterateNewsgroups()
    throws IOException
  {
    return new NewsgroupIterator(iterateNewsgroupListing());
  }

  public Iterable<NewsgroupInfo> iterateNewsgroups(String paramString)
    throws IOException
  {
    return new NewsgroupIterator(iterateNewsgroupListing(paramString));
  }

  public String listHelp()
    throws IOException
  {
    if (!NNTPReply.isInformational(help()))
      return null;
    StringWriter localStringWriter = new StringWriter();
    DotTerminatedMessageReader localDotTerminatedMessageReader = new DotTerminatedMessageReader(this._reader_);
    Util.copyReader(localDotTerminatedMessageReader, localStringWriter);
    localDotTerminatedMessageReader.close();
    localStringWriter.close();
    return localStringWriter.toString();
  }

  public String[] listNewNews(NewGroupsOrNewsQuery paramNewGroupsOrNewsQuery)
    throws IOException
  {
    if (!NNTPReply.isPositiveCompletion(newnews(paramNewGroupsOrNewsQuery.getNewsgroups(), paramNewGroupsOrNewsQuery.getDate(), paramNewGroupsOrNewsQuery.getTime(), paramNewGroupsOrNewsQuery.isGMT(), paramNewGroupsOrNewsQuery.getDistributions())))
      return null;
    paramNewGroupsOrNewsQuery = new Vector();
    Object localObject = new DotTerminatedMessageReader(this._reader_);
    while (true)
    {
      String str = ((BufferedReader)localObject).readLine();
      if (str == null)
        break;
      paramNewGroupsOrNewsQuery.addElement(str);
    }
    int i = paramNewGroupsOrNewsQuery.size();
    if (i < 1)
      return new String[0];
    localObject = new String[i];
    paramNewGroupsOrNewsQuery.copyInto((Object[])localObject);
    return localObject;
  }

  public NewsgroupInfo[] listNewNewsgroups(NewGroupsOrNewsQuery paramNewGroupsOrNewsQuery)
    throws IOException
  {
    if (!NNTPReply.isPositiveCompletion(newgroups(paramNewGroupsOrNewsQuery.getDate(), paramNewGroupsOrNewsQuery.getTime(), paramNewGroupsOrNewsQuery.isGMT(), paramNewGroupsOrNewsQuery.getDistributions())))
      return null;
    return __readNewsgroupListing();
  }

  public NewsgroupInfo[] listNewsgroups()
    throws IOException
  {
    if (!NNTPReply.isPositiveCompletion(list()))
      return null;
    return __readNewsgroupListing();
  }

  public NewsgroupInfo[] listNewsgroups(String paramString)
    throws IOException
  {
    if (!NNTPReply.isPositiveCompletion(listActive(paramString)))
      return null;
    return __readNewsgroupListing();
  }

  public String[] listOverviewFmt()
    throws IOException
  {
    if (!NNTPReply.isPositiveCompletion(sendCommand("LIST", "OVERVIEW.FMT")))
      return null;
    DotTerminatedMessageReader localDotTerminatedMessageReader = new DotTerminatedMessageReader(this._reader_);
    ArrayList localArrayList = new ArrayList();
    while (true)
    {
      String str = localDotTerminatedMessageReader.readLine();
      if (str == null)
        break;
      localArrayList.add(str);
    }
    localDotTerminatedMessageReader.close();
    return (String[])localArrayList.toArray(new String[localArrayList.size()]);
  }

  public boolean logout()
    throws IOException
  {
    return NNTPReply.isPositiveCompletion(quit());
  }

  public Writer postArticle()
    throws IOException
  {
    if (!NNTPReply.isPositiveIntermediate(post()))
      return null;
    return new DotTerminatedMessageWriter(this._writer_);
  }

  public BufferedReader retrieveArticle(long paramLong)
    throws IOException
  {
    return retrieveArticle(paramLong, null);
  }

  public BufferedReader retrieveArticle(long paramLong, ArticleInfo paramArticleInfo)
    throws IOException
  {
    return __retrieve(0, paramLong, paramArticleInfo);
  }

  public BufferedReader retrieveArticle(String paramString, ArticleInfo paramArticleInfo)
    throws IOException
  {
    return __retrieve(0, paramString, paramArticleInfo);
  }

  public Reader retrieveArticle()
    throws IOException
  {
    return retrieveArticle((String)null);
  }

  @Deprecated
  public Reader retrieveArticle(int paramInt)
    throws IOException
  {
    return retrieveArticle(paramInt);
  }

  @Deprecated
  public Reader retrieveArticle(int paramInt, ArticlePointer paramArticlePointer)
    throws IOException
  {
    ArticleInfo localArticleInfo = __ap2ai(paramArticlePointer);
    BufferedReader localBufferedReader = retrieveArticle(paramInt, localArticleInfo);
    __ai2ap(localArticleInfo, paramArticlePointer);
    return localBufferedReader;
  }

  public Reader retrieveArticle(String paramString)
    throws IOException
  {
    return retrieveArticle(paramString, (ArticleInfo)null);
  }

  @Deprecated
  public Reader retrieveArticle(String paramString, ArticlePointer paramArticlePointer)
    throws IOException
  {
    ArticleInfo localArticleInfo = __ap2ai(paramArticlePointer);
    paramString = retrieveArticle(paramString, localArticleInfo);
    __ai2ap(localArticleInfo, paramArticlePointer);
    return paramString;
  }

  public BufferedReader retrieveArticleBody(long paramLong)
    throws IOException
  {
    return retrieveArticleBody(paramLong, null);
  }

  public BufferedReader retrieveArticleBody(long paramLong, ArticleInfo paramArticleInfo)
    throws IOException
  {
    return __retrieve(1, paramLong, paramArticleInfo);
  }

  public BufferedReader retrieveArticleBody(String paramString, ArticleInfo paramArticleInfo)
    throws IOException
  {
    return __retrieve(1, paramString, paramArticleInfo);
  }

  public Reader retrieveArticleBody()
    throws IOException
  {
    return retrieveArticleBody(null);
  }

  @Deprecated
  public Reader retrieveArticleBody(int paramInt)
    throws IOException
  {
    return retrieveArticleBody(paramInt);
  }

  @Deprecated
  public Reader retrieveArticleBody(int paramInt, ArticlePointer paramArticlePointer)
    throws IOException
  {
    ArticleInfo localArticleInfo = __ap2ai(paramArticlePointer);
    BufferedReader localBufferedReader = retrieveArticleBody(paramInt, localArticleInfo);
    __ai2ap(localArticleInfo, paramArticlePointer);
    return localBufferedReader;
  }

  public Reader retrieveArticleBody(String paramString)
    throws IOException
  {
    return retrieveArticleBody(paramString, (ArticleInfo)null);
  }

  @Deprecated
  public Reader retrieveArticleBody(String paramString, ArticlePointer paramArticlePointer)
    throws IOException
  {
    ArticleInfo localArticleInfo = __ap2ai(paramArticlePointer);
    paramString = retrieveArticleBody(paramString, localArticleInfo);
    __ai2ap(localArticleInfo, paramArticlePointer);
    return paramString;
  }

  public BufferedReader retrieveArticleHeader(long paramLong)
    throws IOException
  {
    return retrieveArticleHeader(paramLong, null);
  }

  public BufferedReader retrieveArticleHeader(long paramLong, ArticleInfo paramArticleInfo)
    throws IOException
  {
    return __retrieve(3, paramLong, paramArticleInfo);
  }

  public BufferedReader retrieveArticleHeader(String paramString, ArticleInfo paramArticleInfo)
    throws IOException
  {
    return __retrieve(3, paramString, paramArticleInfo);
  }

  public Reader retrieveArticleHeader()
    throws IOException
  {
    return retrieveArticleHeader((String)null);
  }

  @Deprecated
  public Reader retrieveArticleHeader(int paramInt)
    throws IOException
  {
    return retrieveArticleHeader(paramInt);
  }

  @Deprecated
  public Reader retrieveArticleHeader(int paramInt, ArticlePointer paramArticlePointer)
    throws IOException
  {
    ArticleInfo localArticleInfo = __ap2ai(paramArticlePointer);
    BufferedReader localBufferedReader = retrieveArticleHeader(paramInt, localArticleInfo);
    __ai2ap(localArticleInfo, paramArticlePointer);
    return localBufferedReader;
  }

  public Reader retrieveArticleHeader(String paramString)
    throws IOException
  {
    return retrieveArticleHeader(paramString, (ArticleInfo)null);
  }

  @Deprecated
  public Reader retrieveArticleHeader(String paramString, ArticlePointer paramArticlePointer)
    throws IOException
  {
    ArticleInfo localArticleInfo = __ap2ai(paramArticlePointer);
    paramString = retrieveArticleHeader(paramString, localArticleInfo);
    __ai2ap(localArticleInfo, paramArticlePointer);
    return paramString;
  }

  public BufferedReader retrieveArticleInfo(long paramLong)
    throws IOException
  {
    return __retrieveArticleInfo(Long.toString(paramLong));
  }

  public BufferedReader retrieveArticleInfo(long paramLong1, long paramLong2)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramLong1);
    localStringBuilder.append("-");
    localStringBuilder.append(paramLong2);
    return __retrieveArticleInfo(localStringBuilder.toString());
  }

  @Deprecated
  public Reader retrieveArticleInfo(int paramInt)
    throws IOException
  {
    return retrieveArticleInfo(paramInt);
  }

  @Deprecated
  public Reader retrieveArticleInfo(int paramInt1, int paramInt2)
    throws IOException
  {
    return retrieveArticleInfo(paramInt1, paramInt2);
  }

  public BufferedReader retrieveHeader(String paramString, long paramLong)
    throws IOException
  {
    return __retrieveHeader(paramString, Long.toString(paramLong));
  }

  public BufferedReader retrieveHeader(String paramString, long paramLong1, long paramLong2)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramLong1);
    localStringBuilder.append("-");
    localStringBuilder.append(paramLong2);
    return __retrieveHeader(paramString, localStringBuilder.toString());
  }

  @Deprecated
  public Reader retrieveHeader(String paramString, int paramInt)
    throws IOException
  {
    return retrieveHeader(paramString, paramInt);
  }

  @Deprecated
  public Reader retrieveHeader(String paramString, int paramInt1, int paramInt2)
    throws IOException
  {
    return retrieveHeader(paramString, paramInt1, paramInt2);
  }

  @Deprecated
  public boolean selectArticle(int paramInt)
    throws IOException
  {
    return selectArticle(paramInt);
  }

  @Deprecated
  public boolean selectArticle(int paramInt, ArticlePointer paramArticlePointer)
    throws IOException
  {
    ArticleInfo localArticleInfo = __ap2ai(paramArticlePointer);
    boolean bool = selectArticle(paramInt, localArticleInfo);
    __ai2ap(localArticleInfo, paramArticlePointer);
    return bool;
  }

  public boolean selectArticle(long paramLong)
    throws IOException
  {
    return selectArticle(paramLong, null);
  }

  public boolean selectArticle(long paramLong, ArticleInfo paramArticleInfo)
    throws IOException
  {
    if (!NNTPReply.isPositiveCompletion(stat(paramLong)))
      return false;
    if (paramArticleInfo != null)
      __parseArticlePointer(getReplyString(), paramArticleInfo);
    return true;
  }

  public boolean selectArticle(String paramString)
    throws IOException
  {
    return selectArticle(paramString, (ArticleInfo)null);
  }

  public boolean selectArticle(String paramString, ArticleInfo paramArticleInfo)
    throws IOException
  {
    if (paramString != null)
    {
      if (!NNTPReply.isPositiveCompletion(stat(paramString)))
        return false;
    }
    else if (!NNTPReply.isPositiveCompletion(stat()))
      return false;
    if (paramArticleInfo != null)
      __parseArticlePointer(getReplyString(), paramArticleInfo);
    return true;
  }

  @Deprecated
  public boolean selectArticle(String paramString, ArticlePointer paramArticlePointer)
    throws IOException
  {
    ArticleInfo localArticleInfo = __ap2ai(paramArticlePointer);
    boolean bool = selectArticle(paramString, localArticleInfo);
    __ai2ap(localArticleInfo, paramArticlePointer);
    return bool;
  }

  public boolean selectArticle(ArticleInfo paramArticleInfo)
    throws IOException
  {
    return selectArticle(null, paramArticleInfo);
  }

  @Deprecated
  public boolean selectArticle(ArticlePointer paramArticlePointer)
    throws IOException
  {
    ArticleInfo localArticleInfo = __ap2ai(paramArticlePointer);
    boolean bool = selectArticle(localArticleInfo);
    __ai2ap(localArticleInfo, paramArticlePointer);
    return bool;
  }

  public boolean selectNewsgroup(String paramString)
    throws IOException
  {
    return selectNewsgroup(paramString, null);
  }

  public boolean selectNewsgroup(String paramString, NewsgroupInfo paramNewsgroupInfo)
    throws IOException
  {
    if (!NNTPReply.isPositiveCompletion(group(paramString)))
      return false;
    if (paramNewsgroupInfo != null)
      __parseGroupReply(getReplyString(), paramNewsgroupInfo);
    return true;
  }

  public boolean selectNextArticle()
    throws IOException
  {
    return selectNextArticle((ArticleInfo)null);
  }

  public boolean selectNextArticle(ArticleInfo paramArticleInfo)
    throws IOException
  {
    if (!NNTPReply.isPositiveCompletion(next()))
      return false;
    if (paramArticleInfo != null)
      __parseArticlePointer(getReplyString(), paramArticleInfo);
    return true;
  }

  @Deprecated
  public boolean selectNextArticle(ArticlePointer paramArticlePointer)
    throws IOException
  {
    ArticleInfo localArticleInfo = __ap2ai(paramArticlePointer);
    boolean bool = selectNextArticle(localArticleInfo);
    __ai2ap(localArticleInfo, paramArticlePointer);
    return bool;
  }

  public boolean selectPreviousArticle()
    throws IOException
  {
    return selectPreviousArticle((ArticleInfo)null);
  }

  public boolean selectPreviousArticle(ArticleInfo paramArticleInfo)
    throws IOException
  {
    if (!NNTPReply.isPositiveCompletion(last()))
      return false;
    if (paramArticleInfo != null)
      __parseArticlePointer(getReplyString(), paramArticleInfo);
    return true;
  }

  @Deprecated
  public boolean selectPreviousArticle(ArticlePointer paramArticlePointer)
    throws IOException
  {
    ArticleInfo localArticleInfo = __ap2ai(paramArticlePointer);
    boolean bool = selectPreviousArticle(localArticleInfo);
    __ai2ap(localArticleInfo, paramArticlePointer);
    return bool;
  }
}