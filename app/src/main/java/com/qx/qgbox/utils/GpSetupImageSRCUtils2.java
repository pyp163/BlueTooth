package com.qx.qgbox.utils;

import android.content.Context;
import android.widget.ImageView;
import com.qx.qgbox.service.bluetoothdevmanager;

public class GpSetupImageSRCUtils2
{
  private static ImageView[] imageView = new ImageView[length];
  private static ImageView[] imageViewLB = new ImageView[length];
  private static ImageView[] imageViewLT = new ImageView[length];
  private static ImageView[] imageViewRB = new ImageView[length];
  private static ImageView[] imageViewRT = new ImageView[length];
  static int length = 966;
  private Context mcontext;

  public GpSetupImageSRCUtils2(Context paramContext)
  {
    this.mcontext = paramContext;
    init();
  }

  void init()
  {
    int i = 0;
    while (i < length)
    {
      if (imageView[i] == null)
        imageView[i] = new ImageView(this.mcontext);
      if (imageViewRB[i] == null)
        imageViewRB[i] = new ImageView(this.mcontext);
      if (imageViewRT[i] == null)
        imageViewRT[i] = new ImageView(this.mcontext);
      if (imageViewLB[i] == null)
        imageViewLB[i] = new ImageView(this.mcontext);
      if (imageViewLT[i] == null)
        imageViewLT[i] = new ImageView(this.mcontext);
      setuppicSmall(i);
      i += 1;
    }
  }

  public ImageView[] initGPIndicatorImageView(Context paramContext, int paramInt)
  {
    if (paramInt == 0)
      return imageView;
    if (paramInt == 1)
      return imageViewRB;
    if (paramInt == 2)
      return imageViewRT;
    if (paramInt == 3)
      return imageViewLB;
    if (paramInt == 4)
      return imageViewLT;
    return imageView;
  }

  void setuppicSmall(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return;
    case 961:
    case 962:
    case 963:
    case 964:
    case 965:
      imageView[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.newkeysmall);
      return;
    case 960:
      imageView[paramInt].setImageResource(R.drawable.center3small);
      imageViewRB[paramInt].setImageResource(R.drawable.center3small);
      imageViewRT[paramInt].setImageResource(R.drawable.center3small);
      imageViewLB[paramInt].setImageResource(R.drawable.center3small);
      imageViewLT[paramInt].setImageResource(R.drawable.center3small);
      return;
    case 959:
      imageView[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr2small);
      return;
    case 958:
      imageView[paramInt].setImageResource(R.drawable.center2small);
      imageViewRB[paramInt].setImageResource(R.drawable.center2small);
      imageViewRT[paramInt].setImageResource(R.drawable.center2small);
      imageViewLB[paramInt].setImageResource(R.drawable.center2small);
      imageViewLT[paramInt].setImageResource(R.drawable.center2small);
      return;
    case 957:
      imageView[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr1small);
      return;
    case 956:
      imageView[paramInt].setImageResource(R.drawable.center1small);
      imageViewRB[paramInt].setImageResource(R.drawable.center1small);
      imageViewRT[paramInt].setImageResource(R.drawable.center1small);
      imageViewLB[paramInt].setImageResource(R.drawable.center1small);
      imageViewLT[paramInt].setImageResource(R.drawable.center1small);
      return;
    case 955:
      imageView[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4small);
      return;
    case 954:
      imageView[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3small);
      return;
    case 953:
      imageView[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2small);
      return;
    case 952:
      imageView[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1small);
      return;
    case 951:
      imageView[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbprsmall);
      return;
    case 950:
      imageView[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbplsmall);
      return;
    case 949:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprtsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2small);
      return;
    case 948:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpltsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2small);
      return;
    case 947:
      imageView[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3small);
      return;
    case 946:
      imageView[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3small);
      return;
    case 945:
      imageView[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselectsmall);
      return;
    case 944:
      imageView[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstartsmall);
      return;
    case 943:
      imageView[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gprightsmall);
      return;
    case 942:
      imageView[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleftsmall);
      return;
    case 941:
      imageView[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdownsmall);
      return;
    case 940:
      imageView[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpupsmall);
      return;
    case 939:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprbsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1small);
      return;
    case 938:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gplbsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1small);
      return;
    case 937:
      imageView[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpysmall);
      return;
    case 936:
      imageView[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxsmall);
      return;
    case 935:
      imageView[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbsmall);
      return;
    case 934:
      imageView[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpasmall);
      return;
    case 929:
    case 930:
    case 931:
    case 932:
    case 933:
      imageView[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.newkeysmall);
      return;
    case 928:
      imageView[paramInt].setImageResource(R.drawable.center3small);
      imageViewRB[paramInt].setImageResource(R.drawable.center3small);
      imageViewRT[paramInt].setImageResource(R.drawable.center3small);
      imageViewLB[paramInt].setImageResource(R.drawable.center3small);
      imageViewLT[paramInt].setImageResource(R.drawable.center3small);
      return;
    case 927:
      imageView[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr2small);
      return;
    case 926:
      imageView[paramInt].setImageResource(R.drawable.center2small);
      imageViewRB[paramInt].setImageResource(R.drawable.center2small);
      imageViewRT[paramInt].setImageResource(R.drawable.center2small);
      imageViewLB[paramInt].setImageResource(R.drawable.center2small);
      imageViewLT[paramInt].setImageResource(R.drawable.center2small);
      return;
    case 925:
      imageView[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr1small);
      return;
    case 924:
      imageView[paramInt].setImageResource(R.drawable.center1small);
      imageViewRB[paramInt].setImageResource(R.drawable.center1small);
      imageViewRT[paramInt].setImageResource(R.drawable.center1small);
      imageViewLB[paramInt].setImageResource(R.drawable.center1small);
      imageViewLT[paramInt].setImageResource(R.drawable.center1small);
      return;
    case 923:
      imageView[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4small);
      return;
    case 922:
      imageView[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3small);
      return;
    case 921:
      imageView[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2small);
      return;
    case 920:
      imageView[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1small);
      return;
    case 919:
      imageView[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbprsmall);
      return;
    case 918:
      imageView[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbplsmall);
      return;
    case 917:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprtsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2small);
      return;
    case 916:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpltsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2small);
      return;
    case 915:
      imageView[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3small);
      return;
    case 914:
      imageView[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3small);
      return;
    case 913:
      imageView[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselectsmall);
      return;
    case 912:
      imageView[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstartsmall);
      return;
    case 911:
      imageView[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gprightsmall);
      return;
    case 910:
      imageView[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleftsmall);
      return;
    case 909:
      imageView[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdownsmall);
      return;
    case 908:
      imageView[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpupsmall);
      return;
    case 907:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprbsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1small);
      return;
    case 906:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gplbsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1small);
      return;
    case 905:
      imageView[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpysmall);
      return;
    case 904:
      imageView[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxsmall);
      return;
    case 903:
      imageView[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbsmall);
      return;
    case 902:
      imageView[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpasmall);
      return;
    case 897:
    case 898:
    case 899:
    case 900:
    case 901:
      imageView[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.newkeysmall);
      return;
    case 896:
      imageView[paramInt].setImageResource(R.drawable.center3small);
      imageViewRB[paramInt].setImageResource(R.drawable.center3small);
      imageViewRT[paramInt].setImageResource(R.drawable.center3small);
      imageViewLB[paramInt].setImageResource(R.drawable.center3small);
      imageViewLT[paramInt].setImageResource(R.drawable.center3small);
      return;
    case 895:
      imageView[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr2small);
      return;
    case 894:
      imageView[paramInt].setImageResource(R.drawable.center2small);
      imageViewRB[paramInt].setImageResource(R.drawable.center2small);
      imageViewRT[paramInt].setImageResource(R.drawable.center2small);
      imageViewLB[paramInt].setImageResource(R.drawable.center2small);
      imageViewLT[paramInt].setImageResource(R.drawable.center2small);
      return;
    case 893:
      imageView[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr1small);
      return;
    case 892:
      imageView[paramInt].setImageResource(R.drawable.center1small);
      imageViewRB[paramInt].setImageResource(R.drawable.center1small);
      imageViewRT[paramInt].setImageResource(R.drawable.center1small);
      imageViewLB[paramInt].setImageResource(R.drawable.center1small);
      imageViewLT[paramInt].setImageResource(R.drawable.center1small);
      return;
    case 891:
      imageView[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4small);
      return;
    case 890:
      imageView[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3small);
      return;
    case 889:
      imageView[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2small);
      return;
    case 888:
      imageView[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1small);
      return;
    case 887:
      imageView[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbprsmall);
      return;
    case 886:
      imageView[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbplsmall);
      return;
    case 885:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprtsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2small);
      return;
    case 884:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpltsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2small);
      return;
    case 883:
      imageView[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3small);
      return;
    case 882:
      imageView[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3small);
      return;
    case 881:
      imageView[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselectsmall);
      return;
    case 880:
      imageView[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstartsmall);
      return;
    case 879:
      imageView[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gprightsmall);
      return;
    case 878:
      imageView[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleftsmall);
      return;
    case 877:
      imageView[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdownsmall);
      return;
    case 876:
      imageView[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpupsmall);
      return;
    case 875:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprbsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1small);
      return;
    case 874:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gplbsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1small);
      return;
    case 873:
      imageView[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpysmall);
      return;
    case 872:
      imageView[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxsmall);
      return;
    case 871:
      imageView[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbsmall);
      return;
    case 870:
      imageView[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpasmall);
      return;
    case 865:
    case 866:
    case 867:
    case 868:
    case 869:
      imageView[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.newkeysmall);
      return;
    case 864:
      imageView[paramInt].setImageResource(R.drawable.center3small);
      imageViewRB[paramInt].setImageResource(R.drawable.center3small);
      imageViewRT[paramInt].setImageResource(R.drawable.center3small);
      imageViewLB[paramInt].setImageResource(R.drawable.center3small);
      imageViewLT[paramInt].setImageResource(R.drawable.center3small);
      return;
    case 863:
      imageView[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr2small);
      return;
    case 862:
      imageView[paramInt].setImageResource(R.drawable.center2small);
      imageViewRB[paramInt].setImageResource(R.drawable.center2small);
      imageViewRT[paramInt].setImageResource(R.drawable.center2small);
      imageViewLB[paramInt].setImageResource(R.drawable.center2small);
      imageViewLT[paramInt].setImageResource(R.drawable.center2small);
      return;
    case 861:
      imageView[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr1small);
      return;
    case 860:
      imageView[paramInt].setImageResource(R.drawable.center1small);
      imageViewRB[paramInt].setImageResource(R.drawable.center1small);
      imageViewRT[paramInt].setImageResource(R.drawable.center1small);
      imageViewLB[paramInt].setImageResource(R.drawable.center1small);
      imageViewLT[paramInt].setImageResource(R.drawable.center1small);
      return;
    case 859:
      imageView[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4small);
      return;
    case 858:
      imageView[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3small);
      return;
    case 857:
      imageView[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2small);
      return;
    case 856:
      imageView[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1small);
      return;
    case 855:
      imageView[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbprsmall);
      return;
    case 854:
      imageView[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbplsmall);
      return;
    case 853:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprtsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2small);
      return;
    case 852:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpltsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2small);
      return;
    case 851:
      imageView[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3small);
      return;
    case 850:
      imageView[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3small);
      return;
    case 849:
      imageView[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselectsmall);
      return;
    case 848:
      imageView[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstartsmall);
      return;
    case 847:
      imageView[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gprightsmall);
      return;
    case 846:
      imageView[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleftsmall);
      return;
    case 845:
      imageView[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdownsmall);
      return;
    case 844:
      imageView[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpupsmall);
      return;
    case 843:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprbsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1small);
      return;
    case 842:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gplbsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1small);
      return;
    case 841:
      imageView[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpysmall);
      return;
    case 840:
      imageView[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxsmall);
      return;
    case 839:
      imageView[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbsmall);
      return;
    case 838:
      imageView[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpasmall);
      return;
    case 833:
    case 834:
    case 835:
    case 836:
    case 837:
      imageView[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.newkeysmall);
      return;
    case 832:
      imageView[paramInt].setImageResource(R.drawable.center3small);
      imageViewRB[paramInt].setImageResource(R.drawable.center3small);
      imageViewRT[paramInt].setImageResource(R.drawable.center3small);
      imageViewLB[paramInt].setImageResource(R.drawable.center3small);
      imageViewLT[paramInt].setImageResource(R.drawable.center3small);
      return;
    case 831:
      imageView[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr2small);
      return;
    case 830:
      imageView[paramInt].setImageResource(R.drawable.center2small);
      imageViewRB[paramInt].setImageResource(R.drawable.center2small);
      imageViewRT[paramInt].setImageResource(R.drawable.center2small);
      imageViewLB[paramInt].setImageResource(R.drawable.center2small);
      imageViewLT[paramInt].setImageResource(R.drawable.center2small);
      return;
    case 829:
      imageView[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr1small);
      return;
    case 828:
      imageView[paramInt].setImageResource(R.drawable.center1small);
      imageViewRB[paramInt].setImageResource(R.drawable.center1small);
      imageViewRT[paramInt].setImageResource(R.drawable.center1small);
      imageViewLB[paramInt].setImageResource(R.drawable.center1small);
      imageViewLT[paramInt].setImageResource(R.drawable.center1small);
      return;
    case 827:
      imageView[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4small);
      return;
    case 826:
      imageView[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3small);
      return;
    case 825:
      imageView[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2small);
      return;
    case 824:
      imageView[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1small);
      return;
    case 823:
      imageView[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbprsmall);
      return;
    case 822:
      imageView[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbplsmall);
      return;
    case 821:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprtsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2small);
      return;
    case 820:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpltsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2small);
      return;
    case 819:
      imageView[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3small);
      return;
    case 818:
      imageView[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3small);
      return;
    case 817:
      imageView[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselectsmall);
      return;
    case 816:
      imageView[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstartsmall);
      return;
    case 815:
      imageView[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gprightsmall);
      return;
    case 814:
      imageView[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleftsmall);
      return;
    case 813:
      imageView[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdownsmall);
      return;
    case 812:
      imageView[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpupsmall);
      return;
    case 811:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprbsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1small);
      return;
    case 810:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gplbsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1small);
      return;
    case 809:
      imageView[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpysmall);
      return;
    case 808:
      imageView[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxsmall);
      return;
    case 807:
      imageView[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbsmall);
      return;
    case 806:
      imageView[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpasmall);
      return;
    case 801:
    case 802:
    case 803:
    case 804:
    case 805:
      imageView[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.newkeysmall);
      return;
    case 800:
      imageView[paramInt].setImageResource(R.drawable.center3small);
      imageViewRB[paramInt].setImageResource(R.drawable.center3small);
      imageViewRT[paramInt].setImageResource(R.drawable.center3small);
      imageViewLB[paramInt].setImageResource(R.drawable.center3small);
      imageViewLT[paramInt].setImageResource(R.drawable.center3small);
      return;
    case 799:
      imageView[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr2small);
      return;
    case 798:
      imageView[paramInt].setImageResource(R.drawable.center2small);
      imageViewRB[paramInt].setImageResource(R.drawable.center2small);
      imageViewRT[paramInt].setImageResource(R.drawable.center2small);
      imageViewLB[paramInt].setImageResource(R.drawable.center2small);
      imageViewLT[paramInt].setImageResource(R.drawable.center2small);
      return;
    case 797:
      imageView[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr1small);
      return;
    case 796:
      imageView[paramInt].setImageResource(R.drawable.center1small);
      imageViewRB[paramInt].setImageResource(R.drawable.center1small);
      imageViewRT[paramInt].setImageResource(R.drawable.center1small);
      imageViewLB[paramInt].setImageResource(R.drawable.center1small);
      imageViewLT[paramInt].setImageResource(R.drawable.center1small);
      return;
    case 795:
      imageView[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4small);
      return;
    case 794:
      imageView[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3small);
      return;
    case 793:
      imageView[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2small);
      return;
    case 792:
      imageView[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1small);
      return;
    case 791:
      imageView[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbprsmall);
      return;
    case 790:
      imageView[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbplsmall);
      return;
    case 789:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprtsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2small);
      return;
    case 788:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpltsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2small);
      return;
    case 787:
      imageView[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3small);
      return;
    case 786:
      imageView[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3small);
      return;
    case 785:
      imageView[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselectsmall);
      return;
    case 784:
      imageView[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstartsmall);
      return;
    case 783:
      imageView[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gprightsmall);
      return;
    case 782:
      imageView[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleftsmall);
      return;
    case 781:
      imageView[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdownsmall);
      return;
    case 780:
      imageView[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpupsmall);
      return;
    case 779:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprbsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1small);
      return;
    case 778:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gplbsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1small);
      return;
    case 777:
      imageView[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpysmall);
      return;
    case 776:
      imageView[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxsmall);
      return;
    case 775:
      imageView[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbsmall);
      return;
    case 774:
      imageView[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpasmall);
      return;
    case 769:
    case 770:
    case 771:
    case 772:
    case 773:
      imageView[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.newkeysmall);
      return;
    case 768:
      imageView[paramInt].setImageResource(R.drawable.center3small);
      imageViewRB[paramInt].setImageResource(R.drawable.center3small);
      imageViewRT[paramInt].setImageResource(R.drawable.center3small);
      imageViewLB[paramInt].setImageResource(R.drawable.center3small);
      imageViewLT[paramInt].setImageResource(R.drawable.center3small);
      return;
    case 767:
      imageView[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr2small);
      return;
    case 766:
      imageView[paramInt].setImageResource(R.drawable.center2small);
      imageViewRB[paramInt].setImageResource(R.drawable.center2small);
      imageViewRT[paramInt].setImageResource(R.drawable.center2small);
      imageViewLB[paramInt].setImageResource(R.drawable.center2small);
      imageViewLT[paramInt].setImageResource(R.drawable.center2small);
      return;
    case 765:
      imageView[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr1small);
      return;
    case 764:
      imageView[paramInt].setImageResource(R.drawable.center1small);
      imageViewRB[paramInt].setImageResource(R.drawable.center1small);
      imageViewRT[paramInt].setImageResource(R.drawable.center1small);
      imageViewLB[paramInt].setImageResource(R.drawable.center1small);
      imageViewLT[paramInt].setImageResource(R.drawable.center1small);
      return;
    case 763:
      imageView[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4small);
      return;
    case 762:
      imageView[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3small);
      return;
    case 761:
      imageView[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2small);
      return;
    case 760:
      imageView[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1small);
      return;
    case 759:
      imageView[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbprsmall);
      return;
    case 758:
      imageView[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbplsmall);
      return;
    case 757:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprtsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2small);
      return;
    case 756:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpltsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2small);
      return;
    case 755:
      imageView[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3small);
      return;
    case 754:
      imageView[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3small);
      return;
    case 753:
      imageView[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselectsmall);
      return;
    case 752:
      imageView[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstartsmall);
      return;
    case 751:
      imageView[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gprightsmall);
      return;
    case 750:
      imageView[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleftsmall);
      return;
    case 749:
      imageView[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdownsmall);
      return;
    case 748:
      imageView[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpupsmall);
      return;
    case 747:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprbsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1small);
      return;
    case 746:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gplbsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1small);
      return;
    case 745:
      imageView[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpysmall);
      return;
    case 744:
      imageView[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxsmall);
      return;
    case 743:
      imageView[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbsmall);
      return;
    case 742:
      imageView[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpasmall);
      return;
    case 737:
    case 738:
    case 739:
    case 740:
    case 741:
      imageView[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.newkeysmall);
      return;
    case 736:
      imageView[paramInt].setImageResource(R.drawable.center3small);
      imageViewRB[paramInt].setImageResource(R.drawable.center3small);
      imageViewRT[paramInt].setImageResource(R.drawable.center3small);
      imageViewLB[paramInt].setImageResource(R.drawable.center3small);
      imageViewLT[paramInt].setImageResource(R.drawable.center3small);
      return;
    case 735:
      imageView[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr2small);
      return;
    case 734:
      imageView[paramInt].setImageResource(R.drawable.center2small);
      imageViewRB[paramInt].setImageResource(R.drawable.center2small);
      imageViewRT[paramInt].setImageResource(R.drawable.center2small);
      imageViewLB[paramInt].setImageResource(R.drawable.center2small);
      imageViewLT[paramInt].setImageResource(R.drawable.center2small);
      return;
    case 733:
      imageView[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr1small);
      return;
    case 732:
      imageView[paramInt].setImageResource(R.drawable.center1small);
      imageViewRB[paramInt].setImageResource(R.drawable.center1small);
      imageViewRT[paramInt].setImageResource(R.drawable.center1small);
      imageViewLB[paramInt].setImageResource(R.drawable.center1small);
      imageViewLT[paramInt].setImageResource(R.drawable.center1small);
      return;
    case 731:
      imageView[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4small);
      return;
    case 730:
      imageView[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3small);
      return;
    case 729:
      imageView[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2small);
      return;
    case 728:
      imageView[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1small);
      return;
    case 727:
      imageView[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbprsmall);
      return;
    case 726:
      imageView[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbplsmall);
      return;
    case 725:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprtsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2small);
      return;
    case 724:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpltsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2small);
      return;
    case 723:
      imageView[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3small);
      return;
    case 722:
      imageView[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3small);
      return;
    case 721:
      imageView[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselectsmall);
      return;
    case 720:
      imageView[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstartsmall);
      return;
    case 719:
      imageView[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gprightsmall);
      return;
    case 718:
      imageView[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleftsmall);
      return;
    case 717:
      imageView[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdownsmall);
      return;
    case 716:
      imageView[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpupsmall);
      return;
    case 715:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprbsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1small);
      return;
    case 714:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gplbsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1small);
      return;
    case 713:
      imageView[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpysmall);
      return;
    case 712:
      imageView[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxsmall);
      return;
    case 711:
      imageView[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbsmall);
      return;
    case 710:
      imageView[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpasmall);
      return;
    case 705:
    case 706:
    case 707:
    case 708:
    case 709:
      imageView[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.newkeysmall);
      return;
    case 704:
      imageView[paramInt].setImageResource(R.drawable.center3small);
      imageViewRB[paramInt].setImageResource(R.drawable.center3small);
      imageViewRT[paramInt].setImageResource(R.drawable.center3small);
      imageViewLB[paramInt].setImageResource(R.drawable.center3small);
      imageViewLT[paramInt].setImageResource(R.drawable.center3small);
      return;
    case 703:
      imageView[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr2small);
      return;
    case 702:
      imageView[paramInt].setImageResource(R.drawable.center2small);
      imageViewRB[paramInt].setImageResource(R.drawable.center2small);
      imageViewRT[paramInt].setImageResource(R.drawable.center2small);
      imageViewLB[paramInt].setImageResource(R.drawable.center2small);
      imageViewLT[paramInt].setImageResource(R.drawable.center2small);
      return;
    case 701:
      imageView[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr1small);
      return;
    case 700:
      imageView[paramInt].setImageResource(R.drawable.center1small);
      imageViewRB[paramInt].setImageResource(R.drawable.center1small);
      imageViewRT[paramInt].setImageResource(R.drawable.center1small);
      imageViewLB[paramInt].setImageResource(R.drawable.center1small);
      imageViewLT[paramInt].setImageResource(R.drawable.center1small);
      return;
    case 699:
      imageView[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4small);
      return;
    case 698:
      imageView[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3small);
      return;
    case 697:
      imageView[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2small);
      return;
    case 696:
      imageView[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1small);
      return;
    case 695:
      imageView[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbprsmall);
      return;
    case 694:
      imageView[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbplsmall);
      return;
    case 693:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprtsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2small);
      return;
    case 692:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpltsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2small);
      return;
    case 691:
      imageView[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3small);
      return;
    case 690:
      imageView[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3small);
      return;
    case 689:
      imageView[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselectsmall);
      return;
    case 688:
      imageView[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstartsmall);
      return;
    case 687:
      imageView[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gprightsmall);
      return;
    case 686:
      imageView[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleftsmall);
      return;
    case 685:
      imageView[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdownsmall);
      return;
    case 684:
      imageView[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpupsmall);
      return;
    case 683:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprbsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1small);
      return;
    case 682:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gplbsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1small);
      return;
    case 681:
      imageView[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpysmall);
      return;
    case 680:
      imageView[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxsmall);
      return;
    case 679:
      imageView[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbsmall);
      return;
    case 678:
      imageView[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpasmall);
      return;
    case 673:
    case 674:
    case 675:
    case 676:
    case 677:
      imageView[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.newkeysmall);
      return;
    case 672:
      imageView[paramInt].setImageResource(R.drawable.center3small);
      imageViewRB[paramInt].setImageResource(R.drawable.center3small);
      imageViewRT[paramInt].setImageResource(R.drawable.center3small);
      imageViewLB[paramInt].setImageResource(R.drawable.center3small);
      imageViewLT[paramInt].setImageResource(R.drawable.center3small);
      return;
    case 671:
      imageView[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr2small);
      return;
    case 670:
      imageView[paramInt].setImageResource(R.drawable.center2small);
      imageViewRB[paramInt].setImageResource(R.drawable.center2small);
      imageViewRT[paramInt].setImageResource(R.drawable.center2small);
      imageViewLB[paramInt].setImageResource(R.drawable.center2small);
      imageViewLT[paramInt].setImageResource(R.drawable.center2small);
      return;
    case 669:
      imageView[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr1small);
      return;
    case 668:
      imageView[paramInt].setImageResource(R.drawable.center1small);
      imageViewRB[paramInt].setImageResource(R.drawable.center1small);
      imageViewRT[paramInt].setImageResource(R.drawable.center1small);
      imageViewLB[paramInt].setImageResource(R.drawable.center1small);
      imageViewLT[paramInt].setImageResource(R.drawable.center1small);
      return;
    case 667:
      imageView[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4small);
      return;
    case 666:
      imageView[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3small);
      return;
    case 665:
      imageView[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2small);
      return;
    case 664:
      imageView[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1small);
      return;
    case 663:
      imageView[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbprsmall);
      return;
    case 662:
      imageView[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbplsmall);
      return;
    case 661:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprtsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2small);
      return;
    case 660:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpltsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2small);
      return;
    case 659:
      imageView[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3small);
      return;
    case 658:
      imageView[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3small);
      return;
    case 657:
      imageView[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselectsmall);
      return;
    case 656:
      imageView[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstartsmall);
      return;
    case 655:
      imageView[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gprightsmall);
      return;
    case 654:
      imageView[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleftsmall);
      return;
    case 653:
      imageView[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdownsmall);
      return;
    case 652:
      imageView[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpupsmall);
      return;
    case 651:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprbsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1small);
      return;
    case 650:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gplbsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1small);
      return;
    case 649:
      imageView[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpysmall);
      return;
    case 648:
      imageView[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxsmall);
      return;
    case 647:
      imageView[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbsmall);
      return;
    case 646:
      imageView[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpasmall);
      return;
    case 641:
    case 642:
    case 643:
    case 644:
    case 645:
      imageView[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.newkeysmall);
      return;
    case 640:
      imageView[paramInt].setImageResource(R.drawable.center3small);
      imageViewRB[paramInt].setImageResource(R.drawable.center3small);
      imageViewRT[paramInt].setImageResource(R.drawable.center3small);
      imageViewLB[paramInt].setImageResource(R.drawable.center3small);
      imageViewLT[paramInt].setImageResource(R.drawable.center3small);
      return;
    case 639:
      imageView[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr2small);
      return;
    case 638:
      imageView[paramInt].setImageResource(R.drawable.center2small);
      imageViewRB[paramInt].setImageResource(R.drawable.center2small);
      imageViewRT[paramInt].setImageResource(R.drawable.center2small);
      imageViewLB[paramInt].setImageResource(R.drawable.center2small);
      imageViewLT[paramInt].setImageResource(R.drawable.center2small);
      return;
    case 637:
      imageView[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr1small);
      return;
    case 636:
      imageView[paramInt].setImageResource(R.drawable.center1small);
      imageViewRB[paramInt].setImageResource(R.drawable.center1small);
      imageViewRT[paramInt].setImageResource(R.drawable.center1small);
      imageViewLB[paramInt].setImageResource(R.drawable.center1small);
      imageViewLT[paramInt].setImageResource(R.drawable.center1small);
      return;
    case 635:
      imageView[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4small);
      return;
    case 634:
      imageView[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3small);
      return;
    case 633:
      imageView[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2small);
      return;
    case 632:
      imageView[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1small);
      return;
    case 631:
      imageView[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbprsmall);
      return;
    case 630:
      imageView[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbplsmall);
      return;
    case 629:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprtsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2small);
      return;
    case 628:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpltsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2small);
      return;
    case 627:
      imageView[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3small);
      return;
    case 626:
      imageView[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3small);
      return;
    case 625:
      imageView[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselectsmall);
      return;
    case 624:
      imageView[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstartsmall);
      return;
    case 623:
      imageView[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gprightsmall);
      return;
    case 622:
      imageView[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleftsmall);
      return;
    case 621:
      imageView[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdownsmall);
      return;
    case 620:
      imageView[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpupsmall);
      return;
    case 619:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprbsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1small);
      return;
    case 618:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gplbsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1small);
      return;
    case 617:
      imageView[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpysmall);
      return;
    case 616:
      imageView[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxsmall);
      return;
    case 615:
      imageView[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbsmall);
      return;
    case 614:
      imageView[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpasmall);
      return;
    case 609:
    case 610:
    case 611:
    case 612:
    case 613:
      imageView[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.newkeysmall);
      return;
    case 608:
      imageView[paramInt].setImageResource(R.drawable.center3small);
      imageViewRB[paramInt].setImageResource(R.drawable.center3small);
      imageViewRT[paramInt].setImageResource(R.drawable.center3small);
      imageViewLB[paramInt].setImageResource(R.drawable.center3small);
      imageViewLT[paramInt].setImageResource(R.drawable.center3small);
      return;
    case 607:
      imageView[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr2small);
      return;
    case 606:
      imageView[paramInt].setImageResource(R.drawable.center2small);
      imageViewRB[paramInt].setImageResource(R.drawable.center2small);
      imageViewRT[paramInt].setImageResource(R.drawable.center2small);
      imageViewLB[paramInt].setImageResource(R.drawable.center2small);
      imageViewLT[paramInt].setImageResource(R.drawable.center2small);
      return;
    case 605:
      imageView[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr1small);
      return;
    case 604:
      imageView[paramInt].setImageResource(R.drawable.center1small);
      imageViewRB[paramInt].setImageResource(R.drawable.center1small);
      imageViewRT[paramInt].setImageResource(R.drawable.center1small);
      imageViewLB[paramInt].setImageResource(R.drawable.center1small);
      imageViewLT[paramInt].setImageResource(R.drawable.center1small);
      return;
    case 603:
      imageView[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4small);
      return;
    case 602:
      imageView[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3small);
      return;
    case 601:
      imageView[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2small);
      return;
    case 600:
      imageView[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1small);
      return;
    case 599:
      imageView[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbprsmall);
      return;
    case 598:
      imageView[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbplsmall);
      return;
    case 597:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprtsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2small);
      return;
    case 596:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpltsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2small);
      return;
    case 595:
      imageView[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3small);
      return;
    case 594:
      imageView[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3small);
      return;
    case 593:
      imageView[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselectsmall);
      return;
    case 592:
      imageView[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstartsmall);
      return;
    case 591:
      imageView[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gprightsmall);
      return;
    case 590:
      imageView[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleftsmall);
      return;
    case 589:
      imageView[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdownsmall);
      return;
    case 588:
      imageView[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpupsmall);
      return;
    case 587:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprbsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1small);
      return;
    case 586:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gplbsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1small);
      return;
    case 585:
      imageView[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpysmall);
      return;
    case 584:
      imageView[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxsmall);
      return;
    case 583:
      imageView[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbsmall);
      return;
    case 582:
      imageView[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpasmall);
      return;
    case 577:
    case 578:
    case 579:
    case 580:
    case 581:
      imageView[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.newkeysmall);
      return;
    case 576:
      imageView[paramInt].setImageResource(R.drawable.center3small);
      imageViewRB[paramInt].setImageResource(R.drawable.center3small);
      imageViewRT[paramInt].setImageResource(R.drawable.center3small);
      imageViewLB[paramInt].setImageResource(R.drawable.center3small);
      imageViewLT[paramInt].setImageResource(R.drawable.center3small);
      return;
    case 575:
      imageView[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr2small);
      return;
    case 574:
      imageView[paramInt].setImageResource(R.drawable.center2small);
      imageViewRB[paramInt].setImageResource(R.drawable.center2small);
      imageViewRT[paramInt].setImageResource(R.drawable.center2small);
      imageViewLB[paramInt].setImageResource(R.drawable.center2small);
      imageViewLT[paramInt].setImageResource(R.drawable.center2small);
      return;
    case 573:
      imageView[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr1small);
      return;
    case 572:
      imageView[paramInt].setImageResource(R.drawable.center1small);
      imageViewRB[paramInt].setImageResource(R.drawable.center1small);
      imageViewRT[paramInt].setImageResource(R.drawable.center1small);
      imageViewLB[paramInt].setImageResource(R.drawable.center1small);
      imageViewLT[paramInt].setImageResource(R.drawable.center1small);
      return;
    case 571:
      imageView[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4small);
      return;
    case 570:
      imageView[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3small);
      return;
    case 569:
      imageView[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2small);
      return;
    case 568:
      imageView[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1small);
      return;
    case 567:
      imageView[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbprsmall);
      return;
    case 566:
      imageView[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbplsmall);
      return;
    case 565:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprtsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2small);
      return;
    case 564:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpltsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2small);
      return;
    case 563:
      imageView[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3small);
      return;
    case 562:
      imageView[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3small);
      return;
    case 561:
      imageView[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselectsmall);
      return;
    case 560:
      imageView[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstartsmall);
      return;
    case 559:
      imageView[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gprightsmall);
      return;
    case 558:
      imageView[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleftsmall);
      return;
    case 557:
      imageView[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdownsmall);
      return;
    case 556:
      imageView[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpupsmall);
      return;
    case 555:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprbsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1small);
      return;
    case 554:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gplbsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1small);
      return;
    case 553:
      imageView[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpysmall);
      return;
    case 552:
      imageView[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxsmall);
      return;
    case 551:
      imageView[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbsmall);
      return;
    case 550:
      imageView[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpasmall);
      return;
    case 545:
    case 546:
    case 547:
    case 548:
    case 549:
      imageView[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.newkeysmall);
      return;
    case 544:
      imageView[paramInt].setImageResource(R.drawable.center3small);
      imageViewRB[paramInt].setImageResource(R.drawable.center3small);
      imageViewRT[paramInt].setImageResource(R.drawable.center3small);
      imageViewLB[paramInt].setImageResource(R.drawable.center3small);
      imageViewLT[paramInt].setImageResource(R.drawable.center3small);
      return;
    case 543:
      imageView[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr2small);
      return;
    case 542:
      imageView[paramInt].setImageResource(R.drawable.center2small);
      imageViewRB[paramInt].setImageResource(R.drawable.center2small);
      imageViewRT[paramInt].setImageResource(R.drawable.center2small);
      imageViewLB[paramInt].setImageResource(R.drawable.center2small);
      imageViewLT[paramInt].setImageResource(R.drawable.center2small);
      return;
    case 541:
      imageView[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr1small);
      return;
    case 540:
      imageView[paramInt].setImageResource(R.drawable.center1small);
      imageViewRB[paramInt].setImageResource(R.drawable.center1small);
      imageViewRT[paramInt].setImageResource(R.drawable.center1small);
      imageViewLB[paramInt].setImageResource(R.drawable.center1small);
      imageViewLT[paramInt].setImageResource(R.drawable.center1small);
      return;
    case 539:
      imageView[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4small);
      return;
    case 538:
      imageView[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3small);
      return;
    case 537:
      imageView[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2small);
      return;
    case 536:
      imageView[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1small);
      return;
    case 535:
      imageView[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbprsmall);
      return;
    case 534:
      imageView[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbplsmall);
      return;
    case 533:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprtsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2small);
      return;
    case 532:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpltsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2small);
      return;
    case 531:
      imageView[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3small);
      return;
    case 530:
      imageView[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3small);
      return;
    case 529:
      imageView[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselectsmall);
      return;
    case 528:
      imageView[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstartsmall);
      return;
    case 527:
      imageView[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gprightsmall);
      return;
    case 526:
      imageView[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleftsmall);
      return;
    case 525:
      imageView[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdownsmall);
      return;
    case 524:
      imageView[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpupsmall);
      return;
    case 523:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprbsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1small);
      return;
    case 522:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gplbsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1small);
      return;
    case 521:
      imageView[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpysmall);
      return;
    case 520:
      imageView[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxsmall);
      return;
    case 519:
      imageView[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbsmall);
      return;
    case 518:
      imageView[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpasmall);
      return;
    case 513:
    case 514:
    case 515:
    case 516:
    case 517:
      imageView[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.newkeysmall);
      return;
    case 512:
      imageView[paramInt].setImageResource(R.drawable.center3small);
      imageViewRB[paramInt].setImageResource(R.drawable.center3small);
      imageViewRT[paramInt].setImageResource(R.drawable.center3small);
      imageViewLB[paramInt].setImageResource(R.drawable.center3small);
      imageViewLT[paramInt].setImageResource(R.drawable.center3small);
      return;
    case 511:
      imageView[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr2small);
      return;
    case 510:
      imageView[paramInt].setImageResource(R.drawable.center2small);
      imageViewRB[paramInt].setImageResource(R.drawable.center2small);
      imageViewRT[paramInt].setImageResource(R.drawable.center2small);
      imageViewLB[paramInt].setImageResource(R.drawable.center2small);
      imageViewLT[paramInt].setImageResource(R.drawable.center2small);
      return;
    case 509:
      imageView[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr1small);
      return;
    case 508:
      imageView[paramInt].setImageResource(R.drawable.center1small);
      imageViewRB[paramInt].setImageResource(R.drawable.center1small);
      imageViewRT[paramInt].setImageResource(R.drawable.center1small);
      imageViewLB[paramInt].setImageResource(R.drawable.center1small);
      imageViewLT[paramInt].setImageResource(R.drawable.center1small);
      return;
    case 507:
      imageView[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4small);
      return;
    case 506:
      imageView[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3small);
      return;
    case 505:
      imageView[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2small);
      return;
    case 504:
      imageView[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1small);
      return;
    case 503:
      imageView[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbprsmall);
      return;
    case 502:
      imageView[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbplsmall);
      return;
    case 501:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprtsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2small);
      return;
    case 500:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpltsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2small);
      return;
    case 499:
      imageView[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3small);
      return;
    case 498:
      imageView[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3small);
      return;
    case 497:
      imageView[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselectsmall);
      return;
    case 496:
      imageView[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstartsmall);
      return;
    case 495:
      imageView[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gprightsmall);
      return;
    case 494:
      imageView[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleftsmall);
      return;
    case 493:
      imageView[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdownsmall);
      return;
    case 492:
      imageView[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpupsmall);
      return;
    case 491:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprbsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1small);
      return;
    case 490:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gplbsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1small);
      return;
    case 489:
      imageView[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpysmall);
      return;
    case 488:
      imageView[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxsmall);
      return;
    case 487:
      imageView[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbsmall);
      return;
    case 486:
      imageView[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpasmall);
      return;
    case 481:
    case 482:
    case 483:
    case 484:
    case 485:
      imageView[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.newkeysmall);
      return;
    case 480:
      imageView[paramInt].setImageResource(R.drawable.center3small);
      imageViewRB[paramInt].setImageResource(R.drawable.center3small);
      imageViewRT[paramInt].setImageResource(R.drawable.center3small);
      imageViewLB[paramInt].setImageResource(R.drawable.center3small);
      imageViewLT[paramInt].setImageResource(R.drawable.center3small);
      return;
    case 479:
      imageView[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr2small);
      return;
    case 478:
      imageView[paramInt].setImageResource(R.drawable.center2small);
      imageViewRB[paramInt].setImageResource(R.drawable.center2small);
      imageViewRT[paramInt].setImageResource(R.drawable.center2small);
      imageViewLB[paramInt].setImageResource(R.drawable.center2small);
      imageViewLT[paramInt].setImageResource(R.drawable.center2small);
      return;
    case 477:
      imageView[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr1small);
      return;
    case 476:
      imageView[paramInt].setImageResource(R.drawable.center1small);
      imageViewRB[paramInt].setImageResource(R.drawable.center1small);
      imageViewRT[paramInt].setImageResource(R.drawable.center1small);
      imageViewLB[paramInt].setImageResource(R.drawable.center1small);
      imageViewLT[paramInt].setImageResource(R.drawable.center1small);
      return;
    case 475:
      imageView[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4small);
      return;
    case 474:
      imageView[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3small);
      return;
    case 473:
      imageView[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2small);
      return;
    case 472:
      imageView[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1small);
      return;
    case 471:
      imageView[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbprsmall);
      return;
    case 470:
      imageView[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbplsmall);
      return;
    case 469:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprtsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2small);
      return;
    case 468:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpltsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2small);
      return;
    case 467:
      imageView[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3small);
      return;
    case 466:
      imageView[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3small);
      return;
    case 465:
      imageView[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselectsmall);
      return;
    case 464:
      imageView[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstartsmall);
      return;
    case 463:
      imageView[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gprightsmall);
      return;
    case 462:
      imageView[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleftsmall);
      return;
    case 461:
      imageView[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdownsmall);
      return;
    case 460:
      imageView[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpupsmall);
      return;
    case 459:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprbsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1small);
      return;
    case 458:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gplbsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1small);
      return;
    case 457:
      imageView[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpysmall);
      return;
    case 456:
      imageView[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxsmall);
      return;
    case 455:
      imageView[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbsmall);
      return;
    case 454:
      imageView[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpasmall);
      return;
    case 444:
    case 445:
    case 446:
    case 447:
    case 448:
    case 449:
    case 450:
    case 451:
    case 452:
    case 453:
      imageView[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.newkeysmall);
      return;
    case 443:
      imageView[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4small);
      return;
    case 442:
      imageView[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3small);
      return;
    case 441:
      imageView[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2small);
      return;
    case 440:
      imageView[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1small);
      return;
    case 439:
      imageView[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbprsmall);
      return;
    case 438:
      imageView[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbplsmall);
      return;
    case 437:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprtsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2small);
      return;
    case 436:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpltsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2small);
      return;
    case 435:
      imageView[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3small);
      return;
    case 434:
      imageView[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3small);
      return;
    case 433:
      imageView[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselectsmall);
      return;
    case 432:
      imageView[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstartsmall);
      return;
    case 431:
      imageView[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gprightsmall);
      return;
    case 430:
      imageView[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleftsmall);
      return;
    case 429:
      imageView[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdownsmall);
      return;
    case 428:
      imageView[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpupsmall);
      return;
    case 427:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprbsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1small);
      return;
    case 426:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gplbsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1small);
      return;
    case 425:
      imageView[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpysmall);
      return;
    case 424:
      imageView[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxsmall);
      return;
    case 423:
      imageView[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbsmall);
      return;
    case 422:
      imageView[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpasmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpasmall);
      return;
    case 412:
    case 413:
    case 414:
    case 415:
    case 416:
    case 417:
    case 418:
    case 419:
    case 420:
    case 421:
      imageView[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.newkeysmall);
      return;
    case 411:
      imageView[paramInt].setImageResource(R.drawable.gpm4hp2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4hp2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4hp2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4hp2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4hp2small);
      return;
    case 410:
      imageView[paramInt].setImageResource(R.drawable.gpm3hp2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3hp2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3hp2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3hp2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3hp2small);
      return;
    case 409:
      imageView[paramInt].setImageResource(R.drawable.gpm2hp2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2hp2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2hp2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2hp2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2hp2small);
      return;
    case 408:
      imageView[paramInt].setImageResource(R.drawable.gpm1hp2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1hp2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1hp2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1hp2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1hp2small);
      return;
    case 407:
      imageView[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbprsmall);
      return;
    case 406:
      imageView[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbplsmall);
      return;
    case 405:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprthp2small);
        imageViewRB[paramInt].setImageResource(R.drawable.gprthp2small);
        imageViewRT[paramInt].setImageResource(R.drawable.gprthp2small);
        imageViewLB[paramInt].setImageResource(R.drawable.gprthp2small);
        imageViewLT[paramInt].setImageResource(R.drawable.gprthp2small);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2hp2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2hp2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2hp2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2hp2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2hp2small);
      return;
    case 404:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplthp2small);
        imageViewRB[paramInt].setImageResource(R.drawable.gplthp2small);
        imageViewRT[paramInt].setImageResource(R.drawable.gplthp2small);
        imageViewLB[paramInt].setImageResource(R.drawable.gplthp2small);
        imageViewLT[paramInt].setImageResource(R.drawable.gplthp2small);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2hp2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2hp2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2hp2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2hp2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2hp2small);
      return;
    case 403:
      imageView[paramInt].setImageResource(R.drawable.gpr3hp2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3hp2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3hp2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3hp2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3hp2small);
      return;
    case 402:
      imageView[paramInt].setImageResource(R.drawable.gpl3hp2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3hp2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3hp2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3hp2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3hp2small);
      return;
    case 401:
      imageView[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselectsmall);
      return;
    case 400:
      imageView[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstartsmall);
      return;
    case 399:
      imageView[paramInt].setImageResource(R.drawable.gprighthp2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gprighthp2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gprighthp2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gprighthp2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gprighthp2small);
      return;
    case 398:
      imageView[paramInt].setImageResource(R.drawable.gplefthp2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gplefthp2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gplefthp2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gplefthp2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gplefthp2small);
      return;
    case 397:
      imageView[paramInt].setImageResource(R.drawable.gpdownhp2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdownhp2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdownhp2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdownhp2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdownhp2small);
      return;
    case 396:
      imageView[paramInt].setImageResource(R.drawable.gpuphp2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpuphp2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpuphp2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpuphp2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpuphp2small);
      return;
    case 395:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprbhp2small);
        imageViewRB[paramInt].setImageResource(R.drawable.gprbhp2small);
        imageViewRT[paramInt].setImageResource(R.drawable.gprbhp2small);
        imageViewLB[paramInt].setImageResource(R.drawable.gprbhp2small);
        imageViewLT[paramInt].setImageResource(R.drawable.gprbhp2small);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1hp2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1hp2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1hp2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1hp2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1hp2small);
      return;
    case 394:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplbhp2small);
        imageViewRB[paramInt].setImageResource(R.drawable.gplbhp2small);
        imageViewRT[paramInt].setImageResource(R.drawable.gplbhp2small);
        imageViewLB[paramInt].setImageResource(R.drawable.gplbhp2small);
        imageViewLT[paramInt].setImageResource(R.drawable.gplbhp2small);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1hp2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1hp2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1hp2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1hp2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1hp2small);
      return;
    case 393:
      imageView[paramInt].setImageResource(R.drawable.gpyhp2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpyhp2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpyhp2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpyhp2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpyhp2small);
      return;
    case 392:
      imageView[paramInt].setImageResource(R.drawable.gpxhp2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxhp2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxhp2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxhp2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxhp2small);
      return;
    case 391:
      imageView[paramInt].setImageResource(R.drawable.gpbhp2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbhp2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbhp2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbhp2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbhp2small);
      return;
    case 390:
      imageView[paramInt].setImageResource(R.drawable.gpahp2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpahp2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpahp2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpahp2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpahp2small);
      return;
    case 380:
    case 381:
    case 382:
    case 383:
    case 384:
    case 385:
    case 386:
    case 387:
    case 388:
    case 389:
      imageView[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.newkeysmall);
      return;
    case 379:
      imageView[paramInt].setImageResource(R.drawable.gpm4hp1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4hp1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4hp1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4hp1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4hp1small);
      return;
    case 378:
      imageView[paramInt].setImageResource(R.drawable.gpm3hp1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3hp1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3hp1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3hp1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3hp1small);
      return;
    case 377:
      imageView[paramInt].setImageResource(R.drawable.gpm2hp1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2hp1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2hp1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2hp1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2hp1small);
      return;
    case 376:
      imageView[paramInt].setImageResource(R.drawable.gpm1hp1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1hp1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1hp1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1hp1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1hp1small);
      return;
    case 375:
      imageView[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbprsmall);
      return;
    case 374:
      imageView[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbplsmall);
      return;
    case 373:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprthp1small);
        imageViewRB[paramInt].setImageResource(R.drawable.gprthp1small);
        imageViewRT[paramInt].setImageResource(R.drawable.gprthp1small);
        imageViewLB[paramInt].setImageResource(R.drawable.gprthp1small);
        imageViewLT[paramInt].setImageResource(R.drawable.gprthp1small);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2hp1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2hp1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2hp1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2hp1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2hp1small);
      return;
    case 372:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplthp1small);
        imageViewRB[paramInt].setImageResource(R.drawable.gplthp1small);
        imageViewRT[paramInt].setImageResource(R.drawable.gplthp1small);
        imageViewLB[paramInt].setImageResource(R.drawable.gplthp1small);
        imageViewLT[paramInt].setImageResource(R.drawable.gplthp1small);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2hp1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2hp1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2hp1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2hp1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2hp1small);
      return;
    case 371:
      imageView[paramInt].setImageResource(R.drawable.gpr3hp1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3hp1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3hp1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3hp1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3hp1small);
      return;
    case 370:
      imageView[paramInt].setImageResource(R.drawable.gpl3hp1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3hp1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3hp1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3hp1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3hp1small);
      return;
    case 369:
      imageView[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselectsmall);
      return;
    case 368:
      imageView[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstartsmall);
      return;
    case 367:
      imageView[paramInt].setImageResource(R.drawable.gprighthp1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gprighthp1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gprighthp1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gprighthp1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gprighthp1small);
      return;
    case 366:
      imageView[paramInt].setImageResource(R.drawable.gplefthp1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gplefthp1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gplefthp1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gplefthp1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gplefthp1small);
      return;
    case 365:
      imageView[paramInt].setImageResource(R.drawable.gpdownhp1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdownhp1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdownhp1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdownhp1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdownhp1small);
      return;
    case 364:
      imageView[paramInt].setImageResource(R.drawable.gpuphp1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpuphp1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpuphp1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpuphp1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpuphp1small);
      return;
    case 363:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprbhp1small);
        imageViewRB[paramInt].setImageResource(R.drawable.gprbhp1small);
        imageViewRT[paramInt].setImageResource(R.drawable.gprbhp1small);
        imageViewLB[paramInt].setImageResource(R.drawable.gprbhp1small);
        imageViewLT[paramInt].setImageResource(R.drawable.gprbhp1small);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1hp1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1hp1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1hp1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1hp1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1hp1small);
      return;
    case 362:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplbhp1small);
        imageViewRB[paramInt].setImageResource(R.drawable.gplbhp1small);
        imageViewRT[paramInt].setImageResource(R.drawable.gplbhp1small);
        imageViewLB[paramInt].setImageResource(R.drawable.gplbhp1small);
        imageViewLT[paramInt].setImageResource(R.drawable.gplbhp1small);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1hp1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1hp1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1hp1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1hp1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1hp1small);
      return;
    case 361:
      imageView[paramInt].setImageResource(R.drawable.gpyhp1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpyhp1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpyhp1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpyhp1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpyhp1small);
      return;
    case 360:
      imageView[paramInt].setImageResource(R.drawable.gpxhp1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxhp1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxhp1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxhp1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxhp1small);
      return;
    case 359:
      imageView[paramInt].setImageResource(R.drawable.gpbhp1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbhp1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbhp1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbhp1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbhp1small);
      return;
    case 358:
      imageView[paramInt].setImageResource(R.drawable.gpahp1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpahp1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpahp1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpahp1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpahp1small);
      return;
    case 348:
    case 349:
    case 350:
    case 351:
    case 352:
    case 353:
    case 354:
    case 355:
    case 356:
    case 357:
      imageView[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.newkeysmall);
      return;
    case 347:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpm4comsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpm4comrbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpm4comrtsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpm4comlbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpm4comltsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpm4comsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4comr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4comr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4coml1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4coml2small);
      return;
    case 346:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpm3comsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpm3comrbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpm3comrtsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpm3comlbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpm3comltsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpm3comsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3comr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3comr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3coml1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3coml2small);
      return;
    case 345:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpm2comsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpm2comrbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpm2comrtsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpm2comlbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpm2comltsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpm2comsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2comr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2comr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2coml1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2coml2small);
      return;
    case 344:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpm1comsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpm1comrbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpm1comrtsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpm1comlbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpm1comltsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpm1comsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1comr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1comr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1coml1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1coml2small);
      return;
    case 343:
      imageView[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbprsmall);
      return;
    case 342:
      imageView[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbplsmall);
      return;
    case 341:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprtcomsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprtcomrbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprtcomrtsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprtcomlbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprtcomltsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2comsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2comr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2comr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2coml1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2coml2small);
      return;
    case 340:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpltcomsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpltcomrbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpltcomrtsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpltcomlbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpltcomltsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2comsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2comr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2comr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2coml1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2coml2small);
      return;
    case 339:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpr3comsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpr3comrbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpr3comrtsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpr3comlbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpr3comltsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr3comsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3comr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3comr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3coml1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3coml2small);
      return;
    case 338:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpl3comsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpl3comrbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpl3comrtsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpl3comlbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpl3comltsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl3comsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3comr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3comr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3coml1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3coml2small);
      return;
    case 337:
      imageView[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselectsmall);
      return;
    case 336:
      imageView[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstartsmall);
      return;
    case 335:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprightcomsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprightcomrbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprightcomrtsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprightcomlbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprightcomltsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gprightcomsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gprightcomr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gprightcomr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gprightcoml1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gprightcoml2small);
      return;
    case 334:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpleftcomsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpleftcomrbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpleftcomrtsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpleftcomlbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpleftcomltsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpleftcomsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleftcomr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleftcomr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleftcoml1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleftcoml2small);
      return;
    case 333:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpdowncomsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpdowncomrbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpdowncomrtsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpdowncomlbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpdowncomltsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpdowncomsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdowncomr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdowncomr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdowncoml1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdowncoml2small);
      return;
    case 332:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpupcomsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpupcomrbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpupcomrtsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpupcomlbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpupcomltsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpupcomsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpupcomr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpupcomr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpupcoml1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpupcoml2small);
      return;
    case 331:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprbcomsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprbcomrbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprbcomrtsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprbcomlbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprbcomltsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1comsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1comr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1comr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1coml1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1coml2small);
      return;
    case 330:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplbcomsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gplbcomrbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gplbcomrtsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gplbcomlbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gplbcomltsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1comsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1comr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1comr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1coml1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1coml2small);
      return;
    case 329:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpycomsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpycomrbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpycomrtsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpycomlbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpycomltsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpycomsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpycomr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpycomr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpycoml1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpycoml2small);
      return;
    case 328:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpxcomsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpxcomrbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpxcomrtsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpxcomlbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpxcomltsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpxcomsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxcomr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxcomr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxcoml1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxcoml2small);
      return;
    case 327:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpbcomsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpbcomrbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpbcomrtsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpbcomlbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpbcomltsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpbcomsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbcomr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbcomr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbcoml1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbcoml2small);
      return;
    case 326:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpacomsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpacomrbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpacomrtsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpacomlbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpacomltsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpacomsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpacomr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpacomr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpacoml1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpacoml2small);
      return;
    case 316:
    case 317:
    case 318:
    case 319:
    case 320:
    case 321:
    case 322:
    case 323:
    case 324:
    case 325:
      imageView[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.newkeysmall);
      return;
    case 315:
      imageView[paramInt].setImageResource(R.drawable.gpm4firstsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4firstsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4firstsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4firstsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4firstsmall);
      return;
    case 314:
      imageView[paramInt].setImageResource(R.drawable.gpm3firstsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3firstsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3firstsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3firstsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3firstsmall);
      return;
    case 313:
      imageView[paramInt].setImageResource(R.drawable.gpm2firstsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2firstsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2firstsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2firstsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2firstsmall);
      return;
    case 312:
      imageView[paramInt].setImageResource(R.drawable.gpm1firstsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1firstsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1firstsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1firstsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1firstsmall);
      return;
    case 311:
      imageView[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      return;
    case 310:
      imageView[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      return;
    case 309:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprtfirstsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprtfirstsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprtfirstsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprtfirstsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprtfirstsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2firstsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2firstsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2firstsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2firstsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2firstsmall);
      return;
    case 308:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpltfirstsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpltfirstsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpltfirstsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpltfirstsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpltfirstsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2firstsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2firstsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2firstsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2firstsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2firstsmall);
      return;
    case 307:
      imageView[paramInt].setImageResource(R.drawable.gpr3firstsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3firstsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3firstsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3firstsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3firstsmall);
      return;
    case 306:
      imageView[paramInt].setImageResource(R.drawable.gpl3firstsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3firstsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3firstsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3firstsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3firstsmall);
      return;
    case 305:
      imageView[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      return;
    case 304:
      imageView[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      return;
    case 303:
      imageView[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      return;
    case 302:
      imageView[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      return;
    case 301:
      imageView[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      return;
    case 300:
      imageView[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      return;
    case 299:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprbfirstsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprbfirstsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprbfirstsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprbfirstsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprbfirstsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1firstsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1firstsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1firstsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1firstsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1firstsmall);
      return;
    case 298:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplbfirstsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gplbfirstsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gplbfirstsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gplbfirstsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gplbfirstsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1firstsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1firstsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1firstsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1firstsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1firstsmall);
      return;
    case 297:
      imageView[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      return;
    case 296:
      imageView[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      return;
    case 295:
      imageView[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      return;
    case 294:
      imageView[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gplbfirstsmall);
      return;
    case 284:
    case 285:
    case 286:
    case 287:
    case 288:
    case 289:
    case 290:
    case 291:
    case 292:
    case 293:
      imageView[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.newkeysmall);
      return;
    case 283:
      imageView[paramInt].setImageResource(R.drawable.gpm4rviewsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4rviewsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4rviewsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4rviewsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4rviewsmall);
      return;
    case 282:
      imageView[paramInt].setImageResource(R.drawable.gpm3rviewsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3rviewsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3rviewsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3rviewsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3rviewsmall);
      return;
    case 281:
      imageView[paramInt].setImageResource(R.drawable.gpm2rviewsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2rviewsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2rviewsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2rviewsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2rviewsmall);
      return;
    case 280:
      imageView[paramInt].setImageResource(R.drawable.gpm1rviewsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1rviewsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1rviewsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1rviewsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1rviewsmall);
      return;
    case 279:
      imageView[paramInt].setImageResource(R.drawable.gparviewsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gparviewsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gparviewsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gparviewsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gparviewsmall);
      return;
    case 278:
      imageView[paramInt].setImageResource(R.drawable.gparviewsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gparviewsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gparviewsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gparviewsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gparviewsmall);
      return;
    case 277:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprtviewsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprtviewsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprtviewsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprtviewsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprtviewsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2rviewsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2rviewsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2rviewsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2rviewsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2rviewsmall);
      return;
    case 276:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpltviewsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpltviewsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpltviewsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpltviewsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpltviewsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2rviewsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2rviewsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2rviewsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2rviewsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2rviewsmall);
      return;
    case 275:
      imageView[paramInt].setImageResource(R.drawable.gpr3rviewsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3rviewsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3rviewsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3rviewsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3rviewsmall);
      return;
    case 274:
      imageView[paramInt].setImageResource(R.drawable.gpl3rviewsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3rviewsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3rviewsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3rviewsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3rviewsmall);
      return;
    case 273:
      imageView[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselectsmall);
      return;
    case 272:
      imageView[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstartsmall);
      return;
    case 271:
      imageView[paramInt].setImageResource(R.drawable.gparviewsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gparviewsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gparviewsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gparviewsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gparviewsmall);
      return;
    case 270:
      imageView[paramInt].setImageResource(R.drawable.gparviewsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gparviewsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gparviewsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gparviewsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gparviewsmall);
      return;
    case 269:
      imageView[paramInt].setImageResource(R.drawable.gparviewsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gparviewsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gparviewsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gparviewsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gparviewsmall);
      return;
    case 268:
      imageView[paramInt].setImageResource(R.drawable.gparviewsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gparviewsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gparviewsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gparviewsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gparviewsmall);
      return;
    case 267:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprbviewsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprbviewsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprbviewsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprbviewsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprbviewsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1rviewsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1rviewsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1rviewsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1rviewsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1rviewsmall);
      return;
    case 266:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplbviewsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gplbviewsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gplbviewsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gplbviewsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gplbviewsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1rviewsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1rviewsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1rviewsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1rviewsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1rviewsmall);
      return;
    case 265:
      imageView[paramInt].setImageResource(R.drawable.gpyrviewsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpyrviewsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpyrviewsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpyrviewsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpyrviewsmall);
      return;
    case 264:
      imageView[paramInt].setImageResource(R.drawable.gpxrviewsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxrviewsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxrviewsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxrviewsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxrviewsmall);
      return;
    case 263:
      imageView[paramInt].setImageResource(R.drawable.gpbrviewsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbrviewsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbrviewsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbrviewsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbrviewsmall);
      return;
    case 262:
      imageView[paramInt].setImageResource(R.drawable.gparviewsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gparviewsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gparviewsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gparviewsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gparviewsmall);
      return;
    case 252:
    case 253:
    case 254:
    case 255:
    case 256:
    case 257:
    case 258:
    case 259:
    case 260:
    case 261:
      imageView[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.newkeysmall);
      return;
    case 251:
      imageView[paramInt].setImageResource(R.drawable.gpm4y2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4y2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4y2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4y2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4y2small);
      return;
    case 250:
      imageView[paramInt].setImageResource(R.drawable.gpm3y2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3y2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3y2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3y2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3y2small);
      return;
    case 249:
      imageView[paramInt].setImageResource(R.drawable.gpm2y2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2y2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2y2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2y2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2y2small);
      return;
    case 248:
      imageView[paramInt].setImageResource(R.drawable.gpm1y2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1y2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1y2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1y2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1y2small);
      return;
    case 247:
      imageView[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbprsmall);
      return;
    case 246:
      imageView[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbplsmall);
      return;
    case 245:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprty2small);
        imageViewRB[paramInt].setImageResource(R.drawable.gprty2small);
        imageViewRT[paramInt].setImageResource(R.drawable.gprty2small);
        imageViewLB[paramInt].setImageResource(R.drawable.gprty2small);
        imageViewLT[paramInt].setImageResource(R.drawable.gprty2small);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2y2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2y2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2y2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2y2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2y2small);
      return;
    case 244:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplty2small);
        imageViewRB[paramInt].setImageResource(R.drawable.gplty2small);
        imageViewRT[paramInt].setImageResource(R.drawable.gplty2small);
        imageViewLB[paramInt].setImageResource(R.drawable.gplty2small);
        imageViewLT[paramInt].setImageResource(R.drawable.gplty2small);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2y2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2y2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2y2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2y2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2y2small);
      return;
    case 243:
      imageView[paramInt].setImageResource(R.drawable.gpr3y2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3y2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3y2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3y2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3y2small);
      return;
    case 242:
      imageView[paramInt].setImageResource(R.drawable.gpl3y2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3y2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3y2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3y2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3y2small);
      return;
    case 241:
      imageView[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselectsmall);
      return;
    case 240:
      imageView[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstartsmall);
      return;
    case 239:
      imageView[paramInt].setImageResource(R.drawable.gprighty2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gprighty2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gprighty2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gprighty2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gprighty2small);
      return;
    case 238:
      imageView[paramInt].setImageResource(R.drawable.gplefty2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gplefty2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gplefty2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gplefty2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gplefty2small);
      return;
    case 237:
      imageView[paramInt].setImageResource(R.drawable.gpdowny2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdowny2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdowny2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdowny2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdowny2small);
      return;
    case 236:
      imageView[paramInt].setImageResource(R.drawable.gpupy2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpupy2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpupy2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpupy2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpupy2small);
      return;
    case 235:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprby2small);
        imageViewRB[paramInt].setImageResource(R.drawable.gprby2small);
        imageViewRT[paramInt].setImageResource(R.drawable.gprby2small);
        imageViewLB[paramInt].setImageResource(R.drawable.gprby2small);
        imageViewLT[paramInt].setImageResource(R.drawable.gprby2small);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1y2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1y2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1y2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1y2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1y2small);
      return;
    case 234:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplby2small);
        imageViewRB[paramInt].setImageResource(R.drawable.gplby2small);
        imageViewRT[paramInt].setImageResource(R.drawable.gplby2small);
        imageViewLB[paramInt].setImageResource(R.drawable.gplby2small);
        imageViewLT[paramInt].setImageResource(R.drawable.gplby2small);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1y2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1y2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1y2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1y2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1y2small);
      return;
    case 233:
      imageView[paramInt].setImageResource(R.drawable.gpyy2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpyy2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpyy2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpyy2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpyy2small);
      return;
    case 232:
      imageView[paramInt].setImageResource(R.drawable.gpxy2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxy2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxy2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxy2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxy2small);
      return;
    case 231:
      imageView[paramInt].setImageResource(R.drawable.gpby2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpby2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpby2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpby2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpby2small);
      return;
    case 230:
      imageView[paramInt].setImageResource(R.drawable.gpay2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpay2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpay2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpay2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpay2small);
      return;
    case 220:
    case 221:
    case 222:
    case 223:
    case 224:
    case 225:
    case 226:
    case 227:
    case 228:
    case 229:
      imageView[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.newkeysmall);
      return;
    case 219:
      imageView[paramInt].setImageResource(R.drawable.gpm4y1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4y1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4y1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4y1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4y1small);
      return;
    case 218:
      imageView[paramInt].setImageResource(R.drawable.gpm3y1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3y1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3y1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3y1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3y1small);
      return;
    case 217:
      imageView[paramInt].setImageResource(R.drawable.gpm2y1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2y1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2y1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2y1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2y1small);
      return;
    case 216:
      imageView[paramInt].setImageResource(R.drawable.gpm1y1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1y1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1y1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1y1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1y1small);
      return;
    case 215:
      imageView[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbprsmall);
      return;
    case 214:
      imageView[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbplsmall);
      return;
    case 213:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprty1small);
        imageViewRB[paramInt].setImageResource(R.drawable.gprty1small);
        imageViewRT[paramInt].setImageResource(R.drawable.gprty1small);
        imageViewLB[paramInt].setImageResource(R.drawable.gprty1small);
        imageViewLT[paramInt].setImageResource(R.drawable.gprty1small);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2y1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2y1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2y1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2y1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2y1small);
      return;
    case 212:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplty1small);
        imageViewRB[paramInt].setImageResource(R.drawable.gplty1small);
        imageViewRT[paramInt].setImageResource(R.drawable.gplty1small);
        imageViewLB[paramInt].setImageResource(R.drawable.gplty1small);
        imageViewLT[paramInt].setImageResource(R.drawable.gplty1small);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2y1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2y1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2y1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2y1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2y1small);
      return;
    case 211:
      imageView[paramInt].setImageResource(R.drawable.gpr3y1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3y1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3y1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3y1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3y1small);
      return;
    case 210:
      imageView[paramInt].setImageResource(R.drawable.gpl3y1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3y1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3y1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3y1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3y1small);
      return;
    case 209:
      imageView[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselectsmall);
      return;
    case 208:
      imageView[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstartsmall);
      return;
    case 207:
      imageView[paramInt].setImageResource(R.drawable.gprighty1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gprighty1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gprighty1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gprighty1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gprighty1small);
      return;
    case 206:
      imageView[paramInt].setImageResource(R.drawable.gplefty1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gplefty1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gplefty1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gplefty1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gplefty1small);
      return;
    case 205:
      imageView[paramInt].setImageResource(R.drawable.gpdowny1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdowny1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdowny1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdowny1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdowny1small);
      return;
    case 204:
      imageView[paramInt].setImageResource(R.drawable.gpupy1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpupy1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpupy1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpupy1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpupy1small);
      return;
    case 203:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprby1small);
        imageViewRB[paramInt].setImageResource(R.drawable.gprby1small);
        imageViewRT[paramInt].setImageResource(R.drawable.gprby1small);
        imageViewLB[paramInt].setImageResource(R.drawable.gprby1small);
        imageViewLT[paramInt].setImageResource(R.drawable.gprby1small);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1y1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1y1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1y1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1y1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1y1small);
      return;
    case 202:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplby1small);
        imageViewRB[paramInt].setImageResource(R.drawable.gplby1small);
        imageViewRT[paramInt].setImageResource(R.drawable.gplby1small);
        imageViewLB[paramInt].setImageResource(R.drawable.gplby1small);
        imageViewLT[paramInt].setImageResource(R.drawable.gplby1small);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1y1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1y1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1y1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1y1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1y1small);
      return;
    case 201:
      imageView[paramInt].setImageResource(R.drawable.gpyy1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpyy1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpyy1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpyy1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpyy1small);
      return;
    case 200:
      imageView[paramInt].setImageResource(R.drawable.gpxy1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxy1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxy1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxy1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxy1small);
      return;
    case 199:
      imageView[paramInt].setImageResource(R.drawable.gpby1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpby1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpby1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpby1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpby1small);
      return;
    case 198:
      imageView[paramInt].setImageResource(R.drawable.gpay1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpay1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpay1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpay1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpay1small);
      return;
    case 188:
    case 189:
    case 190:
    case 191:
    case 192:
    case 193:
    case 194:
    case 195:
    case 196:
    case 197:
      imageView[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.newkeysmall);
      return;
    case 187:
      imageView[paramInt].setImageResource(R.drawable.gpm42small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm42small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm42small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm42small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm42small);
      return;
    case 186:
      imageView[paramInt].setImageResource(R.drawable.gpm32small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm32small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm32small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm32small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm32small);
      return;
    case 185:
      imageView[paramInt].setImageResource(R.drawable.gpm22small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm22small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm22small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm22small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm22small);
      return;
    case 184:
      imageView[paramInt].setImageResource(R.drawable.gpm12small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm12small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm12small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm12small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm12small);
      return;
    case 183:
      imageView[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbprsmall);
      return;
    case 182:
      imageView[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbplsmall);
      return;
    case 181:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprt2small);
        imageViewRB[paramInt].setImageResource(R.drawable.gprt2small);
        imageViewRT[paramInt].setImageResource(R.drawable.gprt2small);
        imageViewLB[paramInt].setImageResource(R.drawable.gprt2small);
        imageViewLT[paramInt].setImageResource(R.drawable.gprt2small);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr22small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr22small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr22small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr22small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr22small);
      return;
    case 180:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplt2small);
        imageViewRB[paramInt].setImageResource(R.drawable.gplt2small);
        imageViewRT[paramInt].setImageResource(R.drawable.gplt2small);
        imageViewLB[paramInt].setImageResource(R.drawable.gplt2small);
        imageViewLT[paramInt].setImageResource(R.drawable.gplt2small);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl22small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl22small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl22small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl22small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl22small);
      return;
    case 179:
      imageView[paramInt].setImageResource(R.drawable.gpr32small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr32small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr32small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr32small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr32small);
      return;
    case 178:
      imageView[paramInt].setImageResource(R.drawable.gpl32small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl32small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl32small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl32small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl32small);
      return;
    case 177:
      imageView[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselectsmall);
      return;
    case 176:
      imageView[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstartsmall);
      return;
    case 175:
      imageView[paramInt].setImageResource(R.drawable.gpright2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpright2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpright2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpright2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpright2small);
      return;
    case 174:
      imageView[paramInt].setImageResource(R.drawable.gpleft2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleft2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleft2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleft2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleft2small);
      return;
    case 173:
      imageView[paramInt].setImageResource(R.drawable.gpdown2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdown2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdown2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdown2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdown2small);
      return;
    case 172:
      imageView[paramInt].setImageResource(R.drawable.gpup2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpup2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpup2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpup2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpup2small);
      return;
    case 171:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprb2small);
        imageViewRB[paramInt].setImageResource(R.drawable.gprb2small);
        imageViewRT[paramInt].setImageResource(R.drawable.gprb2small);
        imageViewLB[paramInt].setImageResource(R.drawable.gprb2small);
        imageViewLT[paramInt].setImageResource(R.drawable.gprb2small);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr12small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr12small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr12small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr12small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr12small);
      return;
    case 170:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplb2small);
        imageViewRB[paramInt].setImageResource(R.drawable.gplb2small);
        imageViewRT[paramInt].setImageResource(R.drawable.gplb2small);
        imageViewLB[paramInt].setImageResource(R.drawable.gplb2small);
        imageViewLT[paramInt].setImageResource(R.drawable.gplb2small);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl12small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl12small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl12small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl12small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl12small);
      return;
    case 169:
      imageView[paramInt].setImageResource(R.drawable.gpy2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpy2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpy2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpy2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpy2small);
      return;
    case 168:
      imageView[paramInt].setImageResource(R.drawable.gpx2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpx2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpx2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpx2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpx2small);
      return;
    case 167:
      imageView[paramInt].setImageResource(R.drawable.gpbtn2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbtn2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbtn2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbtn2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbtn2small);
      return;
    case 166:
      imageView[paramInt].setImageResource(R.drawable.gpa2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpa2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpa2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpa2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpa2small);
      return;
    case 156:
    case 157:
    case 158:
    case 159:
    case 160:
    case 161:
    case 162:
    case 163:
    case 164:
    case 165:
      imageView[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.newkeysmall);
      return;
    case 155:
      imageView[paramInt].setImageResource(R.drawable.gpm41small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm41small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm41small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm41small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm41small);
      return;
    case 154:
      imageView[paramInt].setImageResource(R.drawable.gpm31small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm31small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm31small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm31small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm31small);
      return;
    case 153:
      imageView[paramInt].setImageResource(R.drawable.gpm21small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm21small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm21small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm21small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm21small);
      return;
    case 152:
      imageView[paramInt].setImageResource(R.drawable.gpm11small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm11small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm11small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm11small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm11small);
      return;
    case 151:
      imageView[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbprsmall);
      return;
    case 150:
      imageView[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbplsmall);
      return;
    case 149:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprt1small);
        imageViewRB[paramInt].setImageResource(R.drawable.gprt1small);
        imageViewRT[paramInt].setImageResource(R.drawable.gprt1small);
        imageViewLB[paramInt].setImageResource(R.drawable.gprt1small);
        imageViewLT[paramInt].setImageResource(R.drawable.gprt1small);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr21small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr21small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr21small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr21small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr21small);
      return;
    case 148:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplt1small);
        imageViewRB[paramInt].setImageResource(R.drawable.gplt1small);
        imageViewRT[paramInt].setImageResource(R.drawable.gplt1small);
        imageViewLB[paramInt].setImageResource(R.drawable.gplt1small);
        imageViewLT[paramInt].setImageResource(R.drawable.gplt1small);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl21small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl21small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl21small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl21small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl21small);
      return;
    case 147:
      imageView[paramInt].setImageResource(R.drawable.gpr31small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr31small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr31small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr31small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr31small);
      return;
    case 146:
      imageView[paramInt].setImageResource(R.drawable.gpl31small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl31small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl31small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl31small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl31small);
      return;
    case 145:
      imageView[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselectsmall);
      return;
    case 144:
      imageView[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstartsmall);
      return;
    case 143:
      imageView[paramInt].setImageResource(R.drawable.gpright1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpright1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpright1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpright1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpright1small);
      return;
    case 142:
      imageView[paramInt].setImageResource(R.drawable.gpleft1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleft1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleft1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleft1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleft1small);
      return;
    case 141:
      imageView[paramInt].setImageResource(R.drawable.gpdown1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdown1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdown1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdown1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdown1small);
      return;
    case 140:
      imageView[paramInt].setImageResource(R.drawable.gpup1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpup1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpup1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpup1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpup1small);
      return;
    case 139:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprb1small);
        imageViewRB[paramInt].setImageResource(R.drawable.gprb1small);
        imageViewRT[paramInt].setImageResource(R.drawable.gprb1small);
        imageViewLB[paramInt].setImageResource(R.drawable.gprb1small);
        imageViewLT[paramInt].setImageResource(R.drawable.gprb1small);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr11small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr11small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr11small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr11small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr11small);
      return;
    case 138:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplb1small);
        imageViewRB[paramInt].setImageResource(R.drawable.gplb1small);
        imageViewRT[paramInt].setImageResource(R.drawable.gplb1small);
        imageViewLB[paramInt].setImageResource(R.drawable.gplb1small);
        imageViewLT[paramInt].setImageResource(R.drawable.gplb1small);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl11small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl11small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl11small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl11small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl11small);
      return;
    case 137:
      imageView[paramInt].setImageResource(R.drawable.gpy1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpy1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpy1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpy1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpy1small);
      return;
    case 136:
      imageView[paramInt].setImageResource(R.drawable.gpx1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpx1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpx1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpx1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpx1small);
      return;
    case 135:
      imageView[paramInt].setImageResource(R.drawable.gpbtn1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbtn1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbtn1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbtn1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbtn1small);
      return;
    case 134:
      imageView[paramInt].setImageResource(R.drawable.gpa1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpa1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpa1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpa1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpa1small);
      return;
    case 124:
    case 125:
    case 126:
    case 127:
    case 128:
    case 129:
    case 130:
    case 131:
    case 132:
    case 133:
      imageView[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.newkeysmall);
      return;
    case 123:
      imageView[paramInt].setImageResource(R.drawable.gpm4lrsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4lrsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4lrsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4lrsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4lrsmall);
      return;
    case 122:
      imageView[paramInt].setImageResource(R.drawable.gpm3lrsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3lrsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3lrsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3lrsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3lrsmall);
      return;
    case 121:
      imageView[paramInt].setImageResource(R.drawable.gpm2lrsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2lrsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2lrsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2lrsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2lrsmall);
      return;
    case 120:
      imageView[paramInt].setImageResource(R.drawable.gpm1lrsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1lrsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1lrsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1lrsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1lrsmall);
      return;
    case 119:
      imageView[paramInt].setImageResource(R.drawable.gpalrsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpalrsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpalrsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpalrsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpalrsmall);
      return;
    case 118:
      imageView[paramInt].setImageResource(R.drawable.gpalrsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpalrsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpalrsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpalrsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpalrsmall);
      return;
    case 117:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprtlrsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprtlrsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprtlrsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprtlrsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprtlrsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2lrsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2lrsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2lrsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2lrsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2lrsmall);
      return;
    case 116:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpltlrsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpltlrsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpltlrsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpltlrsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpltlrsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2lrsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2lrsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2lrsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2lrsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2lrsmall);
      return;
    case 115:
      imageView[paramInt].setImageResource(R.drawable.gpr3lrsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3lrsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3lrsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3lrsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3lrsmall);
      return;
    case 114:
      imageView[paramInt].setImageResource(R.drawable.gpl3lrsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3lrsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3lrsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3lrsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3lrsmall);
      return;
    case 113:
      imageView[paramInt].setImageResource(R.drawable.gpalrsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpalrsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpalrsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpalrsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpalrsmall);
      return;
    case 112:
      imageView[paramInt].setImageResource(R.drawable.gpalrsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpalrsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpalrsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpalrsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpalrsmall);
      return;
    case 111:
      imageView[paramInt].setImageResource(R.drawable.gpalrsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpalrsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpalrsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpalrsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpalrsmall);
      return;
    case 110:
      imageView[paramInt].setImageResource(R.drawable.gpalrsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpalrsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpalrsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpalrsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpalrsmall);
      return;
    case 109:
      imageView[paramInt].setImageResource(R.drawable.gpalrsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpalrsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpalrsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpalrsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpalrsmall);
      return;
    case 108:
      imageView[paramInt].setImageResource(R.drawable.gpalrsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpalrsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpalrsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpalrsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpalrsmall);
      return;
    case 107:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprblrsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprblrsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprblrsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprblrsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprblrsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1lrsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1lrsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1lrsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1lrsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1lrsmall);
      return;
    case 106:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplblrsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gplblrsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gplblrsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gplblrsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gplblrsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1lrsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1lrsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1lrsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1lrsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1lrsmall);
      return;
    case 105:
      imageView[paramInt].setImageResource(R.drawable.gpylrsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpylrsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpylrsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpylrsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpylrsmall);
      return;
    case 104:
      imageView[paramInt].setImageResource(R.drawable.gpxlrsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxlrsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxlrsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxlrsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxlrsmall);
      return;
    case 103:
      imageView[paramInt].setImageResource(R.drawable.gpblrsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpblrsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpblrsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpblrsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpblrsmall);
      return;
    case 102:
      imageView[paramInt].setImageResource(R.drawable.gpalrsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpalrsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpalrsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpalrsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpalrsmall);
      return;
    case 92:
    case 93:
    case 94:
    case 95:
    case 96:
    case 97:
    case 98:
    case 99:
    case 100:
    case 101:
      imageView[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.newkeysmall);
      return;
    case 91:
      imageView[paramInt].setImageResource(R.drawable.gpm4bomsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4bomsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4bomsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4bomsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4bomsmall);
      return;
    case 90:
      imageView[paramInt].setImageResource(R.drawable.gpm3bomsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3bomsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3bomsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3bomsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3bomsmall);
      return;
    case 89:
      imageView[paramInt].setImageResource(R.drawable.gpm2bomsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2bomsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2bomsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2bomsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2bomsmall);
      return;
    case 88:
      imageView[paramInt].setImageResource(R.drawable.gpm1bomsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1bomsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1bomsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1bomsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1bomsmall);
      return;
    case 87:
      imageView[paramInt].setImageResource(R.drawable.gparightsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gparightsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gparightsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gparightsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gparightsmall);
      return;
    case 86:
      imageView[paramInt].setImageResource(R.drawable.gparightsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gparightsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gparightsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gparightsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gparightsmall);
      return;
    case 85:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprtbomsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprtbomsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprtbomsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprtbomsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprtbomsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2bomsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2bomsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2bomsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2bomsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2bomsmall);
      return;
    case 84:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpltbomsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpltbomsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpltbomsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpltbomsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpltbomsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2bomsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2bomsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2bomsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2bomsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2bomsmall);
      return;
    case 83:
      imageView[paramInt].setImageResource(R.drawable.gpr3bomsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3bomsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3bomsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3bomsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3bomsmall);
      return;
    case 82:
      imageView[paramInt].setImageResource(R.drawable.gpl3bomsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3bomsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3bomsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3bomsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3bomsmall);
      return;
    case 81:
      imageView[paramInt].setImageResource(R.drawable.gparightsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gparightsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gparightsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gparightsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gparightsmall);
      return;
    case 80:
      imageView[paramInt].setImageResource(R.drawable.gparightsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gparightsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gparightsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gparightsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gparightsmall);
      return;
    case 79:
      imageView[paramInt].setImageResource(R.drawable.gparightsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gparightsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gparightsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gparightsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gparightsmall);
      return;
    case 78:
      imageView[paramInt].setImageResource(R.drawable.gparightsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gparightsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gparightsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gparightsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gparightsmall);
      return;
    case 77:
      imageView[paramInt].setImageResource(R.drawable.gparightsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gparightsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gparightsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gparightsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gparightsmall);
      return;
    case 76:
      imageView[paramInt].setImageResource(R.drawable.gparightsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gparightsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gparightsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gparightsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gparightsmall);
      return;
    case 75:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprbbomsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprbbomsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprbbomsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprbbomsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprbbomsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1bomsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1bomsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1bomsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1bomsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1bomsmall);
      return;
    case 74:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplbbomsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gplbbomsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gplbbomsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gplbbomsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gplbbomsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1bomsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1bomsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1bomsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1bomsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1bomsmall);
      return;
    case 73:
      imageView[paramInt].setImageResource(R.drawable.gpyrightsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpyrightsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpyrightsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpyrightsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpyrightsmall);
      return;
    case 72:
      imageView[paramInt].setImageResource(R.drawable.gpxrightsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxrightsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxrightsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxrightsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxrightsmall);
      return;
    case 71:
      imageView[paramInt].setImageResource(R.drawable.gpbright1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbright1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbright1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbright1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbright1small);
      return;
    case 70:
      imageView[paramInt].setImageResource(R.drawable.gparightsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gparightsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gparightsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gparightsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gparightsmall);
      return;
    case 60:
    case 61:
    case 62:
    case 63:
    case 64:
    case 65:
    case 66:
    case 67:
    case 68:
    case 69:
      imageView[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.newkeysmall);
      return;
    case 59:
      imageView[paramInt].setImageResource(R.drawable.gpm4leftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4leftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4leftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4leftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4leftsmall);
      return;
    case 58:
      imageView[paramInt].setImageResource(R.drawable.gpm3leftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3leftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3leftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3leftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3leftsmall);
      return;
    case 57:
      imageView[paramInt].setImageResource(R.drawable.gpm2leftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2leftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2leftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2leftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2leftsmall);
      return;
    case 56:
      imageView[paramInt].setImageResource(R.drawable.gpm1leftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1leftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1leftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1leftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1leftsmall);
      return;
    case 55:
      imageView[paramInt].setImageResource(R.drawable.gpaleftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpaleftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpaleftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpaleftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpaleftsmall);
      return;
    case 54:
      imageView[paramInt].setImageResource(R.drawable.gpaleftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpaleftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpaleftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpaleftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpaleftsmall);
      return;
    case 53:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprtleftsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprtleftsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprtleftsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprtleftsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprtleftsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2leftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2leftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2leftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2leftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2leftsmall);
      return;
    case 52:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpltleftsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpltleftsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpltleftsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpltleftsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpltleftsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2leftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2leftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2leftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2leftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2leftsmall);
      return;
    case 51:
      imageView[paramInt].setImageResource(R.drawable.gpr3leftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3leftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3leftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3leftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3leftsmall);
      return;
    case 49:
      imageView[paramInt].setImageResource(R.drawable.gpaleftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpaleftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpaleftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpaleftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpaleftsmall);
    case 50:
      imageView[paramInt].setImageResource(R.drawable.gpl3leftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3leftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3leftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3leftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3leftsmall);
      return;
    case 48:
      imageView[paramInt].setImageResource(R.drawable.gpaleftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpaleftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpaleftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpaleftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpaleftsmall);
      return;
    case 47:
      imageView[paramInt].setImageResource(R.drawable.gpaleftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpaleftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpaleftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpaleftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpaleftsmall);
      return;
    case 46:
      imageView[paramInt].setImageResource(R.drawable.gpaleftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpaleftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpaleftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpaleftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpaleftsmall);
      return;
    case 45:
      imageView[paramInt].setImageResource(R.drawable.gpaleftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpaleftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpaleftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpaleftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpaleftsmall);
      return;
    case 44:
      imageView[paramInt].setImageResource(R.drawable.gpaleftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpaleftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpaleftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpaleftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpaleftsmall);
      return;
    case 43:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprbleftsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprbleftsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprbleftsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprbleftsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprbleftsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1leftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1leftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1leftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1leftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1leftsmall);
      return;
    case 42:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplbleftsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gplbleftsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gplbleftsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gplbleftsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gplbleftsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1leftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1leftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1leftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1leftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1leftsmall);
      return;
    case 41:
      imageView[paramInt].setImageResource(R.drawable.gpyleftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpyleftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpyleftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpyleftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpyleftsmall);
      return;
    case 40:
      imageView[paramInt].setImageResource(R.drawable.gpxleftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxleftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxleftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxleftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxleftsmall);
      return;
    case 39:
      imageView[paramInt].setImageResource(R.drawable.gpbleft1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbleft1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbleft1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbleft1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbleft1small);
      return;
    case 38:
      imageView[paramInt].setImageResource(R.drawable.gpaleftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpaleftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpaleftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpaleftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpaleftsmall);
      return;
    case 37:
      imageView[paramInt].setImageResource(R.drawable.gpsensorsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpsensorsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpsensorsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpsensorsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpsensorsmall);
      return;
    case 36:
      imageView[paramInt].setImageResource(R.drawable.gptouchsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gptouchsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gptouchsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gptouchsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gptouchsmall);
      return;
    case 35:
      imageView[paramInt].setImageResource(R.drawable.gpysmall_add);
      imageViewRB[paramInt].setImageResource(R.drawable.gpysmall_add);
      imageViewRT[paramInt].setImageResource(R.drawable.gpysmall_add);
      imageViewLB[paramInt].setImageResource(R.drawable.gpysmall_add);
      imageViewLT[paramInt].setImageResource(R.drawable.gpysmall_add);
      return;
    case 34:
      imageView[paramInt].setImageResource(R.drawable.gpxsmall_add);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxsmall_add);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxsmall_add);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxsmall_add);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxsmall_add);
      return;
    case 33:
      imageView[paramInt].setImageResource(R.drawable.gpbsmall_add);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbsmall_add);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbsmall_add);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbsmall_add);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbsmall_add);
      return;
    case 32:
      imageView[paramInt].setImageResource(R.drawable.gpasmall_add);
      imageViewRB[paramInt].setImageResource(R.drawable.gpasmall_add);
      imageViewRT[paramInt].setImageResource(R.drawable.gpasmall_add);
      imageViewLB[paramInt].setImageResource(R.drawable.gpasmall_add);
      imageViewLT[paramInt].setImageResource(R.drawable.gpasmall_add);
      return;
    case 27:
    case 28:
    case 29:
    case 30:
    case 31:
      imageView[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.newkeysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.newkeysmall);
      return;
    case 26:
      imageView[paramInt].setImageResource(R.drawable.center3small);
      imageViewRB[paramInt].setImageResource(R.drawable.center3small);
      imageViewRT[paramInt].setImageResource(R.drawable.center3small);
      imageViewLB[paramInt].setImageResource(R.drawable.center3small);
      imageViewLT[paramInt].setImageResource(R.drawable.center3small);
      return;
    case 25:
      imageView[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr2small);
      return;
    case 24:
      imageView[paramInt].setImageResource(R.drawable.center2small);
      imageViewRB[paramInt].setImageResource(R.drawable.center2small);
      imageViewRT[paramInt].setImageResource(R.drawable.center2small);
      imageViewLB[paramInt].setImageResource(R.drawable.center2small);
      imageViewLT[paramInt].setImageResource(R.drawable.center2small);
      return;
    case 23:
      imageView[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr1small);
      return;
    case 22:
      imageView[paramInt].setImageResource(R.drawable.center1small);
      imageViewRB[paramInt].setImageResource(R.drawable.center1small);
      imageViewRT[paramInt].setImageResource(R.drawable.center1small);
      imageViewLB[paramInt].setImageResource(R.drawable.center1small);
      imageViewLT[paramInt].setImageResource(R.drawable.center1small);
      return;
    case 21:
      imageView[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4small);
      return;
    case 20:
      imageView[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3small);
      return;
    case 19:
      imageView[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2small);
      return;
    case 18:
      imageView[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1small);
      return;
    case 17:
      imageView[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbprsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbprsmall);
      return;
    case 16:
      imageView[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbplsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbplsmall);
      return;
    case 15:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprtsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprtsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2small);
      return;
    case 14:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gpltsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gpltsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2small);
      return;
    case 13:
      imageView[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3small);
      return;
    case 12:
      imageView[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3small);
      return;
    case 11:
      imageView[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselectsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselectsmall);
      return;
    case 10:
      imageView[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstartsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstartsmall);
      return;
    case 9:
      imageView[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gprightsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gprightsmall);
      return;
    case 8:
      imageView[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleftsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleftsmall);
      return;
    case 7:
      imageView[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdownsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdownsmall);
      return;
    case 6:
      imageView[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpupsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpupsmall);
      return;
    case 5:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gprbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gprbsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1small);
      return;
    case 4:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewRB[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewRT[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewLB[paramInt].setImageResource(R.drawable.gplbsmall);
        imageViewLT[paramInt].setImageResource(R.drawable.gplbsmall);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1small);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1small);
      return;
    case 3:
      imageView[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpysmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpysmall);
      return;
    case 2:
      imageView[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxsmall);
      return;
    case 1:
      imageView[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbsmall);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbsmall);
      return;
    case 0:
    }
    imageView[paramInt].setImageResource(R.drawable.gpasmall);
    imageViewRB[paramInt].setImageResource(R.drawable.gpasmall);
    imageViewRT[paramInt].setImageResource(R.drawable.gpasmall);
    imageViewLB[paramInt].setImageResource(R.drawable.gpasmall);
    imageViewLT[paramInt].setImageResource(R.drawable.gpasmall);
  }
}
