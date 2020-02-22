package com.qx.qgbox.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper
{
  private static final String DATABASE_NAME = "installedapp.db";
  private static final int DATABASE_VERSION = 1;
  private static DatabaseHelper mInstance;

  public DatabaseHelper(Context paramContext)
  {
    super(paramContext, "installedapp.db", null, 1);
  }

  public static DatabaseHelper getInstance(Context paramContext)
  {
    try
    {
      if (mInstance == null)
        mInstance = new DatabaseHelper(paramContext);
      paramContext = mInstance;
      return paramContext;
    }
    finally
    {
    }
    throw paramContext;
  }

  public boolean deleteDatebase(Context paramContext)
  {
    return paramContext.deleteDatabase("installedapp.db");
  }

  public void onCreate(SQLiteDatabase paramSQLiteDatabase)
  {
    paramSQLiteDatabase.execSQL("create table IF NOT EXISTS installedapp(_id integer primary key autoincrement,presetPath varchar(120),appPackName varchar(120),appName varchar(120),iconPath varchar(120),deviceType integer,addTime DATETIME)");
  }

  public void onUpgrade(SQLiteDatabase paramSQLiteDatabase, int paramInt1, int paramInt2)
  {
    paramSQLiteDatabase.execSQL("ALTER TABLE records ADD COLUMN other STRING");
  }
}
