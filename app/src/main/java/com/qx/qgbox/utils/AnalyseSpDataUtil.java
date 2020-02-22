package com.qx.qgbox.utils;

import com.qx.qgbox.entitys.MyKeyMap;
import com.qx.qgbox.entitys.NormalKey;
import com.qx.qgbox.gamemouse.DataSaverM;
import com.qx.qgbox.service.bluetoothdevmanager;
import java.util.ArrayList;

public class AnalyseSpDataUtil
{
  public static byte[] changeDataToByteArray(DataSaverM[] paramArrayOfDataSaverM, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    byte[] arrayOfByte1 = new byte[264];
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("resolutionX = ");
    ((StringBuilder)localObject1).append(paramInt1);
    ((StringBuilder)localObject1).append(";  resolutionY = ");
    ((StringBuilder)localObject1).append(paramInt2);
    MyLog.i("my_tag", ((StringBuilder)localObject1).toString());
    Object localObject2 = ChangeDataUtil.intToByteArray(paramInt1);
    byte[] arrayOfByte2 = ChangeDataUtil.intToByteArray(paramInt2);
    if (!paramArrayOfDataSaverM[55].ms.equalsIgnoreCase("-1"));
    try
    {
      localObject1 = ChangeDataUtil.intToByteArray(Integer.valueOf(paramArrayOfDataSaverM[55].ms).intValue());
      break label123;
      label104: localObject1 = ChangeDataUtil.intToByteArray(bluetoothdevmanager.radius);
      break label123;
      localObject1 = ChangeDataUtil.intToByteArray(bluetoothdevmanager.radius);
      label123: arrayOfByte1[2] = 1;
      byte[] arrayOfByte3 = ChangeDataUtil.intToByteArray(paramInt3);
      arrayOfByte1[3] = arrayOfByte3[1];
      int i = 0;
      arrayOfByte1[4] = arrayOfByte3[0];
      arrayOfByte1[5] = localObject2[1];
      arrayOfByte1[6] = localObject2[0];
      arrayOfByte1[7] = arrayOfByte2[1];
      arrayOfByte1[8] = arrayOfByte2[0];
      localObject2 = new ArrayList();
      paramInt2 = 0;
      while (true)
      {
        paramInt3 = i;
        if (paramInt2 >= paramArrayOfDataSaverM.length)
          break;
        if ((paramArrayOfDataSaverM[paramInt2].name.equalsIgnoreCase("mouse")) && (paramArrayOfDataSaverM[paramInt2].x != -1))
        {
          arrayOfByte2 = ChangeDataUtil.intToByteArray(paramInt1 - paramArrayOfDataSaverM[paramInt2].y);
          arrayOfByte3 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaverM[paramInt2].x);
          arrayOfByte1[9] = arrayOfByte2[1];
          arrayOfByte1[10] = arrayOfByte2[0];
          arrayOfByte1[11] = arrayOfByte3[1];
          arrayOfByte1[12] = arrayOfByte3[0];
          arrayOfByte1[13] = ((byte)paramInt4);
        }
        if ((paramArrayOfDataSaverM[paramInt2].name.equalsIgnoreCase("center")) && (paramArrayOfDataSaverM[paramInt2].x != -1))
        {
          arrayOfByte2 = ChangeDataUtil.intToByteArray(paramInt1 - paramArrayOfDataSaverM[paramInt2].y);
          arrayOfByte3 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaverM[paramInt2].x);
          arrayOfByte1[14] = arrayOfByte2[1];
          arrayOfByte1[15] = arrayOfByte2[0];
          arrayOfByte1[16] = arrayOfByte3[1];
          arrayOfByte1[17] = arrayOfByte3[0];
          arrayOfByte1[18] = localObject1[1];
          arrayOfByte1[19] = localObject1[0];
          arrayOfByte1[20] = 26;
          arrayOfByte1[21] = 22;
          arrayOfByte1[22] = 4;
          arrayOfByte1[23] = 7;
        }
        Object localObject3;
        if ((!paramArrayOfDataSaverM[paramInt2].name.equals("-1")) && (paramArrayOfDataSaverM[paramInt2].x != -1) && (paramInt2 < 107) && (CommonUtils.getKeyCodeByeName(paramArrayOfDataSaverM[paramInt2].name) != -1) && (paramInt2 != 55) && (paramInt2 != 56) && (paramInt2 != 98))
        {
          arrayOfByte2 = ChangeDataUtil.intToByteArray(paramInt1 - paramArrayOfDataSaverM[paramInt2].y);
          arrayOfByte3 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaverM[paramInt2].x);
          localObject3 = new NormalKey();
          ((NormalKey)localObject3).setType((byte)1);
          ((NormalKey)localObject3).setCode((byte)CommonUtils.getKeyCodeByeName(paramArrayOfDataSaverM[paramInt2].name));
          ((NormalKey)localObject3).setP1xl(arrayOfByte2[1]);
          ((NormalKey)localObject3).setP1xh(arrayOfByte2[0]);
          ((NormalKey)localObject3).setP1yl(arrayOfByte3[1]);
          ((NormalKey)localObject3).setP1yh(arrayOfByte3[0]);
          ((NormalKey)localObject3).setP2xl((byte)0);
          ((NormalKey)localObject3).setP2xh((byte)0);
          ((NormalKey)localObject3).setP2yl((byte)0);
          ((NormalKey)localObject3).setP2yh((byte)0);
          ((ArrayList)localObject2).add(localObject3);
        }
        byte[] arrayOfByte4;
        NormalKey localNormalKey;
        if ((paramArrayOfDataSaverM[paramInt2].property == 1) && (paramArrayOfDataSaverM[paramInt2].x != -1) && (paramInt2 > 106) && (paramInt2 < 137) && (CommonUtils.getKeyCodeByeName(paramArrayOfDataSaverM[paramInt2].name) != -1))
        {
          arrayOfByte2 = ChangeDataUtil.intToByteArray(paramInt1 - paramArrayOfDataSaverM[paramInt2].y);
          arrayOfByte3 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaverM[paramInt2].x);
          paramInt3 = paramInt2 + 30;
          localObject3 = ChangeDataUtil.intToByteArray(paramInt1 - paramArrayOfDataSaverM[paramInt3].y);
          arrayOfByte4 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaverM[paramInt3].x);
          localNormalKey = new NormalKey();
          localNormalKey.setType((byte)2);
          localNormalKey.setCode((byte)CommonUtils.getKeyCodeByeName(paramArrayOfDataSaverM[paramInt3].name));
          localNormalKey.setP1xl(arrayOfByte2[1]);
          localNormalKey.setP1xh(arrayOfByte2[0]);
          localNormalKey.setP1yl(arrayOfByte3[1]);
          localNormalKey.setP1yh(arrayOfByte3[0]);
          localNormalKey.setP2xl(localObject3[1]);
          localNormalKey.setP2xh(localObject3[0]);
          localNormalKey.setP2yl(arrayOfByte4[1]);
          localNormalKey.setP2yh(arrayOfByte4[0]);
          ((ArrayList)localObject2).add(localNormalKey);
        }
        if ((paramArrayOfDataSaverM[paramInt2].property == 4) && (paramArrayOfDataSaverM[paramInt2].x != -1) && (paramInt2 > 230) && (paramInt2 < 261) && (CommonUtils.getKeyCodeByeName(paramArrayOfDataSaverM[paramInt2].name) != -1))
        {
          arrayOfByte2 = ChangeDataUtil.intToByteArray(paramInt1 - paramArrayOfDataSaverM[paramInt2].y);
          arrayOfByte3 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaverM[paramInt2].x);
          paramInt3 = paramInt2 + 30;
          localObject3 = ChangeDataUtil.intToByteArray(paramInt1 - paramArrayOfDataSaverM[paramInt3].y);
          arrayOfByte4 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaverM[paramInt3].x);
          localNormalKey = new NormalKey();
          localNormalKey.setType((byte)3);
          localNormalKey.setCode((byte)CommonUtils.getKeyCodeByeName(paramArrayOfDataSaverM[paramInt2].name));
          localNormalKey.setP1xl(arrayOfByte2[1]);
          localNormalKey.setP1xh(arrayOfByte2[0]);
          localNormalKey.setP1yl(arrayOfByte3[1]);
          localNormalKey.setP1yh(arrayOfByte3[0]);
          localNormalKey.setP2xl(localObject3[1]);
          localNormalKey.setP2xh(localObject3[0]);
          localNormalKey.setP2yl(arrayOfByte4[1]);
          localNormalKey.setP2yh(arrayOfByte4[0]);
          ((ArrayList)localObject2).add(localNormalKey);
        }
        if ((paramArrayOfDataSaverM[paramInt2].property == 5) && (paramArrayOfDataSaverM[paramInt2].x != -1) && (paramInt2 > 290) && (paramInt2 < 321) && (CommonUtils.getKeyCodeByeName(paramArrayOfDataSaverM[paramInt2].name) != -1))
        {
          arrayOfByte2 = ChangeDataUtil.intToByteArray(paramInt1 - paramArrayOfDataSaverM[paramInt2].y);
          arrayOfByte3 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaverM[paramInt2].x);
          localObject3 = new NormalKey();
          ((NormalKey)localObject3).setType((byte)6);
          ((NormalKey)localObject3).setCode((byte)CommonUtils.getKeyCodeByeName(paramArrayOfDataSaverM[paramInt2].name));
          ((NormalKey)localObject3).setP1xl(arrayOfByte2[1]);
          ((NormalKey)localObject3).setP1xh(arrayOfByte2[0]);
          ((NormalKey)localObject3).setP1yl(arrayOfByte3[1]);
          ((NormalKey)localObject3).setP1yh(arrayOfByte3[0]);
          ((NormalKey)localObject3).setP2xl((byte)3);
          ((NormalKey)localObject3).setP2xh((byte)0);
          ((NormalKey)localObject3).setP2yl((byte)0);
          ((NormalKey)localObject3).setP2yh((byte)0);
          ((ArrayList)localObject2).add(localObject3);
        }
        if ((paramArrayOfDataSaverM[paramInt2].property == 6) && (paramArrayOfDataSaverM[paramInt2].x != -1) && (paramInt2 > 320) && (paramInt2 < 351) && (CommonUtils.getKeyCodeByeName(paramArrayOfDataSaverM[paramInt2].name) != -1))
        {
          arrayOfByte2 = ChangeDataUtil.intToByteArray(paramInt1 - paramArrayOfDataSaverM[paramInt2].y);
          arrayOfByte3 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaverM[paramInt2].x);
          localObject3 = new NormalKey();
          ((NormalKey)localObject3).setType((byte)7);
          ((NormalKey)localObject3).setCode((byte)CommonUtils.getKeyCodeByeName(paramArrayOfDataSaverM[paramInt2].name));
          ((NormalKey)localObject3).setP1xl(arrayOfByte2[1]);
          ((NormalKey)localObject3).setP1xh(arrayOfByte2[0]);
          ((NormalKey)localObject3).setP1yl(arrayOfByte3[1]);
          ((NormalKey)localObject3).setP1yh(arrayOfByte3[0]);
          ((NormalKey)localObject3).setP2xl((byte)10);
          ((NormalKey)localObject3).setP2xh((byte)0);
          ((NormalKey)localObject3).setP2yl((byte)0);
          ((NormalKey)localObject3).setP2yh((byte)0);
          ((ArrayList)localObject2).add(localObject3);
        }
        if (paramArrayOfDataSaverM[paramInt2].property == 2)
        {
          arrayOfByte2 = ChangeDataUtil.intToByteArray(paramInt1 - paramArrayOfDataSaverM[paramInt2].y);
          arrayOfByte3 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaverM[paramInt2].x);
          localObject3 = new NormalKey();
          ((NormalKey)localObject3).setType((byte)4);
          ((NormalKey)localObject3).setCode((byte)-30);
          ((NormalKey)localObject3).setP1xl(arrayOfByte2[1]);
          ((NormalKey)localObject3).setP1xh(arrayOfByte2[0]);
          ((NormalKey)localObject3).setP1yl(arrayOfByte3[1]);
          ((NormalKey)localObject3).setP1yh(arrayOfByte3[0]);
          ((NormalKey)localObject3).setP2xl((byte)0);
          ((NormalKey)localObject3).setP2xh((byte)0);
          ((NormalKey)localObject3).setP2yl((byte)0);
          ((NormalKey)localObject3).setP2yh((byte)0);
          ((ArrayList)localObject2).add(localObject3);
        }
        paramInt2 += 1;
      }
      while (paramInt3 < ((ArrayList)localObject2).size())
      {
        paramInt1 = paramInt3 * 10;
        paramInt2 = paramInt1 + 33;
        if (paramInt2 < arrayOfByte1.length)
        {
          arrayOfByte1[(paramInt1 + 24)] = ((NormalKey)((ArrayList)localObject2).get(paramInt3)).getType();
          arrayOfByte1[(paramInt1 + 25)] = ((NormalKey)((ArrayList)localObject2).get(paramInt3)).getCode();
          arrayOfByte1[(paramInt1 + 26)] = ((NormalKey)((ArrayList)localObject2).get(paramInt3)).getP1xl();
          arrayOfByte1[(paramInt1 + 27)] = ((NormalKey)((ArrayList)localObject2).get(paramInt3)).getP1xh();
          arrayOfByte1[(paramInt1 + 28)] = ((NormalKey)((ArrayList)localObject2).get(paramInt3)).getP1yl();
          arrayOfByte1[(paramInt1 + 29)] = ((NormalKey)((ArrayList)localObject2).get(paramInt3)).getP1yh();
          arrayOfByte1[(paramInt1 + 30)] = ((NormalKey)((ArrayList)localObject2).get(paramInt3)).getP2xl();
          arrayOfByte1[(paramInt1 + 31)] = ((NormalKey)((ArrayList)localObject2).get(paramInt3)).getP2xh();
          arrayOfByte1[(paramInt1 + 32)] = ((NormalKey)((ArrayList)localObject2).get(paramInt3)).getP2yl();
          arrayOfByte1[paramInt2] = ((NormalKey)((ArrayList)localObject2).get(paramInt3)).getP2yh();
        }
        paramInt3 += 1;
      }
      return arrayOfByte1;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      break label104;
    }
  }

  public static DataSaverM[] initMdatasaverByByteArray(byte[] paramArrayOfByte, float paramFloat1, float paramFloat2)
  {
    DataSaverM[] arrayOfDataSaverM = new DataSaverM[351];
    int i = 0;
    while (i < 351)
    {
      arrayOfDataSaverM[i] = new DataSaverM();
      arrayOfDataSaverM[i].property = -1;
      arrayOfDataSaverM[i].name = "-1";
      arrayOfDataSaverM[i].x = -1;
      arrayOfDataSaverM[i].y = -1;
      i += 1;
    }
    int j = ChangeDataUtil.getIntegerByBit(paramArrayOfByte[5], paramArrayOfByte[6]);
    i = ChangeDataUtil.getIntegerByBit(paramArrayOfByte[7], paramArrayOfByte[8]);
    arrayOfDataSaverM[56].property = -1;
    arrayOfDataSaverM[56].name = "mouse";
    Object localObject = arrayOfDataSaverM[56];
    float f2 = ChangeDataUtil.getIntegerByBit(paramArrayOfByte[11], paramArrayOfByte[12]);
    float f1 = i;
    ((DataSaverM)localObject).x = ((int)(f2 * paramFloat1 / f1));
    localObject = arrayOfDataSaverM[56];
    f2 = j - ChangeDataUtil.getIntegerByBit(paramArrayOfByte[9], paramArrayOfByte[10]);
    float f3 = j;
    ((DataSaverM)localObject).y = ((int)(f2 * paramFloat2 / f3));
    arrayOfDataSaverM[56].joystick = "-1";
    arrayOfDataSaverM[56].rumble = "-1";
    arrayOfDataSaverM[56].whichmoto = "-1";
    arrayOfDataSaverM[56].ms = "-1";
    arrayOfDataSaverM[55].property = -1;
    arrayOfDataSaverM[55].name = "center";
    arrayOfDataSaverM[55].x = ((int)(ChangeDataUtil.getIntegerByBit(paramArrayOfByte[16], paramArrayOfByte[17]) * paramFloat1 / f1));
    arrayOfDataSaverM[55].y = ((int)((j - ChangeDataUtil.getIntegerByBit(paramArrayOfByte[14], paramArrayOfByte[15])) * paramFloat2 / f3));
    arrayOfDataSaverM[55].joystick = "-1";
    arrayOfDataSaverM[55].rumble = "-1";
    arrayOfDataSaverM[55].whichmoto = "-1";
    arrayOfDataSaverM[55].ms = "-1";
    arrayOfDataSaverM['Ñ'].property = 3;
    arrayOfDataSaverM['Ñ'].name = "a";
    arrayOfDataSaverM['Ñ'].x = ((int)((ChangeDataUtil.getIntegerByBit(paramArrayOfByte[16], paramArrayOfByte[17]) - ChangeDataUtil.getIntegerByBit(paramArrayOfByte[18], paramArrayOfByte[19])) * paramFloat1 / f1));
    arrayOfDataSaverM['Ñ'].y = ((int)((j - ChangeDataUtil.getIntegerByBit(paramArrayOfByte[14], paramArrayOfByte[15])) * paramFloat2 / f3));
    arrayOfDataSaverM['Ñ'].joystick = "-1";
    arrayOfDataSaverM['Ñ'].rumble = "-1";
    arrayOfDataSaverM['Ñ'].whichmoto = "-1";
    arrayOfDataSaverM['Ñ'].ms = "-1";
    arrayOfDataSaverM['Ò'].property = 3;
    arrayOfDataSaverM['Ò'].name = "s";
    arrayOfDataSaverM['Ò'].x = ((int)(ChangeDataUtil.getIntegerByBit(paramArrayOfByte[16], paramArrayOfByte[17]) * paramFloat1 / f1));
    arrayOfDataSaverM['Ò'].y = ((int)((j - ChangeDataUtil.getIntegerByBit(paramArrayOfByte[14], paramArrayOfByte[15]) + ChangeDataUtil.getIntegerByBit(paramArrayOfByte[18], paramArrayOfByte[19])) * paramFloat2 / f3));
    arrayOfDataSaverM['Ò'].joystick = "-1";
    arrayOfDataSaverM['Ò'].rumble = "-1";
    arrayOfDataSaverM['Ò'].whichmoto = "-1";
    arrayOfDataSaverM['Ò'].ms = "-1";
    arrayOfDataSaverM['Ó'].property = 3;
    arrayOfDataSaverM['Ó'].name = "d";
    arrayOfDataSaverM['Ó'].x = ((int)((ChangeDataUtil.getIntegerByBit(paramArrayOfByte[16], paramArrayOfByte[17]) + ChangeDataUtil.getIntegerByBit(paramArrayOfByte[18], paramArrayOfByte[19])) * paramFloat1 / f1));
    arrayOfDataSaverM['Ó'].y = ((int)((j - ChangeDataUtil.getIntegerByBit(paramArrayOfByte[14], paramArrayOfByte[15])) * paramFloat2 / f3));
    arrayOfDataSaverM['Ó'].joystick = "-1";
    arrayOfDataSaverM['Ó'].rumble = "-1";
    arrayOfDataSaverM['Ó'].whichmoto = "-1";
    arrayOfDataSaverM['Ó'].ms = "-1";
    arrayOfDataSaverM['Ì'].property = 3;
    arrayOfDataSaverM['Ì'].name = "w";
    arrayOfDataSaverM['Ì'].x = ((int)(ChangeDataUtil.getIntegerByBit(paramArrayOfByte[16], paramArrayOfByte[17]) * paramFloat1 / f1));
    arrayOfDataSaverM['Ì'].y = ((int)((j - ChangeDataUtil.getIntegerByBit(paramArrayOfByte[14], paramArrayOfByte[15]) - ChangeDataUtil.getIntegerByBit(paramArrayOfByte[18], paramArrayOfByte[19])) * paramFloat2 / f3));
    arrayOfDataSaverM['Ì'].joystick = "-1";
    arrayOfDataSaverM['Ì'].rumble = "-1";
    arrayOfDataSaverM['Ì'].whichmoto = "-1";
    arrayOfDataSaverM['Ì'].ms = "-1";
    localObject = new ArrayList();
    i = 0;
    while (i < 24)
    {
      NormalKey localNormalKey = new NormalKey();
      int k = i * 10;
      localNormalKey.setType(paramArrayOfByte[(k + 24)]);
      localNormalKey.setCode(paramArrayOfByte[(k + 25)]);
      localNormalKey.setP1xl(paramArrayOfByte[(k + 26)]);
      localNormalKey.setP1xh(paramArrayOfByte[(k + 27)]);
      localNormalKey.setP1yl(paramArrayOfByte[(k + 28)]);
      localNormalKey.setP1yh(paramArrayOfByte[(k + 29)]);
      localNormalKey.setP2xl(paramArrayOfByte[(k + 30)]);
      localNormalKey.setP2xh(paramArrayOfByte[(k + 31)]);
      localNormalKey.setP2yl(paramArrayOfByte[(k + 32)]);
      localNormalKey.setP2yh(paramArrayOfByte[(k + 33)]);
      ((ArrayList)localObject).add(localNormalKey);
      i += 1;
    }
    i = 0;
    while (i < ((ArrayList)localObject).size())
    {
      new MyKeyMap();
      paramArrayOfByte = CommonUtils.getKeyPos(((NormalKey)((ArrayList)localObject).get(i)).getCode() & 0xFF);
      if (((NormalKey)((ArrayList)localObject).get(i)).getType() == 1)
      {
        arrayOfDataSaverM[paramArrayOfByte._id].property = -1;
        arrayOfDataSaverM[paramArrayOfByte._id].name = paramArrayOfByte.name;
        arrayOfDataSaverM[paramArrayOfByte._id].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1yh()) * paramFloat1 / f1));
        arrayOfDataSaverM[paramArrayOfByte._id].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1xh())) * paramFloat2 / f3));
        arrayOfDataSaverM[paramArrayOfByte._id].joystick = "-1";
        arrayOfDataSaverM[paramArrayOfByte._id].rumble = "-1";
        arrayOfDataSaverM[paramArrayOfByte._id].whichmoto = "-1";
        arrayOfDataSaverM[paramArrayOfByte._id].ms = "-1";
      }
      if (((NormalKey)((ArrayList)localObject).get(i)).getType() == 2)
      {
        if (paramArrayOfByte._id < 30)
        {
          arrayOfDataSaverM[(paramArrayOfByte._id + 107)].property = 1;
          arrayOfDataSaverM[(paramArrayOfByte._id + 107)].name = paramArrayOfByte.name;
          arrayOfDataSaverM[(paramArrayOfByte._id + 107)].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1yh()) * paramFloat1 / f1));
          arrayOfDataSaverM[(paramArrayOfByte._id + 107)].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1xh())) * paramFloat2 / f3));
          arrayOfDataSaverM[(paramArrayOfByte._id + 107)].joystick = "-1";
          arrayOfDataSaverM[(paramArrayOfByte._id + 107)].rumble = "-1";
          arrayOfDataSaverM[(paramArrayOfByte._id + 107)].whichmoto = "-1";
          arrayOfDataSaverM[(paramArrayOfByte._id + 107)].ms = "-1";
          arrayOfDataSaverM[(paramArrayOfByte._id + 137)].property = 1;
          arrayOfDataSaverM[(paramArrayOfByte._id + 137)].name = paramArrayOfByte.name;
          arrayOfDataSaverM[(paramArrayOfByte._id + 137)].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP2yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP2yh()) * paramFloat1 / f1));
          arrayOfDataSaverM[(paramArrayOfByte._id + 137)].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP2xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP2xh())) * paramFloat2 / f3));
          arrayOfDataSaverM[(paramArrayOfByte._id + 137)].joystick = "-1";
          arrayOfDataSaverM[(paramArrayOfByte._id + 137)].rumble = "-1";
          arrayOfDataSaverM[(paramArrayOfByte._id + 137)].whichmoto = "-1";
          arrayOfDataSaverM[(paramArrayOfByte._id + 137)].ms = "-1";
        }
        if (paramArrayOfByte._id == 104)
        {
          arrayOfDataSaverM[''].property = 1;
          arrayOfDataSaverM[''].name = paramArrayOfByte.name;
          arrayOfDataSaverM[''].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1yh()) * paramFloat1 / f1));
          arrayOfDataSaverM[''].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1xh())) * paramFloat2 / f3));
          arrayOfDataSaverM[''].joystick = "-1";
          arrayOfDataSaverM[''].rumble = "-1";
          arrayOfDataSaverM[''].whichmoto = "-1";
          arrayOfDataSaverM[''].ms = "-1";
          arrayOfDataSaverM['¥'].property = 1;
          arrayOfDataSaverM['¥'].name = paramArrayOfByte.name;
          arrayOfDataSaverM['¥'].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP2yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP2yh()) * paramFloat1 / f1));
          arrayOfDataSaverM['¥'].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP2xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP2xh())) * paramFloat2 / f3));
          arrayOfDataSaverM['¥'].joystick = "-1";
          arrayOfDataSaverM['¥'].rumble = "-1";
          arrayOfDataSaverM['¥'].whichmoto = "-1";
          arrayOfDataSaverM['¥'].ms = "-1";
        }
        if (paramArrayOfByte._id == 106)
        {
          arrayOfDataSaverM[''].property = 1;
          arrayOfDataSaverM[''].name = paramArrayOfByte.name;
          arrayOfDataSaverM[''].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1yh()) * paramFloat1 / f1));
          arrayOfDataSaverM[''].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1xh())) * paramFloat2 / f3));
          arrayOfDataSaverM[''].joystick = "-1";
          arrayOfDataSaverM[''].rumble = "-1";
          arrayOfDataSaverM[''].whichmoto = "-1";
          arrayOfDataSaverM[''].ms = "-1";
          arrayOfDataSaverM['¦'].property = 1;
          arrayOfDataSaverM['¦'].name = paramArrayOfByte.name;
          arrayOfDataSaverM['¦'].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP2yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP2yh()) * paramFloat1 / f1));
          arrayOfDataSaverM['¦'].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP2xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP2xh())) * paramFloat2 / f3));
          arrayOfDataSaverM['¦'].joystick = "-1";
          arrayOfDataSaverM['¦'].rumble = "-1";
          arrayOfDataSaverM['¦'].whichmoto = "-1";
          arrayOfDataSaverM['¦'].ms = "-1";
        }
        if (paramArrayOfByte._id == 41)
        {
          arrayOfDataSaverM[''].property = 1;
          arrayOfDataSaverM[''].name = paramArrayOfByte.name;
          arrayOfDataSaverM[''].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1yh()) * paramFloat1 / f1));
          arrayOfDataSaverM[''].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1xh())) * paramFloat2 / f3));
          arrayOfDataSaverM[''].joystick = "-1";
          arrayOfDataSaverM[''].rumble = "-1";
          arrayOfDataSaverM[''].whichmoto = "-1";
          arrayOfDataSaverM[''].ms = "-1";
          arrayOfDataSaverM[' '].property = 1;
          arrayOfDataSaverM[' '].name = paramArrayOfByte.name;
          arrayOfDataSaverM[' '].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP2yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP2yh()) * paramFloat1 / f1));
          arrayOfDataSaverM[' '].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP2xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP2xh())) * paramFloat2 / f3));
          arrayOfDataSaverM[' '].joystick = "-1";
          arrayOfDataSaverM[' '].rumble = "-1";
          arrayOfDataSaverM[' '].whichmoto = "-1";
          arrayOfDataSaverM[' '].ms = "-1";
        }
        if (paramArrayOfByte._id == 42)
        {
          arrayOfDataSaverM[121].property = 1;
          arrayOfDataSaverM[121].name = paramArrayOfByte.name;
          arrayOfDataSaverM[121].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1yh()) * paramFloat1 / f1));
          arrayOfDataSaverM[121].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1xh())) * paramFloat2 / f3));
          arrayOfDataSaverM[121].joystick = "-1";
          arrayOfDataSaverM[121].rumble = "-1";
          arrayOfDataSaverM[121].whichmoto = "-1";
          arrayOfDataSaverM[121].ms = "-1";
          arrayOfDataSaverM[''].property = 1;
          arrayOfDataSaverM[''].name = paramArrayOfByte.name;
          arrayOfDataSaverM[''].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP2yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP2yh()) * paramFloat1 / f1));
          arrayOfDataSaverM[''].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP2xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP2xh())) * paramFloat2 / f3));
          arrayOfDataSaverM[''].joystick = "-1";
          arrayOfDataSaverM[''].rumble = "-1";
          arrayOfDataSaverM[''].whichmoto = "-1";
          arrayOfDataSaverM[''].ms = "-1";
        }
        if (paramArrayOfByte._id == 96)
        {
          arrayOfDataSaverM[119].property = 1;
          arrayOfDataSaverM[119].name = paramArrayOfByte.name;
          arrayOfDataSaverM[119].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1yh()) * paramFloat1 / f1));
          arrayOfDataSaverM[119].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1xh())) * paramFloat2 / f3));
          arrayOfDataSaverM[119].joystick = "-1";
          arrayOfDataSaverM[119].rumble = "-1";
          arrayOfDataSaverM[119].whichmoto = "-1";
          arrayOfDataSaverM[119].ms = "-1";
          arrayOfDataSaverM[''].property = 1;
          arrayOfDataSaverM[''].name = paramArrayOfByte.name;
          arrayOfDataSaverM[''].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP2yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP2yh()) * paramFloat1 / f1));
          arrayOfDataSaverM[''].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP2xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP2xh())) * paramFloat2 / f3));
          arrayOfDataSaverM[''].joystick = "-1";
          arrayOfDataSaverM[''].rumble = "-1";
          arrayOfDataSaverM[''].whichmoto = "-1";
          arrayOfDataSaverM[''].ms = "-1";
        }
        if (paramArrayOfByte._id == 97)
        {
          arrayOfDataSaverM[120].property = 1;
          arrayOfDataSaverM[120].name = paramArrayOfByte.name;
          arrayOfDataSaverM[120].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1yh()) * paramFloat1 / f1));
          arrayOfDataSaverM[120].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1xh())) * paramFloat2 / f3));
          arrayOfDataSaverM[120].joystick = "-1";
          arrayOfDataSaverM[120].rumble = "-1";
          arrayOfDataSaverM[120].whichmoto = "-1";
          arrayOfDataSaverM[120].ms = "-1";
          arrayOfDataSaverM[''].property = 1;
          arrayOfDataSaverM[''].name = paramArrayOfByte.name;
          arrayOfDataSaverM[''].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP2yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP2yh()) * paramFloat1 / f1));
          arrayOfDataSaverM[''].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP2xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP2xh())) * paramFloat2 / f3));
          arrayOfDataSaverM[''].joystick = "-1";
          arrayOfDataSaverM[''].rumble = "-1";
          arrayOfDataSaverM[''].whichmoto = "-1";
          arrayOfDataSaverM[''].ms = "-1";
        }
      }
      if (((NormalKey)((ArrayList)localObject).get(i)).getType() == 3)
      {
        if (paramArrayOfByte._id < 30)
        {
          arrayOfDataSaverM[(paramArrayOfByte._id + 231)].property = 4;
          arrayOfDataSaverM[(paramArrayOfByte._id + 231)].name = paramArrayOfByte.name;
          arrayOfDataSaverM[(paramArrayOfByte._id + 231)].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1yh()) * paramFloat1 / f1));
          arrayOfDataSaverM[(paramArrayOfByte._id + 231)].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1xh())) * paramFloat2 / f3));
          arrayOfDataSaverM[(paramArrayOfByte._id + 231)].joystick = "-1";
          arrayOfDataSaverM[(paramArrayOfByte._id + 231)].rumble = "-1";
          arrayOfDataSaverM[(paramArrayOfByte._id + 231)].whichmoto = "-1";
          arrayOfDataSaverM[(paramArrayOfByte._id + 231)].ms = "-1";
          arrayOfDataSaverM[(paramArrayOfByte._id + 261)].property = 4;
          arrayOfDataSaverM[(paramArrayOfByte._id + 261)].name = paramArrayOfByte.name;
          arrayOfDataSaverM[(paramArrayOfByte._id + 261)].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP2yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP2yh()) * paramFloat1 / f1));
          arrayOfDataSaverM[(paramArrayOfByte._id + 261)].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP2xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP2xh())) * paramFloat2 / f3));
          arrayOfDataSaverM[(paramArrayOfByte._id + 261)].joystick = "-1";
          arrayOfDataSaverM[(paramArrayOfByte._id + 261)].rumble = "-1";
          arrayOfDataSaverM[(paramArrayOfByte._id + 261)].whichmoto = "-1";
          arrayOfDataSaverM[(paramArrayOfByte._id + 261)].ms = "-1";
        }
        if (paramArrayOfByte._id == 104)
        {
          arrayOfDataSaverM[259].property = 4;
          arrayOfDataSaverM[259].name = paramArrayOfByte.name;
          arrayOfDataSaverM[259].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1yh()) * paramFloat1 / f1));
          arrayOfDataSaverM[259].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1xh())) * paramFloat2 / f3));
          arrayOfDataSaverM[259].joystick = "-1";
          arrayOfDataSaverM[259].rumble = "-1";
          arrayOfDataSaverM[259].whichmoto = "-1";
          arrayOfDataSaverM[259].ms = "-1";
          arrayOfDataSaverM[289].property = 4;
          arrayOfDataSaverM[289].name = paramArrayOfByte.name;
          arrayOfDataSaverM[289].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP2yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP2yh()) * paramFloat1 / f1));
          arrayOfDataSaverM[289].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP2xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP2xh())) * paramFloat2 / f3));
          arrayOfDataSaverM[289].joystick = "-1";
          arrayOfDataSaverM[289].rumble = "-1";
          arrayOfDataSaverM[289].whichmoto = "-1";
          arrayOfDataSaverM[289].ms = "-1";
        }
        if (paramArrayOfByte._id == 106)
        {
          arrayOfDataSaverM[260].property = 4;
          arrayOfDataSaverM[260].name = paramArrayOfByte.name;
          arrayOfDataSaverM[260].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1yh()) * paramFloat1 / f1));
          arrayOfDataSaverM[260].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1xh())) * paramFloat2 / f3));
          arrayOfDataSaverM[260].joystick = "-1";
          arrayOfDataSaverM[260].rumble = "-1";
          arrayOfDataSaverM[260].whichmoto = "-1";
          arrayOfDataSaverM[260].ms = "-1";
          arrayOfDataSaverM[290].property = 4;
          arrayOfDataSaverM[290].name = paramArrayOfByte.name;
          arrayOfDataSaverM[290].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP2yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP2yh()) * paramFloat1 / f1));
          arrayOfDataSaverM[290].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP2xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP2xh())) * paramFloat2 / f3));
          arrayOfDataSaverM[290].joystick = "-1";
          arrayOfDataSaverM[290].rumble = "-1";
          arrayOfDataSaverM[290].whichmoto = "-1";
          arrayOfDataSaverM[290].ms = "-1";
        }
        if (paramArrayOfByte._id == 42)
        {
          arrayOfDataSaverM['õ'].property = 4;
          arrayOfDataSaverM['õ'].name = paramArrayOfByte.name;
          arrayOfDataSaverM['õ'].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1yh()) * paramFloat1 / f1));
          arrayOfDataSaverM['õ'].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1xh())) * paramFloat2 / f3));
          arrayOfDataSaverM['õ'].joystick = "-1";
          arrayOfDataSaverM['õ'].rumble = "-1";
          arrayOfDataSaverM['õ'].whichmoto = "-1";
          arrayOfDataSaverM['õ'].ms = "-1";
          arrayOfDataSaverM[275].property = 4;
          arrayOfDataSaverM[275].name = paramArrayOfByte.name;
          arrayOfDataSaverM[275].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP2yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP2yh()) * paramFloat1 / f1));
          arrayOfDataSaverM[275].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP2xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP2xh())) * paramFloat2 / f3));
          arrayOfDataSaverM[275].joystick = "-1";
          arrayOfDataSaverM[275].rumble = "-1";
          arrayOfDataSaverM[275].whichmoto = "-1";
          arrayOfDataSaverM[275].ms = "-1";
        }
        if (paramArrayOfByte._id == 96)
        {
          arrayOfDataSaverM['ó'].property = 4;
          arrayOfDataSaverM['ó'].name = paramArrayOfByte.name;
          arrayOfDataSaverM['ó'].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1yh()) * paramFloat1 / f1));
          arrayOfDataSaverM['ó'].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1xh())) * paramFloat2 / f3));
          arrayOfDataSaverM['ó'].joystick = "-1";
          arrayOfDataSaverM['ó'].rumble = "-1";
          arrayOfDataSaverM['ó'].whichmoto = "-1";
          arrayOfDataSaverM['ó'].ms = "-1";
          arrayOfDataSaverM[273].property = 4;
          arrayOfDataSaverM[273].name = paramArrayOfByte.name;
          arrayOfDataSaverM[273].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP2yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP2yh()) * paramFloat1 / f1));
          arrayOfDataSaverM[273].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP2xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP2xh())) * paramFloat2 / f3));
          arrayOfDataSaverM[273].joystick = "-1";
          arrayOfDataSaverM[273].rumble = "-1";
          arrayOfDataSaverM[273].whichmoto = "-1";
          arrayOfDataSaverM[273].ms = "-1";
        }
        if (paramArrayOfByte._id == 97)
        {
          arrayOfDataSaverM['ô'].property = 4;
          arrayOfDataSaverM['ô'].name = paramArrayOfByte.name;
          arrayOfDataSaverM['ô'].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1yh()) * paramFloat1 / f1));
          arrayOfDataSaverM['ô'].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1xh())) * paramFloat2 / f3));
          arrayOfDataSaverM['ô'].joystick = "-1";
          arrayOfDataSaverM['ô'].rumble = "-1";
          arrayOfDataSaverM['ô'].whichmoto = "-1";
          arrayOfDataSaverM['ô'].ms = "-1";
          arrayOfDataSaverM[274].property = 4;
          arrayOfDataSaverM[274].name = paramArrayOfByte.name;
          arrayOfDataSaverM[274].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP2yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP2yh()) * paramFloat1 / f1));
          arrayOfDataSaverM[274].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP2xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP2xh())) * paramFloat2 / f3));
          arrayOfDataSaverM[274].joystick = "-1";
          arrayOfDataSaverM[274].rumble = "-1";
          arrayOfDataSaverM[274].whichmoto = "-1";
          arrayOfDataSaverM[274].ms = "-1";
        }
      }
      if (((NormalKey)((ArrayList)localObject).get(i)).getType() == 6)
      {
        if (paramArrayOfByte._id < 30)
        {
          arrayOfDataSaverM[(paramArrayOfByte._id + 291)].property = 5;
          arrayOfDataSaverM[(paramArrayOfByte._id + 291)].name = paramArrayOfByte.name;
          arrayOfDataSaverM[(paramArrayOfByte._id + 291)].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1yh()) * paramFloat1 / f1));
          arrayOfDataSaverM[(paramArrayOfByte._id + 291)].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1xh())) * paramFloat2 / f3));
          arrayOfDataSaverM[(paramArrayOfByte._id + 291)].joystick = "-1";
          arrayOfDataSaverM[(paramArrayOfByte._id + 291)].rumble = "-1";
          arrayOfDataSaverM[(paramArrayOfByte._id + 291)].whichmoto = "-1";
          arrayOfDataSaverM[(paramArrayOfByte._id + 291)].ms = "-1";
        }
        if (paramArrayOfByte._id == 104)
        {
          arrayOfDataSaverM[319].property = 5;
          arrayOfDataSaverM[319].name = paramArrayOfByte.name;
          arrayOfDataSaverM[319].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1yh()) * paramFloat1 / f1));
          arrayOfDataSaverM[319].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1xh())) * paramFloat2 / f3));
          arrayOfDataSaverM[319].joystick = "-1";
          arrayOfDataSaverM[319].rumble = "-1";
          arrayOfDataSaverM[319].whichmoto = "-1";
          arrayOfDataSaverM[319].ms = "-1";
        }
        if (paramArrayOfByte._id == 106)
        {
          arrayOfDataSaverM[320].property = 5;
          arrayOfDataSaverM[320].name = paramArrayOfByte.name;
          arrayOfDataSaverM[320].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1yh()) * paramFloat1 / f1));
          arrayOfDataSaverM[320].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1xh())) * paramFloat2 / f3));
          arrayOfDataSaverM[320].joystick = "-1";
          arrayOfDataSaverM[320].rumble = "-1";
          arrayOfDataSaverM[320].whichmoto = "-1";
          arrayOfDataSaverM[320].ms = "-1";
        }
        if (paramArrayOfByte._id == 96)
        {
          arrayOfDataSaverM[303].property = 5;
          arrayOfDataSaverM[303].name = paramArrayOfByte.name;
          arrayOfDataSaverM[303].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1yh()) * paramFloat1 / f1));
          arrayOfDataSaverM[303].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1xh())) * paramFloat2 / f3));
          arrayOfDataSaverM[303].joystick = "-1";
          arrayOfDataSaverM[303].rumble = "-1";
          arrayOfDataSaverM[303].whichmoto = "-1";
          arrayOfDataSaverM[303].ms = "-1";
        }
        if (paramArrayOfByte._id == 97)
        {
          arrayOfDataSaverM[304].property = 5;
          arrayOfDataSaverM[304].name = paramArrayOfByte.name;
          arrayOfDataSaverM[304].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1yh()) * paramFloat1 / f1));
          arrayOfDataSaverM[304].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1xh())) * paramFloat2 / f3));
          arrayOfDataSaverM[304].joystick = "-1";
          arrayOfDataSaverM[304].rumble = "-1";
          arrayOfDataSaverM[304].whichmoto = "-1";
          arrayOfDataSaverM[304].ms = "-1";
        }
        if (paramArrayOfByte._id == 42)
        {
          arrayOfDataSaverM[305].property = 5;
          arrayOfDataSaverM[305].name = paramArrayOfByte.name;
          arrayOfDataSaverM[305].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1yh()) * paramFloat1 / f1));
          arrayOfDataSaverM[305].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1xh())) * paramFloat2 / f3));
          arrayOfDataSaverM[305].joystick = "-1";
          arrayOfDataSaverM[305].rumble = "-1";
          arrayOfDataSaverM[305].whichmoto = "-1";
          arrayOfDataSaverM[305].ms = "-1";
        }
      }
      if (((NormalKey)((ArrayList)localObject).get(i)).getType() == 7)
      {
        if (paramArrayOfByte._id < 30)
        {
          arrayOfDataSaverM[(paramArrayOfByte._id + 321)].property = 6;
          arrayOfDataSaverM[(paramArrayOfByte._id + 321)].name = paramArrayOfByte.name;
          arrayOfDataSaverM[(paramArrayOfByte._id + 321)].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1yh()) * paramFloat1 / f1));
          arrayOfDataSaverM[(paramArrayOfByte._id + 321)].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1xh())) * paramFloat2 / f3));
          arrayOfDataSaverM[(paramArrayOfByte._id + 321)].joystick = "-1";
          arrayOfDataSaverM[(paramArrayOfByte._id + 321)].rumble = "-1";
          arrayOfDataSaverM[(paramArrayOfByte._id + 321)].whichmoto = "-1";
          arrayOfDataSaverM[(paramArrayOfByte._id + 321)].ms = "-1";
        }
        if (paramArrayOfByte._id == 104)
        {
          arrayOfDataSaverM[349].property = 6;
          arrayOfDataSaverM[349].name = paramArrayOfByte.name;
          arrayOfDataSaverM[349].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1yh()) * paramFloat1 / f1));
          arrayOfDataSaverM[349].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1xh())) * paramFloat2 / f3));
          arrayOfDataSaverM[349].joystick = "-1";
          arrayOfDataSaverM[349].rumble = "-1";
          arrayOfDataSaverM[349].whichmoto = "-1";
          arrayOfDataSaverM[349].ms = "-1";
        }
        if (paramArrayOfByte._id == 106)
        {
          arrayOfDataSaverM[350].property = 6;
          arrayOfDataSaverM[350].name = paramArrayOfByte.name;
          arrayOfDataSaverM[350].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1yh()) * paramFloat1 / f1));
          arrayOfDataSaverM[350].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1xh())) * paramFloat2 / f3));
          arrayOfDataSaverM[350].joystick = "-1";
          arrayOfDataSaverM[350].rumble = "-1";
          arrayOfDataSaverM[350].whichmoto = "-1";
          arrayOfDataSaverM[350].ms = "-1";
        }
        if (paramArrayOfByte._id == 96)
        {
          arrayOfDataSaverM[333].property = 6;
          arrayOfDataSaverM[333].name = paramArrayOfByte.name;
          arrayOfDataSaverM[333].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1yh()) * paramFloat1 / f1));
          arrayOfDataSaverM[333].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1xh())) * paramFloat2 / f3));
          arrayOfDataSaverM[333].joystick = "-1";
          arrayOfDataSaverM[333].rumble = "-1";
          arrayOfDataSaverM[333].whichmoto = "-1";
          arrayOfDataSaverM[333].ms = "-1";
        }
        if (paramArrayOfByte._id == 97)
        {
          arrayOfDataSaverM[334].property = 6;
          arrayOfDataSaverM[334].name = paramArrayOfByte.name;
          arrayOfDataSaverM[334].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1yh()) * paramFloat1 / f1));
          arrayOfDataSaverM[334].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1xh())) * paramFloat2 / f3));
          arrayOfDataSaverM[334].joystick = "-1";
          arrayOfDataSaverM[334].rumble = "-1";
          arrayOfDataSaverM[334].whichmoto = "-1";
          arrayOfDataSaverM[334].ms = "-1";
        }
        if (paramArrayOfByte._id == 42)
        {
          arrayOfDataSaverM[335].property = 6;
          arrayOfDataSaverM[335].name = paramArrayOfByte.name;
          arrayOfDataSaverM[335].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1yh()) * paramFloat1 / f1));
          arrayOfDataSaverM[335].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1xh())) * paramFloat2 / f3));
          arrayOfDataSaverM[335].joystick = "-1";
          arrayOfDataSaverM[335].rumble = "-1";
          arrayOfDataSaverM[335].whichmoto = "-1";
          arrayOfDataSaverM[335].ms = "-1";
        }
      }
      if (((NormalKey)((ArrayList)localObject).get(i)).getType() == 4)
      {
        arrayOfDataSaverM[98].property = 2;
        arrayOfDataSaverM[98].name = paramArrayOfByte.name;
        arrayOfDataSaverM[98].x = ((int)(ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1yl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1yh()) * paramFloat1 / f1));
        arrayOfDataSaverM[98].y = ((int)((j - ChangeDataUtil.getIntegerByBit(((NormalKey)((ArrayList)localObject).get(i)).getP1xl(), ((NormalKey)((ArrayList)localObject).get(i)).getP1xh())) * paramFloat2 / f3));
        arrayOfDataSaverM[98].joystick = "-1";
        arrayOfDataSaverM[98].rumble = "-1";
        arrayOfDataSaverM[98].whichmoto = "-1";
        arrayOfDataSaverM[98].ms = "-1";
      }
      i += 1;
    }
    return arrayOfDataSaverM;
  }
}
