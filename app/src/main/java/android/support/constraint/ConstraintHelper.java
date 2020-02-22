package android.support.constraint;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.constraint.solver.widgets.Helper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import java.lang.reflect.Field;
import java.util.Arrays;

public abstract class ConstraintHelper extends View
{
  protected int mCount;
  protected Helper mHelperWidget;
  protected int[] mIds = new int[32];
  private String mReferenceIds;
  protected boolean mUseViewMeasure = false;
  protected Context myContext;

  public ConstraintHelper(Context paramContext)
  {
    super(paramContext);
    this.myContext = paramContext;
    init(null);
  }

  public ConstraintHelper(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.myContext = paramContext;
    init(paramAttributeSet);
  }

  public ConstraintHelper(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.myContext = paramContext;
    init(paramAttributeSet);
  }

  private void addID(String paramString)
  {
    if (paramString == null)
      return;
    if (this.myContext == null)
      return;
    paramString = paramString.trim();
    try
    {
      int j = R.id.class.getField(paramString).getInt(null);
      break label34;
      label32: j = 0;
      label34: int i = j;
      if (j == 0)
        i = this.myContext.getResources().getIdentifier(paramString, "id", this.myContext.getPackageName());
      j = i;
      if (i == 0)
      {
        j = i;
        if (isInEditMode())
        {
          j = i;
          if ((getParent() instanceof ConstraintLayout))
          {
            localObject = ((ConstraintLayout)getParent()).getDesignInformation(0, paramString);
            j = i;
            if (localObject != null)
            {
              j = i;
              if ((localObject instanceof Integer))
                j = ((Integer)localObject).intValue();
            }
          }
        }
      }
      if (j != 0)
      {
        setTag(j, null);
        return;
      }
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Could not find id of \"");
      ((StringBuilder)localObject).append(paramString);
      ((StringBuilder)localObject).append("\"");
      Log.w("ConstraintHelper", ((StringBuilder)localObject).toString());
      return;
    }
    catch (Exception localException)
    {
      break label32;
    }
  }

  private void setIds(String paramString)
  {
    if (paramString == null)
      return;
    int j;
    for (int i = 0; ; i = j + 1)
    {
      j = paramString.indexOf(',', i);
      if (j == -1)
      {
        addID(paramString.substring(i));
        return;
      }
      addID(paramString.substring(i, j));
    }
  }

  public int[] getReferencedIds()
  {
    return Arrays.copyOf(this.mIds, this.mCount);
  }

  protected void init(AttributeSet paramAttributeSet)
  {
    if (paramAttributeSet != null)
    {
      paramAttributeSet = getContext().obtainStyledAttributes(paramAttributeSet, R.styleable.ConstraintLayout_Layout);
      int j = paramAttributeSet.getIndexCount();
      int i = 0;
      while (i < j)
      {
        int k = paramAttributeSet.getIndex(i);
        if (k == R.styleable.ConstraintLayout_Layout_constraint_referenced_ids)
        {
          this.mReferenceIds = paramAttributeSet.getString(k);
          setIds(this.mReferenceIds);
        }
        i += 1;
      }
    }
  }

  public void onDraw(Canvas paramCanvas)
  {
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if (this.mUseViewMeasure)
    {
      super.onMeasure(paramInt1, paramInt2);
      return;
    }
    setMeasuredDimension(0, 0);
  }

  public void setReferencedIds(int[] paramArrayOfInt)
  {
    int i = 0;
    this.mCount = 0;
    while (i < paramArrayOfInt.length)
    {
      setTag(paramArrayOfInt[i], null);
      i += 1;
    }
  }

  public void setTag(int paramInt, Object paramObject)
  {
    if (this.mCount + 1 > this.mIds.length)
      this.mIds = Arrays.copyOf(this.mIds, this.mIds.length * 2);
    this.mIds[this.mCount] = paramInt;
    this.mCount += 1;
  }

  public void updatePostLayout(ConstraintLayout paramConstraintLayout)
  {
  }

  public void updatePostMeasure(ConstraintLayout paramConstraintLayout)
  {
  }

  public void updatePreLayout(ConstraintLayout paramConstraintLayout)
  {
    if (isInEditMode())
      setIds(this.mReferenceIds);
    if (this.mHelperWidget == null)
      return;
    this.mHelperWidget.removeAllIds();
    int i = 0;
    while (i < this.mCount)
    {
      View localView = paramConstraintLayout.getViewById(this.mIds[i]);
      if (localView != null)
        this.mHelperWidget.add(paramConstraintLayout.getViewWidget(localView));
      i += 1;
    }
  }

  public void validateParams()
  {
    if (this.mHelperWidget == null)
      return;
    ViewGroup.LayoutParams localLayoutParams = getLayoutParams();
    if ((localLayoutParams instanceof ConstraintLayout.LayoutParams))
      ((ConstraintLayout.LayoutParams)localLayoutParams).widget = this.mHelperWidget;
  }
}