package jonathanfinerty.once;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Once
{
  public static final int THIS_APP_INSTALL = 0;
  public static final int THIS_APP_VERSION = 1;
  private static long lastAppUpdatedTime = -1L;
  private static PersistedMap tagLastSeenMap;
  private static PersistedSet toDoSet;

  public static boolean beenDone(int paramInt, String paramString)
  {
    return beenDone(paramInt, paramString, Amount.moreThan(0));
  }

  public static boolean beenDone(int paramInt, String paramString, CountChecker paramCountChecker)
  {
    paramString = tagLastSeenMap.get(paramString);
    boolean bool = paramString.isEmpty();
    int i = 0;
    if (bool)
      return false;
    if (paramInt == 0)
      return paramCountChecker.check(paramString.size());
    paramString = paramString.iterator();
    paramInt = i;
    while (paramString.hasNext())
      if (((Long)paramString.next()).longValue() > lastAppUpdatedTime)
        paramInt += 1;
    return paramCountChecker.check(paramInt);
  }

  public static boolean beenDone(long paramLong, String paramString)
  {
    return beenDone(paramLong, paramString, Amount.moreThan(0));
  }

  public static boolean beenDone(long paramLong, String paramString, CountChecker paramCountChecker)
  {
    paramString = tagLastSeenMap.get(paramString);
    boolean bool = paramString.isEmpty();
    int i = 0;
    if (bool)
      return false;
    paramString = paramString.iterator();
    while (paramString.hasNext())
    {
      Long localLong = (Long)paramString.next();
      long l = new Date().getTime();
      if (localLong.longValue() > l - paramLong)
        i += 1;
    }
    return paramCountChecker.check(i);
  }

  public static boolean beenDone(String paramString)
  {
    return beenDone(0, paramString, Amount.moreThan(0));
  }

  public static boolean beenDone(String paramString, CountChecker paramCountChecker)
  {
    return beenDone(0, paramString, paramCountChecker);
  }

  public static boolean beenDone(TimeUnit paramTimeUnit, long paramLong, String paramString)
  {
    return beenDone(paramTimeUnit, paramLong, paramString, Amount.moreThan(0));
  }

  public static boolean beenDone(TimeUnit paramTimeUnit, long paramLong, String paramString, CountChecker paramCountChecker)
  {
    return beenDone(paramTimeUnit.toMillis(paramLong), paramString, paramCountChecker);
  }

  public static void clearAll()
  {
    tagLastSeenMap.clear();
  }

  public static void clearAllToDos()
  {
    toDoSet.clear();
  }

  public static void clearDone(String paramString)
  {
    tagLastSeenMap.remove(paramString);
  }

  public static void clearToDo(String paramString)
  {
    toDoSet.remove(paramString);
  }

  public static void initialise(Context paramContext)
  {
    if (tagLastSeenMap == null)
      tagLastSeenMap = new PersistedMap(paramContext, "TagLastSeenMap");
    if (toDoSet == null)
      toDoSet = new PersistedSet(paramContext, "ToDoSet");
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      lastAppUpdatedTime = localPackageManager.getPackageInfo(paramContext.getPackageName(), 0).lastUpdateTime;
      return;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
    }
  }

  public static void markDone(String paramString)
  {
    tagLastSeenMap.put(paramString, new Date().getTime());
    toDoSet.remove(paramString);
  }

  public static boolean needToDo(String paramString)
  {
    return toDoSet.contains(paramString);
  }

  public static void toDo(int paramInt, String paramString)
  {
    Object localObject = tagLastSeenMap.get(paramString);
    if (((List)localObject).isEmpty())
    {
      toDoSet.put(paramString);
      return;
    }
    localObject = (Long)((List)localObject).get(((List)localObject).size() - 1);
    if ((paramInt == 1) && (((Long)localObject).longValue() <= lastAppUpdatedTime))
      toDoSet.put(paramString);
  }

  public static void toDo(String paramString)
  {
    toDoSet.put(paramString);
  }

  @Retention(RetentionPolicy.SOURCE)
  public static @interface Scope
  {
  }
}