package org.apache.http.message;

import java.io.Serializable;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.util.CharArrayBuffer;

public class BasicRequestLine
  implements RequestLine, Cloneable, Serializable
{
  private static final long serialVersionUID = 2810581718468737193L;
  private final String method;
  private final ProtocolVersion protoversion;
  private final String uri;

  public BasicRequestLine(String paramString1, String paramString2, ProtocolVersion paramProtocolVersion)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("Method must not be null.");
    if (paramString2 == null)
      throw new IllegalArgumentException("URI must not be null.");
    if (paramProtocolVersion == null)
      throw new IllegalArgumentException("Protocol version must not be null.");
    this.method = paramString1;
    this.uri = paramString2;
    this.protoversion = paramProtocolVersion;
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    return super.clone();
  }

  public String getMethod()
  {
    return this.method;
  }

  public ProtocolVersion getProtocolVersion()
  {
    return this.protoversion;
  }

  public String getUri()
  {
    return this.uri;
  }

  public String toString()
  {
    return BasicLineFormatter.DEFAULT.formatRequestLine(null, this).toString();
  }
}