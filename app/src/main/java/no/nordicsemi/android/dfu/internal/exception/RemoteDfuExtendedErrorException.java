package no.nordicsemi.android.dfu.internal.exception;

public class RemoteDfuExtendedErrorException extends RemoteDfuException
{
  private static final long serialVersionUID = -6901728550661937942L;
  private final int mError;

  public RemoteDfuExtendedErrorException(String paramString, int paramInt)
  {
    super(paramString, 11);
    this.mError = paramInt;
  }

  public int getExtendedErrorNumber()
  {
    return this.mError;
  }

  public String getMessage()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(super.getMessage());
    localStringBuilder.append(" (extended error ");
    localStringBuilder.append(this.mError);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}