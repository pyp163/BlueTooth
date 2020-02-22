package com.google.gson.internal;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public abstract class UnsafeAllocator
{
  static void assertInstantiable(Class<?> paramClass)
  {
    int i = paramClass.getModifiers();
    StringBuilder localStringBuilder;
    if (Modifier.isInterface(i))
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Interface can't be instantiated! Interface name: ");
      localStringBuilder.append(paramClass.getName());
      throw new UnsupportedOperationException(localStringBuilder.toString());
    }
    if (Modifier.isAbstract(i))
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Abstract class can't be instantiated! Class name: ");
      localStringBuilder.append(paramClass.getName());
      throw new UnsupportedOperationException(localStringBuilder.toString());
    }
  }

  public static UnsafeAllocator create()
  {
    try
    {
      localObject1 = Class.forName("sun.misc.Unsafe");
      final Object localObject2 = ((Class)localObject1).getDeclaredField("theUnsafe");
      ((Field)localObject2).setAccessible(true);
      localObject2 = ((Field)localObject2).get(null);
      localObject1 = new UnsafeAllocator()
      {
        public <T> T newInstance(Class<T> paramAnonymousClass)
          throws Exception
        {
          assertInstantiable(paramAnonymousClass);
          return this.val$allocateInstance.invoke(localObject2, new Object[] { paramAnonymousClass });
        }
      };
      return localObject1;
    }
    catch (Exception localException2)
    {
      try
      {
        localObject1 = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", new Class[] { Class.class });
        ((Method)localObject1).setAccessible(true);
        final int i = ((Integer)((Method)localObject1).invoke(null, new Object[] { Object.class })).intValue();
        localObject1 = ObjectStreamClass.class.getDeclaredMethod("newInstance", new Class[] { Class.class, Integer.TYPE });
        ((Method)localObject1).setAccessible(true);
        localObject1 = new UnsafeAllocator()
        {
          public <T> T newInstance(Class<T> paramAnonymousClass)
            throws Exception
          {
            assertInstantiable(paramAnonymousClass);
            return this.val$newInstance.invoke(null, new Object[] { paramAnonymousClass, Integer.valueOf(i) });
          }
        };
        return localObject1;
      }
      catch (Exception localException2)
      {
        try
        {
          while (true)
          {
            Object localObject1 = ObjectInputStream.class.getDeclaredMethod("newInstance", new Class[] { Class.class, Class.class });
            ((Method)localObject1).setAccessible(true);
            localObject1 = new UnsafeAllocator()
            {
              public <T> T newInstance(Class<T> paramAnonymousClass)
                throws Exception
              {
                assertInstantiable(paramAnonymousClass);
                return this.val$newInstance.invoke(null, new Object[] { paramAnonymousClass, Object.class });
              }
            };
            return localObject1;
            label171: return new UnsafeAllocator()
            {
              public <T> T newInstance(Class<T> paramAnonymousClass)
              {
                StringBuilder localStringBuilder = new StringBuilder();
                localStringBuilder.append("Cannot allocate ");
                localStringBuilder.append(paramAnonymousClass);
                throw new UnsupportedOperationException(localStringBuilder.toString());
              }
            };
            localException1 = localException1;
            continue;
            localException2 = localException2;
          }
        }
        catch (Exception localException3)
        {
          break label171;
        }
      }
    }
  }

  public abstract <T> T newInstance(Class<T> paramClass)
    throws Exception;
}