package okhttp3.internal.http;

import java.io.IOException;
import java.net.ProtocolException;
import okhttp3.Protocol;
import okhttp3.Response;

public final class StatusLine
{
  public static final int HTTP_CONTINUE = 100;
  public static final int HTTP_PERM_REDIRECT = 308;
  public static final int HTTP_TEMP_REDIRECT = 307;
  public final int code;
  public final String message;
  public final Protocol protocol;

  public StatusLine(Protocol paramProtocol, int paramInt, String paramString)
  {
    this.protocol = paramProtocol;
    this.code = paramInt;
    this.message = paramString;
  }

  public static StatusLine get(Response paramResponse)
  {
    return new StatusLine(paramResponse.protocol(), paramResponse.code(), paramResponse.message());
  }

  public static StatusLine parse(String paramString)
    throws IOException
  {
    boolean bool = paramString.startsWith("HTTP/1.");
    int i = 9;
    Object localObject;
    if (bool)
    {
      if ((paramString.length() >= 9) && (paramString.charAt(8) == ' '))
      {
        j = paramString.charAt(7) - '0';
        if (j == 0)
        {
          localObject = Protocol.HTTP_1_0;
        }
        else if (j == 1)
        {
          localObject = Protocol.HTTP_1_1;
        }
        else
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("Unexpected status line: ");
          ((StringBuilder)localObject).append(paramString);
          throw new ProtocolException(((StringBuilder)localObject).toString());
        }
      }
      else
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Unexpected status line: ");
        ((StringBuilder)localObject).append(paramString);
        throw new ProtocolException(((StringBuilder)localObject).toString());
      }
    }
    else
    {
      if (!paramString.startsWith("ICY "))
        break label343;
      localObject = Protocol.HTTP_1_0;
      i = 4;
    }
    int k = paramString.length();
    int j = i + 3;
    if (k < j)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Unexpected status line: ");
      ((StringBuilder)localObject).append(paramString);
      throw new ProtocolException(((StringBuilder)localObject).toString());
    }
    try
    {
      k = Integer.parseInt(paramString.substring(i, j));
      String str = "";
      if (paramString.length() > j)
      {
        if (paramString.charAt(j) != ' ')
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("Unexpected status line: ");
          ((StringBuilder)localObject).append(paramString);
          throw new ProtocolException(((StringBuilder)localObject).toString());
        }
        str = paramString.substring(i + 4);
      }
      return new StatusLine((Protocol)localObject, k, str);
      label306: localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Unexpected status line: ");
      ((StringBuilder)localObject).append(paramString);
      throw new ProtocolException(((StringBuilder)localObject).toString());
      label343: localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Unexpected status line: ");
      ((StringBuilder)localObject).append(paramString);
      throw new ProtocolException(((StringBuilder)localObject).toString());
    }
    catch (NumberFormatException localNumberFormatException)
    {
      break label306;
    }
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    String str;
    if (this.protocol == Protocol.HTTP_1_0)
      str = "HTTP/1.0";
    else
      str = "HTTP/1.1";
    localStringBuilder.append(str);
    localStringBuilder.append(' ');
    localStringBuilder.append(this.code);
    if (this.message != null)
    {
      localStringBuilder.append(' ');
      localStringBuilder.append(this.message);
    }
    return localStringBuilder.toString();
  }
}