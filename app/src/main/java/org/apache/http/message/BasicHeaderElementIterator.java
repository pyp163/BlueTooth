package org.apache.http.message;

import java.util.NoSuchElementException;
import org.apache.http.FormattedHeader;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HeaderIterator;
import org.apache.http.util.CharArrayBuffer;

public class BasicHeaderElementIterator
  implements HeaderElementIterator
{
  private CharArrayBuffer buffer = null;
  private HeaderElement currentElement = null;
  private ParserCursor cursor = null;
  private final HeaderIterator headerIt;
  private final HeaderValueParser parser;

  public BasicHeaderElementIterator(HeaderIterator paramHeaderIterator)
  {
    this(paramHeaderIterator, BasicHeaderValueParser.DEFAULT);
  }

  public BasicHeaderElementIterator(HeaderIterator paramHeaderIterator, HeaderValueParser paramHeaderValueParser)
  {
    if (paramHeaderIterator == null)
      throw new IllegalArgumentException("Header iterator may not be null");
    if (paramHeaderValueParser == null)
      throw new IllegalArgumentException("Parser may not be null");
    this.headerIt = paramHeaderIterator;
    this.parser = paramHeaderValueParser;
  }

  private void bufferHeaderValue()
  {
    this.cursor = null;
    this.buffer = null;
    while (this.headerIt.hasNext())
    {
      Object localObject = this.headerIt.nextHeader();
      if ((localObject instanceof FormattedHeader))
      {
        localObject = (FormattedHeader)localObject;
        this.buffer = ((FormattedHeader)localObject).getBuffer();
        this.cursor = new ParserCursor(0, this.buffer.length());
        this.cursor.updatePos(((FormattedHeader)localObject).getValuePos());
        return;
      }
      localObject = ((Header)localObject).getValue();
      if (localObject != null)
      {
        this.buffer = new CharArrayBuffer(((String)localObject).length());
        this.buffer.append((String)localObject);
        this.cursor = new ParserCursor(0, this.buffer.length());
      }
    }
  }

  private void parseNextElement()
  {
    while (true)
    {
      if ((!this.headerIt.hasNext()) && (this.cursor == null))
        return;
      if ((this.cursor == null) || (this.cursor.atEnd()))
        bufferHeaderValue();
      if (this.cursor != null)
      {
        while (!this.cursor.atEnd())
        {
          HeaderElement localHeaderElement = this.parser.parseHeaderElement(this.buffer, this.cursor);
          if ((localHeaderElement.getName().length() != 0) || (localHeaderElement.getValue() != null))
          {
            this.currentElement = localHeaderElement;
            return;
          }
        }
        if (this.cursor.atEnd())
        {
          this.cursor = null;
          this.buffer = null;
        }
      }
    }
  }

  public boolean hasNext()
  {
    if (this.currentElement == null)
      parseNextElement();
    return this.currentElement != null;
  }

  public final Object next()
    throws NoSuchElementException
  {
    return nextElement();
  }

  public HeaderElement nextElement()
    throws NoSuchElementException
  {
    if (this.currentElement == null)
      parseNextElement();
    if (this.currentElement == null)
      throw new NoSuchElementException("No more header elements available");
    HeaderElement localHeaderElement = this.currentElement;
    this.currentElement = null;
    return localHeaderElement;
  }

  public void remove()
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException("Remove not supported");
  }
}