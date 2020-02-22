package org.apache.http.entity.mime;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.util.ByteArrayBuffer;

public class HttpMultipart
{
  private static final ByteArrayBuffer CR_LF = encode(MIME.DEFAULT_CHARSET, "\r\n");
  private static final ByteArrayBuffer FIELD_SEP = encode(MIME.DEFAULT_CHARSET, ": ");
  private static final ByteArrayBuffer TWO_DASHES = encode(MIME.DEFAULT_CHARSET, "--");
  private final String boundary;
  private final Charset charset;
  private final HttpMultipartMode mode;
  private final List<FormBodyPart> parts;
  private final String subType;

  public HttpMultipart(String paramString1, String paramString2)
  {
    this(paramString1, null, paramString2);
  }

  public HttpMultipart(String paramString1, Charset paramCharset, String paramString2)
  {
    this(paramString1, paramCharset, paramString2, HttpMultipartMode.STRICT);
  }

  public HttpMultipart(String paramString1, Charset paramCharset, String paramString2, HttpMultipartMode paramHttpMultipartMode)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("Multipart subtype may not be null");
    if (paramString2 == null)
      throw new IllegalArgumentException("Multipart boundary may not be null");
    this.subType = paramString1;
    if (paramCharset == null)
      paramCharset = MIME.DEFAULT_CHARSET;
    this.charset = paramCharset;
    this.boundary = paramString2;
    this.parts = new ArrayList();
    this.mode = paramHttpMultipartMode;
  }

  private void doWriteTo(HttpMultipartMode paramHttpMultipartMode, OutputStream paramOutputStream, boolean paramBoolean)
    throws IOException
  {
    ByteArrayBuffer localByteArrayBuffer = encode(this.charset, getBoundary());
    Iterator localIterator = this.parts.iterator();
    while (localIterator.hasNext())
    {
      FormBodyPart localFormBodyPart = (FormBodyPart)localIterator.next();
      writeBytes(TWO_DASHES, paramOutputStream);
      writeBytes(localByteArrayBuffer, paramOutputStream);
      writeBytes(CR_LF, paramOutputStream);
      Object localObject = localFormBodyPart.getHeader();
      switch (1.$SwitchMap$org$apache$http$entity$mime$HttpMultipartMode[paramHttpMultipartMode.ordinal()])
      {
      default:
        break;
      case 2:
        writeField(localFormBodyPart.getHeader().getField("Content-Disposition"), this.charset, paramOutputStream);
        if (localFormBodyPart.getBody().getFilename() != null)
          writeField(localFormBodyPart.getHeader().getField("Content-Type"), this.charset, paramOutputStream);
        break;
      case 1:
        localObject = ((Header)localObject).iterator();
        while (((Iterator)localObject).hasNext())
          writeField((MinimalField)((Iterator)localObject).next(), paramOutputStream);
      }
      writeBytes(CR_LF, paramOutputStream);
      if (paramBoolean)
        localFormBodyPart.getBody().writeTo(paramOutputStream);
      writeBytes(CR_LF, paramOutputStream);
    }
    writeBytes(TWO_DASHES, paramOutputStream);
    writeBytes(localByteArrayBuffer, paramOutputStream);
    writeBytes(TWO_DASHES, paramOutputStream);
    writeBytes(CR_LF, paramOutputStream);
  }

  private static ByteArrayBuffer encode(Charset paramCharset, String paramString)
  {
    paramCharset = paramCharset.encode(CharBuffer.wrap(paramString));
    paramString = new ByteArrayBuffer(paramCharset.remaining());
    paramString.append(paramCharset.array(), paramCharset.position(), paramCharset.remaining());
    return paramString;
  }

  private static void writeBytes(String paramString, OutputStream paramOutputStream)
    throws IOException
  {
    writeBytes(encode(MIME.DEFAULT_CHARSET, paramString), paramOutputStream);
  }

  private static void writeBytes(String paramString, Charset paramCharset, OutputStream paramOutputStream)
    throws IOException
  {
    writeBytes(encode(paramCharset, paramString), paramOutputStream);
  }

  private static void writeBytes(ByteArrayBuffer paramByteArrayBuffer, OutputStream paramOutputStream)
    throws IOException
  {
    paramOutputStream.write(paramByteArrayBuffer.buffer(), 0, paramByteArrayBuffer.length());
  }

  private static void writeField(MinimalField paramMinimalField, OutputStream paramOutputStream)
    throws IOException
  {
    writeBytes(paramMinimalField.getName(), paramOutputStream);
    writeBytes(FIELD_SEP, paramOutputStream);
    writeBytes(paramMinimalField.getBody(), paramOutputStream);
    writeBytes(CR_LF, paramOutputStream);
  }

  private static void writeField(MinimalField paramMinimalField, Charset paramCharset, OutputStream paramOutputStream)
    throws IOException
  {
    writeBytes(paramMinimalField.getName(), paramCharset, paramOutputStream);
    writeBytes(FIELD_SEP, paramOutputStream);
    writeBytes(paramMinimalField.getBody(), paramCharset, paramOutputStream);
    writeBytes(CR_LF, paramOutputStream);
  }

  public void addBodyPart(FormBodyPart paramFormBodyPart)
  {
    if (paramFormBodyPart == null)
      return;
    this.parts.add(paramFormBodyPart);
  }

  public List<FormBodyPart> getBodyParts()
  {
    return this.parts;
  }

  public String getBoundary()
  {
    return this.boundary;
  }

  public Charset getCharset()
  {
    return this.charset;
  }

  public HttpMultipartMode getMode()
  {
    return this.mode;
  }

  public String getSubType()
  {
    return this.subType;
  }

  public long getTotalLength()
  {
    Object localObject = this.parts.iterator();
    long l1 = 0L;
    while (((Iterator)localObject).hasNext())
    {
      long l2 = ((FormBodyPart)((Iterator)localObject).next()).getBody().getContentLength();
      if (l2 >= 0L)
        l1 += l2;
      else
        return -1L;
    }
    localObject = new ByteArrayOutputStream();
    try
    {
      doWriteTo(this.mode, (OutputStream)localObject, false);
      int i = ((ByteArrayOutputStream)localObject).toByteArray().length;
      return l1 + i;
    }
    catch (IOException localIOException)
    {
    }
    return -1L;
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    doWriteTo(this.mode, paramOutputStream, true);
  }
}