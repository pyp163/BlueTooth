package org.apache.http.message;

import java.util.NoSuchElementException;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.ParseException;
import org.apache.http.TokenIterator;

public class BasicTokenIterator
  implements TokenIterator
{
  public static final String HTTP_SEPARATORS = " ,;=()<>@:\\\"/[]?{}\t";
  protected String currentHeader;
  protected String currentToken;
  protected final HeaderIterator headerIt;
  protected int searchPos;

  public BasicTokenIterator(HeaderIterator paramHeaderIterator)
  {
    if (paramHeaderIterator == null)
      throw new IllegalArgumentException("Header iterator must not be null.");
    this.headerIt = paramHeaderIterator;
    this.searchPos = findNext(-1);
  }

  protected String createToken(String paramString, int paramInt1, int paramInt2)
  {
    return paramString.substring(paramInt1, paramInt2);
  }

  protected int findNext(int paramInt)
    throws ParseException
  {
    if (paramInt < 0)
    {
      if (!this.headerIt.hasNext())
        return -1;
      this.currentHeader = this.headerIt.nextHeader().getValue();
      paramInt = 0;
    }
    else
    {
      paramInt = findTokenSeparator(paramInt);
    }
    paramInt = findTokenStart(paramInt);
    if (paramInt < 0)
    {
      this.currentToken = null;
      return -1;
    }
    int i = findTokenEnd(paramInt);
    this.currentToken = createToken(this.currentHeader, paramInt, i);
    return i;
  }

  protected int findTokenEnd(int paramInt)
  {
    if (paramInt < 0)
    {
      StringBuffer localStringBuffer = new StringBuffer();
      localStringBuffer.append("Token start position must not be negative: ");
      localStringBuffer.append(paramInt);
      throw new IllegalArgumentException(localStringBuffer.toString());
    }
    int i = this.currentHeader.length();
    paramInt += 1;
    while ((paramInt < i) && (isTokenChar(this.currentHeader.charAt(paramInt))))
      paramInt += 1;
    return paramInt;
  }

  protected int findTokenSeparator(int paramInt)
  {
    StringBuffer localStringBuffer;
    if (paramInt < 0)
    {
      localStringBuffer = new StringBuffer();
      localStringBuffer.append("Search position must not be negative: ");
      localStringBuffer.append(paramInt);
      throw new IllegalArgumentException(localStringBuffer.toString());
    }
    int i = 0;
    int j = this.currentHeader.length();
    while ((i == 0) && (paramInt < j))
    {
      char c = this.currentHeader.charAt(paramInt);
      if (isTokenSeparator(c))
      {
        i = 1;
      }
      else if (isWhitespace(c))
      {
        paramInt += 1;
      }
      else
      {
        if (isTokenChar(c))
        {
          localStringBuffer = new StringBuffer();
          localStringBuffer.append("Tokens without separator (pos ");
          localStringBuffer.append(paramInt);
          localStringBuffer.append("): ");
          localStringBuffer.append(this.currentHeader);
          throw new ParseException(localStringBuffer.toString());
        }
        localStringBuffer = new StringBuffer();
        localStringBuffer.append("Invalid character after token (pos ");
        localStringBuffer.append(paramInt);
        localStringBuffer.append("): ");
        localStringBuffer.append(this.currentHeader);
        throw new ParseException(localStringBuffer.toString());
      }
    }
    return paramInt;
  }

  protected int findTokenStart(int paramInt)
  {
    StringBuffer localStringBuffer;
    if (paramInt < 0)
    {
      localStringBuffer = new StringBuffer();
      localStringBuffer.append("Search position must not be negative: ");
      localStringBuffer.append(paramInt);
      throw new IllegalArgumentException(localStringBuffer.toString());
    }
    int j = 0;
    while ((j == 0) && (this.currentHeader != null))
    {
      int m = this.currentHeader.length();
      int i = j;
      int k = paramInt;
      while ((i == 0) && (k < m))
      {
        char c = this.currentHeader.charAt(k);
        if ((!isTokenSeparator(c)) && (!isWhitespace(c)))
        {
          if (isTokenChar(this.currentHeader.charAt(k)))
          {
            i = 1;
          }
          else
          {
            localStringBuffer = new StringBuffer();
            localStringBuffer.append("Invalid character before token (pos ");
            localStringBuffer.append(k);
            localStringBuffer.append("): ");
            localStringBuffer.append(this.currentHeader);
            throw new ParseException(localStringBuffer.toString());
          }
        }
        else
          k += 1;
      }
      paramInt = k;
      j = i;
      if (i == 0)
        if (this.headerIt.hasNext())
        {
          this.currentHeader = this.headerIt.nextHeader().getValue();
          paramInt = 0;
          j = i;
        }
        else
        {
          this.currentHeader = null;
          paramInt = k;
          j = i;
        }
    }
    if (j != 0)
      return paramInt;
    return -1;
  }

  public boolean hasNext()
  {
    return this.currentToken != null;
  }

  protected boolean isHttpSeparator(char paramChar)
  {
    return " ,;=()<>@:\\\"/[]?{}\t".indexOf(paramChar) >= 0;
  }

  protected boolean isTokenChar(char paramChar)
  {
    if (Character.isLetterOrDigit(paramChar))
      return true;
    if (Character.isISOControl(paramChar))
      return false;
    return !isHttpSeparator(paramChar);
  }

  protected boolean isTokenSeparator(char paramChar)
  {
    return paramChar == ',';
  }

  protected boolean isWhitespace(char paramChar)
  {
    return (paramChar == '\t') || (Character.isSpaceChar(paramChar));
  }

  public final Object next()
    throws NoSuchElementException, ParseException
  {
    return nextToken();
  }

  public String nextToken()
    throws NoSuchElementException, ParseException
  {
    if (this.currentToken == null)
      throw new NoSuchElementException("Iteration already finished.");
    String str = this.currentToken;
    this.searchPos = findNext(this.searchPos);
    return str;
  }

  public final void remove()
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException("Removing tokens is not supported.");
  }
}