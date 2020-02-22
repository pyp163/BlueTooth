package org.apache.http.conn.params;

import org.apache.http.annotation.NotThreadSafe;
import org.apache.http.params.HttpAbstractParamBean;
import org.apache.http.params.HttpParams;

@NotThreadSafe
public class ConnConnectionParamBean extends HttpAbstractParamBean
{
  public ConnConnectionParamBean(HttpParams paramHttpParams)
  {
    super(paramHttpParams);
  }

  public void setMaxStatusLineGarbage(int paramInt)
  {
    this.params.setIntParameter("http.connection.max-status-line-garbage", paramInt);
  }
}