package com.facebook.imagepipeline.systrace;

public class FrescoSystrace
{
  public static final ArgsBuilder NO_OP_ARGS_BUILDER = new NoOpArgsBuilder(null);
  private static volatile Systrace sInstance;

  public static void beginSection(String paramString)
  {
    getInstance().beginSection(paramString);
  }

  public static ArgsBuilder beginSectionWithArgs(String paramString)
  {
    return getInstance().beginSectionWithArgs(paramString);
  }

  public static void endSection()
  {
    getInstance().endSection();
  }

  private static Systrace getInstance()
  {
    if (sInstance == null)
      try
      {
        if (sInstance == null)
          sInstance = new DefaultFrescoSystrace();
      }
      finally
      {
      }
    return sInstance;
  }

  public static boolean isTracing()
  {
    return getInstance().isTracing();
  }

  public static void provide(Systrace paramSystrace)
  {
    sInstance = paramSystrace;
  }

  public static abstract interface ArgsBuilder
  {
    public abstract ArgsBuilder arg(String paramString, double paramDouble);

    public abstract ArgsBuilder arg(String paramString, int paramInt);

    public abstract ArgsBuilder arg(String paramString, long paramLong);

    public abstract ArgsBuilder arg(String paramString, Object paramObject);

    public abstract void flush();
  }

  private static final class NoOpArgsBuilder
    implements FrescoSystrace.ArgsBuilder
  {
    public FrescoSystrace.ArgsBuilder arg(String paramString, double paramDouble)
    {
      return this;
    }

    public FrescoSystrace.ArgsBuilder arg(String paramString, int paramInt)
    {
      return this;
    }

    public FrescoSystrace.ArgsBuilder arg(String paramString, long paramLong)
    {
      return this;
    }

    public FrescoSystrace.ArgsBuilder arg(String paramString, Object paramObject)
    {
      return this;
    }

    public void flush()
    {
    }
  }

  public static abstract interface Systrace
  {
    public abstract void beginSection(String paramString);

    public abstract FrescoSystrace.ArgsBuilder beginSectionWithArgs(String paramString);

    public abstract void endSection();

    public abstract boolean isTracing();
  }
}