package org.apache.http.client.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.annotation.Immutable;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

@Immutable
public class URLEncodedUtils
{
  public static final String CONTENT_TYPE = "application/x-www-form-urlencoded";
  private static final String NAME_VALUE_SEPARATOR = "=";
  private static final String PARAMETER_SEPARATOR = "&";

  private static String decode(String paramString1, String paramString2)
  {
    if (paramString2 == null)
      paramString2 = "ISO-8859-1";
    try
    {
      paramString1 = URLDecoder.decode(paramString1, paramString2);
      return paramString1;
    }
    catch (UnsupportedEncodingException paramString1)
    {
    }
    throw new IllegalArgumentException(paramString1);
  }

  private static String encode(String paramString1, String paramString2)
  {
    if (paramString2 == null)
      paramString2 = "ISO-8859-1";
    try
    {
      paramString1 = URLEncoder.encode(paramString1, paramString2);
      return paramString1;
    }
    catch (UnsupportedEncodingException paramString1)
    {
    }
    throw new IllegalArgumentException(paramString1);
  }

  public static String format(List<? extends NameValuePair> paramList, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      paramList = (NameValuePair)localIterator.next();
      String str = encode(paramList.getName(), paramString);
      paramList = paramList.getValue();
      if (paramList != null)
        paramList = encode(paramList, paramString);
      else
        paramList = "";
      if (localStringBuilder.length() > 0)
        localStringBuilder.append("&");
      localStringBuilder.append(str);
      localStringBuilder.append("=");
      localStringBuilder.append(paramList);
    }
    return localStringBuilder.toString();
  }

  public static boolean isEncoded(HttpEntity paramHttpEntity)
  {
    paramHttpEntity = paramHttpEntity.getContentType();
    if (paramHttpEntity != null)
    {
      paramHttpEntity = paramHttpEntity.getElements();
      if (paramHttpEntity.length > 0)
        return paramHttpEntity[0].getName().equalsIgnoreCase("application/x-www-form-urlencoded");
      return false;
    }
    return false;
  }

  public static List<NameValuePair> parse(URI paramURI, String paramString)
  {
    List localList = Collections.emptyList();
    String str = paramURI.getRawQuery();
    paramURI = localList;
    if (str != null)
    {
      paramURI = localList;
      if (str.length() > 0)
      {
        paramURI = new ArrayList();
        parse(paramURI, new Scanner(str), paramString);
      }
    }
    return paramURI;
  }

  public static List<NameValuePair> parse(HttpEntity paramHttpEntity)
    throws IOException
  {
    List localList = Collections.emptyList();
    Object localObject1 = paramHttpEntity.getContentType();
    String str = null;
    if (localObject1 != null)
    {
      localObject1 = ((Header)localObject1).getElements();
      if (localObject1.length > 0)
      {
        localObject1 = localObject1[0];
        localObject2 = ((HeaderElement)localObject1).getName();
        NameValuePair localNameValuePair = ((HeaderElement)localObject1).getParameterByName("charset");
        localObject1 = localObject2;
        if (localNameValuePair == null)
          break label73;
        str = localNameValuePair.getValue();
        localObject1 = localObject2;
        break label73;
      }
    }
    localObject1 = null;
    label73: Object localObject2 = localList;
    if (localObject1 != null)
    {
      localObject2 = localList;
      if (((String)localObject1).equalsIgnoreCase("application/x-www-form-urlencoded"))
      {
        paramHttpEntity = EntityUtils.toString(paramHttpEntity, "ASCII");
        localObject2 = localList;
        if (paramHttpEntity != null)
        {
          localObject2 = localList;
          if (paramHttpEntity.length() > 0)
          {
            localObject2 = new ArrayList();
            parse((List)localObject2, new Scanner(paramHttpEntity), str);
          }
        }
      }
    }
    return localObject2;
  }

  public static void parse(List<NameValuePair> paramList, Scanner paramScanner, String paramString)
  {
    paramScanner.useDelimiter("&");
    while (paramScanner.hasNext())
    {
      String[] arrayOfString = paramScanner.next().split("=");
      if ((arrayOfString.length != 0) && (arrayOfString.length <= 2))
      {
        String str2 = decode(arrayOfString[0], paramString);
        String str1 = null;
        if (arrayOfString.length == 2)
          str1 = decode(arrayOfString[1], paramString);
        paramList.add(new BasicNameValuePair(str2, str1));
      }
      else
      {
        throw new IllegalArgumentException("bad parameter");
      }
    }
  }
}