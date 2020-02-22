package org.apache.http.impl.auth;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.util.EncodingUtils;

final class NTLMEngineImpl
  implements NTLMEngine
{
  static final String DEFAULT_CHARSET = "ASCII";
  protected static final int FLAG_NEGOTIATE_128 = 536870912;
  protected static final int FLAG_NEGOTIATE_ALWAYS_SIGN = 32768;
  protected static final int FLAG_NEGOTIATE_KEY_EXCH = 1073741824;
  protected static final int FLAG_NEGOTIATE_NTLM = 512;
  protected static final int FLAG_NEGOTIATE_NTLM2 = 524288;
  protected static final int FLAG_NEGOTIATE_SEAL = 32;
  protected static final int FLAG_NEGOTIATE_SIGN = 16;
  protected static final int FLAG_TARGET_DESIRED = 4;
  protected static final int FLAG_UNICODE_ENCODING = 1;
  private static final SecureRandom RND_GEN;
  private static byte[] SIGNATURE;
  private String credentialCharset = "ASCII";

  static
  {
    try
    {
      Object localObject = SecureRandom.getInstance("SHA1PRNG");
      break label11;
      label9: localObject = null;
      label11: RND_GEN = (SecureRandom)localObject;
      localObject = EncodingUtils.getBytes("NTLMSSP", "ASCII");
      SIGNATURE = new byte[localObject.length + 1];
      System.arraycopy(localObject, 0, SIGNATURE, 0, localObject.length);
      SIGNATURE[localObject.length] = 0;
      return;
    }
    catch (Exception localException)
    {
      break label9;
    }
  }

  static int F(int paramInt1, int paramInt2, int paramInt3)
  {
    return paramInt1 & paramInt3 | paramInt2 & paramInt1;
  }

  static int G(int paramInt1, int paramInt2, int paramInt3)
  {
    return paramInt1 & paramInt3 | paramInt1 & paramInt2 | paramInt2 & paramInt3;
  }

  static int H(int paramInt1, int paramInt2, int paramInt3)
  {
    return paramInt1 ^ paramInt2 ^ paramInt3;
  }

  private static String convertDomain(String paramString)
  {
    return stripDotSuffix(paramString);
  }

  private static String convertHost(String paramString)
  {
    return stripDotSuffix(paramString);
  }

  private static byte[] createBlob(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    byte[] arrayOfByte1 = new byte[4];
    byte[] tmp7_5 = arrayOfByte1;
    tmp7_5[0] = 1;
    byte[] tmp12_7 = tmp7_5;
    tmp12_7[1] = 1;
    byte[] tmp17_12 = tmp12_7;
    tmp17_12[2] = 0;
    byte[] tmp22_17 = tmp17_12;
    tmp22_17[3] = 0;
    tmp22_17;
    byte[] arrayOfByte2 = new byte[4];
    byte[] tmp35_33 = arrayOfByte2;
    tmp35_33[0] = 0;
    byte[] tmp40_35 = tmp35_33;
    tmp40_35[1] = 0;
    byte[] tmp45_40 = tmp40_35;
    tmp45_40[2] = 0;
    byte[] tmp50_45 = tmp45_40;
    tmp50_45[3] = 0;
    tmp50_45;
    byte[] arrayOfByte3 = new byte[4];
    byte[] tmp63_61 = arrayOfByte3;
    tmp63_61[0] = 0;
    byte[] tmp68_63 = tmp63_61;
    tmp68_63[1] = 0;
    byte[] tmp73_68 = tmp68_63;
    tmp73_68[2] = 0;
    byte[] tmp78_73 = tmp73_68;
    tmp78_73[3] = 0;
    tmp78_73;
    long l = System.currentTimeMillis();
    byte[] arrayOfByte4 = new byte[8];
    l = (l + 11644473600000L) * 10000L;
    int i = 0;
    while (i < 8)
    {
      arrayOfByte4[i] = ((byte)(int)l);
      l >>>= 8;
      i += 1;
    }
    byte[] arrayOfByte5 = new byte[arrayOfByte1.length + arrayOfByte2.length + arrayOfByte4.length + 8 + arrayOfByte3.length + paramArrayOfByte2.length];
    System.arraycopy(arrayOfByte1, 0, arrayOfByte5, 0, arrayOfByte1.length);
    i = arrayOfByte1.length + 0;
    System.arraycopy(arrayOfByte2, 0, arrayOfByte5, i, arrayOfByte2.length);
    i += arrayOfByte2.length;
    System.arraycopy(arrayOfByte4, 0, arrayOfByte5, i, arrayOfByte4.length);
    i += arrayOfByte4.length;
    System.arraycopy(paramArrayOfByte1, 0, arrayOfByte5, i, 8);
    i += 8;
    System.arraycopy(arrayOfByte3, 0, arrayOfByte5, i, arrayOfByte3.length);
    System.arraycopy(paramArrayOfByte2, 0, arrayOfByte5, i + arrayOfByte3.length, paramArrayOfByte2.length);
    return arrayOfByte5;
  }

  private static Key createDESKey(byte[] paramArrayOfByte, int paramInt)
  {
    byte[] arrayOfByte = new byte[7];
    System.arraycopy(paramArrayOfByte, paramInt, arrayOfByte, 0, 7);
    paramArrayOfByte = new byte[8];
    paramArrayOfByte[0] = arrayOfByte[0];
    paramArrayOfByte[1] = ((byte)(arrayOfByte[0] << 7 | (arrayOfByte[1] & 0xFF) >>> 1));
    paramArrayOfByte[2] = ((byte)(arrayOfByte[1] << 6 | (arrayOfByte[2] & 0xFF) >>> 2));
    paramArrayOfByte[3] = ((byte)(arrayOfByte[2] << 5 | (arrayOfByte[3] & 0xFF) >>> 3));
    paramArrayOfByte[4] = ((byte)(arrayOfByte[3] << 4 | (arrayOfByte[4] & 0xFF) >>> 4));
    paramArrayOfByte[5] = ((byte)(arrayOfByte[4] << 3 | (arrayOfByte[5] & 0xFF) >>> 5));
    paramArrayOfByte[6] = ((byte)(arrayOfByte[5] << 2 | (arrayOfByte[6] & 0xFF) >>> 6));
    paramArrayOfByte[7] = ((byte)(arrayOfByte[6] << 1));
    oddParity(paramArrayOfByte);
    return new SecretKeySpec(paramArrayOfByte, "DES");
  }

  static byte[] getLMResponse(String paramString, byte[] paramArrayOfByte)
    throws NTLMEngineException
  {
    return lmResponse(lmHash(paramString), paramArrayOfByte);
  }

  static byte[] getLMv2Response(String paramString1, String paramString2, String paramString3, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws NTLMEngineException
  {
    return lmv2Response(ntlmv2Hash(paramString1, paramString2, paramString3), paramArrayOfByte1, paramArrayOfByte2);
  }

  static byte[] getNTLM2SessionResponse(String paramString, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws NTLMEngineException
  {
    try
    {
      paramString = ntlmHash(paramString);
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(paramArrayOfByte1);
      localMessageDigest.update(paramArrayOfByte2);
      paramArrayOfByte1 = localMessageDigest.digest();
      paramArrayOfByte2 = new byte[8];
      System.arraycopy(paramArrayOfByte1, 0, paramArrayOfByte2, 0, 8);
      paramString = lmResponse(paramString, paramArrayOfByte2);
      return paramString;
    }
    catch (Exception paramString)
    {
      if ((paramString instanceof NTLMEngineException))
        throw ((NTLMEngineException)paramString);
    }
    throw new NTLMEngineException(paramString.getMessage(), paramString);
  }

  static byte[] getNTLMResponse(String paramString, byte[] paramArrayOfByte)
    throws NTLMEngineException
  {
    return lmResponse(ntlmHash(paramString), paramArrayOfByte);
  }

  static byte[] getNTLMv2Response(String paramString1, String paramString2, String paramString3, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
    throws NTLMEngineException
  {
    return lmv2Response(ntlmv2Hash(paramString1, paramString2, paramString3), paramArrayOfByte1, createBlob(paramArrayOfByte2, paramArrayOfByte3));
  }

  private static byte[] lmHash(String paramString)
    throws NTLMEngineException
  {
    try
    {
      paramString = paramString.toUpperCase().getBytes("US-ASCII");
      int i = Math.min(paramString.length, 14);
      Object localObject = new byte[14];
      System.arraycopy(paramString, 0, localObject, 0, i);
      paramString = createDESKey((byte[])localObject, 0);
      localObject = createDESKey((byte[])localObject, 7);
      byte[] arrayOfByte = "KGS!@#$%".getBytes("US-ASCII");
      Cipher localCipher = Cipher.getInstance("DES/ECB/NoPadding");
      localCipher.init(1, paramString);
      paramString = localCipher.doFinal(arrayOfByte);
      localCipher.init(1, (Key)localObject);
      localObject = localCipher.doFinal(arrayOfByte);
      arrayOfByte = new byte[16];
      System.arraycopy(paramString, 0, arrayOfByte, 0, 8);
      System.arraycopy(localObject, 0, arrayOfByte, 8, 8);
      return arrayOfByte;
    }
    catch (Exception paramString)
    {
    }
    throw new NTLMEngineException(paramString.getMessage(), paramString);
  }

  private static byte[] lmResponse(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
    throws NTLMEngineException
  {
    try
    {
      Object localObject2 = new byte[21];
      System.arraycopy(paramArrayOfByte1, 0, localObject2, 0, 16);
      paramArrayOfByte1 = createDESKey((byte[])localObject2, 0);
      Object localObject1 = createDESKey((byte[])localObject2, 7);
      localObject2 = createDESKey((byte[])localObject2, 14);
      Cipher localCipher = Cipher.getInstance("DES/ECB/NoPadding");
      localCipher.init(1, paramArrayOfByte1);
      paramArrayOfByte1 = localCipher.doFinal(paramArrayOfByte2);
      localCipher.init(1, (Key)localObject1);
      localObject1 = localCipher.doFinal(paramArrayOfByte2);
      localCipher.init(1, (Key)localObject2);
      paramArrayOfByte2 = localCipher.doFinal(paramArrayOfByte2);
      localObject2 = new byte[24];
      System.arraycopy(paramArrayOfByte1, 0, localObject2, 0, 8);
      System.arraycopy(localObject1, 0, localObject2, 8, 8);
      System.arraycopy(paramArrayOfByte2, 0, localObject2, 16, 8);
      return localObject2;
    }
    catch (Exception paramArrayOfByte1)
    {
    }
    throw new NTLMEngineException(paramArrayOfByte1.getMessage(), paramArrayOfByte1);
  }

  private static byte[] lmv2Response(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3)
    throws NTLMEngineException
  {
    paramArrayOfByte1 = new HMACMD5(paramArrayOfByte1);
    paramArrayOfByte1.update(paramArrayOfByte2);
    paramArrayOfByte1.update(paramArrayOfByte3);
    paramArrayOfByte1 = paramArrayOfByte1.getOutput();
    paramArrayOfByte2 = new byte[paramArrayOfByte1.length + paramArrayOfByte3.length];
    System.arraycopy(paramArrayOfByte1, 0, paramArrayOfByte2, 0, paramArrayOfByte1.length);
    System.arraycopy(paramArrayOfByte3, 0, paramArrayOfByte2, paramArrayOfByte1.length, paramArrayOfByte3.length);
    return paramArrayOfByte2;
  }

  private static byte[] makeNTLM2RandomChallenge()
    throws NTLMEngineException
  {
    if (RND_GEN == null)
      throw new NTLMEngineException("Random generator not available");
    byte[] arrayOfByte = new byte[24];
    synchronized (RND_GEN)
    {
      RND_GEN.nextBytes(arrayOfByte);
      Arrays.fill(arrayOfByte, 8, 24, (byte)0);
      return arrayOfByte;
    }
  }

  private static byte[] makeRandomChallenge()
    throws NTLMEngineException
  {
    if (RND_GEN == null)
      throw new NTLMEngineException("Random generator not available");
    byte[] arrayOfByte = new byte[8];
    synchronized (RND_GEN)
    {
      RND_GEN.nextBytes(arrayOfByte);
      return arrayOfByte;
    }
  }

  private static byte[] ntlmHash(String paramString)
    throws NTLMEngineException
  {
    Object localObject;
    try
    {
      paramString = paramString.getBytes("UnicodeLittleUnmarked");
      localObject = new MD4();
      ((MD4)localObject).update(paramString);
      paramString = ((MD4)localObject).getOutput();
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Unicode not supported: ");
      ((StringBuilder)localObject).append(paramString.getMessage());
    }
    throw new NTLMEngineException(((StringBuilder)localObject).toString(), paramString);
  }

  private static byte[] ntlmv2Hash(String paramString1, String paramString2, String paramString3)
    throws NTLMEngineException
  {
    try
    {
      paramString3 = new HMACMD5(ntlmHash(paramString3));
      paramString3.update(paramString2.toUpperCase().getBytes("UnicodeLittleUnmarked"));
      paramString3.update(paramString1.getBytes("UnicodeLittleUnmarked"));
      paramString1 = paramString3.getOutput();
      return paramString1;
    }
    catch (UnsupportedEncodingException paramString1)
    {
      paramString2 = new StringBuilder();
      paramString2.append("Unicode not supported! ");
      paramString2.append(paramString1.getMessage());
    }
    throw new NTLMEngineException(paramString2.toString(), paramString1);
  }

  private static void oddParity(byte[] paramArrayOfByte)
  {
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      int j = paramArrayOfByte[i];
      if (((j >>> 1 ^ (j >>> 7 ^ j >>> 6 ^ j >>> 5 ^ j >>> 4 ^ j >>> 3 ^ j >>> 2)) & 0x1) == 0)
        j = 1;
      else
        j = 0;
      if (j != 0)
        paramArrayOfByte[i] = ((byte)(paramArrayOfByte[i] | 0x1));
      else
        paramArrayOfByte[i] = ((byte)(paramArrayOfByte[i] & 0xFFFFFFFE));
      i += 1;
    }
  }

  private static byte[] readSecurityBuffer(byte[] paramArrayOfByte, int paramInt)
    throws NTLMEngineException
  {
    int i = readUShort(paramArrayOfByte, paramInt);
    paramInt = readULong(paramArrayOfByte, paramInt + 4);
    if (paramArrayOfByte.length < paramInt + i)
      throw new NTLMEngineException("NTLM authentication - buffer too small for data item");
    byte[] arrayOfByte = new byte[i];
    System.arraycopy(paramArrayOfByte, paramInt, arrayOfByte, 0, i);
    return arrayOfByte;
  }

  private static int readULong(byte[] paramArrayOfByte, int paramInt)
    throws NTLMEngineException
  {
    if (paramArrayOfByte.length < paramInt + 4)
      throw new NTLMEngineException("NTLM authentication - buffer too small for DWORD");
    int i = paramArrayOfByte[paramInt];
    int j = paramArrayOfByte[(paramInt + 1)];
    int k = paramArrayOfByte[(paramInt + 2)];
    return (paramArrayOfByte[(paramInt + 3)] & 0xFF) << 24 | (i & 0xFF | (j & 0xFF) << 8 | (k & 0xFF) << 16);
  }

  private static int readUShort(byte[] paramArrayOfByte, int paramInt)
    throws NTLMEngineException
  {
    if (paramArrayOfByte.length < paramInt + 2)
      throw new NTLMEngineException("NTLM authentication - buffer too small for WORD");
    int i = paramArrayOfByte[paramInt];
    return (paramArrayOfByte[(paramInt + 1)] & 0xFF) << 8 | i & 0xFF;
  }

  static int rotintlft(int paramInt1, int paramInt2)
  {
    return paramInt1 >>> 32 - paramInt2 | paramInt1 << paramInt2;
  }

  private static String stripDotSuffix(String paramString)
  {
    int i = paramString.indexOf(".");
    if (i != -1)
      return paramString.substring(0, i);
    return paramString;
  }

  static void writeULong(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    paramArrayOfByte[paramInt2] = ((byte)(paramInt1 & 0xFF));
    paramArrayOfByte[(paramInt2 + 1)] = ((byte)(paramInt1 >> 8 & 0xFF));
    paramArrayOfByte[(paramInt2 + 2)] = ((byte)(paramInt1 >> 16 & 0xFF));
    paramArrayOfByte[(paramInt2 + 3)] = ((byte)(paramInt1 >> 24 & 0xFF));
  }

  public String generateType1Msg(String paramString1, String paramString2)
    throws NTLMEngineException
  {
    return getType1Message(paramString2, paramString1);
  }

  public String generateType3Msg(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
    throws NTLMEngineException
  {
    paramString5 = new Type2Message(paramString5);
    return getType3Message(paramString1, paramString2, paramString4, paramString3, paramString5.getChallenge(), paramString5.getFlags(), paramString5.getTarget(), paramString5.getTargetInfo());
  }

  String getCredentialCharset()
  {
    return this.credentialCharset;
  }

  final String getResponseFor(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
    throws NTLMEngineException
  {
    if ((paramString1 != null) && (!paramString1.trim().equals("")))
    {
      paramString1 = new Type2Message(paramString1);
      return getType3Message(paramString2, paramString3, paramString4, paramString5, paramString1.getChallenge(), paramString1.getFlags(), paramString1.getTarget(), paramString1.getTargetInfo());
    }
    return getType1Message(paramString4, paramString5);
  }

  String getType1Message(String paramString1, String paramString2)
    throws NTLMEngineException
  {
    return new Type1Message(paramString2, paramString1).getResponse();
  }

  String getType3Message(String paramString1, String paramString2, String paramString3, String paramString4, byte[] paramArrayOfByte1, int paramInt, String paramString5, byte[] paramArrayOfByte2)
    throws NTLMEngineException
  {
    return new Type3Message(paramString4, paramString3, paramString1, paramString2, paramArrayOfByte1, paramInt, paramString5, paramArrayOfByte2).getResponse();
  }

  void setCredentialCharset(String paramString)
  {
    this.credentialCharset = paramString;
  }

  static class HMACMD5
  {
    protected byte[] ipad;
    protected MessageDigest md5;
    protected byte[] opad;

    HMACMD5(byte[] paramArrayOfByte)
      throws NTLMEngineException
    {
      Object localObject;
      try
      {
        this.md5 = MessageDigest.getInstance("MD5");
        this.ipad = new byte[64];
        this.opad = new byte[64];
        int i = paramArrayOfByte.length;
        int j = i;
        localObject = paramArrayOfByte;
        if (i > 64)
        {
          this.md5.update(paramArrayOfByte);
          localObject = this.md5.digest();
          j = localObject.length;
        }
        i = 0;
        int k;
        while (true)
        {
          k = i;
          if (i >= j)
            break;
          this.ipad[i] = ((byte)(0x36 ^ localObject[i]));
          this.opad[i] = ((byte)(0x5C ^ localObject[i]));
          i += 1;
        }
        while (k < 64)
        {
          this.ipad[k] = 54;
          this.opad[k] = 92;
          k += 1;
        }
        this.md5.reset();
        this.md5.update(this.ipad);
        return;
      }
      catch (Exception paramArrayOfByte)
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Error getting md5 message digest implementation: ");
        ((StringBuilder)localObject).append(paramArrayOfByte.getMessage());
      }
      throw new NTLMEngineException(((StringBuilder)localObject).toString(), paramArrayOfByte);
    }

    byte[] getOutput()
    {
      byte[] arrayOfByte = this.md5.digest();
      this.md5.update(this.opad);
      return this.md5.digest(arrayOfByte);
    }

    void update(byte[] paramArrayOfByte)
    {
      this.md5.update(paramArrayOfByte);
    }

    void update(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    {
      this.md5.update(paramArrayOfByte, paramInt1, paramInt2);
    }
  }

  static class MD4
  {
    protected int A = 1732584193;
    protected int B = -271733879;
    protected int C = -1732584194;
    protected int D = 271733878;
    protected long count = 0L;
    protected byte[] dataBuffer = new byte[64];

    byte[] getOutput()
    {
      int i = (int)(this.count & 0x3F);
      if (i < 56)
        i = 56 - i;
      else
        i = 120 - i;
      byte[] arrayOfByte = new byte[i + 8];
      arrayOfByte[0] = -128;
      int j = 0;
      while (j < 8)
      {
        arrayOfByte[(i + j)] = ((byte)(int)(this.count * 8L >>> j * 8));
        j += 1;
      }
      update(arrayOfByte);
      arrayOfByte = new byte[16];
      NTLMEngineImpl.writeULong(arrayOfByte, this.A, 0);
      NTLMEngineImpl.writeULong(arrayOfByte, this.B, 4);
      NTLMEngineImpl.writeULong(arrayOfByte, this.C, 8);
      NTLMEngineImpl.writeULong(arrayOfByte, this.D, 12);
      return arrayOfByte;
    }

    protected void processBuffer()
    {
      int[] arrayOfInt = new int[16];
      int i = 0;
      while (i < 16)
      {
        byte[] arrayOfByte = this.dataBuffer;
        j = i * 4;
        arrayOfInt[i] = ((arrayOfByte[j] & 0xFF) + ((this.dataBuffer[(j + 1)] & 0xFF) << 8) + ((this.dataBuffer[(j + 2)] & 0xFF) << 16) + ((this.dataBuffer[(j + 3)] & 0xFF) << 24));
        i += 1;
      }
      i = this.A;
      int j = this.B;
      int k = this.C;
      int m = this.D;
      round1(arrayOfInt);
      round2(arrayOfInt);
      round3(arrayOfInt);
      this.A += i;
      this.B += j;
      this.C += k;
      this.D += m;
    }

    protected void round1(int[] paramArrayOfInt)
    {
      this.A = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.F(this.B, this.C, this.D) + paramArrayOfInt[0], 3);
      this.D = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.F(this.A, this.B, this.C) + paramArrayOfInt[1], 7);
      this.C = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.F(this.D, this.A, this.B) + paramArrayOfInt[2], 11);
      this.B = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.F(this.C, this.D, this.A) + paramArrayOfInt[3], 19);
      this.A = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.F(this.B, this.C, this.D) + paramArrayOfInt[4], 3);
      this.D = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.F(this.A, this.B, this.C) + paramArrayOfInt[5], 7);
      this.C = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.F(this.D, this.A, this.B) + paramArrayOfInt[6], 11);
      this.B = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.F(this.C, this.D, this.A) + paramArrayOfInt[7], 19);
      this.A = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.F(this.B, this.C, this.D) + paramArrayOfInt[8], 3);
      this.D = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.F(this.A, this.B, this.C) + paramArrayOfInt[9], 7);
      this.C = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.F(this.D, this.A, this.B) + paramArrayOfInt[10], 11);
      this.B = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.F(this.C, this.D, this.A) + paramArrayOfInt[11], 19);
      this.A = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.F(this.B, this.C, this.D) + paramArrayOfInt[12], 3);
      this.D = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.F(this.A, this.B, this.C) + paramArrayOfInt[13], 7);
      this.C = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.F(this.D, this.A, this.B) + paramArrayOfInt[14], 11);
      this.B = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.F(this.C, this.D, this.A) + paramArrayOfInt[15], 19);
    }

    protected void round2(int[] paramArrayOfInt)
    {
      this.A = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.G(this.B, this.C, this.D) + paramArrayOfInt[0] + 1518500249, 3);
      this.D = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.G(this.A, this.B, this.C) + paramArrayOfInt[4] + 1518500249, 5);
      this.C = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.G(this.D, this.A, this.B) + paramArrayOfInt[8] + 1518500249, 9);
      this.B = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.G(this.C, this.D, this.A) + paramArrayOfInt[12] + 1518500249, 13);
      this.A = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.G(this.B, this.C, this.D) + paramArrayOfInt[1] + 1518500249, 3);
      this.D = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.G(this.A, this.B, this.C) + paramArrayOfInt[5] + 1518500249, 5);
      this.C = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.G(this.D, this.A, this.B) + paramArrayOfInt[9] + 1518500249, 9);
      this.B = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.G(this.C, this.D, this.A) + paramArrayOfInt[13] + 1518500249, 13);
      this.A = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.G(this.B, this.C, this.D) + paramArrayOfInt[2] + 1518500249, 3);
      this.D = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.G(this.A, this.B, this.C) + paramArrayOfInt[6] + 1518500249, 5);
      this.C = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.G(this.D, this.A, this.B) + paramArrayOfInt[10] + 1518500249, 9);
      this.B = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.G(this.C, this.D, this.A) + paramArrayOfInt[14] + 1518500249, 13);
      this.A = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.G(this.B, this.C, this.D) + paramArrayOfInt[3] + 1518500249, 3);
      this.D = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.G(this.A, this.B, this.C) + paramArrayOfInt[7] + 1518500249, 5);
      this.C = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.G(this.D, this.A, this.B) + paramArrayOfInt[11] + 1518500249, 9);
      this.B = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.G(this.C, this.D, this.A) + paramArrayOfInt[15] + 1518500249, 13);
    }

    protected void round3(int[] paramArrayOfInt)
    {
      this.A = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.H(this.B, this.C, this.D) + paramArrayOfInt[0] + 1859775393, 3);
      this.D = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.H(this.A, this.B, this.C) + paramArrayOfInt[8] + 1859775393, 9);
      this.C = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.H(this.D, this.A, this.B) + paramArrayOfInt[4] + 1859775393, 11);
      this.B = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.H(this.C, this.D, this.A) + paramArrayOfInt[12] + 1859775393, 15);
      this.A = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.H(this.B, this.C, this.D) + paramArrayOfInt[2] + 1859775393, 3);
      this.D = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.H(this.A, this.B, this.C) + paramArrayOfInt[10] + 1859775393, 9);
      this.C = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.H(this.D, this.A, this.B) + paramArrayOfInt[6] + 1859775393, 11);
      this.B = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.H(this.C, this.D, this.A) + paramArrayOfInt[14] + 1859775393, 15);
      this.A = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.H(this.B, this.C, this.D) + paramArrayOfInt[1] + 1859775393, 3);
      this.D = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.H(this.A, this.B, this.C) + paramArrayOfInt[9] + 1859775393, 9);
      this.C = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.H(this.D, this.A, this.B) + paramArrayOfInt[5] + 1859775393, 11);
      this.B = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.H(this.C, this.D, this.A) + paramArrayOfInt[13] + 1859775393, 15);
      this.A = NTLMEngineImpl.rotintlft(this.A + NTLMEngineImpl.H(this.B, this.C, this.D) + paramArrayOfInt[3] + 1859775393, 3);
      this.D = NTLMEngineImpl.rotintlft(this.D + NTLMEngineImpl.H(this.A, this.B, this.C) + paramArrayOfInt[11] + 1859775393, 9);
      this.C = NTLMEngineImpl.rotintlft(this.C + NTLMEngineImpl.H(this.D, this.A, this.B) + paramArrayOfInt[7] + 1859775393, 11);
      this.B = NTLMEngineImpl.rotintlft(this.B + NTLMEngineImpl.H(this.C, this.D, this.A) + paramArrayOfInt[15] + 1859775393, 15);
    }

    void update(byte[] paramArrayOfByte)
    {
      int j = (int)(this.count & 0x3F);
      int i = 0;
      int k;
      while (paramArrayOfByte.length - i + j >= this.dataBuffer.length)
      {
        k = this.dataBuffer.length - j;
        System.arraycopy(paramArrayOfByte, i, this.dataBuffer, j, k);
        this.count += k;
        i += k;
        processBuffer();
        j = 0;
      }
      if (i < paramArrayOfByte.length)
      {
        k = paramArrayOfByte.length - i;
        System.arraycopy(paramArrayOfByte, i, this.dataBuffer, j, k);
        this.count += k;
      }
    }
  }

  static class NTLMMessage
  {
    private int currentOutputPosition;
    private byte[] messageContents = null;

    NTLMMessage()
    {
      this.currentOutputPosition = 0;
    }

    NTLMMessage(String paramString, int paramInt)
      throws NTLMEngineException
    {
      int i = 0;
      this.currentOutputPosition = 0;
      this.messageContents = Base64.decodeBase64(EncodingUtils.getBytes(paramString, "ASCII"));
      if (this.messageContents.length < NTLMEngineImpl.SIGNATURE.length)
        throw new NTLMEngineException("NTLM message decoding error - packet too short");
      while (i < NTLMEngineImpl.SIGNATURE.length)
      {
        if (this.messageContents[i] != NTLMEngineImpl.SIGNATURE[i])
          throw new NTLMEngineException("NTLM message expected - instead got unrecognized bytes");
        i += 1;
      }
      i = readULong(NTLMEngineImpl.SIGNATURE.length);
      if (i != paramInt)
      {
        paramString = new StringBuilder();
        paramString.append("NTLM type ");
        paramString.append(Integer.toString(paramInt));
        paramString.append(" message expected - instead got type ");
        paramString.append(Integer.toString(i));
        throw new NTLMEngineException(paramString.toString());
      }
      this.currentOutputPosition = this.messageContents.length;
    }

    protected void addByte(byte paramByte)
    {
      this.messageContents[this.currentOutputPosition] = paramByte;
      this.currentOutputPosition += 1;
    }

    protected void addBytes(byte[] paramArrayOfByte)
    {
      int i = 0;
      while (i < paramArrayOfByte.length)
      {
        this.messageContents[this.currentOutputPosition] = paramArrayOfByte[i];
        this.currentOutputPosition += 1;
        i += 1;
      }
    }

    protected void addULong(int paramInt)
    {
      addByte((byte)(paramInt & 0xFF));
      addByte((byte)(paramInt >> 8 & 0xFF));
      addByte((byte)(paramInt >> 16 & 0xFF));
      addByte((byte)(paramInt >> 24 & 0xFF));
    }

    protected void addUShort(int paramInt)
    {
      addByte((byte)(paramInt & 0xFF));
      addByte((byte)(paramInt >> 8 & 0xFF));
    }

    protected int getMessageLength()
    {
      return this.currentOutputPosition;
    }

    protected int getPreambleLength()
    {
      return NTLMEngineImpl.SIGNATURE.length + 4;
    }

    String getResponse()
    {
      if (this.messageContents.length > this.currentOutputPosition)
      {
        byte[] arrayOfByte2 = new byte[this.currentOutputPosition];
        int i = 0;
        while (true)
        {
          arrayOfByte1 = arrayOfByte2;
          if (i >= this.currentOutputPosition)
            break;
          arrayOfByte2[i] = this.messageContents[i];
          i += 1;
        }
      }
      byte[] arrayOfByte1 = this.messageContents;
      return EncodingUtils.getAsciiString(Base64.encodeBase64(arrayOfByte1));
    }

    protected void prepareResponse(int paramInt1, int paramInt2)
    {
      this.messageContents = new byte[paramInt1];
      this.currentOutputPosition = 0;
      addBytes(NTLMEngineImpl.SIGNATURE);
      addULong(paramInt2);
    }

    protected byte readByte(int paramInt)
      throws NTLMEngineException
    {
      if (this.messageContents.length < paramInt + 1)
        throw new NTLMEngineException("NTLM: Message too short");
      return this.messageContents[paramInt];
    }

    protected void readBytes(byte[] paramArrayOfByte, int paramInt)
      throws NTLMEngineException
    {
      if (this.messageContents.length < paramArrayOfByte.length + paramInt)
        throw new NTLMEngineException("NTLM: Message too short");
      System.arraycopy(this.messageContents, paramInt, paramArrayOfByte, 0, paramArrayOfByte.length);
    }

    protected byte[] readSecurityBuffer(int paramInt)
      throws NTLMEngineException
    {
      return NTLMEngineImpl.readSecurityBuffer(this.messageContents, paramInt);
    }

    protected int readULong(int paramInt)
      throws NTLMEngineException
    {
      return NTLMEngineImpl.readULong(this.messageContents, paramInt);
    }

    protected int readUShort(int paramInt)
      throws NTLMEngineException
    {
      return NTLMEngineImpl.readUShort(this.messageContents, paramInt);
    }
  }

  static class Type1Message extends NTLMEngineImpl.NTLMMessage
  {
    protected byte[] domainBytes;
    protected byte[] hostBytes;

    Type1Message(String paramString1, String paramString2)
      throws NTLMEngineException
    {
      try
      {
        paramString2 = NTLMEngineImpl.convertHost(paramString2);
        paramString1 = NTLMEngineImpl.convertDomain(paramString1);
        this.hostBytes = paramString2.getBytes("UnicodeLittleUnmarked");
        this.domainBytes = paramString1.toUpperCase().getBytes("UnicodeLittleUnmarked");
        return;
      }
      catch (UnsupportedEncodingException paramString1)
      {
        paramString2 = new StringBuilder();
        paramString2.append("Unicode unsupported: ");
        paramString2.append(paramString1.getMessage());
      }
      throw new NTLMEngineException(paramString2.toString(), paramString1);
    }

    String getResponse()
    {
      prepareResponse(this.hostBytes.length + 32 + this.domainBytes.length, 1);
      addULong(537395765);
      addUShort(this.domainBytes.length);
      addUShort(this.domainBytes.length);
      addULong(this.hostBytes.length + 32);
      addUShort(this.hostBytes.length);
      addUShort(this.hostBytes.length);
      addULong(32);
      addBytes(this.hostBytes);
      addBytes(this.domainBytes);
      return super.getResponse();
    }
  }

  static class Type2Message extends NTLMEngineImpl.NTLMMessage
  {
    protected byte[] challenge = new byte[8];
    protected int flags;
    protected String target;
    protected byte[] targetInfo;

    Type2Message(String paramString)
      throws NTLMEngineException
    {
      super(2);
      readBytes(this.challenge, 24);
      this.flags = readULong(20);
      if ((this.flags & 0x1) == 0)
      {
        paramString = new StringBuilder();
        paramString.append("NTLM type 2 message has flags that make no sense: ");
        paramString.append(Integer.toString(this.flags));
        throw new NTLMEngineException(paramString.toString());
      }
      this.target = null;
      if (getMessageLength() >= 20)
      {
        paramString = readSecurityBuffer(12);
        if (paramString.length != 0)
          try
          {
            this.target = new String(paramString, "UnicodeLittleUnmarked");
          }
          catch (UnsupportedEncodingException paramString)
          {
            throw new NTLMEngineException(paramString.getMessage(), paramString);
          }
      }
      this.targetInfo = null;
      if (getMessageLength() >= 48)
      {
        paramString = readSecurityBuffer(40);
        if (paramString.length != 0)
          this.targetInfo = paramString;
      }
    }

    byte[] getChallenge()
    {
      return this.challenge;
    }

    int getFlags()
    {
      return this.flags;
    }

    String getTarget()
    {
      return this.target;
    }

    byte[] getTargetInfo()
    {
      return this.targetInfo;
    }
  }

  static class Type3Message extends NTLMEngineImpl.NTLMMessage
  {
    protected byte[] domainBytes;
    protected byte[] hostBytes;
    protected byte[] lmResp;
    protected byte[] ntResp;
    protected int type2Flags;
    protected byte[] userBytes;

    Type3Message(String paramString1, String paramString2, String paramString3, String paramString4, byte[] paramArrayOfByte1, int paramInt, String paramString5, byte[] paramArrayOfByte2)
      throws NTLMEngineException
    {
      this.type2Flags = paramInt;
      paramString2 = NTLMEngineImpl.convertHost(paramString2);
      paramString1 = NTLMEngineImpl.convertDomain(paramString1);
      if ((paramArrayOfByte2 != null) && (paramString5 != null));
      try
      {
        byte[] arrayOfByte = NTLMEngineImpl.access$600();
        this.ntResp = NTLMEngineImpl.getNTLMv2Response(paramString5, paramString3, paramString4, paramArrayOfByte1, arrayOfByte, paramArrayOfByte2);
        this.lmResp = NTLMEngineImpl.getLMv2Response(paramString5, paramString3, paramString4, paramArrayOfByte1, arrayOfByte);
        break label150;
        if ((paramInt & 0x80000) != 0)
        {
          paramString5 = NTLMEngineImpl.access$700();
          this.ntResp = NTLMEngineImpl.getNTLM2SessionResponse(paramString4, paramArrayOfByte1, paramString5);
          this.lmResp = paramString5;
        }
        else
        {
          this.ntResp = NTLMEngineImpl.getNTLMResponse(paramString4, paramArrayOfByte1);
          this.lmResp = NTLMEngineImpl.getLMResponse(paramString4, paramArrayOfByte1);
          break label150;
          label132: this.ntResp = new byte[0];
          this.lmResp = NTLMEngineImpl.getLMResponse(paramString4, paramArrayOfByte1);
        }
        try
        {
          label150: this.domainBytes = paramString1.toUpperCase().getBytes("UnicodeLittleUnmarked");
          this.hostBytes = paramString2.getBytes("UnicodeLittleUnmarked");
          this.userBytes = paramString3.getBytes("UnicodeLittleUnmarked");
          return;
        }
        catch (UnsupportedEncodingException paramString1)
        {
          paramString2 = new StringBuilder();
          paramString2.append("Unicode not supported: ");
          paramString2.append(paramString1.getMessage());
          throw new NTLMEngineException(paramString2.toString(), paramString1);
        }
      }
      catch (NTLMEngineException paramString5)
      {
        break label132;
      }
    }

    String getResponse()
    {
      int i = this.ntResp.length;
      int j = this.lmResp.length;
      int k = this.domainBytes.length;
      int m = this.hostBytes.length;
      int n = this.userBytes.length;
      int i1 = j + 64;
      int i2 = i1 + i;
      int i3 = i2 + k;
      int i4 = i3 + n;
      int i5 = i4 + m + 0;
      prepareResponse(i5, 3);
      addUShort(j);
      addUShort(j);
      addULong(64);
      addUShort(i);
      addUShort(i);
      addULong(i1);
      addUShort(k);
      addUShort(k);
      addULong(i2);
      addUShort(n);
      addUShort(n);
      addULong(i3);
      addUShort(m);
      addUShort(m);
      addULong(i4);
      addULong(0);
      addULong(i5);
      addULong(this.type2Flags & 0x80000 | 0x20000205 | this.type2Flags & 0x10 | this.type2Flags & 0x20 | this.type2Flags & 0x40000000 | this.type2Flags & 0x8000);
      addBytes(this.lmResp);
      addBytes(this.ntResp);
      addBytes(this.domainBytes);
      addBytes(this.userBytes);
      addBytes(this.hostBytes);
      return super.getResponse();
    }
  }
}