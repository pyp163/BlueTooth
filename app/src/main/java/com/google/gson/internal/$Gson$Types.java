package com.google.gson.internal;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;

public final class $Gson$Types
{
  static final Type[] EMPTY_TYPE_ARRAY = new Type[0];

  private $Gson$Types()
  {
    throw new UnsupportedOperationException();
  }

  public static GenericArrayType arrayOf(Type paramType)
  {
    return new GenericArrayTypeImpl(paramType);
  }

  public static Type canonicalize(Type paramType)
  {
    if ((paramType instanceof Class))
    {
      Class localClass = (Class)paramType;
      paramType = localClass;
      if (localClass.isArray())
        paramType = new GenericArrayTypeImpl(canonicalize(localClass.getComponentType()));
      return (Type)paramType;
    }
    if ((paramType instanceof ParameterizedType))
    {
      paramType = (ParameterizedType)paramType;
      return new ParameterizedTypeImpl(paramType.getOwnerType(), paramType.getRawType(), paramType.getActualTypeArguments());
    }
    if ((paramType instanceof GenericArrayType))
      return new GenericArrayTypeImpl(((GenericArrayType)paramType).getGenericComponentType());
    if ((paramType instanceof WildcardType))
    {
      paramType = (WildcardType)paramType;
      return new WildcardTypeImpl(paramType.getUpperBounds(), paramType.getLowerBounds());
    }
    return paramType;
  }

  static void checkNotPrimitive(Type paramType)
  {
    boolean bool;
    if (((paramType instanceof Class)) && (((Class)paramType).isPrimitive()))
      bool = false;
    else
      bool = true;
    .Gson.Preconditions.checkArgument(bool);
  }

  private static Class<?> declaringClassOf(TypeVariable<?> paramTypeVariable)
  {
    paramTypeVariable = paramTypeVariable.getGenericDeclaration();
    if ((paramTypeVariable instanceof Class))
      return (Class)paramTypeVariable;
    return null;
  }

  static boolean equal(Object paramObject1, Object paramObject2)
  {
    return (paramObject1 == paramObject2) || ((paramObject1 != null) && (paramObject1.equals(paramObject2)));
  }

  public static boolean equals(Type paramType1, Type paramType2)
  {
    if (paramType1 == paramType2)
      return true;
    if ((paramType1 instanceof Class))
      return paramType1.equals(paramType2);
    if ((paramType1 instanceof ParameterizedType))
    {
      if (!(paramType2 instanceof ParameterizedType))
        return false;
      paramType1 = (ParameterizedType)paramType1;
      paramType2 = (ParameterizedType)paramType2;
      return (equal(paramType1.getOwnerType(), paramType2.getOwnerType())) && (paramType1.getRawType().equals(paramType2.getRawType())) && (Arrays.equals(paramType1.getActualTypeArguments(), paramType2.getActualTypeArguments()));
    }
    if ((paramType1 instanceof GenericArrayType))
    {
      if (!(paramType2 instanceof GenericArrayType))
        return false;
      paramType1 = (GenericArrayType)paramType1;
      paramType2 = (GenericArrayType)paramType2;
      return equals(paramType1.getGenericComponentType(), paramType2.getGenericComponentType());
    }
    if ((paramType1 instanceof WildcardType))
    {
      if (!(paramType2 instanceof WildcardType))
        return false;
      paramType1 = (WildcardType)paramType1;
      paramType2 = (WildcardType)paramType2;
      return (Arrays.equals(paramType1.getUpperBounds(), paramType2.getUpperBounds())) && (Arrays.equals(paramType1.getLowerBounds(), paramType2.getLowerBounds()));
    }
    if ((paramType1 instanceof TypeVariable))
    {
      if (!(paramType2 instanceof TypeVariable))
        return false;
      paramType1 = (TypeVariable)paramType1;
      paramType2 = (TypeVariable)paramType2;
      return (paramType1.getGenericDeclaration() == paramType2.getGenericDeclaration()) && (paramType1.getName().equals(paramType2.getName()));
    }
    return false;
  }

  public static Type getArrayComponentType(Type paramType)
  {
    if ((paramType instanceof GenericArrayType))
      return ((GenericArrayType)paramType).getGenericComponentType();
    return ((Class)paramType).getComponentType();
  }

  public static Type getCollectionElementType(Type paramType, Class<?> paramClass)
  {
    paramClass = getSupertype(paramType, paramClass, Collection.class);
    paramType = paramClass;
    if ((paramClass instanceof WildcardType))
      paramType = ((WildcardType)paramClass).getUpperBounds()[0];
    if ((paramType instanceof ParameterizedType))
      return ((ParameterizedType)paramType).getActualTypeArguments()[0];
    return Object.class;
  }

  static Type getGenericSupertype(Type paramType, Class<?> paramClass1, Class<?> paramClass2)
  {
    if (paramClass2 == paramClass1)
      return paramType;
    if (paramClass2.isInterface())
    {
      paramType = paramClass1.getInterfaces();
      int i = 0;
      int j = paramType.length;
      while (i < j)
      {
        if (paramType[i] == paramClass2)
          return paramClass1.getGenericInterfaces()[i];
        if (paramClass2.isAssignableFrom(paramType[i]))
          return getGenericSupertype(paramClass1.getGenericInterfaces()[i], paramType[i], paramClass2);
        i += 1;
      }
    }
    if (!paramClass1.isInterface())
      while (paramClass1 != Object.class)
      {
        paramType = paramClass1.getSuperclass();
        if (paramType == paramClass2)
          return paramClass1.getGenericSuperclass();
        if (paramClass2.isAssignableFrom(paramType))
          return getGenericSupertype(paramClass1.getGenericSuperclass(), paramType, paramClass2);
        paramClass1 = paramType;
      }
    return paramClass2;
  }

  public static Type[] getMapKeyAndValueTypes(Type paramType, Class<?> paramClass)
  {
    if (paramType == Properties.class)
      return new Type[] { String.class, String.class };
    paramType = getSupertype(paramType, paramClass, Map.class);
    if ((paramType instanceof ParameterizedType))
      return ((ParameterizedType)paramType).getActualTypeArguments();
    return new Type[] { Object.class, Object.class };
  }

  public static Class<?> getRawType(Type paramType)
  {
    if ((paramType instanceof Class))
      return (Class)paramType;
    if ((paramType instanceof ParameterizedType))
    {
      paramType = ((ParameterizedType)paramType).getRawType();
      .Gson.Preconditions.checkArgument(paramType instanceof Class);
      return (Class)paramType;
    }
    if ((paramType instanceof GenericArrayType))
      return Array.newInstance(getRawType(((GenericArrayType)paramType).getGenericComponentType()), 0).getClass();
    if ((paramType instanceof TypeVariable))
      return Object.class;
    if ((paramType instanceof WildcardType))
      return getRawType(((WildcardType)paramType).getUpperBounds()[0]);
    String str;
    if (paramType == null)
      str = "null";
    else
      str = paramType.getClass().getName();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Expected a Class, ParameterizedType, or GenericArrayType, but <");
    localStringBuilder.append(paramType);
    localStringBuilder.append("> is of type ");
    localStringBuilder.append(str);
    throw new IllegalArgumentException(localStringBuilder.toString());
  }

  static Type getSupertype(Type paramType, Class<?> paramClass1, Class<?> paramClass2)
  {
    Type localType = paramType;
    if ((paramType instanceof WildcardType))
      localType = ((WildcardType)paramType).getUpperBounds()[0];
    .Gson.Preconditions.checkArgument(paramClass2.isAssignableFrom(paramClass1));
    return resolve(localType, paramClass1, getGenericSupertype(localType, paramClass1, paramClass2));
  }

  static int hashCodeOrZero(Object paramObject)
  {
    if (paramObject != null)
      return paramObject.hashCode();
    return 0;
  }

  private static int indexOf(Object[] paramArrayOfObject, Object paramObject)
  {
    int j = paramArrayOfObject.length;
    int i = 0;
    while (i < j)
    {
      if (paramObject.equals(paramArrayOfObject[i]))
        return i;
      i += 1;
    }
    throw new NoSuchElementException();
  }

  public static ParameterizedType newParameterizedTypeWithOwner(Type paramType1, Type paramType2, Type[] paramArrayOfType)
  {
    return new ParameterizedTypeImpl(paramType1, paramType2, paramArrayOfType);
  }

  public static Type resolve(Type paramType1, Class<?> paramClass, Type paramType2)
  {
    return resolve(paramType1, paramClass, paramType2, new HashSet());
  }

  private static Type resolve(Type paramType1, Class<?> paramClass, Type paramType2, Collection<TypeVariable> paramCollection)
  {
    Object localObject2;
    Object localObject1;
    while ((paramType2 instanceof TypeVariable))
    {
      localObject2 = (TypeVariable)paramType2;
      if (paramCollection.contains(localObject2))
        return paramType2;
      paramCollection.add(localObject2);
      localObject1 = resolveTypeVariable(paramType1, paramClass, (TypeVariable)localObject2);
      paramType2 = (Type)localObject1;
      if (localObject1 == localObject2)
        return localObject1;
    }
    if ((paramType2 instanceof Class))
    {
      localObject1 = (Class)paramType2;
      if (((Class)localObject1).isArray())
      {
        paramType2 = ((Class)localObject1).getComponentType();
        paramType1 = resolve(paramType1, paramClass, paramType2, paramCollection);
        if (paramType2 == paramType1)
          return localObject1;
        return arrayOf(paramType1);
      }
    }
    if ((paramType2 instanceof GenericArrayType))
    {
      paramType2 = (GenericArrayType)paramType2;
      localObject1 = paramType2.getGenericComponentType();
      paramType1 = resolve(paramType1, paramClass, (Type)localObject1, paramCollection);
      if (localObject1 == paramType1)
        return paramType2;
      return arrayOf(paramType1);
    }
    boolean bool = paramType2 instanceof ParameterizedType;
    int k = 0;
    if (bool)
    {
      localObject2 = (ParameterizedType)paramType2;
      paramType2 = ((ParameterizedType)localObject2).getOwnerType();
      Type localType1 = resolve(paramType1, paramClass, paramType2, paramCollection);
      int i;
      if (localType1 != paramType2)
        i = 1;
      else
        i = 0;
      paramType2 = ((ParameterizedType)localObject2).getActualTypeArguments();
      int m = paramType2.length;
      while (k < m)
      {
        Type localType2 = resolve(paramType1, paramClass, paramType2[k], paramCollection);
        int j = i;
        localObject1 = paramType2;
        if (localType2 != paramType2[k])
        {
          j = i;
          localObject1 = paramType2;
          if (i == 0)
          {
            localObject1 = (Type[])paramType2.clone();
            j = 1;
          }
          localObject1[k] = localType2;
        }
        k += 1;
        i = j;
        paramType2 = (Type)localObject1;
      }
      paramType1 = (Type)localObject2;
      if (i != 0)
        paramType1 = newParameterizedTypeWithOwner(localType1, ((ParameterizedType)localObject2).getRawType(), paramType2);
      return paramType1;
    }
    if ((paramType2 instanceof WildcardType))
    {
      paramType2 = (WildcardType)paramType2;
      localObject1 = paramType2.getLowerBounds();
      localObject2 = paramType2.getUpperBounds();
      if (localObject1.length == 1)
      {
        paramType1 = resolve(paramType1, paramClass, localObject1[0], paramCollection);
        if (paramType1 != localObject1[0])
          return supertypeOf(paramType1);
      }
      else if (localObject2.length == 1)
      {
        paramType1 = resolve(paramType1, paramClass, localObject2[0], paramCollection);
        if (paramType1 != localObject2[0])
          return subtypeOf(paramType1);
      }
      return paramType2;
    }
    return paramType2;
  }

  static Type resolveTypeVariable(Type paramType, Class<?> paramClass, TypeVariable<?> paramTypeVariable)
  {
    Class localClass = declaringClassOf(paramTypeVariable);
    if (localClass == null)
      return paramTypeVariable;
    paramType = getGenericSupertype(paramType, paramClass, localClass);
    if ((paramType instanceof ParameterizedType))
    {
      int i = indexOf(localClass.getTypeParameters(), paramTypeVariable);
      return ((ParameterizedType)paramType).getActualTypeArguments()[i];
    }
    return paramTypeVariable;
  }

  public static WildcardType subtypeOf(Type paramType)
  {
    if ((paramType instanceof WildcardType))
      paramType = ((WildcardType)paramType).getUpperBounds();
    else
      paramType = new Type[] { paramType };
    return new WildcardTypeImpl(paramType, EMPTY_TYPE_ARRAY);
  }

  public static WildcardType supertypeOf(Type paramType)
  {
    if ((paramType instanceof WildcardType))
      paramType = ((WildcardType)paramType).getLowerBounds();
    else
      paramType = new Type[] { paramType };
    return new WildcardTypeImpl(new Type[] { Object.class }, paramType);
  }

  public static String typeToString(Type paramType)
  {
    if ((paramType instanceof Class))
      return ((Class)paramType).getName();
    return paramType.toString();
  }

  private static final class GenericArrayTypeImpl
    implements GenericArrayType, Serializable
  {
    private static final long serialVersionUID = 0L;
    private final Type componentType;

    public GenericArrayTypeImpl(Type paramType)
    {
      this.componentType = .Gson.Types.canonicalize(paramType);
    }

    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof Serializable)) && (.Gson.Types.equals(this, (Serializable)paramObject));
    }

    public Type getGenericComponentType()
    {
      return this.componentType;
    }

    public int hashCode()
    {
      return this.componentType.hashCode();
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(.Gson.Types.typeToString(this.componentType));
      localStringBuilder.append("[]");
      return localStringBuilder.toString();
    }
  }

  private static final class ParameterizedTypeImpl
    implements ParameterizedType, Serializable
  {
    private static final long serialVersionUID = 0L;
    private final Type ownerType;
    private final Type rawType;
    private final Type[] typeArguments;

    public ParameterizedTypeImpl(Type paramType1, Type paramType2, Type[] paramArrayOfType)
    {
      boolean bool1 = paramType2 instanceof Class;
      int j = 0;
      if (bool1)
      {
        Class localClass = (Class)paramType2;
        bool1 = Modifier.isStatic(localClass.getModifiers());
        boolean bool2 = true;
        if ((!bool1) && (localClass.getEnclosingClass() != null))
          i = 0;
        else
          i = 1;
        bool1 = bool2;
        if (paramType1 == null)
          if (i != 0)
            bool1 = bool2;
          else
            bool1 = false;
        .Gson.Preconditions.checkArgument(bool1);
      }
      if (paramType1 == null)
        paramType1 = null;
      else
        paramType1 = .Gson.Types.canonicalize(paramType1);
      this.ownerType = paramType1;
      this.rawType = .Gson.Types.canonicalize(paramType2);
      this.typeArguments = ((Type[])paramArrayOfType.clone());
      int k = this.typeArguments.length;
      int i = j;
      while (i < k)
      {
        .Gson.Preconditions.checkNotNull(this.typeArguments[i]);
        .Gson.Types.checkNotPrimitive(this.typeArguments[i]);
        this.typeArguments[i] = .Gson.Types.canonicalize(this.typeArguments[i]);
        i += 1;
      }
    }

    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof Serializable)) && (.Gson.Types.equals(this, (Serializable)paramObject));
    }

    public Type[] getActualTypeArguments()
    {
      return (Type[])this.typeArguments.clone();
    }

    public Type getOwnerType()
    {
      return this.ownerType;
    }

    public Type getRawType()
    {
      return this.rawType;
    }

    public int hashCode()
    {
      return Arrays.hashCode(this.typeArguments) ^ this.rawType.hashCode() ^ .Gson.Types.hashCodeOrZero(this.ownerType);
    }

    public String toString()
    {
      int j = this.typeArguments.length;
      if (j == 0)
        return .Gson.Types.typeToString(this.rawType);
      StringBuilder localStringBuilder = new StringBuilder((j + 1) * 30);
      localStringBuilder.append(.Gson.Types.typeToString(this.rawType));
      localStringBuilder.append("<");
      localStringBuilder.append(.Gson.Types.typeToString(this.typeArguments[0]));
      int i = 1;
      while (i < j)
      {
        localStringBuilder.append(", ");
        localStringBuilder.append(.Gson.Types.typeToString(this.typeArguments[i]));
        i += 1;
      }
      localStringBuilder.append(">");
      return localStringBuilder.toString();
    }
  }

  private static final class WildcardTypeImpl
    implements WildcardType, Serializable
  {
    private static final long serialVersionUID = 0L;
    private final Type lowerBound;
    private final Type upperBound;

    public WildcardTypeImpl(Type[] paramArrayOfType1, Type[] paramArrayOfType2)
    {
      int i = paramArrayOfType2.length;
      boolean bool2 = true;
      boolean bool1;
      if (i <= 1)
        bool1 = true;
      else
        bool1 = false;
      .Gson.Preconditions.checkArgument(bool1);
      if (paramArrayOfType1.length == 1)
        bool1 = true;
      else
        bool1 = false;
      .Gson.Preconditions.checkArgument(bool1);
      if (paramArrayOfType2.length == 1)
      {
        .Gson.Preconditions.checkNotNull(paramArrayOfType2[0]);
        .Gson.Types.checkNotPrimitive(paramArrayOfType2[0]);
        if (paramArrayOfType1[0] == Object.class)
          bool1 = bool2;
        else
          bool1 = false;
        .Gson.Preconditions.checkArgument(bool1);
        this.lowerBound = .Gson.Types.canonicalize(paramArrayOfType2[0]);
        this.upperBound = Object.class;
        return;
      }
      .Gson.Preconditions.checkNotNull(paramArrayOfType1[0]);
      .Gson.Types.checkNotPrimitive(paramArrayOfType1[0]);
      this.lowerBound = null;
      this.upperBound = .Gson.Types.canonicalize(paramArrayOfType1[0]);
    }

    public boolean equals(Object paramObject)
    {
      return ((paramObject instanceof Serializable)) && (.Gson.Types.equals(this, (Serializable)paramObject));
    }

    public Type[] getLowerBounds()
    {
      if (this.lowerBound != null)
        return new Type[] { this.lowerBound };
      return .Gson.Types.EMPTY_TYPE_ARRAY;
    }

    public Type[] getUpperBounds()
    {
      return new Type[] { this.upperBound };
    }

    public int hashCode()
    {
      int i;
      if (this.lowerBound != null)
        i = this.lowerBound.hashCode() + 31;
      else
        i = 1;
      return i ^ this.upperBound.hashCode() + 31;
    }

    public String toString()
    {
      if (this.lowerBound != null)
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("? super ");
        localStringBuilder.append(.Gson.Types.typeToString(this.lowerBound));
        return localStringBuilder.toString();
      }
      if (this.upperBound == Object.class)
        return "?";
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("? extends ");
      localStringBuilder.append(.Gson.Types.typeToString(this.upperBound));
      return localStringBuilder.toString();
    }
  }
}