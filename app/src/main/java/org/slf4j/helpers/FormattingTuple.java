package org.slf4j.helpers;

public class FormattingTuple
{
  public static FormattingTuple NULL = new FormattingTuple(null);
  private Object[] argArray;
  private String message;
  private Throwable throwable;

  public FormattingTuple(String paramString)
  {
    this(paramString, null, null);
  }

  public FormattingTuple(String paramString, Object[] paramArrayOfObject, Throwable paramThrowable)
  {
    this.message = paramString;
    this.throwable = paramThrowable;
    if (paramThrowable == null)
    {
      this.argArray = paramArrayOfObject;
      return;
    }
    this.argArray = trimmedCopy(paramArrayOfObject);
  }

  static Object[] trimmedCopy(Object[] paramArrayOfObject)
  {
    if ((paramArrayOfObject != null) && (paramArrayOfObject.length != 0))
    {
      int i = paramArrayOfObject.length - 1;
      Object[] arrayOfObject = new Object[i];
      System.arraycopy(paramArrayOfObject, 0, arrayOfObject, 0, i);
      return arrayOfObject;
    }
    throw new IllegalStateException("non-sensical empty or null argument array");
  }

  public Object[] getArgArray()
  {
    return this.argArray;
  }

  public String getMessage()
  {
    return this.message;
  }

  public Throwable getThrowable()
  {
    return this.throwable;
  }
}