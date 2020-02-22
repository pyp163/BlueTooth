package com.google.gson;

import com.google.gson.internal.Streams;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.MalformedJsonException;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class JsonStreamParser
  implements Iterator<JsonElement>
{
  private final Object lock;
  private final JsonReader parser;

  public JsonStreamParser(Reader paramReader)
  {
    this.parser = new JsonReader(paramReader);
    this.parser.setLenient(true);
    this.lock = new Object();
  }

  public JsonStreamParser(String paramString)
  {
    this(new StringReader(paramString));
  }

  public boolean hasNext()
  {
    synchronized (this.lock)
    {
      try
      {
        JsonToken localJsonToken1 = this.parser.peek();
        JsonToken localJsonToken2 = JsonToken.END_DOCUMENT;
        boolean bool;
        if (localJsonToken1 != localJsonToken2)
          bool = true;
        else
          bool = false;
        return bool;
      }
      catch (IOException localIOException)
      {
        throw new JsonIOException(localIOException);
      }
      catch (MalformedJsonException localMalformedJsonException)
      {
        throw new JsonSyntaxException(localMalformedJsonException);
      }
      throw localMalformedJsonException;
    }
  }

  public JsonElement next()
    throws JsonParseException
  {
    if (!hasNext())
      throw new NoSuchElementException();
    try
    {
      localObject = Streams.parse(this.parser);
      return localObject;
    }
    catch (JsonParseException localJsonParseException)
    {
      Object localObject = localJsonParseException;
      if ((localJsonParseException.getCause() instanceof EOFException))
        localObject = new NoSuchElementException();
      throw ((Throwable)localObject);
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      throw new JsonParseException("Failed parsing JSON source to Json", localOutOfMemoryError);
    }
    catch (StackOverflowError localStackOverflowError)
    {
      throw new JsonParseException("Failed parsing JSON source to Json", localStackOverflowError);
    }
  }

  public void remove()
  {
    throw new UnsupportedOperationException();
  }
}