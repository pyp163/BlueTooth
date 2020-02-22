package com.tencent.mmkv;

public abstract interface MMKVHandler
{
  public abstract void mmkvLog(MMKVLogLevel paramMMKVLogLevel, String paramString1, int paramInt, String paramString2, String paramString3);

  public abstract MMKVRecoverStrategic onMMKVCRCCheckFail(String paramString);

  public abstract MMKVRecoverStrategic onMMKVFileLengthError(String paramString);

  public abstract boolean wantLogRedirecting();
}