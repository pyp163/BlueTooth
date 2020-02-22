package com.facebook.imagepipeline.request;

public abstract class BaseRepeatedPostProcessor extends BasePostprocessor
  implements RepeatedPostprocessor
{
  private RepeatedPostprocessorRunner mCallback;

  private RepeatedPostprocessorRunner getCallback()
  {
    try
    {
      RepeatedPostprocessorRunner localRepeatedPostprocessorRunner = this.mCallback;
      return localRepeatedPostprocessorRunner;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setCallback(RepeatedPostprocessorRunner paramRepeatedPostprocessorRunner)
  {
    try
    {
      this.mCallback = paramRepeatedPostprocessorRunner;
      return;
    }
    finally
    {
      paramRepeatedPostprocessorRunner = finally;
    }
    throw paramRepeatedPostprocessorRunner;
  }

  public void update()
  {
    RepeatedPostprocessorRunner localRepeatedPostprocessorRunner = getCallback();
    if (localRepeatedPostprocessorRunner != null)
      localRepeatedPostprocessorRunner.update();
  }
}