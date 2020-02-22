package org.apache.commons.net.pop3;

import java.io.IOException;
import java.io.Reader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;
import org.apache.commons.net.io.DotTerminatedMessageReader;

public class POP3Client extends POP3
{
  private static POP3MessageInfo __parseStatus(String paramString)
  {
    paramString = new StringTokenizer(paramString);
    if (!paramString.hasMoreElements())
      return null;
    try
    {
      int i = Integer.parseInt(paramString.nextToken());
      if (!paramString.hasMoreElements())
        return null;
      int j = Integer.parseInt(paramString.nextToken());
      return new POP3MessageInfo(i, j);
    }
    catch (NumberFormatException paramString)
    {
    }
    return null;
  }

  private static POP3MessageInfo __parseUID(String paramString)
  {
    paramString = new StringTokenizer(paramString);
    if (!paramString.hasMoreElements())
      return null;
    try
    {
      int i = Integer.parseInt(paramString.nextToken());
      if (!paramString.hasMoreElements())
        return null;
      paramString = paramString.nextToken();
      return new POP3MessageInfo(i, paramString);
    }
    catch (NumberFormatException paramString)
    {
    }
    return null;
  }

  public boolean deleteMessage(int paramInt)
    throws IOException
  {
    int i = getState();
    boolean bool = false;
    if (i == 1)
    {
      if (sendCommand(6, Integer.toString(paramInt)) == 0)
        bool = true;
      return bool;
    }
    return false;
  }

  public POP3MessageInfo listMessage(int paramInt)
    throws IOException
  {
    if (getState() != 1)
      return null;
    if (sendCommand(4, Integer.toString(paramInt)) != 0)
      return null;
    return __parseStatus(this._lastReplyLine.substring(3));
  }

  public POP3MessageInfo[] listMessages()
    throws IOException
  {
    if (getState() != 1)
      return null;
    if (sendCommand(4) != 0)
      return null;
    getAdditionalReply();
    POP3MessageInfo[] arrayOfPOP3MessageInfo = new POP3MessageInfo[this._replyLines.size() - 2];
    ListIterator localListIterator = this._replyLines.listIterator(1);
    int i = 0;
    while (i < arrayOfPOP3MessageInfo.length)
    {
      arrayOfPOP3MessageInfo[i] = __parseStatus((String)localListIterator.next());
      i += 1;
    }
    return arrayOfPOP3MessageInfo;
  }

  public POP3MessageInfo listUniqueIdentifier(int paramInt)
    throws IOException
  {
    if (getState() != 1)
      return null;
    if (sendCommand(11, Integer.toString(paramInt)) != 0)
      return null;
    return __parseUID(this._lastReplyLine.substring(3));
  }

  public POP3MessageInfo[] listUniqueIdentifiers()
    throws IOException
  {
    if (getState() != 1)
      return null;
    if (sendCommand(11) != 0)
      return null;
    getAdditionalReply();
    POP3MessageInfo[] arrayOfPOP3MessageInfo = new POP3MessageInfo[this._replyLines.size() - 2];
    ListIterator localListIterator = this._replyLines.listIterator(1);
    int i = 0;
    while (i < arrayOfPOP3MessageInfo.length)
    {
      arrayOfPOP3MessageInfo[i] = __parseUID((String)localListIterator.next());
      i += 1;
    }
    return arrayOfPOP3MessageInfo;
  }

  public boolean login(String paramString1, String paramString2)
    throws IOException
  {
    if (getState() != 0)
      return false;
    if (sendCommand(0, paramString1) != 0)
      return false;
    if (sendCommand(1, paramString2) != 0)
      return false;
    setState(1);
    return true;
  }

  public boolean login(String paramString1, String paramString2, String paramString3)
    throws IOException, NoSuchAlgorithmException
  {
    if (getState() != 0)
      return false;
    MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString2);
    localStringBuilder.append(paramString3);
    paramString3 = localMessageDigest.digest(localStringBuilder.toString().getBytes());
    paramString2 = new StringBuilder(128);
    int i = 0;
    while (i < paramString3.length)
    {
      int j = paramString3[i] & 0xFF;
      if (j <= 15)
        paramString2.append("0");
      paramString2.append(Integer.toHexString(j));
      i += 1;
    }
    paramString3 = new StringBuilder(256);
    paramString3.append(paramString1);
    paramString3.append(' ');
    paramString3.append(paramString2.toString());
    if (sendCommand(9, paramString3.toString()) != 0)
      return false;
    setState(1);
    return true;
  }

  public boolean logout()
    throws IOException
  {
    if (getState() == 1)
      setState(2);
    sendCommand(2);
    return this._replyCode == 0;
  }

  public boolean noop()
    throws IOException
  {
    int i = getState();
    boolean bool = false;
    if (i == 1)
    {
      if (sendCommand(7) == 0)
        bool = true;
      return bool;
    }
    return false;
  }

  public boolean reset()
    throws IOException
  {
    int i = getState();
    boolean bool = false;
    if (i == 1)
    {
      if (sendCommand(8) == 0)
        bool = true;
      return bool;
    }
    return false;
  }

  public Reader retrieveMessage(int paramInt)
    throws IOException
  {
    if (getState() != 1)
      return null;
    if (sendCommand(5, Integer.toString(paramInt)) != 0)
      return null;
    return new DotTerminatedMessageReader(this._reader);
  }

  public Reader retrieveMessageTop(int paramInt1, int paramInt2)
    throws IOException
  {
    if (paramInt2 >= 0)
    {
      if (getState() != 1)
        return null;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(Integer.toString(paramInt1));
      localStringBuilder.append(" ");
      localStringBuilder.append(Integer.toString(paramInt2));
      if (sendCommand(10, localStringBuilder.toString()) != 0)
        return null;
      return new DotTerminatedMessageReader(this._reader);
    }
    return null;
  }

  public POP3MessageInfo status()
    throws IOException
  {
    if (getState() != 1)
      return null;
    if (sendCommand(3) != 0)
      return null;
    return __parseStatus(this._lastReplyLine.substring(3));
  }
}