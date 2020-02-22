package okio;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public final class HashingSource extends ForwardingSource
{
  private final Mac mac;
  private final MessageDigest messageDigest;

  private HashingSource(Source paramSource, String paramString)
  {
    super(paramSource);
    try
    {
      this.messageDigest = MessageDigest.getInstance(paramString);
      this.mac = null;
      return;
      label19: throw new AssertionError();
    }
    catch (NoSuchAlgorithmException paramSource)
    {
      break label19;
    }
  }

  private HashingSource(Source paramSource, ByteString paramByteString, String paramString)
  {
    super(paramSource);
    try
    {
      this.mac = Mac.getInstance(paramString);
      this.mac.init(new SecretKeySpec(paramByteString.toByteArray(), paramString));
      this.messageDigest = null;
      return;
    }
    catch (InvalidKeyException paramSource)
    {
      throw new IllegalArgumentException(paramSource);
      throw new AssertionError();
    }
    catch (NoSuchAlgorithmException paramSource)
    {
      label48: break label48;
    }
  }

  public static HashingSource hmacSha1(Source paramSource, ByteString paramByteString)
  {
    return new HashingSource(paramSource, paramByteString, "HmacSHA1");
  }

  public static HashingSource hmacSha256(Source paramSource, ByteString paramByteString)
  {
    return new HashingSource(paramSource, paramByteString, "HmacSHA256");
  }

  public static HashingSource md5(Source paramSource)
  {
    return new HashingSource(paramSource, "MD5");
  }

  public static HashingSource sha1(Source paramSource)
  {
    return new HashingSource(paramSource, "SHA-1");
  }

  public static HashingSource sha256(Source paramSource)
  {
    return new HashingSource(paramSource, "SHA-256");
  }

  public final ByteString hash()
  {
    byte[] arrayOfByte;
    if (this.messageDigest != null)
      arrayOfByte = this.messageDigest.digest();
    else
      arrayOfByte = this.mac.doFinal();
    return ByteString.of(arrayOfByte);
  }

  public long read(Buffer paramBuffer, long paramLong)
    throws IOException
  {
    long l4 = super.read(paramBuffer, paramLong);
    if (l4 != -1L)
    {
      long l3 = paramBuffer.size - l4;
      paramLong = paramBuffer.size;
      Segment localSegment1 = paramBuffer.head;
      long l1;
      long l2;
      Segment localSegment2;
      while (true)
      {
        l1 = l3;
        l2 = paramLong;
        localSegment2 = localSegment1;
        if (paramLong <= l3)
          break;
        localSegment1 = localSegment1.prev;
        paramLong -= localSegment1.limit - localSegment1.pos;
      }
      while (l2 < paramBuffer.size)
      {
        int i = (int)(localSegment2.pos + l1 - l2);
        if (this.messageDigest != null)
          this.messageDigest.update(localSegment2.data, i, localSegment2.limit - i);
        else
          this.mac.update(localSegment2.data, i, localSegment2.limit - i);
        l1 = localSegment2.limit - localSegment2.pos + l2;
        localSegment2 = localSegment2.next;
        l2 = l1;
      }
    }
    return l4;
  }
}