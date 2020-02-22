package com.google.gson;

import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.Primitives;
import com.google.gson.internal.Streams;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.internal.bind.CollectionTypeAdapterFactory;
import com.google.gson.internal.bind.DateTypeAdapter;
import com.google.gson.internal.bind.JsonAdapterAnnotationTypeAdapterFactory;
import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.internal.bind.MapTypeAdapterFactory;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.google.gson.internal.bind.ReflectiveTypeAdapterFactory;
import com.google.gson.internal.bind.SqlDateTypeAdapter;
import com.google.gson.internal.bind.TimeTypeAdapter;
import com.google.gson.internal.bind.TypeAdapters;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.google.gson.stream.MalformedJsonException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

public final class Gson
{
  static final boolean DEFAULT_COMPLEX_MAP_KEYS = false;
  static final boolean DEFAULT_ESCAPE_HTML = true;
  static final boolean DEFAULT_JSON_NON_EXECUTABLE = false;
  static final boolean DEFAULT_LENIENT = false;
  static final boolean DEFAULT_PRETTY_PRINT = false;
  static final boolean DEFAULT_SERIALIZE_NULLS = false;
  static final boolean DEFAULT_SPECIALIZE_FLOAT_VALUES = false;
  private static final String JSON_NON_EXECUTABLE_PREFIX = ")]}'\n";
  private static final TypeToken<?> NULL_KEY_SURROGATE = TypeToken.get(Object.class);
  final List<TypeAdapterFactory> builderFactories;
  final List<TypeAdapterFactory> builderHierarchyFactories;
  private final ThreadLocal<Map<TypeToken<?>, FutureTypeAdapter<?>>> calls = new ThreadLocal();
  final boolean complexMapKeySerialization;
  private final ConstructorConstructor constructorConstructor;
  final String datePattern;
  final int dateStyle;
  final Excluder excluder;
  final List<TypeAdapterFactory> factories;
  final FieldNamingStrategy fieldNamingStrategy;
  final boolean generateNonExecutableJson;
  final boolean htmlSafe;
  final Map<Type, InstanceCreator<?>> instanceCreators;
  private final JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory;
  final boolean lenient;
  final LongSerializationPolicy longSerializationPolicy;
  final boolean prettyPrinting;
  final boolean serializeNulls;
  final boolean serializeSpecialFloatingPointValues;
  final int timeStyle;
  private final Map<TypeToken<?>, TypeAdapter<?>> typeTokenCache = new ConcurrentHashMap();

  public Gson()
  {
    this(Excluder.DEFAULT, FieldNamingPolicy.IDENTITY, Collections.emptyMap(), false, false, false, true, false, false, false, LongSerializationPolicy.DEFAULT, null, 2, 2, Collections.emptyList(), Collections.emptyList(), Collections.emptyList());
  }

  Gson(Excluder paramExcluder, FieldNamingStrategy paramFieldNamingStrategy, Map<Type, InstanceCreator<?>> paramMap, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, boolean paramBoolean6, boolean paramBoolean7, LongSerializationPolicy paramLongSerializationPolicy, String paramString, int paramInt1, int paramInt2, List<TypeAdapterFactory> paramList1, List<TypeAdapterFactory> paramList2, List<TypeAdapterFactory> paramList3)
  {
    this.excluder = paramExcluder;
    this.fieldNamingStrategy = paramFieldNamingStrategy;
    this.instanceCreators = paramMap;
    this.constructorConstructor = new ConstructorConstructor(paramMap);
    this.serializeNulls = paramBoolean1;
    this.complexMapKeySerialization = paramBoolean2;
    this.generateNonExecutableJson = paramBoolean3;
    this.htmlSafe = paramBoolean4;
    this.prettyPrinting = paramBoolean5;
    this.lenient = paramBoolean6;
    this.serializeSpecialFloatingPointValues = paramBoolean7;
    this.longSerializationPolicy = paramLongSerializationPolicy;
    this.datePattern = paramString;
    this.dateStyle = paramInt1;
    this.timeStyle = paramInt2;
    this.builderFactories = paramList1;
    this.builderHierarchyFactories = paramList2;
    paramMap = new ArrayList();
    paramMap.add(TypeAdapters.JSON_ELEMENT_FACTORY);
    paramMap.add(ObjectTypeAdapter.FACTORY);
    paramMap.add(paramExcluder);
    paramMap.addAll(paramList3);
    paramMap.add(TypeAdapters.STRING_FACTORY);
    paramMap.add(TypeAdapters.INTEGER_FACTORY);
    paramMap.add(TypeAdapters.BOOLEAN_FACTORY);
    paramMap.add(TypeAdapters.BYTE_FACTORY);
    paramMap.add(TypeAdapters.SHORT_FACTORY);
    paramLongSerializationPolicy = longAdapter(paramLongSerializationPolicy);
    paramMap.add(TypeAdapters.newFactory(Long.TYPE, Long.class, paramLongSerializationPolicy));
    paramMap.add(TypeAdapters.newFactory(Double.TYPE, Double.class, doubleAdapter(paramBoolean7)));
    paramMap.add(TypeAdapters.newFactory(Float.TYPE, Float.class, floatAdapter(paramBoolean7)));
    paramMap.add(TypeAdapters.NUMBER_FACTORY);
    paramMap.add(TypeAdapters.ATOMIC_INTEGER_FACTORY);
    paramMap.add(TypeAdapters.ATOMIC_BOOLEAN_FACTORY);
    paramMap.add(TypeAdapters.newFactory(AtomicLong.class, atomicLongAdapter(paramLongSerializationPolicy)));
    paramMap.add(TypeAdapters.newFactory(AtomicLongArray.class, atomicLongArrayAdapter(paramLongSerializationPolicy)));
    paramMap.add(TypeAdapters.ATOMIC_INTEGER_ARRAY_FACTORY);
    paramMap.add(TypeAdapters.CHARACTER_FACTORY);
    paramMap.add(TypeAdapters.STRING_BUILDER_FACTORY);
    paramMap.add(TypeAdapters.STRING_BUFFER_FACTORY);
    paramMap.add(TypeAdapters.newFactory(BigDecimal.class, TypeAdapters.BIG_DECIMAL));
    paramMap.add(TypeAdapters.newFactory(BigInteger.class, TypeAdapters.BIG_INTEGER));
    paramMap.add(TypeAdapters.URL_FACTORY);
    paramMap.add(TypeAdapters.URI_FACTORY);
    paramMap.add(TypeAdapters.UUID_FACTORY);
    paramMap.add(TypeAdapters.CURRENCY_FACTORY);
    paramMap.add(TypeAdapters.LOCALE_FACTORY);
    paramMap.add(TypeAdapters.INET_ADDRESS_FACTORY);
    paramMap.add(TypeAdapters.BIT_SET_FACTORY);
    paramMap.add(DateTypeAdapter.FACTORY);
    paramMap.add(TypeAdapters.CALENDAR_FACTORY);
    paramMap.add(TimeTypeAdapter.FACTORY);
    paramMap.add(SqlDateTypeAdapter.FACTORY);
    paramMap.add(TypeAdapters.TIMESTAMP_FACTORY);
    paramMap.add(ArrayTypeAdapter.FACTORY);
    paramMap.add(TypeAdapters.CLASS_FACTORY);
    paramMap.add(new CollectionTypeAdapterFactory(this.constructorConstructor));
    paramMap.add(new MapTypeAdapterFactory(this.constructorConstructor, paramBoolean2));
    this.jsonAdapterFactory = new JsonAdapterAnnotationTypeAdapterFactory(this.constructorConstructor);
    paramMap.add(this.jsonAdapterFactory);
    paramMap.add(TypeAdapters.ENUM_FACTORY);
    paramMap.add(new ReflectiveTypeAdapterFactory(this.constructorConstructor, paramFieldNamingStrategy, paramExcluder, this.jsonAdapterFactory));
    this.factories = Collections.unmodifiableList(paramMap);
  }

  private static void assertFullConsumption(Object paramObject, JsonReader paramJsonReader)
  {
    if (paramObject != null)
      try
      {
        if (paramJsonReader.peek() != JsonToken.END_DOCUMENT)
          throw new JsonIOException("JSON document was not fully consumed.");
      }
      catch (IOException paramObject)
      {
        throw new JsonIOException(paramObject);
      }
      catch (MalformedJsonException paramObject)
      {
        throw new JsonSyntaxException(paramObject);
      }
  }

  private static TypeAdapter<AtomicLong> atomicLongAdapter(TypeAdapter<Number> paramTypeAdapter)
  {
    return new TypeAdapter()
    {
      public AtomicLong read(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        return new AtomicLong(((Number)this.val$longAdapter.read(paramAnonymousJsonReader)).longValue());
      }

      public void write(JsonWriter paramAnonymousJsonWriter, AtomicLong paramAnonymousAtomicLong)
        throws IOException
      {
        this.val$longAdapter.write(paramAnonymousJsonWriter, Long.valueOf(paramAnonymousAtomicLong.get()));
      }
    }
    .nullSafe();
  }

  private static TypeAdapter<AtomicLongArray> atomicLongArrayAdapter(TypeAdapter<Number> paramTypeAdapter)
  {
    return new TypeAdapter()
    {
      public AtomicLongArray read(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        ArrayList localArrayList = new ArrayList();
        paramAnonymousJsonReader.beginArray();
        while (paramAnonymousJsonReader.hasNext())
          localArrayList.add(Long.valueOf(((Number)this.val$longAdapter.read(paramAnonymousJsonReader)).longValue()));
        paramAnonymousJsonReader.endArray();
        int j = localArrayList.size();
        paramAnonymousJsonReader = new AtomicLongArray(j);
        int i = 0;
        while (i < j)
        {
          paramAnonymousJsonReader.set(i, ((Long)localArrayList.get(i)).longValue());
          i += 1;
        }
        return paramAnonymousJsonReader;
      }

      public void write(JsonWriter paramAnonymousJsonWriter, AtomicLongArray paramAnonymousAtomicLongArray)
        throws IOException
      {
        paramAnonymousJsonWriter.beginArray();
        int j = paramAnonymousAtomicLongArray.length();
        int i = 0;
        while (i < j)
        {
          this.val$longAdapter.write(paramAnonymousJsonWriter, Long.valueOf(paramAnonymousAtomicLongArray.get(i)));
          i += 1;
        }
        paramAnonymousJsonWriter.endArray();
      }
    }
    .nullSafe();
  }

  static void checkValidFloatingPoint(double paramDouble)
  {
    if ((!Double.isNaN(paramDouble)) && (!Double.isInfinite(paramDouble)))
      return;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramDouble);
    localStringBuilder.append(" is not a valid double value as per JSON specification. To override this behavior, use GsonBuilder.serializeSpecialFloatingPointValues() method.");
    throw new IllegalArgumentException(localStringBuilder.toString());
  }

  private TypeAdapter<Number> doubleAdapter(boolean paramBoolean)
  {
    if (paramBoolean)
      return TypeAdapters.DOUBLE;
    return new TypeAdapter()
    {
      public Double read(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        if (paramAnonymousJsonReader.peek() == JsonToken.NULL)
        {
          paramAnonymousJsonReader.nextNull();
          return null;
        }
        return Double.valueOf(paramAnonymousJsonReader.nextDouble());
      }

      public void write(JsonWriter paramAnonymousJsonWriter, Number paramAnonymousNumber)
        throws IOException
      {
        if (paramAnonymousNumber == null)
        {
          paramAnonymousJsonWriter.nullValue();
          return;
        }
        Gson.checkValidFloatingPoint(paramAnonymousNumber.doubleValue());
        paramAnonymousJsonWriter.value(paramAnonymousNumber);
      }
    };
  }

  private TypeAdapter<Number> floatAdapter(boolean paramBoolean)
  {
    if (paramBoolean)
      return TypeAdapters.FLOAT;
    return new TypeAdapter()
    {
      public Float read(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        if (paramAnonymousJsonReader.peek() == JsonToken.NULL)
        {
          paramAnonymousJsonReader.nextNull();
          return null;
        }
        return Float.valueOf((float)paramAnonymousJsonReader.nextDouble());
      }

      public void write(JsonWriter paramAnonymousJsonWriter, Number paramAnonymousNumber)
        throws IOException
      {
        if (paramAnonymousNumber == null)
        {
          paramAnonymousJsonWriter.nullValue();
          return;
        }
        Gson.checkValidFloatingPoint(paramAnonymousNumber.floatValue());
        paramAnonymousJsonWriter.value(paramAnonymousNumber);
      }
    };
  }

  private static TypeAdapter<Number> longAdapter(LongSerializationPolicy paramLongSerializationPolicy)
  {
    if (paramLongSerializationPolicy == LongSerializationPolicy.DEFAULT)
      return TypeAdapters.LONG;
    return new TypeAdapter()
    {
      public Number read(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        if (paramAnonymousJsonReader.peek() == JsonToken.NULL)
        {
          paramAnonymousJsonReader.nextNull();
          return null;
        }
        return Long.valueOf(paramAnonymousJsonReader.nextLong());
      }

      public void write(JsonWriter paramAnonymousJsonWriter, Number paramAnonymousNumber)
        throws IOException
      {
        if (paramAnonymousNumber == null)
        {
          paramAnonymousJsonWriter.nullValue();
          return;
        }
        paramAnonymousJsonWriter.value(paramAnonymousNumber.toString());
      }
    };
  }

  public Excluder excluder()
  {
    return this.excluder;
  }

  public FieldNamingStrategy fieldNamingStrategy()
  {
    return this.fieldNamingStrategy;
  }

  public <T> T fromJson(JsonElement paramJsonElement, Class<T> paramClass)
    throws JsonSyntaxException
  {
    paramJsonElement = fromJson(paramJsonElement, paramClass);
    return Primitives.wrap(paramClass).cast(paramJsonElement);
  }

  public <T> T fromJson(JsonElement paramJsonElement, Type paramType)
    throws JsonSyntaxException
  {
    if (paramJsonElement == null)
      return null;
    return fromJson(new JsonTreeReader(paramJsonElement), paramType);
  }

  // ERROR //
  public <T> T fromJson(JsonReader paramJsonReader, Type paramType)
    throws JsonIOException, JsonSyntaxException
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 470	com/google/gson/stream/JsonReader:isLenient	()Z
    //   4: istore 4
    //   6: iconst_1
    //   7: istore_3
    //   8: aload_1
    //   9: iconst_1
    //   10: invokevirtual 474	com/google/gson/stream/JsonReader:setLenient	(Z)V
    //   13: aload_1
    //   14: invokevirtual 356	com/google/gson/stream/JsonReader:peek	()Lcom/google/gson/stream/JsonToken;
    //   17: pop
    //   18: iconst_0
    //   19: istore_3
    //   20: aload_0
    //   21: aload_2
    //   22: invokestatic 477	com/google/gson/reflect/TypeToken:get	(Ljava/lang/reflect/Type;)Lcom/google/gson/reflect/TypeToken;
    //   25: invokevirtual 481	com/google/gson/Gson:getAdapter	(Lcom/google/gson/reflect/TypeToken;)Lcom/google/gson/TypeAdapter;
    //   28: aload_1
    //   29: invokevirtual 487	com/google/gson/TypeAdapter:read	(Lcom/google/gson/stream/JsonReader;)Ljava/lang/Object;
    //   32: astore_2
    //   33: aload_1
    //   34: iload 4
    //   36: invokevirtual 474	com/google/gson/stream/JsonReader:setLenient	(Z)V
    //   39: aload_2
    //   40: areturn
    //   41: astore_2
    //   42: goto +88 -> 130
    //   45: astore_2
    //   46: new 397	java/lang/StringBuilder
    //   49: dup
    //   50: invokespecial 398	java/lang/StringBuilder:<init>	()V
    //   53: astore 5
    //   55: aload 5
    //   57: ldc_w 489
    //   60: invokevirtual 407	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   63: pop
    //   64: aload 5
    //   66: aload_2
    //   67: invokevirtual 492	java/lang/AssertionError:getMessage	()Ljava/lang/String;
    //   70: invokevirtual 407	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   73: pop
    //   74: new 466	java/lang/AssertionError
    //   77: dup
    //   78: aload 5
    //   80: invokevirtual 413	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   83: aload_2
    //   84: invokespecial 495	java/lang/AssertionError:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   87: athrow
    //   88: astore_2
    //   89: new 374	com/google/gson/JsonSyntaxException
    //   92: dup
    //   93: aload_2
    //   94: invokespecial 375	com/google/gson/JsonSyntaxException:<init>	(Ljava/lang/Throwable;)V
    //   97: athrow
    //   98: astore_2
    //   99: new 374	com/google/gson/JsonSyntaxException
    //   102: dup
    //   103: aload_2
    //   104: invokespecial 375	com/google/gson/JsonSyntaxException:<init>	(Ljava/lang/Throwable;)V
    //   107: athrow
    //   108: astore_2
    //   109: iload_3
    //   110: ifeq +11 -> 121
    //   113: aload_1
    //   114: iload 4
    //   116: invokevirtual 474	com/google/gson/stream/JsonReader:setLenient	(Z)V
    //   119: aconst_null
    //   120: areturn
    //   121: new 374	com/google/gson/JsonSyntaxException
    //   124: dup
    //   125: aload_2
    //   126: invokespecial 375	com/google/gson/JsonSyntaxException:<init>	(Ljava/lang/Throwable;)V
    //   129: athrow
    //   130: aload_1
    //   131: iload 4
    //   133: invokevirtual 474	com/google/gson/stream/JsonReader:setLenient	(Z)V
    //   136: aload_2
    //   137: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   13	18	41	finally
    //   20	33	41	finally
    //   46	88	41	finally
    //   89	98	41	finally
    //   99	108	41	finally
    //   121	130	41	finally
    //   13	18	45	java/lang/AssertionError
    //   20	33	45	java/lang/AssertionError
    //   13	18	88	java/io/IOException
    //   20	33	88	java/io/IOException
    //   13	18	98	java/lang/IllegalStateException
    //   20	33	98	java/lang/IllegalStateException
    //   13	18	108	java/io/EOFException
    //   20	33	108	java/io/EOFException
  }

  public <T> T fromJson(Reader paramReader, Class<T> paramClass)
    throws JsonSyntaxException, JsonIOException
  {
    paramReader = newJsonReader(paramReader);
    Object localObject = fromJson(paramReader, paramClass);
    assertFullConsumption(localObject, paramReader);
    return Primitives.wrap(paramClass).cast(localObject);
  }

  public <T> T fromJson(Reader paramReader, Type paramType)
    throws JsonIOException, JsonSyntaxException
  {
    paramReader = newJsonReader(paramReader);
    paramType = fromJson(paramReader, paramType);
    assertFullConsumption(paramType, paramReader);
    return paramType;
  }

  public <T> T fromJson(String paramString, Class<T> paramClass)
    throws JsonSyntaxException
  {
    paramString = fromJson(paramString, paramClass);
    return Primitives.wrap(paramClass).cast(paramString);
  }

  public <T> T fromJson(String paramString, Type paramType)
    throws JsonSyntaxException
  {
    if (paramString == null)
      return null;
    return fromJson(new StringReader(paramString), paramType);
  }

  public <T> TypeAdapter<T> getAdapter(TypeToken<T> paramTypeToken)
  {
    Object localObject2 = this.typeTokenCache;
    if (paramTypeToken == null)
      localObject1 = NULL_KEY_SURROGATE;
    else
      localObject1 = paramTypeToken;
    Object localObject1 = (TypeAdapter)((Map)localObject2).get(localObject1);
    if (localObject1 != null)
      return localObject1;
    localObject2 = (Map)this.calls.get();
    int i = 0;
    localObject1 = localObject2;
    if (localObject2 == null)
    {
      localObject1 = new HashMap();
      this.calls.set(localObject1);
      i = 1;
    }
    localObject2 = (FutureTypeAdapter)((Map)localObject1).get(paramTypeToken);
    if (localObject2 != null)
      return localObject2;
    try
    {
      localObject2 = new FutureTypeAdapter();
      ((Map)localObject1).put(paramTypeToken, localObject2);
      Iterator localIterator = this.factories.iterator();
      while (localIterator.hasNext())
      {
        TypeAdapter localTypeAdapter = ((TypeAdapterFactory)localIterator.next()).create(this, paramTypeToken);
        if (localTypeAdapter != null)
        {
          ((FutureTypeAdapter)localObject2).setDelegate(localTypeAdapter);
          this.typeTokenCache.put(paramTypeToken, localTypeAdapter);
          return localTypeAdapter;
        }
      }
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("GSON (2.8.5) cannot handle ");
      ((StringBuilder)localObject2).append(paramTypeToken);
      throw new IllegalArgumentException(((StringBuilder)localObject2).toString());
    }
    finally
    {
      ((Map)localObject1).remove(paramTypeToken);
      if (i != 0)
        this.calls.remove();
    }
  }

  public <T> TypeAdapter<T> getAdapter(Class<T> paramClass)
  {
    return getAdapter(TypeToken.get(paramClass));
  }

  public <T> TypeAdapter<T> getDelegateAdapter(TypeAdapterFactory paramTypeAdapterFactory, TypeToken<T> paramTypeToken)
  {
    Object localObject1 = paramTypeAdapterFactory;
    if (!this.factories.contains(paramTypeAdapterFactory))
      localObject1 = this.jsonAdapterFactory;
    int i = 0;
    paramTypeAdapterFactory = this.factories.iterator();
    while (paramTypeAdapterFactory.hasNext())
    {
      Object localObject2 = (TypeAdapterFactory)paramTypeAdapterFactory.next();
      if (i == 0)
      {
        if (localObject2 == localObject1)
          i = 1;
      }
      else
      {
        localObject2 = ((TypeAdapterFactory)localObject2).create(this, paramTypeToken);
        if (localObject2 != null)
          return localObject2;
      }
    }
    paramTypeAdapterFactory = new StringBuilder();
    paramTypeAdapterFactory.append("GSON cannot serialize ");
    paramTypeAdapterFactory.append(paramTypeToken);
    throw new IllegalArgumentException(paramTypeAdapterFactory.toString());
  }

  public boolean htmlSafe()
  {
    return this.htmlSafe;
  }

  public GsonBuilder newBuilder()
  {
    return new GsonBuilder(this);
  }

  public JsonReader newJsonReader(Reader paramReader)
  {
    paramReader = new JsonReader(paramReader);
    paramReader.setLenient(this.lenient);
    return paramReader;
  }

  public JsonWriter newJsonWriter(Writer paramWriter)
    throws IOException
  {
    if (this.generateNonExecutableJson)
      paramWriter.write(")]}'\n");
    paramWriter = new JsonWriter(paramWriter);
    if (this.prettyPrinting)
      paramWriter.setIndent("  ");
    paramWriter.setSerializeNulls(this.serializeNulls);
    return paramWriter;
  }

  public boolean serializeNulls()
  {
    return this.serializeNulls;
  }

  public String toJson(JsonElement paramJsonElement)
  {
    StringWriter localStringWriter = new StringWriter();
    toJson(paramJsonElement, localStringWriter);
    return localStringWriter.toString();
  }

  public String toJson(Object paramObject)
  {
    if (paramObject == null)
      return toJson(JsonNull.INSTANCE);
    return toJson(paramObject, paramObject.getClass());
  }

  public String toJson(Object paramObject, Type paramType)
  {
    StringWriter localStringWriter = new StringWriter();
    toJson(paramObject, paramType, localStringWriter);
    return localStringWriter.toString();
  }

  // ERROR //
  public void toJson(JsonElement paramJsonElement, JsonWriter paramJsonWriter)
    throws JsonIOException
  {
    // Byte code:
    //   0: aload_2
    //   1: invokevirtual 636	com/google/gson/stream/JsonWriter:isLenient	()Z
    //   4: istore_3
    //   5: aload_2
    //   6: iconst_1
    //   7: invokevirtual 637	com/google/gson/stream/JsonWriter:setLenient	(Z)V
    //   10: aload_2
    //   11: invokevirtual 640	com/google/gson/stream/JsonWriter:isHtmlSafe	()Z
    //   14: istore 4
    //   16: aload_2
    //   17: aload_0
    //   18: getfield 141	com/google/gson/Gson:htmlSafe	Z
    //   21: invokevirtual 643	com/google/gson/stream/JsonWriter:setHtmlSafe	(Z)V
    //   24: aload_2
    //   25: invokevirtual 646	com/google/gson/stream/JsonWriter:getSerializeNulls	()Z
    //   28: istore 5
    //   30: aload_2
    //   31: aload_0
    //   32: getfield 135	com/google/gson/Gson:serializeNulls	Z
    //   35: invokevirtual 606	com/google/gson/stream/JsonWriter:setSerializeNulls	(Z)V
    //   38: aload_1
    //   39: aload_2
    //   40: invokestatic 650	com/google/gson/internal/Streams:write	(Lcom/google/gson/JsonElement;Lcom/google/gson/stream/JsonWriter;)V
    //   43: aload_2
    //   44: iload_3
    //   45: invokevirtual 637	com/google/gson/stream/JsonWriter:setLenient	(Z)V
    //   48: aload_2
    //   49: iload 4
    //   51: invokevirtual 643	com/google/gson/stream/JsonWriter:setHtmlSafe	(Z)V
    //   54: aload_2
    //   55: iload 5
    //   57: invokevirtual 606	com/google/gson/stream/JsonWriter:setSerializeNulls	(Z)V
    //   60: return
    //   61: astore_1
    //   62: goto +56 -> 118
    //   65: astore_1
    //   66: new 397	java/lang/StringBuilder
    //   69: dup
    //   70: invokespecial 398	java/lang/StringBuilder:<init>	()V
    //   73: astore 6
    //   75: aload 6
    //   77: ldc_w 489
    //   80: invokevirtual 407	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   83: pop
    //   84: aload 6
    //   86: aload_1
    //   87: invokevirtual 492	java/lang/AssertionError:getMessage	()Ljava/lang/String;
    //   90: invokevirtual 407	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   93: pop
    //   94: new 466	java/lang/AssertionError
    //   97: dup
    //   98: aload 6
    //   100: invokevirtual 413	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   103: aload_1
    //   104: invokespecial 495	java/lang/AssertionError:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   107: athrow
    //   108: astore_1
    //   109: new 364	com/google/gson/JsonIOException
    //   112: dup
    //   113: aload_1
    //   114: invokespecial 372	com/google/gson/JsonIOException:<init>	(Ljava/lang/Throwable;)V
    //   117: athrow
    //   118: aload_2
    //   119: iload_3
    //   120: invokevirtual 637	com/google/gson/stream/JsonWriter:setLenient	(Z)V
    //   123: aload_2
    //   124: iload 4
    //   126: invokevirtual 643	com/google/gson/stream/JsonWriter:setHtmlSafe	(Z)V
    //   129: aload_2
    //   130: iload 5
    //   132: invokevirtual 606	com/google/gson/stream/JsonWriter:setSerializeNulls	(Z)V
    //   135: aload_1
    //   136: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   38	43	61	finally
    //   66	108	61	finally
    //   109	118	61	finally
    //   38	43	65	java/lang/AssertionError
    //   38	43	108	java/io/IOException
  }

  public void toJson(JsonElement paramJsonElement, Appendable paramAppendable)
    throws JsonIOException
  {
    try
    {
      toJson(paramJsonElement, newJsonWriter(Streams.writerForAppendable(paramAppendable)));
      return;
    }
    catch (IOException paramJsonElement)
    {
    }
    throw new JsonIOException(paramJsonElement);
  }

  public void toJson(Object paramObject, Appendable paramAppendable)
    throws JsonIOException
  {
    if (paramObject != null)
    {
      toJson(paramObject, paramObject.getClass(), paramAppendable);
      return;
    }
    toJson(JsonNull.INSTANCE, paramAppendable);
  }

  // ERROR //
  public void toJson(Object paramObject, Type paramType, JsonWriter paramJsonWriter)
    throws JsonIOException
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_2
    //   2: invokestatic 477	com/google/gson/reflect/TypeToken:get	(Ljava/lang/reflect/Type;)Lcom/google/gson/reflect/TypeToken;
    //   5: invokevirtual 481	com/google/gson/Gson:getAdapter	(Lcom/google/gson/reflect/TypeToken;)Lcom/google/gson/TypeAdapter;
    //   8: astore_2
    //   9: aload_3
    //   10: invokevirtual 636	com/google/gson/stream/JsonWriter:isLenient	()Z
    //   13: istore 4
    //   15: aload_3
    //   16: iconst_1
    //   17: invokevirtual 637	com/google/gson/stream/JsonWriter:setLenient	(Z)V
    //   20: aload_3
    //   21: invokevirtual 640	com/google/gson/stream/JsonWriter:isHtmlSafe	()Z
    //   24: istore 5
    //   26: aload_3
    //   27: aload_0
    //   28: getfield 141	com/google/gson/Gson:htmlSafe	Z
    //   31: invokevirtual 643	com/google/gson/stream/JsonWriter:setHtmlSafe	(Z)V
    //   34: aload_3
    //   35: invokevirtual 646	com/google/gson/stream/JsonWriter:getSerializeNulls	()Z
    //   38: istore 6
    //   40: aload_3
    //   41: aload_0
    //   42: getfield 135	com/google/gson/Gson:serializeNulls	Z
    //   45: invokevirtual 606	com/google/gson/stream/JsonWriter:setSerializeNulls	(Z)V
    //   48: aload_2
    //   49: aload_3
    //   50: aload_1
    //   51: invokevirtual 663	com/google/gson/TypeAdapter:write	(Lcom/google/gson/stream/JsonWriter;Ljava/lang/Object;)V
    //   54: aload_3
    //   55: iload 4
    //   57: invokevirtual 637	com/google/gson/stream/JsonWriter:setLenient	(Z)V
    //   60: aload_3
    //   61: iload 5
    //   63: invokevirtual 643	com/google/gson/stream/JsonWriter:setHtmlSafe	(Z)V
    //   66: aload_3
    //   67: iload 6
    //   69: invokevirtual 606	com/google/gson/stream/JsonWriter:setSerializeNulls	(Z)V
    //   72: return
    //   73: astore_1
    //   74: goto +52 -> 126
    //   77: astore_1
    //   78: new 397	java/lang/StringBuilder
    //   81: dup
    //   82: invokespecial 398	java/lang/StringBuilder:<init>	()V
    //   85: astore_2
    //   86: aload_2
    //   87: ldc_w 489
    //   90: invokevirtual 407	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   93: pop
    //   94: aload_2
    //   95: aload_1
    //   96: invokevirtual 492	java/lang/AssertionError:getMessage	()Ljava/lang/String;
    //   99: invokevirtual 407	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   102: pop
    //   103: new 466	java/lang/AssertionError
    //   106: dup
    //   107: aload_2
    //   108: invokevirtual 413	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   111: aload_1
    //   112: invokespecial 495	java/lang/AssertionError:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   115: athrow
    //   116: astore_1
    //   117: new 364	com/google/gson/JsonIOException
    //   120: dup
    //   121: aload_1
    //   122: invokespecial 372	com/google/gson/JsonIOException:<init>	(Ljava/lang/Throwable;)V
    //   125: athrow
    //   126: aload_3
    //   127: iload 4
    //   129: invokevirtual 637	com/google/gson/stream/JsonWriter:setLenient	(Z)V
    //   132: aload_3
    //   133: iload 5
    //   135: invokevirtual 643	com/google/gson/stream/JsonWriter:setHtmlSafe	(Z)V
    //   138: aload_3
    //   139: iload 6
    //   141: invokevirtual 606	com/google/gson/stream/JsonWriter:setSerializeNulls	(Z)V
    //   144: aload_1
    //   145: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   48	54	73	finally
    //   78	116	73	finally
    //   117	126	73	finally
    //   48	54	77	java/lang/AssertionError
    //   48	54	116	java/io/IOException
  }

  public void toJson(Object paramObject, Type paramType, Appendable paramAppendable)
    throws JsonIOException
  {
    try
    {
      toJson(paramObject, paramType, newJsonWriter(Streams.writerForAppendable(paramAppendable)));
      return;
    }
    catch (IOException paramObject)
    {
    }
    throw new JsonIOException(paramObject);
  }

  public JsonElement toJsonTree(Object paramObject)
  {
    if (paramObject == null)
      return JsonNull.INSTANCE;
    return toJsonTree(paramObject, paramObject.getClass());
  }

  public JsonElement toJsonTree(Object paramObject, Type paramType)
  {
    JsonTreeWriter localJsonTreeWriter = new JsonTreeWriter();
    toJson(paramObject, paramType, localJsonTreeWriter);
    return localJsonTreeWriter.get();
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("{serializeNulls:");
    localStringBuilder.append(this.serializeNulls);
    localStringBuilder.append(",factories:");
    localStringBuilder.append(this.factories);
    localStringBuilder.append(",instanceCreators:");
    localStringBuilder.append(this.constructorConstructor);
    localStringBuilder.append("}");
    return localStringBuilder.toString();
  }

  static class FutureTypeAdapter<T> extends TypeAdapter<T>
  {
    private TypeAdapter<T> delegate;

    public T read(JsonReader paramJsonReader)
      throws IOException
    {
      if (this.delegate == null)
        throw new IllegalStateException();
      return this.delegate.read(paramJsonReader);
    }

    public void setDelegate(TypeAdapter<T> paramTypeAdapter)
    {
      if (this.delegate != null)
        throw new AssertionError();
      this.delegate = paramTypeAdapter;
    }

    public void write(JsonWriter paramJsonWriter, T paramT)
      throws IOException
    {
      if (this.delegate == null)
        throw new IllegalStateException();
      this.delegate.write(paramJsonWriter, paramT);
    }
  }
}