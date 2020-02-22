package org.apache.http.util;

import java.lang.reflect.Method;

public final class ExceptionUtils
{
  private static final Method INIT_CAUSE_METHOD = getInitCauseMethod();

  private static Method getInitCauseMethod()
  {
    try
    {
      if (class$java$lang$Throwable == null)
      {
        localObject = class$("java.lang.Throwable");
        class$java$lang$Throwable = (Class)localObject;
      }
      else
      {
        localObject = class$java$lang$Throwable;
      }
      Class localClass;
      if (class$java$lang$Throwable == null)
      {
        localClass = class$("java.lang.Throwable");
        class$java$lang$Throwable = localClass;
      }
      else
      {
        localClass = class$java$lang$Throwable;
      }
      Object localObject = localClass.getMethod("initCause", new Class[] { localObject });
      return localObject;
      label63: return null;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      break label63;
    }
  }

  public static void initCause(Throwable paramThrowable1, Throwable paramThrowable2)
  {
    if (INIT_CAUSE_METHOD != null);
    try
    {
      INIT_CAUSE_METHOD.invoke(paramThrowable1, new Object[] { paramThrowable2 });
      return;
    }
    catch (Exception paramThrowable1)
    {
    }
  }
}