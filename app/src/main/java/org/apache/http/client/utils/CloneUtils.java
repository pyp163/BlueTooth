package org.apache.http.client.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.http.annotation.Immutable;

@Immutable
public class CloneUtils
{
  public static Object clone(Object paramObject)
    throws CloneNotSupportedException
  {
    if (paramObject == null)
      return null;
    if ((paramObject instanceof Cloneable))
    {
      Object localObject = paramObject.getClass();
      try
      {
        localObject = ((Class)localObject).getMethod("clone", (Class[])null);
        try
        {
          paramObject = ((Method)localObject).invoke(paramObject, (Object[])null);
          return paramObject;
        }
        catch (IllegalAccessException paramObject)
        {
          throw new IllegalAccessError(paramObject.getMessage());
        }
        catch (InvocationTargetException paramObject)
        {
          paramObject = paramObject.getCause();
          if ((paramObject instanceof CloneNotSupportedException))
            throw ((CloneNotSupportedException)paramObject);
          throw new Error("Unexpected exception", paramObject);
        }
      }
      catch (NoSuchMethodException paramObject)
      {
        throw new NoSuchMethodError(paramObject.getMessage());
      }
    }
    throw new CloneNotSupportedException();
  }
}