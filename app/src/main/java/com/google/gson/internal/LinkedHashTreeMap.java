package com.google.gson.internal;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

public final class LinkedHashTreeMap<K, V> extends AbstractMap<K, V>
  implements Serializable
{
  private static final Comparator<Comparable> NATURAL_ORDER = new Comparator()
  {
    public int compare(Comparable paramAnonymousComparable1, Comparable paramAnonymousComparable2)
    {
      return paramAnonymousComparable1.compareTo(paramAnonymousComparable2);
    }
  };
  Comparator<? super K> comparator;
  private LinkedHashTreeMap<K, V>.EntrySet entrySet;
  final Node<K, V> header;
  private LinkedHashTreeMap<K, V>.KeySet keySet;
  int modCount = 0;
  int size = 0;
  Node<K, V>[] table;
  int threshold;

  public LinkedHashTreeMap()
  {
    this(NATURAL_ORDER);
  }

  public LinkedHashTreeMap(Comparator<? super K> paramComparator)
  {
    if (paramComparator == null)
      paramComparator = NATURAL_ORDER;
    this.comparator = paramComparator;
    this.header = new Node();
    this.table = new Node[16];
    this.threshold = (this.table.length / 2 + this.table.length / 4);
  }

  private void doubleCapacity()
  {
    this.table = doubleCapacity(this.table);
    this.threshold = (this.table.length / 2 + this.table.length / 4);
  }

  static <K, V> Node<K, V>[] doubleCapacity(Node<K, V>[] paramArrayOfNode)
  {
    int m = paramArrayOfNode.length;
    Node[] arrayOfNode = new Node[m * 2];
    AvlIterator localAvlIterator = new AvlIterator();
    AvlBuilder localAvlBuilder1 = new AvlBuilder();
    AvlBuilder localAvlBuilder2 = new AvlBuilder();
    int i = 0;
    while (i < m)
    {
      Object localObject = paramArrayOfNode[i];
      if (localObject != null)
      {
        localAvlIterator.reset((Node)localObject);
        int k = 0;
        int j = 0;
        while (true)
        {
          localNode = localAvlIterator.next();
          if (localNode == null)
            break;
          if ((localNode.hash & m) == 0)
            k += 1;
          else
            j += 1;
        }
        localAvlBuilder1.reset(k);
        localAvlBuilder2.reset(j);
        localAvlIterator.reset((Node)localObject);
        while (true)
        {
          localObject = localAvlIterator.next();
          if (localObject == null)
            break;
          if ((((Node)localObject).hash & m) == 0)
            localAvlBuilder1.add((Node)localObject);
          else
            localAvlBuilder2.add((Node)localObject);
        }
        Node localNode = null;
        if (k > 0)
          localObject = localAvlBuilder1.root();
        else
          localObject = null;
        arrayOfNode[i] = localObject;
        localObject = localNode;
        if (j > 0)
          localObject = localAvlBuilder2.root();
        arrayOfNode[(i + m)] = localObject;
      }
      i += 1;
    }
    return arrayOfNode;
  }

  private boolean equal(Object paramObject1, Object paramObject2)
  {
    return (paramObject1 == paramObject2) || ((paramObject1 != null) && (paramObject1.equals(paramObject2)));
  }

  private void rebalance(Node<K, V> paramNode, boolean paramBoolean)
  {
    while (paramNode != null)
    {
      Node localNode1 = paramNode.left;
      Node localNode2 = paramNode.right;
      int m = 0;
      int k = 0;
      int i;
      if (localNode1 != null)
        i = localNode1.height;
      else
        i = 0;
      int j;
      if (localNode2 != null)
        j = localNode2.height;
      else
        j = 0;
      int n = i - j;
      Node localNode3;
      if (n == -2)
      {
        localNode1 = localNode2.left;
        localNode3 = localNode2.right;
        if (localNode3 != null)
          i = localNode3.height;
        else
          i = 0;
        j = k;
        if (localNode1 != null)
          j = localNode1.height;
        i = j - i;
        if ((i != -1) && ((i != 0) || (paramBoolean)))
        {
          rotateRight(localNode2);
          rotateLeft(paramNode);
        }
        else
        {
          rotateLeft(paramNode);
        }
        if (!paramBoolean);
      }
      else if (n == 2)
      {
        localNode2 = localNode1.left;
        localNode3 = localNode1.right;
        if (localNode3 != null)
          i = localNode3.height;
        else
          i = 0;
        j = m;
        if (localNode2 != null)
          j = localNode2.height;
        i = j - i;
        if ((i != 1) && ((i != 0) || (paramBoolean)))
        {
          rotateLeft(localNode1);
          rotateRight(paramNode);
        }
        else
        {
          rotateRight(paramNode);
        }
        if (!paramBoolean);
      }
      else if (n == 0)
      {
        paramNode.height = (i + 1);
        if (!paramBoolean);
      }
      else
      {
        paramNode.height = (Math.max(i, j) + 1);
        if (!paramBoolean)
          return;
      }
      paramNode = paramNode.parent;
    }
  }

  private void replaceInParent(Node<K, V> paramNode1, Node<K, V> paramNode2)
  {
    Node localNode = paramNode1.parent;
    paramNode1.parent = null;
    if (paramNode2 != null)
      paramNode2.parent = localNode;
    if (localNode != null)
    {
      if (localNode.left == paramNode1)
      {
        localNode.left = paramNode2;
        return;
      }
      localNode.right = paramNode2;
      return;
    }
    int i = paramNode1.hash;
    int j = this.table.length;
    this.table[(i & j - 1)] = paramNode2;
  }

  private void rotateLeft(Node<K, V> paramNode)
  {
    Node localNode1 = paramNode.left;
    Node localNode2 = paramNode.right;
    Node localNode3 = localNode2.left;
    Node localNode4 = localNode2.right;
    paramNode.right = localNode3;
    if (localNode3 != null)
      localNode3.parent = paramNode;
    replaceInParent(paramNode, localNode2);
    localNode2.left = paramNode;
    paramNode.parent = localNode2;
    int k = 0;
    if (localNode1 != null)
      i = localNode1.height;
    else
      i = 0;
    if (localNode3 != null)
      j = localNode3.height;
    else
      j = 0;
    paramNode.height = (Math.max(i, j) + 1);
    int j = paramNode.height;
    int i = k;
    if (localNode4 != null)
      i = localNode4.height;
    localNode2.height = (Math.max(j, i) + 1);
  }

  private void rotateRight(Node<K, V> paramNode)
  {
    Node localNode1 = paramNode.left;
    Node localNode2 = paramNode.right;
    Node localNode3 = localNode1.left;
    Node localNode4 = localNode1.right;
    paramNode.left = localNode4;
    if (localNode4 != null)
      localNode4.parent = paramNode;
    replaceInParent(paramNode, localNode1);
    localNode1.right = paramNode;
    paramNode.parent = localNode1;
    int k = 0;
    if (localNode2 != null)
      i = localNode2.height;
    else
      i = 0;
    if (localNode4 != null)
      j = localNode4.height;
    else
      j = 0;
    paramNode.height = (Math.max(i, j) + 1);
    int j = paramNode.height;
    int i = k;
    if (localNode3 != null)
      i = localNode3.height;
    localNode1.height = (Math.max(j, i) + 1);
  }

  private static int secondaryHash(int paramInt)
  {
    paramInt ^= paramInt >>> 20 ^ paramInt >>> 12;
    return paramInt >>> 4 ^ (paramInt >>> 7 ^ paramInt);
  }

  private Object writeReplace()
    throws ObjectStreamException
  {
    return new LinkedHashMap(this);
  }

  public void clear()
  {
    Arrays.fill(this.table, null);
    this.size = 0;
    this.modCount += 1;
    Node localNode2 = this.header;
    Node localNode1;
    for (Object localObject = localNode2.next; localObject != localNode2; localObject = localNode1)
    {
      localNode1 = ((Node)localObject).next;
      ((Node)localObject).prev = null;
      ((Node)localObject).next = null;
    }
    localNode2.prev = localNode2;
    localNode2.next = localNode2;
  }

  public boolean containsKey(Object paramObject)
  {
    return findByObject(paramObject) != null;
  }

  public Set<Map.Entry<K, V>> entrySet()
  {
    EntrySet localEntrySet = this.entrySet;
    if (localEntrySet != null)
      return localEntrySet;
    localEntrySet = new EntrySet();
    this.entrySet = localEntrySet;
    return localEntrySet;
  }

  Node<K, V> find(K paramK, boolean paramBoolean)
  {
    Comparator localComparator = this.comparator;
    Node[] arrayOfNode = this.table;
    int j = secondaryHash(paramK.hashCode());
    int k = arrayOfNode.length - 1 & j;
    Object localObject = arrayOfNode[k];
    if (localObject != null)
    {
      Comparable localComparable;
      if (localComparator == NATURAL_ORDER)
        localComparable = (Comparable)paramK;
      else
        localComparable = null;
      while (true)
      {
        if (localComparable != null)
          i = localComparable.compareTo(((Node)localObject).key);
        else
          i = localComparator.compare(paramK, ((Node)localObject).key);
        if (i == 0)
          return localObject;
        if (i < 0)
          localNode = ((Node)localObject).left;
        else
          localNode = ((Node)localObject).right;
        if (localNode == null)
          break;
        localObject = localNode;
      }
    }
    int i = 0;
    if (!paramBoolean)
      return null;
    Node localNode = this.header;
    if (localObject == null)
    {
      if ((localComparator == NATURAL_ORDER) && (!(paramK instanceof Comparable)))
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append(paramK.getClass().getName());
        ((StringBuilder)localObject).append(" is not Comparable");
        throw new ClassCastException(((StringBuilder)localObject).toString());
      }
      paramK = new Node((Node)localObject, paramK, j, localNode, localNode.prev);
      arrayOfNode[k] = paramK;
    }
    else
    {
      paramK = new Node((Node)localObject, paramK, j, localNode, localNode.prev);
      if (i < 0)
        ((Node)localObject).left = paramK;
      else
        ((Node)localObject).right = paramK;
      rebalance((Node)localObject, true);
    }
    i = this.size;
    this.size = (i + 1);
    if (i > this.threshold)
      doubleCapacity();
    this.modCount += 1;
    return paramK;
  }

  Node<K, V> findByEntry(Map.Entry<?, ?> paramEntry)
  {
    Node localNode = findByObject(paramEntry.getKey());
    int i;
    if ((localNode != null) && (equal(localNode.value, paramEntry.getValue())))
      i = 1;
    else
      i = 0;
    if (i != 0)
      return localNode;
    return null;
  }

  Node<K, V> findByObject(Object paramObject)
  {
    if (paramObject != null);
    try
    {
      paramObject = find(paramObject, false);
      return paramObject;
      return null;
    }
    catch (ClassCastException paramObject)
    {
    }
    return null;
  }

  public V get(Object paramObject)
  {
    paramObject = findByObject(paramObject);
    if (paramObject != null)
      return paramObject.value;
    return null;
  }

  public Set<K> keySet()
  {
    KeySet localKeySet = this.keySet;
    if (localKeySet != null)
      return localKeySet;
    localKeySet = new KeySet();
    this.keySet = localKeySet;
    return localKeySet;
  }

  public V put(K paramK, V paramV)
  {
    if (paramK == null)
      throw new NullPointerException("key == null");
    paramK = find(paramK, true);
    Object localObject = paramK.value;
    paramK.value = paramV;
    return localObject;
  }

  public V remove(Object paramObject)
  {
    paramObject = removeInternalByKey(paramObject);
    if (paramObject != null)
      return paramObject.value;
    return null;
  }

  void removeInternal(Node<K, V> paramNode, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      paramNode.prev.next = paramNode.next;
      paramNode.next.prev = paramNode.prev;
      paramNode.prev = null;
      paramNode.next = null;
    }
    Node localNode1 = paramNode.left;
    Node localNode2 = paramNode.right;
    Node localNode3 = paramNode.parent;
    int j = 0;
    if ((localNode1 != null) && (localNode2 != null))
    {
      if (localNode1.height > localNode2.height)
        localNode1 = localNode1.last();
      else
        localNode1 = localNode2.first();
      removeInternal(localNode1, false);
      localNode2 = paramNode.left;
      int i;
      if (localNode2 != null)
      {
        i = localNode2.height;
        localNode1.left = localNode2;
        localNode2.parent = localNode1;
        paramNode.left = null;
      }
      else
      {
        i = 0;
      }
      localNode2 = paramNode.right;
      if (localNode2 != null)
      {
        j = localNode2.height;
        localNode1.right = localNode2;
        localNode2.parent = localNode1;
        paramNode.right = null;
      }
      localNode1.height = (Math.max(i, j) + 1);
      replaceInParent(paramNode, localNode1);
      return;
    }
    if (localNode1 != null)
    {
      replaceInParent(paramNode, localNode1);
      paramNode.left = null;
    }
    else if (localNode2 != null)
    {
      replaceInParent(paramNode, localNode2);
      paramNode.right = null;
    }
    else
    {
      replaceInParent(paramNode, null);
    }
    rebalance(localNode3, false);
    this.size -= 1;
    this.modCount += 1;
  }

  Node<K, V> removeInternalByKey(Object paramObject)
  {
    paramObject = findByObject(paramObject);
    if (paramObject != null)
      removeInternal(paramObject, true);
    return paramObject;
  }

  public int size()
  {
    return this.size;
  }

  static final class AvlBuilder<K, V>
  {
    private int leavesSkipped;
    private int leavesToSkip;
    private int size;
    private LinkedHashTreeMap.Node<K, V> stack;

    void add(LinkedHashTreeMap.Node<K, V> paramNode)
    {
      paramNode.right = null;
      paramNode.parent = null;
      paramNode.left = null;
      paramNode.height = 1;
      if ((this.leavesToSkip > 0) && ((this.size & 0x1) == 0))
      {
        this.size += 1;
        this.leavesToSkip -= 1;
        this.leavesSkipped += 1;
      }
      paramNode.parent = this.stack;
      this.stack = paramNode;
      this.size += 1;
      if ((this.leavesToSkip > 0) && ((this.size & 0x1) == 0))
      {
        this.size += 1;
        this.leavesToSkip -= 1;
        this.leavesSkipped += 1;
      }
      int i = 4;
      while (true)
      {
        int j = this.size;
        int k = i - 1;
        if ((j & k) != k)
          break;
        LinkedHashTreeMap.Node localNode1;
        if (this.leavesSkipped == 0)
        {
          paramNode = this.stack;
          localNode1 = paramNode.parent;
          LinkedHashTreeMap.Node localNode2 = localNode1.parent;
          localNode1.parent = localNode2.parent;
          this.stack = localNode1;
          localNode1.left = localNode2;
          localNode1.right = paramNode;
          paramNode.height += 1;
          localNode2.parent = localNode1;
          paramNode.parent = localNode1;
        }
        else if (this.leavesSkipped == 1)
        {
          paramNode = this.stack;
          localNode1 = paramNode.parent;
          this.stack = localNode1;
          localNode1.right = paramNode;
          paramNode.height += 1;
          paramNode.parent = localNode1;
          this.leavesSkipped = 0;
        }
        else if (this.leavesSkipped == 2)
        {
          this.leavesSkipped = 0;
        }
        i *= 2;
      }
    }

    void reset(int paramInt)
    {
      this.leavesToSkip = (Integer.highestOneBit(paramInt) * 2 - 1 - paramInt);
      this.size = 0;
      this.leavesSkipped = 0;
      this.stack = null;
    }

    LinkedHashTreeMap.Node<K, V> root()
    {
      LinkedHashTreeMap.Node localNode = this.stack;
      if (localNode.parent != null)
        throw new IllegalStateException();
      return localNode;
    }
  }

  static class AvlIterator<K, V>
  {
    private LinkedHashTreeMap.Node<K, V> stackTop;

    public LinkedHashTreeMap.Node<K, V> next()
    {
      LinkedHashTreeMap.Node localNode2 = this.stackTop;
      if (localNode2 == null)
        return null;
      Object localObject1 = localNode2.parent;
      localNode2.parent = null;
      Object localObject2;
      for (LinkedHashTreeMap.Node localNode1 = localNode2.right; ; localNode1 = ((LinkedHashTreeMap.Node)localObject1).left)
      {
        localObject2 = localObject1;
        localObject1 = localNode1;
        if (localObject1 == null)
          break;
        ((LinkedHashTreeMap.Node)localObject1).parent = localObject2;
      }
      this.stackTop = localObject2;
      return localNode2;
    }

    void reset(LinkedHashTreeMap.Node<K, V> paramNode)
    {
      LinkedHashTreeMap.Node<K, V> localNode = null;
      while (paramNode != null)
      {
        paramNode.parent = localNode;
        LinkedHashTreeMap.Node localNode1 = paramNode.left;
        localNode = paramNode;
        paramNode = localNode1;
      }
      this.stackTop = localNode;
    }
  }

  final class EntrySet extends AbstractSet<Map.Entry<K, V>>
  {
    EntrySet()
    {
    }

    public void clear()
    {
      LinkedHashTreeMap.this.clear();
    }

    public boolean contains(Object paramObject)
    {
      return ((paramObject instanceof Map.Entry)) && (LinkedHashTreeMap.this.findByEntry((Map.Entry)paramObject) != null);
    }

    public Iterator<Map.Entry<K, V>> iterator()
    {
      // Byte code:
      //   0: new 10	com/google/gson/internal/LinkedHashTreeMap$EntrySet$1
      //   3: dup
      //   4: aload_0
      //   5: invokespecial 36	com/google/gson/internal/LinkedHashTreeMap$EntrySet$1:<init>	(Lcom/google/gson/internal/LinkedHashTreeMap$EntrySet;)V
      //   8: areturn
    }

    public boolean remove(Object paramObject)
    {
      if (!(paramObject instanceof Map.Entry))
        return false;
      paramObject = LinkedHashTreeMap.this.findByEntry((Map.Entry)paramObject);
      if (paramObject == null)
        return false;
      LinkedHashTreeMap.this.removeInternal(paramObject, true);
      return true;
    }

    public int size()
    {
      return LinkedHashTreeMap.this.size;
    }
  }

  final class KeySet extends AbstractSet<K>
  {
    KeySet()
    {
    }

    public void clear()
    {
      LinkedHashTreeMap.this.clear();
    }

    public boolean contains(Object paramObject)
    {
      return LinkedHashTreeMap.this.containsKey(paramObject);
    }

    public Iterator<K> iterator()
    {
      // Byte code:
      //   0: new 10	com/google/gson/internal/LinkedHashTreeMap$KeySet$1
      //   3: dup
      //   4: aload_0
      //   5: invokespecial 33	com/google/gson/internal/LinkedHashTreeMap$KeySet$1:<init>	(Lcom/google/gson/internal/LinkedHashTreeMap$KeySet;)V
      //   8: areturn
    }

    public boolean remove(Object paramObject)
    {
      return LinkedHashTreeMap.this.removeInternalByKey(paramObject) != null;
    }

    public int size()
    {
      return LinkedHashTreeMap.this.size;
    }
  }

  private abstract class LinkedTreeMapIterator<T>
    implements Iterator<T>
  {
    int expectedModCount = LinkedHashTreeMap.this.modCount;
    LinkedHashTreeMap.Node<K, V> lastReturned = null;
    LinkedHashTreeMap.Node<K, V> next = LinkedHashTreeMap.this.header.next;

    LinkedTreeMapIterator()
    {
    }

    public final boolean hasNext()
    {
      return this.next != LinkedHashTreeMap.this.header;
    }

    final LinkedHashTreeMap.Node<K, V> nextNode()
    {
      LinkedHashTreeMap.Node localNode = this.next;
      if (localNode == LinkedHashTreeMap.this.header)
        throw new NoSuchElementException();
      if (LinkedHashTreeMap.this.modCount != this.expectedModCount)
        throw new ConcurrentModificationException();
      this.next = localNode.next;
      this.lastReturned = localNode;
      return localNode;
    }

    public final void remove()
    {
      if (this.lastReturned == null)
        throw new IllegalStateException();
      LinkedHashTreeMap.this.removeInternal(this.lastReturned, true);
      this.lastReturned = null;
      this.expectedModCount = LinkedHashTreeMap.this.modCount;
    }
  }

  static final class Node<K, V>
    implements Map.Entry<K, V>
  {
    final int hash;
    int height;
    final K key;
    Node<K, V> left;
    Node<K, V> next;
    Node<K, V> parent;
    Node<K, V> prev;
    Node<K, V> right;
    V value;

    Node()
    {
      this.key = null;
      this.hash = -1;
      this.prev = this;
      this.next = this;
    }

    Node(Node<K, V> paramNode1, K paramK, int paramInt, Node<K, V> paramNode2, Node<K, V> paramNode3)
    {
      this.parent = paramNode1;
      this.key = paramK;
      this.hash = paramInt;
      this.height = 1;
      this.next = paramNode2;
      this.prev = paramNode3;
      paramNode3.next = this;
      paramNode2.prev = this;
    }

    public boolean equals(Object paramObject)
    {
      boolean bool1 = paramObject instanceof Map.Entry;
      boolean bool2 = false;
      if (bool1)
      {
        paramObject = (Map.Entry)paramObject;
        if (this.key == null)
        {
          bool1 = bool2;
          if (paramObject.getKey() != null)
            break label96;
        }
        else
        {
          bool1 = bool2;
          if (!this.key.equals(paramObject.getKey()))
            break label96;
        }
        if (this.value == null)
        {
          bool1 = bool2;
          if (paramObject.getValue() != null)
            break label96;
        }
        else
        {
          bool1 = bool2;
          if (!this.value.equals(paramObject.getValue()))
            break label96;
        }
        bool1 = true;
        label96: return bool1;
      }
      return false;
    }

    public Node<K, V> first()
    {
      Object localObject1 = this.left;
      Object localObject2 = this;
      while (localObject1 != null)
      {
        Node localNode = ((Node)localObject1).left;
        localObject2 = localObject1;
        localObject1 = localNode;
      }
      return localObject2;
    }

    public K getKey()
    {
      return this.key;
    }

    public V getValue()
    {
      return this.value;
    }

    public int hashCode()
    {
      Object localObject = this.key;
      int j = 0;
      int i;
      if (localObject == null)
        i = 0;
      else
        i = this.key.hashCode();
      if (this.value != null)
        j = this.value.hashCode();
      return i ^ j;
    }

    public Node<K, V> last()
    {
      Object localObject1 = this.right;
      Object localObject2 = this;
      while (localObject1 != null)
      {
        Node localNode = ((Node)localObject1).right;
        localObject2 = localObject1;
        localObject1 = localNode;
      }
      return localObject2;
    }

    public V setValue(V paramV)
    {
      Object localObject = this.value;
      this.value = paramV;
      return localObject;
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(this.key);
      localStringBuilder.append("=");
      localStringBuilder.append(this.value);
      return localStringBuilder.toString();
    }
  }
}