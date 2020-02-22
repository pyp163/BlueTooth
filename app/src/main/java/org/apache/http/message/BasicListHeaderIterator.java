package org.apache.http.message;

import java.util.List;
import java.util.NoSuchElementException;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;

public class BasicListHeaderIterator
  implements HeaderIterator
{
  protected final List allHeaders;
  protected int currentIndex;
  protected String headerName;
  protected int lastIndex;

  public BasicListHeaderIterator(List paramList, String paramString)
  {
    if (paramList == null)
      throw new IllegalArgumentException("Header list must not be null.");
    this.allHeaders = paramList;
    this.headerName = paramString;
    this.currentIndex = findNext(-1);
    this.lastIndex = -1;
  }

  protected boolean filterHeader(int paramInt)
  {
    if (this.headerName == null)
      return true;
    String str = ((Header)this.allHeaders.get(paramInt)).getName();
    return this.headerName.equalsIgnoreCase(str);
  }

  protected int findNext(int paramInt)
  {
    if (paramInt < -1)
      return -1;
    int i = this.allHeaders.size();
    for (boolean bool = false; (!bool) && (paramInt < i - 1); bool = filterHeader(paramInt))
      paramInt += 1;
    if (bool)
      return paramInt;
    return -1;
  }

  public boolean hasNext()
  {
    return this.currentIndex >= 0;
  }

  public final Object next()
    throws NoSuchElementException
  {
    return nextHeader();
  }

  public Header nextHeader()
    throws NoSuchElementException
  {
    int i = this.currentIndex;
    if (i < 0)
      throw new NoSuchElementException("Iteration already finished.");
    this.lastIndex = i;
    this.currentIndex = findNext(i);
    return (Header)this.allHeaders.get(i);
  }

  public void remove()
    throws UnsupportedOperationException
  {
    if (this.lastIndex < 0)
      throw new IllegalStateException("No header to remove.");
    this.allHeaders.remove(this.lastIndex);
    this.lastIndex = -1;
    this.currentIndex -= 1;
  }
}