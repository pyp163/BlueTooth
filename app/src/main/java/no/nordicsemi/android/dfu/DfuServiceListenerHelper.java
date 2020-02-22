package no.nordicsemi.android.dfu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import no.nordicsemi.android.error.GattError;

public class DfuServiceListenerHelper
{
  private static LogBroadcastReceiver mLogBroadcastReceiver;
  private static ProgressBroadcastsReceiver mProgressBroadcastReceiver;

  private static String getIncrementedAddress(String paramString)
  {
    String str = paramString.substring(0, 15);
    paramString = paramString.substring(15);
    paramString = String.format(Locale.US, "%02X", new Object[] { Integer.valueOf(Integer.valueOf(paramString, 16).intValue() + 1 & 0xFF) });
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(str);
    localStringBuilder.append(paramString);
    return localStringBuilder.toString();
  }

  public static void registerLogListener(Context paramContext, DfuLogListener paramDfuLogListener)
  {
    if (mLogBroadcastReceiver == null)
    {
      mLogBroadcastReceiver = new LogBroadcastReceiver(null);
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("no.nordicsemi.android.dfu.broadcast.BROADCAST_LOG");
      LocalBroadcastManager.getInstance(paramContext).registerReceiver(mLogBroadcastReceiver, localIntentFilter);
    }
    mLogBroadcastReceiver.setLogListener(paramDfuLogListener);
  }

  public static void registerLogListener(Context paramContext, DfuLogListener paramDfuLogListener, String paramString)
  {
    if (mLogBroadcastReceiver == null)
    {
      mLogBroadcastReceiver = new LogBroadcastReceiver(null);
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("no.nordicsemi.android.dfu.broadcast.BROADCAST_LOG");
      LocalBroadcastManager.getInstance(paramContext).registerReceiver(mLogBroadcastReceiver, localIntentFilter);
    }
    mLogBroadcastReceiver.setLogListener(paramString, paramDfuLogListener);
  }

  public static void registerProgressListener(Context paramContext, DfuProgressListener paramDfuProgressListener)
  {
    if (mProgressBroadcastReceiver == null)
    {
      mProgressBroadcastReceiver = new ProgressBroadcastsReceiver(null);
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("no.nordicsemi.android.dfu.broadcast.BROADCAST_PROGRESS");
      localIntentFilter.addAction("no.nordicsemi.android.dfu.broadcast.BROADCAST_ERROR");
      LocalBroadcastManager.getInstance(paramContext).registerReceiver(mProgressBroadcastReceiver, localIntentFilter);
    }
    mProgressBroadcastReceiver.setProgressListener(paramDfuProgressListener);
  }

  public static void registerProgressListener(Context paramContext, DfuProgressListener paramDfuProgressListener, String paramString)
  {
    if (mProgressBroadcastReceiver == null)
    {
      mProgressBroadcastReceiver = new ProgressBroadcastsReceiver(null);
      IntentFilter localIntentFilter = new IntentFilter();
      localIntentFilter.addAction("no.nordicsemi.android.dfu.broadcast.BROADCAST_PROGRESS");
      localIntentFilter.addAction("no.nordicsemi.android.dfu.broadcast.BROADCAST_ERROR");
      LocalBroadcastManager.getInstance(paramContext).registerReceiver(mProgressBroadcastReceiver, localIntentFilter);
    }
    mProgressBroadcastReceiver.setProgressListener(paramString, paramDfuProgressListener);
  }

  public static void unregisterLogListener(Context paramContext, DfuLogListener paramDfuLogListener)
  {
    if ((mLogBroadcastReceiver != null) && (mLogBroadcastReceiver.removeLogListener(paramDfuLogListener)))
    {
      LocalBroadcastManager.getInstance(paramContext).unregisterReceiver(mLogBroadcastReceiver);
      mLogBroadcastReceiver = null;
    }
  }

  public static void unregisterProgressListener(Context paramContext, DfuProgressListener paramDfuProgressListener)
  {
    if ((mProgressBroadcastReceiver != null) && (mProgressBroadcastReceiver.removeProgressListener(paramDfuProgressListener)))
    {
      LocalBroadcastManager.getInstance(paramContext).unregisterReceiver(mProgressBroadcastReceiver);
      mProgressBroadcastReceiver = null;
    }
  }

  private static class LogBroadcastReceiver extends BroadcastReceiver
  {
    private DfuLogListener mGlobalLogListener;
    private Map<String, DfuLogListener> mListeners = new HashMap();

    private boolean removeLogListener(DfuLogListener paramDfuLogListener)
    {
      if (this.mGlobalLogListener == paramDfuLogListener)
        this.mGlobalLogListener = null;
      Iterator localIterator = this.mListeners.entrySet().iterator();
      Map.Entry localEntry;
      while (localIterator.hasNext())
      {
        localEntry = (Map.Entry)localIterator.next();
        if (localEntry.getValue() == paramDfuLogListener)
          this.mListeners.remove(localEntry.getKey());
      }
      localIterator = this.mListeners.entrySet().iterator();
      while (localIterator.hasNext())
      {
        localEntry = (Map.Entry)localIterator.next();
        if (localEntry.getValue() == paramDfuLogListener)
          this.mListeners.remove(localEntry.getKey());
      }
      return (this.mGlobalLogListener == null) && (this.mListeners.isEmpty());
    }

    private void setLogListener(String paramString, DfuLogListener paramDfuLogListener)
    {
      this.mListeners.put(paramString, paramDfuLogListener);
      this.mListeners.put(DfuServiceListenerHelper.getIncrementedAddress(paramString), paramDfuLogListener);
    }

    private void setLogListener(DfuLogListener paramDfuLogListener)
    {
      this.mGlobalLogListener = paramDfuLogListener;
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      paramContext = paramIntent.getStringExtra("no.nordicsemi.android.dfu.extra.EXTRA_DEVICE_ADDRESS");
      DfuLogListener localDfuLogListener1 = this.mGlobalLogListener;
      DfuLogListener localDfuLogListener2 = (DfuLogListener)this.mListeners.get(paramContext);
      if ((localDfuLogListener1 == null) && (localDfuLogListener2 == null))
        return;
      int i = paramIntent.getIntExtra("no.nordicsemi.android.dfu.extra.EXTRA_LOG_LEVEL", 0);
      paramIntent = paramIntent.getStringExtra("no.nordicsemi.android.dfu.extra.EXTRA_LOG_INFO");
      if (localDfuLogListener1 != null)
        localDfuLogListener1.onLogEvent(paramContext, i, paramIntent);
      if (localDfuLogListener2 != null)
        localDfuLogListener2.onLogEvent(paramContext, i, paramIntent);
    }
  }

  private static class ProgressBroadcastsReceiver extends BroadcastReceiver
  {
    private DfuProgressListener mGlobalProgressListener;
    private Map<String, DfuProgressListener> mListeners = new HashMap();

    private boolean removeProgressListener(DfuProgressListener paramDfuProgressListener)
    {
      if (this.mGlobalProgressListener == paramDfuProgressListener)
        this.mGlobalProgressListener = null;
      Iterator localIterator = this.mListeners.entrySet().iterator();
      Map.Entry localEntry;
      while (localIterator.hasNext())
      {
        localEntry = (Map.Entry)localIterator.next();
        if (localEntry.getValue() == paramDfuProgressListener)
          this.mListeners.remove(localEntry.getKey());
      }
      localIterator = this.mListeners.entrySet().iterator();
      while (localIterator.hasNext())
      {
        localEntry = (Map.Entry)localIterator.next();
        if (localEntry.getValue() == paramDfuProgressListener)
          this.mListeners.remove(localEntry.getKey());
      }
      return (this.mGlobalProgressListener == null) && (this.mListeners.isEmpty());
    }

    private void setProgressListener(String paramString, DfuProgressListener paramDfuProgressListener)
    {
      this.mListeners.put(paramString, paramDfuProgressListener);
      this.mListeners.put(DfuServiceListenerHelper.getIncrementedAddress(paramString), paramDfuProgressListener);
    }

    private void setProgressListener(DfuProgressListener paramDfuProgressListener)
    {
      this.mGlobalProgressListener = paramDfuProgressListener;
    }

    public void onReceive(Context paramContext, Intent paramIntent)
    {
      paramContext = paramIntent.getStringExtra("no.nordicsemi.android.dfu.extra.EXTRA_DEVICE_ADDRESS");
      DfuProgressListener localDfuProgressListener1 = this.mGlobalProgressListener;
      DfuProgressListener localDfuProgressListener2 = (DfuProgressListener)this.mListeners.get(paramContext);
      if ((localDfuProgressListener1 == null) && (localDfuProgressListener2 == null))
        return;
      String str = paramIntent.getAction();
      int i = -1;
      int j = str.hashCode();
      if (j != -2021868104)
      {
        if ((j == -1282379203) && (str.equals("no.nordicsemi.android.dfu.broadcast.BROADCAST_ERROR")))
          i = 1;
      }
      else if (str.equals("no.nordicsemi.android.dfu.broadcast.BROADCAST_PROGRESS"))
        i = 0;
      switch (i)
      {
      default:
      case 1:
        i = paramIntent.getIntExtra("no.nordicsemi.android.dfu.extra.EXTRA_DATA", 0);
        j = paramIntent.getIntExtra("no.nordicsemi.android.dfu.extra.EXTRA_ERROR_TYPE", 0);
        if (localDfuProgressListener1 != null)
          localDfuProgressListener1.onDeviceDisconnected(paramContext);
        if (localDfuProgressListener2 != null)
          localDfuProgressListener2.onDeviceDisconnected(paramContext);
        if (j != 1)
        {
          if (j != 3)
          {
            if (localDfuProgressListener1 != null)
              localDfuProgressListener1.onError(paramContext, i, j, GattError.parse(i));
            if (localDfuProgressListener2 != null)
              localDfuProgressListener2.onError(paramContext, i, j, GattError.parse(i));
          }
          else
          {
            if (localDfuProgressListener1 != null)
              localDfuProgressListener1.onError(paramContext, i, j, GattError.parseDfuRemoteError(i));
            if (localDfuProgressListener2 != null)
              localDfuProgressListener2.onError(paramContext, i, j, GattError.parseDfuRemoteError(i));
          }
        }
        else
        {
          if (localDfuProgressListener1 != null)
            localDfuProgressListener1.onError(paramContext, i, j, GattError.parseConnectionError(i));
          if (localDfuProgressListener2 != null)
          {
            localDfuProgressListener2.onError(paramContext, i, j, GattError.parseConnectionError(i));
            return;
          }
        }
        break;
      case 0:
        i = paramIntent.getIntExtra("no.nordicsemi.android.dfu.extra.EXTRA_DATA", 0);
        float f1 = paramIntent.getFloatExtra("no.nordicsemi.android.dfu.extra.EXTRA_SPEED_B_PER_MS", 0.0F);
        float f2 = paramIntent.getFloatExtra("no.nordicsemi.android.dfu.extra.EXTRA_AVG_SPEED_B_PER_MS", 0.0F);
        j = paramIntent.getIntExtra("no.nordicsemi.android.dfu.extra.EXTRA_PART_CURRENT", 0);
        int k = paramIntent.getIntExtra("no.nordicsemi.android.dfu.extra.EXTRA_PARTS_TOTAL", 0);
        switch (i)
        {
        default:
          if (i != 0)
            break label675;
          if (localDfuProgressListener1 != null)
            localDfuProgressListener1.onDfuProcessStarted(paramContext);
          break;
        case -1:
          if (localDfuProgressListener1 != null)
            localDfuProgressListener1.onDeviceConnecting(paramContext);
          if (localDfuProgressListener2 == null)
            return;
          localDfuProgressListener2.onDeviceConnecting(paramContext);
          return;
        case -2:
          if (localDfuProgressListener1 != null)
          {
            localDfuProgressListener1.onDeviceConnected(paramContext);
            localDfuProgressListener1.onDfuProcessStarting(paramContext);
          }
          if (localDfuProgressListener2 == null)
            return;
          localDfuProgressListener2.onDeviceConnected(paramContext);
          localDfuProgressListener2.onDfuProcessStarting(paramContext);
          return;
        case -3:
          if (localDfuProgressListener1 != null)
            localDfuProgressListener1.onEnablingDfuMode(paramContext);
          if (localDfuProgressListener2 == null)
            return;
          localDfuProgressListener2.onEnablingDfuMode(paramContext);
          return;
        case -4:
          if (localDfuProgressListener1 != null)
            localDfuProgressListener1.onFirmwareValidating(paramContext);
          if (localDfuProgressListener2 == null)
            return;
          localDfuProgressListener2.onFirmwareValidating(paramContext);
          return;
        case -5:
          if (localDfuProgressListener1 != null)
            localDfuProgressListener1.onDeviceDisconnecting(paramContext);
          if (localDfuProgressListener2 == null)
            return;
          localDfuProgressListener2.onDeviceDisconnecting(paramContext);
          return;
        case -6:
          if (localDfuProgressListener1 != null)
          {
            localDfuProgressListener1.onDeviceDisconnected(paramContext);
            localDfuProgressListener1.onDfuCompleted(paramContext);
          }
          if (localDfuProgressListener2 == null)
            return;
          localDfuProgressListener2.onDeviceDisconnected(paramContext);
          localDfuProgressListener2.onDfuCompleted(paramContext);
          return;
        case -7:
          if (localDfuProgressListener1 != null)
          {
            localDfuProgressListener1.onDeviceDisconnected(paramContext);
            localDfuProgressListener1.onDfuAborted(paramContext);
          }
          if (localDfuProgressListener2 == null)
            return;
          localDfuProgressListener2.onDeviceDisconnected(paramContext);
          localDfuProgressListener2.onDfuAborted(paramContext);
          return;
        }
        if (localDfuProgressListener2 != null)
          localDfuProgressListener2.onDfuProcessStarted(paramContext);
        label675: if (localDfuProgressListener1 != null)
          localDfuProgressListener1.onProgressChanged(paramContext, i, f1, f2, j, k);
        if (localDfuProgressListener2 != null)
          localDfuProgressListener2.onProgressChanged(paramContext, i, f1, f2, j, k);
        break;
      }
    }
  }
}