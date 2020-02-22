package androidx.versionedparcelable;

import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.NetworkOnMainThreadException;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.util.ArraySet;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseBooleanArray;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamClass;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public abstract class VersionedParcel
{
  private static final int EX_BAD_PARCELABLE = -2;
  private static final int EX_ILLEGAL_ARGUMENT = -3;
  private static final int EX_ILLEGAL_STATE = -5;
  private static final int EX_NETWORK_MAIN_THREAD = -6;
  private static final int EX_NULL_POINTER = -4;
  private static final int EX_PARCELABLE = -9;
  private static final int EX_SECURITY = -1;
  private static final int EX_UNSUPPORTED_OPERATION = -7;
  private static final String TAG = "VersionedParcel";
  private static final int TYPE_BINDER = 5;
  private static final int TYPE_PARCELABLE = 2;
  private static final int TYPE_SERIALIZABLE = 3;
  private static final int TYPE_STRING = 4;
  private static final int TYPE_VERSIONED_PARCELABLE = 1;

  private Exception createException(int paramInt, String paramString)
  {
    switch (paramInt)
    {
    case -8:
    default:
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Unknown exception code: ");
      localStringBuilder.append(paramInt);
      localStringBuilder.append(" msg ");
      localStringBuilder.append(paramString);
      return new RuntimeException(localStringBuilder.toString());
    case -1:
      return new SecurityException(paramString);
    case -2:
      return new BadParcelableException(paramString);
    case -3:
      return new IllegalArgumentException(paramString);
    case -4:
      return new NullPointerException(paramString);
    case -5:
      return new IllegalStateException(paramString);
    case -6:
      return new NetworkOnMainThreadException();
    case -7:
      return new UnsupportedOperationException(paramString);
    case -9:
    }
    return (Exception)readParcelable();
  }

  private static <T extends VersionedParcelable> Class findParcelClass(T paramT)
    throws ClassNotFoundException
  {
    return findParcelClass(paramT.getClass());
  }

  private static Class findParcelClass(Class<? extends VersionedParcelable> paramClass)
    throws ClassNotFoundException
  {
    return Class.forName(String.format("%s.%sParcelizer", new Object[] { paramClass.getPackage().getName(), paramClass.getSimpleName() }), false, paramClass.getClassLoader());
  }

  @NonNull
  protected static Throwable getRootCause(@NonNull Throwable paramThrowable)
  {
    while (paramThrowable.getCause() != null)
      paramThrowable = paramThrowable.getCause();
    return paramThrowable;
  }

  private <T> int getType(T paramT)
  {
    if ((paramT instanceof String))
      return 4;
    if ((paramT instanceof Parcelable))
      return 2;
    if ((paramT instanceof VersionedParcelable))
      return 1;
    if ((paramT instanceof Serializable))
      return 3;
    if ((paramT instanceof IBinder))
      return 5;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramT.getClass().getName());
    localStringBuilder.append(" cannot be VersionedParcelled");
    throw new IllegalArgumentException(localStringBuilder.toString());
  }

  private <T, S extends Collection<T>> S readCollection(int paramInt, S paramS)
  {
    paramInt = readInt();
    if (paramInt < 0)
      return null;
    if (paramInt != 0)
    {
      int n = readInt();
      if (paramInt < 0)
        return null;
      int i = paramInt;
      int j = paramInt;
      int k = paramInt;
      int m = paramInt;
      switch (n)
      {
      default:
        return paramS;
      case 5:
      case 4:
      case 3:
      case 2:
      case 1:
      }
      while (i > 0)
      {
        paramS.add(readStrongBinder());
        i -= 1;
        continue;
        while (j > 0)
        {
          paramS.add(readString());
          j -= 1;
          continue;
          while (k > 0)
          {
            paramS.add(readSerializable());
            k -= 1;
            continue;
            while (m > 0)
            {
              paramS.add(readParcelable());
              m -= 1;
              continue;
              while (paramInt > 0)
              {
                paramS.add(readVersionedParcelable());
                paramInt -= 1;
              }
            }
          }
        }
      }
    }
    return paramS;
  }

  private Exception readException(int paramInt, String paramString)
  {
    return createException(paramInt, paramString);
  }

  private int readExceptionCode()
  {
    return readInt();
  }

  protected static <T extends VersionedParcelable> T readFromParcel(String paramString, VersionedParcel paramVersionedParcel)
  {
    try
    {
      paramString = (VersionedParcelable)Class.forName(paramString, true, VersionedParcel.class.getClassLoader()).getDeclaredMethod("read", new Class[] { VersionedParcel.class }).invoke(null, new Object[] { paramVersionedParcel });
      return paramString;
    }
    catch (ClassNotFoundException paramString)
    {
      throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", paramString);
    }
    catch (NoSuchMethodException paramString)
    {
      throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", paramString);
    }
    catch (InvocationTargetException paramString)
    {
      if ((paramString.getCause() instanceof RuntimeException))
        throw ((RuntimeException)paramString.getCause());
      throw new RuntimeException("VersionedParcel encountered InvocationTargetException", paramString);
    }
    catch (IllegalAccessException paramString)
    {
    }
    throw new RuntimeException("VersionedParcel encountered IllegalAccessException", paramString);
  }

  private <T> void writeCollection(Collection<T> paramCollection, int paramInt)
  {
    setOutputField(paramInt);
    if (paramCollection == null)
    {
      writeInt(-1);
      return;
    }
    paramInt = paramCollection.size();
    writeInt(paramInt);
    if (paramInt > 0)
    {
      paramInt = getType(paramCollection.iterator().next());
      writeInt(paramInt);
      switch (paramInt)
      {
      default:
        return;
      case 5:
        paramCollection = paramCollection.iterator();
      case 4:
      case 3:
      case 2:
      case 1:
      }
      while (paramCollection.hasNext())
      {
        writeStrongBinder((IBinder)paramCollection.next());
        continue;
        paramCollection = paramCollection.iterator();
        while (paramCollection.hasNext())
        {
          writeString((String)paramCollection.next());
          continue;
          paramCollection = paramCollection.iterator();
          while (paramCollection.hasNext())
          {
            writeSerializable((Serializable)paramCollection.next());
            continue;
            paramCollection = paramCollection.iterator();
            while (paramCollection.hasNext())
            {
              writeParcelable((Parcelable)paramCollection.next());
              continue;
              paramCollection = paramCollection.iterator();
              while (paramCollection.hasNext())
                writeVersionedParcelable((VersionedParcelable)paramCollection.next());
            }
          }
        }
      }
    }
  }

  private void writeSerializable(Serializable paramSerializable)
  {
    if (paramSerializable == null)
    {
      writeString(null);
      return;
    }
    String str = paramSerializable.getClass().getName();
    writeString(str);
    Object localObject = new ByteArrayOutputStream();
    try
    {
      ObjectOutputStream localObjectOutputStream = new ObjectOutputStream((OutputStream)localObject);
      localObjectOutputStream.writeObject(paramSerializable);
      localObjectOutputStream.close();
      writeByteArray(((ByteArrayOutputStream)localObject).toByteArray());
      return;
    }
    catch (IOException paramSerializable)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("VersionedParcelable encountered IOException writing serializable object (name = ");
      ((StringBuilder)localObject).append(str);
      ((StringBuilder)localObject).append(")");
    }
    throw new RuntimeException(((StringBuilder)localObject).toString(), paramSerializable);
  }

  protected static <T extends VersionedParcelable> void writeToParcel(T paramT, VersionedParcel paramVersionedParcel)
  {
    try
    {
      findParcelClass(paramT).getDeclaredMethod("write", new Class[] { paramT.getClass(), VersionedParcel.class }).invoke(null, new Object[] { paramT, paramVersionedParcel });
      return;
    }
    catch (ClassNotFoundException paramT)
    {
      throw new RuntimeException("VersionedParcel encountered ClassNotFoundException", paramT);
    }
    catch (NoSuchMethodException paramT)
    {
      throw new RuntimeException("VersionedParcel encountered NoSuchMethodException", paramT);
    }
    catch (InvocationTargetException paramT)
    {
      if ((paramT.getCause() instanceof RuntimeException))
        throw ((RuntimeException)paramT.getCause());
      throw new RuntimeException("VersionedParcel encountered InvocationTargetException", paramT);
    }
    catch (IllegalAccessException paramT)
    {
    }
    throw new RuntimeException("VersionedParcel encountered IllegalAccessException", paramT);
  }

  private void writeVersionedParcelableCreator(VersionedParcelable paramVersionedParcelable)
  {
    try
    {
      Class localClass = findParcelClass(paramVersionedParcelable.getClass());
      writeString(localClass.getName());
      return;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramVersionedParcelable.getClass().getSimpleName());
      localStringBuilder.append(" does not have a Parcelizer");
      throw new RuntimeException(localStringBuilder.toString(), localClassNotFoundException);
    }
  }

  protected abstract void closeField();

  protected abstract VersionedParcel createSubParcel();

  public boolean isStream()
  {
    return false;
  }

  protected <T> T[] readArray(T[] paramArrayOfT)
  {
    int i = readInt();
    if (i < 0)
      return null;
    ArrayList localArrayList = new ArrayList(i);
    if (i != 0)
    {
      int i1 = readInt();
      if (i < 0)
        return null;
      int j = i;
      int k = i;
      int m = i;
      int n = i;
      switch (i1)
      {
      default:
        break;
      case 5:
      case 4:
      case 3:
      case 2:
      case 1:
        while (j > 0)
        {
          localArrayList.add(readStrongBinder());
          j -= 1;
          continue;
          while (k > 0)
          {
            localArrayList.add(readString());
            k -= 1;
            continue;
            while (m > 0)
            {
              localArrayList.add(readSerializable());
              m -= 1;
              continue;
              while (n > 0)
              {
                localArrayList.add(readParcelable());
                n -= 1;
                continue;
                while (i > 0)
                {
                  localArrayList.add(readVersionedParcelable());
                  i -= 1;
                }
              }
            }
          }
        }
      }
    }
    return localArrayList.toArray(paramArrayOfT);
  }

  public <T> T[] readArray(T[] paramArrayOfT, int paramInt)
  {
    if (!readField(paramInt))
      return paramArrayOfT;
    return readArray(paramArrayOfT);
  }

  protected abstract boolean readBoolean();

  public boolean readBoolean(boolean paramBoolean, int paramInt)
  {
    if (!readField(paramInt))
      return paramBoolean;
    return readBoolean();
  }

  protected boolean[] readBooleanArray()
  {
    int j = readInt();
    if (j < 0)
      return null;
    boolean[] arrayOfBoolean = new boolean[j];
    int i = 0;
    while (i < j)
    {
      int k;
      if (readInt() != 0)
        k = 1;
      else
        k = 0;
      arrayOfBoolean[i] = k;
      i += 1;
    }
    return arrayOfBoolean;
  }

  public boolean[] readBooleanArray(boolean[] paramArrayOfBoolean, int paramInt)
  {
    if (!readField(paramInt))
      return paramArrayOfBoolean;
    return readBooleanArray();
  }

  protected abstract Bundle readBundle();

  public Bundle readBundle(Bundle paramBundle, int paramInt)
  {
    if (!readField(paramInt))
      return paramBundle;
    return readBundle();
  }

  public byte readByte(byte paramByte, int paramInt)
  {
    if (!readField(paramInt))
      return paramByte;
    return (byte)(readInt() & 0xFF);
  }

  protected abstract byte[] readByteArray();

  public byte[] readByteArray(byte[] paramArrayOfByte, int paramInt)
  {
    if (!readField(paramInt))
      return paramArrayOfByte;
    return readByteArray();
  }

  public char[] readCharArray(char[] paramArrayOfChar, int paramInt)
  {
    if (!readField(paramInt))
      return paramArrayOfChar;
    int i = readInt();
    if (i < 0)
      return null;
    paramArrayOfChar = new char[i];
    paramInt = 0;
    while (paramInt < i)
    {
      paramArrayOfChar[paramInt] = ((char)readInt());
      paramInt += 1;
    }
    return paramArrayOfChar;
  }

  protected abstract double readDouble();

  public double readDouble(double paramDouble, int paramInt)
  {
    if (!readField(paramInt))
      return paramDouble;
    return readDouble();
  }

  protected double[] readDoubleArray()
  {
    int j = readInt();
    if (j < 0)
      return null;
    double[] arrayOfDouble = new double[j];
    int i = 0;
    while (i < j)
    {
      arrayOfDouble[i] = readDouble();
      i += 1;
    }
    return arrayOfDouble;
  }

  public double[] readDoubleArray(double[] paramArrayOfDouble, int paramInt)
  {
    if (!readField(paramInt))
      return paramArrayOfDouble;
    return readDoubleArray();
  }

  public Exception readException(Exception paramException, int paramInt)
  {
    if (!readField(paramInt))
      return paramException;
    paramInt = readExceptionCode();
    if (paramInt != 0)
      return readException(paramInt, readString());
    return paramException;
  }

  protected abstract boolean readField(int paramInt);

  protected abstract float readFloat();

  public float readFloat(float paramFloat, int paramInt)
  {
    if (!readField(paramInt))
      return paramFloat;
    return readFloat();
  }

  protected float[] readFloatArray()
  {
    int j = readInt();
    if (j < 0)
      return null;
    float[] arrayOfFloat = new float[j];
    int i = 0;
    while (i < j)
    {
      arrayOfFloat[i] = readFloat();
      i += 1;
    }
    return arrayOfFloat;
  }

  public float[] readFloatArray(float[] paramArrayOfFloat, int paramInt)
  {
    if (!readField(paramInt))
      return paramArrayOfFloat;
    return readFloatArray();
  }

  protected abstract int readInt();

  public int readInt(int paramInt1, int paramInt2)
  {
    if (!readField(paramInt2))
      return paramInt1;
    return readInt();
  }

  protected int[] readIntArray()
  {
    int j = readInt();
    if (j < 0)
      return null;
    int[] arrayOfInt = new int[j];
    int i = 0;
    while (i < j)
    {
      arrayOfInt[i] = readInt();
      i += 1;
    }
    return arrayOfInt;
  }

  public int[] readIntArray(int[] paramArrayOfInt, int paramInt)
  {
    if (!readField(paramInt))
      return paramArrayOfInt;
    return readIntArray();
  }

  public <T> List<T> readList(List<T> paramList, int paramInt)
  {
    if (!readField(paramInt))
      return paramList;
    return (List)readCollection(paramInt, new ArrayList());
  }

  protected abstract long readLong();

  public long readLong(long paramLong, int paramInt)
  {
    if (!readField(paramInt))
      return paramLong;
    return readLong();
  }

  protected long[] readLongArray()
  {
    int j = readInt();
    if (j < 0)
      return null;
    long[] arrayOfLong = new long[j];
    int i = 0;
    while (i < j)
    {
      arrayOfLong[i] = readLong();
      i += 1;
    }
    return arrayOfLong;
  }

  public long[] readLongArray(long[] paramArrayOfLong, int paramInt)
  {
    if (!readField(paramInt))
      return paramArrayOfLong;
    return readLongArray();
  }

  protected abstract <T extends Parcelable> T readParcelable();

  public <T extends Parcelable> T readParcelable(T paramT, int paramInt)
  {
    if (!readField(paramInt))
      return paramT;
    return readParcelable();
  }

  protected Serializable readSerializable()
  {
    String str = readString();
    if (str == null)
      return null;
    Object localObject = new ByteArrayInputStream(readByteArray());
    try
    {
      localObject = (Serializable)new ObjectInputStream((InputStream)localObject)
      {
        protected Class<?> resolveClass(ObjectStreamClass paramAnonymousObjectStreamClass)
          throws IOException, ClassNotFoundException
        {
          Class localClass = Class.forName(paramAnonymousObjectStreamClass.getName(), false, getClass().getClassLoader());
          if (localClass != null)
            return localClass;
          return super.resolveClass(paramAnonymousObjectStreamClass);
        }
      }
      .readObject();
      return localObject;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("VersionedParcelable encountered ClassNotFoundException reading a Serializable object (name = ");
      localStringBuilder.append(str);
      localStringBuilder.append(")");
      throw new RuntimeException(localStringBuilder.toString(), localClassNotFoundException);
    }
    catch (IOException localIOException)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("VersionedParcelable encountered IOException reading a Serializable object (name = ");
      localStringBuilder.append(str);
      localStringBuilder.append(")");
      throw new RuntimeException(localStringBuilder.toString(), localIOException);
    }
  }

  public <T> Set<T> readSet(Set<T> paramSet, int paramInt)
  {
    if (!readField(paramInt))
      return paramSet;
    return (Set)readCollection(paramInt, new ArraySet());
  }

  @RequiresApi(api=21)
  public Size readSize(Size paramSize, int paramInt)
  {
    if (!readField(paramInt))
      return paramSize;
    if (readBoolean())
      return new Size(readInt(), readInt());
    return null;
  }

  @RequiresApi(api=21)
  public SizeF readSizeF(SizeF paramSizeF, int paramInt)
  {
    if (!readField(paramInt))
      return paramSizeF;
    if (readBoolean())
      return new SizeF(readFloat(), readFloat());
    return null;
  }

  public SparseBooleanArray readSparseBooleanArray(SparseBooleanArray paramSparseBooleanArray, int paramInt)
  {
    if (!readField(paramInt))
      return paramSparseBooleanArray;
    int i = readInt();
    if (i < 0)
      return null;
    paramSparseBooleanArray = new SparseBooleanArray(i);
    paramInt = 0;
    while (paramInt < i)
    {
      paramSparseBooleanArray.put(readInt(), readBoolean());
      paramInt += 1;
    }
    return paramSparseBooleanArray;
  }

  protected abstract String readString();

  public String readString(String paramString, int paramInt)
  {
    if (!readField(paramInt))
      return paramString;
    return readString();
  }

  protected abstract IBinder readStrongBinder();

  public IBinder readStrongBinder(IBinder paramIBinder, int paramInt)
  {
    if (!readField(paramInt))
      return paramIBinder;
    return readStrongBinder();
  }

  protected <T extends VersionedParcelable> T readVersionedParcelable()
  {
    String str = readString();
    if (str == null)
      return null;
    return readFromParcel(str, createSubParcel());
  }

  public <T extends VersionedParcelable> T readVersionedParcelable(T paramT, int paramInt)
  {
    if (!readField(paramInt))
      return paramT;
    return readVersionedParcelable();
  }

  protected abstract void setOutputField(int paramInt);

  public void setSerializationFlags(boolean paramBoolean1, boolean paramBoolean2)
  {
  }

  protected <T> void writeArray(T[] paramArrayOfT)
  {
    if (paramArrayOfT == null)
    {
      writeInt(-1);
      return;
    }
    int i1 = paramArrayOfT.length;
    writeInt(i1);
    if (i1 > 0)
    {
      int j = 0;
      int k = 0;
      int m = 0;
      int n = 0;
      int i = 0;
      int i2 = getType(paramArrayOfT[0]);
      writeInt(i2);
      switch (i2)
      {
      default:
        return;
      case 5:
      case 4:
      case 3:
      case 2:
      case 1:
      }
      while (i < i1)
      {
        writeStrongBinder((IBinder)paramArrayOfT[i]);
        i += 1;
        continue;
        while (j < i1)
        {
          writeString((String)paramArrayOfT[j]);
          j += 1;
          continue;
          while (k < i1)
          {
            writeSerializable((Serializable)paramArrayOfT[k]);
            k += 1;
            continue;
            while (m < i1)
            {
              writeParcelable((Parcelable)paramArrayOfT[m]);
              m += 1;
              continue;
              while (n < i1)
              {
                writeVersionedParcelable((VersionedParcelable)paramArrayOfT[n]);
                n += 1;
              }
            }
          }
        }
      }
    }
  }

  public <T> void writeArray(T[] paramArrayOfT, int paramInt)
  {
    setOutputField(paramInt);
    writeArray(paramArrayOfT);
  }

  protected abstract void writeBoolean(boolean paramBoolean);

  public void writeBoolean(boolean paramBoolean, int paramInt)
  {
    setOutputField(paramInt);
    writeBoolean(paramBoolean);
  }

  protected void writeBooleanArray(boolean[] paramArrayOfBoolean)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:553)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }

  public void writeBooleanArray(boolean[] paramArrayOfBoolean, int paramInt)
  {
    setOutputField(paramInt);
    writeBooleanArray(paramArrayOfBoolean);
  }

  protected abstract void writeBundle(Bundle paramBundle);

  public void writeBundle(Bundle paramBundle, int paramInt)
  {
    setOutputField(paramInt);
    writeBundle(paramBundle);
  }

  public void writeByte(byte paramByte, int paramInt)
  {
    setOutputField(paramInt);
    writeInt(paramByte);
  }

  protected abstract void writeByteArray(byte[] paramArrayOfByte);

  public void writeByteArray(byte[] paramArrayOfByte, int paramInt)
  {
    setOutputField(paramInt);
    writeByteArray(paramArrayOfByte);
  }

  protected abstract void writeByteArray(byte[] paramArrayOfByte, int paramInt1, int paramInt2);

  public void writeByteArray(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
  {
    setOutputField(paramInt3);
    writeByteArray(paramArrayOfByte, paramInt1, paramInt2);
  }

  public void writeCharArray(char[] paramArrayOfChar, int paramInt)
  {
    setOutputField(paramInt);
    if (paramArrayOfChar != null)
    {
      int i = paramArrayOfChar.length;
      writeInt(i);
      paramInt = 0;
      while (paramInt < i)
      {
        writeInt(paramArrayOfChar[paramInt]);
        paramInt += 1;
      }
    }
    writeInt(-1);
  }

  protected abstract void writeDouble(double paramDouble);

  public void writeDouble(double paramDouble, int paramInt)
  {
    setOutputField(paramInt);
    writeDouble(paramDouble);
  }

  protected void writeDoubleArray(double[] paramArrayOfDouble)
  {
    if (paramArrayOfDouble != null)
    {
      int j = paramArrayOfDouble.length;
      writeInt(j);
      int i = 0;
      while (i < j)
      {
        writeDouble(paramArrayOfDouble[i]);
        i += 1;
      }
    }
    writeInt(-1);
  }

  public void writeDoubleArray(double[] paramArrayOfDouble, int paramInt)
  {
    setOutputField(paramInt);
    writeDoubleArray(paramArrayOfDouble);
  }

  public void writeException(Exception paramException, int paramInt)
  {
    setOutputField(paramInt);
    if (paramException == null)
    {
      writeNoException();
      return;
    }
    paramInt = 0;
    if (((paramException instanceof Parcelable)) && (paramException.getClass().getClassLoader() == Parcelable.class.getClassLoader()))
      paramInt = -9;
    else if ((paramException instanceof SecurityException))
      paramInt = -1;
    else if ((paramException instanceof BadParcelableException))
      paramInt = -2;
    else if ((paramException instanceof IllegalArgumentException))
      paramInt = -3;
    else if ((paramException instanceof NullPointerException))
      paramInt = -4;
    else if ((paramException instanceof IllegalStateException))
      paramInt = -5;
    else if ((paramException instanceof NetworkOnMainThreadException))
      paramInt = -6;
    else if ((paramException instanceof UnsupportedOperationException))
      paramInt = -7;
    writeInt(paramInt);
    if (paramInt == 0)
    {
      if ((paramException instanceof RuntimeException))
        throw ((RuntimeException)paramException);
      throw new RuntimeException(paramException);
    }
    writeString(paramException.getMessage());
    if (paramInt != -9)
      return;
    writeParcelable((Parcelable)paramException);
  }

  protected abstract void writeFloat(float paramFloat);

  public void writeFloat(float paramFloat, int paramInt)
  {
    setOutputField(paramInt);
    writeFloat(paramFloat);
  }

  protected void writeFloatArray(float[] paramArrayOfFloat)
  {
    if (paramArrayOfFloat != null)
    {
      int j = paramArrayOfFloat.length;
      writeInt(j);
      int i = 0;
      while (i < j)
      {
        writeFloat(paramArrayOfFloat[i]);
        i += 1;
      }
    }
    writeInt(-1);
  }

  public void writeFloatArray(float[] paramArrayOfFloat, int paramInt)
  {
    setOutputField(paramInt);
    writeFloatArray(paramArrayOfFloat);
  }

  protected abstract void writeInt(int paramInt);

  public void writeInt(int paramInt1, int paramInt2)
  {
    setOutputField(paramInt2);
    writeInt(paramInt1);
  }

  protected void writeIntArray(int[] paramArrayOfInt)
  {
    if (paramArrayOfInt != null)
    {
      int j = paramArrayOfInt.length;
      writeInt(j);
      int i = 0;
      while (i < j)
      {
        writeInt(paramArrayOfInt[i]);
        i += 1;
      }
    }
    writeInt(-1);
  }

  public void writeIntArray(int[] paramArrayOfInt, int paramInt)
  {
    setOutputField(paramInt);
    writeIntArray(paramArrayOfInt);
  }

  public <T> void writeList(List<T> paramList, int paramInt)
  {
    writeCollection(paramList, paramInt);
  }

  protected abstract void writeLong(long paramLong);

  public void writeLong(long paramLong, int paramInt)
  {
    setOutputField(paramInt);
    writeLong(paramLong);
  }

  protected void writeLongArray(long[] paramArrayOfLong)
  {
    if (paramArrayOfLong != null)
    {
      int j = paramArrayOfLong.length;
      writeInt(j);
      int i = 0;
      while (i < j)
      {
        writeLong(paramArrayOfLong[i]);
        i += 1;
      }
    }
    writeInt(-1);
  }

  public void writeLongArray(long[] paramArrayOfLong, int paramInt)
  {
    setOutputField(paramInt);
    writeLongArray(paramArrayOfLong);
  }

  protected void writeNoException()
  {
    writeInt(0);
  }

  protected abstract void writeParcelable(Parcelable paramParcelable);

  public void writeParcelable(Parcelable paramParcelable, int paramInt)
  {
    setOutputField(paramInt);
    writeParcelable(paramParcelable);
  }

  public void writeSerializable(Serializable paramSerializable, int paramInt)
  {
    setOutputField(paramInt);
    writeSerializable(paramSerializable);
  }

  public <T> void writeSet(Set<T> paramSet, int paramInt)
  {
    writeCollection(paramSet, paramInt);
  }

  @RequiresApi(api=21)
  public void writeSize(Size paramSize, int paramInt)
  {
    setOutputField(paramInt);
    boolean bool;
    if (paramSize != null)
      bool = true;
    else
      bool = false;
    writeBoolean(bool);
    if (paramSize != null)
    {
      writeInt(paramSize.getWidth());
      writeInt(paramSize.getHeight());
    }
  }

  @RequiresApi(api=21)
  public void writeSizeF(SizeF paramSizeF, int paramInt)
  {
    setOutputField(paramInt);
    boolean bool;
    if (paramSizeF != null)
      bool = true;
    else
      bool = false;
    writeBoolean(bool);
    if (paramSizeF != null)
    {
      writeFloat(paramSizeF.getWidth());
      writeFloat(paramSizeF.getHeight());
    }
  }

  public void writeSparseBooleanArray(SparseBooleanArray paramSparseBooleanArray, int paramInt)
  {
    setOutputField(paramInt);
    if (paramSparseBooleanArray == null)
    {
      writeInt(-1);
      return;
    }
    int i = paramSparseBooleanArray.size();
    writeInt(i);
    paramInt = 0;
    while (paramInt < i)
    {
      writeInt(paramSparseBooleanArray.keyAt(paramInt));
      writeBoolean(paramSparseBooleanArray.valueAt(paramInt));
      paramInt += 1;
    }
  }

  protected abstract void writeString(String paramString);

  public void writeString(String paramString, int paramInt)
  {
    setOutputField(paramInt);
    writeString(paramString);
  }

  protected abstract void writeStrongBinder(IBinder paramIBinder);

  public void writeStrongBinder(IBinder paramIBinder, int paramInt)
  {
    setOutputField(paramInt);
    writeStrongBinder(paramIBinder);
  }

  protected abstract void writeStrongInterface(IInterface paramIInterface);

  public void writeStrongInterface(IInterface paramIInterface, int paramInt)
  {
    setOutputField(paramInt);
    writeStrongInterface(paramIInterface);
  }

  protected void writeVersionedParcelable(VersionedParcelable paramVersionedParcelable)
  {
    if (paramVersionedParcelable == null)
    {
      writeString(null);
      return;
    }
    writeVersionedParcelableCreator(paramVersionedParcelable);
    VersionedParcel localVersionedParcel = createSubParcel();
    writeToParcel(paramVersionedParcelable, localVersionedParcel);
    localVersionedParcel.closeField();
  }

  public void writeVersionedParcelable(VersionedParcelable paramVersionedParcelable, int paramInt)
  {
    setOutputField(paramInt);
    writeVersionedParcelable(paramVersionedParcelable);
  }

  public static class ParcelException extends RuntimeException
  {
    public ParcelException(Throwable paramThrowable)
    {
      super();
    }
  }
}