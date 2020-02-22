package com.nineoldandroids.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class ReflectiveProperty<T, V> extends Property<T, V>
{
  private static final String PREFIX_GET = "get";
  private static final String PREFIX_IS = "is";
  private static final String PREFIX_SET = "set";
  private Field mField;
  private Method mGetter;
  private Method mSetter;

  // ERROR //
  public ReflectiveProperty(Class<T> paramClass, Class<V> paramClass1, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_2
    //   2: aload_3
    //   3: invokespecial 29	com/nineoldandroids/util/Property:<init>	(Ljava/lang/Class;Ljava/lang/String;)V
    //   6: aload_3
    //   7: iconst_0
    //   8: invokevirtual 35	java/lang/String:charAt	(I)C
    //   11: invokestatic 41	java/lang/Character:toUpperCase	(C)C
    //   14: istore 4
    //   16: aload_3
    //   17: iconst_1
    //   18: invokevirtual 45	java/lang/String:substring	(I)Ljava/lang/String;
    //   21: astore 5
    //   23: new 47	java/lang/StringBuilder
    //   26: dup
    //   27: invokespecial 50	java/lang/StringBuilder:<init>	()V
    //   30: astore 6
    //   32: aload 6
    //   34: iload 4
    //   36: invokevirtual 54	java/lang/StringBuilder:append	(C)Ljava/lang/StringBuilder;
    //   39: pop
    //   40: aload 6
    //   42: aload 5
    //   44: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: pop
    //   48: aload 6
    //   50: invokevirtual 61	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   53: astore 5
    //   55: new 47	java/lang/StringBuilder
    //   58: dup
    //   59: invokespecial 50	java/lang/StringBuilder:<init>	()V
    //   62: astore 6
    //   64: aload 6
    //   66: ldc 9
    //   68: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   71: pop
    //   72: aload 6
    //   74: aload 5
    //   76: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: pop
    //   80: aload 6
    //   82: invokevirtual 61	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   85: astore 6
    //   87: aload_0
    //   88: aload_1
    //   89: aload 6
    //   91: aconst_null
    //   92: checkcast 63	[Ljava/lang/Class;
    //   95: invokevirtual 69	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   98: putfield 71	com/nineoldandroids/util/ReflectiveProperty:mGetter	Ljava/lang/reflect/Method;
    //   101: goto +99 -> 200
    //   104: aload_0
    //   105: aload_1
    //   106: aload 6
    //   108: aconst_null
    //   109: checkcast 63	[Ljava/lang/Class;
    //   112: invokevirtual 74	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   115: putfield 71	com/nineoldandroids/util/ReflectiveProperty:mGetter	Ljava/lang/reflect/Method;
    //   118: aload_0
    //   119: getfield 71	com/nineoldandroids/util/ReflectiveProperty:mGetter	Ljava/lang/reflect/Method;
    //   122: iconst_1
    //   123: invokevirtual 80	java/lang/reflect/Method:setAccessible	(Z)V
    //   126: goto +74 -> 200
    //   129: new 47	java/lang/StringBuilder
    //   132: dup
    //   133: invokespecial 50	java/lang/StringBuilder:<init>	()V
    //   136: astore 6
    //   138: aload 6
    //   140: ldc 12
    //   142: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   145: pop
    //   146: aload 6
    //   148: aload 5
    //   150: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   153: pop
    //   154: aload 6
    //   156: invokevirtual 61	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   159: astore 6
    //   161: aload_0
    //   162: aload_1
    //   163: aload 6
    //   165: aconst_null
    //   166: checkcast 63	[Ljava/lang/Class;
    //   169: invokevirtual 69	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   172: putfield 71	com/nineoldandroids/util/ReflectiveProperty:mGetter	Ljava/lang/reflect/Method;
    //   175: goto +25 -> 200
    //   178: aload_0
    //   179: aload_1
    //   180: aload 6
    //   182: aconst_null
    //   183: checkcast 63	[Ljava/lang/Class;
    //   186: invokevirtual 74	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   189: putfield 71	com/nineoldandroids/util/ReflectiveProperty:mGetter	Ljava/lang/reflect/Method;
    //   192: aload_0
    //   193: getfield 71	com/nineoldandroids/util/ReflectiveProperty:mGetter	Ljava/lang/reflect/Method;
    //   196: iconst_1
    //   197: invokevirtual 80	java/lang/reflect/Method:setAccessible	(Z)V
    //   200: aload_0
    //   201: getfield 71	com/nineoldandroids/util/ReflectiveProperty:mGetter	Ljava/lang/reflect/Method;
    //   204: invokevirtual 84	java/lang/reflect/Method:getReturnType	()Ljava/lang/Class;
    //   207: astore_3
    //   208: aload_0
    //   209: aload_2
    //   210: aload_3
    //   211: invokespecial 88	com/nineoldandroids/util/ReflectiveProperty:typesMatch	(Ljava/lang/Class;Ljava/lang/Class;)Z
    //   214: ifne +63 -> 277
    //   217: new 47	java/lang/StringBuilder
    //   220: dup
    //   221: invokespecial 50	java/lang/StringBuilder:<init>	()V
    //   224: astore_1
    //   225: aload_1
    //   226: ldc 90
    //   228: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   231: pop
    //   232: aload_1
    //   233: aload_3
    //   234: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   237: pop
    //   238: aload_1
    //   239: ldc 95
    //   241: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   244: pop
    //   245: aload_1
    //   246: ldc 97
    //   248: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   251: pop
    //   252: aload_1
    //   253: aload_2
    //   254: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   257: pop
    //   258: aload_1
    //   259: ldc 99
    //   261: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   264: pop
    //   265: new 101	com/nineoldandroids/util/NoSuchPropertyException
    //   268: dup
    //   269: aload_1
    //   270: invokevirtual 61	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   273: invokespecial 104	com/nineoldandroids/util/NoSuchPropertyException:<init>	(Ljava/lang/String;)V
    //   276: athrow
    //   277: new 47	java/lang/StringBuilder
    //   280: dup
    //   281: invokespecial 50	java/lang/StringBuilder:<init>	()V
    //   284: astore_2
    //   285: aload_2
    //   286: ldc 15
    //   288: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   291: pop
    //   292: aload_2
    //   293: aload 5
    //   295: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   298: pop
    //   299: aload_2
    //   300: invokevirtual 61	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   303: astore_2
    //   304: aload_0
    //   305: aload_1
    //   306: aload_2
    //   307: iconst_1
    //   308: anewarray 65	java/lang/Class
    //   311: dup
    //   312: iconst_0
    //   313: aload_3
    //   314: aastore
    //   315: invokevirtual 74	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   318: putfield 106	com/nineoldandroids/util/ReflectiveProperty:mSetter	Ljava/lang/reflect/Method;
    //   321: aload_0
    //   322: getfield 106	com/nineoldandroids/util/ReflectiveProperty:mSetter	Ljava/lang/reflect/Method;
    //   325: iconst_1
    //   326: invokevirtual 80	java/lang/reflect/Method:setAccessible	(Z)V
    //   329: return
    //   330: aload_0
    //   331: aload_1
    //   332: aload_3
    //   333: invokevirtual 110	java/lang/Class:getField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   336: putfield 112	com/nineoldandroids/util/ReflectiveProperty:mField	Ljava/lang/reflect/Field;
    //   339: aload_0
    //   340: getfield 112	com/nineoldandroids/util/ReflectiveProperty:mField	Ljava/lang/reflect/Field;
    //   343: invokevirtual 117	java/lang/reflect/Field:getType	()Ljava/lang/Class;
    //   346: astore_1
    //   347: aload_0
    //   348: aload_2
    //   349: aload_1
    //   350: invokespecial 88	com/nineoldandroids/util/ReflectiveProperty:typesMatch	(Ljava/lang/Class;Ljava/lang/Class;)Z
    //   353: ifne +71 -> 424
    //   356: new 47	java/lang/StringBuilder
    //   359: dup
    //   360: invokespecial 50	java/lang/StringBuilder:<init>	()V
    //   363: astore 5
    //   365: aload 5
    //   367: ldc 90
    //   369: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   372: pop
    //   373: aload 5
    //   375: aload_1
    //   376: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   379: pop
    //   380: aload 5
    //   382: ldc 95
    //   384: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   387: pop
    //   388: aload 5
    //   390: ldc 97
    //   392: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   395: pop
    //   396: aload 5
    //   398: aload_2
    //   399: invokevirtual 93	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   402: pop
    //   403: aload 5
    //   405: ldc 99
    //   407: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   410: pop
    //   411: new 101	com/nineoldandroids/util/NoSuchPropertyException
    //   414: dup
    //   415: aload 5
    //   417: invokevirtual 61	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   420: invokespecial 104	com/nineoldandroids/util/NoSuchPropertyException:<init>	(Ljava/lang/String;)V
    //   423: athrow
    //   424: return
    //   425: new 47	java/lang/StringBuilder
    //   428: dup
    //   429: invokespecial 50	java/lang/StringBuilder:<init>	()V
    //   432: astore_1
    //   433: aload_1
    //   434: ldc 119
    //   436: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   439: pop
    //   440: aload_1
    //   441: aload_3
    //   442: invokevirtual 57	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   445: pop
    //   446: new 101	com/nineoldandroids/util/NoSuchPropertyException
    //   449: dup
    //   450: aload_1
    //   451: invokevirtual 61	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   454: invokespecial 104	com/nineoldandroids/util/NoSuchPropertyException:<init>	(Ljava/lang/String;)V
    //   457: athrow
    //   458: astore 7
    //   460: goto -356 -> 104
    //   463: astore 6
    //   465: goto -336 -> 129
    //   468: astore 7
    //   470: goto -292 -> 178
    //   473: astore 5
    //   475: goto -145 -> 330
    //   478: astore_1
    //   479: return
    //   480: astore_1
    //   481: goto -56 -> 425
    //
    // Exception table:
    //   from	to	target	type
    //   87	101	458	java/lang/NoSuchMethodException
    //   104	126	463	java/lang/NoSuchMethodException
    //   161	175	468	java/lang/NoSuchMethodException
    //   178	200	473	java/lang/NoSuchMethodException
    //   304	329	478	java/lang/NoSuchMethodException
    //   330	424	480	java/lang/NoSuchFieldException
  }

  private boolean typesMatch(Class<V> paramClass, Class paramClass1)
  {
    boolean bool2 = true;
    if (paramClass1 != paramClass)
    {
      if (paramClass1.isPrimitive())
      {
        boolean bool1;
        if (paramClass1 == Float.TYPE)
        {
          bool1 = bool2;
          if (paramClass == Float.class);
        }
        else if (paramClass1 == Integer.TYPE)
        {
          bool1 = bool2;
          if (paramClass == Integer.class);
        }
        else if (paramClass1 == Boolean.TYPE)
        {
          bool1 = bool2;
          if (paramClass == Boolean.class);
        }
        else if (paramClass1 == Long.TYPE)
        {
          bool1 = bool2;
          if (paramClass == Long.class);
        }
        else if (paramClass1 == Double.TYPE)
        {
          bool1 = bool2;
          if (paramClass == Double.class);
        }
        else if (paramClass1 == Short.TYPE)
        {
          bool1 = bool2;
          if (paramClass == Short.class);
        }
        else if (paramClass1 == Byte.TYPE)
        {
          bool1 = bool2;
          if (paramClass == Byte.class);
        }
        else
        {
          if ((paramClass1 == Character.TYPE) && (paramClass == Character.class))
            return true;
          bool1 = false;
        }
        return bool1;
      }
      return false;
    }
    return true;
  }

  public V get(T paramT)
  {
    if (this.mGetter != null);
    try
    {
      paramT = this.mGetter.invoke(paramT, (Object[])null);
      return paramT;
    }
    catch (InvocationTargetException paramT)
    {
      throw new RuntimeException(paramT.getCause());
      throw new AssertionError();
      if (this.mField == null);
    }
    catch (IllegalAccessException paramT)
    {
      try
      {
        paramT = this.mField.get(paramT);
        return paramT;
        label61: throw new AssertionError();
        throw new AssertionError();
        paramT = paramT;
      }
      catch (IllegalAccessException paramT)
      {
        break label61;
      }
    }
  }

  public boolean isReadOnly()
  {
    return (this.mSetter == null) && (this.mField == null);
  }

  public void set(T paramT, V paramV)
  {
    if (this.mSetter != null);
    try
    {
      this.mSetter.invoke(paramT, new Object[] { paramV });
      return;
    }
    catch (InvocationTargetException paramT)
    {
      throw new RuntimeException(paramT.getCause());
      throw new AssertionError();
      if (this.mField == null);
    }
    catch (IllegalAccessException paramT)
    {
      try
      {
        this.mField.set(paramT, paramV);
        return;
        label63: throw new AssertionError();
        paramT = new StringBuilder();
        paramT.append("Property ");
        paramT.append(getName());
        paramT.append(" is read-only");
        throw new UnsupportedOperationException(paramT.toString());
        paramT = paramT;
      }
      catch (IllegalAccessException paramT)
      {
        break label63;
      }
    }
  }
}