package org.apache.commons.net.nntp;

class ThreadContainer
{
  ThreadContainer child;
  ThreadContainer next;
  ThreadContainer parent;
  Threadable threadable;

  boolean findChild(ThreadContainer paramThreadContainer)
  {
    if (this.child == null)
      return false;
    if (this.child == paramThreadContainer)
      return true;
    return this.child.findChild(paramThreadContainer);
  }

  void flush()
  {
    Object localObject;
    if ((this.parent != null) && (this.threadable == null))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("no threadable in ");
      ((StringBuilder)localObject).append(toString());
      throw new RuntimeException(((StringBuilder)localObject).toString());
    }
    this.parent = null;
    Threadable localThreadable;
    if (this.threadable != null)
    {
      localThreadable = this.threadable;
      if (this.child == null)
        localObject = null;
      else
        localObject = this.child.threadable;
      localThreadable.setChild((Threadable)localObject);
    }
    if (this.child != null)
    {
      this.child.flush();
      this.child = null;
    }
    if (this.threadable != null)
    {
      localThreadable = this.threadable;
      if (this.next == null)
        localObject = null;
      else
        localObject = this.next.threadable;
      localThreadable.setNext((Threadable)localObject);
    }
    if (this.next != null)
    {
      this.next.flush();
      this.next = null;
    }
    this.threadable = null;
  }

  void reverseChildren()
  {
    if (this.child != null)
    {
      Object localObject2 = this.child;
      Object localObject1 = ((ThreadContainer)localObject2).next;
      Object localObject3 = null;
      while (localObject2 != null)
      {
        ((ThreadContainer)localObject2).next = localObject3;
        ThreadContainer localThreadContainer;
        if (localObject1 == null)
          localThreadContainer = null;
        else
          localThreadContainer = ((ThreadContainer)localObject1).next;
        localObject3 = localObject2;
        localObject2 = localObject1;
        localObject1 = localThreadContainer;
      }
      this.child = localObject3;
      for (localObject1 = this.child; localObject1 != null; localObject1 = ((ThreadContainer)localObject1).next)
        ((ThreadContainer)localObject1).reverseChildren();
    }
  }
}