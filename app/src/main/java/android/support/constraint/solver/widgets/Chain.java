package android.support.constraint.solver.widgets;

import android.support.constraint.solver.ArrayRow;
import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.SolverVariable;
import java.util.ArrayList;

class Chain
{
  private static final boolean DEBUG = false;

  static void applyChainConstraints(ConstraintWidgetContainer paramConstraintWidgetContainer, LinearSystem paramLinearSystem, int paramInt)
  {
    int k = 0;
    int j;
    ChainHead[] arrayOfChainHead;
    int i;
    if (paramInt == 0)
    {
      j = paramConstraintWidgetContainer.mHorizontalChainsSize;
      arrayOfChainHead = paramConstraintWidgetContainer.mHorizontalChainsArray;
      i = 0;
    }
    else
    {
      i = 2;
      j = paramConstraintWidgetContainer.mVerticalChainsSize;
      arrayOfChainHead = paramConstraintWidgetContainer.mVerticalChainsArray;
    }
    while (k < j)
    {
      ChainHead localChainHead = arrayOfChainHead[k];
      localChainHead.define();
      if (paramConstraintWidgetContainer.optimizeFor(4))
      {
        if (!Optimizer.applyChainOptimized(paramConstraintWidgetContainer, paramLinearSystem, paramInt, i, localChainHead))
          applyChainConstraints(paramConstraintWidgetContainer, paramLinearSystem, paramInt, i, localChainHead);
      }
      else
        applyChainConstraints(paramConstraintWidgetContainer, paramLinearSystem, paramInt, i, localChainHead);
      k += 1;
    }
  }

  static void applyChainConstraints(ConstraintWidgetContainer paramConstraintWidgetContainer, LinearSystem paramLinearSystem, int paramInt1, int paramInt2, ChainHead paramChainHead)
  {
    Object localObject2 = paramChainHead.mFirst;
    Object localObject1 = paramChainHead.mLast;
    ConstraintWidget localConstraintWidget1 = paramChainHead.mFirstVisibleWidget;
    ConstraintWidget localConstraintWidget2 = paramChainHead.mLastVisibleWidget;
    Object localObject4 = paramChainHead.mHead;
    float f1 = paramChainHead.mTotalWeight;
    Object localObject3 = paramChainHead.mFirstMatchConstraintWidget;
    localObject3 = paramChainHead.mLastMatchConstraintWidget;
    int i1;
    if (paramConstraintWidgetContainer.mListDimensionBehaviors[paramInt1] == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)
      i1 = 1;
    else
      i1 = 0;
    int i;
    int j;
    int k;
    int m;
    if (paramInt1 == 0)
    {
      if (((ConstraintWidget)localObject4).mHorizontalChainStyle == 0)
        i = 1;
      else
        i = 0;
      if (((ConstraintWidget)localObject4).mHorizontalChainStyle == 1)
        j = 1;
      else
        j = 0;
      k = j;
      m = i;
      if (((ConstraintWidget)localObject4).mHorizontalChainStyle != 2);
    }
    int n;
    Object localObject5;
    while (true)
    {
      k = 1;
      m = j;
      n = i;
      do
      {
        i = 0;
        n = m;
        m = k;
        k = i;
        localObject5 = localObject2;
        i = 0;
        break;
        if (((ConstraintWidget)localObject4).mVerticalChainStyle == 0)
          i = 1;
        else
          i = 0;
        if (((ConstraintWidget)localObject4).mVerticalChainStyle == 1)
          j = 1;
        else
          j = 0;
        k = j;
        m = i;
      }
      while (((ConstraintWidget)localObject4).mVerticalChainStyle != 2);
    }
    Object localObject6;
    int i2;
    Object localObject7;
    while (true)
    {
      localObject6 = null;
      if (i != 0)
        break;
      localObject3 = localObject5.mListAnchors[paramInt2];
      if ((i1 == 0) && (k == 0))
        j = 4;
      else
        j = 1;
      int i3 = ((ConstraintAnchor)localObject3).getMargin();
      i2 = i3;
      if (((ConstraintAnchor)localObject3).mTarget != null)
      {
        i2 = i3;
        if (localObject5 != localObject2)
          i2 = i3 + ((ConstraintAnchor)localObject3).mTarget.getMargin();
      }
      if ((k != 0) && (localObject5 != localObject2) && (localObject5 != localConstraintWidget1))
        j = 6;
      else if ((n != 0) && (i1 != 0))
        j = 4;
      if (((ConstraintAnchor)localObject3).mTarget != null)
      {
        if (localObject5 == localConstraintWidget1)
          paramLinearSystem.addGreaterThan(((ConstraintAnchor)localObject3).mSolverVariable, ((ConstraintAnchor)localObject3).mTarget.mSolverVariable, i2, 5);
        else
          paramLinearSystem.addGreaterThan(((ConstraintAnchor)localObject3).mSolverVariable, ((ConstraintAnchor)localObject3).mTarget.mSolverVariable, i2, 6);
        paramLinearSystem.addEquality(((ConstraintAnchor)localObject3).mSolverVariable, ((ConstraintAnchor)localObject3).mTarget.mSolverVariable, i2, j);
      }
      if (i1 != 0)
      {
        if ((((ConstraintWidget)localObject5).getVisibility() != 8) && (localObject5.mListDimensionBehaviors[paramInt1] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT))
          paramLinearSystem.addGreaterThan(localObject5.mListAnchors[(paramInt2 + 1)].mSolverVariable, localObject5.mListAnchors[paramInt2].mSolverVariable, 0, 5);
        paramLinearSystem.addGreaterThan(localObject5.mListAnchors[paramInt2].mSolverVariable, paramConstraintWidgetContainer.mListAnchors[paramInt2].mSolverVariable, 0, 6);
      }
      localObject7 = localObject5.mListAnchors[(paramInt2 + 1)].mTarget;
      localObject3 = localObject6;
      if (localObject7 != null)
      {
        localObject7 = ((ConstraintAnchor)localObject7).mOwner;
        localObject3 = localObject6;
        if (localObject7.mListAnchors[paramInt2].mTarget != null)
          if (localObject7.mListAnchors[paramInt2].mTarget.mOwner != localObject5)
            localObject3 = localObject6;
          else
            localObject3 = localObject7;
      }
      if (localObject3 != null)
        localObject5 = localObject3;
      else
        i = 1;
    }
    if (localConstraintWidget2 != null)
    {
      localObject3 = ((ConstraintWidget)localObject1).mListAnchors;
      i = paramInt2 + 1;
      if (localObject3[i].mTarget != null)
      {
        localObject3 = localConstraintWidget2.mListAnchors[i];
        paramLinearSystem.addLowerThan(((ConstraintAnchor)localObject3).mSolverVariable, localObject1.mListAnchors[i].mTarget.mSolverVariable, -((ConstraintAnchor)localObject3).getMargin(), 5);
      }
    }
    if (i1 != 0)
    {
      paramConstraintWidgetContainer = paramConstraintWidgetContainer.mListAnchors;
      i = paramInt2 + 1;
      paramLinearSystem.addGreaterThan(paramConstraintWidgetContainer[i].mSolverVariable, localObject1.mListAnchors[i].mSolverVariable, localObject1.mListAnchors[i].getMargin(), 6);
    }
    paramConstraintWidgetContainer = paramChainHead.mWeightedMatchConstraintsWidgets;
    label915: SolverVariable localSolverVariable;
    if (paramConstraintWidgetContainer != null)
    {
      j = paramConstraintWidgetContainer.size();
      if (j > 1)
      {
        float f2 = f1;
        if (paramChainHead.mHasUndefinedWeights)
        {
          f2 = f1;
          if (!paramChainHead.mHasComplexMatchWeights)
            f2 = paramChainHead.mWidgetsMatchCount;
        }
        localObject3 = null;
        i = 0;
        for (float f3 = 0.0F; i < j; f3 = f1)
        {
          localObject5 = (ConstraintWidget)paramConstraintWidgetContainer.get(i);
          f1 = localObject5.mWeight[paramInt1];
          if (f1 < 0.0F)
          {
            if (paramChainHead.mHasComplexMatchWeights)
            {
              paramLinearSystem.addEquality(localObject5.mListAnchors[(paramInt2 + 1)].mSolverVariable, localObject5.mListAnchors[paramInt2].mSolverVariable, 0, 4);
              break label915;
            }
            f1 = 1.0F;
          }
          if (f1 == 0.0F)
          {
            paramLinearSystem.addEquality(localObject5.mListAnchors[(paramInt2 + 1)].mSolverVariable, localObject5.mListAnchors[paramInt2].mSolverVariable, 0, 6);
            f1 = f3;
          }
          else
          {
            if (localObject3 != null)
            {
              localObject6 = localObject3.mListAnchors[paramInt2].mSolverVariable;
              localObject3 = ((ConstraintWidget)localObject3).mListAnchors;
              i1 = paramInt2 + 1;
              localObject3 = localObject3[i1].mSolverVariable;
              localObject7 = localObject5.mListAnchors[paramInt2].mSolverVariable;
              localSolverVariable = localObject5.mListAnchors[i1].mSolverVariable;
              ArrayRow localArrayRow = paramLinearSystem.createRow();
              localArrayRow.createRowEqualMatchDimensions(f3, f2, f1, (SolverVariable)localObject6, (SolverVariable)localObject3, (SolverVariable)localObject7, localSolverVariable);
              paramLinearSystem.addConstraint(localArrayRow);
            }
            localObject3 = localObject5;
          }
          i += 1;
        }
      }
    }
    if ((localConstraintWidget1 != null) && ((localConstraintWidget1 == localConstraintWidget2) || (k != 0)))
    {
      localObject3 = localObject2.mListAnchors[paramInt2];
      paramConstraintWidgetContainer = ((ConstraintWidget)localObject1).mListAnchors;
      i = paramInt2 + 1;
      localObject5 = paramConstraintWidgetContainer[i];
      if (localObject2.mListAnchors[paramInt2].mTarget != null)
        paramChainHead = localObject2.mListAnchors[paramInt2].mTarget.mSolverVariable;
      else
        paramChainHead = null;
      if (localObject1.mListAnchors[i].mTarget != null)
        localObject2 = localObject1.mListAnchors[i].mTarget.mSolverVariable;
      else
        localObject2 = null;
      if (localConstraintWidget1 == localConstraintWidget2)
      {
        localObject3 = localConstraintWidget1.mListAnchors[paramInt2];
        localObject5 = localConstraintWidget1.mListAnchors[i];
      }
      paramConstraintWidgetContainer = (ConstraintWidgetContainer)localObject1;
      if (paramChainHead != null)
      {
        paramConstraintWidgetContainer = (ConstraintWidgetContainer)localObject1;
        if (localObject2 != null)
        {
          if (paramInt1 == 0);
          for (f1 = ((ConstraintWidget)localObject4).mHorizontalBiasPercent; ; f1 = ((ConstraintWidget)localObject4).mVerticalBiasPercent)
            break;
          paramInt1 = ((ConstraintAnchor)localObject3).getMargin();
          i = ((ConstraintAnchor)localObject5).getMargin();
          paramLinearSystem.addCentering(((ConstraintAnchor)localObject3).mSolverVariable, paramChainHead, paramInt1, f1, (SolverVariable)localObject2, ((ConstraintAnchor)localObject5).mSolverVariable, i, 5);
          paramConstraintWidgetContainer = (ConstraintWidgetContainer)localObject1;
        }
      }
    }
    else
    {
      localObject5 = localObject2;
      if ((n != 0) && (localConstraintWidget1 != null))
      {
        if ((paramChainHead.mWidgetsMatchCount > 0) && (paramChainHead.mWidgetsCount == paramChainHead.mWidgetsMatchCount))
          k = 1;
        else
          k = 0;
        localObject2 = localConstraintWidget1;
        for (localObject3 = localObject2; ; localObject3 = paramConstraintWidgetContainer)
        {
          paramConstraintWidgetContainer = (ConstraintWidgetContainer)localObject1;
          if (localObject3 == null)
            break;
          for (paramConstraintWidgetContainer = localObject3.mNextChainWidget[paramInt1]; (paramConstraintWidgetContainer != null) && (paramConstraintWidgetContainer.getVisibility() == 8); paramConstraintWidgetContainer = paramConstraintWidgetContainer.mNextChainWidget[paramInt1]);
          if ((paramConstraintWidgetContainer == null) && (localObject3 != localConstraintWidget2));
          while (true)
          {
            break;
            localObject6 = localObject3.mListAnchors[paramInt2];
            localSolverVariable = ((ConstraintAnchor)localObject6).mSolverVariable;
            if (((ConstraintAnchor)localObject6).mTarget != null)
              localObject4 = ((ConstraintAnchor)localObject6).mTarget.mSolverVariable;
            else
              localObject4 = null;
            if (localObject2 != localObject3)
            {
              paramChainHead = localObject2.mListAnchors[(paramInt2 + 1)].mSolverVariable;
            }
            else
            {
              paramChainHead = (ChainHead)localObject4;
              if (localObject3 == localConstraintWidget1)
              {
                paramChainHead = (ChainHead)localObject4;
                if (localObject2 == localObject3)
                  if (localObject5.mListAnchors[paramInt2].mTarget != null)
                    paramChainHead = localObject5.mListAnchors[paramInt2].mTarget.mSolverVariable;
                  else
                    paramChainHead = null;
              }
            }
            i1 = ((ConstraintAnchor)localObject6).getMargin();
            localObject4 = ((ConstraintWidget)localObject3).mListAnchors;
            i2 = paramInt2 + 1;
            j = localObject4[i2].getMargin();
            if (paramConstraintWidgetContainer != null)
            {
              localObject7 = paramConstraintWidgetContainer.mListAnchors[paramInt2];
              localObject4 = ((ConstraintAnchor)localObject7).mSolverVariable;
              localObject6 = localObject3.mListAnchors[i2].mSolverVariable;
            }
            else
            {
              localObject7 = localObject1.mListAnchors[i2].mTarget;
              if (localObject7 != null)
                localObject4 = ((ConstraintAnchor)localObject7).mSolverVariable;
              else
                localObject4 = null;
              localObject6 = localObject3.mListAnchors[i2].mSolverVariable;
            }
            i = j;
            if (localObject7 != null)
              i = j + ((ConstraintAnchor)localObject7).getMargin();
            j = i1;
            if (localObject2 != null)
              j = i1 + localObject2.mListAnchors[i2].getMargin();
            if ((localSolverVariable != null) && (paramChainHead != null) && (localObject4 != null) && (localObject6 != null))
            {
              if (localObject3 == localConstraintWidget1)
                j = localConstraintWidget1.mListAnchors[paramInt2].getMargin();
              if (localObject3 == localConstraintWidget2)
                i = localConstraintWidget2.mListAnchors[i2].getMargin();
              if (k != 0)
                i1 = 6;
              else
                i1 = 4;
              paramLinearSystem.addCentering(localSolverVariable, paramChainHead, j, 0.5F, (SolverVariable)localObject4, (SolverVariable)localObject6, i, i1);
              break;
            }
          }
          if (((ConstraintWidget)localObject3).getVisibility() != 8)
            localObject2 = localObject3;
        }
      }
      if ((m != 0) && (localConstraintWidget1 != null))
      {
        if ((paramChainHead.mWidgetsMatchCount > 0) && (paramChainHead.mWidgetsCount == paramChainHead.mWidgetsMatchCount))
          i = 1;
        else
          i = 0;
        paramChainHead = localConstraintWidget1;
        for (localObject2 = paramChainHead; localObject2 != null; localObject2 = paramConstraintWidgetContainer)
        {
          for (paramConstraintWidgetContainer = localObject2.mNextChainWidget[paramInt1]; (paramConstraintWidgetContainer != null) && (paramConstraintWidgetContainer.getVisibility() == 8); paramConstraintWidgetContainer = paramConstraintWidgetContainer.mNextChainWidget[paramInt1]);
          if ((localObject2 != localConstraintWidget1) && (localObject2 != localConstraintWidget2) && (paramConstraintWidgetContainer != null))
          {
            if (paramConstraintWidgetContainer == localConstraintWidget2)
              paramConstraintWidgetContainer = null;
            localObject3 = localObject2.mListAnchors[paramInt2];
            localObject7 = ((ConstraintAnchor)localObject3).mSolverVariable;
            if (((ConstraintAnchor)localObject3).mTarget != null)
              localObject4 = ((ConstraintAnchor)localObject3).mTarget.mSolverVariable;
            localObject4 = paramChainHead.mListAnchors;
            i2 = paramInt2 + 1;
            localSolverVariable = localObject4[i2].mSolverVariable;
            i1 = ((ConstraintAnchor)localObject3).getMargin();
            k = localObject2.mListAnchors[i2].getMargin();
            if (paramConstraintWidgetContainer != null)
            {
              localObject6 = paramConstraintWidgetContainer.mListAnchors[paramInt2];
              localObject4 = ((ConstraintAnchor)localObject6).mSolverVariable;
              if (((ConstraintAnchor)localObject6).mTarget != null)
                localObject3 = ((ConstraintAnchor)localObject6).mTarget.mSolverVariable;
              else
                localObject3 = null;
            }
            else
            {
              localObject6 = localObject2.mListAnchors[i2].mTarget;
              if (localObject6 != null)
                localObject4 = ((ConstraintAnchor)localObject6).mSolverVariable;
              else
                localObject4 = null;
              localObject3 = localObject2.mListAnchors[i2].mSolverVariable;
            }
            j = k;
            if (localObject6 != null)
              j = k + ((ConstraintAnchor)localObject6).getMargin();
            k = i1;
            if (paramChainHead != null)
              k = i1 + paramChainHead.mListAnchors[i2].getMargin();
            if (i != 0)
              i1 = 6;
            else
              i1 = 4;
            if ((localObject7 != null) && (localSolverVariable != null) && (localObject4 != null) && (localObject3 != null))
              paramLinearSystem.addCentering((SolverVariable)localObject7, localSolverVariable, k, 0.5F, (SolverVariable)localObject4, (SolverVariable)localObject3, j, i1);
            else;
          }
          if (((ConstraintWidget)localObject2).getVisibility() != 8)
            paramChainHead = (ChainHead)localObject2;
        }
        paramChainHead = localConstraintWidget1.mListAnchors[paramInt2];
        localObject3 = localObject5.mListAnchors[paramInt2].mTarget;
        paramConstraintWidgetContainer = localConstraintWidget2.mListAnchors;
        paramInt1 = paramInt2 + 1;
        localObject2 = paramConstraintWidgetContainer[paramInt1];
        paramConstraintWidgetContainer = localObject1.mListAnchors[paramInt1].mTarget;
        if (localObject3 != null)
          if (localConstraintWidget1 != localConstraintWidget2)
            paramLinearSystem.addEquality(paramChainHead.mSolverVariable, ((ConstraintAnchor)localObject3).mSolverVariable, paramChainHead.getMargin(), 5);
          else if (paramConstraintWidgetContainer != null)
            paramLinearSystem.addCentering(paramChainHead.mSolverVariable, ((ConstraintAnchor)localObject3).mSolverVariable, paramChainHead.getMargin(), 0.5F, ((ConstraintAnchor)localObject2).mSolverVariable, paramConstraintWidgetContainer.mSolverVariable, ((ConstraintAnchor)localObject2).getMargin(), 5);
        paramChainHead = paramConstraintWidgetContainer;
        paramConstraintWidgetContainer = (ConstraintWidgetContainer)localObject1;
        if (paramChainHead != null)
        {
          paramConstraintWidgetContainer = (ConstraintWidgetContainer)localObject1;
          if (localConstraintWidget1 != localConstraintWidget2)
          {
            paramLinearSystem.addEquality(((ConstraintAnchor)localObject2).mSolverVariable, paramChainHead.mSolverVariable, -((ConstraintAnchor)localObject2).getMargin(), 5);
            paramConstraintWidgetContainer = (ConstraintWidgetContainer)localObject1;
          }
        }
      }
      else
      {
        paramConstraintWidgetContainer = (ConstraintWidgetContainer)localObject1;
      }
    }
    if (((n != 0) || (m != 0)) && (localConstraintWidget1 != null))
    {
      localObject2 = localConstraintWidget1.mListAnchors[paramInt2];
      paramChainHead = localConstraintWidget2.mListAnchors;
      paramInt1 = paramInt2 + 1;
      localObject3 = paramChainHead[paramInt1];
      if (((ConstraintAnchor)localObject2).mTarget != null)
        localObject1 = ((ConstraintAnchor)localObject2).mTarget.mSolverVariable;
      else
        localObject1 = null;
      if (((ConstraintAnchor)localObject3).mTarget != null)
        paramChainHead = ((ConstraintAnchor)localObject3).mTarget.mSolverVariable;
      else
        paramChainHead = null;
      if (paramConstraintWidgetContainer != localConstraintWidget2)
      {
        paramChainHead = paramConstraintWidgetContainer.mListAnchors[paramInt1];
        if (paramChainHead.mTarget != null)
          paramChainHead = paramChainHead.mTarget.mSolverVariable;
        else
          paramChainHead = null;
      }
      if (localConstraintWidget1 == localConstraintWidget2)
      {
        localObject2 = localConstraintWidget1.mListAnchors[paramInt2];
        localObject3 = localConstraintWidget1.mListAnchors[paramInt1];
      }
      if ((localObject1 != null) && (paramChainHead != null))
      {
        paramInt2 = ((ConstraintAnchor)localObject2).getMargin();
        if (localConstraintWidget2 != null)
          paramConstraintWidgetContainer = localConstraintWidget2;
        paramInt1 = paramConstraintWidgetContainer.mListAnchors[paramInt1].getMargin();
        paramLinearSystem.addCentering(((ConstraintAnchor)localObject2).mSolverVariable, (SolverVariable)localObject1, paramInt2, 0.5F, paramChainHead, ((ConstraintAnchor)localObject3).mSolverVariable, paramInt1, 5);
      }
    }
  }
}