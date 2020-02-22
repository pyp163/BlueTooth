package org.apache.commons.net.nntp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Threader
{
  private int bogusIdCount = 0;
  private HashMap<String, ThreadContainer> idTable;
  private ThreadContainer root;

  private void buildContainer(Threadable paramThreadable)
  {
    Object localObject2 = paramThreadable.messageThreadId();
    Object localObject4 = (ThreadContainer)this.idTable.get(localObject2);
    Object localObject3 = localObject2;
    Object localObject1 = localObject4;
    if (localObject4 != null)
      if (((ThreadContainer)localObject4).threadable != null)
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("<Bogus-id:");
        i = this.bogusIdCount;
        this.bogusIdCount = (i + 1);
        ((StringBuilder)localObject1).append(i);
        ((StringBuilder)localObject1).append(">");
        localObject3 = ((StringBuilder)localObject1).toString();
        localObject1 = null;
      }
      else
      {
        ((ThreadContainer)localObject4).threadable = paramThreadable;
        localObject1 = localObject4;
        localObject3 = localObject2;
      }
    localObject2 = localObject1;
    if (localObject1 == null)
    {
      localObject2 = new ThreadContainer();
      ((ThreadContainer)localObject2).threadable = paramThreadable;
      this.idTable.put(localObject3, localObject2);
    }
    localObject4 = paramThreadable.messageThreadReferences();
    int i = 0;
    for (paramThreadable = null; i < localObject4.length; paramThreadable = (Threadable)localObject1)
    {
      Object localObject5 = localObject4[i];
      localObject3 = (ThreadContainer)this.idTable.get(localObject5);
      localObject1 = localObject3;
      if (localObject3 == null)
      {
        localObject1 = new ThreadContainer();
        this.idTable.put(localObject5, localObject1);
      }
      if ((paramThreadable != null) && (((ThreadContainer)localObject1).parent == null) && (paramThreadable != localObject1) && (!((ThreadContainer)localObject1).findChild(paramThreadable)))
      {
        ((ThreadContainer)localObject1).parent = paramThreadable;
        ((ThreadContainer)localObject1).next = paramThreadable.child;
        paramThreadable.child = ((ThreadContainer)localObject1);
      }
      i += 1;
    }
    localObject1 = paramThreadable;
    if (paramThreadable != null)
      if (paramThreadable != localObject2)
      {
        localObject1 = paramThreadable;
        if (!((ThreadContainer)localObject2).findChild(paramThreadable));
      }
      else
      {
        localObject1 = null;
      }
    if (((ThreadContainer)localObject2).parent != null)
    {
      paramThreadable = ((ThreadContainer)localObject2).parent.child;
      localObject3 = null;
      while ((paramThreadable != null) && (paramThreadable != localObject2))
      {
        localObject4 = paramThreadable.next;
        localObject3 = paramThreadable;
        paramThreadable = (Threadable)localObject4;
      }
      if (paramThreadable == null)
      {
        paramThreadable = new StringBuilder();
        paramThreadable.append("Didnt find ");
        paramThreadable.append(localObject2);
        paramThreadable.append(" in parent");
        paramThreadable.append(((ThreadContainer)localObject2).parent);
        throw new RuntimeException(paramThreadable.toString());
      }
      if (localObject3 == null)
        ((ThreadContainer)localObject2).parent.child = ((ThreadContainer)localObject2).next;
      else
        ((ThreadContainer)localObject3).next = ((ThreadContainer)localObject2).next;
      ((ThreadContainer)localObject2).next = null;
      ((ThreadContainer)localObject2).parent = null;
    }
    if (localObject1 != null)
    {
      ((ThreadContainer)localObject2).parent = ((ThreadContainer)localObject1);
      ((ThreadContainer)localObject2).next = ((ThreadContainer)localObject1).child;
      ((ThreadContainer)localObject1).child = ((ThreadContainer)localObject2);
    }
  }

  private ThreadContainer findRootSet()
  {
    Object localObject1 = new ThreadContainer();
    Iterator localIterator = this.idTable.keySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject2 = localIterator.next();
      localObject2 = (ThreadContainer)this.idTable.get(localObject2);
      if (((ThreadContainer)localObject2).parent == null)
      {
        if (((ThreadContainer)localObject2).next != null)
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("c.next is ");
          ((StringBuilder)localObject1).append(((ThreadContainer)localObject2).next.toString());
          throw new RuntimeException(((StringBuilder)localObject1).toString());
        }
        ((ThreadContainer)localObject2).next = ((ThreadContainer)localObject1).child;
        ((ThreadContainer)localObject1).child = ((ThreadContainer)localObject2);
      }
    }
    return localObject1;
  }

  private void gatherSubjects()
  {
    Object localObject1 = this.root.child;
    int j = 0;
    int i = 0;
    while (localObject1 != null)
    {
      i += 1;
      localObject1 = ((ThreadContainer)localObject1).next;
    }
    HashMap localHashMap = new HashMap((int)(i * 1.2D), 0.9F);
    localObject1 = this.root.child;
    for (i = j; localObject1 != null; i = j)
    {
      localObject3 = ((ThreadContainer)localObject1).threadable;
      localObject2 = localObject3;
      if (localObject3 == null)
        localObject2 = ((ThreadContainer)localObject1).child.threadable;
      localObject2 = ((Threadable)localObject2).simplifiedSubject();
      j = i;
      if (localObject2 != null)
        if (localObject2 == "")
        {
          j = i;
        }
        else
        {
          localObject3 = (ThreadContainer)localHashMap.get(localObject2);
          if ((localObject3 != null) && ((((ThreadContainer)localObject1).threadable != null) || (((ThreadContainer)localObject3).threadable == null)))
          {
            j = i;
            if (((ThreadContainer)localObject3).threadable != null)
            {
              j = i;
              if (((ThreadContainer)localObject3).threadable.subjectIsReply())
              {
                j = i;
                if (((ThreadContainer)localObject1).threadable != null)
                {
                  j = i;
                  if (((ThreadContainer)localObject1).threadable.subjectIsReply());
                }
              }
            }
          }
          else
          {
            localHashMap.put(localObject2, localObject1);
            j = i + 1;
          }
        }
      localObject1 = ((ThreadContainer)localObject1).next;
    }
    if (i == 0)
      return;
    localObject1 = this.root.child;
    Object localObject2 = ((ThreadContainer)localObject1).next;
    Object localObject3 = null;
    while (localObject1 != null)
    {
      Object localObject5 = ((ThreadContainer)localObject1).threadable;
      Object localObject4 = localObject5;
      if (localObject5 == null)
        localObject4 = ((ThreadContainer)localObject1).child.threadable;
      localObject4 = ((Threadable)localObject4).simplifiedSubject();
      if ((localObject4 != null) && (localObject4 != ""))
      {
        localObject5 = (ThreadContainer)localHashMap.get(localObject4);
        if (localObject5 != localObject1)
        {
          if (localObject3 == null)
            this.root.child = ((ThreadContainer)localObject1).next;
          else
            ((ThreadContainer)localObject3).next = ((ThreadContainer)localObject1).next;
          ((ThreadContainer)localObject1).next = null;
          if ((((ThreadContainer)localObject5).threadable == null) && (((ThreadContainer)localObject1).threadable == null))
          {
            for (localObject4 = ((ThreadContainer)localObject5).child; (localObject4 != null) && (((ThreadContainer)localObject4).next != null); localObject4 = ((ThreadContainer)localObject4).next);
            if (localObject4 != null)
              ((ThreadContainer)localObject4).next = ((ThreadContainer)localObject1).child;
            for (localObject4 = ((ThreadContainer)localObject1).child; localObject4 != null; localObject4 = ((ThreadContainer)localObject4).next)
              ((ThreadContainer)localObject4).parent = ((ThreadContainer)localObject5);
            ((ThreadContainer)localObject1).child = null;
            break label606;
          }
          if ((((ThreadContainer)localObject5).threadable != null) && ((((ThreadContainer)localObject1).threadable == null) || (!((ThreadContainer)localObject1).threadable.subjectIsReply()) || (((ThreadContainer)localObject5).threadable.subjectIsReply())))
          {
            ThreadContainer localThreadContainer = new ThreadContainer();
            localThreadContainer.threadable = ((ThreadContainer)localObject5).threadable;
            localThreadContainer.child = ((ThreadContainer)localObject5).child;
            for (localObject4 = localThreadContainer.child; localObject4 != null; localObject4 = ((ThreadContainer)localObject4).next)
              ((ThreadContainer)localObject4).parent = localThreadContainer;
            ((ThreadContainer)localObject5).threadable = null;
            ((ThreadContainer)localObject5).child = null;
            ((ThreadContainer)localObject1).parent = ((ThreadContainer)localObject5);
            localThreadContainer.parent = ((ThreadContainer)localObject5);
            ((ThreadContainer)localObject5).child = ((ThreadContainer)localObject1);
            ((ThreadContainer)localObject1).next = localThreadContainer;
            break label606;
          }
          ((ThreadContainer)localObject1).parent = ((ThreadContainer)localObject5);
          ((ThreadContainer)localObject1).next = ((ThreadContainer)localObject5).child;
          ((ThreadContainer)localObject5).child = ((ThreadContainer)localObject1);
          break label606;
        }
      }
      localObject3 = localObject1;
      label606: if (localObject2 == null)
        localObject1 = null;
      else
        localObject1 = ((ThreadContainer)localObject2).next;
      localObject4 = localObject1;
      localObject1 = localObject2;
      localObject2 = localObject4;
    }
    localHashMap.clear();
  }

  private void pruneEmptyContainers(ThreadContainer paramThreadContainer)
  {
    Object localObject2 = paramThreadContainer.child;
    Object localObject1 = ((ThreadContainer)localObject2).next;
    Object localObject3 = null;
    while (localObject2 != null)
    {
      Object localObject4;
      if ((((ThreadContainer)localObject2).threadable == null) && (((ThreadContainer)localObject2).child == null))
      {
        if (localObject3 == null)
          paramThreadContainer.child = ((ThreadContainer)localObject2).next;
        else
          localObject3.next = ((ThreadContainer)localObject2).next;
      }
      else if ((((ThreadContainer)localObject2).threadable == null) && (((ThreadContainer)localObject2).child != null) && ((((ThreadContainer)localObject2).parent != null) || (((ThreadContainer)localObject2).child.next == null)))
      {
        localObject1 = ((ThreadContainer)localObject2).child;
        if (localObject3 == null)
          paramThreadContainer.child = ((ThreadContainer)localObject1);
        else
          localObject3.next = ((ThreadContainer)localObject1);
        for (localObject4 = localObject1; ((ThreadContainer)localObject4).next != null; localObject4 = ((ThreadContainer)localObject4).next)
          ((ThreadContainer)localObject4).parent = ((ThreadContainer)localObject2).parent;
        ((ThreadContainer)localObject4).parent = ((ThreadContainer)localObject2).parent;
        ((ThreadContainer)localObject4).next = ((ThreadContainer)localObject2).next;
      }
      else
      {
        if (((ThreadContainer)localObject2).child != null)
          pruneEmptyContainers((ThreadContainer)localObject2);
        localObject3 = localObject2;
      }
      if (localObject1 == null)
      {
        localObject4 = null;
        localObject2 = localObject1;
        localObject1 = localObject4;
      }
      else
      {
        localObject4 = ((ThreadContainer)localObject1).next;
        localObject2 = localObject1;
        localObject1 = localObject4;
      }
    }
  }

  public Threadable thread(Iterable<? extends Threadable> paramIterable)
  {
    if (paramIterable == null)
      return null;
    this.idTable = new HashMap();
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext())
    {
      Threadable localThreadable = (Threadable)paramIterable.next();
      if (!localThreadable.isDummy())
        buildContainer(localThreadable);
    }
    this.root = findRootSet();
    this.idTable.clear();
    this.idTable = null;
    pruneEmptyContainers(this.root);
    this.root.reverseChildren();
    gatherSubjects();
    if (this.root.next != null)
    {
      paramIterable = new StringBuilder();
      paramIterable.append("root node has a next:");
      paramIterable.append(this.root);
      throw new RuntimeException(paramIterable.toString());
    }
    for (paramIterable = this.root.child; paramIterable != null; paramIterable = paramIterable.next)
      if (paramIterable.threadable == null)
        paramIterable.threadable = paramIterable.child.threadable.makeDummy();
    if (this.root.child == null)
      paramIterable = null;
    else
      paramIterable = this.root.child.threadable;
    this.root.flush();
    this.root = null;
    return paramIterable;
  }

  public Threadable thread(List<? extends Threadable> paramList)
  {
    return thread(paramList);
  }

  @Deprecated
  public Threadable thread(Threadable[] paramArrayOfThreadable)
  {
    return thread(Arrays.asList(paramArrayOfThreadable));
  }
}