package org.apache.http.entity.mime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Header
  implements Iterable<MinimalField>
{
  private final Map<String, List<MinimalField>> fieldMap = new HashMap();
  private final List<MinimalField> fields = new LinkedList();

  public void addField(MinimalField paramMinimalField)
  {
    if (paramMinimalField == null)
      return;
    String str = paramMinimalField.getName().toLowerCase(Locale.US);
    List localList = (List)this.fieldMap.get(str);
    Object localObject = localList;
    if (localList == null)
    {
      localObject = new LinkedList();
      this.fieldMap.put(str, localObject);
    }
    ((List)localObject).add(paramMinimalField);
    this.fields.add(paramMinimalField);
  }

  public MinimalField getField(String paramString)
  {
    if (paramString == null)
      return null;
    paramString = paramString.toLowerCase(Locale.US);
    paramString = (List)this.fieldMap.get(paramString);
    if ((paramString != null) && (!paramString.isEmpty()))
      return (MinimalField)paramString.get(0);
    return null;
  }

  public List<MinimalField> getFields()
  {
    return new ArrayList(this.fields);
  }

  public List<MinimalField> getFields(String paramString)
  {
    if (paramString == null)
      return null;
    paramString = paramString.toLowerCase(Locale.US);
    paramString = (List)this.fieldMap.get(paramString);
    if ((paramString != null) && (!paramString.isEmpty()))
      return new ArrayList(paramString);
    return Collections.emptyList();
  }

  public Iterator<MinimalField> iterator()
  {
    return Collections.unmodifiableList(this.fields).iterator();
  }

  public int removeFields(String paramString)
  {
    if (paramString == null)
      return 0;
    paramString = paramString.toLowerCase(Locale.US);
    paramString = (List)this.fieldMap.remove(paramString);
    if (paramString != null)
    {
      if (paramString.isEmpty())
        return 0;
      this.fields.removeAll(paramString);
      return paramString.size();
    }
    return 0;
  }

  public void setField(MinimalField paramMinimalField)
  {
    if (paramMinimalField == null)
      return;
    Object localObject = paramMinimalField.getName().toLowerCase(Locale.US);
    localObject = (List)this.fieldMap.get(localObject);
    if ((localObject != null) && (!((List)localObject).isEmpty()))
    {
      ((List)localObject).clear();
      ((List)localObject).add(paramMinimalField);
      localObject = this.fields.iterator();
      int j = -1;
      int i = 0;
      while (((Iterator)localObject).hasNext())
      {
        int k = j;
        if (((MinimalField)((Iterator)localObject).next()).getName().equalsIgnoreCase(paramMinimalField.getName()))
        {
          ((Iterator)localObject).remove();
          k = j;
          if (j == -1)
            k = i;
        }
        i += 1;
        j = k;
      }
      this.fields.add(j, paramMinimalField);
      return;
    }
    addField(paramMinimalField);
  }

  public String toString()
  {
    return this.fields.toString();
  }
}