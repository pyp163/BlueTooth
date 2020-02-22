package org.apache.commons.net.ftp;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Calendar;
import java.util.Formatter;

public class FTPFile
  implements Serializable
{
  public static final int DIRECTORY_TYPE = 1;
  public static final int EXECUTE_PERMISSION = 2;
  public static final int FILE_TYPE = 0;
  public static final int GROUP_ACCESS = 1;
  public static final int READ_PERMISSION = 0;
  public static final int SYMBOLIC_LINK_TYPE = 2;
  public static final int UNKNOWN_TYPE = 3;
  public static final int USER_ACCESS = 0;
  public static final int WORLD_ACCESS = 2;
  public static final int WRITE_PERMISSION = 1;
  private static final long serialVersionUID = 9010790363003271996L;
  private Calendar _date = null;
  private String _group = "";
  private int _hardLinkCount = 0;
  private String _link;
  private String _name = null;
  private boolean[][] _permissions = (boolean[][])Array.newInstance(Boolean.TYPE, new int[] { 3, 3 });
  private String _rawListing = null;
  private long _size = -1L;
  private int _type = 3;
  private String _user = "";

  private char formatType()
  {
    switch (this._type)
    {
    default:
      return '?';
    case 2:
      return 'l';
    case 1:
      return 'd';
    case 0:
    }
    return '-';
  }

  private String permissionToString(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (hasPermission(paramInt, 0))
      localStringBuilder.append('r');
    else
      localStringBuilder.append('-');
    if (hasPermission(paramInt, 1))
      localStringBuilder.append('w');
    else
      localStringBuilder.append('-');
    if (hasPermission(paramInt, 2))
      localStringBuilder.append('x');
    else
      localStringBuilder.append('-');
    return localStringBuilder.toString();
  }

  public String getGroup()
  {
    return this._group;
  }

  public int getHardLinkCount()
  {
    return this._hardLinkCount;
  }

  public String getLink()
  {
    return this._link;
  }

  public String getName()
  {
    return this._name;
  }

  public String getRawListing()
  {
    return this._rawListing;
  }

  public long getSize()
  {
    return this._size;
  }

  public Calendar getTimestamp()
  {
    return this._date;
  }

  public int getType()
  {
    return this._type;
  }

  public String getUser()
  {
    return this._user;
  }

  public boolean hasPermission(int paramInt1, int paramInt2)
  {
    return this._permissions[paramInt1][paramInt2];
  }

  public boolean isDirectory()
  {
    return this._type == 1;
  }

  public boolean isFile()
  {
    return this._type == 0;
  }

  public boolean isSymbolicLink()
  {
    return this._type == 2;
  }

  public boolean isUnknown()
  {
    return this._type == 3;
  }

  public void setGroup(String paramString)
  {
    this._group = paramString;
  }

  public void setHardLinkCount(int paramInt)
  {
    this._hardLinkCount = paramInt;
  }

  public void setLink(String paramString)
  {
    this._link = paramString;
  }

  public void setName(String paramString)
  {
    this._name = paramString;
  }

  public void setPermission(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    this._permissions[paramInt1][paramInt2] = paramBoolean;
  }

  public void setRawListing(String paramString)
  {
    this._rawListing = paramString;
  }

  public void setSize(long paramLong)
  {
    this._size = paramLong;
  }

  public void setTimestamp(Calendar paramCalendar)
  {
    this._date = paramCalendar;
  }

  public void setType(int paramInt)
  {
    this._type = paramInt;
  }

  public void setUser(String paramString)
  {
    this._user = paramString;
  }

  public String toFormattedString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Formatter localFormatter = new Formatter(localStringBuilder);
    localStringBuilder.append(formatType());
    localStringBuilder.append(permissionToString(0));
    localStringBuilder.append(permissionToString(1));
    localStringBuilder.append(permissionToString(2));
    localFormatter.format(" %4d", new Object[] { Integer.valueOf(getHardLinkCount()) });
    localFormatter.format(" %-8s %-8s", new Object[] { getGroup(), getUser() });
    localFormatter.format(" %8d", new Object[] { Long.valueOf(getSize()) });
    Calendar localCalendar = getTimestamp();
    if (localCalendar != null)
    {
      localFormatter.format(" %1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS", new Object[] { localCalendar });
      localFormatter.format(" %1$tZ", new Object[] { localCalendar });
      localStringBuilder.append(' ');
    }
    localStringBuilder.append(' ');
    localStringBuilder.append(getName());
    return localStringBuilder.toString();
  }

  public String toString()
  {
    return getRawListing();
  }
}