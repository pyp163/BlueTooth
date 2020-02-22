package com.qx.qgbox.utils;

import com.qx.qgbox.activity.MyApplication;
import com.qx.qgbox.entitys.ComKey;
import com.qx.qgbox.entitys.GPKey;
import com.qx.qgbox.entitys.GPNormalKey;
import com.qx.qgbox.entitys.MacroKey;
import com.qx.qgbox.gamepad.DataSaver;
import com.qx.qgbox.gamepad.gpsetupdialog;
import java.util.ArrayList;

public class AnalyseDataUtil
{
  static int deviceX;
  static int deviceY;
  static DataSaver[] gpMmdatasaver = new DataSaver[966];

  public static byte[] changeDataToByteArray(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    Object localObject2 = MyApplication.getMdatasaverScenes0();
    Object localObject6 = MyApplication.getMdatasaverScenes0();
    Object localObject4 = MyApplication.getMdatasaverScenes1();
    if ((localObject6 != null) && (localObject4 != null))
    {
      Object localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("resolutionX = ");
      ((StringBuilder)localObject1).append(paramInt1);
      ((StringBuilder)localObject1).append(";  resolutionY = ");
      ((StringBuilder)localObject1).append(paramInt2);
      MyLog.i("my_tag", ((StringBuilder)localObject1).toString());
      Object localObject3 = ChangeDataUtil.intToByteArray(paramInt1);
      Object localObject5 = ChangeDataUtil.intToByteArray(paramInt2);
      int i;
      Object localObject7;
      byte[] arrayOfByte1;
      Object localObject8;
      if (paramInt3 >= 5)
      {
        paramInt4 *= 10;
        i = paramInt4 + 10;
        localObject1 = new byte[i + paramInt5 * (paramInt6 * 4 + 2)];
        localObject1[2] = 2;
        localObject1[3] = ((byte)paramInt3);
        localObject1[4] = 0;
        localObject1[5] = localObject3[1];
        localObject1[6] = localObject3[0];
        localObject1[7] = localObject5[1];
        localObject1[8] = localObject5[0];
        localObject5 = new ArrayList();
        localObject3 = new ArrayList();
        paramInt5 = 0;
        while (paramInt5 < localObject6.length)
        {
          if ((!localObject6[paramInt5].name.equals("-1")) && (localObject6[paramInt5].x != -1) && (paramInt5 > 294) && (paramInt5 < 326) && (paramInt5 != 310) && (paramInt5 != 311) && (localObject6[paramInt5].property == 7) && (CommonUtils.getGPKeyCodeByName(localObject6[paramInt5].name) != -1))
            localObject1[9] = ((byte)CommonUtils.getGPKeyCodeByName(localObject6[paramInt5].name));
          if ((!localObject2[paramInt5].name.equals("-1")) && (localObject6[paramInt5].x != -1) && (paramInt5 < 38) && (CommonUtils.getGPKeyCodeByName(localObject6[paramInt5].name) != -1) && (paramInt5 != 16) && (paramInt5 != 17) && (paramInt5 != 22) && (paramInt5 != 23) && (paramInt5 != 24) && (paramInt5 != 25) && (paramInt5 != 26))
          {
            localObject7 = ChangeDataUtil.intToByteArray(paramInt1 - localObject6[paramInt5].y);
            arrayOfByte1 = ChangeDataUtil.intToByteArray(localObject6[paramInt5].x);
            localObject8 = new GPNormalKey();
            ((GPNormalKey)localObject8).setType((byte)1);
            ((GPNormalKey)localObject8).setCode((byte)CommonUtils.getGPKeyCodeByName(localObject6[paramInt5].name));
            ((GPNormalKey)localObject8).setP1xl(localObject7[1]);
            ((GPNormalKey)localObject8).setP1xh(localObject7[0]);
            ((GPNormalKey)localObject8).setP1yl(arrayOfByte1[1]);
            ((GPNormalKey)localObject8).setP1yh(arrayOfByte1[0]);
            ((GPNormalKey)localObject8).setP2xl((byte)0);
            ((GPNormalKey)localObject8).setP2xh((byte)0);
            ((GPNormalKey)localObject8).setP2yl((byte)0);
            ((GPNormalKey)localObject8).setP2yh((byte)0);
            ((ArrayList)localObject5).add(localObject8);
          }
          if ((localObject6[paramInt5].property == 1) && (localObject6[paramInt5].x != -1) && (paramInt5 > 37) && (paramInt5 < 70) && (CommonUtils.getGPKeyCodeByName(localObject6[paramInt5].name) != -1) && (paramInt5 != 54) && (paramInt5 != 55))
          {
            arrayOfByte1 = ChangeDataUtil.intToByteArray(paramInt1 - localObject6[paramInt5].y);
            localObject8 = ChangeDataUtil.intToByteArray(localObject6[paramInt5].x);
            localObject7 = new GPNormalKey();
            ((GPNormalKey)localObject7).setType((byte)6);
            ((GPNormalKey)localObject7).setCode((byte)CommonUtils.getGPKeyCodeByName(localObject6[paramInt5].name));
            ((GPNormalKey)localObject7).setP1xl(arrayOfByte1[1]);
            ((GPNormalKey)localObject7).setP1xh(arrayOfByte1[0]);
            ((GPNormalKey)localObject7).setP1yl(localObject8[1]);
            ((GPNormalKey)localObject7).setP1yh(localObject8[0]);
            if (localObject6[paramInt5].radius != 0)
            {
              arrayOfByte1 = ChangeDataUtil.intToByteArray(localObject6[paramInt5].radius);
              ((GPNormalKey)localObject7).setP2xl((byte)1);
              ((GPNormalKey)localObject7).setP2xh(arrayOfByte1[1]);
              ((GPNormalKey)localObject7).setP2yl(arrayOfByte1[0]);
              ((GPNormalKey)localObject7).setP2yh((byte)localObject6[paramInt5].reverse);
            }
            else
            {
              ((GPNormalKey)localObject7).setP2xl((byte)4);
              ((GPNormalKey)localObject7).setP2xh((byte)localObject6[paramInt5].block);
              ((GPNormalKey)localObject7).setP2yl((byte)0);
              ((GPNormalKey)localObject7).setP2yh((byte)localObject2[paramInt5].reverse);
            }
            ((ArrayList)localObject5).add(localObject7);
          }
          if ((localObject6[paramInt5].property == 2) && (localObject6[paramInt5].x != -1) && (paramInt5 > 69) && (paramInt5 < 102) && (CommonUtils.getGPKeyCodeByName(localObject6[paramInt5].name) != -1) && (paramInt5 != 86) && (paramInt5 != 87))
          {
            arrayOfByte1 = ChangeDataUtil.intToByteArray(paramInt1 - localObject6[paramInt5].y);
            localObject8 = ChangeDataUtil.intToByteArray(localObject6[paramInt5].x);
            localObject7 = new GPNormalKey();
            ((GPNormalKey)localObject7).setType((byte)6);
            ((GPNormalKey)localObject7).setCode((byte)CommonUtils.getGPKeyCodeByName(localObject6[paramInt5].name));
            ((GPNormalKey)localObject7).setP1xl(arrayOfByte1[1]);
            ((GPNormalKey)localObject7).setP1xh(arrayOfByte1[0]);
            ((GPNormalKey)localObject7).setP1yl(localObject8[1]);
            ((GPNormalKey)localObject7).setP1yh(localObject8[0]);
            if (localObject6[paramInt5].radius != 0)
            {
              arrayOfByte1 = ChangeDataUtil.intToByteArray(localObject6[paramInt5].radius);
              ((GPNormalKey)localObject7).setP2xl((byte)2);
              ((GPNormalKey)localObject7).setP2xh(arrayOfByte1[1]);
              ((GPNormalKey)localObject7).setP2yl(arrayOfByte1[0]);
              ((GPNormalKey)localObject7).setP2yh((byte)localObject6[paramInt5].reverse);
            }
            else
            {
              ((GPNormalKey)localObject7).setP2xl((byte)5);
              ((GPNormalKey)localObject7).setP2xh((byte)localObject6[paramInt5].block);
              ((GPNormalKey)localObject7).setP2yl((byte)0);
              ((GPNormalKey)localObject7).setP2yh((byte)localObject2[paramInt5].reverse);
            }
            ((ArrayList)localObject5).add(localObject7);
          }
          if ((localObject6[paramInt5].property == 3) && (localObject6[paramInt5].x != -1) && (paramInt5 > 101) && (paramInt5 < 134) && (CommonUtils.getGPKeyCodeByName(localObject6[paramInt5].name) != -1) && (paramInt5 != 118) && (paramInt5 != 119))
          {
            arrayOfByte1 = ChangeDataUtil.intToByteArray(paramInt1 - localObject6[paramInt5].y);
            localObject8 = ChangeDataUtil.intToByteArray(localObject6[paramInt5].x);
            localObject7 = new GPNormalKey();
            ((GPNormalKey)localObject7).setType((byte)6);
            ((GPNormalKey)localObject7).setCode((byte)CommonUtils.getGPKeyCodeByName(localObject6[paramInt5].name));
            ((GPNormalKey)localObject7).setP1xl(arrayOfByte1[1]);
            ((GPNormalKey)localObject7).setP1xh(arrayOfByte1[0]);
            ((GPNormalKey)localObject7).setP1yl(localObject8[1]);
            ((GPNormalKey)localObject7).setP1yh(localObject8[0]);
            if (localObject6[paramInt5].radius != 0)
            {
              arrayOfByte1 = ChangeDataUtil.intToByteArray(localObject6[paramInt5].radius);
              ((GPNormalKey)localObject7).setP2xl((byte)3);
              ((GPNormalKey)localObject7).setP2xh(arrayOfByte1[1]);
              ((GPNormalKey)localObject7).setP2yl(arrayOfByte1[0]);
              ((GPNormalKey)localObject7).setP2yh((byte)localObject6[paramInt5].reverse);
            }
            else
            {
              ((GPNormalKey)localObject7).setP2xl((byte)6);
              ((GPNormalKey)localObject7).setP2xh((byte)localObject6[paramInt5].block);
              ((GPNormalKey)localObject7).setP2yl((byte)0);
              ((GPNormalKey)localObject7).setP2yh((byte)localObject2[paramInt5].reverse);
            }
            ((ArrayList)localObject5).add(localObject7);
          }
          byte[] arrayOfByte2;
          GPNormalKey localGPNormalKey;
          if ((localObject6[paramInt5].property == 4) && (localObject6[paramInt5].x != -1) && (paramInt5 > 133) && (paramInt5 < 166) && (CommonUtils.getGPKeyCodeByName(localObject6[paramInt5].name) != -1) && (paramInt5 != 150) && (paramInt5 != 151))
          {
            localObject7 = ChangeDataUtil.intToByteArray(paramInt1 - localObject6[paramInt5].y);
            arrayOfByte1 = ChangeDataUtil.intToByteArray(localObject6[paramInt5].x);
            paramInt6 = paramInt5 + 32;
            localObject8 = ChangeDataUtil.intToByteArray(paramInt1 - localObject6[paramInt6].y);
            arrayOfByte2 = ChangeDataUtil.intToByteArray(localObject6[paramInt6].x);
            localGPNormalKey = new GPNormalKey();
            localGPNormalKey.setType((byte)2);
            localGPNormalKey.setCode((byte)CommonUtils.getGPKeyCodeByName(localObject6[paramInt5].name));
            localGPNormalKey.setP1xl(localObject7[1]);
            localGPNormalKey.setP1xh(localObject7[0]);
            localGPNormalKey.setP1yl(arrayOfByte1[1]);
            localGPNormalKey.setP1yh(arrayOfByte1[0]);
            localGPNormalKey.setP2xl(localObject8[1]);
            localGPNormalKey.setP2xh(localObject8[0]);
            localGPNormalKey.setP2yl(arrayOfByte2[1]);
            localGPNormalKey.setP2yh(arrayOfByte2[0]);
            ((ArrayList)localObject5).add(localGPNormalKey);
          }
          if ((localObject6[paramInt5].property == 5) && (localObject6[paramInt5].x != -1) && (paramInt5 > 197) && (paramInt5 < 230) && (CommonUtils.getGPKeyCodeByName(localObject6[paramInt5].name) != -1) && (paramInt5 != 214) && (paramInt5 != 215))
          {
            localObject7 = ChangeDataUtil.intToByteArray(paramInt1 - localObject6[paramInt5].y);
            arrayOfByte1 = ChangeDataUtil.intToByteArray(localObject6[paramInt5].x);
            paramInt6 = paramInt5 + 32;
            localObject8 = ChangeDataUtil.intToByteArray(paramInt1 - localObject6[paramInt6].y);
            arrayOfByte2 = ChangeDataUtil.intToByteArray(localObject6[paramInt6].x);
            localGPNormalKey = new GPNormalKey();
            localGPNormalKey.setType((byte)3);
            localGPNormalKey.setCode((byte)CommonUtils.getGPKeyCodeByName(localObject6[paramInt5].name));
            localGPNormalKey.setP1xl(localObject7[1]);
            localGPNormalKey.setP1xh(localObject7[0]);
            localGPNormalKey.setP1yl(arrayOfByte1[1]);
            localGPNormalKey.setP1yh(arrayOfByte1[0]);
            localGPNormalKey.setP2xl(localObject8[1]);
            localGPNormalKey.setP2xh(localObject8[0]);
            localGPNormalKey.setP2yl(arrayOfByte2[1]);
            localGPNormalKey.setP2yh(arrayOfByte2[0]);
            ((ArrayList)localObject5).add(localGPNormalKey);
          }
          if ((localObject6[paramInt5].property == 6) && (localObject6[paramInt5].x != -1) && (paramInt5 > 261) && (paramInt5 < 294) && (CommonUtils.getGPKeyCodeByName(localObject6[paramInt5].name) != -1) && (paramInt5 != 278) && (paramInt5 != 279))
          {
            localObject7 = ChangeDataUtil.intToByteArray(paramInt1 - localObject6[paramInt5].y);
            arrayOfByte1 = ChangeDataUtil.intToByteArray(localObject6[paramInt5].x);
            localObject8 = new GPNormalKey();
            ((GPNormalKey)localObject8).setType((byte)6);
            ((GPNormalKey)localObject8).setCode((byte)CommonUtils.getGPKeyCodeByName(localObject6[paramInt5].name));
            ((GPNormalKey)localObject8).setP1xl(localObject7[1]);
            ((GPNormalKey)localObject8).setP1xh(localObject7[0]);
            ((GPNormalKey)localObject8).setP1yl(arrayOfByte1[1]);
            ((GPNormalKey)localObject8).setP1yh(arrayOfByte1[0]);
            ((GPNormalKey)localObject8).setP2xl((byte)5);
            ((GPNormalKey)localObject8).setP2xh((byte)localObject6[paramInt5].block);
            ((GPNormalKey)localObject8).setP2yl((byte)0);
            ((GPNormalKey)localObject8).setP2yh((byte)localObject2[paramInt5].reverse);
            ((ArrayList)localObject5).add(localObject8);
          }
          if ((localObject6[paramInt5].property == 8) && (localObject6[paramInt5].x != -1) && (paramInt5 > 325) && (paramInt5 < 358) && (CommonUtils.getGPKeyCodeByName1(localObject6[paramInt5].name) != -1) && (paramInt5 != 342) && (paramInt5 != 343))
          {
            localObject7 = ChangeDataUtil.intToByteArray(paramInt1 - localObject6[paramInt5].y);
            arrayOfByte1 = ChangeDataUtil.intToByteArray(localObject6[paramInt5].x);
            localObject8 = new GPNormalKey();
            ((GPNormalKey)localObject8).setType((byte)7);
            ((GPNormalKey)localObject8).setCode((byte)CommonUtils.getGPKeyCodeByName1(localObject6[paramInt5].name));
            ((GPNormalKey)localObject8).setP1xl(localObject7[1]);
            ((GPNormalKey)localObject8).setP1xh(localObject7[0]);
            ((GPNormalKey)localObject8).setP1yl(arrayOfByte1[1]);
            ((GPNormalKey)localObject8).setP1yh(arrayOfByte1[0]);
            ((GPNormalKey)localObject8).setP2xl((byte)0);
            ((GPNormalKey)localObject8).setP2xh((byte)0);
            ((GPNormalKey)localObject8).setP2yl((byte)0);
            ((GPNormalKey)localObject8).setP2yh((byte)0);
            ((ArrayList)localObject5).add(localObject8);
          }
          if ((localObject6[paramInt5].property == 9) && (localObject6[paramInt5].x != -1) && (paramInt5 > 357) && (paramInt5 < 390) && (CommonUtils.getGPKeyCodeByName(localObject6[paramInt5].name) != -1) && (paramInt3 >= 3))
          {
            localObject7 = new GPNormalKey();
            ((GPNormalKey)localObject7).setType((byte)8);
            ((GPNormalKey)localObject7).setCode((byte)CommonUtils.getGPKeyCodeByName(localObject6[paramInt5].name));
            ((GPNormalKey)localObject7).setP1xl((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject6[paramInt5].y));
            ((GPNormalKey)localObject7).setP1xh((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[paramInt5].x));
            paramInt6 = paramInt5 + 32;
            ((GPNormalKey)localObject7).setP1yl((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject6[paramInt6].y));
            ((GPNormalKey)localObject7).setP1yh((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[paramInt6].x));
            ((GPNormalKey)localObject7).setP2xl((byte)localObject6[paramInt5].radius);
            ((GPNormalKey)localObject7).setP2xh((byte)localObject6[paramInt5].block);
            ((GPNormalKey)localObject7).setP2yl((byte)0);
            ((GPNormalKey)localObject7).setP2yh((byte)0);
            if ((((GPNormalKey)localObject7).getP1xl() == ((GPNormalKey)localObject7).getP1yl()) && (((GPNormalKey)localObject7).getP1xh() == ((GPNormalKey)localObject7).getP1yh()))
            {
              ((GPNormalKey)localObject7).setP1yl((byte)(((GPNormalKey)localObject7).getP1xl() + 1));
              ((GPNormalKey)localObject7).setP1yh((byte)(((GPNormalKey)localObject7).getP1xh() + 1));
            }
            ((ArrayList)localObject5).add(localObject7);
          }
          if ((localObject6[paramInt5].property == 10) && (localObject6[paramInt5].x != -1) && (paramInt5 > 421) && (paramInt5 < 454) && (CommonUtils.getGPKeyCodeByName(localObject6[paramInt5].name) != -1) && (paramInt3 >= 4))
          {
            localObject7 = ChangeDataUtil.intToByteArray(paramInt1 - localObject6[paramInt5].y);
            arrayOfByte1 = ChangeDataUtil.intToByteArray(localObject6[paramInt5].x);
            localObject8 = new GPNormalKey();
            ((GPNormalKey)localObject8).setType((byte)9);
            ((GPNormalKey)localObject8).setCode((byte)CommonUtils.getGPKeyCodeByName(localObject6[paramInt5].name));
            ((GPNormalKey)localObject8).setP1xl(localObject7[1]);
            ((GPNormalKey)localObject8).setP1xh(localObject7[0]);
            ((GPNormalKey)localObject8).setP1yl(arrayOfByte1[1]);
            ((GPNormalKey)localObject8).setP1yh(arrayOfByte1[0]);
            ((GPNormalKey)localObject8).setP2xl((byte)localObject6[paramInt5].radius);
            ((GPNormalKey)localObject8).setP2xh((byte)0);
            ((GPNormalKey)localObject8).setP2yl((byte)0);
            ((GPNormalKey)localObject8).setP2yh((byte)0);
            ((ArrayList)localObject5).add(localObject8);
          }
          if ((localObject6[paramInt5].property == 11) && (localObject6[paramInt5].x != -1) && (paramInt5 >= 22) && (paramInt5 <= 26) && (CommonUtils.getGPKeyCodeByName(localObject6[paramInt5].name) != -1) && (paramInt3 >= 5))
          {
            localObject7 = new GPNormalKey();
            ((GPNormalKey)localObject7).setType((byte)10);
            ((GPNormalKey)localObject7).setCode((byte)CommonUtils.getGPKeyCodeByName(localObject6[paramInt5].name));
            ((GPNormalKey)localObject7).setP1xl((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject6[paramInt5].y));
            ((GPNormalKey)localObject7).setP1xh((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[paramInt5].x));
            ((GPNormalKey)localObject7).setP1yl((byte)localObject6[paramInt5].radius);
            ((GPNormalKey)localObject7).setP1yh((byte)0);
            ((GPNormalKey)localObject7).setP2xl((byte)0);
            ((GPNormalKey)localObject7).setP2xh((byte)0);
            ((GPNormalKey)localObject7).setP2yl((byte)0);
            ((GPNormalKey)localObject7).setP2yh((byte)0);
            ((ArrayList)localObject5).add(localObject7);
          }
          if ((localObject6[paramInt5].property == 12) && (localObject6[paramInt5].x != -1) && (paramInt5 >= 22) && (paramInt5 <= 26) && (CommonUtils.getGPKeyCodeByName(localObject6[paramInt5].name) != -1) && (paramInt3 >= 5))
          {
            localObject7 = new GPNormalKey();
            arrayOfByte1 = ChangeDataUtil.intToByteArray(localObject6[paramInt5].radius);
            ((GPNormalKey)localObject7).setType((byte)11);
            ((GPNormalKey)localObject7).setCode((byte)CommonUtils.getGPKeyCodeByName(localObject6[paramInt5].name));
            ((GPNormalKey)localObject7).setP1xl((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject6[paramInt5].y));
            ((GPNormalKey)localObject7).setP1xh((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[paramInt5].x));
            ((GPNormalKey)localObject7).setP1yl(arrayOfByte1[1]);
            ((GPNormalKey)localObject7).setP1yh(arrayOfByte1[0]);
            ((GPNormalKey)localObject7).setP2xl((byte)localObject6[paramInt5].block);
            ((GPNormalKey)localObject7).setP2xh((byte)localObject6[paramInt5].reverse);
            ((GPNormalKey)localObject7).setP2yl((byte)0);
            ((GPNormalKey)localObject7).setP2yh((byte)0);
            ((ArrayList)localObject5).add(localObject7);
          }
          if ((localObject6[paramInt5].property == 14) && (localObject6[paramInt5].x != -1) && (paramInt5 > 933) && (CommonUtils.getGPKeyCodeByName(localObject6[paramInt5].name) != -1))
          {
            localObject7 = new GPNormalKey();
            ((GPNormalKey)localObject7).setType((byte)15);
            ((GPNormalKey)localObject7).setCode((byte)CommonUtils.getGPKeyCodeByName(localObject6[paramInt5].name));
            ((GPNormalKey)localObject7).setP1xl((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject6[paramInt5].y));
            ((GPNormalKey)localObject7).setP1xh((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[paramInt5].x));
            ((GPNormalKey)localObject7).setP1yl((byte)localObject6[paramInt5].radius);
            ((GPNormalKey)localObject7).setP1yh((byte)localObject6[paramInt5].block);
            if ((CommonUtils.isStringValid(localObject6[paramInt5].view_joystick)) && (!localObject6[paramInt5].view_joystick.equalsIgnoreCase("-1")))
              ((GPNormalKey)localObject7).setP2xl((byte)CommonUtils.getGPKeyCodeByName(localObject6[paramInt5].view_joystick));
            else
              ((GPNormalKey)localObject7).setP2xl((byte)0);
            ((GPNormalKey)localObject7).setP2xh((byte)localObject6[paramInt5].turb_speed);
            ((GPNormalKey)localObject7).setP2yl((byte)0);
            ((GPNormalKey)localObject7).setP2yh((byte)0);
            ((ArrayList)localObject5).add(localObject7);
          }
          if ((localObject6[paramInt5].property == 13) && (localObject6[paramInt5].x != -1) && (paramInt5 > 453) && (paramInt5 < 486) && (CommonUtils.getGPKeyCodeByName(localObject6[paramInt5].name) != -1))
          {
            localObject7 = new StringBuilder();
            ((StringBuilder)localObject7).append("----i = ");
            ((StringBuilder)localObject7).append(paramInt5);
            MyLog.e("my_tag", ((StringBuilder)localObject7).toString());
            localObject7 = new MacroKey();
            ((MacroKey)localObject7).setType((byte)14);
            ((MacroKey)localObject7).setKeyCode((byte)CommonUtils.getGPKeyCodeByName(localObject6[paramInt5].name));
            ((MacroKey)localObject7).setPoint0X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject6[paramInt5].y));
            ((MacroKey)localObject7).setPoint0Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[paramInt5].x));
            ((MacroKey)localObject7).setPoint0MacroTime((byte)localObject6[paramInt5].radius);
            ((MacroKey)localObject7).setPoint0PauseTime((byte)localObject6[paramInt5].block);
            paramInt6 = paramInt5 + 32;
            ((MacroKey)localObject7).setPoint1X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject6[paramInt6].y));
            ((MacroKey)localObject7).setPoint1Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[paramInt6].x));
            ((MacroKey)localObject7).setPoint1MacroTime((byte)localObject6[paramInt6].radius);
            ((MacroKey)localObject7).setPoint1PauseTime((byte)localObject6[paramInt6].block);
            paramInt6 = paramInt5 + 64;
            if ((localObject6[paramInt6].x != -1) && (localObject6[paramInt6].y != -1) && (localObject6[paramInt6].radius != -1) && (localObject6[paramInt6].block != -1))
            {
              ((MacroKey)localObject7).setPoint2X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject6[paramInt6].y));
              ((MacroKey)localObject7).setPoint2Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[paramInt6].x));
              ((MacroKey)localObject7).setPoint2MacroTime((byte)localObject6[paramInt6].radius);
              ((MacroKey)localObject7).setPoint2PauseTime((byte)localObject6[paramInt6].block);
            }
            else
            {
              ((MacroKey)localObject7).setPoint2X((byte)0);
              ((MacroKey)localObject7).setPoint2Y((byte)0);
              ((MacroKey)localObject7).setPoint2MacroTime((byte)0);
              ((MacroKey)localObject7).setPoint2PauseTime((byte)0);
            }
            paramInt6 = paramInt5 + 96;
            if ((localObject6[paramInt6].x != -1) && (localObject6[paramInt6].y != -1) && (localObject6[paramInt6].radius != -1) && (localObject6[paramInt6].block != -1))
            {
              ((MacroKey)localObject7).setPoint3X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject6[paramInt6].y));
              ((MacroKey)localObject7).setPoint3Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[paramInt6].x));
              ((MacroKey)localObject7).setPoint3MacroTime((byte)localObject6[paramInt6].radius);
              ((MacroKey)localObject7).setPoint3PauseTime((byte)localObject6[paramInt6].block);
            }
            else
            {
              ((MacroKey)localObject7).setPoint3X((byte)0);
              ((MacroKey)localObject7).setPoint3Y((byte)0);
              ((MacroKey)localObject7).setPoint3MacroTime((byte)0);
              ((MacroKey)localObject7).setPoint3PauseTime((byte)0);
            }
            paramInt6 = paramInt5 + 128;
            if ((localObject6[paramInt6].x != -1) && (localObject6[paramInt6].y != -1) && (localObject6[paramInt6].radius != -1) && (localObject6[paramInt6].block != -1))
            {
              ((MacroKey)localObject7).setPoint4X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject6[paramInt6].y));
              ((MacroKey)localObject7).setPoint4Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[paramInt6].x));
              ((MacroKey)localObject7).setPoint4MacroTime((byte)localObject6[paramInt6].radius);
              ((MacroKey)localObject7).setPoint4PauseTime((byte)localObject6[paramInt6].block);
            }
            else
            {
              ((MacroKey)localObject7).setPoint4X((byte)0);
              ((MacroKey)localObject7).setPoint4Y((byte)0);
              ((MacroKey)localObject7).setPoint4MacroTime((byte)0);
              ((MacroKey)localObject7).setPoint4PauseTime((byte)0);
            }
            paramInt6 = paramInt5 + 160;
            if ((localObject6[paramInt6].x != -1) && (localObject6[paramInt6].y != -1) && (localObject6[paramInt6].radius != -1) && (localObject6[paramInt6].block != -1))
            {
              ((MacroKey)localObject7).setPoint5X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject6[paramInt6].y));
              ((MacroKey)localObject7).setPoint5Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[paramInt6].x));
              ((MacroKey)localObject7).setPoint5MacroTime((byte)localObject6[paramInt6].radius);
              ((MacroKey)localObject7).setPoint5PauseTime((byte)localObject6[paramInt6].block);
            }
            else
            {
              ((MacroKey)localObject7).setPoint5X((byte)0);
              ((MacroKey)localObject7).setPoint5Y((byte)0);
              ((MacroKey)localObject7).setPoint5MacroTime((byte)0);
              ((MacroKey)localObject7).setPoint5PauseTime((byte)0);
            }
            paramInt6 = paramInt5 + 192;
            if ((localObject6[paramInt6].x != -1) && (localObject6[paramInt6].y != -1) && (localObject6[paramInt6].radius != -1) && (localObject6[paramInt6].block != -1))
            {
              ((MacroKey)localObject7).setPoint6X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject6[paramInt6].y));
              ((MacroKey)localObject7).setPoint6Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[paramInt6].x));
              ((MacroKey)localObject7).setPoint6MacroTime((byte)localObject6[paramInt6].radius);
              ((MacroKey)localObject7).setPoint6PauseTime((byte)localObject6[paramInt6].block);
            }
            else
            {
              ((MacroKey)localObject7).setPoint6X((byte)0);
              ((MacroKey)localObject7).setPoint6Y((byte)0);
              ((MacroKey)localObject7).setPoint6MacroTime((byte)0);
              ((MacroKey)localObject7).setPoint6PauseTime((byte)0);
            }
            paramInt6 = paramInt5 + 224;
            if ((localObject6[paramInt6].x != -1) && (localObject6[paramInt6].y != -1) && (localObject6[paramInt6].radius != -1) && (localObject6[paramInt6].block != -1))
            {
              ((MacroKey)localObject7).setPoint7X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject6[paramInt6].y));
              ((MacroKey)localObject7).setPoint7Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[paramInt6].x));
              ((MacroKey)localObject7).setPoint7MacroTime((byte)localObject6[paramInt6].radius);
              ((MacroKey)localObject7).setPoint7PauseTime((byte)localObject6[paramInt6].block);
            }
            else
            {
              ((MacroKey)localObject7).setPoint7X((byte)0);
              ((MacroKey)localObject7).setPoint7Y((byte)0);
              ((MacroKey)localObject7).setPoint7MacroTime((byte)0);
              ((MacroKey)localObject7).setPoint7PauseTime((byte)0);
            }
            paramInt6 = paramInt5 + 256;
            if ((localObject6[paramInt6].x != -1) && (localObject6[paramInt6].y != -1) && (localObject6[paramInt6].radius != -1) && (localObject6[paramInt6].block != -1))
            {
              ((MacroKey)localObject7).setPoint8X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject6[paramInt6].y));
              ((MacroKey)localObject7).setPoint8Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[paramInt6].x));
              ((MacroKey)localObject7).setPoint8MacroTime((byte)localObject6[paramInt6].radius);
              ((MacroKey)localObject7).setPoint8PauseTime((byte)localObject6[paramInt6].block);
            }
            else
            {
              ((MacroKey)localObject7).setPoint8X((byte)0);
              ((MacroKey)localObject7).setPoint8Y((byte)0);
              ((MacroKey)localObject7).setPoint8MacroTime((byte)0);
              ((MacroKey)localObject7).setPoint8PauseTime((byte)0);
            }
            paramInt6 = paramInt5 + 288;
            if ((localObject6[paramInt6].x != -1) && (localObject6[paramInt6].y != -1) && (localObject6[paramInt6].radius != -1) && (localObject6[paramInt6].block != -1))
            {
              ((MacroKey)localObject7).setPoint9X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject6[paramInt6].y));
              ((MacroKey)localObject7).setPoint9Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[paramInt6].x));
              ((MacroKey)localObject7).setPoint9MacroTime((byte)localObject6[paramInt6].radius);
              ((MacroKey)localObject7).setPoint9PauseTime((byte)localObject6[paramInt6].block);
            }
            else
            {
              ((MacroKey)localObject7).setPoint9X((byte)0);
              ((MacroKey)localObject7).setPoint9Y((byte)0);
              ((MacroKey)localObject7).setPoint9MacroTime((byte)0);
              ((MacroKey)localObject7).setPoint9PauseTime((byte)0);
            }
            paramInt6 = paramInt5 + 320;
            if ((localObject6[paramInt6].x != -1) && (localObject6[paramInt6].y != -1) && (localObject6[paramInt6].radius != -1) && (localObject6[paramInt6].block != -1))
            {
              ((MacroKey)localObject7).setPoint10X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject6[paramInt6].y));
              ((MacroKey)localObject7).setPoint10Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[paramInt6].x));
              ((MacroKey)localObject7).setPoint10MacroTime((byte)localObject6[paramInt6].radius);
              ((MacroKey)localObject7).setPoint10PauseTime((byte)localObject6[paramInt6].block);
            }
            else
            {
              ((MacroKey)localObject7).setPoint10X((byte)0);
              ((MacroKey)localObject7).setPoint10Y((byte)0);
              ((MacroKey)localObject7).setPoint10MacroTime((byte)0);
              ((MacroKey)localObject7).setPoint10PauseTime((byte)0);
            }
            paramInt6 = paramInt5 + 352;
            if ((localObject6[paramInt6].x != -1) && (localObject6[paramInt6].y != -1) && (localObject6[paramInt6].radius != -1) && (localObject6[paramInt6].block != -1))
            {
              ((MacroKey)localObject7).setPoint11X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject6[paramInt6].y));
              ((MacroKey)localObject7).setPoint11Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[paramInt6].x));
              ((MacroKey)localObject7).setPoint11MacroTime((byte)localObject6[paramInt6].radius);
              ((MacroKey)localObject7).setPoint11PauseTime((byte)localObject6[paramInt6].block);
            }
            else
            {
              ((MacroKey)localObject7).setPoint11X((byte)0);
              ((MacroKey)localObject7).setPoint11Y((byte)0);
              ((MacroKey)localObject7).setPoint11MacroTime((byte)0);
              ((MacroKey)localObject7).setPoint11PauseTime((byte)0);
            }
            paramInt6 = paramInt5 + 384;
            if ((localObject6[paramInt6].x != -1) && (localObject6[paramInt6].y != -1) && (localObject6[paramInt6].radius != -1) && (localObject6[paramInt6].block != -1))
            {
              ((MacroKey)localObject7).setPoint12X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject6[paramInt6].y));
              ((MacroKey)localObject7).setPoint12Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[paramInt6].x));
              ((MacroKey)localObject7).setPoint12MacroTime((byte)localObject6[paramInt6].radius);
              ((MacroKey)localObject7).setPoint12PauseTime((byte)localObject6[paramInt6].block);
            }
            else
            {
              ((MacroKey)localObject7).setPoint12X((byte)0);
              ((MacroKey)localObject7).setPoint12Y((byte)0);
              ((MacroKey)localObject7).setPoint12MacroTime((byte)0);
              ((MacroKey)localObject7).setPoint12PauseTime((byte)0);
            }
            paramInt6 = paramInt5 + 416;
            if ((localObject6[paramInt6].x != -1) && (localObject6[paramInt6].y != -1) && (localObject6[paramInt6].radius != -1) && (localObject6[paramInt6].block != -1))
            {
              ((MacroKey)localObject7).setPoint13X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject6[paramInt6].y));
              ((MacroKey)localObject7).setPoint13Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[paramInt6].x));
              ((MacroKey)localObject7).setPoint13MacroTime((byte)localObject6[paramInt6].radius);
              ((MacroKey)localObject7).setPoint13PauseTime((byte)localObject6[paramInt6].block);
            }
            else
            {
              ((MacroKey)localObject7).setPoint13X((byte)0);
              ((MacroKey)localObject7).setPoint13Y((byte)0);
              ((MacroKey)localObject7).setPoint13MacroTime((byte)0);
              ((MacroKey)localObject7).setPoint13PauseTime((byte)0);
            }
            paramInt6 = paramInt5 + 448;
            if ((localObject6[paramInt6].x != -1) && (localObject6[paramInt6].y != -1) && (localObject6[paramInt6].radius != -1) && (localObject6[paramInt6].block != -1))
            {
              ((MacroKey)localObject7).setPoint14X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject6[paramInt6].y));
              ((MacroKey)localObject7).setPoint14Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[paramInt6].x));
              ((MacroKey)localObject7).setPoint14MacroTime((byte)localObject6[paramInt6].radius);
              ((MacroKey)localObject7).setPoint14PauseTime((byte)localObject6[paramInt6].block);
            }
            else
            {
              ((MacroKey)localObject7).setPoint14X((byte)0);
              ((MacroKey)localObject7).setPoint14Y((byte)0);
              ((MacroKey)localObject7).setPoint14MacroTime((byte)0);
              ((MacroKey)localObject7).setPoint14PauseTime((byte)0);
            }
            ((ArrayList)localObject3).add(localObject7);
          }
          paramInt5 += 1;
        }
        paramInt5 = 0;
        while (paramInt5 < localObject4.length)
        {
          if ((!localObject4[paramInt5].name.equals("-1")) && (localObject4[paramInt5].x != -1) && (paramInt5 > 294) && (paramInt5 < 326) && (paramInt5 != 310) && (paramInt5 != 311) && (localObject4[paramInt5].property == 7) && (CommonUtils.getGPKeyCodeByName(localObject4[paramInt5].name) != -1))
            localObject1[9] = ((byte)CommonUtils.getGPKeyCodeByName(localObject4[paramInt5].name));
          if (!localObject4[paramInt5].name.equals("-1"))
          {
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append("------ property = ");
            ((StringBuilder)localObject2).append(localObject4[paramInt5].property);
            ((StringBuilder)localObject2).append(";  name = ");
            ((StringBuilder)localObject2).append(localObject4[paramInt5].name);
            ((StringBuilder)localObject2).append("  i = ");
            ((StringBuilder)localObject2).append(paramInt5);
            ((StringBuilder)localObject2).append(";   radius = ");
            ((StringBuilder)localObject2).append(localObject4[paramInt5].radius);
            ((StringBuilder)localObject2).append(";   block = ");
            ((StringBuilder)localObject2).append(localObject4[paramInt5].block);
            MyLog.i("my_tag", ((StringBuilder)localObject2).toString());
          }
          if ((!localObject4[paramInt5].name.equals("-1")) && (localObject4[paramInt5].x != -1) && (paramInt5 < 38) && (CommonUtils.getGPKeyCodeByNameScenes1(localObject4[paramInt5].name) != -1) && (paramInt5 != 16) && (paramInt5 != 17) && (paramInt5 != 22) && (paramInt5 != 23) && (paramInt5 != 24) && (paramInt5 != 25) && (paramInt5 != 26))
          {
            localObject2 = ChangeDataUtil.intToByteArray(paramInt1 - localObject4[paramInt5].y);
            localObject6 = ChangeDataUtil.intToByteArray(localObject4[paramInt5].x);
            localObject7 = new GPNormalKey();
            ((GPNormalKey)localObject7).setType((byte)1);
            ((GPNormalKey)localObject7).setCode((byte)CommonUtils.getGPKeyCodeByNameScenes1(localObject4[paramInt5].name));
            ((GPNormalKey)localObject7).setP1xl(localObject2[1]);
            ((GPNormalKey)localObject7).setP1xh(localObject2[0]);
            ((GPNormalKey)localObject7).setP1yl(localObject6[1]);
            ((GPNormalKey)localObject7).setP1yh(localObject6[0]);
            ((GPNormalKey)localObject7).setP2xl((byte)0);
            ((GPNormalKey)localObject7).setP2xh((byte)0);
            ((GPNormalKey)localObject7).setP2yl((byte)0);
            ((GPNormalKey)localObject7).setP2yh((byte)0);
            ((ArrayList)localObject5).add(localObject7);
          }
          if ((localObject4[paramInt5].property == 1) && (localObject4[paramInt5].x != -1) && (paramInt5 > 37) && (paramInt5 < 70) && (CommonUtils.getGPKeyCodeByNameScenes1(localObject4[paramInt5].name) != -1) && (paramInt5 != 54) && (paramInt5 != 55))
          {
            localObject6 = ChangeDataUtil.intToByteArray(paramInt1 - localObject4[paramInt5].y);
            localObject7 = ChangeDataUtil.intToByteArray(localObject4[paramInt5].x);
            localObject2 = new GPNormalKey();
            ((GPNormalKey)localObject2).setType((byte)6);
            ((GPNormalKey)localObject2).setCode((byte)CommonUtils.getGPKeyCodeByNameScenes1(localObject4[paramInt5].name));
            ((GPNormalKey)localObject2).setP1xl(localObject6[1]);
            ((GPNormalKey)localObject2).setP1xh(localObject6[0]);
            ((GPNormalKey)localObject2).setP1yl(localObject7[1]);
            ((GPNormalKey)localObject2).setP1yh(localObject7[0]);
            if (localObject4[paramInt5].radius != 0)
            {
              localObject6 = ChangeDataUtil.intToByteArray(localObject4[paramInt5].radius);
              ((GPNormalKey)localObject2).setP2xl((byte)1);
              ((GPNormalKey)localObject2).setP2xh(localObject6[1]);
              ((GPNormalKey)localObject2).setP2yl(localObject6[0]);
              ((GPNormalKey)localObject2).setP2yh((byte)localObject4[paramInt5].reverse);
            }
            else
            {
              ((GPNormalKey)localObject2).setP2xl((byte)4);
              ((GPNormalKey)localObject2).setP2xh((byte)localObject4[paramInt5].block);
              ((GPNormalKey)localObject2).setP2yl((byte)0);
              ((GPNormalKey)localObject2).setP2yh((byte)0);
            }
            ((ArrayList)localObject5).add(localObject2);
          }
          if ((localObject4[paramInt5].property == 2) && (localObject4[paramInt5].x != -1) && (paramInt5 > 69) && (paramInt5 < 102) && (CommonUtils.getGPKeyCodeByNameScenes1(localObject4[paramInt5].name) != -1) && (paramInt5 != 86) && (paramInt5 != 87))
          {
            localObject6 = ChangeDataUtil.intToByteArray(paramInt1 - localObject4[paramInt5].y);
            localObject7 = ChangeDataUtil.intToByteArray(localObject4[paramInt5].x);
            localObject2 = new GPNormalKey();
            ((GPNormalKey)localObject2).setType((byte)6);
            ((GPNormalKey)localObject2).setCode((byte)CommonUtils.getGPKeyCodeByNameScenes1(localObject4[paramInt5].name));
            ((GPNormalKey)localObject2).setP1xl(localObject6[1]);
            ((GPNormalKey)localObject2).setP1xh(localObject6[0]);
            ((GPNormalKey)localObject2).setP1yl(localObject7[1]);
            ((GPNormalKey)localObject2).setP1yh(localObject7[0]);
            if (localObject4[paramInt5].radius != 0)
            {
              localObject6 = ChangeDataUtil.intToByteArray(localObject4[paramInt5].radius);
              ((GPNormalKey)localObject2).setP2xl((byte)2);
              ((GPNormalKey)localObject2).setP2xh(localObject6[1]);
              ((GPNormalKey)localObject2).setP2yl(localObject6[0]);
              ((GPNormalKey)localObject2).setP2yh((byte)localObject4[paramInt5].reverse);
            }
            else
            {
              ((GPNormalKey)localObject2).setP2xl((byte)5);
              ((GPNormalKey)localObject2).setP2xh((byte)localObject4[paramInt5].block);
              ((GPNormalKey)localObject2).setP2yl((byte)0);
              ((GPNormalKey)localObject2).setP2yh((byte)0);
            }
            ((ArrayList)localObject5).add(localObject2);
          }
          if ((localObject4[paramInt5].property == 3) && (localObject4[paramInt5].x != -1) && (paramInt5 > 101) && (paramInt5 < 134) && (CommonUtils.getGPKeyCodeByNameScenes1(localObject4[paramInt5].name) != -1) && (paramInt5 != 118) && (paramInt5 != 119))
          {
            localObject6 = ChangeDataUtil.intToByteArray(paramInt1 - localObject4[paramInt5].y);
            localObject7 = ChangeDataUtil.intToByteArray(localObject4[paramInt5].x);
            localObject2 = new GPNormalKey();
            ((GPNormalKey)localObject2).setType((byte)6);
            ((GPNormalKey)localObject2).setCode((byte)CommonUtils.getGPKeyCodeByNameScenes1(localObject4[paramInt5].name));
            ((GPNormalKey)localObject2).setP1xl(localObject6[1]);
            ((GPNormalKey)localObject2).setP1xh(localObject6[0]);
            ((GPNormalKey)localObject2).setP1yl(localObject7[1]);
            ((GPNormalKey)localObject2).setP1yh(localObject7[0]);
            if (localObject4[paramInt5].radius != 0)
            {
              localObject6 = ChangeDataUtil.intToByteArray(localObject4[paramInt5].radius);
              ((GPNormalKey)localObject2).setP2xl((byte)3);
              ((GPNormalKey)localObject2).setP2xh(localObject6[1]);
              ((GPNormalKey)localObject2).setP2yl(localObject6[0]);
              ((GPNormalKey)localObject2).setP2yh((byte)localObject4[paramInt5].reverse);
            }
            else
            {
              ((GPNormalKey)localObject2).setP2xl((byte)6);
              ((GPNormalKey)localObject2).setP2xh((byte)localObject4[paramInt5].block);
              ((GPNormalKey)localObject2).setP2yl((byte)0);
              ((GPNormalKey)localObject2).setP2yh((byte)0);
            }
            ((ArrayList)localObject5).add(localObject2);
          }
          if ((localObject4[paramInt5].property == 4) && (localObject4[paramInt5].x != -1) && (paramInt5 > 133) && (paramInt5 < 166) && (CommonUtils.getGPKeyCodeByNameScenes1(localObject4[paramInt5].name) != -1) && (paramInt5 != 150) && (paramInt5 != 151))
          {
            localObject2 = ChangeDataUtil.intToByteArray(paramInt1 - localObject4[paramInt5].y);
            localObject6 = ChangeDataUtil.intToByteArray(localObject4[paramInt5].x);
            paramInt6 = paramInt5 + 32;
            localObject7 = ChangeDataUtil.intToByteArray(paramInt1 - localObject4[paramInt6].y);
            arrayOfByte1 = ChangeDataUtil.intToByteArray(localObject4[paramInt6].x);
            localObject8 = new GPNormalKey();
            ((GPNormalKey)localObject8).setType((byte)2);
            ((GPNormalKey)localObject8).setCode((byte)CommonUtils.getGPKeyCodeByNameScenes1(localObject4[paramInt5].name));
            ((GPNormalKey)localObject8).setP1xl(localObject2[1]);
            ((GPNormalKey)localObject8).setP1xh(localObject2[0]);
            ((GPNormalKey)localObject8).setP1yl(localObject6[1]);
            ((GPNormalKey)localObject8).setP1yh(localObject6[0]);
            ((GPNormalKey)localObject8).setP2xl(localObject7[1]);
            ((GPNormalKey)localObject8).setP2xh(localObject7[0]);
            ((GPNormalKey)localObject8).setP2yl(arrayOfByte1[1]);
            ((GPNormalKey)localObject8).setP2yh(arrayOfByte1[0]);
            ((ArrayList)localObject5).add(localObject8);
          }
          if ((localObject4[paramInt5].property == 5) && (localObject4[paramInt5].x != -1) && (paramInt5 > 197) && (paramInt5 < 230) && (CommonUtils.getGPKeyCodeByNameScenes1(localObject4[paramInt5].name) != -1) && (paramInt5 != 214) && (paramInt5 != 215))
          {
            localObject2 = ChangeDataUtil.intToByteArray(paramInt1 - localObject4[paramInt5].y);
            localObject6 = ChangeDataUtil.intToByteArray(localObject4[paramInt5].x);
            paramInt6 = paramInt5 + 32;
            localObject7 = ChangeDataUtil.intToByteArray(paramInt1 - localObject4[paramInt6].y);
            arrayOfByte1 = ChangeDataUtil.intToByteArray(localObject4[paramInt6].x);
            localObject8 = new GPNormalKey();
            ((GPNormalKey)localObject8).setType((byte)3);
            ((GPNormalKey)localObject8).setCode((byte)CommonUtils.getGPKeyCodeByNameScenes1(localObject4[paramInt5].name));
            ((GPNormalKey)localObject8).setP1xl(localObject2[1]);
            ((GPNormalKey)localObject8).setP1xh(localObject2[0]);
            ((GPNormalKey)localObject8).setP1yl(localObject6[1]);
            ((GPNormalKey)localObject8).setP1yh(localObject6[0]);
            ((GPNormalKey)localObject8).setP2xl(localObject7[1]);
            ((GPNormalKey)localObject8).setP2xh(localObject7[0]);
            ((GPNormalKey)localObject8).setP2yl(arrayOfByte1[1]);
            ((GPNormalKey)localObject8).setP2yh(arrayOfByte1[0]);
            ((ArrayList)localObject5).add(localObject8);
          }
          if ((localObject4[paramInt5].property == 6) && (localObject4[paramInt5].x != -1) && (paramInt5 > 261) && (paramInt5 < 294) && (CommonUtils.getGPKeyCodeByNameScenes1(localObject4[paramInt5].name) != -1) && (paramInt5 != 278) && (paramInt5 != 279))
          {
            localObject2 = ChangeDataUtil.intToByteArray(paramInt1 - localObject4[paramInt5].y);
            localObject6 = ChangeDataUtil.intToByteArray(localObject4[paramInt5].x);
            localObject7 = new GPNormalKey();
            ((GPNormalKey)localObject7).setType((byte)6);
            ((GPNormalKey)localObject7).setCode((byte)CommonUtils.getGPKeyCodeByNameScenes1(localObject4[paramInt5].name));
            ((GPNormalKey)localObject7).setP1xl(localObject2[1]);
            ((GPNormalKey)localObject7).setP1xh(localObject2[0]);
            ((GPNormalKey)localObject7).setP1yl(localObject6[1]);
            ((GPNormalKey)localObject7).setP1yh(localObject6[0]);
            ((GPNormalKey)localObject7).setP2xl((byte)5);
            ((GPNormalKey)localObject7).setP2xh((byte)localObject4[paramInt5].block);
            ((GPNormalKey)localObject7).setP2yl((byte)0);
            ((GPNormalKey)localObject7).setP2yh((byte)0);
            ((ArrayList)localObject5).add(localObject7);
          }
          if ((localObject4[paramInt5].property == 8) && (localObject4[paramInt5].x != -1) && (paramInt5 > 325) && (paramInt5 < 358) && (CommonUtils.getGPKeyCodeByName1Scenes1(localObject4[paramInt5].name) != -1) && (paramInt5 != 342) && (paramInt5 != 343))
          {
            localObject2 = ChangeDataUtil.intToByteArray(paramInt1 - localObject4[paramInt5].y);
            localObject6 = ChangeDataUtil.intToByteArray(localObject4[paramInt5].x);
            localObject7 = new GPNormalKey();
            ((GPNormalKey)localObject7).setType((byte)7);
            ((GPNormalKey)localObject7).setCode((byte)CommonUtils.getGPKeyCodeByName1Scenes1(localObject4[paramInt5].name));
            ((GPNormalKey)localObject7).setP1xl(localObject2[1]);
            ((GPNormalKey)localObject7).setP1xh(localObject2[0]);
            ((GPNormalKey)localObject7).setP1yl(localObject6[1]);
            ((GPNormalKey)localObject7).setP1yh(localObject6[0]);
            ((GPNormalKey)localObject7).setP2xl((byte)0);
            ((GPNormalKey)localObject7).setP2xh((byte)0);
            ((GPNormalKey)localObject7).setP2yl((byte)0);
            ((GPNormalKey)localObject7).setP2yh((byte)0);
            ((ArrayList)localObject5).add(localObject7);
          }
          if ((localObject4[paramInt5].property == 9) && (localObject4[paramInt5].x != -1) && (paramInt5 > 357) && (paramInt5 < 390) && (CommonUtils.getGPKeyCodeByNameScenes1(localObject4[paramInt5].name) != -1) && (paramInt3 >= 3))
          {
            localObject2 = new GPNormalKey();
            ((GPNormalKey)localObject2).setType((byte)8);
            ((GPNormalKey)localObject2).setCode((byte)CommonUtils.getGPKeyCodeByNameScenes1(localObject4[paramInt5].name));
            ((GPNormalKey)localObject2).setP1xl((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject4[paramInt5].y));
            ((GPNormalKey)localObject2).setP1xh((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject4[paramInt5].x));
            paramInt6 = paramInt5 + 32;
            ((GPNormalKey)localObject2).setP1yl((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject4[paramInt6].y));
            ((GPNormalKey)localObject2).setP1yh((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject4[paramInt6].x));
            ((GPNormalKey)localObject2).setP2xl((byte)localObject4[paramInt5].radius);
            ((GPNormalKey)localObject2).setP2xh((byte)localObject4[paramInt5].block);
            ((GPNormalKey)localObject2).setP2yl((byte)0);
            ((GPNormalKey)localObject2).setP2yh((byte)0);
            if ((((GPNormalKey)localObject2).getP1xl() == ((GPNormalKey)localObject2).getP1yl()) && (((GPNormalKey)localObject2).getP1xh() == ((GPNormalKey)localObject2).getP1yh()))
            {
              ((GPNormalKey)localObject2).setP1yl((byte)(((GPNormalKey)localObject2).getP1xl() + 1));
              ((GPNormalKey)localObject2).setP1yh((byte)(((GPNormalKey)localObject2).getP1xh() + 1));
            }
            ((ArrayList)localObject5).add(localObject2);
          }
          if ((localObject4[paramInt5].property == 10) && (localObject4[paramInt5].x != -1) && (paramInt5 > 421) && (paramInt5 < 454) && (CommonUtils.getGPKeyCodeByNameScenes1(localObject4[paramInt5].name) != -1) && (paramInt3 >= 4))
          {
            localObject2 = ChangeDataUtil.intToByteArray(paramInt1 - localObject4[paramInt5].y);
            localObject6 = ChangeDataUtil.intToByteArray(localObject4[paramInt5].x);
            localObject7 = new GPNormalKey();
            ((GPNormalKey)localObject7).setType((byte)9);
            ((GPNormalKey)localObject7).setCode((byte)CommonUtils.getGPKeyCodeByNameScenes1(localObject4[paramInt5].name));
            ((GPNormalKey)localObject7).setP1xl(localObject2[1]);
            ((GPNormalKey)localObject7).setP1xh(localObject2[0]);
            ((GPNormalKey)localObject7).setP1yl(localObject6[1]);
            ((GPNormalKey)localObject7).setP1yh(localObject6[0]);
            ((GPNormalKey)localObject7).setP2xl((byte)localObject4[paramInt5].radius);
            ((GPNormalKey)localObject7).setP2xh((byte)0);
            ((GPNormalKey)localObject7).setP2yl((byte)0);
            ((GPNormalKey)localObject7).setP2yh((byte)0);
            ((ArrayList)localObject5).add(localObject7);
          }
          if ((localObject4[paramInt5].property == 11) && (localObject4[paramInt5].x != -1) && (paramInt5 >= 22) && (paramInt5 <= 26) && (CommonUtils.getGPKeyCodeByNameScenes1(localObject4[paramInt5].name) != -1) && (paramInt3 >= 5))
          {
            localObject2 = new GPNormalKey();
            ((GPNormalKey)localObject2).setType((byte)10);
            ((GPNormalKey)localObject2).setCode((byte)CommonUtils.getGPKeyCodeByNameScenes1(localObject4[paramInt5].name));
            ((GPNormalKey)localObject2).setP1xl((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject4[paramInt5].y));
            ((GPNormalKey)localObject2).setP1xh((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject4[paramInt5].x));
            ((GPNormalKey)localObject2).setP1yl((byte)localObject4[paramInt5].radius);
            ((GPNormalKey)localObject2).setP1yh((byte)0);
            ((GPNormalKey)localObject2).setP2xl((byte)0);
            ((GPNormalKey)localObject2).setP2xh((byte)0);
            ((GPNormalKey)localObject2).setP2yl((byte)0);
            ((GPNormalKey)localObject2).setP2yh((byte)0);
            ((ArrayList)localObject5).add(localObject2);
          }
          if ((localObject4[paramInt5].property == 12) && (localObject4[paramInt5].x != -1) && (paramInt5 >= 22) && (paramInt5 <= 26) && (CommonUtils.getGPKeyCodeByNameScenes1(localObject4[paramInt5].name) != -1) && (paramInt3 >= 5))
          {
            localObject2 = new GPNormalKey();
            localObject6 = ChangeDataUtil.intToByteArray(localObject4[paramInt5].radius);
            ((GPNormalKey)localObject2).setType((byte)11);
            ((GPNormalKey)localObject2).setCode((byte)CommonUtils.getGPKeyCodeByNameScenes1(localObject4[paramInt5].name));
            ((GPNormalKey)localObject2).setP1xl((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject4[paramInt5].y));
            ((GPNormalKey)localObject2).setP1xh((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject4[paramInt5].x));
            ((GPNormalKey)localObject2).setP1yl(localObject6[1]);
            ((GPNormalKey)localObject2).setP1yh(localObject6[0]);
            ((GPNormalKey)localObject2).setP2xl((byte)localObject4[paramInt5].block);
            ((GPNormalKey)localObject2).setP2xh((byte)localObject4[paramInt5].reverse);
            ((GPNormalKey)localObject2).setP2yl((byte)0);
            ((GPNormalKey)localObject2).setP2yh((byte)0);
            ((ArrayList)localObject5).add(localObject2);
          }
          if ((localObject4[paramInt5].property == 14) && (localObject4[paramInt5].x != -1) && (paramInt5 > 933) && (CommonUtils.getGPKeyCodeByNameScenes1(localObject4[paramInt5].name) != -1))
          {
            localObject2 = new GPNormalKey();
            ((GPNormalKey)localObject2).setType((byte)15);
            ((GPNormalKey)localObject2).setCode((byte)CommonUtils.getGPKeyCodeByNameScenes1(localObject4[paramInt5].name));
            ((GPNormalKey)localObject2).setP1xl((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject4[paramInt5].y));
            ((GPNormalKey)localObject2).setP1xh((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject4[paramInt5].x));
            ((GPNormalKey)localObject2).setP1yl((byte)localObject4[paramInt5].radius);
            ((GPNormalKey)localObject2).setP1yh((byte)localObject4[paramInt5].block);
            if ((CommonUtils.isStringValid(localObject4[paramInt5].view_joystick)) && (!localObject4[paramInt5].view_joystick.equalsIgnoreCase("-1")))
              ((GPNormalKey)localObject2).setP2xl((byte)CommonUtils.getGPKeyCodeByNameScenes1(localObject4[paramInt5].view_joystick));
            else
              ((GPNormalKey)localObject2).setP2xl((byte)0);
            ((GPNormalKey)localObject2).setP2xh((byte)localObject4[paramInt5].turb_speed);
            ((GPNormalKey)localObject2).setP2yl((byte)0);
            ((GPNormalKey)localObject2).setP2yh((byte)0);
            ((ArrayList)localObject5).add(localObject2);
          }
          if ((localObject4[paramInt5].property == 13) && (localObject4[paramInt5].x != -1) && (paramInt5 > 453) && (paramInt5 < 486) && (CommonUtils.getGPKeyCodeByNameScenes1(localObject4[paramInt5].name) != -1))
          {
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append("----i = ");
            ((StringBuilder)localObject2).append(paramInt5);
            MyLog.e("my_tag", ((StringBuilder)localObject2).toString());
            localObject2 = new MacroKey();
            ((MacroKey)localObject2).setType((byte)14);
            ((MacroKey)localObject2).setKeyCode((byte)CommonUtils.getGPKeyCodeByNameScenes1(localObject4[paramInt5].name));
            ((MacroKey)localObject2).setPoint0X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject4[paramInt5].y));
            ((MacroKey)localObject2).setPoint0Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject4[paramInt5].x));
            ((MacroKey)localObject2).setPoint0MacroTime((byte)localObject4[paramInt5].radius);
            ((MacroKey)localObject2).setPoint0PauseTime((byte)localObject4[paramInt5].block);
            paramInt6 = paramInt5 + 32;
            ((MacroKey)localObject2).setPoint1X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject4[paramInt6].y));
            ((MacroKey)localObject2).setPoint1Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject4[paramInt6].x));
            ((MacroKey)localObject2).setPoint1MacroTime((byte)localObject4[paramInt6].radius);
            ((MacroKey)localObject2).setPoint1PauseTime((byte)localObject4[paramInt6].block);
            paramInt6 = paramInt5 + 64;
            if ((localObject4[paramInt6].x != -1) && (localObject4[paramInt6].y != -1) && (localObject4[paramInt6].radius != -1) && (localObject4[paramInt6].block != -1))
            {
              ((MacroKey)localObject2).setPoint2X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject4[paramInt6].y));
              ((MacroKey)localObject2).setPoint2Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject4[paramInt6].x));
              ((MacroKey)localObject2).setPoint2MacroTime((byte)localObject4[paramInt6].radius);
              ((MacroKey)localObject2).setPoint2PauseTime((byte)localObject4[paramInt6].block);
            }
            else
            {
              ((MacroKey)localObject2).setPoint2X((byte)0);
              ((MacroKey)localObject2).setPoint2Y((byte)0);
              ((MacroKey)localObject2).setPoint2MacroTime((byte)0);
              ((MacroKey)localObject2).setPoint2PauseTime((byte)0);
            }
            paramInt6 = paramInt5 + 96;
            if ((localObject4[paramInt6].x != -1) && (localObject4[paramInt6].y != -1) && (localObject4[paramInt6].radius != -1) && (localObject4[paramInt6].block != -1))
            {
              ((MacroKey)localObject2).setPoint3X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject4[paramInt6].y));
              ((MacroKey)localObject2).setPoint3Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject4[paramInt6].x));
              ((MacroKey)localObject2).setPoint3MacroTime((byte)localObject4[paramInt6].radius);
              ((MacroKey)localObject2).setPoint3PauseTime((byte)localObject4[paramInt6].block);
            }
            else
            {
              ((MacroKey)localObject2).setPoint3X((byte)0);
              ((MacroKey)localObject2).setPoint3Y((byte)0);
              ((MacroKey)localObject2).setPoint3MacroTime((byte)0);
              ((MacroKey)localObject2).setPoint3PauseTime((byte)0);
            }
            paramInt6 = paramInt5 + 128;
            if ((localObject4[paramInt6].x != -1) && (localObject4[paramInt6].y != -1) && (localObject4[paramInt6].radius != -1) && (localObject4[paramInt6].block != -1))
            {
              ((MacroKey)localObject2).setPoint4X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject4[paramInt6].y));
              ((MacroKey)localObject2).setPoint4Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject4[paramInt6].x));
              ((MacroKey)localObject2).setPoint4MacroTime((byte)localObject4[paramInt6].radius);
              ((MacroKey)localObject2).setPoint4PauseTime((byte)localObject4[paramInt6].block);
            }
            else
            {
              ((MacroKey)localObject2).setPoint4X((byte)0);
              ((MacroKey)localObject2).setPoint4Y((byte)0);
              ((MacroKey)localObject2).setPoint4MacroTime((byte)0);
              ((MacroKey)localObject2).setPoint4PauseTime((byte)0);
            }
            paramInt6 = paramInt5 + 160;
            if ((localObject4[paramInt6].x != -1) && (localObject4[paramInt6].y != -1) && (localObject4[paramInt6].radius != -1) && (localObject4[paramInt6].block != -1))
            {
              ((MacroKey)localObject2).setPoint5X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject4[paramInt6].y));
              ((MacroKey)localObject2).setPoint5Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject4[paramInt6].x));
              ((MacroKey)localObject2).setPoint5MacroTime((byte)localObject4[paramInt6].radius);
              ((MacroKey)localObject2).setPoint5PauseTime((byte)localObject4[paramInt6].block);
            }
            else
            {
              ((MacroKey)localObject2).setPoint5X((byte)0);
              ((MacroKey)localObject2).setPoint5Y((byte)0);
              ((MacroKey)localObject2).setPoint5MacroTime((byte)0);
              ((MacroKey)localObject2).setPoint5PauseTime((byte)0);
            }
            paramInt6 = paramInt5 + 192;
            if ((localObject4[paramInt6].x != -1) && (localObject4[paramInt6].y != -1) && (localObject4[paramInt6].radius != -1) && (localObject4[paramInt6].block != -1))
            {
              ((MacroKey)localObject2).setPoint6X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject4[paramInt6].y));
              ((MacroKey)localObject2).setPoint6Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject4[paramInt6].x));
              ((MacroKey)localObject2).setPoint6MacroTime((byte)localObject4[paramInt6].radius);
              ((MacroKey)localObject2).setPoint6PauseTime((byte)localObject4[paramInt6].block);
            }
            else
            {
              ((MacroKey)localObject2).setPoint6X((byte)0);
              ((MacroKey)localObject2).setPoint6Y((byte)0);
              ((MacroKey)localObject2).setPoint6MacroTime((byte)0);
              ((MacroKey)localObject2).setPoint6PauseTime((byte)0);
            }
            paramInt6 = paramInt5 + 224;
            if ((localObject4[paramInt6].x != -1) && (localObject4[paramInt6].y != -1) && (localObject4[paramInt6].radius != -1) && (localObject4[paramInt6].block != -1))
            {
              ((MacroKey)localObject2).setPoint7X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject4[paramInt6].y));
              ((MacroKey)localObject2).setPoint7Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject4[paramInt6].x));
              ((MacroKey)localObject2).setPoint7MacroTime((byte)localObject4[paramInt6].radius);
              ((MacroKey)localObject2).setPoint7PauseTime((byte)localObject4[paramInt6].block);
            }
            else
            {
              ((MacroKey)localObject2).setPoint7X((byte)0);
              ((MacroKey)localObject2).setPoint7Y((byte)0);
              ((MacroKey)localObject2).setPoint7MacroTime((byte)0);
              ((MacroKey)localObject2).setPoint7PauseTime((byte)0);
            }
            paramInt6 = paramInt5 + 256;
            if ((localObject4[paramInt6].x != -1) && (localObject4[paramInt6].y != -1) && (localObject4[paramInt6].radius != -1) && (localObject4[paramInt6].block != -1))
            {
              ((MacroKey)localObject2).setPoint8X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject4[paramInt6].y));
              ((MacroKey)localObject2).setPoint8Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject4[paramInt6].x));
              ((MacroKey)localObject2).setPoint8MacroTime((byte)localObject4[paramInt6].radius);
              ((MacroKey)localObject2).setPoint8PauseTime((byte)localObject4[paramInt6].block);
            }
            else
            {
              ((MacroKey)localObject2).setPoint8X((byte)0);
              ((MacroKey)localObject2).setPoint8Y((byte)0);
              ((MacroKey)localObject2).setPoint8MacroTime((byte)0);
              ((MacroKey)localObject2).setPoint8PauseTime((byte)0);
            }
            paramInt6 = paramInt5 + 288;
            if ((localObject4[paramInt6].x != -1) && (localObject4[paramInt6].y != -1) && (localObject4[paramInt6].radius != -1) && (localObject4[paramInt6].block != -1))
            {
              ((MacroKey)localObject2).setPoint9X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject4[paramInt6].y));
              ((MacroKey)localObject2).setPoint9Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject4[paramInt6].x));
              ((MacroKey)localObject2).setPoint9MacroTime((byte)localObject4[paramInt6].radius);
              ((MacroKey)localObject2).setPoint9PauseTime((byte)localObject4[paramInt6].block);
            }
            else
            {
              ((MacroKey)localObject2).setPoint9X((byte)0);
              ((MacroKey)localObject2).setPoint9Y((byte)0);
              ((MacroKey)localObject2).setPoint9MacroTime((byte)0);
              ((MacroKey)localObject2).setPoint9PauseTime((byte)0);
            }
            paramInt6 = paramInt5 + 320;
            if ((localObject4[paramInt6].x != -1) && (localObject4[paramInt6].y != -1) && (localObject4[paramInt6].radius != -1) && (localObject4[paramInt6].block != -1))
            {
              ((MacroKey)localObject2).setPoint10X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject4[paramInt6].y));
              ((MacroKey)localObject2).setPoint10Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject4[paramInt6].x));
              ((MacroKey)localObject2).setPoint10MacroTime((byte)localObject4[paramInt6].radius);
              ((MacroKey)localObject2).setPoint10PauseTime((byte)localObject4[paramInt6].block);
            }
            else
            {
              ((MacroKey)localObject2).setPoint10X((byte)0);
              ((MacroKey)localObject2).setPoint10Y((byte)0);
              ((MacroKey)localObject2).setPoint10MacroTime((byte)0);
              ((MacroKey)localObject2).setPoint10PauseTime((byte)0);
            }
            paramInt6 = paramInt5 + 352;
            if ((localObject4[paramInt6].x != -1) && (localObject4[paramInt6].y != -1) && (localObject4[paramInt6].radius != -1) && (localObject4[paramInt6].block != -1))
            {
              ((MacroKey)localObject2).setPoint11X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject4[paramInt6].y));
              ((MacroKey)localObject2).setPoint11Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject4[paramInt6].x));
              ((MacroKey)localObject2).setPoint11MacroTime((byte)localObject4[paramInt6].radius);
              ((MacroKey)localObject2).setPoint11PauseTime((byte)localObject4[paramInt6].block);
            }
            else
            {
              ((MacroKey)localObject2).setPoint11X((byte)0);
              ((MacroKey)localObject2).setPoint11Y((byte)0);
              ((MacroKey)localObject2).setPoint11MacroTime((byte)0);
              ((MacroKey)localObject2).setPoint11PauseTime((byte)0);
            }
            paramInt6 = paramInt5 + 384;
            if ((localObject4[paramInt6].x != -1) && (localObject4[paramInt6].y != -1) && (localObject4[paramInt6].radius != -1) && (localObject4[paramInt6].block != -1))
            {
              ((MacroKey)localObject2).setPoint12X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject4[paramInt6].y));
              ((MacroKey)localObject2).setPoint12Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject4[paramInt6].x));
              ((MacroKey)localObject2).setPoint12MacroTime((byte)localObject4[paramInt6].radius);
              ((MacroKey)localObject2).setPoint12PauseTime((byte)localObject4[paramInt6].block);
            }
            else
            {
              ((MacroKey)localObject2).setPoint12X((byte)0);
              ((MacroKey)localObject2).setPoint12Y((byte)0);
              ((MacroKey)localObject2).setPoint12MacroTime((byte)0);
              ((MacroKey)localObject2).setPoint12PauseTime((byte)0);
            }
            paramInt6 = paramInt5 + 416;
            if ((localObject4[paramInt6].x != -1) && (localObject4[paramInt6].y != -1) && (localObject4[paramInt6].radius != -1) && (localObject4[paramInt6].block != -1))
            {
              ((MacroKey)localObject2).setPoint13X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject4[paramInt6].y));
              ((MacroKey)localObject2).setPoint13Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject4[paramInt6].x));
              ((MacroKey)localObject2).setPoint13MacroTime((byte)localObject4[paramInt6].radius);
              ((MacroKey)localObject2).setPoint13PauseTime((byte)localObject4[paramInt6].block);
            }
            else
            {
              ((MacroKey)localObject2).setPoint13X((byte)0);
              ((MacroKey)localObject2).setPoint13Y((byte)0);
              ((MacroKey)localObject2).setPoint13MacroTime((byte)0);
              ((MacroKey)localObject2).setPoint13PauseTime((byte)0);
            }
            paramInt6 = paramInt5 + 448;
            if ((localObject4[paramInt6].x != -1) && (localObject4[paramInt6].y != -1) && (localObject4[paramInt6].radius != -1) && (localObject4[paramInt6].block != -1))
            {
              ((MacroKey)localObject2).setPoint14X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject4[paramInt6].y));
              ((MacroKey)localObject2).setPoint14Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject4[paramInt6].x));
              ((MacroKey)localObject2).setPoint14MacroTime((byte)localObject4[paramInt6].radius);
              ((MacroKey)localObject2).setPoint14PauseTime((byte)localObject4[paramInt6].block);
            }
            else
            {
              ((MacroKey)localObject2).setPoint14X((byte)0);
              ((MacroKey)localObject2).setPoint14Y((byte)0);
              ((MacroKey)localObject2).setPoint14MacroTime((byte)0);
              ((MacroKey)localObject2).setPoint14PauseTime((byte)0);
            }
            ((ArrayList)localObject3).add(localObject2);
          }
          paramInt5 += 1;
        }
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("------ key size = ");
        ((StringBuilder)localObject2).append(((ArrayList)localObject5).size());
        MyLog.i("my_tag", ((StringBuilder)localObject2).toString());
        paramInt1 = 0;
        while (paramInt1 < ((ArrayList)localObject5).size())
        {
          paramInt2 = paramInt1 * 10;
          paramInt3 = paramInt2 + 19;
          if (paramInt3 < localObject1.length)
          {
            localObject1[(paramInt2 + 10)] = ((GPNormalKey)((ArrayList)localObject5).get(paramInt1)).getType();
            localObject1[(paramInt2 + 11)] = ((GPNormalKey)((ArrayList)localObject5).get(paramInt1)).getCode();
            localObject1[(paramInt2 + 12)] = ((GPNormalKey)((ArrayList)localObject5).get(paramInt1)).getP1xl();
            localObject1[(paramInt2 + 13)] = ((GPNormalKey)((ArrayList)localObject5).get(paramInt1)).getP1xh();
            localObject1[(paramInt2 + 14)] = ((GPNormalKey)((ArrayList)localObject5).get(paramInt1)).getP1yl();
            localObject1[(paramInt2 + 15)] = ((GPNormalKey)((ArrayList)localObject5).get(paramInt1)).getP1yh();
            localObject1[(paramInt2 + 16)] = ((GPNormalKey)((ArrayList)localObject5).get(paramInt1)).getP2xl();
            localObject1[(paramInt2 + 17)] = ((GPNormalKey)((ArrayList)localObject5).get(paramInt1)).getP2xh();
            localObject1[(paramInt2 + 18)] = ((GPNormalKey)((ArrayList)localObject5).get(paramInt1)).getP2yl();
            localObject1[paramInt3] = ((GPNormalKey)((ArrayList)localObject5).get(paramInt1)).getP2yh();
          }
          paramInt1 += 1;
        }
        paramInt1 = 0;
        while (paramInt1 < ((ArrayList)localObject3).size())
        {
          localObject2 = MacroKeyUtils.AnalysisMacroKey((MacroKey)((ArrayList)localObject3).get(paramInt1));
          if (paramInt4 + 9 + localObject2.length < localObject1.length)
            System.arraycopy(localObject2, 0, localObject1, i + localObject2.length * paramInt1, localObject2.length);
          paramInt1 += 1;
        }
        localObject2 = new byte[localObject1.length - 2];
        System.arraycopy(localObject1, 2, localObject2, 0, localObject1.length - 2);
        paramInt1 = CommonUtils.CRC_GetModbus16((byte[])localObject2, localObject1.length - 2);
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("------save checkCode = ");
        ((StringBuilder)localObject2).append(paramInt1);
        MyLog.i("crc_tag", ((StringBuilder)localObject2).toString());
        localObject2 = ChangeDataUtil.intToByteArray(paramInt1);
        localObject1[0] = localObject2[1];
        localObject1[1] = localObject2[0];
        return localObject1;
      }
      paramInt5 *= (paramInt6 * 4 + 2);
      localObject1 = new byte[paramInt5 + 264];
      localObject1[2] = 2;
      localObject1[3] = ((byte)paramInt3);
      localObject1[4] = 0;
      localObject1[5] = localObject3[1];
      localObject1[6] = localObject3[0];
      localObject1[7] = localObject5[1];
      localObject1[8] = localObject5[0];
      localObject4 = new ArrayList();
      localObject3 = new ArrayList();
      paramInt6 = 0;
      while (paramInt6 < localObject2.length)
      {
        if (paramInt6 == 16)
          if ((localObject2[paramInt6].name.equalsIgnoreCase("jl")) && (localObject2[paramInt6].x != -1) && (paramInt6 < 38))
          {
            localObject5 = ChangeDataUtil.intToByteArray(paramInt1 - localObject2[paramInt6].y);
            localObject6 = ChangeDataUtil.intToByteArray(localObject2[paramInt6].x);
            if (localObject2[paramInt6].radius != 0)
            {
              localObject7 = ChangeDataUtil.intToByteArray(localObject2[paramInt6].radius);
              localObject1[9] = 0;
              localObject1[10] = localObject5[1];
              localObject1[11] = localObject5[0];
              localObject1[12] = localObject6[1];
              localObject1[13] = localObject6[0];
              localObject1[14] = localObject7[1];
              localObject1[15] = localObject7[0];
            }
            else
            {
              localObject1[9] = 1;
              localObject1[10] = localObject5[1];
              localObject1[11] = localObject5[0];
              localObject1[12] = localObject6[1];
              localObject1[13] = localObject6[0];
              localObject1[14] = ((byte)localObject2[paramInt6].block);
              localObject1[15] = 0;
            }
          }
          else
          {
            localObject1[9] = 2;
            localObject1[10] = 0;
            localObject1[11] = 0;
            localObject1[12] = 0;
            localObject1[13] = 0;
            localObject1[14] = 0;
            localObject1[15] = 0;
          }
        if (paramInt6 == 17)
          if ((localObject2[paramInt6].name.equalsIgnoreCase("jr")) && (localObject2[paramInt6].x != -1) && (paramInt6 < 38))
          {
            localObject5 = ChangeDataUtil.intToByteArray(paramInt1 - localObject2[paramInt6].y);
            localObject6 = ChangeDataUtil.intToByteArray(localObject2[paramInt6].x);
            if (localObject2[paramInt6].radius != 0)
            {
              localObject7 = ChangeDataUtil.intToByteArray(localObject2[paramInt6].radius);
              localObject1[16] = 0;
              localObject1[17] = localObject5[1];
              localObject1[18] = localObject5[0];
              localObject1[19] = localObject6[1];
              localObject1[20] = localObject6[0];
              localObject1[21] = localObject7[1];
              localObject1[22] = localObject7[0];
            }
            else
            {
              localObject1[16] = 1;
              localObject1[17] = localObject5[1];
              localObject1[18] = localObject5[0];
              localObject1[19] = localObject6[1];
              localObject1[20] = localObject6[0];
              localObject1[21] = ((byte)localObject2[paramInt6].block);
              localObject1[22] = 0;
            }
          }
          else
          {
            localObject1[16] = 2;
            localObject1[17] = 0;
            localObject1[18] = 0;
            localObject1[19] = 0;
            localObject1[20] = 0;
            localObject1[21] = 0;
            localObject1[22] = 0;
          }
        if ((!localObject2[paramInt6].name.equals("-1")) && (localObject2[paramInt6].x != -1) && (paramInt6 > 294) && (paramInt6 < 326) && (paramInt6 != 310) && (paramInt6 != 311) && (localObject2[paramInt6].property == 7) && (CommonUtils.getGPKeyCodeByName(localObject2[paramInt6].name) != -1))
          localObject1[23] = ((byte)CommonUtils.getGPKeyCodeByName(localObject2[paramInt6].name));
        if ((!localObject2[paramInt6].name.equals("-1")) && (localObject2[paramInt6].x != -1) && (paramInt6 < 38) && (CommonUtils.getGPKeyCodeByName(localObject2[paramInt6].name) != -1) && (paramInt6 != 16) && (paramInt6 != 17) && (paramInt6 != 22) && (paramInt6 != 23) && (paramInt6 != 24) && (paramInt6 != 25) && (paramInt6 != 26))
        {
          localObject5 = ChangeDataUtil.intToByteArray(paramInt1 - localObject2[paramInt6].y);
          localObject6 = ChangeDataUtil.intToByteArray(localObject2[paramInt6].x);
          localObject7 = new GPNormalKey();
          ((GPNormalKey)localObject7).setType((byte)1);
          ((GPNormalKey)localObject7).setCode((byte)CommonUtils.getGPKeyCodeByName(localObject2[paramInt6].name));
          ((GPNormalKey)localObject7).setP1xl(localObject5[1]);
          ((GPNormalKey)localObject7).setP1xh(localObject5[0]);
          ((GPNormalKey)localObject7).setP1yl(localObject6[1]);
          ((GPNormalKey)localObject7).setP1yh(localObject6[0]);
          ((GPNormalKey)localObject7).setP2xl((byte)0);
          ((GPNormalKey)localObject7).setP2xh((byte)0);
          ((GPNormalKey)localObject7).setP2yl((byte)0);
          ((GPNormalKey)localObject7).setP2yh((byte)0);
          ((ArrayList)localObject4).add(localObject7);
        }
        if ((localObject2[paramInt6].property == 1) && (localObject2[paramInt6].x != -1) && (paramInt6 > 37) && (paramInt6 < 70) && (CommonUtils.getGPKeyCodeByName(localObject2[paramInt6].name) != -1) && (paramInt6 != 54) && (paramInt6 != 55))
        {
          localObject6 = ChangeDataUtil.intToByteArray(paramInt1 - localObject2[paramInt6].y);
          localObject7 = ChangeDataUtil.intToByteArray(localObject2[paramInt6].x);
          localObject5 = new GPNormalKey();
          ((GPNormalKey)localObject5).setType((byte)6);
          ((GPNormalKey)localObject5).setCode((byte)CommonUtils.getGPKeyCodeByName(localObject2[paramInt6].name));
          ((GPNormalKey)localObject5).setP1xl(localObject6[1]);
          ((GPNormalKey)localObject5).setP1xh(localObject6[0]);
          ((GPNormalKey)localObject5).setP1yl(localObject7[1]);
          ((GPNormalKey)localObject5).setP1yh(localObject7[0]);
          if (localObject2[paramInt6].radius != 0)
          {
            localObject6 = ChangeDataUtil.intToByteArray(localObject2[paramInt6].radius);
            ((GPNormalKey)localObject5).setP2xl((byte)1);
            ((GPNormalKey)localObject5).setP2xh(localObject6[1]);
            ((GPNormalKey)localObject5).setP2yl(localObject6[0]);
            ((GPNormalKey)localObject5).setP2yh((byte)localObject2[paramInt6].reverse);
          }
          else
          {
            ((GPNormalKey)localObject5).setP2xl((byte)4);
            ((GPNormalKey)localObject5).setP2xh((byte)localObject2[paramInt6].block);
            ((GPNormalKey)localObject5).setP2yl((byte)0);
            ((GPNormalKey)localObject5).setP2yh((byte)0);
          }
          ((ArrayList)localObject4).add(localObject5);
        }
        if ((localObject2[paramInt6].property == 2) && (localObject2[paramInt6].x != -1) && (paramInt6 > 69) && (paramInt6 < 102) && (CommonUtils.getGPKeyCodeByName(localObject2[paramInt6].name) != -1) && (paramInt6 != 86) && (paramInt6 != 87))
        {
          localObject6 = ChangeDataUtil.intToByteArray(paramInt1 - localObject2[paramInt6].y);
          localObject7 = ChangeDataUtil.intToByteArray(localObject2[paramInt6].x);
          localObject5 = new GPNormalKey();
          ((GPNormalKey)localObject5).setType((byte)6);
          ((GPNormalKey)localObject5).setCode((byte)CommonUtils.getGPKeyCodeByName(localObject2[paramInt6].name));
          ((GPNormalKey)localObject5).setP1xl(localObject6[1]);
          ((GPNormalKey)localObject5).setP1xh(localObject6[0]);
          ((GPNormalKey)localObject5).setP1yl(localObject7[1]);
          ((GPNormalKey)localObject5).setP1yh(localObject7[0]);
          if (localObject2[paramInt6].radius != 0)
          {
            localObject6 = ChangeDataUtil.intToByteArray(localObject2[paramInt6].radius);
            ((GPNormalKey)localObject5).setP2xl((byte)2);
            ((GPNormalKey)localObject5).setP2xh(localObject6[1]);
            ((GPNormalKey)localObject5).setP2yl(localObject6[0]);
            ((GPNormalKey)localObject5).setP2yh((byte)localObject2[paramInt6].reverse);
          }
          else
          {
            ((GPNormalKey)localObject5).setP2xl((byte)5);
            ((GPNormalKey)localObject5).setP2xh((byte)localObject2[paramInt6].block);
            ((GPNormalKey)localObject5).setP2yl((byte)0);
            ((GPNormalKey)localObject5).setP2yh((byte)0);
          }
          ((ArrayList)localObject4).add(localObject5);
        }
        if ((localObject2[paramInt6].property == 3) && (localObject2[paramInt6].x != -1) && (paramInt6 > 101) && (paramInt6 < 134) && (CommonUtils.getGPKeyCodeByName(localObject2[paramInt6].name) != -1) && (paramInt6 != 118) && (paramInt6 != 119))
        {
          localObject6 = ChangeDataUtil.intToByteArray(paramInt1 - localObject2[paramInt6].y);
          localObject7 = ChangeDataUtil.intToByteArray(localObject2[paramInt6].x);
          localObject5 = new GPNormalKey();
          ((GPNormalKey)localObject5).setType((byte)6);
          ((GPNormalKey)localObject5).setCode((byte)CommonUtils.getGPKeyCodeByName(localObject2[paramInt6].name));
          ((GPNormalKey)localObject5).setP1xl(localObject6[1]);
          ((GPNormalKey)localObject5).setP1xh(localObject6[0]);
          ((GPNormalKey)localObject5).setP1yl(localObject7[1]);
          ((GPNormalKey)localObject5).setP1yh(localObject7[0]);
          if (localObject2[paramInt6].radius != 0)
          {
            localObject6 = ChangeDataUtil.intToByteArray(localObject2[paramInt6].radius);
            ((GPNormalKey)localObject5).setP2xl((byte)3);
            ((GPNormalKey)localObject5).setP2xh(localObject6[1]);
            ((GPNormalKey)localObject5).setP2yl(localObject6[0]);
            ((GPNormalKey)localObject5).setP2yh((byte)localObject2[paramInt6].reverse);
          }
          else
          {
            ((GPNormalKey)localObject5).setP2xl((byte)6);
            ((GPNormalKey)localObject5).setP2xh((byte)localObject2[paramInt6].block);
            ((GPNormalKey)localObject5).setP2yl((byte)0);
            ((GPNormalKey)localObject5).setP2yh((byte)0);
          }
          ((ArrayList)localObject4).add(localObject5);
        }
        if ((localObject2[paramInt6].property == 4) && (localObject2[paramInt6].x != -1) && (paramInt6 > 133) && (paramInt6 < 166) && (CommonUtils.getGPKeyCodeByName(localObject2[paramInt6].name) != -1) && (paramInt6 != 150) && (paramInt6 != 151))
        {
          localObject5 = ChangeDataUtil.intToByteArray(paramInt1 - localObject2[paramInt6].y);
          localObject6 = ChangeDataUtil.intToByteArray(localObject2[paramInt6].x);
          i = paramInt6 + 32;
          localObject7 = ChangeDataUtil.intToByteArray(paramInt1 - localObject2[i].y);
          arrayOfByte1 = ChangeDataUtil.intToByteArray(localObject2[i].x);
          localObject8 = new GPNormalKey();
          ((GPNormalKey)localObject8).setType((byte)2);
          ((GPNormalKey)localObject8).setCode((byte)CommonUtils.getGPKeyCodeByName(localObject2[paramInt6].name));
          ((GPNormalKey)localObject8).setP1xl(localObject5[1]);
          ((GPNormalKey)localObject8).setP1xh(localObject5[0]);
          ((GPNormalKey)localObject8).setP1yl(localObject6[1]);
          ((GPNormalKey)localObject8).setP1yh(localObject6[0]);
          ((GPNormalKey)localObject8).setP2xl(localObject7[1]);
          ((GPNormalKey)localObject8).setP2xh(localObject7[0]);
          ((GPNormalKey)localObject8).setP2yl(arrayOfByte1[1]);
          ((GPNormalKey)localObject8).setP2yh(arrayOfByte1[0]);
          ((ArrayList)localObject4).add(localObject8);
        }
        if ((localObject2[paramInt6].property == 5) && (localObject2[paramInt6].x != -1) && (paramInt6 > 197) && (paramInt6 < 230) && (CommonUtils.getGPKeyCodeByName(localObject2[paramInt6].name) != -1) && (paramInt6 != 214) && (paramInt6 != 215))
        {
          localObject5 = ChangeDataUtil.intToByteArray(paramInt1 - localObject2[paramInt6].y);
          localObject6 = ChangeDataUtil.intToByteArray(localObject2[paramInt6].x);
          i = paramInt6 + 32;
          localObject7 = ChangeDataUtil.intToByteArray(paramInt1 - localObject2[i].y);
          arrayOfByte1 = ChangeDataUtil.intToByteArray(localObject2[i].x);
          localObject8 = new GPNormalKey();
          ((GPNormalKey)localObject8).setType((byte)3);
          ((GPNormalKey)localObject8).setCode((byte)CommonUtils.getGPKeyCodeByName(localObject2[paramInt6].name));
          ((GPNormalKey)localObject8).setP1xl(localObject5[1]);
          ((GPNormalKey)localObject8).setP1xh(localObject5[0]);
          ((GPNormalKey)localObject8).setP1yl(localObject6[1]);
          ((GPNormalKey)localObject8).setP1yh(localObject6[0]);
          ((GPNormalKey)localObject8).setP2xl(localObject7[1]);
          ((GPNormalKey)localObject8).setP2xh(localObject7[0]);
          ((GPNormalKey)localObject8).setP2yl(arrayOfByte1[1]);
          ((GPNormalKey)localObject8).setP2yh(arrayOfByte1[0]);
          ((ArrayList)localObject4).add(localObject8);
        }
        if ((localObject2[paramInt6].property == 6) && (localObject2[paramInt6].x != -1) && (paramInt6 > 261) && (paramInt6 < 294) && (CommonUtils.getGPKeyCodeByName(localObject2[paramInt6].name) != -1) && (paramInt6 != 278) && (paramInt6 != 279))
        {
          localObject5 = ChangeDataUtil.intToByteArray(paramInt1 - localObject2[paramInt6].y);
          localObject6 = ChangeDataUtil.intToByteArray(localObject2[paramInt6].x);
          localObject7 = new GPNormalKey();
          ((GPNormalKey)localObject7).setType((byte)6);
          ((GPNormalKey)localObject7).setCode((byte)CommonUtils.getGPKeyCodeByName(localObject2[paramInt6].name));
          ((GPNormalKey)localObject7).setP1xl(localObject5[1]);
          ((GPNormalKey)localObject7).setP1xh(localObject5[0]);
          ((GPNormalKey)localObject7).setP1yl(localObject6[1]);
          ((GPNormalKey)localObject7).setP1yh(localObject6[0]);
          ((GPNormalKey)localObject7).setP2xl((byte)5);
          ((GPNormalKey)localObject7).setP2xh((byte)localObject2[paramInt6].block);
          ((GPNormalKey)localObject7).setP2yl((byte)0);
          ((GPNormalKey)localObject7).setP2yh((byte)0);
          ((ArrayList)localObject4).add(localObject7);
        }
        if ((localObject2[paramInt6].property == 8) && (localObject2[paramInt6].x != -1) && (paramInt6 > 325) && (paramInt6 < 358) && (CommonUtils.getGPKeyCodeByName1(localObject2[paramInt6].name) != -1) && (paramInt6 != 342) && (paramInt6 != 343))
        {
          localObject5 = ChangeDataUtil.intToByteArray(paramInt1 - localObject2[paramInt6].y);
          localObject6 = ChangeDataUtil.intToByteArray(localObject2[paramInt6].x);
          localObject7 = new GPNormalKey();
          ((GPNormalKey)localObject7).setType((byte)7);
          ((GPNormalKey)localObject7).setCode((byte)CommonUtils.getGPKeyCodeByName1(localObject2[paramInt6].name));
          ((GPNormalKey)localObject7).setP1xl(localObject5[1]);
          ((GPNormalKey)localObject7).setP1xh(localObject5[0]);
          ((GPNormalKey)localObject7).setP1yl(localObject6[1]);
          ((GPNormalKey)localObject7).setP1yh(localObject6[0]);
          ((GPNormalKey)localObject7).setP2xl((byte)0);
          ((GPNormalKey)localObject7).setP2xh((byte)0);
          ((GPNormalKey)localObject7).setP2yl((byte)0);
          ((GPNormalKey)localObject7).setP2yh((byte)0);
          ((ArrayList)localObject4).add(localObject7);
        }
        if ((localObject2[paramInt6].property == 9) && (localObject2[paramInt6].x != -1) && (paramInt6 > 357) && (paramInt6 < 390) && (CommonUtils.getGPKeyCodeByName(localObject2[paramInt6].name) != -1) && (paramInt3 >= 3))
        {
          localObject5 = new GPNormalKey();
          ((GPNormalKey)localObject5).setType((byte)8);
          ((GPNormalKey)localObject5).setCode((byte)CommonUtils.getGPKeyCodeByName(localObject2[paramInt6].name));
          ((GPNormalKey)localObject5).setP1xl((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject2[paramInt6].y));
          ((GPNormalKey)localObject5).setP1xh((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject2[paramInt6].x));
          i = paramInt6 + 32;
          ((GPNormalKey)localObject5).setP1yl((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject2[i].y));
          ((GPNormalKey)localObject5).setP1yh((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject2[i].x));
          ((GPNormalKey)localObject5).setP2xl((byte)localObject2[paramInt6].radius);
          ((GPNormalKey)localObject5).setP2xh((byte)localObject2[paramInt6].block);
          ((GPNormalKey)localObject5).setP2yl((byte)0);
          ((GPNormalKey)localObject5).setP2yh((byte)0);
          if ((((GPNormalKey)localObject5).getP1xl() == ((GPNormalKey)localObject5).getP1yl()) && (((GPNormalKey)localObject5).getP1xh() == ((GPNormalKey)localObject5).getP1yh()))
          {
            ((GPNormalKey)localObject5).setP1yl((byte)(((GPNormalKey)localObject5).getP1xl() + 1));
            ((GPNormalKey)localObject5).setP1yh((byte)(((GPNormalKey)localObject5).getP1xh() + 1));
          }
          ((ArrayList)localObject4).add(localObject5);
        }
        if ((localObject2[paramInt6].property == 10) && (localObject2[paramInt6].x != -1) && (paramInt6 > 421) && (paramInt6 < 454) && (CommonUtils.getGPKeyCodeByName(localObject2[paramInt6].name) != -1) && (paramInt3 >= 4))
        {
          localObject5 = ChangeDataUtil.intToByteArray(paramInt1 - localObject2[paramInt6].y);
          localObject6 = ChangeDataUtil.intToByteArray(localObject2[paramInt6].x);
          localObject7 = new GPNormalKey();
          ((GPNormalKey)localObject7).setType((byte)9);
          ((GPNormalKey)localObject7).setCode((byte)CommonUtils.getGPKeyCodeByName(localObject2[paramInt6].name));
          ((GPNormalKey)localObject7).setP1xl(localObject5[1]);
          ((GPNormalKey)localObject7).setP1xh(localObject5[0]);
          ((GPNormalKey)localObject7).setP1yl(localObject6[1]);
          ((GPNormalKey)localObject7).setP1yh(localObject6[0]);
          ((GPNormalKey)localObject7).setP2xl((byte)localObject2[paramInt6].radius);
          ((GPNormalKey)localObject7).setP2xh((byte)0);
          ((GPNormalKey)localObject7).setP2yl((byte)0);
          ((GPNormalKey)localObject7).setP2yh((byte)0);
          ((ArrayList)localObject4).add(localObject7);
        }
        if ((localObject2[paramInt6].property == 13) && (localObject2[paramInt6].x != -1) && (paramInt6 > 453) && (paramInt6 < 486) && (CommonUtils.getGPKeyCodeByName(localObject2[paramInt6].name) != -1))
        {
          localObject5 = new StringBuilder();
          ((StringBuilder)localObject5).append("----i = ");
          ((StringBuilder)localObject5).append(paramInt6);
          MyLog.e("my_tag", ((StringBuilder)localObject5).toString());
          localObject5 = new MacroKey();
          ((MacroKey)localObject5).setType((byte)14);
          ((MacroKey)localObject5).setKeyCode((byte)CommonUtils.getGPKeyCodeByName(localObject2[paramInt6].name));
          ((MacroKey)localObject5).setPoint0X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject2[paramInt6].y));
          ((MacroKey)localObject5).setPoint0Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject2[paramInt6].x));
          ((MacroKey)localObject5).setPoint0MacroTime((byte)localObject2[paramInt6].radius);
          ((MacroKey)localObject5).setPoint0PauseTime((byte)localObject2[paramInt6].block);
          i = paramInt6 + 32;
          ((MacroKey)localObject5).setPoint1X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject2[i].y));
          ((MacroKey)localObject5).setPoint1Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject2[i].x));
          ((MacroKey)localObject5).setPoint1MacroTime((byte)localObject2[i].radius);
          ((MacroKey)localObject5).setPoint1PauseTime((byte)localObject2[i].block);
          i = paramInt6 + 64;
          if ((localObject2[i].x != -1) && (localObject2[i].y != -1) && (localObject2[i].radius != -1) && (localObject2[i].block != -1))
          {
            ((MacroKey)localObject5).setPoint2X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject2[i].y));
            ((MacroKey)localObject5).setPoint2Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject2[i].x));
            ((MacroKey)localObject5).setPoint2MacroTime((byte)localObject2[i].radius);
            ((MacroKey)localObject5).setPoint2PauseTime((byte)localObject2[i].block);
          }
          else
          {
            ((MacroKey)localObject5).setPoint2X((byte)0);
            ((MacroKey)localObject5).setPoint2Y((byte)0);
            ((MacroKey)localObject5).setPoint2MacroTime((byte)0);
            ((MacroKey)localObject5).setPoint2PauseTime((byte)0);
          }
          i = paramInt6 + 96;
          if ((localObject2[i].x != -1) && (localObject2[i].y != -1) && (localObject2[i].radius != -1) && (localObject2[i].block != -1))
          {
            ((MacroKey)localObject5).setPoint3X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject2[i].y));
            ((MacroKey)localObject5).setPoint3Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject2[i].x));
            ((MacroKey)localObject5).setPoint3MacroTime((byte)localObject2[i].radius);
            ((MacroKey)localObject5).setPoint3PauseTime((byte)localObject2[i].block);
          }
          else
          {
            ((MacroKey)localObject5).setPoint3X((byte)0);
            ((MacroKey)localObject5).setPoint3Y((byte)0);
            ((MacroKey)localObject5).setPoint3MacroTime((byte)0);
            ((MacroKey)localObject5).setPoint3PauseTime((byte)0);
          }
          i = paramInt6 + 128;
          if ((localObject2[i].x != -1) && (localObject2[i].y != -1) && (localObject2[i].radius != -1) && (localObject2[i].block != -1))
          {
            ((MacroKey)localObject5).setPoint4X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject2[i].y));
            ((MacroKey)localObject5).setPoint4Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject2[i].x));
            ((MacroKey)localObject5).setPoint4MacroTime((byte)localObject2[i].radius);
            ((MacroKey)localObject5).setPoint4PauseTime((byte)localObject2[i].block);
          }
          else
          {
            ((MacroKey)localObject5).setPoint4X((byte)0);
            ((MacroKey)localObject5).setPoint4Y((byte)0);
            ((MacroKey)localObject5).setPoint4MacroTime((byte)0);
            ((MacroKey)localObject5).setPoint4PauseTime((byte)0);
          }
          i = paramInt6 + 160;
          if ((localObject2[i].x != -1) && (localObject2[i].y != -1) && (localObject2[i].radius != -1) && (localObject2[i].block != -1))
          {
            ((MacroKey)localObject5).setPoint5X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject2[i].y));
            ((MacroKey)localObject5).setPoint5Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject2[i].x));
            ((MacroKey)localObject5).setPoint5MacroTime((byte)localObject2[i].radius);
            ((MacroKey)localObject5).setPoint5PauseTime((byte)localObject2[i].block);
          }
          else
          {
            ((MacroKey)localObject5).setPoint5X((byte)0);
            ((MacroKey)localObject5).setPoint5Y((byte)0);
            ((MacroKey)localObject5).setPoint5MacroTime((byte)0);
            ((MacroKey)localObject5).setPoint5PauseTime((byte)0);
          }
          i = paramInt6 + 192;
          if ((localObject2[i].x != -1) && (localObject2[i].y != -1) && (localObject2[i].radius != -1) && (localObject2[i].block != -1))
          {
            ((MacroKey)localObject5).setPoint6X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject2[i].y));
            ((MacroKey)localObject5).setPoint6Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject2[i].x));
            ((MacroKey)localObject5).setPoint6MacroTime((byte)localObject2[i].radius);
            ((MacroKey)localObject5).setPoint6PauseTime((byte)localObject2[i].block);
          }
          else
          {
            ((MacroKey)localObject5).setPoint6X((byte)0);
            ((MacroKey)localObject5).setPoint6Y((byte)0);
            ((MacroKey)localObject5).setPoint6MacroTime((byte)0);
            ((MacroKey)localObject5).setPoint6PauseTime((byte)0);
          }
          i = paramInt6 + 224;
          if ((localObject2[i].x != -1) && (localObject2[i].y != -1) && (localObject2[i].radius != -1) && (localObject2[i].block != -1))
          {
            ((MacroKey)localObject5).setPoint7X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject2[i].y));
            ((MacroKey)localObject5).setPoint7Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject2[i].x));
            ((MacroKey)localObject5).setPoint7MacroTime((byte)localObject2[i].radius);
            ((MacroKey)localObject5).setPoint7PauseTime((byte)localObject2[i].block);
          }
          else
          {
            ((MacroKey)localObject5).setPoint7X((byte)0);
            ((MacroKey)localObject5).setPoint7Y((byte)0);
            ((MacroKey)localObject5).setPoint7MacroTime((byte)0);
            ((MacroKey)localObject5).setPoint7PauseTime((byte)0);
          }
          i = paramInt6 + 256;
          if ((localObject2[i].x != -1) && (localObject2[i].y != -1) && (localObject2[i].radius != -1) && (localObject2[i].block != -1))
          {
            ((MacroKey)localObject5).setPoint8X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject2[i].y));
            ((MacroKey)localObject5).setPoint8Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject2[i].x));
            ((MacroKey)localObject5).setPoint8MacroTime((byte)localObject2[i].radius);
            ((MacroKey)localObject5).setPoint8PauseTime((byte)localObject2[i].block);
          }
          else
          {
            ((MacroKey)localObject5).setPoint8X((byte)0);
            ((MacroKey)localObject5).setPoint8Y((byte)0);
            ((MacroKey)localObject5).setPoint8MacroTime((byte)0);
            ((MacroKey)localObject5).setPoint8PauseTime((byte)0);
          }
          i = paramInt6 + 288;
          if ((localObject2[i].x != -1) && (localObject2[i].y != -1) && (localObject2[i].radius != -1) && (localObject2[i].block != -1))
          {
            ((MacroKey)localObject5).setPoint9X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject2[i].y));
            ((MacroKey)localObject5).setPoint9Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject2[i].x));
            ((MacroKey)localObject5).setPoint9MacroTime((byte)localObject2[i].radius);
            ((MacroKey)localObject5).setPoint9PauseTime((byte)localObject2[i].block);
          }
          else
          {
            ((MacroKey)localObject5).setPoint9X((byte)0);
            ((MacroKey)localObject5).setPoint9Y((byte)0);
            ((MacroKey)localObject5).setPoint9MacroTime((byte)0);
            ((MacroKey)localObject5).setPoint9PauseTime((byte)0);
          }
          i = paramInt6 + 320;
          if ((localObject2[i].x != -1) && (localObject2[i].y != -1) && (localObject2[i].radius != -1) && (localObject2[i].block != -1))
          {
            ((MacroKey)localObject5).setPoint10X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject2[i].y));
            ((MacroKey)localObject5).setPoint10Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject2[i].x));
            ((MacroKey)localObject5).setPoint10MacroTime((byte)localObject2[i].radius);
            ((MacroKey)localObject5).setPoint10PauseTime((byte)localObject2[i].block);
          }
          else
          {
            ((MacroKey)localObject5).setPoint10X((byte)0);
            ((MacroKey)localObject5).setPoint10Y((byte)0);
            ((MacroKey)localObject5).setPoint10MacroTime((byte)0);
            ((MacroKey)localObject5).setPoint10PauseTime((byte)0);
          }
          i = paramInt6 + 352;
          if ((localObject2[i].x != -1) && (localObject2[i].y != -1) && (localObject2[i].radius != -1) && (localObject2[i].block != -1))
          {
            ((MacroKey)localObject5).setPoint11X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject2[i].y));
            ((MacroKey)localObject5).setPoint11Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject2[i].x));
            ((MacroKey)localObject5).setPoint11MacroTime((byte)localObject2[i].radius);
            ((MacroKey)localObject5).setPoint11PauseTime((byte)localObject2[i].block);
          }
          else
          {
            ((MacroKey)localObject5).setPoint11X((byte)0);
            ((MacroKey)localObject5).setPoint11Y((byte)0);
            ((MacroKey)localObject5).setPoint11MacroTime((byte)0);
            ((MacroKey)localObject5).setPoint11PauseTime((byte)0);
          }
          i = paramInt6 + 384;
          if ((localObject2[i].x != -1) && (localObject2[i].y != -1) && (localObject2[i].radius != -1) && (localObject2[i].block != -1))
          {
            ((MacroKey)localObject5).setPoint12X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject2[i].y));
            ((MacroKey)localObject5).setPoint12Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject2[i].x));
            ((MacroKey)localObject5).setPoint12MacroTime((byte)localObject2[i].radius);
            ((MacroKey)localObject5).setPoint12PauseTime((byte)localObject2[i].block);
          }
          else
          {
            ((MacroKey)localObject5).setPoint12X((byte)0);
            ((MacroKey)localObject5).setPoint12Y((byte)0);
            ((MacroKey)localObject5).setPoint12MacroTime((byte)0);
            ((MacroKey)localObject5).setPoint12PauseTime((byte)0);
          }
          i = paramInt6 + 416;
          if ((localObject2[i].x != -1) && (localObject2[i].y != -1) && (localObject2[i].radius != -1) && (localObject2[i].block != -1))
          {
            ((MacroKey)localObject5).setPoint13X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject2[i].y));
            ((MacroKey)localObject5).setPoint13Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject2[i].x));
            ((MacroKey)localObject5).setPoint13MacroTime((byte)localObject2[i].radius);
            ((MacroKey)localObject5).setPoint13PauseTime((byte)localObject2[i].block);
          }
          else
          {
            ((MacroKey)localObject5).setPoint13X((byte)0);
            ((MacroKey)localObject5).setPoint13Y((byte)0);
            ((MacroKey)localObject5).setPoint13MacroTime((byte)0);
            ((MacroKey)localObject5).setPoint13PauseTime((byte)0);
          }
          i = paramInt6 + 448;
          if ((localObject2[i].x != -1) && (localObject2[i].y != -1) && (localObject2[i].radius != -1) && (localObject2[i].block != -1))
          {
            ((MacroKey)localObject5).setPoint14X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - localObject2[i].y));
            ((MacroKey)localObject5).setPoint14Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject2[i].x));
            ((MacroKey)localObject5).setPoint14MacroTime((byte)localObject2[i].radius);
            ((MacroKey)localObject5).setPoint14PauseTime((byte)localObject2[i].block);
          }
          else
          {
            ((MacroKey)localObject5).setPoint14X((byte)0);
            ((MacroKey)localObject5).setPoint14Y((byte)0);
            ((MacroKey)localObject5).setPoint14MacroTime((byte)0);
            ((MacroKey)localObject5).setPoint14PauseTime((byte)0);
          }
          ((ArrayList)localObject3).add(localObject5);
        }
        paramInt6 += 1;
      }
      paramInt1 = 0;
      while (paramInt1 < ((ArrayList)localObject4).size())
      {
        paramInt2 = paramInt1 * 10;
        paramInt3 = paramInt2 + 33;
        if (paramInt3 < localObject1.length)
        {
          localObject1[(paramInt2 + 24)] = ((GPNormalKey)((ArrayList)localObject4).get(paramInt1)).getType();
          localObject1[(paramInt2 + 25)] = ((GPNormalKey)((ArrayList)localObject4).get(paramInt1)).getCode();
          localObject1[(paramInt2 + 26)] = ((GPNormalKey)((ArrayList)localObject4).get(paramInt1)).getP1xl();
          localObject1[(paramInt2 + 27)] = ((GPNormalKey)((ArrayList)localObject4).get(paramInt1)).getP1xh();
          localObject1[(paramInt2 + 28)] = ((GPNormalKey)((ArrayList)localObject4).get(paramInt1)).getP1yl();
          localObject1[(paramInt2 + 29)] = ((GPNormalKey)((ArrayList)localObject4).get(paramInt1)).getP1yh();
          localObject1[(paramInt2 + 30)] = ((GPNormalKey)((ArrayList)localObject4).get(paramInt1)).getP2xl();
          localObject1[(paramInt2 + 31)] = ((GPNormalKey)((ArrayList)localObject4).get(paramInt1)).getP2xh();
          localObject1[(paramInt2 + 32)] = ((GPNormalKey)((ArrayList)localObject4).get(paramInt1)).getP2yl();
          localObject1[paramInt3] = ((GPNormalKey)((ArrayList)localObject4).get(paramInt1)).getP2yh();
        }
        paramInt1 += 1;
      }
      paramInt1 = 0;
      while (paramInt1 < ((ArrayList)localObject3).size())
      {
        localObject2 = MacroKeyUtils.AnalysisMacroKey((MacroKey)((ArrayList)localObject3).get(paramInt1));
        paramInt2 = paramInt4 * 10;
        if (paramInt2 + 23 + localObject2.length < localObject1.length)
          System.arraycopy(localObject2, 0, localObject1, paramInt2 + 24 + localObject2.length * paramInt1, localObject2.length);
        paramInt1 += 1;
      }
      localObject2 = new byte[paramInt5 + 262];
      System.arraycopy(localObject1, 2, localObject2, 0, localObject2.length);
      paramInt1 = CommonUtils.CRC_GetModbus16((byte[])localObject2, localObject2.length);
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("------save checkCode = ");
      ((StringBuilder)localObject2).append(paramInt1);
      MyLog.i("crc_tag", ((StringBuilder)localObject2).toString());
      localObject2 = ChangeDataUtil.intToByteArray(paramInt1);
      localObject1[0] = localObject2[1];
      localObject1[1] = localObject2[0];
      return localObject1;
    }
    return null;
  }

  public static byte[] changeDataToByteArray(DataSaver[] paramArrayOfDataSaver, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("resolutionX = ");
    ((StringBuilder)localObject1).append(paramInt1);
    ((StringBuilder)localObject1).append(";  resolutionY = ");
    ((StringBuilder)localObject1).append(paramInt2);
    MyLog.i("my_tag", ((StringBuilder)localObject1).toString());
    Object localObject2 = ChangeDataUtil.intToByteArray(paramInt1);
    Object localObject3 = ChangeDataUtil.intToByteArray(paramInt2);
    int i;
    byte[] arrayOfByte1;
    Object localObject5;
    byte[] arrayOfByte2;
    GPNormalKey localGPNormalKey;
    if (paramInt3 >= 5)
    {
      i = paramInt4 * 10;
      int j = i + 10;
      localObject1 = new byte[j + paramInt5 * (paramInt6 * 4 + 2)];
      localObject1[2] = 2;
      localObject4 = ChangeDataUtil.intToByteArray(paramInt3);
      localObject1[3] = localObject4[1];
      localObject1[4] = localObject4[0];
      localObject1[5] = localObject2[1];
      localObject1[6] = localObject2[0];
      localObject1[7] = localObject3[1];
      localObject1[8] = localObject3[0];
      localObject3 = new ArrayList();
      localObject2 = new ArrayList();
      paramInt4 = 0;
      while (paramInt4 < paramArrayOfDataSaver.length)
      {
        if ((!paramArrayOfDataSaver[paramInt4].name.equals("-1")) && (paramArrayOfDataSaver[paramInt4].x != -1) && (paramInt4 > 294) && (paramInt4 < 326) && (paramInt4 != 310) && (paramInt4 != 311) && (paramArrayOfDataSaver[paramInt4].property == 7) && (CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt4].name) != -1))
          localObject1[9] = ((byte)CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt4].name));
        if ((!paramArrayOfDataSaver[paramInt4].name.equals("-1")) && (paramArrayOfDataSaver[paramInt4].x != -1) && (paramInt4 < 38) && (CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt4].name) != -1) && (paramInt4 != 16) && (paramInt4 != 17) && (paramInt4 != 22) && (paramInt4 != 23) && (paramInt4 != 24) && (paramInt4 != 25) && (paramInt4 != 26))
        {
          localObject4 = ChangeDataUtil.intToByteArray(paramInt1 - paramArrayOfDataSaver[paramInt4].y);
          arrayOfByte1 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaver[paramInt4].x);
          localObject5 = new GPNormalKey();
          ((GPNormalKey)localObject5).setType((byte)1);
          ((GPNormalKey)localObject5).setCode((byte)CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt4].name));
          ((GPNormalKey)localObject5).setP1xl(localObject4[1]);
          ((GPNormalKey)localObject5).setP1xh(localObject4[0]);
          ((GPNormalKey)localObject5).setP1yl(arrayOfByte1[1]);
          ((GPNormalKey)localObject5).setP1yh(arrayOfByte1[0]);
          ((GPNormalKey)localObject5).setP2xl((byte)0);
          ((GPNormalKey)localObject5).setP2xh((byte)0);
          ((GPNormalKey)localObject5).setP2yl((byte)0);
          ((GPNormalKey)localObject5).setP2yh((byte)0);
          ((ArrayList)localObject3).add(localObject5);
        }
        if ((paramArrayOfDataSaver[paramInt4].property == 1) && (paramArrayOfDataSaver[paramInt4].x != -1) && (paramInt4 > 37) && (paramInt4 < 70) && (CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt4].name) != -1) && (paramInt4 != 54) && (paramInt4 != 55))
        {
          arrayOfByte1 = ChangeDataUtil.intToByteArray(paramInt1 - paramArrayOfDataSaver[paramInt4].y);
          localObject5 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaver[paramInt4].x);
          localObject4 = new GPNormalKey();
          ((GPNormalKey)localObject4).setType((byte)6);
          ((GPNormalKey)localObject4).setCode((byte)CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt4].name));
          ((GPNormalKey)localObject4).setP1xl(arrayOfByte1[1]);
          ((GPNormalKey)localObject4).setP1xh(arrayOfByte1[0]);
          ((GPNormalKey)localObject4).setP1yl(localObject5[1]);
          ((GPNormalKey)localObject4).setP1yh(localObject5[0]);
          if (paramArrayOfDataSaver[paramInt4].radius != 0)
          {
            arrayOfByte1 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaver[paramInt4].radius);
            ((GPNormalKey)localObject4).setP2xl((byte)1);
            ((GPNormalKey)localObject4).setP2xh(arrayOfByte1[1]);
            ((GPNormalKey)localObject4).setP2yl(arrayOfByte1[0]);
            ((GPNormalKey)localObject4).setP2yh((byte)paramArrayOfDataSaver[paramInt4].reverse);
          }
          else
          {
            ((GPNormalKey)localObject4).setP2xl((byte)4);
            ((GPNormalKey)localObject4).setP2xh((byte)paramArrayOfDataSaver[paramInt4].block);
            ((GPNormalKey)localObject4).setP2yl((byte)0);
            ((GPNormalKey)localObject4).setP2yh((byte)paramArrayOfDataSaver[paramInt4].reverse);
          }
          ((ArrayList)localObject3).add(localObject4);
        }
        if ((paramArrayOfDataSaver[paramInt4].property == 2) && (paramArrayOfDataSaver[paramInt4].x != -1) && (paramInt4 > 69) && (paramInt4 < 102) && (CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt4].name) != -1) && (paramInt4 != 86) && (paramInt4 != 87))
        {
          arrayOfByte1 = ChangeDataUtil.intToByteArray(paramInt1 - paramArrayOfDataSaver[paramInt4].y);
          localObject5 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaver[paramInt4].x);
          localObject4 = new GPNormalKey();
          ((GPNormalKey)localObject4).setType((byte)6);
          ((GPNormalKey)localObject4).setCode((byte)CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt4].name));
          ((GPNormalKey)localObject4).setP1xl(arrayOfByte1[1]);
          ((GPNormalKey)localObject4).setP1xh(arrayOfByte1[0]);
          ((GPNormalKey)localObject4).setP1yl(localObject5[1]);
          ((GPNormalKey)localObject4).setP1yh(localObject5[0]);
          if (paramArrayOfDataSaver[paramInt4].radius != 0)
          {
            arrayOfByte1 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaver[paramInt4].radius);
            ((GPNormalKey)localObject4).setP2xl((byte)2);
            ((GPNormalKey)localObject4).setP2xh(arrayOfByte1[1]);
            ((GPNormalKey)localObject4).setP2yl(arrayOfByte1[0]);
            ((GPNormalKey)localObject4).setP2yh((byte)paramArrayOfDataSaver[paramInt4].reverse);
          }
          else
          {
            ((GPNormalKey)localObject4).setP2xl((byte)5);
            ((GPNormalKey)localObject4).setP2xh((byte)paramArrayOfDataSaver[paramInt4].block);
            ((GPNormalKey)localObject4).setP2yl((byte)0);
            ((GPNormalKey)localObject4).setP2yh((byte)paramArrayOfDataSaver[paramInt4].reverse);
          }
          ((ArrayList)localObject3).add(localObject4);
        }
        if ((paramArrayOfDataSaver[paramInt4].property == 3) && (paramArrayOfDataSaver[paramInt4].x != -1) && (paramInt4 > 101) && (paramInt4 < 134) && (CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt4].name) != -1) && (paramInt4 != 118) && (paramInt4 != 119))
        {
          arrayOfByte1 = ChangeDataUtil.intToByteArray(paramInt1 - paramArrayOfDataSaver[paramInt4].y);
          localObject5 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaver[paramInt4].x);
          localObject4 = new GPNormalKey();
          ((GPNormalKey)localObject4).setType((byte)6);
          ((GPNormalKey)localObject4).setCode((byte)CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt4].name));
          ((GPNormalKey)localObject4).setP1xl(arrayOfByte1[1]);
          ((GPNormalKey)localObject4).setP1xh(arrayOfByte1[0]);
          ((GPNormalKey)localObject4).setP1yl(localObject5[1]);
          ((GPNormalKey)localObject4).setP1yh(localObject5[0]);
          if (paramArrayOfDataSaver[paramInt4].radius != 0)
          {
            arrayOfByte1 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaver[paramInt4].radius);
            ((GPNormalKey)localObject4).setP2xl((byte)3);
            ((GPNormalKey)localObject4).setP2xh(arrayOfByte1[1]);
            ((GPNormalKey)localObject4).setP2yl(arrayOfByte1[0]);
            ((GPNormalKey)localObject4).setP2yh((byte)paramArrayOfDataSaver[paramInt4].reverse);
          }
          else
          {
            ((GPNormalKey)localObject4).setP2xl((byte)6);
            ((GPNormalKey)localObject4).setP2xh((byte)paramArrayOfDataSaver[paramInt4].block);
            ((GPNormalKey)localObject4).setP2yl((byte)0);
            ((GPNormalKey)localObject4).setP2yh((byte)paramArrayOfDataSaver[paramInt4].reverse);
          }
          ((ArrayList)localObject3).add(localObject4);
        }
        if ((paramArrayOfDataSaver[paramInt4].property == 4) && (paramArrayOfDataSaver[paramInt4].x != -1) && (paramInt4 > 133) && (paramInt4 < 166) && (CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt4].name) != -1) && (paramInt4 != 150) && (paramInt4 != 151))
        {
          localObject4 = ChangeDataUtil.intToByteArray(paramInt1 - paramArrayOfDataSaver[paramInt4].y);
          arrayOfByte1 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaver[paramInt4].x);
          paramInt5 = paramInt4 + 32;
          localObject5 = ChangeDataUtil.intToByteArray(paramInt1 - paramArrayOfDataSaver[paramInt5].y);
          arrayOfByte2 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaver[paramInt5].x);
          localGPNormalKey = new GPNormalKey();
          localGPNormalKey.setType((byte)2);
          localGPNormalKey.setCode((byte)CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt4].name));
          localGPNormalKey.setP1xl(localObject4[1]);
          localGPNormalKey.setP1xh(localObject4[0]);
          localGPNormalKey.setP1yl(arrayOfByte1[1]);
          localGPNormalKey.setP1yh(arrayOfByte1[0]);
          localGPNormalKey.setP2xl(localObject5[1]);
          localGPNormalKey.setP2xh(localObject5[0]);
          localGPNormalKey.setP2yl(arrayOfByte2[1]);
          localGPNormalKey.setP2yh(arrayOfByte2[0]);
          ((ArrayList)localObject3).add(localGPNormalKey);
        }
        if ((paramArrayOfDataSaver[paramInt4].property == 5) && (paramArrayOfDataSaver[paramInt4].x != -1) && (paramInt4 > 197) && (paramInt4 < 230) && (CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt4].name) != -1) && (paramInt4 != 214) && (paramInt4 != 215))
        {
          localObject4 = ChangeDataUtil.intToByteArray(paramInt1 - paramArrayOfDataSaver[paramInt4].y);
          arrayOfByte1 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaver[paramInt4].x);
          paramInt5 = paramInt4 + 32;
          localObject5 = ChangeDataUtil.intToByteArray(paramInt1 - paramArrayOfDataSaver[paramInt5].y);
          arrayOfByte2 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaver[paramInt5].x);
          localGPNormalKey = new GPNormalKey();
          localGPNormalKey.setType((byte)3);
          localGPNormalKey.setCode((byte)CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt4].name));
          localGPNormalKey.setP1xl(localObject4[1]);
          localGPNormalKey.setP1xh(localObject4[0]);
          localGPNormalKey.setP1yl(arrayOfByte1[1]);
          localGPNormalKey.setP1yh(arrayOfByte1[0]);
          localGPNormalKey.setP2xl(localObject5[1]);
          localGPNormalKey.setP2xh(localObject5[0]);
          localGPNormalKey.setP2yl(arrayOfByte2[1]);
          localGPNormalKey.setP2yh(arrayOfByte2[0]);
          ((ArrayList)localObject3).add(localGPNormalKey);
        }
        if ((paramArrayOfDataSaver[paramInt4].property == 6) && (paramArrayOfDataSaver[paramInt4].x != -1) && (paramInt4 > 261) && (paramInt4 < 294) && (CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt4].name) != -1) && (paramInt4 != 278) && (paramInt4 != 279))
        {
          localObject4 = ChangeDataUtil.intToByteArray(paramInt1 - paramArrayOfDataSaver[paramInt4].y);
          arrayOfByte1 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaver[paramInt4].x);
          localObject5 = new GPNormalKey();
          ((GPNormalKey)localObject5).setType((byte)6);
          ((GPNormalKey)localObject5).setCode((byte)CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt4].name));
          ((GPNormalKey)localObject5).setP1xl(localObject4[1]);
          ((GPNormalKey)localObject5).setP1xh(localObject4[0]);
          ((GPNormalKey)localObject5).setP1yl(arrayOfByte1[1]);
          ((GPNormalKey)localObject5).setP1yh(arrayOfByte1[0]);
          ((GPNormalKey)localObject5).setP2xl((byte)5);
          ((GPNormalKey)localObject5).setP2xh((byte)paramArrayOfDataSaver[paramInt4].block);
          ((GPNormalKey)localObject5).setP2yl((byte)0);
          ((GPNormalKey)localObject5).setP2yh((byte)paramArrayOfDataSaver[paramInt4].reverse);
          ((ArrayList)localObject3).add(localObject5);
        }
        if ((paramArrayOfDataSaver[paramInt4].property == 8) && (paramArrayOfDataSaver[paramInt4].x != -1) && (paramInt4 > 325) && (paramInt4 < 358) && (CommonUtils.getGPKeyCodeByName1(paramArrayOfDataSaver[paramInt4].name) != -1) && (paramInt4 != 342) && (paramInt4 != 343))
        {
          localObject4 = ChangeDataUtil.intToByteArray(paramInt1 - paramArrayOfDataSaver[paramInt4].y);
          arrayOfByte1 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaver[paramInt4].x);
          localObject5 = new GPNormalKey();
          ((GPNormalKey)localObject5).setType((byte)7);
          ((GPNormalKey)localObject5).setCode((byte)CommonUtils.getGPKeyCodeByName1(paramArrayOfDataSaver[paramInt4].name));
          ((GPNormalKey)localObject5).setP1xl(localObject4[1]);
          ((GPNormalKey)localObject5).setP1xh(localObject4[0]);
          ((GPNormalKey)localObject5).setP1yl(arrayOfByte1[1]);
          ((GPNormalKey)localObject5).setP1yh(arrayOfByte1[0]);
          ((GPNormalKey)localObject5).setP2xl((byte)0);
          ((GPNormalKey)localObject5).setP2xh((byte)0);
          ((GPNormalKey)localObject5).setP2yl((byte)0);
          ((GPNormalKey)localObject5).setP2yh((byte)0);
          ((ArrayList)localObject3).add(localObject5);
        }
        if ((paramArrayOfDataSaver[paramInt4].property == 9) && (paramArrayOfDataSaver[paramInt4].x != -1) && (paramInt4 > 357) && (paramInt4 < 390) && (CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt4].name) != -1) && (paramInt3 >= 3))
        {
          localObject4 = new GPNormalKey();
          ((GPNormalKey)localObject4).setType((byte)8);
          ((GPNormalKey)localObject4).setCode((byte)CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt4].name));
          ((GPNormalKey)localObject4).setP1xl((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[paramInt4].y));
          ((GPNormalKey)localObject4).setP1xh((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[paramInt4].x));
          paramInt5 = paramInt4 + 32;
          ((GPNormalKey)localObject4).setP1yl((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[paramInt5].y));
          ((GPNormalKey)localObject4).setP1yh((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[paramInt5].x));
          ((GPNormalKey)localObject4).setP2xl((byte)paramArrayOfDataSaver[paramInt4].radius);
          ((GPNormalKey)localObject4).setP2xh((byte)paramArrayOfDataSaver[paramInt4].block);
          ((GPNormalKey)localObject4).setP2yl((byte)0);
          ((GPNormalKey)localObject4).setP2yh((byte)0);
          if ((((GPNormalKey)localObject4).getP1xl() == ((GPNormalKey)localObject4).getP1yl()) && (((GPNormalKey)localObject4).getP1xh() == ((GPNormalKey)localObject4).getP1yh()))
          {
            ((GPNormalKey)localObject4).setP1yl((byte)(((GPNormalKey)localObject4).getP1xl() + 1));
            ((GPNormalKey)localObject4).setP1yh((byte)(((GPNormalKey)localObject4).getP1xh() + 1));
          }
          ((ArrayList)localObject3).add(localObject4);
        }
        if ((paramArrayOfDataSaver[paramInt4].property == 10) && (paramArrayOfDataSaver[paramInt4].x != -1) && (paramInt4 > 421) && (paramInt4 < 454) && (CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt4].name) != -1) && (paramInt3 >= 4))
        {
          localObject4 = ChangeDataUtil.intToByteArray(paramInt1 - paramArrayOfDataSaver[paramInt4].y);
          arrayOfByte1 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaver[paramInt4].x);
          localObject5 = new GPNormalKey();
          ((GPNormalKey)localObject5).setType((byte)9);
          ((GPNormalKey)localObject5).setCode((byte)CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt4].name));
          ((GPNormalKey)localObject5).setP1xl(localObject4[1]);
          ((GPNormalKey)localObject5).setP1xh(localObject4[0]);
          ((GPNormalKey)localObject5).setP1yl(arrayOfByte1[1]);
          ((GPNormalKey)localObject5).setP1yh(arrayOfByte1[0]);
          ((GPNormalKey)localObject5).setP2xl((byte)paramArrayOfDataSaver[paramInt4].radius);
          ((GPNormalKey)localObject5).setP2xh((byte)0);
          ((GPNormalKey)localObject5).setP2yl((byte)0);
          ((GPNormalKey)localObject5).setP2yh((byte)0);
          ((ArrayList)localObject3).add(localObject5);
        }
        if ((paramArrayOfDataSaver[paramInt4].property == 11) && (paramArrayOfDataSaver[paramInt4].x != -1) && (paramInt4 >= 22) && (paramInt4 <= 26) && (CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt4].name) != -1) && (paramInt3 >= 5))
        {
          localObject4 = new GPNormalKey();
          ((GPNormalKey)localObject4).setType((byte)10);
          ((GPNormalKey)localObject4).setCode((byte)CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt4].name));
          ((GPNormalKey)localObject4).setP1xl((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[paramInt4].y));
          ((GPNormalKey)localObject4).setP1xh((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[paramInt4].x));
          ((GPNormalKey)localObject4).setP1yl((byte)paramArrayOfDataSaver[paramInt4].radius);
          ((GPNormalKey)localObject4).setP1yh((byte)0);
          ((GPNormalKey)localObject4).setP2xl((byte)0);
          ((GPNormalKey)localObject4).setP2xh((byte)0);
          ((GPNormalKey)localObject4).setP2yl((byte)0);
          ((GPNormalKey)localObject4).setP2yh((byte)0);
          ((ArrayList)localObject3).add(localObject4);
        }
        if ((paramArrayOfDataSaver[paramInt4].property == 12) && (paramArrayOfDataSaver[paramInt4].x != -1) && (paramInt4 >= 22) && (paramInt4 <= 26) && (CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt4].name) != -1) && (paramInt3 >= 5))
        {
          localObject4 = new GPNormalKey();
          arrayOfByte1 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaver[paramInt4].radius);
          ((GPNormalKey)localObject4).setType((byte)11);
          ((GPNormalKey)localObject4).setCode((byte)CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt4].name));
          ((GPNormalKey)localObject4).setP1xl((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[paramInt4].y));
          ((GPNormalKey)localObject4).setP1xh((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[paramInt4].x));
          ((GPNormalKey)localObject4).setP1yl(arrayOfByte1[1]);
          ((GPNormalKey)localObject4).setP1yh(arrayOfByte1[0]);
          ((GPNormalKey)localObject4).setP2xl((byte)paramArrayOfDataSaver[paramInt4].block);
          ((GPNormalKey)localObject4).setP2xh((byte)paramArrayOfDataSaver[paramInt4].reverse);
          ((GPNormalKey)localObject4).setP2yl((byte)0);
          ((GPNormalKey)localObject4).setP2yh((byte)0);
          ((ArrayList)localObject3).add(localObject4);
        }
        if ((paramArrayOfDataSaver[paramInt4].property == 14) && (paramArrayOfDataSaver[paramInt4].x != -1) && (paramInt4 > 933) && (CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt4].name) != -1))
        {
          localObject4 = new GPNormalKey();
          ((GPNormalKey)localObject4).setType((byte)15);
          ((GPNormalKey)localObject4).setCode((byte)CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt4].name));
          ((GPNormalKey)localObject4).setP1xl((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[paramInt4].y));
          ((GPNormalKey)localObject4).setP1xh((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[paramInt4].x));
          ((GPNormalKey)localObject4).setP1yl((byte)paramArrayOfDataSaver[paramInt4].radius);
          ((GPNormalKey)localObject4).setP1yh((byte)paramArrayOfDataSaver[paramInt4].block);
          if ((CommonUtils.isStringValid(paramArrayOfDataSaver[paramInt4].view_joystick)) && (!paramArrayOfDataSaver[paramInt4].view_joystick.equalsIgnoreCase("-1")))
            ((GPNormalKey)localObject4).setP2xl((byte)CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt4].view_joystick));
          else
            ((GPNormalKey)localObject4).setP2xl((byte)0);
          ((GPNormalKey)localObject4).setP2xh((byte)paramArrayOfDataSaver[paramInt4].turb_speed);
          ((GPNormalKey)localObject4).setP2yl((byte)0);
          ((GPNormalKey)localObject4).setP2yh((byte)0);
          ((ArrayList)localObject3).add(localObject4);
        }
        if ((paramArrayOfDataSaver[paramInt4].property == 13) && (paramArrayOfDataSaver[paramInt4].x != -1) && (paramInt4 > 453) && (paramInt4 < 486) && (CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt4].name) != -1))
        {
          localObject4 = new StringBuilder();
          ((StringBuilder)localObject4).append("----i = ");
          ((StringBuilder)localObject4).append(paramInt4);
          MyLog.e("my_tag", ((StringBuilder)localObject4).toString());
          localObject4 = new MacroKey();
          ((MacroKey)localObject4).setType((byte)14);
          ((MacroKey)localObject4).setKeyCode((byte)CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt4].name));
          ((MacroKey)localObject4).setPoint0X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[paramInt4].y));
          ((MacroKey)localObject4).setPoint0Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[paramInt4].x));
          ((MacroKey)localObject4).setPoint0MacroTime((byte)paramArrayOfDataSaver[paramInt4].radius);
          ((MacroKey)localObject4).setPoint0PauseTime((byte)paramArrayOfDataSaver[paramInt4].block);
          paramInt5 = paramInt4 + 32;
          ((MacroKey)localObject4).setPoint1X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[paramInt5].y));
          ((MacroKey)localObject4).setPoint1Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[paramInt5].x));
          ((MacroKey)localObject4).setPoint1MacroTime((byte)paramArrayOfDataSaver[paramInt5].radius);
          ((MacroKey)localObject4).setPoint1PauseTime((byte)paramArrayOfDataSaver[paramInt5].block);
          paramInt5 = paramInt4 + 64;
          if ((paramArrayOfDataSaver[paramInt5].x != -1) && (paramArrayOfDataSaver[paramInt5].y != -1) && (paramArrayOfDataSaver[paramInt5].radius != -1) && (paramArrayOfDataSaver[paramInt5].block != -1))
          {
            ((MacroKey)localObject4).setPoint2X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[paramInt5].y));
            ((MacroKey)localObject4).setPoint2Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[paramInt5].x));
            ((MacroKey)localObject4).setPoint2MacroTime((byte)paramArrayOfDataSaver[paramInt5].radius);
            ((MacroKey)localObject4).setPoint2PauseTime((byte)paramArrayOfDataSaver[paramInt5].block);
          }
          else
          {
            ((MacroKey)localObject4).setPoint2X((byte)0);
            ((MacroKey)localObject4).setPoint2Y((byte)0);
            ((MacroKey)localObject4).setPoint2MacroTime((byte)0);
            ((MacroKey)localObject4).setPoint2PauseTime((byte)0);
          }
          paramInt5 = paramInt4 + 96;
          if ((paramArrayOfDataSaver[paramInt5].x != -1) && (paramArrayOfDataSaver[paramInt5].y != -1) && (paramArrayOfDataSaver[paramInt5].radius != -1) && (paramArrayOfDataSaver[paramInt5].block != -1))
          {
            ((MacroKey)localObject4).setPoint3X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[paramInt5].y));
            ((MacroKey)localObject4).setPoint3Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[paramInt5].x));
            ((MacroKey)localObject4).setPoint3MacroTime((byte)paramArrayOfDataSaver[paramInt5].radius);
            ((MacroKey)localObject4).setPoint3PauseTime((byte)paramArrayOfDataSaver[paramInt5].block);
          }
          else
          {
            ((MacroKey)localObject4).setPoint3X((byte)0);
            ((MacroKey)localObject4).setPoint3Y((byte)0);
            ((MacroKey)localObject4).setPoint3MacroTime((byte)0);
            ((MacroKey)localObject4).setPoint3PauseTime((byte)0);
          }
          paramInt5 = paramInt4 + 128;
          if ((paramArrayOfDataSaver[paramInt5].x != -1) && (paramArrayOfDataSaver[paramInt5].y != -1) && (paramArrayOfDataSaver[paramInt5].radius != -1) && (paramArrayOfDataSaver[paramInt5].block != -1))
          {
            ((MacroKey)localObject4).setPoint4X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[paramInt5].y));
            ((MacroKey)localObject4).setPoint4Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[paramInt5].x));
            ((MacroKey)localObject4).setPoint4MacroTime((byte)paramArrayOfDataSaver[paramInt5].radius);
            ((MacroKey)localObject4).setPoint4PauseTime((byte)paramArrayOfDataSaver[paramInt5].block);
          }
          else
          {
            ((MacroKey)localObject4).setPoint4X((byte)0);
            ((MacroKey)localObject4).setPoint4Y((byte)0);
            ((MacroKey)localObject4).setPoint4MacroTime((byte)0);
            ((MacroKey)localObject4).setPoint4PauseTime((byte)0);
          }
          paramInt5 = paramInt4 + 160;
          if ((paramArrayOfDataSaver[paramInt5].x != -1) && (paramArrayOfDataSaver[paramInt5].y != -1) && (paramArrayOfDataSaver[paramInt5].radius != -1) && (paramArrayOfDataSaver[paramInt5].block != -1))
          {
            ((MacroKey)localObject4).setPoint5X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[paramInt5].y));
            ((MacroKey)localObject4).setPoint5Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[paramInt5].x));
            ((MacroKey)localObject4).setPoint5MacroTime((byte)paramArrayOfDataSaver[paramInt5].radius);
            ((MacroKey)localObject4).setPoint5PauseTime((byte)paramArrayOfDataSaver[paramInt5].block);
          }
          else
          {
            ((MacroKey)localObject4).setPoint5X((byte)0);
            ((MacroKey)localObject4).setPoint5Y((byte)0);
            ((MacroKey)localObject4).setPoint5MacroTime((byte)0);
            ((MacroKey)localObject4).setPoint5PauseTime((byte)0);
          }
          paramInt5 = paramInt4 + 192;
          if ((paramArrayOfDataSaver[paramInt5].x != -1) && (paramArrayOfDataSaver[paramInt5].y != -1) && (paramArrayOfDataSaver[paramInt5].radius != -1) && (paramArrayOfDataSaver[paramInt5].block != -1))
          {
            ((MacroKey)localObject4).setPoint6X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[paramInt5].y));
            ((MacroKey)localObject4).setPoint6Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[paramInt5].x));
            ((MacroKey)localObject4).setPoint6MacroTime((byte)paramArrayOfDataSaver[paramInt5].radius);
            ((MacroKey)localObject4).setPoint6PauseTime((byte)paramArrayOfDataSaver[paramInt5].block);
          }
          else
          {
            ((MacroKey)localObject4).setPoint6X((byte)0);
            ((MacroKey)localObject4).setPoint6Y((byte)0);
            ((MacroKey)localObject4).setPoint6MacroTime((byte)0);
            ((MacroKey)localObject4).setPoint6PauseTime((byte)0);
          }
          paramInt5 = paramInt4 + 224;
          if ((paramArrayOfDataSaver[paramInt5].x != -1) && (paramArrayOfDataSaver[paramInt5].y != -1) && (paramArrayOfDataSaver[paramInt5].radius != -1) && (paramArrayOfDataSaver[paramInt5].block != -1))
          {
            ((MacroKey)localObject4).setPoint7X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[paramInt5].y));
            ((MacroKey)localObject4).setPoint7Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[paramInt5].x));
            ((MacroKey)localObject4).setPoint7MacroTime((byte)paramArrayOfDataSaver[paramInt5].radius);
            ((MacroKey)localObject4).setPoint7PauseTime((byte)paramArrayOfDataSaver[paramInt5].block);
          }
          else
          {
            ((MacroKey)localObject4).setPoint7X((byte)0);
            ((MacroKey)localObject4).setPoint7Y((byte)0);
            ((MacroKey)localObject4).setPoint7MacroTime((byte)0);
            ((MacroKey)localObject4).setPoint7PauseTime((byte)0);
          }
          paramInt5 = paramInt4 + 256;
          if ((paramArrayOfDataSaver[paramInt5].x != -1) && (paramArrayOfDataSaver[paramInt5].y != -1) && (paramArrayOfDataSaver[paramInt5].radius != -1) && (paramArrayOfDataSaver[paramInt5].block != -1))
          {
            ((MacroKey)localObject4).setPoint8X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[paramInt5].y));
            ((MacroKey)localObject4).setPoint8Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[paramInt5].x));
            ((MacroKey)localObject4).setPoint8MacroTime((byte)paramArrayOfDataSaver[paramInt5].radius);
            ((MacroKey)localObject4).setPoint8PauseTime((byte)paramArrayOfDataSaver[paramInt5].block);
          }
          else
          {
            ((MacroKey)localObject4).setPoint8X((byte)0);
            ((MacroKey)localObject4).setPoint8Y((byte)0);
            ((MacroKey)localObject4).setPoint8MacroTime((byte)0);
            ((MacroKey)localObject4).setPoint8PauseTime((byte)0);
          }
          paramInt5 = paramInt4 + 288;
          if ((paramArrayOfDataSaver[paramInt5].x != -1) && (paramArrayOfDataSaver[paramInt5].y != -1) && (paramArrayOfDataSaver[paramInt5].radius != -1) && (paramArrayOfDataSaver[paramInt5].block != -1))
          {
            ((MacroKey)localObject4).setPoint9X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[paramInt5].y));
            ((MacroKey)localObject4).setPoint9Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[paramInt5].x));
            ((MacroKey)localObject4).setPoint9MacroTime((byte)paramArrayOfDataSaver[paramInt5].radius);
            ((MacroKey)localObject4).setPoint9PauseTime((byte)paramArrayOfDataSaver[paramInt5].block);
          }
          else
          {
            ((MacroKey)localObject4).setPoint9X((byte)0);
            ((MacroKey)localObject4).setPoint9Y((byte)0);
            ((MacroKey)localObject4).setPoint9MacroTime((byte)0);
            ((MacroKey)localObject4).setPoint9PauseTime((byte)0);
          }
          paramInt5 = paramInt4 + 320;
          if ((paramArrayOfDataSaver[paramInt5].x != -1) && (paramArrayOfDataSaver[paramInt5].y != -1) && (paramArrayOfDataSaver[paramInt5].radius != -1) && (paramArrayOfDataSaver[paramInt5].block != -1))
          {
            ((MacroKey)localObject4).setPoint10X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[paramInt5].y));
            ((MacroKey)localObject4).setPoint10Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[paramInt5].x));
            ((MacroKey)localObject4).setPoint10MacroTime((byte)paramArrayOfDataSaver[paramInt5].radius);
            ((MacroKey)localObject4).setPoint10PauseTime((byte)paramArrayOfDataSaver[paramInt5].block);
          }
          else
          {
            ((MacroKey)localObject4).setPoint10X((byte)0);
            ((MacroKey)localObject4).setPoint10Y((byte)0);
            ((MacroKey)localObject4).setPoint10MacroTime((byte)0);
            ((MacroKey)localObject4).setPoint10PauseTime((byte)0);
          }
          paramInt5 = paramInt4 + 352;
          if ((paramArrayOfDataSaver[paramInt5].x != -1) && (paramArrayOfDataSaver[paramInt5].y != -1) && (paramArrayOfDataSaver[paramInt5].radius != -1) && (paramArrayOfDataSaver[paramInt5].block != -1))
          {
            ((MacroKey)localObject4).setPoint11X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[paramInt5].y));
            ((MacroKey)localObject4).setPoint11Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[paramInt5].x));
            ((MacroKey)localObject4).setPoint11MacroTime((byte)paramArrayOfDataSaver[paramInt5].radius);
            ((MacroKey)localObject4).setPoint11PauseTime((byte)paramArrayOfDataSaver[paramInt5].block);
          }
          else
          {
            ((MacroKey)localObject4).setPoint11X((byte)0);
            ((MacroKey)localObject4).setPoint11Y((byte)0);
            ((MacroKey)localObject4).setPoint11MacroTime((byte)0);
            ((MacroKey)localObject4).setPoint11PauseTime((byte)0);
          }
          paramInt5 = paramInt4 + 384;
          if ((paramArrayOfDataSaver[paramInt5].x != -1) && (paramArrayOfDataSaver[paramInt5].y != -1) && (paramArrayOfDataSaver[paramInt5].radius != -1) && (paramArrayOfDataSaver[paramInt5].block != -1))
          {
            ((MacroKey)localObject4).setPoint12X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[paramInt5].y));
            ((MacroKey)localObject4).setPoint12Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[paramInt5].x));
            ((MacroKey)localObject4).setPoint12MacroTime((byte)paramArrayOfDataSaver[paramInt5].radius);
            ((MacroKey)localObject4).setPoint12PauseTime((byte)paramArrayOfDataSaver[paramInt5].block);
          }
          else
          {
            ((MacroKey)localObject4).setPoint12X((byte)0);
            ((MacroKey)localObject4).setPoint12Y((byte)0);
            ((MacroKey)localObject4).setPoint12MacroTime((byte)0);
            ((MacroKey)localObject4).setPoint12PauseTime((byte)0);
          }
          paramInt5 = paramInt4 + 416;
          if ((paramArrayOfDataSaver[paramInt5].x != -1) && (paramArrayOfDataSaver[paramInt5].y != -1) && (paramArrayOfDataSaver[paramInt5].radius != -1) && (paramArrayOfDataSaver[paramInt5].block != -1))
          {
            ((MacroKey)localObject4).setPoint13X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[paramInt5].y));
            ((MacroKey)localObject4).setPoint13Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[paramInt5].x));
            ((MacroKey)localObject4).setPoint13MacroTime((byte)paramArrayOfDataSaver[paramInt5].radius);
            ((MacroKey)localObject4).setPoint13PauseTime((byte)paramArrayOfDataSaver[paramInt5].block);
          }
          else
          {
            ((MacroKey)localObject4).setPoint13X((byte)0);
            ((MacroKey)localObject4).setPoint13Y((byte)0);
            ((MacroKey)localObject4).setPoint13MacroTime((byte)0);
            ((MacroKey)localObject4).setPoint13PauseTime((byte)0);
          }
          paramInt5 = paramInt4 + 448;
          if ((paramArrayOfDataSaver[paramInt5].x != -1) && (paramArrayOfDataSaver[paramInt5].y != -1) && (paramArrayOfDataSaver[paramInt5].radius != -1) && (paramArrayOfDataSaver[paramInt5].block != -1))
          {
            ((MacroKey)localObject4).setPoint14X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[paramInt5].y));
            ((MacroKey)localObject4).setPoint14Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[paramInt5].x));
            ((MacroKey)localObject4).setPoint14MacroTime((byte)paramArrayOfDataSaver[paramInt5].radius);
            ((MacroKey)localObject4).setPoint14PauseTime((byte)paramArrayOfDataSaver[paramInt5].block);
          }
          else
          {
            ((MacroKey)localObject4).setPoint14X((byte)0);
            ((MacroKey)localObject4).setPoint14Y((byte)0);
            ((MacroKey)localObject4).setPoint14MacroTime((byte)0);
            ((MacroKey)localObject4).setPoint14PauseTime((byte)0);
          }
          ((ArrayList)localObject2).add(localObject4);
        }
        paramInt4 += 1;
      }
      paramArrayOfDataSaver = new StringBuilder();
      paramArrayOfDataSaver.append("------ key size = ");
      paramArrayOfDataSaver.append(((ArrayList)localObject3).size());
      MyLog.i("my_tag", paramArrayOfDataSaver.toString());
      paramInt1 = 0;
      while (paramInt1 < ((ArrayList)localObject3).size())
      {
        paramInt2 = paramInt1 * 10;
        paramInt3 = paramInt2 + 19;
        if (paramInt3 < localObject1.length)
        {
          localObject1[(paramInt2 + 10)] = ((GPNormalKey)((ArrayList)localObject3).get(paramInt1)).getType();
          localObject1[(paramInt2 + 11)] = ((GPNormalKey)((ArrayList)localObject3).get(paramInt1)).getCode();
          localObject1[(paramInt2 + 12)] = ((GPNormalKey)((ArrayList)localObject3).get(paramInt1)).getP1xl();
          localObject1[(paramInt2 + 13)] = ((GPNormalKey)((ArrayList)localObject3).get(paramInt1)).getP1xh();
          localObject1[(paramInt2 + 14)] = ((GPNormalKey)((ArrayList)localObject3).get(paramInt1)).getP1yl();
          localObject1[(paramInt2 + 15)] = ((GPNormalKey)((ArrayList)localObject3).get(paramInt1)).getP1yh();
          localObject1[(paramInt2 + 16)] = ((GPNormalKey)((ArrayList)localObject3).get(paramInt1)).getP2xl();
          localObject1[(paramInt2 + 17)] = ((GPNormalKey)((ArrayList)localObject3).get(paramInt1)).getP2xh();
          localObject1[(paramInt2 + 18)] = ((GPNormalKey)((ArrayList)localObject3).get(paramInt1)).getP2yl();
          localObject1[paramInt3] = ((GPNormalKey)((ArrayList)localObject3).get(paramInt1)).getP2yh();
        }
        paramInt1 += 1;
      }
      paramInt1 = 0;
      while (paramInt1 < ((ArrayList)localObject2).size())
      {
        paramArrayOfDataSaver = MacroKeyUtils.AnalysisMacroKey((MacroKey)((ArrayList)localObject2).get(paramInt1));
        if (i + 9 + paramArrayOfDataSaver.length < localObject1.length)
          System.arraycopy(paramArrayOfDataSaver, 0, localObject1, j + paramArrayOfDataSaver.length * paramInt1, paramArrayOfDataSaver.length);
        paramInt1 += 1;
      }
      paramArrayOfDataSaver = new byte[localObject1.length - 2];
      System.arraycopy(localObject1, 2, paramArrayOfDataSaver, 0, localObject1.length - 2);
      paramInt1 = CommonUtils.CRC_GetModbus16(paramArrayOfDataSaver, localObject1.length - 2);
      paramArrayOfDataSaver = new StringBuilder();
      paramArrayOfDataSaver.append("------save checkCode = ");
      paramArrayOfDataSaver.append(paramInt1);
      MyLog.i("crc_tag", paramArrayOfDataSaver.toString());
      paramArrayOfDataSaver = ChangeDataUtil.intToByteArray(paramInt1);
      localObject1[0] = paramArrayOfDataSaver[1];
      localObject1[1] = paramArrayOfDataSaver[0];
      return localObject1;
    }
    paramInt5 *= (paramInt6 * 4 + 2);
    localObject1 = new byte[paramInt5 + 264];
    localObject1[2] = 2;
    Object localObject4 = ChangeDataUtil.intToByteArray(paramInt3);
    localObject1[3] = localObject4[1];
    localObject1[4] = localObject4[0];
    localObject1[5] = localObject2[1];
    localObject1[6] = localObject2[0];
    localObject1[7] = localObject3[1];
    localObject1[8] = localObject3[0];
    localObject3 = new ArrayList();
    localObject2 = new ArrayList();
    paramInt6 = 0;
    while (paramInt6 < paramArrayOfDataSaver.length)
    {
      if (paramInt6 == 16)
        if ((paramArrayOfDataSaver[paramInt6].name.equalsIgnoreCase("jl")) && (paramArrayOfDataSaver[paramInt6].x != -1) && (paramInt6 < 38))
        {
          localObject4 = ChangeDataUtil.intToByteArray(paramInt1 - paramArrayOfDataSaver[paramInt6].y);
          arrayOfByte1 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaver[paramInt6].x);
          if (paramArrayOfDataSaver[paramInt6].radius != 0)
          {
            localObject5 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaver[paramInt6].radius);
            localObject1[9] = 0;
            localObject1[10] = localObject4[1];
            localObject1[11] = localObject4[0];
            localObject1[12] = arrayOfByte1[1];
            localObject1[13] = arrayOfByte1[0];
            localObject1[14] = localObject5[1];
            localObject1[15] = localObject5[0];
          }
          else
          {
            localObject1[9] = 1;
            localObject1[10] = localObject4[1];
            localObject1[11] = localObject4[0];
            localObject1[12] = arrayOfByte1[1];
            localObject1[13] = arrayOfByte1[0];
            localObject1[14] = ((byte)paramArrayOfDataSaver[paramInt6].block);
            localObject1[15] = 0;
          }
        }
        else
        {
          localObject1[9] = 2;
          localObject1[10] = 0;
          localObject1[11] = 0;
          localObject1[12] = 0;
          localObject1[13] = 0;
          localObject1[14] = 0;
          localObject1[15] = 0;
        }
      if (paramInt6 == 17)
        if ((paramArrayOfDataSaver[paramInt6].name.equalsIgnoreCase("jr")) && (paramArrayOfDataSaver[paramInt6].x != -1) && (paramInt6 < 38))
        {
          localObject4 = ChangeDataUtil.intToByteArray(paramInt1 - paramArrayOfDataSaver[paramInt6].y);
          arrayOfByte1 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaver[paramInt6].x);
          if (paramArrayOfDataSaver[paramInt6].radius != 0)
          {
            localObject5 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaver[paramInt6].radius);
            localObject1[16] = 0;
            localObject1[17] = localObject4[1];
            localObject1[18] = localObject4[0];
            localObject1[19] = arrayOfByte1[1];
            localObject1[20] = arrayOfByte1[0];
            localObject1[21] = localObject5[1];
            localObject1[22] = localObject5[0];
          }
          else
          {
            localObject1[16] = 1;
            localObject1[17] = localObject4[1];
            localObject1[18] = localObject4[0];
            localObject1[19] = arrayOfByte1[1];
            localObject1[20] = arrayOfByte1[0];
            localObject1[21] = ((byte)paramArrayOfDataSaver[paramInt6].block);
            localObject1[22] = 0;
          }
        }
        else
        {
          localObject1[16] = 2;
          localObject1[17] = 0;
          localObject1[18] = 0;
          localObject1[19] = 0;
          localObject1[20] = 0;
          localObject1[21] = 0;
          localObject1[22] = 0;
        }
      if ((!paramArrayOfDataSaver[paramInt6].name.equals("-1")) && (paramArrayOfDataSaver[paramInt6].x != -1) && (paramInt6 > 294) && (paramInt6 < 326) && (paramInt6 != 310) && (paramInt6 != 311) && (paramArrayOfDataSaver[paramInt6].property == 7) && (CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt6].name) != -1))
        localObject1[23] = ((byte)CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt6].name));
      if ((!paramArrayOfDataSaver[paramInt6].name.equals("-1")) && (paramArrayOfDataSaver[paramInt6].x != -1) && (paramInt6 < 38) && (CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt6].name) != -1) && (paramInt6 != 16) && (paramInt6 != 17))
      {
        localObject4 = ChangeDataUtil.intToByteArray(paramInt1 - paramArrayOfDataSaver[paramInt6].y);
        arrayOfByte1 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaver[paramInt6].x);
        localObject5 = new GPNormalKey();
        ((GPNormalKey)localObject5).setType((byte)1);
        ((GPNormalKey)localObject5).setCode((byte)CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt6].name));
        ((GPNormalKey)localObject5).setP1xl(localObject4[1]);
        ((GPNormalKey)localObject5).setP1xh(localObject4[0]);
        ((GPNormalKey)localObject5).setP1yl(arrayOfByte1[1]);
        ((GPNormalKey)localObject5).setP1yh(arrayOfByte1[0]);
        ((GPNormalKey)localObject5).setP2xl((byte)0);
        ((GPNormalKey)localObject5).setP2xh((byte)0);
        ((GPNormalKey)localObject5).setP2yl((byte)0);
        ((GPNormalKey)localObject5).setP2yh((byte)0);
        ((ArrayList)localObject3).add(localObject5);
      }
      if ((paramArrayOfDataSaver[paramInt6].property == 1) && (paramArrayOfDataSaver[paramInt6].x != -1) && (paramInt6 > 37) && (paramInt6 < 70) && (CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt6].name) != -1) && (paramInt6 != 54) && (paramInt6 != 55))
      {
        arrayOfByte1 = ChangeDataUtil.intToByteArray(paramInt1 - paramArrayOfDataSaver[paramInt6].y);
        localObject5 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaver[paramInt6].x);
        localObject4 = new GPNormalKey();
        ((GPNormalKey)localObject4).setType((byte)6);
        ((GPNormalKey)localObject4).setCode((byte)CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt6].name));
        ((GPNormalKey)localObject4).setP1xl(arrayOfByte1[1]);
        ((GPNormalKey)localObject4).setP1xh(arrayOfByte1[0]);
        ((GPNormalKey)localObject4).setP1yl(localObject5[1]);
        ((GPNormalKey)localObject4).setP1yh(localObject5[0]);
        if (paramArrayOfDataSaver[paramInt6].radius != 0)
        {
          arrayOfByte1 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaver[paramInt6].radius);
          ((GPNormalKey)localObject4).setP2xl((byte)1);
          ((GPNormalKey)localObject4).setP2xh(arrayOfByte1[1]);
          ((GPNormalKey)localObject4).setP2yl(arrayOfByte1[0]);
          ((GPNormalKey)localObject4).setP2yh((byte)paramArrayOfDataSaver[paramInt6].reverse);
        }
        else
        {
          ((GPNormalKey)localObject4).setP2xl((byte)4);
          ((GPNormalKey)localObject4).setP2xh((byte)paramArrayOfDataSaver[paramInt6].block);
          ((GPNormalKey)localObject4).setP2yl((byte)0);
          ((GPNormalKey)localObject4).setP2yh((byte)paramArrayOfDataSaver[paramInt6].reverse);
        }
        ((ArrayList)localObject3).add(localObject4);
      }
      if ((paramArrayOfDataSaver[paramInt6].property == 2) && (paramArrayOfDataSaver[paramInt6].x != -1) && (paramInt6 > 69) && (paramInt6 < 102) && (CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt6].name) != -1) && (paramInt6 != 86) && (paramInt6 != 87))
      {
        arrayOfByte1 = ChangeDataUtil.intToByteArray(paramInt1 - paramArrayOfDataSaver[paramInt6].y);
        localObject5 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaver[paramInt6].x);
        localObject4 = new GPNormalKey();
        ((GPNormalKey)localObject4).setType((byte)6);
        ((GPNormalKey)localObject4).setCode((byte)CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt6].name));
        ((GPNormalKey)localObject4).setP1xl(arrayOfByte1[1]);
        ((GPNormalKey)localObject4).setP1xh(arrayOfByte1[0]);
        ((GPNormalKey)localObject4).setP1yl(localObject5[1]);
        ((GPNormalKey)localObject4).setP1yh(localObject5[0]);
        if (paramArrayOfDataSaver[paramInt6].radius != 0)
        {
          arrayOfByte1 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaver[paramInt6].radius);
          ((GPNormalKey)localObject4).setP2xl((byte)2);
          ((GPNormalKey)localObject4).setP2xh(arrayOfByte1[1]);
          ((GPNormalKey)localObject4).setP2yl(arrayOfByte1[0]);
          ((GPNormalKey)localObject4).setP2yh((byte)paramArrayOfDataSaver[paramInt6].reverse);
        }
        else
        {
          ((GPNormalKey)localObject4).setP2xl((byte)5);
          ((GPNormalKey)localObject4).setP2xh((byte)paramArrayOfDataSaver[paramInt6].block);
          ((GPNormalKey)localObject4).setP2yl((byte)0);
          ((GPNormalKey)localObject4).setP2yh((byte)paramArrayOfDataSaver[paramInt6].reverse);
        }
        ((ArrayList)localObject3).add(localObject4);
      }
      if ((paramArrayOfDataSaver[paramInt6].property == 3) && (paramArrayOfDataSaver[paramInt6].x != -1) && (paramInt6 > 101) && (paramInt6 < 134) && (CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt6].name) != -1) && (paramInt6 != 118) && (paramInt6 != 119))
      {
        arrayOfByte1 = ChangeDataUtil.intToByteArray(paramInt1 - paramArrayOfDataSaver[paramInt6].y);
        localObject5 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaver[paramInt6].x);
        localObject4 = new GPNormalKey();
        ((GPNormalKey)localObject4).setType((byte)6);
        ((GPNormalKey)localObject4).setCode((byte)CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt6].name));
        ((GPNormalKey)localObject4).setP1xl(arrayOfByte1[1]);
        ((GPNormalKey)localObject4).setP1xh(arrayOfByte1[0]);
        ((GPNormalKey)localObject4).setP1yl(localObject5[1]);
        ((GPNormalKey)localObject4).setP1yh(localObject5[0]);
        if (paramArrayOfDataSaver[paramInt6].radius != 0)
        {
          arrayOfByte1 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaver[paramInt6].radius);
          ((GPNormalKey)localObject4).setP2xl((byte)3);
          ((GPNormalKey)localObject4).setP2xh(arrayOfByte1[1]);
          ((GPNormalKey)localObject4).setP2yl(arrayOfByte1[0]);
          ((GPNormalKey)localObject4).setP2yh((byte)paramArrayOfDataSaver[paramInt6].reverse);
        }
        else
        {
          ((GPNormalKey)localObject4).setP2xl((byte)6);
          ((GPNormalKey)localObject4).setP2xh((byte)paramArrayOfDataSaver[paramInt6].block);
          ((GPNormalKey)localObject4).setP2yl((byte)0);
          ((GPNormalKey)localObject4).setP2yh((byte)paramArrayOfDataSaver[paramInt6].reverse);
        }
        ((ArrayList)localObject3).add(localObject4);
      }
      if ((paramArrayOfDataSaver[paramInt6].property == 4) && (paramArrayOfDataSaver[paramInt6].x != -1) && (paramInt6 > 133) && (paramInt6 < 166) && (CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt6].name) != -1) && (paramInt6 != 150) && (paramInt6 != 151))
      {
        localObject4 = ChangeDataUtil.intToByteArray(paramInt1 - paramArrayOfDataSaver[paramInt6].y);
        arrayOfByte1 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaver[paramInt6].x);
        i = paramInt6 + 32;
        localObject5 = ChangeDataUtil.intToByteArray(paramInt1 - paramArrayOfDataSaver[i].y);
        arrayOfByte2 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaver[i].x);
        localGPNormalKey = new GPNormalKey();
        localGPNormalKey.setType((byte)2);
        localGPNormalKey.setCode((byte)CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt6].name));
        localGPNormalKey.setP1xl(localObject4[1]);
        localGPNormalKey.setP1xh(localObject4[0]);
        localGPNormalKey.setP1yl(arrayOfByte1[1]);
        localGPNormalKey.setP1yh(arrayOfByte1[0]);
        localGPNormalKey.setP2xl(localObject5[1]);
        localGPNormalKey.setP2xh(localObject5[0]);
        localGPNormalKey.setP2yl(arrayOfByte2[1]);
        localGPNormalKey.setP2yh(arrayOfByte2[0]);
        ((ArrayList)localObject3).add(localGPNormalKey);
      }
      if ((paramArrayOfDataSaver[paramInt6].property == 5) && (paramArrayOfDataSaver[paramInt6].x != -1) && (paramInt6 > 197) && (paramInt6 < 230) && (CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt6].name) != -1) && (paramInt6 != 214) && (paramInt6 != 215))
      {
        localObject4 = ChangeDataUtil.intToByteArray(paramInt1 - paramArrayOfDataSaver[paramInt6].y);
        arrayOfByte1 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaver[paramInt6].x);
        i = paramInt6 + 32;
        localObject5 = ChangeDataUtil.intToByteArray(paramInt1 - paramArrayOfDataSaver[i].y);
        arrayOfByte2 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaver[i].x);
        localGPNormalKey = new GPNormalKey();
        localGPNormalKey.setType((byte)3);
        localGPNormalKey.setCode((byte)CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt6].name));
        localGPNormalKey.setP1xl(localObject4[1]);
        localGPNormalKey.setP1xh(localObject4[0]);
        localGPNormalKey.setP1yl(arrayOfByte1[1]);
        localGPNormalKey.setP1yh(arrayOfByte1[0]);
        localGPNormalKey.setP2xl(localObject5[1]);
        localGPNormalKey.setP2xh(localObject5[0]);
        localGPNormalKey.setP2yl(arrayOfByte2[1]);
        localGPNormalKey.setP2yh(arrayOfByte2[0]);
        ((ArrayList)localObject3).add(localGPNormalKey);
      }
      if ((paramArrayOfDataSaver[paramInt6].property == 6) && (paramArrayOfDataSaver[paramInt6].x != -1) && (paramInt6 > 261) && (paramInt6 < 294) && (CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt6].name) != -1) && (paramInt6 != 278) && (paramInt6 != 279))
      {
        localObject4 = ChangeDataUtil.intToByteArray(paramInt1 - paramArrayOfDataSaver[paramInt6].y);
        arrayOfByte1 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaver[paramInt6].x);
        localObject5 = new GPNormalKey();
        ((GPNormalKey)localObject5).setType((byte)6);
        ((GPNormalKey)localObject5).setCode((byte)CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt6].name));
        ((GPNormalKey)localObject5).setP1xl(localObject4[1]);
        ((GPNormalKey)localObject5).setP1xh(localObject4[0]);
        ((GPNormalKey)localObject5).setP1yl(arrayOfByte1[1]);
        ((GPNormalKey)localObject5).setP1yh(arrayOfByte1[0]);
        ((GPNormalKey)localObject5).setP2xl((byte)5);
        ((GPNormalKey)localObject5).setP2xh((byte)paramArrayOfDataSaver[paramInt6].block);
        ((GPNormalKey)localObject5).setP2yl((byte)0);
        ((GPNormalKey)localObject5).setP2yh((byte)paramArrayOfDataSaver[paramInt6].reverse);
        ((ArrayList)localObject3).add(localObject5);
      }
      if ((paramArrayOfDataSaver[paramInt6].property == 8) && (paramArrayOfDataSaver[paramInt6].x != -1) && (paramInt6 > 325) && (paramInt6 < 358) && (CommonUtils.getGPKeyCodeByName1(paramArrayOfDataSaver[paramInt6].name) != -1) && (paramInt6 != 342) && (paramInt6 != 343))
      {
        localObject4 = ChangeDataUtil.intToByteArray(paramInt1 - paramArrayOfDataSaver[paramInt6].y);
        arrayOfByte1 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaver[paramInt6].x);
        localObject5 = new GPNormalKey();
        ((GPNormalKey)localObject5).setType((byte)7);
        ((GPNormalKey)localObject5).setCode((byte)CommonUtils.getGPKeyCodeByName1(paramArrayOfDataSaver[paramInt6].name));
        ((GPNormalKey)localObject5).setP1xl(localObject4[1]);
        ((GPNormalKey)localObject5).setP1xh(localObject4[0]);
        ((GPNormalKey)localObject5).setP1yl(arrayOfByte1[1]);
        ((GPNormalKey)localObject5).setP1yh(arrayOfByte1[0]);
        ((GPNormalKey)localObject5).setP2xl((byte)0);
        ((GPNormalKey)localObject5).setP2xh((byte)0);
        ((GPNormalKey)localObject5).setP2yl((byte)0);
        ((GPNormalKey)localObject5).setP2yh((byte)0);
        ((ArrayList)localObject3).add(localObject5);
      }
      if ((paramArrayOfDataSaver[paramInt6].property == 9) && (paramArrayOfDataSaver[paramInt6].x != -1) && (paramInt6 > 357) && (paramInt6 < 390) && (CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt6].name) != -1) && (paramInt3 >= 3))
      {
        localObject4 = new GPNormalKey();
        ((GPNormalKey)localObject4).setType((byte)8);
        ((GPNormalKey)localObject4).setCode((byte)CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt6].name));
        ((GPNormalKey)localObject4).setP1xl((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[paramInt6].y));
        ((GPNormalKey)localObject4).setP1xh((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[paramInt6].x));
        i = paramInt6 + 32;
        ((GPNormalKey)localObject4).setP1yl((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[i].y));
        ((GPNormalKey)localObject4).setP1yh((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[i].x));
        ((GPNormalKey)localObject4).setP2xl((byte)paramArrayOfDataSaver[paramInt6].radius);
        ((GPNormalKey)localObject4).setP2xh((byte)paramArrayOfDataSaver[paramInt6].block);
        ((GPNormalKey)localObject4).setP2yl((byte)0);
        ((GPNormalKey)localObject4).setP2yh((byte)0);
        if ((((GPNormalKey)localObject4).getP1xl() == ((GPNormalKey)localObject4).getP1yl()) && (((GPNormalKey)localObject4).getP1xh() == ((GPNormalKey)localObject4).getP1yh()))
        {
          ((GPNormalKey)localObject4).setP1yl((byte)(((GPNormalKey)localObject4).getP1xl() + 1));
          ((GPNormalKey)localObject4).setP1yh((byte)(((GPNormalKey)localObject4).getP1xh() + 1));
        }
        ((ArrayList)localObject3).add(localObject4);
      }
      if ((paramArrayOfDataSaver[paramInt6].property == 10) && (paramArrayOfDataSaver[paramInt6].x != -1) && (paramInt6 > 421) && (paramInt6 < 454) && (CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt6].name) != -1) && (paramInt3 >= 4))
      {
        localObject4 = ChangeDataUtil.intToByteArray(paramInt1 - paramArrayOfDataSaver[paramInt6].y);
        arrayOfByte1 = ChangeDataUtil.intToByteArray(paramArrayOfDataSaver[paramInt6].x);
        localObject5 = new GPNormalKey();
        ((GPNormalKey)localObject5).setType((byte)9);
        ((GPNormalKey)localObject5).setCode((byte)CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt6].name));
        ((GPNormalKey)localObject5).setP1xl(localObject4[1]);
        ((GPNormalKey)localObject5).setP1xh(localObject4[0]);
        ((GPNormalKey)localObject5).setP1yl(arrayOfByte1[1]);
        ((GPNormalKey)localObject5).setP1yh(arrayOfByte1[0]);
        ((GPNormalKey)localObject5).setP2xl((byte)paramArrayOfDataSaver[paramInt6].radius);
        ((GPNormalKey)localObject5).setP2xh((byte)0);
        ((GPNormalKey)localObject5).setP2yl((byte)0);
        ((GPNormalKey)localObject5).setP2yh((byte)0);
        ((ArrayList)localObject3).add(localObject5);
      }
      if ((paramArrayOfDataSaver[paramInt6].property == 13) && (paramArrayOfDataSaver[paramInt6].x != -1) && (paramInt6 > 453) && (paramInt6 < 486) && (CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt6].name) != -1))
      {
        localObject4 = new StringBuilder();
        ((StringBuilder)localObject4).append("----i = ");
        ((StringBuilder)localObject4).append(paramInt6);
        MyLog.e("my_tag", ((StringBuilder)localObject4).toString());
        localObject4 = new MacroKey();
        ((MacroKey)localObject4).setType((byte)14);
        ((MacroKey)localObject4).setKeyCode((byte)CommonUtils.getGPKeyCodeByName(paramArrayOfDataSaver[paramInt6].name));
        ((MacroKey)localObject4).setPoint0X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[paramInt6].y));
        ((MacroKey)localObject4).setPoint0Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[paramInt6].x));
        ((MacroKey)localObject4).setPoint0MacroTime((byte)paramArrayOfDataSaver[paramInt6].radius);
        ((MacroKey)localObject4).setPoint0PauseTime((byte)paramArrayOfDataSaver[paramInt6].block);
        i = paramInt6 + 32;
        ((MacroKey)localObject4).setPoint1X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[i].y));
        ((MacroKey)localObject4).setPoint1Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[i].x));
        ((MacroKey)localObject4).setPoint1MacroTime((byte)paramArrayOfDataSaver[i].radius);
        ((MacroKey)localObject4).setPoint1PauseTime((byte)paramArrayOfDataSaver[i].block);
        i = paramInt6 + 64;
        if ((paramArrayOfDataSaver[i].x != -1) && (paramArrayOfDataSaver[i].y != -1) && (paramArrayOfDataSaver[i].radius != -1) && (paramArrayOfDataSaver[i].block != -1))
        {
          ((MacroKey)localObject4).setPoint2X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[i].y));
          ((MacroKey)localObject4).setPoint2Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[i].x));
          ((MacroKey)localObject4).setPoint2MacroTime((byte)paramArrayOfDataSaver[i].radius);
          ((MacroKey)localObject4).setPoint2PauseTime((byte)paramArrayOfDataSaver[i].block);
        }
        else
        {
          ((MacroKey)localObject4).setPoint2X((byte)0);
          ((MacroKey)localObject4).setPoint2Y((byte)0);
          ((MacroKey)localObject4).setPoint2MacroTime((byte)0);
          ((MacroKey)localObject4).setPoint2PauseTime((byte)0);
        }
        i = paramInt6 + 96;
        if ((paramArrayOfDataSaver[i].x != -1) && (paramArrayOfDataSaver[i].y != -1) && (paramArrayOfDataSaver[i].radius != -1) && (paramArrayOfDataSaver[i].block != -1))
        {
          ((MacroKey)localObject4).setPoint3X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[i].y));
          ((MacroKey)localObject4).setPoint3Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[i].x));
          ((MacroKey)localObject4).setPoint3MacroTime((byte)paramArrayOfDataSaver[i].radius);
          ((MacroKey)localObject4).setPoint3PauseTime((byte)paramArrayOfDataSaver[i].block);
        }
        else
        {
          ((MacroKey)localObject4).setPoint3X((byte)0);
          ((MacroKey)localObject4).setPoint3Y((byte)0);
          ((MacroKey)localObject4).setPoint3MacroTime((byte)0);
          ((MacroKey)localObject4).setPoint3PauseTime((byte)0);
        }
        i = paramInt6 + 128;
        if ((paramArrayOfDataSaver[i].x != -1) && (paramArrayOfDataSaver[i].y != -1) && (paramArrayOfDataSaver[i].radius != -1) && (paramArrayOfDataSaver[i].block != -1))
        {
          ((MacroKey)localObject4).setPoint4X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[i].y));
          ((MacroKey)localObject4).setPoint4Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[i].x));
          ((MacroKey)localObject4).setPoint4MacroTime((byte)paramArrayOfDataSaver[i].radius);
          ((MacroKey)localObject4).setPoint4PauseTime((byte)paramArrayOfDataSaver[i].block);
        }
        else
        {
          ((MacroKey)localObject4).setPoint4X((byte)0);
          ((MacroKey)localObject4).setPoint4Y((byte)0);
          ((MacroKey)localObject4).setPoint4MacroTime((byte)0);
          ((MacroKey)localObject4).setPoint4PauseTime((byte)0);
        }
        i = paramInt6 + 160;
        if ((paramArrayOfDataSaver[i].x != -1) && (paramArrayOfDataSaver[i].y != -1) && (paramArrayOfDataSaver[i].radius != -1) && (paramArrayOfDataSaver[i].block != -1))
        {
          ((MacroKey)localObject4).setPoint5X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[i].y));
          ((MacroKey)localObject4).setPoint5Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[i].x));
          ((MacroKey)localObject4).setPoint5MacroTime((byte)paramArrayOfDataSaver[i].radius);
          ((MacroKey)localObject4).setPoint5PauseTime((byte)paramArrayOfDataSaver[i].block);
        }
        else
        {
          ((MacroKey)localObject4).setPoint5X((byte)0);
          ((MacroKey)localObject4).setPoint5Y((byte)0);
          ((MacroKey)localObject4).setPoint5MacroTime((byte)0);
          ((MacroKey)localObject4).setPoint5PauseTime((byte)0);
        }
        i = paramInt6 + 192;
        if ((paramArrayOfDataSaver[i].x != -1) && (paramArrayOfDataSaver[i].y != -1) && (paramArrayOfDataSaver[i].radius != -1) && (paramArrayOfDataSaver[i].block != -1))
        {
          ((MacroKey)localObject4).setPoint6X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[i].y));
          ((MacroKey)localObject4).setPoint6Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[i].x));
          ((MacroKey)localObject4).setPoint6MacroTime((byte)paramArrayOfDataSaver[i].radius);
          ((MacroKey)localObject4).setPoint6PauseTime((byte)paramArrayOfDataSaver[i].block);
        }
        else
        {
          ((MacroKey)localObject4).setPoint6X((byte)0);
          ((MacroKey)localObject4).setPoint6Y((byte)0);
          ((MacroKey)localObject4).setPoint6MacroTime((byte)0);
          ((MacroKey)localObject4).setPoint6PauseTime((byte)0);
        }
        i = paramInt6 + 224;
        if ((paramArrayOfDataSaver[i].x != -1) && (paramArrayOfDataSaver[i].y != -1) && (paramArrayOfDataSaver[i].radius != -1) && (paramArrayOfDataSaver[i].block != -1))
        {
          ((MacroKey)localObject4).setPoint7X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[i].y));
          ((MacroKey)localObject4).setPoint7Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[i].x));
          ((MacroKey)localObject4).setPoint7MacroTime((byte)paramArrayOfDataSaver[i].radius);
          ((MacroKey)localObject4).setPoint7PauseTime((byte)paramArrayOfDataSaver[i].block);
        }
        else
        {
          ((MacroKey)localObject4).setPoint7X((byte)0);
          ((MacroKey)localObject4).setPoint7Y((byte)0);
          ((MacroKey)localObject4).setPoint7MacroTime((byte)0);
          ((MacroKey)localObject4).setPoint7PauseTime((byte)0);
        }
        i = paramInt6 + 256;
        if ((paramArrayOfDataSaver[i].x != -1) && (paramArrayOfDataSaver[i].y != -1) && (paramArrayOfDataSaver[i].radius != -1) && (paramArrayOfDataSaver[i].block != -1))
        {
          ((MacroKey)localObject4).setPoint8X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[i].y));
          ((MacroKey)localObject4).setPoint8Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[i].x));
          ((MacroKey)localObject4).setPoint8MacroTime((byte)paramArrayOfDataSaver[i].radius);
          ((MacroKey)localObject4).setPoint8PauseTime((byte)paramArrayOfDataSaver[i].block);
        }
        else
        {
          ((MacroKey)localObject4).setPoint8X((byte)0);
          ((MacroKey)localObject4).setPoint8Y((byte)0);
          ((MacroKey)localObject4).setPoint8MacroTime((byte)0);
          ((MacroKey)localObject4).setPoint8PauseTime((byte)0);
        }
        i = paramInt6 + 288;
        if ((paramArrayOfDataSaver[i].x != -1) && (paramArrayOfDataSaver[i].y != -1) && (paramArrayOfDataSaver[i].radius != -1) && (paramArrayOfDataSaver[i].block != -1))
        {
          ((MacroKey)localObject4).setPoint9X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[i].y));
          ((MacroKey)localObject4).setPoint9Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[i].x));
          ((MacroKey)localObject4).setPoint9MacroTime((byte)paramArrayOfDataSaver[i].radius);
          ((MacroKey)localObject4).setPoint9PauseTime((byte)paramArrayOfDataSaver[i].block);
        }
        else
        {
          ((MacroKey)localObject4).setPoint9X((byte)0);
          ((MacroKey)localObject4).setPoint9Y((byte)0);
          ((MacroKey)localObject4).setPoint9MacroTime((byte)0);
          ((MacroKey)localObject4).setPoint9PauseTime((byte)0);
        }
        i = paramInt6 + 320;
        if ((paramArrayOfDataSaver[i].x != -1) && (paramArrayOfDataSaver[i].y != -1) && (paramArrayOfDataSaver[i].radius != -1) && (paramArrayOfDataSaver[i].block != -1))
        {
          ((MacroKey)localObject4).setPoint10X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[i].y));
          ((MacroKey)localObject4).setPoint10Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[i].x));
          ((MacroKey)localObject4).setPoint10MacroTime((byte)paramArrayOfDataSaver[i].radius);
          ((MacroKey)localObject4).setPoint10PauseTime((byte)paramArrayOfDataSaver[i].block);
        }
        else
        {
          ((MacroKey)localObject4).setPoint10X((byte)0);
          ((MacroKey)localObject4).setPoint10Y((byte)0);
          ((MacroKey)localObject4).setPoint10MacroTime((byte)0);
          ((MacroKey)localObject4).setPoint10PauseTime((byte)0);
        }
        i = paramInt6 + 352;
        if ((paramArrayOfDataSaver[i].x != -1) && (paramArrayOfDataSaver[i].y != -1) && (paramArrayOfDataSaver[i].radius != -1) && (paramArrayOfDataSaver[i].block != -1))
        {
          ((MacroKey)localObject4).setPoint11X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[i].y));
          ((MacroKey)localObject4).setPoint11Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[i].x));
          ((MacroKey)localObject4).setPoint11MacroTime((byte)paramArrayOfDataSaver[i].radius);
          ((MacroKey)localObject4).setPoint11PauseTime((byte)paramArrayOfDataSaver[i].block);
        }
        else
        {
          ((MacroKey)localObject4).setPoint11X((byte)0);
          ((MacroKey)localObject4).setPoint11Y((byte)0);
          ((MacroKey)localObject4).setPoint11MacroTime((byte)0);
          ((MacroKey)localObject4).setPoint11PauseTime((byte)0);
        }
        i = paramInt6 + 384;
        if ((paramArrayOfDataSaver[i].x != -1) && (paramArrayOfDataSaver[i].y != -1) && (paramArrayOfDataSaver[i].radius != -1) && (paramArrayOfDataSaver[i].block != -1))
        {
          ((MacroKey)localObject4).setPoint12X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[i].y));
          ((MacroKey)localObject4).setPoint12Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[i].x));
          ((MacroKey)localObject4).setPoint12MacroTime((byte)paramArrayOfDataSaver[i].radius);
          ((MacroKey)localObject4).setPoint12PauseTime((byte)paramArrayOfDataSaver[i].block);
        }
        else
        {
          ((MacroKey)localObject4).setPoint12X((byte)0);
          ((MacroKey)localObject4).setPoint12Y((byte)0);
          ((MacroKey)localObject4).setPoint12MacroTime((byte)0);
          ((MacroKey)localObject4).setPoint12PauseTime((byte)0);
        }
        i = paramInt6 + 416;
        if ((paramArrayOfDataSaver[i].x != -1) && (paramArrayOfDataSaver[i].y != -1) && (paramArrayOfDataSaver[i].radius != -1) && (paramArrayOfDataSaver[i].block != -1))
        {
          ((MacroKey)localObject4).setPoint13X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[i].y));
          ((MacroKey)localObject4).setPoint13Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[i].x));
          ((MacroKey)localObject4).setPoint13MacroTime((byte)paramArrayOfDataSaver[i].radius);
          ((MacroKey)localObject4).setPoint13PauseTime((byte)paramArrayOfDataSaver[i].block);
        }
        else
        {
          ((MacroKey)localObject4).setPoint13X((byte)0);
          ((MacroKey)localObject4).setPoint13Y((byte)0);
          ((MacroKey)localObject4).setPoint13MacroTime((byte)0);
          ((MacroKey)localObject4).setPoint13PauseTime((byte)0);
        }
        i = paramInt6 + 448;
        if ((paramArrayOfDataSaver[i].x != -1) && (paramArrayOfDataSaver[i].y != -1) && (paramArrayOfDataSaver[i].radius != -1) && (paramArrayOfDataSaver[i].block != -1))
        {
          ((MacroKey)localObject4).setPoint14X((byte)ChangeDataUtil.get16BitByValue(paramInt1, paramInt1 - paramArrayOfDataSaver[i].y));
          ((MacroKey)localObject4).setPoint14Y((byte)ChangeDataUtil.get16BitByValue(paramInt2, paramArrayOfDataSaver[i].x));
          ((MacroKey)localObject4).setPoint14MacroTime((byte)paramArrayOfDataSaver[i].radius);
          ((MacroKey)localObject4).setPoint14PauseTime((byte)paramArrayOfDataSaver[i].block);
        }
        else
        {
          ((MacroKey)localObject4).setPoint14X((byte)0);
          ((MacroKey)localObject4).setPoint14Y((byte)0);
          ((MacroKey)localObject4).setPoint14MacroTime((byte)0);
          ((MacroKey)localObject4).setPoint14PauseTime((byte)0);
        }
        ((ArrayList)localObject2).add(localObject4);
      }
      paramInt6 += 1;
    }
    paramInt1 = 0;
    while (paramInt1 < ((ArrayList)localObject3).size())
    {
      paramInt2 = paramInt1 * 10;
      paramInt3 = paramInt2 + 33;
      if (paramInt3 < localObject1.length)
      {
        localObject1[(paramInt2 + 24)] = ((GPNormalKey)((ArrayList)localObject3).get(paramInt1)).getType();
        localObject1[(paramInt2 + 25)] = ((GPNormalKey)((ArrayList)localObject3).get(paramInt1)).getCode();
        localObject1[(paramInt2 + 26)] = ((GPNormalKey)((ArrayList)localObject3).get(paramInt1)).getP1xl();
        localObject1[(paramInt2 + 27)] = ((GPNormalKey)((ArrayList)localObject3).get(paramInt1)).getP1xh();
        localObject1[(paramInt2 + 28)] = ((GPNormalKey)((ArrayList)localObject3).get(paramInt1)).getP1yl();
        localObject1[(paramInt2 + 29)] = ((GPNormalKey)((ArrayList)localObject3).get(paramInt1)).getP1yh();
        localObject1[(paramInt2 + 30)] = ((GPNormalKey)((ArrayList)localObject3).get(paramInt1)).getP2xl();
        localObject1[(paramInt2 + 31)] = ((GPNormalKey)((ArrayList)localObject3).get(paramInt1)).getP2xh();
        localObject1[(paramInt2 + 32)] = ((GPNormalKey)((ArrayList)localObject3).get(paramInt1)).getP2yl();
        localObject1[paramInt3] = ((GPNormalKey)((ArrayList)localObject3).get(paramInt1)).getP2yh();
      }
      paramInt1 += 1;
    }
    paramInt1 = 0;
    while (paramInt1 < ((ArrayList)localObject2).size())
    {
      paramArrayOfDataSaver = MacroKeyUtils.AnalysisMacroKey((MacroKey)((ArrayList)localObject2).get(paramInt1));
      paramInt2 = paramInt4 * 10;
      if (paramInt2 + 23 + paramArrayOfDataSaver.length < localObject1.length)
        System.arraycopy(paramArrayOfDataSaver, 0, localObject1, paramInt2 + 24 + paramArrayOfDataSaver.length * paramInt1, paramArrayOfDataSaver.length);
      paramInt1 += 1;
    }
    paramArrayOfDataSaver = new byte[paramInt5 + 262];
    System.arraycopy(localObject1, 2, paramArrayOfDataSaver, 0, paramArrayOfDataSaver.length);
    paramInt1 = CommonUtils.CRC_GetModbus16(paramArrayOfDataSaver, paramArrayOfDataSaver.length);
    paramArrayOfDataSaver = new StringBuilder();
    paramArrayOfDataSaver.append("------save checkCode = ");
    paramArrayOfDataSaver.append(paramInt1);
    MyLog.i("crc_tag", paramArrayOfDataSaver.toString());
    paramArrayOfDataSaver = ChangeDataUtil.intToByteArray(paramInt1);
    localObject1[0] = paramArrayOfDataSaver[1];
    localObject1[1] = paramArrayOfDataSaver[0];
    return localObject1;
  }

  public static byte[] changeDataToByteArrayPortraitScreen(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    Object localObject5 = MyApplication.getMdatasaverScenes0();
    Object localObject6 = MyApplication.getMdatasaverScenes0();
    Object localObject3 = MyApplication.getMdatasaverScenes1();
    if ((localObject6 != null) && (localObject3 != null))
    {
      Object localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("resolutionX = ");
      ((StringBuilder)localObject1).append(paramInt1);
      ((StringBuilder)localObject1).append(";  resolutionY = ");
      ((StringBuilder)localObject1).append(paramInt2);
      MyLog.i("my_tag", ((StringBuilder)localObject1).toString());
      Object localObject2 = ChangeDataUtil.intToByteArray(paramInt2);
      Object localObject4 = ChangeDataUtil.intToByteArray(paramInt1);
      paramInt4 *= 10;
      int i = paramInt4 + 10;
      localObject1 = new byte[(paramInt6 * 4 + 2) * paramInt5 + i];
      localObject1[2] = 2;
      localObject1[3] = ((byte)paramInt3);
      localObject1[4] = 1;
      localObject1[5] = localObject2[1];
      localObject1[6] = localObject2[0];
      localObject1[7] = localObject4[1];
      localObject1[8] = localObject4[0];
      localObject4 = new ArrayList();
      localObject2 = new ArrayList();
      paramInt6 = 0;
      paramInt5 = i;
      Object localObject7;
      byte[] arrayOfByte1;
      Object localObject8;
      while (paramInt6 < localObject6.length)
      {
        if ((!localObject6[paramInt6].name.equals("-1")) && (localObject6[paramInt6].x != -1) && (paramInt6 > 294) && (paramInt6 < 326) && (paramInt6 != 310) && (paramInt6 != 311) && (localObject6[paramInt6].property == 7) && (CommonUtils.getGPKeyCodeByName(localObject6[paramInt6].name) != -1))
          localObject1[9] = ((byte)CommonUtils.getGPKeyCodeByName(localObject6[paramInt6].name));
        if ((!localObject5[paramInt6].name.equals("-1")) && (localObject6[paramInt6].x != -1) && (paramInt6 < 38) && (CommonUtils.getGPKeyCodeByName(localObject6[paramInt6].name) != -1) && (paramInt6 != 16) && (paramInt6 != 17) && (paramInt6 != 22) && (paramInt6 != 23) && (paramInt6 != 24) && (paramInt6 != 25) && (paramInt6 != 26))
        {
          localObject7 = ChangeDataUtil.intToByteArray(localObject6[paramInt6].x);
          arrayOfByte1 = ChangeDataUtil.intToByteArray(localObject6[paramInt6].y);
          localObject8 = new GPNormalKey();
          ((GPNormalKey)localObject8).setType((byte)1);
          ((GPNormalKey)localObject8).setCode((byte)CommonUtils.getGPKeyCodeByName(localObject6[paramInt6].name));
          ((GPNormalKey)localObject8).setP1xl(localObject7[1]);
          ((GPNormalKey)localObject8).setP1xh(localObject7[0]);
          ((GPNormalKey)localObject8).setP1yl(arrayOfByte1[1]);
          ((GPNormalKey)localObject8).setP1yh(arrayOfByte1[0]);
          ((GPNormalKey)localObject8).setP2xl((byte)0);
          ((GPNormalKey)localObject8).setP2xh((byte)0);
          ((GPNormalKey)localObject8).setP2yl((byte)0);
          ((GPNormalKey)localObject8).setP2yh((byte)0);
          ((ArrayList)localObject4).add(localObject8);
        }
        if ((localObject6[paramInt6].property == 1) && (localObject6[paramInt6].x != -1) && (paramInt6 > 37) && (paramInt6 < 70) && (CommonUtils.getGPKeyCodeByName(localObject6[paramInt6].name) != -1) && (paramInt6 != 54) && (paramInt6 != 55))
        {
          arrayOfByte1 = ChangeDataUtil.intToByteArray(localObject6[paramInt6].x);
          localObject8 = ChangeDataUtil.intToByteArray(localObject6[paramInt6].y);
          localObject7 = new GPNormalKey();
          ((GPNormalKey)localObject7).setType((byte)6);
          ((GPNormalKey)localObject7).setCode((byte)CommonUtils.getGPKeyCodeByName(localObject6[paramInt6].name));
          ((GPNormalKey)localObject7).setP1xl(arrayOfByte1[1]);
          ((GPNormalKey)localObject7).setP1xh(arrayOfByte1[0]);
          ((GPNormalKey)localObject7).setP1yl(localObject8[1]);
          ((GPNormalKey)localObject7).setP1yh(localObject8[0]);
          if (localObject6[paramInt6].radius != 0)
          {
            arrayOfByte1 = ChangeDataUtil.intToByteArray(localObject6[paramInt6].radius);
            ((GPNormalKey)localObject7).setP2xl((byte)1);
            ((GPNormalKey)localObject7).setP2xh(arrayOfByte1[1]);
            ((GPNormalKey)localObject7).setP2yl(arrayOfByte1[0]);
            ((GPNormalKey)localObject7).setP2yh((byte)localObject6[paramInt6].reverse);
          }
          else
          {
            ((GPNormalKey)localObject7).setP2xl((byte)4);
            ((GPNormalKey)localObject7).setP2xh((byte)localObject6[paramInt6].block);
            ((GPNormalKey)localObject7).setP2yl((byte)0);
            ((GPNormalKey)localObject7).setP2yh((byte)localObject5[paramInt6].reverse);
          }
          ((ArrayList)localObject4).add(localObject7);
        }
        if ((localObject6[paramInt6].property == 2) && (localObject6[paramInt6].x != -1) && (paramInt6 > 69) && (paramInt6 < 102) && (CommonUtils.getGPKeyCodeByName(localObject6[paramInt6].name) != -1) && (paramInt6 != 86) && (paramInt6 != 87))
        {
          arrayOfByte1 = ChangeDataUtil.intToByteArray(localObject6[paramInt6].x);
          localObject8 = ChangeDataUtil.intToByteArray(localObject6[paramInt6].y);
          localObject7 = new GPNormalKey();
          ((GPNormalKey)localObject7).setType((byte)6);
          ((GPNormalKey)localObject7).setCode((byte)CommonUtils.getGPKeyCodeByName(localObject6[paramInt6].name));
          ((GPNormalKey)localObject7).setP1xl(arrayOfByte1[1]);
          ((GPNormalKey)localObject7).setP1xh(arrayOfByte1[0]);
          ((GPNormalKey)localObject7).setP1yl(localObject8[1]);
          ((GPNormalKey)localObject7).setP1yh(localObject8[0]);
          if (localObject6[paramInt6].radius != 0)
          {
            arrayOfByte1 = ChangeDataUtil.intToByteArray(localObject6[paramInt6].radius);
            ((GPNormalKey)localObject7).setP2xl((byte)2);
            ((GPNormalKey)localObject7).setP2xh(arrayOfByte1[1]);
            ((GPNormalKey)localObject7).setP2yl(arrayOfByte1[0]);
            ((GPNormalKey)localObject7).setP2yh((byte)localObject6[paramInt6].reverse);
          }
          else
          {
            ((GPNormalKey)localObject7).setP2xl((byte)5);
            ((GPNormalKey)localObject7).setP2xh((byte)localObject6[paramInt6].block);
            ((GPNormalKey)localObject7).setP2yl((byte)0);
            ((GPNormalKey)localObject7).setP2yh((byte)localObject5[paramInt6].reverse);
          }
          ((ArrayList)localObject4).add(localObject7);
        }
        if ((localObject6[paramInt6].property == 3) && (localObject6[paramInt6].x != -1) && (paramInt6 > 101) && (paramInt6 < 134) && (CommonUtils.getGPKeyCodeByName(localObject6[paramInt6].name) != -1) && (paramInt6 != 118) && (paramInt6 != 119))
        {
          arrayOfByte1 = ChangeDataUtil.intToByteArray(localObject6[paramInt6].x);
          localObject8 = ChangeDataUtil.intToByteArray(localObject6[paramInt6].y);
          localObject7 = new GPNormalKey();
          ((GPNormalKey)localObject7).setType((byte)6);
          ((GPNormalKey)localObject7).setCode((byte)CommonUtils.getGPKeyCodeByName(localObject6[paramInt6].name));
          ((GPNormalKey)localObject7).setP1xl(arrayOfByte1[1]);
          ((GPNormalKey)localObject7).setP1xh(arrayOfByte1[0]);
          ((GPNormalKey)localObject7).setP1yl(localObject8[1]);
          ((GPNormalKey)localObject7).setP1yh(localObject8[0]);
          if (localObject6[paramInt6].radius != 0)
          {
            arrayOfByte1 = ChangeDataUtil.intToByteArray(localObject6[paramInt6].radius);
            ((GPNormalKey)localObject7).setP2xl((byte)3);
            ((GPNormalKey)localObject7).setP2xh(arrayOfByte1[1]);
            ((GPNormalKey)localObject7).setP2yl(arrayOfByte1[0]);
            ((GPNormalKey)localObject7).setP2yh((byte)localObject6[paramInt6].reverse);
          }
          else
          {
            ((GPNormalKey)localObject7).setP2xl((byte)6);
            ((GPNormalKey)localObject7).setP2xh((byte)localObject6[paramInt6].block);
            ((GPNormalKey)localObject7).setP2yl((byte)0);
            ((GPNormalKey)localObject7).setP2yh((byte)localObject5[paramInt6].reverse);
          }
          ((ArrayList)localObject4).add(localObject7);
        }
        byte[] arrayOfByte2;
        GPNormalKey localGPNormalKey;
        if ((localObject6[paramInt6].property == 4) && (localObject6[paramInt6].x != -1) && (paramInt6 > 133) && (paramInt6 < 166) && (CommonUtils.getGPKeyCodeByName(localObject6[paramInt6].name) != -1) && (paramInt6 != 150) && (paramInt6 != 151))
        {
          localObject7 = ChangeDataUtil.intToByteArray(localObject6[paramInt6].x);
          arrayOfByte1 = ChangeDataUtil.intToByteArray(localObject6[paramInt6].y);
          i = paramInt6 + 32;
          localObject8 = ChangeDataUtil.intToByteArray(localObject6[i].x);
          arrayOfByte2 = ChangeDataUtil.intToByteArray(localObject6[i].y);
          localGPNormalKey = new GPNormalKey();
          localGPNormalKey.setType((byte)2);
          localGPNormalKey.setCode((byte)CommonUtils.getGPKeyCodeByName(localObject6[paramInt6].name));
          localGPNormalKey.setP1xl(localObject7[1]);
          localGPNormalKey.setP1xh(localObject7[0]);
          localGPNormalKey.setP1yl(arrayOfByte1[1]);
          localGPNormalKey.setP1yh(arrayOfByte1[0]);
          localGPNormalKey.setP2xl(localObject8[1]);
          localGPNormalKey.setP2xh(localObject8[0]);
          localGPNormalKey.setP2yl(arrayOfByte2[1]);
          localGPNormalKey.setP2yh(arrayOfByte2[0]);
          ((ArrayList)localObject4).add(localGPNormalKey);
        }
        if ((localObject6[paramInt6].property == 5) && (localObject6[paramInt6].x != -1) && (paramInt6 > 197) && (paramInt6 < 230) && (CommonUtils.getGPKeyCodeByName(localObject6[paramInt6].name) != -1) && (paramInt6 != 214) && (paramInt6 != 215))
        {
          localObject7 = ChangeDataUtil.intToByteArray(localObject6[paramInt6].x);
          arrayOfByte1 = ChangeDataUtil.intToByteArray(localObject6[paramInt6].y);
          i = paramInt6 + 32;
          localObject8 = ChangeDataUtil.intToByteArray(localObject6[i].x);
          arrayOfByte2 = ChangeDataUtil.intToByteArray(localObject6[i].y);
          localGPNormalKey = new GPNormalKey();
          localGPNormalKey.setType((byte)3);
          localGPNormalKey.setCode((byte)CommonUtils.getGPKeyCodeByName(localObject6[paramInt6].name));
          localGPNormalKey.setP1xl(localObject7[1]);
          localGPNormalKey.setP1xh(localObject7[0]);
          localGPNormalKey.setP1yl(arrayOfByte1[1]);
          localGPNormalKey.setP1yh(arrayOfByte1[0]);
          localGPNormalKey.setP2xl(localObject8[1]);
          localGPNormalKey.setP2xh(localObject8[0]);
          localGPNormalKey.setP2yl(arrayOfByte2[1]);
          localGPNormalKey.setP2yh(arrayOfByte2[0]);
          ((ArrayList)localObject4).add(localGPNormalKey);
        }
        if ((localObject6[paramInt6].property == 6) && (localObject6[paramInt6].x != -1) && (paramInt6 > 261) && (paramInt6 < 294) && (CommonUtils.getGPKeyCodeByName(localObject6[paramInt6].name) != -1) && (paramInt6 != 278) && (paramInt6 != 279))
        {
          localObject7 = ChangeDataUtil.intToByteArray(localObject6[paramInt6].x);
          arrayOfByte1 = ChangeDataUtil.intToByteArray(localObject6[paramInt6].y);
          localObject8 = new GPNormalKey();
          ((GPNormalKey)localObject8).setType((byte)6);
          ((GPNormalKey)localObject8).setCode((byte)CommonUtils.getGPKeyCodeByName(localObject6[paramInt6].name));
          ((GPNormalKey)localObject8).setP1xl(localObject7[1]);
          ((GPNormalKey)localObject8).setP1xh(localObject7[0]);
          ((GPNormalKey)localObject8).setP1yl(arrayOfByte1[1]);
          ((GPNormalKey)localObject8).setP1yh(arrayOfByte1[0]);
          ((GPNormalKey)localObject8).setP2xl((byte)5);
          ((GPNormalKey)localObject8).setP2xh((byte)localObject6[paramInt6].block);
          ((GPNormalKey)localObject8).setP2yl((byte)0);
          ((GPNormalKey)localObject8).setP2yh((byte)localObject5[paramInt6].reverse);
          ((ArrayList)localObject4).add(localObject8);
        }
        if ((localObject6[paramInt6].property == 8) && (localObject6[paramInt6].x != -1) && (paramInt6 > 325) && (paramInt6 < 358) && (CommonUtils.getGPKeyCodeByName1(localObject6[paramInt6].name) != -1) && (paramInt6 != 342) && (paramInt6 != 343))
        {
          localObject7 = ChangeDataUtil.intToByteArray(localObject6[paramInt6].x);
          arrayOfByte1 = ChangeDataUtil.intToByteArray(localObject6[paramInt6].y);
          localObject8 = new GPNormalKey();
          ((GPNormalKey)localObject8).setType((byte)7);
          ((GPNormalKey)localObject8).setCode((byte)CommonUtils.getGPKeyCodeByName1(localObject6[paramInt6].name));
          ((GPNormalKey)localObject8).setP1xl(localObject7[1]);
          ((GPNormalKey)localObject8).setP1xh(localObject7[0]);
          ((GPNormalKey)localObject8).setP1yl(arrayOfByte1[1]);
          ((GPNormalKey)localObject8).setP1yh(arrayOfByte1[0]);
          ((GPNormalKey)localObject8).setP2xl((byte)0);
          ((GPNormalKey)localObject8).setP2xh((byte)0);
          ((GPNormalKey)localObject8).setP2yl((byte)0);
          ((GPNormalKey)localObject8).setP2yh((byte)0);
          ((ArrayList)localObject4).add(localObject8);
        }
        if ((localObject6[paramInt6].property == 9) && (localObject6[paramInt6].x != -1) && (paramInt6 > 357) && (paramInt6 < 390) && (CommonUtils.getGPKeyCodeByName(localObject6[paramInt6].name) != -1) && (paramInt3 >= 3))
        {
          localObject7 = new GPNormalKey();
          ((GPNormalKey)localObject7).setType((byte)8);
          ((GPNormalKey)localObject7).setCode((byte)CommonUtils.getGPKeyCodeByName(localObject6[paramInt6].name));
          ((GPNormalKey)localObject7).setP1xl((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[paramInt6].x));
          ((GPNormalKey)localObject7).setP1xh((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject6[paramInt6].y));
          i = paramInt6 + 32;
          ((GPNormalKey)localObject7).setP1yl((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[i].x));
          ((GPNormalKey)localObject7).setP1yh((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject6[i].y));
          ((GPNormalKey)localObject7).setP2xl((byte)localObject6[paramInt6].radius);
          ((GPNormalKey)localObject7).setP2xh((byte)localObject6[paramInt6].block);
          ((GPNormalKey)localObject7).setP2yl((byte)0);
          ((GPNormalKey)localObject7).setP2yh((byte)0);
          if ((((GPNormalKey)localObject7).getP1xl() == ((GPNormalKey)localObject7).getP1yl()) && (((GPNormalKey)localObject7).getP1xh() == ((GPNormalKey)localObject7).getP1yh()))
          {
            ((GPNormalKey)localObject7).setP1yl((byte)(((GPNormalKey)localObject7).getP1xl() + 1));
            ((GPNormalKey)localObject7).setP1yh((byte)(((GPNormalKey)localObject7).getP1xh() + 1));
          }
          ((ArrayList)localObject4).add(localObject7);
        }
        if ((localObject6[paramInt6].property == 10) && (localObject6[paramInt6].x != -1) && (paramInt6 > 421) && (paramInt6 < 454) && (CommonUtils.getGPKeyCodeByName(localObject6[paramInt6].name) != -1) && (paramInt3 >= 4))
        {
          localObject7 = ChangeDataUtil.intToByteArray(localObject6[paramInt6].x);
          arrayOfByte1 = ChangeDataUtil.intToByteArray(localObject6[paramInt6].y);
          localObject8 = new GPNormalKey();
          ((GPNormalKey)localObject8).setType((byte)9);
          ((GPNormalKey)localObject8).setCode((byte)CommonUtils.getGPKeyCodeByName(localObject6[paramInt6].name));
          ((GPNormalKey)localObject8).setP1xl(localObject7[1]);
          ((GPNormalKey)localObject8).setP1xh(localObject7[0]);
          ((GPNormalKey)localObject8).setP1yl(arrayOfByte1[1]);
          ((GPNormalKey)localObject8).setP1yh(arrayOfByte1[0]);
          ((GPNormalKey)localObject8).setP2xl((byte)localObject6[paramInt6].radius);
          ((GPNormalKey)localObject8).setP2xh((byte)0);
          ((GPNormalKey)localObject8).setP2yl((byte)0);
          ((GPNormalKey)localObject8).setP2yh((byte)0);
          ((ArrayList)localObject4).add(localObject8);
        }
        if ((localObject6[paramInt6].property == 11) && (localObject6[paramInt6].x != -1) && (paramInt6 >= 22) && (paramInt6 <= 26) && (CommonUtils.getGPKeyCodeByName(localObject6[paramInt6].name) != -1) && (paramInt3 >= 5))
        {
          localObject7 = new GPNormalKey();
          ((GPNormalKey)localObject7).setType((byte)10);
          ((GPNormalKey)localObject7).setCode((byte)CommonUtils.getGPKeyCodeByName(localObject6[paramInt6].name));
          ((GPNormalKey)localObject7).setP1xl((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[paramInt6].x));
          ((GPNormalKey)localObject7).setP1xh((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject6[paramInt6].y));
          ((GPNormalKey)localObject7).setP1yl((byte)localObject6[paramInt6].radius);
          ((GPNormalKey)localObject7).setP1yh((byte)0);
          ((GPNormalKey)localObject7).setP2xl((byte)0);
          ((GPNormalKey)localObject7).setP2xh((byte)0);
          ((GPNormalKey)localObject7).setP2yl((byte)0);
          ((GPNormalKey)localObject7).setP2yh((byte)0);
          ((ArrayList)localObject4).add(localObject7);
        }
        if ((localObject6[paramInt6].property == 12) && (localObject6[paramInt6].x != -1) && (paramInt6 >= 22) && (paramInt6 <= 26) && (CommonUtils.getGPKeyCodeByName(localObject6[paramInt6].name) != -1) && (paramInt3 >= 5))
        {
          localObject7 = new GPNormalKey();
          arrayOfByte1 = ChangeDataUtil.intToByteArray(localObject6[paramInt6].radius);
          ((GPNormalKey)localObject7).setType((byte)11);
          ((GPNormalKey)localObject7).setCode((byte)CommonUtils.getGPKeyCodeByName(localObject6[paramInt6].name));
          ((GPNormalKey)localObject7).setP1xl((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[paramInt6].x));
          ((GPNormalKey)localObject7).setP1xh((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject6[paramInt6].y));
          ((GPNormalKey)localObject7).setP1yl(arrayOfByte1[1]);
          ((GPNormalKey)localObject7).setP1yh(arrayOfByte1[0]);
          ((GPNormalKey)localObject7).setP2xl((byte)localObject6[paramInt6].block);
          ((GPNormalKey)localObject7).setP2xh((byte)localObject6[paramInt6].reverse);
          ((GPNormalKey)localObject7).setP2yl((byte)0);
          ((GPNormalKey)localObject7).setP2yh((byte)0);
          ((ArrayList)localObject4).add(localObject7);
        }
        if ((localObject6[paramInt6].property == 14) && (localObject6[paramInt6].x != -1) && (paramInt6 > 933) && (CommonUtils.getGPKeyCodeByName(localObject6[paramInt6].name) != -1))
        {
          localObject7 = new GPNormalKey();
          ((GPNormalKey)localObject7).setType((byte)15);
          ((GPNormalKey)localObject7).setCode((byte)CommonUtils.getGPKeyCodeByName(localObject6[paramInt6].name));
          ((GPNormalKey)localObject7).setP1xl((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[paramInt6].x));
          ((GPNormalKey)localObject7).setP1xh((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject6[paramInt6].y));
          ((GPNormalKey)localObject7).setP1yl((byte)localObject6[paramInt6].radius);
          ((GPNormalKey)localObject7).setP1yh((byte)localObject6[paramInt6].block);
          if ((CommonUtils.isStringValid(localObject6[paramInt6].view_joystick)) && (!localObject6[paramInt6].view_joystick.equalsIgnoreCase("-1")))
            ((GPNormalKey)localObject7).setP2xl((byte)CommonUtils.getGPKeyCodeByName(localObject6[paramInt6].view_joystick));
          else
            ((GPNormalKey)localObject7).setP2xl((byte)0);
          ((GPNormalKey)localObject7).setP2xh((byte)localObject6[paramInt6].turb_speed);
          ((GPNormalKey)localObject7).setP2yl((byte)0);
          ((GPNormalKey)localObject7).setP2yh((byte)0);
          ((ArrayList)localObject4).add(localObject7);
        }
        if ((localObject6[paramInt6].property == 13) && (localObject6[paramInt6].x != -1) && (paramInt6 > 453) && (paramInt6 < 486) && (CommonUtils.getGPKeyCodeByName(localObject6[paramInt6].name) != -1))
        {
          localObject7 = new StringBuilder();
          ((StringBuilder)localObject7).append("----i = ");
          ((StringBuilder)localObject7).append(paramInt6);
          MyLog.e("my_tag", ((StringBuilder)localObject7).toString());
          localObject7 = new MacroKey();
          ((MacroKey)localObject7).setType((byte)14);
          ((MacroKey)localObject7).setKeyCode((byte)CommonUtils.getGPKeyCodeByName(localObject6[paramInt6].name));
          ((MacroKey)localObject7).setPoint0X((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[paramInt6].x));
          ((MacroKey)localObject7).setPoint0Y((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject6[paramInt6].y));
          ((MacroKey)localObject7).setPoint0MacroTime((byte)localObject6[paramInt6].radius);
          ((MacroKey)localObject7).setPoint0PauseTime((byte)localObject6[paramInt6].block);
          i = paramInt6 + 32;
          ((MacroKey)localObject7).setPoint1X((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[i].x));
          ((MacroKey)localObject7).setPoint1Y((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject6[i].y));
          ((MacroKey)localObject7).setPoint1MacroTime((byte)localObject6[i].radius);
          ((MacroKey)localObject7).setPoint1PauseTime((byte)localObject6[i].block);
          i = paramInt6 + 64;
          if ((localObject6[i].x != -1) && (localObject6[i].y != -1) && (localObject6[i].radius != -1) && (localObject6[i].block != -1))
          {
            ((MacroKey)localObject7).setPoint2X((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[i].x));
            ((MacroKey)localObject7).setPoint2Y((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject6[i].y));
            ((MacroKey)localObject7).setPoint2MacroTime((byte)localObject6[i].radius);
            ((MacroKey)localObject7).setPoint2PauseTime((byte)localObject6[i].block);
          }
          else
          {
            ((MacroKey)localObject7).setPoint2X((byte)0);
            ((MacroKey)localObject7).setPoint2Y((byte)0);
            ((MacroKey)localObject7).setPoint2MacroTime((byte)0);
            ((MacroKey)localObject7).setPoint2PauseTime((byte)0);
          }
          i = paramInt6 + 96;
          if ((localObject6[i].x != -1) && (localObject6[i].y != -1) && (localObject6[i].radius != -1) && (localObject6[i].block != -1))
          {
            ((MacroKey)localObject7).setPoint3X((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[i].x));
            ((MacroKey)localObject7).setPoint3Y((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject6[i].y));
            ((MacroKey)localObject7).setPoint3MacroTime((byte)localObject6[i].radius);
            ((MacroKey)localObject7).setPoint3PauseTime((byte)localObject6[i].block);
          }
          else
          {
            ((MacroKey)localObject7).setPoint3X((byte)0);
            ((MacroKey)localObject7).setPoint3Y((byte)0);
            ((MacroKey)localObject7).setPoint3MacroTime((byte)0);
            ((MacroKey)localObject7).setPoint3PauseTime((byte)0);
          }
          i = paramInt6 + 128;
          if ((localObject6[i].x != -1) && (localObject6[i].y != -1) && (localObject6[i].radius != -1) && (localObject6[i].block != -1))
          {
            ((MacroKey)localObject7).setPoint4X((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[i].x));
            ((MacroKey)localObject7).setPoint4Y((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject6[i].y));
            ((MacroKey)localObject7).setPoint4MacroTime((byte)localObject6[i].radius);
            ((MacroKey)localObject7).setPoint4PauseTime((byte)localObject6[i].block);
          }
          else
          {
            ((MacroKey)localObject7).setPoint4X((byte)0);
            ((MacroKey)localObject7).setPoint4Y((byte)0);
            ((MacroKey)localObject7).setPoint4MacroTime((byte)0);
            ((MacroKey)localObject7).setPoint4PauseTime((byte)0);
          }
          i = paramInt6 + 160;
          if ((localObject6[i].x != -1) && (localObject6[i].y != -1) && (localObject6[i].radius != -1) && (localObject6[i].block != -1))
          {
            ((MacroKey)localObject7).setPoint5X((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[i].x));
            ((MacroKey)localObject7).setPoint5Y((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject6[i].y));
            ((MacroKey)localObject7).setPoint5MacroTime((byte)localObject6[i].radius);
            ((MacroKey)localObject7).setPoint5PauseTime((byte)localObject6[i].block);
          }
          else
          {
            ((MacroKey)localObject7).setPoint5X((byte)0);
            ((MacroKey)localObject7).setPoint5Y((byte)0);
            ((MacroKey)localObject7).setPoint5MacroTime((byte)0);
            ((MacroKey)localObject7).setPoint5PauseTime((byte)0);
          }
          i = paramInt6 + 192;
          if ((localObject6[i].x != -1) && (localObject6[i].y != -1) && (localObject6[i].radius != -1) && (localObject6[i].block != -1))
          {
            ((MacroKey)localObject7).setPoint6X((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[i].x));
            ((MacroKey)localObject7).setPoint6Y((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject6[i].y));
            ((MacroKey)localObject7).setPoint6MacroTime((byte)localObject6[i].radius);
            ((MacroKey)localObject7).setPoint6PauseTime((byte)localObject6[i].block);
          }
          else
          {
            ((MacroKey)localObject7).setPoint6X((byte)0);
            ((MacroKey)localObject7).setPoint6Y((byte)0);
            ((MacroKey)localObject7).setPoint6MacroTime((byte)0);
            ((MacroKey)localObject7).setPoint6PauseTime((byte)0);
          }
          i = paramInt6 + 224;
          if ((localObject6[i].x != -1) && (localObject6[i].y != -1) && (localObject6[i].radius != -1) && (localObject6[i].block != -1))
          {
            ((MacroKey)localObject7).setPoint7X((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[i].x));
            ((MacroKey)localObject7).setPoint7Y((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject6[i].y));
            ((MacroKey)localObject7).setPoint7MacroTime((byte)localObject6[i].radius);
            ((MacroKey)localObject7).setPoint7PauseTime((byte)localObject6[i].block);
          }
          else
          {
            ((MacroKey)localObject7).setPoint7X((byte)0);
            ((MacroKey)localObject7).setPoint7Y((byte)0);
            ((MacroKey)localObject7).setPoint7MacroTime((byte)0);
            ((MacroKey)localObject7).setPoint7PauseTime((byte)0);
          }
          i = paramInt6 + 256;
          if ((localObject6[i].x != -1) && (localObject6[i].y != -1) && (localObject6[i].radius != -1) && (localObject6[i].block != -1))
          {
            ((MacroKey)localObject7).setPoint8X((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[i].x));
            ((MacroKey)localObject7).setPoint8Y((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject6[i].y));
            ((MacroKey)localObject7).setPoint8MacroTime((byte)localObject6[i].radius);
            ((MacroKey)localObject7).setPoint8PauseTime((byte)localObject6[i].block);
          }
          else
          {
            ((MacroKey)localObject7).setPoint8X((byte)0);
            ((MacroKey)localObject7).setPoint8Y((byte)0);
            ((MacroKey)localObject7).setPoint8MacroTime((byte)0);
            ((MacroKey)localObject7).setPoint8PauseTime((byte)0);
          }
          i = paramInt6 + 288;
          if ((localObject6[i].x != -1) && (localObject6[i].y != -1) && (localObject6[i].radius != -1) && (localObject6[i].block != -1))
          {
            ((MacroKey)localObject7).setPoint9X((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[i].x));
            ((MacroKey)localObject7).setPoint9Y((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject6[i].y));
            ((MacroKey)localObject7).setPoint9MacroTime((byte)localObject6[i].radius);
            ((MacroKey)localObject7).setPoint9PauseTime((byte)localObject6[i].block);
          }
          else
          {
            ((MacroKey)localObject7).setPoint9X((byte)0);
            ((MacroKey)localObject7).setPoint9Y((byte)0);
            ((MacroKey)localObject7).setPoint9MacroTime((byte)0);
            ((MacroKey)localObject7).setPoint9PauseTime((byte)0);
          }
          i = paramInt6 + 320;
          if ((localObject6[i].x != -1) && (localObject6[i].y != -1) && (localObject6[i].radius != -1) && (localObject6[i].block != -1))
          {
            ((MacroKey)localObject7).setPoint10X((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[i].x));
            ((MacroKey)localObject7).setPoint10Y((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject6[i].y));
            ((MacroKey)localObject7).setPoint10MacroTime((byte)localObject6[i].radius);
            ((MacroKey)localObject7).setPoint10PauseTime((byte)localObject6[i].block);
          }
          else
          {
            ((MacroKey)localObject7).setPoint10X((byte)0);
            ((MacroKey)localObject7).setPoint10Y((byte)0);
            ((MacroKey)localObject7).setPoint10MacroTime((byte)0);
            ((MacroKey)localObject7).setPoint10PauseTime((byte)0);
          }
          i = paramInt6 + 352;
          if ((localObject6[i].x != -1) && (localObject6[i].y != -1) && (localObject6[i].radius != -1) && (localObject6[i].block != -1))
          {
            ((MacroKey)localObject7).setPoint11X((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[i].x));
            ((MacroKey)localObject7).setPoint11Y((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject6[i].y));
            ((MacroKey)localObject7).setPoint11MacroTime((byte)localObject6[i].radius);
            ((MacroKey)localObject7).setPoint11PauseTime((byte)localObject6[i].block);
          }
          else
          {
            ((MacroKey)localObject7).setPoint11X((byte)0);
            ((MacroKey)localObject7).setPoint11Y((byte)0);
            ((MacroKey)localObject7).setPoint11MacroTime((byte)0);
            ((MacroKey)localObject7).setPoint11PauseTime((byte)0);
          }
          i = paramInt6 + 384;
          if ((localObject6[i].x != -1) && (localObject6[i].y != -1) && (localObject6[i].radius != -1) && (localObject6[i].block != -1))
          {
            ((MacroKey)localObject7).setPoint12X((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[i].x));
            ((MacroKey)localObject7).setPoint12Y((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject6[i].y));
            ((MacroKey)localObject7).setPoint12MacroTime((byte)localObject6[i].radius);
            ((MacroKey)localObject7).setPoint12PauseTime((byte)localObject6[i].block);
          }
          else
          {
            ((MacroKey)localObject7).setPoint12X((byte)0);
            ((MacroKey)localObject7).setPoint12Y((byte)0);
            ((MacroKey)localObject7).setPoint12MacroTime((byte)0);
            ((MacroKey)localObject7).setPoint12PauseTime((byte)0);
          }
          i = paramInt6 + 416;
          if ((localObject6[i].x != -1) && (localObject6[i].y != -1) && (localObject6[i].radius != -1) && (localObject6[i].block != -1))
          {
            ((MacroKey)localObject7).setPoint13X((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[i].x));
            ((MacroKey)localObject7).setPoint13Y((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject6[i].y));
            ((MacroKey)localObject7).setPoint13MacroTime((byte)localObject6[i].radius);
            ((MacroKey)localObject7).setPoint13PauseTime((byte)localObject6[i].block);
          }
          else
          {
            ((MacroKey)localObject7).setPoint13X((byte)0);
            ((MacroKey)localObject7).setPoint13Y((byte)0);
            ((MacroKey)localObject7).setPoint13MacroTime((byte)0);
            ((MacroKey)localObject7).setPoint13PauseTime((byte)0);
          }
          i = paramInt6 + 448;
          if ((localObject6[i].x != -1) && (localObject6[i].y != -1) && (localObject6[i].radius != -1) && (localObject6[i].block != -1))
          {
            ((MacroKey)localObject7).setPoint14X((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject6[i].x));
            ((MacroKey)localObject7).setPoint14Y((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject6[i].y));
            ((MacroKey)localObject7).setPoint14MacroTime((byte)localObject6[i].radius);
            ((MacroKey)localObject7).setPoint14PauseTime((byte)localObject6[i].block);
          }
          else
          {
            ((MacroKey)localObject7).setPoint14X((byte)0);
            ((MacroKey)localObject7).setPoint14Y((byte)0);
            ((MacroKey)localObject7).setPoint14MacroTime((byte)0);
            ((MacroKey)localObject7).setPoint14PauseTime((byte)0);
          }
          ((ArrayList)localObject2).add(localObject7);
        }
        paramInt6 += 1;
      }
      paramInt6 = 0;
      while (paramInt6 < localObject3.length)
      {
        if ((!localObject3[paramInt6].name.equals("-1")) && (localObject3[paramInt6].x != -1) && (paramInt6 > 294) && (paramInt6 < 326) && (paramInt6 != 310) && (paramInt6 != 311) && (localObject3[paramInt6].property == 7) && (CommonUtils.getGPKeyCodeByName(localObject3[paramInt6].name) != -1))
          localObject1[9] = ((byte)CommonUtils.getGPKeyCodeByName(localObject3[paramInt6].name));
        if (!localObject3[paramInt6].name.equals("-1"))
        {
          localObject5 = new StringBuilder();
          ((StringBuilder)localObject5).append("------ property = ");
          ((StringBuilder)localObject5).append(localObject3[paramInt6].property);
          ((StringBuilder)localObject5).append(";  name = ");
          ((StringBuilder)localObject5).append(localObject3[paramInt6].name);
          ((StringBuilder)localObject5).append("  i = ");
          ((StringBuilder)localObject5).append(paramInt6);
          ((StringBuilder)localObject5).append(";   radius = ");
          ((StringBuilder)localObject5).append(localObject3[paramInt6].radius);
          ((StringBuilder)localObject5).append(";   block = ");
          ((StringBuilder)localObject5).append(localObject3[paramInt6].block);
          MyLog.i("my_tag", ((StringBuilder)localObject5).toString());
        }
        if ((!localObject3[paramInt6].name.equals("-1")) && (localObject3[paramInt6].x != -1) && (paramInt6 < 38) && (CommonUtils.getGPKeyCodeByNameScenes1(localObject3[paramInt6].name) != -1) && (paramInt6 != 16) && (paramInt6 != 17) && (paramInt6 != 22) && (paramInt6 != 23) && (paramInt6 != 24) && (paramInt6 != 25) && (paramInt6 != 26))
        {
          localObject5 = ChangeDataUtil.intToByteArray(localObject3[paramInt6].x);
          localObject6 = ChangeDataUtil.intToByteArray(localObject3[paramInt6].y);
          localObject7 = new GPNormalKey();
          ((GPNormalKey)localObject7).setType((byte)1);
          ((GPNormalKey)localObject7).setCode((byte)CommonUtils.getGPKeyCodeByNameScenes1(localObject3[paramInt6].name));
          ((GPNormalKey)localObject7).setP1xl(localObject5[1]);
          ((GPNormalKey)localObject7).setP1xh(localObject5[0]);
          ((GPNormalKey)localObject7).setP1yl(localObject6[1]);
          ((GPNormalKey)localObject7).setP1yh(localObject6[0]);
          ((GPNormalKey)localObject7).setP2xl((byte)0);
          ((GPNormalKey)localObject7).setP2xh((byte)0);
          ((GPNormalKey)localObject7).setP2yl((byte)0);
          ((GPNormalKey)localObject7).setP2yh((byte)0);
          ((ArrayList)localObject4).add(localObject7);
        }
        if ((localObject3[paramInt6].property == 1) && (localObject3[paramInt6].x != -1) && (paramInt6 > 37) && (paramInt6 < 70) && (CommonUtils.getGPKeyCodeByNameScenes1(localObject3[paramInt6].name) != -1) && (paramInt6 != 54) && (paramInt6 != 55))
        {
          localObject6 = ChangeDataUtil.intToByteArray(localObject3[paramInt6].x);
          localObject7 = ChangeDataUtil.intToByteArray(localObject3[paramInt6].y);
          localObject5 = new GPNormalKey();
          ((GPNormalKey)localObject5).setType((byte)6);
          ((GPNormalKey)localObject5).setCode((byte)CommonUtils.getGPKeyCodeByNameScenes1(localObject3[paramInt6].name));
          ((GPNormalKey)localObject5).setP1xl(localObject6[1]);
          ((GPNormalKey)localObject5).setP1xh(localObject6[0]);
          ((GPNormalKey)localObject5).setP1yl(localObject7[1]);
          ((GPNormalKey)localObject5).setP1yh(localObject7[0]);
          if (localObject3[paramInt6].radius != 0)
          {
            localObject6 = ChangeDataUtil.intToByteArray(localObject3[paramInt6].radius);
            ((GPNormalKey)localObject5).setP2xl((byte)1);
            ((GPNormalKey)localObject5).setP2xh(localObject6[1]);
            ((GPNormalKey)localObject5).setP2yl(localObject6[0]);
            ((GPNormalKey)localObject5).setP2yh((byte)localObject3[paramInt6].reverse);
          }
          else
          {
            ((GPNormalKey)localObject5).setP2xl((byte)4);
            ((GPNormalKey)localObject5).setP2xh((byte)localObject3[paramInt6].block);
            ((GPNormalKey)localObject5).setP2yl((byte)0);
            ((GPNormalKey)localObject5).setP2yh((byte)0);
          }
          ((ArrayList)localObject4).add(localObject5);
        }
        if ((localObject3[paramInt6].property == 2) && (localObject3[paramInt6].x != -1) && (paramInt6 > 69) && (paramInt6 < 102) && (CommonUtils.getGPKeyCodeByNameScenes1(localObject3[paramInt6].name) != -1) && (paramInt6 != 86) && (paramInt6 != 87))
        {
          localObject6 = ChangeDataUtil.intToByteArray(localObject3[paramInt6].x);
          localObject7 = ChangeDataUtil.intToByteArray(localObject3[paramInt6].y);
          localObject5 = new GPNormalKey();
          ((GPNormalKey)localObject5).setType((byte)6);
          ((GPNormalKey)localObject5).setCode((byte)CommonUtils.getGPKeyCodeByNameScenes1(localObject3[paramInt6].name));
          ((GPNormalKey)localObject5).setP1xl(localObject6[1]);
          ((GPNormalKey)localObject5).setP1xh(localObject6[0]);
          ((GPNormalKey)localObject5).setP1yl(localObject7[1]);
          ((GPNormalKey)localObject5).setP1yh(localObject7[0]);
          if (localObject3[paramInt6].radius != 0)
          {
            localObject6 = ChangeDataUtil.intToByteArray(localObject3[paramInt6].radius);
            ((GPNormalKey)localObject5).setP2xl((byte)2);
            ((GPNormalKey)localObject5).setP2xh(localObject6[1]);
            ((GPNormalKey)localObject5).setP2yl(localObject6[0]);
            ((GPNormalKey)localObject5).setP2yh((byte)localObject3[paramInt6].reverse);
          }
          else
          {
            ((GPNormalKey)localObject5).setP2xl((byte)5);
            ((GPNormalKey)localObject5).setP2xh((byte)localObject3[paramInt6].block);
            ((GPNormalKey)localObject5).setP2yl((byte)0);
            ((GPNormalKey)localObject5).setP2yh((byte)0);
          }
          ((ArrayList)localObject4).add(localObject5);
        }
        if ((localObject3[paramInt6].property == 3) && (localObject3[paramInt6].x != -1) && (paramInt6 > 101) && (paramInt6 < 134) && (CommonUtils.getGPKeyCodeByNameScenes1(localObject3[paramInt6].name) != -1) && (paramInt6 != 118) && (paramInt6 != 119))
        {
          localObject6 = ChangeDataUtil.intToByteArray(localObject3[paramInt6].x);
          localObject7 = ChangeDataUtil.intToByteArray(localObject3[paramInt6].y);
          localObject5 = new GPNormalKey();
          ((GPNormalKey)localObject5).setType((byte)6);
          ((GPNormalKey)localObject5).setCode((byte)CommonUtils.getGPKeyCodeByNameScenes1(localObject3[paramInt6].name));
          ((GPNormalKey)localObject5).setP1xl(localObject6[1]);
          ((GPNormalKey)localObject5).setP1xh(localObject6[0]);
          ((GPNormalKey)localObject5).setP1yl(localObject7[1]);
          ((GPNormalKey)localObject5).setP1yh(localObject7[0]);
          if (localObject3[paramInt6].radius != 0)
          {
            localObject6 = ChangeDataUtil.intToByteArray(localObject3[paramInt6].radius);
            ((GPNormalKey)localObject5).setP2xl((byte)3);
            ((GPNormalKey)localObject5).setP2xh(localObject6[1]);
            ((GPNormalKey)localObject5).setP2yl(localObject6[0]);
            ((GPNormalKey)localObject5).setP2yh((byte)localObject3[paramInt6].reverse);
          }
          else
          {
            ((GPNormalKey)localObject5).setP2xl((byte)6);
            ((GPNormalKey)localObject5).setP2xh((byte)localObject3[paramInt6].block);
            ((GPNormalKey)localObject5).setP2yl((byte)0);
            ((GPNormalKey)localObject5).setP2yh((byte)0);
          }
          ((ArrayList)localObject4).add(localObject5);
        }
        if ((localObject3[paramInt6].property == 4) && (localObject3[paramInt6].x != -1) && (paramInt6 > 133) && (paramInt6 < 166) && (CommonUtils.getGPKeyCodeByNameScenes1(localObject3[paramInt6].name) != -1) && (paramInt6 != 150) && (paramInt6 != 151))
        {
          localObject5 = ChangeDataUtil.intToByteArray(localObject3[paramInt6].x);
          localObject6 = ChangeDataUtil.intToByteArray(localObject3[paramInt6].y);
          i = paramInt6 + 32;
          localObject7 = ChangeDataUtil.intToByteArray(localObject3[i].x);
          arrayOfByte1 = ChangeDataUtil.intToByteArray(localObject3[i].y);
          localObject8 = new GPNormalKey();
          ((GPNormalKey)localObject8).setType((byte)2);
          ((GPNormalKey)localObject8).setCode((byte)CommonUtils.getGPKeyCodeByNameScenes1(localObject3[paramInt6].name));
          ((GPNormalKey)localObject8).setP1xl(localObject5[1]);
          ((GPNormalKey)localObject8).setP1xh(localObject5[0]);
          ((GPNormalKey)localObject8).setP1yl(localObject6[1]);
          ((GPNormalKey)localObject8).setP1yh(localObject6[0]);
          ((GPNormalKey)localObject8).setP2xl(localObject7[1]);
          ((GPNormalKey)localObject8).setP2xh(localObject7[0]);
          ((GPNormalKey)localObject8).setP2yl(arrayOfByte1[1]);
          ((GPNormalKey)localObject8).setP2yh(arrayOfByte1[0]);
          ((ArrayList)localObject4).add(localObject8);
        }
        if ((localObject3[paramInt6].property == 5) && (localObject3[paramInt6].x != -1) && (paramInt6 > 197) && (paramInt6 < 230) && (CommonUtils.getGPKeyCodeByNameScenes1(localObject3[paramInt6].name) != -1) && (paramInt6 != 214) && (paramInt6 != 215))
        {
          localObject5 = ChangeDataUtil.intToByteArray(localObject3[paramInt6].x);
          localObject6 = ChangeDataUtil.intToByteArray(localObject3[paramInt6].y);
          i = paramInt6 + 32;
          localObject7 = ChangeDataUtil.intToByteArray(localObject3[i].x);
          arrayOfByte1 = ChangeDataUtil.intToByteArray(localObject3[i].y);
          localObject8 = new GPNormalKey();
          ((GPNormalKey)localObject8).setType((byte)3);
          ((GPNormalKey)localObject8).setCode((byte)CommonUtils.getGPKeyCodeByNameScenes1(localObject3[paramInt6].name));
          ((GPNormalKey)localObject8).setP1xl(localObject5[1]);
          ((GPNormalKey)localObject8).setP1xh(localObject5[0]);
          ((GPNormalKey)localObject8).setP1yl(localObject6[1]);
          ((GPNormalKey)localObject8).setP1yh(localObject6[0]);
          ((GPNormalKey)localObject8).setP2xl(localObject7[1]);
          ((GPNormalKey)localObject8).setP2xh(localObject7[0]);
          ((GPNormalKey)localObject8).setP2yl(arrayOfByte1[1]);
          ((GPNormalKey)localObject8).setP2yh(arrayOfByte1[0]);
          ((ArrayList)localObject4).add(localObject8);
        }
        if ((localObject3[paramInt6].property == 6) && (localObject3[paramInt6].x != -1) && (paramInt6 > 261) && (paramInt6 < 294) && (CommonUtils.getGPKeyCodeByNameScenes1(localObject3[paramInt6].name) != -1) && (paramInt6 != 278) && (paramInt6 != 279))
        {
          localObject5 = ChangeDataUtil.intToByteArray(localObject3[paramInt6].x);
          localObject6 = ChangeDataUtil.intToByteArray(localObject3[paramInt6].y);
          localObject7 = new GPNormalKey();
          ((GPNormalKey)localObject7).setType((byte)6);
          ((GPNormalKey)localObject7).setCode((byte)CommonUtils.getGPKeyCodeByNameScenes1(localObject3[paramInt6].name));
          ((GPNormalKey)localObject7).setP1xl(localObject5[1]);
          ((GPNormalKey)localObject7).setP1xh(localObject5[0]);
          ((GPNormalKey)localObject7).setP1yl(localObject6[1]);
          ((GPNormalKey)localObject7).setP1yh(localObject6[0]);
          ((GPNormalKey)localObject7).setP2xl((byte)5);
          ((GPNormalKey)localObject7).setP2xh((byte)localObject3[paramInt6].block);
          ((GPNormalKey)localObject7).setP2yl((byte)0);
          ((GPNormalKey)localObject7).setP2yh((byte)0);
          ((ArrayList)localObject4).add(localObject7);
        }
        if ((localObject3[paramInt6].property == 8) && (localObject3[paramInt6].x != -1) && (paramInt6 > 325) && (paramInt6 < 358) && (CommonUtils.getGPKeyCodeByName1Scenes1(localObject3[paramInt6].name) != -1) && (paramInt6 != 342) && (paramInt6 != 343))
        {
          localObject5 = ChangeDataUtil.intToByteArray(localObject3[paramInt6].x);
          localObject6 = ChangeDataUtil.intToByteArray(localObject3[paramInt6].y);
          localObject7 = new GPNormalKey();
          ((GPNormalKey)localObject7).setType((byte)7);
          ((GPNormalKey)localObject7).setCode((byte)CommonUtils.getGPKeyCodeByName1Scenes1(localObject3[paramInt6].name));
          ((GPNormalKey)localObject7).setP1xl(localObject5[1]);
          ((GPNormalKey)localObject7).setP1xh(localObject5[0]);
          ((GPNormalKey)localObject7).setP1yl(localObject6[1]);
          ((GPNormalKey)localObject7).setP1yh(localObject6[0]);
          ((GPNormalKey)localObject7).setP2xl((byte)0);
          ((GPNormalKey)localObject7).setP2xh((byte)0);
          ((GPNormalKey)localObject7).setP2yl((byte)0);
          ((GPNormalKey)localObject7).setP2yh((byte)0);
          ((ArrayList)localObject4).add(localObject7);
        }
        if ((localObject3[paramInt6].property == 9) && (localObject3[paramInt6].x != -1) && (paramInt6 > 357) && (paramInt6 < 390) && (CommonUtils.getGPKeyCodeByNameScenes1(localObject3[paramInt6].name) != -1) && (paramInt3 >= 3))
        {
          localObject5 = new GPNormalKey();
          ((GPNormalKey)localObject5).setType((byte)8);
          ((GPNormalKey)localObject5).setCode((byte)CommonUtils.getGPKeyCodeByNameScenes1(localObject3[paramInt6].name));
          ((GPNormalKey)localObject5).setP1xl((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject3[paramInt6].x));
          ((GPNormalKey)localObject5).setP1xh((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject3[paramInt6].y));
          i = paramInt6 + 32;
          ((GPNormalKey)localObject5).setP1yl((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject3[i].x));
          ((GPNormalKey)localObject5).setP1yh((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject3[i].y));
          ((GPNormalKey)localObject5).setP2xl((byte)localObject3[paramInt6].radius);
          ((GPNormalKey)localObject5).setP2xh((byte)localObject3[paramInt6].block);
          ((GPNormalKey)localObject5).setP2yl((byte)0);
          ((GPNormalKey)localObject5).setP2yh((byte)0);
          if ((((GPNormalKey)localObject5).getP1xl() == ((GPNormalKey)localObject5).getP1yl()) && (((GPNormalKey)localObject5).getP1xh() == ((GPNormalKey)localObject5).getP1yh()))
          {
            ((GPNormalKey)localObject5).setP1yl((byte)(((GPNormalKey)localObject5).getP1xl() + 1));
            ((GPNormalKey)localObject5).setP1yh((byte)(((GPNormalKey)localObject5).getP1xh() + 1));
          }
          ((ArrayList)localObject4).add(localObject5);
        }
        if ((localObject3[paramInt6].property == 10) && (localObject3[paramInt6].x != -1) && (paramInt6 > 421) && (paramInt6 < 454) && (CommonUtils.getGPKeyCodeByNameScenes1(localObject3[paramInt6].name) != -1) && (paramInt3 >= 4))
        {
          localObject5 = ChangeDataUtil.intToByteArray(localObject3[paramInt6].x);
          localObject6 = ChangeDataUtil.intToByteArray(localObject3[paramInt6].y);
          localObject7 = new GPNormalKey();
          ((GPNormalKey)localObject7).setType((byte)9);
          ((GPNormalKey)localObject7).setCode((byte)CommonUtils.getGPKeyCodeByNameScenes1(localObject3[paramInt6].name));
          ((GPNormalKey)localObject7).setP1xl(localObject5[1]);
          ((GPNormalKey)localObject7).setP1xh(localObject5[0]);
          ((GPNormalKey)localObject7).setP1yl(localObject6[1]);
          ((GPNormalKey)localObject7).setP1yh(localObject6[0]);
          ((GPNormalKey)localObject7).setP2xl((byte)localObject3[paramInt6].radius);
          ((GPNormalKey)localObject7).setP2xh((byte)0);
          ((GPNormalKey)localObject7).setP2yl((byte)0);
          ((GPNormalKey)localObject7).setP2yh((byte)0);
          ((ArrayList)localObject4).add(localObject7);
        }
        if ((localObject3[paramInt6].property == 11) && (localObject3[paramInt6].x != -1) && (paramInt6 >= 22) && (paramInt6 <= 26) && (CommonUtils.getGPKeyCodeByNameScenes1(localObject3[paramInt6].name) != -1) && (paramInt3 >= 5))
        {
          localObject5 = new GPNormalKey();
          ((GPNormalKey)localObject5).setType((byte)10);
          ((GPNormalKey)localObject5).setCode((byte)CommonUtils.getGPKeyCodeByNameScenes1(localObject3[paramInt6].name));
          ((GPNormalKey)localObject5).setP1xl((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject3[paramInt6].x));
          ((GPNormalKey)localObject5).setP1xh((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject3[paramInt6].y));
          ((GPNormalKey)localObject5).setP1yl((byte)localObject3[paramInt6].radius);
          ((GPNormalKey)localObject5).setP1yh((byte)0);
          ((GPNormalKey)localObject5).setP2xl((byte)0);
          ((GPNormalKey)localObject5).setP2xh((byte)0);
          ((GPNormalKey)localObject5).setP2yl((byte)0);
          ((GPNormalKey)localObject5).setP2yh((byte)0);
          ((ArrayList)localObject4).add(localObject5);
        }
        if ((localObject3[paramInt6].property == 12) && (localObject3[paramInt6].x != -1) && (paramInt6 >= 22))
          if ((paramInt6 <= 26) && (CommonUtils.getGPKeyCodeByNameScenes1(localObject3[paramInt6].name) != -1))
          {
            if (paramInt3 >= 5)
            {
              localObject5 = new GPNormalKey();
              localObject6 = ChangeDataUtil.intToByteArray(localObject3[paramInt6].radius);
              ((GPNormalKey)localObject5).setType((byte)11);
              ((GPNormalKey)localObject5).setCode((byte)CommonUtils.getGPKeyCodeByNameScenes1(localObject3[paramInt6].name));
              ((GPNormalKey)localObject5).setP1xl((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject3[paramInt6].x));
              ((GPNormalKey)localObject5).setP1xh((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject3[paramInt6].y));
              ((GPNormalKey)localObject5).setP1yl(localObject6[1]);
              ((GPNormalKey)localObject5).setP1yh(localObject6[0]);
              ((GPNormalKey)localObject5).setP2xl((byte)localObject3[paramInt6].block);
              ((GPNormalKey)localObject5).setP2xh((byte)localObject3[paramInt6].reverse);
              ((GPNormalKey)localObject5).setP2yl((byte)0);
              ((GPNormalKey)localObject5).setP2yh((byte)0);
              ((ArrayList)localObject4).add(localObject5);
            }
          }
          else;
        if ((localObject3[paramInt6].property == 14) && (localObject3[paramInt6].x != -1) && (paramInt6 > 933) && (CommonUtils.getGPKeyCodeByNameScenes1(localObject3[paramInt6].name) != -1))
        {
          localObject5 = new GPNormalKey();
          ((GPNormalKey)localObject5).setType((byte)15);
          ((GPNormalKey)localObject5).setCode((byte)CommonUtils.getGPKeyCodeByNameScenes1(localObject3[paramInt6].name));
          ((GPNormalKey)localObject5).setP1xl((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject3[paramInt6].x));
          ((GPNormalKey)localObject5).setP1xh((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject3[paramInt6].y));
          ((GPNormalKey)localObject5).setP1yl((byte)localObject3[paramInt6].radius);
          ((GPNormalKey)localObject5).setP1yh((byte)localObject3[paramInt6].block);
          if ((CommonUtils.isStringValid(localObject3[paramInt6].view_joystick)) && (!localObject3[paramInt6].view_joystick.equalsIgnoreCase("-1")))
            ((GPNormalKey)localObject5).setP2xl((byte)CommonUtils.getGPKeyCodeByNameScenes1(localObject3[paramInt6].view_joystick));
          else
            ((GPNormalKey)localObject5).setP2xl((byte)0);
          ((GPNormalKey)localObject5).setP2xh((byte)localObject3[paramInt6].turb_speed);
          ((GPNormalKey)localObject5).setP2yl((byte)0);
          ((GPNormalKey)localObject5).setP2yh((byte)0);
          ((ArrayList)localObject4).add(localObject5);
        }
        if ((localObject3[paramInt6].property == 13) && (localObject3[paramInt6].x != -1) && (paramInt6 > 453) && (paramInt6 < 486) && (CommonUtils.getGPKeyCodeByNameScenes1(localObject3[paramInt6].name) != -1))
        {
          localObject5 = new StringBuilder();
          ((StringBuilder)localObject5).append("----i = ");
          ((StringBuilder)localObject5).append(paramInt6);
          MyLog.e("my_tag", ((StringBuilder)localObject5).toString());
          localObject5 = new MacroKey();
          ((MacroKey)localObject5).setType((byte)14);
          ((MacroKey)localObject5).setKeyCode((byte)CommonUtils.getGPKeyCodeByNameScenes1(localObject3[paramInt6].name));
          ((MacroKey)localObject5).setPoint0X((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject3[paramInt6].x));
          ((MacroKey)localObject5).setPoint0Y((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject3[paramInt6].y));
          ((MacroKey)localObject5).setPoint0MacroTime((byte)localObject3[paramInt6].radius);
          ((MacroKey)localObject5).setPoint0PauseTime((byte)localObject3[paramInt6].block);
          i = paramInt6 + 32;
          ((MacroKey)localObject5).setPoint1X((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject3[i].x));
          ((MacroKey)localObject5).setPoint1Y((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject3[i].y));
          ((MacroKey)localObject5).setPoint1MacroTime((byte)localObject3[i].radius);
          ((MacroKey)localObject5).setPoint1PauseTime((byte)localObject3[i].block);
          i = paramInt6 + 64;
          if ((localObject3[i].x != -1) && (localObject3[i].y != -1) && (localObject3[i].radius != -1) && (localObject3[i].block != -1))
          {
            ((MacroKey)localObject5).setPoint2X((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject3[i].x));
            ((MacroKey)localObject5).setPoint2Y((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject3[i].y));
            ((MacroKey)localObject5).setPoint2MacroTime((byte)localObject3[i].radius);
            ((MacroKey)localObject5).setPoint2PauseTime((byte)localObject3[i].block);
          }
          else
          {
            ((MacroKey)localObject5).setPoint2X((byte)0);
            ((MacroKey)localObject5).setPoint2Y((byte)0);
            ((MacroKey)localObject5).setPoint2MacroTime((byte)0);
            ((MacroKey)localObject5).setPoint2PauseTime((byte)0);
          }
          i = paramInt6 + 96;
          if ((localObject3[i].x != -1) && (localObject3[i].y != -1) && (localObject3[i].radius != -1) && (localObject3[i].block != -1))
          {
            ((MacroKey)localObject5).setPoint3X((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject3[i].x));
            ((MacroKey)localObject5).setPoint3Y((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject3[i].y));
            ((MacroKey)localObject5).setPoint3MacroTime((byte)localObject3[i].radius);
            ((MacroKey)localObject5).setPoint3PauseTime((byte)localObject3[i].block);
          }
          else
          {
            ((MacroKey)localObject5).setPoint3X((byte)0);
            ((MacroKey)localObject5).setPoint3Y((byte)0);
            ((MacroKey)localObject5).setPoint3MacroTime((byte)0);
            ((MacroKey)localObject5).setPoint3PauseTime((byte)0);
          }
          i = paramInt6 + 128;
          if ((localObject3[i].x != -1) && (localObject3[i].y != -1) && (localObject3[i].radius != -1) && (localObject3[i].block != -1))
          {
            ((MacroKey)localObject5).setPoint4X((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject3[i].x));
            ((MacroKey)localObject5).setPoint4Y((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject3[i].y));
            ((MacroKey)localObject5).setPoint4MacroTime((byte)localObject3[i].radius);
            ((MacroKey)localObject5).setPoint4PauseTime((byte)localObject3[i].block);
          }
          else
          {
            ((MacroKey)localObject5).setPoint4X((byte)0);
            ((MacroKey)localObject5).setPoint4Y((byte)0);
            ((MacroKey)localObject5).setPoint4MacroTime((byte)0);
            ((MacroKey)localObject5).setPoint4PauseTime((byte)0);
          }
          i = paramInt6 + 160;
          if ((localObject3[i].x != -1) && (localObject3[i].y != -1) && (localObject3[i].radius != -1) && (localObject3[i].block != -1))
          {
            ((MacroKey)localObject5).setPoint5X((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject3[i].x));
            ((MacroKey)localObject5).setPoint5Y((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject3[i].y));
            ((MacroKey)localObject5).setPoint5MacroTime((byte)localObject3[i].radius);
            ((MacroKey)localObject5).setPoint5PauseTime((byte)localObject3[i].block);
          }
          else
          {
            ((MacroKey)localObject5).setPoint5X((byte)0);
            ((MacroKey)localObject5).setPoint5Y((byte)0);
            ((MacroKey)localObject5).setPoint5MacroTime((byte)0);
            ((MacroKey)localObject5).setPoint5PauseTime((byte)0);
          }
          i = paramInt6 + 192;
          if ((localObject3[i].x != -1) && (localObject3[i].y != -1) && (localObject3[i].radius != -1) && (localObject3[i].block != -1))
          {
            ((MacroKey)localObject5).setPoint6X((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject3[i].x));
            ((MacroKey)localObject5).setPoint6Y((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject3[i].y));
            ((MacroKey)localObject5).setPoint6MacroTime((byte)localObject3[i].radius);
            ((MacroKey)localObject5).setPoint6PauseTime((byte)localObject3[i].block);
          }
          else
          {
            ((MacroKey)localObject5).setPoint6X((byte)0);
            ((MacroKey)localObject5).setPoint6Y((byte)0);
            ((MacroKey)localObject5).setPoint6MacroTime((byte)0);
            ((MacroKey)localObject5).setPoint6PauseTime((byte)0);
          }
          i = paramInt6 + 224;
          if ((localObject3[i].x != -1) && (localObject3[i].y != -1) && (localObject3[i].radius != -1) && (localObject3[i].block != -1))
          {
            ((MacroKey)localObject5).setPoint7X((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject3[i].x));
            ((MacroKey)localObject5).setPoint7Y((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject3[i].y));
            ((MacroKey)localObject5).setPoint7MacroTime((byte)localObject3[i].radius);
            ((MacroKey)localObject5).setPoint7PauseTime((byte)localObject3[i].block);
          }
          else
          {
            ((MacroKey)localObject5).setPoint7X((byte)0);
            ((MacroKey)localObject5).setPoint7Y((byte)0);
            ((MacroKey)localObject5).setPoint7MacroTime((byte)0);
            ((MacroKey)localObject5).setPoint7PauseTime((byte)0);
          }
          i = paramInt6 + 256;
          if ((localObject3[i].x != -1) && (localObject3[i].y != -1) && (localObject3[i].radius != -1) && (localObject3[i].block != -1))
          {
            ((MacroKey)localObject5).setPoint8X((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject3[i].x));
            ((MacroKey)localObject5).setPoint8Y((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject3[i].y));
            ((MacroKey)localObject5).setPoint8MacroTime((byte)localObject3[i].radius);
            ((MacroKey)localObject5).setPoint8PauseTime((byte)localObject3[i].block);
          }
          else
          {
            ((MacroKey)localObject5).setPoint8X((byte)0);
            ((MacroKey)localObject5).setPoint8Y((byte)0);
            ((MacroKey)localObject5).setPoint8MacroTime((byte)0);
            ((MacroKey)localObject5).setPoint8PauseTime((byte)0);
          }
          i = paramInt6 + 288;
          if ((localObject3[i].x != -1) && (localObject3[i].y != -1) && (localObject3[i].radius != -1) && (localObject3[i].block != -1))
          {
            ((MacroKey)localObject5).setPoint9X((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject3[i].x));
            ((MacroKey)localObject5).setPoint9Y((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject3[i].y));
            ((MacroKey)localObject5).setPoint9MacroTime((byte)localObject3[i].radius);
            ((MacroKey)localObject5).setPoint9PauseTime((byte)localObject3[i].block);
          }
          else
          {
            ((MacroKey)localObject5).setPoint9X((byte)0);
            ((MacroKey)localObject5).setPoint9Y((byte)0);
            ((MacroKey)localObject5).setPoint9MacroTime((byte)0);
            ((MacroKey)localObject5).setPoint9PauseTime((byte)0);
          }
          i = paramInt6 + 320;
          if ((localObject3[i].x != -1) && (localObject3[i].y != -1) && (localObject3[i].radius != -1) && (localObject3[i].block != -1))
          {
            ((MacroKey)localObject5).setPoint10X((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject3[i].x));
            ((MacroKey)localObject5).setPoint10Y((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject3[i].y));
            ((MacroKey)localObject5).setPoint10MacroTime((byte)localObject3[i].radius);
            ((MacroKey)localObject5).setPoint10PauseTime((byte)localObject3[i].block);
          }
          else
          {
            ((MacroKey)localObject5).setPoint10X((byte)0);
            ((MacroKey)localObject5).setPoint10Y((byte)0);
            ((MacroKey)localObject5).setPoint10MacroTime((byte)0);
            ((MacroKey)localObject5).setPoint10PauseTime((byte)0);
          }
          i = paramInt6 + 352;
          if ((localObject3[i].x != -1) && (localObject3[i].y != -1) && (localObject3[i].radius != -1) && (localObject3[i].block != -1))
          {
            ((MacroKey)localObject5).setPoint11X((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject3[i].x));
            ((MacroKey)localObject5).setPoint11Y((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject3[i].y));
            ((MacroKey)localObject5).setPoint11MacroTime((byte)localObject3[i].radius);
            ((MacroKey)localObject5).setPoint11PauseTime((byte)localObject3[i].block);
          }
          else
          {
            ((MacroKey)localObject5).setPoint11X((byte)0);
            ((MacroKey)localObject5).setPoint11Y((byte)0);
            ((MacroKey)localObject5).setPoint11MacroTime((byte)0);
            ((MacroKey)localObject5).setPoint11PauseTime((byte)0);
          }
          i = paramInt6 + 384;
          if ((localObject3[i].x != -1) && (localObject3[i].y != -1) && (localObject3[i].radius != -1) && (localObject3[i].block != -1))
          {
            ((MacroKey)localObject5).setPoint12X((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject3[i].x));
            ((MacroKey)localObject5).setPoint12Y((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject3[i].y));
            ((MacroKey)localObject5).setPoint12MacroTime((byte)localObject3[i].radius);
            ((MacroKey)localObject5).setPoint12PauseTime((byte)localObject3[i].block);
          }
          else
          {
            ((MacroKey)localObject5).setPoint12X((byte)0);
            ((MacroKey)localObject5).setPoint12Y((byte)0);
            ((MacroKey)localObject5).setPoint12MacroTime((byte)0);
            ((MacroKey)localObject5).setPoint12PauseTime((byte)0);
          }
          i = paramInt6 + 416;
          if ((localObject3[i].x != -1) && (localObject3[i].y != -1) && (localObject3[i].radius != -1) && (localObject3[i].block != -1))
          {
            ((MacroKey)localObject5).setPoint13X((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject3[i].x));
            ((MacroKey)localObject5).setPoint13Y((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject3[i].y));
            ((MacroKey)localObject5).setPoint13MacroTime((byte)localObject3[i].radius);
            ((MacroKey)localObject5).setPoint13PauseTime((byte)localObject3[i].block);
          }
          else
          {
            ((MacroKey)localObject5).setPoint13X((byte)0);
            ((MacroKey)localObject5).setPoint13Y((byte)0);
            ((MacroKey)localObject5).setPoint13MacroTime((byte)0);
            ((MacroKey)localObject5).setPoint13PauseTime((byte)0);
          }
          i = paramInt6 + 448;
          if ((localObject3[i].x != -1) && (localObject3[i].y != -1) && (localObject3[i].radius != -1) && (localObject3[i].block != -1))
          {
            ((MacroKey)localObject5).setPoint14X((byte)ChangeDataUtil.get16BitByValue(paramInt2, localObject3[i].x));
            ((MacroKey)localObject5).setPoint14Y((byte)ChangeDataUtil.get16BitByValue(paramInt1, localObject3[i].y));
            ((MacroKey)localObject5).setPoint14MacroTime((byte)localObject3[i].radius);
            ((MacroKey)localObject5).setPoint14PauseTime((byte)localObject3[i].block);
          }
          else
          {
            ((MacroKey)localObject5).setPoint14X((byte)0);
            ((MacroKey)localObject5).setPoint14Y((byte)0);
            ((MacroKey)localObject5).setPoint14MacroTime((byte)0);
            ((MacroKey)localObject5).setPoint14PauseTime((byte)0);
          }
          ((ArrayList)localObject2).add(localObject5);
        }
        paramInt6 += 1;
      }
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append("------ key size = ");
      ((StringBuilder)localObject3).append(((ArrayList)localObject4).size());
      MyLog.i("my_tag", ((StringBuilder)localObject3).toString());
      paramInt1 = 0;
      while (paramInt1 < ((ArrayList)localObject4).size())
      {
        paramInt2 = paramInt1 * 10;
        paramInt3 = paramInt2 + 19;
        if (paramInt3 < localObject1.length)
        {
          localObject1[(paramInt2 + 10)] = ((GPNormalKey)((ArrayList)localObject4).get(paramInt1)).getType();
          localObject1[(paramInt2 + 11)] = ((GPNormalKey)((ArrayList)localObject4).get(paramInt1)).getCode();
          localObject1[(paramInt2 + 12)] = ((GPNormalKey)((ArrayList)localObject4).get(paramInt1)).getP1xl();
          localObject1[(paramInt2 + 13)] = ((GPNormalKey)((ArrayList)localObject4).get(paramInt1)).getP1xh();
          localObject1[(paramInt2 + 14)] = ((GPNormalKey)((ArrayList)localObject4).get(paramInt1)).getP1yl();
          localObject1[(paramInt2 + 15)] = ((GPNormalKey)((ArrayList)localObject4).get(paramInt1)).getP1yh();
          localObject1[(paramInt2 + 16)] = ((GPNormalKey)((ArrayList)localObject4).get(paramInt1)).getP2xl();
          localObject1[(paramInt2 + 17)] = ((GPNormalKey)((ArrayList)localObject4).get(paramInt1)).getP2xh();
          localObject1[(paramInt2 + 18)] = ((GPNormalKey)((ArrayList)localObject4).get(paramInt1)).getP2yl();
          localObject1[paramInt3] = ((GPNormalKey)((ArrayList)localObject4).get(paramInt1)).getP2yh();
        }
        paramInt1 += 1;
      }
      paramInt1 = 0;
      while (paramInt1 < ((ArrayList)localObject2).size())
      {
        localObject3 = MacroKeyUtils.AnalysisMacroKey((MacroKey)((ArrayList)localObject2).get(paramInt1));
        if (paramInt4 + 9 + localObject3.length < localObject1.length)
          System.arraycopy(localObject3, 0, localObject1, paramInt5 + localObject3.length * paramInt1, localObject3.length);
        paramInt1 += 1;
      }
      localObject2 = new byte[localObject1.length - 2];
      System.arraycopy(localObject1, 2, localObject2, 0, localObject1.length - 2);
      paramInt1 = CommonUtils.CRC_GetModbus16((byte[])localObject2, localObject1.length - 2);
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("------save checkCode = ");
      ((StringBuilder)localObject2).append(paramInt1);
      MyLog.i("crc_tag", ((StringBuilder)localObject2).toString());
      localObject2 = ChangeDataUtil.intToByteArray(paramInt1);
      localObject1[0] = localObject2[1];
      localObject1[1] = localObject2[0];
      return localObject1;
    }
    return null;
  }

  static void initMacroKey(GPKey paramGPKey, MacroKey paramMacroKey, float paramFloat1, float paramFloat2)
  {
    gpMmdatasaver[(paramGPKey._id + 454)].property = 13;
    gpMmdatasaver[(paramGPKey._id + 454)].name = paramGPKey.name;
    gpMmdatasaver[(paramGPKey._id + 454)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, paramMacroKey.getPoint0Y() & 0xFF) * paramFloat1 / deviceY));
    gpMmdatasaver[(paramGPKey._id + 454)].y = ((int)((deviceX - ChangeDataUtil.getValueBy16Bit(deviceX, paramMacroKey.getPoint0X() & 0xFF)) * paramFloat2 / deviceX));
    gpMmdatasaver[(paramGPKey._id + 454)].radius = (paramMacroKey.getPoint0MacroTime() & 0xFF);
    gpMmdatasaver[(paramGPKey._id + 454)].block = (paramMacroKey.getPoint0PauseTime() & 0xFF);
    if (gpMmdatasaver[(paramGPKey._id + 454)].block == 255)
      return;
    gpMmdatasaver[(paramGPKey._id + 486)].property = 13;
    gpMmdatasaver[(paramGPKey._id + 486)].name = paramGPKey.name;
    gpMmdatasaver[(paramGPKey._id + 486)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, paramMacroKey.getPoint1Y() & 0xFF) * paramFloat1 / deviceY));
    gpMmdatasaver[(paramGPKey._id + 486)].y = ((int)((deviceX - ChangeDataUtil.getValueBy16Bit(deviceX, paramMacroKey.getPoint1X() & 0xFF)) * paramFloat2 / deviceX));
    gpMmdatasaver[(paramGPKey._id + 486)].radius = (paramMacroKey.getPoint1MacroTime() & 0xFF);
    gpMmdatasaver[(paramGPKey._id + 486)].block = (paramMacroKey.getPoint1PauseTime() & 0xFF);
    if (gpMmdatasaver[(paramGPKey._id + 486)].block == 255)
      return;
    gpMmdatasaver[(paramGPKey._id + 518)].property = 13;
    gpMmdatasaver[(paramGPKey._id + 518)].name = paramGPKey.name;
    gpMmdatasaver[(paramGPKey._id + 518)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, paramMacroKey.getPoint2Y() & 0xFF) * paramFloat1 / deviceY));
    gpMmdatasaver[(paramGPKey._id + 518)].y = ((int)((deviceX - ChangeDataUtil.getValueBy16Bit(deviceX, paramMacroKey.getPoint2X() & 0xFF)) * paramFloat2 / deviceX));
    gpMmdatasaver[(paramGPKey._id + 518)].radius = (paramMacroKey.getPoint2MacroTime() & 0xFF);
    gpMmdatasaver[(paramGPKey._id + 518)].block = (paramMacroKey.getPoint2PauseTime() & 0xFF);
    if (gpMmdatasaver[(paramGPKey._id + 518)].block == 255)
      return;
    gpMmdatasaver[(paramGPKey._id + 550)].property = 13;
    gpMmdatasaver[(paramGPKey._id + 550)].name = paramGPKey.name;
    gpMmdatasaver[(paramGPKey._id + 550)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, paramMacroKey.getPoint3Y() & 0xFF) * paramFloat1 / deviceY));
    gpMmdatasaver[(paramGPKey._id + 550)].y = ((int)((deviceX - ChangeDataUtil.getValueBy16Bit(deviceX, paramMacroKey.getPoint3X() & 0xFF)) * paramFloat2 / deviceX));
    gpMmdatasaver[(paramGPKey._id + 550)].radius = (paramMacroKey.getPoint3MacroTime() & 0xFF);
    gpMmdatasaver[(paramGPKey._id + 550)].block = (paramMacroKey.getPoint3PauseTime() & 0xFF);
    if (gpMmdatasaver[(paramGPKey._id + 550)].block == 255)
      return;
    gpMmdatasaver[(paramGPKey._id + 582)].property = 13;
    gpMmdatasaver[(paramGPKey._id + 582)].name = paramGPKey.name;
    gpMmdatasaver[(paramGPKey._id + 582)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, paramMacroKey.getPoint4Y() & 0xFF) * paramFloat1 / deviceY));
    gpMmdatasaver[(paramGPKey._id + 582)].y = ((int)((deviceX - ChangeDataUtil.getValueBy16Bit(deviceX, paramMacroKey.getPoint4X() & 0xFF)) * paramFloat2 / deviceX));
    gpMmdatasaver[(paramGPKey._id + 582)].radius = (paramMacroKey.getPoint4MacroTime() & 0xFF);
    gpMmdatasaver[(paramGPKey._id + 582)].block = (paramMacroKey.getPoint4PauseTime() & 0xFF);
    if (gpMmdatasaver[(paramGPKey._id + 582)].block == 255)
      return;
    gpMmdatasaver[(paramGPKey._id + 614)].property = 13;
    gpMmdatasaver[(paramGPKey._id + 614)].name = paramGPKey.name;
    gpMmdatasaver[(paramGPKey._id + 614)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, paramMacroKey.getPoint5Y() & 0xFF) * paramFloat1 / deviceY));
    gpMmdatasaver[(paramGPKey._id + 614)].y = ((int)((deviceX - ChangeDataUtil.getValueBy16Bit(deviceX, paramMacroKey.getPoint5X() & 0xFF)) * paramFloat2 / deviceX));
    gpMmdatasaver[(paramGPKey._id + 614)].radius = (paramMacroKey.getPoint5MacroTime() & 0xFF);
    gpMmdatasaver[(paramGPKey._id + 614)].block = (paramMacroKey.getPoint5PauseTime() & 0xFF);
    if (gpMmdatasaver[(paramGPKey._id + 614)].block == 255)
      return;
    gpMmdatasaver[(paramGPKey._id + 646)].property = 13;
    gpMmdatasaver[(paramGPKey._id + 646)].name = paramGPKey.name;
    gpMmdatasaver[(paramGPKey._id + 646)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, paramMacroKey.getPoint6Y() & 0xFF) * paramFloat1 / deviceY));
    gpMmdatasaver[(paramGPKey._id + 646)].y = ((int)((deviceX - ChangeDataUtil.getValueBy16Bit(deviceX, paramMacroKey.getPoint6X() & 0xFF)) * paramFloat2 / deviceX));
    gpMmdatasaver[(paramGPKey._id + 646)].radius = (paramMacroKey.getPoint6MacroTime() & 0xFF);
    gpMmdatasaver[(paramGPKey._id + 646)].block = (paramMacroKey.getPoint6PauseTime() & 0xFF);
    if (gpMmdatasaver[(paramGPKey._id + 646)].block == 255)
      return;
    gpMmdatasaver[(paramGPKey._id + 678)].property = 13;
    gpMmdatasaver[(paramGPKey._id + 678)].name = paramGPKey.name;
    gpMmdatasaver[(paramGPKey._id + 678)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, paramMacroKey.getPoint7Y() & 0xFF) * paramFloat1 / deviceY));
    gpMmdatasaver[(paramGPKey._id + 678)].y = ((int)((deviceX - ChangeDataUtil.getValueBy16Bit(deviceX, paramMacroKey.getPoint7X() & 0xFF)) * paramFloat2 / deviceX));
    gpMmdatasaver[(paramGPKey._id + 678)].radius = (paramMacroKey.getPoint7MacroTime() & 0xFF);
    gpMmdatasaver[(paramGPKey._id + 678)].block = (paramMacroKey.getPoint7PauseTime() & 0xFF);
    if (gpMmdatasaver[(paramGPKey._id + 678)].block == 255)
      return;
    gpMmdatasaver[(paramGPKey._id + 710)].property = 13;
    gpMmdatasaver[(paramGPKey._id + 710)].name = paramGPKey.name;
    gpMmdatasaver[(paramGPKey._id + 710)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, paramMacroKey.getPoint8Y() & 0xFF) * paramFloat1 / deviceY));
    gpMmdatasaver[(paramGPKey._id + 710)].y = ((int)((deviceX - ChangeDataUtil.getValueBy16Bit(deviceX, paramMacroKey.getPoint8X() & 0xFF)) * paramFloat2 / deviceX));
    gpMmdatasaver[(paramGPKey._id + 710)].radius = (paramMacroKey.getPoint8MacroTime() & 0xFF);
    gpMmdatasaver[(paramGPKey._id + 710)].block = (paramMacroKey.getPoint8PauseTime() & 0xFF);
    if (gpMmdatasaver[(paramGPKey._id + 710)].block == 255)
      return;
    gpMmdatasaver[(paramGPKey._id + 742)].property = 13;
    gpMmdatasaver[(paramGPKey._id + 742)].name = paramGPKey.name;
    gpMmdatasaver[(paramGPKey._id + 742)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, paramMacroKey.getPoint9Y() & 0xFF) * paramFloat1 / deviceY));
    gpMmdatasaver[(paramGPKey._id + 742)].y = ((int)((deviceX - ChangeDataUtil.getValueBy16Bit(deviceX, paramMacroKey.getPoint9X() & 0xFF)) * paramFloat2 / deviceX));
    gpMmdatasaver[(paramGPKey._id + 742)].radius = (paramMacroKey.getPoint9MacroTime() & 0xFF);
    gpMmdatasaver[(paramGPKey._id + 742)].block = (paramMacroKey.getPoint9PauseTime() & 0xFF);
    if (gpMmdatasaver[(paramGPKey._id + 742)].block == 255)
      return;
    gpMmdatasaver[(paramGPKey._id + 774)].property = 13;
    gpMmdatasaver[(paramGPKey._id + 774)].name = paramGPKey.name;
    gpMmdatasaver[(paramGPKey._id + 774)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, paramMacroKey.getPoint10Y() & 0xFF) * paramFloat1 / deviceY));
    gpMmdatasaver[(paramGPKey._id + 774)].y = ((int)((deviceX - ChangeDataUtil.getValueBy16Bit(deviceX, paramMacroKey.getPoint10X() & 0xFF)) * paramFloat2 / deviceX));
    gpMmdatasaver[(paramGPKey._id + 774)].radius = (paramMacroKey.getPoint10MacroTime() & 0xFF);
    gpMmdatasaver[(paramGPKey._id + 774)].block = (paramMacroKey.getPoint10PauseTime() & 0xFF);
    if (gpMmdatasaver[(paramGPKey._id + 774)].block == 255)
      return;
    gpMmdatasaver[(paramGPKey._id + 806)].property = 13;
    gpMmdatasaver[(paramGPKey._id + 806)].name = paramGPKey.name;
    gpMmdatasaver[(paramGPKey._id + 806)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, paramMacroKey.getPoint11Y() & 0xFF) * paramFloat1 / deviceY));
    gpMmdatasaver[(paramGPKey._id + 806)].y = ((int)((deviceX - ChangeDataUtil.getValueBy16Bit(deviceX, paramMacroKey.getPoint11X() & 0xFF)) * paramFloat2 / deviceX));
    gpMmdatasaver[(paramGPKey._id + 806)].radius = (paramMacroKey.getPoint11MacroTime() & 0xFF);
    gpMmdatasaver[(paramGPKey._id + 806)].block = (paramMacroKey.getPoint11PauseTime() & 0xFF);
    if (gpMmdatasaver[(paramGPKey._id + 806)].block == 255)
      return;
    gpMmdatasaver[(paramGPKey._id + 838)].property = 13;
    gpMmdatasaver[(paramGPKey._id + 838)].name = paramGPKey.name;
    gpMmdatasaver[(paramGPKey._id + 838)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, paramMacroKey.getPoint12Y() & 0xFF) * paramFloat1 / deviceY));
    gpMmdatasaver[(paramGPKey._id + 838)].y = ((int)((deviceX - ChangeDataUtil.getValueBy16Bit(deviceX, paramMacroKey.getPoint12X() & 0xFF)) * paramFloat2 / deviceX));
    gpMmdatasaver[(paramGPKey._id + 838)].radius = (paramMacroKey.getPoint12MacroTime() & 0xFF);
    gpMmdatasaver[(paramGPKey._id + 838)].block = (paramMacroKey.getPoint12PauseTime() & 0xFF);
    if (gpMmdatasaver[(paramGPKey._id + 838)].block == 255)
      return;
    gpMmdatasaver[(paramGPKey._id + 870)].property = 13;
    gpMmdatasaver[(paramGPKey._id + 870)].name = paramGPKey.name;
    gpMmdatasaver[(paramGPKey._id + 870)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, paramMacroKey.getPoint13Y() & 0xFF) * paramFloat1 / deviceY));
    gpMmdatasaver[(paramGPKey._id + 870)].y = ((int)((deviceX - ChangeDataUtil.getValueBy16Bit(deviceX, paramMacroKey.getPoint13X() & 0xFF)) * paramFloat2 / deviceX));
    gpMmdatasaver[(paramGPKey._id + 870)].radius = (paramMacroKey.getPoint13MacroTime() & 0xFF);
    gpMmdatasaver[(paramGPKey._id + 870)].block = (paramMacroKey.getPoint13PauseTime() & 0xFF);
    if (gpMmdatasaver[(paramGPKey._id + 870)].block == 255)
      return;
    gpMmdatasaver[(paramGPKey._id + 902)].property = 13;
    gpMmdatasaver[(paramGPKey._id + 902)].name = paramGPKey.name;
    gpMmdatasaver[(paramGPKey._id + 902)].x = ((int)(paramFloat1 * ChangeDataUtil.getValueBy16Bit(deviceY, paramMacroKey.getPoint14Y() & 0xFF) / deviceY));
    gpMmdatasaver[(paramGPKey._id + 902)].y = ((int)(paramFloat2 * (deviceX - ChangeDataUtil.getValueBy16Bit(deviceX, paramMacroKey.getPoint14X() & 0xFF)) / deviceX));
    gpMmdatasaver[(paramGPKey._id + 902)].radius = (paramMacroKey.getPoint14MacroTime() & 0xFF);
    gpMmdatasaver[(paramGPKey._id + 902)].block = (paramMacroKey.getPoint14PauseTime() & 0xFF);
  }

  static void initMacroKeyPortraitScreen(GPKey paramGPKey, MacroKey paramMacroKey, float paramFloat1, float paramFloat2)
  {
    gpMmdatasaver[(paramGPKey._id + 454)].property = 13;
    gpMmdatasaver[(paramGPKey._id + 454)].name = paramGPKey.name;
    gpMmdatasaver[(paramGPKey._id + 454)].y = ((int)(ChangeDataUtil.getValueBy16Bit(deviceX, paramMacroKey.getPoint0Y() & 0xFF) * paramFloat1 / deviceY));
    gpMmdatasaver[(paramGPKey._id + 454)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, paramMacroKey.getPoint0X() & 0xFF) * paramFloat2 / deviceX));
    gpMmdatasaver[(paramGPKey._id + 454)].radius = (paramMacroKey.getPoint0MacroTime() & 0xFF);
    gpMmdatasaver[(paramGPKey._id + 454)].block = (paramMacroKey.getPoint0PauseTime() & 0xFF);
    if (gpMmdatasaver[(paramGPKey._id + 454)].block == 255)
      return;
    gpMmdatasaver[(paramGPKey._id + 486)].property = 13;
    gpMmdatasaver[(paramGPKey._id + 486)].name = paramGPKey.name;
    gpMmdatasaver[(paramGPKey._id + 486)].y = ((int)(ChangeDataUtil.getValueBy16Bit(deviceX, paramMacroKey.getPoint1Y() & 0xFF) * paramFloat1 / deviceY));
    gpMmdatasaver[(paramGPKey._id + 486)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, paramMacroKey.getPoint1X() & 0xFF) * paramFloat2 / deviceX));
    gpMmdatasaver[(paramGPKey._id + 486)].radius = (paramMacroKey.getPoint1MacroTime() & 0xFF);
    gpMmdatasaver[(paramGPKey._id + 486)].block = (paramMacroKey.getPoint1PauseTime() & 0xFF);
    if (gpMmdatasaver[(paramGPKey._id + 486)].block == 255)
      return;
    gpMmdatasaver[(paramGPKey._id + 518)].property = 13;
    gpMmdatasaver[(paramGPKey._id + 518)].name = paramGPKey.name;
    gpMmdatasaver[(paramGPKey._id + 518)].y = ((int)(ChangeDataUtil.getValueBy16Bit(deviceX, paramMacroKey.getPoint2Y() & 0xFF) * paramFloat1 / deviceY));
    gpMmdatasaver[(paramGPKey._id + 518)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, paramMacroKey.getPoint2X() & 0xFF) * paramFloat2 / deviceX));
    gpMmdatasaver[(paramGPKey._id + 518)].radius = (paramMacroKey.getPoint2MacroTime() & 0xFF);
    gpMmdatasaver[(paramGPKey._id + 518)].block = (paramMacroKey.getPoint2PauseTime() & 0xFF);
    if (gpMmdatasaver[(paramGPKey._id + 518)].block == 255)
      return;
    gpMmdatasaver[(paramGPKey._id + 550)].property = 13;
    gpMmdatasaver[(paramGPKey._id + 550)].name = paramGPKey.name;
    gpMmdatasaver[(paramGPKey._id + 550)].y = ((int)(ChangeDataUtil.getValueBy16Bit(deviceX, paramMacroKey.getPoint3Y() & 0xFF) * paramFloat1 / deviceY));
    gpMmdatasaver[(paramGPKey._id + 550)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, paramMacroKey.getPoint3X() & 0xFF) * paramFloat2 / deviceX));
    gpMmdatasaver[(paramGPKey._id + 550)].radius = (paramMacroKey.getPoint3MacroTime() & 0xFF);
    gpMmdatasaver[(paramGPKey._id + 550)].block = (paramMacroKey.getPoint3PauseTime() & 0xFF);
    if (gpMmdatasaver[(paramGPKey._id + 550)].block == 255)
      return;
    gpMmdatasaver[(paramGPKey._id + 582)].property = 13;
    gpMmdatasaver[(paramGPKey._id + 582)].name = paramGPKey.name;
    gpMmdatasaver[(paramGPKey._id + 582)].y = ((int)(ChangeDataUtil.getValueBy16Bit(deviceX, paramMacroKey.getPoint4Y() & 0xFF) * paramFloat1 / deviceY));
    gpMmdatasaver[(paramGPKey._id + 582)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, paramMacroKey.getPoint4X() & 0xFF) * paramFloat2 / deviceX));
    gpMmdatasaver[(paramGPKey._id + 582)].radius = (paramMacroKey.getPoint4MacroTime() & 0xFF);
    gpMmdatasaver[(paramGPKey._id + 582)].block = (paramMacroKey.getPoint4PauseTime() & 0xFF);
    if (gpMmdatasaver[(paramGPKey._id + 582)].block == 255)
      return;
    gpMmdatasaver[(paramGPKey._id + 614)].property = 13;
    gpMmdatasaver[(paramGPKey._id + 614)].name = paramGPKey.name;
    gpMmdatasaver[(paramGPKey._id + 614)].y = ((int)(ChangeDataUtil.getValueBy16Bit(deviceX, paramMacroKey.getPoint5Y() & 0xFF) * paramFloat1 / deviceY));
    gpMmdatasaver[(paramGPKey._id + 614)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, paramMacroKey.getPoint5X() & 0xFF) * paramFloat2 / deviceX));
    gpMmdatasaver[(paramGPKey._id + 614)].radius = (paramMacroKey.getPoint5MacroTime() & 0xFF);
    gpMmdatasaver[(paramGPKey._id + 614)].block = (paramMacroKey.getPoint5PauseTime() & 0xFF);
    if (gpMmdatasaver[(paramGPKey._id + 614)].block == 255)
      return;
    gpMmdatasaver[(paramGPKey._id + 646)].property = 13;
    gpMmdatasaver[(paramGPKey._id + 646)].name = paramGPKey.name;
    gpMmdatasaver[(paramGPKey._id + 646)].y = ((int)(ChangeDataUtil.getValueBy16Bit(deviceX, paramMacroKey.getPoint6Y() & 0xFF) * paramFloat1 / deviceY));
    gpMmdatasaver[(paramGPKey._id + 646)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, paramMacroKey.getPoint6X() & 0xFF) * paramFloat2 / deviceX));
    gpMmdatasaver[(paramGPKey._id + 646)].radius = (paramMacroKey.getPoint6MacroTime() & 0xFF);
    gpMmdatasaver[(paramGPKey._id + 646)].block = (paramMacroKey.getPoint6PauseTime() & 0xFF);
    if (gpMmdatasaver[(paramGPKey._id + 646)].block == 255)
      return;
    gpMmdatasaver[(paramGPKey._id + 678)].property = 13;
    gpMmdatasaver[(paramGPKey._id + 678)].name = paramGPKey.name;
    gpMmdatasaver[(paramGPKey._id + 678)].y = ((int)(ChangeDataUtil.getValueBy16Bit(deviceX, paramMacroKey.getPoint7Y() & 0xFF) * paramFloat1 / deviceY));
    gpMmdatasaver[(paramGPKey._id + 678)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, paramMacroKey.getPoint7X() & 0xFF) * paramFloat2 / deviceX));
    gpMmdatasaver[(paramGPKey._id + 678)].radius = (paramMacroKey.getPoint7MacroTime() & 0xFF);
    gpMmdatasaver[(paramGPKey._id + 678)].block = (paramMacroKey.getPoint7PauseTime() & 0xFF);
    if (gpMmdatasaver[(paramGPKey._id + 678)].block == 255)
      return;
    gpMmdatasaver[(paramGPKey._id + 710)].property = 13;
    gpMmdatasaver[(paramGPKey._id + 710)].name = paramGPKey.name;
    gpMmdatasaver[(paramGPKey._id + 710)].y = ((int)(ChangeDataUtil.getValueBy16Bit(deviceX, paramMacroKey.getPoint8Y() & 0xFF) * paramFloat1 / deviceY));
    gpMmdatasaver[(paramGPKey._id + 710)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, paramMacroKey.getPoint8X() & 0xFF) * paramFloat2 / deviceX));
    gpMmdatasaver[(paramGPKey._id + 710)].radius = (paramMacroKey.getPoint8MacroTime() & 0xFF);
    gpMmdatasaver[(paramGPKey._id + 710)].block = (paramMacroKey.getPoint8PauseTime() & 0xFF);
    if (gpMmdatasaver[(paramGPKey._id + 710)].block == 255)
      return;
    gpMmdatasaver[(paramGPKey._id + 742)].property = 13;
    gpMmdatasaver[(paramGPKey._id + 742)].name = paramGPKey.name;
    gpMmdatasaver[(paramGPKey._id + 742)].y = ((int)(ChangeDataUtil.getValueBy16Bit(deviceX, paramMacroKey.getPoint9Y() & 0xFF) * paramFloat1 / deviceY));
    gpMmdatasaver[(paramGPKey._id + 742)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, paramMacroKey.getPoint9X() & 0xFF) * paramFloat2 / deviceX));
    gpMmdatasaver[(paramGPKey._id + 742)].radius = (paramMacroKey.getPoint9MacroTime() & 0xFF);
    gpMmdatasaver[(paramGPKey._id + 742)].block = (paramMacroKey.getPoint9PauseTime() & 0xFF);
    if (gpMmdatasaver[(paramGPKey._id + 742)].block == 255)
      return;
    gpMmdatasaver[(paramGPKey._id + 774)].property = 13;
    gpMmdatasaver[(paramGPKey._id + 774)].name = paramGPKey.name;
    gpMmdatasaver[(paramGPKey._id + 774)].y = ((int)(ChangeDataUtil.getValueBy16Bit(deviceX, paramMacroKey.getPoint10Y() & 0xFF) * paramFloat1 / deviceY));
    gpMmdatasaver[(paramGPKey._id + 774)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, paramMacroKey.getPoint10X() & 0xFF) * paramFloat2 / deviceX));
    gpMmdatasaver[(paramGPKey._id + 774)].radius = (paramMacroKey.getPoint10MacroTime() & 0xFF);
    gpMmdatasaver[(paramGPKey._id + 774)].block = (paramMacroKey.getPoint10PauseTime() & 0xFF);
    if (gpMmdatasaver[(paramGPKey._id + 774)].block == 255)
      return;
    gpMmdatasaver[(paramGPKey._id + 806)].property = 13;
    gpMmdatasaver[(paramGPKey._id + 806)].name = paramGPKey.name;
    gpMmdatasaver[(paramGPKey._id + 806)].y = ((int)(ChangeDataUtil.getValueBy16Bit(deviceX, paramMacroKey.getPoint11Y() & 0xFF) * paramFloat1 / deviceY));
    gpMmdatasaver[(paramGPKey._id + 806)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, paramMacroKey.getPoint11X() & 0xFF) * paramFloat2 / deviceX));
    gpMmdatasaver[(paramGPKey._id + 806)].radius = (paramMacroKey.getPoint11MacroTime() & 0xFF);
    gpMmdatasaver[(paramGPKey._id + 806)].block = (paramMacroKey.getPoint11PauseTime() & 0xFF);
    if (gpMmdatasaver[(paramGPKey._id + 806)].block == 255)
      return;
    gpMmdatasaver[(paramGPKey._id + 838)].property = 13;
    gpMmdatasaver[(paramGPKey._id + 838)].name = paramGPKey.name;
    gpMmdatasaver[(paramGPKey._id + 838)].y = ((int)(ChangeDataUtil.getValueBy16Bit(deviceX, paramMacroKey.getPoint12Y() & 0xFF) * paramFloat1 / deviceY));
    gpMmdatasaver[(paramGPKey._id + 838)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, paramMacroKey.getPoint12X() & 0xFF) * paramFloat2 / deviceX));
    gpMmdatasaver[(paramGPKey._id + 838)].radius = (paramMacroKey.getPoint12MacroTime() & 0xFF);
    gpMmdatasaver[(paramGPKey._id + 838)].block = (paramMacroKey.getPoint12PauseTime() & 0xFF);
    if (gpMmdatasaver[(paramGPKey._id + 838)].block == 255)
      return;
    gpMmdatasaver[(paramGPKey._id + 870)].property = 13;
    gpMmdatasaver[(paramGPKey._id + 870)].name = paramGPKey.name;
    gpMmdatasaver[(paramGPKey._id + 870)].y = ((int)(ChangeDataUtil.getValueBy16Bit(deviceX, paramMacroKey.getPoint13Y() & 0xFF) * paramFloat1 / deviceY));
    gpMmdatasaver[(paramGPKey._id + 870)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, paramMacroKey.getPoint13X() & 0xFF) * paramFloat2 / deviceX));
    gpMmdatasaver[(paramGPKey._id + 870)].radius = (paramMacroKey.getPoint13MacroTime() & 0xFF);
    gpMmdatasaver[(paramGPKey._id + 870)].block = (paramMacroKey.getPoint13PauseTime() & 0xFF);
    if (gpMmdatasaver[(paramGPKey._id + 870)].block == 255)
      return;
    gpMmdatasaver[(paramGPKey._id + 902)].property = 13;
    gpMmdatasaver[(paramGPKey._id + 902)].name = paramGPKey.name;
    gpMmdatasaver[(paramGPKey._id + 902)].y = ((int)(paramFloat1 * ChangeDataUtil.getValueBy16Bit(deviceX, paramMacroKey.getPoint14Y() & 0xFF) / deviceY));
    gpMmdatasaver[(paramGPKey._id + 902)].x = ((int)(paramFloat2 * ChangeDataUtil.getValueBy16Bit(deviceY, paramMacroKey.getPoint14X() & 0xFF) / deviceX));
    gpMmdatasaver[(paramGPKey._id + 902)].radius = (paramMacroKey.getPoint14MacroTime() & 0xFF);
    gpMmdatasaver[(paramGPKey._id + 902)].block = (paramMacroKey.getPoint14PauseTime() & 0xFF);
  }

  public static DataSaver[] initMdatasaverByByteArray(byte[] paramArrayOfByte, float paramFloat1, float paramFloat2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = 0;
    while (i < gpsetupdialog.length)
    {
      gpMmdatasaver[i] = new DataSaver();
      gpMmdatasaver[i].property = -1;
      gpMmdatasaver[i].name = "-1";
      gpMmdatasaver[i].x = -1;
      gpMmdatasaver[i].y = -1;
      gpMmdatasaver[i].radius = 0;
      gpMmdatasaver[i].block = 0;
      gpMmdatasaver[i].reverse = 0;
      i += 1;
    }
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = new ArrayList();
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("1---------screenhp = ");
    ((StringBuilder)localObject2).append(paramFloat2);
    ((StringBuilder)localObject2).append(";   screenwp = ");
    ((StringBuilder)localObject2).append(paramFloat1);
    MyLog.i("my_tag", ((StringBuilder)localObject2).toString());
    deviceX = ChangeDataUtil.getIntegerByBit(paramArrayOfByte[5], paramArrayOfByte[6]);
    deviceY = ChangeDataUtil.getIntegerByBit(paramArrayOfByte[7], paramArrayOfByte[8]);
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("1---------deviceX = ");
    ((StringBuilder)localObject2).append(deviceX);
    ((StringBuilder)localObject2).append(";   deviceY = ");
    ((StringBuilder)localObject2).append(deviceY);
    MyLog.i("my_tag", ((StringBuilder)localObject2).toString());
    if ((paramInt1 != 0) && (paramInt2 != 0))
    {
      localObject2 = new byte[(paramInt2 * 4 + 2) * paramInt1];
      System.arraycopy(paramArrayOfByte, paramArrayOfByte.length - localObject2.length, localObject2, 0, localObject2.length);
      Object localObject3 = new StringBuilder();
      ((StringBuilder)localObject3).append("整体数据结构长度：");
      ((StringBuilder)localObject3).append(paramArrayOfByte.length);
      ((StringBuilder)localObject3).append("    宏数据结构总长度：");
      ((StringBuilder)localObject3).append(localObject2.length);
      MyLog.i("my_tag", ((StringBuilder)localObject3).toString());
      ((ArrayList)localObject1).clear();
      paramInt2 = 0;
      while (paramInt2 < paramInt1)
      {
        localObject3 = new byte[62];
        System.arraycopy(localObject2, paramInt2 * 62, localObject3, 0, 62);
        if (localObject3[0] == 14)
          ((ArrayList)localObject1).add(MacroKeyUtils.AnalysisMacroData((byte[])localObject3));
        paramInt2 += 1;
      }
      if (((ArrayList)localObject1).size() > 0)
      {
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("mMacroKeyList.size() = ");
        ((StringBuilder)localObject2).append(((ArrayList)localObject1).size());
        MyLog.i("my_tag", ((StringBuilder)localObject2).toString());
        paramInt1 = 0;
        while (paramInt1 < ((ArrayList)localObject1).size())
        {
          new GPKey();
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append(paramInt1);
          ((StringBuilder)localObject2).append("Macro Key code = ");
          ((StringBuilder)localObject2).append(((MacroKey)((ArrayList)localObject1).get(paramInt1)).getKeyCode() & 0xFF);
          MyLog.i("my_tag", ((StringBuilder)localObject2).toString());
          localObject2 = CommonUtils.getGPKey(((MacroKey)((ArrayList)localObject1).get(paramInt1)).getKeyCode() & 0xFF);
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append(paramInt1);
          ((StringBuilder)localObject3).append("Macro Key name = ");
          ((StringBuilder)localObject3).append(((GPKey)localObject2).name);
          ((StringBuilder)localObject3).append("   id = ");
          ((StringBuilder)localObject3).append(((GPKey)localObject2)._id);
          MyLog.i("my_tag", ((StringBuilder)localObject3).toString());
          if (localObject2 != null)
            initMacroKey((GPKey)localObject2, (MacroKey)((ArrayList)localObject1).get(paramInt1), paramFloat1, paramFloat2);
          paramInt1 += 1;
        }
      }
    }
    if (paramInt3 >= 5)
    {
      localArrayList.clear();
      paramInt1 = 0;
      while (paramInt1 < paramInt4)
      {
        localObject1 = new GPNormalKey();
        paramInt2 = paramInt1 * 10;
        ((GPNormalKey)localObject1).setType(paramArrayOfByte[(paramInt2 + 10)]);
        ((GPNormalKey)localObject1).setCode(paramArrayOfByte[(paramInt2 + 11)]);
        ((GPNormalKey)localObject1).setP1xl(paramArrayOfByte[(paramInt2 + 12)]);
        ((GPNormalKey)localObject1).setP1xh(paramArrayOfByte[(paramInt2 + 13)]);
        ((GPNormalKey)localObject1).setP1yl(paramArrayOfByte[(paramInt2 + 14)]);
        ((GPNormalKey)localObject1).setP1yh(paramArrayOfByte[(paramInt2 + 15)]);
        ((GPNormalKey)localObject1).setP2xl(paramArrayOfByte[(paramInt2 + 16)]);
        ((GPNormalKey)localObject1).setP2xh(paramArrayOfByte[(paramInt2 + 17)]);
        ((GPNormalKey)localObject1).setP2yl(paramArrayOfByte[(paramInt2 + 18)]);
        ((GPNormalKey)localObject1).setP2yh(paramArrayOfByte[(paramInt2 + 19)]);
        localArrayList.add(localObject1);
        paramInt1 += 1;
      }
      paramInt1 = 0;
      while (paramInt1 < localArrayList.size())
      {
        new GPKey();
        paramArrayOfByte = CommonUtils.getGPKey(((GPNormalKey)localArrayList.get(paramInt1)).getCode() & 0xFF);
        if (paramArrayOfByte != null)
        {
          if (((GPNormalKey)localArrayList.get(paramInt1)).getType() == 1)
          {
            gpMmdatasaver[paramArrayOfByte._id].property = 0;
            gpMmdatasaver[paramArrayOfByte._id].name = paramArrayOfByte.name;
            gpMmdatasaver[paramArrayOfByte._id].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
            gpMmdatasaver[paramArrayOfByte._id].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
          }
          if ((((GPNormalKey)localArrayList.get(paramInt1)).getType() == 2) && (paramArrayOfByte._id < 31))
          {
            gpMmdatasaver[(paramArrayOfByte._id + 134)].property = 4;
            gpMmdatasaver[(paramArrayOfByte._id + 134)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 134)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 134)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
            gpMmdatasaver[(paramArrayOfByte._id + 166)].property = 4;
            gpMmdatasaver[(paramArrayOfByte._id + 166)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 166)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP2yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP2yh()) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 166)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP2xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh())) * paramFloat2 / deviceX));
          }
          if ((((GPNormalKey)localArrayList.get(paramInt1)).getType() == 3) && (paramArrayOfByte._id < 31))
          {
            gpMmdatasaver[(paramArrayOfByte._id + 198)].property = 5;
            gpMmdatasaver[(paramArrayOfByte._id + 198)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 198)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 198)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
            gpMmdatasaver[(paramArrayOfByte._id + 230)].property = 5;
            gpMmdatasaver[(paramArrayOfByte._id + 230)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 230)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP2yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP2yh()) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 230)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP2xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh())) * paramFloat2 / deviceX));
          }
          if (((GPNormalKey)localArrayList.get(paramInt1)).getType() == 4)
          {
            gpMmdatasaver[(paramArrayOfByte._id + 262)].property = 6;
            gpMmdatasaver[(paramArrayOfByte._id + 262)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 262)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 262)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
          }
          if (((GPNormalKey)localArrayList.get(paramInt1)).getType() == 5)
          {
            gpMmdatasaver[(paramArrayOfByte._id + 38)].property = 2;
            gpMmdatasaver[(paramArrayOfByte._id + 38)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 38)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 38)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
            gpMmdatasaver[(paramArrayOfByte._id + 38)].radius = 0;
            gpMmdatasaver[(paramArrayOfByte._id + 38)].block = 0;
            gpMmdatasaver[(paramArrayOfByte._id + 38)].reverse = 0;
          }
          if (((GPNormalKey)localArrayList.get(paramInt1)).getType() == 6)
          {
            if (((GPNormalKey)localArrayList.get(paramInt1)).getP2xl() == 1)
            {
              gpMmdatasaver[(paramArrayOfByte._id + 38)].property = 1;
              gpMmdatasaver[(paramArrayOfByte._id + 38)].name = paramArrayOfByte.name;
              gpMmdatasaver[(paramArrayOfByte._id + 38)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
              gpMmdatasaver[(paramArrayOfByte._id + 38)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
              gpMmdatasaver[(paramArrayOfByte._id + 38)].radius = ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP2xh(), ((GPNormalKey)localArrayList.get(paramInt1)).getP2yl());
              gpMmdatasaver[(paramArrayOfByte._id + 38)].block = 0;
              gpMmdatasaver[(paramArrayOfByte._id + 38)].reverse = ((GPNormalKey)localArrayList.get(paramInt1)).getP2yh();
            }
            if (((GPNormalKey)localArrayList.get(paramInt1)).getP2xl() == 2)
            {
              gpMmdatasaver[(paramArrayOfByte._id + 70)].property = 2;
              gpMmdatasaver[(paramArrayOfByte._id + 70)].name = paramArrayOfByte.name;
              gpMmdatasaver[(paramArrayOfByte._id + 70)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
              gpMmdatasaver[(paramArrayOfByte._id + 70)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
              gpMmdatasaver[(paramArrayOfByte._id + 70)].radius = ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP2xh(), ((GPNormalKey)localArrayList.get(paramInt1)).getP2yl());
              gpMmdatasaver[(paramArrayOfByte._id + 70)].block = 0;
              gpMmdatasaver[(paramArrayOfByte._id + 70)].reverse = ((GPNormalKey)localArrayList.get(paramInt1)).getP2yh();
            }
            if (((GPNormalKey)localArrayList.get(paramInt1)).getP2xl() == 3)
            {
              gpMmdatasaver[(paramArrayOfByte._id + 102)].property = 3;
              gpMmdatasaver[(paramArrayOfByte._id + 102)].name = paramArrayOfByte.name;
              gpMmdatasaver[(paramArrayOfByte._id + 102)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
              gpMmdatasaver[(paramArrayOfByte._id + 102)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
              gpMmdatasaver[(paramArrayOfByte._id + 102)].radius = ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP2xh(), ((GPNormalKey)localArrayList.get(paramInt1)).getP2yl());
              gpMmdatasaver[(paramArrayOfByte._id + 102)].block = 0;
              gpMmdatasaver[(paramArrayOfByte._id + 102)].reverse = ((GPNormalKey)localArrayList.get(paramInt1)).getP2yh();
            }
            if (((GPNormalKey)localArrayList.get(paramInt1)).getP2xl() == 4)
            {
              gpMmdatasaver[(paramArrayOfByte._id + 38)].property = 1;
              gpMmdatasaver[(paramArrayOfByte._id + 38)].name = paramArrayOfByte.name;
              gpMmdatasaver[(paramArrayOfByte._id + 38)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
              gpMmdatasaver[(paramArrayOfByte._id + 38)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
              gpMmdatasaver[(paramArrayOfByte._id + 38)].radius = 0;
              gpMmdatasaver[(paramArrayOfByte._id + 38)].block = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh();
              gpMmdatasaver[(paramArrayOfByte._id + 38)].reverse = ((GPNormalKey)localArrayList.get(paramInt1)).getP2yh();
            }
            if (((GPNormalKey)localArrayList.get(paramInt1)).getP2xl() == 5)
            {
              gpMmdatasaver[(paramArrayOfByte._id + 262)].property = 6;
              gpMmdatasaver[(paramArrayOfByte._id + 262)].name = paramArrayOfByte.name;
              gpMmdatasaver[(paramArrayOfByte._id + 262)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
              gpMmdatasaver[(paramArrayOfByte._id + 262)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
              gpMmdatasaver[(paramArrayOfByte._id + 262)].radius = 0;
              gpMmdatasaver[(paramArrayOfByte._id + 262)].block = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh();
              gpMmdatasaver[(paramArrayOfByte._id + 262)].reverse = ((GPNormalKey)localArrayList.get(paramInt1)).getP2yh();
            }
            if (((GPNormalKey)localArrayList.get(paramInt1)).getP2xl() == 6)
            {
              gpMmdatasaver[(paramArrayOfByte._id + 102)].property = 3;
              gpMmdatasaver[(paramArrayOfByte._id + 102)].name = paramArrayOfByte.name;
              gpMmdatasaver[(paramArrayOfByte._id + 102)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
              gpMmdatasaver[(paramArrayOfByte._id + 102)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
              gpMmdatasaver[(paramArrayOfByte._id + 102)].radius = 0;
              gpMmdatasaver[(paramArrayOfByte._id + 102)].block = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh();
              gpMmdatasaver[(paramArrayOfByte._id + 102)].reverse = ((GPNormalKey)localArrayList.get(paramInt1)).getP2yh();
            }
          }
          if (((GPNormalKey)localArrayList.get(paramInt1)).getType() == 7)
          {
            gpMmdatasaver[(paramArrayOfByte._id + 326)].property = 8;
            gpMmdatasaver[(paramArrayOfByte._id + 326)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 326)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 326)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
          }
          if (((GPNormalKey)localArrayList.get(paramInt1)).getType() == 8)
          {
            if (paramArrayOfByte._id < 31)
            {
              gpMmdatasaver[(paramArrayOfByte._id + 358)].property = 9;
              gpMmdatasaver[(paramArrayOfByte._id + 358)].name = paramArrayOfByte.name;
              gpMmdatasaver[(paramArrayOfByte._id + 358)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh() & 0xFF) * paramFloat1 / deviceY));
              gpMmdatasaver[(paramArrayOfByte._id + 358)].y = ((int)((deviceX - ChangeDataUtil.getValueBy16Bit(deviceX, ((GPNormalKey)localArrayList.get(paramInt1)).getP1xl() & 0xFF)) * paramFloat2 / deviceX));
              gpMmdatasaver[(paramArrayOfByte._id + 358)].radius = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xl();
              gpMmdatasaver[(paramArrayOfByte._id + 358)].block = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh();
              gpMmdatasaver[(paramArrayOfByte._id + 390)].property = 9;
              gpMmdatasaver[(paramArrayOfByte._id + 390)].name = paramArrayOfByte.name;
              gpMmdatasaver[(paramArrayOfByte._id + 390)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh() & 0xFF) * paramFloat1 / deviceY));
              gpMmdatasaver[(paramArrayOfByte._id + 390)].y = ((int)((deviceX - ChangeDataUtil.getValueBy16Bit(deviceX, ((GPNormalKey)localArrayList.get(paramInt1)).getP1yl() & 0xFF)) * paramFloat2 / deviceX));
              gpMmdatasaver[(paramArrayOfByte._id + 390)].radius = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xl();
              gpMmdatasaver[(paramArrayOfByte._id + 390)].block = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh();
            }
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("name = ");
            ((StringBuilder)localObject1).append(gpMmdatasaver[(paramArrayOfByte._id + 358)].name);
            ((StringBuilder)localObject1).append("----x1 = ");
            ((StringBuilder)localObject1).append(gpMmdatasaver[(paramArrayOfByte._id + 358)].x);
            ((StringBuilder)localObject1).append(";  y1 = ");
            ((StringBuilder)localObject1).append(gpMmdatasaver[(paramArrayOfByte._id + 358)].y);
            ((StringBuilder)localObject1).append(";   x2 = ");
            ((StringBuilder)localObject1).append(gpMmdatasaver[(paramArrayOfByte._id + 390)].x);
            ((StringBuilder)localObject1).append(";   y2 = ");
            ((StringBuilder)localObject1).append(gpMmdatasaver[(paramArrayOfByte._id + 390)].y);
            MyLog.i("my_tag", ((StringBuilder)localObject1).toString());
          }
          if ((((GPNormalKey)localArrayList.get(paramInt1)).getType() == 9) && (paramArrayOfByte._id < 31))
          {
            gpMmdatasaver[(paramArrayOfByte._id + 422)].property = 10;
            gpMmdatasaver[(paramArrayOfByte._id + 422)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 422)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 422)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
            gpMmdatasaver[(paramArrayOfByte._id + 422)].radius = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xl();
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("点击频率 = ");
            ((StringBuilder)localObject1).append(gpMmdatasaver[(paramArrayOfByte._id + 422)].radius);
            MyLog.i("crc_tag", ((StringBuilder)localObject1).toString());
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("------TURO按键 = ");
            ((StringBuilder)localObject1).append(gpMmdatasaver[paramInt1].name);
            MyLog.i("crc_tag", ((StringBuilder)localObject1).toString());
          }
          if ((((GPNormalKey)localArrayList.get(paramInt1)).getType() == 10) && (paramArrayOfByte._id < 31))
          {
            gpMmdatasaver[paramArrayOfByte._id].property = 11;
            gpMmdatasaver[paramArrayOfByte._id].name = paramArrayOfByte.name;
            gpMmdatasaver[paramArrayOfByte._id].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh() & 0xFF) * paramFloat1 / deviceY));
            gpMmdatasaver[paramArrayOfByte._id].y = ((int)((deviceX - ChangeDataUtil.getValueBy16Bit(deviceX, ((GPNormalKey)localArrayList.get(paramInt1)).getP1xl() & 0xFF)) * paramFloat2 / deviceX));
            gpMmdatasaver[paramArrayOfByte._id].radius = ((GPNormalKey)localArrayList.get(paramInt1)).getP1yl();
            gpMmdatasaver[paramArrayOfByte._id].block = 0;
            gpMmdatasaver[paramArrayOfByte._id].reverse = 0;
          }
          if ((((GPNormalKey)localArrayList.get(paramInt1)).getType() == 11) && (paramArrayOfByte._id < 31))
          {
            gpMmdatasaver[paramArrayOfByte._id].property = 12;
            gpMmdatasaver[paramArrayOfByte._id].name = paramArrayOfByte.name;
            gpMmdatasaver[paramArrayOfByte._id].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh() & 0xFF) * paramFloat1 / deviceY));
            gpMmdatasaver[paramArrayOfByte._id].y = ((int)((deviceX - ChangeDataUtil.getValueBy16Bit(deviceX, ((GPNormalKey)localArrayList.get(paramInt1)).getP1xl() & 0xFF)) * paramFloat2 / deviceX));
            gpMmdatasaver[paramArrayOfByte._id].radius = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * (paramFloat2 / deviceX)));
            gpMmdatasaver[paramArrayOfByte._id].block = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xl();
            gpMmdatasaver[paramArrayOfByte._id].reverse = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh();
          }
          if (((GPNormalKey)localArrayList.get(paramInt1)).getType() == 15)
          {
            if (paramArrayOfByte._id < 31)
            {
              gpMmdatasaver[(paramArrayOfByte._id + 934)].property = 14;
              gpMmdatasaver[(paramArrayOfByte._id + 934)].name = paramArrayOfByte.name;
              gpMmdatasaver[(paramArrayOfByte._id + 934)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh() & 0xFF) * paramFloat1 / deviceY));
              gpMmdatasaver[(paramArrayOfByte._id + 934)].y = ((int)((deviceX - ChangeDataUtil.getValueBy16Bit(deviceX, ((GPNormalKey)localArrayList.get(paramInt1)).getP1xl() & 0xFF)) * paramFloat2 / deviceX));
              gpMmdatasaver[(paramArrayOfByte._id + 934)].radius = (((GPNormalKey)localArrayList.get(paramInt1)).getP1yl() & 0xFF);
              gpMmdatasaver[(paramArrayOfByte._id + 934)].block = (((GPNormalKey)localArrayList.get(paramInt1)).getP1yh() & 0xFF);
              if ((((GPNormalKey)localArrayList.get(paramInt1)).getP2xl() & 0xFF) == 0)
                gpMmdatasaver[(paramArrayOfByte._id + 934)].view_joystick = "-1";
              else
                gpMmdatasaver[(paramArrayOfByte._id + 934)].view_joystick = CommonUtils.getGPKey(((GPNormalKey)localArrayList.get(paramInt1)).getP2xl() & 0xFF).name;
              gpMmdatasaver[(paramArrayOfByte._id + 934)].turb_speed = (((GPNormalKey)localArrayList.get(paramInt1)).getP2xh() & 0xFF);
            }
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("压枪键 name = ");
            ((StringBuilder)localObject1).append(gpMmdatasaver[(paramArrayOfByte._id + 934)].name);
            ((StringBuilder)localObject1).append("----x1 = ");
            ((StringBuilder)localObject1).append(gpMmdatasaver[(paramArrayOfByte._id + 934)].x);
            ((StringBuilder)localObject1).append(";  y1 = ");
            ((StringBuilder)localObject1).append(gpMmdatasaver[(paramArrayOfByte._id + 934)].y);
            MyLog.i("my_tag", ((StringBuilder)localObject1).toString());
          }
        }
        paramInt1 += 1;
      }
    }
    if (paramArrayOfByte[9] == 0)
    {
      gpMmdatasaver[16].property = 0;
      gpMmdatasaver[16].name = "jl";
      gpMmdatasaver[16].x = ((int)(ChangeDataUtil.getIntegerByBit(paramArrayOfByte[12], paramArrayOfByte[13]) * paramFloat1 / deviceY));
      gpMmdatasaver[16].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(paramArrayOfByte[10], paramArrayOfByte[11])) * paramFloat2 / deviceX));
      gpMmdatasaver[16].radius = ((int)(ChangeDataUtil.getIntegerByBit(paramArrayOfByte[14], paramArrayOfByte[15]) * (paramFloat2 / deviceX)));
      gpMmdatasaver[16].block = 0;
      gpMmdatasaver[16].reverse = 0;
    }
    else if (paramArrayOfByte[9] == 1)
    {
      gpMmdatasaver[16].property = 0;
      gpMmdatasaver[16].name = "jl";
      gpMmdatasaver[16].x = ((int)(ChangeDataUtil.getIntegerByBit(paramArrayOfByte[12], paramArrayOfByte[13]) * paramFloat1 / deviceY));
      gpMmdatasaver[16].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(paramArrayOfByte[10], paramArrayOfByte[11])) * paramFloat2 / deviceX));
      gpMmdatasaver[16].radius = 0;
      gpMmdatasaver[16].block = paramArrayOfByte[14];
      gpMmdatasaver[16].reverse = 0;
    }
    else if (paramArrayOfByte[9] == 2)
    {
      gpMmdatasaver[16].property = -1;
      gpMmdatasaver[16].name = "-1";
      gpMmdatasaver[16].x = -1;
      gpMmdatasaver[16].y = -1;
      gpMmdatasaver[16].radius = 0;
      gpMmdatasaver[16].block = 0;
      gpMmdatasaver[16].reverse = 0;
    }
    if (paramArrayOfByte[16] == 0)
    {
      gpMmdatasaver[17].property = 0;
      gpMmdatasaver[17].name = "jr";
      gpMmdatasaver[17].x = ((int)(ChangeDataUtil.getIntegerByBit(paramArrayOfByte[19], paramArrayOfByte[20]) * paramFloat1 / deviceY));
      gpMmdatasaver[17].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(paramArrayOfByte[17], paramArrayOfByte[18])) * paramFloat2 / deviceX));
      gpMmdatasaver[17].radius = ((int)(ChangeDataUtil.getIntegerByBit(paramArrayOfByte[21], paramArrayOfByte[22]) * (paramFloat2 / deviceX)));
      gpMmdatasaver[17].block = 0;
      gpMmdatasaver[17].reverse = 0;
    }
    else if (paramArrayOfByte[16] == 1)
    {
      gpMmdatasaver[17].property = 0;
      gpMmdatasaver[17].name = "jr";
      gpMmdatasaver[17].x = ((int)(ChangeDataUtil.getIntegerByBit(paramArrayOfByte[19], paramArrayOfByte[20]) * paramFloat1 / deviceY));
      gpMmdatasaver[17].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(paramArrayOfByte[17], paramArrayOfByte[18])) * paramFloat2 / deviceX));
      gpMmdatasaver[17].radius = 0;
      gpMmdatasaver[17].block = paramArrayOfByte[21];
      gpMmdatasaver[17].reverse = 0;
    }
    else if (paramArrayOfByte[16] == 2)
    {
      gpMmdatasaver[17].property = -1;
      gpMmdatasaver[17].name = "-1";
      gpMmdatasaver[17].x = -1;
      gpMmdatasaver[17].y = -1;
      gpMmdatasaver[17].radius = 0;
      gpMmdatasaver[17].block = 0;
      gpMmdatasaver[17].reverse = 0;
    }
    new ComKey();
    localObject1 = CommonUtils.checkComKey(paramArrayOfByte[23] & 0xFF);
    if (((ComKey)localObject1)._id != -1)
    {
      gpMmdatasaver[(localObject1._id + 294)].property = 7;
      gpMmdatasaver[(localObject1._id + 294)].name = ((ComKey)localObject1).name;
      gpsetupdialog.comKeyFirst = ((ComKey)localObject1).name;
      gpMmdatasaver[(localObject1._id + 294)].x = ((int)(2.0F * paramFloat1 / 3.0F));
      gpMmdatasaver[(localObject1._id + 294)].y = ((int)(2.0F * paramFloat2 / 3.0F));
      gpMmdatasaver[(localObject1._id + 294)].radius = 0;
      gpMmdatasaver[(localObject1._id + 294)].block = paramArrayOfByte[21];
    }
    localArrayList.clear();
    paramInt1 = 0;
    while (paramInt1 < 24)
    {
      localObject1 = new GPNormalKey();
      paramInt2 = paramInt1 * 10;
      ((GPNormalKey)localObject1).setType(paramArrayOfByte[(paramInt2 + 24)]);
      ((GPNormalKey)localObject1).setCode(paramArrayOfByte[(paramInt2 + 25)]);
      ((GPNormalKey)localObject1).setP1xl(paramArrayOfByte[(paramInt2 + 26)]);
      ((GPNormalKey)localObject1).setP1xh(paramArrayOfByte[(paramInt2 + 27)]);
      ((GPNormalKey)localObject1).setP1yl(paramArrayOfByte[(paramInt2 + 28)]);
      ((GPNormalKey)localObject1).setP1yh(paramArrayOfByte[(paramInt2 + 29)]);
      ((GPNormalKey)localObject1).setP2xl(paramArrayOfByte[(paramInt2 + 30)]);
      ((GPNormalKey)localObject1).setP2xh(paramArrayOfByte[(paramInt2 + 31)]);
      ((GPNormalKey)localObject1).setP2yl(paramArrayOfByte[(paramInt2 + 32)]);
      ((GPNormalKey)localObject1).setP2yh(paramArrayOfByte[(paramInt2 + 33)]);
      localArrayList.add(localObject1);
      paramInt1 += 1;
    }
    paramArrayOfByte = new StringBuilder();
    paramArrayOfByte.append("--------- mGPNormalKeyList.size() = ");
    paramArrayOfByte.append(localArrayList.size());
    MyLog.i("my_tag", paramArrayOfByte.toString());
    paramInt1 = 0;
    while (paramInt1 < localArrayList.size())
    {
      new GPKey();
      paramArrayOfByte = CommonUtils.getGPKey(((GPNormalKey)localArrayList.get(paramInt1)).getCode() & 0xFF);
      if (paramArrayOfByte != null)
      {
        if (((GPNormalKey)localArrayList.get(paramInt1)).getType() == 1)
        {
          gpMmdatasaver[paramArrayOfByte._id].property = 0;
          gpMmdatasaver[paramArrayOfByte._id].name = paramArrayOfByte.name;
          gpMmdatasaver[paramArrayOfByte._id].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
          gpMmdatasaver[paramArrayOfByte._id].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
        }
        if ((((GPNormalKey)localArrayList.get(paramInt1)).getType() == 2) && (paramArrayOfByte._id < 31))
        {
          gpMmdatasaver[(paramArrayOfByte._id + 134)].property = 4;
          gpMmdatasaver[(paramArrayOfByte._id + 134)].name = paramArrayOfByte.name;
          gpMmdatasaver[(paramArrayOfByte._id + 134)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
          gpMmdatasaver[(paramArrayOfByte._id + 134)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
          gpMmdatasaver[(paramArrayOfByte._id + 166)].property = 4;
          gpMmdatasaver[(paramArrayOfByte._id + 166)].name = paramArrayOfByte.name;
          gpMmdatasaver[(paramArrayOfByte._id + 166)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP2yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP2yh()) * paramFloat1 / deviceY));
          gpMmdatasaver[(paramArrayOfByte._id + 166)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP2xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh())) * paramFloat2 / deviceX));
        }
        if ((((GPNormalKey)localArrayList.get(paramInt1)).getType() == 3) && (paramArrayOfByte._id < 31))
        {
          gpMmdatasaver[(paramArrayOfByte._id + 198)].property = 5;
          gpMmdatasaver[(paramArrayOfByte._id + 198)].name = paramArrayOfByte.name;
          gpMmdatasaver[(paramArrayOfByte._id + 198)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
          gpMmdatasaver[(paramArrayOfByte._id + 198)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
          gpMmdatasaver[(paramArrayOfByte._id + 230)].property = 5;
          gpMmdatasaver[(paramArrayOfByte._id + 230)].name = paramArrayOfByte.name;
          gpMmdatasaver[(paramArrayOfByte._id + 230)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP2yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP2yh()) * paramFloat1 / deviceY));
          gpMmdatasaver[(paramArrayOfByte._id + 230)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP2xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh())) * paramFloat2 / deviceX));
        }
        if (((GPNormalKey)localArrayList.get(paramInt1)).getType() == 4)
        {
          gpMmdatasaver[(paramArrayOfByte._id + 262)].property = 6;
          gpMmdatasaver[(paramArrayOfByte._id + 262)].name = paramArrayOfByte.name;
          gpMmdatasaver[(paramArrayOfByte._id + 262)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
          gpMmdatasaver[(paramArrayOfByte._id + 262)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
        }
        if (((GPNormalKey)localArrayList.get(paramInt1)).getType() == 5)
        {
          gpMmdatasaver[(paramArrayOfByte._id + 38)].property = 2;
          gpMmdatasaver[(paramArrayOfByte._id + 38)].name = paramArrayOfByte.name;
          gpMmdatasaver[(paramArrayOfByte._id + 38)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
          gpMmdatasaver[(paramArrayOfByte._id + 38)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
          gpMmdatasaver[(paramArrayOfByte._id + 38)].radius = 0;
          gpMmdatasaver[(paramArrayOfByte._id + 38)].block = 0;
          gpMmdatasaver[(paramArrayOfByte._id + 38)].reverse = 0;
        }
        if (((GPNormalKey)localArrayList.get(paramInt1)).getType() == 6)
        {
          if (((GPNormalKey)localArrayList.get(paramInt1)).getP2xl() == 1)
          {
            gpMmdatasaver[(paramArrayOfByte._id + 38)].property = 1;
            gpMmdatasaver[(paramArrayOfByte._id + 38)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 38)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 38)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
            gpMmdatasaver[(paramArrayOfByte._id + 38)].radius = ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP2xh(), ((GPNormalKey)localArrayList.get(paramInt1)).getP2yl());
            gpMmdatasaver[(paramArrayOfByte._id + 38)].block = 0;
            gpMmdatasaver[(paramArrayOfByte._id + 38)].reverse = ((GPNormalKey)localArrayList.get(paramInt1)).getP2yh();
          }
          if (((GPNormalKey)localArrayList.get(paramInt1)).getP2xl() == 2)
          {
            gpMmdatasaver[(paramArrayOfByte._id + 70)].property = 2;
            gpMmdatasaver[(paramArrayOfByte._id + 70)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 70)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 70)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
            gpMmdatasaver[(paramArrayOfByte._id + 70)].radius = ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP2xh(), ((GPNormalKey)localArrayList.get(paramInt1)).getP2yl());
            gpMmdatasaver[(paramArrayOfByte._id + 70)].block = 0;
            gpMmdatasaver[(paramArrayOfByte._id + 70)].reverse = ((GPNormalKey)localArrayList.get(paramInt1)).getP2yh();
          }
          if (((GPNormalKey)localArrayList.get(paramInt1)).getP2xl() == 3)
          {
            gpMmdatasaver[(paramArrayOfByte._id + 102)].property = 3;
            gpMmdatasaver[(paramArrayOfByte._id + 102)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 102)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 102)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
            gpMmdatasaver[(paramArrayOfByte._id + 102)].radius = ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP2xh(), ((GPNormalKey)localArrayList.get(paramInt1)).getP2yl());
            gpMmdatasaver[(paramArrayOfByte._id + 102)].block = 0;
            gpMmdatasaver[(paramArrayOfByte._id + 102)].reverse = ((GPNormalKey)localArrayList.get(paramInt1)).getP2yh();
          }
          if (((GPNormalKey)localArrayList.get(paramInt1)).getP2xl() == 4)
          {
            gpMmdatasaver[(paramArrayOfByte._id + 38)].property = 1;
            gpMmdatasaver[(paramArrayOfByte._id + 38)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 38)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 38)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
            gpMmdatasaver[(paramArrayOfByte._id + 38)].radius = 0;
            gpMmdatasaver[(paramArrayOfByte._id + 38)].block = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh();
            gpMmdatasaver[(paramArrayOfByte._id + 38)].reverse = 0;
          }
          if (((GPNormalKey)localArrayList.get(paramInt1)).getP2xl() == 5)
          {
            gpMmdatasaver[(paramArrayOfByte._id + 262)].property = 6;
            gpMmdatasaver[(paramArrayOfByte._id + 262)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 262)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 262)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
            gpMmdatasaver[(paramArrayOfByte._id + 262)].radius = 0;
            gpMmdatasaver[(paramArrayOfByte._id + 262)].block = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh();
            gpMmdatasaver[(paramArrayOfByte._id + 262)].reverse = 0;
          }
          if (((GPNormalKey)localArrayList.get(paramInt1)).getP2xl() == 6)
          {
            gpMmdatasaver[(paramArrayOfByte._id + 102)].property = 3;
            gpMmdatasaver[(paramArrayOfByte._id + 102)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 102)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 102)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
            gpMmdatasaver[(paramArrayOfByte._id + 102)].radius = 0;
            gpMmdatasaver[(paramArrayOfByte._id + 102)].block = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh();
            gpMmdatasaver[(paramArrayOfByte._id + 102)].reverse = 0;
          }
        }
        if (((GPNormalKey)localArrayList.get(paramInt1)).getType() == 7)
        {
          gpMmdatasaver[(paramArrayOfByte._id + 326)].property = 8;
          gpMmdatasaver[(paramArrayOfByte._id + 326)].name = paramArrayOfByte.name;
          gpMmdatasaver[(paramArrayOfByte._id + 326)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
          gpMmdatasaver[(paramArrayOfByte._id + 326)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
        }
        if (((GPNormalKey)localArrayList.get(paramInt1)).getType() == 8)
        {
          if (paramArrayOfByte._id < 31)
          {
            gpMmdatasaver[(paramArrayOfByte._id + 358)].property = 9;
            gpMmdatasaver[(paramArrayOfByte._id + 358)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 358)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh() & 0xFF) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 358)].y = ((int)((deviceX - ChangeDataUtil.getValueBy16Bit(deviceX, ((GPNormalKey)localArrayList.get(paramInt1)).getP1xl() & 0xFF)) * paramFloat2 / deviceX));
            gpMmdatasaver[(paramArrayOfByte._id + 358)].radius = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xl();
            gpMmdatasaver[(paramArrayOfByte._id + 358)].block = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh();
            gpMmdatasaver[(paramArrayOfByte._id + 390)].property = 9;
            gpMmdatasaver[(paramArrayOfByte._id + 390)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 390)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh() & 0xFF) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 390)].y = ((int)((deviceX - ChangeDataUtil.getValueBy16Bit(deviceX, ((GPNormalKey)localArrayList.get(paramInt1)).getP1yl() & 0xFF)) * paramFloat2 / deviceX));
            gpMmdatasaver[(paramArrayOfByte._id + 390)].radius = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xl();
            gpMmdatasaver[(paramArrayOfByte._id + 390)].block = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh();
          }
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("name = ");
          ((StringBuilder)localObject1).append(gpMmdatasaver[(paramArrayOfByte._id + 358)].name);
          ((StringBuilder)localObject1).append("----x1 = ");
          ((StringBuilder)localObject1).append(gpMmdatasaver[(paramArrayOfByte._id + 358)].x);
          ((StringBuilder)localObject1).append(";  y1 = ");
          ((StringBuilder)localObject1).append(gpMmdatasaver[(paramArrayOfByte._id + 358)].y);
          ((StringBuilder)localObject1).append(";   x2 = ");
          ((StringBuilder)localObject1).append(gpMmdatasaver[(paramArrayOfByte._id + 390)].x);
          ((StringBuilder)localObject1).append(";   y2 = ");
          ((StringBuilder)localObject1).append(gpMmdatasaver[(paramArrayOfByte._id + 390)].y);
          MyLog.i("my_tag", ((StringBuilder)localObject1).toString());
        }
        if ((((GPNormalKey)localArrayList.get(paramInt1)).getType() == 9) && (paramArrayOfByte._id < 31))
        {
          gpMmdatasaver[(paramArrayOfByte._id + 422)].property = 10;
          gpMmdatasaver[(paramArrayOfByte._id + 422)].name = paramArrayOfByte.name;
          gpMmdatasaver[(paramArrayOfByte._id + 422)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
          gpMmdatasaver[(paramArrayOfByte._id + 422)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
          gpMmdatasaver[(paramArrayOfByte._id + 422)].radius = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xl();
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("点击频率 = ");
          ((StringBuilder)localObject1).append(gpMmdatasaver[(paramArrayOfByte._id + 422)].radius);
          MyLog.i("crc_tag", ((StringBuilder)localObject1).toString());
          paramArrayOfByte = new StringBuilder();
          paramArrayOfByte.append("------TURO按键 = ");
          paramArrayOfByte.append(gpMmdatasaver[paramInt1].name);
          MyLog.i("crc_tag", paramArrayOfByte.toString());
        }
      }
      paramInt1 += 1;
    }
    return gpMmdatasaver;
  }

  public static DataSaver[] initMdatasaverByByteArray(byte[] paramArrayOfByte, float paramFloat1, float paramFloat2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    int i = 0;
    while (i < gpsetupdialog.length)
    {
      gpMmdatasaver[i] = new DataSaver();
      gpMmdatasaver[i].property = -1;
      gpMmdatasaver[i].name = "-1";
      gpMmdatasaver[i].x = -1;
      gpMmdatasaver[i].y = -1;
      gpMmdatasaver[i].radius = 0;
      gpMmdatasaver[i].block = 0;
      gpMmdatasaver[i].reverse = 0;
      i += 1;
    }
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = new ArrayList();
    deviceX = ChangeDataUtil.getIntegerByBit(paramArrayOfByte[5], paramArrayOfByte[6]);
    deviceY = ChangeDataUtil.getIntegerByBit(paramArrayOfByte[7], paramArrayOfByte[8]);
    if ((paramInt1 != 0) && (paramInt2 != 0))
    {
      Object localObject2 = new byte[(paramInt2 * 4 + 2) * paramInt1];
      System.arraycopy(paramArrayOfByte, paramArrayOfByte.length - localObject2.length, localObject2, 0, localObject2.length);
      ((ArrayList)localObject1).clear();
      paramInt2 = 0;
      while (paramInt2 < paramInt1)
      {
        byte[] arrayOfByte = new byte[62];
        System.arraycopy(localObject2, paramInt2 * 62, arrayOfByte, 0, 62);
        if (arrayOfByte[0] == 14)
          ((ArrayList)localObject1).add(MacroKeyUtils.AnalysisMacroData(arrayOfByte));
        paramInt2 += 1;
      }
      if (((ArrayList)localObject1).size() > 0)
      {
        paramInt1 = 0;
        while (paramInt1 < ((ArrayList)localObject1).size())
        {
          new GPKey();
          localObject2 = CommonUtils.getGPKey(((MacroKey)((ArrayList)localObject1).get(paramInt1)).getKeyCode() & 0xFF);
          if ((localObject2 != null) && (((GPKey)localObject2).scenes == paramInt5))
            initMacroKey((GPKey)localObject2, (MacroKey)((ArrayList)localObject1).get(paramInt1), paramFloat1, paramFloat2);
          paramInt1 += 1;
        }
      }
    }
    if (paramInt3 >= 5)
    {
      localArrayList.clear();
      paramInt1 = 0;
      while (paramInt1 < paramInt4)
      {
        localObject1 = new GPNormalKey();
        paramInt2 = paramInt1 * 10;
        ((GPNormalKey)localObject1).setType(paramArrayOfByte[(paramInt2 + 10)]);
        ((GPNormalKey)localObject1).setCode(paramArrayOfByte[(paramInt2 + 11)]);
        ((GPNormalKey)localObject1).setP1xl(paramArrayOfByte[(paramInt2 + 12)]);
        ((GPNormalKey)localObject1).setP1xh(paramArrayOfByte[(paramInt2 + 13)]);
        ((GPNormalKey)localObject1).setP1yl(paramArrayOfByte[(paramInt2 + 14)]);
        ((GPNormalKey)localObject1).setP1yh(paramArrayOfByte[(paramInt2 + 15)]);
        ((GPNormalKey)localObject1).setP2xl(paramArrayOfByte[(paramInt2 + 16)]);
        ((GPNormalKey)localObject1).setP2xh(paramArrayOfByte[(paramInt2 + 17)]);
        ((GPNormalKey)localObject1).setP2yl(paramArrayOfByte[(paramInt2 + 18)]);
        ((GPNormalKey)localObject1).setP2yh(paramArrayOfByte[(paramInt2 + 19)]);
        localArrayList.add(localObject1);
        paramInt1 += 1;
      }
      paramInt1 = 0;
      while (paramInt1 < localArrayList.size())
      {
        new GPKey();
        paramArrayOfByte = CommonUtils.getGPKey(((GPNormalKey)localArrayList.get(paramInt1)).getCode() & 0xFF);
        if ((paramArrayOfByte != null) && (paramArrayOfByte.scenes == paramInt5))
        {
          if (((GPNormalKey)localArrayList.get(paramInt1)).getType() == 1)
          {
            gpMmdatasaver[paramArrayOfByte._id].property = 0;
            gpMmdatasaver[paramArrayOfByte._id].name = paramArrayOfByte.name;
            gpMmdatasaver[paramArrayOfByte._id].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
            gpMmdatasaver[paramArrayOfByte._id].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
          }
          if ((((GPNormalKey)localArrayList.get(paramInt1)).getType() == 2) && (paramArrayOfByte._id < 31))
          {
            gpMmdatasaver[(paramArrayOfByte._id + 134)].property = 4;
            gpMmdatasaver[(paramArrayOfByte._id + 134)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 134)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 134)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
            gpMmdatasaver[(paramArrayOfByte._id + 166)].property = 4;
            gpMmdatasaver[(paramArrayOfByte._id + 166)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 166)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP2yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP2yh()) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 166)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP2xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh())) * paramFloat2 / deviceX));
          }
          if ((((GPNormalKey)localArrayList.get(paramInt1)).getType() == 3) && (paramArrayOfByte._id < 31))
          {
            gpMmdatasaver[(paramArrayOfByte._id + 198)].property = 5;
            gpMmdatasaver[(paramArrayOfByte._id + 198)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 198)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 198)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
            gpMmdatasaver[(paramArrayOfByte._id + 230)].property = 5;
            gpMmdatasaver[(paramArrayOfByte._id + 230)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 230)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP2yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP2yh()) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 230)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP2xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh())) * paramFloat2 / deviceX));
          }
          if (((GPNormalKey)localArrayList.get(paramInt1)).getType() == 4)
          {
            gpMmdatasaver[(paramArrayOfByte._id + 262)].property = 6;
            gpMmdatasaver[(paramArrayOfByte._id + 262)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 262)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 262)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
          }
          if (((GPNormalKey)localArrayList.get(paramInt1)).getType() == 5)
          {
            gpMmdatasaver[(paramArrayOfByte._id + 38)].property = 2;
            gpMmdatasaver[(paramArrayOfByte._id + 38)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 38)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 38)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
            gpMmdatasaver[(paramArrayOfByte._id + 38)].radius = 0;
            gpMmdatasaver[(paramArrayOfByte._id + 38)].block = 0;
            gpMmdatasaver[(paramArrayOfByte._id + 38)].reverse = 0;
          }
          if (((GPNormalKey)localArrayList.get(paramInt1)).getType() == 6)
          {
            if (((GPNormalKey)localArrayList.get(paramInt1)).getP2xl() == 1)
            {
              gpMmdatasaver[(paramArrayOfByte._id + 38)].property = 1;
              gpMmdatasaver[(paramArrayOfByte._id + 38)].name = paramArrayOfByte.name;
              gpMmdatasaver[(paramArrayOfByte._id + 38)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
              gpMmdatasaver[(paramArrayOfByte._id + 38)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
              gpMmdatasaver[(paramArrayOfByte._id + 38)].radius = ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP2xh(), ((GPNormalKey)localArrayList.get(paramInt1)).getP2yl());
              gpMmdatasaver[(paramArrayOfByte._id + 38)].block = 0;
              gpMmdatasaver[(paramArrayOfByte._id + 38)].reverse = ((GPNormalKey)localArrayList.get(paramInt1)).getP2yh();
            }
            if (((GPNormalKey)localArrayList.get(paramInt1)).getP2xl() == 2)
            {
              gpMmdatasaver[(paramArrayOfByte._id + 70)].property = 2;
              gpMmdatasaver[(paramArrayOfByte._id + 70)].name = paramArrayOfByte.name;
              gpMmdatasaver[(paramArrayOfByte._id + 70)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
              gpMmdatasaver[(paramArrayOfByte._id + 70)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
              gpMmdatasaver[(paramArrayOfByte._id + 70)].radius = ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP2xh(), ((GPNormalKey)localArrayList.get(paramInt1)).getP2yl());
              gpMmdatasaver[(paramArrayOfByte._id + 70)].block = 0;
              gpMmdatasaver[(paramArrayOfByte._id + 70)].reverse = ((GPNormalKey)localArrayList.get(paramInt1)).getP2yh();
            }
            if (((GPNormalKey)localArrayList.get(paramInt1)).getP2xl() == 3)
            {
              gpMmdatasaver[(paramArrayOfByte._id + 102)].property = 3;
              gpMmdatasaver[(paramArrayOfByte._id + 102)].name = paramArrayOfByte.name;
              gpMmdatasaver[(paramArrayOfByte._id + 102)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
              gpMmdatasaver[(paramArrayOfByte._id + 102)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
              gpMmdatasaver[(paramArrayOfByte._id + 102)].radius = ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP2xh(), ((GPNormalKey)localArrayList.get(paramInt1)).getP2yl());
              gpMmdatasaver[(paramArrayOfByte._id + 102)].block = 0;
              gpMmdatasaver[(paramArrayOfByte._id + 102)].reverse = ((GPNormalKey)localArrayList.get(paramInt1)).getP2yh();
            }
            if (((GPNormalKey)localArrayList.get(paramInt1)).getP2xl() == 4)
            {
              gpMmdatasaver[(paramArrayOfByte._id + 38)].property = 1;
              gpMmdatasaver[(paramArrayOfByte._id + 38)].name = paramArrayOfByte.name;
              gpMmdatasaver[(paramArrayOfByte._id + 38)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
              gpMmdatasaver[(paramArrayOfByte._id + 38)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
              gpMmdatasaver[(paramArrayOfByte._id + 38)].radius = 0;
              gpMmdatasaver[(paramArrayOfByte._id + 38)].block = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh();
              gpMmdatasaver[(paramArrayOfByte._id + 38)].reverse = ((GPNormalKey)localArrayList.get(paramInt1)).getP2yh();
            }
            if (((GPNormalKey)localArrayList.get(paramInt1)).getP2xl() == 5)
            {
              gpMmdatasaver[(paramArrayOfByte._id + 262)].property = 6;
              gpMmdatasaver[(paramArrayOfByte._id + 262)].name = paramArrayOfByte.name;
              gpMmdatasaver[(paramArrayOfByte._id + 262)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
              gpMmdatasaver[(paramArrayOfByte._id + 262)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
              gpMmdatasaver[(paramArrayOfByte._id + 262)].radius = 0;
              gpMmdatasaver[(paramArrayOfByte._id + 262)].block = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh();
              gpMmdatasaver[(paramArrayOfByte._id + 262)].reverse = ((GPNormalKey)localArrayList.get(paramInt1)).getP2yh();
            }
            if (((GPNormalKey)localArrayList.get(paramInt1)).getP2xl() == 6)
            {
              gpMmdatasaver[(paramArrayOfByte._id + 102)].property = 3;
              gpMmdatasaver[(paramArrayOfByte._id + 102)].name = paramArrayOfByte.name;
              gpMmdatasaver[(paramArrayOfByte._id + 102)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
              gpMmdatasaver[(paramArrayOfByte._id + 102)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
              gpMmdatasaver[(paramArrayOfByte._id + 102)].radius = 0;
              gpMmdatasaver[(paramArrayOfByte._id + 102)].block = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh();
              gpMmdatasaver[(paramArrayOfByte._id + 102)].reverse = ((GPNormalKey)localArrayList.get(paramInt1)).getP2yh();
            }
          }
          if (((GPNormalKey)localArrayList.get(paramInt1)).getType() == 7)
          {
            gpMmdatasaver[(paramArrayOfByte._id + 326)].property = 8;
            gpMmdatasaver[(paramArrayOfByte._id + 326)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 326)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 326)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
          }
          if (((GPNormalKey)localArrayList.get(paramInt1)).getType() == 8)
          {
            if (paramArrayOfByte._id < 31)
            {
              gpMmdatasaver[(paramArrayOfByte._id + 358)].property = 9;
              gpMmdatasaver[(paramArrayOfByte._id + 358)].name = paramArrayOfByte.name;
              gpMmdatasaver[(paramArrayOfByte._id + 358)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh() & 0xFF) * paramFloat1 / deviceY));
              gpMmdatasaver[(paramArrayOfByte._id + 358)].y = ((int)((deviceX - ChangeDataUtil.getValueBy16Bit(deviceX, ((GPNormalKey)localArrayList.get(paramInt1)).getP1xl() & 0xFF)) * paramFloat2 / deviceX));
              gpMmdatasaver[(paramArrayOfByte._id + 358)].radius = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xl();
              gpMmdatasaver[(paramArrayOfByte._id + 358)].block = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh();
              gpMmdatasaver[(paramArrayOfByte._id + 390)].property = 9;
              gpMmdatasaver[(paramArrayOfByte._id + 390)].name = paramArrayOfByte.name;
              gpMmdatasaver[(paramArrayOfByte._id + 390)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh() & 0xFF) * paramFloat1 / deviceY));
              gpMmdatasaver[(paramArrayOfByte._id + 390)].y = ((int)((deviceX - ChangeDataUtil.getValueBy16Bit(deviceX, ((GPNormalKey)localArrayList.get(paramInt1)).getP1yl() & 0xFF)) * paramFloat2 / deviceX));
              gpMmdatasaver[(paramArrayOfByte._id + 390)].radius = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xl();
              gpMmdatasaver[(paramArrayOfByte._id + 390)].block = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh();
            }
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("name = ");
            ((StringBuilder)localObject1).append(gpMmdatasaver[(paramArrayOfByte._id + 358)].name);
            ((StringBuilder)localObject1).append("----x1 = ");
            ((StringBuilder)localObject1).append(gpMmdatasaver[(paramArrayOfByte._id + 358)].x);
            ((StringBuilder)localObject1).append(";  y1 = ");
            ((StringBuilder)localObject1).append(gpMmdatasaver[(paramArrayOfByte._id + 358)].y);
            ((StringBuilder)localObject1).append(";   x2 = ");
            ((StringBuilder)localObject1).append(gpMmdatasaver[(paramArrayOfByte._id + 390)].x);
            ((StringBuilder)localObject1).append(";   y2 = ");
            ((StringBuilder)localObject1).append(gpMmdatasaver[(paramArrayOfByte._id + 390)].y);
            MyLog.i("my_tag", ((StringBuilder)localObject1).toString());
          }
          if ((((GPNormalKey)localArrayList.get(paramInt1)).getType() == 9) && (paramArrayOfByte._id < 31))
          {
            gpMmdatasaver[(paramArrayOfByte._id + 422)].property = 10;
            gpMmdatasaver[(paramArrayOfByte._id + 422)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 422)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 422)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
            gpMmdatasaver[(paramArrayOfByte._id + 422)].radius = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xl();
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("点击频率 = ");
            ((StringBuilder)localObject1).append(gpMmdatasaver[(paramArrayOfByte._id + 422)].radius);
            MyLog.i("crc_tag", ((StringBuilder)localObject1).toString());
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("------TURO按键 = ");
            ((StringBuilder)localObject1).append(gpMmdatasaver[paramInt1].name);
            MyLog.i("crc_tag", ((StringBuilder)localObject1).toString());
          }
          if ((((GPNormalKey)localArrayList.get(paramInt1)).getType() == 10) && (paramArrayOfByte._id < 31))
          {
            gpMmdatasaver[paramArrayOfByte._id].property = 11;
            gpMmdatasaver[paramArrayOfByte._id].name = paramArrayOfByte.name;
            gpMmdatasaver[paramArrayOfByte._id].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh() & 0xFF) * paramFloat1 / deviceY));
            gpMmdatasaver[paramArrayOfByte._id].y = ((int)((deviceX - ChangeDataUtil.getValueBy16Bit(deviceX, ((GPNormalKey)localArrayList.get(paramInt1)).getP1xl() & 0xFF)) * paramFloat2 / deviceX));
            gpMmdatasaver[paramArrayOfByte._id].radius = ((GPNormalKey)localArrayList.get(paramInt1)).getP1yl();
            gpMmdatasaver[paramArrayOfByte._id].block = 0;
            gpMmdatasaver[paramArrayOfByte._id].reverse = 0;
          }
          if ((((GPNormalKey)localArrayList.get(paramInt1)).getType() == 11) && (paramArrayOfByte._id < 31))
          {
            gpMmdatasaver[paramArrayOfByte._id].property = 12;
            gpMmdatasaver[paramArrayOfByte._id].name = paramArrayOfByte.name;
            gpMmdatasaver[paramArrayOfByte._id].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh() & 0xFF) * paramFloat1 / deviceY));
            gpMmdatasaver[paramArrayOfByte._id].y = ((int)((deviceX - ChangeDataUtil.getValueBy16Bit(deviceX, ((GPNormalKey)localArrayList.get(paramInt1)).getP1xl() & 0xFF)) * paramFloat2 / deviceX));
            gpMmdatasaver[paramArrayOfByte._id].radius = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * (paramFloat2 / deviceX)));
            gpMmdatasaver[paramArrayOfByte._id].block = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xl();
            gpMmdatasaver[paramArrayOfByte._id].reverse = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh();
          }
          if ((((GPNormalKey)localArrayList.get(paramInt1)).getType() == 15) && (paramArrayOfByte._id < 31))
          {
            gpMmdatasaver[(paramArrayOfByte._id + 934)].property = 14;
            gpMmdatasaver[(paramArrayOfByte._id + 934)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 934)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh() & 0xFF) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 934)].y = ((int)((deviceX - ChangeDataUtil.getValueBy16Bit(deviceX, ((GPNormalKey)localArrayList.get(paramInt1)).getP1xl() & 0xFF)) * paramFloat2 / deviceX));
            gpMmdatasaver[(paramArrayOfByte._id + 934)].radius = (((GPNormalKey)localArrayList.get(paramInt1)).getP1yl() & 0xFF);
            gpMmdatasaver[(paramArrayOfByte._id + 934)].block = (((GPNormalKey)localArrayList.get(paramInt1)).getP1yh() & 0xFF);
            if ((((GPNormalKey)localArrayList.get(paramInt1)).getP2xl() & 0xFF) == 0)
              gpMmdatasaver[(paramArrayOfByte._id + 934)].view_joystick = "-1";
            else
              gpMmdatasaver[(paramArrayOfByte._id + 934)].view_joystick = CommonUtils.getGPKey(((GPNormalKey)localArrayList.get(paramInt1)).getP2xl() & 0xFF).name;
            gpMmdatasaver[(paramArrayOfByte._id + 934)].turb_speed = (((GPNormalKey)localArrayList.get(paramInt1)).getP2xh() & 0xFF);
          }
        }
        paramInt1 += 1;
      }
    }
    if (paramArrayOfByte[9] == 0)
    {
      gpMmdatasaver[16].property = 0;
      gpMmdatasaver[16].name = "jl";
      gpMmdatasaver[16].x = ((int)(ChangeDataUtil.getIntegerByBit(paramArrayOfByte[12], paramArrayOfByte[13]) * paramFloat1 / deviceY));
      gpMmdatasaver[16].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(paramArrayOfByte[10], paramArrayOfByte[11])) * paramFloat2 / deviceX));
      gpMmdatasaver[16].radius = ((int)(ChangeDataUtil.getIntegerByBit(paramArrayOfByte[14], paramArrayOfByte[15]) * (paramFloat2 / deviceX)));
      gpMmdatasaver[16].block = 0;
      gpMmdatasaver[16].reverse = 0;
    }
    else if (paramArrayOfByte[9] == 1)
    {
      gpMmdatasaver[16].property = 0;
      gpMmdatasaver[16].name = "jl";
      gpMmdatasaver[16].x = ((int)(ChangeDataUtil.getIntegerByBit(paramArrayOfByte[12], paramArrayOfByte[13]) * paramFloat1 / deviceY));
      gpMmdatasaver[16].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(paramArrayOfByte[10], paramArrayOfByte[11])) * paramFloat2 / deviceX));
      gpMmdatasaver[16].radius = 0;
      gpMmdatasaver[16].block = paramArrayOfByte[14];
      gpMmdatasaver[16].reverse = 0;
    }
    else if (paramArrayOfByte[9] == 2)
    {
      gpMmdatasaver[16].property = -1;
      gpMmdatasaver[16].name = "-1";
      gpMmdatasaver[16].x = -1;
      gpMmdatasaver[16].y = -1;
      gpMmdatasaver[16].radius = 0;
      gpMmdatasaver[16].block = 0;
      gpMmdatasaver[16].reverse = 0;
    }
    if (paramArrayOfByte[16] == 0)
    {
      gpMmdatasaver[17].property = 0;
      gpMmdatasaver[17].name = "jr";
      gpMmdatasaver[17].x = ((int)(ChangeDataUtil.getIntegerByBit(paramArrayOfByte[19], paramArrayOfByte[20]) * paramFloat1 / deviceY));
      gpMmdatasaver[17].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(paramArrayOfByte[17], paramArrayOfByte[18])) * paramFloat2 / deviceX));
      gpMmdatasaver[17].radius = ((int)(ChangeDataUtil.getIntegerByBit(paramArrayOfByte[21], paramArrayOfByte[22]) * (paramFloat2 / deviceX)));
      gpMmdatasaver[17].block = 0;
      gpMmdatasaver[17].reverse = 0;
    }
    else if (paramArrayOfByte[16] == 1)
    {
      gpMmdatasaver[17].property = 0;
      gpMmdatasaver[17].name = "jr";
      gpMmdatasaver[17].x = ((int)(ChangeDataUtil.getIntegerByBit(paramArrayOfByte[19], paramArrayOfByte[20]) * paramFloat1 / deviceY));
      gpMmdatasaver[17].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(paramArrayOfByte[17], paramArrayOfByte[18])) * paramFloat2 / deviceX));
      gpMmdatasaver[17].radius = 0;
      gpMmdatasaver[17].block = paramArrayOfByte[21];
      gpMmdatasaver[17].reverse = 0;
    }
    else if (paramArrayOfByte[16] == 2)
    {
      gpMmdatasaver[17].property = -1;
      gpMmdatasaver[17].name = "-1";
      gpMmdatasaver[17].x = -1;
      gpMmdatasaver[17].y = -1;
      gpMmdatasaver[17].radius = 0;
      gpMmdatasaver[17].block = 0;
      gpMmdatasaver[17].reverse = 0;
    }
    new ComKey();
    localObject1 = CommonUtils.checkComKey(paramArrayOfByte[23] & 0xFF);
    if (((ComKey)localObject1)._id != -1)
    {
      gpMmdatasaver[(localObject1._id + 294)].property = 7;
      gpMmdatasaver[(localObject1._id + 294)].name = ((ComKey)localObject1).name;
      gpsetupdialog.comKeyFirst = ((ComKey)localObject1).name;
      gpMmdatasaver[(localObject1._id + 294)].x = ((int)(2.0F * paramFloat1 / 3.0F));
      gpMmdatasaver[(localObject1._id + 294)].y = ((int)(2.0F * paramFloat2 / 3.0F));
      gpMmdatasaver[(localObject1._id + 294)].radius = 0;
      gpMmdatasaver[(localObject1._id + 294)].block = paramArrayOfByte[21];
    }
    localArrayList.clear();
    paramInt1 = 0;
    while (paramInt1 < 24)
    {
      localObject1 = new GPNormalKey();
      paramInt2 = paramInt1 * 10;
      ((GPNormalKey)localObject1).setType(paramArrayOfByte[(paramInt2 + 24)]);
      ((GPNormalKey)localObject1).setCode(paramArrayOfByte[(paramInt2 + 25)]);
      ((GPNormalKey)localObject1).setP1xl(paramArrayOfByte[(paramInt2 + 26)]);
      ((GPNormalKey)localObject1).setP1xh(paramArrayOfByte[(paramInt2 + 27)]);
      ((GPNormalKey)localObject1).setP1yl(paramArrayOfByte[(paramInt2 + 28)]);
      ((GPNormalKey)localObject1).setP1yh(paramArrayOfByte[(paramInt2 + 29)]);
      ((GPNormalKey)localObject1).setP2xl(paramArrayOfByte[(paramInt2 + 30)]);
      ((GPNormalKey)localObject1).setP2xh(paramArrayOfByte[(paramInt2 + 31)]);
      ((GPNormalKey)localObject1).setP2yl(paramArrayOfByte[(paramInt2 + 32)]);
      ((GPNormalKey)localObject1).setP2yh(paramArrayOfByte[(paramInt2 + 33)]);
      localArrayList.add(localObject1);
      paramInt1 += 1;
    }
    paramArrayOfByte = new StringBuilder();
    paramArrayOfByte.append("--------- mGPNormalKeyList.size() = ");
    paramArrayOfByte.append(localArrayList.size());
    MyLog.i("my_tag", paramArrayOfByte.toString());
    paramInt1 = 0;
    while (paramInt1 < localArrayList.size())
    {
      new GPKey();
      paramArrayOfByte = CommonUtils.getGPKey(((GPNormalKey)localArrayList.get(paramInt1)).getCode() & 0xFF);
      if (paramArrayOfByte != null)
      {
        if (((GPNormalKey)localArrayList.get(paramInt1)).getType() == 1)
        {
          gpMmdatasaver[paramArrayOfByte._id].property = 0;
          gpMmdatasaver[paramArrayOfByte._id].name = paramArrayOfByte.name;
          gpMmdatasaver[paramArrayOfByte._id].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
          gpMmdatasaver[paramArrayOfByte._id].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
        }
        if ((((GPNormalKey)localArrayList.get(paramInt1)).getType() == 2) && (paramArrayOfByte._id < 31))
        {
          gpMmdatasaver[(paramArrayOfByte._id + 134)].property = 4;
          gpMmdatasaver[(paramArrayOfByte._id + 134)].name = paramArrayOfByte.name;
          gpMmdatasaver[(paramArrayOfByte._id + 134)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
          gpMmdatasaver[(paramArrayOfByte._id + 134)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
          gpMmdatasaver[(paramArrayOfByte._id + 166)].property = 4;
          gpMmdatasaver[(paramArrayOfByte._id + 166)].name = paramArrayOfByte.name;
          gpMmdatasaver[(paramArrayOfByte._id + 166)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP2yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP2yh()) * paramFloat1 / deviceY));
          gpMmdatasaver[(paramArrayOfByte._id + 166)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP2xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh())) * paramFloat2 / deviceX));
        }
        if ((((GPNormalKey)localArrayList.get(paramInt1)).getType() == 3) && (paramArrayOfByte._id < 31))
        {
          gpMmdatasaver[(paramArrayOfByte._id + 198)].property = 5;
          gpMmdatasaver[(paramArrayOfByte._id + 198)].name = paramArrayOfByte.name;
          gpMmdatasaver[(paramArrayOfByte._id + 198)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
          gpMmdatasaver[(paramArrayOfByte._id + 198)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
          gpMmdatasaver[(paramArrayOfByte._id + 230)].property = 5;
          gpMmdatasaver[(paramArrayOfByte._id + 230)].name = paramArrayOfByte.name;
          gpMmdatasaver[(paramArrayOfByte._id + 230)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP2yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP2yh()) * paramFloat1 / deviceY));
          gpMmdatasaver[(paramArrayOfByte._id + 230)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP2xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh())) * paramFloat2 / deviceX));
        }
        if (((GPNormalKey)localArrayList.get(paramInt1)).getType() == 4)
        {
          gpMmdatasaver[(paramArrayOfByte._id + 262)].property = 6;
          gpMmdatasaver[(paramArrayOfByte._id + 262)].name = paramArrayOfByte.name;
          gpMmdatasaver[(paramArrayOfByte._id + 262)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
          gpMmdatasaver[(paramArrayOfByte._id + 262)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
        }
        if (((GPNormalKey)localArrayList.get(paramInt1)).getType() == 5)
        {
          gpMmdatasaver[(paramArrayOfByte._id + 38)].property = 2;
          gpMmdatasaver[(paramArrayOfByte._id + 38)].name = paramArrayOfByte.name;
          gpMmdatasaver[(paramArrayOfByte._id + 38)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
          gpMmdatasaver[(paramArrayOfByte._id + 38)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
          gpMmdatasaver[(paramArrayOfByte._id + 38)].radius = 0;
          gpMmdatasaver[(paramArrayOfByte._id + 38)].block = 0;
          gpMmdatasaver[(paramArrayOfByte._id + 38)].reverse = 0;
        }
        if (((GPNormalKey)localArrayList.get(paramInt1)).getType() == 6)
        {
          if (((GPNormalKey)localArrayList.get(paramInt1)).getP2xl() == 1)
          {
            gpMmdatasaver[(paramArrayOfByte._id + 38)].property = 1;
            gpMmdatasaver[(paramArrayOfByte._id + 38)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 38)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 38)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
            gpMmdatasaver[(paramArrayOfByte._id + 38)].radius = ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP2xh(), ((GPNormalKey)localArrayList.get(paramInt1)).getP2yl());
            gpMmdatasaver[(paramArrayOfByte._id + 38)].block = 0;
            gpMmdatasaver[(paramArrayOfByte._id + 38)].reverse = ((GPNormalKey)localArrayList.get(paramInt1)).getP2yh();
          }
          if (((GPNormalKey)localArrayList.get(paramInt1)).getP2xl() == 2)
          {
            gpMmdatasaver[(paramArrayOfByte._id + 70)].property = 2;
            gpMmdatasaver[(paramArrayOfByte._id + 70)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 70)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 70)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
            gpMmdatasaver[(paramArrayOfByte._id + 70)].radius = ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP2xh(), ((GPNormalKey)localArrayList.get(paramInt1)).getP2yl());
            gpMmdatasaver[(paramArrayOfByte._id + 70)].block = 0;
            gpMmdatasaver[(paramArrayOfByte._id + 70)].reverse = ((GPNormalKey)localArrayList.get(paramInt1)).getP2yh();
          }
          if (((GPNormalKey)localArrayList.get(paramInt1)).getP2xl() == 3)
          {
            gpMmdatasaver[(paramArrayOfByte._id + 102)].property = 3;
            gpMmdatasaver[(paramArrayOfByte._id + 102)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 102)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 102)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
            gpMmdatasaver[(paramArrayOfByte._id + 102)].radius = ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP2xh(), ((GPNormalKey)localArrayList.get(paramInt1)).getP2yl());
            gpMmdatasaver[(paramArrayOfByte._id + 102)].block = 0;
            gpMmdatasaver[(paramArrayOfByte._id + 102)].reverse = ((GPNormalKey)localArrayList.get(paramInt1)).getP2yh();
          }
          if (((GPNormalKey)localArrayList.get(paramInt1)).getP2xl() == 4)
          {
            gpMmdatasaver[(paramArrayOfByte._id + 38)].property = 1;
            gpMmdatasaver[(paramArrayOfByte._id + 38)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 38)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 38)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
            gpMmdatasaver[(paramArrayOfByte._id + 38)].radius = 0;
            gpMmdatasaver[(paramArrayOfByte._id + 38)].block = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh();
            gpMmdatasaver[(paramArrayOfByte._id + 38)].reverse = 0;
          }
          if (((GPNormalKey)localArrayList.get(paramInt1)).getP2xl() == 5)
          {
            gpMmdatasaver[(paramArrayOfByte._id + 262)].property = 6;
            gpMmdatasaver[(paramArrayOfByte._id + 262)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 262)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 262)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
            gpMmdatasaver[(paramArrayOfByte._id + 262)].radius = 0;
            gpMmdatasaver[(paramArrayOfByte._id + 262)].block = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh();
            gpMmdatasaver[(paramArrayOfByte._id + 262)].reverse = 0;
          }
          if (((GPNormalKey)localArrayList.get(paramInt1)).getP2xl() == 6)
          {
            gpMmdatasaver[(paramArrayOfByte._id + 102)].property = 3;
            gpMmdatasaver[(paramArrayOfByte._id + 102)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 102)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 102)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
            gpMmdatasaver[(paramArrayOfByte._id + 102)].radius = 0;
            gpMmdatasaver[(paramArrayOfByte._id + 102)].block = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh();
            gpMmdatasaver[(paramArrayOfByte._id + 102)].reverse = 0;
          }
        }
        if (((GPNormalKey)localArrayList.get(paramInt1)).getType() == 7)
        {
          gpMmdatasaver[(paramArrayOfByte._id + 326)].property = 8;
          gpMmdatasaver[(paramArrayOfByte._id + 326)].name = paramArrayOfByte.name;
          gpMmdatasaver[(paramArrayOfByte._id + 326)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
          gpMmdatasaver[(paramArrayOfByte._id + 326)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
        }
        if (((GPNormalKey)localArrayList.get(paramInt1)).getType() == 8)
        {
          if (paramArrayOfByte._id < 31)
          {
            gpMmdatasaver[(paramArrayOfByte._id + 358)].property = 9;
            gpMmdatasaver[(paramArrayOfByte._id + 358)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 358)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh() & 0xFF) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 358)].y = ((int)((deviceX - ChangeDataUtil.getValueBy16Bit(deviceX, ((GPNormalKey)localArrayList.get(paramInt1)).getP1xl() & 0xFF)) * paramFloat2 / deviceX));
            gpMmdatasaver[(paramArrayOfByte._id + 358)].radius = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xl();
            gpMmdatasaver[(paramArrayOfByte._id + 358)].block = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh();
            gpMmdatasaver[(paramArrayOfByte._id + 390)].property = 9;
            gpMmdatasaver[(paramArrayOfByte._id + 390)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 390)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh() & 0xFF) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 390)].y = ((int)((deviceX - ChangeDataUtil.getValueBy16Bit(deviceX, ((GPNormalKey)localArrayList.get(paramInt1)).getP1yl() & 0xFF)) * paramFloat2 / deviceX));
            gpMmdatasaver[(paramArrayOfByte._id + 390)].radius = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xl();
            gpMmdatasaver[(paramArrayOfByte._id + 390)].block = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh();
          }
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("name = ");
          ((StringBuilder)localObject1).append(gpMmdatasaver[(paramArrayOfByte._id + 358)].name);
          ((StringBuilder)localObject1).append("----x1 = ");
          ((StringBuilder)localObject1).append(gpMmdatasaver[(paramArrayOfByte._id + 358)].x);
          ((StringBuilder)localObject1).append(";  y1 = ");
          ((StringBuilder)localObject1).append(gpMmdatasaver[(paramArrayOfByte._id + 358)].y);
          ((StringBuilder)localObject1).append(";   x2 = ");
          ((StringBuilder)localObject1).append(gpMmdatasaver[(paramArrayOfByte._id + 390)].x);
          ((StringBuilder)localObject1).append(";   y2 = ");
          ((StringBuilder)localObject1).append(gpMmdatasaver[(paramArrayOfByte._id + 390)].y);
          MyLog.i("my_tag", ((StringBuilder)localObject1).toString());
        }
        if ((((GPNormalKey)localArrayList.get(paramInt1)).getType() == 9) && (paramArrayOfByte._id < 31))
        {
          gpMmdatasaver[(paramArrayOfByte._id + 422)].property = 10;
          gpMmdatasaver[(paramArrayOfByte._id + 422)].name = paramArrayOfByte.name;
          gpMmdatasaver[(paramArrayOfByte._id + 422)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
          gpMmdatasaver[(paramArrayOfByte._id + 422)].y = ((int)((deviceX - ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh())) * paramFloat2 / deviceX));
          gpMmdatasaver[(paramArrayOfByte._id + 422)].radius = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xl();
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("点击频率 = ");
          ((StringBuilder)localObject1).append(gpMmdatasaver[(paramArrayOfByte._id + 422)].radius);
          MyLog.i("crc_tag", ((StringBuilder)localObject1).toString());
          paramArrayOfByte = new StringBuilder();
          paramArrayOfByte.append("------TURO按键 = ");
          paramArrayOfByte.append(gpMmdatasaver[paramInt1].name);
          MyLog.i("crc_tag", paramArrayOfByte.toString());
        }
      }
      paramInt1 += 1;
    }
    if (paramInt5 == 0)
      MyApplication.setMdatasaverScenes0(gpMmdatasaver);
    if (paramInt5 == 1)
      MyApplication.setMdatasaverScenes1(gpMmdatasaver);
    return gpMmdatasaver;
  }

  public static DataSaver[] initMdatasaverByByteArrayPortraitScreen(byte[] paramArrayOfByte, float paramFloat1, float paramFloat2, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    paramInt3 = 0;
    while (paramInt3 < gpsetupdialog.length)
    {
      gpMmdatasaver[paramInt3] = new DataSaver();
      gpMmdatasaver[paramInt3].property = -1;
      gpMmdatasaver[paramInt3].name = "-1";
      gpMmdatasaver[paramInt3].x = -1;
      gpMmdatasaver[paramInt3].y = -1;
      gpMmdatasaver[paramInt3].radius = 0;
      gpMmdatasaver[paramInt3].block = 0;
      gpMmdatasaver[paramInt3].reverse = 0;
      paramInt3 += 1;
    }
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = new ArrayList();
    deviceY = ChangeDataUtil.getIntegerByBit(paramArrayOfByte[5], paramArrayOfByte[6]);
    deviceX = ChangeDataUtil.getIntegerByBit(paramArrayOfByte[7], paramArrayOfByte[8]);
    if ((paramInt1 != 0) && (paramInt2 != 0))
    {
      Object localObject2 = new byte[(paramInt2 * 4 + 2) * paramInt1];
      System.arraycopy(paramArrayOfByte, paramArrayOfByte.length - localObject2.length, localObject2, 0, localObject2.length);
      ((ArrayList)localObject1).clear();
      paramInt2 = 0;
      while (paramInt2 < paramInt1)
      {
        byte[] arrayOfByte = new byte[62];
        System.arraycopy(localObject2, paramInt2 * 62, arrayOfByte, 0, 62);
        if (arrayOfByte[0] == 14)
          ((ArrayList)localObject1).add(MacroKeyUtils.AnalysisMacroData(arrayOfByte));
        paramInt2 += 1;
      }
      if (((ArrayList)localObject1).size() > 0)
      {
        paramInt1 = 0;
        while (paramInt1 < ((ArrayList)localObject1).size())
        {
          new GPKey();
          localObject2 = CommonUtils.getGPKey(((MacroKey)((ArrayList)localObject1).get(paramInt1)).getKeyCode() & 0xFF);
          if ((localObject2 != null) && (((GPKey)localObject2).scenes == paramInt5))
            initMacroKeyPortraitScreen((GPKey)localObject2, (MacroKey)((ArrayList)localObject1).get(paramInt1), paramFloat1, paramFloat2);
          paramInt1 += 1;
        }
      }
    }
    localArrayList.clear();
    paramInt1 = 0;
    while (paramInt1 < paramInt4)
    {
      localObject1 = new GPNormalKey();
      paramInt2 = paramInt1 * 10;
      ((GPNormalKey)localObject1).setType(paramArrayOfByte[(paramInt2 + 10)]);
      ((GPNormalKey)localObject1).setCode(paramArrayOfByte[(paramInt2 + 11)]);
      ((GPNormalKey)localObject1).setP1xl(paramArrayOfByte[(paramInt2 + 12)]);
      ((GPNormalKey)localObject1).setP1xh(paramArrayOfByte[(paramInt2 + 13)]);
      ((GPNormalKey)localObject1).setP1yl(paramArrayOfByte[(paramInt2 + 14)]);
      ((GPNormalKey)localObject1).setP1yh(paramArrayOfByte[(paramInt2 + 15)]);
      ((GPNormalKey)localObject1).setP2xl(paramArrayOfByte[(paramInt2 + 16)]);
      ((GPNormalKey)localObject1).setP2xh(paramArrayOfByte[(paramInt2 + 17)]);
      ((GPNormalKey)localObject1).setP2yl(paramArrayOfByte[(paramInt2 + 18)]);
      ((GPNormalKey)localObject1).setP2yh(paramArrayOfByte[(paramInt2 + 19)]);
      localArrayList.add(localObject1);
      paramInt1 += 1;
    }
    paramInt1 = 0;
    while (paramInt1 < localArrayList.size())
    {
      new GPKey();
      paramArrayOfByte = CommonUtils.getGPKey(((GPNormalKey)localArrayList.get(paramInt1)).getCode() & 0xFF);
      if ((paramArrayOfByte != null) && (paramArrayOfByte.scenes == paramInt5))
      {
        if (((GPNormalKey)localArrayList.get(paramInt1)).getType() == 1)
        {
          gpMmdatasaver[paramArrayOfByte._id].property = 0;
          gpMmdatasaver[paramArrayOfByte._id].name = paramArrayOfByte.name;
          gpMmdatasaver[paramArrayOfByte._id].y = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
          gpMmdatasaver[paramArrayOfByte._id].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh()) * paramFloat2 / deviceX));
        }
        if ((((GPNormalKey)localArrayList.get(paramInt1)).getType() == 2) && (paramArrayOfByte._id < 31))
        {
          gpMmdatasaver[(paramArrayOfByte._id + 134)].property = 4;
          gpMmdatasaver[(paramArrayOfByte._id + 134)].name = paramArrayOfByte.name;
          gpMmdatasaver[(paramArrayOfByte._id + 134)].y = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
          gpMmdatasaver[(paramArrayOfByte._id + 134)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh()) * paramFloat2 / deviceX));
          gpMmdatasaver[(paramArrayOfByte._id + 166)].property = 4;
          gpMmdatasaver[(paramArrayOfByte._id + 166)].name = paramArrayOfByte.name;
          gpMmdatasaver[(paramArrayOfByte._id + 166)].y = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP2yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP2yh()) * paramFloat1 / deviceY));
          gpMmdatasaver[(paramArrayOfByte._id + 166)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP2xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh()) * paramFloat2 / deviceX));
        }
        if ((((GPNormalKey)localArrayList.get(paramInt1)).getType() == 3) && (paramArrayOfByte._id < 31))
        {
          gpMmdatasaver[(paramArrayOfByte._id + 198)].property = 5;
          gpMmdatasaver[(paramArrayOfByte._id + 198)].name = paramArrayOfByte.name;
          gpMmdatasaver[(paramArrayOfByte._id + 198)].y = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
          gpMmdatasaver[(paramArrayOfByte._id + 198)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh()) * paramFloat2 / deviceX));
          gpMmdatasaver[(paramArrayOfByte._id + 230)].property = 5;
          gpMmdatasaver[(paramArrayOfByte._id + 230)].name = paramArrayOfByte.name;
          gpMmdatasaver[(paramArrayOfByte._id + 230)].y = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP2yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP2yh()) * paramFloat1 / deviceY));
          gpMmdatasaver[(paramArrayOfByte._id + 230)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP2xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh()) * paramFloat2 / deviceX));
        }
        if (((GPNormalKey)localArrayList.get(paramInt1)).getType() == 4)
        {
          gpMmdatasaver[(paramArrayOfByte._id + 262)].property = 6;
          gpMmdatasaver[(paramArrayOfByte._id + 262)].name = paramArrayOfByte.name;
          gpMmdatasaver[(paramArrayOfByte._id + 262)].y = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
          gpMmdatasaver[(paramArrayOfByte._id + 262)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh()) * paramFloat2 / deviceX));
        }
        if (((GPNormalKey)localArrayList.get(paramInt1)).getType() == 5)
        {
          gpMmdatasaver[(paramArrayOfByte._id + 38)].property = 2;
          gpMmdatasaver[(paramArrayOfByte._id + 38)].name = paramArrayOfByte.name;
          gpMmdatasaver[(paramArrayOfByte._id + 38)].y = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
          gpMmdatasaver[(paramArrayOfByte._id + 38)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh()) * paramFloat2 / deviceX));
          gpMmdatasaver[(paramArrayOfByte._id + 38)].radius = 0;
          gpMmdatasaver[(paramArrayOfByte._id + 38)].block = 0;
          gpMmdatasaver[(paramArrayOfByte._id + 38)].reverse = 0;
        }
        if (((GPNormalKey)localArrayList.get(paramInt1)).getType() == 6)
        {
          if (((GPNormalKey)localArrayList.get(paramInt1)).getP2xl() == 1)
          {
            gpMmdatasaver[(paramArrayOfByte._id + 38)].property = 1;
            gpMmdatasaver[(paramArrayOfByte._id + 38)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 38)].y = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 38)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh()) * paramFloat2 / deviceX));
            gpMmdatasaver[(paramArrayOfByte._id + 38)].radius = ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP2xh(), ((GPNormalKey)localArrayList.get(paramInt1)).getP2yl());
            gpMmdatasaver[(paramArrayOfByte._id + 38)].block = 0;
            gpMmdatasaver[(paramArrayOfByte._id + 38)].reverse = ((GPNormalKey)localArrayList.get(paramInt1)).getP2yh();
          }
          if (((GPNormalKey)localArrayList.get(paramInt1)).getP2xl() == 2)
          {
            gpMmdatasaver[(paramArrayOfByte._id + 70)].property = 2;
            gpMmdatasaver[(paramArrayOfByte._id + 70)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 70)].y = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 70)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh()) * paramFloat2 / deviceX));
            gpMmdatasaver[(paramArrayOfByte._id + 70)].radius = ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP2xh(), ((GPNormalKey)localArrayList.get(paramInt1)).getP2yl());
            gpMmdatasaver[(paramArrayOfByte._id + 70)].block = 0;
            gpMmdatasaver[(paramArrayOfByte._id + 70)].reverse = ((GPNormalKey)localArrayList.get(paramInt1)).getP2yh();
          }
          if (((GPNormalKey)localArrayList.get(paramInt1)).getP2xl() == 3)
          {
            gpMmdatasaver[(paramArrayOfByte._id + 102)].property = 3;
            gpMmdatasaver[(paramArrayOfByte._id + 102)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 102)].y = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 102)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh()) * paramFloat2 / deviceX));
            gpMmdatasaver[(paramArrayOfByte._id + 102)].radius = ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP2xh(), ((GPNormalKey)localArrayList.get(paramInt1)).getP2yl());
            gpMmdatasaver[(paramArrayOfByte._id + 102)].block = 0;
            gpMmdatasaver[(paramArrayOfByte._id + 102)].reverse = ((GPNormalKey)localArrayList.get(paramInt1)).getP2yh();
          }
          if (((GPNormalKey)localArrayList.get(paramInt1)).getP2xl() == 4)
          {
            gpMmdatasaver[(paramArrayOfByte._id + 38)].property = 1;
            gpMmdatasaver[(paramArrayOfByte._id + 38)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 38)].y = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 38)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh()) * paramFloat2 / deviceX));
            gpMmdatasaver[(paramArrayOfByte._id + 38)].radius = 0;
            gpMmdatasaver[(paramArrayOfByte._id + 38)].block = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh();
            gpMmdatasaver[(paramArrayOfByte._id + 38)].reverse = ((GPNormalKey)localArrayList.get(paramInt1)).getP2yh();
          }
          if (((GPNormalKey)localArrayList.get(paramInt1)).getP2xl() == 5)
          {
            gpMmdatasaver[(paramArrayOfByte._id + 262)].property = 6;
            gpMmdatasaver[(paramArrayOfByte._id + 262)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 262)].y = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 262)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh()) * paramFloat2 / deviceX));
            gpMmdatasaver[(paramArrayOfByte._id + 262)].radius = 0;
            gpMmdatasaver[(paramArrayOfByte._id + 262)].block = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh();
            gpMmdatasaver[(paramArrayOfByte._id + 262)].reverse = ((GPNormalKey)localArrayList.get(paramInt1)).getP2yh();
          }
          if (((GPNormalKey)localArrayList.get(paramInt1)).getP2xl() == 6)
          {
            gpMmdatasaver[(paramArrayOfByte._id + 102)].property = 3;
            gpMmdatasaver[(paramArrayOfByte._id + 102)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 102)].y = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 102)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh()) * paramFloat2 / deviceX));
            gpMmdatasaver[(paramArrayOfByte._id + 102)].radius = 0;
            gpMmdatasaver[(paramArrayOfByte._id + 102)].block = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh();
            gpMmdatasaver[(paramArrayOfByte._id + 102)].reverse = ((GPNormalKey)localArrayList.get(paramInt1)).getP2yh();
          }
        }
        if (((GPNormalKey)localArrayList.get(paramInt1)).getType() == 7)
        {
          gpMmdatasaver[(paramArrayOfByte._id + 326)].property = 8;
          gpMmdatasaver[(paramArrayOfByte._id + 326)].name = paramArrayOfByte.name;
          gpMmdatasaver[(paramArrayOfByte._id + 326)].y = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
          gpMmdatasaver[(paramArrayOfByte._id + 326)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh()) * paramFloat2 / deviceX));
        }
        if (((GPNormalKey)localArrayList.get(paramInt1)).getType() == 8)
        {
          if (paramArrayOfByte._id < 31)
          {
            gpMmdatasaver[(paramArrayOfByte._id + 358)].property = 9;
            gpMmdatasaver[(paramArrayOfByte._id + 358)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 358)].y = ((int)(ChangeDataUtil.getValueBy16Bit(deviceX, ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh() & 0xFF) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 358)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, ((GPNormalKey)localArrayList.get(paramInt1)).getP1xl() & 0xFF) * paramFloat2 / deviceX));
            gpMmdatasaver[(paramArrayOfByte._id + 358)].radius = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xl();
            gpMmdatasaver[(paramArrayOfByte._id + 358)].block = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh();
            gpMmdatasaver[(paramArrayOfByte._id + 390)].property = 9;
            gpMmdatasaver[(paramArrayOfByte._id + 390)].name = paramArrayOfByte.name;
            gpMmdatasaver[(paramArrayOfByte._id + 390)].y = ((int)(ChangeDataUtil.getValueBy16Bit(deviceX, ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh() & 0xFF) * paramFloat1 / deviceY));
            gpMmdatasaver[(paramArrayOfByte._id + 390)].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, ((GPNormalKey)localArrayList.get(paramInt1)).getP1yl() & 0xFF) * paramFloat2 / deviceX));
            gpMmdatasaver[(paramArrayOfByte._id + 390)].radius = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xl();
            gpMmdatasaver[(paramArrayOfByte._id + 390)].block = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh();
          }
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("name = ");
          ((StringBuilder)localObject1).append(gpMmdatasaver[(paramArrayOfByte._id + 358)].name);
          ((StringBuilder)localObject1).append("----x1 = ");
          ((StringBuilder)localObject1).append(gpMmdatasaver[(paramArrayOfByte._id + 358)].x);
          ((StringBuilder)localObject1).append(";  y1 = ");
          ((StringBuilder)localObject1).append(gpMmdatasaver[(paramArrayOfByte._id + 358)].y);
          ((StringBuilder)localObject1).append(";   x2 = ");
          ((StringBuilder)localObject1).append(gpMmdatasaver[(paramArrayOfByte._id + 390)].x);
          ((StringBuilder)localObject1).append(";   y2 = ");
          ((StringBuilder)localObject1).append(gpMmdatasaver[(paramArrayOfByte._id + 390)].y);
          MyLog.i("my_tag", ((StringBuilder)localObject1).toString());
        }
        if ((((GPNormalKey)localArrayList.get(paramInt1)).getType() == 9) && (paramArrayOfByte._id < 31))
        {
          gpMmdatasaver[(paramArrayOfByte._id + 422)].property = 10;
          gpMmdatasaver[(paramArrayOfByte._id + 422)].name = paramArrayOfByte.name;
          gpMmdatasaver[(paramArrayOfByte._id + 422)].y = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * paramFloat1 / deviceY));
          gpMmdatasaver[(paramArrayOfByte._id + 422)].x = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1xl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh()) * paramFloat2 / deviceX));
          gpMmdatasaver[(paramArrayOfByte._id + 422)].radius = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xl();
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("点击频率 = ");
          ((StringBuilder)localObject1).append(gpMmdatasaver[(paramArrayOfByte._id + 422)].radius);
          MyLog.i("crc_tag", ((StringBuilder)localObject1).toString());
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("------TURO按键 = ");
          ((StringBuilder)localObject1).append(gpMmdatasaver[paramInt1].name);
          MyLog.i("crc_tag", ((StringBuilder)localObject1).toString());
        }
        if ((((GPNormalKey)localArrayList.get(paramInt1)).getType() == 10) && (paramArrayOfByte._id < 31))
        {
          gpMmdatasaver[paramArrayOfByte._id].property = 12;
          gpMmdatasaver[paramArrayOfByte._id].name = paramArrayOfByte.name;
          gpMmdatasaver[paramArrayOfByte._id].y = ((int)(ChangeDataUtil.getValueBy16Bit(deviceX, ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh() & 0xFF) * paramFloat1 / deviceY));
          gpMmdatasaver[paramArrayOfByte._id].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, ((GPNormalKey)localArrayList.get(paramInt1)).getP1xl() & 0xFF) * paramFloat2 / deviceX));
          gpMmdatasaver[paramArrayOfByte._id].radius = 288;
          gpMmdatasaver[paramArrayOfByte._id].block = 0;
          gpMmdatasaver[paramArrayOfByte._id].reverse = 70;
        }
        if ((((GPNormalKey)localArrayList.get(paramInt1)).getType() == 11) && (paramArrayOfByte._id < 31))
        {
          gpMmdatasaver[paramArrayOfByte._id].property = 12;
          gpMmdatasaver[paramArrayOfByte._id].name = paramArrayOfByte.name;
          gpMmdatasaver[paramArrayOfByte._id].y = ((int)(ChangeDataUtil.getValueBy16Bit(deviceX, ((GPNormalKey)localArrayList.get(paramInt1)).getP1xh() & 0xFF) * paramFloat1 / deviceY));
          gpMmdatasaver[paramArrayOfByte._id].x = ((int)(ChangeDataUtil.getValueBy16Bit(deviceY, ((GPNormalKey)localArrayList.get(paramInt1)).getP1xl() & 0xFF) * paramFloat2 / deviceX));
          gpMmdatasaver[paramArrayOfByte._id].radius = ((int)(ChangeDataUtil.getIntegerByBit(((GPNormalKey)localArrayList.get(paramInt1)).getP1yl(), ((GPNormalKey)localArrayList.get(paramInt1)).getP1yh()) * (paramFloat2 / deviceX)));
          gpMmdatasaver[paramArrayOfByte._id].block = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xl();
          gpMmdatasaver[paramArrayOfByte._id].reverse = ((GPNormalKey)localArrayList.get(paramInt1)).getP2xh();
        }
      }
      paramInt1 += 1;
    }
    if (paramInt5 == 0)
      MyApplication.setMdatasaverScenes0(gpMmdatasaver);
    if (paramInt5 == 1)
      MyApplication.setMdatasaverScenes1(gpMmdatasaver);
    return gpMmdatasaver;
  }
}
