package com.google.gson.internal.reflect;

import com.google.gson.internal.JavaVersion;
import java.lang.reflect.AccessibleObject;

public abstract class ReflectionAccessor
{
  private static final ReflectionAccessor instance = (ReflectionAccessor)localObject;

  static
  {
    Object localObject;
    if (JavaVersion.getMajorJavaVersion() < 9)
      localObject = new PreJava9ReflectionAccessor();
    else
      localObject = new UnsafeReflectionAccessor();
  }

  public static ReflectionAccessor getInstance()
  {
    return instance;
  }

  public abstract void makeAccessible(AccessibleObject paramAccessibleObject);
}