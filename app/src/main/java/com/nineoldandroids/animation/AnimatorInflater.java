package com.nineoldandroids.animation;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.util.Xml;
import android.view.animation.AnimationUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class AnimatorInflater
{
  private static final int[] Animator = { 16843073, 16843160, 16843198, 16843199, 16843200, 16843486, 16843487, 16843488 };
  private static final int[] AnimatorSet = { 16843490 };
  private static final int AnimatorSet_ordering = 0;
  private static final int Animator_duration = 1;
  private static final int Animator_interpolator = 0;
  private static final int Animator_repeatCount = 3;
  private static final int Animator_repeatMode = 4;
  private static final int Animator_startOffset = 2;
  private static final int Animator_valueFrom = 5;
  private static final int Animator_valueTo = 6;
  private static final int Animator_valueType = 7;
  private static final int[] PropertyAnimator = { 16843489 };
  private static final int PropertyAnimator_propertyName = 0;
  private static final int TOGETHER = 0;
  private static final int VALUE_TYPE_FLOAT = 0;

  private static Animator createAnimatorFromXml(Context paramContext, XmlPullParser paramXmlPullParser)
    throws XmlPullParserException, IOException
  {
    return createAnimatorFromXml(paramContext, paramXmlPullParser, Xml.asAttributeSet(paramXmlPullParser), null, 0);
  }

  private static Animator createAnimatorFromXml(Context paramContext, XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, AnimatorSet paramAnimatorSet, int paramInt)
    throws XmlPullParserException, IOException
  {
    int k = paramXmlPullParser.getDepth();
    Object localObject2 = null;
    Object localObject3 = localObject2;
    int j;
    int i;
    while (true)
    {
      int m = paramXmlPullParser.next();
      j = 0;
      i = 0;
      if (((m == 3) && (paramXmlPullParser.getDepth() <= k)) || (m == 1))
        break label276;
      if (m == 2)
      {
        Object localObject1 = paramXmlPullParser.getName();
        if (((String)localObject1).equals("objectAnimator"))
        {
          localObject1 = loadObjectAnimator(paramContext, paramAttributeSet);
        }
        else if (((String)localObject1).equals("animator"))
        {
          localObject1 = loadAnimator(paramContext, paramAttributeSet, null);
        }
        else
        {
          if (!((String)localObject1).equals("set"))
            break;
          localObject1 = new AnimatorSet();
          localObject3 = paramContext.obtainStyledAttributes(paramAttributeSet, AnimatorSet);
          TypedValue localTypedValue = new TypedValue();
          ((TypedArray)localObject3).getValue(0, localTypedValue);
          if (localTypedValue.type == 16)
            i = localTypedValue.data;
          createAnimatorFromXml(paramContext, paramXmlPullParser, paramAttributeSet, (AnimatorSet)localObject1, i);
          ((TypedArray)localObject3).recycle();
        }
        localObject3 = localObject1;
        if (paramAnimatorSet != null)
        {
          localObject3 = localObject2;
          if (localObject2 == null)
            localObject3 = new ArrayList();
          ((ArrayList)localObject3).add(localObject1);
          localObject2 = localObject3;
          localObject3 = localObject1;
        }
      }
    }
    paramContext = new StringBuilder();
    paramContext.append("Unknown animator name: ");
    paramContext.append(paramXmlPullParser.getName());
    throw new RuntimeException(paramContext.toString());
    label276: if ((paramAnimatorSet != null) && (localObject2 != null))
    {
      paramContext = new Animator[localObject2.size()];
      paramXmlPullParser = localObject2.iterator();
      i = j;
      while (paramXmlPullParser.hasNext())
      {
        paramContext[i] = ((Animator)paramXmlPullParser.next());
        i += 1;
      }
      if (paramInt == 0)
      {
        paramAnimatorSet.playTogether(paramContext);
        return localObject3;
      }
      paramAnimatorSet.playSequentially(paramContext);
    }
    return localObject3;
  }

  // ERROR //
  public static Animator loadAnimator(Context paramContext, int paramInt)
    throws Resources.NotFoundException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore 5
    //   6: aconst_null
    //   7: astore_2
    //   8: aload_0
    //   9: invokevirtual 182	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   12: iload_1
    //   13: invokevirtual 188	android/content/res/Resources:getAnimation	(I)Landroid/content/res/XmlResourceParser;
    //   16: astore_3
    //   17: aload_0
    //   18: aload_3
    //   19: invokestatic 190	com/nineoldandroids/animation/AnimatorInflater:createAnimatorFromXml	(Landroid/content/Context;Lorg/xmlpull/v1/XmlPullParser;)Lcom/nineoldandroids/animation/Animator;
    //   22: astore_0
    //   23: aload_3
    //   24: ifnull +9 -> 33
    //   27: aload_3
    //   28: invokeinterface 195 1 0
    //   33: aload_0
    //   34: areturn
    //   35: astore_0
    //   36: aload_3
    //   37: astore_2
    //   38: goto +153 -> 191
    //   41: astore_2
    //   42: aload_3
    //   43: astore_0
    //   44: aload_2
    //   45: astore_3
    //   46: goto +19 -> 65
    //   49: astore_2
    //   50: aload_3
    //   51: astore_0
    //   52: aload_2
    //   53: astore_3
    //   54: goto +74 -> 128
    //   57: astore_0
    //   58: goto +133 -> 191
    //   61: astore_3
    //   62: aload 4
    //   64: astore_0
    //   65: aload_0
    //   66: astore_2
    //   67: new 135	java/lang/StringBuilder
    //   70: dup
    //   71: invokespecial 136	java/lang/StringBuilder:<init>	()V
    //   74: astore 4
    //   76: aload_0
    //   77: astore_2
    //   78: aload 4
    //   80: ldc 197
    //   82: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   85: pop
    //   86: aload_0
    //   87: astore_2
    //   88: aload 4
    //   90: iload_1
    //   91: invokestatic 203	java/lang/Integer:toHexString	(I)Ljava/lang/String;
    //   94: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   97: pop
    //   98: aload_0
    //   99: astore_2
    //   100: new 178	android/content/res/Resources$NotFoundException
    //   103: dup
    //   104: aload 4
    //   106: invokevirtual 147	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   109: invokespecial 204	android/content/res/Resources$NotFoundException:<init>	(Ljava/lang/String;)V
    //   112: astore 4
    //   114: aload_0
    //   115: astore_2
    //   116: aload 4
    //   118: aload_3
    //   119: invokevirtual 208	android/content/res/Resources$NotFoundException:initCause	(Ljava/lang/Throwable;)Ljava/lang/Throwable;
    //   122: pop
    //   123: aload_0
    //   124: astore_2
    //   125: aload 4
    //   127: athrow
    //   128: aload_0
    //   129: astore_2
    //   130: new 135	java/lang/StringBuilder
    //   133: dup
    //   134: invokespecial 136	java/lang/StringBuilder:<init>	()V
    //   137: astore 4
    //   139: aload_0
    //   140: astore_2
    //   141: aload 4
    //   143: ldc 197
    //   145: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   148: pop
    //   149: aload_0
    //   150: astore_2
    //   151: aload 4
    //   153: iload_1
    //   154: invokestatic 203	java/lang/Integer:toHexString	(I)Ljava/lang/String;
    //   157: invokevirtual 142	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   160: pop
    //   161: aload_0
    //   162: astore_2
    //   163: new 178	android/content/res/Resources$NotFoundException
    //   166: dup
    //   167: aload 4
    //   169: invokevirtual 147	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   172: invokespecial 204	android/content/res/Resources$NotFoundException:<init>	(Ljava/lang/String;)V
    //   175: astore 4
    //   177: aload_0
    //   178: astore_2
    //   179: aload 4
    //   181: aload_3
    //   182: invokevirtual 208	android/content/res/Resources$NotFoundException:initCause	(Ljava/lang/Throwable;)Ljava/lang/Throwable;
    //   185: pop
    //   186: aload_0
    //   187: astore_2
    //   188: aload 4
    //   190: athrow
    //   191: aload_2
    //   192: ifnull +9 -> 201
    //   195: aload_2
    //   196: invokeinterface 195 1 0
    //   201: aload_0
    //   202: athrow
    //   203: astore_3
    //   204: aload 5
    //   206: astore_0
    //   207: goto -79 -> 128
    //
    // Exception table:
    //   from	to	target	type
    //   17	23	35	finally
    //   17	23	41	java/io/IOException
    //   17	23	49	org/xmlpull/v1/XmlPullParserException
    //   8	17	57	finally
    //   67	76	57	finally
    //   78	86	57	finally
    //   88	98	57	finally
    //   100	114	57	finally
    //   116	123	57	finally
    //   125	128	57	finally
    //   130	139	57	finally
    //   141	149	57	finally
    //   151	161	57	finally
    //   163	177	57	finally
    //   179	186	57	finally
    //   188	191	57	finally
    //   8	17	61	java/io/IOException
    //   8	17	203	org/xmlpull/v1/XmlPullParserException
  }

  private static ValueAnimator loadAnimator(Context paramContext, AttributeSet paramAttributeSet, ValueAnimator paramValueAnimator)
    throws Resources.NotFoundException
  {
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, Animator);
    long l1 = localTypedArray.getInt(1, 0);
    long l2 = localTypedArray.getInt(2, 0);
    int i = localTypedArray.getInt(7, 0);
    if (paramValueAnimator == null)
      paramAttributeSet = new ValueAnimator();
    else
      paramAttributeSet = paramValueAnimator;
    if (i == 0)
      i = 1;
    else
      i = 0;
    paramValueAnimator = localTypedArray.peekValue(5);
    int j;
    if (paramValueAnimator != null)
      j = 1;
    else
      j = 0;
    int m;
    if (j != 0)
      m = paramValueAnimator.type;
    else
      m = 0;
    paramValueAnimator = localTypedArray.peekValue(6);
    int k;
    if (paramValueAnimator != null)
      k = 1;
    else
      k = 0;
    int n;
    if (k != 0)
      n = paramValueAnimator.type;
    else
      n = 0;
    int i1;
    if ((j == 0) || (m < 28) || (m > 31))
    {
      i1 = i;
      if (k != 0)
      {
        i1 = i;
        if (n >= 28)
        {
          i1 = i;
          if (n > 31);
        }
      }
    }
    else
    {
      paramAttributeSet.setEvaluator(new ArgbEvaluator());
      i1 = 0;
    }
    if (i1 != 0)
    {
      float f1;
      if (j != 0)
      {
        if (m == 5)
          f1 = localTypedArray.getDimension(5, 0.0F);
        else
          f1 = localTypedArray.getFloat(5, 0.0F);
        if (k != 0)
        {
          float f2;
          if (n == 5)
            f2 = localTypedArray.getDimension(6, 0.0F);
          else
            f2 = localTypedArray.getFloat(6, 0.0F);
          paramAttributeSet.setFloatValues(new float[] { f1, f2 });
        }
        else
        {
          paramAttributeSet.setFloatValues(new float[] { f1 });
        }
      }
      else
      {
        if (n == 5)
          f1 = localTypedArray.getDimension(6, 0.0F);
        else
          f1 = localTypedArray.getFloat(6, 0.0F);
        paramAttributeSet.setFloatValues(new float[] { f1 });
      }
    }
    else if (j != 0)
    {
      if (m == 5)
        i = (int)localTypedArray.getDimension(5, 0.0F);
      else if ((m >= 28) && (m <= 31))
        i = localTypedArray.getColor(5, 0);
      else
        i = localTypedArray.getInt(5, 0);
      if (k != 0)
      {
        if (n == 5)
          j = (int)localTypedArray.getDimension(6, 0.0F);
        else if ((n >= 28) && (n <= 31))
          j = localTypedArray.getColor(6, 0);
        else
          j = localTypedArray.getInt(6, 0);
        paramAttributeSet.setIntValues(new int[] { i, j });
      }
      else
      {
        paramAttributeSet.setIntValues(new int[] { i });
      }
    }
    else if (k != 0)
    {
      if (n == 5)
        i = (int)localTypedArray.getDimension(6, 0.0F);
      while (true)
      {
        break;
        if ((n >= 28) && (n <= 31))
          i = localTypedArray.getColor(6, 0);
        else
          i = localTypedArray.getInt(6, 0);
      }
      paramAttributeSet.setIntValues(new int[] { i });
    }
    paramAttributeSet.setDuration(l1);
    paramAttributeSet.setStartDelay(l2);
    if (localTypedArray.hasValue(3))
      paramAttributeSet.setRepeatCount(localTypedArray.getInt(3, 0));
    if (localTypedArray.hasValue(4))
      paramAttributeSet.setRepeatMode(localTypedArray.getInt(4, 1));
    i = localTypedArray.getResourceId(0, 0);
    if (i > 0)
      paramAttributeSet.setInterpolator(AnimationUtils.loadInterpolator(paramContext, i));
    localTypedArray.recycle();
    return paramAttributeSet;
  }

  private static ObjectAnimator loadObjectAnimator(Context paramContext, AttributeSet paramAttributeSet)
    throws Resources.NotFoundException
  {
    ObjectAnimator localObjectAnimator = new ObjectAnimator();
    loadAnimator(paramContext, paramAttributeSet, localObjectAnimator);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, PropertyAnimator);
    localObjectAnimator.setPropertyName(paramContext.getString(0));
    paramContext.recycle();
    return localObjectAnimator;
  }
}