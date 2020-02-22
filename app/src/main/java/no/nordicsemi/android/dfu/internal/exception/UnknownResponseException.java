package no.nordicsemi.android.dfu.internal.exception;

import java.util.Locale;

public class UnknownResponseException extends Exception
{
  private static final char[] HEX_ARRAY = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };
  private static final long serialVersionUID = -8716125467309979289L;
  private final int mExpectedOpCode;
  private final int mExpectedReturnCode;
  private final byte[] mResponse;

  public UnknownResponseException(String paramString, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    super(paramString);
    if (paramArrayOfByte == null)
      paramArrayOfByte = new byte[0];
    this.mResponse = paramArrayOfByte;
    this.mExpectedReturnCode = paramInt1;
    this.mExpectedOpCode = paramInt2;
  }

  public static String bytesToHex(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if ((paramArrayOfByte != null) && (paramArrayOfByte.length > paramInt1) && (paramInt2 > 0))
    {
      int i = Math.min(paramInt2, paramArrayOfByte.length - paramInt1);
      char[] arrayOfChar = new char[i * 2];
      paramInt2 = 0;
      while (paramInt2 < i)
      {
        int j = paramArrayOfByte[(paramInt1 + paramInt2)] & 0xFF;
        int k = paramInt2 * 2;
        arrayOfChar[k] = HEX_ARRAY[(j >>> 4)];
        arrayOfChar[(k + 1)] = HEX_ARRAY[(j & 0xF)];
        paramInt2 += 1;
      }
      paramArrayOfByte = new StringBuilder();
      paramArrayOfByte.append("0x");
      paramArrayOfByte.append(new String(arrayOfChar));
      return paramArrayOfByte.toString();
    }
    return "";
  }

  public String getMessage()
  {
    return String.format(Locale.US, "%s (response: %s, expected: 0x%02X%02X..)", new Object[] { super.getMessage(), bytesToHex(this.mResponse, 0, this.mResponse.length), Integer.valueOf(this.mExpectedReturnCode), Integer.valueOf(this.mExpectedOpCode) });
  }
}