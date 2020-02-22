package org.apache.commons.net.smtp;

import java.util.Enumeration;
import java.util.Vector;

public final class RelayPath
{
  String _emailAddress;
  Vector<String> _path = new Vector();

  public RelayPath(String paramString)
  {
    this._emailAddress = paramString;
  }

  public void addRelay(String paramString)
  {
    this._path.addElement(paramString);
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append('<');
    Enumeration localEnumeration = this._path.elements();
    if (localEnumeration.hasMoreElements())
    {
      localStringBuilder.append('@');
      localStringBuilder.append((String)localEnumeration.nextElement());
      while (localEnumeration.hasMoreElements())
      {
        localStringBuilder.append(",@");
        localStringBuilder.append((String)localEnumeration.nextElement());
      }
      localStringBuilder.append(':');
    }
    localStringBuilder.append(this._emailAddress);
    localStringBuilder.append('>');
    return localStringBuilder.toString();
  }
}