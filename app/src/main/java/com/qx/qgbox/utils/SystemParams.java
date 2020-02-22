package com.qx.qgbox.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.Set;

public class SystemParams
{
  private static SystemParams instance;
  private static SharedPreferences sharedPrederences;

  public static SystemParams getInstance()
  {
    if (instance == null)
      try
      {
        if (instance == null)
          instance = new SystemParams();
      }
      finally
      {
      }
    return instance;
  }

  public static void init(Context paramContext)
  {
    sharedPrederences = paramContext.getSharedPreferences("hobbees", 0);
  }

  public void clear()
  {
    sharedPrederences.edit().clear().commit();
  }

  public boolean getBoolean(String paramString)
  {
    return sharedPrederences.getBoolean(paramString, false);
  }

  public boolean getBoolean(String paramString, boolean paramBoolean)
  {
    return sharedPrederences.getBoolean(paramString, paramBoolean);
  }

  public float getFloat(String paramString)
  {
    return sharedPrederences.getFloat(paramString, 0.0F);
  }

  public float getFloat(String paramString, float paramFloat)
  {
    return sharedPrederences.getFloat(paramString, paramFloat);
  }

  public int getInt(String paramString)
  {
    return sharedPrederences.getInt(paramString, 0);
  }

  public int getInt(String paramString, int paramInt)
  {
    return sharedPrederences.getInt(paramString, paramInt);
  }

  public long getLong(String paramString)
  {
    return sharedPrederences.getLong(paramString, 0L);
  }

  public long getLong(String paramString, long paramLong)
  {
    return sharedPrederences.getLong(paramString, paramLong);
  }

  public String getString(String paramString)
  {
    return sharedPrederences.getString(paramString, null);
  }

  public String getString(String paramString1, String paramString2)
  {
    return sharedPrederences.getString(paramString1, paramString2);
  }

  public void remove(String paramString)
  {
    SharedPreferences.Editor localEditor = sharedPrederences.edit();
    localEditor.remove(paramString);
    localEditor.commit();
  }

  public void setBoolean(String paramString, boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = sharedPrederences.edit();
    localEditor.putBoolean(paramString, paramBoolean);
    localEditor.commit();
  }

  public void setFloat(String paramString, float paramFloat)
  {
    SharedPreferences.Editor localEditor = sharedPrederences.edit();
    localEditor.putFloat(paramString, paramFloat);
    localEditor.commit();
  }

  public void setInt(String paramString, int paramInt)
  {
    SharedPreferences.Editor localEditor = sharedPrederences.edit();
    localEditor.putInt(paramString, paramInt);
    localEditor.commit();
  }

  public void setLong(String paramString, long paramLong)
  {
    SharedPreferences.Editor localEditor = sharedPrederences.edit();
    localEditor.putLong(paramString, paramLong);
    localEditor.commit();
  }

  public void setSetString(String paramString, Set<String> paramSet)
  {
    SharedPreferences.Editor localEditor = sharedPrederences.edit();
    localEditor.putStringSet(paramString, paramSet);
    localEditor.commit();
  }

  public void setString(String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = sharedPrederences.edit();
    localEditor.putString(paramString1, paramString2);
    localEditor.commit();
  }
}
