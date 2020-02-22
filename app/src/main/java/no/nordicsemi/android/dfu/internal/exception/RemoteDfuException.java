package no.nordicsemi.android.dfu.internal.exception;

public class RemoteDfuException extends Exception
{
  private static final long serialVersionUID = -6901728550661937942L;
  private final int mState;

  public RemoteDfuException(String paramString, int paramInt)
  {
    super(paramString);
    this.mState = paramInt;
  }

  public int getErrorNumber()
  {
    return this.mState;
  }

  public String getMessage()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(super.getMessage());
    localStringBuilder.append(" (error ");
    localStringBuilder.append(this.mState);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}