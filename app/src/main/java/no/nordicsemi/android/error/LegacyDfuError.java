package no.nordicsemi.android.error;

public final class LegacyDfuError
{
  public static final int CRC_ERROR = 5;
  public static final int DATA_SIZE_EXCEEDS_LIMIT = 4;
  public static final int INVALID_STATE = 2;
  public static final int NOT_SUPPORTED = 3;
  public static final int OPERATION_FAILED = 6;

  public static String parse(int paramInt)
  {
    switch (paramInt)
    {
    default:
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("UNKNOWN (");
      localStringBuilder.append(paramInt);
      localStringBuilder.append(")");
      return localStringBuilder.toString();
    case 262:
      return "OPERATION FAILED";
    case 261:
      return "INVALID CRC ERROR";
    case 260:
      return "DATA SIZE EXCEEDS LIMIT";
    case 259:
      return "NOT SUPPORTED";
    case 258:
    }
    return "INVALID STATE";
  }
}