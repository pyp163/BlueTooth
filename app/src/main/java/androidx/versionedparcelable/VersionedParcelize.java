package androidx.versionedparcelable;

import android.support.annotation.RestrictTo;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({java.lang.annotation.ElementType.TYPE})
@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public @interface VersionedParcelize
{
  public abstract boolean allowSerialization();

  public abstract int[] deprecatedIds();

  public abstract boolean ignoreParcelables();

  public abstract boolean isCustom();

  public abstract String jetifyAs();
}