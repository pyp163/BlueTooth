package no.nordicsemi.android.dfu;

import android.content.Intent;
import android.os.ParcelUuid;
import android.os.Parcelable;
import java.util.UUID;

class UuidHelper
{
  static void assignCustomUuids(Intent paramIntent)
  {
    Parcelable[] arrayOfParcelable = paramIntent.getParcelableArrayExtra("no.nordicsemi.android.dfu.extra.EXTRA_CUSTOM_UUIDS_FOR_LEGACY_DFU");
    if ((arrayOfParcelable != null) && (arrayOfParcelable.length == 4))
    {
      if (arrayOfParcelable[0] != null)
        localObject = ((ParcelUuid)arrayOfParcelable[0]).getUuid();
      else
        localObject = LegacyDfuImpl.DEFAULT_DFU_SERVICE_UUID;
      LegacyDfuImpl.DFU_SERVICE_UUID = (UUID)localObject;
      if (arrayOfParcelable[1] != null)
        localObject = ((ParcelUuid)arrayOfParcelable[1]).getUuid();
      else
        localObject = LegacyDfuImpl.DEFAULT_DFU_CONTROL_POINT_UUID;
      LegacyDfuImpl.DFU_CONTROL_POINT_UUID = (UUID)localObject;
      if (arrayOfParcelable[2] != null)
        localObject = ((ParcelUuid)arrayOfParcelable[2]).getUuid();
      else
        localObject = LegacyDfuImpl.DEFAULT_DFU_PACKET_UUID;
      LegacyDfuImpl.DFU_PACKET_UUID = (UUID)localObject;
      if (arrayOfParcelable[3] != null)
        localObject = ((ParcelUuid)arrayOfParcelable[3]).getUuid();
      else
        localObject = LegacyDfuImpl.DEFAULT_DFU_VERSION_UUID;
      LegacyDfuImpl.DFU_VERSION_UUID = (UUID)localObject;
      LegacyButtonlessDfuImpl.DFU_SERVICE_UUID = LegacyDfuImpl.DFU_SERVICE_UUID;
      LegacyButtonlessDfuImpl.DFU_CONTROL_POINT_UUID = LegacyDfuImpl.DFU_CONTROL_POINT_UUID;
      LegacyButtonlessDfuImpl.DFU_VERSION_UUID = LegacyDfuImpl.DFU_VERSION_UUID;
    }
    else
    {
      LegacyDfuImpl.DFU_SERVICE_UUID = LegacyDfuImpl.DEFAULT_DFU_SERVICE_UUID;
      LegacyDfuImpl.DFU_CONTROL_POINT_UUID = LegacyDfuImpl.DEFAULT_DFU_CONTROL_POINT_UUID;
      LegacyDfuImpl.DFU_PACKET_UUID = LegacyDfuImpl.DEFAULT_DFU_PACKET_UUID;
      LegacyDfuImpl.DFU_VERSION_UUID = LegacyDfuImpl.DEFAULT_DFU_VERSION_UUID;
      LegacyButtonlessDfuImpl.DFU_SERVICE_UUID = LegacyDfuImpl.DEFAULT_DFU_SERVICE_UUID;
      LegacyButtonlessDfuImpl.DFU_CONTROL_POINT_UUID = LegacyDfuImpl.DEFAULT_DFU_CONTROL_POINT_UUID;
      LegacyButtonlessDfuImpl.DFU_VERSION_UUID = LegacyDfuImpl.DEFAULT_DFU_VERSION_UUID;
    }
    arrayOfParcelable = paramIntent.getParcelableArrayExtra("no.nordicsemi.android.dfu.extra.EXTRA_CUSTOM_UUIDS_FOR_SECURE_DFU");
    if ((arrayOfParcelable != null) && (arrayOfParcelable.length == 3))
    {
      if (arrayOfParcelable[0] != null)
        localObject = ((ParcelUuid)arrayOfParcelable[0]).getUuid();
      else
        localObject = SecureDfuImpl.DEFAULT_DFU_SERVICE_UUID;
      SecureDfuImpl.DFU_SERVICE_UUID = (UUID)localObject;
      if (arrayOfParcelable[1] != null)
        localObject = ((ParcelUuid)arrayOfParcelable[1]).getUuid();
      else
        localObject = SecureDfuImpl.DEFAULT_DFU_CONTROL_POINT_UUID;
      SecureDfuImpl.DFU_CONTROL_POINT_UUID = (UUID)localObject;
      if (arrayOfParcelable[2] != null)
        localObject = ((ParcelUuid)arrayOfParcelable[2]).getUuid();
      else
        localObject = SecureDfuImpl.DEFAULT_DFU_PACKET_UUID;
      SecureDfuImpl.DFU_PACKET_UUID = (UUID)localObject;
    }
    else
    {
      SecureDfuImpl.DFU_SERVICE_UUID = SecureDfuImpl.DEFAULT_DFU_SERVICE_UUID;
      SecureDfuImpl.DFU_CONTROL_POINT_UUID = SecureDfuImpl.DEFAULT_DFU_CONTROL_POINT_UUID;
      SecureDfuImpl.DFU_PACKET_UUID = SecureDfuImpl.DEFAULT_DFU_PACKET_UUID;
    }
    arrayOfParcelable = paramIntent.getParcelableArrayExtra("no.nordicsemi.android.dfu.extra.EXTRA_CUSTOM_UUIDS_FOR_EXPERIMENTAL_BUTTONLESS_DFU");
    if ((arrayOfParcelable != null) && (arrayOfParcelable.length == 2))
    {
      if (arrayOfParcelable[0] != null)
        localObject = ((ParcelUuid)arrayOfParcelable[0]).getUuid();
      else
        localObject = ExperimentalButtonlessDfuImpl.DEFAULT_EXPERIMENTAL_BUTTONLESS_DFU_SERVICE_UUID;
      ExperimentalButtonlessDfuImpl.EXPERIMENTAL_BUTTONLESS_DFU_SERVICE_UUID = (UUID)localObject;
      if (arrayOfParcelable[1] != null)
        localObject = ((ParcelUuid)arrayOfParcelable[1]).getUuid();
      else
        localObject = ExperimentalButtonlessDfuImpl.DEFAULT_EXPERIMENTAL_BUTTONLESS_DFU_UUID;
      ExperimentalButtonlessDfuImpl.EXPERIMENTAL_BUTTONLESS_DFU_UUID = (UUID)localObject;
    }
    else
    {
      ExperimentalButtonlessDfuImpl.EXPERIMENTAL_BUTTONLESS_DFU_SERVICE_UUID = ExperimentalButtonlessDfuImpl.DEFAULT_EXPERIMENTAL_BUTTONLESS_DFU_SERVICE_UUID;
      ExperimentalButtonlessDfuImpl.EXPERIMENTAL_BUTTONLESS_DFU_UUID = ExperimentalButtonlessDfuImpl.DEFAULT_EXPERIMENTAL_BUTTONLESS_DFU_UUID;
    }
    arrayOfParcelable = paramIntent.getParcelableArrayExtra("no.nordicsemi.android.dfu.extra.EXTRA_CUSTOM_UUIDS_FOR_BUTTONLESS_DFU_WITHOUT_BOND_SHARING");
    if ((arrayOfParcelable != null) && (arrayOfParcelable.length == 2))
    {
      if (arrayOfParcelable[0] != null)
        localObject = ((ParcelUuid)arrayOfParcelable[0]).getUuid();
      else
        localObject = ButtonlessDfuWithoutBondSharingImpl.DEFAULT_BUTTONLESS_DFU_SERVICE_UUID;
      ButtonlessDfuWithoutBondSharingImpl.BUTTONLESS_DFU_SERVICE_UUID = (UUID)localObject;
      if (arrayOfParcelable[1] != null)
        localObject = ((ParcelUuid)arrayOfParcelable[1]).getUuid();
      else
        localObject = ButtonlessDfuWithoutBondSharingImpl.DEFAULT_BUTTONLESS_DFU_UUID;
      ButtonlessDfuWithoutBondSharingImpl.BUTTONLESS_DFU_UUID = (UUID)localObject;
    }
    else
    {
      ButtonlessDfuWithoutBondSharingImpl.BUTTONLESS_DFU_SERVICE_UUID = ButtonlessDfuWithoutBondSharingImpl.DEFAULT_BUTTONLESS_DFU_SERVICE_UUID;
      ButtonlessDfuWithoutBondSharingImpl.BUTTONLESS_DFU_UUID = ButtonlessDfuWithoutBondSharingImpl.DEFAULT_BUTTONLESS_DFU_UUID;
    }
    Object localObject = paramIntent.getParcelableArrayExtra("no.nordicsemi.android.dfu.extra.EXTRA_CUSTOM_UUIDS_FOR_BUTTONLESS_DFU_WITH_BOND_SHARING");
    if ((localObject != null) && (localObject.length == 2))
    {
      if (localObject[0] != null)
        paramIntent = ((ParcelUuid)localObject[0]).getUuid();
      else
        paramIntent = ButtonlessDfuWithBondSharingImpl.DEFAULT_BUTTONLESS_DFU_SERVICE_UUID;
      ButtonlessDfuWithBondSharingImpl.BUTTONLESS_DFU_SERVICE_UUID = paramIntent;
      if (localObject[1] != null)
        paramIntent = ((ParcelUuid)localObject[1]).getUuid();
      else
        paramIntent = ButtonlessDfuWithBondSharingImpl.DEFAULT_BUTTONLESS_DFU_UUID;
      ButtonlessDfuWithBondSharingImpl.BUTTONLESS_DFU_UUID = paramIntent;
      return;
    }
    ButtonlessDfuWithBondSharingImpl.BUTTONLESS_DFU_SERVICE_UUID = ButtonlessDfuWithBondSharingImpl.DEFAULT_BUTTONLESS_DFU_SERVICE_UUID;
    ButtonlessDfuWithBondSharingImpl.BUTTONLESS_DFU_UUID = ButtonlessDfuWithBondSharingImpl.DEFAULT_BUTTONLESS_DFU_UUID;
  }
}