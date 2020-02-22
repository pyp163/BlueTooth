package no.nordicsemi.android.error;

public final class SecureDfuError
{
  public static final int BUTTONLESS_ERROR_OPERATION_FAILED = 4;
  public static final int BUTTONLESS_ERROR_OP_CODE_NOT_SUPPORTED = 2;
  public static final int EXTENDED_ERROR = 11;
  public static final int EXT_ERROR_FW_VERSION_FAILURE = 5;
  public static final int EXT_ERROR_HASH_FAILED = 10;
  public static final int EXT_ERROR_HW_VERSION_FAILURE = 6;
  public static final int EXT_ERROR_INIT_COMMAND_INVALID = 4;
  public static final int EXT_ERROR_INSUFFICIENT_SPACE = 13;
  public static final int EXT_ERROR_SD_VERSION_FAILURE = 7;
  public static final int EXT_ERROR_SIGNATURE_MISSING = 8;
  public static final int EXT_ERROR_UNKNOWN_COMMAND = 3;
  public static final int EXT_ERROR_VERIFICATION_FAILED = 12;
  public static final int EXT_ERROR_WRONG_COMMAND_FORMAT = 2;
  public static final int EXT_ERROR_WRONG_HASH_TYPE = 9;
  public static final int EXT_ERROR_WRONG_SIGNATURE_TYPE = 11;
  public static final int INSUFFICIENT_RESOURCES = 4;
  public static final int INVALID_OBJECT = 5;
  public static final int INVALID_PARAM = 3;
  public static final int OPERATION_FAILED = 10;
  public static final int OPERATION_NOT_PERMITTED = 8;
  public static final int OP_CODE_NOT_SUPPORTED = 2;
  public static final int UNSUPPORTED_TYPE = 7;

  public static String parse(int paramInt)
  {
    switch (paramInt)
    {
    case 518:
    case 521:
    default:
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("UNKNOWN (");
      localStringBuilder.append(paramInt);
      localStringBuilder.append(")");
      return localStringBuilder.toString();
    case 523:
      return "EXTENDED ERROR";
    case 522:
      return "OPERATION FAILED";
    case 520:
      return "OPERATION NOT PERMITTED";
    case 519:
      return "UNSUPPORTED TYPE";
    case 517:
      return "INVALID OBJECT";
    case 516:
      return "INSUFFICIENT RESOURCES";
    case 515:
      return "INVALID PARAM";
    case 514:
    }
    return "OP CODE NOT SUPPORTED";
  }

  public static String parseButtonlessError(int paramInt)
  {
    if (paramInt != 2050)
    {
      if (paramInt != 2052)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("UNKNOWN (");
        localStringBuilder.append(paramInt);
        localStringBuilder.append(")");
        return localStringBuilder.toString();
      }
      return "OPERATION FAILED";
    }
    return "OP CODE NOT SUPPORTED";
  }

  public static String parseExtendedError(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return "Reserved for future use";
    case 1037:
      return "Insufficient space";
    case 1036:
      return "Verification failed";
    case 1035:
      return "Wrong signature type";
    case 1034:
      return "Hash failed";
    case 1033:
      return "Wrong hash type";
    case 1032:
      return "Signature mismatch";
    case 1031:
      return "SD version failure";
    case 1030:
      return "HW version failure";
    case 1029:
      return "FW version failure";
    case 1028:
      return "Init command invalid";
    case 1027:
      return "Unknown command";
    case 1026:
    }
    return "Wrong command format";
  }
}