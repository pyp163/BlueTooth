package okhttp3.internal.tls;

import javax.security.auth.x500.X500Principal;

final class DistinguishedNameParser
{
  private int beg;
  private char[] chars;
  private int cur;
  private final String dn;
  private int end;
  private final int length;
  private int pos;

  DistinguishedNameParser(X500Principal paramX500Principal)
  {
    this.dn = paramX500Principal.getName("RFC2253");
    this.length = this.dn.length();
  }

  private String escapedAV()
  {
    this.beg = this.pos;
    this.end = this.pos;
    label208: 
    do
    {
      while (true)
      {
        if (this.pos >= this.length)
          return new String(this.chars, this.beg, this.end - this.beg);
        i = this.chars[this.pos];
        if (i == 32)
          break label208;
        if (i == 59)
          break;
        if (i != 92);
        switch (i)
        {
        default:
          arrayOfChar = this.chars;
          i = this.end;
          this.end = (i + 1);
          arrayOfChar[i] = this.chars[this.pos];
          this.pos += 1;
          continue;
          arrayOfChar = this.chars;
          i = this.end;
          this.end = (i + 1);
          arrayOfChar[i] = getEscaped();
          this.pos += 1;
        case 43:
        case 44:
        }
      }
      return new String(this.chars, this.beg, this.end - this.beg);
      this.cur = this.end;
      this.pos += 1;
      char[] arrayOfChar = this.chars;
      int i = this.end;
      this.end = (i + 1);
      arrayOfChar[i] = ' ';
      while ((this.pos < this.length) && (this.chars[this.pos] == ' '))
      {
        arrayOfChar = this.chars;
        i = this.end;
        this.end = (i + 1);
        arrayOfChar[i] = ' ';
        this.pos += 1;
      }
    }
    while ((this.pos != this.length) && (this.chars[this.pos] != ',') && (this.chars[this.pos] != '+') && (this.chars[this.pos] != ';'));
    return new String(this.chars, this.beg, this.cur - this.beg);
  }

  private int getByte(int paramInt)
  {
    int i = paramInt + 1;
    if (i >= this.length)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Malformed DN: ");
      localStringBuilder.append(this.dn);
      throw new IllegalStateException(localStringBuilder.toString());
    }
    paramInt = this.chars[paramInt];
    if ((paramInt >= 48) && (paramInt <= 57))
    {
      paramInt -= 48;
    }
    else if ((paramInt >= 97) && (paramInt <= 102))
    {
      paramInt -= 87;
    }
    else
    {
      if ((paramInt < 65) || (paramInt > 70))
        break label218;
      paramInt -= 55;
    }
    i = this.chars[i];
    if ((i >= 48) && (i <= 57))
    {
      i -= 48;
    }
    else if ((i >= 97) && (i <= 102))
    {
      i -= 87;
    }
    else
    {
      if ((i < 65) || (i > 70))
        break label182;
      i -= 55;
    }
    return (paramInt << 4) + i;
    label182: StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Malformed DN: ");
    localStringBuilder.append(this.dn);
    throw new IllegalStateException(localStringBuilder.toString());
    label218: localStringBuilder = new StringBuilder();
    localStringBuilder.append("Malformed DN: ");
    localStringBuilder.append(this.dn);
    throw new IllegalStateException(localStringBuilder.toString());
  }

  private char getEscaped()
  {
    this.pos += 1;
    if (this.pos == this.length)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Unexpected end of DN: ");
      localStringBuilder.append(this.dn);
      throw new IllegalStateException(localStringBuilder.toString());
    }
    int i = this.chars[this.pos];
    if ((i != 32) && (i != 37) && (i != 92) && (i != 95))
      switch (i)
      {
      default:
        switch (i)
        {
        default:
          switch (i)
          {
          default:
            return getUTF8();
          case 59:
          case 60:
          case 61:
          case 62:
          }
          break;
        case 42:
        case 43:
        case 44:
        }
        break;
      case 34:
      case 35:
      }
    return this.chars[this.pos];
  }

  private char getUTF8()
  {
    int i = getByte(this.pos);
    this.pos += 1;
    if (i < 128)
      return (char)i;
    if ((i >= 192) && (i <= 247))
    {
      int j;
      if (i <= 223)
      {
        i &= 31;
        j = 1;
      }
      else if (i <= 239)
      {
        j = 2;
        i &= 15;
      }
      else
      {
        j = 3;
        i &= 7;
      }
      int k = 0;
      while (k < j)
      {
        this.pos += 1;
        if (this.pos != this.length)
        {
          if (this.chars[this.pos] != '\\')
            return '?';
          this.pos += 1;
          int m = getByte(this.pos);
          this.pos += 1;
          if ((m & 0xC0) != 128)
            return '?';
          i = (i << 6) + (m & 0x3F);
          k += 1;
        }
        else
        {
          return '?';
        }
      }
      return (char)i;
    }
    return '?';
  }

  private String hexAV()
  {
    if (this.pos + 4 >= this.length)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Unexpected end of DN: ");
      ((StringBuilder)localObject).append(this.dn);
      throw new IllegalStateException(((StringBuilder)localObject).toString());
    }
    this.beg = this.pos;
    int i;
    for (this.pos += 1; (this.pos != this.length) && (this.chars[this.pos] != '+') && (this.chars[this.pos] != ',') && (this.chars[this.pos] != ';'); this.pos += 1)
    {
      if (this.chars[this.pos] == ' ')
      {
        this.end = this.pos;
        for (this.pos += 1; (this.pos < this.length) && (this.chars[this.pos] == ' '); this.pos += 1);
      }
      if ((this.chars[this.pos] >= 'A') && (this.chars[this.pos] <= 'F'))
      {
        localObject = this.chars;
        i = this.pos;
        localObject[i] = ((char)(localObject[i] + ' '));
      }
    }
    this.end = this.pos;
    int k = this.end - this.beg;
    if ((k >= 5) && ((k & 0x1) != 0))
    {
      localObject = new byte[k / 2];
      i = 0;
      int j = this.beg + 1;
      while (i < localObject.length)
      {
        localObject[i] = ((byte)getByte(j));
        j += 2;
        i += 1;
      }
      return new String(this.chars, this.beg, k);
    }
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Unexpected end of DN: ");
    ((StringBuilder)localObject).append(this.dn);
    throw new IllegalStateException(((StringBuilder)localObject).toString());
  }

  private String nextAT()
  {
    while ((this.pos < this.length) && (this.chars[this.pos] == ' '))
      this.pos += 1;
    if (this.pos == this.length)
      return null;
    this.beg = this.pos;
    for (this.pos += 1; (this.pos < this.length) && (this.chars[this.pos] != '=') && (this.chars[this.pos] != ' '); this.pos += 1);
    StringBuilder localStringBuilder;
    if (this.pos >= this.length)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Unexpected end of DN: ");
      localStringBuilder.append(this.dn);
      throw new IllegalStateException(localStringBuilder.toString());
    }
    this.end = this.pos;
    if (this.chars[this.pos] == ' ')
    {
      while ((this.pos < this.length) && (this.chars[this.pos] != '=') && (this.chars[this.pos] == ' '))
        this.pos += 1;
      if ((this.chars[this.pos] != '=') || (this.pos == this.length))
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("Unexpected end of DN: ");
        localStringBuilder.append(this.dn);
        throw new IllegalStateException(localStringBuilder.toString());
      }
    }
    for (this.pos += 1; (this.pos < this.length) && (this.chars[this.pos] == ' '); this.pos += 1);
    if ((this.end - this.beg > 4) && (this.chars[(this.beg + 3)] == '.') && ((this.chars[this.beg] == 'O') || (this.chars[this.beg] == 'o')) && ((this.chars[(this.beg + 1)] == 'I') || (this.chars[(this.beg + 1)] == 'i')) && ((this.chars[(this.beg + 2)] == 'D') || (this.chars[(this.beg + 2)] == 'd')))
      this.beg += 4;
    return new String(this.chars, this.beg, this.end - this.beg);
  }

  private String quotedAV()
  {
    this.pos += 1;
    this.beg = this.pos;
    for (this.end = this.beg; ; this.end += 1)
    {
      if (this.pos == this.length)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Unexpected end of DN: ");
        localStringBuilder.append(this.dn);
        throw new IllegalStateException(localStringBuilder.toString());
      }
      if (this.chars[this.pos] == '"')
      {
        for (this.pos += 1; (this.pos < this.length) && (this.chars[this.pos] == ' '); this.pos += 1);
        return new String(this.chars, this.beg, this.end - this.beg);
      }
      if (this.chars[this.pos] == '\\')
        this.chars[this.end] = getEscaped();
      else
        this.chars[this.end] = this.chars[this.pos];
      this.pos += 1;
    }
  }

  public String findMostSpecific(String paramString)
  {
    this.pos = 0;
    this.beg = 0;
    this.end = 0;
    this.cur = 0;
    this.chars = this.dn.toCharArray();
    String str1 = nextAT();
    String str2 = str1;
    if (str1 == null)
      return null;
    do
    {
      str1 = "";
      if (this.pos == this.length)
        return null;
      switch (this.chars[this.pos])
      {
      default:
        str1 = escapedAV();
        break;
      case '#':
        str1 = hexAV();
        break;
      case '"':
        str1 = quotedAV();
      case '+':
      case ',':
      case ';':
      }
      if (paramString.equalsIgnoreCase(str2))
        return str1;
      if (this.pos >= this.length)
        return null;
      if ((this.chars[this.pos] != ',') && (this.chars[this.pos] != ';') && (this.chars[this.pos] != '+'))
      {
        paramString = new StringBuilder();
        paramString.append("Malformed DN: ");
        paramString.append(this.dn);
        throw new IllegalStateException(paramString.toString());
      }
      this.pos += 1;
      str1 = nextAT();
      str2 = str1;
    }
    while (str1 != null);
    paramString = new StringBuilder();
    paramString.append("Malformed DN: ");
    paramString.append(this.dn);
    throw new IllegalStateException(paramString.toString());
  }
}