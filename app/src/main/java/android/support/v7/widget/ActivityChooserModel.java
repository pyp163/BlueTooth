package android.support.v7.widget;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.DataSetObservable;
import android.os.AsyncTask;
import android.text.TextUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ActivityChooserModel extends DataSetObservable
{
  static final String ATTRIBUTE_ACTIVITY = "activity";
  static final String ATTRIBUTE_TIME = "time";
  static final String ATTRIBUTE_WEIGHT = "weight";
  static final boolean DEBUG = false;
  private static final int DEFAULT_ACTIVITY_INFLATION = 5;
  private static final float DEFAULT_HISTORICAL_RECORD_WEIGHT = 1.0F;
  public static final String DEFAULT_HISTORY_FILE_NAME = "activity_choser_model_history.xml";
  public static final int DEFAULT_HISTORY_MAX_LENGTH = 50;
  private static final String HISTORY_FILE_EXTENSION = ".xml";
  private static final int INVALID_INDEX = -1;
  static final String LOG_TAG = "ActivityChooserModel";
  static final String TAG_HISTORICAL_RECORD = "historical-record";
  static final String TAG_HISTORICAL_RECORDS = "historical-records";
  private static final Map<String, ActivityChooserModel> sDataModelRegistry = new HashMap();
  private static final Object sRegistryLock = new Object();
  private final List<ActivityResolveInfo> mActivities = new ArrayList();
  private OnChooseActivityListener mActivityChoserModelPolicy;
  private ActivitySorter mActivitySorter = new DefaultSorter();
  boolean mCanReadHistoricalData = true;
  final Context mContext;
  private final List<HistoricalRecord> mHistoricalRecords = new ArrayList();
  private boolean mHistoricalRecordsChanged = true;
  final String mHistoryFileName;
  private int mHistoryMaxSize = 50;
  private final Object mInstanceLock = new Object();
  private Intent mIntent;
  private boolean mReadShareHistoryCalled = false;
  private boolean mReloadActivities = false;

  private ActivityChooserModel(Context paramContext, String paramString)
  {
    this.mContext = paramContext.getApplicationContext();
    if ((!TextUtils.isEmpty(paramString)) && (!paramString.endsWith(".xml")))
    {
      paramContext = new StringBuilder();
      paramContext.append(paramString);
      paramContext.append(".xml");
      this.mHistoryFileName = paramContext.toString();
      return;
    }
    this.mHistoryFileName = paramString;
  }

  private boolean addHistoricalRecord(HistoricalRecord paramHistoricalRecord)
  {
    boolean bool = this.mHistoricalRecords.add(paramHistoricalRecord);
    if (bool)
    {
      this.mHistoricalRecordsChanged = true;
      pruneExcessiveHistoricalRecordsIfNeeded();
      persistHistoricalDataIfNeeded();
      sortActivitiesIfNeeded();
      notifyChanged();
    }
    return bool;
  }

  private void ensureConsistentState()
  {
    boolean bool1 = loadActivitiesIfNeeded();
    boolean bool2 = readHistoricalDataIfNeeded();
    pruneExcessiveHistoricalRecordsIfNeeded();
    if ((bool1 | bool2))
    {
      sortActivitiesIfNeeded();
      notifyChanged();
    }
  }

  public static ActivityChooserModel get(Context paramContext, String paramString)
  {
    synchronized (sRegistryLock)
    {
      ActivityChooserModel localActivityChooserModel2 = (ActivityChooserModel)sDataModelRegistry.get(paramString);
      ActivityChooserModel localActivityChooserModel1 = localActivityChooserModel2;
      if (localActivityChooserModel2 == null)
      {
        localActivityChooserModel1 = new ActivityChooserModel(paramContext, paramString);
        sDataModelRegistry.put(paramString, localActivityChooserModel1);
      }
      return localActivityChooserModel1;
    }
  }

  private boolean loadActivitiesIfNeeded()
  {
    boolean bool = this.mReloadActivities;
    int i = 0;
    if ((bool) && (this.mIntent != null))
    {
      this.mReloadActivities = false;
      this.mActivities.clear();
      List localList = this.mContext.getPackageManager().queryIntentActivities(this.mIntent, 0);
      int j = localList.size();
      while (i < j)
      {
        ResolveInfo localResolveInfo = (ResolveInfo)localList.get(i);
        this.mActivities.add(new ActivityResolveInfo(localResolveInfo));
        i += 1;
      }
      return true;
    }
    return false;
  }

  private void persistHistoricalDataIfNeeded()
  {
    if (!this.mReadShareHistoryCalled)
      throw new IllegalStateException("No preceding call to #readHistoricalData");
    if (!this.mHistoricalRecordsChanged)
      return;
    this.mHistoricalRecordsChanged = false;
    if (!TextUtils.isEmpty(this.mHistoryFileName))
      new PersistHistoryAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Object[] { new ArrayList(this.mHistoricalRecords), this.mHistoryFileName });
  }

  private void pruneExcessiveHistoricalRecordsIfNeeded()
  {
    int j = this.mHistoricalRecords.size() - this.mHistoryMaxSize;
    if (j <= 0)
      return;
    this.mHistoricalRecordsChanged = true;
    int i = 0;
    while (i < j)
    {
      HistoricalRecord localHistoricalRecord = (HistoricalRecord)this.mHistoricalRecords.remove(0);
      i += 1;
    }
  }

  private boolean readHistoricalDataIfNeeded()
  {
    if ((this.mCanReadHistoricalData) && (this.mHistoricalRecordsChanged) && (!TextUtils.isEmpty(this.mHistoryFileName)))
    {
      this.mCanReadHistoricalData = false;
      this.mReadShareHistoryCalled = true;
      readHistoricalDataImpl();
      return true;
    }
    return false;
  }

  // ERROR //
  private void readHistoricalDataImpl()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 135	android/support/v7/widget/ActivityChooserModel:mContext	Landroid/content/Context;
    //   4: aload_0
    //   5: getfield 160	android/support/v7/widget/ActivityChooserModel:mHistoryFileName	Ljava/lang/String;
    //   8: invokevirtual 267	android/content/Context:openFileInput	(Ljava/lang/String;)Ljava/io/FileInputStream;
    //   11: astore_2
    //   12: invokestatic 273	android/util/Xml:newPullParser	()Lorg/xmlpull/v1/XmlPullParser;
    //   15: astore_3
    //   16: aload_3
    //   17: aload_2
    //   18: ldc_w 275
    //   21: invokeinterface 281 3 0
    //   26: iconst_0
    //   27: istore_1
    //   28: iload_1
    //   29: iconst_1
    //   30: if_icmpeq +18 -> 48
    //   33: iload_1
    //   34: iconst_2
    //   35: if_icmpeq +13 -> 48
    //   38: aload_3
    //   39: invokeinterface 284 1 0
    //   44: istore_1
    //   45: goto -17 -> 28
    //   48: ldc 63
    //   50: aload_3
    //   51: invokeinterface 287 1 0
    //   56: invokevirtual 290	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   59: ifne +14 -> 73
    //   62: new 261	org/xmlpull/v1/XmlPullParserException
    //   65: dup
    //   66: ldc_w 292
    //   69: invokespecial 293	org/xmlpull/v1/XmlPullParserException:<init>	(Ljava/lang/String;)V
    //   72: athrow
    //   73: aload_0
    //   74: getfield 114	android/support/v7/widget/ActivityChooserModel:mHistoricalRecords	Ljava/util/List;
    //   77: astore 4
    //   79: aload 4
    //   81: invokeinterface 206 1 0
    //   86: aload_3
    //   87: invokeinterface 284 1 0
    //   92: istore_1
    //   93: iload_1
    //   94: iconst_1
    //   95: if_icmpne +12 -> 107
    //   98: aload_2
    //   99: ifnull +207 -> 306
    //   102: aload_2
    //   103: invokevirtual 298	java/io/FileInputStream:close	()V
    //   106: return
    //   107: iload_1
    //   108: iconst_3
    //   109: if_icmpeq -23 -> 86
    //   112: iload_1
    //   113: iconst_4
    //   114: if_icmpne +6 -> 120
    //   117: goto -31 -> 86
    //   120: ldc 60
    //   122: aload_3
    //   123: invokeinterface 287 1 0
    //   128: invokevirtual 290	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   131: ifne +14 -> 145
    //   134: new 261	org/xmlpull/v1/XmlPullParserException
    //   137: dup
    //   138: ldc_w 300
    //   141: invokespecial 293	org/xmlpull/v1/XmlPullParserException:<init>	(Ljava/lang/String;)V
    //   144: athrow
    //   145: aload 4
    //   147: new 18	android/support/v7/widget/ActivityChooserModel$HistoricalRecord
    //   150: dup
    //   151: aload_3
    //   152: aconst_null
    //   153: ldc 29
    //   155: invokeinterface 304 3 0
    //   160: aload_3
    //   161: aconst_null
    //   162: ldc 32
    //   164: invokeinterface 304 3 0
    //   169: invokestatic 310	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   172: aload_3
    //   173: aconst_null
    //   174: ldc 35
    //   176: invokeinterface 304 3 0
    //   181: invokestatic 316	java/lang/Float:parseFloat	(Ljava/lang/String;)F
    //   184: invokespecial 319	android/support/v7/widget/ActivityChooserModel$HistoricalRecord:<init>	(Ljava/lang/String;JF)V
    //   187: invokeinterface 168 2 0
    //   192: pop
    //   193: goto -107 -> 86
    //   196: astore_3
    //   197: goto +110 -> 307
    //   200: astore_3
    //   201: getstatic 321	android/support/v7/widget/ActivityChooserModel:LOG_TAG	Ljava/lang/String;
    //   204: astore 4
    //   206: new 149	java/lang/StringBuilder
    //   209: dup
    //   210: invokespecial 150	java/lang/StringBuilder:<init>	()V
    //   213: astore 5
    //   215: aload 5
    //   217: ldc_w 323
    //   220: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   223: pop
    //   224: aload 5
    //   226: aload_0
    //   227: getfield 160	android/support/v7/widget/ActivityChooserModel:mHistoryFileName	Ljava/lang/String;
    //   230: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   233: pop
    //   234: aload 4
    //   236: aload 5
    //   238: invokevirtual 158	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   241: aload_3
    //   242: invokestatic 329	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   245: pop
    //   246: aload_2
    //   247: ifnull +59 -> 306
    //   250: goto -148 -> 102
    //   253: astore_3
    //   254: getstatic 321	android/support/v7/widget/ActivityChooserModel:LOG_TAG	Ljava/lang/String;
    //   257: astore 4
    //   259: new 149	java/lang/StringBuilder
    //   262: dup
    //   263: invokespecial 150	java/lang/StringBuilder:<init>	()V
    //   266: astore 5
    //   268: aload 5
    //   270: ldc_w 323
    //   273: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   276: pop
    //   277: aload 5
    //   279: aload_0
    //   280: getfield 160	android/support/v7/widget/ActivityChooserModel:mHistoryFileName	Ljava/lang/String;
    //   283: invokevirtual 154	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   286: pop
    //   287: aload 4
    //   289: aload 5
    //   291: invokevirtual 158	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   294: aload_3
    //   295: invokestatic 329	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   298: pop
    //   299: aload_2
    //   300: ifnull +6 -> 306
    //   303: goto -201 -> 102
    //   306: return
    //   307: aload_2
    //   308: ifnull +7 -> 315
    //   311: aload_2
    //   312: invokevirtual 298	java/io/FileInputStream:close	()V
    //   315: aload_3
    //   316: athrow
    //   317: astore_2
    //   318: return
    //   319: astore_2
    //   320: return
    //   321: astore_2
    //   322: goto -7 -> 315
    //
    // Exception table:
    //   from	to	target	type
    //   12	26	196	finally
    //   38	45	196	finally
    //   48	73	196	finally
    //   73	86	196	finally
    //   86	93	196	finally
    //   120	145	196	finally
    //   145	193	196	finally
    //   201	246	196	finally
    //   254	299	196	finally
    //   12	26	200	java/io/IOException
    //   38	45	200	java/io/IOException
    //   48	73	200	java/io/IOException
    //   73	86	200	java/io/IOException
    //   86	93	200	java/io/IOException
    //   120	145	200	java/io/IOException
    //   145	193	200	java/io/IOException
    //   12	26	253	org/xmlpull/v1/XmlPullParserException
    //   38	45	253	org/xmlpull/v1/XmlPullParserException
    //   48	73	253	org/xmlpull/v1/XmlPullParserException
    //   73	86	253	org/xmlpull/v1/XmlPullParserException
    //   86	93	253	org/xmlpull/v1/XmlPullParserException
    //   120	145	253	org/xmlpull/v1/XmlPullParserException
    //   145	193	253	org/xmlpull/v1/XmlPullParserException
    //   0	12	317	java/io/FileNotFoundException
    //   102	106	319	java/io/IOException
    //   311	315	321	java/io/IOException
  }

  private boolean sortActivitiesIfNeeded()
  {
    if ((this.mActivitySorter != null) && (this.mIntent != null) && (!this.mActivities.isEmpty()) && (!this.mHistoricalRecords.isEmpty()))
    {
      this.mActivitySorter.sort(this.mIntent, this.mActivities, Collections.unmodifiableList(this.mHistoricalRecords));
      return true;
    }
    return false;
  }

  public Intent chooseActivity(int paramInt)
  {
    synchronized (this.mInstanceLock)
    {
      if (this.mIntent == null)
        return null;
      ensureConsistentState();
      Object localObject2 = (ActivityResolveInfo)this.mActivities.get(paramInt);
      localObject2 = new ComponentName(((ActivityResolveInfo)localObject2).resolveInfo.activityInfo.packageName, ((ActivityResolveInfo)localObject2).resolveInfo.activityInfo.name);
      Intent localIntent1 = new Intent(this.mIntent);
      localIntent1.setComponent((ComponentName)localObject2);
      if (this.mActivityChoserModelPolicy != null)
      {
        Intent localIntent2 = new Intent(localIntent1);
        if (this.mActivityChoserModelPolicy.onChooseActivity(this, localIntent2))
          return null;
      }
      addHistoricalRecord(new HistoricalRecord((ComponentName)localObject2, System.currentTimeMillis(), 1.0F));
      return localIntent1;
    }
  }

  public ResolveInfo getActivity(int paramInt)
  {
    synchronized (this.mInstanceLock)
    {
      ensureConsistentState();
      ResolveInfo localResolveInfo = ((ActivityResolveInfo)this.mActivities.get(paramInt)).resolveInfo;
      return localResolveInfo;
    }
  }

  public int getActivityCount()
  {
    synchronized (this.mInstanceLock)
    {
      ensureConsistentState();
      int i = this.mActivities.size();
      return i;
    }
  }

  public int getActivityIndex(ResolveInfo paramResolveInfo)
  {
    while (true)
    {
      int i;
      synchronized (this.mInstanceLock)
      {
        ensureConsistentState();
        List localList = this.mActivities;
        int j = localList.size();
        i = 0;
        if (i < j)
        {
          if (((ActivityResolveInfo)localList.get(i)).resolveInfo == paramResolveInfo)
            return i;
        }
        else
          return -1;
      }
      i += 1;
    }
  }

  public ResolveInfo getDefaultActivity()
  {
    synchronized (this.mInstanceLock)
    {
      ensureConsistentState();
      if (!this.mActivities.isEmpty())
      {
        ResolveInfo localResolveInfo = ((ActivityResolveInfo)this.mActivities.get(0)).resolveInfo;
        return localResolveInfo;
      }
      return null;
    }
  }

  public int getHistoryMaxSize()
  {
    synchronized (this.mInstanceLock)
    {
      int i = this.mHistoryMaxSize;
      return i;
    }
  }

  public int getHistorySize()
  {
    synchronized (this.mInstanceLock)
    {
      ensureConsistentState();
      int i = this.mHistoricalRecords.size();
      return i;
    }
  }

  public Intent getIntent()
  {
    synchronized (this.mInstanceLock)
    {
      Intent localIntent = this.mIntent;
      return localIntent;
    }
  }

  public void setActivitySorter(ActivitySorter paramActivitySorter)
  {
    synchronized (this.mInstanceLock)
    {
      if (this.mActivitySorter == paramActivitySorter)
        return;
      this.mActivitySorter = paramActivitySorter;
      if (sortActivitiesIfNeeded())
        notifyChanged();
      return;
    }
  }

  public void setDefaultActivity(int paramInt)
  {
    while (true)
    {
      synchronized (this.mInstanceLock)
      {
        ensureConsistentState();
        ActivityResolveInfo localActivityResolveInfo1 = (ActivityResolveInfo)this.mActivities.get(paramInt);
        ActivityResolveInfo localActivityResolveInfo2 = (ActivityResolveInfo)this.mActivities.get(0);
        if (localActivityResolveInfo2 != null)
        {
          f = localActivityResolveInfo2.weight - localActivityResolveInfo1.weight + 5.0F;
          addHistoricalRecord(new HistoricalRecord(new ComponentName(localActivityResolveInfo1.resolveInfo.activityInfo.packageName, localActivityResolveInfo1.resolveInfo.activityInfo.name), System.currentTimeMillis(), f));
          return;
        }
      }
      float f = 1.0F;
    }
  }

  public void setHistoryMaxSize(int paramInt)
  {
    synchronized (this.mInstanceLock)
    {
      if (this.mHistoryMaxSize == paramInt)
        return;
      this.mHistoryMaxSize = paramInt;
      pruneExcessiveHistoricalRecordsIfNeeded();
      if (sortActivitiesIfNeeded())
        notifyChanged();
      return;
    }
  }

  public void setIntent(Intent paramIntent)
  {
    synchronized (this.mInstanceLock)
    {
      if (this.mIntent == paramIntent)
        return;
      this.mIntent = paramIntent;
      this.mReloadActivities = true;
      ensureConsistentState();
      return;
    }
  }

  public void setOnChooseActivityListener(OnChooseActivityListener paramOnChooseActivityListener)
  {
    synchronized (this.mInstanceLock)
    {
      this.mActivityChoserModelPolicy = paramOnChooseActivityListener;
      return;
    }
  }

  public static abstract interface ActivityChooserModelClient
  {
    public abstract void setActivityChooserModel(ActivityChooserModel paramActivityChooserModel);
  }

  public static final class ActivityResolveInfo
    implements Comparable<ActivityResolveInfo>
  {
    public final ResolveInfo resolveInfo;
    public float weight;

    public ActivityResolveInfo(ResolveInfo paramResolveInfo)
    {
      this.resolveInfo = paramResolveInfo;
    }

    public int compareTo(ActivityResolveInfo paramActivityResolveInfo)
    {
      return Float.floatToIntBits(paramActivityResolveInfo.weight) - Float.floatToIntBits(this.weight);
    }

    public boolean equals(Object paramObject)
    {
      if (this == paramObject)
        return true;
      if (paramObject == null)
        return false;
      if (getClass() != paramObject.getClass())
        return false;
      paramObject = (ActivityResolveInfo)paramObject;
      return Float.floatToIntBits(this.weight) == Float.floatToIntBits(paramObject.weight);
    }

    public int hashCode()
    {
      return Float.floatToIntBits(this.weight) + 31;
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("[");
      localStringBuilder.append("resolveInfo:");
      localStringBuilder.append(this.resolveInfo.toString());
      localStringBuilder.append("; weight:");
      localStringBuilder.append(new BigDecimal(this.weight));
      localStringBuilder.append("]");
      return localStringBuilder.toString();
    }
  }

  public static abstract interface ActivitySorter
  {
    public abstract void sort(Intent paramIntent, List<ActivityChooserModel.ActivityResolveInfo> paramList, List<ActivityChooserModel.HistoricalRecord> paramList1);
  }

  private static final class DefaultSorter
    implements ActivityChooserModel.ActivitySorter
  {
    private static final float WEIGHT_DECAY_COEFFICIENT = 0.95F;
    private final Map<ComponentName, ActivityChooserModel.ActivityResolveInfo> mPackageNameToActivityMap = new HashMap();

    public void sort(Intent paramIntent, List<ActivityChooserModel.ActivityResolveInfo> paramList, List<ActivityChooserModel.HistoricalRecord> paramList1)
    {
      paramIntent = this.mPackageNameToActivityMap;
      paramIntent.clear();
      int j = paramList.size();
      int i = 0;
      Object localObject;
      while (i < j)
      {
        localObject = (ActivityChooserModel.ActivityResolveInfo)paramList.get(i);
        ((ActivityChooserModel.ActivityResolveInfo)localObject).weight = 0.0F;
        paramIntent.put(new ComponentName(((ActivityChooserModel.ActivityResolveInfo)localObject).resolveInfo.activityInfo.packageName, ((ActivityChooserModel.ActivityResolveInfo)localObject).resolveInfo.activityInfo.name), localObject);
        i += 1;
      }
      i = paramList1.size() - 1;
      float f2;
      for (float f1 = 1.0F; i >= 0; f1 = f2)
      {
        localObject = (ActivityChooserModel.HistoricalRecord)paramList1.get(i);
        ActivityChooserModel.ActivityResolveInfo localActivityResolveInfo = (ActivityChooserModel.ActivityResolveInfo)paramIntent.get(((ActivityChooserModel.HistoricalRecord)localObject).activity);
        f2 = f1;
        if (localActivityResolveInfo != null)
        {
          localActivityResolveInfo.weight += ((ActivityChooserModel.HistoricalRecord)localObject).weight * f1;
          f2 = f1 * 0.95F;
        }
        i -= 1;
      }
      Collections.sort(paramList);
    }
  }

  public static final class HistoricalRecord
  {
    public final ComponentName activity;
    public final long time;
    public final float weight;

    public HistoricalRecord(ComponentName paramComponentName, long paramLong, float paramFloat)
    {
      this.activity = paramComponentName;
      this.time = paramLong;
      this.weight = paramFloat;
    }

    public HistoricalRecord(String paramString, long paramLong, float paramFloat)
    {
      this(ComponentName.unflattenFromString(paramString), paramLong, paramFloat);
    }

    public boolean equals(Object paramObject)
    {
      if (this == paramObject)
        return true;
      if (paramObject == null)
        return false;
      if (getClass() != paramObject.getClass())
        return false;
      paramObject = (HistoricalRecord)paramObject;
      if (this.activity == null)
      {
        if (paramObject.activity != null)
          return false;
      }
      else if (!this.activity.equals(paramObject.activity))
        return false;
      if (this.time != paramObject.time)
        return false;
      return Float.floatToIntBits(this.weight) == Float.floatToIntBits(paramObject.weight);
    }

    public int hashCode()
    {
      int i;
      if (this.activity == null)
        i = 0;
      else
        i = this.activity.hashCode();
      return ((i + 31) * 31 + (int)(this.time ^ this.time >>> 32)) * 31 + Float.floatToIntBits(this.weight);
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("[");
      localStringBuilder.append("; activity:");
      localStringBuilder.append(this.activity);
      localStringBuilder.append("; time:");
      localStringBuilder.append(this.time);
      localStringBuilder.append("; weight:");
      localStringBuilder.append(new BigDecimal(this.weight));
      localStringBuilder.append("]");
      return localStringBuilder.toString();
    }
  }

  public static abstract interface OnChooseActivityListener
  {
    public abstract boolean onChooseActivity(ActivityChooserModel paramActivityChooserModel, Intent paramIntent);
  }

  private final class PersistHistoryAsyncTask extends AsyncTask<Object, Void, Void>
  {
    PersistHistoryAsyncTask()
    {
    }

    // ERROR //
    public Void doInBackground(Object[] paramArrayOfObject)
    {
      // Byte code:
      //   0: aload_1
      //   1: iconst_0
      //   2: aaload
      //   3: checkcast 33	java/util/List
      //   6: astore 4
      //   8: aload_1
      //   9: iconst_1
      //   10: aaload
      //   11: checkcast 35	java/lang/String
      //   14: astore 5
      //   16: aload_0
      //   17: getfield 14	android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask:this$0	Landroid/support/v7/widget/ActivityChooserModel;
      //   20: getfield 39	android/support/v7/widget/ActivityChooserModel:mContext	Landroid/content/Context;
      //   23: aload 5
      //   25: iconst_0
      //   26: invokevirtual 45	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
      //   29: astore_1
      //   30: invokestatic 51	android/util/Xml:newSerializer	()Lorg/xmlpull/v1/XmlSerializer;
      //   33: astore 5
      //   35: aload 5
      //   37: aload_1
      //   38: aconst_null
      //   39: invokeinterface 57 3 0
      //   44: aload 5
      //   46: ldc 59
      //   48: iconst_1
      //   49: invokestatic 65	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
      //   52: invokeinterface 69 3 0
      //   57: aload 5
      //   59: aconst_null
      //   60: ldc 71
      //   62: invokeinterface 75 3 0
      //   67: pop
      //   68: aload 4
      //   70: invokeinterface 79 1 0
      //   75: istore_3
      //   76: iconst_0
      //   77: istore_2
      //   78: iload_2
      //   79: iload_3
      //   80: if_icmpge +102 -> 182
      //   83: aload 4
      //   85: iconst_0
      //   86: invokeinterface 83 2 0
      //   91: checkcast 85	android/support/v7/widget/ActivityChooserModel$HistoricalRecord
      //   94: astore 6
      //   96: aload 5
      //   98: aconst_null
      //   99: ldc 87
      //   101: invokeinterface 75 3 0
      //   106: pop
      //   107: aload 5
      //   109: aconst_null
      //   110: ldc 89
      //   112: aload 6
      //   114: getfield 92	android/support/v7/widget/ActivityChooserModel$HistoricalRecord:activity	Landroid/content/ComponentName;
      //   117: invokevirtual 98	android/content/ComponentName:flattenToString	()Ljava/lang/String;
      //   120: invokeinterface 102 4 0
      //   125: pop
      //   126: aload 5
      //   128: aconst_null
      //   129: ldc 104
      //   131: aload 6
      //   133: getfield 107	android/support/v7/widget/ActivityChooserModel$HistoricalRecord:time	J
      //   136: invokestatic 110	java/lang/String:valueOf	(J)Ljava/lang/String;
      //   139: invokeinterface 102 4 0
      //   144: pop
      //   145: aload 5
      //   147: aconst_null
      //   148: ldc 112
      //   150: aload 6
      //   152: getfield 115	android/support/v7/widget/ActivityChooserModel$HistoricalRecord:weight	F
      //   155: invokestatic 118	java/lang/String:valueOf	(F)Ljava/lang/String;
      //   158: invokeinterface 102 4 0
      //   163: pop
      //   164: aload 5
      //   166: aconst_null
      //   167: ldc 87
      //   169: invokeinterface 121 3 0
      //   174: pop
      //   175: iload_2
      //   176: iconst_1
      //   177: iadd
      //   178: istore_2
      //   179: goto -101 -> 78
      //   182: aload 5
      //   184: aconst_null
      //   185: ldc 71
      //   187: invokeinterface 121 3 0
      //   192: pop
      //   193: aload 5
      //   195: invokeinterface 124 1 0
      //   200: aload_0
      //   201: getfield 14	android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask:this$0	Landroid/support/v7/widget/ActivityChooserModel;
      //   204: iconst_1
      //   205: putfield 128	android/support/v7/widget/ActivityChooserModel:mCanReadHistoricalData	Z
      //   208: aload_1
      //   209: ifnull +209 -> 418
      //   212: aload_1
      //   213: invokevirtual 133	java/io/FileOutputStream:close	()V
      //   216: aconst_null
      //   217: areturn
      //   218: astore 4
      //   220: goto +200 -> 420
      //   223: astore 4
      //   225: getstatic 137	android/support/v7/widget/ActivityChooserModel:LOG_TAG	Ljava/lang/String;
      //   228: astore 5
      //   230: new 139	java/lang/StringBuilder
      //   233: dup
      //   234: invokespecial 140	java/lang/StringBuilder:<init>	()V
      //   237: astore 6
      //   239: aload 6
      //   241: ldc 142
      //   243: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   246: pop
      //   247: aload 6
      //   249: aload_0
      //   250: getfield 14	android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask:this$0	Landroid/support/v7/widget/ActivityChooserModel;
      //   253: getfield 149	android/support/v7/widget/ActivityChooserModel:mHistoryFileName	Ljava/lang/String;
      //   256: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   259: pop
      //   260: aload 5
      //   262: aload 6
      //   264: invokevirtual 152	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   267: aload 4
      //   269: invokestatic 158	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   272: pop
      //   273: aload_0
      //   274: getfield 14	android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask:this$0	Landroid/support/v7/widget/ActivityChooserModel;
      //   277: iconst_1
      //   278: putfield 128	android/support/v7/widget/ActivityChooserModel:mCanReadHistoricalData	Z
      //   281: aload_1
      //   282: ifnull +136 -> 418
      //   285: goto -73 -> 212
      //   288: astore 4
      //   290: getstatic 137	android/support/v7/widget/ActivityChooserModel:LOG_TAG	Ljava/lang/String;
      //   293: astore 5
      //   295: new 139	java/lang/StringBuilder
      //   298: dup
      //   299: invokespecial 140	java/lang/StringBuilder:<init>	()V
      //   302: astore 6
      //   304: aload 6
      //   306: ldc 142
      //   308: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   311: pop
      //   312: aload 6
      //   314: aload_0
      //   315: getfield 14	android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask:this$0	Landroid/support/v7/widget/ActivityChooserModel;
      //   318: getfield 149	android/support/v7/widget/ActivityChooserModel:mHistoryFileName	Ljava/lang/String;
      //   321: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   324: pop
      //   325: aload 5
      //   327: aload 6
      //   329: invokevirtual 152	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   332: aload 4
      //   334: invokestatic 158	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   337: pop
      //   338: aload_0
      //   339: getfield 14	android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask:this$0	Landroid/support/v7/widget/ActivityChooserModel;
      //   342: iconst_1
      //   343: putfield 128	android/support/v7/widget/ActivityChooserModel:mCanReadHistoricalData	Z
      //   346: aload_1
      //   347: ifnull +71 -> 418
      //   350: goto -138 -> 212
      //   353: astore 4
      //   355: getstatic 137	android/support/v7/widget/ActivityChooserModel:LOG_TAG	Ljava/lang/String;
      //   358: astore 5
      //   360: new 139	java/lang/StringBuilder
      //   363: dup
      //   364: invokespecial 140	java/lang/StringBuilder:<init>	()V
      //   367: astore 6
      //   369: aload 6
      //   371: ldc 142
      //   373: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   376: pop
      //   377: aload 6
      //   379: aload_0
      //   380: getfield 14	android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask:this$0	Landroid/support/v7/widget/ActivityChooserModel;
      //   383: getfield 149	android/support/v7/widget/ActivityChooserModel:mHistoryFileName	Ljava/lang/String;
      //   386: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   389: pop
      //   390: aload 5
      //   392: aload 6
      //   394: invokevirtual 152	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   397: aload 4
      //   399: invokestatic 158	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   402: pop
      //   403: aload_0
      //   404: getfield 14	android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask:this$0	Landroid/support/v7/widget/ActivityChooserModel;
      //   407: iconst_1
      //   408: putfield 128	android/support/v7/widget/ActivityChooserModel:mCanReadHistoricalData	Z
      //   411: aload_1
      //   412: ifnull +6 -> 418
      //   415: goto -203 -> 212
      //   418: aconst_null
      //   419: areturn
      //   420: aload_0
      //   421: getfield 14	android/support/v7/widget/ActivityChooserModel$PersistHistoryAsyncTask:this$0	Landroid/support/v7/widget/ActivityChooserModel;
      //   424: iconst_1
      //   425: putfield 128	android/support/v7/widget/ActivityChooserModel:mCanReadHistoricalData	Z
      //   428: aload_1
      //   429: ifnull +7 -> 436
      //   432: aload_1
      //   433: invokevirtual 133	java/io/FileOutputStream:close	()V
      //   436: aload 4
      //   438: athrow
      //   439: astore_1
      //   440: getstatic 137	android/support/v7/widget/ActivityChooserModel:LOG_TAG	Ljava/lang/String;
      //   443: astore 4
      //   445: new 139	java/lang/StringBuilder
      //   448: dup
      //   449: invokespecial 140	java/lang/StringBuilder:<init>	()V
      //   452: astore 6
      //   454: aload 6
      //   456: ldc 142
      //   458: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   461: pop
      //   462: aload 6
      //   464: aload 5
      //   466: invokevirtual 146	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   469: pop
      //   470: aload 4
      //   472: aload 6
      //   474: invokevirtual 152	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   477: aload_1
      //   478: invokestatic 158	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
      //   481: pop
      //   482: aconst_null
      //   483: areturn
      //   484: astore_1
      //   485: aconst_null
      //   486: areturn
      //   487: astore_1
      //   488: goto -52 -> 436
      //
      // Exception table:
      //   from	to	target	type
      //   35	76	218	finally
      //   83	175	218	finally
      //   182	200	218	finally
      //   225	273	218	finally
      //   290	338	218	finally
      //   355	403	218	finally
      //   35	76	223	java/io/IOException
      //   83	175	223	java/io/IOException
      //   182	200	223	java/io/IOException
      //   35	76	288	java/lang/IllegalStateException
      //   83	175	288	java/lang/IllegalStateException
      //   182	200	288	java/lang/IllegalStateException
      //   35	76	353	java/lang/IllegalArgumentException
      //   83	175	353	java/lang/IllegalArgumentException
      //   182	200	353	java/lang/IllegalArgumentException
      //   16	30	439	java/io/FileNotFoundException
      //   212	216	484	java/io/IOException
      //   432	436	487	java/io/IOException
    }
  }
}