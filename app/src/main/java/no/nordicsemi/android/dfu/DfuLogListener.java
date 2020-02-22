package no.nordicsemi.android.dfu;

public abstract interface DfuLogListener
{
  public abstract void onLogEvent(String paramString1, int paramInt, String paramString2);
}