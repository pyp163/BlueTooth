package okhttp3.internal.http2;

import java.io.IOException;
import okhttp3.internal.Util;
import okio.ByteString;

public final class Http2
{
  static final String[] BINARY;
  static final ByteString CONNECTION_PREFACE = ByteString.encodeUtf8("PRI * HTTP/2.0\r\n\r\nSM\r\n\r\n");
  static final String[] FLAGS;
  static final byte FLAG_ACK = 1;
  static final byte FLAG_COMPRESSED = 32;
  static final byte FLAG_END_HEADERS = 4;
  static final byte FLAG_END_PUSH_PROMISE = 4;
  static final byte FLAG_END_STREAM = 1;
  static final byte FLAG_NONE = 0;
  static final byte FLAG_PADDED = 8;
  static final byte FLAG_PRIORITY = 32;
  private static final String[] FRAME_NAMES = { "DATA", "HEADERS", "PRIORITY", "RST_STREAM", "SETTINGS", "PUSH_PROMISE", "PING", "GOAWAY", "WINDOW_UPDATE", "CONTINUATION" };
  static final int INITIAL_MAX_FRAME_SIZE = 16384;
  static final byte TYPE_CONTINUATION = 9;
  static final byte TYPE_DATA = 0;
  static final byte TYPE_GOAWAY = 7;
  static final byte TYPE_HEADERS = 1;
  static final byte TYPE_PING = 6;
  static final byte TYPE_PRIORITY = 2;
  static final byte TYPE_PUSH_PROMISE = 5;
  static final byte TYPE_RST_STREAM = 3;
  static final byte TYPE_SETTINGS = 4;
  static final byte TYPE_WINDOW_UPDATE = 8;

  static
  {
    FLAGS = new String[64];
    BINARY = new String[256];
    int k = 0;
    int i = 0;
    while (i < BINARY.length)
    {
      BINARY[i] = Util.format("%8s", new Object[] { Integer.toBinaryString(i) }).replace(' ', '0');
      i += 1;
    }
    FLAGS[0] = "";
    FLAGS[1] = "END_STREAM";
    int[] arrayOfInt = new int[1];
    arrayOfInt[0] = 1;
    FLAGS[8] = "PADDED";
    int j = arrayOfInt.length;
    i = 0;
    Object localObject2;
    while (i < j)
    {
      m = arrayOfInt[i];
      localObject1 = FLAGS;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(FLAGS[m]);
      ((StringBuilder)localObject2).append("|PADDED");
      localObject1[(m | 0x8)] = ((StringBuilder)localObject2).toString();
      i += 1;
    }
    FLAGS[4] = "END_HEADERS";
    FLAGS[32] = "PRIORITY";
    FLAGS[36] = "END_HEADERS|PRIORITY";
    Object localObject1 = new int[3];
    Object tmp264_262 = localObject1;
    tmp264_262[0] = 4;
    Object tmp268_264 = tmp264_262;
    tmp268_264[1] = 32;
    Object tmp273_268 = tmp268_264;
    tmp273_268[2] = 36;
    tmp273_268;
    int m = localObject1.length;
    i = 0;
    while (true)
    {
      j = k;
      if (i >= m)
        break;
      int n = localObject1[i];
      int i1 = arrayOfInt.length;
      j = 0;
      while (j < i1)
      {
        int i2 = arrayOfInt[j];
        localObject2 = FLAGS;
        int i3 = i2 | n;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(FLAGS[i2]);
        localStringBuilder.append('|');
        localStringBuilder.append(FLAGS[n]);
        localObject2[i3] = localStringBuilder.toString();
        localObject2 = FLAGS;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append(FLAGS[i2]);
        localStringBuilder.append('|');
        localStringBuilder.append(FLAGS[n]);
        localStringBuilder.append("|PADDED");
        localObject2[(i3 | 0x8)] = localStringBuilder.toString();
        j += 1;
      }
      i += 1;
    }
    while (j < FLAGS.length)
    {
      if (FLAGS[j] == null)
        FLAGS[j] = BINARY[j];
      j += 1;
    }
  }

  static String formatFlags(byte paramByte1, byte paramByte2)
  {
    if (paramByte2 == 0)
      return "";
    switch (paramByte1)
    {
    case 5:
    default:
      if (paramByte2 < FLAGS.length)
        str = FLAGS[paramByte2];
      break;
    case 4:
    case 6:
      if (paramByte2 == 1)
        return "ACK";
      return BINARY[paramByte2];
    case 2:
    case 3:
    case 7:
    case 8:
      return BINARY[paramByte2];
    }
    String str = BINARY[paramByte2];
    if ((paramByte1 == 5) && ((paramByte2 & 0x4) != 0))
      return str.replace("HEADERS", "PUSH_PROMISE");
    if ((paramByte1 == 0) && ((paramByte2 & 0x20) != 0))
      return str.replace("PRIORITY", "COMPRESSED");
    return str;
  }

  static String frameLog(boolean paramBoolean, int paramInt1, int paramInt2, byte paramByte1, byte paramByte2)
  {
    String str1;
    if (paramByte1 < FRAME_NAMES.length)
      str1 = FRAME_NAMES[paramByte1];
    else
      str1 = Util.format("0x%02x", new Object[] { Byte.valueOf(paramByte1) });
    String str3 = formatFlags(paramByte1, paramByte2);
    String str2;
    if (paramBoolean)
      str2 = "<<";
    else
      str2 = ">>";
    return Util.format("%s 0x%08x %5d %-13s %s", new Object[] { str2, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), str1, str3 });
  }

  static IllegalArgumentException illegalArgument(String paramString, Object[] paramArrayOfObject)
  {
    throw new IllegalArgumentException(Util.format(paramString, paramArrayOfObject));
  }

  static IOException ioException(String paramString, Object[] paramArrayOfObject)
    throws IOException
  {
    throw new IOException(Util.format(paramString, paramArrayOfObject));
  }
}