package android.support.design.resources;

public class TextAppearanceConfig
{
  private static boolean shouldLoadFontSynchronously;

  public static void setShouldLoadFontSynchronously(boolean paramBoolean)
  {
    shouldLoadFontSynchronously = paramBoolean;
  }

  public static boolean shouldLoadFontSynchronously()
  {
    return shouldLoadFontSynchronously;
  }
}