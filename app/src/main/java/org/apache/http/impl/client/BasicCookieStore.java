package org.apache.http.impl.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;
import org.apache.http.annotation.GuardedBy;
import org.apache.http.annotation.ThreadSafe;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieIdentityComparator;

@ThreadSafe
public class BasicCookieStore
  implements CookieStore, Serializable
{
  private static final long serialVersionUID = -7581093305228232025L;

  @GuardedBy("this")
  private final TreeSet<Cookie> cookies = new TreeSet(new CookieIdentityComparator());

  public void addCookie(Cookie paramCookie)
  {
    if (paramCookie != null);
    try
    {
      this.cookies.remove(paramCookie);
      if (!paramCookie.isExpired(new Date()))
        this.cookies.add(paramCookie);
    }
    finally
    {
    }
  }

  public void addCookies(Cookie[] paramArrayOfCookie)
  {
    if (paramArrayOfCookie != null)
      try
      {
        int j = paramArrayOfCookie.length;
        int i = 0;
        while (i < j)
        {
          addCookie(paramArrayOfCookie[i]);
          i += 1;
        }
      }
      finally
      {
      }
  }

  public void clear()
  {
    try
    {
      this.cookies.clear();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean clearExpired(Date paramDate)
  {
    boolean bool = false;
    if (paramDate == null)
      return false;
    try
    {
      Iterator localIterator = this.cookies.iterator();
      while (localIterator.hasNext())
        if (((Cookie)localIterator.next()).isExpired(paramDate))
        {
          localIterator.remove();
          bool = true;
        }
      return bool;
    }
    finally
    {
    }
    throw paramDate;
  }

  public List<Cookie> getCookies()
  {
    try
    {
      ArrayList localArrayList = new ArrayList(this.cookies);
      return localArrayList;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public String toString()
  {
    try
    {
      String str = this.cookies.toString();
      return str;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}