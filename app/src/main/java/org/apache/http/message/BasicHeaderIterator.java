package org.apache.http.message;

import java.util.NoSuchElementException;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;

public class BasicHeaderIterator
  implements HeaderIterator
{
  protected final Header[] allHeaders;
  protected int currentIndex;
  protected String headerName;

  public BasicHeaderIterator(Header[] paramArrayOfHeader, String paramString)
  {
    if (paramArrayOfHeader == null)
      throw new IllegalArgumentException("Header array must not be null.");
    this.allHeaders = paramArrayOfHeader;
    this.headerName = paramString;
    this.currentIndex = findNext(-1);
  }

  protected boolean filterHeader(int paramInt)
  {
    return (this.headerName == null) || (this.headerName.equalsIgnoreCase(this.allHeaders[paramInt].getName()));
  }

  protected int findNext(int paramInt)
  {
    if (paramInt < -1)
      return -1;
    int i = this.allHeaders.length;
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
    this.currentIndex = findNext(i);
    return this.allHeaders[i];
  }

  public void remove()
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException("Removing headers is not supported.");
  }
}