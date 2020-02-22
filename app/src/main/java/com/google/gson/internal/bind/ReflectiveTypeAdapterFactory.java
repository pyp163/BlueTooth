package com.google.gson.internal.bind;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal..Gson.Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.Primitives;
import com.google.gson.internal.reflect.ReflectionAccessor;
import com.google.gson.reflect.TypeToken;
import com.google.gson.reflect.TypeToken<*>;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ReflectiveTypeAdapterFactory
  implements TypeAdapterFactory
{
  private final ReflectionAccessor accessor = ReflectionAccessor.getInstance();
  private final ConstructorConstructor constructorConstructor;
  private final Excluder excluder;
  private final FieldNamingStrategy fieldNamingPolicy;
  private final JsonAdapterAnnotationTypeAdapterFactory jsonAdapterFactory;

  public ReflectiveTypeAdapterFactory(ConstructorConstructor paramConstructorConstructor, FieldNamingStrategy paramFieldNamingStrategy, Excluder paramExcluder, JsonAdapterAnnotationTypeAdapterFactory paramJsonAdapterAnnotationTypeAdapterFactory)
  {
    this.constructorConstructor = paramConstructorConstructor;
    this.fieldNamingPolicy = paramFieldNamingStrategy;
    this.excluder = paramExcluder;
    this.jsonAdapterFactory = paramJsonAdapterAnnotationTypeAdapterFactory;
  }

  private BoundField createBoundField(final Gson paramGson, final Field paramField, String paramString, final TypeToken<?> paramTypeToken, boolean paramBoolean1, boolean paramBoolean2)
  {
    final boolean bool2 = Primitives.isPrimitive(paramTypeToken.getRawType());
    Object localObject1 = (JsonAdapter)paramField.getAnnotation(JsonAdapter.class);
    if (localObject1 != null)
      localObject1 = this.jsonAdapterFactory.getTypeAdapter(this.constructorConstructor, paramGson, paramTypeToken, (JsonAdapter)localObject1);
    else
      localObject1 = null;
    final boolean bool1;
    if (localObject1 != null)
      bool1 = true;
    else
      bool1 = false;
    Object localObject2 = localObject1;
    if (localObject1 == null)
      localObject2 = paramGson.getAdapter(paramTypeToken);
    return new BoundField(paramString, paramBoolean1, paramBoolean2)
    {
      void read(JsonReader paramAnonymousJsonReader, Object paramAnonymousObject)
        throws IOException, IllegalAccessException
      {
        paramAnonymousJsonReader = this.val$typeAdapter.read(paramAnonymousJsonReader);
        if ((paramAnonymousJsonReader != null) || (!bool2))
          paramField.set(paramAnonymousObject, paramAnonymousJsonReader);
      }

      void write(JsonWriter paramAnonymousJsonWriter, Object paramAnonymousObject)
        throws IOException, IllegalAccessException
      {
        Object localObject = paramField.get(paramAnonymousObject);
        if (bool1)
          paramAnonymousObject = this.val$typeAdapter;
        else
          paramAnonymousObject = new TypeAdapterRuntimeTypeWrapper(paramGson, this.val$typeAdapter, paramTypeToken.getType());
        paramAnonymousObject.write(paramAnonymousJsonWriter, localObject);
      }

      public boolean writeField(Object paramAnonymousObject)
        throws IOException, IllegalAccessException
      {
        boolean bool2 = this.serialized;
        boolean bool1 = false;
        if (!bool2)
          return false;
        if (paramField.get(paramAnonymousObject) != paramAnonymousObject)
          bool1 = true;
        return bool1;
      }
    };
  }

  static boolean excludeField(Field paramField, boolean paramBoolean, Excluder paramExcluder)
  {
    return (!paramExcluder.excludeClass(paramField.getType(), paramBoolean)) && (!paramExcluder.excludeField(paramField, paramBoolean));
  }

  private Map<String, BoundField> getBoundFields(Gson paramGson, TypeToken<?> paramTypeToken, Class<?> paramClass)
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    if (paramClass.isInterface())
      return localLinkedHashMap;
    Type localType2 = paramTypeToken.getType();
    Object localObject1 = paramTypeToken;
    while (paramClass != Object.class)
    {
      Field[] arrayOfField = paramClass.getDeclaredFields();
      int m = arrayOfField.length;
      int i = 0;
      while (true)
      {
        paramTypeToken = this;
        if (i >= m)
          break;
        Field localField = arrayOfField[i];
        boolean bool1 = paramTypeToken.excludeField(localField, true);
        boolean bool2 = paramTypeToken.excludeField(localField, false);
        if ((bool1) || (bool2))
        {
          paramTypeToken.accessor.makeAccessible(localField);
          Type localType1 = .Gson.Types.resolve(((TypeToken)localObject1).getType(), paramClass, localField.getGenericType());
          List localList = paramTypeToken.getFieldNames(localField);
          paramTypeToken = null;
          int j = localList.size();
          int k = 0;
          while (k < j)
          {
            Object localObject2 = (String)localList.get(k);
            if (k != 0)
              bool1 = false;
            localObject2 = (BoundField)localLinkedHashMap.put(localObject2, createBoundField(paramGson, localField, (String)localObject2, TypeToken.get(localType1), bool1, bool2));
            if (paramTypeToken == null)
              paramTypeToken = (TypeToken<?>)localObject2;
            k += 1;
          }
          if (paramTypeToken != null)
          {
            paramGson = new StringBuilder();
            paramGson.append(localType2);
            paramGson.append(" declares multiple JSON fields named ");
            paramGson.append(paramTypeToken.name);
            throw new IllegalArgumentException(paramGson.toString());
          }
        }
        i += 1;
      }
      localObject1 = TypeToken.get(.Gson.Types.resolve(((TypeToken)localObject1).getType(), paramClass, paramClass.getGenericSuperclass()));
      paramClass = ((TypeToken)localObject1).getRawType();
    }
    return localLinkedHashMap;
  }

  private List<String> getFieldNames(Field paramField)
  {
    Object localObject = (SerializedName)paramField.getAnnotation(SerializedName.class);
    if (localObject == null)
      return Collections.singletonList(this.fieldNamingPolicy.translateName(paramField));
    paramField = ((SerializedName)localObject).value();
    localObject = ((SerializedName)localObject).alternate();
    if (localObject.length == 0)
      return Collections.singletonList(paramField);
    ArrayList localArrayList = new ArrayList(localObject.length + 1);
    localArrayList.add(paramField);
    int j = localObject.length;
    int i = 0;
    while (i < j)
    {
      localArrayList.add(localObject[i]);
      i += 1;
    }
    return localArrayList;
  }

  public <T> TypeAdapter<T> create(Gson paramGson, TypeToken<T> paramTypeToken)
  {
    Class localClass = paramTypeToken.getRawType();
    if (!Object.class.isAssignableFrom(localClass))
      return null;
    return new Adapter(this.constructorConstructor.get(paramTypeToken), getBoundFields(paramGson, paramTypeToken, localClass));
  }

  public boolean excludeField(Field paramField, boolean paramBoolean)
  {
    return excludeField(paramField, paramBoolean, this.excluder);
  }

  public static final class Adapter<T> extends TypeAdapter<T>
  {
    private final Map<String, ReflectiveTypeAdapterFactory.BoundField> boundFields;
    private final ObjectConstructor<T> constructor;

    Adapter(ObjectConstructor<T> paramObjectConstructor, Map<String, ReflectiveTypeAdapterFactory.BoundField> paramMap)
    {
      this.constructor = paramObjectConstructor;
      this.boundFields = paramMap;
    }

    public T read(JsonReader paramJsonReader)
      throws IOException
    {
      if (paramJsonReader.peek() == JsonToken.NULL)
      {
        paramJsonReader.nextNull();
        return null;
      }
      Object localObject1 = this.constructor.construct();
      try
      {
        paramJsonReader.beginObject();
        while (paramJsonReader.hasNext())
        {
          Object localObject2 = paramJsonReader.nextName();
          localObject2 = (ReflectiveTypeAdapterFactory.BoundField)this.boundFields.get(localObject2);
          if ((localObject2 != null) && (((ReflectiveTypeAdapterFactory.BoundField)localObject2).deserialized))
            ((ReflectiveTypeAdapterFactory.BoundField)localObject2).read(paramJsonReader, localObject1);
          else
            paramJsonReader.skipValue();
        }
        paramJsonReader.endObject();
        return localObject1;
      }
      catch (IllegalAccessException paramJsonReader)
      {
        throw new AssertionError(paramJsonReader);
      }
      catch (IllegalStateException paramJsonReader)
      {
      }
      throw new JsonSyntaxException(paramJsonReader);
    }

    public void write(JsonWriter paramJsonWriter, T paramT)
      throws IOException
    {
      if (paramT == null)
      {
        paramJsonWriter.nullValue();
        return;
      }
      paramJsonWriter.beginObject();
      try
      {
        Iterator localIterator = this.boundFields.values().iterator();
        while (localIterator.hasNext())
        {
          ReflectiveTypeAdapterFactory.BoundField localBoundField = (ReflectiveTypeAdapterFactory.BoundField)localIterator.next();
          if (localBoundField.writeField(paramT))
          {
            paramJsonWriter.name(localBoundField.name);
            localBoundField.write(paramJsonWriter, paramT);
          }
        }
        paramJsonWriter.endObject();
        return;
      }
      catch (IllegalAccessException paramJsonWriter)
      {
      }
      throw new AssertionError(paramJsonWriter);
    }
  }

  static abstract class BoundField
  {
    final boolean deserialized;
    final String name;
    final boolean serialized;

    protected BoundField(String paramString, boolean paramBoolean1, boolean paramBoolean2)
    {
      this.name = paramString;
      this.serialized = paramBoolean1;
      this.deserialized = paramBoolean2;
    }

    abstract void read(JsonReader paramJsonReader, Object paramObject)
      throws IOException, IllegalAccessException;

    abstract void write(JsonWriter paramJsonWriter, Object paramObject)
      throws IOException, IllegalAccessException;

    abstract boolean writeField(Object paramObject)
      throws IOException, IllegalAccessException;
  }
}