package jonathanfinerty.once;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

class PersistedSet
{
  private static final String DELIMITER = ",";
  private static final String STRING_SET_KEY = "PersistedSetValues";
  private final AsyncSharedPreferenceLoader preferenceLoader;
  private SharedPreferences preferences;
  private Set<String> set = new HashSet();

  public PersistedSet(Context paramContext, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(PersistedSet.class.getSimpleName());
    localStringBuilder.append(paramString);
    this.preferenceLoader = new AsyncSharedPreferenceLoader(paramContext, localStringBuilder.toString());
  }

  private String StringSetToString(Set<String> paramSet)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    String str = "";
    Iterator localIterator = paramSet.iterator();
    for (paramSet = str; localIterator.hasNext(); paramSet = ",")
    {
      str = (String)localIterator.next();
      localStringBuilder.append(paramSet);
      localStringBuilder.append(str);
    }
    return localStringBuilder.toString();
  }

  @NonNull
  private Set<String> StringToStringSet(@Nullable String paramString)
  {
    if (paramString == null)
      return new HashSet();
    return new HashSet(Arrays.asList(paramString.split(",")));
  }

  private void updatePreferences()
  {
    SharedPreferences.Editor localEditor = this.preferences.edit();
    if (Build.VERSION.SDK_INT >= 11)
      localEditor.putStringSet("PersistedSetValues", this.set);
    else
      localEditor.putString("PersistedSetValues", StringSetToString(this.set));
    localEditor.apply();
  }

  private void waitForLoad()
  {
    if (this.preferences == null)
    {
      this.preferences = this.preferenceLoader.get();
      if (Build.VERSION.SDK_INT >= 11)
      {
        this.set = this.preferences.getStringSet("PersistedSetValues", new HashSet());
        return;
      }
      this.set = new HashSet(StringToStringSet(this.preferences.getString("PersistedSetValues", null)));
    }
  }

  public void clear()
  {
    waitForLoad();
    this.set.clear();
    updatePreferences();
  }

  public boolean contains(String paramString)
  {
    waitForLoad();
    return this.set.contains(paramString);
  }

  public void put(String paramString)
  {
    waitForLoad();
    this.set.add(paramString);
    updatePreferences();
  }

  public void remove(String paramString)
  {
    waitForLoad();
    this.set.remove(paramString);
    updatePreferences();
  }
}