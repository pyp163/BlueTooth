package android.support.v4.print;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.CancellationSignal.OnCancelListener;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintAttributes.Builder;
import android.print.PrintAttributes.Margins;
import android.print.PrintAttributes.MediaSize;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentAdapter.LayoutResultCallback;
import android.print.PrintDocumentAdapter.WriteResultCallback;
import android.print.PrintDocumentInfo.Builder;
import android.print.PrintManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.io.FileNotFoundException;

public final class PrintHelper
{

  @SuppressLint({"InlinedApi"})
  public static final int COLOR_MODE_COLOR = 2;

  @SuppressLint({"InlinedApi"})
  public static final int COLOR_MODE_MONOCHROME = 1;
  static final boolean IS_MIN_MARGINS_HANDLING_CORRECT = bool1;
  private static final String LOG_TAG = "PrintHelper";
  private static final int MAX_PRINT_SIZE = 3500;
  public static final int ORIENTATION_LANDSCAPE = 1;
  public static final int ORIENTATION_PORTRAIT = 2;
  static final boolean PRINT_ACTIVITY_RESPECTS_ORIENTATION;
  public static final int SCALE_MODE_FILL = 2;
  public static final int SCALE_MODE_FIT = 1;
  int mColorMode = 2;
  final Context mContext;
  BitmapFactory.Options mDecodeOptions = null;
  final Object mLock = new Object();
  int mOrientation = 1;
  int mScaleMode = 2;

  static
  {
    int i = Build.VERSION.SDK_INT;
    boolean bool2 = true;
    boolean bool1;
    if ((i >= 20) && (Build.VERSION.SDK_INT <= 23))
      bool1 = false;
    else
      bool1 = true;
    PRINT_ACTIVITY_RESPECTS_ORIENTATION = bool1;
    if (Build.VERSION.SDK_INT != 23)
      bool1 = bool2;
    else
      bool1 = false;
  }

  public PrintHelper(@NonNull Context paramContext)
  {
    this.mContext = paramContext;
  }

  static Bitmap convertBitmapForColorMode(Bitmap paramBitmap, int paramInt)
  {
    if (paramInt != 1)
      return paramBitmap;
    Bitmap localBitmap = Bitmap.createBitmap(paramBitmap.getWidth(), paramBitmap.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    Paint localPaint = new Paint();
    ColorMatrix localColorMatrix = new ColorMatrix();
    localColorMatrix.setSaturation(0.0F);
    localPaint.setColorFilter(new ColorMatrixColorFilter(localColorMatrix));
    localCanvas.drawBitmap(paramBitmap, 0.0F, 0.0F, localPaint);
    localCanvas.setBitmap(null);
    return localBitmap;
  }

  @RequiresApi(19)
  private static PrintAttributes.Builder copyAttributes(PrintAttributes paramPrintAttributes)
  {
    PrintAttributes.Builder localBuilder = new PrintAttributes.Builder().setMediaSize(paramPrintAttributes.getMediaSize()).setResolution(paramPrintAttributes.getResolution()).setMinMargins(paramPrintAttributes.getMinMargins());
    if (paramPrintAttributes.getColorMode() != 0)
      localBuilder.setColorMode(paramPrintAttributes.getColorMode());
    if ((Build.VERSION.SDK_INT >= 23) && (paramPrintAttributes.getDuplexMode() != 0))
      localBuilder.setDuplexMode(paramPrintAttributes.getDuplexMode());
    return localBuilder;
  }

  static Matrix getMatrix(int paramInt1, int paramInt2, RectF paramRectF, int paramInt3)
  {
    Matrix localMatrix = new Matrix();
    float f1 = paramRectF.width();
    float f2 = paramInt1;
    f1 /= f2;
    if (paramInt3 == 2)
      f1 = Math.max(f1, paramRectF.height() / paramInt2);
    else
      f1 = Math.min(f1, paramRectF.height() / paramInt2);
    localMatrix.postScale(f1, f1);
    localMatrix.postTranslate((paramRectF.width() - f2 * f1) / 2.0F, (paramRectF.height() - paramInt2 * f1) / 2.0F);
    return localMatrix;
  }

  static boolean isPortrait(Bitmap paramBitmap)
  {
    return paramBitmap.getWidth() <= paramBitmap.getHeight();
  }

  // ERROR //
  private Bitmap loadBitmap(Uri paramUri, BitmapFactory.Options paramOptions)
    throws FileNotFoundException
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +90 -> 91
    //   4: aload_0
    //   5: getfield 78	android/support/v4/print/PrintHelper:mContext	Landroid/content/Context;
    //   8: ifnonnull +6 -> 14
    //   11: goto +80 -> 91
    //   14: aconst_null
    //   15: astore_3
    //   16: aload_0
    //   17: getfield 78	android/support/v4/print/PrintHelper:mContext	Landroid/content/Context;
    //   20: invokevirtual 222	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   23: aload_1
    //   24: invokevirtual 228	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
    //   27: astore_1
    //   28: aload_1
    //   29: aconst_null
    //   30: aload_2
    //   31: invokestatic 234	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   34: astore_2
    //   35: aload_1
    //   36: ifnull +19 -> 55
    //   39: aload_1
    //   40: invokevirtual 239	java/io/InputStream:close	()V
    //   43: aload_2
    //   44: areturn
    //   45: astore_1
    //   46: ldc 33
    //   48: ldc 241
    //   50: aload_1
    //   51: invokestatic 247	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   54: pop
    //   55: aload_2
    //   56: areturn
    //   57: astore_3
    //   58: aload_1
    //   59: astore_2
    //   60: aload_3
    //   61: astore_1
    //   62: goto +6 -> 68
    //   65: astore_1
    //   66: aload_3
    //   67: astore_2
    //   68: aload_2
    //   69: ifnull +20 -> 89
    //   72: aload_2
    //   73: invokevirtual 239	java/io/InputStream:close	()V
    //   76: goto +13 -> 89
    //   79: astore_2
    //   80: ldc 33
    //   82: ldc 241
    //   84: aload_2
    //   85: invokestatic 247	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   88: pop
    //   89: aload_1
    //   90: athrow
    //   91: new 249	java/lang/IllegalArgumentException
    //   94: dup
    //   95: ldc 251
    //   97: invokespecial 254	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   100: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   39	43	45	java/io/IOException
    //   28	35	57	finally
    //   16	28	65	finally
    //   72	76	79	java/io/IOException
  }

  public static boolean systemSupportsPrint()
  {
    return Build.VERSION.SDK_INT >= 19;
  }

  public int getColorMode()
  {
    return this.mColorMode;
  }

  public int getOrientation()
  {
    if ((Build.VERSION.SDK_INT >= 19) && (this.mOrientation == 0))
      return 1;
    return this.mOrientation;
  }

  public int getScaleMode()
  {
    return this.mScaleMode;
  }

  // ERROR //
  Bitmap loadConstrainedBitmap(Uri paramUri)
    throws FileNotFoundException
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +222 -> 223
    //   4: aload_0
    //   5: getfield 78	android/support/v4/print/PrintHelper:mContext	Landroid/content/Context;
    //   8: ifnonnull +6 -> 14
    //   11: goto +212 -> 223
    //   14: new 263	android/graphics/BitmapFactory$Options
    //   17: dup
    //   18: invokespecial 264	android/graphics/BitmapFactory$Options:<init>	()V
    //   21: astore 6
    //   23: aload 6
    //   25: iconst_1
    //   26: putfield 267	android/graphics/BitmapFactory$Options:inJustDecodeBounds	Z
    //   29: aload_0
    //   30: aload_1
    //   31: aload 6
    //   33: invokespecial 269	android/support/v4/print/PrintHelper:loadBitmap	(Landroid/net/Uri;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   36: pop
    //   37: aload 6
    //   39: getfield 272	android/graphics/BitmapFactory$Options:outWidth	I
    //   42: istore 4
    //   44: aload 6
    //   46: getfield 275	android/graphics/BitmapFactory$Options:outHeight	I
    //   49: istore 5
    //   51: iload 4
    //   53: ifle +168 -> 221
    //   56: iload 5
    //   58: ifgt +5 -> 63
    //   61: aconst_null
    //   62: areturn
    //   63: iload 4
    //   65: iload 5
    //   67: invokestatic 278	java/lang/Math:max	(II)I
    //   70: istore_3
    //   71: iconst_1
    //   72: istore_2
    //   73: iload_3
    //   74: sipush 3500
    //   77: if_icmple +14 -> 91
    //   80: iload_3
    //   81: iconst_1
    //   82: iushr
    //   83: istore_3
    //   84: iload_2
    //   85: iconst_1
    //   86: ishl
    //   87: istore_2
    //   88: goto -15 -> 73
    //   91: iload_2
    //   92: ifle +127 -> 219
    //   95: iload 4
    //   97: iload 5
    //   99: invokestatic 280	java/lang/Math:min	(II)I
    //   102: iload_2
    //   103: idiv
    //   104: ifgt +5 -> 109
    //   107: aconst_null
    //   108: areturn
    //   109: aload_0
    //   110: getfield 70	android/support/v4/print/PrintHelper:mLock	Ljava/lang/Object;
    //   113: astore 6
    //   115: aload 6
    //   117: monitorenter
    //   118: aload_0
    //   119: new 263	android/graphics/BitmapFactory$Options
    //   122: dup
    //   123: invokespecial 264	android/graphics/BitmapFactory$Options:<init>	()V
    //   126: putfield 68	android/support/v4/print/PrintHelper:mDecodeOptions	Landroid/graphics/BitmapFactory$Options;
    //   129: aload_0
    //   130: getfield 68	android/support/v4/print/PrintHelper:mDecodeOptions	Landroid/graphics/BitmapFactory$Options;
    //   133: iconst_1
    //   134: putfield 283	android/graphics/BitmapFactory$Options:inMutable	Z
    //   137: aload_0
    //   138: getfield 68	android/support/v4/print/PrintHelper:mDecodeOptions	Landroid/graphics/BitmapFactory$Options;
    //   141: iload_2
    //   142: putfield 286	android/graphics/BitmapFactory$Options:inSampleSize	I
    //   145: aload_0
    //   146: getfield 68	android/support/v4/print/PrintHelper:mDecodeOptions	Landroid/graphics/BitmapFactory$Options;
    //   149: astore 7
    //   151: aload 6
    //   153: monitorexit
    //   154: aload_0
    //   155: aload_1
    //   156: aload 7
    //   158: invokespecial 269	android/support/v4/print/PrintHelper:loadBitmap	(Landroid/net/Uri;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
    //   161: astore 6
    //   163: aload_0
    //   164: getfield 70	android/support/v4/print/PrintHelper:mLock	Ljava/lang/Object;
    //   167: astore_1
    //   168: aload_1
    //   169: monitorenter
    //   170: aload_0
    //   171: aconst_null
    //   172: putfield 68	android/support/v4/print/PrintHelper:mDecodeOptions	Landroid/graphics/BitmapFactory$Options;
    //   175: aload_1
    //   176: monitorexit
    //   177: aload 6
    //   179: areturn
    //   180: astore 6
    //   182: aload_1
    //   183: monitorexit
    //   184: aload 6
    //   186: athrow
    //   187: astore 6
    //   189: aload_0
    //   190: getfield 70	android/support/v4/print/PrintHelper:mLock	Ljava/lang/Object;
    //   193: astore_1
    //   194: aload_1
    //   195: monitorenter
    //   196: aload_0
    //   197: aconst_null
    //   198: putfield 68	android/support/v4/print/PrintHelper:mDecodeOptions	Landroid/graphics/BitmapFactory$Options;
    //   201: aload_1
    //   202: monitorexit
    //   203: aload 6
    //   205: athrow
    //   206: astore 6
    //   208: aload_1
    //   209: monitorexit
    //   210: aload 6
    //   212: athrow
    //   213: astore_1
    //   214: aload 6
    //   216: monitorexit
    //   217: aload_1
    //   218: athrow
    //   219: aconst_null
    //   220: areturn
    //   221: aconst_null
    //   222: areturn
    //   223: new 249	java/lang/IllegalArgumentException
    //   226: dup
    //   227: ldc_w 288
    //   230: invokespecial 254	java/lang/IllegalArgumentException:<init>	(Ljava/lang/String;)V
    //   233: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   170	177	180	finally
    //   182	184	180	finally
    //   154	163	187	finally
    //   196	203	206	finally
    //   208	210	206	finally
    //   118	154	213	finally
    //   214	217	213	finally
  }

  public void printBitmap(@NonNull String paramString, @NonNull Bitmap paramBitmap)
  {
    printBitmap(paramString, paramBitmap, null);
  }

  public void printBitmap(@NonNull String paramString, @NonNull Bitmap paramBitmap, @Nullable OnPrintFinishCallback paramOnPrintFinishCallback)
  {
    if (Build.VERSION.SDK_INT >= 19)
    {
      if (paramBitmap == null)
        return;
      PrintManager localPrintManager = (PrintManager)this.mContext.getSystemService("print");
      if (isPortrait(paramBitmap))
        localObject = PrintAttributes.MediaSize.UNKNOWN_PORTRAIT;
      else
        localObject = PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE;
      Object localObject = new PrintAttributes.Builder().setMediaSize((PrintAttributes.MediaSize)localObject).setColorMode(this.mColorMode).build();
      localPrintManager.print(paramString, new PrintBitmapAdapter(paramString, this.mScaleMode, paramBitmap, paramOnPrintFinishCallback), (PrintAttributes)localObject);
      return;
    }
  }

  public void printBitmap(@NonNull String paramString, @NonNull Uri paramUri)
    throws FileNotFoundException
  {
    printBitmap(paramString, paramUri, null);
  }

  public void printBitmap(@NonNull String paramString, @NonNull Uri paramUri, @Nullable OnPrintFinishCallback paramOnPrintFinishCallback)
    throws FileNotFoundException
  {
    if (Build.VERSION.SDK_INT < 19)
      return;
    paramUri = new PrintUriAdapter(paramString, paramUri, paramOnPrintFinishCallback, this.mScaleMode);
    paramOnPrintFinishCallback = (PrintManager)this.mContext.getSystemService("print");
    PrintAttributes.Builder localBuilder = new PrintAttributes.Builder();
    localBuilder.setColorMode(this.mColorMode);
    if ((this.mOrientation != 1) && (this.mOrientation != 0))
    {
      if (this.mOrientation == 2)
        localBuilder.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_PORTRAIT);
    }
    else
      localBuilder.setMediaSize(PrintAttributes.MediaSize.UNKNOWN_LANDSCAPE);
    paramOnPrintFinishCallback.print(paramString, paramUri, localBuilder.build());
  }

  public void setColorMode(int paramInt)
  {
    this.mColorMode = paramInt;
  }

  public void setOrientation(int paramInt)
  {
    this.mOrientation = paramInt;
  }

  public void setScaleMode(int paramInt)
  {
    this.mScaleMode = paramInt;
  }

  @RequiresApi(19)
  void writeBitmap(final PrintAttributes paramPrintAttributes, final int paramInt, final Bitmap paramBitmap, final ParcelFileDescriptor paramParcelFileDescriptor, final CancellationSignal paramCancellationSignal, final PrintDocumentAdapter.WriteResultCallback paramWriteResultCallback)
  {
    final PrintAttributes localPrintAttributes;
    if (IS_MIN_MARGINS_HANDLING_CORRECT)
      localPrintAttributes = paramPrintAttributes;
    else
      localPrintAttributes = copyAttributes(paramPrintAttributes).setMinMargins(new PrintAttributes.Margins(0, 0, 0, 0)).build();
    new AsyncTask()
    {
      // ERROR //
      protected Throwable doInBackground(Void[] paramAnonymousArrayOfVoid)
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 31	android/support/v4/print/PrintHelper$1:val$cancellationSignal	Landroid/os/CancellationSignal;
        //   4: invokevirtual 64	android/os/CancellationSignal:isCanceled	()Z
        //   7: ifeq +5 -> 12
        //   10: aconst_null
        //   11: areturn
        //   12: new 66	android/print/pdf/PrintedPdfDocument
        //   15: dup
        //   16: aload_0
        //   17: getfield 29	android/support/v4/print/PrintHelper$1:this$0	Landroid/support/v4/print/PrintHelper;
        //   20: getfield 70	android/support/v4/print/PrintHelper:mContext	Landroid/content/Context;
        //   23: aload_0
        //   24: getfield 33	android/support/v4/print/PrintHelper$1:val$pdfAttributes	Landroid/print/PrintAttributes;
        //   27: invokespecial 73	android/print/pdf/PrintedPdfDocument:<init>	(Landroid/content/Context;Landroid/print/PrintAttributes;)V
        //   30: astore 4
        //   32: aload_0
        //   33: getfield 35	android/support/v4/print/PrintHelper$1:val$bitmap	Landroid/graphics/Bitmap;
        //   36: aload_0
        //   37: getfield 33	android/support/v4/print/PrintHelper$1:val$pdfAttributes	Landroid/print/PrintAttributes;
        //   40: invokevirtual 79	android/print/PrintAttributes:getColorMode	()I
        //   43: invokestatic 83	android/support/v4/print/PrintHelper:convertBitmapForColorMode	(Landroid/graphics/Bitmap;I)Landroid/graphics/Bitmap;
        //   46: astore_3
        //   47: aload_0
        //   48: getfield 31	android/support/v4/print/PrintHelper$1:val$cancellationSignal	Landroid/os/CancellationSignal;
        //   51: invokevirtual 64	android/os/CancellationSignal:isCanceled	()Z
        //   54: istore_2
        //   55: iload_2
        //   56: ifeq +5 -> 61
        //   59: aconst_null
        //   60: areturn
        //   61: aload 4
        //   63: iconst_1
        //   64: invokevirtual 87	android/print/pdf/PrintedPdfDocument:startPage	(I)Landroid/graphics/pdf/PdfDocument$Page;
        //   67: astore 5
        //   69: getstatic 91	android/support/v4/print/PrintHelper:IS_MIN_MARGINS_HANDLING_CORRECT	Z
        //   72: ifeq +22 -> 94
        //   75: new 93	android/graphics/RectF
        //   78: dup
        //   79: aload 5
        //   81: invokevirtual 99	android/graphics/pdf/PdfDocument$Page:getInfo	()Landroid/graphics/pdf/PdfDocument$PageInfo;
        //   84: invokevirtual 105	android/graphics/pdf/PdfDocument$PageInfo:getContentRect	()Landroid/graphics/Rect;
        //   87: invokespecial 108	android/graphics/RectF:<init>	(Landroid/graphics/Rect;)V
        //   90: astore_1
        //   91: goto +59 -> 150
        //   94: new 66	android/print/pdf/PrintedPdfDocument
        //   97: dup
        //   98: aload_0
        //   99: getfield 29	android/support/v4/print/PrintHelper$1:this$0	Landroid/support/v4/print/PrintHelper;
        //   102: getfield 70	android/support/v4/print/PrintHelper:mContext	Landroid/content/Context;
        //   105: aload_0
        //   106: getfield 37	android/support/v4/print/PrintHelper$1:val$attributes	Landroid/print/PrintAttributes;
        //   109: invokespecial 73	android/print/pdf/PrintedPdfDocument:<init>	(Landroid/content/Context;Landroid/print/PrintAttributes;)V
        //   112: astore 6
        //   114: aload 6
        //   116: iconst_1
        //   117: invokevirtual 87	android/print/pdf/PrintedPdfDocument:startPage	(I)Landroid/graphics/pdf/PdfDocument$Page;
        //   120: astore 7
        //   122: new 93	android/graphics/RectF
        //   125: dup
        //   126: aload 7
        //   128: invokevirtual 99	android/graphics/pdf/PdfDocument$Page:getInfo	()Landroid/graphics/pdf/PdfDocument$PageInfo;
        //   131: invokevirtual 105	android/graphics/pdf/PdfDocument$PageInfo:getContentRect	()Landroid/graphics/Rect;
        //   134: invokespecial 108	android/graphics/RectF:<init>	(Landroid/graphics/Rect;)V
        //   137: astore_1
        //   138: aload 6
        //   140: aload 7
        //   142: invokevirtual 112	android/print/pdf/PrintedPdfDocument:finishPage	(Landroid/graphics/pdf/PdfDocument$Page;)V
        //   145: aload 6
        //   147: invokevirtual 115	android/print/pdf/PrintedPdfDocument:close	()V
        //   150: aload_3
        //   151: invokevirtual 120	android/graphics/Bitmap:getWidth	()I
        //   154: aload_3
        //   155: invokevirtual 123	android/graphics/Bitmap:getHeight	()I
        //   158: aload_1
        //   159: aload_0
        //   160: getfield 39	android/support/v4/print/PrintHelper$1:val$fittingMode	I
        //   163: invokestatic 127	android/support/v4/print/PrintHelper:getMatrix	(IILandroid/graphics/RectF;I)Landroid/graphics/Matrix;
        //   166: astore 6
        //   168: getstatic 91	android/support/v4/print/PrintHelper:IS_MIN_MARGINS_HANDLING_CORRECT	Z
        //   171: ifeq +6 -> 177
        //   174: goto +27 -> 201
        //   177: aload 6
        //   179: aload_1
        //   180: getfield 131	android/graphics/RectF:left	F
        //   183: aload_1
        //   184: getfield 134	android/graphics/RectF:top	F
        //   187: invokevirtual 140	android/graphics/Matrix:postTranslate	(FF)Z
        //   190: pop
        //   191: aload 5
        //   193: invokevirtual 144	android/graphics/pdf/PdfDocument$Page:getCanvas	()Landroid/graphics/Canvas;
        //   196: aload_1
        //   197: invokevirtual 150	android/graphics/Canvas:clipRect	(Landroid/graphics/RectF;)Z
        //   200: pop
        //   201: aload 5
        //   203: invokevirtual 144	android/graphics/pdf/PdfDocument$Page:getCanvas	()Landroid/graphics/Canvas;
        //   206: aload_3
        //   207: aload 6
        //   209: aconst_null
        //   210: invokevirtual 154	android/graphics/Canvas:drawBitmap	(Landroid/graphics/Bitmap;Landroid/graphics/Matrix;Landroid/graphics/Paint;)V
        //   213: aload 4
        //   215: aload 5
        //   217: invokevirtual 112	android/print/pdf/PrintedPdfDocument:finishPage	(Landroid/graphics/pdf/PdfDocument$Page;)V
        //   220: aload_0
        //   221: getfield 31	android/support/v4/print/PrintHelper$1:val$cancellationSignal	Landroid/os/CancellationSignal;
        //   224: invokevirtual 64	android/os/CancellationSignal:isCanceled	()Z
        //   227: istore_2
        //   228: iload_2
        //   229: ifeq +38 -> 267
        //   232: aload 4
        //   234: invokevirtual 115	android/print/pdf/PrintedPdfDocument:close	()V
        //   237: aload_0
        //   238: getfield 41	android/support/v4/print/PrintHelper$1:val$fileDescriptor	Landroid/os/ParcelFileDescriptor;
        //   241: astore_1
        //   242: aload_1
        //   243: ifnull +10 -> 253
        //   246: aload_0
        //   247: getfield 41	android/support/v4/print/PrintHelper$1:val$fileDescriptor	Landroid/os/ParcelFileDescriptor;
        //   250: invokevirtual 157	android/os/ParcelFileDescriptor:close	()V
        //   253: aload_3
        //   254: aload_0
        //   255: getfield 35	android/support/v4/print/PrintHelper$1:val$bitmap	Landroid/graphics/Bitmap;
        //   258: if_acmpeq +7 -> 265
        //   261: aload_3
        //   262: invokevirtual 160	android/graphics/Bitmap:recycle	()V
        //   265: aconst_null
        //   266: areturn
        //   267: aload 4
        //   269: new 162	java/io/FileOutputStream
        //   272: dup
        //   273: aload_0
        //   274: getfield 41	android/support/v4/print/PrintHelper$1:val$fileDescriptor	Landroid/os/ParcelFileDescriptor;
        //   277: invokevirtual 166	android/os/ParcelFileDescriptor:getFileDescriptor	()Ljava/io/FileDescriptor;
        //   280: invokespecial 169	java/io/FileOutputStream:<init>	(Ljava/io/FileDescriptor;)V
        //   283: invokevirtual 173	android/print/pdf/PrintedPdfDocument:writeTo	(Ljava/io/OutputStream;)V
        //   286: aload 4
        //   288: invokevirtual 115	android/print/pdf/PrintedPdfDocument:close	()V
        //   291: aload_0
        //   292: getfield 41	android/support/v4/print/PrintHelper$1:val$fileDescriptor	Landroid/os/ParcelFileDescriptor;
        //   295: astore_1
        //   296: aload_1
        //   297: ifnull +10 -> 307
        //   300: aload_0
        //   301: getfield 41	android/support/v4/print/PrintHelper$1:val$fileDescriptor	Landroid/os/ParcelFileDescriptor;
        //   304: invokevirtual 157	android/os/ParcelFileDescriptor:close	()V
        //   307: aload_3
        //   308: aload_0
        //   309: getfield 35	android/support/v4/print/PrintHelper$1:val$bitmap	Landroid/graphics/Bitmap;
        //   312: if_acmpeq +63 -> 375
        //   315: aload_3
        //   316: invokevirtual 160	android/graphics/Bitmap:recycle	()V
        //   319: aconst_null
        //   320: areturn
        //   321: astore_1
        //   322: aload 4
        //   324: invokevirtual 115	android/print/pdf/PrintedPdfDocument:close	()V
        //   327: aload_0
        //   328: getfield 41	android/support/v4/print/PrintHelper$1:val$fileDescriptor	Landroid/os/ParcelFileDescriptor;
        //   331: astore 4
        //   333: aload 4
        //   335: ifnull +10 -> 345
        //   338: aload_0
        //   339: getfield 41	android/support/v4/print/PrintHelper$1:val$fileDescriptor	Landroid/os/ParcelFileDescriptor;
        //   342: invokevirtual 157	android/os/ParcelFileDescriptor:close	()V
        //   345: aload_3
        //   346: aload_0
        //   347: getfield 35	android/support/v4/print/PrintHelper$1:val$bitmap	Landroid/graphics/Bitmap;
        //   350: if_acmpeq +7 -> 357
        //   353: aload_3
        //   354: invokevirtual 160	android/graphics/Bitmap:recycle	()V
        //   357: aload_1
        //   358: athrow
        //   359: astore_1
        //   360: aload_1
        //   361: areturn
        //   362: astore_1
        //   363: goto -110 -> 253
        //   366: astore_1
        //   367: goto -60 -> 307
        //   370: astore 4
        //   372: goto -27 -> 345
        //   375: aconst_null
        //   376: areturn
        //
        // Exception table:
        //   from	to	target	type
        //   61	91	321	finally
        //   94	150	321	finally
        //   150	174	321	finally
        //   177	201	321	finally
        //   201	228	321	finally
        //   267	286	321	finally
        //   0	10	359	java/lang/Throwable
        //   12	55	359	java/lang/Throwable
        //   232	242	359	java/lang/Throwable
        //   246	253	359	java/lang/Throwable
        //   253	265	359	java/lang/Throwable
        //   286	296	359	java/lang/Throwable
        //   300	307	359	java/lang/Throwable
        //   307	319	359	java/lang/Throwable
        //   322	333	359	java/lang/Throwable
        //   338	345	359	java/lang/Throwable
        //   345	357	359	java/lang/Throwable
        //   357	359	359	java/lang/Throwable
        //   246	253	362	java/io/IOException
        //   300	307	366	java/io/IOException
        //   338	345	370	java/io/IOException
      }

      protected void onPostExecute(Throwable paramAnonymousThrowable)
      {
        if (paramCancellationSignal.isCanceled())
        {
          paramWriteResultCallback.onWriteCancelled();
          return;
        }
        if (paramAnonymousThrowable == null)
        {
          paramWriteResultCallback.onWriteFinished(new PageRange[] { PageRange.ALL_PAGES });
          return;
        }
        Log.e("PrintHelper", "Error writing printed content", paramAnonymousThrowable);
        paramWriteResultCallback.onWriteFailed(null);
      }
    }
    .execute(new Void[0]);
  }

  public static abstract interface OnPrintFinishCallback
  {
    public abstract void onFinish();
  }

  @RequiresApi(19)
  private class PrintBitmapAdapter extends PrintDocumentAdapter
  {
    private PrintAttributes mAttributes;
    private final Bitmap mBitmap;
    private final PrintHelper.OnPrintFinishCallback mCallback;
    private final int mFittingMode;
    private final String mJobName;

    PrintBitmapAdapter(String paramInt, int paramBitmap, Bitmap paramOnPrintFinishCallback, PrintHelper.OnPrintFinishCallback arg5)
    {
      this.mJobName = paramInt;
      this.mFittingMode = paramBitmap;
      this.mBitmap = paramOnPrintFinishCallback;
      Object localObject;
      this.mCallback = localObject;
    }

    public void onFinish()
    {
      if (this.mCallback != null)
        this.mCallback.onFinish();
    }

    public void onLayout(PrintAttributes paramPrintAttributes1, PrintAttributes paramPrintAttributes2, CancellationSignal paramCancellationSignal, PrintDocumentAdapter.LayoutResultCallback paramLayoutResultCallback, Bundle paramBundle)
    {
      this.mAttributes = paramPrintAttributes2;
      paramLayoutResultCallback.onLayoutFinished(new PrintDocumentInfo.Builder(this.mJobName).setContentType(1).setPageCount(1).build(), paramPrintAttributes2.equals(paramPrintAttributes1) ^ true);
    }

    public void onWrite(PageRange[] paramArrayOfPageRange, ParcelFileDescriptor paramParcelFileDescriptor, CancellationSignal paramCancellationSignal, PrintDocumentAdapter.WriteResultCallback paramWriteResultCallback)
    {
      PrintHelper.this.writeBitmap(this.mAttributes, this.mFittingMode, this.mBitmap, paramParcelFileDescriptor, paramCancellationSignal, paramWriteResultCallback);
    }
  }

  @RequiresApi(19)
  private class PrintUriAdapter extends PrintDocumentAdapter
  {
    PrintAttributes mAttributes;
    Bitmap mBitmap;
    final PrintHelper.OnPrintFinishCallback mCallback;
    final int mFittingMode;
    final Uri mImageFile;
    final String mJobName;
    AsyncTask<Uri, Boolean, Bitmap> mLoadBitmap;

    PrintUriAdapter(String paramUri, Uri paramOnPrintFinishCallback, PrintHelper.OnPrintFinishCallback paramInt, int arg5)
    {
      this.mJobName = paramUri;
      this.mImageFile = paramOnPrintFinishCallback;
      this.mCallback = paramInt;
      int i;
      this.mFittingMode = i;
      this.mBitmap = null;
    }

    void cancelLoad()
    {
      synchronized (PrintHelper.this.mLock)
      {
        if (PrintHelper.this.mDecodeOptions != null)
        {
          if (Build.VERSION.SDK_INT < 24)
            PrintHelper.this.mDecodeOptions.requestCancelDecode();
          PrintHelper.this.mDecodeOptions = null;
        }
        return;
      }
    }

    public void onFinish()
    {
      super.onFinish();
      cancelLoad();
      if (this.mLoadBitmap != null)
        this.mLoadBitmap.cancel(true);
      if (this.mCallback != null)
        this.mCallback.onFinish();
      if (this.mBitmap != null)
      {
        this.mBitmap.recycle();
        this.mBitmap = null;
      }
    }

    public void onLayout(final PrintAttributes paramPrintAttributes1, final PrintAttributes paramPrintAttributes2, final CancellationSignal paramCancellationSignal, final PrintDocumentAdapter.LayoutResultCallback paramLayoutResultCallback, Bundle paramBundle)
    {
      try
      {
        this.mAttributes = paramPrintAttributes2;
        if (paramCancellationSignal.isCanceled())
        {
          paramLayoutResultCallback.onLayoutCancelled();
          return;
        }
        if (this.mBitmap != null)
        {
          paramLayoutResultCallback.onLayoutFinished(new PrintDocumentInfo.Builder(this.mJobName).setContentType(1).setPageCount(1).build(), paramPrintAttributes2.equals(paramPrintAttributes1) ^ true);
          return;
        }
        this.mLoadBitmap = new AsyncTask()
        {
          protected Bitmap doInBackground(Uri[] paramAnonymousArrayOfUri)
          {
            try
            {
              paramAnonymousArrayOfUri = PrintHelper.this.loadConstrainedBitmap(PrintHelper.PrintUriAdapter.this.mImageFile);
              return paramAnonymousArrayOfUri;
              label20: return null;
            }
            catch (FileNotFoundException paramAnonymousArrayOfUri)
            {
              break label20;
            }
          }

          protected void onCancelled(Bitmap paramAnonymousBitmap)
          {
            paramLayoutResultCallback.onLayoutCancelled();
            PrintHelper.PrintUriAdapter.this.mLoadBitmap = null;
          }

          protected void onPostExecute(Bitmap paramAnonymousBitmap)
          {
            super.onPostExecute(paramAnonymousBitmap);
            Object localObject = paramAnonymousBitmap;
            if (paramAnonymousBitmap != null)
              if (PrintHelper.PRINT_ACTIVITY_RESPECTS_ORIENTATION)
              {
                localObject = paramAnonymousBitmap;
                if (PrintHelper.this.mOrientation != 0);
              }
              else
              {
                try
                {
                  PrintAttributes.MediaSize localMediaSize = PrintHelper.PrintUriAdapter.this.mAttributes.getMediaSize();
                  localObject = paramAnonymousBitmap;
                  if (localMediaSize != null)
                  {
                    localObject = paramAnonymousBitmap;
                    if (localMediaSize.isPortrait() != PrintHelper.isPortrait(paramAnonymousBitmap))
                    {
                      localObject = new Matrix();
                      ((Matrix)localObject).postRotate(90.0F);
                      localObject = Bitmap.createBitmap(paramAnonymousBitmap, 0, 0, paramAnonymousBitmap.getWidth(), paramAnonymousBitmap.getHeight(), (Matrix)localObject, true);
                    }
                  }
                }
                finally
                {
                }
              }
            PrintHelper.PrintUriAdapter.this.mBitmap = ((Bitmap)localObject);
            if (localObject != null)
            {
              paramAnonymousBitmap = new PrintDocumentInfo.Builder(PrintHelper.PrintUriAdapter.this.mJobName).setContentType(1).setPageCount(1).build();
              boolean bool = paramPrintAttributes2.equals(paramPrintAttributes1);
              paramLayoutResultCallback.onLayoutFinished(paramAnonymousBitmap, true ^ bool);
            }
            else
            {
              paramLayoutResultCallback.onLayoutFailed(null);
            }
            PrintHelper.PrintUriAdapter.this.mLoadBitmap = null;
          }

          protected void onPreExecute()
          {
            paramCancellationSignal.setOnCancelListener(new CancellationSignal.OnCancelListener()
            {
              public void onCancel()
              {
                PrintHelper.PrintUriAdapter.this.cancelLoad();
                PrintHelper.PrintUriAdapter.1.this.cancel(false);
              }
            });
          }
        }
        .execute(new Uri[0]);
        return;
      }
      finally
      {
      }
      throw paramPrintAttributes1;
    }

    public void onWrite(PageRange[] paramArrayOfPageRange, ParcelFileDescriptor paramParcelFileDescriptor, CancellationSignal paramCancellationSignal, PrintDocumentAdapter.WriteResultCallback paramWriteResultCallback)
    {
      PrintHelper.this.writeBitmap(this.mAttributes, this.mFittingMode, this.mBitmap, paramParcelFileDescriptor, paramCancellationSignal, paramWriteResultCallback);
    }
  }
}