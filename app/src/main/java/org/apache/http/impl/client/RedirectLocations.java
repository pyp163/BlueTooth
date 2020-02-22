package org.apache.http.impl.client;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class RedirectLocations
{
  private final List<URI> all = new ArrayList();
  private final Set<URI> unique = new HashSet();

  public void add(URI paramURI)
  {
    this.unique.add(paramURI);
    this.all.add(paramURI);
  }

  public boolean contains(URI paramURI)
  {
    return this.unique.contains(paramURI);
  }

  public List<URI> getAll()
  {
    return new ArrayList(this.all);
  }

  public boolean remove(URI paramURI)
  {
    boolean bool = this.unique.remove(paramURI);
    if (bool)
    {
      Iterator localIterator = this.all.iterator();
      while (localIterator.hasNext())
        if (((URI)localIterator.next()).equals(paramURI))
          localIterator.remove();
    }
    return bool;
  }
}