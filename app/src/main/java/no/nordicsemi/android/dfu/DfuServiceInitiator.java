package no.nordicsemi.android.dfu;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.ParcelUuid;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import java.security.InvalidParameterException;
import java.util.UUID;

public class DfuServiceInitiator
{
  public static final int DEFAULT_PRN_VALUE = 12;
  public static final int SCOPE_APPLICATION = 3542;
  public static final int SCOPE_SYSTEM_COMPONENTS = 7578;
  private Parcelable[] buttonlessDfuWithBondSharingUuids;
  private Parcelable[] buttonlessDfuWithoutBondSharingUuids;
  private int currentMtu = 23;
  private final String deviceAddress;
  private String deviceName;
  private boolean disableNotification = false;
  private boolean disableResume = false;
  private boolean enableUnsafeExperimentalButtonlessDfu = false;
  private Parcelable[] experimentalButtonlessDfuUuids;
  private String filePath;
  private int fileResId;
  private int fileType = -1;
  private Uri fileUri;
  private boolean forceDfu = false;
  private String initFilePath;
  private int initFileResId;
  private Uri initFileUri;
  private boolean keepBond;
  private Parcelable[] legacyDfuUuids;
  private String mimeType;
  private int mtu = 517;
  private int numberOfPackets = 12;
  private Boolean packetReceiptNotificationsEnabled;
  private boolean restoreBond;
  private Parcelable[] secureDfuUuids;
  private boolean startAsForegroundService = true;

  public DfuServiceInitiator(@NonNull String paramString)
  {
    this.deviceAddress = paramString;
  }

  @RequiresApi(api=26)
  public static void createDfuNotificationChannel(@NonNull Context paramContext)
  {
    NotificationChannel localNotificationChannel = new NotificationChannel("dfu", paramContext.getString(R.string.dfu_channel_name), 2);
    localNotificationChannel.setDescription(paramContext.getString(R.string.dfu_channel_description));
    localNotificationChannel.setShowBadge(false);
    localNotificationChannel.setLockscreenVisibility(1);
    paramContext = (NotificationManager)paramContext.getSystemService("notification");
    if (paramContext != null)
      paramContext.createNotificationChannel(localNotificationChannel);
  }

  private DfuServiceInitiator init(@Nullable Uri paramUri, @Nullable String paramString, int paramInt)
  {
    if ("application/zip".equals(this.mimeType))
      throw new InvalidParameterException("Init file must be located inside the ZIP");
    this.initFileUri = paramUri;
    this.initFilePath = paramString;
    this.initFileResId = paramInt;
    return this;
  }

  private DfuServiceInitiator init(@Nullable Uri paramUri, @Nullable String paramString1, int paramInt1, int paramInt2, @NonNull String paramString2)
  {
    this.fileUri = paramUri;
    this.filePath = paramString1;
    this.fileResId = paramInt1;
    this.fileType = paramInt2;
    this.mimeType = paramString2;
    if ("application/zip".equals(paramString2))
    {
      this.initFileUri = null;
      this.initFilePath = null;
      this.initFileResId = 0;
    }
    return this;
  }

  public DfuServiceInitiator disableMtuRequest()
  {
    this.mtu = 0;
    return this;
  }

  public DfuServiceInitiator disableResume()
  {
    this.disableResume = true;
    return this;
  }

  @Deprecated
  public DfuServiceInitiator setBinOrHex(int paramInt1, int paramInt2)
  {
    if (paramInt1 == 0)
      throw new UnsupportedOperationException("You must specify the file type");
    return init(null, null, paramInt2, paramInt1, "application/octet-stream");
  }

  @Deprecated
  public DfuServiceInitiator setBinOrHex(int paramInt, @NonNull Uri paramUri)
  {
    if (paramInt == 0)
      throw new UnsupportedOperationException("You must specify the file type");
    return init(paramUri, null, 0, paramInt, "application/octet-stream");
  }

  @Deprecated
  public DfuServiceInitiator setBinOrHex(int paramInt, @Nullable Uri paramUri, @Nullable String paramString)
  {
    if (paramInt == 0)
      throw new UnsupportedOperationException("You must specify the file type");
    return init(paramUri, paramString, 0, paramInt, "application/octet-stream");
  }

  @Deprecated
  public DfuServiceInitiator setBinOrHex(int paramInt, @NonNull String paramString)
  {
    if (paramInt == 0)
      throw new UnsupportedOperationException("You must specify the file type");
    return init(null, paramString, 0, paramInt, "application/octet-stream");
  }

  public DfuServiceInitiator setCurrentMtu(int paramInt)
  {
    this.currentMtu = paramInt;
    return this;
  }

  public DfuServiceInitiator setCustomUuidsForButtonlessDfuWithBondSharing(@Nullable UUID paramUUID1, @Nullable UUID paramUUID2)
  {
    ParcelUuid localParcelUuid = null;
    if (paramUUID1 != null)
      paramUUID1 = new ParcelUuid(paramUUID1);
    else
      paramUUID1 = null;
    if (paramUUID2 != null)
      localParcelUuid = new ParcelUuid(paramUUID2);
    this.buttonlessDfuWithBondSharingUuids = new ParcelUuid[] { paramUUID1, localParcelUuid };
    return this;
  }

  public DfuServiceInitiator setCustomUuidsForButtonlessDfuWithoutBondSharing(@Nullable UUID paramUUID1, @Nullable UUID paramUUID2)
  {
    ParcelUuid localParcelUuid = null;
    if (paramUUID1 != null)
      paramUUID1 = new ParcelUuid(paramUUID1);
    else
      paramUUID1 = null;
    if (paramUUID2 != null)
      localParcelUuid = new ParcelUuid(paramUUID2);
    this.buttonlessDfuWithoutBondSharingUuids = new ParcelUuid[] { paramUUID1, localParcelUuid };
    return this;
  }

  public DfuServiceInitiator setCustomUuidsForExperimentalButtonlessDfu(@Nullable UUID paramUUID1, @Nullable UUID paramUUID2)
  {
    ParcelUuid localParcelUuid = null;
    if (paramUUID1 != null)
      paramUUID1 = new ParcelUuid(paramUUID1);
    else
      paramUUID1 = null;
    if (paramUUID2 != null)
      localParcelUuid = new ParcelUuid(paramUUID2);
    this.experimentalButtonlessDfuUuids = new ParcelUuid[] { paramUUID1, localParcelUuid };
    return this;
  }

  public DfuServiceInitiator setCustomUuidsForLegacyDfu(@Nullable UUID paramUUID1, @Nullable UUID paramUUID2, @Nullable UUID paramUUID3, @Nullable UUID paramUUID4)
  {
    ParcelUuid localParcelUuid = null;
    if (paramUUID1 != null)
      paramUUID1 = new ParcelUuid(paramUUID1);
    else
      paramUUID1 = null;
    if (paramUUID2 != null)
      paramUUID2 = new ParcelUuid(paramUUID2);
    else
      paramUUID2 = null;
    if (paramUUID3 != null)
      paramUUID3 = new ParcelUuid(paramUUID3);
    else
      paramUUID3 = null;
    if (paramUUID4 != null)
      localParcelUuid = new ParcelUuid(paramUUID4);
    this.legacyDfuUuids = new ParcelUuid[] { paramUUID1, paramUUID2, paramUUID3, localParcelUuid };
    return this;
  }

  public DfuServiceInitiator setCustomUuidsForSecureDfu(@Nullable UUID paramUUID1, @Nullable UUID paramUUID2, @Nullable UUID paramUUID3)
  {
    ParcelUuid localParcelUuid = null;
    if (paramUUID1 != null)
      paramUUID1 = new ParcelUuid(paramUUID1);
    else
      paramUUID1 = null;
    if (paramUUID2 != null)
      paramUUID2 = new ParcelUuid(paramUUID2);
    else
      paramUUID2 = null;
    if (paramUUID3 != null)
      localParcelUuid = new ParcelUuid(paramUUID3);
    this.secureDfuUuids = new ParcelUuid[] { paramUUID1, paramUUID2, localParcelUuid };
    return this;
  }

  public DfuServiceInitiator setDeviceName(@Nullable String paramString)
  {
    this.deviceName = paramString;
    return this;
  }

  public DfuServiceInitiator setDisableNotification(boolean paramBoolean)
  {
    this.disableNotification = paramBoolean;
    return this;
  }

  public DfuServiceInitiator setForceDfu(boolean paramBoolean)
  {
    this.forceDfu = paramBoolean;
    return this;
  }

  public DfuServiceInitiator setForeground(boolean paramBoolean)
  {
    this.startAsForegroundService = paramBoolean;
    return this;
  }

  @Deprecated
  public DfuServiceInitiator setInitFile(int paramInt)
  {
    return init(null, null, paramInt);
  }

  @Deprecated
  public DfuServiceInitiator setInitFile(@NonNull Uri paramUri)
  {
    return init(paramUri, null, 0);
  }

  @Deprecated
  public DfuServiceInitiator setInitFile(@Nullable Uri paramUri, @Nullable String paramString)
  {
    return init(paramUri, paramString, 0);
  }

  @Deprecated
  public DfuServiceInitiator setInitFile(@Nullable String paramString)
  {
    return init(null, paramString, 0);
  }

  public DfuServiceInitiator setKeepBond(boolean paramBoolean)
  {
    this.keepBond = paramBoolean;
    return this;
  }

  public DfuServiceInitiator setMtu(int paramInt)
  {
    this.mtu = paramInt;
    return this;
  }

  public DfuServiceInitiator setPacketsReceiptNotificationsEnabled(boolean paramBoolean)
  {
    this.packetReceiptNotificationsEnabled = Boolean.valueOf(paramBoolean);
    return this;
  }

  public DfuServiceInitiator setPacketsReceiptNotificationsValue(int paramInt)
  {
    if (paramInt <= 0)
      paramInt = 12;
    this.numberOfPackets = paramInt;
    return this;
  }

  public DfuServiceInitiator setRestoreBond(boolean paramBoolean)
  {
    this.restoreBond = paramBoolean;
    return this;
  }

  public DfuServiceInitiator setScope(int paramInt)
  {
    if (!"application/zip".equals(this.mimeType))
      throw new UnsupportedOperationException("Scope can be set only for a ZIP file");
    if (paramInt == 3542)
    {
      this.fileType = 4;
      return this;
    }
    if (paramInt == 7578)
    {
      this.fileType = 3;
      return this;
    }
    throw new UnsupportedOperationException("Unknown scope");
  }

  public DfuServiceInitiator setUnsafeExperimentalButtonlessServiceInSecureDfuEnabled(boolean paramBoolean)
  {
    this.enableUnsafeExperimentalButtonlessDfu = paramBoolean;
    return this;
  }

  public DfuServiceInitiator setZip(int paramInt)
  {
    return init(null, null, paramInt, 0, "application/zip");
  }

  public DfuServiceInitiator setZip(@NonNull Uri paramUri)
  {
    return init(paramUri, null, 0, 0, "application/zip");
  }

  public DfuServiceInitiator setZip(@Nullable Uri paramUri, @Nullable String paramString)
  {
    return init(paramUri, paramString, 0, 0, "application/zip");
  }

  public DfuServiceInitiator setZip(@NonNull String paramString)
  {
    return init(null, paramString, 0, 0, "application/zip");
  }

  public DfuServiceController start(@NonNull Context paramContext, @NonNull Class<? extends DfuBaseService> paramClass)
  {
    if (this.fileType == -1)
      throw new UnsupportedOperationException("You must specify the firmware file before starting the service");
    paramClass = new Intent(paramContext, paramClass);
    paramClass.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_DEVICE_ADDRESS", this.deviceAddress);
    paramClass.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_DEVICE_NAME", this.deviceName);
    paramClass.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_DISABLE_NOTIFICATION", this.disableNotification);
    paramClass.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_FOREGROUND_SERVICE", this.startAsForegroundService);
    paramClass.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_MIME_TYPE", this.mimeType);
    paramClass.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_FILE_TYPE", this.fileType);
    paramClass.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_FILE_URI", this.fileUri);
    paramClass.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_FILE_PATH", this.filePath);
    paramClass.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_FILE_RES_ID", this.fileResId);
    paramClass.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_INIT_FILE_URI", this.initFileUri);
    paramClass.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_INIT_FILE_PATH", this.initFilePath);
    paramClass.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_INIT_FILE_RES_ID", this.initFileResId);
    paramClass.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_KEEP_BOND", this.keepBond);
    paramClass.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_RESTORE_BOND", this.restoreBond);
    paramClass.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_FORCE_DFU", this.forceDfu);
    paramClass.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_DISABLE_RESUME", this.disableResume);
    if (this.mtu > 0)
      paramClass.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_MTU", this.mtu);
    paramClass.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_CURRENT_MTU", this.currentMtu);
    paramClass.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_UNSAFE_EXPERIMENTAL_BUTTONLESS_DFU", this.enableUnsafeExperimentalButtonlessDfu);
    if (this.packetReceiptNotificationsEnabled != null)
    {
      paramClass.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_PRN_ENABLED", this.packetReceiptNotificationsEnabled);
      paramClass.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_PRN_VALUE", this.numberOfPackets);
    }
    if (this.legacyDfuUuids != null)
      paramClass.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_CUSTOM_UUIDS_FOR_LEGACY_DFU", this.legacyDfuUuids);
    if (this.secureDfuUuids != null)
      paramClass.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_CUSTOM_UUIDS_FOR_SECURE_DFU", this.secureDfuUuids);
    if (this.experimentalButtonlessDfuUuids != null)
      paramClass.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_CUSTOM_UUIDS_FOR_EXPERIMENTAL_BUTTONLESS_DFU", this.experimentalButtonlessDfuUuids);
    if (this.buttonlessDfuWithoutBondSharingUuids != null)
      paramClass.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_CUSTOM_UUIDS_FOR_BUTTONLESS_DFU_WITHOUT_BOND_SHARING", this.buttonlessDfuWithoutBondSharingUuids);
    if (this.buttonlessDfuWithBondSharingUuids != null)
      paramClass.putExtra("no.nordicsemi.android.dfu.extra.EXTRA_CUSTOM_UUIDS_FOR_BUTTONLESS_DFU_WITH_BOND_SHARING", this.buttonlessDfuWithBondSharingUuids);
    if ((Build.VERSION.SDK_INT >= 26) && (this.startAsForegroundService))
      paramContext.startForegroundService(paramClass);
    else
      paramContext.startService(paramClass);
    return new DfuServiceController(paramContext);
  }
}