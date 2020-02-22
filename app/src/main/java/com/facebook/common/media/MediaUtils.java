package com.facebook.common.media;

import android.webkit.MimeTypeMap;
import com.facebook.common.internal.ImmutableMap;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;

public class MediaUtils
{
  public static final Map<String, String> ADDITIONAL_ALLOWED_MIME_TYPES = ImmutableMap.of("mkv", "video/x-matroska", "glb", "model/gltf-binary");

  @Nullable
  private static String extractExtension(String paramString)
  {
    int i = paramString.lastIndexOf('.');
    if ((i >= 0) && (i != paramString.length() - 1))
      return paramString.substring(i + 1);
    return null;
  }

  @Nullable
  public static String extractMime(String paramString)
  {
    paramString = extractExtension(paramString);
    if (paramString == null)
      return null;
    String str2 = paramString.toLowerCase(Locale.US);
    String str1 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(str2);
    paramString = str1;
    if (str1 == null)
      paramString = (String)ADDITIONAL_ALLOWED_MIME_TYPES.get(str2);
    return paramString;
  }

  public static boolean isNonNativeSupportedMimeType(String paramString)
  {
    return ADDITIONAL_ALLOWED_MIME_TYPES.containsValue(paramString);
  }

  public static boolean isPhoto(@Nullable String paramString)
  {
    return (paramString != null) && (paramString.startsWith("image/"));
  }

  public static boolean isThreeD(@Nullable String paramString)
  {
    return (paramString != null) && (paramString.equals("model/gltf-binary"));
  }

  public static boolean isVideo(@Nullable String paramString)
  {
    return (paramString != null) && (paramString.startsWith("video/"));
  }
}