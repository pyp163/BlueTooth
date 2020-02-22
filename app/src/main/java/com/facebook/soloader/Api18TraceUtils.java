package com.facebook.soloader;

import android.annotation.TargetApi;
import android.os.Trace;

@TargetApi(18)
@DoNotOptimize
class Api18TraceUtils
{
  public static void beginTraceSection(String paramString)
  {
    Trace.beginSection(paramString);
  }

  public static void endSection()
  {
    Trace.endSection();
  }
}