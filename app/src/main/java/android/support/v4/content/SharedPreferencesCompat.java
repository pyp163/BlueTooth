package android.support.v4.content;

import android.content.SharedPreferences.Editor;
import android.support.annotation.NonNull;

@Deprecated
public final class SharedPreferencesCompat
{
  @Deprecated
  public static final class EditorCompat
  {
    private static EditorCompat sInstance;
    private final Helper mHelper = new Helper();

    @Deprecated
    public static EditorCompat getInstance()
    {
      if (sInstance == null)
        sInstance = new EditorCompat();
      return sInstance;
    }

    @Deprecated
    public void apply(@NonNull SharedPreferences.Editor paramEditor)
    {
      this.mHelper.apply(paramEditor);
    }

    private static class Helper
    {
      public void apply(@NonNull SharedPreferences.Editor paramEditor)
      {
        try
        {
          paramEditor.apply();
          return;
          label7: paramEditor.commit();
          return;
        }
        catch (AbstractMethodError localAbstractMethodError)
        {
          break label7;
        }
      }
    }
  }
}