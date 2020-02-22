package com.qx.qgbox.utils;

import com.qx.qgbox.entitys.MacroKey;

public class MacroKeyUtils
{
  public static MacroKey AnalysisMacroData(byte[] paramArrayOfByte)
  {
    MacroKey localMacroKey = new MacroKey();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("macrodata = ");
    localStringBuilder.append(CommonUtils.byteToString(paramArrayOfByte));
    MyLog.i("my_tag", localStringBuilder.toString());
    localMacroKey.setType(paramArrayOfByte[0]);
    localMacroKey.setKeyCode(paramArrayOfByte[1]);
    localMacroKey.setPoint0X(paramArrayOfByte[2]);
    localMacroKey.setPoint0Y(paramArrayOfByte[3]);
    localMacroKey.setPoint0MacroTime(paramArrayOfByte[4]);
    localMacroKey.setPoint0PauseTime(paramArrayOfByte[5]);
    localMacroKey.setPoint1X(paramArrayOfByte[6]);
    localMacroKey.setPoint1Y(paramArrayOfByte[7]);
    localMacroKey.setPoint1MacroTime(paramArrayOfByte[8]);
    localMacroKey.setPoint1PauseTime(paramArrayOfByte[9]);
    localMacroKey.setPoint2X(paramArrayOfByte[10]);
    localMacroKey.setPoint2Y(paramArrayOfByte[11]);
    localMacroKey.setPoint2MacroTime(paramArrayOfByte[12]);
    localMacroKey.setPoint2PauseTime(paramArrayOfByte[13]);
    localMacroKey.setPoint3X(paramArrayOfByte[14]);
    localMacroKey.setPoint3Y(paramArrayOfByte[15]);
    localMacroKey.setPoint3MacroTime(paramArrayOfByte[16]);
    localMacroKey.setPoint3PauseTime(paramArrayOfByte[17]);
    localMacroKey.setPoint4X(paramArrayOfByte[18]);
    localMacroKey.setPoint4Y(paramArrayOfByte[19]);
    localMacroKey.setPoint4MacroTime(paramArrayOfByte[20]);
    localMacroKey.setPoint4PauseTime(paramArrayOfByte[21]);
    localMacroKey.setPoint5X(paramArrayOfByte[22]);
    localMacroKey.setPoint5Y(paramArrayOfByte[23]);
    localMacroKey.setPoint5MacroTime(paramArrayOfByte[24]);
    localMacroKey.setPoint5PauseTime(paramArrayOfByte[25]);
    localMacroKey.setPoint6X(paramArrayOfByte[26]);
    localMacroKey.setPoint6Y(paramArrayOfByte[27]);
    localMacroKey.setPoint6MacroTime(paramArrayOfByte[28]);
    localMacroKey.setPoint6PauseTime(paramArrayOfByte[29]);
    localMacroKey.setPoint7X(paramArrayOfByte[30]);
    localMacroKey.setPoint7Y(paramArrayOfByte[31]);
    localMacroKey.setPoint7MacroTime(paramArrayOfByte[32]);
    localMacroKey.setPoint7PauseTime(paramArrayOfByte[33]);
    localMacroKey.setPoint8X(paramArrayOfByte[34]);
    localMacroKey.setPoint8Y(paramArrayOfByte[35]);
    localMacroKey.setPoint8MacroTime(paramArrayOfByte[36]);
    localMacroKey.setPoint8PauseTime(paramArrayOfByte[37]);
    localMacroKey.setPoint9X(paramArrayOfByte[38]);
    localMacroKey.setPoint9Y(paramArrayOfByte[39]);
    localMacroKey.setPoint9MacroTime(paramArrayOfByte[40]);
    localMacroKey.setPoint9PauseTime(paramArrayOfByte[41]);
    localMacroKey.setPoint10X(paramArrayOfByte[42]);
    localMacroKey.setPoint10Y(paramArrayOfByte[43]);
    localMacroKey.setPoint10MacroTime(paramArrayOfByte[44]);
    localMacroKey.setPoint10PauseTime(paramArrayOfByte[45]);
    localMacroKey.setPoint11X(paramArrayOfByte[46]);
    localMacroKey.setPoint11Y(paramArrayOfByte[47]);
    localMacroKey.setPoint11MacroTime(paramArrayOfByte[48]);
    localMacroKey.setPoint11PauseTime(paramArrayOfByte[49]);
    localMacroKey.setPoint12X(paramArrayOfByte[50]);
    localMacroKey.setPoint12Y(paramArrayOfByte[51]);
    localMacroKey.setPoint12MacroTime(paramArrayOfByte[52]);
    localMacroKey.setPoint12PauseTime(paramArrayOfByte[53]);
    localMacroKey.setPoint13X(paramArrayOfByte[54]);
    localMacroKey.setPoint13Y(paramArrayOfByte[55]);
    localMacroKey.setPoint13MacroTime(paramArrayOfByte[56]);
    localMacroKey.setPoint13PauseTime(paramArrayOfByte[57]);
    localMacroKey.setPoint14X(paramArrayOfByte[58]);
    localMacroKey.setPoint14Y(paramArrayOfByte[59]);
    localMacroKey.setPoint14MacroTime(paramArrayOfByte[60]);
    localMacroKey.setPoint14PauseTime(paramArrayOfByte[61]);
    return localMacroKey;
  }

  public static byte[] AnalysisMacroKey(MacroKey paramMacroKey)
  {
    return new byte[] { paramMacroKey.getType(), paramMacroKey.getKeyCode(), paramMacroKey.getPoint0X(), paramMacroKey.getPoint0Y(), paramMacroKey.getPoint0MacroTime(), paramMacroKey.getPoint0PauseTime(), paramMacroKey.getPoint1X(), paramMacroKey.getPoint1Y(), paramMacroKey.getPoint1MacroTime(), paramMacroKey.getPoint1PauseTime(), paramMacroKey.getPoint2X(), paramMacroKey.getPoint2Y(), paramMacroKey.getPoint2MacroTime(), paramMacroKey.getPoint2PauseTime(), paramMacroKey.getPoint3X(), paramMacroKey.getPoint3Y(), paramMacroKey.getPoint3MacroTime(), paramMacroKey.getPoint3PauseTime(), paramMacroKey.getPoint4X(), paramMacroKey.getPoint4Y(), paramMacroKey.getPoint4MacroTime(), paramMacroKey.getPoint4PauseTime(), paramMacroKey.getPoint5X(), paramMacroKey.getPoint5Y(), paramMacroKey.getPoint5MacroTime(), paramMacroKey.getPoint5PauseTime(), paramMacroKey.getPoint6X(), paramMacroKey.getPoint6Y(), paramMacroKey.getPoint6MacroTime(), paramMacroKey.getPoint6PauseTime(), paramMacroKey.getPoint7X(), paramMacroKey.getPoint7Y(), paramMacroKey.getPoint7MacroTime(), paramMacroKey.getPoint7PauseTime(), paramMacroKey.getPoint8X(), paramMacroKey.getPoint8Y(), paramMacroKey.getPoint8MacroTime(), paramMacroKey.getPoint8PauseTime(), paramMacroKey.getPoint9X(), paramMacroKey.getPoint9Y(), paramMacroKey.getPoint9MacroTime(), paramMacroKey.getPoint9PauseTime(), paramMacroKey.getPoint10X(), paramMacroKey.getPoint10Y(), paramMacroKey.getPoint10MacroTime(), paramMacroKey.getPoint10PauseTime(), paramMacroKey.getPoint11X(), paramMacroKey.getPoint11Y(), paramMacroKey.getPoint11MacroTime(), paramMacroKey.getPoint11PauseTime(), paramMacroKey.getPoint12X(), paramMacroKey.getPoint12Y(), paramMacroKey.getPoint12MacroTime(), paramMacroKey.getPoint12PauseTime(), paramMacroKey.getPoint13X(), paramMacroKey.getPoint13Y(), paramMacroKey.getPoint13MacroTime(), paramMacroKey.getPoint13PauseTime(), paramMacroKey.getPoint14X(), paramMacroKey.getPoint14Y(), paramMacroKey.getPoint14MacroTime(), paramMacroKey.getPoint14PauseTime() };
  }
}
