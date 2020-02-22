package org.apache.http.impl.auth;

import org.apache.http.annotation.Immutable;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthSchemeFactory;
import org.apache.http.params.HttpParams;

@Immutable
public class NegotiateSchemeFactory
  implements AuthSchemeFactory
{
  private final SpnegoTokenGenerator spengoGenerator;
  private final boolean stripPort;

  public NegotiateSchemeFactory()
  {
    this(null, false);
  }

  public NegotiateSchemeFactory(SpnegoTokenGenerator paramSpnegoTokenGenerator)
  {
    this(paramSpnegoTokenGenerator, false);
  }

  public NegotiateSchemeFactory(SpnegoTokenGenerator paramSpnegoTokenGenerator, boolean paramBoolean)
  {
    this.spengoGenerator = paramSpnegoTokenGenerator;
    this.stripPort = paramBoolean;
  }

  public SpnegoTokenGenerator getSpengoGenerator()
  {
    return this.spengoGenerator;
  }

  public boolean isStripPort()
  {
    return this.stripPort;
  }

  public AuthScheme newInstance(HttpParams paramHttpParams)
  {
    return new NegotiateScheme(this.spengoGenerator, this.stripPort);
  }
}