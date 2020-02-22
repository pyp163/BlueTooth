package com.qx.qgbox.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import com.qx.qgbox.entitys.InstalledApp;
import com.qx.qgbox.entitys.SunyesMaxGamePreset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AppInfoProvider
{
  static String[] filterPackageName = { "com.qx.qgbox", "com.redteamobile.oneplus.roaming", "com.autonavi.manu.widget", "com.chaozhuo.gameassistant", "com.android.egg", "com.tencent.mm", "com.tencent.mobileqq", "com.qiyi.video", "com.sw.joymap", "com.ss.android.ugc.live", "com.tencent.qqlive", "com.xunmeng.pinduoduo", "com.ss.android.ugc.aweme", "com.qx.joymap", "com.jingdong.app.mall", "com.smile.gifmaker", "com.ss.android.article.video", "com.taobao.litetao", "com.qx.bledfu", "com.huajiao", "air.tv.douyu.android", "com.duowan.kiwi", "com.duowan.live", "com.tencent.qgame", "dfu.dfu", "com.julun.lingmeng", "com.tencent.now", "com.kugou.fanxing", "com.suning.mobile.ebuy", "com.kaola", "com.huya.kiwi", "cn.oneplus", "com.kascend.chushou", "com.taobao.taobao", "com.netease.yanxuan", "com.eg.android.AlipayGphone", "me.gfuil.bmap", "com.oneplus", "com.achievo.vipshop", "com.taobao.etao", "com.duowan.mobile", "com.netease.cc", "com.meelive.ingkee", "com.sogou.map.android.maps", "net.oneplus", "com.jingyao.easybike", "com.autonavi.realtimebus.expand", "com.edcsc.wbus", "com.baidu.BaiduMap.auto", "com.sdu.didi.psnger", "cn.oppo", "cn.caocaokeji.user", "bdbd.wiex.ditu", "com.wlqq", "com.ygkj.chelaile.standard", "com.sh.paipai", "com.tencent.map", "com.didapinche.booking", "com.oppo", "com.autonavi.subway", "com.saicmobility.user", "com.xiguakeji.bddh", "com.baidu.BaiduMap", "com.jsyc.driver", "com.ctrip.xzhe.map", "net.oppo", "com.mygolbs.mybus", "com.autonavi.minimap", "com.autonavi.gxdtaojin", "com.ganji.android.haoche_c", "com.csii", "com.sxnxs.mbank", "cn.vivo", "com.nxy.mobilebank.hebei", "com.yitong.xian.mbank", "com.samoyed.credit", "net.mybank.activity", "com.hundsun.mobile.hsbrowsertest", "com.vivo", "com.android.bankabc", "com.tieling.mobilebank", "com.njzx.home", "com.cgbchina.xpt", "com.chq.main", "cn.com.jzbank.vbank", "com.sfz.sfzjqqb", "net.vivo", "com.i154524261.vdf", "com.picbank.picbank", "com.icbc.im", "cmb.pb", "com.icbc.mercantilebank", "cn.handanbank.apk", "com.iqianjin.client", "cn.xiaomi", "cn.com.hrcrb.mobilebank.per", "com.chinatelecom.bestpayclient", "com.hnzycfc.zyxj", "com.anshan.mobilebank", "com.bankcomm.Bankcomm", "com.xiaomi", "com.rytong.bankbj", "com.rybring.weidai", "com.csii_qzbank", "com.wefax.nbank", "com.chinamworld.bocmbci", "com.csii.zybk.ui", "net.xiaomi", "com.pingan.paces.ccms", "com.mbank.dongguanbank", "com.iss.taianbank", "com.syqy.wecash", "cn.com.hfnsbank", "com.iss.dongyingbank", "cn.huawei", "com.bank", "com.icbc", "com.csii.hkb", "com.lingyue.YqdAndroid", "com.gwtsz.gts2.cf2", "com.czccb.czbank", "com.pafinancialtech.rizhaobank", "com.huawei", "cn.com.gdbank.direct", "com.jfjinye.jfjinye", "com.chinamworld.klb", "com.benxi.mobilebank", "com.renrendai.haohuan", "com.mymoney.sms", "net.huawei", "com.yitong.fjnx.mbank.android", "com.yitong.mbank", "com.wooribankchina.mbs", "com.youcash.ZYWallet", "com.ecitic.bank.mobile", "com.mtzx.bank", "com.nxy.mobilebank.hb", "com.nxy.mobilebank.jl", "com.spdbccc.app", "com.chinamworld.main", "com.nxy.hn", "com.xjbank.mbk", "com.hanweb.android.zhjh", "cn.com.hbbank.ui", "com.wukonglicai.app", "com.mtb", "com.fdbank.newmobile", "com.fuxinbank.mobilebank.personal", "com.ynet.mbank.crcb", "com.baoding.mobilebank", "com.citiccard.mobilebank", "com.sinotech.ln_student", "com.ccb.companybank", "com.gdnybank.m", "com.gf.client", "com.guilinbank.mobilebank", "cn.com.hzb.mobilebank.per", "cn.com.gzbank.ui", "com.cib.cibmb", "cn.com.lzb.mobilebank.per", "com.yitong.cd.mbank.android", "com.rytong.egfbank", "com.shengjingbank.mobile.cust", "com.cmbc.cc.mbank", "com.iss.rizhaobank", "com.zzbank.mobile.bank", "com.cmbchina.ccd.pluto.cmbActivity", "com.cib.xyk", "com.xinjianghuihe.mobilebank", "com.cx.photo", "com.unionpay", "com.meizu", "cn.meizu", "net.meizu", "cn.futu.trader" };
  private ArrayList<SunyesMaxGamePreset> mSunyesMaxGamePresetList = new ArrayList();
  private PackageManager packageManager;

  public AppInfoProvider(Context paramContext, ArrayList<SunyesMaxGamePreset> paramArrayList)
  {
    this.packageManager = paramContext.getPackageManager();
    this.mSunyesMaxGamePresetList = paramArrayList;
  }

  public boolean filterApp(ApplicationInfo paramApplicationInfo)
  {
    if ((paramApplicationInfo.flags & 0x80) != 0)
      return true;
    return (paramApplicationInfo.flags & 0x1) == 0;
  }

  public ArrayList<InstalledApp> getAllApps()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.packageManager.getInstalledPackages(8192).iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (PackageInfo)localIterator.next();
      InstalledApp localInstalledApp = new InstalledApp();
      String str1 = ((PackageInfo)localObject).packageName;
      localObject = ((PackageInfo)localObject).applicationInfo;
      Drawable localDrawable = ((ApplicationInfo)localObject).loadIcon(this.packageManager);
      String str2 = ((ApplicationInfo)localObject).loadLabel(this.packageManager).toString();
      localInstalledApp.setPackageName(str1);
      localInstalledApp.setAppName(str2);
      localInstalledApp.setIcon(localDrawable);
      if ((filterApp((ApplicationInfo)localObject)) && (!isAdded(str1)) && (!packageFilter(str1)))
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("---------packageName = ");
        ((StringBuilder)localObject).append(str1);
        MyLog.i("my_tag", ((StringBuilder)localObject).toString());
        localArrayList.add(localInstalledApp);
      }
    }
    return localArrayList;
  }

  public boolean isAdded(String paramString)
  {
    int i = 0;
    while (i < this.mSunyesMaxGamePresetList.size())
    {
      if ((CommonUtils.isStringValid(paramString)) && (CommonUtils.isStringValid(((SunyesMaxGamePreset)this.mSunyesMaxGamePresetList.get(i)).getAppPackageName())) && (paramString.equalsIgnoreCase(((SunyesMaxGamePreset)this.mSunyesMaxGamePresetList.get(i)).getAppPackageName())))
        return true;
      i += 1;
    }
    return false;
  }

  public boolean packageFilter(String paramString)
  {
    int i = 0;
    while (i < filterPackageName.length)
    {
      if ((CommonUtils.isStringValid(paramString)) && (paramString.contains(filterPackageName[i])))
        return true;
      i += 1;
    }
    return false;
  }
}
