package org.apache.commons.net.imap;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.net.MalformedServerReplyException;

public final class IMAPReply
{
  public static final int BAD = 2;
  public static final int CONT = 3;
  private static final String IMAP_BAD = "BAD";
  private static final String IMAP_CONTINUATION_PREFIX = "+";
  private static final String IMAP_NO = "NO";
  private static final String IMAP_OK = "OK";
  private static final String IMAP_UNTAGGED_PREFIX = "* ";
  public static final int NO = 1;
  public static final int OK = 0;
  private static final Pattern TAGGED_PATTERN = Pattern.compile("^\\w+ (\\S+).*");
  private static final String TAGGED_RESPONSE = "^\\w+ (\\S+).*";
  private static final Pattern UNTAGGED_PATTERN = Pattern.compile("^\\* (\\S+).*");
  private static final String UNTAGGED_RESPONSE = "^\\* (\\S+).*";

  public static int getReplyCode(String paramString)
    throws IOException
  {
    return getReplyCode(paramString, TAGGED_PATTERN);
  }

  private static int getReplyCode(String paramString, Pattern paramPattern)
    throws IOException
  {
    if (isContinuation(paramString))
      return 3;
    paramPattern = paramPattern.matcher(paramString);
    if (paramPattern.matches())
    {
      paramPattern = paramPattern.group(1);
      if (paramPattern.equals("OK"))
        return 0;
      if (paramPattern.equals("BAD"))
        return 2;
      if (paramPattern.equals("NO"))
        return 1;
    }
    paramPattern = new StringBuilder();
    paramPattern.append("Received unexpected IMAP protocol response from server: '");
    paramPattern.append(paramString);
    paramPattern.append("'.");
    throw new MalformedServerReplyException(paramPattern.toString());
  }

  public static int getUntaggedReplyCode(String paramString)
    throws IOException
  {
    return getReplyCode(paramString, UNTAGGED_PATTERN);
  }

  public static boolean isContinuation(int paramInt)
  {
    return paramInt == 3;
  }

  public static boolean isContinuation(String paramString)
  {
    return paramString.startsWith("+");
  }

  public static boolean isSuccess(int paramInt)
  {
    return paramInt == 0;
  }

  public static boolean isUntagged(String paramString)
  {
    return paramString.startsWith("* ");
  }
}