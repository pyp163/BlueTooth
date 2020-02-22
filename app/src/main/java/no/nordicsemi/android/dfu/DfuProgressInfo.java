package no.nordicsemi.android.dfu;

import android.os.SystemClock;
import android.support.annotation.NonNull;

class DfuProgressInfo
{
  private int bytesReceived;
  private int bytesSent;
  private int currentPart;
  private int imageSizeInBytes;
  private int initialBytesSent;
  private int lastBytesSent;
  private long lastProgressTime;
  private final ProgressListener mListener;
  private int maxObjectSizeInBytes;
  private int progress;
  private long timeStart;
  private int totalParts;

  DfuProgressInfo(@NonNull ProgressListener paramProgressListener)
  {
    this.mListener = paramProgressListener;
  }

  void addBytesSent(int paramInt)
  {
    setBytesSent(this.bytesSent + paramInt);
  }

  int getAvailableObjectSizeIsBytes()
  {
    return Math.min(this.imageSizeInBytes - this.bytesSent, this.maxObjectSizeInBytes - this.bytesSent % this.maxObjectSizeInBytes);
  }

  float getAverageSpeed()
  {
    long l = SystemClock.elapsedRealtime();
    if (l - this.timeStart != 0L)
      return (this.bytesSent - this.initialBytesSent) / (float)(l - this.timeStart);
    return 0.0F;
  }

  int getBytesReceived()
  {
    return this.bytesReceived;
  }

  int getBytesSent()
  {
    return this.bytesSent;
  }

  int getCurrentPart()
  {
    return this.currentPart;
  }

  int getImageSizeInBytes()
  {
    return this.imageSizeInBytes;
  }

  int getProgress()
  {
    return this.progress;
  }

  float getSpeed()
  {
    long l = SystemClock.elapsedRealtime();
    float f;
    if (l - this.timeStart != 0L)
      f = (this.bytesSent - this.lastBytesSent) / (float)(l - this.lastProgressTime);
    else
      f = 0.0F;
    this.lastProgressTime = l;
    this.lastBytesSent = this.bytesSent;
    return f;
  }

  int getTotalParts()
  {
    return this.totalParts;
  }

  DfuProgressInfo init(int paramInt1, int paramInt2, int paramInt3)
  {
    this.imageSizeInBytes = paramInt1;
    this.maxObjectSizeInBytes = 2147483647;
    this.currentPart = paramInt2;
    this.totalParts = paramInt3;
    return this;
  }

  boolean isComplete()
  {
    return this.bytesSent == this.imageSizeInBytes;
  }

  boolean isLastPart()
  {
    return this.currentPart == this.totalParts;
  }

  boolean isObjectComplete()
  {
    return this.bytesSent % this.maxObjectSizeInBytes == 0;
  }

  void setBytesReceived(int paramInt)
  {
    this.bytesReceived = paramInt;
  }

  void setBytesSent(int paramInt)
  {
    if (this.timeStart == 0L)
    {
      this.timeStart = SystemClock.elapsedRealtime();
      this.initialBytesSent = paramInt;
    }
    this.bytesSent = paramInt;
    this.progress = ((int)(paramInt * 100.0F / this.imageSizeInBytes));
    this.mListener.updateProgressNotification();
  }

  void setMaxObjectSizeInBytes(int paramInt)
  {
    this.maxObjectSizeInBytes = paramInt;
  }

  public void setProgress(int paramInt)
  {
    this.progress = paramInt;
    this.mListener.updateProgressNotification();
  }

  DfuProgressInfo setTotalPart(int paramInt)
  {
    this.totalParts = paramInt;
    return this;
  }

  static abstract interface ProgressListener
  {
    public abstract void updateProgressNotification();
  }
}