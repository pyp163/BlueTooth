package org.apache.http.client.utils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Stack;
import org.apache.http.HttpHost;
import org.apache.http.annotation.Immutable;

@Immutable
public class URIUtils
{
  public static URI createURI(String paramString1, String paramString2, int paramInt, String paramString3, String paramString4, String paramString5)
    throws URISyntaxException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (paramString2 != null)
    {
      if (paramString1 != null)
      {
        localStringBuilder.append(paramString1);
        localStringBuilder.append("://");
      }
      localStringBuilder.append(paramString2);
      if (paramInt > 0)
      {
        localStringBuilder.append(':');
        localStringBuilder.append(paramInt);
      }
    }
    if ((paramString3 == null) || (!paramString3.startsWith("/")))
      localStringBuilder.append('/');
    if (paramString3 != null)
      localStringBuilder.append(paramString3);
    if (paramString4 != null)
    {
      localStringBuilder.append('?');
      localStringBuilder.append(paramString4);
    }
    if (paramString5 != null)
    {
      localStringBuilder.append('#');
      localStringBuilder.append(paramString5);
    }
    return new URI(localStringBuilder.toString());
  }

  public static HttpHost extractHost(URI paramURI)
  {
    Object localObject3 = null;
    if (paramURI == null)
      return null;
    Object localObject2 = localObject3;
    if (paramURI.isAbsolute())
    {
      int i = paramURI.getPort();
      localObject2 = paramURI.getHost();
      int j = i;
      Object localObject1 = localObject2;
      if (localObject2 == null)
      {
        String str = paramURI.getAuthority();
        j = i;
        localObject1 = str;
        if (str != null)
        {
          int k = str.indexOf('@');
          localObject2 = str;
          if (k >= 0)
          {
            j = str.length();
            k += 1;
            if (j > k)
              localObject2 = str.substring(k);
            else
              localObject2 = null;
          }
          j = i;
          localObject1 = localObject2;
          if (localObject2 != null)
          {
            k = ((String)localObject2).indexOf(':');
            j = i;
            localObject1 = localObject2;
            if (k >= 0)
            {
              j = k + 1;
              if (j < ((String)localObject2).length())
                i = Integer.parseInt(((String)localObject2).substring(j));
              localObject1 = ((String)localObject2).substring(0, k);
              j = i;
            }
          }
        }
      }
      paramURI = paramURI.getScheme();
      localObject2 = localObject3;
      if (localObject1 != null)
        localObject2 = new HttpHost((String)localObject1, j, paramURI);
    }
    return localObject2;
  }

  private static String normalizePath(String paramString)
  {
    if (paramString == null)
      return null;
    int i = 0;
    while ((i < paramString.length()) && (paramString.charAt(i) == '/'))
      i += 1;
    String str = paramString;
    if (i > 1)
      str = paramString.substring(i - 1);
    return str;
  }

  private static URI removeDotSegments(URI paramURI)
  {
    Object localObject1 = paramURI.getPath();
    if (localObject1 != null)
    {
      if (((String)localObject1).indexOf("/.") == -1)
        return paramURI;
      localObject1 = ((String)localObject1).split("/");
      Object localObject2 = new Stack();
      int i = 0;
      while (i < localObject1.length)
      {
        if ((localObject1[i].length() != 0) && (!".".equals(localObject1[i])))
          if ("..".equals(localObject1[i]))
          {
            if (!((Stack)localObject2).isEmpty())
              ((Stack)localObject2).pop();
          }
          else
            ((Stack)localObject2).push(localObject1[i]);
        i += 1;
      }
      localObject1 = new StringBuilder();
      localObject2 = ((Stack)localObject2).iterator();
      while (((Iterator)localObject2).hasNext())
      {
        String str = (String)((Iterator)localObject2).next();
        ((StringBuilder)localObject1).append('/');
        ((StringBuilder)localObject1).append(str);
      }
      try
      {
        paramURI = new URI(paramURI.getScheme(), paramURI.getAuthority(), ((StringBuilder)localObject1).toString(), paramURI.getQuery(), paramURI.getFragment());
        return paramURI;
      }
      catch (URISyntaxException paramURI)
      {
        throw new IllegalArgumentException(paramURI);
      }
    }
    return paramURI;
  }

  public static URI resolve(URI paramURI, String paramString)
  {
    return resolve(paramURI, URI.create(paramString));
  }

  public static URI resolve(URI paramURI1, URI paramURI2)
  {
    if (paramURI1 == null)
      throw new IllegalArgumentException("Base URI may nor be null");
    if (paramURI2 == null)
      throw new IllegalArgumentException("Reference URI may nor be null");
    String str = paramURI2.toString();
    if (str.startsWith("?"))
      return resolveReferenceStartingWithQueryString(paramURI1, paramURI2);
    int i;
    if (str.length() == 0)
      i = 1;
    else
      i = 0;
    if (i != 0)
      paramURI2 = URI.create("#");
    paramURI2 = paramURI1.resolve(paramURI2);
    paramURI1 = paramURI2;
    if (i != 0)
    {
      paramURI1 = paramURI2.toString();
      paramURI1 = URI.create(paramURI1.substring(0, paramURI1.indexOf('#')));
    }
    return removeDotSegments(paramURI1);
  }

  private static URI resolveReferenceStartingWithQueryString(URI paramURI1, URI paramURI2)
  {
    Object localObject = paramURI1.toString();
    paramURI1 = (URI)localObject;
    if (((String)localObject).indexOf('?') > -1)
      paramURI1 = ((String)localObject).substring(0, ((String)localObject).indexOf('?'));
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramURI1);
    ((StringBuilder)localObject).append(paramURI2.toString());
    return URI.create(((StringBuilder)localObject).toString());
  }

  public static URI rewriteURI(URI paramURI, HttpHost paramHttpHost)
    throws URISyntaxException
  {
    return rewriteURI(paramURI, paramHttpHost, false);
  }

  public static URI rewriteURI(URI paramURI, HttpHost paramHttpHost, boolean paramBoolean)
    throws URISyntaxException
  {
    if (paramURI == null)
      throw new IllegalArgumentException("URI may nor be null");
    String str2 = null;
    String str1 = null;
    if (paramHttpHost != null)
    {
      str2 = paramHttpHost.getSchemeName();
      String str3 = paramHttpHost.getHostName();
      int i = paramHttpHost.getPort();
      paramHttpHost = normalizePath(paramURI.getRawPath());
      String str4 = paramURI.getRawQuery();
      if (paramBoolean);
      for (paramURI = str1; ; paramURI = paramURI.getRawFragment())
        break;
      return createURI(str2, str3, i, paramHttpHost, str4, paramURI);
    }
    paramHttpHost = normalizePath(paramURI.getRawPath());
    str1 = paramURI.getRawQuery();
    if (paramBoolean);
    for (paramURI = str2; ; paramURI = paramURI.getRawFragment())
      break;
    return createURI(null, null, -1, paramHttpHost, str1, paramURI);
  }
}