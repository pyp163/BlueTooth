package androidx.versionedparcelable;

import android.support.annotation.RestrictTo;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public abstract class CustomVersionedParcelable
  implements VersionedParcelable
{
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public void onPostParceling()
  {
  }

  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public void onPreParceling(boolean paramBoolean)
  {
  }
}