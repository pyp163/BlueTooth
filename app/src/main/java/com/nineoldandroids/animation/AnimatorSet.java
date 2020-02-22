package com.nineoldandroids.animation;

import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public final class AnimatorSet extends Animator
{
  private ValueAnimator mDelayAnim = null;
  private long mDuration = -1L;
  private boolean mNeedsSort = true;
  private HashMap<Animator, Node> mNodeMap = new HashMap();
  private ArrayList<Node> mNodes = new ArrayList();
  private ArrayList<Animator> mPlayingSet = new ArrayList();
  private AnimatorSetListener mSetListener = null;
  private ArrayList<Node> mSortedNodes = new ArrayList();
  private long mStartDelay = 0L;
  private boolean mStarted = false;
  boolean mTerminated = false;

  private void sortNodes()
  {
    Object localObject1;
    int j;
    int i;
    Object localObject2;
    int k;
    int m;
    if (this.mNeedsSort)
    {
      this.mSortedNodes.clear();
      localObject1 = new ArrayList();
      j = this.mNodes.size();
      i = 0;
      while (i < j)
      {
        localObject2 = (Node)this.mNodes.get(i);
        if ((((Node)localObject2).dependencies == null) || (((Node)localObject2).dependencies.size() == 0))
          ((ArrayList)localObject1).add(localObject2);
        i += 1;
      }
      localObject2 = new ArrayList();
      while (((ArrayList)localObject1).size() > 0)
      {
        k = ((ArrayList)localObject1).size();
        i = 0;
        while (i < k)
        {
          Node localNode1 = (Node)((ArrayList)localObject1).get(i);
          this.mSortedNodes.add(localNode1);
          if (localNode1.nodeDependents != null)
          {
            m = localNode1.nodeDependents.size();
            j = 0;
            while (j < m)
            {
              Node localNode2 = (Node)localNode1.nodeDependents.get(j);
              localNode2.nodeDependencies.remove(localNode1);
              if (localNode2.nodeDependencies.size() == 0)
                ((ArrayList)localObject2).add(localNode2);
              j += 1;
            }
          }
          i += 1;
        }
        ((ArrayList)localObject1).clear();
        ((ArrayList)localObject1).addAll((Collection)localObject2);
        ((ArrayList)localObject2).clear();
      }
      this.mNeedsSort = false;
      if (this.mSortedNodes.size() != this.mNodes.size())
        throw new IllegalStateException("Circular dependencies cannot exist in AnimatorSet");
    }
    else
    {
      k = this.mNodes.size();
      i = 0;
      while (i < k)
      {
        localObject1 = (Node)this.mNodes.get(i);
        if ((((Node)localObject1).dependencies != null) && (((Node)localObject1).dependencies.size() > 0))
        {
          m = ((Node)localObject1).dependencies.size();
          j = 0;
          while (j < m)
          {
            localObject2 = (Dependency)((Node)localObject1).dependencies.get(j);
            if (((Node)localObject1).nodeDependencies == null)
              ((Node)localObject1).nodeDependencies = new ArrayList();
            if (!((Node)localObject1).nodeDependencies.contains(((Dependency)localObject2).node))
              ((Node)localObject1).nodeDependencies.add(((Dependency)localObject2).node);
            j += 1;
          }
        }
        ((Node)localObject1).done = false;
        i += 1;
      }
    }
  }

  public void cancel()
  {
    this.mTerminated = true;
    if (isStarted())
    {
      Object localObject1 = null;
      Object localObject2;
      if (this.mListeners != null)
      {
        localObject2 = (ArrayList)this.mListeners.clone();
        Iterator localIterator = ((ArrayList)localObject2).iterator();
        while (true)
        {
          localObject1 = localObject2;
          if (!localIterator.hasNext())
            break;
          ((Animator.AnimatorListener)localIterator.next()).onAnimationCancel(this);
        }
      }
      if ((this.mDelayAnim != null) && (this.mDelayAnim.isRunning()))
      {
        this.mDelayAnim.cancel();
      }
      else if (this.mSortedNodes.size() > 0)
      {
        localObject2 = this.mSortedNodes.iterator();
        while (((Iterator)localObject2).hasNext())
          ((Node)((Iterator)localObject2).next()).animation.cancel();
      }
      if (localObject1 != null)
      {
        localObject1 = ((ArrayList)localObject1).iterator();
        while (((Iterator)localObject1).hasNext())
          ((Animator.AnimatorListener)((Iterator)localObject1).next()).onAnimationEnd(this);
      }
      this.mStarted = false;
    }
  }

  public AnimatorSet clone()
  {
    AnimatorSet localAnimatorSet = (AnimatorSet)super.clone();
    localAnimatorSet.mNeedsSort = true;
    localAnimatorSet.mTerminated = false;
    localAnimatorSet.mStarted = false;
    localAnimatorSet.mPlayingSet = new ArrayList();
    localAnimatorSet.mNodeMap = new HashMap();
    localAnimatorSet.mNodes = new ArrayList();
    localAnimatorSet.mSortedNodes = new ArrayList();
    HashMap localHashMap = new HashMap();
    Object localObject3 = this.mNodes.iterator();
    Object localObject2;
    Object localObject4;
    while (((Iterator)localObject3).hasNext())
    {
      localObject1 = (Node)((Iterator)localObject3).next();
      localObject2 = ((Node)localObject1).clone();
      localHashMap.put(localObject1, localObject2);
      localAnimatorSet.mNodes.add(localObject2);
      localAnimatorSet.mNodeMap.put(((Node)localObject2).animation, localObject2);
      localObject1 = null;
      ((Node)localObject2).dependencies = null;
      ((Node)localObject2).tmpDependencies = null;
      ((Node)localObject2).nodeDependents = null;
      ((Node)localObject2).nodeDependencies = null;
      localObject4 = ((Node)localObject2).animation.getListeners();
      if (localObject4 != null)
      {
        Iterator localIterator = ((ArrayList)localObject4).iterator();
        while (localIterator.hasNext())
        {
          Animator.AnimatorListener localAnimatorListener = (Animator.AnimatorListener)localIterator.next();
          if ((localAnimatorListener instanceof AnimatorSetListener))
          {
            localObject2 = localObject1;
            if (localObject1 == null)
              localObject2 = new ArrayList();
            ((ArrayList)localObject2).add(localAnimatorListener);
            localObject1 = localObject2;
          }
        }
        if (localObject1 != null)
        {
          localObject1 = ((ArrayList)localObject1).iterator();
          while (((Iterator)localObject1).hasNext())
            ((ArrayList)localObject4).remove((Animator.AnimatorListener)((Iterator)localObject1).next());
        }
      }
    }
    Object localObject1 = this.mNodes.iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject3 = (Node)((Iterator)localObject1).next();
      localObject2 = (Node)localHashMap.get(localObject3);
      if (((Node)localObject3).dependencies != null)
      {
        localObject3 = ((Node)localObject3).dependencies.iterator();
        while (((Iterator)localObject3).hasNext())
        {
          localObject4 = (Dependency)((Iterator)localObject3).next();
          ((Node)localObject2).addDependency(new Dependency((Node)localHashMap.get(((Dependency)localObject4).node), ((Dependency)localObject4).rule));
        }
      }
    }
    return localAnimatorSet;
  }

  public void end()
  {
    this.mTerminated = true;
    if (isStarted())
    {
      Iterator localIterator;
      if (this.mSortedNodes.size() != this.mNodes.size())
      {
        sortNodes();
        localIterator = this.mSortedNodes.iterator();
        while (localIterator.hasNext())
        {
          Node localNode = (Node)localIterator.next();
          if (this.mSetListener == null)
            this.mSetListener = new AnimatorSetListener(this);
          localNode.animation.addListener(this.mSetListener);
        }
      }
      if (this.mDelayAnim != null)
        this.mDelayAnim.cancel();
      if (this.mSortedNodes.size() > 0)
      {
        localIterator = this.mSortedNodes.iterator();
        while (localIterator.hasNext())
          ((Node)localIterator.next()).animation.end();
      }
      if (this.mListeners != null)
      {
        localIterator = ((ArrayList)this.mListeners.clone()).iterator();
        while (localIterator.hasNext())
          ((Animator.AnimatorListener)localIterator.next()).onAnimationEnd(this);
      }
      this.mStarted = false;
    }
  }

  public ArrayList<Animator> getChildAnimations()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.mNodes.iterator();
    while (localIterator.hasNext())
      localArrayList.add(((Node)localIterator.next()).animation);
    return localArrayList;
  }

  public long getDuration()
  {
    return this.mDuration;
  }

  public long getStartDelay()
  {
    return this.mStartDelay;
  }

  public boolean isRunning()
  {
    Iterator localIterator = this.mNodes.iterator();
    while (localIterator.hasNext())
      if (((Node)localIterator.next()).animation.isRunning())
        return true;
    return false;
  }

  public boolean isStarted()
  {
    return this.mStarted;
  }

  public Builder play(Animator paramAnimator)
  {
    if (paramAnimator != null)
    {
      this.mNeedsSort = true;
      return new Builder(paramAnimator);
    }
    return null;
  }

  public void playSequentially(List<Animator> paramList)
  {
    if ((paramList != null) && (paramList.size() > 0))
    {
      this.mNeedsSort = true;
      int j = paramList.size();
      int i = 0;
      if (j == 1)
      {
        play((Animator)paramList.get(0));
        return;
      }
      while (i < paramList.size() - 1)
      {
        Builder localBuilder = play((Animator)paramList.get(i));
        i += 1;
        localBuilder.before((Animator)paramList.get(i));
      }
    }
  }

  public void playSequentially(Animator[] paramArrayOfAnimator)
  {
    if (paramArrayOfAnimator != null)
    {
      this.mNeedsSort = true;
      int j = paramArrayOfAnimator.length;
      int i = 0;
      if (j == 1)
      {
        play(paramArrayOfAnimator[0]);
        return;
      }
      while (i < paramArrayOfAnimator.length - 1)
      {
        Builder localBuilder = play(paramArrayOfAnimator[i]);
        i += 1;
        localBuilder.before(paramArrayOfAnimator[i]);
      }
    }
  }

  public void playTogether(Collection<Animator> paramCollection)
  {
    if ((paramCollection != null) && (paramCollection.size() > 0))
    {
      this.mNeedsSort = true;
      Animator localAnimator = null;
      Iterator localIterator = paramCollection.iterator();
      paramCollection = localAnimator;
      while (localIterator.hasNext())
      {
        localAnimator = (Animator)localIterator.next();
        if (paramCollection == null)
          paramCollection = play(localAnimator);
        else
          paramCollection.with(localAnimator);
      }
    }
  }

  public void playTogether(Animator[] paramArrayOfAnimator)
  {
    if (paramArrayOfAnimator != null)
    {
      int i = 1;
      this.mNeedsSort = true;
      Builder localBuilder = play(paramArrayOfAnimator[0]);
      while (i < paramArrayOfAnimator.length)
      {
        localBuilder.with(paramArrayOfAnimator[i]);
        i += 1;
      }
    }
  }

  public AnimatorSet setDuration(long paramLong)
  {
    if (paramLong < 0L)
      throw new IllegalArgumentException("duration must be a value of zero or greater");
    Iterator localIterator = this.mNodes.iterator();
    while (localIterator.hasNext())
      ((Node)localIterator.next()).animation.setDuration(paramLong);
    this.mDuration = paramLong;
    return this;
  }

  public void setInterpolator(Interpolator paramInterpolator)
  {
    Iterator localIterator = this.mNodes.iterator();
    while (localIterator.hasNext())
      ((Node)localIterator.next()).animation.setInterpolator(paramInterpolator);
  }

  public void setStartDelay(long paramLong)
  {
    this.mStartDelay = paramLong;
  }

  public void setTarget(Object paramObject)
  {
    Iterator localIterator = this.mNodes.iterator();
    while (localIterator.hasNext())
    {
      Animator localAnimator = ((Node)localIterator.next()).animation;
      if ((localAnimator instanceof AnimatorSet))
        ((AnimatorSet)localAnimator).setTarget(paramObject);
      else if ((localAnimator instanceof ObjectAnimator))
        ((ObjectAnimator)localAnimator).setTarget(paramObject);
    }
  }

  public void setupEndValues()
  {
    Iterator localIterator = this.mNodes.iterator();
    while (localIterator.hasNext())
      ((Node)localIterator.next()).animation.setupEndValues();
  }

  public void setupStartValues()
  {
    Iterator localIterator = this.mNodes.iterator();
    while (localIterator.hasNext())
      ((Node)localIterator.next()).animation.setupStartValues();
  }

  public void start()
  {
    int k = 0;
    this.mTerminated = false;
    this.mStarted = true;
    sortNodes();
    int m = this.mSortedNodes.size();
    int i = 0;
    Object localObject2;
    Object localObject3;
    while (i < m)
    {
      localObject1 = (Node)this.mSortedNodes.get(i);
      localObject2 = ((Node)localObject1).animation.getListeners();
      if ((localObject2 != null) && (((ArrayList)localObject2).size() > 0))
      {
        localObject2 = new ArrayList((Collection)localObject2).iterator();
        while (((Iterator)localObject2).hasNext())
        {
          localObject3 = (Animator.AnimatorListener)((Iterator)localObject2).next();
          if (((localObject3 instanceof DependencyListener)) || ((localObject3 instanceof AnimatorSetListener)))
            ((Node)localObject1).animation.removeListener((Animator.AnimatorListener)localObject3);
        }
      }
      i += 1;
    }
    Object localObject1 = new ArrayList();
    i = 0;
    int j;
    while (i < m)
    {
      localObject2 = (Node)this.mSortedNodes.get(i);
      if (this.mSetListener == null)
        this.mSetListener = new AnimatorSetListener(this);
      if ((((Node)localObject2).dependencies != null) && (((Node)localObject2).dependencies.size() != 0))
      {
        int n = ((Node)localObject2).dependencies.size();
        j = 0;
        while (j < n)
        {
          localObject3 = (Dependency)((Node)localObject2).dependencies.get(j);
          ((Dependency)localObject3).node.animation.addListener(new DependencyListener(this, (Node)localObject2, ((Dependency)localObject3).rule));
          j += 1;
        }
        ((Node)localObject2).tmpDependencies = ((ArrayList)((Node)localObject2).dependencies.clone());
      }
      else
      {
        ((ArrayList)localObject1).add(localObject2);
      }
      ((Node)localObject2).animation.addListener(this.mSetListener);
      i += 1;
    }
    if (this.mStartDelay <= 0L)
    {
      localObject1 = ((ArrayList)localObject1).iterator();
      while (((Iterator)localObject1).hasNext())
      {
        localObject2 = (Node)((Iterator)localObject1).next();
        ((Node)localObject2).animation.start();
        this.mPlayingSet.add(((Node)localObject2).animation);
      }
    }
    this.mDelayAnim = ValueAnimator.ofFloat(new float[] { 0.0F, 1.0F });
    this.mDelayAnim.setDuration(this.mStartDelay);
    this.mDelayAnim.addListener(new AnimatorListenerAdapter()
    {
      boolean canceled = false;

      public void onAnimationCancel(Animator paramAnonymousAnimator)
      {
        this.canceled = true;
      }

      public void onAnimationEnd(Animator paramAnonymousAnimator)
      {
        if (!this.canceled)
        {
          int j = this.val$nodesToStart.size();
          int i = 0;
          while (i < j)
          {
            paramAnonymousAnimator = (AnimatorSet.Node)this.val$nodesToStart.get(i);
            paramAnonymousAnimator.animation.start();
            AnimatorSet.this.mPlayingSet.add(paramAnonymousAnimator.animation);
            i += 1;
          }
        }
      }
    });
    this.mDelayAnim.start();
    if (this.mListeners != null)
    {
      localObject1 = (ArrayList)this.mListeners.clone();
      j = ((ArrayList)localObject1).size();
      i = 0;
      while (i < j)
      {
        ((Animator.AnimatorListener)((ArrayList)localObject1).get(i)).onAnimationStart(this);
        i += 1;
      }
    }
    if ((this.mNodes.size() == 0) && (this.mStartDelay == 0L))
    {
      this.mStarted = false;
      if (this.mListeners != null)
      {
        localObject1 = (ArrayList)this.mListeners.clone();
        j = ((ArrayList)localObject1).size();
        i = k;
        while (i < j)
        {
          ((Animator.AnimatorListener)((ArrayList)localObject1).get(i)).onAnimationEnd(this);
          i += 1;
        }
      }
    }
  }

  private class AnimatorSetListener
    implements Animator.AnimatorListener
  {
    private AnimatorSet mAnimatorSet;

    AnimatorSetListener(AnimatorSet arg2)
    {
      Object localObject;
      this.mAnimatorSet = localObject;
    }

    public void onAnimationCancel(Animator paramAnimator)
    {
      if ((!AnimatorSet.this.mTerminated) && (AnimatorSet.this.mPlayingSet.size() == 0) && (AnimatorSet.this.mListeners != null))
      {
        int j = AnimatorSet.this.mListeners.size();
        int i = 0;
        while (i < j)
        {
          ((Animator.AnimatorListener)AnimatorSet.this.mListeners.get(i)).onAnimationCancel(this.mAnimatorSet);
          i += 1;
        }
      }
    }

    public void onAnimationEnd(Animator paramAnimator)
    {
      paramAnimator.removeListener(this);
      AnimatorSet.this.mPlayingSet.remove(paramAnimator);
      paramAnimator = (AnimatorSet.Node)this.mAnimatorSet.mNodeMap.get(paramAnimator);
      int k = 1;
      paramAnimator.done = true;
      if (!AnimatorSet.this.mTerminated)
      {
        paramAnimator = this.mAnimatorSet.mSortedNodes;
        int m = paramAnimator.size();
        int i = 0;
        int j;
        while (true)
        {
          j = k;
          if (i >= m)
            break;
          if (!((AnimatorSet.Node)paramAnimator.get(i)).done)
          {
            j = 0;
            break;
          }
          i += 1;
        }
        if (j != 0)
        {
          if (AnimatorSet.this.mListeners != null)
          {
            paramAnimator = (ArrayList)AnimatorSet.this.mListeners.clone();
            j = paramAnimator.size();
            i = 0;
            while (i < j)
            {
              ((Animator.AnimatorListener)paramAnimator.get(i)).onAnimationEnd(this.mAnimatorSet);
              i += 1;
            }
          }
          AnimatorSet.access$302(this.mAnimatorSet, false);
        }
      }
    }

    public void onAnimationRepeat(Animator paramAnimator)
    {
    }

    public void onAnimationStart(Animator paramAnimator)
    {
    }
  }

  public class Builder
  {
    private AnimatorSet.Node mCurrentNode;

    Builder(Animator arg2)
    {
      Object localObject;
      this.mCurrentNode = ((AnimatorSet.Node)AnimatorSet.this.mNodeMap.get(localObject));
      if (this.mCurrentNode == null)
      {
        this.mCurrentNode = new AnimatorSet.Node(localObject);
        AnimatorSet.this.mNodeMap.put(localObject, this.mCurrentNode);
        AnimatorSet.this.mNodes.add(this.mCurrentNode);
      }
    }

    public Builder after(long paramLong)
    {
      ValueAnimator localValueAnimator = ValueAnimator.ofFloat(new float[] { 0.0F, 1.0F });
      localValueAnimator.setDuration(paramLong);
      after(localValueAnimator);
      return this;
    }

    public Builder after(Animator paramAnimator)
    {
      AnimatorSet.Node localNode2 = (AnimatorSet.Node)AnimatorSet.this.mNodeMap.get(paramAnimator);
      AnimatorSet.Node localNode1 = localNode2;
      if (localNode2 == null)
      {
        localNode1 = new AnimatorSet.Node(paramAnimator);
        AnimatorSet.this.mNodeMap.put(paramAnimator, localNode1);
        AnimatorSet.this.mNodes.add(localNode1);
      }
      paramAnimator = new AnimatorSet.Dependency(localNode1, 1);
      this.mCurrentNode.addDependency(paramAnimator);
      return this;
    }

    public Builder before(Animator paramAnimator)
    {
      AnimatorSet.Node localNode2 = (AnimatorSet.Node)AnimatorSet.this.mNodeMap.get(paramAnimator);
      AnimatorSet.Node localNode1 = localNode2;
      if (localNode2 == null)
      {
        localNode1 = new AnimatorSet.Node(paramAnimator);
        AnimatorSet.this.mNodeMap.put(paramAnimator, localNode1);
        AnimatorSet.this.mNodes.add(localNode1);
      }
      localNode1.addDependency(new AnimatorSet.Dependency(this.mCurrentNode, 1));
      return this;
    }

    public Builder with(Animator paramAnimator)
    {
      AnimatorSet.Node localNode2 = (AnimatorSet.Node)AnimatorSet.this.mNodeMap.get(paramAnimator);
      AnimatorSet.Node localNode1 = localNode2;
      if (localNode2 == null)
      {
        localNode1 = new AnimatorSet.Node(paramAnimator);
        AnimatorSet.this.mNodeMap.put(paramAnimator, localNode1);
        AnimatorSet.this.mNodes.add(localNode1);
      }
      localNode1.addDependency(new AnimatorSet.Dependency(this.mCurrentNode, 0));
      return this;
    }
  }

  private static class Dependency
  {
    static final int AFTER = 1;
    static final int WITH = 0;
    public AnimatorSet.Node node;
    public int rule;

    public Dependency(AnimatorSet.Node paramNode, int paramInt)
    {
      this.node = paramNode;
      this.rule = paramInt;
    }
  }

  private static class DependencyListener
    implements Animator.AnimatorListener
  {
    private AnimatorSet mAnimatorSet;
    private AnimatorSet.Node mNode;
    private int mRule;

    public DependencyListener(AnimatorSet paramAnimatorSet, AnimatorSet.Node paramNode, int paramInt)
    {
      this.mAnimatorSet = paramAnimatorSet;
      this.mNode = paramNode;
      this.mRule = paramInt;
    }

    private void startIfReady(Animator paramAnimator)
    {
      if (this.mAnimatorSet.mTerminated)
        return;
      Object localObject2 = null;
      int j = this.mNode.tmpDependencies.size();
      int i = 0;
      Object localObject1;
      while (true)
      {
        localObject1 = localObject2;
        if (i >= j)
          break;
        localObject1 = (AnimatorSet.Dependency)this.mNode.tmpDependencies.get(i);
        if ((((AnimatorSet.Dependency)localObject1).rule == this.mRule) && (((AnimatorSet.Dependency)localObject1).node.animation == paramAnimator))
        {
          paramAnimator.removeListener(this);
          break;
        }
        i += 1;
      }
      this.mNode.tmpDependencies.remove(localObject1);
      if (this.mNode.tmpDependencies.size() == 0)
      {
        this.mNode.animation.start();
        this.mAnimatorSet.mPlayingSet.add(this.mNode.animation);
      }
    }

    public void onAnimationCancel(Animator paramAnimator)
    {
    }

    public void onAnimationEnd(Animator paramAnimator)
    {
      if (this.mRule == 1)
        startIfReady(paramAnimator);
    }

    public void onAnimationRepeat(Animator paramAnimator)
    {
    }

    public void onAnimationStart(Animator paramAnimator)
    {
      if (this.mRule == 0)
        startIfReady(paramAnimator);
    }
  }

  private static class Node
    implements Cloneable
  {
    public Animator animation;
    public ArrayList<AnimatorSet.Dependency> dependencies = null;
    public boolean done = false;
    public ArrayList<Node> nodeDependencies = null;
    public ArrayList<Node> nodeDependents = null;
    public ArrayList<AnimatorSet.Dependency> tmpDependencies = null;

    public Node(Animator paramAnimator)
    {
      this.animation = paramAnimator;
    }

    public void addDependency(AnimatorSet.Dependency paramDependency)
    {
      if (this.dependencies == null)
      {
        this.dependencies = new ArrayList();
        this.nodeDependencies = new ArrayList();
      }
      this.dependencies.add(paramDependency);
      if (!this.nodeDependencies.contains(paramDependency.node))
        this.nodeDependencies.add(paramDependency.node);
      paramDependency = paramDependency.node;
      if (paramDependency.nodeDependents == null)
        paramDependency.nodeDependents = new ArrayList();
      paramDependency.nodeDependents.add(this);
    }

    public Node clone()
    {
      try
      {
        Node localNode = (Node)super.clone();
        localNode.animation = this.animation.clone();
        return localNode;
        label21: throw new AssertionError();
      }
      catch (CloneNotSupportedException localCloneNotSupportedException)
      {
        break label21;
      }
    }
  }
}