package com.google.gson.internal;

import java.io.ObjectStreamException;
import java.math.BigDecimal;

public final class LazilyParsedNumber extends Number
{
  private final String value;

  public LazilyParsedNumber(String paramString)
  {
    this.value = paramString;
  }

  private Object writeReplace()
    throws ObjectStreamException
  {
    return new BigDecimal(this.value);
  }

  public double doubleValue()
  {
    return Double.parseDouble(this.value);
  }

  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (this == paramObject)
      return true;
    if ((paramObject instanceof LazilyParsedNumber))
    {
      paramObject = (LazilyParsedNumber)paramObject;
      if (this.value != paramObject.value)
      {
        if (this.value.equals(paramObject.value))
          return true;
        bool = false;
      }
      return bool;
    }
    return false;
  }

  public float floatValue()
  {
    return Float.parseFloat(this.value);
  }

  public int hashCode()
  {
    return this.value.hashCode();
  }

  public int intValue()
  {
    try
    {
      int i = Integer.parseInt(this.value);
      return i;
    }
    catch (NumberFormatException localNumberFormatException1)
    {
      try
      {
        while (true)
        {
          long l = Long.parseLong(this.value);
          return (int)l;
          label21: return new BigDecimal(this.value).intValue();
          localNumberFormatException1 = localNumberFormatException1;
        }
      }
      catch (NumberFormatException localNumberFormatException2)
      {
        break label21;
      }
    }
  }

  public long longValue()
  {
    try
    {
      long l = Long.parseLong(this.value);
      return l;
      label10: return new BigDecimal(this.value).longValue();
    }
    catch (NumberFormatException localNumberFormatException)
    {
      break label10;
    }
  }

  public String toString()
  {
    return this.value;
  }
}