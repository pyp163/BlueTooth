package org.apache.commons.net.telnet;

public class WindowSizeOptionHandler extends TelnetOptionHandler
{
  protected static final int WINDOW_SIZE = 31;
  private int m_nHeight = 24;
  private int m_nWidth = 80;

  public WindowSizeOptionHandler(int paramInt1, int paramInt2)
  {
    super(31, false, false, false, false);
    this.m_nWidth = paramInt1;
    this.m_nHeight = paramInt2;
  }

  public WindowSizeOptionHandler(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    super(31, paramBoolean1, paramBoolean2, paramBoolean3, paramBoolean4);
    this.m_nWidth = paramInt1;
    this.m_nHeight = paramInt2;
  }

  public int[] answerSubnegotiation(int[] paramArrayOfInt, int paramInt)
  {
    return null;
  }

  public int[] startSubnegotiationLocal()
  {
    int n = this.m_nWidth;
    int i1 = this.m_nHeight;
    if (this.m_nWidth % 256 == 255)
      j = 6;
    else
      j = 5;
    int i = j;
    if (this.m_nWidth / 256 == 255)
      i = j + 1;
    int j = i;
    if (this.m_nHeight % 256 == 255)
      j = i + 1;
    int k = j;
    if (this.m_nHeight / 256 == 255)
      k = j + 1;
    int[] arrayOfInt = new int[k];
    arrayOfInt[0] = 31;
    i = 1;
    j = 24;
    while (i < k)
    {
      arrayOfInt[i] = ((255 << j & n * 65536 + i1) >>> j);
      int m = i;
      if (arrayOfInt[i] == 255)
      {
        m = i + 1;
        arrayOfInt[m] = 255;
      }
      i = m + 1;
      j -= 8;
    }
    return arrayOfInt;
  }

  public int[] startSubnegotiationRemote()
  {
    return null;
  }
}