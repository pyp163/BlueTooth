package org.slf4j.helpers;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import org.slf4j.Marker;

public class BasicMarker
  implements Marker
{
  private static String CLOSE = " ]";
  private static String OPEN = "[ ";
  private static String SEP = ", ";
  private static final long serialVersionUID = 1803952589649545191L;
  private final String name;
  private List refereceList;

  BasicMarker(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("A marker name cannot be null");
    this.name = paramString;
  }

  public void add(Marker paramMarker)
  {
    if (paramMarker == null);
    try
    {
      throw new IllegalArgumentException("A null value cannot be added to a Marker as reference.");
      boolean bool = contains(paramMarker);
      if (bool)
        return;
      bool = paramMarker.contains(this);
      if (bool)
        return;
      if (this.refereceList == null)
        this.refereceList = new Vector();
      this.refereceList.add(paramMarker);
      return;
      label76: throw paramMarker;
    }
    finally
    {
      break label76;
    }
  }

  public boolean contains(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Other cannot be null");
    if (this.name.equals(paramString))
      return true;
    if (hasReferences())
    {
      int i = 0;
      while (i < this.refereceList.size())
      {
        if (((Marker)this.refereceList.get(i)).contains(paramString))
          return true;
        i += 1;
      }
    }
    return false;
  }

  public boolean contains(Marker paramMarker)
  {
    if (paramMarker == null)
      throw new IllegalArgumentException("Other cannot be null");
    if (equals(paramMarker))
      return true;
    if (hasReferences())
    {
      int i = 0;
      while (i < this.refereceList.size())
      {
        if (((Marker)this.refereceList.get(i)).contains(paramMarker))
          return true;
        i += 1;
      }
    }
    return false;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject)
      return true;
    if (paramObject == null)
      return false;
    if (!(paramObject instanceof Marker))
      return false;
    paramObject = (Marker)paramObject;
    return this.name.equals(paramObject.getName());
  }

  public String getName()
  {
    return this.name;
  }

  public boolean hasChildren()
  {
    return hasReferences();
  }

  public boolean hasReferences()
  {
    try
    {
      if (this.refereceList != null)
      {
        int i = this.refereceList.size();
        if (i > 0)
        {
          bool = true;
          break label30;
        }
      }
      boolean bool = false;
      label30: return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int hashCode()
  {
    return this.name.hashCode();
  }

  public Iterator iterator()
  {
    try
    {
      if (this.refereceList != null)
      {
        localIterator = this.refereceList.iterator();
        return localIterator;
      }
      Iterator localIterator = Collections.EMPTY_LIST.iterator();
      return localIterator;
    }
    finally
    {
    }
  }

  public boolean remove(Marker paramMarker)
  {
    try
    {
      List localList = this.refereceList;
      if (localList == null)
        return false;
      int j = this.refereceList.size();
      int i = 0;
      while (i < j)
      {
        if (paramMarker.equals((Marker)this.refereceList.get(i)))
        {
          this.refereceList.remove(i);
          return true;
        }
        i += 1;
      }
      return false;
    }
    finally
    {
    }
    throw paramMarker;
  }

  public String toString()
  {
    if (!hasReferences())
      return getName();
    Iterator localIterator = iterator();
    StringBuffer localStringBuffer = new StringBuffer(getName());
    localStringBuffer.append(' ');
    localStringBuffer.append(OPEN);
    while (localIterator.hasNext())
    {
      localStringBuffer.append(((Marker)localIterator.next()).getName());
      if (localIterator.hasNext())
        localStringBuffer.append(SEP);
    }
    localStringBuffer.append(CLOSE);
    return localStringBuffer.toString();
  }
}