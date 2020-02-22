package no.nordicsemi.android.dfu;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

public class DfuServiceController
  implements DfuController
{
  private boolean mAborted;
  private LocalBroadcastManager mBroadcastManager;
  private boolean mPaused;

  DfuServiceController(Context paramContext)
  {
    this.mBroadcastManager = LocalBroadcastManager.getInstance(paramContext);
  }

  public void abort()
  {
    if (!this.mAborted)
    {
      this.mAborted = true;
      this.mPaused = false;
      Intent localIntent = new Intent("no.nordicsemi.android.dfu.broadcast.BROADCAST_ACTION");
      localIntent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_ACTION", 2);
      this.mBroadcastManager.sendBroadcast(localIntent);
    }
  }

  public boolean isAborted()
  {
    return this.mAborted;
  }

  public boolean isPaused()
  {
    return this.mPaused;
  }

  public void pause()
  {
    if ((!this.mAborted) && (!this.mPaused))
    {
      this.mPaused = true;
      Intent localIntent = new Intent("no.nordicsemi.android.dfu.broadcast.BROADCAST_ACTION");
      localIntent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_ACTION", 0);
      this.mBroadcastManager.sendBroadcast(localIntent);
    }
  }

  public void resume()
  {
    if ((!this.mAborted) && (this.mPaused))
    {
      this.mPaused = false;
      Intent localIntent = new Intent("no.nordicsemi.android.dfu.broadcast.BROADCAST_ACTION");
      localIntent.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_ACTION", 1);
      this.mBroadcastManager.sendBroadcast(localIntent);
    }
  }
}