package org.apache.http;

import java.io.Serializable;
import org.apache.http.util.CharArrayBuffer;

public class ProtocolVersion
  implements Serializable, Cloneable
{
  private static final long serialVersionUID = 8950662842175091068L;
  protected final int major;
  protected final int minor;
  protected final String protocol;

  public ProtocolVersion(String paramString, int paramInt1, int paramInt2)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Protocol name must not be null.");
    if (paramInt1 < 0)
      throw new IllegalArgumentException("Protocol major version number must not be negative.");
    if (paramInt2 < 0)
      throw new IllegalArgumentException("Protocol minor version number may not be negative");
    this.protocol = paramString;
    this.major = paramInt1;
    this.minor = paramInt2;
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    return super.clone();
  }

  public int compareToVersion(ProtocolVersion paramProtocolVersion)
  {
    if (paramProtocolVersion == null)
      throw new IllegalArgumentException("Protocol version must not be null.");
    if (!this.protocol.equals(paramProtocolVersion.protocol))
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append("Versions for different protocols cannot be compared. ");
      localStringBuffer.append(this);
      localStringBuffer.append(" ");
      localStringBuffer.append(paramProtocolVersion);
      throw new IllegalArgumentException(localStringBuffer.toString());
    }
    int j = getMajor() - paramProtocolVersion.getMajor();
    int i = j;
    if (j == 0)
      i = getMinor() - paramProtocolVersion.getMinor();
    return i;
  }

  public final boolean equals(Object paramObject)
  {
    if (this == paramObject)
      return true;
    if (!(paramObject instanceof ProtocolVersion))
      return false;
    paramObject = (ProtocolVersion)paramObject;
    return (this.protocol.equals(paramObject.protocol)) && (this.major == paramObject.major) && (this.minor == paramObject.minor);
  }

  public ProtocolVersion forVersion(int paramInt1, int paramInt2)
  {
    if ((paramInt1 == this.major) && (paramInt2 == this.minor))
      return this;
    return new ProtocolVersion(this.protocol, paramInt1, paramInt2);
  }

  public final int getMajor()
  {
    return this.major;
  }

  public final int getMinor()
  {
    return this.minor;
  }

  public final String getProtocol()
  {
    return this.protocol;
  }

  public final boolean greaterEquals(ProtocolVersion paramProtocolVersion)
  {
    return (isComparable(paramProtocolVersion)) && (compareToVersion(paramProtocolVersion) >= 0);
  }

  public final int hashCode()
  {
    return this.protocol.hashCode() ^ this.major * 100000 ^ this.minor;
  }

  public boolean isComparable(ProtocolVersion paramProtocolVersion)
  {
    return (paramProtocolVersion != null) && (this.protocol.equals(paramProtocolVersion.protocol));
  }

  public final boolean lessEquals(ProtocolVersion paramProtocolVersion)
  {
    return (isComparable(paramProtocolVersion)) && (compareToVersion(paramProtocolVersion) <= 0);
  }

  public String toString()
  {
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(16);
    localCharArrayBuffer.append(this.protocol);
    localCharArrayBuffer.append('/');
    localCharArrayBuffer.append(Integer.toString(this.major));
    localCharArrayBuffer.append('.');
    localCharArrayBuffer.append(Integer.toString(this.minor));
    return localCharArrayBuffer.toString();
  }
}