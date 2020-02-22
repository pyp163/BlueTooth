package okhttp3.internal.cache;

import java.io.IOException;
import javax.annotation.Nullable;
import okhttp3.Request;
import okhttp3.Response;

public abstract interface InternalCache
{
  @Nullable
  public abstract Response get(Request paramRequest)
    throws IOException;

  @Nullable
  public abstract CacheRequest put(Response paramResponse)
    throws IOException;

  public abstract void remove(Request paramRequest)
    throws IOException;

  public abstract void trackConditionalCacheHit();

  public abstract void trackResponse(CacheStrategy paramCacheStrategy);

  public abstract void update(Response paramResponse1, Response paramResponse2);
}