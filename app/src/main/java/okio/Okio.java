package okio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

public final class Okio
{
  static final Logger logger = Logger.getLogger(Okio.class.getName());

  public static Sink appendingSink(File paramFile)
    throws FileNotFoundException
  {
    if (paramFile == null)
      throw new IllegalArgumentException("file == null");
    return sink(new FileOutputStream(paramFile, true));
  }

  public static Sink blackhole()
  {
    return new Sink()
    {
      public void close()
        throws IOException
      {
      }

      public void flush()
        throws IOException
      {
      }

      public Timeout timeout()
      {
        return Timeout.NONE;
      }

      public void write(Buffer paramAnonymousBuffer, long paramAnonymousLong)
        throws IOException
      {
        paramAnonymousBuffer.skip(paramAnonymousLong);
      }
    };
  }

  public static BufferedSink buffer(Sink paramSink)
  {
    return new RealBufferedSink(paramSink);
  }

  public static BufferedSource buffer(Source paramSource)
  {
    return new RealBufferedSource(paramSource);
  }

  static boolean isAndroidGetsocknameError(AssertionError paramAssertionError)
  {
    return (paramAssertionError.getCause() != null) && (paramAssertionError.getMessage() != null) && (paramAssertionError.getMessage().contains("getsockname failed"));
  }

  public static Sink sink(File paramFile)
    throws FileNotFoundException
  {
    if (paramFile == null)
      throw new IllegalArgumentException("file == null");
    return sink(new FileOutputStream(paramFile));
  }

  public static Sink sink(OutputStream paramOutputStream)
  {
    return sink(paramOutputStream, new Timeout());
  }

  private static Sink sink(final OutputStream paramOutputStream, Timeout paramTimeout)
  {
    if (paramOutputStream == null)
      throw new IllegalArgumentException("out == null");
    if (paramTimeout == null)
      throw new IllegalArgumentException("timeout == null");
    return new Sink()
    {
      public void close()
        throws IOException
      {
        paramOutputStream.close();
      }

      public void flush()
        throws IOException
      {
        paramOutputStream.flush();
      }

      public Timeout timeout()
      {
        return Okio.this;
      }

      public String toString()
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("sink(");
        localStringBuilder.append(paramOutputStream);
        localStringBuilder.append(")");
        return localStringBuilder.toString();
      }

      public void write(Buffer paramAnonymousBuffer, long paramAnonymousLong)
        throws IOException
      {
        Util.checkOffsetAndCount(paramAnonymousBuffer.size, 0L, paramAnonymousLong);
        while (paramAnonymousLong > 0L)
        {
          Okio.this.throwIfReached();
          Segment localSegment = paramAnonymousBuffer.head;
          int i = (int)Math.min(paramAnonymousLong, localSegment.limit - localSegment.pos);
          paramOutputStream.write(localSegment.data, localSegment.pos, i);
          localSegment.pos += i;
          long l2 = i;
          long l1 = paramAnonymousLong - l2;
          paramAnonymousBuffer.size -= l2;
          paramAnonymousLong = l1;
          if (localSegment.pos == localSegment.limit)
          {
            paramAnonymousBuffer.head = localSegment.pop();
            SegmentPool.recycle(localSegment);
            paramAnonymousLong = l1;
          }
        }
      }
    };
  }

  public static Sink sink(Socket paramSocket)
    throws IOException
  {
    if (paramSocket == null)
      throw new IllegalArgumentException("socket == null");
    if (paramSocket.getOutputStream() == null)
      throw new IOException("socket's output stream == null");
    AsyncTimeout localAsyncTimeout = timeout(paramSocket);
    return localAsyncTimeout.sink(sink(paramSocket.getOutputStream(), localAsyncTimeout));
  }

  @IgnoreJRERequirement
  public static Sink sink(Path paramPath, OpenOption[] paramArrayOfOpenOption)
    throws IOException
  {
    if (paramPath == null)
      throw new IllegalArgumentException("path == null");
    return sink(Files.newOutputStream(paramPath, paramArrayOfOpenOption));
  }

  public static Source source(File paramFile)
    throws FileNotFoundException
  {
    if (paramFile == null)
      throw new IllegalArgumentException("file == null");
    return source(new FileInputStream(paramFile));
  }

  public static Source source(InputStream paramInputStream)
  {
    return source(paramInputStream, new Timeout());
  }

  private static Source source(final InputStream paramInputStream, Timeout paramTimeout)
  {
    if (paramInputStream == null)
      throw new IllegalArgumentException("in == null");
    if (paramTimeout == null)
      throw new IllegalArgumentException("timeout == null");
    return new Source()
    {
      public void close()
        throws IOException
      {
        paramInputStream.close();
      }

      public long read(Buffer paramAnonymousBuffer, long paramAnonymousLong)
        throws IOException
      {
        boolean bool = paramAnonymousLong < 0L;
        if (bool)
        {
          paramAnonymousBuffer = new StringBuilder();
          paramAnonymousBuffer.append("byteCount < 0: ");
          paramAnonymousBuffer.append(paramAnonymousLong);
          throw new IllegalArgumentException(paramAnonymousBuffer.toString());
        }
        if (!bool)
          return 0L;
        try
        {
          Okio.this.throwIfReached();
          Segment localSegment = paramAnonymousBuffer.writableSegment(1);
          int i = (int)Math.min(paramAnonymousLong, 8192 - localSegment.limit);
          i = paramInputStream.read(localSegment.data, localSegment.limit, i);
          if (i == -1)
            return -1L;
          localSegment.limit += i;
          paramAnonymousLong = paramAnonymousBuffer.size;
          long l = i;
          paramAnonymousBuffer.size = (paramAnonymousLong + l);
          return l;
        }
        catch (AssertionError paramAnonymousBuffer)
        {
          if (Okio.isAndroidGetsocknameError(paramAnonymousBuffer))
            throw new IOException(paramAnonymousBuffer);
        }
        throw paramAnonymousBuffer;
      }

      public Timeout timeout()
      {
        return Okio.this;
      }

      public String toString()
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("source(");
        localStringBuilder.append(paramInputStream);
        localStringBuilder.append(")");
        return localStringBuilder.toString();
      }
    };
  }

  public static Source source(Socket paramSocket)
    throws IOException
  {
    if (paramSocket == null)
      throw new IllegalArgumentException("socket == null");
    if (paramSocket.getInputStream() == null)
      throw new IOException("socket's input stream == null");
    AsyncTimeout localAsyncTimeout = timeout(paramSocket);
    return localAsyncTimeout.source(source(paramSocket.getInputStream(), localAsyncTimeout));
  }

  @IgnoreJRERequirement
  public static Source source(Path paramPath, OpenOption[] paramArrayOfOpenOption)
    throws IOException
  {
    if (paramPath == null)
      throw new IllegalArgumentException("path == null");
    return source(Files.newInputStream(paramPath, paramArrayOfOpenOption));
  }

  private static AsyncTimeout timeout(Socket paramSocket)
  {
    return new AsyncTimeout()
    {
      protected IOException newTimeoutException(@Nullable IOException paramAnonymousIOException)
      {
        SocketTimeoutException localSocketTimeoutException = new SocketTimeoutException("timeout");
        if (paramAnonymousIOException != null)
          localSocketTimeoutException.initCause(paramAnonymousIOException);
        return localSocketTimeoutException;
      }

      protected void timedOut()
      {
        try
        {
          Okio.this.close();
          return;
        }
        catch (AssertionError localAssertionError)
        {
          if (Okio.isAndroidGetsocknameError(localAssertionError))
          {
            localLogger = Okio.logger;
            localLevel = Level.WARNING;
            localStringBuilder = new StringBuilder();
            localStringBuilder.append("Failed to close timed out socket ");
            localStringBuilder.append(Okio.this);
            localLogger.log(localLevel, localStringBuilder.toString(), localAssertionError);
            return;
          }
          throw localAssertionError;
        }
        catch (Exception localException)
        {
          Logger localLogger = Okio.logger;
          Level localLevel = Level.WARNING;
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("Failed to close timed out socket ");
          localStringBuilder.append(Okio.this);
          localLogger.log(localLevel, localStringBuilder.toString(), localException);
        }
      }
    };
  }
}