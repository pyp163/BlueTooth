package no.nordicsemi.android.error;

public class GattError
{
  public static String parse(int paramInt)
  {
    switch (paramInt)
    {
    default:
      switch (paramInt)
      {
      default:
        switch (paramInt)
        {
        default:
          switch (paramInt)
          {
          default:
            switch (paramInt)
            {
            default:
              switch (paramInt)
              {
              default:
                switch (paramInt)
                {
                default:
                  StringBuilder localStringBuilder = new StringBuilder();
                  localStringBuilder.append("UNKNOWN (");
                  localStringBuilder.append(paramInt);
                  localStringBuilder.append(")");
                  return localStringBuilder.toString();
                case 257:
                  return "TOO MANY OPEN CONNECTIONS";
                case 42:
                  return "HCI ERROR DIFF TRANSACTION COLLISION";
                case 34:
                  return "GATT CONN LMP TIMEOUT";
                case 30:
                  return "HCI ERROR INVALID LMP PARAM";
                case 26:
                }
                return "HCI ERROR UNSUPPORTED REMOTE FEATURE";
              case 4110:
                return "DFU DEVICE NOT BONDED";
              case 4109:
                return "DFU CRC ERROR";
              case 4108:
                return "DFU INIT PACKET REQUIRED";
              case 4107:
                return "DFU INIT PACKET REQUIRED";
              case 4106:
                return "BLUETOOTH ADAPTER DISABLED";
              case 4105:
                return "DFU FILE TYPE NOT SUPPORTED";
              case 4104:
              }
              return "DFU INVALID RESPONSE";
            case 4102:
              return "DFU CHARACTERISTICS NOT FOUND";
            case 4101:
              return "DFU SERVICE DISCOVERY NOT STARTED";
            case 4100:
              return "DFU IO EXCEPTION";
            case 4099:
              return "DFU NOT A VALID HEX FILE";
            case 4098:
              return "DFU FILE ERROR";
            case 4097:
              return "DFU FILE NOT FOUND";
            case 4096:
            }
            return "DFU DEVICE DISCONNECTED";
          case 255:
            return "GATT VALUE OUT OF RANGE";
          case 254:
            return "GATT PROCEDURE IN PROGRESS";
          case 253:
          }
          return "GATT CCCD CFG ERROR";
        case 143:
          return "GATT CONGESTED";
        case 142:
          return "GATT NOT ENCRYPTED";
        case 141:
          return "GATT ENCRYPTED NO MITM";
        case 140:
          return "GATT SERVICE STARTED";
        case 139:
          return "GATT INVALID CFG";
        case 138:
          return "GATT MORE";
        case 137:
          return "GATT AUTH FAIL";
        case 136:
          return "GATT PENDING";
        case 135:
          return "GATT ILLEGAL PARAMETER";
        case 134:
          return "GATT CMD STARTED";
        case 133:
          return "GATT ERROR";
        case 132:
          return "GATT BUSY";
        case 131:
          return "GATT DB FULL";
        case 130:
          return "GATT WRONG STATE";
        case 129:
          return "GATT INTERNAL ERROR";
        case 128:
        }
        return "GATT NO RESOURCES";
      case 59:
        return "GATT UNACCEPT CONN INTERVAL";
      case 58:
      }
      return "GATT CONTROLLER BUSY";
    case 17:
      return "GATT INSUF RESOURCE";
    case 16:
      return "GATT UNSUPPORT GRP TYPE";
    case 15:
      return "GATT INSUF ENCRYPTION";
    case 14:
      return "GATT ERR UNLIKELY";
    case 13:
      return "GATT INVALID ATTR LEN";
    case 12:
      return "GATT INSUF KEY SIZE";
    case 11:
      return "GATT NOT LONG";
    case 10:
      return "GATT NOT FOUND";
    case 9:
      return "GATT PREPARE Q FULL";
    case 8:
      return "GATT INSUF AUTHORIZATION";
    case 7:
      return "GATT INVALID OFFSET";
    case 6:
      return "GATT REQ NOT SUPPORTED";
    case 5:
      return "GATT INSUF AUTHENTICATION";
    case 4:
      return "GATT INVALID PDU";
    case 3:
      return "GATT WRITE NOT PERMIT";
    case 2:
      return "GATT READ NOT PERMIT";
    case 1:
    }
    return "GATT INVALID HANDLE";
  }

  public static String parseConnectionError(int paramInt)
  {
    if (paramInt != 8)
    {
      if (paramInt != 19)
      {
        if (paramInt != 22)
        {
          if (paramInt != 34)
          {
            if (paramInt != 62)
            {
              if (paramInt != 133)
              {
                if (paramInt != 256)
                {
                  switch (paramInt)
                  {
                  default:
                    StringBuilder localStringBuilder = new StringBuilder();
                    localStringBuilder.append("UNKNOWN (");
                    localStringBuilder.append(paramInt);
                    localStringBuilder.append(")");
                    return localStringBuilder.toString();
                  case 1:
                    return "GATT CONN L2C FAILURE";
                  case 0:
                  }
                  return "SUCCESS";
                }
                return "GATT CONN CANCEL ";
              }
              return "GATT ERROR";
            }
            return "GATT CONN FAIL ESTABLISH";
          }
          return "GATT CONN LMP TIMEOUT";
        }
        return "GATT CONN TERMINATE LOCAL HOST";
      }
      return "GATT CONN TERMINATE PEER USER";
    }
    return "GATT CONN TIMEOUT";
  }

  public static String parseDfuRemoteError(int paramInt)
  {
    int i = paramInt & 0xF00;
    if (i != 256)
    {
      if (i != 512)
      {
        if (i != 1024)
        {
          if (i != 2048)
          {
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("UNKNOWN (");
            localStringBuilder.append(paramInt);
            localStringBuilder.append(")");
            return localStringBuilder.toString();
          }
          return SecureDfuError.parseButtonlessError(paramInt);
        }
        return SecureDfuError.parseExtendedError(paramInt);
      }
      return SecureDfuError.parse(paramInt);
    }
    return LegacyDfuError.parse(paramInt);
  }
}