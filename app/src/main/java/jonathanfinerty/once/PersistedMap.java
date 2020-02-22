package jonathanfinerty.once;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.List<Ljava.lang.Long;>;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

class PersistedMap
{
  private static final String DELIMITER = ",";
  private final Map<String, List<Long>> map = new ConcurrentHashMap();
  private final AsyncSharedPreferenceLoader preferenceLoader;
  private SharedPreferences preferences;

  public PersistedMap(Context paramContext, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(PersistedMap.class.getSimpleName());
    localStringBuilder.append(paramString);
    this.preferenceLoader = new AsyncSharedPreferenceLoader(paramContext, localStringBuilder.toString());
  }

  private String listToString(List<Long> paramList)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Object localObject = "";
    Iterator localIterator = paramList.iterator();
    for (paramList = (List<Long>)localObject; localIterator.hasNext(); paramList = ",")
    {
      localObject = (Long)localIterator.next();
      localStringBuilder.append(paramList);
      localStringBuilder.append(localObject);
    }
    return localStringBuilder.toString();
  }

  private List<Long> loadFromLegacyStorageFormat(String paramString)
  {
    long l = this.preferences.getLong(paramString, -1L);
    ArrayList localArrayList = new ArrayList(1);
    localArrayList.add(Long.valueOf(l));
    this.preferences.edit().putString(paramString, listToString(localArrayList)).apply();
    return localArrayList;
  }

  private List<Long> stringToList(String paramString)
  {
    if ((paramString != null) && (!paramString.isEmpty()))
    {
      paramString = paramString.split(",");
      ArrayList localArrayList = new ArrayList(paramString.length);
      int j = paramString.length;
      int i = 0;
      while (i < j)
      {
        localArrayList.add(Long.valueOf(Long.parseLong(paramString[i])));
        i += 1;
      }
      return localArrayList;
    }
    return Collections.emptyList();
  }

  private void waitForLoad()
  {
    if (this.preferences != null)
      return;
    this.preferences = this.preferenceLoader.get();
    Iterator localIterator = this.preferences.getAll().keySet().iterator();
    while (true)
    {
      String str;
      if (localIterator.hasNext())
        str = (String)localIterator.next();
      try
      {
        List localList = stringToList(this.preferences.getString(str, null));
        break label83;
        label77: localList = loadFromLegacyStorageFormat(str);
        label83: this.map.put(str, localList);
        continue;
        return;
      }
      catch (ClassCastException localClassCastException)
      {
        break label77;
      }
    }
  }

  public void clear()
  {
    waitForLoad();
    this.map.clear();
    SharedPreferences.Editor localEditor = this.preferences.edit();
    localEditor.clear();
    localEditor.apply();
  }

  @NonNull
  public List<Long> get(String paramString)
  {
    waitForLoad();
    paramString = (List)this.map.get(paramString);
    if (paramString == null)
      return Collections.emptyList();
    return paramString;
  }

  public void put(String paramString, long paramLong)
  {
    waitForLoad();
    Object localObject2 = (List)this.map.get(paramString);
    Object localObject1 = localObject2;
    if (localObject2 == null)
      localObject1 = new ArrayList(1);
    ((List)localObject1).add(Long.valueOf(paramLong));
    this.map.put(paramString, localObject1);
    localObject2 = this.preferences.edit();
    ((SharedPreferences.Editor)localObject2).putString(paramString, listToString((List)localObject1));
    ((SharedPreferences.Editor)localObject2).apply();
  }

  public void remove(String paramString)
  {
    waitForLoad();
    this.map.remove(paramString);
    SharedPreferences.Editor localEditor = this.preferences.edit();
    localEditor.remove(paramString);
    localEditor.apply();
  }
}