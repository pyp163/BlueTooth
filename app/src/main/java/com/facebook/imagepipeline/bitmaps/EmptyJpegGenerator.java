package com.facebook.imagepipeline.bitmaps;

import com.facebook.common.memory.PooledByteBufferFactory;

public class EmptyJpegGenerator
{
  private static final byte[] EMPTY_JPEG_PREFIX = { -1, -40, -1, -37, 0, 67, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -64, 0, 17, 8 };
  private static final byte[] EMPTY_JPEG_SUFFIX = { 3, 1, 34, 0, 2, 17, 0, 3, 17, 0, -1, -60, 0, 31, 0, 0, 1, 5, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, -1, -60, 0, -75, 16, 0, 2, 1, 3, 3, 2, 4, 3, 5, 5, 4, 4, 0, 0, 1, 125, 1, 2, 3, 0, 4, 17, 5, 18, 33, 49, 65, 6, 19, 81, 97, 7, 34, 113, 20, 50, -127, -111, -95, 8, 35, 66, -79, -63, 21, 82, -47, -16, 36, 51, 98, 114, -126, 9, 10, 22, 23, 24, 25, 26, 37, 38, 39, 40, 41, 42, 52, 53, 54, 55, 56, 57, 58, 67, 68, 69, 70, 71, 72, 73, 74, 83, 84, 85, 86, 87, 88, 89, 90, 99, 100, 101, 102, 103, 104, 105, 106, 115, 116, 117, 118, 119, 120, 121, 122, -125, -124, -123, -122, -121, -120, -119, -118, -110, -109, -108, -107, -106, -105, -104, -103, -102, -94, -93, -92, -91, -90, -89, -88, -87, -86, -78, -77, -76, -75, -74, -73, -72, -71, -70, -62, -61, -60, -59, -58, -57, -56, -55, -54, -46, -45, -44, -43, -42, -41, -40, -39, -38, -31, -30, -29, -28, -27, -26, -25, -24, -23, -22, -15, -14, -13, -12, -11, -10, -9, -8, -7, -6, -1, -60, 0, 31, 1, 0, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, -1, -60, 0, -75, 17, 0, 2, 1, 2, 4, 4, 3, 4, 7, 5, 4, 4, 0, 1, 2, 119, 0, 1, 2, 3, 17, 4, 5, 33, 49, 6, 18, 65, 81, 7, 97, 113, 19, 34, 50, -127, 8, 20, 66, -111, -95, -79, -63, 9, 35, 51, 82, -16, 21, 98, 114, -47, 10, 22, 36, 52, -31, 37, -15, 23, 24, 25, 26, 38, 39, 40, 41, 42, 53, 54, 55, 56, 57, 58, 67, 68, 69, 70, 71, 72, 73, 74, 83, 84, 85, 86, 87, 88, 89, 90, 99, 100, 101, 102, 103, 104, 105, 106, 115, 116, 117, 118, 119, 120, 121, 122, -126, -125, -124, -123, -122, -121, -120, -119, -118, -110, -109, -108, -107, -106, -105, -104, -103, -102, -94, -93, -92, -91, -90, -89, -88, -87, -86, -78, -77, -76, -75, -74, -73, -72, -71, -70, -62, -61, -60, -59, -58, -57, -56, -55, -54, -46, -45, -44, -43, -42, -41, -40, -39, -38, -30, -29, -28, -27, -26, -25, -24, -23, -22, -14, -13, -12, -11, -10, -9, -8, -7, -6, -1, -38, 0, 12, 3, 1, 0, 2, 17, 3, 17, 0, 63, 0, -114, -118, 40, -96, 15, -1, -39 };
  private final PooledByteBufferFactory mPooledByteBufferFactory;

  public EmptyJpegGenerator(PooledByteBufferFactory paramPooledByteBufferFactory)
  {
    this.mPooledByteBufferFactory = paramPooledByteBufferFactory;
  }

  // ERROR //
  public com.facebook.common.references.CloseableReference<com.facebook.common.memory.PooledByteBuffer> generate(short paramShort1, short paramShort2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 5
    //   3: aconst_null
    //   4: astore_3
    //   5: aload_0
    //   6: getfield 196	com/facebook/imagepipeline/bitmaps/EmptyJpegGenerator:mPooledByteBufferFactory	Lcom/facebook/common/memory/PooledByteBufferFactory;
    //   9: getstatic 21	com/facebook/imagepipeline/bitmaps/EmptyJpegGenerator:EMPTY_JPEG_PREFIX	[B
    //   12: arraylength
    //   13: getstatic 189	com/facebook/imagepipeline/bitmaps/EmptyJpegGenerator:EMPTY_JPEG_SUFFIX	[B
    //   16: arraylength
    //   17: iadd
    //   18: iconst_4
    //   19: iadd
    //   20: invokeinterface 206 2 0
    //   25: astore 4
    //   27: aload 4
    //   29: getstatic 21	com/facebook/imagepipeline/bitmaps/EmptyJpegGenerator:EMPTY_JPEG_PREFIX	[B
    //   32: invokevirtual 212	com/facebook/common/memory/PooledByteBufferOutputStream:write	([B)V
    //   35: aload 4
    //   37: iload_2
    //   38: bipush 8
    //   40: ishr
    //   41: i2b
    //   42: invokevirtual 215	com/facebook/common/memory/PooledByteBufferOutputStream:write	(I)V
    //   45: aload 4
    //   47: iload_2
    //   48: sipush 255
    //   51: iand
    //   52: i2b
    //   53: invokevirtual 215	com/facebook/common/memory/PooledByteBufferOutputStream:write	(I)V
    //   56: aload 4
    //   58: iload_1
    //   59: bipush 8
    //   61: ishr
    //   62: i2b
    //   63: invokevirtual 215	com/facebook/common/memory/PooledByteBufferOutputStream:write	(I)V
    //   66: aload 4
    //   68: iload_1
    //   69: sipush 255
    //   72: iand
    //   73: i2b
    //   74: invokevirtual 215	com/facebook/common/memory/PooledByteBufferOutputStream:write	(I)V
    //   77: aload 4
    //   79: getstatic 189	com/facebook/imagepipeline/bitmaps/EmptyJpegGenerator:EMPTY_JPEG_SUFFIX	[B
    //   82: invokevirtual 212	com/facebook/common/memory/PooledByteBufferOutputStream:write	([B)V
    //   85: aload 4
    //   87: invokevirtual 219	com/facebook/common/memory/PooledByteBufferOutputStream:toByteBuffer	()Lcom/facebook/common/memory/PooledByteBuffer;
    //   90: invokestatic 225	com/facebook/common/references/CloseableReference:of	(Ljava/io/Closeable;)Lcom/facebook/common/references/CloseableReference;
    //   93: astore_3
    //   94: aload 4
    //   96: ifnull +8 -> 104
    //   99: aload 4
    //   101: invokevirtual 228	com/facebook/common/memory/PooledByteBufferOutputStream:close	()V
    //   104: aload_3
    //   105: areturn
    //   106: astore_3
    //   107: goto +41 -> 148
    //   110: astore 5
    //   112: aload 4
    //   114: astore_3
    //   115: aload 5
    //   117: astore 4
    //   119: goto +19 -> 138
    //   122: astore 5
    //   124: aload_3
    //   125: astore 4
    //   127: aload 5
    //   129: astore_3
    //   130: goto +18 -> 148
    //   133: astore 4
    //   135: aload 5
    //   137: astore_3
    //   138: new 230	java/lang/RuntimeException
    //   141: dup
    //   142: aload 4
    //   144: invokespecial 233	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   147: athrow
    //   148: aload 4
    //   150: ifnull +8 -> 158
    //   153: aload 4
    //   155: invokevirtual 228	com/facebook/common/memory/PooledByteBufferOutputStream:close	()V
    //   158: aload_3
    //   159: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   27	94	106	finally
    //   27	94	110	java/io/IOException
    //   5	27	122	finally
    //   138	148	122	finally
    //   5	27	133	java/io/IOException
  }
}