package org.apache.commons.net.smtp;

import java.io.IOException;
import java.io.Writer;
import java.net.InetAddress;
import org.apache.commons.net.io.DotTerminatedMessageWriter;

public class SMTPClient extends SMTP
{
  public SMTPClient()
  {
  }

  public SMTPClient(String paramString)
  {
    super(paramString);
  }

  public boolean addRecipient(String paramString)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("<");
    localStringBuilder.append(paramString);
    localStringBuilder.append(">");
    return SMTPReply.isPositiveCompletion(rcpt(localStringBuilder.toString()));
  }

  public boolean addRecipient(RelayPath paramRelayPath)
    throws IOException
  {
    return SMTPReply.isPositiveCompletion(rcpt(paramRelayPath.toString()));
  }

  public boolean completePendingCommand()
    throws IOException
  {
    return SMTPReply.isPositiveCompletion(getReply());
  }

  public String listHelp()
    throws IOException
  {
    if (SMTPReply.isPositiveCompletion(help()))
      return getReplyString();
    return null;
  }

  public String listHelp(String paramString)
    throws IOException
  {
    if (SMTPReply.isPositiveCompletion(help(paramString)))
      return getReplyString();
    return null;
  }

  public boolean login()
    throws IOException
  {
    String str = getLocalAddress().getHostName();
    if (str == null)
      return false;
    return SMTPReply.isPositiveCompletion(helo(str));
  }

  public boolean login(String paramString)
    throws IOException
  {
    return SMTPReply.isPositiveCompletion(helo(paramString));
  }

  public boolean logout()
    throws IOException
  {
    return SMTPReply.isPositiveCompletion(quit());
  }

  public boolean reset()
    throws IOException
  {
    return SMTPReply.isPositiveCompletion(rset());
  }

  public Writer sendMessageData()
    throws IOException
  {
    if (!SMTPReply.isPositiveIntermediate(data()))
      return null;
    return new DotTerminatedMessageWriter(this._writer);
  }

  public boolean sendNoOp()
    throws IOException
  {
    return SMTPReply.isPositiveCompletion(noop());
  }

  public boolean sendShortMessageData(String paramString)
    throws IOException
  {
    Writer localWriter = sendMessageData();
    if (localWriter == null)
      return false;
    localWriter.write(paramString);
    localWriter.close();
    return completePendingCommand();
  }

  public boolean sendSimpleMessage(String paramString1, String paramString2, String paramString3)
    throws IOException
  {
    if (!setSender(paramString1))
      return false;
    if (!addRecipient(paramString2))
      return false;
    return sendShortMessageData(paramString3);
  }

  public boolean sendSimpleMessage(String paramString1, String[] paramArrayOfString, String paramString2)
    throws IOException
  {
    if (!setSender(paramString1))
      return false;
    int i = 0;
    int j = 0;
    while (i < paramArrayOfString.length)
    {
      if (addRecipient(paramArrayOfString[i]))
        j = 1;
      i += 1;
    }
    if (j == 0)
      return false;
    return sendShortMessageData(paramString2);
  }

  public boolean setSender(String paramString)
    throws IOException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("<");
    localStringBuilder.append(paramString);
    localStringBuilder.append(">");
    return SMTPReply.isPositiveCompletion(mail(localStringBuilder.toString()));
  }

  public boolean setSender(RelayPath paramRelayPath)
    throws IOException
  {
    return SMTPReply.isPositiveCompletion(mail(paramRelayPath.toString()));
  }

  public boolean verify(String paramString)
    throws IOException
  {
    int i = vrfy(paramString);
    return (i == 250) || (i == 251);
  }
}