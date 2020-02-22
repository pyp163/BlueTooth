package com.qx.qgbox.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.qx.qgbox.entitys.SunyesMaxGamePreset;
import java.util.ArrayList;

public class DBManager
{
  public static final int DEVICE_TYPE_GP = 1;
  public static final int DEVICE_TYPE_SP = 0;
  private SQLiteDatabase db;
  private DatabaseHelper helper;

  public DBManager(Context paramContext)
  {
    this.helper = DatabaseHelper.getInstance(paramContext);
    try
    {
      this.db = this.helper.getWritableDatabase();
      return;
    }
    catch (Exception paramContext)
    {
    }
  }

  public void addSunyesMaxGamePreset(SunyesMaxGamePreset paramSunyesMaxGamePreset)
  {
    this.db.beginTransaction();
    try
    {
      this.db.execSQL("INSERT INTO installedapp VALUES(null, ?,?,?,?,?,?)", new Object[] { paramSunyesMaxGamePreset.getPresetPath(), paramSunyesMaxGamePreset.getAppPackageName(), paramSunyesMaxGamePreset.getAppName(), paramSunyesMaxGamePreset.getIconPath(), Integer.valueOf(paramSunyesMaxGamePreset.getDeviceType()), paramSunyesMaxGamePreset.getAddTime() });
      this.db.setTransactionSuccessful();
      return;
    }
    finally
    {
      this.db.endTransaction();
    }
    throw paramSunyesMaxGamePreset;
  }

  public void closeDB()
  {
    this.db.close();
  }

  public void delete(int paramInt)
  {
    SQLiteDatabase localSQLiteDatabase = this.db;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramInt);
    localStringBuilder.append("");
    localSQLiteDatabase.delete("installedapp", " _id = ?", new String[] { localStringBuilder.toString() });
  }

  public ArrayList<SunyesMaxGamePreset> queryAll(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    Cursor localCursor = queryTheCursorCursor(paramInt);
    while (localCursor.moveToNext())
    {
      SunyesMaxGamePreset localSunyesMaxGamePreset = new SunyesMaxGamePreset();
      localSunyesMaxGamePreset.smgpId = localCursor.getInt(localCursor.getColumnIndex("_id"));
      localSunyesMaxGamePreset.presetPath = localCursor.getString(localCursor.getColumnIndex("presetPath"));
      localSunyesMaxGamePreset.appPackageName = localCursor.getString(localCursor.getColumnIndex("appPackName"));
      localSunyesMaxGamePreset.iconPath = localCursor.getString(localCursor.getColumnIndex("iconPath"));
      localSunyesMaxGamePreset.addTime = localCursor.getString(localCursor.getColumnIndex("addTime"));
      localSunyesMaxGamePreset.deviceType = localCursor.getInt(localCursor.getColumnIndex("deviceType"));
      localSunyesMaxGamePreset.appName = localCursor.getString(localCursor.getColumnIndex("appName"));
      localArrayList.add(localSunyesMaxGamePreset);
    }
    localCursor.close();
    return localArrayList;
  }

  public SunyesMaxGamePreset queryByGame(String paramString, int paramInt)
  {
    paramString = queryTheCursorCursorByGameName(paramString, paramInt);
    paramString.moveToNext();
    SunyesMaxGamePreset localSunyesMaxGamePreset = new SunyesMaxGamePreset();
    localSunyesMaxGamePreset.smgpId = paramString.getInt(paramString.getColumnIndex("_id"));
    localSunyesMaxGamePreset.presetPath = paramString.getString(paramString.getColumnIndex("presetPath"));
    localSunyesMaxGamePreset.appPackageName = paramString.getString(paramString.getColumnIndex("appPackName"));
    localSunyesMaxGamePreset.iconPath = paramString.getString(paramString.getColumnIndex("iconPath"));
    localSunyesMaxGamePreset.addTime = paramString.getString(paramString.getColumnIndex("addTime"));
    localSunyesMaxGamePreset.appName = paramString.getString(paramString.getColumnIndex("appName"));
    localSunyesMaxGamePreset.deviceType = paramString.getInt(paramString.getColumnIndex("deviceType"));
    paramString.close();
    return localSunyesMaxGamePreset;
  }

  public Cursor queryTheCursorCursor(int paramInt)
  {
    SQLiteDatabase localSQLiteDatabase = this.db;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SELECT * FROM installedapp where deviceType=");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(" ORDER BY addTime ASC");
    return localSQLiteDatabase.rawQuery(localStringBuilder.toString(), null);
  }

  public Cursor queryTheCursorCursorByGameName(String paramString, int paramInt)
  {
    SQLiteDatabase localSQLiteDatabase = this.db;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SELECT * FROM installedapp where appName='");
    localStringBuilder.append(paramString);
    localStringBuilder.append("' and deviceType=");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(";");
    return localSQLiteDatabase.rawQuery(localStringBuilder.toString(), null);
  }

  public void updateByGameName(String paramString1, String paramString2, int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("update installedapp set presetPath='");
    localStringBuilder.append(paramString2);
    localStringBuilder.append("' where appName='");
    localStringBuilder.append(paramString1);
    localStringBuilder.append("' AND deviceType=");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(";");
    paramString1 = localStringBuilder.toString();
    this.db.execSQL(paramString1);
  }
}
