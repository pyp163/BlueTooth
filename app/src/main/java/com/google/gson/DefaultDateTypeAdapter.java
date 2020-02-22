package com.google.gson;

import com.google.gson.internal.JavaVersion;
import com.google.gson.internal.PreJava9DateFormatProvider;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

final class DefaultDateTypeAdapter extends TypeAdapter<java.util.Date>
{
  private static final String SIMPLE_NAME = "DefaultDateTypeAdapter";
  private final List<DateFormat> dateFormats = new ArrayList();
  private final Class<? extends java.util.Date> dateType;

  public DefaultDateTypeAdapter(int paramInt1, int paramInt2)
  {
    this(java.util.Date.class, paramInt1, paramInt2);
  }

  DefaultDateTypeAdapter(Class<? extends java.util.Date> paramClass)
  {
    this.dateType = verifyDateType(paramClass);
    this.dateFormats.add(DateFormat.getDateTimeInstance(2, 2, Locale.US));
    if (!Locale.getDefault().equals(Locale.US))
      this.dateFormats.add(DateFormat.getDateTimeInstance(2, 2));
    if (JavaVersion.isJava9OrLater())
      this.dateFormats.add(PreJava9DateFormatProvider.getUSDateTimeFormat(2, 2));
  }

  DefaultDateTypeAdapter(Class<? extends java.util.Date> paramClass, int paramInt)
  {
    this.dateType = verifyDateType(paramClass);
    this.dateFormats.add(DateFormat.getDateInstance(paramInt, Locale.US));
    if (!Locale.getDefault().equals(Locale.US))
      this.dateFormats.add(DateFormat.getDateInstance(paramInt));
    if (JavaVersion.isJava9OrLater())
      this.dateFormats.add(PreJava9DateFormatProvider.getUSDateFormat(paramInt));
  }

  public DefaultDateTypeAdapter(Class<? extends java.util.Date> paramClass, int paramInt1, int paramInt2)
  {
    this.dateType = verifyDateType(paramClass);
    this.dateFormats.add(DateFormat.getDateTimeInstance(paramInt1, paramInt2, Locale.US));
    if (!Locale.getDefault().equals(Locale.US))
      this.dateFormats.add(DateFormat.getDateTimeInstance(paramInt1, paramInt2));
    if (JavaVersion.isJava9OrLater())
      this.dateFormats.add(PreJava9DateFormatProvider.getUSDateTimeFormat(paramInt1, paramInt2));
  }

  DefaultDateTypeAdapter(Class<? extends java.util.Date> paramClass, String paramString)
  {
    this.dateType = verifyDateType(paramClass);
    this.dateFormats.add(new SimpleDateFormat(paramString, Locale.US));
    if (!Locale.getDefault().equals(Locale.US))
      this.dateFormats.add(new SimpleDateFormat(paramString));
  }

  private java.util.Date deserializeToDate(String paramString)
  {
    while (true)
    {
      Object localObject1;
      Object localObject2;
      synchronized (this.dateFormats)
      {
        localObject1 = this.dateFormats.iterator();
        if (((Iterator)localObject1).hasNext())
          localObject2 = (DateFormat)((Iterator)localObject1).next();
      }
      try
      {
        localObject2 = ((DateFormat)localObject2).parse(paramString);
        return localObject2;
        try
        {
          localObject1 = ISO8601Utils.parse(paramString, new ParsePosition(0));
          return localObject1;
        }
        catch (ParseException localParseException1)
        {
          throw new JsonSyntaxException(paramString, localParseException1);
        }
        paramString = finally;
        throw paramString;
      }
      catch (ParseException localParseException2)
      {
      }
    }
  }

  private static Class<? extends java.util.Date> verifyDateType(Class<? extends java.util.Date> paramClass)
  {
    if ((paramClass != java.util.Date.class) && (paramClass != java.sql.Date.class) && (paramClass != Timestamp.class))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Date type must be one of ");
      localStringBuilder.append(java.util.Date.class);
      localStringBuilder.append(", ");
      localStringBuilder.append(Timestamp.class);
      localStringBuilder.append(", or ");
      localStringBuilder.append(java.sql.Date.class);
      localStringBuilder.append(" but was ");
      localStringBuilder.append(paramClass);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    return paramClass;
  }

  public java.util.Date read(JsonReader paramJsonReader)
    throws IOException
  {
    if (paramJsonReader.peek() == JsonToken.NULL)
    {
      paramJsonReader.nextNull();
      return null;
    }
    paramJsonReader = deserializeToDate(paramJsonReader.nextString());
    if (this.dateType == java.util.Date.class)
      return paramJsonReader;
    if (this.dateType == Timestamp.class)
      return new Timestamp(paramJsonReader.getTime());
    if (this.dateType == java.sql.Date.class)
      return new java.sql.Date(paramJsonReader.getTime());
    throw new AssertionError();
  }

  public String toString()
  {
    DateFormat localDateFormat = (DateFormat)this.dateFormats.get(0);
    if ((localDateFormat instanceof SimpleDateFormat))
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("DefaultDateTypeAdapter(");
      localStringBuilder.append(((SimpleDateFormat)localDateFormat).toPattern());
      localStringBuilder.append(')');
      return localStringBuilder.toString();
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("DefaultDateTypeAdapter(");
    localStringBuilder.append(localDateFormat.getClass().getSimpleName());
    localStringBuilder.append(')');
    return localStringBuilder.toString();
  }

  public void write(JsonWriter paramJsonWriter, java.util.Date paramDate)
    throws IOException
  {
    if (paramDate == null)
    {
      paramJsonWriter.nullValue();
      return;
    }
    synchronized (this.dateFormats)
    {
      paramJsonWriter.value(((DateFormat)this.dateFormats.get(0)).format(paramDate));
      return;
    }
  }
}