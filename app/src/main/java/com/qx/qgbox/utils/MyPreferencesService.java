package com.qx.qgbox.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@SuppressLint({"CommitPrefEdits"})
public class MyPreferencesService
{
  public static final String APP_SETTING_INFO_FILE = "app_setting_info_file";
  public static final String APP_SHARE_PREFERENCE_FILE = "app_config_file";
  public static final String AUTO_SYNC_PRESET_TIP_SETTING = "auto_sync_preset_tip";
  public static final String AUTO_SYNC_PRESET_TIP_SETTING_KEY = "auto_sync_preset_tip_key";
  public static final String FLOAT_BUTTON_SETTING = "float_btn_setup";
  public static final String FLOAT_BUTTON_SETTING_KEY = "float_btn_setup_key";
  public static final String FW_FILES_NAME = "fw_update";
  public static final String GUN_AUTO_FIRE_RATE = "gun_auto_fire_rate";
  public static final String GUN_CONFIG = "gun_config";
  public static final String GUN_FIRE_MODE = "gun_fire_mode";
  public static final String GUN_MODE = "gun_mode";
  public static final String GUN_NAME = "gun_name";
  public static final String GUN_RANGE = "gun_range";
  public static final String GUN_SYSTEM_SWITCH = "gun_system_switch";
  public static final String INSTALL_APP_PACKAGE_NAME_LIST = "install_app_package_name_list";
  public static final String IS_CLOSE_HELP = "is_close_help_flag";
  public static final String IS_CLOSE_TIP_FLAG = "is_close_tip_flag";
  public static final String IS_FIRST_INSTALL = "is_first_install";
  public static final String JP_APP_UPDATE_FLAG = "app_update_flag";
  public static final String JP_APP_URL = "app_url";
  public static final String JP_FLAG = "jp_flag";
  public static final String JP_UPDATE_FLAG = "update_flag";
  public static final String JP_URL = "load_url";
  public static final String MOUSE_RELLOER_SPEED = "mouse_relloer_speed";
  public static final String MOUSE_SPEED = "mouse_speed";
  public static final String MOUSE_TOUCH_MODE = "mouse_touch_mode";
  public static final String ProjectName = "ProjectName";
  public static final String STRING_LIST_FILE_NAME = "app_game_list";
  public static final String TOUCH_MODE = "touch_mode";
  public static final String TOUCH_RATIO = "touch_ratio";
  private Context context;

  public MyPreferencesService(Context paramContext)
  {
    this.context = paramContext;
  }

  public Map<String, Boolean> getBoolean(String paramString1, String paramString2)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put(paramString2, Boolean.valueOf(this.context.getSharedPreferences(paramString1, 0).getBoolean(paramString2, true)));
    return localHashMap;
  }

  public Map<String, Boolean> getBooleanDefaultFalse(String paramString1, String paramString2)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put(paramString2, Boolean.valueOf(this.context.getSharedPreferences(paramString1, 0).getBoolean(paramString2, false)));
    return localHashMap;
  }

  public Map<String, String> getDeviceFWVersionByMac(String paramString)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put(paramString, this.context.getSharedPreferences("fw_update", 0).getString(paramString, ""));
    return localHashMap;
  }

  public Map<String, String> getFWMacAddress(String paramString)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put(paramString, this.context.getSharedPreferences("fw_info", 0).getString(paramString, ""));
    return localHashMap;
  }

  public Map<String, String> getFWProjectName(String paramString)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put(paramString, this.context.getSharedPreferences("fw_info", 0).getString(paramString, ""));
    return localHashMap;
  }

  public Map<String, Boolean> getFWUpdateFlagBoolean(String paramString)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put(paramString, Boolean.valueOf(this.context.getSharedPreferences("fw_info", 0).getBoolean(paramString, true)));
    return localHashMap;
  }

  public Map<String, Integer> getInteger(String paramString1, String paramString2)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put(paramString2, Integer.valueOf(this.context.getSharedPreferences(paramString1, 0).getInt(paramString2, 0)));
    return localHashMap;
  }

  public Map<String, Integer> getMouseSpeed(String paramString1, String paramString2)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put(paramString2, Integer.valueOf(this.context.getSharedPreferences(paramString1, 0).getInt(paramString2, -1)));
    return localHashMap;
  }

  public Map<String, String> getString(String paramString1, String paramString2)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put(paramString2, this.context.getSharedPreferences(paramString1, 0).getString(paramString2, ""));
    return localHashMap;
  }

  public Map<String, Integer> getTouchMode(String paramString1, String paramString2)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put(paramString2, Integer.valueOf(this.context.getSharedPreferences(paramString1, 0).getInt(paramString2, 10001)));
    return localHashMap;
  }

  public ArrayList<String> loadArray(ArrayList<String> paramArrayList, String paramString)
  {
    Object localObject = this.context;
    Context localContext = this.context;
    int i = 0;
    paramString = ((Context)localObject).getSharedPreferences(paramString, 0);
    paramArrayList.clear();
    int j = paramString.getInt("Status_size", 0);
    while (i < j)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Status_");
      ((StringBuilder)localObject).append(i);
      paramArrayList.add(paramString.getString(((StringBuilder)localObject).toString(), null));
      i += 1;
    }
    return paramArrayList;
  }

  public boolean saveArray(ArrayList<String> paramArrayList, String paramString)
  {
    Object localObject = this.context;
    Context localContext = this.context;
    int i = 0;
    paramString = ((Context)localObject).getSharedPreferences(paramString, 0).edit();
    paramString.putInt("Status_size", paramArrayList.size());
    while (i < paramArrayList.size())
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Status_");
      ((StringBuilder)localObject).append(i);
      paramString.remove(((StringBuilder)localObject).toString());
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Status_");
      ((StringBuilder)localObject).append(i);
      paramString.putString(((StringBuilder)localObject).toString(), (String)paramArrayList.get(i));
      i += 1;
    }
    return paramString.commit();
  }

  public void saveBoolean(Boolean paramBoolean, String paramString1, String paramString2)
  {
    paramString1 = this.context.getSharedPreferences(paramString1, 0).edit();
    paramString1.putBoolean(paramString2, paramBoolean.booleanValue());
    paramString1.commit();
  }

  public void saveDeviceFWVersionByMac(String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = this.context.getSharedPreferences("fw_update", 0).edit();
    localEditor.putString(paramString2, paramString1);
    localEditor.commit();
  }

  public void saveFWMacAddress(String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = this.context.getSharedPreferences("fw_info", 0).edit();
    localEditor.putString(paramString2, paramString1);
    localEditor.commit();
  }

  public void saveFWProjectName(String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = this.context.getSharedPreferences("fw_info", 0).edit();
    localEditor.putString(paramString2, paramString1);
    localEditor.commit();
  }

  public void saveFWUpdateFlag(Boolean paramBoolean, String paramString)
  {
    SharedPreferences.Editor localEditor = this.context.getSharedPreferences("fw_info", 0).edit();
    localEditor.putBoolean(paramString, paramBoolean.booleanValue());
    localEditor.commit();
  }

  public void saveInteger(Integer paramInteger, String paramString1, String paramString2)
  {
    paramString1 = this.context.getSharedPreferences(paramString1, 0).edit();
    paramString1.putInt(paramString2, paramInteger.intValue());
    paramString1.commit();
  }

  public void saveString(String paramString1, String paramString2, String paramString3)
  {
    paramString2 = this.context.getSharedPreferences(paramString2, 0).edit();
    paramString2.putString(paramString3, paramString1);
    paramString2.commit();
  }
}
