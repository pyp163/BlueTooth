package com.facebook.common.internal;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ImmutableMap<K, V> extends HashMap<K, V>
{
  private ImmutableMap(Map<? extends K, ? extends V> paramMap)
  {
    super(paramMap);
  }

  public static <K, V> ImmutableMap<K, V> copyOf(Map<? extends K, ? extends V> paramMap)
  {
    return new ImmutableMap(paramMap);
  }

  public static <K, V> Map<K, V> of()
  {
    return Collections.unmodifiableMap(new HashMap());
  }

  public static <K, V> Map<K, V> of(K paramK, V paramV)
  {
    HashMap localHashMap = new HashMap(1);
    localHashMap.put(paramK, paramV);
    return Collections.unmodifiableMap(localHashMap);
  }

  public static <K, V> Map<K, V> of(K paramK1, V paramV1, K paramK2, V paramV2)
  {
    HashMap localHashMap = new HashMap(2);
    localHashMap.put(paramK1, paramV1);
    localHashMap.put(paramK2, paramV2);
    return Collections.unmodifiableMap(localHashMap);
  }

  public static <K, V> Map<K, V> of(K paramK1, V paramV1, K paramK2, V paramV2, K paramK3, V paramV3)
  {
    HashMap localHashMap = new HashMap(3);
    localHashMap.put(paramK1, paramV1);
    localHashMap.put(paramK2, paramV2);
    localHashMap.put(paramK3, paramV3);
    return Collections.unmodifiableMap(localHashMap);
  }

  public static <K, V> Map<K, V> of(K paramK1, V paramV1, K paramK2, V paramV2, K paramK3, V paramV3, K paramK4, V paramV4)
  {
    HashMap localHashMap = new HashMap(4);
    localHashMap.put(paramK1, paramV1);
    localHashMap.put(paramK2, paramV2);
    localHashMap.put(paramK3, paramV3);
    localHashMap.put(paramK4, paramV4);
    return Collections.unmodifiableMap(localHashMap);
  }

  public static <K, V> Map<K, V> of(K paramK1, V paramV1, K paramK2, V paramV2, K paramK3, V paramV3, K paramK4, V paramV4, K paramK5, V paramV5)
  {
    HashMap localHashMap = new HashMap(5);
    localHashMap.put(paramK1, paramV1);
    localHashMap.put(paramK2, paramV2);
    localHashMap.put(paramK3, paramV3);
    localHashMap.put(paramK4, paramV4);
    localHashMap.put(paramK5, paramV5);
    return Collections.unmodifiableMap(localHashMap);
  }

  public static <K, V> Map<K, V> of(K paramK1, V paramV1, K paramK2, V paramV2, K paramK3, V paramV3, K paramK4, V paramV4, K paramK5, V paramV5, K paramK6, V paramV6)
  {
    HashMap localHashMap = new HashMap(6);
    localHashMap.put(paramK1, paramV1);
    localHashMap.put(paramK2, paramV2);
    localHashMap.put(paramK3, paramV3);
    localHashMap.put(paramK4, paramV4);
    localHashMap.put(paramK5, paramV5);
    localHashMap.put(paramK6, paramV6);
    return Collections.unmodifiableMap(localHashMap);
  }
}