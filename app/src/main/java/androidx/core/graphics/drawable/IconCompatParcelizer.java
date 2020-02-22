package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.support.annotation.RestrictTo;
import android.support.v4.graphics.drawable.IconCompat;
import androidx.versionedparcelable.VersionedParcel;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY})
public class IconCompatParcelizer
{
  public static IconCompat read(VersionedParcel paramVersionedParcel)
  {
    IconCompat localIconCompat = new IconCompat();
    localIconCompat.mType = paramVersionedParcel.readInt(localIconCompat.mType, 1);
    localIconCompat.mData = paramVersionedParcel.readByteArray(localIconCompat.mData, 2);
    localIconCompat.mParcelable = paramVersionedParcel.readParcelable(localIconCompat.mParcelable, 3);
    localIconCompat.mInt1 = paramVersionedParcel.readInt(localIconCompat.mInt1, 4);
    localIconCompat.mInt2 = paramVersionedParcel.readInt(localIconCompat.mInt2, 5);
    localIconCompat.mTintList = ((ColorStateList)paramVersionedParcel.readParcelable(localIconCompat.mTintList, 6));
    localIconCompat.mTintModeStr = paramVersionedParcel.readString(localIconCompat.mTintModeStr, 7);
    localIconCompat.onPostParceling();
    return localIconCompat;
  }

  public static void write(IconCompat paramIconCompat, VersionedParcel paramVersionedParcel)
  {
    paramVersionedParcel.setSerializationFlags(true, true);
    paramIconCompat.onPreParceling(paramVersionedParcel.isStream());
    paramVersionedParcel.writeInt(paramIconCompat.mType, 1);
    paramVersionedParcel.writeByteArray(paramIconCompat.mData, 2);
    paramVersionedParcel.writeParcelable(paramIconCompat.mParcelable, 3);
    paramVersionedParcel.writeInt(paramIconCompat.mInt1, 4);
    paramVersionedParcel.writeInt(paramIconCompat.mInt2, 5);
    paramVersionedParcel.writeParcelable(paramIconCompat.mTintList, 6);
    paramVersionedParcel.writeString(paramIconCompat.mTintModeStr, 7);
  }
}