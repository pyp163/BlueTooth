package org.apache.http.client.entity;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

public class DeflateDecompressingEntity extends DecompressingEntity
{
  public DeflateDecompressingEntity(HttpEntity paramHttpEntity)
  {
    super(paramHttpEntity);
  }

  public Header getContentEncoding()
  {
    return null;
  }

  public long getContentLength()
  {
    return -1L;
  }

  InputStream getDecompressingInputStream(InputStream paramInputStream)
    throws IOException
  {
    byte[] arrayOfByte = new byte[6];
    paramInputStream = new PushbackInputStream(paramInputStream, arrayOfByte.length);
    int i = paramInputStream.read(arrayOfByte);
    if (i == -1)
      throw new IOException("Unable to read the response");
    Object localObject = new byte[1];
    Inflater localInflater = new Inflater();
    try
    {
      int j;
      while (true)
      {
        j = localInflater.inflate((byte[])localObject);
        if (j != 0)
          break;
        if (localInflater.finished())
          throw new IOException("Unable to read the response");
        if (localInflater.needsDictionary())
          break;
        if (localInflater.needsInput())
          localInflater.setInput(arrayOfByte);
      }
      if (j == -1)
        throw new IOException("Unable to read the response");
      paramInputStream.unread(arrayOfByte, 0, i);
      localObject = new InflaterInputStream(paramInputStream);
      return localObject;
      label149: paramInputStream.unread(arrayOfByte, 0, i);
      return new InflaterInputStream(paramInputStream, new Inflater(true));
    }
    catch (DataFormatException localDataFormatException)
    {
      break label149;
    }
  }
}