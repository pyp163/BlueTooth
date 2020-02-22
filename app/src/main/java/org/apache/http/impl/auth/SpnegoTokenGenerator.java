package org.apache.http.impl.auth;

import java.io.IOException;

public abstract interface SpnegoTokenGenerator
{
  public abstract byte[] generateSpnegoDERObject(byte[] paramArrayOfByte)
    throws IOException;
}