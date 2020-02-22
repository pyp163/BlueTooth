package no.nordicsemi.android.dfu;

public abstract interface DfuProgressListener
{
  public abstract void onDeviceConnected(String paramString);

  public abstract void onDeviceConnecting(String paramString);

  public abstract void onDeviceDisconnected(String paramString);

  public abstract void onDeviceDisconnecting(String paramString);

  public abstract void onDfuAborted(String paramString);

  public abstract void onDfuCompleted(String paramString);

  public abstract void onDfuProcessStarted(String paramString);

  public abstract void onDfuProcessStarting(String paramString);

  public abstract void onEnablingDfuMode(String paramString);

  public abstract void onError(String paramString1, int paramInt1, int paramInt2, String paramString2);

  public abstract void onFirmwareValidating(String paramString);

  public abstract void onProgressChanged(String paramString, int paramInt1, float paramFloat1, float paramFloat2, int paramInt2, int paramInt3);
}