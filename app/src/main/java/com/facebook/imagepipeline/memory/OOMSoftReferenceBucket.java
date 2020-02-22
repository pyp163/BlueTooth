package com.facebook.imagepipeline.memory;

import com.facebook.common.references.OOMSoftReference;
import java.util.LinkedList;
import java.util.Queue;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
class OOMSoftReferenceBucket<V> extends Bucket<V>
{
  private LinkedList<OOMSoftReference<V>> mSpareReferences = new LinkedList();

  public OOMSoftReferenceBucket(int paramInt1, int paramInt2, int paramInt3)
  {
    super(paramInt1, paramInt2, paramInt3, false);
  }

  void addToFreeList(V paramV)
  {
    OOMSoftReference localOOMSoftReference2 = (OOMSoftReference)this.mSpareReferences.poll();
    OOMSoftReference localOOMSoftReference1 = localOOMSoftReference2;
    if (localOOMSoftReference2 == null)
      localOOMSoftReference1 = new OOMSoftReference();
    localOOMSoftReference1.set(paramV);
    this.mFreeList.add(localOOMSoftReference1);
  }

  public V pop()
  {
    OOMSoftReference localOOMSoftReference = (OOMSoftReference)this.mFreeList.poll();
    Object localObject = localOOMSoftReference.get();
    localOOMSoftReference.clear();
    this.mSpareReferences.add(localOOMSoftReference);
    return localObject;
  }
}