package android.support.v4.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.ConcurrentModificationException;

public class SimpleArrayMap<K, V>
{
  private static final int BASE_SIZE = 4;
  private static final int CACHE_SIZE = 10;
  private static final boolean CONCURRENT_MODIFICATION_EXCEPTIONS = true;
  private static final boolean DEBUG = false;
  private static final String TAG = "ArrayMap";

  @Nullable
  static Object[] mBaseCache;
  static int mBaseCacheSize;

  @Nullable
  static Object[] mTwiceBaseCache;
  static int mTwiceBaseCacheSize;
  Object[] mArray;
  int[] mHashes;
  int mSize;

  public SimpleArrayMap()
  {
    this.mHashes = ContainerHelpers.EMPTY_INTS;
    this.mArray = ContainerHelpers.EMPTY_OBJECTS;
    this.mSize = 0;
  }

  public SimpleArrayMap(int paramInt)
  {
    if (paramInt == 0)
    {
      this.mHashes = ContainerHelpers.EMPTY_INTS;
      this.mArray = ContainerHelpers.EMPTY_OBJECTS;
    }
    else
    {
      allocArrays(paramInt);
    }
    this.mSize = 0;
  }

  public SimpleArrayMap(SimpleArrayMap<K, V> paramSimpleArrayMap)
  {
    this();
    if (paramSimpleArrayMap != null)
      putAll(paramSimpleArrayMap);
  }

  private void allocArrays(int paramInt)
  {
    if (paramInt == 8)
      try
      {
        if (mTwiceBaseCache != null)
        {
          Object[] arrayOfObject1 = mTwiceBaseCache;
          this.mArray = arrayOfObject1;
          mTwiceBaseCache = (Object[])arrayOfObject1[0];
          this.mHashes = ((int[])arrayOfObject1[1]);
          arrayOfObject1[1] = null;
          arrayOfObject1[0] = null;
          mTwiceBaseCacheSize -= 1;
          return;
        }
      }
      finally
      {
      }
    else if (paramInt == 4)
      try
      {
        if (mBaseCache != null)
        {
          Object[] arrayOfObject2 = mBaseCache;
          this.mArray = arrayOfObject2;
          mBaseCache = (Object[])arrayOfObject2[0];
          this.mHashes = ((int[])arrayOfObject2[1]);
          arrayOfObject2[1] = null;
          arrayOfObject2[0] = null;
          mBaseCacheSize -= 1;
          return;
        }
      }
      finally
      {
      }
    this.mHashes = new int[paramInt];
    this.mArray = new Object[paramInt << 1];
  }

  private static int binarySearchHashes(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    try
    {
      paramInt1 = ContainerHelpers.binarySearch(paramArrayOfInt, paramInt1, paramInt2);
      return paramInt1;
      label9: throw new ConcurrentModificationException();
    }
    catch (ArrayIndexOutOfBoundsException paramArrayOfInt)
    {
      break label9;
    }
  }

  private static void freeArrays(int[] paramArrayOfInt, Object[] paramArrayOfObject, int paramInt)
  {
    if (paramArrayOfInt.length == 8)
      try
      {
        if (mTwiceBaseCacheSize < 10)
        {
          paramArrayOfObject[0] = mTwiceBaseCache;
          paramArrayOfObject[1] = paramArrayOfInt;
          paramInt = (paramInt << 1) - 1;
          break label118;
          mTwiceBaseCache = paramArrayOfObject;
          mTwiceBaseCacheSize += 1;
        }
        return;
      }
      finally
      {
      }
    else if (paramArrayOfInt.length != 4);
    while (true)
    {
      try
      {
        if (mBaseCacheSize < 10)
        {
          paramArrayOfObject[0] = mBaseCache;
          paramArrayOfObject[1] = paramArrayOfInt;
          paramInt = (paramInt << 1) - 1;
          break label134;
          mBaseCache = paramArrayOfObject;
          mBaseCacheSize += 1;
        }
        return;
      }
      finally
      {
      }
      return;
      label118: 
      while (paramInt >= 2)
      {
        paramArrayOfObject[paramInt] = null;
        paramInt -= 1;
      }
      break;
      label134: 
      while (paramInt >= 2)
      {
        paramArrayOfObject[paramInt] = null;
        paramInt -= 1;
      }
    }
  }

  public void clear()
  {
    if (this.mSize > 0)
    {
      int[] arrayOfInt = this.mHashes;
      Object[] arrayOfObject = this.mArray;
      int i = this.mSize;
      this.mHashes = ContainerHelpers.EMPTY_INTS;
      this.mArray = ContainerHelpers.EMPTY_OBJECTS;
      this.mSize = 0;
      freeArrays(arrayOfInt, arrayOfObject, i);
    }
    if (this.mSize > 0)
      throw new ConcurrentModificationException();
  }

  public boolean containsKey(@Nullable Object paramObject)
  {
    return indexOfKey(paramObject) >= 0;
  }

  public boolean containsValue(Object paramObject)
  {
    return indexOfValue(paramObject) >= 0;
  }

  public void ensureCapacity(int paramInt)
  {
    int i = this.mSize;
    if (this.mHashes.length < paramInt)
    {
      int[] arrayOfInt = this.mHashes;
      Object[] arrayOfObject = this.mArray;
      allocArrays(paramInt);
      if (this.mSize > 0)
      {
        System.arraycopy(arrayOfInt, 0, this.mHashes, 0, i);
        System.arraycopy(arrayOfObject, 0, this.mArray, 0, i << 1);
      }
      freeArrays(arrayOfInt, arrayOfObject, i);
    }
    if (this.mSize != i)
      throw new ConcurrentModificationException();
  }

  // ERROR //
  public boolean equals(Object paramObject)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: if_acmpne +5 -> 7
    //   5: iconst_1
    //   6: ireturn
    //   7: aload_1
    //   8: instanceof 2
    //   11: ifeq +97 -> 108
    //   14: aload_1
    //   15: checkcast 2	android/support/v4/util/SimpleArrayMap
    //   18: astore_1
    //   19: aload_0
    //   20: invokevirtual 113	android/support/v4/util/SimpleArrayMap:size	()I
    //   23: aload_1
    //   24: invokevirtual 113	android/support/v4/util/SimpleArrayMap:size	()I
    //   27: if_icmpeq +5 -> 32
    //   30: iconst_0
    //   31: ireturn
    //   32: iconst_0
    //   33: istore_2
    //   34: iload_2
    //   35: aload_0
    //   36: getfield 47	android/support/v4/util/SimpleArrayMap:mSize	I
    //   39: if_icmpge +67 -> 106
    //   42: aload_0
    //   43: iload_2
    //   44: invokevirtual 117	android/support/v4/util/SimpleArrayMap:keyAt	(I)Ljava/lang/Object;
    //   47: astore 4
    //   49: aload_0
    //   50: iload_2
    //   51: invokevirtual 120	android/support/v4/util/SimpleArrayMap:valueAt	(I)Ljava/lang/Object;
    //   54: astore 5
    //   56: aload_1
    //   57: aload 4
    //   59: invokevirtual 124	android/support/v4/util/SimpleArrayMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   62: astore 6
    //   64: aload 5
    //   66: ifnonnull +19 -> 85
    //   69: aload 6
    //   71: ifnonnull +158 -> 229
    //   74: aload_1
    //   75: aload 4
    //   77: invokevirtual 126	android/support/v4/util/SimpleArrayMap:containsKey	(Ljava/lang/Object;)Z
    //   80: ifne +19 -> 99
    //   83: iconst_0
    //   84: ireturn
    //   85: aload 5
    //   87: aload 6
    //   89: invokevirtual 128	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   92: istore_3
    //   93: iload_3
    //   94: ifne +5 -> 99
    //   97: iconst_0
    //   98: ireturn
    //   99: iload_2
    //   100: iconst_1
    //   101: iadd
    //   102: istore_2
    //   103: goto -69 -> 34
    //   106: iconst_1
    //   107: ireturn
    //   108: aload_1
    //   109: instanceof 130
    //   112: ifeq +103 -> 215
    //   115: aload_1
    //   116: checkcast 130	java/util/Map
    //   119: astore_1
    //   120: aload_0
    //   121: invokevirtual 113	android/support/v4/util/SimpleArrayMap:size	()I
    //   124: aload_1
    //   125: invokeinterface 131 1 0
    //   130: if_icmpeq +5 -> 135
    //   133: iconst_0
    //   134: ireturn
    //   135: iconst_0
    //   136: istore_2
    //   137: iload_2
    //   138: aload_0
    //   139: getfield 47	android/support/v4/util/SimpleArrayMap:mSize	I
    //   142: if_icmpge +71 -> 213
    //   145: aload_0
    //   146: iload_2
    //   147: invokevirtual 117	android/support/v4/util/SimpleArrayMap:keyAt	(I)Ljava/lang/Object;
    //   150: astore 4
    //   152: aload_0
    //   153: iload_2
    //   154: invokevirtual 120	android/support/v4/util/SimpleArrayMap:valueAt	(I)Ljava/lang/Object;
    //   157: astore 5
    //   159: aload_1
    //   160: aload 4
    //   162: invokeinterface 132 2 0
    //   167: astore 6
    //   169: aload 5
    //   171: ifnonnull +21 -> 192
    //   174: aload 6
    //   176: ifnonnull +55 -> 231
    //   179: aload_1
    //   180: aload 4
    //   182: invokeinterface 133 2 0
    //   187: ifne +19 -> 206
    //   190: iconst_0
    //   191: ireturn
    //   192: aload 5
    //   194: aload 6
    //   196: invokevirtual 128	java/lang/Object:equals	(Ljava/lang/Object;)Z
    //   199: istore_3
    //   200: iload_3
    //   201: ifne +5 -> 206
    //   204: iconst_0
    //   205: ireturn
    //   206: iload_2
    //   207: iconst_1
    //   208: iadd
    //   209: istore_2
    //   210: goto -73 -> 137
    //   213: iconst_1
    //   214: ireturn
    //   215: iconst_0
    //   216: ireturn
    //   217: astore_1
    //   218: iconst_0
    //   219: ireturn
    //   220: astore_1
    //   221: iconst_0
    //   222: ireturn
    //   223: astore_1
    //   224: iconst_0
    //   225: ireturn
    //   226: astore_1
    //   227: iconst_0
    //   228: ireturn
    //   229: iconst_0
    //   230: ireturn
    //   231: iconst_0
    //   232: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   34	64	217	java/lang/NullPointerException
    //   74	83	217	java/lang/NullPointerException
    //   85	93	217	java/lang/NullPointerException
    //   34	64	220	java/lang/ClassCastException
    //   74	83	220	java/lang/ClassCastException
    //   85	93	220	java/lang/ClassCastException
    //   137	169	223	java/lang/NullPointerException
    //   179	190	223	java/lang/NullPointerException
    //   192	200	223	java/lang/NullPointerException
    //   137	169	226	java/lang/ClassCastException
    //   179	190	226	java/lang/ClassCastException
    //   192	200	226	java/lang/ClassCastException
  }

  @Nullable
  public V get(Object paramObject)
  {
    int i = indexOfKey(paramObject);
    if (i >= 0)
      return this.mArray[((i << 1) + 1)];
    return null;
  }

  public int hashCode()
  {
    int[] arrayOfInt = this.mHashes;
    Object[] arrayOfObject = this.mArray;
    int n = this.mSize;
    int j = 0;
    int i = 1;
    int k = 0;
    while (j < n)
    {
      Object localObject = arrayOfObject[i];
      int i1 = arrayOfInt[j];
      int m;
      if (localObject == null)
        m = 0;
      else
        m = localObject.hashCode();
      k += (m ^ i1);
      j += 1;
      i += 2;
    }
    return k;
  }

  int indexOf(Object paramObject, int paramInt)
  {
    int j = this.mSize;
    if (j == 0)
      return -1;
    int k = binarySearchHashes(this.mHashes, j, paramInt);
    if (k < 0)
      return k;
    if (paramObject.equals(this.mArray[(k << 1)]))
      return k;
    int i = k + 1;
    while ((i < j) && (this.mHashes[i] == paramInt))
    {
      if (paramObject.equals(this.mArray[(i << 1)]))
        return i;
      i += 1;
    }
    j = k - 1;
    while ((j >= 0) && (this.mHashes[j] == paramInt))
    {
      if (paramObject.equals(this.mArray[(j << 1)]))
        return j;
      j -= 1;
    }
    return i;
  }

  public int indexOfKey(@Nullable Object paramObject)
  {
    if (paramObject == null)
      return indexOfNull();
    return indexOf(paramObject, paramObject.hashCode());
  }

  int indexOfNull()
  {
    int j = this.mSize;
    if (j == 0)
      return -1;
    int k = binarySearchHashes(this.mHashes, j, 0);
    if (k < 0)
      return k;
    if (this.mArray[(k << 1)] == null)
      return k;
    int i = k + 1;
    while ((i < j) && (this.mHashes[i] == 0))
    {
      if (this.mArray[(i << 1)] == null)
        return i;
      i += 1;
    }
    j = k - 1;
    while ((j >= 0) && (this.mHashes[j] == 0))
    {
      if (this.mArray[(j << 1)] == null)
        return j;
      j -= 1;
    }
    return i;
  }

  int indexOfValue(Object paramObject)
  {
    int j = this.mSize * 2;
    Object[] arrayOfObject = this.mArray;
    if (paramObject == null)
    {
      i = 1;
      while (i < j)
      {
        if (arrayOfObject[i] == null)
          return i >> 1;
        i += 2;
      }
    }
    int i = 1;
    while (i < j)
    {
      if (paramObject.equals(arrayOfObject[i]))
        return i >> 1;
      i += 2;
    }
    return -1;
  }

  public boolean isEmpty()
  {
    return this.mSize <= 0;
  }

  public K keyAt(int paramInt)
  {
    return this.mArray[(paramInt << 1)];
  }

  @Nullable
  public V put(K paramK, V paramV)
  {
    int k = this.mSize;
    int i;
    int j;
    if (paramK == null)
    {
      i = indexOfNull();
      j = 0;
    }
    else
    {
      j = paramK.hashCode();
      i = indexOf(paramK, j);
    }
    if (i >= 0)
    {
      i = (i << 1) + 1;
      paramK = this.mArray[i];
      this.mArray[i] = paramV;
      return paramK;
    }
    int m = i;
    Object localObject1;
    Object localObject2;
    if (k >= this.mHashes.length)
    {
      i = 4;
      if (k >= 8)
        i = (k >> 1) + k;
      else if (k >= 4)
        i = 8;
      localObject1 = this.mHashes;
      localObject2 = this.mArray;
      allocArrays(i);
      if (k != this.mSize)
        throw new ConcurrentModificationException();
      if (this.mHashes.length > 0)
      {
        System.arraycopy(localObject1, 0, this.mHashes, 0, localObject1.length);
        System.arraycopy(localObject2, 0, this.mArray, 0, localObject2.length);
      }
      freeArrays((int[])localObject1, (Object[])localObject2, k);
    }
    if (m < k)
    {
      localObject1 = this.mHashes;
      localObject2 = this.mHashes;
      i = m + 1;
      System.arraycopy(localObject1, m, localObject2, i, k - m);
      System.arraycopy(this.mArray, m << 1, this.mArray, i << 1, this.mSize - m << 1);
    }
    if ((k == this.mSize) && (m < this.mHashes.length))
    {
      this.mHashes[m] = j;
      localObject1 = this.mArray;
      i = m << 1;
      localObject1[i] = paramK;
      this.mArray[(i + 1)] = paramV;
      this.mSize += 1;
      return null;
    }
    throw new ConcurrentModificationException();
  }

  public void putAll(@NonNull SimpleArrayMap<? extends K, ? extends V> paramSimpleArrayMap)
  {
    int j = paramSimpleArrayMap.mSize;
    ensureCapacity(this.mSize + j);
    int k = this.mSize;
    int i = 0;
    if (k == 0)
    {
      if (j > 0)
      {
        System.arraycopy(paramSimpleArrayMap.mHashes, 0, this.mHashes, 0, j);
        System.arraycopy(paramSimpleArrayMap.mArray, 0, this.mArray, 0, j << 1);
        this.mSize = j;
      }
    }
    else
      while (i < j)
      {
        put(paramSimpleArrayMap.keyAt(i), paramSimpleArrayMap.valueAt(i));
        i += 1;
      }
  }

  @Nullable
  public V remove(Object paramObject)
  {
    int i = indexOfKey(paramObject);
    if (i >= 0)
      return removeAt(i);
    return null;
  }

  public V removeAt(int paramInt)
  {
    Object localObject1 = this.mArray;
    int m = paramInt << 1;
    localObject1 = localObject1[(m + 1)];
    int k = this.mSize;
    int i = 0;
    if (k <= 1)
    {
      freeArrays(this.mHashes, this.mArray, k);
      this.mHashes = ContainerHelpers.EMPTY_INTS;
      this.mArray = ContainerHelpers.EMPTY_OBJECTS;
      paramInt = i;
    }
    else
    {
      int j = k - 1;
      int n = this.mHashes.length;
      i = 8;
      Object localObject2;
      Object localObject3;
      if ((n > 8) && (this.mSize < this.mHashes.length / 3))
      {
        if (k > 8)
          i = k + (k >> 1);
        localObject2 = this.mHashes;
        localObject3 = this.mArray;
        allocArrays(i);
        if (k != this.mSize)
          throw new ConcurrentModificationException();
        if (paramInt > 0)
        {
          System.arraycopy(localObject2, 0, this.mHashes, 0, paramInt);
          System.arraycopy(localObject3, 0, this.mArray, 0, m);
        }
        if (paramInt < j)
        {
          i = paramInt + 1;
          int[] arrayOfInt = this.mHashes;
          n = j - paramInt;
          System.arraycopy(localObject2, i, arrayOfInt, paramInt, n);
          System.arraycopy(localObject3, i << 1, this.mArray, m, n << 1);
        }
      }
      else
      {
        if (paramInt < j)
        {
          localObject2 = this.mHashes;
          i = paramInt + 1;
          localObject3 = this.mHashes;
          n = j - paramInt;
          System.arraycopy(localObject2, i, localObject3, paramInt, n);
          System.arraycopy(this.mArray, i << 1, this.mArray, m, n << 1);
        }
        localObject2 = this.mArray;
        paramInt = j << 1;
        localObject2[paramInt] = null;
        this.mArray[(paramInt + 1)] = null;
      }
      paramInt = j;
    }
    if (k != this.mSize)
      throw new ConcurrentModificationException();
    this.mSize = paramInt;
    return localObject1;
  }

  public V setValueAt(int paramInt, V paramV)
  {
    paramInt = (paramInt << 1) + 1;
    Object localObject = this.mArray[paramInt];
    this.mArray[paramInt] = paramV;
    return localObject;
  }

  public int size()
  {
    return this.mSize;
  }

  public String toString()
  {
    if (isEmpty())
      return "{}";
    StringBuilder localStringBuilder = new StringBuilder(this.mSize * 28);
    localStringBuilder.append('{');
    int i = 0;
    while (i < this.mSize)
    {
      if (i > 0)
        localStringBuilder.append(", ");
      Object localObject = keyAt(i);
      if (localObject != this)
        localStringBuilder.append(localObject);
      else
        localStringBuilder.append("(this Map)");
      localStringBuilder.append('=');
      localObject = valueAt(i);
      if (localObject != this)
        localStringBuilder.append(localObject);
      else
        localStringBuilder.append("(this Map)");
      i += 1;
    }
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }

  public V valueAt(int paramInt)
  {
    return this.mArray[((paramInt << 1) + 1)];
  }
}