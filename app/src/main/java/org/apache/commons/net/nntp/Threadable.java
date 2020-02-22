package org.apache.commons.net.nntp;

public abstract interface Threadable
{
  public abstract boolean isDummy();

  public abstract Threadable makeDummy();

  public abstract String messageThreadId();

  public abstract String[] messageThreadReferences();

  public abstract void setChild(Threadable paramThreadable);

  public abstract void setNext(Threadable paramThreadable);

  public abstract String simplifiedSubject();

  public abstract boolean subjectIsReply();
}