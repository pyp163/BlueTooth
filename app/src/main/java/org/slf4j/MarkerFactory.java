package org.slf4j;

import org.slf4j.helpers.BasicMarkerFactory;
import org.slf4j.helpers.Util;
import org.slf4j.impl.StaticMarkerBinder;

public class MarkerFactory
{
  static IMarkerFactory markerFactory;

  static
  {
    try
    {
      markerFactory = StaticMarkerBinder.SINGLETON.getMarkerFactory();
      return;
    }
    catch (Exception localException)
    {
      Util.report("Unexpected failure while binding MarkerFactory", localException);
      return;
      markerFactory = new BasicMarkerFactory();
      return;
    }
    catch (NoClassDefFoundError localNoClassDefFoundError)
    {
      label18: break label18;
    }
  }

  public static Marker getDetachedMarker(String paramString)
  {
    return markerFactory.getDetachedMarker(paramString);
  }

  public static IMarkerFactory getIMarkerFactory()
  {
    return markerFactory;
  }

  public static Marker getMarker(String paramString)
  {
    return markerFactory.getMarker(paramString);
  }
}