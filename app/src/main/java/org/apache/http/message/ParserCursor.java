package org.apache.http.message;

import org.apache.http.util.CharArrayBuffer;

public class ParserCursor
{
  private final int lowerBound;
  private int pos;
  private final int upperBound;

  public ParserCursor(int paramInt1, int paramInt2)
  {
    if (paramInt1 < 0)
      throw new IndexOutOfBoundsException("Lower bound cannot be negative");
    if (paramInt1 > paramInt2)
      throw new IndexOutOfBoundsException("Lower bound cannot be greater then upper bound");
    this.lowerBound = paramInt1;
    this.upperBound = paramInt2;
    this.pos = paramInt1;
  }

  public boolean atEnd()
  {
    return this.pos >= this.upperBound;
  }

  public int getLowerBound()
  {
    return this.lowerBound;
  }

  public int getPos()
  {
    return this.pos;
  }

  public int getUpperBound()
  {
    return this.upperBound;
  }

  public String toString()
  {
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(16);
    localCharArrayBuffer.append('[');
    localCharArrayBuffer.append(Integer.toString(this.lowerBound));
    localCharArrayBuffer.append('>');
    localCharArrayBuffer.append(Integer.toString(this.pos));
    localCharArrayBuffer.append('>');
    localCharArrayBuffer.append(Integer.toString(this.upperBound));
    localCharArrayBuffer.append(']');
    return localCharArrayBuffer.toString();
  }

  public void updatePos(int paramInt)
  {
    StringBuffer localStringBuffer;
    if (paramInt < this.lowerBound)
    {
      localStringBuffer = new StringBuffer();
      localStringBuffer.append("pos: ");
      localStringBuffer.append(paramInt);
      localStringBuffer.append(" < lowerBound: ");
      localStringBuffer.append(this.lowerBound);
      throw new IndexOutOfBoundsException(localStringBuffer.toString());
    }
    if (paramInt > this.upperBound)
    {
      localStringBuffer = new StringBuffer();
      localStringBuffer.append("pos: ");
      localStringBuffer.append(paramInt);
      localStringBuffer.append(" > upperBound: ");
      localStringBuffer.append(this.upperBound);
      throw new IndexOutOfBoundsException(localStringBuffer.toString());
    }
    this.pos = paramInt;
  }
}