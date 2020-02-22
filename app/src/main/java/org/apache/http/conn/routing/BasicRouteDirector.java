package org.apache.http.conn.routing;

import java.net.InetAddress;
import org.apache.http.HttpHost;
import org.apache.http.annotation.Immutable;

@Immutable
public class BasicRouteDirector
  implements HttpRouteDirector
{
  protected int directStep(RouteInfo paramRouteInfo1, RouteInfo paramRouteInfo2)
  {
    if (paramRouteInfo2.getHopCount() > 1)
      return -1;
    if (!paramRouteInfo1.getTargetHost().equals(paramRouteInfo2.getTargetHost()))
      return -1;
    if (paramRouteInfo1.isSecure() != paramRouteInfo2.isSecure())
      return -1;
    if ((paramRouteInfo1.getLocalAddress() != null) && (!paramRouteInfo1.getLocalAddress().equals(paramRouteInfo2.getLocalAddress())))
      return -1;
    return 0;
  }

  protected int firstStep(RouteInfo paramRouteInfo)
  {
    int j = paramRouteInfo.getHopCount();
    int i = 1;
    if (j > 1)
      i = 2;
    return i;
  }

  public int nextStep(RouteInfo paramRouteInfo1, RouteInfo paramRouteInfo2)
  {
    if (paramRouteInfo1 == null)
      throw new IllegalArgumentException("Planned route may not be null.");
    if ((paramRouteInfo2 != null) && (paramRouteInfo2.getHopCount() >= 1))
    {
      if (paramRouteInfo1.getHopCount() > 1)
        return proxiedStep(paramRouteInfo1, paramRouteInfo2);
      return directStep(paramRouteInfo1, paramRouteInfo2);
    }
    return firstStep(paramRouteInfo1);
  }

  protected int proxiedStep(RouteInfo paramRouteInfo1, RouteInfo paramRouteInfo2)
  {
    if (paramRouteInfo2.getHopCount() <= 1)
      return -1;
    if (!paramRouteInfo1.getTargetHost().equals(paramRouteInfo2.getTargetHost()))
      return -1;
    int j = paramRouteInfo1.getHopCount();
    int k = paramRouteInfo2.getHopCount();
    if (j < k)
      return -1;
    int i = 0;
    while (i < k - 1)
    {
      if (!paramRouteInfo1.getHopTarget(i).equals(paramRouteInfo2.getHopTarget(i)))
        return -1;
      i += 1;
    }
    if (j > k)
      return 4;
    if (((paramRouteInfo2.isTunnelled()) && (!paramRouteInfo1.isTunnelled())) || ((paramRouteInfo2.isLayered()) && (!paramRouteInfo1.isLayered())))
      return -1;
    if ((paramRouteInfo1.isTunnelled()) && (!paramRouteInfo2.isTunnelled()))
      return 3;
    if ((paramRouteInfo1.isLayered()) && (!paramRouteInfo2.isLayered()))
      return 5;
    if (paramRouteInfo1.isSecure() != paramRouteInfo2.isSecure())
      return -1;
    return 0;
  }
}