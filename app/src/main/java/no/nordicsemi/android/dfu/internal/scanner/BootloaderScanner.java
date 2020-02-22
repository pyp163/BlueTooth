package no.nordicsemi.android.dfu.internal.scanner;

import android.support.annotation.Nullable;

public abstract interface BootloaderScanner
{
  public static final int ADDRESS_DIFF = 1;
  public static final long TIMEOUT = 5000L;

  @Nullable
  public abstract String searchFor(String paramString);
}