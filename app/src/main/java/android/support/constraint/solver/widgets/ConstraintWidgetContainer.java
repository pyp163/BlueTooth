package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.Metrics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConstraintWidgetContainer extends WidgetContainer
{
  private static final boolean DEBUG = false;
  static final boolean DEBUG_GRAPH = false;
  private static final boolean DEBUG_LAYOUT = false;
  private static final int MAX_ITERATIONS = 8;
  private static final boolean USE_SNAPSHOT = true;
  int mDebugSolverPassCount = 0;
  public boolean mGroupsWrapOptimized = false;
  private boolean mHeightMeasuredTooSmall = false;
  ChainHead[] mHorizontalChainsArray = new ChainHead[4];
  int mHorizontalChainsSize = 0;
  public boolean mHorizontalWrapOptimized = false;
  private boolean mIsRtl = false;
  private int mOptimizationLevel = 7;
  int mPaddingBottom;
  int mPaddingLeft;
  int mPaddingRight;
  int mPaddingTop;
  public boolean mSkipSolver = false;
  private Snapshot mSnapshot;
  protected LinearSystem mSystem = new LinearSystem();
  ChainHead[] mVerticalChainsArray = new ChainHead[4];
  int mVerticalChainsSize = 0;
  public boolean mVerticalWrapOptimized = false;
  public List<ConstraintWidgetGroup> mWidgetGroups = new ArrayList();
  private boolean mWidthMeasuredTooSmall = false;
  public int mWrapFixedHeight = 0;
  public int mWrapFixedWidth = 0;

  public ConstraintWidgetContainer()
  {
  }

  public ConstraintWidgetContainer(int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
  }

  public ConstraintWidgetContainer(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  private void addHorizontalChain(ConstraintWidget paramConstraintWidget)
  {
    if (this.mHorizontalChainsSize + 1 >= this.mHorizontalChainsArray.length)
      this.mHorizontalChainsArray = ((ChainHead[])Arrays.copyOf(this.mHorizontalChainsArray, this.mHorizontalChainsArray.length * 2));
    this.mHorizontalChainsArray[this.mHorizontalChainsSize] = new ChainHead(paramConstraintWidget, 0, isRtl());
    this.mHorizontalChainsSize += 1;
  }

  private void addVerticalChain(ConstraintWidget paramConstraintWidget)
  {
    if (this.mVerticalChainsSize + 1 >= this.mVerticalChainsArray.length)
      this.mVerticalChainsArray = ((ChainHead[])Arrays.copyOf(this.mVerticalChainsArray, this.mVerticalChainsArray.length * 2));
    this.mVerticalChainsArray[this.mVerticalChainsSize] = new ChainHead(paramConstraintWidget, 1, isRtl());
    this.mVerticalChainsSize += 1;
  }

  private void resetChains()
  {
    this.mHorizontalChainsSize = 0;
    this.mVerticalChainsSize = 0;
  }

  void addChain(ConstraintWidget paramConstraintWidget, int paramInt)
  {
    if (paramInt == 0)
    {
      addHorizontalChain(paramConstraintWidget);
      return;
    }
    if (paramInt == 1)
      addVerticalChain(paramConstraintWidget);
  }

  public boolean addChildrenToSolver(LinearSystem paramLinearSystem)
  {
    addToSolver(paramLinearSystem);
    int j = this.mChildren.size();
    int i = 0;
    while (i < j)
    {
      ConstraintWidget localConstraintWidget = (ConstraintWidget)this.mChildren.get(i);
      if ((localConstraintWidget instanceof ConstraintWidgetContainer))
      {
        ConstraintWidget.DimensionBehaviour localDimensionBehaviour1 = localConstraintWidget.mListDimensionBehaviors[0];
        ConstraintWidget.DimensionBehaviour localDimensionBehaviour2 = localConstraintWidget.mListDimensionBehaviors[1];
        if (localDimensionBehaviour1 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)
          localConstraintWidget.setHorizontalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
        if (localDimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)
          localConstraintWidget.setVerticalDimensionBehaviour(ConstraintWidget.DimensionBehaviour.FIXED);
        localConstraintWidget.addToSolver(paramLinearSystem);
        if (localDimensionBehaviour1 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)
          localConstraintWidget.setHorizontalDimensionBehaviour(localDimensionBehaviour1);
        if (localDimensionBehaviour2 == ConstraintWidget.DimensionBehaviour.WRAP_CONTENT)
          localConstraintWidget.setVerticalDimensionBehaviour(localDimensionBehaviour2);
      }
      else
      {
        Optimizer.checkMatchParent(this, paramLinearSystem, localConstraintWidget);
        localConstraintWidget.addToSolver(paramLinearSystem);
      }
      i += 1;
    }
    if (this.mHorizontalChainsSize > 0)
      Chain.applyChainConstraints(this, paramLinearSystem, 0);
    if (this.mVerticalChainsSize > 0)
      Chain.applyChainConstraints(this, paramLinearSystem, 1);
    return true;
  }

  public void analyze(int paramInt)
  {
    super.analyze(paramInt);
    int j = this.mChildren.size();
    int i = 0;
    while (i < j)
    {
      ((ConstraintWidget)this.mChildren.get(i)).analyze(paramInt);
      i += 1;
    }
  }

  public void fillMetrics(Metrics paramMetrics)
  {
    this.mSystem.fillMetrics(paramMetrics);
  }

  public ArrayList<Guideline> getHorizontalGuidelines()
  {
    ArrayList localArrayList = new ArrayList();
    int j = this.mChildren.size();
    int i = 0;
    while (i < j)
    {
      Object localObject = (ConstraintWidget)this.mChildren.get(i);
      if ((localObject instanceof Guideline))
      {
        localObject = (Guideline)localObject;
        if (((Guideline)localObject).getOrientation() == 0)
          localArrayList.add(localObject);
      }
      i += 1;
    }
    return localArrayList;
  }

  public int getOptimizationLevel()
  {
    return this.mOptimizationLevel;
  }

  public LinearSystem getSystem()
  {
    return this.mSystem;
  }

  public String getType()
  {
    return "ConstraintLayout";
  }

  public ArrayList<Guideline> getVerticalGuidelines()
  {
    ArrayList localArrayList = new ArrayList();
    int j = this.mChildren.size();
    int i = 0;
    while (i < j)
    {
      Object localObject = (ConstraintWidget)this.mChildren.get(i);
      if ((localObject instanceof Guideline))
      {
        localObject = (Guideline)localObject;
        if (((Guideline)localObject).getOrientation() == 1)
          localArrayList.add(localObject);
      }
      i += 1;
    }
    return localArrayList;
  }

  public List<ConstraintWidgetGroup> getWidgetGroups()
  {
    return this.mWidgetGroups;
  }

  public boolean handlesInternalConstraints()
  {
    return false;
  }

  public boolean isHeightMeasuredTooSmall()
  {
    return this.mHeightMeasuredTooSmall;
  }

  public boolean isRtl()
  {
    return this.mIsRtl;
  }

  public boolean isWidthMeasuredTooSmall()
  {
    return this.mWidthMeasuredTooSmall;
  }

  // ERROR //
  public void layout()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 213	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mX	I
    //   4: istore 11
    //   6: aload_0
    //   7: getfield 216	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mY	I
    //   10: istore 4
    //   12: iconst_0
    //   13: aload_0
    //   14: invokevirtual 219	android/support/constraint/solver/widgets/ConstraintWidgetContainer:getWidth	()I
    //   17: invokestatic 225	java/lang/Math:max	(II)I
    //   20: istore 12
    //   22: iconst_0
    //   23: aload_0
    //   24: invokevirtual 228	android/support/constraint/solver/widgets/ConstraintWidgetContainer:getHeight	()I
    //   27: invokestatic 225	java/lang/Math:max	(II)I
    //   30: istore 13
    //   32: aload_0
    //   33: iconst_0
    //   34: putfield 83	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mWidthMeasuredTooSmall	Z
    //   37: aload_0
    //   38: iconst_0
    //   39: putfield 85	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mHeightMeasuredTooSmall	Z
    //   42: aload_0
    //   43: getfield 232	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mParent	Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   46: ifnull +64 -> 110
    //   49: aload_0
    //   50: getfield 234	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mSnapshot	Landroid/support/constraint/solver/widgets/Snapshot;
    //   53: ifnonnull +15 -> 68
    //   56: aload_0
    //   57: new 236	android/support/constraint/solver/widgets/Snapshot
    //   60: dup
    //   61: aload_0
    //   62: invokespecial 238	android/support/constraint/solver/widgets/Snapshot:<init>	(Landroid/support/constraint/solver/widgets/ConstraintWidget;)V
    //   65: putfield 234	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mSnapshot	Landroid/support/constraint/solver/widgets/Snapshot;
    //   68: aload_0
    //   69: getfield 234	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mSnapshot	Landroid/support/constraint/solver/widgets/Snapshot;
    //   72: aload_0
    //   73: invokevirtual 241	android/support/constraint/solver/widgets/Snapshot:updateFrom	(Landroid/support/constraint/solver/widgets/ConstraintWidget;)V
    //   76: aload_0
    //   77: aload_0
    //   78: getfield 243	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mPaddingLeft	I
    //   81: invokevirtual 246	android/support/constraint/solver/widgets/ConstraintWidgetContainer:setX	(I)V
    //   84: aload_0
    //   85: aload_0
    //   86: getfield 248	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mPaddingTop	I
    //   89: invokevirtual 251	android/support/constraint/solver/widgets/ConstraintWidgetContainer:setY	(I)V
    //   92: aload_0
    //   93: invokevirtual 254	android/support/constraint/solver/widgets/ConstraintWidgetContainer:resetAnchors	()V
    //   96: aload_0
    //   97: aload_0
    //   98: getfield 52	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mSystem	Landroid/support/constraint/solver/LinearSystem;
    //   101: invokevirtual 258	android/support/constraint/solver/LinearSystem:getCache	()Landroid/support/constraint/solver/Cache;
    //   104: invokevirtual 262	android/support/constraint/solver/widgets/ConstraintWidgetContainer:resetSolverVariables	(Landroid/support/constraint/solver/Cache;)V
    //   107: goto +13 -> 120
    //   110: aload_0
    //   111: iconst_0
    //   112: putfield 213	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mX	I
    //   115: aload_0
    //   116: iconst_0
    //   117: putfield 216	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mY	I
    //   120: aload_0
    //   121: getfield 79	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mOptimizationLevel	I
    //   124: ifeq +40 -> 164
    //   127: aload_0
    //   128: bipush 8
    //   130: invokevirtual 266	android/support/constraint/solver/widgets/ConstraintWidgetContainer:optimizeFor	(I)Z
    //   133: ifne +7 -> 140
    //   136: aload_0
    //   137: invokevirtual 269	android/support/constraint/solver/widgets/ConstraintWidgetContainer:optimizeReset	()V
    //   140: aload_0
    //   141: bipush 32
    //   143: invokevirtual 266	android/support/constraint/solver/widgets/ConstraintWidgetContainer:optimizeFor	(I)Z
    //   146: ifne +7 -> 153
    //   149: aload_0
    //   150: invokevirtual 272	android/support/constraint/solver/widgets/ConstraintWidgetContainer:optimize	()V
    //   153: aload_0
    //   154: getfield 52	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mSystem	Landroid/support/constraint/solver/LinearSystem;
    //   157: iconst_1
    //   158: putfield 275	android/support/constraint/solver/LinearSystem:graphOptimizer	Z
    //   161: goto +11 -> 172
    //   164: aload_0
    //   165: getfield 52	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mSystem	Landroid/support/constraint/solver/LinearSystem;
    //   168: iconst_0
    //   169: putfield 275	android/support/constraint/solver/LinearSystem:graphOptimizer	Z
    //   172: aload_0
    //   173: getfield 276	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mListDimensionBehaviors	[Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   176: iconst_1
    //   177: aaload
    //   178: astore 18
    //   180: aload_0
    //   181: getfield 276	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mListDimensionBehaviors	[Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   184: iconst_0
    //   185: aaload
    //   186: astore 19
    //   188: aload_0
    //   189: invokespecial 278	android/support/constraint/solver/widgets/ConstraintWidgetContainer:resetChains	()V
    //   192: aload_0
    //   193: getfield 67	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mWidgetGroups	Ljava/util/List;
    //   196: invokeinterface 281 1 0
    //   201: ifne +33 -> 234
    //   204: aload_0
    //   205: getfield 67	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mWidgetGroups	Ljava/util/List;
    //   208: invokeinterface 284 1 0
    //   213: aload_0
    //   214: getfield 67	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mWidgetGroups	Ljava/util/List;
    //   217: iconst_0
    //   218: new 286	android/support/constraint/solver/widgets/ConstraintWidgetGroup
    //   221: dup
    //   222: aload_0
    //   223: getfield 128	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mChildren	Ljava/util/ArrayList;
    //   226: invokespecial 289	android/support/constraint/solver/widgets/ConstraintWidgetGroup:<init>	(Ljava/util/List;)V
    //   229: invokeinterface 292 3 0
    //   234: aload_0
    //   235: getfield 67	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mWidgetGroups	Ljava/util/List;
    //   238: invokeinterface 281 1 0
    //   243: istore_2
    //   244: aload_0
    //   245: getfield 128	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mChildren	Ljava/util/ArrayList;
    //   248: astore 20
    //   250: aload_0
    //   251: invokevirtual 296	android/support/constraint/solver/widgets/ConstraintWidgetContainer:getHorizontalDimensionBehaviour	()Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   254: getstatic 148	android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour:WRAP_CONTENT	Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   257: if_acmpeq +22 -> 279
    //   260: aload_0
    //   261: invokevirtual 299	android/support/constraint/solver/widgets/ConstraintWidgetContainer:getVerticalDimensionBehaviour	()Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   264: getstatic 148	android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour:WRAP_CONTENT	Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   267: if_acmpne +6 -> 273
    //   270: goto +9 -> 279
    //   273: iconst_0
    //   274: istore 7
    //   276: goto +6 -> 282
    //   279: iconst_1
    //   280: istore 7
    //   282: iconst_0
    //   283: istore 8
    //   285: iconst_0
    //   286: istore_1
    //   287: iload 8
    //   289: iload_2
    //   290: if_icmpge +1030 -> 1320
    //   293: aload_0
    //   294: getfield 81	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mSkipSolver	Z
    //   297: ifne +1023 -> 1320
    //   300: aload_0
    //   301: getfield 67	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mWidgetGroups	Ljava/util/List;
    //   304: iload 8
    //   306: invokeinterface 300 2 0
    //   311: checkcast 286	android/support/constraint/solver/widgets/ConstraintWidgetGroup
    //   314: getfield 301	android/support/constraint/solver/widgets/ConstraintWidgetGroup:mSkipSolver	Z
    //   317: ifeq +11 -> 328
    //   320: iload_1
    //   321: istore_3
    //   322: iload 4
    //   324: istore_1
    //   325: goto +981 -> 1306
    //   328: aload_0
    //   329: bipush 32
    //   331: invokevirtual 266	android/support/constraint/solver/widgets/ConstraintWidgetContainer:optimizeFor	(I)Z
    //   334: ifeq +74 -> 408
    //   337: aload_0
    //   338: invokevirtual 296	android/support/constraint/solver/widgets/ConstraintWidgetContainer:getHorizontalDimensionBehaviour	()Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   341: getstatic 151	android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour:FIXED	Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   344: if_acmpne +40 -> 384
    //   347: aload_0
    //   348: invokevirtual 299	android/support/constraint/solver/widgets/ConstraintWidgetContainer:getVerticalDimensionBehaviour	()Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   351: getstatic 151	android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour:FIXED	Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   354: if_acmpne +30 -> 384
    //   357: aload_0
    //   358: aload_0
    //   359: getfield 67	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mWidgetGroups	Ljava/util/List;
    //   362: iload 8
    //   364: invokeinterface 300 2 0
    //   369: checkcast 286	android/support/constraint/solver/widgets/ConstraintWidgetGroup
    //   372: invokevirtual 304	android/support/constraint/solver/widgets/ConstraintWidgetGroup:getWidgetsToSolve	()Ljava/util/List;
    //   375: checkcast 64	java/util/ArrayList
    //   378: putfield 128	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mChildren	Ljava/util/ArrayList;
    //   381: goto +27 -> 408
    //   384: aload_0
    //   385: aload_0
    //   386: getfield 67	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mWidgetGroups	Ljava/util/List;
    //   389: iload 8
    //   391: invokeinterface 300 2 0
    //   396: checkcast 286	android/support/constraint/solver/widgets/ConstraintWidgetGroup
    //   399: getfield 307	android/support/constraint/solver/widgets/ConstraintWidgetGroup:mConstrainedGroup	Ljava/util/List;
    //   402: checkcast 64	java/util/ArrayList
    //   405: putfield 128	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mChildren	Ljava/util/ArrayList;
    //   408: aload_0
    //   409: invokespecial 278	android/support/constraint/solver/widgets/ConstraintWidgetContainer:resetChains	()V
    //   412: aload_0
    //   413: getfield 128	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mChildren	Ljava/util/ArrayList;
    //   416: invokevirtual 132	java/util/ArrayList:size	()I
    //   419: istore 6
    //   421: iconst_0
    //   422: istore_3
    //   423: iload_3
    //   424: iload 6
    //   426: if_icmpge +39 -> 465
    //   429: aload_0
    //   430: getfield 128	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mChildren	Ljava/util/ArrayList;
    //   433: iload_3
    //   434: invokevirtual 136	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   437: checkcast 138	android/support/constraint/solver/widgets/ConstraintWidget
    //   440: astore 17
    //   442: aload 17
    //   444: instanceof 4
    //   447: ifeq +11 -> 458
    //   450: aload 17
    //   452: checkcast 4	android/support/constraint/solver/widgets/WidgetContainer
    //   455: invokevirtual 309	android/support/constraint/solver/widgets/WidgetContainer:layout	()V
    //   458: iload_3
    //   459: iconst_1
    //   460: iadd
    //   461: istore_3
    //   462: goto -39 -> 423
    //   465: iconst_1
    //   466: istore 14
    //   468: iconst_0
    //   469: istore 5
    //   471: iload 6
    //   473: istore_3
    //   474: iload 14
    //   476: ifeq +808 -> 1284
    //   479: iload 5
    //   481: iconst_1
    //   482: iadd
    //   483: istore 10
    //   485: aload_0
    //   486: getfield 52	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mSystem	Landroid/support/constraint/solver/LinearSystem;
    //   489: invokevirtual 312	android/support/constraint/solver/LinearSystem:reset	()V
    //   492: aload_0
    //   493: invokespecial 278	android/support/constraint/solver/widgets/ConstraintWidgetContainer:resetChains	()V
    //   496: aload_0
    //   497: aload_0
    //   498: getfield 52	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mSystem	Landroid/support/constraint/solver/LinearSystem;
    //   501: invokevirtual 315	android/support/constraint/solver/widgets/ConstraintWidgetContainer:createObjectVariables	(Landroid/support/constraint/solver/LinearSystem;)V
    //   504: iconst_0
    //   505: istore 6
    //   507: iload 6
    //   509: iload_3
    //   510: if_icmpge +41 -> 551
    //   513: iload_1
    //   514: istore 5
    //   516: aload_0
    //   517: getfield 128	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mChildren	Ljava/util/ArrayList;
    //   520: iload 6
    //   522: invokevirtual 136	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   525: checkcast 138	android/support/constraint/solver/widgets/ConstraintWidget
    //   528: astore 17
    //   530: aload 17
    //   532: aload_0
    //   533: getfield 52	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mSystem	Landroid/support/constraint/solver/LinearSystem;
    //   536: invokevirtual 316	android/support/constraint/solver/widgets/ConstraintWidget:createObjectVariables	(Landroid/support/constraint/solver/LinearSystem;)V
    //   539: iload 6
    //   541: iconst_1
    //   542: iadd
    //   543: istore 6
    //   545: iload 5
    //   547: istore_1
    //   548: goto -41 -> 507
    //   551: iload_1
    //   552: istore 6
    //   554: iload_2
    //   555: istore 5
    //   557: aload_0
    //   558: aload_0
    //   559: getfield 52	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mSystem	Landroid/support/constraint/solver/LinearSystem;
    //   562: invokevirtual 318	android/support/constraint/solver/widgets/ConstraintWidgetContainer:addChildrenToSolver	(Landroid/support/constraint/solver/LinearSystem;)Z
    //   565: istore 15
    //   567: iload 15
    //   569: ifeq +28 -> 597
    //   572: aload_0
    //   573: getfield 52	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mSystem	Landroid/support/constraint/solver/LinearSystem;
    //   576: invokevirtual 321	android/support/constraint/solver/LinearSystem:minimize	()V
    //   579: goto +18 -> 597
    //   582: astore 17
    //   584: iload 15
    //   586: istore 14
    //   588: iload 6
    //   590: istore_1
    //   591: iload 5
    //   593: istore_2
    //   594: goto +23 -> 617
    //   597: iload 15
    //   599: istore 14
    //   601: iload 6
    //   603: istore_1
    //   604: iload 5
    //   606: istore_2
    //   607: goto +56 -> 663
    //   610: astore 17
    //   612: goto +5 -> 617
    //   615: astore 17
    //   617: aload 17
    //   619: invokevirtual 324	java/lang/Exception:printStackTrace	()V
    //   622: getstatic 330	java/lang/System:out	Ljava/io/PrintStream;
    //   625: astore 21
    //   627: new 332	java/lang/StringBuilder
    //   630: dup
    //   631: invokespecial 333	java/lang/StringBuilder:<init>	()V
    //   634: astore 22
    //   636: aload 22
    //   638: ldc_w 335
    //   641: invokevirtual 339	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   644: pop
    //   645: aload 22
    //   647: aload 17
    //   649: invokevirtual 342	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   652: pop
    //   653: aload 21
    //   655: aload 22
    //   657: invokevirtual 345	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   660: invokevirtual 351	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   663: iload 14
    //   665: ifeq +17 -> 682
    //   668: aload_0
    //   669: aload_0
    //   670: getfield 52	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mSystem	Landroid/support/constraint/solver/LinearSystem;
    //   673: getstatic 355	android/support/constraint/solver/widgets/Optimizer:flags	[Z
    //   676: invokevirtual 359	android/support/constraint/solver/widgets/ConstraintWidgetContainer:updateChildrenFromSolver	(Landroid/support/constraint/solver/LinearSystem;[Z)V
    //   679: goto +113 -> 792
    //   682: aload_0
    //   683: aload_0
    //   684: getfield 52	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mSystem	Landroid/support/constraint/solver/LinearSystem;
    //   687: invokevirtual 362	android/support/constraint/solver/widgets/ConstraintWidgetContainer:updateFromSolver	(Landroid/support/constraint/solver/LinearSystem;)V
    //   690: iconst_0
    //   691: istore 5
    //   693: iload 5
    //   695: iload_3
    //   696: if_icmpge -17 -> 679
    //   699: aload_0
    //   700: getfield 128	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mChildren	Ljava/util/ArrayList;
    //   703: iload 5
    //   705: invokevirtual 136	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   708: checkcast 138	android/support/constraint/solver/widgets/ConstraintWidget
    //   711: astore 17
    //   713: aload 17
    //   715: getfield 142	android/support/constraint/solver/widgets/ConstraintWidget:mListDimensionBehaviors	[Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   718: iconst_0
    //   719: aaload
    //   720: getstatic 365	android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour:MATCH_CONSTRAINT	Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   723: if_acmpne +25 -> 748
    //   726: aload 17
    //   728: invokevirtual 366	android/support/constraint/solver/widgets/ConstraintWidget:getWidth	()I
    //   731: aload 17
    //   733: invokevirtual 369	android/support/constraint/solver/widgets/ConstraintWidget:getWrapWidth	()I
    //   736: if_icmpge +12 -> 748
    //   739: getstatic 355	android/support/constraint/solver/widgets/Optimizer:flags	[Z
    //   742: iconst_2
    //   743: iconst_1
    //   744: bastore
    //   745: goto -66 -> 679
    //   748: aload 17
    //   750: getfield 142	android/support/constraint/solver/widgets/ConstraintWidget:mListDimensionBehaviors	[Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   753: iconst_1
    //   754: aaload
    //   755: getstatic 365	android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour:MATCH_CONSTRAINT	Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   758: if_acmpne +25 -> 783
    //   761: aload 17
    //   763: invokevirtual 370	android/support/constraint/solver/widgets/ConstraintWidget:getHeight	()I
    //   766: aload 17
    //   768: invokevirtual 373	android/support/constraint/solver/widgets/ConstraintWidget:getWrapHeight	()I
    //   771: if_icmpge +12 -> 783
    //   774: getstatic 355	android/support/constraint/solver/widgets/Optimizer:flags	[Z
    //   777: iconst_2
    //   778: iconst_1
    //   779: bastore
    //   780: goto +12 -> 792
    //   783: iload 5
    //   785: iconst_1
    //   786: iadd
    //   787: istore 5
    //   789: goto -96 -> 693
    //   792: iload 7
    //   794: ifeq +224 -> 1018
    //   797: iload 10
    //   799: bipush 8
    //   801: if_icmpge +217 -> 1018
    //   804: getstatic 355	android/support/constraint/solver/widgets/Optimizer:flags	[Z
    //   807: iconst_2
    //   808: baload
    //   809: ifeq +209 -> 1018
    //   812: iconst_0
    //   813: istore 6
    //   815: iconst_0
    //   816: istore 9
    //   818: iconst_0
    //   819: istore 5
    //   821: iload 6
    //   823: iload_3
    //   824: if_icmpge +62 -> 886
    //   827: aload_0
    //   828: getfield 128	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mChildren	Ljava/util/ArrayList;
    //   831: iload 6
    //   833: invokevirtual 136	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   836: checkcast 138	android/support/constraint/solver/widgets/ConstraintWidget
    //   839: astore 17
    //   841: iload 9
    //   843: aload 17
    //   845: getfield 374	android/support/constraint/solver/widgets/ConstraintWidget:mX	I
    //   848: aload 17
    //   850: invokevirtual 366	android/support/constraint/solver/widgets/ConstraintWidget:getWidth	()I
    //   853: iadd
    //   854: invokestatic 225	java/lang/Math:max	(II)I
    //   857: istore 9
    //   859: iload 5
    //   861: aload 17
    //   863: getfield 375	android/support/constraint/solver/widgets/ConstraintWidget:mY	I
    //   866: aload 17
    //   868: invokevirtual 370	android/support/constraint/solver/widgets/ConstraintWidget:getHeight	()I
    //   871: iadd
    //   872: invokestatic 225	java/lang/Math:max	(II)I
    //   875: istore 5
    //   877: iload 6
    //   879: iconst_1
    //   880: iadd
    //   881: istore 6
    //   883: goto -62 -> 821
    //   886: iload_3
    //   887: istore 6
    //   889: aload_0
    //   890: getfield 378	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mMinWidth	I
    //   893: iload 9
    //   895: invokestatic 225	java/lang/Math:max	(II)I
    //   898: istore_3
    //   899: aload_0
    //   900: getfield 381	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mMinHeight	I
    //   903: iload 5
    //   905: invokestatic 225	java/lang/Math:max	(II)I
    //   908: istore 9
    //   910: aload 19
    //   912: getstatic 148	android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour:WRAP_CONTENT	Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   915: if_acmpne +34 -> 949
    //   918: aload_0
    //   919: invokevirtual 219	android/support/constraint/solver/widgets/ConstraintWidgetContainer:getWidth	()I
    //   922: iload_3
    //   923: if_icmpge +26 -> 949
    //   926: aload_0
    //   927: iload_3
    //   928: invokevirtual 384	android/support/constraint/solver/widgets/ConstraintWidgetContainer:setWidth	(I)V
    //   931: aload_0
    //   932: getfield 276	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mListDimensionBehaviors	[Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   935: iconst_0
    //   936: getstatic 148	android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour:WRAP_CONTENT	Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   939: aastore
    //   940: iconst_1
    //   941: istore 15
    //   943: iconst_1
    //   944: istore 5
    //   946: goto +9 -> 955
    //   949: iconst_0
    //   950: istore 15
    //   952: iload_1
    //   953: istore 5
    //   955: iload 15
    //   957: istore 14
    //   959: iload 5
    //   961: istore_1
    //   962: iload 6
    //   964: istore_3
    //   965: aload 18
    //   967: getstatic 148	android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour:WRAP_CONTENT	Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   970: if_acmpne +51 -> 1021
    //   973: iload 15
    //   975: istore 14
    //   977: iload 5
    //   979: istore_1
    //   980: iload 6
    //   982: istore_3
    //   983: aload_0
    //   984: invokevirtual 228	android/support/constraint/solver/widgets/ConstraintWidgetContainer:getHeight	()I
    //   987: iload 9
    //   989: if_icmpge +32 -> 1021
    //   992: aload_0
    //   993: iload 9
    //   995: invokevirtual 387	android/support/constraint/solver/widgets/ConstraintWidgetContainer:setHeight	(I)V
    //   998: aload_0
    //   999: getfield 276	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mListDimensionBehaviors	[Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   1002: iconst_1
    //   1003: getstatic 148	android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour:WRAP_CONTENT	Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   1006: aastore
    //   1007: iconst_1
    //   1008: istore 14
    //   1010: iconst_1
    //   1011: istore_1
    //   1012: iload 6
    //   1014: istore_3
    //   1015: goto +6 -> 1021
    //   1018: iconst_0
    //   1019: istore 14
    //   1021: aload_0
    //   1022: getfield 378	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mMinWidth	I
    //   1025: aload_0
    //   1026: invokevirtual 219	android/support/constraint/solver/widgets/ConstraintWidgetContainer:getWidth	()I
    //   1029: invokestatic 225	java/lang/Math:max	(II)I
    //   1032: istore 5
    //   1034: iload 5
    //   1036: aload_0
    //   1037: invokevirtual 219	android/support/constraint/solver/widgets/ConstraintWidgetContainer:getWidth	()I
    //   1040: if_icmple +23 -> 1063
    //   1043: aload_0
    //   1044: iload 5
    //   1046: invokevirtual 384	android/support/constraint/solver/widgets/ConstraintWidgetContainer:setWidth	(I)V
    //   1049: aload_0
    //   1050: getfield 276	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mListDimensionBehaviors	[Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   1053: iconst_0
    //   1054: getstatic 151	android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour:FIXED	Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   1057: aastore
    //   1058: iconst_1
    //   1059: istore 14
    //   1061: iconst_1
    //   1062: istore_1
    //   1063: aload_0
    //   1064: getfield 381	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mMinHeight	I
    //   1067: aload_0
    //   1068: invokevirtual 228	android/support/constraint/solver/widgets/ConstraintWidgetContainer:getHeight	()I
    //   1071: invokestatic 225	java/lang/Math:max	(II)I
    //   1074: istore 5
    //   1076: iload 5
    //   1078: aload_0
    //   1079: invokevirtual 228	android/support/constraint/solver/widgets/ConstraintWidgetContainer:getHeight	()I
    //   1082: if_icmple +26 -> 1108
    //   1085: aload_0
    //   1086: iload 5
    //   1088: invokevirtual 387	android/support/constraint/solver/widgets/ConstraintWidgetContainer:setHeight	(I)V
    //   1091: aload_0
    //   1092: getfield 276	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mListDimensionBehaviors	[Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   1095: iconst_1
    //   1096: getstatic 151	android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour:FIXED	Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   1099: aastore
    //   1100: iconst_1
    //   1101: istore 14
    //   1103: iconst_1
    //   1104: istore_1
    //   1105: goto +3 -> 1108
    //   1108: iload 14
    //   1110: istore 16
    //   1112: iload_1
    //   1113: istore 6
    //   1115: iload_1
    //   1116: ifne +154 -> 1270
    //   1119: iload 14
    //   1121: istore 15
    //   1123: iload_1
    //   1124: istore 5
    //   1126: aload_0
    //   1127: getfield 276	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mListDimensionBehaviors	[Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   1130: iconst_0
    //   1131: aaload
    //   1132: getstatic 148	android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour:WRAP_CONTENT	Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   1135: if_acmpne +57 -> 1192
    //   1138: iload 14
    //   1140: istore 15
    //   1142: iload_1
    //   1143: istore 5
    //   1145: iload 12
    //   1147: ifle +45 -> 1192
    //   1150: iload 14
    //   1152: istore 15
    //   1154: iload_1
    //   1155: istore 5
    //   1157: aload_0
    //   1158: invokevirtual 219	android/support/constraint/solver/widgets/ConstraintWidgetContainer:getWidth	()I
    //   1161: iload 12
    //   1163: if_icmple +29 -> 1192
    //   1166: aload_0
    //   1167: iconst_1
    //   1168: putfield 83	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mWidthMeasuredTooSmall	Z
    //   1171: aload_0
    //   1172: getfield 276	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mListDimensionBehaviors	[Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   1175: iconst_0
    //   1176: getstatic 151	android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour:FIXED	Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   1179: aastore
    //   1180: aload_0
    //   1181: iload 12
    //   1183: invokevirtual 384	android/support/constraint/solver/widgets/ConstraintWidgetContainer:setWidth	(I)V
    //   1186: iconst_1
    //   1187: istore 15
    //   1189: iconst_1
    //   1190: istore 5
    //   1192: iload 15
    //   1194: istore 16
    //   1196: iload 5
    //   1198: istore 6
    //   1200: aload_0
    //   1201: getfield 276	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mListDimensionBehaviors	[Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   1204: iconst_1
    //   1205: aaload
    //   1206: getstatic 148	android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour:WRAP_CONTENT	Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   1209: if_acmpne +61 -> 1270
    //   1212: iload 15
    //   1214: istore 16
    //   1216: iload 5
    //   1218: istore 6
    //   1220: iload 13
    //   1222: ifle +48 -> 1270
    //   1225: iload 15
    //   1227: istore 16
    //   1229: iload 5
    //   1231: istore 6
    //   1233: aload_0
    //   1234: invokevirtual 228	android/support/constraint/solver/widgets/ConstraintWidgetContainer:getHeight	()I
    //   1237: iload 13
    //   1239: if_icmple +31 -> 1270
    //   1242: aload_0
    //   1243: iconst_1
    //   1244: putfield 85	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mHeightMeasuredTooSmall	Z
    //   1247: aload_0
    //   1248: getfield 276	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mListDimensionBehaviors	[Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   1251: iconst_1
    //   1252: getstatic 151	android/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour:FIXED	Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   1255: aastore
    //   1256: aload_0
    //   1257: iload 13
    //   1259: invokevirtual 387	android/support/constraint/solver/widgets/ConstraintWidgetContainer:setHeight	(I)V
    //   1262: iconst_1
    //   1263: istore 14
    //   1265: iconst_1
    //   1266: istore_1
    //   1267: goto +10 -> 1277
    //   1270: iload 16
    //   1272: istore 14
    //   1274: iload 6
    //   1276: istore_1
    //   1277: iload 10
    //   1279: istore 5
    //   1281: goto -807 -> 474
    //   1284: aload_0
    //   1285: getfield 67	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mWidgetGroups	Ljava/util/List;
    //   1288: iload 8
    //   1290: invokeinterface 300 2 0
    //   1295: checkcast 286	android/support/constraint/solver/widgets/ConstraintWidgetGroup
    //   1298: invokevirtual 390	android/support/constraint/solver/widgets/ConstraintWidgetGroup:updateUnresolvedWidgets	()V
    //   1301: iload_1
    //   1302: istore_3
    //   1303: iload 4
    //   1305: istore_1
    //   1306: iload 8
    //   1308: iconst_1
    //   1309: iadd
    //   1310: istore 8
    //   1312: iload_1
    //   1313: istore 4
    //   1315: iload_3
    //   1316: istore_1
    //   1317: goto -1030 -> 287
    //   1320: aload_0
    //   1321: aload 20
    //   1323: checkcast 64	java/util/ArrayList
    //   1326: putfield 128	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mChildren	Ljava/util/ArrayList;
    //   1329: aload_0
    //   1330: getfield 232	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mParent	Landroid/support/constraint/solver/widgets/ConstraintWidget;
    //   1333: ifnull +68 -> 1401
    //   1336: aload_0
    //   1337: getfield 378	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mMinWidth	I
    //   1340: aload_0
    //   1341: invokevirtual 219	android/support/constraint/solver/widgets/ConstraintWidgetContainer:getWidth	()I
    //   1344: invokestatic 225	java/lang/Math:max	(II)I
    //   1347: istore_2
    //   1348: aload_0
    //   1349: getfield 381	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mMinHeight	I
    //   1352: aload_0
    //   1353: invokevirtual 228	android/support/constraint/solver/widgets/ConstraintWidgetContainer:getHeight	()I
    //   1356: invokestatic 225	java/lang/Math:max	(II)I
    //   1359: istore_3
    //   1360: aload_0
    //   1361: getfield 234	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mSnapshot	Landroid/support/constraint/solver/widgets/Snapshot;
    //   1364: aload_0
    //   1365: invokevirtual 393	android/support/constraint/solver/widgets/Snapshot:applyTo	(Landroid/support/constraint/solver/widgets/ConstraintWidget;)V
    //   1368: aload_0
    //   1369: iload_2
    //   1370: aload_0
    //   1371: getfield 243	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mPaddingLeft	I
    //   1374: iadd
    //   1375: aload_0
    //   1376: getfield 395	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mPaddingRight	I
    //   1379: iadd
    //   1380: invokevirtual 384	android/support/constraint/solver/widgets/ConstraintWidgetContainer:setWidth	(I)V
    //   1383: aload_0
    //   1384: iload_3
    //   1385: aload_0
    //   1386: getfield 248	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mPaddingTop	I
    //   1389: iadd
    //   1390: aload_0
    //   1391: getfield 397	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mPaddingBottom	I
    //   1394: iadd
    //   1395: invokevirtual 387	android/support/constraint/solver/widgets/ConstraintWidgetContainer:setHeight	(I)V
    //   1398: goto +15 -> 1413
    //   1401: aload_0
    //   1402: iload 11
    //   1404: putfield 213	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mX	I
    //   1407: aload_0
    //   1408: iload 4
    //   1410: putfield 216	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mY	I
    //   1413: iload_1
    //   1414: ifeq +19 -> 1433
    //   1417: aload_0
    //   1418: getfield 276	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mListDimensionBehaviors	[Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   1421: iconst_0
    //   1422: aload 19
    //   1424: aastore
    //   1425: aload_0
    //   1426: getfield 276	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mListDimensionBehaviors	[Landroid/support/constraint/solver/widgets/ConstraintWidget$DimensionBehaviour;
    //   1429: iconst_1
    //   1430: aload 18
    //   1432: aastore
    //   1433: aload_0
    //   1434: aload_0
    //   1435: getfield 52	android/support/constraint/solver/widgets/ConstraintWidgetContainer:mSystem	Landroid/support/constraint/solver/LinearSystem;
    //   1438: invokevirtual 258	android/support/constraint/solver/LinearSystem:getCache	()Landroid/support/constraint/solver/Cache;
    //   1441: invokevirtual 262	android/support/constraint/solver/widgets/ConstraintWidgetContainer:resetSolverVariables	(Landroid/support/constraint/solver/Cache;)V
    //   1444: aload_0
    //   1445: aload_0
    //   1446: invokevirtual 401	android/support/constraint/solver/widgets/ConstraintWidgetContainer:getRootConstraintContainer	()Landroid/support/constraint/solver/widgets/ConstraintWidgetContainer;
    //   1449: if_acmpne +7 -> 1456
    //   1452: aload_0
    //   1453: invokevirtual 404	android/support/constraint/solver/widgets/ConstraintWidgetContainer:updateDrawPosition	()V
    //   1456: return
    //   1457: astore 17
    //   1459: iload 5
    //   1461: istore_1
    //   1462: goto -845 -> 617
    //
    // Exception table:
    //   from	to	target	type
    //   572	579	582	java/lang/Exception
    //   530	539	610	java/lang/Exception
    //   557	567	610	java/lang/Exception
    //   485	504	615	java/lang/Exception
    //   516	530	1457	java/lang/Exception
  }

  public void optimize()
  {
    if (!optimizeFor(8))
      analyze(this.mOptimizationLevel);
    solveGraph();
  }

  public boolean optimizeFor(int paramInt)
  {
    return (this.mOptimizationLevel & paramInt) == paramInt;
  }

  public void optimizeForDimensions(int paramInt1, int paramInt2)
  {
    if ((this.mListDimensionBehaviors[0] != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) && (this.mResolutionWidth != null))
      this.mResolutionWidth.resolve(paramInt1);
    if ((this.mListDimensionBehaviors[1] != ConstraintWidget.DimensionBehaviour.WRAP_CONTENT) && (this.mResolutionHeight != null))
      this.mResolutionHeight.resolve(paramInt2);
  }

  public void optimizeReset()
  {
    int j = this.mChildren.size();
    resetResolutionNodes();
    int i = 0;
    while (i < j)
    {
      ((ConstraintWidget)this.mChildren.get(i)).resetResolutionNodes();
      i += 1;
    }
  }

  public void preOptimize()
  {
    optimizeReset();
    analyze(this.mOptimizationLevel);
  }

  public void reset()
  {
    this.mSystem.reset();
    this.mPaddingLeft = 0;
    this.mPaddingRight = 0;
    this.mPaddingTop = 0;
    this.mPaddingBottom = 0;
    this.mWidgetGroups.clear();
    this.mSkipSolver = false;
    super.reset();
  }

  public void resetGraph()
  {
    ResolutionAnchor localResolutionAnchor1 = getAnchor(ConstraintAnchor.Type.LEFT).getResolutionNode();
    ResolutionAnchor localResolutionAnchor2 = getAnchor(ConstraintAnchor.Type.TOP).getResolutionNode();
    localResolutionAnchor1.invalidateAnchors();
    localResolutionAnchor2.invalidateAnchors();
    localResolutionAnchor1.resolve(null, 0.0F);
    localResolutionAnchor2.resolve(null, 0.0F);
  }

  public void setOptimizationLevel(int paramInt)
  {
    this.mOptimizationLevel = paramInt;
  }

  public void setPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mPaddingLeft = paramInt1;
    this.mPaddingTop = paramInt2;
    this.mPaddingRight = paramInt3;
    this.mPaddingBottom = paramInt4;
  }

  public void setRtl(boolean paramBoolean)
  {
    this.mIsRtl = paramBoolean;
  }

  public void solveGraph()
  {
    ResolutionAnchor localResolutionAnchor1 = getAnchor(ConstraintAnchor.Type.LEFT).getResolutionNode();
    ResolutionAnchor localResolutionAnchor2 = getAnchor(ConstraintAnchor.Type.TOP).getResolutionNode();
    localResolutionAnchor1.resolve(null, 0.0F);
    localResolutionAnchor2.resolve(null, 0.0F);
  }

  public void updateChildrenFromSolver(LinearSystem paramLinearSystem, boolean[] paramArrayOfBoolean)
  {
    paramArrayOfBoolean[2] = false;
    updateFromSolver(paramLinearSystem);
    int j = this.mChildren.size();
    int i = 0;
    while (i < j)
    {
      ConstraintWidget localConstraintWidget = (ConstraintWidget)this.mChildren.get(i);
      localConstraintWidget.updateFromSolver(paramLinearSystem);
      if ((localConstraintWidget.mListDimensionBehaviors[0] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) && (localConstraintWidget.getWidth() < localConstraintWidget.getWrapWidth()))
        paramArrayOfBoolean[2] = true;
      if ((localConstraintWidget.mListDimensionBehaviors[1] == ConstraintWidget.DimensionBehaviour.MATCH_CONSTRAINT) && (localConstraintWidget.getHeight() < localConstraintWidget.getWrapHeight()))
        paramArrayOfBoolean[2] = true;
      i += 1;
    }
  }
}