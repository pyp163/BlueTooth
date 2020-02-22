package com.facebook.imagepipeline.memory;

import android.graphics.Bitmap;
import android.os.Build.VERSION;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Throwables;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.references.ResourceReleaser;
import com.facebook.imagepipeline.common.TooManyBitmapsException;
import com.facebook.imagepipeline.nativecode.Bitmaps;
import com.facebook.imageutils.BitmapUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.concurrent.GuardedBy;

public class BitmapCounter
{

  @GuardedBy("this")
  private int mCount;
  private final int mMaxCount;
  private final int mMaxSize;

  @GuardedBy("this")
  private long mSize;
  private final ResourceReleaser<Bitmap> mUnpooledBitmapsReleaser;

  public BitmapCounter(int paramInt1, int paramInt2)
  {
    boolean bool2 = false;
    if (paramInt1 > 0)
      bool1 = true;
    else
      bool1 = false;
    Preconditions.checkArgument(bool1);
    boolean bool1 = bool2;
    if (paramInt2 > 0)
      bool1 = true;
    Preconditions.checkArgument(bool1);
    this.mMaxCount = paramInt1;
    this.mMaxSize = paramInt2;
    this.mUnpooledBitmapsReleaser = new ResourceReleaser()
    {
      public void release(Bitmap paramAnonymousBitmap)
      {
        try
        {
          BitmapCounter.this.decrease(paramAnonymousBitmap);
          return;
        }
        finally
        {
          paramAnonymousBitmap.recycle();
        }
      }
    };
  }

  public List<CloseableReference<Bitmap>> associateBitmapsWithBitmapCounter(List<Bitmap> paramList)
  {
    int i = 0;
    while (true)
    {
      try
      {
        Object localObject1;
        if (i < paramList.size())
        {
          localObject1 = (Bitmap)paramList.get(i);
          if (Build.VERSION.SDK_INT < 21)
            Bitmaps.pinBitmap((Bitmap)localObject1);
          if (!increase((Bitmap)localObject1))
            throw new TooManyBitmapsException();
        }
        else
        {
          localObject1 = new ArrayList(paramList.size());
          localObject2 = paramList.iterator();
          if (((Iterator)localObject2).hasNext())
          {
            ((List)localObject1).add(CloseableReference.of((Bitmap)((Iterator)localObject2).next(), this.mUnpooledBitmapsReleaser));
            continue;
          }
          return localObject1;
        }
      }
      catch (Exception localException)
      {
        Object localObject2;
        if (paramList != null)
        {
          paramList = paramList.iterator();
          if (paramList.hasNext())
          {
            localObject2 = (Bitmap)paramList.next();
            if (i > 0)
              decrease((Bitmap)localObject2);
            ((Bitmap)localObject2).recycle();
            i -= 1;
            continue;
          }
        }
        throw Throwables.propagate(localException);
      }
      i += 1;
    }
  }

  public void decrease(Bitmap paramBitmap)
  {
    while (true)
    {
      try
      {
        int i = BitmapUtil.getSizeInBytes(paramBitmap);
        if (this.mCount > 0)
        {
          bool = true;
          Preconditions.checkArgument(bool, "No bitmaps registered.");
          long l = i;
          if (l > this.mSize)
            break label105;
          bool = true;
          Preconditions.checkArgument(bool, "Bitmap size bigger than the total registered size: %d, %d", new Object[] { Integer.valueOf(i), Long.valueOf(this.mSize) });
          this.mSize -= l;
          this.mCount -= 1;
          return;
        }
      }
      finally
      {
      }
      boolean bool = false;
      continue;
      label105: bool = false;
    }
  }

  public int getCount()
  {
    try
    {
      int i = this.mCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int getMaxCount()
  {
    try
    {
      int i = this.mMaxCount;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int getMaxSize()
  {
    try
    {
      int i = this.mMaxSize;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public ResourceReleaser<Bitmap> getReleaser()
  {
    return this.mUnpooledBitmapsReleaser;
  }

  public long getSize()
  {
    try
    {
      long l = this.mSize;
      return l;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean increase(Bitmap paramBitmap)
  {
    try
    {
      int i = BitmapUtil.getSizeInBytes(paramBitmap);
      if (this.mCount < this.mMaxCount)
      {
        long l1 = this.mSize;
        long l2 = i;
        if (l1 + l2 <= this.mMaxSize)
        {
          this.mCount += 1;
          this.mSize += l2;
          return true;
        }
      }
      return false;
    }
    finally
    {
    }
    throw paramBitmap;
  }
}