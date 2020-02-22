package jonathanfinerty.once;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import java.util.concurrent.ExecutionException;

class AsyncSharedPreferenceLoader
{
  private final AsyncTask<String, Void, SharedPreferences> asyncTask = new AsyncTask()
  {
    protected SharedPreferences doInBackground(String[] paramAnonymousArrayOfString)
    {
      return AsyncSharedPreferenceLoader.this.context.getSharedPreferences(paramAnonymousArrayOfString[0], 0);
    }
  };
  private final Context context;

  public AsyncSharedPreferenceLoader(Context paramContext, String paramString)
  {
    this.context = paramContext;
    this.asyncTask.execute(new String[] { paramString });
  }

  public SharedPreferences get()
  {
    try
    {
      SharedPreferences localSharedPreferences = (SharedPreferences)this.asyncTask.get();
      return localSharedPreferences;
      label13: return null;
    }
    catch (InterruptedException|ExecutionException localInterruptedException)
    {
      break label13;
    }
  }
}