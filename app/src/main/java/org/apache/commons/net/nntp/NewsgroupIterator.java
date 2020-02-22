package org.apache.commons.net.nntp;

import java.util.Iterator;

class NewsgroupIterator
  implements Iterator<NewsgroupInfo>, Iterable<NewsgroupInfo>
{
  private final Iterator<String> stringIterator;

  public NewsgroupIterator(Iterable<String> paramIterable)
  {
    this.stringIterator = paramIterable.iterator();
  }

  public boolean hasNext()
  {
    return this.stringIterator.hasNext();
  }

  public Iterator<NewsgroupInfo> iterator()
  {
    return this;
  }

  public NewsgroupInfo next()
  {
    return NNTPClient.__parseNewsgroupListEntry((String)this.stringIterator.next());
  }

  public void remove()
  {
    this.stringIterator.remove();
  }
}