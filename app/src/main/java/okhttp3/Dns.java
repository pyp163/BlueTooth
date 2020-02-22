package okhttp3;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

public abstract interface Dns
{
  public static final Dns SYSTEM = Dns..Lambda.0.$instance;

  public abstract List<InetAddress> lookup(String paramString)
    throws UnknownHostException;
}