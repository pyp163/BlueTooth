package org.apache.http;

import java.util.Iterator;

public abstract interface TokenIterator extends Iterator
{
  public abstract boolean hasNext();

  public abstract String nextToken();
}