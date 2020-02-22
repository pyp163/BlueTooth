package org.apache.http.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;

public final class EntityUtils
{
  public static void consume(HttpEntity paramHttpEntity)
    throws IOException
  {
    if (paramHttpEntity == null)
      return;
    if (paramHttpEntity.isStreaming())
    {
      paramHttpEntity = paramHttpEntity.getContent();
      if (paramHttpEntity != null)
        paramHttpEntity.close();
    }
  }

  public static String getContentCharSet(HttpEntity paramHttpEntity)
    throws ParseException
  {
    if (paramHttpEntity == null)
      throw new IllegalArgumentException("HTTP entity may not be null");
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramHttpEntity.getContentType() != null)
    {
      paramHttpEntity = paramHttpEntity.getContentType().getElements();
      localObject1 = localObject2;
      if (paramHttpEntity.length > 0)
      {
        paramHttpEntity = paramHttpEntity[0].getParameterByName("charset");
        localObject1 = localObject2;
        if (paramHttpEntity != null)
          localObject1 = paramHttpEntity.getValue();
      }
    }
    return localObject1;
  }

  public static String getContentMimeType(HttpEntity paramHttpEntity)
    throws ParseException
  {
    if (paramHttpEntity == null)
      throw new IllegalArgumentException("HTTP entity may not be null");
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramHttpEntity.getContentType() != null)
    {
      paramHttpEntity = paramHttpEntity.getContentType().getElements();
      localObject1 = localObject2;
      if (paramHttpEntity.length > 0)
        localObject1 = paramHttpEntity[0].getName();
    }
    return localObject1;
  }

  public static byte[] toByteArray(HttpEntity paramHttpEntity)
    throws IOException
  {
    if (paramHttpEntity == null)
      throw new IllegalArgumentException("HTTP entity may not be null");
    InputStream localInputStream = paramHttpEntity.getContent();
    if (localInputStream == null)
      return null;
    try
    {
      if (paramHttpEntity.getContentLength() > 2147483647L)
        throw new IllegalArgumentException("HTTP entity too large to be buffered in memory");
      int j = (int)paramHttpEntity.getContentLength();
      int i = j;
      if (j < 0)
        i = 4096;
      paramHttpEntity = new ByteArrayBuffer(i);
      byte[] arrayOfByte = new byte[4096];
      while (true)
      {
        i = localInputStream.read(arrayOfByte);
        if (i == -1)
          break;
        paramHttpEntity.append(arrayOfByte, 0, i);
      }
      paramHttpEntity = paramHttpEntity.toByteArray();
      return paramHttpEntity;
    }
    finally
    {
      localInputStream.close();
    }
    throw paramHttpEntity;
  }

  public static String toString(HttpEntity paramHttpEntity)
    throws IOException, ParseException
  {
    return toString(paramHttpEntity, null);
  }

  public static String toString(HttpEntity paramHttpEntity, String paramString)
    throws IOException, ParseException
  {
    if (paramHttpEntity == null)
      throw new IllegalArgumentException("HTTP entity may not be null");
    InputStream localInputStream = paramHttpEntity.getContent();
    if (localInputStream == null)
      return null;
    while (true)
    {
      try
      {
        if (paramHttpEntity.getContentLength() > 2147483647L)
          throw new IllegalArgumentException("HTTP entity too large to be buffered in memory");
        int j = (int)paramHttpEntity.getContentLength();
        int i = j;
        if (j < 0)
          i = 4096;
        Object localObject = getContentCharSet(paramHttpEntity);
        paramHttpEntity = (HttpEntity)localObject;
        if (localObject == null)
        {
          paramHttpEntity = paramString;
          break label159;
          paramHttpEntity = new InputStreamReader(localInputStream, paramString);
          paramString = new CharArrayBuffer(i);
          localObject = new char[1024];
          i = paramHttpEntity.read((char[])localObject);
          if (i != -1)
          {
            paramString.append((char[])localObject, 0, i);
            continue;
          }
          paramHttpEntity = paramString.toString();
          return paramHttpEntity;
        }
      }
      finally
      {
        localInputStream.close();
      }
      label159: paramString = paramHttpEntity;
      if (paramHttpEntity == null)
        paramString = "ISO-8859-1";
    }
  }
}