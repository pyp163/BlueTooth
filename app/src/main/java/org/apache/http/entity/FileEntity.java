package org.apache.http.entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileEntity extends AbstractHttpEntity
  implements Cloneable
{
  protected final File file;

  public FileEntity(File paramFile, String paramString)
  {
    if (paramFile == null)
      throw new IllegalArgumentException("File may not be null");
    this.file = paramFile;
    setContentType(paramString);
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    return super.clone();
  }

  public InputStream getContent()
    throws IOException
  {
    return new FileInputStream(this.file);
  }

  public long getContentLength()
  {
    return this.file.length();
  }

  public boolean isRepeatable()
  {
    return true;
  }

  public boolean isStreaming()
  {
    return false;
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    if (paramOutputStream == null)
      throw new IllegalArgumentException("Output stream may not be null");
    FileInputStream localFileInputStream = new FileInputStream(this.file);
    try
    {
      byte[] arrayOfByte = new byte[4096];
      while (true)
      {
        int i = localFileInputStream.read(arrayOfByte);
        if (i == -1)
          break;
        paramOutputStream.write(arrayOfByte, 0, i);
      }
      paramOutputStream.flush();
      return;
    }
    finally
    {
      localFileInputStream.close();
    }
    throw paramOutputStream;
  }
}