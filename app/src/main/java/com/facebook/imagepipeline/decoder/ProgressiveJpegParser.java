package com.facebook.imagepipeline.decoder;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Throwables;
import com.facebook.common.memory.ByteArrayPool;
import com.facebook.common.util.StreamUtil;
import java.io.IOException;
import java.io.InputStream;

public class ProgressiveJpegParser
{
  private static final int BUFFER_SIZE = 16384;
  private static final int NOT_A_JPEG = 6;
  private static final int READ_FIRST_JPEG_BYTE = 0;
  private static final int READ_MARKER_FIRST_BYTE_OR_ENTROPY_DATA = 2;
  private static final int READ_MARKER_SECOND_BYTE = 3;
  private static final int READ_SECOND_JPEG_BYTE = 1;
  private static final int READ_SIZE_FIRST_BYTE = 4;
  private static final int READ_SIZE_SECOND_BYTE = 5;
  private int mBestScanEndOffset;
  private int mBestScanNumber;
  private final ByteArrayPool mByteArrayPool;
  private int mBytesParsed;
  private boolean mEndMarkerRead;
  private int mLastByteRead;
  private int mNextFullScanNumber;
  private int mParserState;

  public ProgressiveJpegParser(ByteArrayPool paramByteArrayPool)
  {
    this.mByteArrayPool = ((ByteArrayPool)Preconditions.checkNotNull(paramByteArrayPool));
    this.mBytesParsed = 0;
    this.mLastByteRead = 0;
    this.mNextFullScanNumber = 0;
    this.mBestScanEndOffset = 0;
    this.mBestScanNumber = 0;
    this.mParserState = 0;
  }

  private boolean doParseMoreData(InputStream paramInputStream)
  {
    int i = this.mBestScanNumber;
    boolean bool2 = false;
    while (true)
      try
      {
        if (this.mParserState != 6)
        {
          int j = paramInputStream.read();
          if (j != -1)
          {
            this.mBytesParsed += 1;
            if (this.mEndMarkerRead)
            {
              this.mParserState = 6;
              this.mEndMarkerRead = false;
              return false;
            }
            switch (this.mParserState)
            {
            case 5:
              Preconditions.checkState(false);
              continue;
              int k = (this.mLastByteRead << 8) + j - 2;
              StreamUtil.skip(paramInputStream, k);
              this.mBytesParsed += k;
              this.mParserState = 2;
              break;
            case 4:
              this.mParserState = 5;
              break;
            case 3:
              if (j == 255)
              {
                this.mParserState = 3;
              }
              else if (j == 0)
              {
                this.mParserState = 2;
              }
              else if (j == 217)
              {
                this.mEndMarkerRead = true;
                newScanOrImageEndFound(this.mBytesParsed - 2);
                this.mParserState = 2;
              }
              else
              {
                if (j == 218)
                  newScanOrImageEndFound(this.mBytesParsed - 2);
                if (doesMarkerStartSegment(j))
                  this.mParserState = 4;
                else
                  this.mParserState = 2;
              }
              break;
            case 2:
              if (j == 255)
                this.mParserState = 3;
              break;
            case 1:
              if (j == 216)
                this.mParserState = 2;
              else
                this.mParserState = 6;
              break;
            case 0:
              if (j == 255)
                this.mParserState = 1;
              else
                this.mParserState = 6;
              this.mLastByteRead = j;
            }
          }
        }
      }
      catch (IOException paramInputStream)
      {
        Throwables.propagate(paramInputStream);
        boolean bool1 = bool2;
        if (this.mParserState != 6)
        {
          bool1 = bool2;
          if (this.mBestScanNumber != i)
            bool1 = true;
        }
        return bool1;
      }
  }

  private static boolean doesMarkerStartSegment(int paramInt)
  {
    if (paramInt == 1)
      return false;
    if ((paramInt >= 208) && (paramInt <= 215))
      return false;
    return (paramInt != 217) && (paramInt != 216);
  }

  private void newScanOrImageEndFound(int paramInt)
  {
    if (this.mNextFullScanNumber > 0)
      this.mBestScanEndOffset = paramInt;
    paramInt = this.mNextFullScanNumber;
    this.mNextFullScanNumber = (paramInt + 1);
    this.mBestScanNumber = paramInt;
  }

  public int getBestScanEndOffset()
  {
    return this.mBestScanEndOffset;
  }

  public int getBestScanNumber()
  {
    return this.mBestScanNumber;
  }

  public boolean isEndMarkerRead()
  {
    return this.mEndMarkerRead;
  }

  public boolean isJpeg()
  {
    return (this.mBytesParsed > 1) && (this.mParserState != 6);
  }

  // ERROR //
  public boolean parseMoreData(com.facebook.imagepipeline.image.EncodedImage paramEncodedImage)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 58	com/facebook/imagepipeline/decoder/ProgressiveJpegParser:mParserState	I
    //   4: bipush 6
    //   6: if_icmpne +5 -> 11
    //   9: iconst_0
    //   10: ireturn
    //   11: aload_1
    //   12: invokevirtual 107	com/facebook/imagepipeline/image/EncodedImage:getSize	()I
    //   15: aload_0
    //   16: getfield 48	com/facebook/imagepipeline/decoder/ProgressiveJpegParser:mBytesParsed	I
    //   19: if_icmpgt +5 -> 24
    //   22: iconst_0
    //   23: ireturn
    //   24: new 109	com/facebook/common/memory/PooledByteArrayBufferedInputStream
    //   27: dup
    //   28: aload_1
    //   29: invokevirtual 113	com/facebook/imagepipeline/image/EncodedImage:getInputStream	()Ljava/io/InputStream;
    //   32: aload_0
    //   33: getfield 46	com/facebook/imagepipeline/decoder/ProgressiveJpegParser:mByteArrayPool	Lcom/facebook/common/memory/ByteArrayPool;
    //   36: sipush 16384
    //   39: invokeinterface 117 2 0
    //   44: checkcast 119	[B
    //   47: aload_0
    //   48: getfield 46	com/facebook/imagepipeline/decoder/ProgressiveJpegParser:mByteArrayPool	Lcom/facebook/common/memory/ByteArrayPool;
    //   51: invokespecial 122	com/facebook/common/memory/PooledByteArrayBufferedInputStream:<init>	(Ljava/io/InputStream;[BLcom/facebook/common/references/ResourceReleaser;)V
    //   54: astore_1
    //   55: aload_1
    //   56: aload_0
    //   57: getfield 48	com/facebook/imagepipeline/decoder/ProgressiveJpegParser:mBytesParsed	I
    //   60: i2l
    //   61: invokestatic 81	com/facebook/common/util/StreamUtil:skip	(Ljava/io/InputStream;J)J
    //   64: pop2
    //   65: aload_0
    //   66: aload_1
    //   67: invokespecial 124	com/facebook/imagepipeline/decoder/ProgressiveJpegParser:doParseMoreData	(Ljava/io/InputStream;)Z
    //   70: istore_2
    //   71: aload_1
    //   72: invokestatic 130	com/facebook/common/internal/Closeables:closeQuietly	(Ljava/io/InputStream;)V
    //   75: iload_2
    //   76: ireturn
    //   77: astore_3
    //   78: goto +15 -> 93
    //   81: astore_3
    //   82: aload_3
    //   83: invokestatic 95	com/facebook/common/internal/Throwables:propagate	(Ljava/lang/Throwable;)Ljava/lang/RuntimeException;
    //   86: pop
    //   87: aload_1
    //   88: invokestatic 130	com/facebook/common/internal/Closeables:closeQuietly	(Ljava/io/InputStream;)V
    //   91: iconst_0
    //   92: ireturn
    //   93: aload_1
    //   94: invokestatic 130	com/facebook/common/internal/Closeables:closeQuietly	(Ljava/io/InputStream;)V
    //   97: aload_3
    //   98: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   55	71	77	finally
    //   82	87	77	finally
    //   55	71	81	java/io/IOException
  }
}