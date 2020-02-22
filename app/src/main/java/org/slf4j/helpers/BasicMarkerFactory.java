package org.slf4j.helpers;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.IMarkerFactory;
import org.slf4j.Marker;

public class BasicMarkerFactory
  implements IMarkerFactory
{
  Map markerMap = new HashMap();

  public boolean detachMarker(String paramString)
  {
    boolean bool = false;
    if (paramString == null)
      return false;
    if (this.markerMap.remove(paramString) != null)
      bool = true;
    return bool;
  }

  public boolean exists(String paramString)
  {
    if (paramString == null)
      return false;
    try
    {
      boolean bool = this.markerMap.containsKey(paramString);
      return bool;
    }
    finally
    {
      paramString = finally;
    }
    throw paramString;
  }

  public Marker getDetachedMarker(String paramString)
  {
    return new BasicMarker(paramString);
  }

  public Marker getMarker(String paramString)
  {
    if (paramString == null);
    try
    {
      throw new IllegalArgumentException("Marker name cannot be null");
      Marker localMarker = (Marker)this.markerMap.get(paramString);
      Object localObject = localMarker;
      if (localMarker == null)
      {
        localObject = new BasicMarker(paramString);
        this.markerMap.put(paramString, localObject);
      }
      return localObject;
      label61: throw paramString;
    }
    finally
    {
      break label61;
    }
  }
}