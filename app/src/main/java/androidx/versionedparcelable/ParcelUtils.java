package androidx.versionedparcelable;

import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import java.io.InputStream;
import java.io.OutputStream;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class ParcelUtils
{
  public static <T extends VersionedParcelable> T fromInputStream(InputStream paramInputStream)
  {
    return new VersionedParcelStream(paramInputStream, null).readVersionedParcelable();
  }

  public static <T extends VersionedParcelable> T fromParcelable(Parcelable paramParcelable)
  {
    if (!(paramParcelable instanceof ParcelImpl))
      throw new IllegalArgumentException("Invalid parcel");
    return ((ParcelImpl)paramParcelable).getVersionedParcel();
  }

  public static void toOutputStream(VersionedParcelable paramVersionedParcelable, OutputStream paramOutputStream)
  {
    paramOutputStream = new VersionedParcelStream(null, paramOutputStream);
    paramOutputStream.writeVersionedParcelable(paramVersionedParcelable);
    paramOutputStream.closeField();
  }

  public static Parcelable toParcelable(VersionedParcelable paramVersionedParcelable)
  {
    return new ParcelImpl(paramVersionedParcelable);
  }
}