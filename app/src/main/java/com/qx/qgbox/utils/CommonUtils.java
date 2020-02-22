package com.qx.qgbox.utils;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.AdaptiveIconDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.qx.qgbox.entitys.ComKey;
import com.qx.qgbox.entitys.GPKey;
import com.qx.qgbox.entitys.MyKeyMap;
import com.qx.qgbox.entitys.NewBluetoothDevice;
import com.qx.qgbox.entitys.OfficialGamePreset;
import com.qx.qgbox.entitys.SunyesMaxGamePreset;
import com.qx.qgbox.gamemouse.Setupitem;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils
{
  static byte[] encrpyArray0 = { -70, -118, 38, 73, 82, 73, -39, -2, 68, -77, 99, 40, -4, -115, 38, -44, 69 };
  static byte[] encrpyArray1 = { -40, 23, -16, -91, -100, 56, 97, 108, 104, -112, -48, -34, 26, 94, 97, 62, -95 };
  static byte[] encrpyArray2 = { -9, -31, -35, 120, -79, 45, -122, -14, -96, -128, 74, 54, 2, -11, 52, 17, -96 };
  static byte[] encrpyArray3 = { 117, -60, -21, -34, -10, -35, -14, -79, -116, 83, 58, 103, -124, -38, 48, -98, 109 };
  static byte[] encrpyArray4 = { 23, -70, 56, 127, 41, 23, 121, -55, 64, -124, -99, 56, 68, 42, 24, -74, 71 };
  static byte[] encrpyArray5 = { -35, 80, -75, 113, -79, 43, -4, 121, 109, -88, 41, -117, 72, 26, -100, 125, -83 };
  static byte[] encrpyArray6 = { 82, 115, -123, -22, -24, 20, -80, 68, -111, 16, 67, -40, -4, 12, 60, -17, 94 };
  static byte[] encrpyArray7 = { 38, 116, 83, 22, 92, 65, 77, 94, 121, -62, 26, 11, 49, -125, -102, 112, -101 };

  public static int CRC_GetModbus16(byte[] paramArrayOfByte, int paramInt)
  {
    return GetCrc_16(paramArrayOfByte, paramInt, 65535, new int[] { 0, 49345, 49537, 320, 49921, 960, 640, 49729, 50689, 1728, 1920, 51009, 1280, 50625, 50305, 1088, 52225, 3264, 3456, 52545, 3840, 53185, 52865, 3648, 2560, 51905, 52097, 2880, 51457, 2496, 2176, 51265, 55297, 6336, 6528, 55617, 6912, 56257, 55937, 6720, 7680, 57025, 57217, 8000, 56577, 7616, 7296, 56385, 5120, 54465, 54657, 5440, 55041, 6080, 5760, 54849, 53761, 4800, 4992, 54081, 4352, 53697, 53377, 4160, 61441, 12480, 12672, 61761, 13056, 62401, 62081, 12864, 13824, 63169, 63361, 14144, 62721, 13760, 13440, 62529, 15360, 64705, 64897, 15680, 65281, 16320, 16000, 65089, 64001, 15040, 15232, 64321, 14592, 63937, 63617, 14400, 10240, 59585, 59777, 10560, 60161, 11200, 10880, 59969, 60929, 11968, 12160, 61249, 11520, 60865, 60545, 11328, 58369, 9408, 9600, 58689, 9984, 59329, 59009, 9792, 8704, 58049, 58241, 9024, 57601, 8640, 8320, 57409, 40961, 24768, 24960, 41281, 25344, 41921, 41601, 25152, 26112, 42689, 42881, 26432, 42241, 26048, 25728, 42049, 27648, 44225, 44417, 27968, 44801, 28608, 28288, 44609, 43521, 27328, 27520, 43841, 26880, 43457, 43137, 26688, 30720, 47297, 47489, 31040, 47873, 31680, 31360, 47681, 48641, 32448, 32640, 48961, 32000, 48577, 48257, 31808, 46081, 29888, 30080, 46401, 30464, 47041, 46721, 30272, 29184, 45761, 45953, 29504, 45313, 29120, 28800, 45121, 20480, 37057, 37249, 20800, 37633, 21440, 21120, 37441, 38401, 22208, 22400, 38721, 21760, 38337, 38017, 21568, 39937, 23744, 23936, 40257, 24320, 40897, 40577, 24128, 23040, 39617, 39809, 23360, 39169, 22976, 22656, 38977, 34817, 18624, 18816, 35137, 19200, 35777, 35457, 19008, 19968, 36545, 36737, 20288, 36097, 19904, 19584, 35905, 17408, 33985, 34177, 17728, 34561, 18368, 18048, 34369, 33281, 17088, 17280, 33601, 16640, 33217, 32897, 16448 });
  }

  public static int CalcCRC16(byte[] paramArrayOfByte, int paramInt)
  {
    int k = 65535;
    int i = 0;
    int j = paramInt;
    paramInt = k;
    while (j > 0)
    {
      k = j - 1;
      paramInt ^= paramArrayOfByte[i] & 0xFF;
      j = 0;
      while (j < 8)
      {
        if ((paramInt & 0x1) == 1)
          paramInt = paramInt >> 1 ^ 0xA001;
        else
          paramInt >>= 1;
        j += 1;
      }
      i += 1;
      j = k;
    }
    return paramInt;
  }

  public static int GetCrc_16(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int[] paramArrayOfInt)
  {
    int j = 0;
    int i = paramInt2;
    paramInt2 = j;
    while (paramInt1 > 0)
    {
      i = i >> 8 ^ paramArrayOfInt[((i & 0xFF ^ paramArrayOfByte[paramInt2]) & 0xFF)];
      paramInt2 += 1;
      paramInt1 -= 1;
    }
    return i;
  }

  public static int GetRevCrc_16(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int[] paramArrayOfInt)
  {
    int j = 0;
    int i = paramInt2;
    paramInt2 = j;
    while (paramInt1 > 0)
    {
      i = i << 8 ^ paramArrayOfInt[((i >> 8 ^ paramArrayOfByte[paramInt2]) & 0xFF)];
      paramInt2 += 1;
      paramInt1 -= 1;
    }
    return i;
  }

  public static String ReadStream(InputStream paramInputStream)
    throws Exception
  {
    byte[] arrayOfByte = new byte[1024];
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    while (true)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i <= 0)
        break;
      localByteArrayOutputStream.write(arrayOfByte, 0, i);
    }
    paramInputStream.close();
    return new String(localByteArrayOutputStream.toByteArray());
  }

  public static int analysePoint(int paramInt)
  {
    int j = (paramInt * paramInt + 3) / 7;
    int i = j;
    if (paramInt < 0)
      i = j * -1;
    return i;
  }

  public static int analysePoint2(int paramInt1, int paramInt2)
  {
    int m = Math.abs(paramInt1);
    int i = 3;
    if (m < 3)
      return paramInt1;
    int k = 0;
    int j = 0;
    if ((m != 3) && (m != 4))
    {
      if ((m != 5) && (m != 6))
      {
        if ((m != 7) && (m != 8))
        {
          if (m == 9)
          {
            paramInt2 = j;
            if (paramInt1 > 0)
              paramInt2 = 5;
            if (paramInt1 < 0)
              return -5;
          }
          else
          {
            switch (paramInt2)
            {
            default:
              return paramInt1 * 1;
            case 8:
              return (int)(paramInt1 * 0.9D);
            case 7:
              return (int)(paramInt1 * 0.8D);
            case 6:
              return (int)(paramInt1 * 0.7D);
            case 5:
              return (int)(paramInt1 * 0.6D);
            case 4:
              return (int)(paramInt1 * 0.5D);
            case 3:
              return (int)(paramInt1 * 0.4D);
            case 2:
              return (int)(paramInt1 * 0.3D);
            case 1:
              return (int)(paramInt1 * 0.2D);
            case 0:
            }
            return (int)(paramInt1 * 0.2D);
          }
        }
        else
        {
          paramInt2 = k;
          if (paramInt1 > 0)
            paramInt2 = 4;
          if (paramInt1 < 0)
            return -4;
        }
        return paramInt2;
      }
      if (paramInt1 > 0)
        paramInt2 = i;
      else
        paramInt2 = 0;
      if (paramInt1 < 0)
        return -3;
      return paramInt2;
    }
    if (paramInt1 > 0)
      paramInt2 = 2;
    else
      paramInt2 = 0;
    if (paramInt1 < 0)
      return -2;
    return paramInt2;
  }

  public static String asciiToString(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    paramString = paramString.split(",");
    int i = 0;
    while (i < paramString.length)
    {
      localStringBuffer.append((char)Integer.parseInt(paramString[i]));
      i += 1;
    }
    return localStringBuffer.toString();
  }

  public static String byteToString(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder(paramArrayOfByte.length);
    int j = paramArrayOfByte.length;
    int i = 0;
    while (i < j)
    {
      localStringBuilder.append(String.format("%02x ", new Object[] { Byte.valueOf(paramArrayOfByte[i]) }));
      i += 1;
    }
    return localStringBuilder.toString();
  }

  public static boolean checkArrayList(ArrayList<NewBluetoothDevice> paramArrayList, NewBluetoothDevice paramNewBluetoothDevice)
  {
    int i = 0;
    while (i < paramArrayList.size())
    {
      if (paramNewBluetoothDevice.getMacAddress().equalsIgnoreCase(((NewBluetoothDevice)paramArrayList.get(i)).getMacAddress()))
        return true;
      i += 1;
    }
    return false;
  }

  public static ComKey checkComKey(int paramInt)
  {
    if (paramInt == 174)
      return new ComKey(6, "UP");
    if (paramInt == 172)
      return new ComKey(7, "DOWN");
    if (paramInt == 170)
      return new ComKey(8, "LEFT");
    if (paramInt == 168)
      return new ComKey(9, "RIGHT");
    if (paramInt == 166)
      return new ComKey(0, "A");
    if (paramInt == 164)
      return new ComKey(1, "B");
    if (paramInt == 162)
      return new ComKey(2, "X");
    if (paramInt == 160)
      return new ComKey(3, "Y");
    if (paramInt == 158)
      return new ComKey(4, "L1");
    if (paramInt == 156)
      return new ComKey(14, "L2");
    if (paramInt == 154)
      return new ComKey(5, "R1");
    if (paramInt == 152)
      return new ComKey(15, "R2");
    if (paramInt == 150)
      return new ComKey(12, "L3");
    if (paramInt == 148)
      return new ComKey(13, "R3");
    if (paramInt == 146)
      return new ComKey(18, "M1");
    if (paramInt == 144)
      return new ComKey(19, "M2");
    if (paramInt == 142)
      return new ComKey(20, "M3");
    if (paramInt == 140)
      return new ComKey(21, "M4");
    if (paramInt == 116)
      return new ComKey(6, "UP");
    if (paramInt == 114)
      return new ComKey(7, "DOWN");
    if (paramInt == 112)
      return new ComKey(8, "LEFT");
    if (paramInt == 110)
      return new ComKey(9, "RIGHT");
    if (paramInt == 108)
      return new ComKey(0, "A");
    if (paramInt == 106)
      return new ComKey(1, "B");
    if (paramInt == 104)
      return new ComKey(2, "X");
    if (paramInt == 102)
      return new ComKey(3, "Y");
    if (paramInt == 100)
      return new ComKey(4, "L1");
    if (paramInt == 98)
      return new ComKey(14, "L2");
    if (paramInt == 96)
      return new ComKey(5, "R1");
    if (paramInt == 94)
      return new ComKey(15, "R2");
    if (paramInt == 92)
      return new ComKey(12, "L3");
    if (paramInt == 90)
      return new ComKey(13, "R3");
    if (paramInt == 88)
      return new ComKey(18, "M1");
    if (paramInt == 86)
      return new ComKey(19, "M2");
    if (paramInt == 84)
      return new ComKey(20, "M3");
    if (paramInt == 82)
      return new ComKey(21, "M4");
    return new ComKey(-1, "-1");
  }

  public static boolean checkDevice(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4)
  {
    int i = 0;
    if ((paramArrayOfByte1 != null) && (paramArrayOfByte2 != null) && (paramArrayOfByte3 != null))
    {
      if (paramArrayOfByte4 == null)
        return false;
      int j = paramArrayOfByte1.length;
      if ((paramArrayOfByte2.length == j) && (paramArrayOfByte3.length == j))
      {
        if (paramArrayOfByte4.length != j)
          return false;
        byte[] arrayOfByte = new byte[17];
        while (i < j)
        {
          arrayOfByte[i] = ((byte)(paramArrayOfByte1[i] ^ paramArrayOfByte2[i] ^ paramArrayOfByte3[i]));
          i += 1;
        }
        paramArrayOfByte1 = new StringBuilder();
        paramArrayOfByte1.append("appEncrpyArray:");
        paramArrayOfByte1.append(byteToString(arrayOfByte));
        MyLog.i("my_tag", paramArrayOfByte1.toString());
        return Arrays.equals(arrayOfByte, paramArrayOfByte4);
      }
      return false;
    }
    return false;
  }

  public static boolean checkFWVersion(String paramString1, String paramString2)
  {
    paramString1 = paramString1.split("\\.");
    paramString2 = paramString2.split("\\.");
    if (paramString1.length > paramString2.length)
      return true;
    if ((paramString1.length == paramString2.length) && (paramString2.length == 2))
    {
      if (Integer.valueOf(paramString1[0]).intValue() > Integer.valueOf(paramString2[0]).intValue())
        return true;
      if (Integer.valueOf(paramString1[0]) == Integer.valueOf(paramString2[0]))
        return Integer.valueOf(paramString1[1]).intValue() > Integer.valueOf(paramString2[1]).intValue();
      return false;
    }
    return false;
  }

  public static boolean checkSetupitemList(List<Setupitem> paramList, String paramString)
  {
    int i = 0;
    while (i < paramList.size())
    {
      if (paramString.equals(((Setupitem)paramList.get(i)).getitemName()))
        return true;
      i += 1;
    }
    return false;
  }

  public static boolean checkStringList(ArrayList<String> paramArrayList, String paramString)
  {
    int i = 0;
    while (i < paramArrayList.size())
    {
      if (paramString.equals(paramArrayList.get(i)))
        return true;
      i += 1;
    }
    return false;
  }

  public static void delDrawable(Context paramContext, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramContext.getFilesDir());
    localStringBuilder.append(File.separator);
    localStringBuilder.append(paramString);
    localStringBuilder.append(".png");
    paramContext = new File(localStringBuilder.toString());
    if (paramContext.exists())
      paramContext.delete();
  }

  public static boolean fileIsExists(String paramString)
  {
    try
    {
      boolean bool = new File(paramString).exists();
      return bool;
    }
    catch (Exception paramString)
    {
    }
    return false;
  }

  public static SunyesMaxGamePreset getAddAppSunyesMaxGamePreset(Context paramContext)
  {
    return new SunyesMaxGamePreset("null", paramContext.getResources().getDrawable(R.mipmap.ic_add_circle), paramContext.getString(R.string.location_list1), "null", 0);
  }

  @RequiresApi(api=26)
  public static Bitmap getAppIcon(Drawable paramDrawable)
  {
    if ((paramDrawable instanceof BitmapDrawable))
      return ((BitmapDrawable)paramDrawable).getBitmap();
    if ((paramDrawable instanceof AdaptiveIconDrawable))
    {
      paramDrawable = (AdaptiveIconDrawable)paramDrawable;
      paramDrawable = new LayerDrawable(new Drawable[] { paramDrawable.getBackground(), paramDrawable.getForeground() });
      Bitmap localBitmap = Bitmap.createBitmap(paramDrawable.getIntrinsicWidth(), paramDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
      Canvas localCanvas = new Canvas(localBitmap);
      paramDrawable.setBounds(0, 0, localCanvas.getWidth(), localCanvas.getHeight());
      paramDrawable.draw(localCanvas);
      return localBitmap;
    }
    return null;
  }

  public static String getBootloaderMac(String paramString)
  {
    Object localObject = paramString.substring(paramString.length() - 1).toUpperCase(Locale.getDefault());
    String str = paramString.substring(0, paramString.length() - 1).toUpperCase(Locale.getDefault());
    if ("F".equals(localObject))
      paramString = "0";
    else
      paramString = Integer.toHexString(Integer.parseInt((String)localObject, 16) + 1).toUpperCase(Locale.getDefault());
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(str);
    ((StringBuilder)localObject).append(paramString);
    return ((StringBuilder)localObject).toString();
  }

  public static byte[] getByteArray()
  {
    byte[] arrayOfByte = new byte[17];
    Random localRandom = new Random();
    int i = 0;
    while (i < 17)
    {
      arrayOfByte[i] = ((byte)(localRandom.nextInt(254) + 1));
      i += 1;
    }
    return arrayOfByte;
  }

  public static String getCurrentDateTime()
  {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(new Date(System.currentTimeMillis()));
  }

  public static byte[] getDeviceEncrpyArray(byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = new byte[17];
    System.arraycopy(paramArrayOfByte, 3, arrayOfByte, 0, 17);
    return arrayOfByte;
  }

  public static byte[] getEncrpyArray(int paramInt)
  {
    paramInt %= 8;
    if (paramInt == 0)
      return encrpyArray0;
    if (paramInt == 1)
      return encrpyArray1;
    if (paramInt == 2)
      return encrpyArray2;
    if (paramInt == 3)
      return encrpyArray3;
    if (paramInt == 4)
      return encrpyArray4;
    if (paramInt == 5)
      return encrpyArray5;
    if (paramInt == 6)
      return encrpyArray6;
    return encrpyArray7;
  }

  public static GPKey getGPKey(int paramInt)
  {
    if ((paramInt != 174) && (paramInt != 173))
    {
      if ((paramInt != 172) && (paramInt != 171))
      {
        if ((paramInt != 170) && (paramInt != 169))
        {
          if ((paramInt != 168) && (paramInt != 167))
          {
            if ((paramInt != 166) && (paramInt != 165))
            {
              if ((paramInt != 164) && (paramInt != 163))
              {
                if ((paramInt != 162) && (paramInt != 161))
                {
                  if ((paramInt != 160) && (paramInt != 159))
                  {
                    if ((paramInt != 158) && (paramInt != 157))
                    {
                      if ((paramInt != 156) && (paramInt != 155))
                      {
                        if ((paramInt != 154) && (paramInt != 153))
                        {
                          if ((paramInt != 152) && (paramInt != 151))
                          {
                            if ((paramInt != 150) && (paramInt != 149))
                            {
                              if ((paramInt != 148) && (paramInt != 147))
                              {
                                if ((paramInt != 146) && (paramInt != 145))
                                {
                                  if ((paramInt != 144) && (paramInt != 143))
                                  {
                                    if ((paramInt != 142) && (paramInt != 141))
                                    {
                                      if ((paramInt != 140) && (paramInt != 139))
                                      {
                                        if (paramInt == 240)
                                          return new GPKey(22, "JOYSTICK1", 0);
                                        if (paramInt == 239)
                                          return new GPKey(23, "JOYSTICK2", 0);
                                        if (paramInt == 238)
                                          return new GPKey(24, "JOYSTICK3", 0);
                                        if (paramInt == 237)
                                          return new GPKey(25, "JOYSTICK4", 0);
                                        if (paramInt == 236)
                                          return new GPKey(26, "JOYSTICK5", 0);
                                        if ((paramInt != 116) && (paramInt != 115))
                                        {
                                          if ((paramInt != 114) && (paramInt != 113))
                                          {
                                            if ((paramInt != 112) && (paramInt != 111))
                                            {
                                              if ((paramInt != 110) && (paramInt != 109))
                                              {
                                                if ((paramInt != 108) && (paramInt != 107))
                                                {
                                                  if ((paramInt != 106) && (paramInt != 105))
                                                  {
                                                    if ((paramInt != 104) && (paramInt != 103))
                                                    {
                                                      if ((paramInt != 102) && (paramInt != 101))
                                                      {
                                                        if ((paramInt != 100) && (paramInt != 99))
                                                        {
                                                          if ((paramInt != 98) && (paramInt != 97))
                                                          {
                                                            if ((paramInt != 96) && (paramInt != 95))
                                                            {
                                                              if ((paramInt != 94) && (paramInt != 93))
                                                              {
                                                                if ((paramInt != 92) && (paramInt != 91))
                                                                {
                                                                  if ((paramInt != 90) && (paramInt != 89))
                                                                  {
                                                                    if ((paramInt != 88) && (paramInt != 87))
                                                                    {
                                                                      if ((paramInt != 86) && (paramInt != 85))
                                                                      {
                                                                        if ((paramInt != 84) && (paramInt != 83))
                                                                        {
                                                                          if ((paramInt != 82) && (paramInt != 81))
                                                                          {
                                                                            if (paramInt == 232)
                                                                              return new GPKey(22, "JOYSTICK1", 1);
                                                                            if (paramInt == 231)
                                                                              return new GPKey(23, "JOYSTICK2", 1);
                                                                            if (paramInt == 230)
                                                                              return new GPKey(24, "JOYSTICK3", 1);
                                                                            if (paramInt == 229)
                                                                              return new GPKey(25, "JOYSTICK4", 1);
                                                                            if (paramInt == 228)
                                                                              return new GPKey(26, "JOYSTICK5", 1);
                                                                            return null;
                                                                          }
                                                                          return new GPKey(21, "M4", 1);
                                                                        }
                                                                        return new GPKey(20, "M3", 1);
                                                                      }
                                                                      return new GPKey(19, "M2", 1);
                                                                    }
                                                                    return new GPKey(18, "M1", 1);
                                                                  }
                                                                  return new GPKey(13, "R3", 1);
                                                                }
                                                                return new GPKey(12, "L3", 1);
                                                              }
                                                              return new GPKey(15, "R2", 1);
                                                            }
                                                            return new GPKey(5, "R1", 1);
                                                          }
                                                          return new GPKey(14, "L2", 1);
                                                        }
                                                        return new GPKey(4, "L1", 1);
                                                      }
                                                      return new GPKey(3, "Y", 1);
                                                    }
                                                    return new GPKey(2, "X", 1);
                                                  }
                                                  return new GPKey(1, "B", 1);
                                                }
                                                return new GPKey(0, "A", 1);
                                              }
                                              return new GPKey(9, "RIGHT", 1);
                                            }
                                            return new GPKey(8, "LEFT", 1);
                                          }
                                          return new GPKey(7, "DOWN", 1);
                                        }
                                        return new GPKey(6, "UP", 1);
                                      }
                                      return new GPKey(21, "M4", 0);
                                    }
                                    return new GPKey(20, "M3", 0);
                                  }
                                  return new GPKey(19, "M2", 0);
                                }
                                return new GPKey(18, "M1", 0);
                              }
                              return new GPKey(13, "R3", 0);
                            }
                            return new GPKey(12, "L3", 0);
                          }
                          return new GPKey(15, "R2", 0);
                        }
                        return new GPKey(5, "R1", 0);
                      }
                      return new GPKey(14, "L2", 0);
                    }
                    return new GPKey(4, "L1", 0);
                  }
                  return new GPKey(3, "Y", 0);
                }
                return new GPKey(2, "X", 0);
              }
              return new GPKey(1, "B", 0);
            }
            return new GPKey(0, "A", 0);
          }
          return new GPKey(9, "RIGHT", 0);
        }
        return new GPKey(8, "LEFT", 0);
      }
      return new GPKey(7, "DOWN", 0);
    }
    return new GPKey(6, "UP", 0);
  }

  public static int getGPKeyCodeByName(String paramString)
  {
    if (paramString.equalsIgnoreCase("up"))
      return 174;
    if (paramString.equalsIgnoreCase("down"))
      return 172;
    if (paramString.equalsIgnoreCase("left"))
      return 170;
    if (paramString.equalsIgnoreCase("right"))
      return 168;
    if (paramString.equalsIgnoreCase("a"))
      return 166;
    if (paramString.equalsIgnoreCase("b"))
      return 164;
    if (paramString.equalsIgnoreCase("x"))
      return 162;
    if (paramString.equalsIgnoreCase("y"))
      return 160;
    if (paramString.equalsIgnoreCase("l1"))
      return 158;
    if (paramString.equalsIgnoreCase("l2"))
      return 156;
    if (paramString.equalsIgnoreCase("r1"))
      return 154;
    if (paramString.equalsIgnoreCase("r2"))
      return 152;
    if (paramString.equalsIgnoreCase("l3"))
      return 150;
    if (paramString.equalsIgnoreCase("r3"))
      return 148;
    if (paramString.equalsIgnoreCase("m1"))
      return 146;
    if (paramString.equalsIgnoreCase("m2"))
      return 144;
    if (paramString.equalsIgnoreCase("m3"))
      return 142;
    if (paramString.equalsIgnoreCase("m4"))
      return 140;
    if (paramString.equalsIgnoreCase("JOYSTICK1"))
      return 240;
    if (paramString.equalsIgnoreCase("JOYSTICK2"))
      return 239;
    if (paramString.equalsIgnoreCase("JOYSTICK3"))
      return 238;
    if (paramString.equalsIgnoreCase("JOYSTICK4"))
      return 237;
    if (paramString.equalsIgnoreCase("JOYSTICK5"))
      return 236;
    return -1;
  }

  public static int getGPKeyCodeByName1(String paramString)
  {
    if (paramString.equalsIgnoreCase("up"))
      return 173;
    if (paramString.equalsIgnoreCase("down"))
      return 171;
    if (paramString.equalsIgnoreCase("left"))
      return 169;
    if (paramString.equalsIgnoreCase("right"))
      return 167;
    if (paramString.equalsIgnoreCase("a"))
      return 165;
    if (paramString.equalsIgnoreCase("b"))
      return 163;
    if (paramString.equalsIgnoreCase("x"))
      return 161;
    if (paramString.equalsIgnoreCase("y"))
      return 159;
    if (paramString.equalsIgnoreCase("l1"))
      return 157;
    if (paramString.equalsIgnoreCase("l2"))
      return 155;
    if (paramString.equalsIgnoreCase("r1"))
      return 153;
    if (paramString.equalsIgnoreCase("r2"))
      return 151;
    if (paramString.equalsIgnoreCase("l3"))
      return 149;
    if (paramString.equalsIgnoreCase("r3"))
      return 147;
    if (paramString.equalsIgnoreCase("m1"))
      return 145;
    if (paramString.equalsIgnoreCase("m2"))
      return 143;
    if (paramString.equalsIgnoreCase("m3"))
      return 141;
    if (paramString.equalsIgnoreCase("m4"))
      return 139;
    return -1;
  }

  public static int getGPKeyCodeByName1Scenes1(String paramString)
  {
    if (paramString.equalsIgnoreCase("up"))
      return 115;
    if (paramString.equalsIgnoreCase("down"))
      return 113;
    if (paramString.equalsIgnoreCase("left"))
      return 111;
    if (paramString.equalsIgnoreCase("right"))
      return 109;
    if (paramString.equalsIgnoreCase("a"))
      return 107;
    if (paramString.equalsIgnoreCase("b"))
      return 105;
    if (paramString.equalsIgnoreCase("x"))
      return 103;
    if (paramString.equalsIgnoreCase("y"))
      return 101;
    if (paramString.equalsIgnoreCase("l1"))
      return 99;
    if (paramString.equalsIgnoreCase("l2"))
      return 97;
    if (paramString.equalsIgnoreCase("r1"))
      return 95;
    if (paramString.equalsIgnoreCase("r2"))
      return 93;
    if (paramString.equalsIgnoreCase("l3"))
      return 91;
    if (paramString.equalsIgnoreCase("r3"))
      return 89;
    if (paramString.equalsIgnoreCase("m1"))
      return 87;
    if (paramString.equalsIgnoreCase("m2"))
      return 85;
    if (paramString.equalsIgnoreCase("m3"))
      return 83;
    if (paramString.equalsIgnoreCase("m4"))
      return 81;
    return -1;
  }

  public static int getGPKeyCodeByNameScenes1(String paramString)
  {
    if (paramString.equalsIgnoreCase("up"))
      return 116;
    if (paramString.equalsIgnoreCase("down"))
      return 114;
    if (paramString.equalsIgnoreCase("left"))
      return 112;
    if (paramString.equalsIgnoreCase("right"))
      return 110;
    if (paramString.equalsIgnoreCase("a"))
      return 108;
    if (paramString.equalsIgnoreCase("b"))
      return 106;
    if (paramString.equalsIgnoreCase("x"))
      return 104;
    if (paramString.equalsIgnoreCase("y"))
      return 102;
    if (paramString.equalsIgnoreCase("l1"))
      return 100;
    if (paramString.equalsIgnoreCase("l2"))
      return 98;
    if (paramString.equalsIgnoreCase("r1"))
      return 96;
    if (paramString.equalsIgnoreCase("r2"))
      return 94;
    if (paramString.equalsIgnoreCase("l3"))
      return 92;
    if (paramString.equalsIgnoreCase("r3"))
      return 90;
    if (paramString.equalsIgnoreCase("m1"))
      return 88;
    if (paramString.equalsIgnoreCase("m2"))
      return 86;
    if (paramString.equalsIgnoreCase("m3"))
      return 84;
    if (paramString.equalsIgnoreCase("m4"))
      return 82;
    if (paramString.equalsIgnoreCase("JOYSTICK1"))
      return 232;
    if (paramString.equalsIgnoreCase("JOYSTICK2"))
      return 231;
    if (paramString.equalsIgnoreCase("JOYSTICK3"))
      return 230;
    if (paramString.equalsIgnoreCase("JOYSTICK4"))
      return 229;
    if (paramString.equalsIgnoreCase("JOYSTICK5"))
      return 228;
    return -1;
  }

  public static OfficialGamePreset getGameDownloadOfficialGamePreset(ArrayList<OfficialGamePreset> paramArrayList, int paramInt)
  {
    int i = 0;
    while (i < paramArrayList.size())
    {
      if (((OfficialGamePreset)paramArrayList.get(i)).getGameId() == paramInt)
        return (OfficialGamePreset)paramArrayList.get(i);
      i += 1;
    }
    return null;
  }

  public static String getGameDownloadUrl(ArrayList<OfficialGamePreset> paramArrayList, int paramInt)
  {
    int i = 0;
    while (i < paramArrayList.size())
    {
      if (((OfficialGamePreset)paramArrayList.get(i)).getGameId() == paramInt)
        return ((OfficialGamePreset)paramArrayList.get(i)).getPresetUrl();
      i += 1;
    }
    return "-1";
  }

  public static int getKeyCodeByeName(String paramString)
  {
    if (paramString.equalsIgnoreCase("1"))
      return 30;
    if (paramString.equalsIgnoreCase("2"))
      return 31;
    if (paramString.equalsIgnoreCase("3"))
      return 32;
    if (paramString.equalsIgnoreCase("4"))
      return 33;
    if (paramString.equalsIgnoreCase("5"))
      return 34;
    if (paramString.equalsIgnoreCase("6"))
      return 35;
    if (paramString.equalsIgnoreCase("q"))
      return 20;
    if (paramString.equalsIgnoreCase("w"))
      return 26;
    if (paramString.equalsIgnoreCase("e"))
      return 8;
    if (paramString.equalsIgnoreCase("r"))
      return 21;
    if (paramString.equalsIgnoreCase("t"))
      return 23;
    if (paramString.equalsIgnoreCase("y"))
      return 28;
    if (paramString.equalsIgnoreCase("a"))
      return 4;
    if (paramString.equalsIgnoreCase("s"))
      return 22;
    if (paramString.equalsIgnoreCase("d"))
      return 7;
    if (paramString.equalsIgnoreCase("f"))
      return 9;
    if (paramString.equalsIgnoreCase("g"))
      return 10;
    if (paramString.equalsIgnoreCase("h"))
      return 11;
    if (paramString.equalsIgnoreCase("z"))
      return 29;
    if (paramString.equalsIgnoreCase("x"))
      return 27;
    if (paramString.equalsIgnoreCase("c"))
      return 6;
    if (paramString.equalsIgnoreCase("v"))
      return 25;
    if (paramString.equalsIgnoreCase("b"))
      return 5;
    if (paramString.equalsIgnoreCase("n"))
      return 17;
    if (paramString.equalsIgnoreCase("k"))
      return 14;
    if (paramString.equalsIgnoreCase("m"))
      return 16;
    if (paramString.equalsIgnoreCase("o"))
      return 18;
    if (paramString.equalsIgnoreCase("p"))
      return 19;
    if (paramString.equalsIgnoreCase("comma"))
      return 54;
    if (paramString.equalsIgnoreCase("dot"))
      return 55;
    if (paramString.equalsIgnoreCase("i"))
      return 12;
    if (paramString.equalsIgnoreCase("j"))
      return 13;
    if (paramString.equalsIgnoreCase("l"))
      return 15;
    if (paramString.equalsIgnoreCase("u"))
      return 24;
    if (paramString.equalsIgnoreCase("7"))
      return 36;
    if (paramString.equalsIgnoreCase("8"))
      return 37;
    if (paramString.equalsIgnoreCase("9"))
      return 38;
    if (paramString.equalsIgnoreCase("0"))
      return 39;
    if (paramString.equalsIgnoreCase("enter"))
      return 40;
    if (paramString.equalsIgnoreCase("esc"))
      return 41;
    if (paramString.equalsIgnoreCase("backspace"))
      return 42;
    if (paramString.equalsIgnoreCase("tab"))
      return 43;
    if (paramString.equalsIgnoreCase("space"))
      return 44;
    if (paramString.equalsIgnoreCase("neg"))
      return 45;
    if (paramString.equalsIgnoreCase("equ"))
      return 46;
    if (paramString.equalsIgnoreCase("leftbracket"))
      return 47;
    if (paramString.equalsIgnoreCase("rightbracket"))
      return 48;
    if (paramString.equalsIgnoreCase("slash"))
      return 49;
    if (paramString.equalsIgnoreCase("fenhao"))
      return 51;
    if (paramString.equalsIgnoreCase("yinhao"))
      return 52;
    if (paramString.equalsIgnoreCase("tilde"))
      return 53;
    if (paramString.equalsIgnoreCase("backslash"))
      return 56;
    if (paramString.equalsIgnoreCase("caps"))
      return 57;
    if (paramString.equalsIgnoreCase("f1"))
      return 58;
    if (paramString.equalsIgnoreCase("f2"))
      return 59;
    if (paramString.equalsIgnoreCase("center"))
      return 60;
    if (paramString.equalsIgnoreCase("mouse"))
      return 61;
    if (paramString.equalsIgnoreCase("f5"))
      return 62;
    if (paramString.equalsIgnoreCase("f6"))
      return 63;
    if (paramString.equalsIgnoreCase("f7"))
      return 64;
    if (paramString.equalsIgnoreCase("f8"))
      return 65;
    if (paramString.equalsIgnoreCase("f9"))
      return 66;
    if (paramString.equalsIgnoreCase("f10"))
      return 67;
    if (paramString.equalsIgnoreCase("f11"))
      return 68;
    if (paramString.equalsIgnoreCase("f12"))
      return 69;
    if (paramString.equalsIgnoreCase("printscreen"))
      return 70;
    if (paramString.equalsIgnoreCase("scrollock"))
      return 71;
    if (paramString.equalsIgnoreCase("pause"))
      return 72;
    if (paramString.equalsIgnoreCase("insert"))
      return 73;
    if (paramString.equalsIgnoreCase("home"))
      return 74;
    if (paramString.equalsIgnoreCase("pageup"))
      return 75;
    if (paramString.equalsIgnoreCase("del"))
      return 76;
    if (paramString.equalsIgnoreCase("end"))
      return 77;
    if (paramString.equalsIgnoreCase("pagedown"))
      return 78;
    if (paramString.equalsIgnoreCase("rightarrow"))
      return 79;
    if (paramString.equalsIgnoreCase("leftarrow"))
      return 80;
    if (paramString.equalsIgnoreCase("downarrow"))
      return 81;
    if (paramString.equalsIgnoreCase("uparrow"))
      return 82;
    if (paramString.equalsIgnoreCase("numlock"))
      return 83;
    if (paramString.equalsIgnoreCase("numslash"))
      return 84;
    if (paramString.equalsIgnoreCase("numstar"))
      return 85;
    if (paramString.equalsIgnoreCase("numneg"))
      return 86;
    if (paramString.equalsIgnoreCase("numadd"))
      return 87;
    if (paramString.equalsIgnoreCase("numenter"))
      return 88;
    if (paramString.equalsIgnoreCase("num1"))
      return 89;
    if (paramString.equalsIgnoreCase("num2"))
      return 90;
    if (paramString.equalsIgnoreCase("num3"))
      return 91;
    if (paramString.equalsIgnoreCase("num4"))
      return 92;
    if (paramString.equalsIgnoreCase("num5"))
      return 93;
    if (paramString.equalsIgnoreCase("num6"))
      return 94;
    if (paramString.equalsIgnoreCase("num7"))
      return 95;
    if (paramString.equalsIgnoreCase("num8"))
      return 96;
    if (paramString.equalsIgnoreCase("num9"))
      return 97;
    if (paramString.equalsIgnoreCase("num0"))
      return 98;
    if (paramString.equalsIgnoreCase("numdot"))
      return 99;
    if (paramString.equalsIgnoreCase("app"))
      return 101;
    if (paramString.equalsIgnoreCase("leftctl"))
      return 224;
    if (paramString.equalsIgnoreCase("leftshift"))
      return 225;
    if (paramString.equalsIgnoreCase("leftalt"))
      return 226;
    if (paramString.equalsIgnoreCase("leftwin"))
      return 227;
    if (paramString.equalsIgnoreCase("rightctl"))
      return 228;
    if (paramString.equalsIgnoreCase("rightshift"))
      return 229;
    if (paramString.equalsIgnoreCase("rightalt"))
      return 230;
    if (paramString.equalsIgnoreCase("rightwin"))
      return 231;
    if (paramString.equalsIgnoreCase("mouseleft"))
      return 253;
    if (paramString.equalsIgnoreCase("mouseright"))
      return 252;
    if (paramString.equalsIgnoreCase("mousemiddle"))
      return 251;
    return -1;
  }

  public static MyKeyMap getKeyPos(int paramInt)
  {
    MyKeyMap localMyKeyMap = new MyKeyMap();
    if (paramInt == 30)
      return new MyKeyMap("1", 30, 0);
    if (paramInt == 31)
      return new MyKeyMap("2", 31, 1);
    if (paramInt == 32)
      return new MyKeyMap("3", 32, 2);
    if (paramInt == 33)
      return new MyKeyMap("4", 33, 3);
    if (paramInt == 34)
      return new MyKeyMap("5", 34, 4);
    if (paramInt == 35)
      return new MyKeyMap("6", 35, 5);
    if (paramInt == 20)
      return new MyKeyMap("q", 20, 6);
    if (paramInt == 26)
      return new MyKeyMap("w", 26, 7);
    if (paramInt == 8)
      return new MyKeyMap("e", 8, 8);
    if (paramInt == 21)
      return new MyKeyMap("r", 21, 9);
    if (paramInt == 23)
      return new MyKeyMap("t", 23, 10);
    if (paramInt == 28)
      return new MyKeyMap("y", 28, 11);
    if (paramInt == 4)
      return new MyKeyMap("a", 4, 12);
    if (paramInt == 22)
      return new MyKeyMap("s", 22, 13);
    if (paramInt == 7)
      return new MyKeyMap("d", 7, 14);
    if (paramInt == 9)
      return new MyKeyMap("f", 9, 15);
    if (paramInt == 10)
      return new MyKeyMap("g", 10, 16);
    if (paramInt == 11)
      return new MyKeyMap("h", 11, 17);
    if (paramInt == 29)
      return new MyKeyMap("z", 29, 18);
    if (paramInt == 27)
      return new MyKeyMap("x", 27, 19);
    if (paramInt == 6)
      return new MyKeyMap("c", 6, 20);
    if (paramInt == 25)
      return new MyKeyMap("v", 25, 21);
    if (paramInt == 5)
      return new MyKeyMap("b", 5, 22);
    if (paramInt == 17)
      return new MyKeyMap("n", 17, 23);
    if (paramInt == 14)
      return new MyKeyMap("k", 14, 24);
    if (paramInt == 16)
      return new MyKeyMap("m", 16, 25);
    if (paramInt == 18)
      return new MyKeyMap("o", 18, 26);
    if (paramInt == 19)
      return new MyKeyMap("p", 19, 27);
    if (paramInt == 54)
      return new MyKeyMap("comma", 54, 28);
    if (paramInt == 55)
      return new MyKeyMap("dot", 55, 29);
    if (paramInt == 12)
      return new MyKeyMap("i", 12, 30);
    if (paramInt == 13)
      return new MyKeyMap("j", 13, 31);
    if (paramInt == 15)
      return new MyKeyMap("l", 15, 32);
    if (paramInt == 24)
      return new MyKeyMap("u", 24, 33);
    if (paramInt == 36)
      return new MyKeyMap("7", 36, 34);
    if (paramInt == 37)
      return new MyKeyMap("8", 37, 35);
    if (paramInt == 38)
      return new MyKeyMap("9", 38, 36);
    if (paramInt == 39)
      return new MyKeyMap("0", 39, 37);
    if (paramInt == 40)
      return new MyKeyMap("enter", 40, 38);
    if (paramInt == 41)
      return new MyKeyMap("esc", 41, 39);
    if (paramInt == 42)
      return new MyKeyMap("backspace", 42, 40);
    if (paramInt == 43)
      return new MyKeyMap("tab", 43, 41);
    if (paramInt == 44)
      return new MyKeyMap("space", 44, 42);
    if (paramInt == 45)
      return new MyKeyMap("neg", 45, 43);
    if (paramInt == 46)
      return new MyKeyMap("equ", 46, 44);
    if (paramInt == 47)
      return new MyKeyMap("leftbracket", 47, 45);
    if (paramInt == 48)
      return new MyKeyMap("rightbracket", 48, 46);
    if (paramInt == 49)
      return new MyKeyMap("slash", 49, 47);
    if (paramInt == 51)
      return new MyKeyMap("fenhao", 51, 48);
    if (paramInt == 52)
      return new MyKeyMap("yinhao", 52, 49);
    if (paramInt == 53)
      return new MyKeyMap("tilde", 53, 50);
    if (paramInt == 56)
      return new MyKeyMap("backslash", 56, 51);
    if (paramInt == 57)
      return new MyKeyMap("caps", 57, 52);
    if (paramInt == 58)
      return new MyKeyMap("f1", 58, 53);
    if (paramInt == 59)
      return new MyKeyMap("f2", 59, 54);
    if (paramInt == 60)
      return new MyKeyMap("center", 60, 55);
    if (paramInt == 61)
      return new MyKeyMap("mouse", 61, 56);
    if (paramInt == 62)
      return new MyKeyMap("f5", 62, 57);
    if (paramInt == 63)
      return new MyKeyMap("f6", 63, 58);
    if (paramInt == 64)
      return new MyKeyMap("f7", 64, 59);
    if (paramInt == 65)
      return new MyKeyMap("f8", 65, 60);
    if (paramInt == 66)
      return new MyKeyMap("f9", 66, 61);
    if (paramInt == 67)
      return new MyKeyMap("f10", 67, 62);
    if (paramInt == 68)
      return new MyKeyMap("f11", 68, 63);
    if (paramInt == 69)
      return new MyKeyMap("f12", 69, 64);
    if (paramInt == 70)
      return new MyKeyMap("printscreen", 70, 65);
    if (paramInt == 71)
      return new MyKeyMap("scrollock", 71, 66);
    if (paramInt == 72)
      return new MyKeyMap("pause", 72, 67);
    if (paramInt == 73)
      return new MyKeyMap("insert", 73, 68);
    if (paramInt == 74)
      return new MyKeyMap("home", 74, 69);
    if (paramInt == 75)
      return new MyKeyMap("pageup", 75, 70);
    if (paramInt == 76)
      return new MyKeyMap("del", 76, 71);
    if (paramInt == 77)
      return new MyKeyMap("end", 77, 72);
    if (paramInt == 78)
      return new MyKeyMap("pagedown", 78, 73);
    if (paramInt == 79)
      return new MyKeyMap("rightarrow", 79, 74);
    if (paramInt == 80)
      return new MyKeyMap("leftarrow", 80, 75);
    if (paramInt == 81)
      return new MyKeyMap("downarrow", 81, 76);
    if (paramInt == 82)
      return new MyKeyMap("uparrow", 82, 77);
    if (paramInt == 83)
      return new MyKeyMap("numlock", 83, 78);
    if (paramInt == 84)
      return new MyKeyMap("numslash", 84, 79);
    if (paramInt == 85)
      return new MyKeyMap("numstar", 85, 80);
    if (paramInt == 86)
      return new MyKeyMap("numneg", 86, 81);
    if (paramInt == 87)
      return new MyKeyMap("numadd", 87, 82);
    if (paramInt == 88)
      return new MyKeyMap("numenter", 88, 83);
    if (paramInt == 89)
      return new MyKeyMap("num1", 89, 84);
    if (paramInt == 90)
      return new MyKeyMap("num2", 90, 85);
    if (paramInt == 91)
      return new MyKeyMap("num3", 91, 86);
    if (paramInt == 92)
      return new MyKeyMap("num4", 92, 87);
    if (paramInt == 93)
      return new MyKeyMap("num5", 93, 88);
    if (paramInt == 94)
      return new MyKeyMap("num6", 94, 89);
    if (paramInt == 95)
      return new MyKeyMap("num7", 95, 90);
    if (paramInt == 96)
      return new MyKeyMap("num8", 96, 91);
    if (paramInt == 97)
      return new MyKeyMap("num9", 97, 92);
    if (paramInt == 98)
      return new MyKeyMap("num0", 98, 93);
    if (paramInt == 99)
      return new MyKeyMap("numdot", 99, 94);
    if (paramInt == 101)
      return new MyKeyMap("app", 101, 95);
    if (paramInt == 224)
      return new MyKeyMap("leftctl", 224, 96);
    if (paramInt == 225)
      return new MyKeyMap("leftshift", 225, 97);
    if (paramInt == 226)
      return new MyKeyMap("leftalt", 226, 98);
    if (paramInt == 227)
      return new MyKeyMap("leftwin", 227, 99);
    if (paramInt == 228)
      return new MyKeyMap("rightctl", 228, 100);
    if (paramInt == 229)
      return new MyKeyMap("rightshift", 229, 101);
    if (paramInt == 230)
      return new MyKeyMap("rightalt", 230, 102);
    if (paramInt == 231)
      return new MyKeyMap("rightwin", 231, 103);
    if (paramInt == 253)
      return new MyKeyMap("mouseleft", 1, 104);
    if (paramInt == 252)
      return new MyKeyMap("mouseright", 2, 106);
    if (paramInt == 251)
      return new MyKeyMap("mousemiddle", 3, 105);
    return localMyKeyMap;
  }

  public static int getLocalVersion(Context paramContext)
  {
    try
    {
      int i = paramContext.getApplicationContext().getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return 0;
  }

  public static String getLocalVersionName(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getApplicationContext().getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      paramContext.printStackTrace();
    }
    return "";
  }

  public static byte[] getMapDataByteArray(byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = new byte[17];
    System.arraycopy(paramArrayOfByte, 0, arrayOfByte, 0, 17);
    return arrayOfByte;
  }

  public static String getResolution(int paramInt)
  {
    if (paramInt <= 1280)
      return "1280x720";
    if (paramInt <= 1920)
      return "1920x1080";
    if (paramInt <= 2160)
      return "2160x1080";
    if (paramInt <= 2280)
      return "2280x1080";
    return "2560x1440";
  }

  public static byte[] intToByteArray(int paramInt)
  {
    return new byte[] { (byte)(paramInt >> 8 & 0xFF), (byte)(paramInt & 0xFF) };
  }

  public static boolean isAndroidV3Mode(String paramString)
  {
    if (paramString == null)
      return false;
    paramString = paramString.split(":");
    if (paramString.length != 6)
      return false;
    return paramString[0].equals("01");
  }

  public static boolean isAvilible(Context paramContext, String paramString)
  {
    paramContext = paramContext.getPackageManager();
    int i = 0;
    paramContext = paramContext.getInstalledPackages(0);
    ArrayList localArrayList = new ArrayList();
    if (paramContext != null)
      while (i < paramContext.size())
      {
        localArrayList.add(((PackageInfo)paramContext.get(i)).packageName);
        i += 1;
      }
    return localArrayList.contains(paramString);
  }

  public static boolean isBinFilePidVidEqualDevice(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    Object localObject;
    if ((paramString1 != null) && (paramString2 != null))
    {
      if (paramString3 == null)
        return false;
      localObject = new byte[4];
    }
    try
    {
      paramContext = paramContext.getContentResolver().openInputStream(Uri.fromFile(new File(paramString1)));
      paramContext.skip(8176L);
      paramContext.read((byte[])localObject, 0, localObject.length);
      paramContext.close();
      paramContext = new StringBuilder();
      paramContext.append("--mFileIndexBuffer:");
      paramContext.append(byteToString((byte[])localObject));
      MyLog.i("my_tag", paramContext.toString());
      paramContext = String.valueOf(ChangeDataUtil.getIntegerByBit(localObject[0], localObject[1]));
      paramString1 = String.valueOf(ChangeDataUtil.getIntegerByBit(localObject[2], localObject[3]));
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("--binPid = ");
      ((StringBuilder)localObject).append(paramContext);
      ((StringBuilder)localObject).append("   devicePid = ");
      ((StringBuilder)localObject).append(paramString2);
      ((StringBuilder)localObject).append("   binVid = ");
      ((StringBuilder)localObject).append(paramString1);
      ((StringBuilder)localObject).append("   deviceVid = ");
      ((StringBuilder)localObject).append(paramString3);
      MyLog.i("my_tag", ((StringBuilder)localObject).toString());
      return (paramContext.equals(paramString2)) && (paramString1.equals(paramString3));
      return false;
    }
    catch (IOException paramContext)
    {
    }
    return false;
  }

  public static boolean isBundingGameNameOk(ArrayList<SunyesMaxGamePreset> paramArrayList, String paramString)
  {
    int i = 0;
    while (i < paramArrayList.size())
    {
      if ((isStringValid(((SunyesMaxGamePreset)paramArrayList.get(i)).getAppName())) && (isStringValid(paramString)) && (((SunyesMaxGamePreset)paramArrayList.get(i)).getAppName().equalsIgnoreCase(paramString)))
        return true;
      i += 1;
    }
    return false;
  }

  public static boolean isContain(ArrayList<OfficialGamePreset> paramArrayList, int paramInt)
  {
    int i = 0;
    while (i < paramArrayList.size())
    {
      if (((OfficialGamePreset)paramArrayList.get(i)).getGameId() == paramInt)
        return true;
      i += 1;
    }
    return false;
  }

  public static boolean isContainChinese(String paramString)
  {
    if (!isStringValid(paramString))
    {
      MyLog.i("my_tag", "game name is null");
      return false;
    }
    return Pattern.compile("[一-龥]").matcher(paramString).find();
  }

  public static boolean isFilesExists(String paramString)
  {
    if (new File(paramString).exists())
    {
      System.out.print("----压缩文件存在----");
      return true;
    }
    return false;
  }

  public static boolean isHardWareVendorMediaTek()
  {
    if (Build.HARDWARE.matches("mt[0-9]*"))
    {
      Log.i("phone_info", "MediaTek platform");
      return true;
    }
    return false;
  }

  public static boolean isHardWareVendorQualcomm()
  {
    if (Build.HARDWARE.matches("qcom"))
    {
      Log.i("phone_info", "Qualcomm platform");
      return true;
    }
    return false;
  }

  public static boolean isHdmiSwitchSet()
  {
    File localFile = new File("/sys/devices/virtual/switch/hdmi/state");
    Object localObject = localFile;
    if (!localFile.exists())
      localObject = new File("/sys/class/switch/hdmi/state");
    boolean bool = false;
    try
    {
      localObject = new Scanner((File)localObject);
      int i = ((Scanner)localObject).nextInt();
      ((Scanner)localObject).close();
      if (i > 0)
        bool = true;
      return bool;
    }
    catch (Exception localException)
    {
    }
    return false;
  }

  public static int isInt(ArrayList<Integer> paramArrayList, int paramInt)
  {
    int i = 0;
    while (i < paramArrayList.size())
    {
      if (((Integer)paramArrayList.get(i)).intValue() == paramInt)
        return i;
      i += 1;
    }
    return -1;
  }

  public static boolean isInterval(String paramString, int paramInt1, int paramInt2)
  {
    if ((paramString != null) && (paramString != ""))
    {
      if (paramString.isEmpty())
        return false;
      paramString = paramString.split("x");
    }
    try
    {
      if (Integer.valueOf(paramString[0]).intValue() >= paramInt1)
      {
        paramInt1 = Integer.valueOf(paramString[0]).intValue();
        if (paramInt1 <= paramInt2)
          return true;
      }
      return false;
      return false;
    }
    catch (NumberFormatException paramString)
    {
    }
    return false;
  }

  public static boolean isPicUrl(String paramString)
  {
    return (paramString != null) && ((paramString.endsWith(".jpg")) || (paramString.endsWith(".png")));
  }

  public static boolean isStringInList(String paramString, ArrayList<String> paramArrayList)
  {
    if ((paramString != null) && (paramString != ""))
    {
      if (paramString.isEmpty())
        return true;
      int i = 0;
      while (i < paramArrayList.size())
      {
        if (paramString.equals(paramArrayList.get(i)))
          return true;
        i += 1;
      }
      return false;
    }
    return true;
  }

  public static boolean isStringValid(String paramString)
  {
    return (paramString != null) && (paramString != "") && (!paramString.isEmpty());
  }

  @RequiresApi(api=26)
  public static String saveDrawable(Context paramContext, Drawable paramDrawable, String paramString)
  {
    paramDrawable = getAppIcon(paramDrawable);
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramContext.getFilesDir());
    localStringBuilder.append(File.separator);
    localStringBuilder.append(paramString);
    localStringBuilder.append(".png");
    paramContext = localStringBuilder.toString();
    paramString = new StringBuilder();
    paramString.append("Save Drawable Path");
    paramString.append(paramContext);
    MyLog.i("my_tag", paramString.toString());
    try
    {
      paramString = new FileOutputStream(paramContext);
      paramDrawable.compress(Bitmap.CompressFormat.PNG, 100, paramString);
      paramString.close();
      return paramContext;
    }
    catch (Exception paramContext)
    {
      paramDrawable = new StringBuilder();
      paramDrawable.append("Save Drawable Error");
      paramDrawable.append(paramContext);
      MyLog.i("my_tag", paramDrawable.toString());
    }
    return null;
  }

  public static void setGridViewHeight(GridView paramGridView)
  {
    Object localObject = paramGridView.getAdapter();
    if (localObject == null)
      return;
    if (((ListAdapter)localObject).getCount() <= 12)
      return;
    paramGridView.getNumColumns();
    localObject = ((ListAdapter)localObject).getView(0, null, paramGridView);
    ((View)localObject).measure(0, 0);
    int i = ((View)localObject).getMeasuredHeight();
    localObject = paramGridView.getLayoutParams();
    ((ViewGroup.LayoutParams)localObject).height = (i * 3 + 20);
    paramGridView.setLayoutParams((ViewGroup.LayoutParams)localObject);
  }

  public static void showMsg(Context paramContext, String paramString)
  {
    Toast.makeText(paramContext, paramString, 0).show();
  }

  public static void showMsg(Context paramContext, String paramString, int paramInt)
  {
    Toast localToast = new Toast(paramContext);
    localToast.setGravity(17, 0, 0);
    paramContext = LayoutInflater.from(paramContext).inflate(R.layout.toast_view, null);
    ((TextView)paramContext.findViewById(R.id.tv_message_toast)).setText(paramString);
    localToast.setView(paramContext);
    localToast.setDuration(1);
    localToast.show();
  }

  public static void startInstallApp(Context paramContext, String paramString)
  {
    String str = paramContext.getPackageManager().getLaunchIntentForPackage(paramString).getComponent().getClassName();
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.MAIN");
    localIntent.addCategory("android.intent.category.LAUNCHER");
    localIntent.setFlags(270532608);
    localIntent.setComponent(new ComponentName(paramString, str));
    paramContext.startActivity(localIntent);
  }
}
