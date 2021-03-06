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
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class TimeTypeAdapter extends TypeAdapter<Time>
{
  public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory()
  {
    public <T> TypeAdapter<T> create(Gson paramAnonymousGson, TypeToken<T> paramAnonymousTypeToken)
    {
      if (paramAnonymousTypeToken.getRawType() == Time.class)
        return new TimeTypeAdapter();
      return null;
    }
  };
  private final DateFormat format = new SimpleDateFormat("hh:mm:ss a");

  public Time read(JsonReader paramJsonReader)
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
        paramJsonReader = new Time(this.format.parse(paramJsonReader.nextString()).getTime());
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

  public void write(JsonWriter paramJsonWriter, Time paramTime)
    throws IOException
  {
    if (paramTime == null)
      paramTime = null;
    try
    {
      paramTime = this.format.format(paramTime);
      paramJsonWriter.value(paramTime);
      return;
    }
    finally
    {
    }
    throw paramJsonWriter;
  }
}