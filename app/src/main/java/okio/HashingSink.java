package okio;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.annotation.Nullable;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public final class HashingSink extends ForwardingSink
{

  @Nullable
  private final Mac mac;

  @Nullable
  private final MessageDigest messageDigest;

  private HashingSink(Sink paramSink, String paramString)
  {
    super(paramSink);
    try
    {
      this.messageDigest = MessageDigest.getInstance(paramString);
      this.mac = null;
      return;
      label19: throw new AssertionError();
    }
    catch (NoSuchAlgorithmException paramSink)
    {
      break label19;
    }
  }

  private HashingSink(Sink paramSink, ByteString paramByteString, String paramString)
  {
    super(paramSink);
    try
    {
      this.mac = Mac.getInstance(paramString);
      this.mac.init(new SecretKeySpec(paramByteString.toByteArray(), paramString));
      this.messageDigest = null;
      return;
    }
    catch (InvalidKeyException paramSink)
    {
      throw new IllegalArgumentException(paramSink);
      throw new AssertionError();
    }
    catch (NoSuchAlgorithmException paramSink)
    {
      label48: break label48;
    }
  }

  public static HashingSink hmacSha1(Sink paramSink, ByteString paramByteString)
  {
    return new HashingSink(paramSink, paramByteString, "HmacSHA1");
  }

  public static HashingSink hmacSha256(Sink paramSink, ByteString paramByteString)
  {
    return new HashingSink(paramSink, paramByteString, "HmacSHA256");
  }

  public static HashingSink hmacSha512(Sink paramSink, ByteString paramByteString)
  {
    return new HashingSink(paramSink, paramByteString, "HmacSHA512");
  }

  public static HashingSink md5(Sink paramSink)
  {
    return new HashingSink(paramSink, "MD5");
  }

  public static HashingSink sha1(Sink paramSink)
  {
    return new HashingSink(paramSink, "SHA-1");
  }

  public static HashingSink sha256(Sink paramSink)
  {
    return new HashingSink(paramSink, "SHA-256");
  }

  public static HashingSink sha512(Sink paramSink)
  {
    return new HashingSink(paramSink, "SHA-512");
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

  public void write(Buffer paramBuffer, long paramLong)
    throws IOException
  {
    Util.checkOffsetAndCount(paramBuffer.size, 0L, paramLong);
    Segment localSegment = paramBuffer.head;
    long l = 0L;
    while (l < paramLong)
    {
      int i = (int)Math.min(paramLong - l, localSegment.limit - localSegment.pos);
      if (this.messageDigest != null)
        this.messageDigest.update(localSegment.data, localSegment.pos, i);
      else
        this.mac.update(localSegment.data, localSegment.pos, i);
      l += i;
      localSegment = localSegment.next;
    }
    super.write(paramBuffer, paramLong);
  }
}