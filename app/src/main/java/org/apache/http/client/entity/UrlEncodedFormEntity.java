package org.apache.http.client.entity;

import java.io.UnsupportedEncodingException;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;

@NotThreadSafe
public class UrlEncodedFormEntity extends StringEntity
{
  public UrlEncodedFormEntity(List<? extends NameValuePair> paramList)
    throws UnsupportedEncodingException
  {
    this(paramList, "ISO-8859-1");
  }

  public UrlEncodedFormEntity(List<? extends NameValuePair> paramList, String paramString)
    throws UnsupportedEncodingException
  {
    super(URLEncodedUtils.format(paramList, paramString), paramString);
    paramList = new StringBuilder();
    paramList.append("application/x-www-form-urlencoded; charset=");
    if (paramString == null)
      paramString = "ISO-8859-1";
    paramList.append(paramString);
    setContentType(paramList.toString());
  }
}