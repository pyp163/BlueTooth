package android.support.transition;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorListenerAdapter;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import java.util.ArrayList;

class AnimatorUtils
{
  static void addPauseListener(@NonNull Animator paramAnimator, @NonNull AnimatorListenerAdapter paramAnimatorListenerAdapter)
  {
    if (Build.VERSION.SDK_INT >= 19)
      paramAnimator.addPauseListener(paramAnimatorListenerAdapter);
  }

  static void pause(@NonNull Animator paramAnimator)
  {
    if (Build.VERSION.SDK_INT >= 19)
    {
      paramAnimator.pause();
      return;
    }
    ArrayList localArrayList = paramAnimator.getListeners();
    if (localArrayList != null)
    {
      int i = 0;
      int j = localArrayList.size();
      while (i < j)
      {
        Animator.AnimatorListener localAnimatorListener = (Animator.AnimatorListener)localArrayList.get(i);
        if ((localAnimatorListener instanceof AnimatorPauseListenerCompat))
          ((AnimatorPauseListenerCompat)localAnimatorListener).onAnimationPause(paramAnimator);
        i += 1;
      }
    }
  }

  static void resume(@NonNull Animator paramAnimator)
  {
    if (Build.VERSION.SDK_INT >= 19)
    {
      paramAnimator.resume();
      return;
    }
    ArrayList localArrayList = paramAnimator.getListeners();
    if (localArrayList != null)
    {
      int i = 0;
      int j = localArrayList.size();
      while (i < j)
      {
        Animator.AnimatorListener localAnimatorListener = (Animator.AnimatorListener)localArrayList.get(i);
        if ((localAnimatorListener instanceof AnimatorPauseListenerCompat))
          ((AnimatorPauseListenerCompat)localAnimatorListener).onAnimationResume(paramAnimator);
        i += 1;
      }
    }
  }

  static abstract interface AnimatorPauseListenerCompat
  {
    public abstract void onAnimationPause(Animator paramAnimator);

    public abstract void onAnimationResume(Animator paramAnimator);
  }
}