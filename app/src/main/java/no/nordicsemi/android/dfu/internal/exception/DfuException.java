package no.nordicsemi.android.dfu.internal.exception;

public class DfuException extends Exception
{
  private static final long serialVersionUID = -6901728550661937942L;
  private final int mError;

  public DfuException(String paramString, int paramInt)
  {
    super(paramString);
    this.mError = paramInt;
  }

  public int getErrorNumber()
  {
    return this.mError;
  }

  public String getMessage()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(super.getMessage());
    localStringBuilder.append(" (error ");
    localStringBuilder.append(this.mError & 0xFFFFBFFF);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}