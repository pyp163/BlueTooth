package org.apache.http.impl.conn.tsccm;

import java.lang.ref.Reference;

@Deprecated
public abstract interface RefQueueHandler
{
  public abstract void handleReference(Reference<?> paramReference);
}