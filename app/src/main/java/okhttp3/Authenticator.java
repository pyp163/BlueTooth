package okhttp3;

import java.io.IOException;
import javax.annotation.Nullable;

public abstract interface Authenticator
{
  public static final Authenticator NONE = Authenticator..Lambda.0.$instance;

  @Nullable
  public abstract Request authenticate(@Nullable Route paramRoute, Response paramResponse)
    throws IOException;
}