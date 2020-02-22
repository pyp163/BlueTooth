package android.support.v4.graphics.drawable;

import android.support.annotation.RestrictTo;
import androidx.versionedparcelable.VersionedParcel;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
public final class IconCompatParcelizer extends androidx.core.graphics.drawable.IconCompatParcelizer
{
  public static IconCompat read(VersionedParcel paramVersionedParcel)
  {
    return androidx.core.graphics.drawable.IconCompatParcelizer.read(paramVersionedParcel);
  }

  public static void write(IconCompat paramIconCompat, VersionedParcel paramVersionedParcel)
  {
    androidx.core.graphics.drawable.IconCompatParcelizer.write(paramIconCompat, paramVersionedParcel);
  }
}