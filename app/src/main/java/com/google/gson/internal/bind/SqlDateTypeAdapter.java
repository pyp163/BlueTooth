package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class SqlDateTypeAdapter extends TypeAdapter<java.sql.Date>
{
  public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory()
  {
    public <T> TypeAdapter<T> create(Gson paramAnonymousGson, TypeToken<T> paramAnonymousTypeToken)
    {
      if (paramAnonymousTypeToken.getRawType() == java.sql.Date.class)
        return new SqlDateTypeAdapter();
      return null;
    }
  };
  private final DateFormat format = new SimpleDateFormat("MMM d, yyyy");

  public java.sql.Date read(JsonReader paramJsonReader)
    throws IOException
  {
    try
    {
      if (paramJsonReader.peek() == JsonToken.NULL)
      {
        paramJsonReader.nextNull();
        return null;
      }
      try
      {
        paramJsonReader = new java.sql.Date(this.format.parse(paramJsonReader.nextString()).getTime());
        return paramJsonReader;
      }
      catch (ParseException paramJsonReader)
      {
        throw new JsonSyntaxException(paramJsonReader);
      }
    }
    finally
    {
    }
    throw paramJsonReader;
  }

  public void write(JsonWriter paramJsonWriter, java.sql.Date paramDate)
    throws IOException
  {
    if (paramDate == null)
      paramDate = null;
    try
    {
      paramDate = this.format.format(paramDate);
      paramJsonWriter.value(paramDate);
      return;
    }
    finally
    {
    }
    throw paramJsonWriter;
  }
}