package com.tencent.mmkv;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.IOException;

public final class ParcelableMMKV
  implements Parcelable
{
  public static final Parcelable.Creator<ParcelableMMKV> CREATOR = new Parcelable.Creator()
  {
    public ParcelableMMKV createFromParcel(Parcel paramAnonymousParcel)
    {
      String str = paramAnonymousParcel.readString();
      ParcelFileDescriptor localParcelFileDescriptor1 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(paramAnonymousParcel);
      ParcelFileDescriptor localParcelFileDescriptor2 = (ParcelFileDescriptor)ParcelFileDescriptor.CREATOR.createFromParcel(paramAnonymousParcel);
      paramAnonymousParcel = paramAnonymousParcel.readString();
      if ((localParcelFileDescriptor1 != null) && (localParcelFileDescriptor2 != null))
        return new ParcelableMMKV(str, localParcelFileDescriptor1.detachFd(), localParcelFileDescriptor2.detachFd(), paramAnonymousParcel, null);
      return null;
    }

    public ParcelableMMKV[] newArray(int paramAnonymousInt)
    {
      return new ParcelableMMKV[paramAnonymousInt];
    }
  };
  private int ashmemFD = -1;
  private int ashmemMetaFD = -1;
  private String cryptKey = null;
  private String mmapID;

  public ParcelableMMKV(MMKV paramMMKV)
  {
    this.mmapID = paramMMKV.mmapID();
    this.ashmemFD = paramMMKV.ashmemFD();
    this.ashmemMetaFD = paramMMKV.ashmemMetaFD();
    this.cryptKey = paramMMKV.cryptKey();
  }

  private ParcelableMMKV(String paramString1, int paramInt1, int paramInt2, String paramString2)
  {
    this.mmapID = paramString1;
    this.ashmemFD = paramInt1;
    this.ashmemMetaFD = paramInt2;
    this.cryptKey = paramString2;
  }

  public int describeContents()
  {
    return 1;
  }

  public MMKV toMMKV()
  {
    if ((this.ashmemFD >= 0) && (this.ashmemMetaFD >= 0))
      return MMKV.mmkvWithAshmemFD(this.mmapID, this.ashmemFD, this.ashmemMetaFD, this.cryptKey);
    return null;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    try
    {
      paramParcel.writeString(this.mmapID);
      ParcelFileDescriptor localParcelFileDescriptor1 = ParcelFileDescriptor.fromFd(this.ashmemFD);
      ParcelFileDescriptor localParcelFileDescriptor2 = ParcelFileDescriptor.fromFd(this.ashmemMetaFD);
      paramInt |= 1;
      localParcelFileDescriptor1.writeToParcel(paramParcel, paramInt);
      localParcelFileDescriptor2.writeToParcel(paramParcel, paramInt);
      if (this.cryptKey != null)
      {
        paramParcel.writeString(this.cryptKey);
        return;
      }
    }
    catch (IOException paramParcel)
    {
      paramParcel.printStackTrace();
    }
  }
}