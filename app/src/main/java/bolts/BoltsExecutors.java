package bolts;

import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

final class BoltsExecutors
{
  private static final BoltsExecutors INSTANCE = new BoltsExecutors();
  private final ExecutorService background;
  private final Executor immediate;
  private final ScheduledExecutorService scheduled;

  private BoltsExecutors()
  {
    ExecutorService localExecutorService;
    if (!isAndroidRuntime())
      localExecutorService = Executors.newCachedThreadPool();
    else
      localExecutorService = AndroidExecutors.newCachedThreadPool();
    this.background = localExecutorService;
    this.scheduled = Executors.newSingleThreadScheduledExecutor();
    this.immediate = new ImmediateExecutor(null);
  }

  public static ExecutorService background()
  {
    return INSTANCE.background;
  }

  static Executor immediate()
  {
    return INSTANCE.immediate;
  }

  private static boolean isAndroidRuntime()
  {
    String str = System.getProperty("java.runtime.name");
    if (str == null)
      return false;
    return str.toLowerCase(Locale.US).contains("android");
  }

  static ScheduledExecutorService scheduled()
  {
    return INSTANCE.scheduled;
  }

  private static class ImmediateExecutor
    implements Executor
  {
    private static final int MAX_DEPTH = 15;
    private ThreadLocal<Integer> executionDepth = new ThreadLocal();

    private int decrementDepth()
    {
      Integer localInteger2 = (Integer)this.executionDepth.get();
      Integer localInteger1 = localInteger2;
      if (localInteger2 == null)
        localInteger1 = Integer.valueOf(0);
      int i = localInteger1.intValue() - 1;
      if (i == 0)
      {
        this.executionDepth.remove();
        return i;
      }
      this.executionDepth.set(Integer.valueOf(i));
      return i;
    }

    private int incrementDepth()
    {
      Integer localInteger2 = (Integer)this.executionDepth.get();
      Integer localInteger1 = localInteger2;
      if (localInteger2 == null)
        localInteger1 = Integer.valueOf(0);
      int i = localInteger1.intValue() + 1;
      this.executionDepth.set(Integer.valueOf(i));
      return i;
    }

    public void execute(Runnable paramRunnable)
    {
      if (incrementDepth() <= 15);
      try
      {
        paramRunnable.run();
        break label27;
        BoltsExecutors.background().execute(paramRunnable);
        label27: decrementDepth();
        return;
        label33: decrementDepth();
        throw paramRunnable;
      }
      finally
      {
        break label33;
      }
    }
  }
}