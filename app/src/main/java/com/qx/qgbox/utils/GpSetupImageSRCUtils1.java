package com.qx.qgbox.utils;

import android.content.Context;
import android.widget.ImageView.ScaleType;
import com.qx.qgbox.service.bluetoothdevmanager;
import com.qx.qgbox.views.NumImageView;

public class GpSetupImageSRCUtils1
{
  private static NumImageView[] imageView = new NumImageView[length];
  private static NumImageView[] imageViewLB = new NumImageView[length];
  private static NumImageView[] imageViewLT = new NumImageView[length];
  private static NumImageView[] imageViewRB = new NumImageView[length];
  private static NumImageView[] imageViewRT = new NumImageView[length];
  static int length = 966;
  private Context context;

  public GpSetupImageSRCUtils1(Context paramContext)
  {
    this.context = paramContext;
    init();
  }

  static void setuppic(int paramInt)
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
      imageView[paramInt].setImageResource(R.drawable.newkey);
      imageViewRB[paramInt].setImageResource(R.drawable.newkey);
      imageViewRT[paramInt].setImageResource(R.drawable.newkey);
      imageViewLB[paramInt].setImageResource(R.drawable.newkey);
      imageViewLT[paramInt].setImageResource(R.drawable.newkey);
      return;
    case 960:
      imageView[paramInt].setImageResource(R.drawable.center3);
      imageViewRB[paramInt].setImageResource(R.drawable.center3);
      imageViewRT[paramInt].setImageResource(R.drawable.center3);
      imageViewLB[paramInt].setImageResource(R.drawable.center3);
      imageViewLT[paramInt].setImageResource(R.drawable.center3);
      return;
    case 959:
      imageView[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr2);
      return;
    case 958:
      imageView[paramInt].setImageResource(R.drawable.center2);
      imageViewRB[paramInt].setImageResource(R.drawable.center2);
      imageViewRT[paramInt].setImageResource(R.drawable.center2);
      imageViewLB[paramInt].setImageResource(R.drawable.center2);
      imageViewLT[paramInt].setImageResource(R.drawable.center2);
      return;
    case 957:
      imageView[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr1);
      return;
    case 956:
      imageView[paramInt].setImageResource(R.drawable.center1);
      imageViewRB[paramInt].setImageResource(R.drawable.center1);
      imageViewRT[paramInt].setImageResource(R.drawable.center1);
      imageViewLB[paramInt].setImageResource(R.drawable.center1);
      imageViewLT[paramInt].setImageResource(R.drawable.center1);
      return;
    case 955:
      imageView[paramInt].setImageResource(R.drawable.gpm4);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4);
      return;
    case 954:
      imageView[paramInt].setImageResource(R.drawable.gpm3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3);
      return;
    case 953:
      imageView[paramInt].setImageResource(R.drawable.gpm2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2);
      return;
    case 952:
      imageView[paramInt].setImageResource(R.drawable.gpm1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1);
      return;
    case 951:
      imageView[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr);
      return;
    case 950:
      imageView[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpl);
      return;
    case 949:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprt);
        imageViewRB[paramInt].setImageResource(R.drawable.gprt);
        imageViewRT[paramInt].setImageResource(R.drawable.gprt);
        imageViewLB[paramInt].setImageResource(R.drawable.gprt);
        imageViewLT[paramInt].setImageResource(R.drawable.gprt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2);
      return;
    case 948:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplt);
        imageViewRB[paramInt].setImageResource(R.drawable.gplt);
        imageViewRT[paramInt].setImageResource(R.drawable.gplt);
        imageViewLB[paramInt].setImageResource(R.drawable.gplt);
        imageViewLT[paramInt].setImageResource(R.drawable.gplt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2);
      return;
    case 947:
      imageView[paramInt].setImageResource(R.drawable.gpr3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3);
      return;
    case 946:
      imageView[paramInt].setImageResource(R.drawable.gpl3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3);
      return;
    case 945:
      imageView[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselect);
      return;
    case 944:
      imageView[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstart);
      return;
    case 943:
      imageView[paramInt].setImageResource(R.drawable.gpright);
      imageViewRB[paramInt].setImageResource(R.drawable.gpright);
      imageViewRT[paramInt].setImageResource(R.drawable.gpright);
      imageViewLB[paramInt].setImageResource(R.drawable.gpright);
      imageViewLT[paramInt].setImageResource(R.drawable.gpright);
      return;
    case 942:
      imageView[paramInt].setImageResource(R.drawable.gpleft);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleft);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleft);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleft);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleft);
      return;
    case 941:
      imageView[paramInt].setImageResource(R.drawable.gpdown);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdown);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdown);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdown);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdown);
      return;
    case 940:
      imageView[paramInt].setImageResource(R.drawable.gpup);
      imageViewRB[paramInt].setImageResource(R.drawable.gpup);
      imageViewRT[paramInt].setImageResource(R.drawable.gpup);
      imageViewLB[paramInt].setImageResource(R.drawable.gpup);
      imageViewLT[paramInt].setImageResource(R.drawable.gpup);
      return;
    case 939:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprb);
        imageViewRB[paramInt].setImageResource(R.drawable.gprb);
        imageViewRT[paramInt].setImageResource(R.drawable.gprb);
        imageViewLB[paramInt].setImageResource(R.drawable.gprb);
        imageViewLT[paramInt].setImageResource(R.drawable.gprb);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1);
      return;
    case 938:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplb);
        imageViewRB[paramInt].setImageResource(R.drawable.gplb);
        imageViewRT[paramInt].setImageResource(R.drawable.gplb);
        imageViewLB[paramInt].setImageResource(R.drawable.gplb);
        imageViewLT[paramInt].setImageResource(R.drawable.gplb);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1);
      return;
    case 937:
      imageView[paramInt].setImageResource(R.drawable.gpy);
      imageViewRB[paramInt].setImageResource(R.drawable.gpy);
      imageViewRT[paramInt].setImageResource(R.drawable.gpy);
      imageViewLB[paramInt].setImageResource(R.drawable.gpy);
      imageViewLT[paramInt].setImageResource(R.drawable.gpy);
      return;
    case 936:
      imageView[paramInt].setImageResource(R.drawable.gpx);
      imageViewRB[paramInt].setImageResource(R.drawable.gpx);
      imageViewRT[paramInt].setImageResource(R.drawable.gpx);
      imageViewLB[paramInt].setImageResource(R.drawable.gpx);
      imageViewLT[paramInt].setImageResource(R.drawable.gpx);
      return;
    case 935:
      imageView[paramInt].setImageResource(R.drawable.gpb);
      imageViewRB[paramInt].setImageResource(R.drawable.gpb);
      imageViewRT[paramInt].setImageResource(R.drawable.gpb);
      imageViewLB[paramInt].setImageResource(R.drawable.gpb);
      imageViewLT[paramInt].setImageResource(R.drawable.gpb);
      return;
    case 934:
      imageView[paramInt].setImageResource(R.drawable.gpa);
      imageViewRB[paramInt].setImageResource(R.drawable.gpa);
      imageViewRT[paramInt].setImageResource(R.drawable.gpa);
      imageViewLB[paramInt].setImageResource(R.drawable.gpa);
      imageViewLT[paramInt].setImageResource(R.drawable.gpa);
      return;
    case 929:
    case 930:
    case 931:
    case 932:
    case 933:
      imageView[paramInt].setImageResource(R.drawable.newkey);
      imageViewRB[paramInt].setImageResource(R.drawable.newkey);
      imageViewRT[paramInt].setImageResource(R.drawable.newkey);
      imageViewLB[paramInt].setImageResource(R.drawable.newkey);
      imageViewLT[paramInt].setImageResource(R.drawable.newkey);
      return;
    case 928:
      imageView[paramInt].setImageResource(R.drawable.center3);
      imageViewRB[paramInt].setImageResource(R.drawable.center3);
      imageViewRT[paramInt].setImageResource(R.drawable.center3);
      imageViewLB[paramInt].setImageResource(R.drawable.center3);
      imageViewLT[paramInt].setImageResource(R.drawable.center3);
      return;
    case 927:
      imageView[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr2);
      return;
    case 926:
      imageView[paramInt].setImageResource(R.drawable.center2);
      imageViewRB[paramInt].setImageResource(R.drawable.center2);
      imageViewRT[paramInt].setImageResource(R.drawable.center2);
      imageViewLB[paramInt].setImageResource(R.drawable.center2);
      imageViewLT[paramInt].setImageResource(R.drawable.center2);
      return;
    case 925:
      imageView[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr1);
      return;
    case 924:
      imageView[paramInt].setImageResource(R.drawable.center1);
      imageViewRB[paramInt].setImageResource(R.drawable.center1);
      imageViewRT[paramInt].setImageResource(R.drawable.center1);
      imageViewLB[paramInt].setImageResource(R.drawable.center1);
      imageViewLT[paramInt].setImageResource(R.drawable.center1);
      return;
    case 923:
      imageView[paramInt].setImageResource(R.drawable.gpm4);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4);
      return;
    case 922:
      imageView[paramInt].setImageResource(R.drawable.gpm3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3);
      return;
    case 921:
      imageView[paramInt].setImageResource(R.drawable.gpm2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2);
      return;
    case 920:
      imageView[paramInt].setImageResource(R.drawable.gpm1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1);
      return;
    case 919:
      imageView[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr);
      return;
    case 918:
      imageView[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpl);
      return;
    case 917:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprt);
        imageViewRB[paramInt].setImageResource(R.drawable.gprt);
        imageViewRT[paramInt].setImageResource(R.drawable.gprt);
        imageViewLB[paramInt].setImageResource(R.drawable.gprt);
        imageViewLT[paramInt].setImageResource(R.drawable.gprt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2);
      return;
    case 916:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplt);
        imageViewRB[paramInt].setImageResource(R.drawable.gplt);
        imageViewRT[paramInt].setImageResource(R.drawable.gplt);
        imageViewLB[paramInt].setImageResource(R.drawable.gplt);
        imageViewLT[paramInt].setImageResource(R.drawable.gplt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2);
      return;
    case 915:
      imageView[paramInt].setImageResource(R.drawable.gpr3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3);
      return;
    case 914:
      imageView[paramInt].setImageResource(R.drawable.gpl3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3);
      return;
    case 913:
      imageView[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselect);
      return;
    case 912:
      imageView[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstart);
      return;
    case 911:
      imageView[paramInt].setImageResource(R.drawable.gpright);
      imageViewRB[paramInt].setImageResource(R.drawable.gpright);
      imageViewRT[paramInt].setImageResource(R.drawable.gpright);
      imageViewLB[paramInt].setImageResource(R.drawable.gpright);
      imageViewLT[paramInt].setImageResource(R.drawable.gpright);
      return;
    case 910:
      imageView[paramInt].setImageResource(R.drawable.gpleft);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleft);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleft);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleft);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleft);
      return;
    case 909:
      imageView[paramInt].setImageResource(R.drawable.gpdown);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdown);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdown);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdown);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdown);
      return;
    case 908:
      imageView[paramInt].setImageResource(R.drawable.gpup);
      imageViewRB[paramInt].setImageResource(R.drawable.gpup);
      imageViewRT[paramInt].setImageResource(R.drawable.gpup);
      imageViewLB[paramInt].setImageResource(R.drawable.gpup);
      imageViewLT[paramInt].setImageResource(R.drawable.gpup);
      return;
    case 907:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprb);
        imageViewRB[paramInt].setImageResource(R.drawable.gprb);
        imageViewRT[paramInt].setImageResource(R.drawable.gprb);
        imageViewLB[paramInt].setImageResource(R.drawable.gprb);
        imageViewLT[paramInt].setImageResource(R.drawable.gprb);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1);
      return;
    case 906:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplb);
        imageViewRB[paramInt].setImageResource(R.drawable.gplb);
        imageViewRT[paramInt].setImageResource(R.drawable.gplb);
        imageViewLB[paramInt].setImageResource(R.drawable.gplb);
        imageViewLT[paramInt].setImageResource(R.drawable.gplb);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1);
      return;
    case 905:
      imageView[paramInt].setImageResource(R.drawable.gpy);
      imageViewRB[paramInt].setImageResource(R.drawable.gpy);
      imageViewRT[paramInt].setImageResource(R.drawable.gpy);
      imageViewLB[paramInt].setImageResource(R.drawable.gpy);
      imageViewLT[paramInt].setImageResource(R.drawable.gpy);
      return;
    case 904:
      imageView[paramInt].setImageResource(R.drawable.gpx);
      imageViewRB[paramInt].setImageResource(R.drawable.gpx);
      imageViewRT[paramInt].setImageResource(R.drawable.gpx);
      imageViewLB[paramInt].setImageResource(R.drawable.gpx);
      imageViewLT[paramInt].setImageResource(R.drawable.gpx);
      return;
    case 903:
      imageView[paramInt].setImageResource(R.drawable.gpb);
      imageViewRB[paramInt].setImageResource(R.drawable.gpb);
      imageViewRT[paramInt].setImageResource(R.drawable.gpb);
      imageViewLB[paramInt].setImageResource(R.drawable.gpb);
      imageViewLT[paramInt].setImageResource(R.drawable.gpb);
      return;
    case 902:
      imageView[paramInt].setImageResource(R.drawable.gpa);
      imageViewRB[paramInt].setImageResource(R.drawable.gpa);
      imageViewRT[paramInt].setImageResource(R.drawable.gpa);
      imageViewLB[paramInt].setImageResource(R.drawable.gpa);
      imageViewLT[paramInt].setImageResource(R.drawable.gpa);
      return;
    case 897:
    case 898:
    case 899:
    case 900:
    case 901:
      imageView[paramInt].setImageResource(R.drawable.newkey);
      imageViewRB[paramInt].setImageResource(R.drawable.newkey);
      imageViewRT[paramInt].setImageResource(R.drawable.newkey);
      imageViewLB[paramInt].setImageResource(R.drawable.newkey);
      imageViewLT[paramInt].setImageResource(R.drawable.newkey);
      return;
    case 896:
      imageView[paramInt].setImageResource(R.drawable.center3);
      imageViewRB[paramInt].setImageResource(R.drawable.center3);
      imageViewRT[paramInt].setImageResource(R.drawable.center3);
      imageViewLB[paramInt].setImageResource(R.drawable.center3);
      imageViewLT[paramInt].setImageResource(R.drawable.center3);
      return;
    case 895:
      imageView[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr2);
      return;
    case 894:
      imageView[paramInt].setImageResource(R.drawable.center2);
      imageViewRB[paramInt].setImageResource(R.drawable.center2);
      imageViewRT[paramInt].setImageResource(R.drawable.center2);
      imageViewLB[paramInt].setImageResource(R.drawable.center2);
      imageViewLT[paramInt].setImageResource(R.drawable.center2);
      return;
    case 893:
      imageView[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr1);
      return;
    case 892:
      imageView[paramInt].setImageResource(R.drawable.center1);
      imageViewRB[paramInt].setImageResource(R.drawable.center1);
      imageViewRT[paramInt].setImageResource(R.drawable.center1);
      imageViewLB[paramInt].setImageResource(R.drawable.center1);
      imageViewLT[paramInt].setImageResource(R.drawable.center1);
      return;
    case 891:
      imageView[paramInt].setImageResource(R.drawable.gpm4);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4);
      return;
    case 890:
      imageView[paramInt].setImageResource(R.drawable.gpm3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3);
      return;
    case 889:
      imageView[paramInt].setImageResource(R.drawable.gpm2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2);
      return;
    case 888:
      imageView[paramInt].setImageResource(R.drawable.gpm1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1);
      return;
    case 887:
      imageView[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr);
      return;
    case 886:
      imageView[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpl);
      return;
    case 885:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprt);
        imageViewRB[paramInt].setImageResource(R.drawable.gprt);
        imageViewRT[paramInt].setImageResource(R.drawable.gprt);
        imageViewLB[paramInt].setImageResource(R.drawable.gprt);
        imageViewLT[paramInt].setImageResource(R.drawable.gprt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2);
      return;
    case 884:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplt);
        imageViewRB[paramInt].setImageResource(R.drawable.gplt);
        imageViewRT[paramInt].setImageResource(R.drawable.gplt);
        imageViewLB[paramInt].setImageResource(R.drawable.gplt);
        imageViewLT[paramInt].setImageResource(R.drawable.gplt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2);
      return;
    case 883:
      imageView[paramInt].setImageResource(R.drawable.gpr3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3);
      return;
    case 882:
      imageView[paramInt].setImageResource(R.drawable.gpl3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3);
      return;
    case 881:
      imageView[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselect);
      return;
    case 880:
      imageView[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstart);
      return;
    case 879:
      imageView[paramInt].setImageResource(R.drawable.gpright);
      imageViewRB[paramInt].setImageResource(R.drawable.gpright);
      imageViewRT[paramInt].setImageResource(R.drawable.gpright);
      imageViewLB[paramInt].setImageResource(R.drawable.gpright);
      imageViewLT[paramInt].setImageResource(R.drawable.gpright);
      return;
    case 878:
      imageView[paramInt].setImageResource(R.drawable.gpleft);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleft);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleft);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleft);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleft);
      return;
    case 877:
      imageView[paramInt].setImageResource(R.drawable.gpdown);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdown);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdown);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdown);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdown);
      return;
    case 876:
      imageView[paramInt].setImageResource(R.drawable.gpup);
      imageViewRB[paramInt].setImageResource(R.drawable.gpup);
      imageViewRT[paramInt].setImageResource(R.drawable.gpup);
      imageViewLB[paramInt].setImageResource(R.drawable.gpup);
      imageViewLT[paramInt].setImageResource(R.drawable.gpup);
      return;
    case 875:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprb);
        imageViewRB[paramInt].setImageResource(R.drawable.gprb);
        imageViewRT[paramInt].setImageResource(R.drawable.gprb);
        imageViewLB[paramInt].setImageResource(R.drawable.gprb);
        imageViewLT[paramInt].setImageResource(R.drawable.gprb);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1);
      return;
    case 874:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplb);
        imageViewRB[paramInt].setImageResource(R.drawable.gplb);
        imageViewRT[paramInt].setImageResource(R.drawable.gplb);
        imageViewLB[paramInt].setImageResource(R.drawable.gplb);
        imageViewLT[paramInt].setImageResource(R.drawable.gplb);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1);
      return;
    case 873:
      imageView[paramInt].setImageResource(R.drawable.gpy);
      imageViewRB[paramInt].setImageResource(R.drawable.gpy);
      imageViewRT[paramInt].setImageResource(R.drawable.gpy);
      imageViewLB[paramInt].setImageResource(R.drawable.gpy);
      imageViewLT[paramInt].setImageResource(R.drawable.gpy);
      return;
    case 872:
      imageView[paramInt].setImageResource(R.drawable.gpx);
      imageViewRB[paramInt].setImageResource(R.drawable.gpx);
      imageViewRT[paramInt].setImageResource(R.drawable.gpx);
      imageViewLB[paramInt].setImageResource(R.drawable.gpx);
      imageViewLT[paramInt].setImageResource(R.drawable.gpx);
      return;
    case 871:
      imageView[paramInt].setImageResource(R.drawable.gpb);
      imageViewRB[paramInt].setImageResource(R.drawable.gpb);
      imageViewRT[paramInt].setImageResource(R.drawable.gpb);
      imageViewLB[paramInt].setImageResource(R.drawable.gpb);
      imageViewLT[paramInt].setImageResource(R.drawable.gpb);
      return;
    case 870:
      imageView[paramInt].setImageResource(R.drawable.gpa);
      imageViewRB[paramInt].setImageResource(R.drawable.gpa);
      imageViewRT[paramInt].setImageResource(R.drawable.gpa);
      imageViewLB[paramInt].setImageResource(R.drawable.gpa);
      imageViewLT[paramInt].setImageResource(R.drawable.gpa);
      return;
    case 865:
    case 866:
    case 867:
    case 868:
    case 869:
      imageView[paramInt].setImageResource(R.drawable.newkey);
      imageViewRB[paramInt].setImageResource(R.drawable.newkey);
      imageViewRT[paramInt].setImageResource(R.drawable.newkey);
      imageViewLB[paramInt].setImageResource(R.drawable.newkey);
      imageViewLT[paramInt].setImageResource(R.drawable.newkey);
      return;
    case 864:
      imageView[paramInt].setImageResource(R.drawable.center3);
      imageViewRB[paramInt].setImageResource(R.drawable.center3);
      imageViewRT[paramInt].setImageResource(R.drawable.center3);
      imageViewLB[paramInt].setImageResource(R.drawable.center3);
      imageViewLT[paramInt].setImageResource(R.drawable.center3);
      return;
    case 863:
      imageView[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr2);
      return;
    case 862:
      imageView[paramInt].setImageResource(R.drawable.center2);
      imageViewRB[paramInt].setImageResource(R.drawable.center2);
      imageViewRT[paramInt].setImageResource(R.drawable.center2);
      imageViewLB[paramInt].setImageResource(R.drawable.center2);
      imageViewLT[paramInt].setImageResource(R.drawable.center2);
      return;
    case 861:
      imageView[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr1);
      return;
    case 860:
      imageView[paramInt].setImageResource(R.drawable.center1);
      imageViewRB[paramInt].setImageResource(R.drawable.center1);
      imageViewRT[paramInt].setImageResource(R.drawable.center1);
      imageViewLB[paramInt].setImageResource(R.drawable.center1);
      imageViewLT[paramInt].setImageResource(R.drawable.center1);
      return;
    case 859:
      imageView[paramInt].setImageResource(R.drawable.gpm4);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4);
      return;
    case 858:
      imageView[paramInt].setImageResource(R.drawable.gpm3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3);
      return;
    case 857:
      imageView[paramInt].setImageResource(R.drawable.gpm2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2);
      return;
    case 856:
      imageView[paramInt].setImageResource(R.drawable.gpm1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1);
      return;
    case 855:
      imageView[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr);
      return;
    case 854:
      imageView[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpl);
      return;
    case 853:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprt);
        imageViewRB[paramInt].setImageResource(R.drawable.gprt);
        imageViewRT[paramInt].setImageResource(R.drawable.gprt);
        imageViewLB[paramInt].setImageResource(R.drawable.gprt);
        imageViewLT[paramInt].setImageResource(R.drawable.gprt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2);
      return;
    case 852:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplt);
        imageViewRB[paramInt].setImageResource(R.drawable.gplt);
        imageViewRT[paramInt].setImageResource(R.drawable.gplt);
        imageViewLB[paramInt].setImageResource(R.drawable.gplt);
        imageViewLT[paramInt].setImageResource(R.drawable.gplt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2);
      return;
    case 851:
      imageView[paramInt].setImageResource(R.drawable.gpr3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3);
      return;
    case 850:
      imageView[paramInt].setImageResource(R.drawable.gpl3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3);
      return;
    case 849:
      imageView[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselect);
      return;
    case 848:
      imageView[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstart);
      return;
    case 847:
      imageView[paramInt].setImageResource(R.drawable.gpright);
      imageViewRB[paramInt].setImageResource(R.drawable.gpright);
      imageViewRT[paramInt].setImageResource(R.drawable.gpright);
      imageViewLB[paramInt].setImageResource(R.drawable.gpright);
      imageViewLT[paramInt].setImageResource(R.drawable.gpright);
      return;
    case 846:
      imageView[paramInt].setImageResource(R.drawable.gpleft);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleft);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleft);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleft);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleft);
      return;
    case 845:
      imageView[paramInt].setImageResource(R.drawable.gpdown);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdown);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdown);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdown);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdown);
      return;
    case 844:
      imageView[paramInt].setImageResource(R.drawable.gpup);
      imageViewRB[paramInt].setImageResource(R.drawable.gpup);
      imageViewRT[paramInt].setImageResource(R.drawable.gpup);
      imageViewLB[paramInt].setImageResource(R.drawable.gpup);
      imageViewLT[paramInt].setImageResource(R.drawable.gpup);
      return;
    case 843:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprb);
        imageViewRB[paramInt].setImageResource(R.drawable.gprb);
        imageViewRT[paramInt].setImageResource(R.drawable.gprb);
        imageViewLB[paramInt].setImageResource(R.drawable.gprb);
        imageViewLT[paramInt].setImageResource(R.drawable.gprb);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1);
      return;
    case 842:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplb);
        imageViewRB[paramInt].setImageResource(R.drawable.gplb);
        imageViewRT[paramInt].setImageResource(R.drawable.gplb);
        imageViewLB[paramInt].setImageResource(R.drawable.gplb);
        imageViewLT[paramInt].setImageResource(R.drawable.gplb);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1);
      return;
    case 841:
      imageView[paramInt].setImageResource(R.drawable.gpy);
      imageViewRB[paramInt].setImageResource(R.drawable.gpy);
      imageViewRT[paramInt].setImageResource(R.drawable.gpy);
      imageViewLB[paramInt].setImageResource(R.drawable.gpy);
      imageViewLT[paramInt].setImageResource(R.drawable.gpy);
      return;
    case 840:
      imageView[paramInt].setImageResource(R.drawable.gpx);
      imageViewRB[paramInt].setImageResource(R.drawable.gpx);
      imageViewRT[paramInt].setImageResource(R.drawable.gpx);
      imageViewLB[paramInt].setImageResource(R.drawable.gpx);
      imageViewLT[paramInt].setImageResource(R.drawable.gpx);
      return;
    case 839:
      imageView[paramInt].setImageResource(R.drawable.gpb);
      imageViewRB[paramInt].setImageResource(R.drawable.gpb);
      imageViewRT[paramInt].setImageResource(R.drawable.gpb);
      imageViewLB[paramInt].setImageResource(R.drawable.gpb);
      imageViewLT[paramInt].setImageResource(R.drawable.gpb);
      return;
    case 838:
      imageView[paramInt].setImageResource(R.drawable.gpa);
      imageViewRB[paramInt].setImageResource(R.drawable.gpa);
      imageViewRT[paramInt].setImageResource(R.drawable.gpa);
      imageViewLB[paramInt].setImageResource(R.drawable.gpa);
      imageViewLT[paramInt].setImageResource(R.drawable.gpa);
      return;
    case 833:
    case 834:
    case 835:
    case 836:
    case 837:
      imageView[paramInt].setImageResource(R.drawable.newkey);
      imageViewRB[paramInt].setImageResource(R.drawable.newkey);
      imageViewRT[paramInt].setImageResource(R.drawable.newkey);
      imageViewLB[paramInt].setImageResource(R.drawable.newkey);
      imageViewLT[paramInt].setImageResource(R.drawable.newkey);
      return;
    case 832:
      imageView[paramInt].setImageResource(R.drawable.center3);
      imageViewRB[paramInt].setImageResource(R.drawable.center3);
      imageViewRT[paramInt].setImageResource(R.drawable.center3);
      imageViewLB[paramInt].setImageResource(R.drawable.center3);
      imageViewLT[paramInt].setImageResource(R.drawable.center3);
      return;
    case 831:
      imageView[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr2);
      return;
    case 830:
      imageView[paramInt].setImageResource(R.drawable.center2);
      imageViewRB[paramInt].setImageResource(R.drawable.center2);
      imageViewRT[paramInt].setImageResource(R.drawable.center2);
      imageViewLB[paramInt].setImageResource(R.drawable.center2);
      imageViewLT[paramInt].setImageResource(R.drawable.center2);
      return;
    case 829:
      imageView[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr1);
      return;
    case 828:
      imageView[paramInt].setImageResource(R.drawable.center1);
      imageViewRB[paramInt].setImageResource(R.drawable.center1);
      imageViewRT[paramInt].setImageResource(R.drawable.center1);
      imageViewLB[paramInt].setImageResource(R.drawable.center1);
      imageViewLT[paramInt].setImageResource(R.drawable.center1);
      return;
    case 827:
      imageView[paramInt].setImageResource(R.drawable.gpm4);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4);
      return;
    case 826:
      imageView[paramInt].setImageResource(R.drawable.gpm3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3);
      return;
    case 825:
      imageView[paramInt].setImageResource(R.drawable.gpm2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2);
      return;
    case 824:
      imageView[paramInt].setImageResource(R.drawable.gpm1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1);
      return;
    case 823:
      imageView[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr);
      return;
    case 822:
      imageView[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpl);
      return;
    case 821:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprt);
        imageViewRB[paramInt].setImageResource(R.drawable.gprt);
        imageViewRT[paramInt].setImageResource(R.drawable.gprt);
        imageViewLB[paramInt].setImageResource(R.drawable.gprt);
        imageViewLT[paramInt].setImageResource(R.drawable.gprt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2);
      return;
    case 820:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplt);
        imageViewRB[paramInt].setImageResource(R.drawable.gplt);
        imageViewRT[paramInt].setImageResource(R.drawable.gplt);
        imageViewLB[paramInt].setImageResource(R.drawable.gplt);
        imageViewLT[paramInt].setImageResource(R.drawable.gplt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2);
      return;
    case 819:
      imageView[paramInt].setImageResource(R.drawable.gpr3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3);
      return;
    case 818:
      imageView[paramInt].setImageResource(R.drawable.gpl3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3);
      return;
    case 817:
      imageView[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselect);
      return;
    case 816:
      imageView[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstart);
      return;
    case 815:
      imageView[paramInt].setImageResource(R.drawable.gpright);
      imageViewRB[paramInt].setImageResource(R.drawable.gpright);
      imageViewRT[paramInt].setImageResource(R.drawable.gpright);
      imageViewLB[paramInt].setImageResource(R.drawable.gpright);
      imageViewLT[paramInt].setImageResource(R.drawable.gpright);
      return;
    case 814:
      imageView[paramInt].setImageResource(R.drawable.gpleft);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleft);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleft);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleft);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleft);
      return;
    case 813:
      imageView[paramInt].setImageResource(R.drawable.gpdown);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdown);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdown);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdown);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdown);
      return;
    case 812:
      imageView[paramInt].setImageResource(R.drawable.gpup);
      imageViewRB[paramInt].setImageResource(R.drawable.gpup);
      imageViewRT[paramInt].setImageResource(R.drawable.gpup);
      imageViewLB[paramInt].setImageResource(R.drawable.gpup);
      imageViewLT[paramInt].setImageResource(R.drawable.gpup);
      return;
    case 811:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprb);
        imageViewRB[paramInt].setImageResource(R.drawable.gprb);
        imageViewRT[paramInt].setImageResource(R.drawable.gprb);
        imageViewLB[paramInt].setImageResource(R.drawable.gprb);
        imageViewLT[paramInt].setImageResource(R.drawable.gprb);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1);
      return;
    case 810:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplb);
        imageViewRB[paramInt].setImageResource(R.drawable.gplb);
        imageViewRT[paramInt].setImageResource(R.drawable.gplb);
        imageViewLB[paramInt].setImageResource(R.drawable.gplb);
        imageViewLT[paramInt].setImageResource(R.drawable.gplb);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1);
      return;
    case 809:
      imageView[paramInt].setImageResource(R.drawable.gpy);
      imageViewRB[paramInt].setImageResource(R.drawable.gpy);
      imageViewRT[paramInt].setImageResource(R.drawable.gpy);
      imageViewLB[paramInt].setImageResource(R.drawable.gpy);
      imageViewLT[paramInt].setImageResource(R.drawable.gpy);
      return;
    case 808:
      imageView[paramInt].setImageResource(R.drawable.gpx);
      imageViewRB[paramInt].setImageResource(R.drawable.gpx);
      imageViewRT[paramInt].setImageResource(R.drawable.gpx);
      imageViewLB[paramInt].setImageResource(R.drawable.gpx);
      imageViewLT[paramInt].setImageResource(R.drawable.gpx);
      return;
    case 807:
      imageView[paramInt].setImageResource(R.drawable.gpb);
      imageViewRB[paramInt].setImageResource(R.drawable.gpb);
      imageViewRT[paramInt].setImageResource(R.drawable.gpb);
      imageViewLB[paramInt].setImageResource(R.drawable.gpb);
      imageViewLT[paramInt].setImageResource(R.drawable.gpb);
      return;
    case 806:
      imageView[paramInt].setImageResource(R.drawable.gpa);
      imageViewRB[paramInt].setImageResource(R.drawable.gpa);
      imageViewRT[paramInt].setImageResource(R.drawable.gpa);
      imageViewLB[paramInt].setImageResource(R.drawable.gpa);
      imageViewLT[paramInt].setImageResource(R.drawable.gpa);
      return;
    case 801:
    case 802:
    case 803:
    case 804:
    case 805:
      imageView[paramInt].setImageResource(R.drawable.newkey);
      imageViewRB[paramInt].setImageResource(R.drawable.newkey);
      imageViewRT[paramInt].setImageResource(R.drawable.newkey);
      imageViewLB[paramInt].setImageResource(R.drawable.newkey);
      imageViewLT[paramInt].setImageResource(R.drawable.newkey);
      return;
    case 800:
      imageView[paramInt].setImageResource(R.drawable.center3);
      imageViewRB[paramInt].setImageResource(R.drawable.center3);
      imageViewRT[paramInt].setImageResource(R.drawable.center3);
      imageViewLB[paramInt].setImageResource(R.drawable.center3);
      imageViewLT[paramInt].setImageResource(R.drawable.center3);
      return;
    case 799:
      imageView[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr2);
      return;
    case 798:
      imageView[paramInt].setImageResource(R.drawable.center2);
      imageViewRB[paramInt].setImageResource(R.drawable.center2);
      imageViewRT[paramInt].setImageResource(R.drawable.center2);
      imageViewLB[paramInt].setImageResource(R.drawable.center2);
      imageViewLT[paramInt].setImageResource(R.drawable.center2);
      return;
    case 797:
      imageView[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr1);
      return;
    case 796:
      imageView[paramInt].setImageResource(R.drawable.center1);
      imageViewRB[paramInt].setImageResource(R.drawable.center1);
      imageViewRT[paramInt].setImageResource(R.drawable.center1);
      imageViewLB[paramInt].setImageResource(R.drawable.center1);
      imageViewLT[paramInt].setImageResource(R.drawable.center1);
      return;
    case 795:
      imageView[paramInt].setImageResource(R.drawable.gpm4);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4);
      return;
    case 794:
      imageView[paramInt].setImageResource(R.drawable.gpm3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3);
      return;
    case 793:
      imageView[paramInt].setImageResource(R.drawable.gpm2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2);
      return;
    case 792:
      imageView[paramInt].setImageResource(R.drawable.gpm1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1);
      return;
    case 791:
      imageView[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr);
      return;
    case 790:
      imageView[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpl);
      return;
    case 789:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprt);
        imageViewRB[paramInt].setImageResource(R.drawable.gprt);
        imageViewRT[paramInt].setImageResource(R.drawable.gprt);
        imageViewLB[paramInt].setImageResource(R.drawable.gprt);
        imageViewLT[paramInt].setImageResource(R.drawable.gprt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2);
      return;
    case 788:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplt);
        imageViewRB[paramInt].setImageResource(R.drawable.gplt);
        imageViewRT[paramInt].setImageResource(R.drawable.gplt);
        imageViewLB[paramInt].setImageResource(R.drawable.gplt);
        imageViewLT[paramInt].setImageResource(R.drawable.gplt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2);
      return;
    case 787:
      imageView[paramInt].setImageResource(R.drawable.gpr3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3);
      return;
    case 786:
      imageView[paramInt].setImageResource(R.drawable.gpl3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3);
      return;
    case 785:
      imageView[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselect);
      return;
    case 784:
      imageView[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstart);
      return;
    case 783:
      imageView[paramInt].setImageResource(R.drawable.gpright);
      imageViewRB[paramInt].setImageResource(R.drawable.gpright);
      imageViewRT[paramInt].setImageResource(R.drawable.gpright);
      imageViewLB[paramInt].setImageResource(R.drawable.gpright);
      imageViewLT[paramInt].setImageResource(R.drawable.gpright);
      return;
    case 782:
      imageView[paramInt].setImageResource(R.drawable.gpleft);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleft);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleft);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleft);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleft);
      return;
    case 781:
      imageView[paramInt].setImageResource(R.drawable.gpdown);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdown);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdown);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdown);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdown);
      return;
    case 780:
      imageView[paramInt].setImageResource(R.drawable.gpup);
      imageViewRB[paramInt].setImageResource(R.drawable.gpup);
      imageViewRT[paramInt].setImageResource(R.drawable.gpup);
      imageViewLB[paramInt].setImageResource(R.drawable.gpup);
      imageViewLT[paramInt].setImageResource(R.drawable.gpup);
      return;
    case 779:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprb);
        imageViewRB[paramInt].setImageResource(R.drawable.gprb);
        imageViewRT[paramInt].setImageResource(R.drawable.gprb);
        imageViewLB[paramInt].setImageResource(R.drawable.gprb);
        imageViewLT[paramInt].setImageResource(R.drawable.gprb);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1);
      return;
    case 778:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplb);
        imageViewRB[paramInt].setImageResource(R.drawable.gplb);
        imageViewRT[paramInt].setImageResource(R.drawable.gplb);
        imageViewLB[paramInt].setImageResource(R.drawable.gplb);
        imageViewLT[paramInt].setImageResource(R.drawable.gplb);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1);
      return;
    case 777:
      imageView[paramInt].setImageResource(R.drawable.gpy);
      imageViewRB[paramInt].setImageResource(R.drawable.gpy);
      imageViewRT[paramInt].setImageResource(R.drawable.gpy);
      imageViewLB[paramInt].setImageResource(R.drawable.gpy);
      imageViewLT[paramInt].setImageResource(R.drawable.gpy);
      return;
    case 776:
      imageView[paramInt].setImageResource(R.drawable.gpx);
      imageViewRB[paramInt].setImageResource(R.drawable.gpx);
      imageViewRT[paramInt].setImageResource(R.drawable.gpx);
      imageViewLB[paramInt].setImageResource(R.drawable.gpx);
      imageViewLT[paramInt].setImageResource(R.drawable.gpx);
      return;
    case 775:
      imageView[paramInt].setImageResource(R.drawable.gpb);
      imageViewRB[paramInt].setImageResource(R.drawable.gpb);
      imageViewRT[paramInt].setImageResource(R.drawable.gpb);
      imageViewLB[paramInt].setImageResource(R.drawable.gpb);
      imageViewLT[paramInt].setImageResource(R.drawable.gpb);
      return;
    case 774:
      imageView[paramInt].setImageResource(R.drawable.gpa);
      imageViewRB[paramInt].setImageResource(R.drawable.gpa);
      imageViewRT[paramInt].setImageResource(R.drawable.gpa);
      imageViewLB[paramInt].setImageResource(R.drawable.gpa);
      imageViewLT[paramInt].setImageResource(R.drawable.gpa);
      return;
    case 769:
    case 770:
    case 771:
    case 772:
    case 773:
      imageView[paramInt].setImageResource(R.drawable.newkey);
      imageViewRB[paramInt].setImageResource(R.drawable.newkey);
      imageViewRT[paramInt].setImageResource(R.drawable.newkey);
      imageViewLB[paramInt].setImageResource(R.drawable.newkey);
      imageViewLT[paramInt].setImageResource(R.drawable.newkey);
      return;
    case 768:
      imageView[paramInt].setImageResource(R.drawable.center3);
      imageViewRB[paramInt].setImageResource(R.drawable.center3);
      imageViewRT[paramInt].setImageResource(R.drawable.center3);
      imageViewLB[paramInt].setImageResource(R.drawable.center3);
      imageViewLT[paramInt].setImageResource(R.drawable.center3);
      return;
    case 767:
      imageView[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr2);
      return;
    case 766:
      imageView[paramInt].setImageResource(R.drawable.center2);
      imageViewRB[paramInt].setImageResource(R.drawable.center2);
      imageViewRT[paramInt].setImageResource(R.drawable.center2);
      imageViewLB[paramInt].setImageResource(R.drawable.center2);
      imageViewLT[paramInt].setImageResource(R.drawable.center2);
      return;
    case 765:
      imageView[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr1);
      return;
    case 764:
      imageView[paramInt].setImageResource(R.drawable.center1);
      imageViewRB[paramInt].setImageResource(R.drawable.center1);
      imageViewRT[paramInt].setImageResource(R.drawable.center1);
      imageViewLB[paramInt].setImageResource(R.drawable.center1);
      imageViewLT[paramInt].setImageResource(R.drawable.center1);
      return;
    case 763:
      imageView[paramInt].setImageResource(R.drawable.gpm4);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4);
      return;
    case 762:
      imageView[paramInt].setImageResource(R.drawable.gpm3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3);
      return;
    case 761:
      imageView[paramInt].setImageResource(R.drawable.gpm2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2);
      return;
    case 760:
      imageView[paramInt].setImageResource(R.drawable.gpm1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1);
      return;
    case 759:
      imageView[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr);
      return;
    case 758:
      imageView[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpl);
      return;
    case 757:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprt);
        imageViewRB[paramInt].setImageResource(R.drawable.gprt);
        imageViewRT[paramInt].setImageResource(R.drawable.gprt);
        imageViewLB[paramInt].setImageResource(R.drawable.gprt);
        imageViewLT[paramInt].setImageResource(R.drawable.gprt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2);
      return;
    case 756:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplt);
        imageViewRB[paramInt].setImageResource(R.drawable.gplt);
        imageViewRT[paramInt].setImageResource(R.drawable.gplt);
        imageViewLB[paramInt].setImageResource(R.drawable.gplt);
        imageViewLT[paramInt].setImageResource(R.drawable.gplt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2);
      return;
    case 755:
      imageView[paramInt].setImageResource(R.drawable.gpr3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3);
      return;
    case 754:
      imageView[paramInt].setImageResource(R.drawable.gpl3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3);
      return;
    case 753:
      imageView[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselect);
      return;
    case 752:
      imageView[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstart);
      return;
    case 751:
      imageView[paramInt].setImageResource(R.drawable.gpright);
      imageViewRB[paramInt].setImageResource(R.drawable.gpright);
      imageViewRT[paramInt].setImageResource(R.drawable.gpright);
      imageViewLB[paramInt].setImageResource(R.drawable.gpright);
      imageViewLT[paramInt].setImageResource(R.drawable.gpright);
      return;
    case 750:
      imageView[paramInt].setImageResource(R.drawable.gpleft);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleft);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleft);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleft);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleft);
      return;
    case 749:
      imageView[paramInt].setImageResource(R.drawable.gpdown);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdown);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdown);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdown);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdown);
      return;
    case 748:
      imageView[paramInt].setImageResource(R.drawable.gpup);
      imageViewRB[paramInt].setImageResource(R.drawable.gpup);
      imageViewRT[paramInt].setImageResource(R.drawable.gpup);
      imageViewLB[paramInt].setImageResource(R.drawable.gpup);
      imageViewLT[paramInt].setImageResource(R.drawable.gpup);
      return;
    case 747:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprb);
        imageViewRB[paramInt].setImageResource(R.drawable.gprb);
        imageViewRT[paramInt].setImageResource(R.drawable.gprb);
        imageViewLB[paramInt].setImageResource(R.drawable.gprb);
        imageViewLT[paramInt].setImageResource(R.drawable.gprb);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1);
      return;
    case 746:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplb);
        imageViewRB[paramInt].setImageResource(R.drawable.gplb);
        imageViewRT[paramInt].setImageResource(R.drawable.gplb);
        imageViewLB[paramInt].setImageResource(R.drawable.gplb);
        imageViewLT[paramInt].setImageResource(R.drawable.gplb);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1);
      return;
    case 745:
      imageView[paramInt].setImageResource(R.drawable.gpy);
      imageViewRB[paramInt].setImageResource(R.drawable.gpy);
      imageViewRT[paramInt].setImageResource(R.drawable.gpy);
      imageViewLB[paramInt].setImageResource(R.drawable.gpy);
      imageViewLT[paramInt].setImageResource(R.drawable.gpy);
      return;
    case 744:
      imageView[paramInt].setImageResource(R.drawable.gpx);
      imageViewRB[paramInt].setImageResource(R.drawable.gpx);
      imageViewRT[paramInt].setImageResource(R.drawable.gpx);
      imageViewLB[paramInt].setImageResource(R.drawable.gpx);
      imageViewLT[paramInt].setImageResource(R.drawable.gpx);
      return;
    case 743:
      imageView[paramInt].setImageResource(R.drawable.gpb);
      imageViewRB[paramInt].setImageResource(R.drawable.gpb);
      imageViewRT[paramInt].setImageResource(R.drawable.gpb);
      imageViewLB[paramInt].setImageResource(R.drawable.gpb);
      imageViewLT[paramInt].setImageResource(R.drawable.gpb);
      return;
    case 742:
      imageView[paramInt].setImageResource(R.drawable.gpa);
      imageViewRB[paramInt].setImageResource(R.drawable.gpa);
      imageViewRT[paramInt].setImageResource(R.drawable.gpa);
      imageViewLB[paramInt].setImageResource(R.drawable.gpa);
      imageViewLT[paramInt].setImageResource(R.drawable.gpa);
      return;
    case 737:
    case 738:
    case 739:
    case 740:
    case 741:
      imageView[paramInt].setImageResource(R.drawable.newkey);
      imageViewRB[paramInt].setImageResource(R.drawable.newkey);
      imageViewRT[paramInt].setImageResource(R.drawable.newkey);
      imageViewLB[paramInt].setImageResource(R.drawable.newkey);
      imageViewLT[paramInt].setImageResource(R.drawable.newkey);
      return;
    case 736:
      imageView[paramInt].setImageResource(R.drawable.center3);
      imageViewRB[paramInt].setImageResource(R.drawable.center3);
      imageViewRT[paramInt].setImageResource(R.drawable.center3);
      imageViewLB[paramInt].setImageResource(R.drawable.center3);
      imageViewLT[paramInt].setImageResource(R.drawable.center3);
      return;
    case 735:
      imageView[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr2);
      return;
    case 734:
      imageView[paramInt].setImageResource(R.drawable.center2);
      imageViewRB[paramInt].setImageResource(R.drawable.center2);
      imageViewRT[paramInt].setImageResource(R.drawable.center2);
      imageViewLB[paramInt].setImageResource(R.drawable.center2);
      imageViewLT[paramInt].setImageResource(R.drawable.center2);
      return;
    case 733:
      imageView[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr1);
      return;
    case 732:
      imageView[paramInt].setImageResource(R.drawable.center1);
      imageViewRB[paramInt].setImageResource(R.drawable.center1);
      imageViewRT[paramInt].setImageResource(R.drawable.center1);
      imageViewLB[paramInt].setImageResource(R.drawable.center1);
      imageViewLT[paramInt].setImageResource(R.drawable.center1);
      return;
    case 731:
      imageView[paramInt].setImageResource(R.drawable.gpm4);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4);
      return;
    case 730:
      imageView[paramInt].setImageResource(R.drawable.gpm3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3);
      return;
    case 729:
      imageView[paramInt].setImageResource(R.drawable.gpm2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2);
      return;
    case 728:
      imageView[paramInt].setImageResource(R.drawable.gpm1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1);
      return;
    case 727:
      imageView[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr);
      return;
    case 726:
      imageView[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpl);
      return;
    case 725:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprt);
        imageViewRB[paramInt].setImageResource(R.drawable.gprt);
        imageViewRT[paramInt].setImageResource(R.drawable.gprt);
        imageViewLB[paramInt].setImageResource(R.drawable.gprt);
        imageViewLT[paramInt].setImageResource(R.drawable.gprt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2);
      return;
    case 724:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplt);
        imageViewRB[paramInt].setImageResource(R.drawable.gplt);
        imageViewRT[paramInt].setImageResource(R.drawable.gplt);
        imageViewLB[paramInt].setImageResource(R.drawable.gplt);
        imageViewLT[paramInt].setImageResource(R.drawable.gplt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2);
      return;
    case 723:
      imageView[paramInt].setImageResource(R.drawable.gpr3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3);
      return;
    case 722:
      imageView[paramInt].setImageResource(R.drawable.gpl3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3);
      return;
    case 721:
      imageView[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselect);
      return;
    case 720:
      imageView[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstart);
      return;
    case 719:
      imageView[paramInt].setImageResource(R.drawable.gpright);
      imageViewRB[paramInt].setImageResource(R.drawable.gpright);
      imageViewRT[paramInt].setImageResource(R.drawable.gpright);
      imageViewLB[paramInt].setImageResource(R.drawable.gpright);
      imageViewLT[paramInt].setImageResource(R.drawable.gpright);
      return;
    case 718:
      imageView[paramInt].setImageResource(R.drawable.gpleft);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleft);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleft);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleft);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleft);
      return;
    case 717:
      imageView[paramInt].setImageResource(R.drawable.gpdown);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdown);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdown);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdown);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdown);
      return;
    case 716:
      imageView[paramInt].setImageResource(R.drawable.gpup);
      imageViewRB[paramInt].setImageResource(R.drawable.gpup);
      imageViewRT[paramInt].setImageResource(R.drawable.gpup);
      imageViewLB[paramInt].setImageResource(R.drawable.gpup);
      imageViewLT[paramInt].setImageResource(R.drawable.gpup);
      return;
    case 715:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprb);
        imageViewRB[paramInt].setImageResource(R.drawable.gprb);
        imageViewRT[paramInt].setImageResource(R.drawable.gprb);
        imageViewLB[paramInt].setImageResource(R.drawable.gprb);
        imageViewLT[paramInt].setImageResource(R.drawable.gprb);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1);
      return;
    case 714:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplb);
        imageViewRB[paramInt].setImageResource(R.drawable.gplb);
        imageViewRT[paramInt].setImageResource(R.drawable.gplb);
        imageViewLB[paramInt].setImageResource(R.drawable.gplb);
        imageViewLT[paramInt].setImageResource(R.drawable.gplb);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1);
      return;
    case 713:
      imageView[paramInt].setImageResource(R.drawable.gpy);
      imageViewRB[paramInt].setImageResource(R.drawable.gpy);
      imageViewRT[paramInt].setImageResource(R.drawable.gpy);
      imageViewLB[paramInt].setImageResource(R.drawable.gpy);
      imageViewLT[paramInt].setImageResource(R.drawable.gpy);
      return;
    case 712:
      imageView[paramInt].setImageResource(R.drawable.gpx);
      imageViewRB[paramInt].setImageResource(R.drawable.gpx);
      imageViewRT[paramInt].setImageResource(R.drawable.gpx);
      imageViewLB[paramInt].setImageResource(R.drawable.gpx);
      imageViewLT[paramInt].setImageResource(R.drawable.gpx);
      return;
    case 711:
      imageView[paramInt].setImageResource(R.drawable.gpb);
      imageViewRB[paramInt].setImageResource(R.drawable.gpb);
      imageViewRT[paramInt].setImageResource(R.drawable.gpb);
      imageViewLB[paramInt].setImageResource(R.drawable.gpb);
      imageViewLT[paramInt].setImageResource(R.drawable.gpb);
      return;
    case 710:
      imageView[paramInt].setImageResource(R.drawable.gpa);
      imageViewRB[paramInt].setImageResource(R.drawable.gpa);
      imageViewRT[paramInt].setImageResource(R.drawable.gpa);
      imageViewLB[paramInt].setImageResource(R.drawable.gpa);
      imageViewLT[paramInt].setImageResource(R.drawable.gpa);
      return;
    case 705:
    case 706:
    case 707:
    case 708:
    case 709:
      imageView[paramInt].setImageResource(R.drawable.newkey);
      imageViewRB[paramInt].setImageResource(R.drawable.newkey);
      imageViewRT[paramInt].setImageResource(R.drawable.newkey);
      imageViewLB[paramInt].setImageResource(R.drawable.newkey);
      imageViewLT[paramInt].setImageResource(R.drawable.newkey);
      return;
    case 704:
      imageView[paramInt].setImageResource(R.drawable.center3);
      imageViewRB[paramInt].setImageResource(R.drawable.center3);
      imageViewRT[paramInt].setImageResource(R.drawable.center3);
      imageViewLB[paramInt].setImageResource(R.drawable.center3);
      imageViewLT[paramInt].setImageResource(R.drawable.center3);
      return;
    case 703:
      imageView[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr2);
      return;
    case 702:
      imageView[paramInt].setImageResource(R.drawable.center2);
      imageViewRB[paramInt].setImageResource(R.drawable.center2);
      imageViewRT[paramInt].setImageResource(R.drawable.center2);
      imageViewLB[paramInt].setImageResource(R.drawable.center2);
      imageViewLT[paramInt].setImageResource(R.drawable.center2);
      return;
    case 701:
      imageView[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr1);
      return;
    case 700:
      imageView[paramInt].setImageResource(R.drawable.center1);
      imageViewRB[paramInt].setImageResource(R.drawable.center1);
      imageViewRT[paramInt].setImageResource(R.drawable.center1);
      imageViewLB[paramInt].setImageResource(R.drawable.center1);
      imageViewLT[paramInt].setImageResource(R.drawable.center1);
      return;
    case 699:
      imageView[paramInt].setImageResource(R.drawable.gpm4);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4);
      return;
    case 698:
      imageView[paramInt].setImageResource(R.drawable.gpm3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3);
      return;
    case 697:
      imageView[paramInt].setImageResource(R.drawable.gpm2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2);
      return;
    case 696:
      imageView[paramInt].setImageResource(R.drawable.gpm1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1);
      return;
    case 695:
      imageView[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr);
      return;
    case 694:
      imageView[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpl);
      return;
    case 693:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprt);
        imageViewRB[paramInt].setImageResource(R.drawable.gprt);
        imageViewRT[paramInt].setImageResource(R.drawable.gprt);
        imageViewLB[paramInt].setImageResource(R.drawable.gprt);
        imageViewLT[paramInt].setImageResource(R.drawable.gprt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2);
      return;
    case 692:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplt);
        imageViewRB[paramInt].setImageResource(R.drawable.gplt);
        imageViewRT[paramInt].setImageResource(R.drawable.gplt);
        imageViewLB[paramInt].setImageResource(R.drawable.gplt);
        imageViewLT[paramInt].setImageResource(R.drawable.gplt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2);
      return;
    case 691:
      imageView[paramInt].setImageResource(R.drawable.gpr3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3);
      return;
    case 690:
      imageView[paramInt].setImageResource(R.drawable.gpl3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3);
      return;
    case 689:
      imageView[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselect);
      return;
    case 688:
      imageView[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstart);
      return;
    case 687:
      imageView[paramInt].setImageResource(R.drawable.gpright);
      imageViewRB[paramInt].setImageResource(R.drawable.gpright);
      imageViewRT[paramInt].setImageResource(R.drawable.gpright);
      imageViewLB[paramInt].setImageResource(R.drawable.gpright);
      imageViewLT[paramInt].setImageResource(R.drawable.gpright);
      return;
    case 686:
      imageView[paramInt].setImageResource(R.drawable.gpleft);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleft);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleft);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleft);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleft);
      return;
    case 685:
      imageView[paramInt].setImageResource(R.drawable.gpdown);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdown);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdown);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdown);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdown);
      return;
    case 684:
      imageView[paramInt].setImageResource(R.drawable.gpup);
      imageViewRB[paramInt].setImageResource(R.drawable.gpup);
      imageViewRT[paramInt].setImageResource(R.drawable.gpup);
      imageViewLB[paramInt].setImageResource(R.drawable.gpup);
      imageViewLT[paramInt].setImageResource(R.drawable.gpup);
      return;
    case 683:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprb);
        imageViewRB[paramInt].setImageResource(R.drawable.gprb);
        imageViewRT[paramInt].setImageResource(R.drawable.gprb);
        imageViewLB[paramInt].setImageResource(R.drawable.gprb);
        imageViewLT[paramInt].setImageResource(R.drawable.gprb);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1);
      return;
    case 682:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplb);
        imageViewRB[paramInt].setImageResource(R.drawable.gplb);
        imageViewRT[paramInt].setImageResource(R.drawable.gplb);
        imageViewLB[paramInt].setImageResource(R.drawable.gplb);
        imageViewLT[paramInt].setImageResource(R.drawable.gplb);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1);
      return;
    case 681:
      imageView[paramInt].setImageResource(R.drawable.gpy);
      imageViewRB[paramInt].setImageResource(R.drawable.gpy);
      imageViewRT[paramInt].setImageResource(R.drawable.gpy);
      imageViewLB[paramInt].setImageResource(R.drawable.gpy);
      imageViewLT[paramInt].setImageResource(R.drawable.gpy);
      return;
    case 680:
      imageView[paramInt].setImageResource(R.drawable.gpx);
      imageViewRB[paramInt].setImageResource(R.drawable.gpx);
      imageViewRT[paramInt].setImageResource(R.drawable.gpx);
      imageViewLB[paramInt].setImageResource(R.drawable.gpx);
      imageViewLT[paramInt].setImageResource(R.drawable.gpx);
      return;
    case 679:
      imageView[paramInt].setImageResource(R.drawable.gpb);
      imageViewRB[paramInt].setImageResource(R.drawable.gpb);
      imageViewRT[paramInt].setImageResource(R.drawable.gpb);
      imageViewLB[paramInt].setImageResource(R.drawable.gpb);
      imageViewLT[paramInt].setImageResource(R.drawable.gpb);
      return;
    case 678:
      imageView[paramInt].setImageResource(R.drawable.gpa);
      imageViewRB[paramInt].setImageResource(R.drawable.gpa);
      imageViewRT[paramInt].setImageResource(R.drawable.gpa);
      imageViewLB[paramInt].setImageResource(R.drawable.gpa);
      imageViewLT[paramInt].setImageResource(R.drawable.gpa);
      return;
    case 673:
    case 674:
    case 675:
    case 676:
    case 677:
      imageView[paramInt].setImageResource(R.drawable.newkey);
      imageViewRB[paramInt].setImageResource(R.drawable.newkey);
      imageViewRT[paramInt].setImageResource(R.drawable.newkey);
      imageViewLB[paramInt].setImageResource(R.drawable.newkey);
      imageViewLT[paramInt].setImageResource(R.drawable.newkey);
      return;
    case 672:
      imageView[paramInt].setImageResource(R.drawable.center3);
      imageViewRB[paramInt].setImageResource(R.drawable.center3);
      imageViewRT[paramInt].setImageResource(R.drawable.center3);
      imageViewLB[paramInt].setImageResource(R.drawable.center3);
      imageViewLT[paramInt].setImageResource(R.drawable.center3);
      return;
    case 671:
      imageView[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr2);
      return;
    case 670:
      imageView[paramInt].setImageResource(R.drawable.center2);
      imageViewRB[paramInt].setImageResource(R.drawable.center2);
      imageViewRT[paramInt].setImageResource(R.drawable.center2);
      imageViewLB[paramInt].setImageResource(R.drawable.center2);
      imageViewLT[paramInt].setImageResource(R.drawable.center2);
      return;
    case 669:
      imageView[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr1);
      return;
    case 668:
      imageView[paramInt].setImageResource(R.drawable.center1);
      imageViewRB[paramInt].setImageResource(R.drawable.center1);
      imageViewRT[paramInt].setImageResource(R.drawable.center1);
      imageViewLB[paramInt].setImageResource(R.drawable.center1);
      imageViewLT[paramInt].setImageResource(R.drawable.center1);
      return;
    case 667:
      imageView[paramInt].setImageResource(R.drawable.gpm4);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4);
      return;
    case 666:
      imageView[paramInt].setImageResource(R.drawable.gpm3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3);
      return;
    case 665:
      imageView[paramInt].setImageResource(R.drawable.gpm2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2);
      return;
    case 664:
      imageView[paramInt].setImageResource(R.drawable.gpm1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1);
      return;
    case 663:
      imageView[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr);
      return;
    case 662:
      imageView[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpl);
      return;
    case 661:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprt);
        imageViewRB[paramInt].setImageResource(R.drawable.gprt);
        imageViewRT[paramInt].setImageResource(R.drawable.gprt);
        imageViewLB[paramInt].setImageResource(R.drawable.gprt);
        imageViewLT[paramInt].setImageResource(R.drawable.gprt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2);
      return;
    case 660:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplt);
        imageViewRB[paramInt].setImageResource(R.drawable.gplt);
        imageViewRT[paramInt].setImageResource(R.drawable.gplt);
        imageViewLB[paramInt].setImageResource(R.drawable.gplt);
        imageViewLT[paramInt].setImageResource(R.drawable.gplt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2);
      return;
    case 659:
      imageView[paramInt].setImageResource(R.drawable.gpr3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3);
      return;
    case 658:
      imageView[paramInt].setImageResource(R.drawable.gpl3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3);
      return;
    case 657:
      imageView[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselect);
      return;
    case 656:
      imageView[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstart);
      return;
    case 655:
      imageView[paramInt].setImageResource(R.drawable.gpright);
      imageViewRB[paramInt].setImageResource(R.drawable.gpright);
      imageViewRT[paramInt].setImageResource(R.drawable.gpright);
      imageViewLB[paramInt].setImageResource(R.drawable.gpright);
      imageViewLT[paramInt].setImageResource(R.drawable.gpright);
      return;
    case 654:
      imageView[paramInt].setImageResource(R.drawable.gpleft);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleft);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleft);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleft);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleft);
      return;
    case 653:
      imageView[paramInt].setImageResource(R.drawable.gpdown);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdown);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdown);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdown);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdown);
      return;
    case 652:
      imageView[paramInt].setImageResource(R.drawable.gpup);
      imageViewRB[paramInt].setImageResource(R.drawable.gpup);
      imageViewRT[paramInt].setImageResource(R.drawable.gpup);
      imageViewLB[paramInt].setImageResource(R.drawable.gpup);
      imageViewLT[paramInt].setImageResource(R.drawable.gpup);
      return;
    case 651:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprb);
        imageViewRB[paramInt].setImageResource(R.drawable.gprb);
        imageViewRT[paramInt].setImageResource(R.drawable.gprb);
        imageViewLB[paramInt].setImageResource(R.drawable.gprb);
        imageViewLT[paramInt].setImageResource(R.drawable.gprb);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1);
      return;
    case 650:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplb);
        imageViewRB[paramInt].setImageResource(R.drawable.gplb);
        imageViewRT[paramInt].setImageResource(R.drawable.gplb);
        imageViewLB[paramInt].setImageResource(R.drawable.gplb);
        imageViewLT[paramInt].setImageResource(R.drawable.gplb);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1);
      return;
    case 649:
      imageView[paramInt].setImageResource(R.drawable.gpy);
      imageViewRB[paramInt].setImageResource(R.drawable.gpy);
      imageViewRT[paramInt].setImageResource(R.drawable.gpy);
      imageViewLB[paramInt].setImageResource(R.drawable.gpy);
      imageViewLT[paramInt].setImageResource(R.drawable.gpy);
      return;
    case 648:
      imageView[paramInt].setImageResource(R.drawable.gpx);
      imageViewRB[paramInt].setImageResource(R.drawable.gpx);
      imageViewRT[paramInt].setImageResource(R.drawable.gpx);
      imageViewLB[paramInt].setImageResource(R.drawable.gpx);
      imageViewLT[paramInt].setImageResource(R.drawable.gpx);
      return;
    case 647:
      imageView[paramInt].setImageResource(R.drawable.gpb);
      imageViewRB[paramInt].setImageResource(R.drawable.gpb);
      imageViewRT[paramInt].setImageResource(R.drawable.gpb);
      imageViewLB[paramInt].setImageResource(R.drawable.gpb);
      imageViewLT[paramInt].setImageResource(R.drawable.gpb);
      return;
    case 646:
      imageView[paramInt].setImageResource(R.drawable.gpa);
      imageViewRB[paramInt].setImageResource(R.drawable.gpa);
      imageViewRT[paramInt].setImageResource(R.drawable.gpa);
      imageViewLB[paramInt].setImageResource(R.drawable.gpa);
      imageViewLT[paramInt].setImageResource(R.drawable.gpa);
      return;
    case 641:
    case 642:
    case 643:
    case 644:
    case 645:
      imageView[paramInt].setImageResource(R.drawable.newkey);
      imageViewRB[paramInt].setImageResource(R.drawable.newkey);
      imageViewRT[paramInt].setImageResource(R.drawable.newkey);
      imageViewLB[paramInt].setImageResource(R.drawable.newkey);
      imageViewLT[paramInt].setImageResource(R.drawable.newkey);
      return;
    case 640:
      imageView[paramInt].setImageResource(R.drawable.center3);
      imageViewRB[paramInt].setImageResource(R.drawable.center3);
      imageViewRT[paramInt].setImageResource(R.drawable.center3);
      imageViewLB[paramInt].setImageResource(R.drawable.center3);
      imageViewLT[paramInt].setImageResource(R.drawable.center3);
      return;
    case 639:
      imageView[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr2);
      return;
    case 638:
      imageView[paramInt].setImageResource(R.drawable.center2);
      imageViewRB[paramInt].setImageResource(R.drawable.center2);
      imageViewRT[paramInt].setImageResource(R.drawable.center2);
      imageViewLB[paramInt].setImageResource(R.drawable.center2);
      imageViewLT[paramInt].setImageResource(R.drawable.center2);
      return;
    case 637:
      imageView[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr1);
      return;
    case 636:
      imageView[paramInt].setImageResource(R.drawable.center1);
      imageViewRB[paramInt].setImageResource(R.drawable.center1);
      imageViewRT[paramInt].setImageResource(R.drawable.center1);
      imageViewLB[paramInt].setImageResource(R.drawable.center1);
      imageViewLT[paramInt].setImageResource(R.drawable.center1);
      return;
    case 635:
      imageView[paramInt].setImageResource(R.drawable.gpm4);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4);
      return;
    case 634:
      imageView[paramInt].setImageResource(R.drawable.gpm3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3);
      return;
    case 633:
      imageView[paramInt].setImageResource(R.drawable.gpm2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2);
      return;
    case 632:
      imageView[paramInt].setImageResource(R.drawable.gpm1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1);
      return;
    case 631:
      imageView[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr);
      return;
    case 630:
      imageView[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpl);
      return;
    case 629:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprt);
        imageViewRB[paramInt].setImageResource(R.drawable.gprt);
        imageViewRT[paramInt].setImageResource(R.drawable.gprt);
        imageViewLB[paramInt].setImageResource(R.drawable.gprt);
        imageViewLT[paramInt].setImageResource(R.drawable.gprt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2);
      return;
    case 628:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplt);
        imageViewRB[paramInt].setImageResource(R.drawable.gplt);
        imageViewRT[paramInt].setImageResource(R.drawable.gplt);
        imageViewLB[paramInt].setImageResource(R.drawable.gplt);
        imageViewLT[paramInt].setImageResource(R.drawable.gplt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2);
      return;
    case 627:
      imageView[paramInt].setImageResource(R.drawable.gpr3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3);
      return;
    case 626:
      imageView[paramInt].setImageResource(R.drawable.gpl3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3);
      return;
    case 625:
      imageView[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselect);
      return;
    case 624:
      imageView[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstart);
      return;
    case 623:
      imageView[paramInt].setImageResource(R.drawable.gpright);
      imageViewRB[paramInt].setImageResource(R.drawable.gpright);
      imageViewRT[paramInt].setImageResource(R.drawable.gpright);
      imageViewLB[paramInt].setImageResource(R.drawable.gpright);
      imageViewLT[paramInt].setImageResource(R.drawable.gpright);
      return;
    case 622:
      imageView[paramInt].setImageResource(R.drawable.gpleft);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleft);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleft);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleft);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleft);
      return;
    case 621:
      imageView[paramInt].setImageResource(R.drawable.gpdown);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdown);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdown);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdown);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdown);
      return;
    case 620:
      imageView[paramInt].setImageResource(R.drawable.gpup);
      imageViewRB[paramInt].setImageResource(R.drawable.gpup);
      imageViewRT[paramInt].setImageResource(R.drawable.gpup);
      imageViewLB[paramInt].setImageResource(R.drawable.gpup);
      imageViewLT[paramInt].setImageResource(R.drawable.gpup);
      return;
    case 619:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprb);
        imageViewRB[paramInt].setImageResource(R.drawable.gprb);
        imageViewRT[paramInt].setImageResource(R.drawable.gprb);
        imageViewLB[paramInt].setImageResource(R.drawable.gprb);
        imageViewLT[paramInt].setImageResource(R.drawable.gprb);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1);
      return;
    case 618:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplb);
        imageViewRB[paramInt].setImageResource(R.drawable.gplb);
        imageViewRT[paramInt].setImageResource(R.drawable.gplb);
        imageViewLB[paramInt].setImageResource(R.drawable.gplb);
        imageViewLT[paramInt].setImageResource(R.drawable.gplb);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1);
      return;
    case 617:
      imageView[paramInt].setImageResource(R.drawable.gpy);
      imageViewRB[paramInt].setImageResource(R.drawable.gpy);
      imageViewRT[paramInt].setImageResource(R.drawable.gpy);
      imageViewLB[paramInt].setImageResource(R.drawable.gpy);
      imageViewLT[paramInt].setImageResource(R.drawable.gpy);
      return;
    case 616:
      imageView[paramInt].setImageResource(R.drawable.gpx);
      imageViewRB[paramInt].setImageResource(R.drawable.gpx);
      imageViewRT[paramInt].setImageResource(R.drawable.gpx);
      imageViewLB[paramInt].setImageResource(R.drawable.gpx);
      imageViewLT[paramInt].setImageResource(R.drawable.gpx);
      return;
    case 615:
      imageView[paramInt].setImageResource(R.drawable.gpb);
      imageViewRB[paramInt].setImageResource(R.drawable.gpb);
      imageViewRT[paramInt].setImageResource(R.drawable.gpb);
      imageViewLB[paramInt].setImageResource(R.drawable.gpb);
      imageViewLT[paramInt].setImageResource(R.drawable.gpb);
      return;
    case 614:
      imageView[paramInt].setImageResource(R.drawable.gpa);
      imageViewRB[paramInt].setImageResource(R.drawable.gpa);
      imageViewRT[paramInt].setImageResource(R.drawable.gpa);
      imageViewLB[paramInt].setImageResource(R.drawable.gpa);
      imageViewLT[paramInt].setImageResource(R.drawable.gpa);
      return;
    case 609:
    case 610:
    case 611:
    case 612:
    case 613:
      imageView[paramInt].setImageResource(R.drawable.newkey);
      imageViewRB[paramInt].setImageResource(R.drawable.newkey);
      imageViewRT[paramInt].setImageResource(R.drawable.newkey);
      imageViewLB[paramInt].setImageResource(R.drawable.newkey);
      imageViewLT[paramInt].setImageResource(R.drawable.newkey);
      return;
    case 608:
      imageView[paramInt].setImageResource(R.drawable.center3);
      imageViewRB[paramInt].setImageResource(R.drawable.center3);
      imageViewRT[paramInt].setImageResource(R.drawable.center3);
      imageViewLB[paramInt].setImageResource(R.drawable.center3);
      imageViewLT[paramInt].setImageResource(R.drawable.center3);
      return;
    case 607:
      imageView[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr2);
      return;
    case 606:
      imageView[paramInt].setImageResource(R.drawable.center2);
      imageViewRB[paramInt].setImageResource(R.drawable.center2);
      imageViewRT[paramInt].setImageResource(R.drawable.center2);
      imageViewLB[paramInt].setImageResource(R.drawable.center2);
      imageViewLT[paramInt].setImageResource(R.drawable.center2);
      return;
    case 605:
      imageView[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr1);
      return;
    case 604:
      imageView[paramInt].setImageResource(R.drawable.center1);
      imageViewRB[paramInt].setImageResource(R.drawable.center1);
      imageViewRT[paramInt].setImageResource(R.drawable.center1);
      imageViewLB[paramInt].setImageResource(R.drawable.center1);
      imageViewLT[paramInt].setImageResource(R.drawable.center1);
      return;
    case 603:
      imageView[paramInt].setImageResource(R.drawable.gpm4);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4);
      return;
    case 602:
      imageView[paramInt].setImageResource(R.drawable.gpm3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3);
      return;
    case 601:
      imageView[paramInt].setImageResource(R.drawable.gpm2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2);
      return;
    case 600:
      imageView[paramInt].setImageResource(R.drawable.gpm1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1);
      return;
    case 599:
      imageView[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr);
      return;
    case 598:
      imageView[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpl);
      return;
    case 597:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprt);
        imageViewRB[paramInt].setImageResource(R.drawable.gprt);
        imageViewRT[paramInt].setImageResource(R.drawable.gprt);
        imageViewLB[paramInt].setImageResource(R.drawable.gprt);
        imageViewLT[paramInt].setImageResource(R.drawable.gprt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2);
      return;
    case 596:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplt);
        imageViewRB[paramInt].setImageResource(R.drawable.gplt);
        imageViewRT[paramInt].setImageResource(R.drawable.gplt);
        imageViewLB[paramInt].setImageResource(R.drawable.gplt);
        imageViewLT[paramInt].setImageResource(R.drawable.gplt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2);
      return;
    case 595:
      imageView[paramInt].setImageResource(R.drawable.gpr3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3);
      return;
    case 594:
      imageView[paramInt].setImageResource(R.drawable.gpl3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3);
      return;
    case 593:
      imageView[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselect);
      return;
    case 592:
      imageView[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstart);
      return;
    case 591:
      imageView[paramInt].setImageResource(R.drawable.gpright);
      imageViewRB[paramInt].setImageResource(R.drawable.gpright);
      imageViewRT[paramInt].setImageResource(R.drawable.gpright);
      imageViewLB[paramInt].setImageResource(R.drawable.gpright);
      imageViewLT[paramInt].setImageResource(R.drawable.gpright);
      return;
    case 590:
      imageView[paramInt].setImageResource(R.drawable.gpleft);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleft);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleft);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleft);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleft);
      return;
    case 589:
      imageView[paramInt].setImageResource(R.drawable.gpdown);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdown);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdown);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdown);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdown);
      return;
    case 588:
      imageView[paramInt].setImageResource(R.drawable.gpup);
      imageViewRB[paramInt].setImageResource(R.drawable.gpup);
      imageViewRT[paramInt].setImageResource(R.drawable.gpup);
      imageViewLB[paramInt].setImageResource(R.drawable.gpup);
      imageViewLT[paramInt].setImageResource(R.drawable.gpup);
      return;
    case 587:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprb);
        imageViewRB[paramInt].setImageResource(R.drawable.gprb);
        imageViewRT[paramInt].setImageResource(R.drawable.gprb);
        imageViewLB[paramInt].setImageResource(R.drawable.gprb);
        imageViewLT[paramInt].setImageResource(R.drawable.gprb);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1);
      return;
    case 586:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplb);
        imageViewRB[paramInt].setImageResource(R.drawable.gplb);
        imageViewRT[paramInt].setImageResource(R.drawable.gplb);
        imageViewLB[paramInt].setImageResource(R.drawable.gplb);
        imageViewLT[paramInt].setImageResource(R.drawable.gplb);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1);
      return;
    case 585:
      imageView[paramInt].setImageResource(R.drawable.gpy);
      imageViewRB[paramInt].setImageResource(R.drawable.gpy);
      imageViewRT[paramInt].setImageResource(R.drawable.gpy);
      imageViewLB[paramInt].setImageResource(R.drawable.gpy);
      imageViewLT[paramInt].setImageResource(R.drawable.gpy);
      return;
    case 584:
      imageView[paramInt].setImageResource(R.drawable.gpx);
      imageViewRB[paramInt].setImageResource(R.drawable.gpx);
      imageViewRT[paramInt].setImageResource(R.drawable.gpx);
      imageViewLB[paramInt].setImageResource(R.drawable.gpx);
      imageViewLT[paramInt].setImageResource(R.drawable.gpx);
      return;
    case 583:
      imageView[paramInt].setImageResource(R.drawable.gpb);
      imageViewRB[paramInt].setImageResource(R.drawable.gpb);
      imageViewRT[paramInt].setImageResource(R.drawable.gpb);
      imageViewLB[paramInt].setImageResource(R.drawable.gpb);
      imageViewLT[paramInt].setImageResource(R.drawable.gpb);
      return;
    case 582:
      imageView[paramInt].setImageResource(R.drawable.gpa);
      imageViewRB[paramInt].setImageResource(R.drawable.gpa);
      imageViewRT[paramInt].setImageResource(R.drawable.gpa);
      imageViewLB[paramInt].setImageResource(R.drawable.gpa);
      imageViewLT[paramInt].setImageResource(R.drawable.gpa);
      return;
    case 577:
    case 578:
    case 579:
    case 580:
    case 581:
      imageView[paramInt].setImageResource(R.drawable.newkey);
      imageViewRB[paramInt].setImageResource(R.drawable.newkey);
      imageViewRT[paramInt].setImageResource(R.drawable.newkey);
      imageViewLB[paramInt].setImageResource(R.drawable.newkey);
      imageViewLT[paramInt].setImageResource(R.drawable.newkey);
      return;
    case 576:
      imageView[paramInt].setImageResource(R.drawable.center3);
      imageViewRB[paramInt].setImageResource(R.drawable.center3);
      imageViewRT[paramInt].setImageResource(R.drawable.center3);
      imageViewLB[paramInt].setImageResource(R.drawable.center3);
      imageViewLT[paramInt].setImageResource(R.drawable.center3);
      return;
    case 575:
      imageView[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr2);
      return;
    case 574:
      imageView[paramInt].setImageResource(R.drawable.center2);
      imageViewRB[paramInt].setImageResource(R.drawable.center2);
      imageViewRT[paramInt].setImageResource(R.drawable.center2);
      imageViewLB[paramInt].setImageResource(R.drawable.center2);
      imageViewLT[paramInt].setImageResource(R.drawable.center2);
      return;
    case 573:
      imageView[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr1);
      return;
    case 572:
      imageView[paramInt].setImageResource(R.drawable.center1);
      imageViewRB[paramInt].setImageResource(R.drawable.center1);
      imageViewRT[paramInt].setImageResource(R.drawable.center1);
      imageViewLB[paramInt].setImageResource(R.drawable.center1);
      imageViewLT[paramInt].setImageResource(R.drawable.center1);
      return;
    case 571:
      imageView[paramInt].setImageResource(R.drawable.gpm4);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4);
      return;
    case 570:
      imageView[paramInt].setImageResource(R.drawable.gpm3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3);
      return;
    case 569:
      imageView[paramInt].setImageResource(R.drawable.gpm2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2);
      return;
    case 568:
      imageView[paramInt].setImageResource(R.drawable.gpm1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1);
      return;
    case 567:
      imageView[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr);
      return;
    case 566:
      imageView[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpl);
      return;
    case 565:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprt);
        imageViewRB[paramInt].setImageResource(R.drawable.gprt);
        imageViewRT[paramInt].setImageResource(R.drawable.gprt);
        imageViewLB[paramInt].setImageResource(R.drawable.gprt);
        imageViewLT[paramInt].setImageResource(R.drawable.gprt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2);
      return;
    case 564:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplt);
        imageViewRB[paramInt].setImageResource(R.drawable.gplt);
        imageViewRT[paramInt].setImageResource(R.drawable.gplt);
        imageViewLB[paramInt].setImageResource(R.drawable.gplt);
        imageViewLT[paramInt].setImageResource(R.drawable.gplt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2);
      return;
    case 563:
      imageView[paramInt].setImageResource(R.drawable.gpr3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3);
      return;
    case 562:
      imageView[paramInt].setImageResource(R.drawable.gpl3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3);
      return;
    case 561:
      imageView[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselect);
      return;
    case 560:
      imageView[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstart);
      return;
    case 559:
      imageView[paramInt].setImageResource(R.drawable.gpright);
      imageViewRB[paramInt].setImageResource(R.drawable.gpright);
      imageViewRT[paramInt].setImageResource(R.drawable.gpright);
      imageViewLB[paramInt].setImageResource(R.drawable.gpright);
      imageViewLT[paramInt].setImageResource(R.drawable.gpright);
      return;
    case 558:
      imageView[paramInt].setImageResource(R.drawable.gpleft);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleft);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleft);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleft);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleft);
      return;
    case 557:
      imageView[paramInt].setImageResource(R.drawable.gpdown);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdown);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdown);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdown);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdown);
      return;
    case 556:
      imageView[paramInt].setImageResource(R.drawable.gpup);
      imageViewRB[paramInt].setImageResource(R.drawable.gpup);
      imageViewRT[paramInt].setImageResource(R.drawable.gpup);
      imageViewLB[paramInt].setImageResource(R.drawable.gpup);
      imageViewLT[paramInt].setImageResource(R.drawable.gpup);
      return;
    case 555:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprb);
        imageViewRB[paramInt].setImageResource(R.drawable.gprb);
        imageViewRT[paramInt].setImageResource(R.drawable.gprb);
        imageViewLB[paramInt].setImageResource(R.drawable.gprb);
        imageViewLT[paramInt].setImageResource(R.drawable.gprb);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1);
      return;
    case 554:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplb);
        imageViewRB[paramInt].setImageResource(R.drawable.gplb);
        imageViewRT[paramInt].setImageResource(R.drawable.gplb);
        imageViewLB[paramInt].setImageResource(R.drawable.gplb);
        imageViewLT[paramInt].setImageResource(R.drawable.gplb);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1);
      return;
    case 553:
      imageView[paramInt].setImageResource(R.drawable.gpy);
      imageViewRB[paramInt].setImageResource(R.drawable.gpy);
      imageViewRT[paramInt].setImageResource(R.drawable.gpy);
      imageViewLB[paramInt].setImageResource(R.drawable.gpy);
      imageViewLT[paramInt].setImageResource(R.drawable.gpy);
      return;
    case 552:
      imageView[paramInt].setImageResource(R.drawable.gpx);
      imageViewRB[paramInt].setImageResource(R.drawable.gpx);
      imageViewRT[paramInt].setImageResource(R.drawable.gpx);
      imageViewLB[paramInt].setImageResource(R.drawable.gpx);
      imageViewLT[paramInt].setImageResource(R.drawable.gpx);
      return;
    case 551:
      imageView[paramInt].setImageResource(R.drawable.gpb);
      imageViewRB[paramInt].setImageResource(R.drawable.gpb);
      imageViewRT[paramInt].setImageResource(R.drawable.gpb);
      imageViewLB[paramInt].setImageResource(R.drawable.gpb);
      imageViewLT[paramInt].setImageResource(R.drawable.gpb);
      return;
    case 550:
      imageView[paramInt].setImageResource(R.drawable.gpa);
      imageViewRB[paramInt].setImageResource(R.drawable.gpa);
      imageViewRT[paramInt].setImageResource(R.drawable.gpa);
      imageViewLB[paramInt].setImageResource(R.drawable.gpa);
      imageViewLT[paramInt].setImageResource(R.drawable.gpa);
      return;
    case 545:
    case 546:
    case 547:
    case 548:
    case 549:
      imageView[paramInt].setImageResource(R.drawable.newkey);
      imageViewRB[paramInt].setImageResource(R.drawable.newkey);
      imageViewRT[paramInt].setImageResource(R.drawable.newkey);
      imageViewLB[paramInt].setImageResource(R.drawable.newkey);
      imageViewLT[paramInt].setImageResource(R.drawable.newkey);
      return;
    case 544:
      imageView[paramInt].setImageResource(R.drawable.center3);
      imageViewRB[paramInt].setImageResource(R.drawable.center3);
      imageViewRT[paramInt].setImageResource(R.drawable.center3);
      imageViewLB[paramInt].setImageResource(R.drawable.center3);
      imageViewLT[paramInt].setImageResource(R.drawable.center3);
      return;
    case 543:
      imageView[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr2);
      return;
    case 542:
      imageView[paramInt].setImageResource(R.drawable.center2);
      imageViewRB[paramInt].setImageResource(R.drawable.center2);
      imageViewRT[paramInt].setImageResource(R.drawable.center2);
      imageViewLB[paramInt].setImageResource(R.drawable.center2);
      imageViewLT[paramInt].setImageResource(R.drawable.center2);
      return;
    case 541:
      imageView[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr1);
      return;
    case 540:
      imageView[paramInt].setImageResource(R.drawable.center1);
      imageViewRB[paramInt].setImageResource(R.drawable.center1);
      imageViewRT[paramInt].setImageResource(R.drawable.center1);
      imageViewLB[paramInt].setImageResource(R.drawable.center1);
      imageViewLT[paramInt].setImageResource(R.drawable.center1);
      return;
    case 539:
      imageView[paramInt].setImageResource(R.drawable.gpm4);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4);
      return;
    case 538:
      imageView[paramInt].setImageResource(R.drawable.gpm3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3);
      return;
    case 537:
      imageView[paramInt].setImageResource(R.drawable.gpm2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2);
      return;
    case 536:
      imageView[paramInt].setImageResource(R.drawable.gpm1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1);
      return;
    case 535:
      imageView[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr);
      return;
    case 534:
      imageView[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpl);
      return;
    case 533:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprt);
        imageViewRB[paramInt].setImageResource(R.drawable.gprt);
        imageViewRT[paramInt].setImageResource(R.drawable.gprt);
        imageViewLB[paramInt].setImageResource(R.drawable.gprt);
        imageViewLT[paramInt].setImageResource(R.drawable.gprt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2);
      return;
    case 532:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplt);
        imageViewRB[paramInt].setImageResource(R.drawable.gplt);
        imageViewRT[paramInt].setImageResource(R.drawable.gplt);
        imageViewLB[paramInt].setImageResource(R.drawable.gplt);
        imageViewLT[paramInt].setImageResource(R.drawable.gplt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2);
      return;
    case 531:
      imageView[paramInt].setImageResource(R.drawable.gpr3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3);
      return;
    case 530:
      imageView[paramInt].setImageResource(R.drawable.gpl3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3);
      return;
    case 529:
      imageView[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselect);
      return;
    case 528:
      imageView[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstart);
      return;
    case 527:
      imageView[paramInt].setImageResource(R.drawable.gpright);
      imageViewRB[paramInt].setImageResource(R.drawable.gpright);
      imageViewRT[paramInt].setImageResource(R.drawable.gpright);
      imageViewLB[paramInt].setImageResource(R.drawable.gpright);
      imageViewLT[paramInt].setImageResource(R.drawable.gpright);
      return;
    case 526:
      imageView[paramInt].setImageResource(R.drawable.gpleft);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleft);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleft);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleft);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleft);
      return;
    case 525:
      imageView[paramInt].setImageResource(R.drawable.gpdown);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdown);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdown);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdown);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdown);
      return;
    case 524:
      imageView[paramInt].setImageResource(R.drawable.gpup);
      imageViewRB[paramInt].setImageResource(R.drawable.gpup);
      imageViewRT[paramInt].setImageResource(R.drawable.gpup);
      imageViewLB[paramInt].setImageResource(R.drawable.gpup);
      imageViewLT[paramInt].setImageResource(R.drawable.gpup);
      return;
    case 523:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprb);
        imageViewRB[paramInt].setImageResource(R.drawable.gprb);
        imageViewRT[paramInt].setImageResource(R.drawable.gprb);
        imageViewLB[paramInt].setImageResource(R.drawable.gprb);
        imageViewLT[paramInt].setImageResource(R.drawable.gprb);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1);
      return;
    case 522:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplb);
        imageViewRB[paramInt].setImageResource(R.drawable.gplb);
        imageViewRT[paramInt].setImageResource(R.drawable.gplb);
        imageViewLB[paramInt].setImageResource(R.drawable.gplb);
        imageViewLT[paramInt].setImageResource(R.drawable.gplb);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1);
      return;
    case 521:
      imageView[paramInt].setImageResource(R.drawable.gpy);
      imageViewRB[paramInt].setImageResource(R.drawable.gpy);
      imageViewRT[paramInt].setImageResource(R.drawable.gpy);
      imageViewLB[paramInt].setImageResource(R.drawable.gpy);
      imageViewLT[paramInt].setImageResource(R.drawable.gpy);
      return;
    case 520:
      imageView[paramInt].setImageResource(R.drawable.gpx);
      imageViewRB[paramInt].setImageResource(R.drawable.gpx);
      imageViewRT[paramInt].setImageResource(R.drawable.gpx);
      imageViewLB[paramInt].setImageResource(R.drawable.gpx);
      imageViewLT[paramInt].setImageResource(R.drawable.gpx);
      return;
    case 519:
      imageView[paramInt].setImageResource(R.drawable.gpb);
      imageViewRB[paramInt].setImageResource(R.drawable.gpb);
      imageViewRT[paramInt].setImageResource(R.drawable.gpb);
      imageViewLB[paramInt].setImageResource(R.drawable.gpb);
      imageViewLT[paramInt].setImageResource(R.drawable.gpb);
      return;
    case 518:
      imageView[paramInt].setImageResource(R.drawable.gpa);
      imageViewRB[paramInt].setImageResource(R.drawable.gpa);
      imageViewRT[paramInt].setImageResource(R.drawable.gpa);
      imageViewLB[paramInt].setImageResource(R.drawable.gpa);
      imageViewLT[paramInt].setImageResource(R.drawable.gpa);
      return;
    case 513:
    case 514:
    case 515:
    case 516:
    case 517:
      imageView[paramInt].setImageResource(R.drawable.newkey);
      imageViewRB[paramInt].setImageResource(R.drawable.newkey);
      imageViewRT[paramInt].setImageResource(R.drawable.newkey);
      imageViewLB[paramInt].setImageResource(R.drawable.newkey);
      imageViewLT[paramInt].setImageResource(R.drawable.newkey);
      return;
    case 512:
      imageView[paramInt].setImageResource(R.drawable.center3);
      imageViewRB[paramInt].setImageResource(R.drawable.center3);
      imageViewRT[paramInt].setImageResource(R.drawable.center3);
      imageViewLB[paramInt].setImageResource(R.drawable.center3);
      imageViewLT[paramInt].setImageResource(R.drawable.center3);
      return;
    case 511:
      imageView[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr2);
      return;
    case 510:
      imageView[paramInt].setImageResource(R.drawable.center2);
      imageViewRB[paramInt].setImageResource(R.drawable.center2);
      imageViewRT[paramInt].setImageResource(R.drawable.center2);
      imageViewLB[paramInt].setImageResource(R.drawable.center2);
      imageViewLT[paramInt].setImageResource(R.drawable.center2);
      return;
    case 509:
      imageView[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr1);
      return;
    case 508:
      imageView[paramInt].setImageResource(R.drawable.center1);
      imageViewRB[paramInt].setImageResource(R.drawable.center1);
      imageViewRT[paramInt].setImageResource(R.drawable.center1);
      imageViewLB[paramInt].setImageResource(R.drawable.center1);
      imageViewLT[paramInt].setImageResource(R.drawable.center1);
      return;
    case 507:
      imageView[paramInt].setImageResource(R.drawable.gpm4);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4);
      return;
    case 506:
      imageView[paramInt].setImageResource(R.drawable.gpm3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3);
      return;
    case 505:
      imageView[paramInt].setImageResource(R.drawable.gpm2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2);
      return;
    case 504:
      imageView[paramInt].setImageResource(R.drawable.gpm1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1);
      return;
    case 503:
      imageView[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr);
      return;
    case 502:
      imageView[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpl);
      return;
    case 501:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprt);
        imageViewRB[paramInt].setImageResource(R.drawable.gprt);
        imageViewRT[paramInt].setImageResource(R.drawable.gprt);
        imageViewLB[paramInt].setImageResource(R.drawable.gprt);
        imageViewLT[paramInt].setImageResource(R.drawable.gprt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2);
      return;
    case 500:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplt);
        imageViewRB[paramInt].setImageResource(R.drawable.gplt);
        imageViewRT[paramInt].setImageResource(R.drawable.gplt);
        imageViewLB[paramInt].setImageResource(R.drawable.gplt);
        imageViewLT[paramInt].setImageResource(R.drawable.gplt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2);
      return;
    case 499:
      imageView[paramInt].setImageResource(R.drawable.gpr3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3);
      return;
    case 498:
      imageView[paramInt].setImageResource(R.drawable.gpl3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3);
      return;
    case 497:
      imageView[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselect);
      return;
    case 496:
      imageView[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstart);
      return;
    case 495:
      imageView[paramInt].setImageResource(R.drawable.gpright);
      imageViewRB[paramInt].setImageResource(R.drawable.gpright);
      imageViewRT[paramInt].setImageResource(R.drawable.gpright);
      imageViewLB[paramInt].setImageResource(R.drawable.gpright);
      imageViewLT[paramInt].setImageResource(R.drawable.gpright);
      return;
    case 494:
      imageView[paramInt].setImageResource(R.drawable.gpleft);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleft);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleft);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleft);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleft);
      return;
    case 493:
      imageView[paramInt].setImageResource(R.drawable.gpdown);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdown);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdown);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdown);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdown);
      return;
    case 492:
      imageView[paramInt].setImageResource(R.drawable.gpup);
      imageViewRB[paramInt].setImageResource(R.drawable.gpup);
      imageViewRT[paramInt].setImageResource(R.drawable.gpup);
      imageViewLB[paramInt].setImageResource(R.drawable.gpup);
      imageViewLT[paramInt].setImageResource(R.drawable.gpup);
      return;
    case 491:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprb);
        imageViewRB[paramInt].setImageResource(R.drawable.gprb);
        imageViewRT[paramInt].setImageResource(R.drawable.gprb);
        imageViewLB[paramInt].setImageResource(R.drawable.gprb);
        imageViewLT[paramInt].setImageResource(R.drawable.gprb);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1);
      return;
    case 490:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplb);
        imageViewRB[paramInt].setImageResource(R.drawable.gplb);
        imageViewRT[paramInt].setImageResource(R.drawable.gplb);
        imageViewLB[paramInt].setImageResource(R.drawable.gplb);
        imageViewLT[paramInt].setImageResource(R.drawable.gplb);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1);
      return;
    case 489:
      imageView[paramInt].setImageResource(R.drawable.gpy);
      imageViewRB[paramInt].setImageResource(R.drawable.gpy);
      imageViewRT[paramInt].setImageResource(R.drawable.gpy);
      imageViewLB[paramInt].setImageResource(R.drawable.gpy);
      imageViewLT[paramInt].setImageResource(R.drawable.gpy);
      return;
    case 488:
      imageView[paramInt].setImageResource(R.drawable.gpx);
      imageViewRB[paramInt].setImageResource(R.drawable.gpx);
      imageViewRT[paramInt].setImageResource(R.drawable.gpx);
      imageViewLB[paramInt].setImageResource(R.drawable.gpx);
      imageViewLT[paramInt].setImageResource(R.drawable.gpx);
      return;
    case 487:
      imageView[paramInt].setImageResource(R.drawable.gpb);
      imageViewRB[paramInt].setImageResource(R.drawable.gpb);
      imageViewRT[paramInt].setImageResource(R.drawable.gpb);
      imageViewLB[paramInt].setImageResource(R.drawable.gpb);
      imageViewLT[paramInt].setImageResource(R.drawable.gpb);
      return;
    case 486:
      imageView[paramInt].setImageResource(R.drawable.gpa);
      imageViewRB[paramInt].setImageResource(R.drawable.gpa);
      imageViewRT[paramInt].setImageResource(R.drawable.gpa);
      imageViewLB[paramInt].setImageResource(R.drawable.gpa);
      imageViewLT[paramInt].setImageResource(R.drawable.gpa);
      return;
    case 481:
    case 482:
    case 483:
    case 484:
    case 485:
      imageView[paramInt].setImageResource(R.drawable.newkey);
      imageViewRB[paramInt].setImageResource(R.drawable.newkey);
      imageViewRT[paramInt].setImageResource(R.drawable.newkey);
      imageViewLB[paramInt].setImageResource(R.drawable.newkey);
      imageViewLT[paramInt].setImageResource(R.drawable.newkey);
      return;
    case 480:
      imageView[paramInt].setImageResource(R.drawable.center3);
      imageViewRB[paramInt].setImageResource(R.drawable.center3);
      imageViewRT[paramInt].setImageResource(R.drawable.center3);
      imageViewLB[paramInt].setImageResource(R.drawable.center3);
      imageViewLT[paramInt].setImageResource(R.drawable.center3);
      return;
    case 479:
      imageView[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr2);
      return;
    case 478:
      imageView[paramInt].setImageResource(R.drawable.center2);
      imageViewRB[paramInt].setImageResource(R.drawable.center2);
      imageViewRT[paramInt].setImageResource(R.drawable.center2);
      imageViewLB[paramInt].setImageResource(R.drawable.center2);
      imageViewLT[paramInt].setImageResource(R.drawable.center2);
      return;
    case 477:
      imageView[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr1);
      return;
    case 476:
      imageView[paramInt].setImageResource(R.drawable.center1);
      imageViewRB[paramInt].setImageResource(R.drawable.center1);
      imageViewRT[paramInt].setImageResource(R.drawable.center1);
      imageViewLB[paramInt].setImageResource(R.drawable.center1);
      imageViewLT[paramInt].setImageResource(R.drawable.center1);
      return;
    case 475:
      imageView[paramInt].setImageResource(R.drawable.gpm4);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4);
      return;
    case 474:
      imageView[paramInt].setImageResource(R.drawable.gpm3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3);
      return;
    case 473:
      imageView[paramInt].setImageResource(R.drawable.gpm2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2);
      return;
    case 472:
      imageView[paramInt].setImageResource(R.drawable.gpm1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1);
      return;
    case 471:
      imageView[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr);
      return;
    case 470:
      imageView[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpl);
      return;
    case 469:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprt);
        imageViewRB[paramInt].setImageResource(R.drawable.gprt);
        imageViewRT[paramInt].setImageResource(R.drawable.gprt);
        imageViewLB[paramInt].setImageResource(R.drawable.gprt);
        imageViewLT[paramInt].setImageResource(R.drawable.gprt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2);
      return;
    case 468:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplt);
        imageViewRB[paramInt].setImageResource(R.drawable.gplt);
        imageViewRT[paramInt].setImageResource(R.drawable.gplt);
        imageViewLB[paramInt].setImageResource(R.drawable.gplt);
        imageViewLT[paramInt].setImageResource(R.drawable.gplt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2);
      return;
    case 467:
      imageView[paramInt].setImageResource(R.drawable.gpr3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3);
      return;
    case 466:
      imageView[paramInt].setImageResource(R.drawable.gpl3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3);
      return;
    case 465:
      imageView[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselect);
      return;
    case 464:
      imageView[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstart);
      return;
    case 463:
      imageView[paramInt].setImageResource(R.drawable.gpright);
      imageViewRB[paramInt].setImageResource(R.drawable.gpright);
      imageViewRT[paramInt].setImageResource(R.drawable.gpright);
      imageViewLB[paramInt].setImageResource(R.drawable.gpright);
      imageViewLT[paramInt].setImageResource(R.drawable.gpright);
      return;
    case 462:
      imageView[paramInt].setImageResource(R.drawable.gpleft);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleft);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleft);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleft);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleft);
      return;
    case 461:
      imageView[paramInt].setImageResource(R.drawable.gpdown);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdown);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdown);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdown);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdown);
      return;
    case 460:
      imageView[paramInt].setImageResource(R.drawable.gpup);
      imageViewRB[paramInt].setImageResource(R.drawable.gpup);
      imageViewRT[paramInt].setImageResource(R.drawable.gpup);
      imageViewLB[paramInt].setImageResource(R.drawable.gpup);
      imageViewLT[paramInt].setImageResource(R.drawable.gpup);
      return;
    case 459:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprb);
        imageViewRB[paramInt].setImageResource(R.drawable.gprb);
        imageViewRT[paramInt].setImageResource(R.drawable.gprb);
        imageViewLB[paramInt].setImageResource(R.drawable.gprb);
        imageViewLT[paramInt].setImageResource(R.drawable.gprb);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1);
      return;
    case 458:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplb);
        imageViewRB[paramInt].setImageResource(R.drawable.gplb);
        imageViewRT[paramInt].setImageResource(R.drawable.gplb);
        imageViewLB[paramInt].setImageResource(R.drawable.gplb);
        imageViewLT[paramInt].setImageResource(R.drawable.gplb);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1);
      return;
    case 457:
      imageView[paramInt].setImageResource(R.drawable.gpy);
      imageViewRB[paramInt].setImageResource(R.drawable.gpy);
      imageViewRT[paramInt].setImageResource(R.drawable.gpy);
      imageViewLB[paramInt].setImageResource(R.drawable.gpy);
      imageViewLT[paramInt].setImageResource(R.drawable.gpy);
      return;
    case 456:
      imageView[paramInt].setImageResource(R.drawable.gpx);
      imageViewRB[paramInt].setImageResource(R.drawable.gpx);
      imageViewRT[paramInt].setImageResource(R.drawable.gpx);
      imageViewLB[paramInt].setImageResource(R.drawable.gpx);
      imageViewLT[paramInt].setImageResource(R.drawable.gpx);
      return;
    case 455:
      imageView[paramInt].setImageResource(R.drawable.gpb);
      imageViewRB[paramInt].setImageResource(R.drawable.gpb);
      imageViewRT[paramInt].setImageResource(R.drawable.gpb);
      imageViewLB[paramInt].setImageResource(R.drawable.gpb);
      imageViewLT[paramInt].setImageResource(R.drawable.gpb);
      return;
    case 454:
      imageView[paramInt].setImageResource(R.drawable.gpa);
      imageViewRB[paramInt].setImageResource(R.drawable.gpa);
      imageViewRT[paramInt].setImageResource(R.drawable.gpa);
      imageViewLB[paramInt].setImageResource(R.drawable.gpa);
      imageViewLT[paramInt].setImageResource(R.drawable.gpa);
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
      imageView[paramInt].setImageResource(R.drawable.newkey);
      imageViewRB[paramInt].setImageResource(R.drawable.newkey);
      imageViewRT[paramInt].setImageResource(R.drawable.newkey);
      imageViewLB[paramInt].setImageResource(R.drawable.newkey);
      imageViewLT[paramInt].setImageResource(R.drawable.newkey);
      return;
    case 443:
      imageView[paramInt].setImageResource(R.drawable.gpm4);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4);
      return;
    case 442:
      imageView[paramInt].setImageResource(R.drawable.gpm3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3);
      return;
    case 441:
      imageView[paramInt].setImageResource(R.drawable.gpm2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2);
      return;
    case 440:
      imageView[paramInt].setImageResource(R.drawable.gpm1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1);
      return;
    case 439:
      imageView[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr);
      return;
    case 438:
      imageView[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpl);
      return;
    case 437:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprt);
        imageViewRB[paramInt].setImageResource(R.drawable.gprt);
        imageViewRT[paramInt].setImageResource(R.drawable.gprt);
        imageViewLB[paramInt].setImageResource(R.drawable.gprt);
        imageViewLT[paramInt].setImageResource(R.drawable.gprt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2);
      return;
    case 436:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplt);
        imageViewRB[paramInt].setImageResource(R.drawable.gplt);
        imageViewRT[paramInt].setImageResource(R.drawable.gplt);
        imageViewLB[paramInt].setImageResource(R.drawable.gplt);
        imageViewLT[paramInt].setImageResource(R.drawable.gplt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2);
      return;
    case 435:
      imageView[paramInt].setImageResource(R.drawable.gpr3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3);
      return;
    case 434:
      imageView[paramInt].setImageResource(R.drawable.gpl3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3);
      return;
    case 433:
      imageView[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselect);
      return;
    case 432:
      imageView[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstart);
      return;
    case 431:
      imageView[paramInt].setImageResource(R.drawable.gpright);
      imageViewRB[paramInt].setImageResource(R.drawable.gpright);
      imageViewRT[paramInt].setImageResource(R.drawable.gpright);
      imageViewLB[paramInt].setImageResource(R.drawable.gpright);
      imageViewLT[paramInt].setImageResource(R.drawable.gpright);
      return;
    case 430:
      imageView[paramInt].setImageResource(R.drawable.gpleft);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleft);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleft);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleft);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleft);
      return;
    case 429:
      imageView[paramInt].setImageResource(R.drawable.gpdown);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdown);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdown);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdown);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdown);
      return;
    case 428:
      imageView[paramInt].setImageResource(R.drawable.gpup);
      imageViewRB[paramInt].setImageResource(R.drawable.gpup);
      imageViewRT[paramInt].setImageResource(R.drawable.gpup);
      imageViewLB[paramInt].setImageResource(R.drawable.gpup);
      imageViewLT[paramInt].setImageResource(R.drawable.gpup);
      return;
    case 427:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprb);
        imageViewRB[paramInt].setImageResource(R.drawable.gprb);
        imageViewRT[paramInt].setImageResource(R.drawable.gprb);
        imageViewLB[paramInt].setImageResource(R.drawable.gprb);
        imageViewLT[paramInt].setImageResource(R.drawable.gprb);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1);
      return;
    case 426:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplb);
        imageViewRB[paramInt].setImageResource(R.drawable.gplb);
        imageViewRT[paramInt].setImageResource(R.drawable.gplb);
        imageViewLB[paramInt].setImageResource(R.drawable.gplb);
        imageViewLT[paramInt].setImageResource(R.drawable.gplb);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1);
      return;
    case 425:
      imageView[paramInt].setImageResource(R.drawable.gpy);
      imageViewRB[paramInt].setImageResource(R.drawable.gpy);
      imageViewRT[paramInt].setImageResource(R.drawable.gpy);
      imageViewLB[paramInt].setImageResource(R.drawable.gpy);
      imageViewLT[paramInt].setImageResource(R.drawable.gpy);
      return;
    case 424:
      imageView[paramInt].setImageResource(R.drawable.gpx);
      imageViewRB[paramInt].setImageResource(R.drawable.gpx);
      imageViewRT[paramInt].setImageResource(R.drawable.gpx);
      imageViewLB[paramInt].setImageResource(R.drawable.gpx);
      imageViewLT[paramInt].setImageResource(R.drawable.gpx);
      return;
    case 423:
      imageView[paramInt].setImageResource(R.drawable.gpb);
      imageViewRB[paramInt].setImageResource(R.drawable.gpb);
      imageViewRT[paramInt].setImageResource(R.drawable.gpb);
      imageViewLB[paramInt].setImageResource(R.drawable.gpb);
      imageViewLT[paramInt].setImageResource(R.drawable.gpb);
      return;
    case 422:
      imageView[paramInt].setImageResource(R.drawable.gpa);
      imageViewRB[paramInt].setImageResource(R.drawable.gpa);
      imageViewRT[paramInt].setImageResource(R.drawable.gpa);
      imageViewLB[paramInt].setImageResource(R.drawable.gpa);
      imageViewLT[paramInt].setImageResource(R.drawable.gpa);
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
      imageView[paramInt].setImageResource(R.drawable.newkey);
      imageViewRB[paramInt].setImageResource(R.drawable.newkey);
      imageViewRT[paramInt].setImageResource(R.drawable.newkey);
      imageViewLB[paramInt].setImageResource(R.drawable.newkey);
      imageViewLT[paramInt].setImageResource(R.drawable.newkey);
      return;
    case 411:
      imageView[paramInt].setImageResource(R.drawable.gpm4hp2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4hp2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4hp2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4hp2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4hp2);
      return;
    case 410:
      imageView[paramInt].setImageResource(R.drawable.gpm3hp2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3hp2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3hp2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3hp2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3hp2);
      return;
    case 409:
      imageView[paramInt].setImageResource(R.drawable.gpm2hp2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2hp2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2hp2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2hp2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2hp2);
      return;
    case 408:
      imageView[paramInt].setImageResource(R.drawable.gpm1hp2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1hp2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1hp2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1hp2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1hp2);
      return;
    case 407:
      imageView[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr);
      return;
    case 406:
      imageView[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpl);
      return;
    case 405:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprthp2);
        imageViewRB[paramInt].setImageResource(R.drawable.gprthp2);
        imageViewRT[paramInt].setImageResource(R.drawable.gprthp2);
        imageViewLB[paramInt].setImageResource(R.drawable.gprthp2);
        imageViewLT[paramInt].setImageResource(R.drawable.gprthp2);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2hp2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2hp2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2hp2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2hp2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2hp2);
      return;
    case 404:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplthp2);
        imageViewRB[paramInt].setImageResource(R.drawable.gplthp2);
        imageViewRT[paramInt].setImageResource(R.drawable.gplthp2);
        imageViewLB[paramInt].setImageResource(R.drawable.gplthp2);
        imageViewLT[paramInt].setImageResource(R.drawable.gplthp2);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2hp2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2hp2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2hp2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2hp2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2hp2);
      return;
    case 403:
      imageView[paramInt].setImageResource(R.drawable.gpr3hp2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3hp2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3hp2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3hp2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3hp2);
      return;
    case 402:
      imageView[paramInt].setImageResource(R.drawable.gpl3hp2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3hp2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3hp2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3hp2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3hp2);
      return;
    case 401:
      imageView[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselect);
      return;
    case 400:
      imageView[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstart);
      return;
    case 399:
      imageView[paramInt].setImageResource(R.drawable.gprighthp2);
      imageViewRB[paramInt].setImageResource(R.drawable.gprighthp2);
      imageViewRT[paramInt].setImageResource(R.drawable.gprighthp2);
      imageViewLB[paramInt].setImageResource(R.drawable.gprighthp2);
      imageViewLT[paramInt].setImageResource(R.drawable.gprighthp2);
      return;
    case 398:
      imageView[paramInt].setImageResource(R.drawable.gplefthp2);
      imageViewRB[paramInt].setImageResource(R.drawable.gplefthp2);
      imageViewRT[paramInt].setImageResource(R.drawable.gplefthp2);
      imageViewLB[paramInt].setImageResource(R.drawable.gplefthp2);
      imageViewLT[paramInt].setImageResource(R.drawable.gplefthp2);
      return;
    case 397:
      imageView[paramInt].setImageResource(R.drawable.gpdownhp2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdownhp2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdownhp2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdownhp2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdownhp2);
      return;
    case 396:
      imageView[paramInt].setImageResource(R.drawable.gpuphp2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpuphp2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpuphp2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpuphp2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpuphp2);
      return;
    case 395:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprbhp2);
        imageViewRB[paramInt].setImageResource(R.drawable.gprbhp2);
        imageViewRT[paramInt].setImageResource(R.drawable.gprbhp2);
        imageViewLB[paramInt].setImageResource(R.drawable.gprbhp2);
        imageViewLT[paramInt].setImageResource(R.drawable.gprbhp2);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1hp2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1hp2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1hp2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1hp2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1hp2);
      return;
    case 394:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplbhp2);
        imageViewRB[paramInt].setImageResource(R.drawable.gplbhp2);
        imageViewRT[paramInt].setImageResource(R.drawable.gplbhp2);
        imageViewLB[paramInt].setImageResource(R.drawable.gplbhp2);
        imageViewLT[paramInt].setImageResource(R.drawable.gplbhp2);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1hp2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1hp2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1hp2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1hp2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1hp2);
      return;
    case 393:
      imageView[paramInt].setImageResource(R.drawable.gpyhp2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpyhp2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpyhp2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpyhp2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpyhp2);
      return;
    case 392:
      imageView[paramInt].setImageResource(R.drawable.gpxhp2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxhp2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxhp2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxhp2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxhp2);
      return;
    case 391:
      imageView[paramInt].setImageResource(R.drawable.gpbhp2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbhp2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbhp2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbhp2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbhp2);
      return;
    case 390:
      imageView[paramInt].setImageResource(R.drawable.gpahp2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpahp2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpahp2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpahp2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpahp2);
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
      imageView[paramInt].setImageResource(R.drawable.newkey);
      imageViewRB[paramInt].setImageResource(R.drawable.newkey);
      imageViewRT[paramInt].setImageResource(R.drawable.newkey);
      imageViewLB[paramInt].setImageResource(R.drawable.newkey);
      imageViewLT[paramInt].setImageResource(R.drawable.newkey);
      return;
    case 379:
      imageView[paramInt].setImageResource(R.drawable.gpm4hp1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4hp1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4hp1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4hp1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4hp1);
      return;
    case 378:
      imageView[paramInt].setImageResource(R.drawable.gpm3hp1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3hp1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3hp1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3hp1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3hp1);
      return;
    case 377:
      imageView[paramInt].setImageResource(R.drawable.gpm2hp1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2hp1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2hp1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2hp1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2hp1);
      return;
    case 376:
      imageView[paramInt].setImageResource(R.drawable.gpm1hp1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1hp1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1hp1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1hp1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1hp1);
      return;
    case 375:
      imageView[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr);
      return;
    case 374:
      imageView[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpl);
      return;
    case 373:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprthp1);
        imageViewRB[paramInt].setImageResource(R.drawable.gprthp1);
        imageViewRT[paramInt].setImageResource(R.drawable.gprthp1);
        imageViewLB[paramInt].setImageResource(R.drawable.gprthp1);
        imageViewLT[paramInt].setImageResource(R.drawable.gprthp1);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2hp1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2hp1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2hp1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2hp1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2hp1);
      return;
    case 372:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplthp1);
        imageViewRB[paramInt].setImageResource(R.drawable.gplthp1);
        imageViewRT[paramInt].setImageResource(R.drawable.gplthp1);
        imageViewLB[paramInt].setImageResource(R.drawable.gplthp1);
        imageViewLT[paramInt].setImageResource(R.drawable.gplthp1);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2hp1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2hp1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2hp1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2hp1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2hp1);
      return;
    case 371:
      imageView[paramInt].setImageResource(R.drawable.gpr3hp1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3hp1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3hp1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3hp1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3hp1);
      return;
    case 370:
      imageView[paramInt].setImageResource(R.drawable.gpl3hp1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3hp1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3hp1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3hp1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3hp1);
      return;
    case 369:
      imageView[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselect);
      return;
    case 368:
      imageView[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstart);
      return;
    case 367:
      imageView[paramInt].setImageResource(R.drawable.gprighthp1);
      imageViewRB[paramInt].setImageResource(R.drawable.gprighthp1);
      imageViewRT[paramInt].setImageResource(R.drawable.gprighthp1);
      imageViewLB[paramInt].setImageResource(R.drawable.gprighthp1);
      imageViewLT[paramInt].setImageResource(R.drawable.gprighthp1);
      return;
    case 366:
      imageView[paramInt].setImageResource(R.drawable.gplefthp1);
      imageViewRB[paramInt].setImageResource(R.drawable.gplefthp1);
      imageViewRT[paramInt].setImageResource(R.drawable.gplefthp1);
      imageViewLB[paramInt].setImageResource(R.drawable.gplefthp1);
      imageViewLT[paramInt].setImageResource(R.drawable.gplefthp1);
      return;
    case 365:
      imageView[paramInt].setImageResource(R.drawable.gpdownhp1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdownhp1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdownhp1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdownhp1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdownhp1);
      return;
    case 364:
      imageView[paramInt].setImageResource(R.drawable.gpuphp1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpuphp1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpuphp1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpuphp1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpuphp1);
      return;
    case 363:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprbhp1);
        imageViewRB[paramInt].setImageResource(R.drawable.gprbhp1);
        imageViewRT[paramInt].setImageResource(R.drawable.gprbhp1);
        imageViewLB[paramInt].setImageResource(R.drawable.gprbhp1);
        imageViewLT[paramInt].setImageResource(R.drawable.gprbhp1);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1hp1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1hp1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1hp1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1hp1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1hp1);
      return;
    case 362:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplbhp1);
        imageViewRB[paramInt].setImageResource(R.drawable.gplbhp1);
        imageViewRT[paramInt].setImageResource(R.drawable.gplbhp1);
        imageViewLB[paramInt].setImageResource(R.drawable.gplbhp1);
        imageViewLT[paramInt].setImageResource(R.drawable.gplbhp1);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1hp1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1hp1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1hp1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1hp1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1hp1);
      return;
    case 361:
      imageView[paramInt].setImageResource(R.drawable.gpyhp1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpyhp1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpyhp1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpyhp1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpyhp1);
      return;
    case 360:
      imageView[paramInt].setImageResource(R.drawable.gpxhp1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxhp1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxhp1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxhp1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxhp1);
      return;
    case 359:
      imageView[paramInt].setImageResource(R.drawable.gpbhp1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbhp1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbhp1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbhp1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbhp1);
      return;
    case 358:
      imageView[paramInt].setImageResource(R.drawable.gpahp1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpahp1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpahp1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpahp1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpahp1);
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
      imageView[paramInt].setImageResource(R.drawable.newkey);
      imageViewRB[paramInt].setImageResource(R.drawable.newkey);
      imageViewRT[paramInt].setImageResource(R.drawable.newkey);
      imageViewLB[paramInt].setImageResource(R.drawable.newkey);
      imageViewLT[paramInt].setImageResource(R.drawable.newkey);
      return;
    case 347:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpm4com);
        imageViewRB[paramInt].setImageResource(R.drawable.gpm4comrb);
        imageViewRT[paramInt].setImageResource(R.drawable.gpm4comrt);
        imageViewLB[paramInt].setImageResource(R.drawable.gpm4comlb);
        imageViewLT[paramInt].setImageResource(R.drawable.gpm4comlt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpm4com);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4comr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4comr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4coml1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4coml2);
      return;
    case 346:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpm3com);
        imageViewRB[paramInt].setImageResource(R.drawable.gpm3comrb);
        imageViewRT[paramInt].setImageResource(R.drawable.gpm3comrt);
        imageViewLB[paramInt].setImageResource(R.drawable.gpm3comlb);
        imageViewLT[paramInt].setImageResource(R.drawable.gpm3comlt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpm3com);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3comr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3comr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3coml1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3coml2);
      return;
    case 345:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpm2com);
        imageViewRB[paramInt].setImageResource(R.drawable.gpm2comrb);
        imageViewRT[paramInt].setImageResource(R.drawable.gpm2comrt);
        imageViewLB[paramInt].setImageResource(R.drawable.gpm2comlb);
        imageViewLT[paramInt].setImageResource(R.drawable.gpm2comlt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpm2com);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2comr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2comr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2coml1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2coml2);
      return;
    case 344:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpm1com);
        imageViewRB[paramInt].setImageResource(R.drawable.gpm1comrb);
        imageViewRT[paramInt].setImageResource(R.drawable.gpm1comrt);
        imageViewLB[paramInt].setImageResource(R.drawable.gpm1comlb);
        imageViewLT[paramInt].setImageResource(R.drawable.gpm1comlt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpm1com);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1comr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1comr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1coml1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1coml2);
      return;
    case 343:
      imageView[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr);
      return;
    case 342:
      imageView[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpl);
      return;
    case 341:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprtcom);
        imageViewRB[paramInt].setImageResource(R.drawable.gprtcomrb);
        imageViewRT[paramInt].setImageResource(R.drawable.gprtcomrt);
        imageViewLB[paramInt].setImageResource(R.drawable.gprtcomlb);
        imageViewLT[paramInt].setImageResource(R.drawable.gprtcomlt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2com);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2comr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2comr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2coml1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2coml2);
      return;
    case 340:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpltcom);
        imageViewRB[paramInt].setImageResource(R.drawable.gpltcomrb);
        imageViewRT[paramInt].setImageResource(R.drawable.gpltcomrt);
        imageViewLB[paramInt].setImageResource(R.drawable.gpltcomlb);
        imageViewLT[paramInt].setImageResource(R.drawable.gpltcomlt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2com);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2comr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2comr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2coml1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2coml2);
      return;
    case 339:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpr3com);
        imageViewRB[paramInt].setImageResource(R.drawable.gpr3comrb);
        imageViewRT[paramInt].setImageResource(R.drawable.gpr3comrt);
        imageViewLB[paramInt].setImageResource(R.drawable.gpr3comlb);
        imageViewLT[paramInt].setImageResource(R.drawable.gpr3comlt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr3com);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3comr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3comr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3coml1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3coml2);
      return;
    case 338:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpl3com);
        imageViewRB[paramInt].setImageResource(R.drawable.gpl3comrb);
        imageViewRT[paramInt].setImageResource(R.drawable.gpl3comrt);
        imageViewLB[paramInt].setImageResource(R.drawable.gpl3comlb);
        imageViewLT[paramInt].setImageResource(R.drawable.gpl3comlt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl3com);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3comr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3comr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3coml1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3coml2);
      return;
    case 337:
      imageView[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselect);
      return;
    case 336:
      imageView[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstart);
      return;
    case 335:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprightcom);
        imageViewRB[paramInt].setImageResource(R.drawable.gprightcomrb);
        imageViewRT[paramInt].setImageResource(R.drawable.gprightcomrt);
        imageViewLB[paramInt].setImageResource(R.drawable.gprightcomlb);
        imageViewLT[paramInt].setImageResource(R.drawable.gprightcomlt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gprightcom);
      imageViewRB[paramInt].setImageResource(R.drawable.gprightcomr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gprightcomr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gprightcoml1);
      imageViewLT[paramInt].setImageResource(R.drawable.gprightcoml2);
      return;
    case 334:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpleftcom);
        imageViewRB[paramInt].setImageResource(R.drawable.gpleftcomrb);
        imageViewRT[paramInt].setImageResource(R.drawable.gpleftcomrt);
        imageViewLB[paramInt].setImageResource(R.drawable.gpleftcomlb);
        imageViewLT[paramInt].setImageResource(R.drawable.gpleftcomlt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpleftcom);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleftcomr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleftcomr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleftcoml1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleftcoml2);
      return;
    case 333:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpdowncom);
        imageViewRB[paramInt].setImageResource(R.drawable.gpdowncomrb);
        imageViewRT[paramInt].setImageResource(R.drawable.gpdowncomrt);
        imageViewLB[paramInt].setImageResource(R.drawable.gpdowncomlb);
        imageViewLT[paramInt].setImageResource(R.drawable.gpdowncomlt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpdowncom);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdowncomr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdowncomr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdowncoml1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdowncoml2);
      return;
    case 332:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpupcom);
        imageViewRB[paramInt].setImageResource(R.drawable.gpupcomrb);
        imageViewRT[paramInt].setImageResource(R.drawable.gpupcomrt);
        imageViewLB[paramInt].setImageResource(R.drawable.gpupcomlb);
        imageViewLT[paramInt].setImageResource(R.drawable.gpupcomlt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpupcom);
      imageViewRB[paramInt].setImageResource(R.drawable.gpupcomr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpupcomr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpupcoml1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpupcoml2);
      return;
    case 331:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprbcom);
        imageViewRB[paramInt].setImageResource(R.drawable.gprbcomrb);
        imageViewRT[paramInt].setImageResource(R.drawable.gprbcomrt);
        imageViewLB[paramInt].setImageResource(R.drawable.gprbcomlb);
        imageViewLT[paramInt].setImageResource(R.drawable.gprbcomlt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1com);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1comr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1comr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1coml1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1coml2);
      return;
    case 330:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplbcom);
        imageViewRB[paramInt].setImageResource(R.drawable.gplbcomrb);
        imageViewRT[paramInt].setImageResource(R.drawable.gplbcomrt);
        imageViewLB[paramInt].setImageResource(R.drawable.gplbcomlb);
        imageViewLT[paramInt].setImageResource(R.drawable.gplbcomlt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1com);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1comr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1comr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1coml1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1coml2);
      return;
    case 329:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpycom);
        imageViewRB[paramInt].setImageResource(R.drawable.gpycomrb);
        imageViewRT[paramInt].setImageResource(R.drawable.gpycomrt);
        imageViewLB[paramInt].setImageResource(R.drawable.gpycomlb);
        imageViewLT[paramInt].setImageResource(R.drawable.gpycomlt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpycom);
      imageViewRB[paramInt].setImageResource(R.drawable.gpycomr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpycomr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpycoml1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpycoml2);
      return;
    case 328:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpxcom);
        imageViewRB[paramInt].setImageResource(R.drawable.gpxcomrb);
        imageViewRT[paramInt].setImageResource(R.drawable.gpxcomrt);
        imageViewLB[paramInt].setImageResource(R.drawable.gpxcomlb);
        imageViewLT[paramInt].setImageResource(R.drawable.gpxcomlt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpxcom);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxcomr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxcomr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxcoml1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxcoml2);
      return;
    case 327:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpbcom);
        imageViewRB[paramInt].setImageResource(R.drawable.gpbcomrb);
        imageViewRT[paramInt].setImageResource(R.drawable.gpbcomrt);
        imageViewLB[paramInt].setImageResource(R.drawable.gpbcomlb);
        imageViewLT[paramInt].setImageResource(R.drawable.gpbcomlt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpbcom);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbcomr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbcomr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbcoml1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbcoml2);
      return;
    case 326:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpacom);
        imageViewRB[paramInt].setImageResource(R.drawable.gpacomrb);
        imageViewRT[paramInt].setImageResource(R.drawable.gpacomrt);
        imageViewLB[paramInt].setImageResource(R.drawable.gpacomlb);
        imageViewLT[paramInt].setImageResource(R.drawable.gpacomlt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpacom);
      imageViewRB[paramInt].setImageResource(R.drawable.gpacomr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpacomr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpacoml1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpacoml2);
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
      imageView[paramInt].setImageResource(R.drawable.newkey);
      imageViewRB[paramInt].setImageResource(R.drawable.newkey);
      imageViewRT[paramInt].setImageResource(R.drawable.newkey);
      imageViewLB[paramInt].setImageResource(R.drawable.newkey);
      imageViewLT[paramInt].setImageResource(R.drawable.newkey);
      return;
    case 315:
      imageView[paramInt].setImageResource(R.drawable.gpm4first);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4first);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4first);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4first);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4first);
      return;
    case 314:
      imageView[paramInt].setImageResource(R.drawable.gpm3first);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3first);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3first);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3first);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3first);
      return;
    case 313:
      imageView[paramInt].setImageResource(R.drawable.gpm2first);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2first);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2first);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2first);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2first);
      return;
    case 312:
      imageView[paramInt].setImageResource(R.drawable.gpm1first);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1first);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1first);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1first);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1first);
      return;
    case 311:
      imageView[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewRB[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewRT[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewLB[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewLT[paramInt].setImageResource(R.drawable.gplbfirst);
      return;
    case 310:
      imageView[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewRB[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewRT[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewLB[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewLT[paramInt].setImageResource(R.drawable.gplbfirst);
      return;
    case 309:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprtfirst);
        imageViewRB[paramInt].setImageResource(R.drawable.gprtfirst);
        imageViewRT[paramInt].setImageResource(R.drawable.gprtfirst);
        imageViewLB[paramInt].setImageResource(R.drawable.gprtfirst);
        imageViewLT[paramInt].setImageResource(R.drawable.gprtfirst);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2first);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2first);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2first);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2first);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2first);
      return;
    case 308:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpltfirst);
        imageViewRB[paramInt].setImageResource(R.drawable.gpltfirst);
        imageViewRT[paramInt].setImageResource(R.drawable.gpltfirst);
        imageViewLB[paramInt].setImageResource(R.drawable.gpltfirst);
        imageViewLT[paramInt].setImageResource(R.drawable.gpltfirst);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2first);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2first);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2first);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2first);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2first);
      return;
    case 307:
      imageView[paramInt].setImageResource(R.drawable.gpr3first);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3first);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3first);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3first);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3first);
      return;
    case 306:
      imageView[paramInt].setImageResource(R.drawable.gpl3first);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3first);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3first);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3first);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3first);
      return;
    case 305:
      imageView[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewRB[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewRT[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewLB[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewLT[paramInt].setImageResource(R.drawable.gplbfirst);
      return;
    case 304:
      imageView[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewRB[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewRT[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewLB[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewLT[paramInt].setImageResource(R.drawable.gplbfirst);
      return;
    case 303:
      imageView[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewRB[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewRT[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewLB[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewLT[paramInt].setImageResource(R.drawable.gplbfirst);
      return;
    case 302:
      imageView[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewRB[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewRT[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewLB[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewLT[paramInt].setImageResource(R.drawable.gplbfirst);
      return;
    case 301:
      imageView[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewRB[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewRT[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewLB[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewLT[paramInt].setImageResource(R.drawable.gplbfirst);
      return;
    case 300:
      imageView[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewRB[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewRT[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewLB[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewLT[paramInt].setImageResource(R.drawable.gplbfirst);
      return;
    case 299:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprbfirst);
        imageViewRB[paramInt].setImageResource(R.drawable.gprbfirst);
        imageViewRT[paramInt].setImageResource(R.drawable.gprbfirst);
        imageViewLB[paramInt].setImageResource(R.drawable.gprbfirst);
        imageViewLT[paramInt].setImageResource(R.drawable.gprbfirst);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1first);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1first);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1first);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1first);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1first);
      return;
    case 298:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplbfirst);
        imageViewRB[paramInt].setImageResource(R.drawable.gplbfirst);
        imageViewRT[paramInt].setImageResource(R.drawable.gplbfirst);
        imageViewLB[paramInt].setImageResource(R.drawable.gplbfirst);
        imageViewLT[paramInt].setImageResource(R.drawable.gplbfirst);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1first);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1first);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1first);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1first);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1first);
      return;
    case 297:
      imageView[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewRB[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewRT[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewLB[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewLT[paramInt].setImageResource(R.drawable.gplbfirst);
      return;
    case 296:
      imageView[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewRB[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewRT[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewLB[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewLT[paramInt].setImageResource(R.drawable.gplbfirst);
      return;
    case 295:
      imageView[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewRB[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewRT[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewLB[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewLT[paramInt].setImageResource(R.drawable.gplbfirst);
      return;
    case 294:
      imageView[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewRB[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewRT[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewLB[paramInt].setImageResource(R.drawable.gplbfirst);
      imageViewLT[paramInt].setImageResource(R.drawable.gplbfirst);
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
      imageView[paramInt].setImageResource(R.drawable.newkey);
      imageViewRB[paramInt].setImageResource(R.drawable.newkey);
      imageViewRT[paramInt].setImageResource(R.drawable.newkey);
      imageViewLB[paramInt].setImageResource(R.drawable.newkey);
      imageViewLT[paramInt].setImageResource(R.drawable.newkey);
      return;
    case 283:
      imageView[paramInt].setImageResource(R.drawable.gpm4rview);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4rview);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4rview);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4rview);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4rview);
      return;
    case 282:
      imageView[paramInt].setImageResource(R.drawable.gpm3rview);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3rview);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3rview);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3rview);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3rview);
      return;
    case 281:
      imageView[paramInt].setImageResource(R.drawable.gpm2rview);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2rview);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2rview);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2rview);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2rview);
      return;
    case 280:
      imageView[paramInt].setImageResource(R.drawable.gpm1rview);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1rview);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1rview);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1rview);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1rview);
      return;
    case 279:
      imageView[paramInt].setImageResource(R.drawable.gparview);
      imageViewRB[paramInt].setImageResource(R.drawable.gparview);
      imageViewRT[paramInt].setImageResource(R.drawable.gparview);
      imageViewLB[paramInt].setImageResource(R.drawable.gparview);
      imageViewLT[paramInt].setImageResource(R.drawable.gparview);
      return;
    case 278:
      imageView[paramInt].setImageResource(R.drawable.gparview);
      imageViewRB[paramInt].setImageResource(R.drawable.gparview);
      imageViewRT[paramInt].setImageResource(R.drawable.gparview);
      imageViewLB[paramInt].setImageResource(R.drawable.gparview);
      imageViewLT[paramInt].setImageResource(R.drawable.gparview);
      return;
    case 277:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprtview);
        imageViewRB[paramInt].setImageResource(R.drawable.gprtview);
        imageViewRT[paramInt].setImageResource(R.drawable.gprtview);
        imageViewLB[paramInt].setImageResource(R.drawable.gprtview);
        imageViewLT[paramInt].setImageResource(R.drawable.gprtview);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2rview);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2rview);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2rview);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2rview);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2rview);
      return;
    case 276:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpltview);
        imageViewRB[paramInt].setImageResource(R.drawable.gpltview);
        imageViewRT[paramInt].setImageResource(R.drawable.gpltview);
        imageViewLB[paramInt].setImageResource(R.drawable.gpltview);
        imageViewLT[paramInt].setImageResource(R.drawable.gpltview);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2rview);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2rview);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2rview);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2rview);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2rview);
      return;
    case 275:
      imageView[paramInt].setImageResource(R.drawable.gpr3rview);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3rview);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3rview);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3rview);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3rview);
      return;
    case 274:
      imageView[paramInt].setImageResource(R.drawable.gpl3rview);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3rview);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3rview);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3rview);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3rview);
      return;
    case 273:
      imageView[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselect);
      return;
    case 272:
      imageView[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstart);
      return;
    case 271:
      imageView[paramInt].setImageResource(R.drawable.gparview);
      imageViewRB[paramInt].setImageResource(R.drawable.gparview);
      imageViewRT[paramInt].setImageResource(R.drawable.gparview);
      imageViewLB[paramInt].setImageResource(R.drawable.gparview);
      imageViewLT[paramInt].setImageResource(R.drawable.gparview);
      return;
    case 270:
      imageView[paramInt].setImageResource(R.drawable.gparview);
      imageViewRB[paramInt].setImageResource(R.drawable.gparview);
      imageViewRT[paramInt].setImageResource(R.drawable.gparview);
      imageViewLB[paramInt].setImageResource(R.drawable.gparview);
      imageViewLT[paramInt].setImageResource(R.drawable.gparview);
      return;
    case 269:
      imageView[paramInt].setImageResource(R.drawable.gparview);
      imageViewRB[paramInt].setImageResource(R.drawable.gparview);
      imageViewRT[paramInt].setImageResource(R.drawable.gparview);
      imageViewLB[paramInt].setImageResource(R.drawable.gparview);
      imageViewLT[paramInt].setImageResource(R.drawable.gparview);
      return;
    case 268:
      imageView[paramInt].setImageResource(R.drawable.gparview);
      imageViewRB[paramInt].setImageResource(R.drawable.gparview);
      imageViewRT[paramInt].setImageResource(R.drawable.gparview);
      imageViewLB[paramInt].setImageResource(R.drawable.gparview);
      imageViewLT[paramInt].setImageResource(R.drawable.gparview);
      return;
    case 267:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprbview);
        imageViewRB[paramInt].setImageResource(R.drawable.gprbview);
        imageViewRT[paramInt].setImageResource(R.drawable.gprbview);
        imageViewLB[paramInt].setImageResource(R.drawable.gprbview);
        imageViewLT[paramInt].setImageResource(R.drawable.gprbview);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1rview);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1rview);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1rview);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1rview);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1rview);
      return;
    case 266:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplbview);
        imageViewRB[paramInt].setImageResource(R.drawable.gplbview);
        imageViewRT[paramInt].setImageResource(R.drawable.gplbview);
        imageViewLB[paramInt].setImageResource(R.drawable.gplbview);
        imageViewLT[paramInt].setImageResource(R.drawable.gplbview);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1rview);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1rview);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1rview);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1rview);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1rview);
      return;
    case 265:
      imageView[paramInt].setImageResource(R.drawable.gpyrview);
      imageViewRB[paramInt].setImageResource(R.drawable.gpyrview);
      imageViewRT[paramInt].setImageResource(R.drawable.gpyrview);
      imageViewLB[paramInt].setImageResource(R.drawable.gpyrview);
      imageViewLT[paramInt].setImageResource(R.drawable.gpyrview);
      return;
    case 264:
      imageView[paramInt].setImageResource(R.drawable.gpxrview);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxrview);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxrview);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxrview);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxrview);
      return;
    case 263:
      imageView[paramInt].setImageResource(R.drawable.gpbrview);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbrview);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbrview);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbrview);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbrview);
      return;
    case 262:
      imageView[paramInt].setImageResource(R.drawable.gparview);
      imageViewRB[paramInt].setImageResource(R.drawable.gparview);
      imageViewRT[paramInt].setImageResource(R.drawable.gparview);
      imageViewLB[paramInt].setImageResource(R.drawable.gparview);
      imageViewLT[paramInt].setImageResource(R.drawable.gparview);
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
      imageView[paramInt].setImageResource(R.drawable.newkey);
      imageViewRB[paramInt].setImageResource(R.drawable.newkey);
      imageViewRT[paramInt].setImageResource(R.drawable.newkey);
      imageViewLB[paramInt].setImageResource(R.drawable.newkey);
      imageViewLT[paramInt].setImageResource(R.drawable.newkey);
      return;
    case 251:
      imageView[paramInt].setImageResource(R.drawable.gpm4y2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4y2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4y2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4y2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4y2);
      return;
    case 250:
      imageView[paramInt].setImageResource(R.drawable.gpm3y2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3y2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3y2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3y2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3y2);
      return;
    case 249:
      imageView[paramInt].setImageResource(R.drawable.gpm2y2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2y2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2y2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2y2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2y2);
      return;
    case 248:
      imageView[paramInt].setImageResource(R.drawable.gpm1y2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1y2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1y2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1y2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1y2);
      return;
    case 247:
      imageView[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr);
      return;
    case 246:
      imageView[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpl);
      return;
    case 245:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprty2);
        imageViewRB[paramInt].setImageResource(R.drawable.gprty2);
        imageViewRT[paramInt].setImageResource(R.drawable.gprty2);
        imageViewLB[paramInt].setImageResource(R.drawable.gprty2);
        imageViewLT[paramInt].setImageResource(R.drawable.gprty2);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2y2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2y2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2y2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2y2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2y2);
      return;
    case 244:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplty2);
        imageViewRB[paramInt].setImageResource(R.drawable.gplty2);
        imageViewRT[paramInt].setImageResource(R.drawable.gplty2);
        imageViewLB[paramInt].setImageResource(R.drawable.gplty2);
        imageViewLT[paramInt].setImageResource(R.drawable.gplty2);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2y2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2y2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2y2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2y2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2y2);
      return;
    case 243:
      imageView[paramInt].setImageResource(R.drawable.gpr3y2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3y2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3y2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3y2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3y2);
      return;
    case 242:
      imageView[paramInt].setImageResource(R.drawable.gpl3y2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3y2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3y2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3y2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3y2);
      return;
    case 241:
      imageView[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselect);
      return;
    case 240:
      imageView[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstart);
      return;
    case 239:
      imageView[paramInt].setImageResource(R.drawable.gprighty2);
      imageViewRB[paramInt].setImageResource(R.drawable.gprighty2);
      imageViewRT[paramInt].setImageResource(R.drawable.gprighty2);
      imageViewLB[paramInt].setImageResource(R.drawable.gprighty2);
      imageViewLT[paramInt].setImageResource(R.drawable.gprighty2);
      return;
    case 238:
      imageView[paramInt].setImageResource(R.drawable.gplefty2);
      imageViewRB[paramInt].setImageResource(R.drawable.gplefty2);
      imageViewRT[paramInt].setImageResource(R.drawable.gplefty2);
      imageViewLB[paramInt].setImageResource(R.drawable.gplefty2);
      imageViewLT[paramInt].setImageResource(R.drawable.gplefty2);
      return;
    case 237:
      imageView[paramInt].setImageResource(R.drawable.gpdowny2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdowny2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdowny2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdowny2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdowny2);
      return;
    case 236:
      imageView[paramInt].setImageResource(R.drawable.gpupy2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpupy2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpupy2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpupy2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpupy2);
      return;
    case 235:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprby2);
        imageViewRB[paramInt].setImageResource(R.drawable.gprby2);
        imageViewRT[paramInt].setImageResource(R.drawable.gprby2);
        imageViewLB[paramInt].setImageResource(R.drawable.gprby2);
        imageViewLT[paramInt].setImageResource(R.drawable.gprby2);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1y2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1y2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1y2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1y2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1y2);
      return;
    case 234:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplby2);
        imageViewRB[paramInt].setImageResource(R.drawable.gplby2);
        imageViewRT[paramInt].setImageResource(R.drawable.gplby2);
        imageViewLB[paramInt].setImageResource(R.drawable.gplby2);
        imageViewLT[paramInt].setImageResource(R.drawable.gplby2);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1y2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1y2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1y2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1y2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1y2);
      return;
    case 233:
      imageView[paramInt].setImageResource(R.drawable.gpyy2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpyy2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpyy2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpyy2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpyy2);
      return;
    case 232:
      imageView[paramInt].setImageResource(R.drawable.gpxy2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxy2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxy2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxy2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxy2);
      return;
    case 231:
      imageView[paramInt].setImageResource(R.drawable.gpby2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpby2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpby2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpby2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpby2);
      return;
    case 230:
      imageView[paramInt].setImageResource(R.drawable.gpay2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpay2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpay2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpay2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpay2);
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
      imageView[paramInt].setImageResource(R.drawable.newkey);
      imageViewRB[paramInt].setImageResource(R.drawable.newkey);
      imageViewRT[paramInt].setImageResource(R.drawable.newkey);
      imageViewLB[paramInt].setImageResource(R.drawable.newkey);
      imageViewLT[paramInt].setImageResource(R.drawable.newkey);
      return;
    case 219:
      imageView[paramInt].setImageResource(R.drawable.gpm4y1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4y1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4y1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4y1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4y1);
      return;
    case 218:
      imageView[paramInt].setImageResource(R.drawable.gpm3y1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3y1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3y1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3y1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3y1);
      return;
    case 217:
      imageView[paramInt].setImageResource(R.drawable.gpm2y1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2y1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2y1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2y1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2y1);
      return;
    case 216:
      imageView[paramInt].setImageResource(R.drawable.gpm1y1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1y1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1y1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1y1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1y1);
      return;
    case 215:
      imageView[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr);
      return;
    case 214:
      imageView[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpl);
      return;
    case 213:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprty1);
        imageViewRB[paramInt].setImageResource(R.drawable.gprty1);
        imageViewRT[paramInt].setImageResource(R.drawable.gprty1);
        imageViewLB[paramInt].setImageResource(R.drawable.gprty1);
        imageViewLT[paramInt].setImageResource(R.drawable.gprty1);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2y1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2y1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2y1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2y1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2y1);
      return;
    case 212:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplty1);
        imageViewRB[paramInt].setImageResource(R.drawable.gplty1);
        imageViewRT[paramInt].setImageResource(R.drawable.gplty1);
        imageViewLB[paramInt].setImageResource(R.drawable.gplty1);
        imageViewLT[paramInt].setImageResource(R.drawable.gplty1);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2y1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2y1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2y1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2y1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2y1);
      return;
    case 211:
      imageView[paramInt].setImageResource(R.drawable.gpr3y1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3y1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3y1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3y1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3y1);
      return;
    case 210:
      imageView[paramInt].setImageResource(R.drawable.gpl3y1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3y1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3y1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3y1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3y1);
      return;
    case 209:
      imageView[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselect);
      return;
    case 208:
      imageView[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstart);
      return;
    case 207:
      imageView[paramInt].setImageResource(R.drawable.gprighty1);
      imageViewRB[paramInt].setImageResource(R.drawable.gprighty1);
      imageViewRT[paramInt].setImageResource(R.drawable.gprighty1);
      imageViewLB[paramInt].setImageResource(R.drawable.gprighty1);
      imageViewLT[paramInt].setImageResource(R.drawable.gprighty1);
      return;
    case 206:
      imageView[paramInt].setImageResource(R.drawable.gplefty1);
      imageViewRB[paramInt].setImageResource(R.drawable.gplefty1);
      imageViewRT[paramInt].setImageResource(R.drawable.gplefty1);
      imageViewLB[paramInt].setImageResource(R.drawable.gplefty1);
      imageViewLT[paramInt].setImageResource(R.drawable.gplefty1);
      return;
    case 205:
      imageView[paramInt].setImageResource(R.drawable.gpdowny1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdowny1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdowny1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdowny1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdowny1);
      return;
    case 204:
      imageView[paramInt].setImageResource(R.drawable.gpupy1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpupy1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpupy1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpupy1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpupy1);
      return;
    case 203:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprby1);
        imageViewRB[paramInt].setImageResource(R.drawable.gprby1);
        imageViewRT[paramInt].setImageResource(R.drawable.gprby1);
        imageViewLB[paramInt].setImageResource(R.drawable.gprby1);
        imageViewLT[paramInt].setImageResource(R.drawable.gprby1);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1y1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1y1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1y1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1y1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1y1);
      return;
    case 202:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplby1);
        imageViewRB[paramInt].setImageResource(R.drawable.gplby1);
        imageViewRT[paramInt].setImageResource(R.drawable.gplby1);
        imageViewLB[paramInt].setImageResource(R.drawable.gplby1);
        imageViewLT[paramInt].setImageResource(R.drawable.gplby1);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1y1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1y1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1y1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1y1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1y1);
      return;
    case 201:
      imageView[paramInt].setImageResource(R.drawable.gpyy1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpyy1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpyy1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpyy1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpyy1);
      return;
    case 200:
      imageView[paramInt].setImageResource(R.drawable.gpxy1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxy1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxy1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxy1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxy1);
      return;
    case 199:
      imageView[paramInt].setImageResource(R.drawable.gpby1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpby1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpby1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpby1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpby1);
      return;
    case 198:
      imageView[paramInt].setImageResource(R.drawable.gpay1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpay1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpay1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpay1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpay1);
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
      imageView[paramInt].setImageResource(R.drawable.newkey);
      imageViewRB[paramInt].setImageResource(R.drawable.newkey);
      imageViewRT[paramInt].setImageResource(R.drawable.newkey);
      imageViewLB[paramInt].setImageResource(R.drawable.newkey);
      imageViewLT[paramInt].setImageResource(R.drawable.newkey);
      return;
    case 187:
      imageView[paramInt].setImageResource(R.drawable.gpm42);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm42);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm42);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm42);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm42);
      return;
    case 186:
      imageView[paramInt].setImageResource(R.drawable.gpm32);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm32);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm32);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm32);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm32);
      return;
    case 185:
      imageView[paramInt].setImageResource(R.drawable.gpm22);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm22);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm22);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm22);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm22);
      return;
    case 184:
      imageView[paramInt].setImageResource(R.drawable.gpm12);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm12);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm12);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm12);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm12);
      return;
    case 183:
      imageView[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr);
      return;
    case 182:
      imageView[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpl);
      return;
    case 181:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprt2);
        imageViewRB[paramInt].setImageResource(R.drawable.gprt2);
        imageViewRT[paramInt].setImageResource(R.drawable.gprt2);
        imageViewLB[paramInt].setImageResource(R.drawable.gprt2);
        imageViewLT[paramInt].setImageResource(R.drawable.gprt2);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr22);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr22);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr22);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr22);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr22);
      return;
    case 180:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplt2);
        imageViewRB[paramInt].setImageResource(R.drawable.gplt2);
        imageViewRT[paramInt].setImageResource(R.drawable.gplt2);
        imageViewLB[paramInt].setImageResource(R.drawable.gplt2);
        imageViewLT[paramInt].setImageResource(R.drawable.gplt2);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl22);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl22);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl22);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl22);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl22);
      return;
    case 179:
      imageView[paramInt].setImageResource(R.drawable.gpr32);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr32);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr32);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr32);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr32);
      return;
    case 178:
      imageView[paramInt].setImageResource(R.drawable.gpl32);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl32);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl32);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl32);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl32);
      return;
    case 177:
      imageView[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselect);
      return;
    case 176:
      imageView[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstart);
      return;
    case 175:
      imageView[paramInt].setImageResource(R.drawable.gpright2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpright2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpright2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpright2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpright2);
      return;
    case 174:
      imageView[paramInt].setImageResource(R.drawable.gpleft2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleft2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleft2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleft2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleft2);
      return;
    case 173:
      imageView[paramInt].setImageResource(R.drawable.gpdown2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdown2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdown2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdown2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdown2);
      return;
    case 172:
      imageView[paramInt].setImageResource(R.drawable.gpup2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpup2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpup2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpup2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpup2);
      return;
    case 171:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprb2);
        imageViewRB[paramInt].setImageResource(R.drawable.gprb2);
        imageViewRT[paramInt].setImageResource(R.drawable.gprb2);
        imageViewLB[paramInt].setImageResource(R.drawable.gprb2);
        imageViewLT[paramInt].setImageResource(R.drawable.gprb2);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr12);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr12);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr12);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr12);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr12);
      return;
    case 170:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplb2);
        imageViewRB[paramInt].setImageResource(R.drawable.gplb2);
        imageViewRT[paramInt].setImageResource(R.drawable.gplb2);
        imageViewLB[paramInt].setImageResource(R.drawable.gplb2);
        imageViewLT[paramInt].setImageResource(R.drawable.gplb2);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl12);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl12);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl12);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl12);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl12);
      return;
    case 169:
      imageView[paramInt].setImageResource(R.drawable.gpy2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpy2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpy2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpy2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpy2);
      return;
    case 168:
      imageView[paramInt].setImageResource(R.drawable.gpx2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpx2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpx2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpx2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpx2);
      return;
    case 167:
      imageView[paramInt].setImageResource(R.drawable.gpbtn2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbtn2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbtn2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbtn2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbtn2);
      return;
    case 166:
      imageView[paramInt].setImageResource(R.drawable.gpa2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpa2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpa2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpa2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpa2);
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
      imageView[paramInt].setImageResource(R.drawable.newkey);
      imageViewRB[paramInt].setImageResource(R.drawable.newkey);
      imageViewRT[paramInt].setImageResource(R.drawable.newkey);
      imageViewLB[paramInt].setImageResource(R.drawable.newkey);
      imageViewLT[paramInt].setImageResource(R.drawable.newkey);
      return;
    case 155:
      imageView[paramInt].setImageResource(R.drawable.gpm41);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm41);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm41);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm41);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm41);
      return;
    case 154:
      imageView[paramInt].setImageResource(R.drawable.gpm31);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm31);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm31);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm31);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm31);
      return;
    case 153:
      imageView[paramInt].setImageResource(R.drawable.gpm21);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm21);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm21);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm21);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm21);
      return;
    case 152:
      imageView[paramInt].setImageResource(R.drawable.gpm11);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm11);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm11);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm11);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm11);
      return;
    case 151:
      imageView[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr);
      return;
    case 150:
      imageView[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpl);
      return;
    case 149:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprt1);
        imageViewRB[paramInt].setImageResource(R.drawable.gprt1);
        imageViewRT[paramInt].setImageResource(R.drawable.gprt1);
        imageViewLB[paramInt].setImageResource(R.drawable.gprt1);
        imageViewLT[paramInt].setImageResource(R.drawable.gprt1);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr21);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr21);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr21);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr21);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr21);
      return;
    case 148:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplt1);
        imageViewRB[paramInt].setImageResource(R.drawable.gplt1);
        imageViewRT[paramInt].setImageResource(R.drawable.gplt1);
        imageViewLB[paramInt].setImageResource(R.drawable.gplt1);
        imageViewLT[paramInt].setImageResource(R.drawable.gplt1);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl21);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl21);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl21);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl21);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl21);
      return;
    case 147:
      imageView[paramInt].setImageResource(R.drawable.gpr31);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr31);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr31);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr31);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr31);
      return;
    case 146:
      imageView[paramInt].setImageResource(R.drawable.gpl31);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl31);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl31);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl31);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl31);
      return;
    case 145:
      imageView[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselect);
      return;
    case 144:
      imageView[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstart);
      return;
    case 143:
      imageView[paramInt].setImageResource(R.drawable.gpright1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpright1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpright1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpright1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpright1);
      return;
    case 142:
      imageView[paramInt].setImageResource(R.drawable.gpleft1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleft1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleft1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleft1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleft1);
      return;
    case 141:
      imageView[paramInt].setImageResource(R.drawable.gpdown1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdown1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdown1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdown1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdown1);
      return;
    case 140:
      imageView[paramInt].setImageResource(R.drawable.gpup1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpup1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpup1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpup1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpup1);
      return;
    case 139:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprb1);
        imageViewRB[paramInt].setImageResource(R.drawable.gprb1);
        imageViewRT[paramInt].setImageResource(R.drawable.gprb1);
        imageViewLB[paramInt].setImageResource(R.drawable.gprb1);
        imageViewLT[paramInt].setImageResource(R.drawable.gprb1);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr11);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr11);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr11);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr11);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr11);
      return;
    case 138:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplb1);
        imageViewRB[paramInt].setImageResource(R.drawable.gplb1);
        imageViewRT[paramInt].setImageResource(R.drawable.gplb1);
        imageViewLB[paramInt].setImageResource(R.drawable.gplb1);
        imageViewLT[paramInt].setImageResource(R.drawable.gplb1);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl11);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl11);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl11);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl11);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl11);
      return;
    case 137:
      imageView[paramInt].setImageResource(R.drawable.gpy1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpy1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpy1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpy1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpy1);
      return;
    case 136:
      imageView[paramInt].setImageResource(R.drawable.gpx1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpx1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpx1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpx1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpx1);
      return;
    case 135:
      imageView[paramInt].setImageResource(R.drawable.gpbtn1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbtn1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbtn1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbtn1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbtn1);
      return;
    case 134:
      imageView[paramInt].setImageResource(R.drawable.gpa1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpa1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpa1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpa1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpa1);
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
      imageView[paramInt].setImageResource(R.drawable.newkey);
      imageViewRB[paramInt].setImageResource(R.drawable.newkey);
      imageViewRT[paramInt].setImageResource(R.drawable.newkey);
      imageViewLB[paramInt].setImageResource(R.drawable.newkey);
      imageViewLT[paramInt].setImageResource(R.drawable.newkey);
      return;
    case 123:
      imageView[paramInt].setImageResource(R.drawable.gpm4lr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4lr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4lr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4lr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4lr);
      return;
    case 122:
      imageView[paramInt].setImageResource(R.drawable.gpm3lr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3lr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3lr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3lr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3lr);
      return;
    case 121:
      imageView[paramInt].setImageResource(R.drawable.gpm2lr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2lr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2lr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2lr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2lr);
      return;
    case 120:
      imageView[paramInt].setImageResource(R.drawable.gpm1lr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1lr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1lr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1lr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1lr);
      return;
    case 119:
      imageView[paramInt].setImageResource(R.drawable.gpalr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpalr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpalr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpalr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpalr);
      return;
    case 118:
      imageView[paramInt].setImageResource(R.drawable.gpalr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpalr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpalr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpalr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpalr);
      return;
    case 117:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprtlr);
        imageViewRB[paramInt].setImageResource(R.drawable.gprtlr);
        imageViewRT[paramInt].setImageResource(R.drawable.gprtlr);
        imageViewLB[paramInt].setImageResource(R.drawable.gprtlr);
        imageViewLT[paramInt].setImageResource(R.drawable.gprtlr);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2lr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2lr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2lr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2lr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2lr);
      return;
    case 116:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpltlr);
        imageViewRB[paramInt].setImageResource(R.drawable.gpltlr);
        imageViewRT[paramInt].setImageResource(R.drawable.gpltlr);
        imageViewLB[paramInt].setImageResource(R.drawable.gpltlr);
        imageViewLT[paramInt].setImageResource(R.drawable.gpltlr);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2lr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2lr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2lr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2lr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2lr);
      return;
    case 115:
      imageView[paramInt].setImageResource(R.drawable.gpr3lr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3lr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3lr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3lr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3lr);
      return;
    case 114:
      imageView[paramInt].setImageResource(R.drawable.gpl3lr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3lr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3lr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3lr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3lr);
      return;
    case 113:
      imageView[paramInt].setImageResource(R.drawable.gpalr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpalr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpalr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpalr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpalr);
      return;
    case 112:
      imageView[paramInt].setImageResource(R.drawable.gpalr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpalr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpalr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpalr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpalr);
      return;
    case 111:
      imageView[paramInt].setImageResource(R.drawable.gpalr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpalr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpalr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpalr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpalr);
      return;
    case 110:
      imageView[paramInt].setImageResource(R.drawable.gpalr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpalr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpalr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpalr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpalr);
      return;
    case 109:
      imageView[paramInt].setImageResource(R.drawable.gpalr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpalr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpalr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpalr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpalr);
      return;
    case 108:
      imageView[paramInt].setImageResource(R.drawable.gpalr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpalr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpalr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpalr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpalr);
      return;
    case 107:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprblr);
        imageViewRB[paramInt].setImageResource(R.drawable.gprblr);
        imageViewRT[paramInt].setImageResource(R.drawable.gprblr);
        imageViewLB[paramInt].setImageResource(R.drawable.gprblr);
        imageViewLT[paramInt].setImageResource(R.drawable.gprblr);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1lr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1lr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1lr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1lr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1lr);
      return;
    case 106:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplblr);
        imageViewRB[paramInt].setImageResource(R.drawable.gplblr);
        imageViewRT[paramInt].setImageResource(R.drawable.gplblr);
        imageViewLB[paramInt].setImageResource(R.drawable.gplblr);
        imageViewLT[paramInt].setImageResource(R.drawable.gplblr);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1lr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1lr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1lr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1lr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1lr);
      return;
    case 105:
      imageView[paramInt].setImageResource(R.drawable.gpylr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpylr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpylr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpylr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpylr);
      return;
    case 104:
      imageView[paramInt].setImageResource(R.drawable.gpxlr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxlr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxlr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxlr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxlr);
      return;
    case 103:
      imageView[paramInt].setImageResource(R.drawable.gpblr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpblr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpblr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpblr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpblr);
      return;
    case 102:
      imageView[paramInt].setImageResource(R.drawable.gpalr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpalr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpalr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpalr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpalr);
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
      imageView[paramInt].setImageResource(R.drawable.newkey);
      imageViewRB[paramInt].setImageResource(R.drawable.newkey);
      imageViewRT[paramInt].setImageResource(R.drawable.newkey);
      imageViewLB[paramInt].setImageResource(R.drawable.newkey);
      imageViewLT[paramInt].setImageResource(R.drawable.newkey);
      return;
    case 91:
      imageView[paramInt].setImageResource(R.drawable.gpm4bom);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4bom);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4bom);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4bom);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4bom);
      return;
    case 90:
      imageView[paramInt].setImageResource(R.drawable.gpm3bom);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3bom);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3bom);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3bom);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3bom);
      return;
    case 89:
      imageView[paramInt].setImageResource(R.drawable.gpm2bom);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2bom);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2bom);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2bom);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2bom);
      return;
    case 88:
      imageView[paramInt].setImageResource(R.drawable.gpm1bom);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1bom);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1bom);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1bom);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1bom);
      return;
    case 87:
      imageView[paramInt].setImageResource(R.drawable.gparight);
      imageViewRB[paramInt].setImageResource(R.drawable.gparight);
      imageViewRT[paramInt].setImageResource(R.drawable.gparight);
      imageViewLB[paramInt].setImageResource(R.drawable.gparight);
      imageViewLT[paramInt].setImageResource(R.drawable.gparight);
      return;
    case 86:
      imageView[paramInt].setImageResource(R.drawable.gparight);
      imageViewRB[paramInt].setImageResource(R.drawable.gparight);
      imageViewRT[paramInt].setImageResource(R.drawable.gparight);
      imageViewLB[paramInt].setImageResource(R.drawable.gparight);
      imageViewLT[paramInt].setImageResource(R.drawable.gparight);
      return;
    case 85:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprtbom);
        imageViewRB[paramInt].setImageResource(R.drawable.gprtbom);
        imageViewRT[paramInt].setImageResource(R.drawable.gprtbom);
        imageViewLB[paramInt].setImageResource(R.drawable.gprtbom);
        imageViewLT[paramInt].setImageResource(R.drawable.gprtbom);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2bom);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2bom);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2bom);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2bom);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2bom);
      return;
    case 84:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpltbom);
        imageViewRB[paramInt].setImageResource(R.drawable.gpltbom);
        imageViewRT[paramInt].setImageResource(R.drawable.gpltbom);
        imageViewLB[paramInt].setImageResource(R.drawable.gpltbom);
        imageViewLT[paramInt].setImageResource(R.drawable.gpltbom);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2bom);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2bom);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2bom);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2bom);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2bom);
      return;
    case 83:
      imageView[paramInt].setImageResource(R.drawable.gpr3bom);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3bom);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3bom);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3bom);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3bom);
      return;
    case 82:
      imageView[paramInt].setImageResource(R.drawable.gpl3bom);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3bom);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3bom);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3bom);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3bom);
      return;
    case 81:
      imageView[paramInt].setImageResource(R.drawable.gparight);
      imageViewRB[paramInt].setImageResource(R.drawable.gparight);
      imageViewRT[paramInt].setImageResource(R.drawable.gparight);
      imageViewLB[paramInt].setImageResource(R.drawable.gparight);
      imageViewLT[paramInt].setImageResource(R.drawable.gparight);
      return;
    case 80:
      imageView[paramInt].setImageResource(R.drawable.gparight);
      imageViewRB[paramInt].setImageResource(R.drawable.gparight);
      imageViewRT[paramInt].setImageResource(R.drawable.gparight);
      imageViewLB[paramInt].setImageResource(R.drawable.gparight);
      imageViewLT[paramInt].setImageResource(R.drawable.gparight);
      return;
    case 79:
      imageView[paramInt].setImageResource(R.drawable.gparight);
      imageViewRB[paramInt].setImageResource(R.drawable.gparight);
      imageViewRT[paramInt].setImageResource(R.drawable.gparight);
      imageViewLB[paramInt].setImageResource(R.drawable.gparight);
      imageViewLT[paramInt].setImageResource(R.drawable.gparight);
      return;
    case 78:
      imageView[paramInt].setImageResource(R.drawable.gparight);
      imageViewRB[paramInt].setImageResource(R.drawable.gparight);
      imageViewRT[paramInt].setImageResource(R.drawable.gparight);
      imageViewLB[paramInt].setImageResource(R.drawable.gparight);
      imageViewLT[paramInt].setImageResource(R.drawable.gparight);
      return;
    case 77:
      imageView[paramInt].setImageResource(R.drawable.gparight);
      imageViewRB[paramInt].setImageResource(R.drawable.gparight);
      imageViewRT[paramInt].setImageResource(R.drawable.gparight);
      imageViewLB[paramInt].setImageResource(R.drawable.gparight);
      imageViewLT[paramInt].setImageResource(R.drawable.gparight);
      return;
    case 76:
      imageView[paramInt].setImageResource(R.drawable.gparight);
      imageViewRB[paramInt].setImageResource(R.drawable.gparight);
      imageViewRT[paramInt].setImageResource(R.drawable.gparight);
      imageViewLB[paramInt].setImageResource(R.drawable.gparight);
      imageViewLT[paramInt].setImageResource(R.drawable.gparight);
      return;
    case 75:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprbbom);
        imageViewRB[paramInt].setImageResource(R.drawable.gprbbom);
        imageViewRT[paramInt].setImageResource(R.drawable.gprbbom);
        imageViewLB[paramInt].setImageResource(R.drawable.gprbbom);
        imageViewLT[paramInt].setImageResource(R.drawable.gprbbom);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1bom);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1bom);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1bom);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1bom);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1bom);
      return;
    case 74:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplbbom);
        imageViewRB[paramInt].setImageResource(R.drawable.gplbbom);
        imageViewRT[paramInt].setImageResource(R.drawable.gplbbom);
        imageViewLB[paramInt].setImageResource(R.drawable.gplbbom);
        imageViewLT[paramInt].setImageResource(R.drawable.gplbbom);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1bom);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1bom);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1bom);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1bom);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1bom);
      return;
    case 73:
      imageView[paramInt].setImageResource(R.drawable.gpyright);
      imageViewRB[paramInt].setImageResource(R.drawable.gpyright);
      imageViewRT[paramInt].setImageResource(R.drawable.gpyright);
      imageViewLB[paramInt].setImageResource(R.drawable.gpyright);
      imageViewLT[paramInt].setImageResource(R.drawable.gpyright);
      return;
    case 72:
      imageView[paramInt].setImageResource(R.drawable.gpxright);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxright);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxright);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxright);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxright);
      return;
    case 71:
      imageView[paramInt].setImageResource(R.drawable.gpbright1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbright1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbright1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbright1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbright1);
      return;
    case 70:
      imageView[paramInt].setImageResource(R.drawable.gparight);
      imageViewRB[paramInt].setImageResource(R.drawable.gparight);
      imageViewRT[paramInt].setImageResource(R.drawable.gparight);
      imageViewLB[paramInt].setImageResource(R.drawable.gparight);
      imageViewLT[paramInt].setImageResource(R.drawable.gparight);
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
      imageView[paramInt].setImageResource(R.drawable.newkey);
      imageViewRB[paramInt].setImageResource(R.drawable.newkey);
      imageViewRT[paramInt].setImageResource(R.drawable.newkey);
      imageViewLB[paramInt].setImageResource(R.drawable.newkey);
      imageViewLT[paramInt].setImageResource(R.drawable.newkey);
      return;
    case 59:
      imageView[paramInt].setImageResource(R.drawable.gpm4left);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4left);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4left);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4left);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4left);
      return;
    case 58:
      imageView[paramInt].setImageResource(R.drawable.gpm3left);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3left);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3left);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3left);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3left);
      return;
    case 57:
      imageView[paramInt].setImageResource(R.drawable.gpm2left);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2left);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2left);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2left);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2left);
      return;
    case 56:
      imageView[paramInt].setImageResource(R.drawable.gpm1left);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1left);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1left);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1left);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1left);
      return;
    case 55:
      imageView[paramInt].setImageResource(R.drawable.gpaleft);
      imageViewRB[paramInt].setImageResource(R.drawable.gpaleft);
      imageViewRT[paramInt].setImageResource(R.drawable.gpaleft);
      imageViewLB[paramInt].setImageResource(R.drawable.gpaleft);
      imageViewLT[paramInt].setImageResource(R.drawable.gpaleft);
      return;
    case 54:
      imageView[paramInt].setImageResource(R.drawable.gpaleft);
      imageViewRB[paramInt].setImageResource(R.drawable.gpaleft);
      imageViewRT[paramInt].setImageResource(R.drawable.gpaleft);
      imageViewLB[paramInt].setImageResource(R.drawable.gpaleft);
      imageViewLT[paramInt].setImageResource(R.drawable.gpaleft);
      return;
    case 53:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprtleft);
        imageViewRB[paramInt].setImageResource(R.drawable.gprtleft);
        imageViewRT[paramInt].setImageResource(R.drawable.gprtleft);
        imageViewLB[paramInt].setImageResource(R.drawable.gprtleft);
        imageViewLT[paramInt].setImageResource(R.drawable.gprtleft);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2left);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2left);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2left);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2left);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2left);
      return;
    case 52:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gpltleft);
        imageViewRB[paramInt].setImageResource(R.drawable.gpltleft);
        imageViewRT[paramInt].setImageResource(R.drawable.gpltleft);
        imageViewLB[paramInt].setImageResource(R.drawable.gpltleft);
        imageViewLT[paramInt].setImageResource(R.drawable.gpltleft);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2left);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2left);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2left);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2left);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2left);
      return;
    case 51:
      imageView[paramInt].setImageResource(R.drawable.gpr3left);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3left);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3left);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3left);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3left);
      return;
    case 49:
      imageView[paramInt].setImageResource(R.drawable.gpaleft);
      imageViewRB[paramInt].setImageResource(R.drawable.gpaleft);
      imageViewRT[paramInt].setImageResource(R.drawable.gpaleft);
      imageViewLB[paramInt].setImageResource(R.drawable.gpaleft);
      imageViewLT[paramInt].setImageResource(R.drawable.gpaleft);
    case 50:
      imageView[paramInt].setImageResource(R.drawable.gpl3left);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3left);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3left);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3left);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3left);
      return;
    case 48:
      imageView[paramInt].setImageResource(R.drawable.gpaleft);
      imageViewRB[paramInt].setImageResource(R.drawable.gpaleft);
      imageViewRT[paramInt].setImageResource(R.drawable.gpaleft);
      imageViewLB[paramInt].setImageResource(R.drawable.gpaleft);
      imageViewLT[paramInt].setImageResource(R.drawable.gpaleft);
      return;
    case 47:
      imageView[paramInt].setImageResource(R.drawable.gpaleft);
      imageViewRB[paramInt].setImageResource(R.drawable.gpaleft);
      imageViewRT[paramInt].setImageResource(R.drawable.gpaleft);
      imageViewLB[paramInt].setImageResource(R.drawable.gpaleft);
      imageViewLT[paramInt].setImageResource(R.drawable.gpaleft);
      return;
    case 46:
      imageView[paramInt].setImageResource(R.drawable.gpaleft);
      imageViewRB[paramInt].setImageResource(R.drawable.gpaleft);
      imageViewRT[paramInt].setImageResource(R.drawable.gpaleft);
      imageViewLB[paramInt].setImageResource(R.drawable.gpaleft);
      imageViewLT[paramInt].setImageResource(R.drawable.gpaleft);
      return;
    case 45:
      imageView[paramInt].setImageResource(R.drawable.gpaleft);
      imageViewRB[paramInt].setImageResource(R.drawable.gpaleft);
      imageViewRT[paramInt].setImageResource(R.drawable.gpaleft);
      imageViewLB[paramInt].setImageResource(R.drawable.gpaleft);
      imageViewLT[paramInt].setImageResource(R.drawable.gpaleft);
      return;
    case 44:
      imageView[paramInt].setImageResource(R.drawable.gpaleft);
      imageViewRB[paramInt].setImageResource(R.drawable.gpaleft);
      imageViewRT[paramInt].setImageResource(R.drawable.gpaleft);
      imageViewLB[paramInt].setImageResource(R.drawable.gpaleft);
      imageViewLT[paramInt].setImageResource(R.drawable.gpaleft);
      return;
    case 43:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprbleft);
        imageViewRB[paramInt].setImageResource(R.drawable.gprbleft);
        imageViewRT[paramInt].setImageResource(R.drawable.gprbleft);
        imageViewLB[paramInt].setImageResource(R.drawable.gprbleft);
        imageViewLT[paramInt].setImageResource(R.drawable.gprbleft);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1left);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1left);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1left);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1left);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1left);
      return;
    case 42:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplbleft);
        imageViewRB[paramInt].setImageResource(R.drawable.gplbleft);
        imageViewRT[paramInt].setImageResource(R.drawable.gplbleft);
        imageViewLB[paramInt].setImageResource(R.drawable.gplbleft);
        imageViewLT[paramInt].setImageResource(R.drawable.gplbleft);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1left);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1left);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1left);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1left);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1left);
      return;
    case 41:
      imageView[paramInt].setImageResource(R.drawable.gpyleft);
      imageViewRB[paramInt].setImageResource(R.drawable.gpyleft);
      imageViewRT[paramInt].setImageResource(R.drawable.gpyleft);
      imageViewLB[paramInt].setImageResource(R.drawable.gpyleft);
      imageViewLT[paramInt].setImageResource(R.drawable.gpyleft);
      return;
    case 40:
      imageView[paramInt].setImageResource(R.drawable.gpxleft);
      imageViewRB[paramInt].setImageResource(R.drawable.gpxleft);
      imageViewRT[paramInt].setImageResource(R.drawable.gpxleft);
      imageViewLB[paramInt].setImageResource(R.drawable.gpxleft);
      imageViewLT[paramInt].setImageResource(R.drawable.gpxleft);
      return;
    case 39:
      imageView[paramInt].setImageResource(R.drawable.gpbleft1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbleft1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbleft1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbleft1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbleft1);
      return;
    case 38:
      imageView[paramInt].setImageResource(R.drawable.gpaleft);
      imageViewRB[paramInt].setImageResource(R.drawable.gpaleft);
      imageViewRT[paramInt].setImageResource(R.drawable.gpaleft);
      imageViewLB[paramInt].setImageResource(R.drawable.gpaleft);
      imageViewLT[paramInt].setImageResource(R.drawable.gpaleft);
      return;
    case 37:
      imageView[paramInt].setImageResource(R.drawable.gpsensor);
      imageViewRB[paramInt].setImageResource(R.drawable.gpsensor);
      imageViewRT[paramInt].setImageResource(R.drawable.gpsensor);
      imageViewLB[paramInt].setImageResource(R.drawable.gpsensor);
      imageViewLT[paramInt].setImageResource(R.drawable.gpsensor);
      return;
    case 36:
      imageView[paramInt].setImageResource(R.drawable.gptouch);
      imageViewRB[paramInt].setImageResource(R.drawable.gptouch);
      imageViewRT[paramInt].setImageResource(R.drawable.gptouch);
      imageViewLB[paramInt].setImageResource(R.drawable.gptouch);
      imageViewLT[paramInt].setImageResource(R.drawable.gptouch);
      return;
    case 35:
      imageView[paramInt].setImageResource(R.drawable.gpy_add);
      imageViewRB[paramInt].setImageResource(R.drawable.gpy_add);
      imageViewRT[paramInt].setImageResource(R.drawable.gpy_add);
      imageViewLB[paramInt].setImageResource(R.drawable.gpy_add);
      imageViewLT[paramInt].setImageResource(R.drawable.gpy_add);
      return;
    case 34:
      imageView[paramInt].setImageResource(R.drawable.gpx_add);
      imageViewRB[paramInt].setImageResource(R.drawable.gpx_add);
      imageViewRT[paramInt].setImageResource(R.drawable.gpx_add);
      imageViewLB[paramInt].setImageResource(R.drawable.gpx_add);
      imageViewLT[paramInt].setImageResource(R.drawable.gpx_add);
      return;
    case 33:
      imageView[paramInt].setImageResource(R.drawable.gpb_add);
      imageViewRB[paramInt].setImageResource(R.drawable.gpb_add);
      imageViewRT[paramInt].setImageResource(R.drawable.gpb_add);
      imageViewLB[paramInt].setImageResource(R.drawable.gpb_add);
      imageViewLT[paramInt].setImageResource(R.drawable.gpb_add);
      return;
    case 32:
      imageView[paramInt].setImageResource(R.drawable.gpa_add);
      imageViewRB[paramInt].setImageResource(R.drawable.gpa_add);
      imageViewRT[paramInt].setImageResource(R.drawable.gpa_add);
      imageViewLB[paramInt].setImageResource(R.drawable.gpa_add);
      imageViewLT[paramInt].setImageResource(R.drawable.gpa_add);
      return;
    case 27:
    case 28:
    case 29:
    case 30:
    case 31:
      imageView[paramInt].setImageResource(R.drawable.newkey);
      imageViewRB[paramInt].setImageResource(R.drawable.newkey);
      imageViewRT[paramInt].setImageResource(R.drawable.newkey);
      imageViewLB[paramInt].setImageResource(R.drawable.newkey);
      imageViewLT[paramInt].setImageResource(R.drawable.newkey);
      return;
    case 26:
      imageView[paramInt].setImageResource(R.drawable.center3);
      imageViewRB[paramInt].setImageResource(R.drawable.center3);
      imageViewRT[paramInt].setImageResource(R.drawable.center3);
      imageViewLB[paramInt].setImageResource(R.drawable.center3);
      imageViewLT[paramInt].setImageResource(R.drawable.center3);
      return;
    case 25:
      imageView[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr2);
      return;
    case 24:
      imageView[paramInt].setImageResource(R.drawable.center2);
      imageViewRB[paramInt].setImageResource(R.drawable.center2);
      imageViewRT[paramInt].setImageResource(R.drawable.center2);
      imageViewLB[paramInt].setImageResource(R.drawable.center2);
      imageViewLT[paramInt].setImageResource(R.drawable.center2);
      return;
    case 23:
      imageView[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr1);
      return;
    case 22:
      imageView[paramInt].setImageResource(R.drawable.center1);
      imageViewRB[paramInt].setImageResource(R.drawable.center1);
      imageViewRT[paramInt].setImageResource(R.drawable.center1);
      imageViewLB[paramInt].setImageResource(R.drawable.center1);
      imageViewLT[paramInt].setImageResource(R.drawable.center1);
      return;
    case 21:
      imageView[paramInt].setImageResource(R.drawable.gpm4);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm4);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm4);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm4);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm4);
      return;
    case 20:
      imageView[paramInt].setImageResource(R.drawable.gpm3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm3);
      return;
    case 19:
      imageView[paramInt].setImageResource(R.drawable.gpm2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm2);
      return;
    case 18:
      imageView[paramInt].setImageResource(R.drawable.gpm1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpm1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpm1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpm1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpm1);
      return;
    case 17:
      imageView[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpr);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpr);
      return;
    case 16:
      imageView[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewRT[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLB[paramInt].setImageResource(R.drawable.gpbpl);
      imageViewLT[paramInt].setImageResource(R.drawable.gpbpl);
      return;
    case 15:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprt);
        imageViewRB[paramInt].setImageResource(R.drawable.gprt);
        imageViewRT[paramInt].setImageResource(R.drawable.gprt);
        imageViewLB[paramInt].setImageResource(R.drawable.gprt);
        imageViewLT[paramInt].setImageResource(R.drawable.gprt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr2);
      return;
    case 14:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplt);
        imageViewRB[paramInt].setImageResource(R.drawable.gplt);
        imageViewRT[paramInt].setImageResource(R.drawable.gplt);
        imageViewLB[paramInt].setImageResource(R.drawable.gplt);
        imageViewLT[paramInt].setImageResource(R.drawable.gplt);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl2);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl2);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl2);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl2);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl2);
      return;
    case 13:
      imageView[paramInt].setImageResource(R.drawable.gpr3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr3);
      return;
    case 12:
      imageView[paramInt].setImageResource(R.drawable.gpl3);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl3);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl3);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl3);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl3);
      return;
    case 11:
      imageView[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewRT[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLB[paramInt].setImageResource(R.drawable.gpselect);
      imageViewLT[paramInt].setImageResource(R.drawable.gpselect);
      return;
    case 10:
      imageView[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewRT[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLB[paramInt].setImageResource(R.drawable.gpstart);
      imageViewLT[paramInt].setImageResource(R.drawable.gpstart);
      return;
    case 9:
      imageView[paramInt].setImageResource(R.drawable.gpright);
      imageViewRB[paramInt].setImageResource(R.drawable.gpright);
      imageViewRT[paramInt].setImageResource(R.drawable.gpright);
      imageViewLB[paramInt].setImageResource(R.drawable.gpright);
      imageViewLT[paramInt].setImageResource(R.drawable.gpright);
      return;
    case 8:
      imageView[paramInt].setImageResource(R.drawable.gpleft);
      imageViewRB[paramInt].setImageResource(R.drawable.gpleft);
      imageViewRT[paramInt].setImageResource(R.drawable.gpleft);
      imageViewLB[paramInt].setImageResource(R.drawable.gpleft);
      imageViewLT[paramInt].setImageResource(R.drawable.gpleft);
      return;
    case 7:
      imageView[paramInt].setImageResource(R.drawable.gpdown);
      imageViewRB[paramInt].setImageResource(R.drawable.gpdown);
      imageViewRT[paramInt].setImageResource(R.drawable.gpdown);
      imageViewLB[paramInt].setImageResource(R.drawable.gpdown);
      imageViewLT[paramInt].setImageResource(R.drawable.gpdown);
      return;
    case 6:
      imageView[paramInt].setImageResource(R.drawable.gpup);
      imageViewRB[paramInt].setImageResource(R.drawable.gpup);
      imageViewRT[paramInt].setImageResource(R.drawable.gpup);
      imageViewLB[paramInt].setImageResource(R.drawable.gpup);
      imageViewLT[paramInt].setImageResource(R.drawable.gpup);
      return;
    case 5:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gprb);
        imageViewRB[paramInt].setImageResource(R.drawable.gprb);
        imageViewRT[paramInt].setImageResource(R.drawable.gprb);
        imageViewLB[paramInt].setImageResource(R.drawable.gprb);
        imageViewLT[paramInt].setImageResource(R.drawable.gprb);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpr1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpr1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpr1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpr1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpr1);
      return;
    case 4:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        imageView[paramInt].setImageResource(R.drawable.gplb);
        imageViewRB[paramInt].setImageResource(R.drawable.gplb);
        imageViewRT[paramInt].setImageResource(R.drawable.gplb);
        imageViewLB[paramInt].setImageResource(R.drawable.gplb);
        imageViewLT[paramInt].setImageResource(R.drawable.gplb);
        return;
      }
      imageView[paramInt].setImageResource(R.drawable.gpl1);
      imageViewRB[paramInt].setImageResource(R.drawable.gpl1);
      imageViewRT[paramInt].setImageResource(R.drawable.gpl1);
      imageViewLB[paramInt].setImageResource(R.drawable.gpl1);
      imageViewLT[paramInt].setImageResource(R.drawable.gpl1);
      return;
    case 3:
      imageView[paramInt].setImageResource(R.drawable.gpy);
      imageViewRB[paramInt].setImageResource(R.drawable.gpy);
      imageViewRT[paramInt].setImageResource(R.drawable.gpy);
      imageViewLB[paramInt].setImageResource(R.drawable.gpy);
      imageViewLT[paramInt].setImageResource(R.drawable.gpy);
      return;
    case 2:
      imageView[paramInt].setImageResource(R.drawable.gpx);
      imageViewRB[paramInt].setImageResource(R.drawable.gpx);
      imageViewRT[paramInt].setImageResource(R.drawable.gpx);
      imageViewLB[paramInt].setImageResource(R.drawable.gpx);
      imageViewLT[paramInt].setImageResource(R.drawable.gpx);
      return;
    case 1:
      imageView[paramInt].setImageResource(R.drawable.gpb);
      imageViewRB[paramInt].setImageResource(R.drawable.gpb);
      imageViewRT[paramInt].setImageResource(R.drawable.gpb);
      imageViewLB[paramInt].setImageResource(R.drawable.gpb);
      imageViewLT[paramInt].setImageResource(R.drawable.gpb);
      return;
    case 0:
    }
    imageView[paramInt].setImageResource(R.drawable.gpa);
    imageViewRB[paramInt].setImageResource(R.drawable.gpa);
    imageViewRT[paramInt].setImageResource(R.drawable.gpa);
    imageViewLB[paramInt].setImageResource(R.drawable.gpa);
    imageViewLT[paramInt].setImageResource(R.drawable.gpa);
  }

  void init()
  {
    int i = 0;
    while (i < length)
    {
      if (imageView[i] == null)
      {
        imageView[i] = new NumImageView(this.context);
        imageView[i].setScaleType(ImageView.ScaleType.CENTER);
      }
      if (imageViewRB[i] == null)
      {
        imageViewRB[i] = new NumImageView(this.context);
        imageViewRB[i].setScaleType(ImageView.ScaleType.CENTER);
      }
      if (imageViewRT[i] == null)
      {
        imageViewRT[i] = new NumImageView(this.context);
        imageViewRT[i].setScaleType(ImageView.ScaleType.CENTER);
      }
      if (imageViewLB[i] == null)
      {
        imageViewLB[i] = new NumImageView(this.context);
        imageViewLB[i].setScaleType(ImageView.ScaleType.CENTER);
      }
      if (imageViewLT[i] == null)
      {
        imageViewLT[i] = new NumImageView(this.context);
        imageViewLT[i].setScaleType(ImageView.ScaleType.CENTER);
      }
      imageView[i].setId(-1);
      imageViewRB[i].setId(-1);
      imageViewRT[i].setId(-1);
      imageViewLB[i].setId(-1);
      imageViewLT[i].setId(-1);
      setuppic(i);
      i += 1;
    }
  }

  public NumImageView[] initGPIndicatorImageView(Context paramContext, int paramInt)
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

  public NumImageView setupzoompic(NumImageView paramNumImageView1, NumImageView paramNumImageView2, NumImageView paramNumImageView3, NumImageView paramNumImageView4, NumImageView paramNumImageView5, Context paramContext, int paramInt1, int paramInt2)
  {
    switch (paramInt1)
    {
    default:
      break;
    case 929:
    case 930:
    case 931:
    case 932:
    case 933:
      paramNumImageView1.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView2.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView3.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView4.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView5.setImageResource(R.drawable.newkeyzoom);
      break;
    case 928:
      paramNumImageView1.setImageResource(R.drawable.center3zoom);
      paramNumImageView2.setImageResource(R.drawable.center3zoom);
      paramNumImageView3.setImageResource(R.drawable.center3zoom);
      paramNumImageView4.setImageResource(R.drawable.center3zoom);
      paramNumImageView5.setImageResource(R.drawable.center3zoom);
      break;
    case 927:
      paramNumImageView1.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbpr2zoom);
      break;
    case 926:
      paramNumImageView1.setImageResource(R.drawable.center2zoom);
      paramNumImageView2.setImageResource(R.drawable.center2zoom);
      paramNumImageView3.setImageResource(R.drawable.center2zoom);
      paramNumImageView4.setImageResource(R.drawable.center2zoom);
      paramNumImageView5.setImageResource(R.drawable.center2zoom);
      break;
    case 925:
      paramNumImageView1.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbpr1zoom);
      break;
    case 924:
      paramNumImageView1.setImageResource(R.drawable.center1zoom);
      paramNumImageView2.setImageResource(R.drawable.center1zoom);
      paramNumImageView3.setImageResource(R.drawable.center1zoom);
      paramNumImageView4.setImageResource(R.drawable.center1zoom);
      paramNumImageView5.setImageResource(R.drawable.center1zoom);
      break;
    case 923:
      paramNumImageView1.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm4zoom);
      break;
    case 922:
      paramNumImageView1.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm3zoom);
      break;
    case 921:
      paramNumImageView1.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm2zoom);
      break;
    case 920:
      paramNumImageView1.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm1zoom);
      break;
    case 919:
      paramNumImageView1.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbprzoom);
      break;
    case 918:
      paramNumImageView1.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbplzoom);
      break;
    case 917:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprtzoom);
        paramNumImageView2.setImageResource(R.drawable.gprtzoom);
        paramNumImageView3.setImageResource(R.drawable.gprtzoom);
        paramNumImageView4.setImageResource(R.drawable.gprtzoom);
        paramNumImageView5.setImageResource(R.drawable.gprtzoom);
      }
      break;
    case 916:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpltzoom);
        paramNumImageView2.setImageResource(R.drawable.gpltzoom);
        paramNumImageView3.setImageResource(R.drawable.gpltzoom);
        paramNumImageView4.setImageResource(R.drawable.gpltzoom);
        paramNumImageView5.setImageResource(R.drawable.gpltzoom);
      }
      break;
    case 915:
      paramNumImageView1.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpr3zoom);
      break;
    case 914:
      paramNumImageView1.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpl3zoom);
      break;
    case 913:
      paramNumImageView1.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView2.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView3.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView4.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView5.setImageResource(R.drawable.gpselectzoom);
      break;
    case 912:
      paramNumImageView1.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView2.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView3.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView4.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView5.setImageResource(R.drawable.gpstartzoom);
      break;
    case 911:
      paramNumImageView1.setImageResource(R.drawable.gprightzoom);
      paramNumImageView2.setImageResource(R.drawable.gprightzoom);
      paramNumImageView3.setImageResource(R.drawable.gprightzoom);
      paramNumImageView4.setImageResource(R.drawable.gprightzoom);
      paramNumImageView5.setImageResource(R.drawable.gprightzoom);
      break;
    case 910:
      paramNumImageView1.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView2.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView3.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView4.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView5.setImageResource(R.drawable.gpleftzoom);
      break;
    case 909:
      paramNumImageView1.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView2.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView3.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView4.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView5.setImageResource(R.drawable.gpdownzoom);
      break;
    case 908:
      paramNumImageView1.setImageResource(R.drawable.gpupzoom);
      paramNumImageView2.setImageResource(R.drawable.gpupzoom);
      paramNumImageView3.setImageResource(R.drawable.gpupzoom);
      paramNumImageView4.setImageResource(R.drawable.gpupzoom);
      paramNumImageView5.setImageResource(R.drawable.gpupzoom);
      break;
    case 907:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprbzoom);
        paramNumImageView2.setImageResource(R.drawable.gprbzoom);
        paramNumImageView3.setImageResource(R.drawable.gprbzoom);
        paramNumImageView4.setImageResource(R.drawable.gprbzoom);
        paramNumImageView5.setImageResource(R.drawable.gprbzoom);
      }
      break;
    case 906:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gplbzoom);
        paramNumImageView2.setImageResource(R.drawable.gplbzoom);
        paramNumImageView3.setImageResource(R.drawable.gplbzoom);
        paramNumImageView4.setImageResource(R.drawable.gplbzoom);
        paramNumImageView5.setImageResource(R.drawable.gplbzoom);
      }
      break;
    case 905:
      paramNumImageView1.setImageResource(R.drawable.gpyzoom);
      paramNumImageView2.setImageResource(R.drawable.gpyzoom);
      paramNumImageView3.setImageResource(R.drawable.gpyzoom);
      paramNumImageView4.setImageResource(R.drawable.gpyzoom);
      paramNumImageView5.setImageResource(R.drawable.gpyzoom);
      break;
    case 904:
      paramNumImageView1.setImageResource(R.drawable.gpxzoom);
      paramNumImageView2.setImageResource(R.drawable.gpxzoom);
      paramNumImageView3.setImageResource(R.drawable.gpxzoom);
      paramNumImageView4.setImageResource(R.drawable.gpxzoom);
      paramNumImageView5.setImageResource(R.drawable.gpxzoom);
      break;
    case 903:
      paramNumImageView1.setImageResource(R.drawable.gpbzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbzoom);
      break;
    case 902:
      paramNumImageView1.setImageResource(R.drawable.gpazoom);
      paramNumImageView2.setImageResource(R.drawable.gpazoom);
      paramNumImageView3.setImageResource(R.drawable.gpazoom);
      paramNumImageView4.setImageResource(R.drawable.gpazoom);
      paramNumImageView5.setImageResource(R.drawable.gpazoom);
      break;
    case 897:
    case 898:
    case 899:
    case 900:
    case 901:
      paramNumImageView1.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView2.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView3.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView4.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView5.setImageResource(R.drawable.newkeyzoom);
      break;
    case 896:
      paramNumImageView1.setImageResource(R.drawable.center3zoom);
      paramNumImageView2.setImageResource(R.drawable.center3zoom);
      paramNumImageView3.setImageResource(R.drawable.center3zoom);
      paramNumImageView4.setImageResource(R.drawable.center3zoom);
      paramNumImageView5.setImageResource(R.drawable.center3zoom);
      break;
    case 895:
      paramNumImageView1.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbpr2zoom);
      break;
    case 894:
      paramNumImageView1.setImageResource(R.drawable.center2zoom);
      paramNumImageView2.setImageResource(R.drawable.center2zoom);
      paramNumImageView3.setImageResource(R.drawable.center2zoom);
      paramNumImageView4.setImageResource(R.drawable.center2zoom);
      paramNumImageView5.setImageResource(R.drawable.center2zoom);
      break;
    case 893:
      paramNumImageView1.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbpr1zoom);
      break;
    case 892:
      paramNumImageView1.setImageResource(R.drawable.center1zoom);
      paramNumImageView2.setImageResource(R.drawable.center1zoom);
      paramNumImageView3.setImageResource(R.drawable.center1zoom);
      paramNumImageView4.setImageResource(R.drawable.center1zoom);
      paramNumImageView5.setImageResource(R.drawable.center1zoom);
      break;
    case 891:
      paramNumImageView1.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm4zoom);
      break;
    case 890:
      paramNumImageView1.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm3zoom);
      break;
    case 889:
      paramNumImageView1.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm2zoom);
      break;
    case 888:
      paramNumImageView1.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm1zoom);
      break;
    case 887:
      paramNumImageView1.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbprzoom);
      break;
    case 886:
      paramNumImageView1.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbplzoom);
      break;
    case 885:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprtzoom);
        paramNumImageView2.setImageResource(R.drawable.gprtzoom);
        paramNumImageView3.setImageResource(R.drawable.gprtzoom);
        paramNumImageView4.setImageResource(R.drawable.gprtzoom);
        paramNumImageView5.setImageResource(R.drawable.gprtzoom);
      }
      break;
    case 884:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpltzoom);
        paramNumImageView2.setImageResource(R.drawable.gpltzoom);
        paramNumImageView3.setImageResource(R.drawable.gpltzoom);
        paramNumImageView4.setImageResource(R.drawable.gpltzoom);
        paramNumImageView5.setImageResource(R.drawable.gpltzoom);
      }
      break;
    case 883:
      paramNumImageView1.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpr3zoom);
      break;
    case 882:
      paramNumImageView1.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpl3zoom);
      break;
    case 881:
      paramNumImageView1.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView2.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView3.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView4.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView5.setImageResource(R.drawable.gpselectzoom);
      break;
    case 880:
      paramNumImageView1.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView2.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView3.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView4.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView5.setImageResource(R.drawable.gpstartzoom);
      break;
    case 879:
      paramNumImageView1.setImageResource(R.drawable.gprightzoom);
      paramNumImageView2.setImageResource(R.drawable.gprightzoom);
      paramNumImageView3.setImageResource(R.drawable.gprightzoom);
      paramNumImageView4.setImageResource(R.drawable.gprightzoom);
      paramNumImageView5.setImageResource(R.drawable.gprightzoom);
      break;
    case 878:
      paramNumImageView1.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView2.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView3.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView4.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView5.setImageResource(R.drawable.gpleftzoom);
      break;
    case 877:
      paramNumImageView1.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView2.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView3.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView4.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView5.setImageResource(R.drawable.gpdownzoom);
      break;
    case 876:
      paramNumImageView1.setImageResource(R.drawable.gpupzoom);
      paramNumImageView2.setImageResource(R.drawable.gpupzoom);
      paramNumImageView3.setImageResource(R.drawable.gpupzoom);
      paramNumImageView4.setImageResource(R.drawable.gpupzoom);
      paramNumImageView5.setImageResource(R.drawable.gpupzoom);
      break;
    case 875:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprbzoom);
        paramNumImageView2.setImageResource(R.drawable.gprbzoom);
        paramNumImageView3.setImageResource(R.drawable.gprbzoom);
        paramNumImageView4.setImageResource(R.drawable.gprbzoom);
        paramNumImageView5.setImageResource(R.drawable.gprbzoom);
      }
      break;
    case 874:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gplbzoom);
        paramNumImageView2.setImageResource(R.drawable.gplbzoom);
        paramNumImageView3.setImageResource(R.drawable.gplbzoom);
        paramNumImageView4.setImageResource(R.drawable.gplbzoom);
        paramNumImageView5.setImageResource(R.drawable.gplbzoom);
      }
      break;
    case 873:
      paramNumImageView1.setImageResource(R.drawable.gpyzoom);
      paramNumImageView2.setImageResource(R.drawable.gpyzoom);
      paramNumImageView3.setImageResource(R.drawable.gpyzoom);
      paramNumImageView4.setImageResource(R.drawable.gpyzoom);
      paramNumImageView5.setImageResource(R.drawable.gpyzoom);
      break;
    case 872:
      paramNumImageView1.setImageResource(R.drawable.gpxzoom);
      paramNumImageView2.setImageResource(R.drawable.gpxzoom);
      paramNumImageView3.setImageResource(R.drawable.gpxzoom);
      paramNumImageView4.setImageResource(R.drawable.gpxzoom);
      paramNumImageView5.setImageResource(R.drawable.gpxzoom);
      break;
    case 871:
      paramNumImageView1.setImageResource(R.drawable.gpbzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbzoom);
      break;
    case 870:
      paramNumImageView1.setImageResource(R.drawable.gpazoom);
      paramNumImageView2.setImageResource(R.drawable.gpazoom);
      paramNumImageView3.setImageResource(R.drawable.gpazoom);
      paramNumImageView4.setImageResource(R.drawable.gpazoom);
      paramNumImageView5.setImageResource(R.drawable.gpazoom);
      break;
    case 865:
    case 866:
    case 867:
    case 868:
    case 869:
      paramNumImageView1.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView2.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView3.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView4.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView5.setImageResource(R.drawable.newkeyzoom);
      break;
    case 864:
      paramNumImageView1.setImageResource(R.drawable.center3zoom);
      paramNumImageView2.setImageResource(R.drawable.center3zoom);
      paramNumImageView3.setImageResource(R.drawable.center3zoom);
      paramNumImageView4.setImageResource(R.drawable.center3zoom);
      paramNumImageView5.setImageResource(R.drawable.center3zoom);
      break;
    case 863:
      paramNumImageView1.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbpr2zoom);
      break;
    case 862:
      paramNumImageView1.setImageResource(R.drawable.center2zoom);
      paramNumImageView2.setImageResource(R.drawable.center2zoom);
      paramNumImageView3.setImageResource(R.drawable.center2zoom);
      paramNumImageView4.setImageResource(R.drawable.center2zoom);
      paramNumImageView5.setImageResource(R.drawable.center2zoom);
      break;
    case 861:
      paramNumImageView1.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbpr1zoom);
      break;
    case 860:
      paramNumImageView1.setImageResource(R.drawable.center1zoom);
      paramNumImageView2.setImageResource(R.drawable.center1zoom);
      paramNumImageView3.setImageResource(R.drawable.center1zoom);
      paramNumImageView4.setImageResource(R.drawable.center1zoom);
      paramNumImageView5.setImageResource(R.drawable.center1zoom);
      break;
    case 859:
      paramNumImageView1.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm4zoom);
      break;
    case 858:
      paramNumImageView1.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm3zoom);
      break;
    case 857:
      paramNumImageView1.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm2zoom);
      break;
    case 856:
      paramNumImageView1.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm1zoom);
      break;
    case 855:
      paramNumImageView1.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbprzoom);
      break;
    case 854:
      paramNumImageView1.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbplzoom);
      break;
    case 853:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprtzoom);
        paramNumImageView2.setImageResource(R.drawable.gprtzoom);
        paramNumImageView3.setImageResource(R.drawable.gprtzoom);
        paramNumImageView4.setImageResource(R.drawable.gprtzoom);
        paramNumImageView5.setImageResource(R.drawable.gprtzoom);
      }
      break;
    case 852:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpltzoom);
        paramNumImageView2.setImageResource(R.drawable.gpltzoom);
        paramNumImageView3.setImageResource(R.drawable.gpltzoom);
        paramNumImageView4.setImageResource(R.drawable.gpltzoom);
        paramNumImageView5.setImageResource(R.drawable.gpltzoom);
      }
      break;
    case 851:
      paramNumImageView1.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpr3zoom);
      break;
    case 850:
      paramNumImageView1.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpl3zoom);
      break;
    case 849:
      paramNumImageView1.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView2.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView3.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView4.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView5.setImageResource(R.drawable.gpselectzoom);
      break;
    case 848:
      paramNumImageView1.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView2.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView3.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView4.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView5.setImageResource(R.drawable.gpstartzoom);
      break;
    case 847:
      paramNumImageView1.setImageResource(R.drawable.gprightzoom);
      paramNumImageView2.setImageResource(R.drawable.gprightzoom);
      paramNumImageView3.setImageResource(R.drawable.gprightzoom);
      paramNumImageView4.setImageResource(R.drawable.gprightzoom);
      paramNumImageView5.setImageResource(R.drawable.gprightzoom);
      break;
    case 846:
      paramNumImageView1.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView2.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView3.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView4.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView5.setImageResource(R.drawable.gpleftzoom);
      break;
    case 845:
      paramNumImageView1.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView2.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView3.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView4.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView5.setImageResource(R.drawable.gpdownzoom);
      break;
    case 844:
      paramNumImageView1.setImageResource(R.drawable.gpupzoom);
      paramNumImageView2.setImageResource(R.drawable.gpupzoom);
      paramNumImageView3.setImageResource(R.drawable.gpupzoom);
      paramNumImageView4.setImageResource(R.drawable.gpupzoom);
      paramNumImageView5.setImageResource(R.drawable.gpupzoom);
      break;
    case 843:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprbzoom);
        paramNumImageView2.setImageResource(R.drawable.gprbzoom);
        paramNumImageView3.setImageResource(R.drawable.gprbzoom);
        paramNumImageView4.setImageResource(R.drawable.gprbzoom);
        paramNumImageView5.setImageResource(R.drawable.gprbzoom);
      }
      break;
    case 842:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gplbzoom);
        paramNumImageView2.setImageResource(R.drawable.gplbzoom);
        paramNumImageView3.setImageResource(R.drawable.gplbzoom);
        paramNumImageView4.setImageResource(R.drawable.gplbzoom);
        paramNumImageView5.setImageResource(R.drawable.gplbzoom);
      }
      break;
    case 841:
      paramNumImageView1.setImageResource(R.drawable.gpyzoom);
      paramNumImageView2.setImageResource(R.drawable.gpyzoom);
      paramNumImageView3.setImageResource(R.drawable.gpyzoom);
      paramNumImageView4.setImageResource(R.drawable.gpyzoom);
      paramNumImageView5.setImageResource(R.drawable.gpyzoom);
      break;
    case 840:
      paramNumImageView1.setImageResource(R.drawable.gpxzoom);
      paramNumImageView2.setImageResource(R.drawable.gpxzoom);
      paramNumImageView3.setImageResource(R.drawable.gpxzoom);
      paramNumImageView4.setImageResource(R.drawable.gpxzoom);
      paramNumImageView5.setImageResource(R.drawable.gpxzoom);
      break;
    case 839:
      paramNumImageView1.setImageResource(R.drawable.gpbzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbzoom);
      break;
    case 838:
      paramNumImageView1.setImageResource(R.drawable.gpazoom);
      paramNumImageView2.setImageResource(R.drawable.gpazoom);
      paramNumImageView3.setImageResource(R.drawable.gpazoom);
      paramNumImageView4.setImageResource(R.drawable.gpazoom);
      paramNumImageView5.setImageResource(R.drawable.gpazoom);
      break;
    case 833:
    case 834:
    case 835:
    case 836:
    case 837:
      paramNumImageView1.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView2.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView3.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView4.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView5.setImageResource(R.drawable.newkeyzoom);
      break;
    case 832:
      paramNumImageView1.setImageResource(R.drawable.center3zoom);
      paramNumImageView2.setImageResource(R.drawable.center3zoom);
      paramNumImageView3.setImageResource(R.drawable.center3zoom);
      paramNumImageView4.setImageResource(R.drawable.center3zoom);
      paramNumImageView5.setImageResource(R.drawable.center3zoom);
      break;
    case 831:
      paramNumImageView1.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbpr2zoom);
      break;
    case 830:
      paramNumImageView1.setImageResource(R.drawable.center2zoom);
      paramNumImageView2.setImageResource(R.drawable.center2zoom);
      paramNumImageView3.setImageResource(R.drawable.center2zoom);
      paramNumImageView4.setImageResource(R.drawable.center2zoom);
      paramNumImageView5.setImageResource(R.drawable.center2zoom);
      break;
    case 829:
      paramNumImageView1.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbpr1zoom);
      break;
    case 828:
      paramNumImageView1.setImageResource(R.drawable.center1zoom);
      paramNumImageView2.setImageResource(R.drawable.center1zoom);
      paramNumImageView3.setImageResource(R.drawable.center1zoom);
      paramNumImageView4.setImageResource(R.drawable.center1zoom);
      paramNumImageView5.setImageResource(R.drawable.center1zoom);
      break;
    case 827:
      paramNumImageView1.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm4zoom);
      break;
    case 826:
      paramNumImageView1.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm3zoom);
      break;
    case 825:
      paramNumImageView1.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm2zoom);
      break;
    case 824:
      paramNumImageView1.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm1zoom);
      break;
    case 823:
      paramNumImageView1.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbprzoom);
      break;
    case 822:
      paramNumImageView1.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbplzoom);
      break;
    case 821:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprtzoom);
        paramNumImageView2.setImageResource(R.drawable.gprtzoom);
        paramNumImageView3.setImageResource(R.drawable.gprtzoom);
        paramNumImageView4.setImageResource(R.drawable.gprtzoom);
        paramNumImageView5.setImageResource(R.drawable.gprtzoom);
      }
      break;
    case 820:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpltzoom);
        paramNumImageView2.setImageResource(R.drawable.gpltzoom);
        paramNumImageView3.setImageResource(R.drawable.gpltzoom);
        paramNumImageView4.setImageResource(R.drawable.gpltzoom);
        paramNumImageView5.setImageResource(R.drawable.gpltzoom);
      }
      break;
    case 819:
      paramNumImageView1.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpr3zoom);
      break;
    case 818:
      paramNumImageView1.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpl3zoom);
      break;
    case 817:
      paramNumImageView1.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView2.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView3.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView4.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView5.setImageResource(R.drawable.gpselectzoom);
      break;
    case 816:
      paramNumImageView1.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView2.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView3.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView4.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView5.setImageResource(R.drawable.gpstartzoom);
      break;
    case 815:
      paramNumImageView1.setImageResource(R.drawable.gprightzoom);
      paramNumImageView2.setImageResource(R.drawable.gprightzoom);
      paramNumImageView3.setImageResource(R.drawable.gprightzoom);
      paramNumImageView4.setImageResource(R.drawable.gprightzoom);
      paramNumImageView5.setImageResource(R.drawable.gprightzoom);
      break;
    case 814:
      paramNumImageView1.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView2.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView3.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView4.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView5.setImageResource(R.drawable.gpleftzoom);
      break;
    case 813:
      paramNumImageView1.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView2.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView3.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView4.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView5.setImageResource(R.drawable.gpdownzoom);
      break;
    case 812:
      paramNumImageView1.setImageResource(R.drawable.gpupzoom);
      paramNumImageView2.setImageResource(R.drawable.gpupzoom);
      paramNumImageView3.setImageResource(R.drawable.gpupzoom);
      paramNumImageView4.setImageResource(R.drawable.gpupzoom);
      paramNumImageView5.setImageResource(R.drawable.gpupzoom);
      break;
    case 811:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprbzoom);
        paramNumImageView2.setImageResource(R.drawable.gprbzoom);
        paramNumImageView3.setImageResource(R.drawable.gprbzoom);
        paramNumImageView4.setImageResource(R.drawable.gprbzoom);
        paramNumImageView5.setImageResource(R.drawable.gprbzoom);
      }
      break;
    case 810:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gplbzoom);
        paramNumImageView2.setImageResource(R.drawable.gplbzoom);
        paramNumImageView3.setImageResource(R.drawable.gplbzoom);
        paramNumImageView4.setImageResource(R.drawable.gplbzoom);
        paramNumImageView5.setImageResource(R.drawable.gplbzoom);
      }
      break;
    case 809:
      paramNumImageView1.setImageResource(R.drawable.gpyzoom);
      paramNumImageView2.setImageResource(R.drawable.gpyzoom);
      paramNumImageView3.setImageResource(R.drawable.gpyzoom);
      paramNumImageView4.setImageResource(R.drawable.gpyzoom);
      paramNumImageView5.setImageResource(R.drawable.gpyzoom);
      break;
    case 808:
      paramNumImageView1.setImageResource(R.drawable.gpxzoom);
      paramNumImageView2.setImageResource(R.drawable.gpxzoom);
      paramNumImageView3.setImageResource(R.drawable.gpxzoom);
      paramNumImageView4.setImageResource(R.drawable.gpxzoom);
      paramNumImageView5.setImageResource(R.drawable.gpxzoom);
      break;
    case 807:
      paramNumImageView1.setImageResource(R.drawable.gpbzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbzoom);
      break;
    case 806:
      paramNumImageView1.setImageResource(R.drawable.gpazoom);
      paramNumImageView2.setImageResource(R.drawable.gpazoom);
      paramNumImageView3.setImageResource(R.drawable.gpazoom);
      paramNumImageView4.setImageResource(R.drawable.gpazoom);
      paramNumImageView5.setImageResource(R.drawable.gpazoom);
      break;
    case 801:
    case 802:
    case 803:
    case 804:
    case 805:
      paramNumImageView1.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView2.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView3.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView4.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView5.setImageResource(R.drawable.newkeyzoom);
      break;
    case 800:
      paramNumImageView1.setImageResource(R.drawable.center3zoom);
      paramNumImageView2.setImageResource(R.drawable.center3zoom);
      paramNumImageView3.setImageResource(R.drawable.center3zoom);
      paramNumImageView4.setImageResource(R.drawable.center3zoom);
      paramNumImageView5.setImageResource(R.drawable.center3zoom);
      break;
    case 799:
      paramNumImageView1.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbpr2zoom);
      break;
    case 798:
      paramNumImageView1.setImageResource(R.drawable.center2zoom);
      paramNumImageView2.setImageResource(R.drawable.center2zoom);
      paramNumImageView3.setImageResource(R.drawable.center2zoom);
      paramNumImageView4.setImageResource(R.drawable.center2zoom);
      paramNumImageView5.setImageResource(R.drawable.center2zoom);
      break;
    case 797:
      paramNumImageView1.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbpr1zoom);
      break;
    case 796:
      paramNumImageView1.setImageResource(R.drawable.center1zoom);
      paramNumImageView2.setImageResource(R.drawable.center1zoom);
      paramNumImageView3.setImageResource(R.drawable.center1zoom);
      paramNumImageView4.setImageResource(R.drawable.center1zoom);
      paramNumImageView5.setImageResource(R.drawable.center1zoom);
      break;
    case 795:
      paramNumImageView1.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm4zoom);
      break;
    case 794:
      paramNumImageView1.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm3zoom);
      break;
    case 793:
      paramNumImageView1.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm2zoom);
      break;
    case 792:
      paramNumImageView1.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm1zoom);
      break;
    case 791:
      paramNumImageView1.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbprzoom);
      break;
    case 790:
      paramNumImageView1.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbplzoom);
      break;
    case 789:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprtzoom);
        paramNumImageView2.setImageResource(R.drawable.gprtzoom);
        paramNumImageView3.setImageResource(R.drawable.gprtzoom);
        paramNumImageView4.setImageResource(R.drawable.gprtzoom);
        paramNumImageView5.setImageResource(R.drawable.gprtzoom);
      }
      break;
    case 788:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpltzoom);
        paramNumImageView2.setImageResource(R.drawable.gpltzoom);
        paramNumImageView3.setImageResource(R.drawable.gpltzoom);
        paramNumImageView4.setImageResource(R.drawable.gpltzoom);
        paramNumImageView5.setImageResource(R.drawable.gpltzoom);
      }
      break;
    case 787:
      paramNumImageView1.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpr3zoom);
      break;
    case 786:
      paramNumImageView1.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpl3zoom);
      break;
    case 785:
      paramNumImageView1.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView2.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView3.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView4.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView5.setImageResource(R.drawable.gpselectzoom);
      break;
    case 784:
      paramNumImageView1.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView2.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView3.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView4.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView5.setImageResource(R.drawable.gpstartzoom);
      break;
    case 783:
      paramNumImageView1.setImageResource(R.drawable.gprightzoom);
      paramNumImageView2.setImageResource(R.drawable.gprightzoom);
      paramNumImageView3.setImageResource(R.drawable.gprightzoom);
      paramNumImageView4.setImageResource(R.drawable.gprightzoom);
      paramNumImageView5.setImageResource(R.drawable.gprightzoom);
      break;
    case 782:
      paramNumImageView1.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView2.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView3.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView4.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView5.setImageResource(R.drawable.gpleftzoom);
      break;
    case 781:
      paramNumImageView1.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView2.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView3.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView4.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView5.setImageResource(R.drawable.gpdownzoom);
      break;
    case 780:
      paramNumImageView1.setImageResource(R.drawable.gpupzoom);
      paramNumImageView2.setImageResource(R.drawable.gpupzoom);
      paramNumImageView3.setImageResource(R.drawable.gpupzoom);
      paramNumImageView4.setImageResource(R.drawable.gpupzoom);
      paramNumImageView5.setImageResource(R.drawable.gpupzoom);
      break;
    case 779:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprbzoom);
        paramNumImageView2.setImageResource(R.drawable.gprbzoom);
        paramNumImageView3.setImageResource(R.drawable.gprbzoom);
        paramNumImageView4.setImageResource(R.drawable.gprbzoom);
        paramNumImageView5.setImageResource(R.drawable.gprbzoom);
      }
      break;
    case 778:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gplbzoom);
        paramNumImageView2.setImageResource(R.drawable.gplbzoom);
        paramNumImageView3.setImageResource(R.drawable.gplbzoom);
        paramNumImageView4.setImageResource(R.drawable.gplbzoom);
        paramNumImageView5.setImageResource(R.drawable.gplbzoom);
      }
      break;
    case 777:
      paramNumImageView1.setImageResource(R.drawable.gpyzoom);
      paramNumImageView2.setImageResource(R.drawable.gpyzoom);
      paramNumImageView3.setImageResource(R.drawable.gpyzoom);
      paramNumImageView4.setImageResource(R.drawable.gpyzoom);
      paramNumImageView5.setImageResource(R.drawable.gpyzoom);
      break;
    case 776:
      paramNumImageView1.setImageResource(R.drawable.gpxzoom);
      paramNumImageView2.setImageResource(R.drawable.gpxzoom);
      paramNumImageView3.setImageResource(R.drawable.gpxzoom);
      paramNumImageView4.setImageResource(R.drawable.gpxzoom);
      paramNumImageView5.setImageResource(R.drawable.gpxzoom);
      break;
    case 775:
      paramNumImageView1.setImageResource(R.drawable.gpbzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbzoom);
      break;
    case 774:
      paramNumImageView1.setImageResource(R.drawable.gpazoom);
      paramNumImageView2.setImageResource(R.drawable.gpazoom);
      paramNumImageView3.setImageResource(R.drawable.gpazoom);
      paramNumImageView4.setImageResource(R.drawable.gpazoom);
      paramNumImageView5.setImageResource(R.drawable.gpazoom);
      break;
    case 769:
    case 770:
    case 771:
    case 772:
    case 773:
      paramNumImageView1.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView2.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView3.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView4.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView5.setImageResource(R.drawable.newkeyzoom);
      break;
    case 768:
      paramNumImageView1.setImageResource(R.drawable.center3zoom);
      paramNumImageView2.setImageResource(R.drawable.center3zoom);
      paramNumImageView3.setImageResource(R.drawable.center3zoom);
      paramNumImageView4.setImageResource(R.drawable.center3zoom);
      paramNumImageView5.setImageResource(R.drawable.center3zoom);
      break;
    case 767:
      paramNumImageView1.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbpr2zoom);
      break;
    case 766:
      paramNumImageView1.setImageResource(R.drawable.center2zoom);
      paramNumImageView2.setImageResource(R.drawable.center2zoom);
      paramNumImageView3.setImageResource(R.drawable.center2zoom);
      paramNumImageView4.setImageResource(R.drawable.center2zoom);
      paramNumImageView5.setImageResource(R.drawable.center2zoom);
      break;
    case 765:
      paramNumImageView1.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbpr1zoom);
      break;
    case 764:
      paramNumImageView1.setImageResource(R.drawable.center1zoom);
      paramNumImageView2.setImageResource(R.drawable.center1zoom);
      paramNumImageView3.setImageResource(R.drawable.center1zoom);
      paramNumImageView4.setImageResource(R.drawable.center1zoom);
      paramNumImageView5.setImageResource(R.drawable.center1zoom);
      break;
    case 763:
      paramNumImageView1.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm4zoom);
      break;
    case 762:
      paramNumImageView1.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm3zoom);
      break;
    case 761:
      paramNumImageView1.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm2zoom);
      break;
    case 760:
      paramNumImageView1.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm1zoom);
      break;
    case 759:
      paramNumImageView1.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbprzoom);
      break;
    case 758:
      paramNumImageView1.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbplzoom);
      break;
    case 757:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprtzoom);
        paramNumImageView2.setImageResource(R.drawable.gprtzoom);
        paramNumImageView3.setImageResource(R.drawable.gprtzoom);
        paramNumImageView4.setImageResource(R.drawable.gprtzoom);
        paramNumImageView5.setImageResource(R.drawable.gprtzoom);
      }
      break;
    case 756:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpltzoom);
        paramNumImageView2.setImageResource(R.drawable.gpltzoom);
        paramNumImageView3.setImageResource(R.drawable.gpltzoom);
        paramNumImageView4.setImageResource(R.drawable.gpltzoom);
        paramNumImageView5.setImageResource(R.drawable.gpltzoom);
      }
      break;
    case 755:
      paramNumImageView1.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpr3zoom);
      break;
    case 754:
      paramNumImageView1.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpl3zoom);
      break;
    case 753:
      paramNumImageView1.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView2.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView3.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView4.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView5.setImageResource(R.drawable.gpselectzoom);
      break;
    case 752:
      paramNumImageView1.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView2.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView3.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView4.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView5.setImageResource(R.drawable.gpstartzoom);
      break;
    case 751:
      paramNumImageView1.setImageResource(R.drawable.gprightzoom);
      paramNumImageView2.setImageResource(R.drawable.gprightzoom);
      paramNumImageView3.setImageResource(R.drawable.gprightzoom);
      paramNumImageView4.setImageResource(R.drawable.gprightzoom);
      paramNumImageView5.setImageResource(R.drawable.gprightzoom);
      break;
    case 750:
      paramNumImageView1.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView2.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView3.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView4.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView5.setImageResource(R.drawable.gpleftzoom);
      break;
    case 749:
      paramNumImageView1.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView2.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView3.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView4.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView5.setImageResource(R.drawable.gpdownzoom);
      break;
    case 748:
      paramNumImageView1.setImageResource(R.drawable.gpupzoom);
      paramNumImageView2.setImageResource(R.drawable.gpupzoom);
      paramNumImageView3.setImageResource(R.drawable.gpupzoom);
      paramNumImageView4.setImageResource(R.drawable.gpupzoom);
      paramNumImageView5.setImageResource(R.drawable.gpupzoom);
      break;
    case 747:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprbzoom);
        paramNumImageView2.setImageResource(R.drawable.gprbzoom);
        paramNumImageView3.setImageResource(R.drawable.gprbzoom);
        paramNumImageView4.setImageResource(R.drawable.gprbzoom);
        paramNumImageView5.setImageResource(R.drawable.gprbzoom);
      }
      break;
    case 746:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gplbzoom);
        paramNumImageView2.setImageResource(R.drawable.gplbzoom);
        paramNumImageView3.setImageResource(R.drawable.gplbzoom);
        paramNumImageView4.setImageResource(R.drawable.gplbzoom);
        paramNumImageView5.setImageResource(R.drawable.gplbzoom);
      }
      break;
    case 745:
      paramNumImageView1.setImageResource(R.drawable.gpyzoom);
      paramNumImageView2.setImageResource(R.drawable.gpyzoom);
      paramNumImageView3.setImageResource(R.drawable.gpyzoom);
      paramNumImageView4.setImageResource(R.drawable.gpyzoom);
      paramNumImageView5.setImageResource(R.drawable.gpyzoom);
      break;
    case 744:
      paramNumImageView1.setImageResource(R.drawable.gpxzoom);
      paramNumImageView2.setImageResource(R.drawable.gpxzoom);
      paramNumImageView3.setImageResource(R.drawable.gpxzoom);
      paramNumImageView4.setImageResource(R.drawable.gpxzoom);
      paramNumImageView5.setImageResource(R.drawable.gpxzoom);
      break;
    case 743:
      paramNumImageView1.setImageResource(R.drawable.gpbzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbzoom);
      break;
    case 742:
      paramNumImageView1.setImageResource(R.drawable.gpazoom);
      paramNumImageView2.setImageResource(R.drawable.gpazoom);
      paramNumImageView3.setImageResource(R.drawable.gpazoom);
      paramNumImageView4.setImageResource(R.drawable.gpazoom);
      paramNumImageView5.setImageResource(R.drawable.gpazoom);
      break;
    case 737:
    case 738:
    case 739:
    case 740:
    case 741:
      paramNumImageView1.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView2.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView3.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView4.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView5.setImageResource(R.drawable.newkeyzoom);
      break;
    case 736:
      paramNumImageView1.setImageResource(R.drawable.center3zoom);
      paramNumImageView2.setImageResource(R.drawable.center3zoom);
      paramNumImageView3.setImageResource(R.drawable.center3zoom);
      paramNumImageView4.setImageResource(R.drawable.center3zoom);
      paramNumImageView5.setImageResource(R.drawable.center3zoom);
      break;
    case 735:
      paramNumImageView1.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbpr2zoom);
      break;
    case 734:
      paramNumImageView1.setImageResource(R.drawable.center2zoom);
      paramNumImageView2.setImageResource(R.drawable.center2zoom);
      paramNumImageView3.setImageResource(R.drawable.center2zoom);
      paramNumImageView4.setImageResource(R.drawable.center2zoom);
      paramNumImageView5.setImageResource(R.drawable.center2zoom);
      break;
    case 733:
      paramNumImageView1.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbpr1zoom);
      break;
    case 732:
      paramNumImageView1.setImageResource(R.drawable.center1zoom);
      paramNumImageView2.setImageResource(R.drawable.center1zoom);
      paramNumImageView3.setImageResource(R.drawable.center1zoom);
      paramNumImageView4.setImageResource(R.drawable.center1zoom);
      paramNumImageView5.setImageResource(R.drawable.center1zoom);
      break;
    case 731:
      paramNumImageView1.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm4zoom);
      break;
    case 730:
      paramNumImageView1.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm3zoom);
      break;
    case 729:
      paramNumImageView1.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm2zoom);
      break;
    case 728:
      paramNumImageView1.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm1zoom);
      break;
    case 727:
      paramNumImageView1.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbprzoom);
      break;
    case 726:
      paramNumImageView1.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbplzoom);
      break;
    case 725:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprtzoom);
        paramNumImageView2.setImageResource(R.drawable.gprtzoom);
        paramNumImageView3.setImageResource(R.drawable.gprtzoom);
        paramNumImageView4.setImageResource(R.drawable.gprtzoom);
        paramNumImageView5.setImageResource(R.drawable.gprtzoom);
      }
      break;
    case 724:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpltzoom);
        paramNumImageView2.setImageResource(R.drawable.gpltzoom);
        paramNumImageView3.setImageResource(R.drawable.gpltzoom);
        paramNumImageView4.setImageResource(R.drawable.gpltzoom);
        paramNumImageView5.setImageResource(R.drawable.gpltzoom);
      }
      break;
    case 723:
      paramNumImageView1.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpr3zoom);
      break;
    case 722:
      paramNumImageView1.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpl3zoom);
      break;
    case 721:
      paramNumImageView1.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView2.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView3.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView4.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView5.setImageResource(R.drawable.gpselectzoom);
      break;
    case 720:
      paramNumImageView1.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView2.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView3.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView4.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView5.setImageResource(R.drawable.gpstartzoom);
      break;
    case 719:
      paramNumImageView1.setImageResource(R.drawable.gprightzoom);
      paramNumImageView2.setImageResource(R.drawable.gprightzoom);
      paramNumImageView3.setImageResource(R.drawable.gprightzoom);
      paramNumImageView4.setImageResource(R.drawable.gprightzoom);
      paramNumImageView5.setImageResource(R.drawable.gprightzoom);
      break;
    case 718:
      paramNumImageView1.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView2.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView3.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView4.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView5.setImageResource(R.drawable.gpleftzoom);
      break;
    case 717:
      paramNumImageView1.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView2.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView3.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView4.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView5.setImageResource(R.drawable.gpdownzoom);
      break;
    case 716:
      paramNumImageView1.setImageResource(R.drawable.gpupzoom);
      paramNumImageView2.setImageResource(R.drawable.gpupzoom);
      paramNumImageView3.setImageResource(R.drawable.gpupzoom);
      paramNumImageView4.setImageResource(R.drawable.gpupzoom);
      paramNumImageView5.setImageResource(R.drawable.gpupzoom);
      break;
    case 715:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprbzoom);
        paramNumImageView2.setImageResource(R.drawable.gprbzoom);
        paramNumImageView3.setImageResource(R.drawable.gprbzoom);
        paramNumImageView4.setImageResource(R.drawable.gprbzoom);
        paramNumImageView5.setImageResource(R.drawable.gprbzoom);
      }
      break;
    case 714:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gplbzoom);
        paramNumImageView2.setImageResource(R.drawable.gplbzoom);
        paramNumImageView3.setImageResource(R.drawable.gplbzoom);
        paramNumImageView4.setImageResource(R.drawable.gplbzoom);
        paramNumImageView5.setImageResource(R.drawable.gplbzoom);
      }
      break;
    case 713:
      paramNumImageView1.setImageResource(R.drawable.gpyzoom);
      paramNumImageView2.setImageResource(R.drawable.gpyzoom);
      paramNumImageView3.setImageResource(R.drawable.gpyzoom);
      paramNumImageView4.setImageResource(R.drawable.gpyzoom);
      paramNumImageView5.setImageResource(R.drawable.gpyzoom);
      break;
    case 712:
      paramNumImageView1.setImageResource(R.drawable.gpxzoom);
      paramNumImageView2.setImageResource(R.drawable.gpxzoom);
      paramNumImageView3.setImageResource(R.drawable.gpxzoom);
      paramNumImageView4.setImageResource(R.drawable.gpxzoom);
      paramNumImageView5.setImageResource(R.drawable.gpxzoom);
      break;
    case 711:
      paramNumImageView1.setImageResource(R.drawable.gpbzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbzoom);
      break;
    case 710:
      paramNumImageView1.setImageResource(R.drawable.gpazoom);
      paramNumImageView2.setImageResource(R.drawable.gpazoom);
      paramNumImageView3.setImageResource(R.drawable.gpazoom);
      paramNumImageView4.setImageResource(R.drawable.gpazoom);
      paramNumImageView5.setImageResource(R.drawable.gpazoom);
      break;
    case 705:
    case 706:
    case 707:
    case 708:
    case 709:
      paramNumImageView1.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView2.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView3.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView4.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView5.setImageResource(R.drawable.newkeyzoom);
      break;
    case 704:
      paramNumImageView1.setImageResource(R.drawable.center3zoom);
      paramNumImageView2.setImageResource(R.drawable.center3zoom);
      paramNumImageView3.setImageResource(R.drawable.center3zoom);
      paramNumImageView4.setImageResource(R.drawable.center3zoom);
      paramNumImageView5.setImageResource(R.drawable.center3zoom);
      break;
    case 703:
      paramNumImageView1.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbpr2zoom);
      break;
    case 702:
      paramNumImageView1.setImageResource(R.drawable.center2zoom);
      paramNumImageView2.setImageResource(R.drawable.center2zoom);
      paramNumImageView3.setImageResource(R.drawable.center2zoom);
      paramNumImageView4.setImageResource(R.drawable.center2zoom);
      paramNumImageView5.setImageResource(R.drawable.center2zoom);
      break;
    case 701:
      paramNumImageView1.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbpr1zoom);
      break;
    case 700:
      paramNumImageView1.setImageResource(R.drawable.center1zoom);
      paramNumImageView2.setImageResource(R.drawable.center1zoom);
      paramNumImageView3.setImageResource(R.drawable.center1zoom);
      paramNumImageView4.setImageResource(R.drawable.center1zoom);
      paramNumImageView5.setImageResource(R.drawable.center1zoom);
      break;
    case 699:
      paramNumImageView1.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm4zoom);
      break;
    case 698:
      paramNumImageView1.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm3zoom);
      break;
    case 697:
      paramNumImageView1.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm2zoom);
      break;
    case 696:
      paramNumImageView1.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm1zoom);
      break;
    case 695:
      paramNumImageView1.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbprzoom);
      break;
    case 694:
      paramNumImageView1.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbplzoom);
      break;
    case 693:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprtzoom);
        paramNumImageView2.setImageResource(R.drawable.gprtzoom);
        paramNumImageView3.setImageResource(R.drawable.gprtzoom);
        paramNumImageView4.setImageResource(R.drawable.gprtzoom);
        paramNumImageView5.setImageResource(R.drawable.gprtzoom);
      }
      break;
    case 692:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpltzoom);
        paramNumImageView2.setImageResource(R.drawable.gpltzoom);
        paramNumImageView3.setImageResource(R.drawable.gpltzoom);
        paramNumImageView4.setImageResource(R.drawable.gpltzoom);
        paramNumImageView5.setImageResource(R.drawable.gpltzoom);
      }
      break;
    case 691:
      paramNumImageView1.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpr3zoom);
      break;
    case 690:
      paramNumImageView1.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpl3zoom);
      break;
    case 689:
      paramNumImageView1.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView2.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView3.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView4.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView5.setImageResource(R.drawable.gpselectzoom);
      break;
    case 688:
      paramNumImageView1.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView2.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView3.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView4.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView5.setImageResource(R.drawable.gpstartzoom);
      break;
    case 687:
      paramNumImageView1.setImageResource(R.drawable.gprightzoom);
      paramNumImageView2.setImageResource(R.drawable.gprightzoom);
      paramNumImageView3.setImageResource(R.drawable.gprightzoom);
      paramNumImageView4.setImageResource(R.drawable.gprightzoom);
      paramNumImageView5.setImageResource(R.drawable.gprightzoom);
      break;
    case 686:
      paramNumImageView1.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView2.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView3.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView4.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView5.setImageResource(R.drawable.gpleftzoom);
      break;
    case 685:
      paramNumImageView1.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView2.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView3.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView4.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView5.setImageResource(R.drawable.gpdownzoom);
      break;
    case 684:
      paramNumImageView1.setImageResource(R.drawable.gpupzoom);
      paramNumImageView2.setImageResource(R.drawable.gpupzoom);
      paramNumImageView3.setImageResource(R.drawable.gpupzoom);
      paramNumImageView4.setImageResource(R.drawable.gpupzoom);
      paramNumImageView5.setImageResource(R.drawable.gpupzoom);
      break;
    case 683:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprbzoom);
        paramNumImageView2.setImageResource(R.drawable.gprbzoom);
        paramNumImageView3.setImageResource(R.drawable.gprbzoom);
        paramNumImageView4.setImageResource(R.drawable.gprbzoom);
        paramNumImageView5.setImageResource(R.drawable.gprbzoom);
      }
      break;
    case 682:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gplbzoom);
        paramNumImageView2.setImageResource(R.drawable.gplbzoom);
        paramNumImageView3.setImageResource(R.drawable.gplbzoom);
        paramNumImageView4.setImageResource(R.drawable.gplbzoom);
        paramNumImageView5.setImageResource(R.drawable.gplbzoom);
      }
      break;
    case 681:
      paramNumImageView1.setImageResource(R.drawable.gpyzoom);
      paramNumImageView2.setImageResource(R.drawable.gpyzoom);
      paramNumImageView3.setImageResource(R.drawable.gpyzoom);
      paramNumImageView4.setImageResource(R.drawable.gpyzoom);
      paramNumImageView5.setImageResource(R.drawable.gpyzoom);
      break;
    case 680:
      paramNumImageView1.setImageResource(R.drawable.gpxzoom);
      paramNumImageView2.setImageResource(R.drawable.gpxzoom);
      paramNumImageView3.setImageResource(R.drawable.gpxzoom);
      paramNumImageView4.setImageResource(R.drawable.gpxzoom);
      paramNumImageView5.setImageResource(R.drawable.gpxzoom);
      break;
    case 679:
      paramNumImageView1.setImageResource(R.drawable.gpbzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbzoom);
      break;
    case 678:
      paramNumImageView1.setImageResource(R.drawable.gpazoom);
      paramNumImageView2.setImageResource(R.drawable.gpazoom);
      paramNumImageView3.setImageResource(R.drawable.gpazoom);
      paramNumImageView4.setImageResource(R.drawable.gpazoom);
      paramNumImageView5.setImageResource(R.drawable.gpazoom);
      break;
    case 673:
    case 674:
    case 675:
    case 676:
    case 677:
      paramNumImageView1.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView2.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView3.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView4.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView5.setImageResource(R.drawable.newkeyzoom);
      break;
    case 672:
      paramNumImageView1.setImageResource(R.drawable.center3zoom);
      paramNumImageView2.setImageResource(R.drawable.center3zoom);
      paramNumImageView3.setImageResource(R.drawable.center3zoom);
      paramNumImageView4.setImageResource(R.drawable.center3zoom);
      paramNumImageView5.setImageResource(R.drawable.center3zoom);
      break;
    case 671:
      paramNumImageView1.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbpr2zoom);
      break;
    case 670:
      paramNumImageView1.setImageResource(R.drawable.center2zoom);
      paramNumImageView2.setImageResource(R.drawable.center2zoom);
      paramNumImageView3.setImageResource(R.drawable.center2zoom);
      paramNumImageView4.setImageResource(R.drawable.center2zoom);
      paramNumImageView5.setImageResource(R.drawable.center2zoom);
      break;
    case 669:
      paramNumImageView1.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbpr1zoom);
      break;
    case 668:
      paramNumImageView1.setImageResource(R.drawable.center1zoom);
      paramNumImageView2.setImageResource(R.drawable.center1zoom);
      paramNumImageView3.setImageResource(R.drawable.center1zoom);
      paramNumImageView4.setImageResource(R.drawable.center1zoom);
      paramNumImageView5.setImageResource(R.drawable.center1zoom);
      break;
    case 667:
      paramNumImageView1.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm4zoom);
      break;
    case 666:
      paramNumImageView1.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm3zoom);
      break;
    case 665:
      paramNumImageView1.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm2zoom);
      break;
    case 664:
      paramNumImageView1.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm1zoom);
      break;
    case 663:
      paramNumImageView1.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbprzoom);
      break;
    case 662:
      paramNumImageView1.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbplzoom);
      break;
    case 661:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprtzoom);
        paramNumImageView2.setImageResource(R.drawable.gprtzoom);
        paramNumImageView3.setImageResource(R.drawable.gprtzoom);
        paramNumImageView4.setImageResource(R.drawable.gprtzoom);
        paramNumImageView5.setImageResource(R.drawable.gprtzoom);
      }
      break;
    case 660:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpltzoom);
        paramNumImageView2.setImageResource(R.drawable.gpltzoom);
        paramNumImageView3.setImageResource(R.drawable.gpltzoom);
        paramNumImageView4.setImageResource(R.drawable.gpltzoom);
        paramNumImageView5.setImageResource(R.drawable.gpltzoom);
      }
      break;
    case 659:
      paramNumImageView1.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpr3zoom);
      break;
    case 658:
      paramNumImageView1.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpl3zoom);
      break;
    case 657:
      paramNumImageView1.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView2.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView3.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView4.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView5.setImageResource(R.drawable.gpselectzoom);
      break;
    case 656:
      paramNumImageView1.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView2.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView3.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView4.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView5.setImageResource(R.drawable.gpstartzoom);
      break;
    case 655:
      paramNumImageView1.setImageResource(R.drawable.gprightzoom);
      paramNumImageView2.setImageResource(R.drawable.gprightzoom);
      paramNumImageView3.setImageResource(R.drawable.gprightzoom);
      paramNumImageView4.setImageResource(R.drawable.gprightzoom);
      paramNumImageView5.setImageResource(R.drawable.gprightzoom);
      break;
    case 654:
      paramNumImageView1.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView2.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView3.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView4.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView5.setImageResource(R.drawable.gpleftzoom);
      break;
    case 653:
      paramNumImageView1.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView2.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView3.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView4.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView5.setImageResource(R.drawable.gpdownzoom);
      break;
    case 652:
      paramNumImageView1.setImageResource(R.drawable.gpupzoom);
      paramNumImageView2.setImageResource(R.drawable.gpupzoom);
      paramNumImageView3.setImageResource(R.drawable.gpupzoom);
      paramNumImageView4.setImageResource(R.drawable.gpupzoom);
      paramNumImageView5.setImageResource(R.drawable.gpupzoom);
      break;
    case 651:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprbzoom);
        paramNumImageView2.setImageResource(R.drawable.gprbzoom);
        paramNumImageView3.setImageResource(R.drawable.gprbzoom);
        paramNumImageView4.setImageResource(R.drawable.gprbzoom);
        paramNumImageView5.setImageResource(R.drawable.gprbzoom);
      }
      break;
    case 650:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gplbzoom);
        paramNumImageView2.setImageResource(R.drawable.gplbzoom);
        paramNumImageView3.setImageResource(R.drawable.gplbzoom);
        paramNumImageView4.setImageResource(R.drawable.gplbzoom);
        paramNumImageView5.setImageResource(R.drawable.gplbzoom);
      }
      break;
    case 649:
      paramNumImageView1.setImageResource(R.drawable.gpyzoom);
      paramNumImageView2.setImageResource(R.drawable.gpyzoom);
      paramNumImageView3.setImageResource(R.drawable.gpyzoom);
      paramNumImageView4.setImageResource(R.drawable.gpyzoom);
      paramNumImageView5.setImageResource(R.drawable.gpyzoom);
      break;
    case 648:
      paramNumImageView1.setImageResource(R.drawable.gpxzoom);
      paramNumImageView2.setImageResource(R.drawable.gpxzoom);
      paramNumImageView3.setImageResource(R.drawable.gpxzoom);
      paramNumImageView4.setImageResource(R.drawable.gpxzoom);
      paramNumImageView5.setImageResource(R.drawable.gpxzoom);
      break;
    case 647:
      paramNumImageView1.setImageResource(R.drawable.gpbzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbzoom);
      break;
    case 646:
      paramNumImageView1.setImageResource(R.drawable.gpazoom);
      paramNumImageView2.setImageResource(R.drawable.gpazoom);
      paramNumImageView3.setImageResource(R.drawable.gpazoom);
      paramNumImageView4.setImageResource(R.drawable.gpazoom);
      paramNumImageView5.setImageResource(R.drawable.gpazoom);
      break;
    case 641:
    case 642:
    case 643:
    case 644:
    case 645:
      paramNumImageView1.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView2.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView3.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView4.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView5.setImageResource(R.drawable.newkeyzoom);
      break;
    case 640:
      paramNumImageView1.setImageResource(R.drawable.center3zoom);
      paramNumImageView2.setImageResource(R.drawable.center3zoom);
      paramNumImageView3.setImageResource(R.drawable.center3zoom);
      paramNumImageView4.setImageResource(R.drawable.center3zoom);
      paramNumImageView5.setImageResource(R.drawable.center3zoom);
      break;
    case 639:
      paramNumImageView1.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbpr2zoom);
      break;
    case 638:
      paramNumImageView1.setImageResource(R.drawable.center2zoom);
      paramNumImageView2.setImageResource(R.drawable.center2zoom);
      paramNumImageView3.setImageResource(R.drawable.center2zoom);
      paramNumImageView4.setImageResource(R.drawable.center2zoom);
      paramNumImageView5.setImageResource(R.drawable.center2zoom);
      break;
    case 637:
      paramNumImageView1.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbpr1zoom);
      break;
    case 636:
      paramNumImageView1.setImageResource(R.drawable.center1zoom);
      paramNumImageView2.setImageResource(R.drawable.center1zoom);
      paramNumImageView3.setImageResource(R.drawable.center1zoom);
      paramNumImageView4.setImageResource(R.drawable.center1zoom);
      paramNumImageView5.setImageResource(R.drawable.center1zoom);
      break;
    case 635:
      paramNumImageView1.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm4zoom);
      break;
    case 634:
      paramNumImageView1.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm3zoom);
      break;
    case 633:
      paramNumImageView1.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm2zoom);
      break;
    case 632:
      paramNumImageView1.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm1zoom);
      break;
    case 631:
      paramNumImageView1.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbprzoom);
      break;
    case 630:
      paramNumImageView1.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbplzoom);
      break;
    case 629:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprtzoom);
        paramNumImageView2.setImageResource(R.drawable.gprtzoom);
        paramNumImageView3.setImageResource(R.drawable.gprtzoom);
        paramNumImageView4.setImageResource(R.drawable.gprtzoom);
        paramNumImageView5.setImageResource(R.drawable.gprtzoom);
      }
      break;
    case 628:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpltzoom);
        paramNumImageView2.setImageResource(R.drawable.gpltzoom);
        paramNumImageView3.setImageResource(R.drawable.gpltzoom);
        paramNumImageView4.setImageResource(R.drawable.gpltzoom);
        paramNumImageView5.setImageResource(R.drawable.gpltzoom);
      }
      break;
    case 627:
      paramNumImageView1.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpr3zoom);
      break;
    case 626:
      paramNumImageView1.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpl3zoom);
      break;
    case 625:
      paramNumImageView1.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView2.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView3.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView4.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView5.setImageResource(R.drawable.gpselectzoom);
      break;
    case 624:
      paramNumImageView1.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView2.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView3.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView4.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView5.setImageResource(R.drawable.gpstartzoom);
      break;
    case 623:
      paramNumImageView1.setImageResource(R.drawable.gprightzoom);
      paramNumImageView2.setImageResource(R.drawable.gprightzoom);
      paramNumImageView3.setImageResource(R.drawable.gprightzoom);
      paramNumImageView4.setImageResource(R.drawable.gprightzoom);
      paramNumImageView5.setImageResource(R.drawable.gprightzoom);
      break;
    case 622:
      paramNumImageView1.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView2.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView3.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView4.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView5.setImageResource(R.drawable.gpleftzoom);
      break;
    case 621:
      paramNumImageView1.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView2.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView3.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView4.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView5.setImageResource(R.drawable.gpdownzoom);
      break;
    case 620:
      paramNumImageView1.setImageResource(R.drawable.gpupzoom);
      paramNumImageView2.setImageResource(R.drawable.gpupzoom);
      paramNumImageView3.setImageResource(R.drawable.gpupzoom);
      paramNumImageView4.setImageResource(R.drawable.gpupzoom);
      paramNumImageView5.setImageResource(R.drawable.gpupzoom);
      break;
    case 619:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprbzoom);
        paramNumImageView2.setImageResource(R.drawable.gprbzoom);
        paramNumImageView3.setImageResource(R.drawable.gprbzoom);
        paramNumImageView4.setImageResource(R.drawable.gprbzoom);
        paramNumImageView5.setImageResource(R.drawable.gprbzoom);
      }
      break;
    case 618:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gplbzoom);
        paramNumImageView2.setImageResource(R.drawable.gplbzoom);
        paramNumImageView3.setImageResource(R.drawable.gplbzoom);
        paramNumImageView4.setImageResource(R.drawable.gplbzoom);
        paramNumImageView5.setImageResource(R.drawable.gplbzoom);
      }
      break;
    case 617:
      paramNumImageView1.setImageResource(R.drawable.gpyzoom);
      paramNumImageView2.setImageResource(R.drawable.gpyzoom);
      paramNumImageView3.setImageResource(R.drawable.gpyzoom);
      paramNumImageView4.setImageResource(R.drawable.gpyzoom);
      paramNumImageView5.setImageResource(R.drawable.gpyzoom);
      break;
    case 616:
      paramNumImageView1.setImageResource(R.drawable.gpxzoom);
      paramNumImageView2.setImageResource(R.drawable.gpxzoom);
      paramNumImageView3.setImageResource(R.drawable.gpxzoom);
      paramNumImageView4.setImageResource(R.drawable.gpxzoom);
      paramNumImageView5.setImageResource(R.drawable.gpxzoom);
      break;
    case 615:
      paramNumImageView1.setImageResource(R.drawable.gpbzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbzoom);
      break;
    case 614:
      paramNumImageView1.setImageResource(R.drawable.gpazoom);
      paramNumImageView2.setImageResource(R.drawable.gpazoom);
      paramNumImageView3.setImageResource(R.drawable.gpazoom);
      paramNumImageView4.setImageResource(R.drawable.gpazoom);
      paramNumImageView5.setImageResource(R.drawable.gpazoom);
      break;
    case 609:
    case 610:
    case 611:
    case 612:
    case 613:
      paramNumImageView1.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView2.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView3.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView4.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView5.setImageResource(R.drawable.newkeyzoom);
      break;
    case 608:
      paramNumImageView1.setImageResource(R.drawable.center3zoom);
      paramNumImageView2.setImageResource(R.drawable.center3zoom);
      paramNumImageView3.setImageResource(R.drawable.center3zoom);
      paramNumImageView4.setImageResource(R.drawable.center3zoom);
      paramNumImageView5.setImageResource(R.drawable.center3zoom);
      break;
    case 607:
      paramNumImageView1.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbpr2zoom);
      break;
    case 606:
      paramNumImageView1.setImageResource(R.drawable.center2zoom);
      paramNumImageView2.setImageResource(R.drawable.center2zoom);
      paramNumImageView3.setImageResource(R.drawable.center2zoom);
      paramNumImageView4.setImageResource(R.drawable.center2zoom);
      paramNumImageView5.setImageResource(R.drawable.center2zoom);
      break;
    case 605:
      paramNumImageView1.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbpr1zoom);
      break;
    case 604:
      paramNumImageView1.setImageResource(R.drawable.center1zoom);
      paramNumImageView2.setImageResource(R.drawable.center1zoom);
      paramNumImageView3.setImageResource(R.drawable.center1zoom);
      paramNumImageView4.setImageResource(R.drawable.center1zoom);
      paramNumImageView5.setImageResource(R.drawable.center1zoom);
      break;
    case 603:
      paramNumImageView1.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm4zoom);
      break;
    case 602:
      paramNumImageView1.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm3zoom);
      break;
    case 601:
      paramNumImageView1.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm2zoom);
      break;
    case 600:
      paramNumImageView1.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm1zoom);
      break;
    case 599:
      paramNumImageView1.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbprzoom);
      break;
    case 598:
      paramNumImageView1.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbplzoom);
      break;
    case 597:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprtzoom);
        paramNumImageView2.setImageResource(R.drawable.gprtzoom);
        paramNumImageView3.setImageResource(R.drawable.gprtzoom);
        paramNumImageView4.setImageResource(R.drawable.gprtzoom);
        paramNumImageView5.setImageResource(R.drawable.gprtzoom);
      }
      break;
    case 596:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpltzoom);
        paramNumImageView2.setImageResource(R.drawable.gpltzoom);
        paramNumImageView3.setImageResource(R.drawable.gpltzoom);
        paramNumImageView4.setImageResource(R.drawable.gpltzoom);
        paramNumImageView5.setImageResource(R.drawable.gpltzoom);
      }
      break;
    case 595:
      paramNumImageView1.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpr3zoom);
      break;
    case 594:
      paramNumImageView1.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpl3zoom);
      break;
    case 593:
      paramNumImageView1.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView2.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView3.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView4.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView5.setImageResource(R.drawable.gpselectzoom);
      break;
    case 592:
      paramNumImageView1.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView2.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView3.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView4.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView5.setImageResource(R.drawable.gpstartzoom);
      break;
    case 591:
      paramNumImageView1.setImageResource(R.drawable.gprightzoom);
      paramNumImageView2.setImageResource(R.drawable.gprightzoom);
      paramNumImageView3.setImageResource(R.drawable.gprightzoom);
      paramNumImageView4.setImageResource(R.drawable.gprightzoom);
      paramNumImageView5.setImageResource(R.drawable.gprightzoom);
      break;
    case 590:
      paramNumImageView1.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView2.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView3.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView4.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView5.setImageResource(R.drawable.gpleftzoom);
      break;
    case 589:
      paramNumImageView1.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView2.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView3.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView4.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView5.setImageResource(R.drawable.gpdownzoom);
      break;
    case 588:
      paramNumImageView1.setImageResource(R.drawable.gpupzoom);
      paramNumImageView2.setImageResource(R.drawable.gpupzoom);
      paramNumImageView3.setImageResource(R.drawable.gpupzoom);
      paramNumImageView4.setImageResource(R.drawable.gpupzoom);
      paramNumImageView5.setImageResource(R.drawable.gpupzoom);
      break;
    case 587:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprbzoom);
        paramNumImageView2.setImageResource(R.drawable.gprbzoom);
        paramNumImageView3.setImageResource(R.drawable.gprbzoom);
        paramNumImageView4.setImageResource(R.drawable.gprbzoom);
        paramNumImageView5.setImageResource(R.drawable.gprbzoom);
      }
      break;
    case 586:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gplbzoom);
        paramNumImageView2.setImageResource(R.drawable.gplbzoom);
        paramNumImageView3.setImageResource(R.drawable.gplbzoom);
        paramNumImageView4.setImageResource(R.drawable.gplbzoom);
        paramNumImageView5.setImageResource(R.drawable.gplbzoom);
      }
      break;
    case 585:
      paramNumImageView1.setImageResource(R.drawable.gpyzoom);
      paramNumImageView2.setImageResource(R.drawable.gpyzoom);
      paramNumImageView3.setImageResource(R.drawable.gpyzoom);
      paramNumImageView4.setImageResource(R.drawable.gpyzoom);
      paramNumImageView5.setImageResource(R.drawable.gpyzoom);
      break;
    case 584:
      paramNumImageView1.setImageResource(R.drawable.gpxzoom);
      paramNumImageView2.setImageResource(R.drawable.gpxzoom);
      paramNumImageView3.setImageResource(R.drawable.gpxzoom);
      paramNumImageView4.setImageResource(R.drawable.gpxzoom);
      paramNumImageView5.setImageResource(R.drawable.gpxzoom);
      break;
    case 583:
      paramNumImageView1.setImageResource(R.drawable.gpbzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbzoom);
      break;
    case 582:
      paramNumImageView1.setImageResource(R.drawable.gpazoom);
      paramNumImageView2.setImageResource(R.drawable.gpazoom);
      paramNumImageView3.setImageResource(R.drawable.gpazoom);
      paramNumImageView4.setImageResource(R.drawable.gpazoom);
      paramNumImageView5.setImageResource(R.drawable.gpazoom);
      break;
    case 577:
    case 578:
    case 579:
    case 580:
    case 581:
      paramNumImageView1.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView2.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView3.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView4.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView5.setImageResource(R.drawable.newkeyzoom);
      break;
    case 576:
      paramNumImageView1.setImageResource(R.drawable.center3zoom);
      paramNumImageView2.setImageResource(R.drawable.center3zoom);
      paramNumImageView3.setImageResource(R.drawable.center3zoom);
      paramNumImageView4.setImageResource(R.drawable.center3zoom);
      paramNumImageView5.setImageResource(R.drawable.center3zoom);
      break;
    case 575:
      paramNumImageView1.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbpr2zoom);
      break;
    case 574:
      paramNumImageView1.setImageResource(R.drawable.center2zoom);
      paramNumImageView2.setImageResource(R.drawable.center2zoom);
      paramNumImageView3.setImageResource(R.drawable.center2zoom);
      paramNumImageView4.setImageResource(R.drawable.center2zoom);
      paramNumImageView5.setImageResource(R.drawable.center2zoom);
      break;
    case 573:
      paramNumImageView1.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbpr1zoom);
      break;
    case 572:
      paramNumImageView1.setImageResource(R.drawable.center1zoom);
      paramNumImageView2.setImageResource(R.drawable.center1zoom);
      paramNumImageView3.setImageResource(R.drawable.center1zoom);
      paramNumImageView4.setImageResource(R.drawable.center1zoom);
      paramNumImageView5.setImageResource(R.drawable.center1zoom);
      break;
    case 571:
      paramNumImageView1.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm4zoom);
      break;
    case 570:
      paramNumImageView1.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm3zoom);
      break;
    case 569:
      paramNumImageView1.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm2zoom);
      break;
    case 568:
      paramNumImageView1.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm1zoom);
      break;
    case 567:
      paramNumImageView1.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbprzoom);
      break;
    case 566:
      paramNumImageView1.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbplzoom);
      break;
    case 565:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprtzoom);
        paramNumImageView2.setImageResource(R.drawable.gprtzoom);
        paramNumImageView3.setImageResource(R.drawable.gprtzoom);
        paramNumImageView4.setImageResource(R.drawable.gprtzoom);
        paramNumImageView5.setImageResource(R.drawable.gprtzoom);
      }
      break;
    case 564:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpltzoom);
        paramNumImageView2.setImageResource(R.drawable.gpltzoom);
        paramNumImageView3.setImageResource(R.drawable.gpltzoom);
        paramNumImageView4.setImageResource(R.drawable.gpltzoom);
        paramNumImageView5.setImageResource(R.drawable.gpltzoom);
      }
      break;
    case 563:
      paramNumImageView1.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpr3zoom);
      break;
    case 562:
      paramNumImageView1.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpl3zoom);
      break;
    case 561:
      paramNumImageView1.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView2.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView3.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView4.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView5.setImageResource(R.drawable.gpselectzoom);
      break;
    case 560:
      paramNumImageView1.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView2.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView3.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView4.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView5.setImageResource(R.drawable.gpstartzoom);
      break;
    case 559:
      paramNumImageView1.setImageResource(R.drawable.gprightzoom);
      paramNumImageView2.setImageResource(R.drawable.gprightzoom);
      paramNumImageView3.setImageResource(R.drawable.gprightzoom);
      paramNumImageView4.setImageResource(R.drawable.gprightzoom);
      paramNumImageView5.setImageResource(R.drawable.gprightzoom);
      break;
    case 558:
      paramNumImageView1.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView2.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView3.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView4.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView5.setImageResource(R.drawable.gpleftzoom);
      break;
    case 557:
      paramNumImageView1.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView2.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView3.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView4.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView5.setImageResource(R.drawable.gpdownzoom);
      break;
    case 556:
      paramNumImageView1.setImageResource(R.drawable.gpupzoom);
      paramNumImageView2.setImageResource(R.drawable.gpupzoom);
      paramNumImageView3.setImageResource(R.drawable.gpupzoom);
      paramNumImageView4.setImageResource(R.drawable.gpupzoom);
      paramNumImageView5.setImageResource(R.drawable.gpupzoom);
      break;
    case 555:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprbzoom);
        paramNumImageView2.setImageResource(R.drawable.gprbzoom);
        paramNumImageView3.setImageResource(R.drawable.gprbzoom);
        paramNumImageView4.setImageResource(R.drawable.gprbzoom);
        paramNumImageView5.setImageResource(R.drawable.gprbzoom);
      }
      break;
    case 554:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gplbzoom);
        paramNumImageView2.setImageResource(R.drawable.gplbzoom);
        paramNumImageView3.setImageResource(R.drawable.gplbzoom);
        paramNumImageView4.setImageResource(R.drawable.gplbzoom);
        paramNumImageView5.setImageResource(R.drawable.gplbzoom);
      }
      break;
    case 553:
      paramNumImageView1.setImageResource(R.drawable.gpyzoom);
      paramNumImageView2.setImageResource(R.drawable.gpyzoom);
      paramNumImageView3.setImageResource(R.drawable.gpyzoom);
      paramNumImageView4.setImageResource(R.drawable.gpyzoom);
      paramNumImageView5.setImageResource(R.drawable.gpyzoom);
      break;
    case 552:
      paramNumImageView1.setImageResource(R.drawable.gpxzoom);
      paramNumImageView2.setImageResource(R.drawable.gpxzoom);
      paramNumImageView3.setImageResource(R.drawable.gpxzoom);
      paramNumImageView4.setImageResource(R.drawable.gpxzoom);
      paramNumImageView5.setImageResource(R.drawable.gpxzoom);
      break;
    case 551:
      paramNumImageView1.setImageResource(R.drawable.gpbzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbzoom);
      break;
    case 550:
      paramNumImageView1.setImageResource(R.drawable.gpazoom);
      paramNumImageView2.setImageResource(R.drawable.gpazoom);
      paramNumImageView3.setImageResource(R.drawable.gpazoom);
      paramNumImageView4.setImageResource(R.drawable.gpazoom);
      paramNumImageView5.setImageResource(R.drawable.gpazoom);
      break;
    case 545:
    case 546:
    case 547:
    case 548:
    case 549:
      paramNumImageView1.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView2.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView3.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView4.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView5.setImageResource(R.drawable.newkeyzoom);
      break;
    case 544:
      paramNumImageView1.setImageResource(R.drawable.center3zoom);
      paramNumImageView2.setImageResource(R.drawable.center3zoom);
      paramNumImageView3.setImageResource(R.drawable.center3zoom);
      paramNumImageView4.setImageResource(R.drawable.center3zoom);
      paramNumImageView5.setImageResource(R.drawable.center3zoom);
      break;
    case 543:
      paramNumImageView1.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbpr2zoom);
      break;
    case 542:
      paramNumImageView1.setImageResource(R.drawable.center2zoom);
      paramNumImageView2.setImageResource(R.drawable.center2zoom);
      paramNumImageView3.setImageResource(R.drawable.center2zoom);
      paramNumImageView4.setImageResource(R.drawable.center2zoom);
      paramNumImageView5.setImageResource(R.drawable.center2zoom);
      break;
    case 541:
      paramNumImageView1.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbpr1zoom);
      break;
    case 540:
      paramNumImageView1.setImageResource(R.drawable.center1zoom);
      paramNumImageView2.setImageResource(R.drawable.center1zoom);
      paramNumImageView3.setImageResource(R.drawable.center1zoom);
      paramNumImageView4.setImageResource(R.drawable.center1zoom);
      paramNumImageView5.setImageResource(R.drawable.center1zoom);
      break;
    case 539:
      paramNumImageView1.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm4zoom);
      break;
    case 538:
      paramNumImageView1.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm3zoom);
      break;
    case 537:
      paramNumImageView1.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm2zoom);
      break;
    case 536:
      paramNumImageView1.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm1zoom);
      break;
    case 535:
      paramNumImageView1.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbprzoom);
      break;
    case 534:
      paramNumImageView1.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbplzoom);
      break;
    case 533:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprtzoom);
        paramNumImageView2.setImageResource(R.drawable.gprtzoom);
        paramNumImageView3.setImageResource(R.drawable.gprtzoom);
        paramNumImageView4.setImageResource(R.drawable.gprtzoom);
        paramNumImageView5.setImageResource(R.drawable.gprtzoom);
      }
      break;
    case 532:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpltzoom);
        paramNumImageView2.setImageResource(R.drawable.gpltzoom);
        paramNumImageView3.setImageResource(R.drawable.gpltzoom);
        paramNumImageView4.setImageResource(R.drawable.gpltzoom);
        paramNumImageView5.setImageResource(R.drawable.gpltzoom);
      }
      break;
    case 531:
      paramNumImageView1.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpr3zoom);
      break;
    case 530:
      paramNumImageView1.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpl3zoom);
      break;
    case 529:
      paramNumImageView1.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView2.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView3.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView4.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView5.setImageResource(R.drawable.gpselectzoom);
      break;
    case 528:
      paramNumImageView1.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView2.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView3.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView4.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView5.setImageResource(R.drawable.gpstartzoom);
      break;
    case 527:
      paramNumImageView1.setImageResource(R.drawable.gprightzoom);
      paramNumImageView2.setImageResource(R.drawable.gprightzoom);
      paramNumImageView3.setImageResource(R.drawable.gprightzoom);
      paramNumImageView4.setImageResource(R.drawable.gprightzoom);
      paramNumImageView5.setImageResource(R.drawable.gprightzoom);
      break;
    case 526:
      paramNumImageView1.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView2.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView3.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView4.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView5.setImageResource(R.drawable.gpleftzoom);
      break;
    case 525:
      paramNumImageView1.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView2.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView3.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView4.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView5.setImageResource(R.drawable.gpdownzoom);
      break;
    case 524:
      paramNumImageView1.setImageResource(R.drawable.gpupzoom);
      paramNumImageView2.setImageResource(R.drawable.gpupzoom);
      paramNumImageView3.setImageResource(R.drawable.gpupzoom);
      paramNumImageView4.setImageResource(R.drawable.gpupzoom);
      paramNumImageView5.setImageResource(R.drawable.gpupzoom);
      break;
    case 523:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprbzoom);
        paramNumImageView2.setImageResource(R.drawable.gprbzoom);
        paramNumImageView3.setImageResource(R.drawable.gprbzoom);
        paramNumImageView4.setImageResource(R.drawable.gprbzoom);
        paramNumImageView5.setImageResource(R.drawable.gprbzoom);
      }
      break;
    case 522:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gplbzoom);
        paramNumImageView2.setImageResource(R.drawable.gplbzoom);
        paramNumImageView3.setImageResource(R.drawable.gplbzoom);
        paramNumImageView4.setImageResource(R.drawable.gplbzoom);
        paramNumImageView5.setImageResource(R.drawable.gplbzoom);
      }
      break;
    case 521:
      paramNumImageView1.setImageResource(R.drawable.gpyzoom);
      paramNumImageView2.setImageResource(R.drawable.gpyzoom);
      paramNumImageView3.setImageResource(R.drawable.gpyzoom);
      paramNumImageView4.setImageResource(R.drawable.gpyzoom);
      paramNumImageView5.setImageResource(R.drawable.gpyzoom);
      break;
    case 520:
      paramNumImageView1.setImageResource(R.drawable.gpxzoom);
      paramNumImageView2.setImageResource(R.drawable.gpxzoom);
      paramNumImageView3.setImageResource(R.drawable.gpxzoom);
      paramNumImageView4.setImageResource(R.drawable.gpxzoom);
      paramNumImageView5.setImageResource(R.drawable.gpxzoom);
      break;
    case 519:
      paramNumImageView1.setImageResource(R.drawable.gpbzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbzoom);
      break;
    case 518:
      paramNumImageView1.setImageResource(R.drawable.gpazoom);
      paramNumImageView2.setImageResource(R.drawable.gpazoom);
      paramNumImageView3.setImageResource(R.drawable.gpazoom);
      paramNumImageView4.setImageResource(R.drawable.gpazoom);
      paramNumImageView5.setImageResource(R.drawable.gpazoom);
      break;
    case 513:
    case 514:
    case 515:
    case 516:
    case 517:
      paramNumImageView1.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView2.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView3.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView4.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView5.setImageResource(R.drawable.newkeyzoom);
      break;
    case 512:
      paramNumImageView1.setImageResource(R.drawable.center3zoom);
      paramNumImageView2.setImageResource(R.drawable.center3zoom);
      paramNumImageView3.setImageResource(R.drawable.center3zoom);
      paramNumImageView4.setImageResource(R.drawable.center3zoom);
      paramNumImageView5.setImageResource(R.drawable.center3zoom);
      break;
    case 511:
      paramNumImageView1.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbpr2zoom);
      break;
    case 510:
      paramNumImageView1.setImageResource(R.drawable.center2zoom);
      paramNumImageView2.setImageResource(R.drawable.center2zoom);
      paramNumImageView3.setImageResource(R.drawable.center2zoom);
      paramNumImageView4.setImageResource(R.drawable.center2zoom);
      paramNumImageView5.setImageResource(R.drawable.center2zoom);
      break;
    case 509:
      paramNumImageView1.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbpr1zoom);
      break;
    case 508:
      paramNumImageView1.setImageResource(R.drawable.center1zoom);
      paramNumImageView2.setImageResource(R.drawable.center1zoom);
      paramNumImageView3.setImageResource(R.drawable.center1zoom);
      paramNumImageView4.setImageResource(R.drawable.center1zoom);
      paramNumImageView5.setImageResource(R.drawable.center1zoom);
      break;
    case 507:
      paramNumImageView1.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm4zoom);
      break;
    case 506:
      paramNumImageView1.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm3zoom);
      break;
    case 505:
      paramNumImageView1.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm2zoom);
      break;
    case 504:
      paramNumImageView1.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm1zoom);
      break;
    case 503:
      paramNumImageView1.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbprzoom);
      break;
    case 502:
      paramNumImageView1.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbplzoom);
      break;
    case 501:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprtzoom);
        paramNumImageView2.setImageResource(R.drawable.gprtzoom);
        paramNumImageView3.setImageResource(R.drawable.gprtzoom);
        paramNumImageView4.setImageResource(R.drawable.gprtzoom);
        paramNumImageView5.setImageResource(R.drawable.gprtzoom);
      }
      break;
    case 500:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpltzoom);
        paramNumImageView2.setImageResource(R.drawable.gpltzoom);
        paramNumImageView3.setImageResource(R.drawable.gpltzoom);
        paramNumImageView4.setImageResource(R.drawable.gpltzoom);
        paramNumImageView5.setImageResource(R.drawable.gpltzoom);
      }
      break;
    case 499:
      paramNumImageView1.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpr3zoom);
      break;
    case 498:
      paramNumImageView1.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpl3zoom);
      break;
    case 497:
      paramNumImageView1.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView2.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView3.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView4.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView5.setImageResource(R.drawable.gpselectzoom);
      break;
    case 496:
      paramNumImageView1.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView2.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView3.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView4.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView5.setImageResource(R.drawable.gpstartzoom);
      break;
    case 495:
      paramNumImageView1.setImageResource(R.drawable.gprightzoom);
      paramNumImageView2.setImageResource(R.drawable.gprightzoom);
      paramNumImageView3.setImageResource(R.drawable.gprightzoom);
      paramNumImageView4.setImageResource(R.drawable.gprightzoom);
      paramNumImageView5.setImageResource(R.drawable.gprightzoom);
      break;
    case 494:
      paramNumImageView1.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView2.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView3.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView4.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView5.setImageResource(R.drawable.gpleftzoom);
      break;
    case 493:
      paramNumImageView1.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView2.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView3.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView4.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView5.setImageResource(R.drawable.gpdownzoom);
      break;
    case 492:
      paramNumImageView1.setImageResource(R.drawable.gpupzoom);
      paramNumImageView2.setImageResource(R.drawable.gpupzoom);
      paramNumImageView3.setImageResource(R.drawable.gpupzoom);
      paramNumImageView4.setImageResource(R.drawable.gpupzoom);
      paramNumImageView5.setImageResource(R.drawable.gpupzoom);
      break;
    case 491:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprbzoom);
        paramNumImageView2.setImageResource(R.drawable.gprbzoom);
        paramNumImageView3.setImageResource(R.drawable.gprbzoom);
        paramNumImageView4.setImageResource(R.drawable.gprbzoom);
        paramNumImageView5.setImageResource(R.drawable.gprbzoom);
      }
      break;
    case 490:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gplbzoom);
        paramNumImageView2.setImageResource(R.drawable.gplbzoom);
        paramNumImageView3.setImageResource(R.drawable.gplbzoom);
        paramNumImageView4.setImageResource(R.drawable.gplbzoom);
        paramNumImageView5.setImageResource(R.drawable.gplbzoom);
      }
      break;
    case 489:
      paramNumImageView1.setImageResource(R.drawable.gpyzoom);
      paramNumImageView2.setImageResource(R.drawable.gpyzoom);
      paramNumImageView3.setImageResource(R.drawable.gpyzoom);
      paramNumImageView4.setImageResource(R.drawable.gpyzoom);
      paramNumImageView5.setImageResource(R.drawable.gpyzoom);
      break;
    case 488:
      paramNumImageView1.setImageResource(R.drawable.gpxzoom);
      paramNumImageView2.setImageResource(R.drawable.gpxzoom);
      paramNumImageView3.setImageResource(R.drawable.gpxzoom);
      paramNumImageView4.setImageResource(R.drawable.gpxzoom);
      paramNumImageView5.setImageResource(R.drawable.gpxzoom);
      break;
    case 487:
      paramNumImageView1.setImageResource(R.drawable.gpbzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbzoom);
      break;
    case 486:
      paramNumImageView1.setImageResource(R.drawable.gpazoom);
      paramNumImageView2.setImageResource(R.drawable.gpazoom);
      paramNumImageView3.setImageResource(R.drawable.gpazoom);
      paramNumImageView4.setImageResource(R.drawable.gpazoom);
      paramNumImageView5.setImageResource(R.drawable.gpazoom);
      break;
    case 481:
    case 482:
    case 483:
    case 484:
    case 485:
      paramNumImageView1.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView2.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView3.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView4.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView5.setImageResource(R.drawable.newkeyzoom);
      break;
    case 480:
      paramNumImageView1.setImageResource(R.drawable.center3zoom);
      paramNumImageView2.setImageResource(R.drawable.center3zoom);
      paramNumImageView3.setImageResource(R.drawable.center3zoom);
      paramNumImageView4.setImageResource(R.drawable.center3zoom);
      paramNumImageView5.setImageResource(R.drawable.center3zoom);
      break;
    case 479:
      paramNumImageView1.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbpr2zoom);
      break;
    case 478:
      paramNumImageView1.setImageResource(R.drawable.center2zoom);
      paramNumImageView2.setImageResource(R.drawable.center2zoom);
      paramNumImageView3.setImageResource(R.drawable.center2zoom);
      paramNumImageView4.setImageResource(R.drawable.center2zoom);
      paramNumImageView5.setImageResource(R.drawable.center2zoom);
      break;
    case 477:
      paramNumImageView1.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbpr1zoom);
      break;
    case 476:
      paramNumImageView1.setImageResource(R.drawable.center1zoom);
      paramNumImageView2.setImageResource(R.drawable.center1zoom);
      paramNumImageView3.setImageResource(R.drawable.center1zoom);
      paramNumImageView4.setImageResource(R.drawable.center1zoom);
      paramNumImageView5.setImageResource(R.drawable.center1zoom);
      break;
    case 475:
      paramNumImageView1.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm4zoom);
      break;
    case 474:
      paramNumImageView1.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm3zoom);
      break;
    case 473:
      paramNumImageView1.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm2zoom);
      break;
    case 472:
      paramNumImageView1.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm1zoom);
      break;
    case 471:
      paramNumImageView1.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbprzoom);
      break;
    case 470:
      paramNumImageView1.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbplzoom);
      break;
    case 469:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprtzoom);
        paramNumImageView2.setImageResource(R.drawable.gprtzoom);
        paramNumImageView3.setImageResource(R.drawable.gprtzoom);
        paramNumImageView4.setImageResource(R.drawable.gprtzoom);
        paramNumImageView5.setImageResource(R.drawable.gprtzoom);
      }
      break;
    case 468:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpltzoom);
        paramNumImageView2.setImageResource(R.drawable.gpltzoom);
        paramNumImageView3.setImageResource(R.drawable.gpltzoom);
        paramNumImageView4.setImageResource(R.drawable.gpltzoom);
        paramNumImageView5.setImageResource(R.drawable.gpltzoom);
      }
      break;
    case 467:
      paramNumImageView1.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpr3zoom);
      break;
    case 466:
      paramNumImageView1.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpl3zoom);
      break;
    case 465:
      paramNumImageView1.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView2.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView3.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView4.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView5.setImageResource(R.drawable.gpselectzoom);
      break;
    case 464:
      paramNumImageView1.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView2.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView3.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView4.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView5.setImageResource(R.drawable.gpstartzoom);
      break;
    case 463:
      paramNumImageView1.setImageResource(R.drawable.gprightzoom);
      paramNumImageView2.setImageResource(R.drawable.gprightzoom);
      paramNumImageView3.setImageResource(R.drawable.gprightzoom);
      paramNumImageView4.setImageResource(R.drawable.gprightzoom);
      paramNumImageView5.setImageResource(R.drawable.gprightzoom);
      break;
    case 462:
      paramNumImageView1.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView2.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView3.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView4.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView5.setImageResource(R.drawable.gpleftzoom);
      break;
    case 461:
      paramNumImageView1.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView2.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView3.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView4.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView5.setImageResource(R.drawable.gpdownzoom);
      break;
    case 460:
      paramNumImageView1.setImageResource(R.drawable.gpupzoom);
      paramNumImageView2.setImageResource(R.drawable.gpupzoom);
      paramNumImageView3.setImageResource(R.drawable.gpupzoom);
      paramNumImageView4.setImageResource(R.drawable.gpupzoom);
      paramNumImageView5.setImageResource(R.drawable.gpupzoom);
      break;
    case 459:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprbzoom);
        paramNumImageView2.setImageResource(R.drawable.gprbzoom);
        paramNumImageView3.setImageResource(R.drawable.gprbzoom);
        paramNumImageView4.setImageResource(R.drawable.gprbzoom);
        paramNumImageView5.setImageResource(R.drawable.gprbzoom);
      }
      break;
    case 458:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gplbzoom);
        paramNumImageView2.setImageResource(R.drawable.gplbzoom);
        paramNumImageView3.setImageResource(R.drawable.gplbzoom);
        paramNumImageView4.setImageResource(R.drawable.gplbzoom);
        paramNumImageView5.setImageResource(R.drawable.gplbzoom);
      }
      break;
    case 457:
      paramNumImageView1.setImageResource(R.drawable.gpyzoom);
      paramNumImageView2.setImageResource(R.drawable.gpyzoom);
      paramNumImageView3.setImageResource(R.drawable.gpyzoom);
      paramNumImageView4.setImageResource(R.drawable.gpyzoom);
      paramNumImageView5.setImageResource(R.drawable.gpyzoom);
      break;
    case 456:
      paramNumImageView1.setImageResource(R.drawable.gpxzoom);
      paramNumImageView2.setImageResource(R.drawable.gpxzoom);
      paramNumImageView3.setImageResource(R.drawable.gpxzoom);
      paramNumImageView4.setImageResource(R.drawable.gpxzoom);
      paramNumImageView5.setImageResource(R.drawable.gpxzoom);
      break;
    case 455:
      paramNumImageView1.setImageResource(R.drawable.gpbzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbzoom);
      break;
    case 454:
      paramNumImageView1.setImageResource(R.drawable.gpazoom);
      paramNumImageView2.setImageResource(R.drawable.gpazoom);
      paramNumImageView3.setImageResource(R.drawable.gpazoom);
      paramNumImageView4.setImageResource(R.drawable.gpazoom);
      paramNumImageView5.setImageResource(R.drawable.gpazoom);
      break;
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
      paramNumImageView1.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView2.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView3.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView4.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView5.setImageResource(R.drawable.newkeyzoom);
      break;
    case 443:
      paramNumImageView1.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm4zoom);
      break;
    case 442:
      paramNumImageView1.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm3zoom);
      break;
    case 441:
      paramNumImageView1.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm2zoom);
      break;
    case 440:
      paramNumImageView1.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm1zoom);
      break;
    case 439:
      paramNumImageView1.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbprzoom);
      break;
    case 438:
      paramNumImageView1.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbplzoom);
      break;
    case 437:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprtzoom);
        paramNumImageView2.setImageResource(R.drawable.gprtzoom);
        paramNumImageView3.setImageResource(R.drawable.gprtzoom);
        paramNumImageView4.setImageResource(R.drawable.gprtzoom);
        paramNumImageView5.setImageResource(R.drawable.gprtzoom);
      }
      break;
    case 436:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpltzoom);
        paramNumImageView2.setImageResource(R.drawable.gpltzoom);
        paramNumImageView3.setImageResource(R.drawable.gpltzoom);
        paramNumImageView4.setImageResource(R.drawable.gpltzoom);
        paramNumImageView5.setImageResource(R.drawable.gpltzoom);
      }
      break;
    case 435:
      paramNumImageView1.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpr3zoom);
      break;
    case 434:
      paramNumImageView1.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpl3zoom);
      break;
    case 433:
      paramNumImageView1.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView2.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView3.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView4.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView5.setImageResource(R.drawable.gpselectzoom);
      break;
    case 432:
      paramNumImageView1.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView2.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView3.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView4.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView5.setImageResource(R.drawable.gpstartzoom);
      break;
    case 431:
      paramNumImageView1.setImageResource(R.drawable.gprightzoom);
      paramNumImageView2.setImageResource(R.drawable.gprightzoom);
      paramNumImageView3.setImageResource(R.drawable.gprightzoom);
      paramNumImageView4.setImageResource(R.drawable.gprightzoom);
      paramNumImageView5.setImageResource(R.drawable.gprightzoom);
      break;
    case 430:
      paramNumImageView1.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView2.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView3.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView4.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView5.setImageResource(R.drawable.gpleftzoom);
      break;
    case 429:
      paramNumImageView1.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView2.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView3.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView4.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView5.setImageResource(R.drawable.gpdownzoom);
      break;
    case 428:
      paramNumImageView1.setImageResource(R.drawable.gpupzoom);
      paramNumImageView2.setImageResource(R.drawable.gpupzoom);
      paramNumImageView3.setImageResource(R.drawable.gpupzoom);
      paramNumImageView4.setImageResource(R.drawable.gpupzoom);
      paramNumImageView5.setImageResource(R.drawable.gpupzoom);
      break;
    case 427:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprbzoom);
        paramNumImageView2.setImageResource(R.drawable.gprbzoom);
        paramNumImageView3.setImageResource(R.drawable.gprbzoom);
        paramNumImageView4.setImageResource(R.drawable.gprbzoom);
        paramNumImageView5.setImageResource(R.drawable.gprbzoom);
      }
      break;
    case 426:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gplbzoom);
        paramNumImageView2.setImageResource(R.drawable.gplbzoom);
        paramNumImageView3.setImageResource(R.drawable.gplbzoom);
        paramNumImageView4.setImageResource(R.drawable.gplbzoom);
        paramNumImageView5.setImageResource(R.drawable.gplbzoom);
      }
      break;
    case 425:
      paramNumImageView1.setImageResource(R.drawable.gpyzoom);
      paramNumImageView2.setImageResource(R.drawable.gpyzoom);
      paramNumImageView3.setImageResource(R.drawable.gpyzoom);
      paramNumImageView4.setImageResource(R.drawable.gpyzoom);
      paramNumImageView5.setImageResource(R.drawable.gpyzoom);
      break;
    case 424:
      paramNumImageView1.setImageResource(R.drawable.gpxzoom);
      paramNumImageView2.setImageResource(R.drawable.gpxzoom);
      paramNumImageView3.setImageResource(R.drawable.gpxzoom);
      paramNumImageView4.setImageResource(R.drawable.gpxzoom);
      paramNumImageView5.setImageResource(R.drawable.gpxzoom);
      break;
    case 423:
      paramNumImageView1.setImageResource(R.drawable.gpbzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbzoom);
      break;
    case 422:
      paramNumImageView1.setImageResource(R.drawable.gpazoom);
      paramNumImageView2.setImageResource(R.drawable.gpazoom);
      paramNumImageView3.setImageResource(R.drawable.gpazoom);
      paramNumImageView4.setImageResource(R.drawable.gpazoom);
      paramNumImageView5.setImageResource(R.drawable.gpazoom);
      break;
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
      paramNumImageView1.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView2.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView3.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView4.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView5.setImageResource(R.drawable.newkeyzoom);
      break;
    case 411:
      paramNumImageView1.setImageResource(R.drawable.gpm4hp2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm4hp2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm4hp2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm4hp2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm4hp2zoom);
      break;
    case 410:
      paramNumImageView1.setImageResource(R.drawable.gpm3hp2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm3hp2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm3hp2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm3hp2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm3hp2zoom);
      break;
    case 409:
      paramNumImageView1.setImageResource(R.drawable.gpm2hp2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm2hp2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm2hp2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm2hp2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm2hp2zoom);
      break;
    case 408:
      paramNumImageView1.setImageResource(R.drawable.gpm1hp2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm1hp2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm1hp2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm1hp2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm1hp2zoom);
      break;
    case 407:
      paramNumImageView1.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbprzoom);
      break;
    case 406:
      paramNumImageView1.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbplzoom);
      break;
    case 405:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2hp2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr2hp2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr2hp2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr2hp2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr2hp2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprthp2zoom);
        paramNumImageView2.setImageResource(R.drawable.gprthp2zoom);
        paramNumImageView3.setImageResource(R.drawable.gprthp2zoom);
        paramNumImageView4.setImageResource(R.drawable.gprthp2zoom);
        paramNumImageView5.setImageResource(R.drawable.gprthp2zoom);
      }
      break;
    case 404:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2hp2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl2hp2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl2hp2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl2hp2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl2hp2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gplthp2zoom);
        paramNumImageView2.setImageResource(R.drawable.gplthp2zoom);
        paramNumImageView3.setImageResource(R.drawable.gplthp2zoom);
        paramNumImageView4.setImageResource(R.drawable.gplthp2zoom);
        paramNumImageView5.setImageResource(R.drawable.gplthp2zoom);
      }
      break;
    case 403:
      paramNumImageView1.setImageResource(R.drawable.gpr3hp2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpr3hp2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpr3hp2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpr3hp2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpr3hp2zoom);
      break;
    case 402:
      paramNumImageView1.setImageResource(R.drawable.gpl3hp2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpl3hp2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpl3hp2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpl3hp2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpl3hp2zoom);
      break;
    case 401:
      paramNumImageView1.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView2.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView3.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView4.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView5.setImageResource(R.drawable.gpselectzoom);
      break;
    case 400:
      paramNumImageView1.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView2.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView3.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView4.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView5.setImageResource(R.drawable.gpstartzoom);
      break;
    case 399:
      paramNumImageView1.setImageResource(R.drawable.gprighthp2zoom);
      paramNumImageView2.setImageResource(R.drawable.gprighthp2zoom);
      paramNumImageView3.setImageResource(R.drawable.gprighthp2zoom);
      paramNumImageView4.setImageResource(R.drawable.gprighthp2zoom);
      paramNumImageView5.setImageResource(R.drawable.gprighthp2zoom);
      break;
    case 398:
      paramNumImageView1.setImageResource(R.drawable.gplefthp2zoom);
      paramNumImageView2.setImageResource(R.drawable.gplefthp2zoom);
      paramNumImageView3.setImageResource(R.drawable.gplefthp2zoom);
      paramNumImageView4.setImageResource(R.drawable.gplefthp2zoom);
      paramNumImageView5.setImageResource(R.drawable.gplefthp2zoom);
      break;
    case 397:
      paramNumImageView1.setImageResource(R.drawable.gpdownhp2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpdownhp2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpdownhp2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpdownhp2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpdownhp2zoom);
      break;
    case 396:
      paramNumImageView1.setImageResource(R.drawable.gpuphp2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpuphp2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpuphp2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpuphp2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpuphp2zoom);
      break;
    case 395:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1hp2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr1hp2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr1hp2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr1hp2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr1hp2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprbhp2zoom);
        paramNumImageView2.setImageResource(R.drawable.gprbhp2zoom);
        paramNumImageView3.setImageResource(R.drawable.gprbhp2zoom);
        paramNumImageView4.setImageResource(R.drawable.gprbhp2zoom);
        paramNumImageView5.setImageResource(R.drawable.gprbhp2zoom);
      }
      break;
    case 394:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1hp2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl1hp2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl1hp2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl1hp2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl1hp2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gplbhp2zoom);
        paramNumImageView2.setImageResource(R.drawable.gplbhp2zoom);
        paramNumImageView3.setImageResource(R.drawable.gplbhp2zoom);
        paramNumImageView4.setImageResource(R.drawable.gplbhp2zoom);
        paramNumImageView5.setImageResource(R.drawable.gplbhp2zoom);
      }
      break;
    case 393:
      paramNumImageView1.setImageResource(R.drawable.gpyhp2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpyhp2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpyhp2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpyhp2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpyhp2zoom);
      break;
    case 392:
      paramNumImageView1.setImageResource(R.drawable.gpxhp2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpxhp2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpxhp2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpxhp2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpxhp2zoom);
      break;
    case 391:
      paramNumImageView1.setImageResource(R.drawable.gpbhp2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbhp2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbhp2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbhp2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbhp2zoom);
      break;
    case 390:
      paramNumImageView1.setImageResource(R.drawable.gpahp2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpahp2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpahp2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpahp2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpahp2zoom);
      break;
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
      paramNumImageView1.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView2.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView3.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView4.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView5.setImageResource(R.drawable.newkeyzoom);
      break;
    case 379:
      paramNumImageView1.setImageResource(R.drawable.gpm4hp1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm4hp1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm4hp1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm4hp1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm4hp1zoom);
      break;
    case 378:
      paramNumImageView1.setImageResource(R.drawable.gpm3hp1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm3hp1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm3hp1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm3hp1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm3hp1zoom);
      break;
    case 377:
      paramNumImageView1.setImageResource(R.drawable.gpm2hp1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm2hp1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm2hp1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm2hp1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm2hp1zoom);
      break;
    case 376:
      paramNumImageView1.setImageResource(R.drawable.gpm1hp1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm1hp1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm1hp1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm1hp1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm1hp1zoom);
      break;
    case 375:
      paramNumImageView1.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbprzoom);
      break;
    case 374:
      paramNumImageView1.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbplzoom);
      break;
    case 373:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2hp1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr2hp1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr2hp1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr2hp1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr2hp1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprthp1zoom);
        paramNumImageView2.setImageResource(R.drawable.gprthp1zoom);
        paramNumImageView3.setImageResource(R.drawable.gprthp1zoom);
        paramNumImageView4.setImageResource(R.drawable.gprthp1zoom);
        paramNumImageView5.setImageResource(R.drawable.gprthp1zoom);
      }
      break;
    case 372:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2hp1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl2hp1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl2hp1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl2hp1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl2hp1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gplthp1zoom);
        paramNumImageView2.setImageResource(R.drawable.gplthp1zoom);
        paramNumImageView3.setImageResource(R.drawable.gplthp1zoom);
        paramNumImageView4.setImageResource(R.drawable.gplthp1zoom);
        paramNumImageView5.setImageResource(R.drawable.gplthp1zoom);
      }
      break;
    case 371:
      paramNumImageView1.setImageResource(R.drawable.gpr3hp1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpr3hp1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpr3hp1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpr3hp1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpr3hp1zoom);
      break;
    case 370:
      paramNumImageView1.setImageResource(R.drawable.gpl3hp1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpl3hp1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpl3hp1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpl3hp1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpl3hp1zoom);
      break;
    case 369:
      paramNumImageView1.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView2.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView3.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView4.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView5.setImageResource(R.drawable.gpselectzoom);
      break;
    case 368:
      paramNumImageView1.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView2.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView3.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView4.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView5.setImageResource(R.drawable.gpstartzoom);
      break;
    case 367:
      paramNumImageView1.setImageResource(R.drawable.gprighthp1zoom);
      paramNumImageView2.setImageResource(R.drawable.gprighthp1zoom);
      paramNumImageView3.setImageResource(R.drawable.gprighthp1zoom);
      paramNumImageView4.setImageResource(R.drawable.gprighthp1zoom);
      paramNumImageView5.setImageResource(R.drawable.gprighthp1zoom);
      break;
    case 366:
      paramNumImageView1.setImageResource(R.drawable.gplefthp1zoom);
      paramNumImageView2.setImageResource(R.drawable.gplefthp1zoom);
      paramNumImageView3.setImageResource(R.drawable.gplefthp1zoom);
      paramNumImageView4.setImageResource(R.drawable.gplefthp1zoom);
      paramNumImageView5.setImageResource(R.drawable.gplefthp1zoom);
      break;
    case 365:
      paramNumImageView1.setImageResource(R.drawable.gpdownhp1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpdownhp1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpdownhp1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpdownhp1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpdownhp1zoom);
      break;
    case 364:
      paramNumImageView1.setImageResource(R.drawable.gpuphp1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpuphp1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpuphp1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpuphp1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpuphp1zoom);
      break;
    case 363:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1hp1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr1hp1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr1hp1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr1hp1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr1hp1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprbhp1zoom);
        paramNumImageView2.setImageResource(R.drawable.gprbhp1zoom);
        paramNumImageView3.setImageResource(R.drawable.gprbhp1zoom);
        paramNumImageView4.setImageResource(R.drawable.gprbhp1zoom);
        paramNumImageView5.setImageResource(R.drawable.gprbhp1zoom);
      }
      break;
    case 362:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1hp1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl1hp1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl1hp1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl1hp1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl1hp1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gplbhp1zoom);
        paramNumImageView2.setImageResource(R.drawable.gplbhp1zoom);
        paramNumImageView3.setImageResource(R.drawable.gplbhp1zoom);
        paramNumImageView4.setImageResource(R.drawable.gplbhp1zoom);
        paramNumImageView5.setImageResource(R.drawable.gplbhp1zoom);
      }
      break;
    case 361:
      paramNumImageView1.setImageResource(R.drawable.gpyhp1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpyhp1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpyhp1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpyhp1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpyhp1zoom);
      break;
    case 360:
      paramNumImageView1.setImageResource(R.drawable.gpxhp1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpxhp1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpxhp1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpxhp1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpxhp1zoom);
      break;
    case 359:
      paramNumImageView1.setImageResource(R.drawable.gpbhp1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbhp1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbhp1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbhp1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbhp1zoom);
      break;
    case 358:
      paramNumImageView1.setImageResource(R.drawable.gpahp1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpahp1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpahp1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpahp1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpahp1zoom);
      break;
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
      paramNumImageView1.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView2.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView3.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView4.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView5.setImageResource(R.drawable.newkeyzoom);
      break;
    case 347:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpm4comzoom);
        paramNumImageView2.setImageResource(R.drawable.gpm4comr1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpm4comr2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpm4coml1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpm4coml2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpm4comzoom);
        paramNumImageView2.setImageResource(R.drawable.gpm4comrbzoom);
        paramNumImageView3.setImageResource(R.drawable.gpm4comrtzoom);
        paramNumImageView4.setImageResource(R.drawable.gpm4comlbzoom);
        paramNumImageView5.setImageResource(R.drawable.gpm4comltzoom);
      }
      break;
    case 346:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpm3comzoom);
        paramNumImageView2.setImageResource(R.drawable.gpm3comr1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpm3comr2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpm3coml1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpm3coml2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpm3comzoom);
        paramNumImageView2.setImageResource(R.drawable.gpm3comrbzoom);
        paramNumImageView3.setImageResource(R.drawable.gpm3comrtzoom);
        paramNumImageView4.setImageResource(R.drawable.gpm3comlbzoom);
        paramNumImageView5.setImageResource(R.drawable.gpm3comltzoom);
      }
      break;
    case 345:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpm2comzoom);
        paramNumImageView2.setImageResource(R.drawable.gpm2comr1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpm2comr2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpm2coml1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpm2coml2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpm2comzoom);
        paramNumImageView2.setImageResource(R.drawable.gpm2comrbzoom);
        paramNumImageView3.setImageResource(R.drawable.gpm2comrtzoom);
        paramNumImageView4.setImageResource(R.drawable.gpm2comlbzoom);
        paramNumImageView5.setImageResource(R.drawable.gpm2comltzoom);
      }
      break;
    case 344:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpm1comzoom);
        paramNumImageView2.setImageResource(R.drawable.gpm1comr1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpm1comr2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpm1coml1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpm1coml2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpm1comzoom);
        paramNumImageView2.setImageResource(R.drawable.gpm1comrbzoom);
        paramNumImageView3.setImageResource(R.drawable.gpm1comrtzoom);
        paramNumImageView4.setImageResource(R.drawable.gpm1comlbzoom);
        paramNumImageView5.setImageResource(R.drawable.gpm1comltzoom);
      }
      break;
    case 343:
      paramNumImageView1.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbprzoom);
      break;
    case 342:
      paramNumImageView1.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbplzoom);
      break;
    case 341:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2comzoom);
        paramNumImageView2.setImageResource(R.drawable.gpr2comr1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr2comr2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr2coml1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr2coml2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprtcomzoom);
        paramNumImageView2.setImageResource(R.drawable.gprtcomrbzoom);
        paramNumImageView3.setImageResource(R.drawable.gprtcomrtzoom);
        paramNumImageView4.setImageResource(R.drawable.gprtcomlbzoom);
        paramNumImageView5.setImageResource(R.drawable.gprtcomltzoom);
      }
      break;
    case 340:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2comzoom);
        paramNumImageView2.setImageResource(R.drawable.gpl2comr1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl2comr2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl2coml1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl2coml2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpltcomzoom);
        paramNumImageView2.setImageResource(R.drawable.gpltcomrbzoom);
        paramNumImageView3.setImageResource(R.drawable.gpltcomrtzoom);
        paramNumImageView4.setImageResource(R.drawable.gpltcomlbzoom);
        paramNumImageView5.setImageResource(R.drawable.gpltcomltzoom);
      }
      break;
    case 339:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr3comzoom);
        paramNumImageView2.setImageResource(R.drawable.gpr3comr1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr3comr2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr3coml1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr3coml2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr3comzoom);
        paramNumImageView2.setImageResource(R.drawable.gpr3comrbzoom);
        paramNumImageView3.setImageResource(R.drawable.gpr3comrtzoom);
        paramNumImageView4.setImageResource(R.drawable.gpr3comlbzoom);
        paramNumImageView5.setImageResource(R.drawable.gpr3comltzoom);
      }
      break;
    case 338:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl3comzoom);
        paramNumImageView2.setImageResource(R.drawable.gpl3comr1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl3comr2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl3coml1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl3coml2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl3comzoom);
        paramNumImageView2.setImageResource(R.drawable.gpl3comrbzoom);
        paramNumImageView3.setImageResource(R.drawable.gpl3comrtzoom);
        paramNumImageView4.setImageResource(R.drawable.gpl3comlbzoom);
        paramNumImageView5.setImageResource(R.drawable.gpl3comltzoom);
      }
      break;
    case 337:
      paramNumImageView1.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView2.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView3.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView4.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView5.setImageResource(R.drawable.gpselectzoom);
      break;
    case 336:
      paramNumImageView1.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView2.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView3.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView4.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView5.setImageResource(R.drawable.gpstartzoom);
      break;
    case 335:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprightcomzoom);
        paramNumImageView2.setImageResource(R.drawable.gprightcomr1zoom);
        paramNumImageView3.setImageResource(R.drawable.gprightcomr2zoom);
        paramNumImageView4.setImageResource(R.drawable.gprightcoml1zoom);
        paramNumImageView5.setImageResource(R.drawable.gprightcoml2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprightcomzoom);
        paramNumImageView2.setImageResource(R.drawable.gprightcomrbzoom);
        paramNumImageView3.setImageResource(R.drawable.gprightcomrtzoom);
        paramNumImageView4.setImageResource(R.drawable.gprightcomlbzoom);
        paramNumImageView5.setImageResource(R.drawable.gprightcomltzoom);
      }
      break;
    case 334:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpleftcomzoom);
        paramNumImageView2.setImageResource(R.drawable.gpleftcomr1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpleftcomr2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpleftcoml1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpleftcoml2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpleftcomzoom);
        paramNumImageView2.setImageResource(R.drawable.gpleftcomrbzoom);
        paramNumImageView3.setImageResource(R.drawable.gpleftcomrtzoom);
        paramNumImageView4.setImageResource(R.drawable.gpleftcomlbzoom);
        paramNumImageView5.setImageResource(R.drawable.gpleftcomltzoom);
      }
      break;
    case 333:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpdowncomzoom);
        paramNumImageView2.setImageResource(R.drawable.gpdowncomr1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpdowncomr2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpdowncoml1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpdowncoml2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpdowncomzoom);
        paramNumImageView2.setImageResource(R.drawable.gpdowncomrbzoom);
        paramNumImageView3.setImageResource(R.drawable.gpdowncomrtzoom);
        paramNumImageView4.setImageResource(R.drawable.gpdowncomlbzoom);
        paramNumImageView5.setImageResource(R.drawable.gpdowncomltzoom);
      }
      break;
    case 332:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpupcomzoom);
        paramNumImageView2.setImageResource(R.drawable.gpupcomr1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpupcomr2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpupcoml1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpupcoml2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpupcomzoom);
        paramNumImageView2.setImageResource(R.drawable.gpupcomrbzoom);
        paramNumImageView3.setImageResource(R.drawable.gpupcomrtzoom);
        paramNumImageView4.setImageResource(R.drawable.gpupcomlbzoom);
        paramNumImageView5.setImageResource(R.drawable.gpupcomltzoom);
      }
      break;
    case 331:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1comzoom);
        paramNumImageView2.setImageResource(R.drawable.gpr1comr1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr1comr2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr1coml1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr1coml2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprbcomzoom);
        paramNumImageView2.setImageResource(R.drawable.gprbcomrbzoom);
        paramNumImageView3.setImageResource(R.drawable.gprbcomrtzoom);
        paramNumImageView4.setImageResource(R.drawable.gprbcomlbzoom);
        paramNumImageView5.setImageResource(R.drawable.gprbcomltzoom);
      }
      break;
    case 330:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1comzoom);
        paramNumImageView2.setImageResource(R.drawable.gpl1comr1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl1comr2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl1coml1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl1coml2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gplbcomzoom);
        paramNumImageView2.setImageResource(R.drawable.gplbcomrbzoom);
        paramNumImageView3.setImageResource(R.drawable.gplbcomrtzoom);
        paramNumImageView4.setImageResource(R.drawable.gplbcomlbzoom);
        paramNumImageView5.setImageResource(R.drawable.gplbcomltzoom);
      }
      break;
    case 329:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpycomzoom);
        paramNumImageView2.setImageResource(R.drawable.gpycomr1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpycomr2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpycoml1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpycoml2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpycomzoom);
        paramNumImageView2.setImageResource(R.drawable.gpycomrbzoom);
        paramNumImageView3.setImageResource(R.drawable.gpycomrtzoom);
        paramNumImageView4.setImageResource(R.drawable.gpycomlbzoom);
        paramNumImageView5.setImageResource(R.drawable.gpycomltzoom);
      }
      break;
    case 328:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpxcomzoom);
        paramNumImageView2.setImageResource(R.drawable.gpxcomr1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpxcomr2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpxcoml1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpxcoml2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpxcomzoom);
        paramNumImageView2.setImageResource(R.drawable.gpxcomrbzoom);
        paramNumImageView3.setImageResource(R.drawable.gpxcomrtzoom);
        paramNumImageView4.setImageResource(R.drawable.gpxcomlbzoom);
        paramNumImageView5.setImageResource(R.drawable.gpxcomltzoom);
      }
      break;
    case 327:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpbcomzoom);
        paramNumImageView2.setImageResource(R.drawable.gpbcomr1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpbcomr2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpbcoml1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpbcoml2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpbcomzoom);
        paramNumImageView2.setImageResource(R.drawable.gpbcomrbzoom);
        paramNumImageView3.setImageResource(R.drawable.gpbcomrtzoom);
        paramNumImageView4.setImageResource(R.drawable.gpbcomlbzoom);
        paramNumImageView5.setImageResource(R.drawable.gpbcomltzoom);
      }
      break;
    case 326:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpacomzoom);
        paramNumImageView2.setImageResource(R.drawable.gpacomr1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpacomr2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpacoml1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpacoml2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpacomzoom);
        paramNumImageView2.setImageResource(R.drawable.gpacomrbzoom);
        paramNumImageView3.setImageResource(R.drawable.gpacomrtzoom);
        paramNumImageView4.setImageResource(R.drawable.gpacomlbzoom);
        paramNumImageView5.setImageResource(R.drawable.gpacomltzoom);
      }
      break;
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
      paramNumImageView1.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView2.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView3.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView4.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView5.setImageResource(R.drawable.newkeyzoom);
      break;
    case 315:
      paramNumImageView1.setImageResource(R.drawable.gpm4firstzoom);
      paramNumImageView2.setImageResource(R.drawable.gpm4firstzoom);
      paramNumImageView3.setImageResource(R.drawable.gpm4firstzoom);
      paramNumImageView4.setImageResource(R.drawable.gpm4firstzoom);
      paramNumImageView5.setImageResource(R.drawable.gpm4firstzoom);
      break;
    case 314:
      paramNumImageView1.setImageResource(R.drawable.gpm3firstzoom);
      paramNumImageView2.setImageResource(R.drawable.gpm3firstzoom);
      paramNumImageView3.setImageResource(R.drawable.gpm3firstzoom);
      paramNumImageView4.setImageResource(R.drawable.gpm3firstzoom);
      paramNumImageView5.setImageResource(R.drawable.gpm3firstzoom);
      break;
    case 313:
      paramNumImageView1.setImageResource(R.drawable.gpm2firstzoom);
      paramNumImageView2.setImageResource(R.drawable.gpm2firstzoom);
      paramNumImageView3.setImageResource(R.drawable.gpm2firstzoom);
      paramNumImageView4.setImageResource(R.drawable.gpm2firstzoom);
      paramNumImageView5.setImageResource(R.drawable.gpm2firstzoom);
      break;
    case 312:
      paramNumImageView1.setImageResource(R.drawable.gpm1firstzoom);
      paramNumImageView2.setImageResource(R.drawable.gpm1firstzoom);
      paramNumImageView3.setImageResource(R.drawable.gpm1firstzoom);
      paramNumImageView4.setImageResource(R.drawable.gpm1firstzoom);
      paramNumImageView5.setImageResource(R.drawable.gpm1firstzoom);
      break;
    case 311:
      paramNumImageView1.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView2.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView3.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView4.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView5.setImageResource(R.drawable.gplbfirstzoom);
      break;
    case 310:
      paramNumImageView1.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView2.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView3.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView4.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView5.setImageResource(R.drawable.gplbfirstzoom);
      break;
    case 309:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2firstzoom);
        paramNumImageView2.setImageResource(R.drawable.gpr2firstzoom);
        paramNumImageView3.setImageResource(R.drawable.gpr2firstzoom);
        paramNumImageView4.setImageResource(R.drawable.gpr2firstzoom);
        paramNumImageView5.setImageResource(R.drawable.gpr2firstzoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprtfirstzoom);
        paramNumImageView2.setImageResource(R.drawable.gprtfirstzoom);
        paramNumImageView3.setImageResource(R.drawable.gprtfirstzoom);
        paramNumImageView4.setImageResource(R.drawable.gprtfirstzoom);
        paramNumImageView5.setImageResource(R.drawable.gprtfirstzoom);
      }
      break;
    case 308:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2firstzoom);
        paramNumImageView2.setImageResource(R.drawable.gpl2firstzoom);
        paramNumImageView3.setImageResource(R.drawable.gpl2firstzoom);
        paramNumImageView4.setImageResource(R.drawable.gpl2firstzoom);
        paramNumImageView5.setImageResource(R.drawable.gpl2firstzoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpltfirstzoom);
        paramNumImageView2.setImageResource(R.drawable.gpltfirstzoom);
        paramNumImageView3.setImageResource(R.drawable.gpltfirstzoom);
        paramNumImageView4.setImageResource(R.drawable.gpltfirstzoom);
        paramNumImageView5.setImageResource(R.drawable.gpltfirstzoom);
      }
      break;
    case 307:
      paramNumImageView1.setImageResource(R.drawable.gpr3firstzoom);
      paramNumImageView2.setImageResource(R.drawable.gpr3firstzoom);
      paramNumImageView3.setImageResource(R.drawable.gpr3firstzoom);
      paramNumImageView4.setImageResource(R.drawable.gpr3firstzoom);
      paramNumImageView5.setImageResource(R.drawable.gpr3firstzoom);
      break;
    case 306:
      paramNumImageView1.setImageResource(R.drawable.gpl3firstzoom);
      paramNumImageView2.setImageResource(R.drawable.gpl3firstzoom);
      paramNumImageView3.setImageResource(R.drawable.gpl3firstzoom);
      paramNumImageView4.setImageResource(R.drawable.gpl3firstzoom);
      paramNumImageView5.setImageResource(R.drawable.gpl3firstzoom);
      break;
    case 305:
      paramNumImageView1.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView2.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView3.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView4.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView5.setImageResource(R.drawable.gplbfirstzoom);
      break;
    case 304:
      paramNumImageView1.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView2.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView3.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView4.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView5.setImageResource(R.drawable.gplbfirstzoom);
      break;
    case 303:
      paramNumImageView1.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView2.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView3.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView4.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView5.setImageResource(R.drawable.gplbfirstzoom);
      break;
    case 302:
      paramNumImageView1.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView2.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView3.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView4.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView5.setImageResource(R.drawable.gplbfirstzoom);
      break;
    case 301:
      paramNumImageView1.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView2.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView3.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView4.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView5.setImageResource(R.drawable.gplbfirstzoom);
      break;
    case 300:
      paramNumImageView1.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView2.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView3.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView4.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView5.setImageResource(R.drawable.gplbfirstzoom);
      break;
    case 299:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1firstzoom);
        paramNumImageView2.setImageResource(R.drawable.gpr1firstzoom);
        paramNumImageView3.setImageResource(R.drawable.gpr1firstzoom);
        paramNumImageView4.setImageResource(R.drawable.gpr1firstzoom);
        paramNumImageView5.setImageResource(R.drawable.gpr1firstzoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprbfirstzoom);
        paramNumImageView2.setImageResource(R.drawable.gprbfirstzoom);
        paramNumImageView3.setImageResource(R.drawable.gprbfirstzoom);
        paramNumImageView4.setImageResource(R.drawable.gprbfirstzoom);
        paramNumImageView5.setImageResource(R.drawable.gprbfirstzoom);
      }
      break;
    case 298:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1firstzoom);
        paramNumImageView2.setImageResource(R.drawable.gpl1firstzoom);
        paramNumImageView3.setImageResource(R.drawable.gpl1firstzoom);
        paramNumImageView4.setImageResource(R.drawable.gpl1firstzoom);
        paramNumImageView5.setImageResource(R.drawable.gpl1firstzoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gplbfirstzoom);
        paramNumImageView2.setImageResource(R.drawable.gplbfirstzoom);
        paramNumImageView3.setImageResource(R.drawable.gplbfirstzoom);
        paramNumImageView4.setImageResource(R.drawable.gplbfirstzoom);
        paramNumImageView5.setImageResource(R.drawable.gplbfirstzoom);
      }
      break;
    case 297:
      paramNumImageView1.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView2.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView3.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView4.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView5.setImageResource(R.drawable.gplbfirstzoom);
      break;
    case 296:
      paramNumImageView1.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView2.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView3.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView4.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView5.setImageResource(R.drawable.gplbfirstzoom);
      break;
    case 295:
      paramNumImageView1.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView2.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView3.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView4.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView5.setImageResource(R.drawable.gplbfirstzoom);
      break;
    case 294:
      paramNumImageView1.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView2.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView3.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView4.setImageResource(R.drawable.gplbfirstzoom);
      paramNumImageView5.setImageResource(R.drawable.gplbfirstzoom);
      break;
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
      paramNumImageView1.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView2.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView3.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView4.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView5.setImageResource(R.drawable.newkeyzoom);
      break;
    case 283:
      paramNumImageView1.setImageResource(R.drawable.gpm4rviewzoom);
      paramNumImageView2.setImageResource(R.drawable.gpm4rviewzoom);
      paramNumImageView3.setImageResource(R.drawable.gpm4rviewzoom);
      paramNumImageView4.setImageResource(R.drawable.gpm4rviewzoom);
      paramNumImageView5.setImageResource(R.drawable.gpm4rviewzoom);
      break;
    case 282:
      paramNumImageView1.setImageResource(R.drawable.gpm3rviewzoom);
      paramNumImageView2.setImageResource(R.drawable.gpm3rviewzoom);
      paramNumImageView3.setImageResource(R.drawable.gpm3rviewzoom);
      paramNumImageView4.setImageResource(R.drawable.gpm3rviewzoom);
      paramNumImageView5.setImageResource(R.drawable.gpm3rviewzoom);
      break;
    case 281:
      paramNumImageView1.setImageResource(R.drawable.gpm2rviewzoom);
      paramNumImageView2.setImageResource(R.drawable.gpm2rviewzoom);
      paramNumImageView3.setImageResource(R.drawable.gpm2rviewzoom);
      paramNumImageView4.setImageResource(R.drawable.gpm2rviewzoom);
      paramNumImageView5.setImageResource(R.drawable.gpm2rviewzoom);
      break;
    case 280:
      paramNumImageView1.setImageResource(R.drawable.gpm1rviewzoom);
      paramNumImageView2.setImageResource(R.drawable.gpm1rviewzoom);
      paramNumImageView3.setImageResource(R.drawable.gpm1rviewzoom);
      paramNumImageView4.setImageResource(R.drawable.gpm1rviewzoom);
      paramNumImageView5.setImageResource(R.drawable.gpm1rviewzoom);
      break;
    case 279:
      paramNumImageView1.setImageResource(R.drawable.gparviewzoom);
      paramNumImageView2.setImageResource(R.drawable.gparviewzoom);
      paramNumImageView3.setImageResource(R.drawable.gparviewzoom);
      paramNumImageView4.setImageResource(R.drawable.gparviewzoom);
      paramNumImageView5.setImageResource(R.drawable.gparviewzoom);
      break;
    case 278:
      paramNumImageView1.setImageResource(R.drawable.gparviewzoom);
      paramNumImageView2.setImageResource(R.drawable.gparviewzoom);
      paramNumImageView3.setImageResource(R.drawable.gparviewzoom);
      paramNumImageView4.setImageResource(R.drawable.gparviewzoom);
      paramNumImageView5.setImageResource(R.drawable.gparviewzoom);
      break;
    case 277:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2rviewzoom);
        paramNumImageView2.setImageResource(R.drawable.gpr2rviewzoom);
        paramNumImageView3.setImageResource(R.drawable.gpr2rviewzoom);
        paramNumImageView4.setImageResource(R.drawable.gpr2rviewzoom);
        paramNumImageView5.setImageResource(R.drawable.gpr2rviewzoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprtviewzoom);
        paramNumImageView2.setImageResource(R.drawable.gprtviewzoom);
        paramNumImageView3.setImageResource(R.drawable.gprtviewzoom);
        paramNumImageView4.setImageResource(R.drawable.gprtviewzoom);
        paramNumImageView5.setImageResource(R.drawable.gprtviewzoom);
      }
      break;
    case 276:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2rviewzoom);
        paramNumImageView2.setImageResource(R.drawable.gpl2rviewzoom);
        paramNumImageView3.setImageResource(R.drawable.gpl2rviewzoom);
        paramNumImageView4.setImageResource(R.drawable.gpl2rviewzoom);
        paramNumImageView5.setImageResource(R.drawable.gpl2rviewzoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpltviewzoom);
        paramNumImageView2.setImageResource(R.drawable.gpltviewzoom);
        paramNumImageView3.setImageResource(R.drawable.gpltviewzoom);
        paramNumImageView4.setImageResource(R.drawable.gpltviewzoom);
        paramNumImageView5.setImageResource(R.drawable.gpltviewzoom);
      }
      break;
    case 275:
      paramNumImageView1.setImageResource(R.drawable.gpr3rviewzoom);
      paramNumImageView2.setImageResource(R.drawable.gpr3rviewzoom);
      paramNumImageView3.setImageResource(R.drawable.gpr3rviewzoom);
      paramNumImageView4.setImageResource(R.drawable.gpr3rviewzoom);
      paramNumImageView5.setImageResource(R.drawable.gpr3rviewzoom);
      break;
    case 274:
      paramNumImageView1.setImageResource(R.drawable.gpl3rviewzoom);
      paramNumImageView2.setImageResource(R.drawable.gpl3rviewzoom);
      paramNumImageView3.setImageResource(R.drawable.gpl3rviewzoom);
      paramNumImageView4.setImageResource(R.drawable.gpl3rviewzoom);
      paramNumImageView5.setImageResource(R.drawable.gpl3rviewzoom);
      break;
    case 273:
      paramNumImageView1.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView2.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView3.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView4.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView5.setImageResource(R.drawable.gpselectzoom);
      break;
    case 272:
      paramNumImageView1.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView2.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView3.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView4.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView5.setImageResource(R.drawable.gpstartzoom);
      break;
    case 271:
      paramNumImageView1.setImageResource(R.drawable.gparviewzoom);
      paramNumImageView2.setImageResource(R.drawable.gparviewzoom);
      paramNumImageView3.setImageResource(R.drawable.gparviewzoom);
      paramNumImageView4.setImageResource(R.drawable.gparviewzoom);
      paramNumImageView5.setImageResource(R.drawable.gparviewzoom);
      break;
    case 270:
      paramNumImageView1.setImageResource(R.drawable.gparviewzoom);
      paramNumImageView2.setImageResource(R.drawable.gparviewzoom);
      paramNumImageView3.setImageResource(R.drawable.gparviewzoom);
      paramNumImageView4.setImageResource(R.drawable.gparviewzoom);
      paramNumImageView5.setImageResource(R.drawable.gparviewzoom);
      break;
    case 269:
      paramNumImageView1.setImageResource(R.drawable.gparviewzoom);
      paramNumImageView2.setImageResource(R.drawable.gparviewzoom);
      paramNumImageView3.setImageResource(R.drawable.gparviewzoom);
      paramNumImageView4.setImageResource(R.drawable.gparviewzoom);
      paramNumImageView5.setImageResource(R.drawable.gparviewzoom);
      break;
    case 268:
      paramNumImageView1.setImageResource(R.drawable.gparviewzoom);
      paramNumImageView2.setImageResource(R.drawable.gparviewzoom);
      paramNumImageView3.setImageResource(R.drawable.gparviewzoom);
      paramNumImageView4.setImageResource(R.drawable.gparviewzoom);
      paramNumImageView5.setImageResource(R.drawable.gparviewzoom);
      break;
    case 267:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1rviewzoom);
        paramNumImageView2.setImageResource(R.drawable.gpr1rviewzoom);
        paramNumImageView3.setImageResource(R.drawable.gpr1rviewzoom);
        paramNumImageView4.setImageResource(R.drawable.gpr1rviewzoom);
        paramNumImageView5.setImageResource(R.drawable.gpr1rviewzoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprbviewzoom);
        paramNumImageView2.setImageResource(R.drawable.gprbviewzoom);
        paramNumImageView3.setImageResource(R.drawable.gprbviewzoom);
        paramNumImageView4.setImageResource(R.drawable.gprbviewzoom);
        paramNumImageView5.setImageResource(R.drawable.gprbviewzoom);
      }
      break;
    case 266:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1rviewzoom);
        paramNumImageView2.setImageResource(R.drawable.gpl1rviewzoom);
        paramNumImageView3.setImageResource(R.drawable.gpl1rviewzoom);
        paramNumImageView4.setImageResource(R.drawable.gpl1rviewzoom);
        paramNumImageView5.setImageResource(R.drawable.gpl1rviewzoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gplbviewzoom);
        paramNumImageView2.setImageResource(R.drawable.gplbviewzoom);
        paramNumImageView3.setImageResource(R.drawable.gplbviewzoom);
        paramNumImageView4.setImageResource(R.drawable.gplbviewzoom);
        paramNumImageView5.setImageResource(R.drawable.gplbviewzoom);
      }
      break;
    case 265:
      paramNumImageView1.setImageResource(R.drawable.gpyrviewzoom);
      paramNumImageView2.setImageResource(R.drawable.gpyrviewzoom);
      paramNumImageView3.setImageResource(R.drawable.gpyrviewzoom);
      paramNumImageView4.setImageResource(R.drawable.gpyrviewzoom);
      paramNumImageView5.setImageResource(R.drawable.gpyrviewzoom);
      break;
    case 264:
      paramNumImageView1.setImageResource(R.drawable.gpxrviewzoom);
      paramNumImageView2.setImageResource(R.drawable.gpxrviewzoom);
      paramNumImageView3.setImageResource(R.drawable.gpxrviewzoom);
      paramNumImageView4.setImageResource(R.drawable.gpxrviewzoom);
      paramNumImageView5.setImageResource(R.drawable.gpxrviewzoom);
      break;
    case 263:
      paramNumImageView1.setImageResource(R.drawable.gpbrviewzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbrviewzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbrviewzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbrviewzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbrviewzoom);
      break;
    case 262:
      paramNumImageView1.setImageResource(R.drawable.gparviewzoom);
      paramNumImageView2.setImageResource(R.drawable.gparviewzoom);
      paramNumImageView3.setImageResource(R.drawable.gparviewzoom);
      paramNumImageView4.setImageResource(R.drawable.gparviewzoom);
      paramNumImageView5.setImageResource(R.drawable.gparviewzoom);
      break;
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
      paramNumImageView1.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView2.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView3.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView4.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView5.setImageResource(R.drawable.newkeyzoom);
      break;
    case 251:
      paramNumImageView1.setImageResource(R.drawable.gpm4y2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm4y2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm4y2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm4y2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm4y2zoom);
      break;
    case 250:
      paramNumImageView1.setImageResource(R.drawable.gpm3y2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm3y2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm3y2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm3y2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm3y2zoom);
      break;
    case 249:
      paramNumImageView1.setImageResource(R.drawable.gpm2y2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm2y2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm2y2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm2y2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm2y2zoom);
      break;
    case 248:
      paramNumImageView1.setImageResource(R.drawable.gpm1y2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm1y2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm1y2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm1y2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm1y2zoom);
      break;
    case 247:
      paramNumImageView1.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbprzoom);
      break;
    case 246:
      paramNumImageView1.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbplzoom);
      break;
    case 245:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2y2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr2y2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr2y2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr2y2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr2y2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprty2zoom);
        paramNumImageView2.setImageResource(R.drawable.gprty2zoom);
        paramNumImageView3.setImageResource(R.drawable.gprty2zoom);
        paramNumImageView4.setImageResource(R.drawable.gprty2zoom);
        paramNumImageView5.setImageResource(R.drawable.gprty2zoom);
      }
      break;
    case 244:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2y2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl2y2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl2y2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl2y2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl2y2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gplty2zoom);
        paramNumImageView2.setImageResource(R.drawable.gplty2zoom);
        paramNumImageView3.setImageResource(R.drawable.gplty2zoom);
        paramNumImageView4.setImageResource(R.drawable.gplty2zoom);
        paramNumImageView5.setImageResource(R.drawable.gplty2zoom);
      }
      break;
    case 243:
      paramNumImageView1.setImageResource(R.drawable.gpr3y2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpr3y2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpr3y2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpr3y2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpr3y2zoom);
      break;
    case 242:
      paramNumImageView1.setImageResource(R.drawable.gpl3y2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpl3y2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpl3y2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpl3y2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpl3y2zoom);
      break;
    case 241:
      paramNumImageView1.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView2.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView3.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView4.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView5.setImageResource(R.drawable.gpselectzoom);
      break;
    case 240:
      paramNumImageView1.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView2.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView3.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView4.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView5.setImageResource(R.drawable.gpstartzoom);
      break;
    case 239:
      paramNumImageView1.setImageResource(R.drawable.gprighty2zoom);
      paramNumImageView2.setImageResource(R.drawable.gprighty2zoom);
      paramNumImageView3.setImageResource(R.drawable.gprighty2zoom);
      paramNumImageView4.setImageResource(R.drawable.gprighty2zoom);
      paramNumImageView5.setImageResource(R.drawable.gprighty2zoom);
      break;
    case 238:
      paramNumImageView1.setImageResource(R.drawable.gplefty2zoom);
      paramNumImageView2.setImageResource(R.drawable.gplefty2zoom);
      paramNumImageView3.setImageResource(R.drawable.gplefty2zoom);
      paramNumImageView4.setImageResource(R.drawable.gplefty2zoom);
      paramNumImageView5.setImageResource(R.drawable.gplefty2zoom);
      break;
    case 237:
      paramNumImageView1.setImageResource(R.drawable.gpdowny2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpdowny2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpdowny2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpdowny2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpdowny2zoom);
      break;
    case 236:
      paramNumImageView1.setImageResource(R.drawable.gpupy2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpupy2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpupy2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpupy2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpupy2zoom);
      break;
    case 235:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1y2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr1y2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr1y2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr1y2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr1y2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprby2zoom);
        paramNumImageView2.setImageResource(R.drawable.gprby2zoom);
        paramNumImageView3.setImageResource(R.drawable.gprby2zoom);
        paramNumImageView4.setImageResource(R.drawable.gprby2zoom);
        paramNumImageView5.setImageResource(R.drawable.gprby2zoom);
      }
      break;
    case 234:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1y2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl1y2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl1y2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl1y2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl1y2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gplby2zoom);
        paramNumImageView2.setImageResource(R.drawable.gplby2zoom);
        paramNumImageView3.setImageResource(R.drawable.gplby2zoom);
        paramNumImageView4.setImageResource(R.drawable.gplby2zoom);
        paramNumImageView5.setImageResource(R.drawable.gplby2zoom);
      }
      break;
    case 233:
      paramNumImageView1.setImageResource(R.drawable.gpyy2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpyy2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpyy2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpyy2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpyy2zoom);
      break;
    case 232:
      paramNumImageView1.setImageResource(R.drawable.gpxy2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpxy2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpxy2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpxy2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpxy2zoom);
      break;
    case 231:
      paramNumImageView1.setImageResource(R.drawable.gpby2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpby2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpby2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpby2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpby2zoom);
      break;
    case 230:
      paramNumImageView1.setImageResource(R.drawable.gpay2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpay2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpay2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpay2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpay2zoom);
      break;
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
      paramNumImageView1.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView2.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView3.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView4.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView5.setImageResource(R.drawable.newkeyzoom);
      break;
    case 219:
      paramNumImageView1.setImageResource(R.drawable.gpm4y1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm4y1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm4y1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm4y1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm4y1zoom);
      break;
    case 218:
      paramNumImageView1.setImageResource(R.drawable.gpm3y1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm3y1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm3y1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm3y1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm3y1zoom);
      break;
    case 217:
      paramNumImageView1.setImageResource(R.drawable.gpm2y1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm2y1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm2y1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm2y1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm2y1zoom);
      break;
    case 216:
      paramNumImageView1.setImageResource(R.drawable.gpm1y1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm1y1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm1y1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm1y1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm1y1zoom);
      break;
    case 215:
      paramNumImageView1.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbprzoom);
      break;
    case 214:
      paramNumImageView1.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbplzoom);
      break;
    case 213:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2y1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr2y1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr2y1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr2y1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr2y1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprty1zoom);
        paramNumImageView2.setImageResource(R.drawable.gprty1zoom);
        paramNumImageView3.setImageResource(R.drawable.gprty1zoom);
        paramNumImageView4.setImageResource(R.drawable.gprty1zoom);
        paramNumImageView5.setImageResource(R.drawable.gprty1zoom);
      }
      break;
    case 212:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2y1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl2y1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl2y1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl2y1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl2y1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gplty1zoom);
        paramNumImageView2.setImageResource(R.drawable.gplty1zoom);
        paramNumImageView3.setImageResource(R.drawable.gplty1zoom);
        paramNumImageView4.setImageResource(R.drawable.gplty1zoom);
        paramNumImageView5.setImageResource(R.drawable.gplty1zoom);
      }
      break;
    case 211:
      paramNumImageView1.setImageResource(R.drawable.gpr3y1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpr3y1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpr3y1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpr3y1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpr3y1zoom);
      break;
    case 210:
      paramNumImageView1.setImageResource(R.drawable.gpl3y1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpl3y1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpl3y1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpl3y1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpl3y1zoom);
      break;
    case 209:
      paramNumImageView1.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView2.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView3.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView4.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView5.setImageResource(R.drawable.gpselectzoom);
      break;
    case 208:
      paramNumImageView1.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView2.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView3.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView4.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView5.setImageResource(R.drawable.gpstartzoom);
      break;
    case 207:
      paramNumImageView1.setImageResource(R.drawable.gprighty1zoom);
      paramNumImageView2.setImageResource(R.drawable.gprighty1zoom);
      paramNumImageView3.setImageResource(R.drawable.gprighty1zoom);
      paramNumImageView4.setImageResource(R.drawable.gprighty1zoom);
      paramNumImageView5.setImageResource(R.drawable.gprighty1zoom);
      break;
    case 206:
      paramNumImageView1.setImageResource(R.drawable.gplefty1zoom);
      paramNumImageView2.setImageResource(R.drawable.gplefty1zoom);
      paramNumImageView3.setImageResource(R.drawable.gplefty1zoom);
      paramNumImageView4.setImageResource(R.drawable.gplefty1zoom);
      paramNumImageView5.setImageResource(R.drawable.gplefty1zoom);
      break;
    case 205:
      paramNumImageView1.setImageResource(R.drawable.gpdowny1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpdowny1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpdowny1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpdowny1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpdowny1zoom);
      break;
    case 204:
      paramNumImageView1.setImageResource(R.drawable.gpupy1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpupy1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpupy1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpupy1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpupy1zoom);
      break;
    case 203:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1y1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr1y1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr1y1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr1y1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr1y1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprby1zoom);
        paramNumImageView2.setImageResource(R.drawable.gprby1zoom);
        paramNumImageView3.setImageResource(R.drawable.gprby1zoom);
        paramNumImageView4.setImageResource(R.drawable.gprby1zoom);
        paramNumImageView5.setImageResource(R.drawable.gprby1zoom);
      }
      break;
    case 202:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1y1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl1y1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl1y1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl1y1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl1y1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gplby1zoom);
        paramNumImageView2.setImageResource(R.drawable.gplby1zoom);
        paramNumImageView3.setImageResource(R.drawable.gplby1zoom);
        paramNumImageView4.setImageResource(R.drawable.gplby1zoom);
        paramNumImageView5.setImageResource(R.drawable.gplby1zoom);
      }
      break;
    case 201:
      paramNumImageView1.setImageResource(R.drawable.gpyy1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpyy1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpyy1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpyy1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpyy1zoom);
      break;
    case 200:
      paramNumImageView1.setImageResource(R.drawable.gpxy1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpxy1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpxy1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpxy1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpxy1zoom);
      break;
    case 199:
      paramNumImageView1.setImageResource(R.drawable.gpby1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpby1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpby1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpby1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpby1zoom);
      break;
    case 198:
      paramNumImageView1.setImageResource(R.drawable.gpay1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpay1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpay1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpay1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpay1zoom);
      break;
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
      paramNumImageView1.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView2.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView3.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView4.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView5.setImageResource(R.drawable.newkeyzoom);
      break;
    case 187:
      paramNumImageView1.setImageResource(R.drawable.gpm42zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm42zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm42zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm42zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm42zoom);
      break;
    case 186:
      paramNumImageView1.setImageResource(R.drawable.gpm32zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm32zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm32zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm32zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm32zoom);
      break;
    case 185:
      paramNumImageView1.setImageResource(R.drawable.gpm22zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm22zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm22zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm22zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm22zoom);
      break;
    case 184:
      paramNumImageView1.setImageResource(R.drawable.gpm12zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm12zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm12zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm12zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm12zoom);
      break;
    case 183:
      paramNumImageView1.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbprzoom);
      break;
    case 182:
      paramNumImageView1.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbplzoom);
      break;
    case 181:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr22zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr22zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr22zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr22zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr22zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprt2zoom);
        paramNumImageView2.setImageResource(R.drawable.gprt2zoom);
        paramNumImageView3.setImageResource(R.drawable.gprt2zoom);
        paramNumImageView4.setImageResource(R.drawable.gprt2zoom);
        paramNumImageView5.setImageResource(R.drawable.gprt2zoom);
      }
      break;
    case 180:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl22zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl22zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl22zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl22zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl22zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gplt2zoom);
        paramNumImageView2.setImageResource(R.drawable.gplt2zoom);
        paramNumImageView3.setImageResource(R.drawable.gplt2zoom);
        paramNumImageView4.setImageResource(R.drawable.gplt2zoom);
        paramNumImageView5.setImageResource(R.drawable.gplt2zoom);
      }
      break;
    case 179:
      paramNumImageView1.setImageResource(R.drawable.gpr32zoom);
      paramNumImageView2.setImageResource(R.drawable.gpr32zoom);
      paramNumImageView3.setImageResource(R.drawable.gpr32zoom);
      paramNumImageView4.setImageResource(R.drawable.gpr32zoom);
      paramNumImageView5.setImageResource(R.drawable.gpr32zoom);
      break;
    case 178:
      paramNumImageView1.setImageResource(R.drawable.gpl32zoom);
      paramNumImageView2.setImageResource(R.drawable.gpl32zoom);
      paramNumImageView3.setImageResource(R.drawable.gpl32zoom);
      paramNumImageView4.setImageResource(R.drawable.gpl32zoom);
      paramNumImageView5.setImageResource(R.drawable.gpl32zoom);
      break;
    case 177:
      paramNumImageView1.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView2.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView3.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView4.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView5.setImageResource(R.drawable.gpselectzoom);
      break;
    case 176:
      paramNumImageView1.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView2.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView3.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView4.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView5.setImageResource(R.drawable.gpstartzoom);
      break;
    case 175:
      paramNumImageView1.setImageResource(R.drawable.gpright2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpright2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpright2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpright2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpright2zoom);
      break;
    case 174:
      paramNumImageView1.setImageResource(R.drawable.gpleft2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpleft2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpleft2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpleft2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpleft2zoom);
      break;
    case 173:
      paramNumImageView1.setImageResource(R.drawable.gpdown2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpdown2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpdown2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpdown2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpdown2zoom);
      break;
    case 172:
      paramNumImageView1.setImageResource(R.drawable.gpup2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpup2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpup2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpup2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpup2zoom);
      break;
    case 171:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr12zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr12zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr12zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr12zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr12zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprb2zoom);
        paramNumImageView2.setImageResource(R.drawable.gprb2zoom);
        paramNumImageView3.setImageResource(R.drawable.gprb2zoom);
        paramNumImageView4.setImageResource(R.drawable.gprb2zoom);
        paramNumImageView5.setImageResource(R.drawable.gprb2zoom);
      }
      break;
    case 170:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl12zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl12zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl12zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl12zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl12zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gplb2zoom);
        paramNumImageView2.setImageResource(R.drawable.gplb2zoom);
        paramNumImageView3.setImageResource(R.drawable.gplb2zoom);
        paramNumImageView4.setImageResource(R.drawable.gplb2zoom);
        paramNumImageView5.setImageResource(R.drawable.gplb2zoom);
      }
      break;
    case 169:
      paramNumImageView1.setImageResource(R.drawable.gpy2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpy2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpy2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpy2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpy2zoom);
      break;
    case 168:
      paramNumImageView1.setImageResource(R.drawable.gpx2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpx2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpx2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpx2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpx2zoom);
      break;
    case 167:
      paramNumImageView1.setImageResource(R.drawable.gpbtn2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbtn2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbtn2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbtn2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbtn2zoom);
      break;
    case 166:
      paramNumImageView1.setImageResource(R.drawable.gpa2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpa2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpa2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpa2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpa2zoom);
      break;
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
      paramNumImageView1.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView2.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView3.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView4.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView5.setImageResource(R.drawable.newkeyzoom);
      break;
    case 155:
      paramNumImageView1.setImageResource(R.drawable.gpm41zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm41zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm41zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm41zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm41zoom);
      break;
    case 154:
      paramNumImageView1.setImageResource(R.drawable.gpm31zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm31zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm31zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm31zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm31zoom);
      break;
    case 153:
      paramNumImageView1.setImageResource(R.drawable.gpm21zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm21zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm21zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm21zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm21zoom);
      break;
    case 152:
      paramNumImageView1.setImageResource(R.drawable.gpm11zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm11zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm11zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm11zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm11zoom);
      break;
    case 151:
      paramNumImageView1.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbprzoom);
      break;
    case 150:
      paramNumImageView1.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbplzoom);
      break;
    case 149:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr21zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr21zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr21zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr21zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr21zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprt1zoom);
        paramNumImageView2.setImageResource(R.drawable.gprt1zoom);
        paramNumImageView3.setImageResource(R.drawable.gprt1zoom);
        paramNumImageView4.setImageResource(R.drawable.gprt1zoom);
        paramNumImageView5.setImageResource(R.drawable.gprt1zoom);
      }
      break;
    case 148:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl21zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl21zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl21zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl21zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl21zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gplt1zoom);
        paramNumImageView2.setImageResource(R.drawable.gplt1zoom);
        paramNumImageView3.setImageResource(R.drawable.gplt1zoom);
        paramNumImageView4.setImageResource(R.drawable.gplt1zoom);
        paramNumImageView5.setImageResource(R.drawable.gplt1zoom);
      }
      break;
    case 147:
      paramNumImageView1.setImageResource(R.drawable.gpr31zoom);
      paramNumImageView2.setImageResource(R.drawable.gpr31zoom);
      paramNumImageView3.setImageResource(R.drawable.gpr31zoom);
      paramNumImageView4.setImageResource(R.drawable.gpr31zoom);
      paramNumImageView5.setImageResource(R.drawable.gpr31zoom);
      break;
    case 146:
      paramNumImageView1.setImageResource(R.drawable.gpl31zoom);
      paramNumImageView2.setImageResource(R.drawable.gpl31zoom);
      paramNumImageView3.setImageResource(R.drawable.gpl31zoom);
      paramNumImageView4.setImageResource(R.drawable.gpl31zoom);
      paramNumImageView5.setImageResource(R.drawable.gpl31zoom);
      break;
    case 145:
      paramNumImageView1.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView2.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView3.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView4.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView5.setImageResource(R.drawable.gpselectzoom);
      break;
    case 144:
      paramNumImageView1.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView2.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView3.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView4.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView5.setImageResource(R.drawable.gpstartzoom);
      break;
    case 143:
      paramNumImageView1.setImageResource(R.drawable.gpright1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpright1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpright1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpright1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpright1zoom);
      break;
    case 142:
      paramNumImageView1.setImageResource(R.drawable.gpleft1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpleft1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpleft1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpleft1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpleft1zoom);
      break;
    case 141:
      paramNumImageView1.setImageResource(R.drawable.gpdown1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpdown1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpdown1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpdown1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpdown1zoom);
      break;
    case 140:
      paramNumImageView1.setImageResource(R.drawable.gpup1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpup1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpup1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpup1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpup1zoom);
      break;
    case 139:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr11zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr11zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr11zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr11zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr11zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprb1zoom);
        paramNumImageView2.setImageResource(R.drawable.gprb1zoom);
        paramNumImageView3.setImageResource(R.drawable.gprb1zoom);
        paramNumImageView4.setImageResource(R.drawable.gprb1zoom);
        paramNumImageView5.setImageResource(R.drawable.gprb1zoom);
      }
      break;
    case 138:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl11zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl11zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl11zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl11zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl11zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gplb1zoom);
        paramNumImageView2.setImageResource(R.drawable.gplb1zoom);
        paramNumImageView3.setImageResource(R.drawable.gplb1zoom);
        paramNumImageView4.setImageResource(R.drawable.gplb1zoom);
        paramNumImageView5.setImageResource(R.drawable.gplb1zoom);
      }
      break;
    case 137:
      paramNumImageView1.setImageResource(R.drawable.gpy1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpy1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpy1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpy1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpy1zoom);
      break;
    case 136:
      paramNumImageView1.setImageResource(R.drawable.gpx1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpx1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpx1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpx1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpx1zoom);
      break;
    case 135:
      paramNumImageView1.setImageResource(R.drawable.gpbtn1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbtn1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbtn1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbtn1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbtn1zoom);
      break;
    case 134:
      paramNumImageView1.setImageResource(R.drawable.gpa1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpa1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpa1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpa1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpa1zoom);
      break;
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
      paramNumImageView1.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView2.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView3.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView4.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView5.setImageResource(R.drawable.newkeyzoom);
      break;
    case 123:
      paramNumImageView1.setImageResource(R.drawable.gpm4lrzoom);
      paramNumImageView2.setImageResource(R.drawable.gpm4lrzoom);
      paramNumImageView3.setImageResource(R.drawable.gpm4lrzoom);
      paramNumImageView4.setImageResource(R.drawable.gpm4lrzoom);
      paramNumImageView5.setImageResource(R.drawable.gpm4lrzoom);
      break;
    case 122:
      paramNumImageView1.setImageResource(R.drawable.gpm3lrzoom);
      paramNumImageView2.setImageResource(R.drawable.gpm3lrzoom);
      paramNumImageView3.setImageResource(R.drawable.gpm3lrzoom);
      paramNumImageView4.setImageResource(R.drawable.gpm3lrzoom);
      paramNumImageView5.setImageResource(R.drawable.gpm3lrzoom);
      break;
    case 121:
      paramNumImageView1.setImageResource(R.drawable.gpm2lrzoom);
      paramNumImageView2.setImageResource(R.drawable.gpm2lrzoom);
      paramNumImageView3.setImageResource(R.drawable.gpm2lrzoom);
      paramNumImageView4.setImageResource(R.drawable.gpm2lrzoom);
      paramNumImageView5.setImageResource(R.drawable.gpm2lrzoom);
      break;
    case 120:
      paramNumImageView1.setImageResource(R.drawable.gpm1lrzoom);
      paramNumImageView2.setImageResource(R.drawable.gpm1lrzoom);
      paramNumImageView3.setImageResource(R.drawable.gpm1lrzoom);
      paramNumImageView4.setImageResource(R.drawable.gpm1lrzoom);
      paramNumImageView5.setImageResource(R.drawable.gpm1lrzoom);
      break;
    case 119:
      paramNumImageView1.setImageResource(R.drawable.gpalrzoom);
      paramNumImageView2.setImageResource(R.drawable.gpalrzoom);
      paramNumImageView3.setImageResource(R.drawable.gpalrzoom);
      paramNumImageView4.setImageResource(R.drawable.gpalrzoom);
      paramNumImageView5.setImageResource(R.drawable.gpalrzoom);
      break;
    case 118:
      paramNumImageView1.setImageResource(R.drawable.gpalrzoom);
      paramNumImageView2.setImageResource(R.drawable.gpalrzoom);
      paramNumImageView3.setImageResource(R.drawable.gpalrzoom);
      paramNumImageView4.setImageResource(R.drawable.gpalrzoom);
      paramNumImageView5.setImageResource(R.drawable.gpalrzoom);
      break;
    case 117:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2lrzoom);
        paramNumImageView2.setImageResource(R.drawable.gpr2lrzoom);
        paramNumImageView3.setImageResource(R.drawable.gpr2lrzoom);
        paramNumImageView4.setImageResource(R.drawable.gpr2lrzoom);
        paramNumImageView5.setImageResource(R.drawable.gpr2lrzoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprtlrzoom);
        paramNumImageView2.setImageResource(R.drawable.gprtlrzoom);
        paramNumImageView3.setImageResource(R.drawable.gprtlrzoom);
        paramNumImageView4.setImageResource(R.drawable.gprtlrzoom);
        paramNumImageView5.setImageResource(R.drawable.gprtlrzoom);
      }
      break;
    case 116:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2lrzoom);
        paramNumImageView2.setImageResource(R.drawable.gpl2lrzoom);
        paramNumImageView3.setImageResource(R.drawable.gpl2lrzoom);
        paramNumImageView4.setImageResource(R.drawable.gpl2lrzoom);
        paramNumImageView5.setImageResource(R.drawable.gpl2lrzoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpltlrzoom);
        paramNumImageView2.setImageResource(R.drawable.gpltlrzoom);
        paramNumImageView3.setImageResource(R.drawable.gpltlrzoom);
        paramNumImageView4.setImageResource(R.drawable.gpltlrzoom);
        paramNumImageView5.setImageResource(R.drawable.gpltlrzoom);
      }
      break;
    case 115:
      paramNumImageView1.setImageResource(R.drawable.gpr3lrzoom);
      paramNumImageView2.setImageResource(R.drawable.gpr3lrzoom);
      paramNumImageView3.setImageResource(R.drawable.gpr3lrzoom);
      paramNumImageView4.setImageResource(R.drawable.gpr3lrzoom);
      paramNumImageView5.setImageResource(R.drawable.gpr3lrzoom);
      break;
    case 114:
      paramNumImageView1.setImageResource(R.drawable.gpl3lrzoom);
      paramNumImageView2.setImageResource(R.drawable.gpl3lrzoom);
      paramNumImageView3.setImageResource(R.drawable.gpl3lrzoom);
      paramNumImageView4.setImageResource(R.drawable.gpl3lrzoom);
      paramNumImageView5.setImageResource(R.drawable.gpl3lrzoom);
      break;
    case 113:
      paramNumImageView1.setImageResource(R.drawable.gpalrzoom);
      paramNumImageView2.setImageResource(R.drawable.gpalrzoom);
      paramNumImageView3.setImageResource(R.drawable.gpalrzoom);
      paramNumImageView4.setImageResource(R.drawable.gpalrzoom);
      paramNumImageView5.setImageResource(R.drawable.gpalrzoom);
      break;
    case 112:
      paramNumImageView1.setImageResource(R.drawable.gpalrzoom);
      paramNumImageView2.setImageResource(R.drawable.gpalrzoom);
      paramNumImageView3.setImageResource(R.drawable.gpalrzoom);
      paramNumImageView4.setImageResource(R.drawable.gpalrzoom);
      paramNumImageView5.setImageResource(R.drawable.gpalrzoom);
      break;
    case 111:
      paramNumImageView1.setImageResource(R.drawable.gpalrzoom);
      paramNumImageView2.setImageResource(R.drawable.gpalrzoom);
      paramNumImageView3.setImageResource(R.drawable.gpalrzoom);
      paramNumImageView4.setImageResource(R.drawable.gpalrzoom);
      paramNumImageView5.setImageResource(R.drawable.gpalrzoom);
      break;
    case 110:
      paramNumImageView1.setImageResource(R.drawable.gpalrzoom);
      paramNumImageView2.setImageResource(R.drawable.gpalrzoom);
      paramNumImageView3.setImageResource(R.drawable.gpalrzoom);
      paramNumImageView4.setImageResource(R.drawable.gpalrzoom);
      paramNumImageView5.setImageResource(R.drawable.gpalrzoom);
      break;
    case 109:
      paramNumImageView1.setImageResource(R.drawable.gpalrzoom);
      paramNumImageView2.setImageResource(R.drawable.gpalrzoom);
      paramNumImageView3.setImageResource(R.drawable.gpalrzoom);
      paramNumImageView4.setImageResource(R.drawable.gpalrzoom);
      paramNumImageView5.setImageResource(R.drawable.gpalrzoom);
      break;
    case 108:
      paramNumImageView1.setImageResource(R.drawable.gpalrzoom);
      paramNumImageView2.setImageResource(R.drawable.gpalrzoom);
      paramNumImageView3.setImageResource(R.drawable.gpalrzoom);
      paramNumImageView4.setImageResource(R.drawable.gpalrzoom);
      paramNumImageView5.setImageResource(R.drawable.gpalrzoom);
      break;
    case 107:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1lrzoom);
        paramNumImageView2.setImageResource(R.drawable.gpr1lrzoom);
        paramNumImageView3.setImageResource(R.drawable.gpr1lrzoom);
        paramNumImageView4.setImageResource(R.drawable.gpr1lrzoom);
        paramNumImageView5.setImageResource(R.drawable.gpr1lrzoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprblrzoom);
        paramNumImageView2.setImageResource(R.drawable.gprblrzoom);
        paramNumImageView3.setImageResource(R.drawable.gprblrzoom);
        paramNumImageView4.setImageResource(R.drawable.gprblrzoom);
        paramNumImageView5.setImageResource(R.drawable.gprblrzoom);
      }
      break;
    case 106:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1lrzoom);
        paramNumImageView2.setImageResource(R.drawable.gpl1lrzoom);
        paramNumImageView3.setImageResource(R.drawable.gpl1lrzoom);
        paramNumImageView4.setImageResource(R.drawable.gpl1lrzoom);
        paramNumImageView5.setImageResource(R.drawable.gpl1lrzoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gplblrzoom);
        paramNumImageView2.setImageResource(R.drawable.gplblrzoom);
        paramNumImageView3.setImageResource(R.drawable.gplblrzoom);
        paramNumImageView4.setImageResource(R.drawable.gplblrzoom);
        paramNumImageView5.setImageResource(R.drawable.gplblrzoom);
      }
      break;
    case 105:
      paramNumImageView1.setImageResource(R.drawable.gpylrzoom);
      paramNumImageView2.setImageResource(R.drawable.gpylrzoom);
      paramNumImageView3.setImageResource(R.drawable.gpylrzoom);
      paramNumImageView4.setImageResource(R.drawable.gpylrzoom);
      paramNumImageView5.setImageResource(R.drawable.gpylrzoom);
      break;
    case 104:
      paramNumImageView1.setImageResource(R.drawable.gpxlrzoom);
      paramNumImageView2.setImageResource(R.drawable.gpxlrzoom);
      paramNumImageView3.setImageResource(R.drawable.gpxlrzoom);
      paramNumImageView4.setImageResource(R.drawable.gpxlrzoom);
      paramNumImageView5.setImageResource(R.drawable.gpxlrzoom);
      break;
    case 103:
      paramNumImageView1.setImageResource(R.drawable.gpblrzoom);
      paramNumImageView2.setImageResource(R.drawable.gpblrzoom);
      paramNumImageView3.setImageResource(R.drawable.gpblrzoom);
      paramNumImageView4.setImageResource(R.drawable.gpblrzoom);
      paramNumImageView5.setImageResource(R.drawable.gpblrzoom);
      break;
    case 102:
      paramNumImageView1.setImageResource(R.drawable.gpalrzoom);
      paramNumImageView2.setImageResource(R.drawable.gpalrzoom);
      paramNumImageView3.setImageResource(R.drawable.gpalrzoom);
      paramNumImageView4.setImageResource(R.drawable.gpalrzoom);
      paramNumImageView5.setImageResource(R.drawable.gpalrzoom);
      break;
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
      paramNumImageView1.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView2.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView3.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView4.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView5.setImageResource(R.drawable.newkeyzoom);
      break;
    case 91:
      paramNumImageView1.setImageResource(R.drawable.gpm4bomzoom);
      paramNumImageView2.setImageResource(R.drawable.gpm4bomzoom);
      paramNumImageView3.setImageResource(R.drawable.gpm4bomzoom);
      paramNumImageView4.setImageResource(R.drawable.gpm4bomzoom);
      paramNumImageView5.setImageResource(R.drawable.gpm4bomzoom);
      break;
    case 90:
      paramNumImageView1.setImageResource(R.drawable.gpm3bomzoom);
      paramNumImageView2.setImageResource(R.drawable.gpm3bomzoom);
      paramNumImageView3.setImageResource(R.drawable.gpm3bomzoom);
      paramNumImageView4.setImageResource(R.drawable.gpm3bomzoom);
      paramNumImageView5.setImageResource(R.drawable.gpm3bomzoom);
      break;
    case 89:
      paramNumImageView1.setImageResource(R.drawable.gpm2bomzoom);
      paramNumImageView2.setImageResource(R.drawable.gpm2bomzoom);
      paramNumImageView3.setImageResource(R.drawable.gpm2bomzoom);
      paramNumImageView4.setImageResource(R.drawable.gpm2bomzoom);
      paramNumImageView5.setImageResource(R.drawable.gpm2bomzoom);
      break;
    case 88:
      paramNumImageView1.setImageResource(R.drawable.gpm1bomzoom);
      paramNumImageView2.setImageResource(R.drawable.gpm1bomzoom);
      paramNumImageView3.setImageResource(R.drawable.gpm1bomzoom);
      paramNumImageView4.setImageResource(R.drawable.gpm1bomzoom);
      paramNumImageView5.setImageResource(R.drawable.gpm1bomzoom);
      break;
    case 87:
      paramNumImageView1.setImageResource(R.drawable.gparightzoom);
      paramNumImageView2.setImageResource(R.drawable.gparightzoom);
      paramNumImageView3.setImageResource(R.drawable.gparightzoom);
      paramNumImageView4.setImageResource(R.drawable.gparightzoom);
      paramNumImageView5.setImageResource(R.drawable.gparightzoom);
      break;
    case 86:
      paramNumImageView1.setImageResource(R.drawable.gparightzoom);
      paramNumImageView2.setImageResource(R.drawable.gparightzoom);
      paramNumImageView3.setImageResource(R.drawable.gparightzoom);
      paramNumImageView4.setImageResource(R.drawable.gparightzoom);
      paramNumImageView5.setImageResource(R.drawable.gparightzoom);
      break;
    case 85:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2bomzoom);
        paramNumImageView2.setImageResource(R.drawable.gpr2bomzoom);
        paramNumImageView3.setImageResource(R.drawable.gpr2bomzoom);
        paramNumImageView4.setImageResource(R.drawable.gpr2bomzoom);
        paramNumImageView5.setImageResource(R.drawable.gpr2bomzoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprtbomzoom);
        paramNumImageView2.setImageResource(R.drawable.gprtbomzoom);
        paramNumImageView3.setImageResource(R.drawable.gprtbomzoom);
        paramNumImageView4.setImageResource(R.drawable.gprtbomzoom);
        paramNumImageView5.setImageResource(R.drawable.gprtbomzoom);
      }
      break;
    case 84:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2bomzoom);
        paramNumImageView2.setImageResource(R.drawable.gpl2bomzoom);
        paramNumImageView3.setImageResource(R.drawable.gpl2bomzoom);
        paramNumImageView4.setImageResource(R.drawable.gpl2bomzoom);
        paramNumImageView5.setImageResource(R.drawable.gpl2bomzoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpltbomzoom);
        paramNumImageView2.setImageResource(R.drawable.gpltbomzoom);
        paramNumImageView3.setImageResource(R.drawable.gpltbomzoom);
        paramNumImageView4.setImageResource(R.drawable.gpltbomzoom);
        paramNumImageView5.setImageResource(R.drawable.gpltbomzoom);
      }
      break;
    case 83:
      paramNumImageView1.setImageResource(R.drawable.gpr3bomzoom);
      paramNumImageView2.setImageResource(R.drawable.gpr3bomzoom);
      paramNumImageView3.setImageResource(R.drawable.gpr3bomzoom);
      paramNumImageView4.setImageResource(R.drawable.gpr3bomzoom);
      paramNumImageView5.setImageResource(R.drawable.gpr3bomzoom);
      break;
    case 82:
      paramNumImageView1.setImageResource(R.drawable.gpl3bomzoom);
      paramNumImageView2.setImageResource(R.drawable.gpl3bomzoom);
      paramNumImageView3.setImageResource(R.drawable.gpl3bomzoom);
      paramNumImageView4.setImageResource(R.drawable.gpl3bomzoom);
      paramNumImageView5.setImageResource(R.drawable.gpl3bomzoom);
      break;
    case 81:
      paramNumImageView1.setImageResource(R.drawable.gparightzoom);
      paramNumImageView2.setImageResource(R.drawable.gparightzoom);
      paramNumImageView3.setImageResource(R.drawable.gparightzoom);
      paramNumImageView4.setImageResource(R.drawable.gparightzoom);
      paramNumImageView5.setImageResource(R.drawable.gparightzoom);
      break;
    case 80:
      paramNumImageView1.setImageResource(R.drawable.gparightzoom);
      paramNumImageView2.setImageResource(R.drawable.gparightzoom);
      paramNumImageView3.setImageResource(R.drawable.gparightzoom);
      paramNumImageView4.setImageResource(R.drawable.gparightzoom);
      paramNumImageView5.setImageResource(R.drawable.gparightzoom);
      break;
    case 79:
      paramNumImageView1.setImageResource(R.drawable.gparightzoom);
      paramNumImageView2.setImageResource(R.drawable.gparightzoom);
      paramNumImageView3.setImageResource(R.drawable.gparightzoom);
      paramNumImageView4.setImageResource(R.drawable.gparightzoom);
      paramNumImageView5.setImageResource(R.drawable.gparightzoom);
      break;
    case 78:
      paramNumImageView1.setImageResource(R.drawable.gparightzoom);
      paramNumImageView2.setImageResource(R.drawable.gparightzoom);
      paramNumImageView3.setImageResource(R.drawable.gparightzoom);
      paramNumImageView4.setImageResource(R.drawable.gparightzoom);
      paramNumImageView5.setImageResource(R.drawable.gparightzoom);
      break;
    case 77:
      paramNumImageView1.setImageResource(R.drawable.gparightzoom);
      paramNumImageView2.setImageResource(R.drawable.gparightzoom);
      paramNumImageView3.setImageResource(R.drawable.gparightzoom);
      paramNumImageView4.setImageResource(R.drawable.gparightzoom);
      paramNumImageView5.setImageResource(R.drawable.gparightzoom);
      break;
    case 76:
      paramNumImageView1.setImageResource(R.drawable.gparightzoom);
      paramNumImageView2.setImageResource(R.drawable.gparightzoom);
      paramNumImageView3.setImageResource(R.drawable.gparightzoom);
      paramNumImageView4.setImageResource(R.drawable.gparightzoom);
      paramNumImageView5.setImageResource(R.drawable.gparightzoom);
      break;
    case 75:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1bomzoom);
        paramNumImageView2.setImageResource(R.drawable.gpr1bomzoom);
        paramNumImageView3.setImageResource(R.drawable.gpr1bomzoom);
        paramNumImageView4.setImageResource(R.drawable.gpr1bomzoom);
        paramNumImageView5.setImageResource(R.drawable.gpr1bomzoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprbbomzoom);
        paramNumImageView2.setImageResource(R.drawable.gprbbomzoom);
        paramNumImageView3.setImageResource(R.drawable.gprbbomzoom);
        paramNumImageView4.setImageResource(R.drawable.gprbbomzoom);
        paramNumImageView5.setImageResource(R.drawable.gprbbomzoom);
      }
      break;
    case 74:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1bomzoom);
        paramNumImageView2.setImageResource(R.drawable.gpl1bomzoom);
        paramNumImageView3.setImageResource(R.drawable.gpl1bomzoom);
        paramNumImageView4.setImageResource(R.drawable.gpl1bomzoom);
        paramNumImageView5.setImageResource(R.drawable.gpl1bomzoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gplbbomzoom);
        paramNumImageView2.setImageResource(R.drawable.gplbbomzoom);
        paramNumImageView3.setImageResource(R.drawable.gplbbomzoom);
        paramNumImageView4.setImageResource(R.drawable.gplbbomzoom);
        paramNumImageView5.setImageResource(R.drawable.gplbbomzoom);
      }
      break;
    case 73:
      paramNumImageView1.setImageResource(R.drawable.gpyrightzoom);
      paramNumImageView2.setImageResource(R.drawable.gpyrightzoom);
      paramNumImageView3.setImageResource(R.drawable.gpyrightzoom);
      paramNumImageView4.setImageResource(R.drawable.gpyrightzoom);
      paramNumImageView5.setImageResource(R.drawable.gpyrightzoom);
      break;
    case 72:
      paramNumImageView1.setImageResource(R.drawable.gpxrightzoom);
      paramNumImageView2.setImageResource(R.drawable.gpxrightzoom);
      paramNumImageView3.setImageResource(R.drawable.gpxrightzoom);
      paramNumImageView4.setImageResource(R.drawable.gpxrightzoom);
      paramNumImageView5.setImageResource(R.drawable.gpxrightzoom);
      break;
    case 71:
      paramNumImageView1.setImageResource(R.drawable.gpbright1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbright1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbright1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbright1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbright1zoom);
      break;
    case 70:
      paramNumImageView1.setImageResource(R.drawable.gparightzoom);
      paramNumImageView2.setImageResource(R.drawable.gparightzoom);
      paramNumImageView3.setImageResource(R.drawable.gparightzoom);
      paramNumImageView4.setImageResource(R.drawable.gparightzoom);
      paramNumImageView5.setImageResource(R.drawable.gparightzoom);
      break;
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
      paramNumImageView1.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView2.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView3.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView4.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView5.setImageResource(R.drawable.newkeyzoom);
      break;
    case 59:
      paramNumImageView1.setImageResource(R.drawable.gpm4leftzoom);
      paramNumImageView2.setImageResource(R.drawable.gpm4leftzoom);
      paramNumImageView3.setImageResource(R.drawable.gpm4leftzoom);
      paramNumImageView4.setImageResource(R.drawable.gpm4leftzoom);
      paramNumImageView5.setImageResource(R.drawable.gpm4leftzoom);
      break;
    case 58:
      paramNumImageView1.setImageResource(R.drawable.gpm3leftzoom);
      paramNumImageView2.setImageResource(R.drawable.gpm3leftzoom);
      paramNumImageView3.setImageResource(R.drawable.gpm3leftzoom);
      paramNumImageView4.setImageResource(R.drawable.gpm3leftzoom);
      paramNumImageView5.setImageResource(R.drawable.gpm3leftzoom);
      break;
    case 57:
      paramNumImageView1.setImageResource(R.drawable.gpm2leftzoom);
      paramNumImageView2.setImageResource(R.drawable.gpm2leftzoom);
      paramNumImageView3.setImageResource(R.drawable.gpm2leftzoom);
      paramNumImageView4.setImageResource(R.drawable.gpm2leftzoom);
      paramNumImageView5.setImageResource(R.drawable.gpm2leftzoom);
      break;
    case 56:
      paramNumImageView1.setImageResource(R.drawable.gpm1leftzoom);
      paramNumImageView2.setImageResource(R.drawable.gpm1leftzoom);
      paramNumImageView3.setImageResource(R.drawable.gpm1leftzoom);
      paramNumImageView4.setImageResource(R.drawable.gpm1leftzoom);
      paramNumImageView5.setImageResource(R.drawable.gpm1leftzoom);
      break;
    case 55:
      paramNumImageView1.setImageResource(R.drawable.gpaleftzoom);
      paramNumImageView2.setImageResource(R.drawable.gpaleftzoom);
      paramNumImageView3.setImageResource(R.drawable.gpaleftzoom);
      paramNumImageView4.setImageResource(R.drawable.gpaleftzoom);
      paramNumImageView5.setImageResource(R.drawable.gpaleftzoom);
      break;
    case 54:
      paramNumImageView1.setImageResource(R.drawable.gpaleftzoom);
      paramNumImageView2.setImageResource(R.drawable.gpaleftzoom);
      paramNumImageView3.setImageResource(R.drawable.gpaleftzoom);
      paramNumImageView4.setImageResource(R.drawable.gpaleftzoom);
      paramNumImageView5.setImageResource(R.drawable.gpaleftzoom);
      break;
    case 53:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2leftzoom);
        paramNumImageView2.setImageResource(R.drawable.gpr2leftzoom);
        paramNumImageView3.setImageResource(R.drawable.gpr2leftzoom);
        paramNumImageView4.setImageResource(R.drawable.gpr2leftzoom);
        paramNumImageView5.setImageResource(R.drawable.gpr2leftzoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprtleftzoom);
        paramNumImageView2.setImageResource(R.drawable.gprtleftzoom);
        paramNumImageView3.setImageResource(R.drawable.gprtleftzoom);
        paramNumImageView4.setImageResource(R.drawable.gprtleftzoom);
        paramNumImageView5.setImageResource(R.drawable.gprtleftzoom);
      }
      break;
    case 52:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2leftzoom);
        paramNumImageView2.setImageResource(R.drawable.gpl2leftzoom);
        paramNumImageView3.setImageResource(R.drawable.gpl2leftzoom);
        paramNumImageView4.setImageResource(R.drawable.gpl2leftzoom);
        paramNumImageView5.setImageResource(R.drawable.gpl2leftzoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpltleftzoom);
        paramNumImageView2.setImageResource(R.drawable.gpltleftzoom);
        paramNumImageView3.setImageResource(R.drawable.gpltleftzoom);
        paramNumImageView4.setImageResource(R.drawable.gpltleftzoom);
        paramNumImageView5.setImageResource(R.drawable.gpltleftzoom);
      }
      break;
    case 51:
      paramNumImageView1.setImageResource(R.drawable.gpr3leftzoom);
      paramNumImageView2.setImageResource(R.drawable.gpr3leftzoom);
      paramNumImageView3.setImageResource(R.drawable.gpr3leftzoom);
      paramNumImageView4.setImageResource(R.drawable.gpr3leftzoom);
      paramNumImageView5.setImageResource(R.drawable.gpr3leftzoom);
      break;
    case 49:
      paramNumImageView1.setImageResource(R.drawable.gpaleftzoom);
      paramNumImageView2.setImageResource(R.drawable.gpaleftzoom);
      paramNumImageView3.setImageResource(R.drawable.gpaleftzoom);
      paramNumImageView4.setImageResource(R.drawable.gpaleftzoom);
      paramNumImageView5.setImageResource(R.drawable.gpaleftzoom);
    case 50:
      paramNumImageView1.setImageResource(R.drawable.gpl3leftzoom);
      paramNumImageView2.setImageResource(R.drawable.gpl3leftzoom);
      paramNumImageView3.setImageResource(R.drawable.gpl3leftzoom);
      paramNumImageView4.setImageResource(R.drawable.gpl3leftzoom);
      paramNumImageView5.setImageResource(R.drawable.gpl3leftzoom);
      break;
    case 48:
      paramNumImageView1.setImageResource(R.drawable.gpaleftzoom);
      paramNumImageView2.setImageResource(R.drawable.gpaleftzoom);
      paramNumImageView3.setImageResource(R.drawable.gpaleftzoom);
      paramNumImageView4.setImageResource(R.drawable.gpaleftzoom);
      paramNumImageView5.setImageResource(R.drawable.gpaleftzoom);
      break;
    case 47:
      paramNumImageView1.setImageResource(R.drawable.gpaleftzoom);
      paramNumImageView2.setImageResource(R.drawable.gpaleftzoom);
      paramNumImageView3.setImageResource(R.drawable.gpaleftzoom);
      paramNumImageView4.setImageResource(R.drawable.gpaleftzoom);
      paramNumImageView5.setImageResource(R.drawable.gpaleftzoom);
      break;
    case 46:
      paramNumImageView1.setImageResource(R.drawable.gpaleftzoom);
      paramNumImageView2.setImageResource(R.drawable.gpaleftzoom);
      paramNumImageView3.setImageResource(R.drawable.gpaleftzoom);
      paramNumImageView4.setImageResource(R.drawable.gpaleftzoom);
      paramNumImageView5.setImageResource(R.drawable.gpaleftzoom);
      break;
    case 45:
      paramNumImageView1.setImageResource(R.drawable.gpaleftzoom);
      paramNumImageView2.setImageResource(R.drawable.gpaleftzoom);
      paramNumImageView3.setImageResource(R.drawable.gpaleftzoom);
      paramNumImageView4.setImageResource(R.drawable.gpaleftzoom);
      paramNumImageView5.setImageResource(R.drawable.gpaleftzoom);
      break;
    case 44:
      paramNumImageView1.setImageResource(R.drawable.gpaleftzoom);
      paramNumImageView2.setImageResource(R.drawable.gpaleftzoom);
      paramNumImageView3.setImageResource(R.drawable.gpaleftzoom);
      paramNumImageView4.setImageResource(R.drawable.gpaleftzoom);
      paramNumImageView5.setImageResource(R.drawable.gpaleftzoom);
      break;
    case 43:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1leftzoom);
        paramNumImageView2.setImageResource(R.drawable.gpr1leftzoom);
        paramNumImageView3.setImageResource(R.drawable.gpr1leftzoom);
        paramNumImageView4.setImageResource(R.drawable.gpr1leftzoom);
        paramNumImageView5.setImageResource(R.drawable.gpr1leftzoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprbleftzoom);
        paramNumImageView2.setImageResource(R.drawable.gprbleftzoom);
        paramNumImageView3.setImageResource(R.drawable.gprbleftzoom);
        paramNumImageView4.setImageResource(R.drawable.gprbleftzoom);
        paramNumImageView5.setImageResource(R.drawable.gprbleftzoom);
      }
      break;
    case 42:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1leftzoom);
        paramNumImageView2.setImageResource(R.drawable.gpl1leftzoom);
        paramNumImageView3.setImageResource(R.drawable.gpl1leftzoom);
        paramNumImageView4.setImageResource(R.drawable.gpl1leftzoom);
        paramNumImageView5.setImageResource(R.drawable.gpl1leftzoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gplbleftzoom);
        paramNumImageView2.setImageResource(R.drawable.gplbleftzoom);
        paramNumImageView3.setImageResource(R.drawable.gplbleftzoom);
        paramNumImageView4.setImageResource(R.drawable.gplbleftzoom);
        paramNumImageView5.setImageResource(R.drawable.gplbleftzoom);
      }
      break;
    case 41:
      paramNumImageView1.setImageResource(R.drawable.gpyleftzoom);
      paramNumImageView2.setImageResource(R.drawable.gpyleftzoom);
      paramNumImageView3.setImageResource(R.drawable.gpyleftzoom);
      paramNumImageView4.setImageResource(R.drawable.gpyleftzoom);
      paramNumImageView5.setImageResource(R.drawable.gpyleftzoom);
      break;
    case 40:
      paramNumImageView1.setImageResource(R.drawable.gpxleftzoom);
      paramNumImageView2.setImageResource(R.drawable.gpxleftzoom);
      paramNumImageView3.setImageResource(R.drawable.gpxleftzoom);
      paramNumImageView4.setImageResource(R.drawable.gpxleftzoom);
      paramNumImageView5.setImageResource(R.drawable.gpxleftzoom);
      break;
    case 39:
      paramNumImageView1.setImageResource(R.drawable.gpbleft1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbleft1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbleft1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbleft1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbleft1zoom);
      break;
    case 38:
      paramNumImageView1.setImageResource(R.drawable.gpaleftzoom);
      paramNumImageView2.setImageResource(R.drawable.gpaleftzoom);
      paramNumImageView3.setImageResource(R.drawable.gpaleftzoom);
      paramNumImageView4.setImageResource(R.drawable.gpaleftzoom);
      paramNumImageView5.setImageResource(R.drawable.gpaleftzoom);
      break;
    case 37:
      paramNumImageView1.setImageResource(R.drawable.gpsensorzoom);
      paramNumImageView2.setImageResource(R.drawable.gpsensorzoom);
      paramNumImageView3.setImageResource(R.drawable.gpsensorzoom);
      paramNumImageView4.setImageResource(R.drawable.gpsensorzoom);
      paramNumImageView5.setImageResource(R.drawable.gpsensorzoom);
      break;
    case 36:
      paramNumImageView1.setImageResource(R.drawable.gptouchzoom);
      paramNumImageView2.setImageResource(R.drawable.gptouchzoom);
      paramNumImageView3.setImageResource(R.drawable.gptouchzoom);
      paramNumImageView4.setImageResource(R.drawable.gptouchzoom);
      paramNumImageView5.setImageResource(R.drawable.gptouchzoom);
      break;
    case 35:
      paramNumImageView1.setImageResource(R.drawable.gpyzoom_add);
      paramNumImageView2.setImageResource(R.drawable.gpyzoom_add);
      paramNumImageView3.setImageResource(R.drawable.gpyzoom_add);
      paramNumImageView4.setImageResource(R.drawable.gpyzoom_add);
      paramNumImageView5.setImageResource(R.drawable.gpyzoom_add);
      break;
    case 34:
      paramNumImageView1.setImageResource(R.drawable.gpxzoom_add);
      paramNumImageView2.setImageResource(R.drawable.gpxzoom_add);
      paramNumImageView3.setImageResource(R.drawable.gpxzoom_add);
      paramNumImageView4.setImageResource(R.drawable.gpxzoom_add);
      paramNumImageView5.setImageResource(R.drawable.gpxzoom_add);
      break;
    case 33:
      paramNumImageView1.setImageResource(R.drawable.gpbzoom_add);
      paramNumImageView2.setImageResource(R.drawable.gpbzoom_add);
      paramNumImageView3.setImageResource(R.drawable.gpbzoom_add);
      paramNumImageView4.setImageResource(R.drawable.gpbzoom_add);
      paramNumImageView5.setImageResource(R.drawable.gpbzoom_add);
      break;
    case 32:
      paramNumImageView1.setImageResource(R.drawable.gpazoom_add);
      paramNumImageView2.setImageResource(R.drawable.gpazoom_add);
      paramNumImageView3.setImageResource(R.drawable.gpazoom_add);
      paramNumImageView4.setImageResource(R.drawable.gpazoom_add);
      paramNumImageView5.setImageResource(R.drawable.gpazoom_add);
      break;
    case 27:
    case 28:
    case 29:
    case 30:
    case 31:
      paramNumImageView1.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView2.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView3.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView4.setImageResource(R.drawable.newkeyzoom);
      paramNumImageView5.setImageResource(R.drawable.newkeyzoom);
      break;
    case 26:
      paramNumImageView1.setImageResource(R.drawable.center3zoom);
      paramNumImageView2.setImageResource(R.drawable.center3zoom);
      paramNumImageView3.setImageResource(R.drawable.center3zoom);
      paramNumImageView4.setImageResource(R.drawable.center3zoom);
      paramNumImageView5.setImageResource(R.drawable.center3zoom);
      break;
    case 25:
      paramNumImageView1.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbpr2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbpr2zoom);
      break;
    case 24:
      paramNumImageView1.setImageResource(R.drawable.center2zoom);
      paramNumImageView2.setImageResource(R.drawable.center2zoom);
      paramNumImageView3.setImageResource(R.drawable.center2zoom);
      paramNumImageView4.setImageResource(R.drawable.center2zoom);
      paramNumImageView5.setImageResource(R.drawable.center2zoom);
      break;
    case 23:
      paramNumImageView1.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpbpr1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpbpr1zoom);
      break;
    case 22:
      paramNumImageView1.setImageResource(R.drawable.center1zoom);
      paramNumImageView2.setImageResource(R.drawable.center1zoom);
      paramNumImageView3.setImageResource(R.drawable.center1zoom);
      paramNumImageView4.setImageResource(R.drawable.center1zoom);
      paramNumImageView5.setImageResource(R.drawable.center1zoom);
      break;
    case 21:
      paramNumImageView1.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm4zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm4zoom);
      break;
    case 20:
      paramNumImageView1.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm3zoom);
      break;
    case 19:
      paramNumImageView1.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm2zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm2zoom);
      break;
    case 18:
      paramNumImageView1.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView2.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView3.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView4.setImageResource(R.drawable.gpm1zoom);
      paramNumImageView5.setImageResource(R.drawable.gpm1zoom);
      break;
    case 17:
      paramNumImageView1.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbprzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbprzoom);
      break;
    case 16:
      paramNumImageView1.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbplzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbplzoom);
      break;
    case 15:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprtzoom);
        paramNumImageView2.setImageResource(R.drawable.gprtzoom);
        paramNumImageView3.setImageResource(R.drawable.gprtzoom);
        paramNumImageView4.setImageResource(R.drawable.gprtzoom);
        paramNumImageView5.setImageResource(R.drawable.gprtzoom);
      }
      break;
    case 14:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl2zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl2zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpltzoom);
        paramNumImageView2.setImageResource(R.drawable.gpltzoom);
        paramNumImageView3.setImageResource(R.drawable.gpltzoom);
        paramNumImageView4.setImageResource(R.drawable.gpltzoom);
        paramNumImageView5.setImageResource(R.drawable.gpltzoom);
      }
      break;
    case 13:
      paramNumImageView1.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpr3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpr3zoom);
      break;
    case 12:
      paramNumImageView1.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView2.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView3.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView4.setImageResource(R.drawable.gpl3zoom);
      paramNumImageView5.setImageResource(R.drawable.gpl3zoom);
      break;
    case 11:
      paramNumImageView1.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView2.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView3.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView4.setImageResource(R.drawable.gpselectzoom);
      paramNumImageView5.setImageResource(R.drawable.gpselectzoom);
      break;
    case 10:
      paramNumImageView1.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView2.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView3.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView4.setImageResource(R.drawable.gpstartzoom);
      paramNumImageView5.setImageResource(R.drawable.gpstartzoom);
      break;
    case 9:
      paramNumImageView1.setImageResource(R.drawable.gprightzoom);
      paramNumImageView2.setImageResource(R.drawable.gprightzoom);
      paramNumImageView3.setImageResource(R.drawable.gprightzoom);
      paramNumImageView4.setImageResource(R.drawable.gprightzoom);
      paramNumImageView5.setImageResource(R.drawable.gprightzoom);
      break;
    case 8:
      paramNumImageView1.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView2.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView3.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView4.setImageResource(R.drawable.gpleftzoom);
      paramNumImageView5.setImageResource(R.drawable.gpleftzoom);
      break;
    case 7:
      paramNumImageView1.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView2.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView3.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView4.setImageResource(R.drawable.gpdownzoom);
      paramNumImageView5.setImageResource(R.drawable.gpdownzoom);
      break;
    case 6:
      paramNumImageView1.setImageResource(R.drawable.gpupzoom);
      paramNumImageView2.setImageResource(R.drawable.gpupzoom);
      paramNumImageView3.setImageResource(R.drawable.gpupzoom);
      paramNumImageView4.setImageResource(R.drawable.gpupzoom);
      paramNumImageView5.setImageResource(R.drawable.gpupzoom);
      break;
    case 5:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpr1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpr1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprbzoom);
        paramNumImageView2.setImageResource(R.drawable.gprbzoom);
        paramNumImageView3.setImageResource(R.drawable.gprbzoom);
        paramNumImageView4.setImageResource(R.drawable.gprbzoom);
        paramNumImageView5.setImageResource(R.drawable.gprbzoom);
      }
      break;
    case 4:
      if (bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView2.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView3.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView4.setImageResource(R.drawable.gpl1zoom);
        paramNumImageView5.setImageResource(R.drawable.gpl1zoom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gplbzoom);
        paramNumImageView2.setImageResource(R.drawable.gplbzoom);
        paramNumImageView3.setImageResource(R.drawable.gplbzoom);
        paramNumImageView4.setImageResource(R.drawable.gplbzoom);
        paramNumImageView5.setImageResource(R.drawable.gplbzoom);
      }
      break;
    case 3:
      paramNumImageView1.setImageResource(R.drawable.gpyzoom);
      paramNumImageView2.setImageResource(R.drawable.gpyzoom);
      paramNumImageView3.setImageResource(R.drawable.gpyzoom);
      paramNumImageView4.setImageResource(R.drawable.gpyzoom);
      paramNumImageView5.setImageResource(R.drawable.gpyzoom);
      break;
    case 2:
      paramNumImageView1.setImageResource(R.drawable.gpxzoom);
      paramNumImageView2.setImageResource(R.drawable.gpxzoom);
      paramNumImageView3.setImageResource(R.drawable.gpxzoom);
      paramNumImageView4.setImageResource(R.drawable.gpxzoom);
      paramNumImageView5.setImageResource(R.drawable.gpxzoom);
      break;
    case 1:
      paramNumImageView1.setImageResource(R.drawable.gpbzoom);
      paramNumImageView2.setImageResource(R.drawable.gpbzoom);
      paramNumImageView3.setImageResource(R.drawable.gpbzoom);
      paramNumImageView4.setImageResource(R.drawable.gpbzoom);
      paramNumImageView5.setImageResource(R.drawable.gpbzoom);
      break;
    case 0:
      paramNumImageView1.setImageResource(R.drawable.gpazoom);
      paramNumImageView2.setImageResource(R.drawable.gpazoom);
      paramNumImageView3.setImageResource(R.drawable.gpazoom);
      paramNumImageView4.setImageResource(R.drawable.gpazoom);
      paramNumImageView5.setImageResource(R.drawable.gpazoom);
    }
    if (paramInt2 == 0)
      return paramNumImageView1;
    if (paramInt2 == 1)
      return paramNumImageView2;
    if (paramInt2 == 2)
      return paramNumImageView3;
    if (paramInt2 == 3)
      return paramNumImageView4;
    if (paramInt2 == 4)
      return paramNumImageView5;
    return paramNumImageView1;
  }

  public NumImageView updateImagePic(NumImageView paramNumImageView1, NumImageView paramNumImageView2, NumImageView paramNumImageView3, NumImageView paramNumImageView4, NumImageView paramNumImageView5, Context paramContext, int paramInt1, int paramInt2)
  {
    switch (paramInt1)
    {
    default:
      break;
    case 961:
    case 962:
    case 963:
    case 964:
    case 965:
      paramNumImageView1.setImageResource(R.drawable.newkey);
      paramNumImageView2.setImageResource(R.drawable.newkey);
      paramNumImageView3.setImageResource(R.drawable.newkey);
      paramNumImageView4.setImageResource(R.drawable.newkey);
      paramNumImageView5.setImageResource(R.drawable.newkey);
      break;
    case 960:
      paramNumImageView1.setImageResource(R.drawable.center3);
      paramNumImageView2.setImageResource(R.drawable.center3);
      paramNumImageView3.setImageResource(R.drawable.center3);
      paramNumImageView4.setImageResource(R.drawable.center3);
      paramNumImageView5.setImageResource(R.drawable.center3);
      break;
    case 959:
      paramNumImageView1.setImageResource(R.drawable.gpbpr2);
      paramNumImageView2.setImageResource(R.drawable.gpbpr2);
      paramNumImageView3.setImageResource(R.drawable.gpbpr2);
      paramNumImageView4.setImageResource(R.drawable.gpbpr2);
      paramNumImageView5.setImageResource(R.drawable.gpbpr2);
      break;
    case 958:
      paramNumImageView1.setImageResource(R.drawable.center2);
      paramNumImageView2.setImageResource(R.drawable.center2);
      paramNumImageView3.setImageResource(R.drawable.center2);
      paramNumImageView4.setImageResource(R.drawable.center2);
      paramNumImageView5.setImageResource(R.drawable.center2);
      break;
    case 957:
      paramNumImageView1.setImageResource(R.drawable.gpbpr1);
      paramNumImageView2.setImageResource(R.drawable.gpbpr1);
      paramNumImageView3.setImageResource(R.drawable.gpbpr1);
      paramNumImageView4.setImageResource(R.drawable.gpbpr1);
      paramNumImageView5.setImageResource(R.drawable.gpbpr1);
      break;
    case 956:
      paramNumImageView1.setImageResource(R.drawable.center1);
      paramNumImageView2.setImageResource(R.drawable.center1);
      paramNumImageView3.setImageResource(R.drawable.center1);
      paramNumImageView4.setImageResource(R.drawable.center1);
      paramNumImageView5.setImageResource(R.drawable.center1);
      break;
    case 955:
      paramNumImageView1.setImageResource(R.drawable.gpm4);
      paramNumImageView2.setImageResource(R.drawable.gpm4);
      paramNumImageView3.setImageResource(R.drawable.gpm4);
      paramNumImageView4.setImageResource(R.drawable.gpm4);
      paramNumImageView5.setImageResource(R.drawable.gpm4);
      break;
    case 954:
      paramNumImageView1.setImageResource(R.drawable.gpm3);
      paramNumImageView2.setImageResource(R.drawable.gpm3);
      paramNumImageView3.setImageResource(R.drawable.gpm3);
      paramNumImageView4.setImageResource(R.drawable.gpm3);
      paramNumImageView5.setImageResource(R.drawable.gpm3);
      break;
    case 953:
      paramNumImageView1.setImageResource(R.drawable.gpm2);
      paramNumImageView2.setImageResource(R.drawable.gpm2);
      paramNumImageView3.setImageResource(R.drawable.gpm2);
      paramNumImageView4.setImageResource(R.drawable.gpm2);
      paramNumImageView5.setImageResource(R.drawable.gpm2);
      break;
    case 952:
      paramNumImageView1.setImageResource(R.drawable.gpm1);
      paramNumImageView2.setImageResource(R.drawable.gpm1);
      paramNumImageView3.setImageResource(R.drawable.gpm1);
      paramNumImageView4.setImageResource(R.drawable.gpm1);
      paramNumImageView5.setImageResource(R.drawable.gpm1);
      break;
    case 951:
      paramNumImageView1.setImageResource(R.drawable.gpbpr);
      paramNumImageView2.setImageResource(R.drawable.gpbpr);
      paramNumImageView3.setImageResource(R.drawable.gpbpr);
      paramNumImageView4.setImageResource(R.drawable.gpbpr);
      paramNumImageView5.setImageResource(R.drawable.gpbpr);
      break;
    case 950:
      paramNumImageView1.setImageResource(R.drawable.gpbpl);
      paramNumImageView2.setImageResource(R.drawable.gpbpl);
      paramNumImageView3.setImageResource(R.drawable.gpbpl);
      paramNumImageView4.setImageResource(R.drawable.gpbpl);
      paramNumImageView5.setImageResource(R.drawable.gpbpl);
      break;
    case 949:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprt);
        paramNumImageView2.setImageResource(R.drawable.gprt);
        paramNumImageView3.setImageResource(R.drawable.gprt);
        paramNumImageView4.setImageResource(R.drawable.gprt);
        paramNumImageView5.setImageResource(R.drawable.gprt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2);
        paramNumImageView2.setImageResource(R.drawable.gpr2);
        paramNumImageView3.setImageResource(R.drawable.gpr2);
        paramNumImageView4.setImageResource(R.drawable.gpr2);
        paramNumImageView5.setImageResource(R.drawable.gpr2);
      }
      break;
    case 948:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplt);
        paramNumImageView2.setImageResource(R.drawable.gplt);
        paramNumImageView3.setImageResource(R.drawable.gplt);
        paramNumImageView4.setImageResource(R.drawable.gplt);
        paramNumImageView5.setImageResource(R.drawable.gplt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2);
        paramNumImageView2.setImageResource(R.drawable.gpl2);
        paramNumImageView3.setImageResource(R.drawable.gpl2);
        paramNumImageView4.setImageResource(R.drawable.gpl2);
        paramNumImageView5.setImageResource(R.drawable.gpl2);
      }
      break;
    case 947:
      paramNumImageView1.setImageResource(R.drawable.gpr3);
      paramNumImageView2.setImageResource(R.drawable.gpr3);
      paramNumImageView3.setImageResource(R.drawable.gpr3);
      paramNumImageView4.setImageResource(R.drawable.gpr3);
      paramNumImageView5.setImageResource(R.drawable.gpr3);
      break;
    case 946:
      paramNumImageView1.setImageResource(R.drawable.gpl3);
      paramNumImageView2.setImageResource(R.drawable.gpl3);
      paramNumImageView3.setImageResource(R.drawable.gpl3);
      paramNumImageView4.setImageResource(R.drawable.gpl3);
      paramNumImageView5.setImageResource(R.drawable.gpl3);
      break;
    case 945:
      paramNumImageView1.setImageResource(R.drawable.gpselect);
      paramNumImageView2.setImageResource(R.drawable.gpselect);
      paramNumImageView3.setImageResource(R.drawable.gpselect);
      paramNumImageView4.setImageResource(R.drawable.gpselect);
      paramNumImageView5.setImageResource(R.drawable.gpselect);
      break;
    case 944:
      paramNumImageView1.setImageResource(R.drawable.gpstart);
      paramNumImageView2.setImageResource(R.drawable.gpstart);
      paramNumImageView3.setImageResource(R.drawable.gpstart);
      paramNumImageView4.setImageResource(R.drawable.gpstart);
      paramNumImageView5.setImageResource(R.drawable.gpstart);
      break;
    case 943:
      paramNumImageView1.setImageResource(R.drawable.gpright);
      paramNumImageView2.setImageResource(R.drawable.gpright);
      paramNumImageView3.setImageResource(R.drawable.gpright);
      paramNumImageView4.setImageResource(R.drawable.gpright);
      paramNumImageView5.setImageResource(R.drawable.gpright);
      break;
    case 942:
      paramNumImageView1.setImageResource(R.drawable.gpleft);
      paramNumImageView2.setImageResource(R.drawable.gpleft);
      paramNumImageView3.setImageResource(R.drawable.gpleft);
      paramNumImageView4.setImageResource(R.drawable.gpleft);
      paramNumImageView5.setImageResource(R.drawable.gpleft);
      break;
    case 941:
      paramNumImageView1.setImageResource(R.drawable.gpdown);
      paramNumImageView2.setImageResource(R.drawable.gpdown);
      paramNumImageView3.setImageResource(R.drawable.gpdown);
      paramNumImageView4.setImageResource(R.drawable.gpdown);
      paramNumImageView5.setImageResource(R.drawable.gpdown);
      break;
    case 940:
      paramNumImageView1.setImageResource(R.drawable.gpup);
      paramNumImageView2.setImageResource(R.drawable.gpup);
      paramNumImageView3.setImageResource(R.drawable.gpup);
      paramNumImageView4.setImageResource(R.drawable.gpup);
      paramNumImageView5.setImageResource(R.drawable.gpup);
      break;
    case 939:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprb);
        paramNumImageView2.setImageResource(R.drawable.gprb);
        paramNumImageView3.setImageResource(R.drawable.gprb);
        paramNumImageView4.setImageResource(R.drawable.gprb);
        paramNumImageView5.setImageResource(R.drawable.gprb);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1);
        paramNumImageView2.setImageResource(R.drawable.gpr1);
        paramNumImageView3.setImageResource(R.drawable.gpr1);
        paramNumImageView4.setImageResource(R.drawable.gpr1);
        paramNumImageView5.setImageResource(R.drawable.gpr1);
      }
      break;
    case 938:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplb);
        paramNumImageView2.setImageResource(R.drawable.gplb);
        paramNumImageView3.setImageResource(R.drawable.gplb);
        paramNumImageView4.setImageResource(R.drawable.gplb);
        paramNumImageView5.setImageResource(R.drawable.gplb);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1);
        paramNumImageView2.setImageResource(R.drawable.gpl1);
        paramNumImageView3.setImageResource(R.drawable.gpl1);
        paramNumImageView4.setImageResource(R.drawable.gpl1);
        paramNumImageView5.setImageResource(R.drawable.gpl1);
      }
      break;
    case 937:
      paramNumImageView1.setImageResource(R.drawable.gpy);
      paramNumImageView2.setImageResource(R.drawable.gpy);
      paramNumImageView3.setImageResource(R.drawable.gpy);
      paramNumImageView4.setImageResource(R.drawable.gpy);
      paramNumImageView5.setImageResource(R.drawable.gpy);
      break;
    case 936:
      paramNumImageView1.setImageResource(R.drawable.gpx);
      paramNumImageView2.setImageResource(R.drawable.gpx);
      paramNumImageView3.setImageResource(R.drawable.gpx);
      paramNumImageView4.setImageResource(R.drawable.gpx);
      paramNumImageView5.setImageResource(R.drawable.gpx);
      break;
    case 935:
      paramNumImageView1.setImageResource(R.drawable.gpb);
      paramNumImageView2.setImageResource(R.drawable.gpb);
      paramNumImageView3.setImageResource(R.drawable.gpb);
      paramNumImageView4.setImageResource(R.drawable.gpb);
      paramNumImageView5.setImageResource(R.drawable.gpb);
      break;
    case 934:
      paramNumImageView1.setImageResource(R.drawable.gpa);
      paramNumImageView2.setImageResource(R.drawable.gpa);
      paramNumImageView3.setImageResource(R.drawable.gpa);
      paramNumImageView4.setImageResource(R.drawable.gpa);
      paramNumImageView5.setImageResource(R.drawable.gpa);
      break;
    case 929:
    case 930:
    case 931:
    case 932:
    case 933:
      paramNumImageView1.setImageResource(R.drawable.newkey);
      paramNumImageView2.setImageResource(R.drawable.newkey);
      paramNumImageView3.setImageResource(R.drawable.newkey);
      paramNumImageView4.setImageResource(R.drawable.newkey);
      paramNumImageView5.setImageResource(R.drawable.newkey);
      break;
    case 928:
      paramNumImageView1.setImageResource(R.drawable.center3);
      paramNumImageView2.setImageResource(R.drawable.center3);
      paramNumImageView3.setImageResource(R.drawable.center3);
      paramNumImageView4.setImageResource(R.drawable.center3);
      paramNumImageView5.setImageResource(R.drawable.center3);
      break;
    case 927:
      paramNumImageView1.setImageResource(R.drawable.gpbpr2);
      paramNumImageView2.setImageResource(R.drawable.gpbpr2);
      paramNumImageView3.setImageResource(R.drawable.gpbpr2);
      paramNumImageView4.setImageResource(R.drawable.gpbpr2);
      paramNumImageView5.setImageResource(R.drawable.gpbpr2);
      break;
    case 926:
      paramNumImageView1.setImageResource(R.drawable.center2);
      paramNumImageView2.setImageResource(R.drawable.center2);
      paramNumImageView3.setImageResource(R.drawable.center2);
      paramNumImageView4.setImageResource(R.drawable.center2);
      paramNumImageView5.setImageResource(R.drawable.center2);
      break;
    case 925:
      paramNumImageView1.setImageResource(R.drawable.gpbpr1);
      paramNumImageView2.setImageResource(R.drawable.gpbpr1);
      paramNumImageView3.setImageResource(R.drawable.gpbpr1);
      paramNumImageView4.setImageResource(R.drawable.gpbpr1);
      paramNumImageView5.setImageResource(R.drawable.gpbpr1);
      break;
    case 924:
      paramNumImageView1.setImageResource(R.drawable.center1);
      paramNumImageView2.setImageResource(R.drawable.center1);
      paramNumImageView3.setImageResource(R.drawable.center1);
      paramNumImageView4.setImageResource(R.drawable.center1);
      paramNumImageView5.setImageResource(R.drawable.center1);
      break;
    case 923:
      paramNumImageView1.setImageResource(R.drawable.gpm4);
      paramNumImageView2.setImageResource(R.drawable.gpm4);
      paramNumImageView3.setImageResource(R.drawable.gpm4);
      paramNumImageView4.setImageResource(R.drawable.gpm4);
      paramNumImageView5.setImageResource(R.drawable.gpm4);
      break;
    case 922:
      paramNumImageView1.setImageResource(R.drawable.gpm3);
      paramNumImageView2.setImageResource(R.drawable.gpm3);
      paramNumImageView3.setImageResource(R.drawable.gpm3);
      paramNumImageView4.setImageResource(R.drawable.gpm3);
      paramNumImageView5.setImageResource(R.drawable.gpm3);
      break;
    case 921:
      paramNumImageView1.setImageResource(R.drawable.gpm2);
      paramNumImageView2.setImageResource(R.drawable.gpm2);
      paramNumImageView3.setImageResource(R.drawable.gpm2);
      paramNumImageView4.setImageResource(R.drawable.gpm2);
      paramNumImageView5.setImageResource(R.drawable.gpm2);
      break;
    case 920:
      paramNumImageView1.setImageResource(R.drawable.gpm1);
      paramNumImageView2.setImageResource(R.drawable.gpm1);
      paramNumImageView3.setImageResource(R.drawable.gpm1);
      paramNumImageView4.setImageResource(R.drawable.gpm1);
      paramNumImageView5.setImageResource(R.drawable.gpm1);
      break;
    case 919:
      paramNumImageView1.setImageResource(R.drawable.gpbpr);
      paramNumImageView2.setImageResource(R.drawable.gpbpr);
      paramNumImageView3.setImageResource(R.drawable.gpbpr);
      paramNumImageView4.setImageResource(R.drawable.gpbpr);
      paramNumImageView5.setImageResource(R.drawable.gpbpr);
      break;
    case 918:
      paramNumImageView1.setImageResource(R.drawable.gpbpl);
      paramNumImageView2.setImageResource(R.drawable.gpbpl);
      paramNumImageView3.setImageResource(R.drawable.gpbpl);
      paramNumImageView4.setImageResource(R.drawable.gpbpl);
      paramNumImageView5.setImageResource(R.drawable.gpbpl);
      break;
    case 917:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprt);
        paramNumImageView2.setImageResource(R.drawable.gprt);
        paramNumImageView3.setImageResource(R.drawable.gprt);
        paramNumImageView4.setImageResource(R.drawable.gprt);
        paramNumImageView5.setImageResource(R.drawable.gprt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2);
        paramNumImageView2.setImageResource(R.drawable.gpr2);
        paramNumImageView3.setImageResource(R.drawable.gpr2);
        paramNumImageView4.setImageResource(R.drawable.gpr2);
        paramNumImageView5.setImageResource(R.drawable.gpr2);
      }
      break;
    case 916:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplt);
        paramNumImageView2.setImageResource(R.drawable.gplt);
        paramNumImageView3.setImageResource(R.drawable.gplt);
        paramNumImageView4.setImageResource(R.drawable.gplt);
        paramNumImageView5.setImageResource(R.drawable.gplt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2);
        paramNumImageView2.setImageResource(R.drawable.gpl2);
        paramNumImageView3.setImageResource(R.drawable.gpl2);
        paramNumImageView4.setImageResource(R.drawable.gpl2);
        paramNumImageView5.setImageResource(R.drawable.gpl2);
      }
      break;
    case 915:
      paramNumImageView1.setImageResource(R.drawable.gpr3);
      paramNumImageView2.setImageResource(R.drawable.gpr3);
      paramNumImageView3.setImageResource(R.drawable.gpr3);
      paramNumImageView4.setImageResource(R.drawable.gpr3);
      paramNumImageView5.setImageResource(R.drawable.gpr3);
      break;
    case 914:
      paramNumImageView1.setImageResource(R.drawable.gpl3);
      paramNumImageView2.setImageResource(R.drawable.gpl3);
      paramNumImageView3.setImageResource(R.drawable.gpl3);
      paramNumImageView4.setImageResource(R.drawable.gpl3);
      paramNumImageView5.setImageResource(R.drawable.gpl3);
      break;
    case 913:
      paramNumImageView1.setImageResource(R.drawable.gpselect);
      paramNumImageView2.setImageResource(R.drawable.gpselect);
      paramNumImageView3.setImageResource(R.drawable.gpselect);
      paramNumImageView4.setImageResource(R.drawable.gpselect);
      paramNumImageView5.setImageResource(R.drawable.gpselect);
      break;
    case 912:
      paramNumImageView1.setImageResource(R.drawable.gpstart);
      paramNumImageView2.setImageResource(R.drawable.gpstart);
      paramNumImageView3.setImageResource(R.drawable.gpstart);
      paramNumImageView4.setImageResource(R.drawable.gpstart);
      paramNumImageView5.setImageResource(R.drawable.gpstart);
      break;
    case 911:
      paramNumImageView1.setImageResource(R.drawable.gpright);
      paramNumImageView2.setImageResource(R.drawable.gpright);
      paramNumImageView3.setImageResource(R.drawable.gpright);
      paramNumImageView4.setImageResource(R.drawable.gpright);
      paramNumImageView5.setImageResource(R.drawable.gpright);
      break;
    case 910:
      paramNumImageView1.setImageResource(R.drawable.gpleft);
      paramNumImageView2.setImageResource(R.drawable.gpleft);
      paramNumImageView3.setImageResource(R.drawable.gpleft);
      paramNumImageView4.setImageResource(R.drawable.gpleft);
      paramNumImageView5.setImageResource(R.drawable.gpleft);
      break;
    case 909:
      paramNumImageView1.setImageResource(R.drawable.gpdown);
      paramNumImageView2.setImageResource(R.drawable.gpdown);
      paramNumImageView3.setImageResource(R.drawable.gpdown);
      paramNumImageView4.setImageResource(R.drawable.gpdown);
      paramNumImageView5.setImageResource(R.drawable.gpdown);
      break;
    case 908:
      paramNumImageView1.setImageResource(R.drawable.gpup);
      paramNumImageView2.setImageResource(R.drawable.gpup);
      paramNumImageView3.setImageResource(R.drawable.gpup);
      paramNumImageView4.setImageResource(R.drawable.gpup);
      paramNumImageView5.setImageResource(R.drawable.gpup);
      break;
    case 907:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprb);
        paramNumImageView2.setImageResource(R.drawable.gprb);
        paramNumImageView3.setImageResource(R.drawable.gprb);
        paramNumImageView4.setImageResource(R.drawable.gprb);
        paramNumImageView5.setImageResource(R.drawable.gprb);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1);
        paramNumImageView2.setImageResource(R.drawable.gpr1);
        paramNumImageView3.setImageResource(R.drawable.gpr1);
        paramNumImageView4.setImageResource(R.drawable.gpr1);
        paramNumImageView5.setImageResource(R.drawable.gpr1);
      }
      break;
    case 906:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplb);
        paramNumImageView2.setImageResource(R.drawable.gplb);
        paramNumImageView3.setImageResource(R.drawable.gplb);
        paramNumImageView4.setImageResource(R.drawable.gplb);
        paramNumImageView5.setImageResource(R.drawable.gplb);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1);
        paramNumImageView2.setImageResource(R.drawable.gpl1);
        paramNumImageView3.setImageResource(R.drawable.gpl1);
        paramNumImageView4.setImageResource(R.drawable.gpl1);
        paramNumImageView5.setImageResource(R.drawable.gpl1);
      }
      break;
    case 905:
      paramNumImageView1.setImageResource(R.drawable.gpy);
      paramNumImageView2.setImageResource(R.drawable.gpy);
      paramNumImageView3.setImageResource(R.drawable.gpy);
      paramNumImageView4.setImageResource(R.drawable.gpy);
      paramNumImageView5.setImageResource(R.drawable.gpy);
      break;
    case 904:
      paramNumImageView1.setImageResource(R.drawable.gpx);
      paramNumImageView2.setImageResource(R.drawable.gpx);
      paramNumImageView3.setImageResource(R.drawable.gpx);
      paramNumImageView4.setImageResource(R.drawable.gpx);
      paramNumImageView5.setImageResource(R.drawable.gpx);
      break;
    case 903:
      paramNumImageView1.setImageResource(R.drawable.gpb);
      paramNumImageView2.setImageResource(R.drawable.gpb);
      paramNumImageView3.setImageResource(R.drawable.gpb);
      paramNumImageView4.setImageResource(R.drawable.gpb);
      paramNumImageView5.setImageResource(R.drawable.gpb);
      break;
    case 902:
      paramNumImageView1.setImageResource(R.drawable.gpa);
      paramNumImageView2.setImageResource(R.drawable.gpa);
      paramNumImageView3.setImageResource(R.drawable.gpa);
      paramNumImageView4.setImageResource(R.drawable.gpa);
      paramNumImageView5.setImageResource(R.drawable.gpa);
      break;
    case 897:
    case 898:
    case 899:
    case 900:
    case 901:
      paramNumImageView1.setImageResource(R.drawable.newkey);
      paramNumImageView2.setImageResource(R.drawable.newkey);
      paramNumImageView3.setImageResource(R.drawable.newkey);
      paramNumImageView4.setImageResource(R.drawable.newkey);
      paramNumImageView5.setImageResource(R.drawable.newkey);
      break;
    case 896:
      paramNumImageView1.setImageResource(R.drawable.center3);
      paramNumImageView2.setImageResource(R.drawable.center3);
      paramNumImageView3.setImageResource(R.drawable.center3);
      paramNumImageView4.setImageResource(R.drawable.center3);
      paramNumImageView5.setImageResource(R.drawable.center3);
      break;
    case 895:
      paramNumImageView1.setImageResource(R.drawable.gpbpr2);
      paramNumImageView2.setImageResource(R.drawable.gpbpr2);
      paramNumImageView3.setImageResource(R.drawable.gpbpr2);
      paramNumImageView4.setImageResource(R.drawable.gpbpr2);
      paramNumImageView5.setImageResource(R.drawable.gpbpr2);
      break;
    case 894:
      paramNumImageView1.setImageResource(R.drawable.center2);
      paramNumImageView2.setImageResource(R.drawable.center2);
      paramNumImageView3.setImageResource(R.drawable.center2);
      paramNumImageView4.setImageResource(R.drawable.center2);
      paramNumImageView5.setImageResource(R.drawable.center2);
      break;
    case 893:
      paramNumImageView1.setImageResource(R.drawable.gpbpr1);
      paramNumImageView2.setImageResource(R.drawable.gpbpr1);
      paramNumImageView3.setImageResource(R.drawable.gpbpr1);
      paramNumImageView4.setImageResource(R.drawable.gpbpr1);
      paramNumImageView5.setImageResource(R.drawable.gpbpr1);
      break;
    case 892:
      paramNumImageView1.setImageResource(R.drawable.center1);
      paramNumImageView2.setImageResource(R.drawable.center1);
      paramNumImageView3.setImageResource(R.drawable.center1);
      paramNumImageView4.setImageResource(R.drawable.center1);
      paramNumImageView5.setImageResource(R.drawable.center1);
      break;
    case 891:
      paramNumImageView1.setImageResource(R.drawable.gpm4);
      paramNumImageView2.setImageResource(R.drawable.gpm4);
      paramNumImageView3.setImageResource(R.drawable.gpm4);
      paramNumImageView4.setImageResource(R.drawable.gpm4);
      paramNumImageView5.setImageResource(R.drawable.gpm4);
      break;
    case 890:
      paramNumImageView1.setImageResource(R.drawable.gpm3);
      paramNumImageView2.setImageResource(R.drawable.gpm3);
      paramNumImageView3.setImageResource(R.drawable.gpm3);
      paramNumImageView4.setImageResource(R.drawable.gpm3);
      paramNumImageView5.setImageResource(R.drawable.gpm3);
      break;
    case 889:
      paramNumImageView1.setImageResource(R.drawable.gpm2);
      paramNumImageView2.setImageResource(R.drawable.gpm2);
      paramNumImageView3.setImageResource(R.drawable.gpm2);
      paramNumImageView4.setImageResource(R.drawable.gpm2);
      paramNumImageView5.setImageResource(R.drawable.gpm2);
      break;
    case 888:
      paramNumImageView1.setImageResource(R.drawable.gpm1);
      paramNumImageView2.setImageResource(R.drawable.gpm1);
      paramNumImageView3.setImageResource(R.drawable.gpm1);
      paramNumImageView4.setImageResource(R.drawable.gpm1);
      paramNumImageView5.setImageResource(R.drawable.gpm1);
      break;
    case 887:
      paramNumImageView1.setImageResource(R.drawable.gpbpr);
      paramNumImageView2.setImageResource(R.drawable.gpbpr);
      paramNumImageView3.setImageResource(R.drawable.gpbpr);
      paramNumImageView4.setImageResource(R.drawable.gpbpr);
      paramNumImageView5.setImageResource(R.drawable.gpbpr);
      break;
    case 886:
      paramNumImageView1.setImageResource(R.drawable.gpbpl);
      paramNumImageView2.setImageResource(R.drawable.gpbpl);
      paramNumImageView3.setImageResource(R.drawable.gpbpl);
      paramNumImageView4.setImageResource(R.drawable.gpbpl);
      paramNumImageView5.setImageResource(R.drawable.gpbpl);
      break;
    case 885:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprt);
        paramNumImageView2.setImageResource(R.drawable.gprt);
        paramNumImageView3.setImageResource(R.drawable.gprt);
        paramNumImageView4.setImageResource(R.drawable.gprt);
        paramNumImageView5.setImageResource(R.drawable.gprt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2);
        paramNumImageView2.setImageResource(R.drawable.gpr2);
        paramNumImageView3.setImageResource(R.drawable.gpr2);
        paramNumImageView4.setImageResource(R.drawable.gpr2);
        paramNumImageView5.setImageResource(R.drawable.gpr2);
      }
      break;
    case 884:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplt);
        paramNumImageView2.setImageResource(R.drawable.gplt);
        paramNumImageView3.setImageResource(R.drawable.gplt);
        paramNumImageView4.setImageResource(R.drawable.gplt);
        paramNumImageView5.setImageResource(R.drawable.gplt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2);
        paramNumImageView2.setImageResource(R.drawable.gpl2);
        paramNumImageView3.setImageResource(R.drawable.gpl2);
        paramNumImageView4.setImageResource(R.drawable.gpl2);
        paramNumImageView5.setImageResource(R.drawable.gpl2);
      }
      break;
    case 883:
      paramNumImageView1.setImageResource(R.drawable.gpr3);
      paramNumImageView2.setImageResource(R.drawable.gpr3);
      paramNumImageView3.setImageResource(R.drawable.gpr3);
      paramNumImageView4.setImageResource(R.drawable.gpr3);
      paramNumImageView5.setImageResource(R.drawable.gpr3);
      break;
    case 882:
      paramNumImageView1.setImageResource(R.drawable.gpl3);
      paramNumImageView2.setImageResource(R.drawable.gpl3);
      paramNumImageView3.setImageResource(R.drawable.gpl3);
      paramNumImageView4.setImageResource(R.drawable.gpl3);
      paramNumImageView5.setImageResource(R.drawable.gpl3);
      break;
    case 881:
      paramNumImageView1.setImageResource(R.drawable.gpselect);
      paramNumImageView2.setImageResource(R.drawable.gpselect);
      paramNumImageView3.setImageResource(R.drawable.gpselect);
      paramNumImageView4.setImageResource(R.drawable.gpselect);
      paramNumImageView5.setImageResource(R.drawable.gpselect);
      break;
    case 880:
      paramNumImageView1.setImageResource(R.drawable.gpstart);
      paramNumImageView2.setImageResource(R.drawable.gpstart);
      paramNumImageView3.setImageResource(R.drawable.gpstart);
      paramNumImageView4.setImageResource(R.drawable.gpstart);
      paramNumImageView5.setImageResource(R.drawable.gpstart);
      break;
    case 879:
      paramNumImageView1.setImageResource(R.drawable.gpright);
      paramNumImageView2.setImageResource(R.drawable.gpright);
      paramNumImageView3.setImageResource(R.drawable.gpright);
      paramNumImageView4.setImageResource(R.drawable.gpright);
      paramNumImageView5.setImageResource(R.drawable.gpright);
      break;
    case 878:
      paramNumImageView1.setImageResource(R.drawable.gpleft);
      paramNumImageView2.setImageResource(R.drawable.gpleft);
      paramNumImageView3.setImageResource(R.drawable.gpleft);
      paramNumImageView4.setImageResource(R.drawable.gpleft);
      paramNumImageView5.setImageResource(R.drawable.gpleft);
      break;
    case 877:
      paramNumImageView1.setImageResource(R.drawable.gpdown);
      paramNumImageView2.setImageResource(R.drawable.gpdown);
      paramNumImageView3.setImageResource(R.drawable.gpdown);
      paramNumImageView4.setImageResource(R.drawable.gpdown);
      paramNumImageView5.setImageResource(R.drawable.gpdown);
      break;
    case 876:
      paramNumImageView1.setImageResource(R.drawable.gpup);
      paramNumImageView2.setImageResource(R.drawable.gpup);
      paramNumImageView3.setImageResource(R.drawable.gpup);
      paramNumImageView4.setImageResource(R.drawable.gpup);
      paramNumImageView5.setImageResource(R.drawable.gpup);
      break;
    case 875:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprb);
        paramNumImageView2.setImageResource(R.drawable.gprb);
        paramNumImageView3.setImageResource(R.drawable.gprb);
        paramNumImageView4.setImageResource(R.drawable.gprb);
        paramNumImageView5.setImageResource(R.drawable.gprb);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1);
        paramNumImageView2.setImageResource(R.drawable.gpr1);
        paramNumImageView3.setImageResource(R.drawable.gpr1);
        paramNumImageView4.setImageResource(R.drawable.gpr1);
        paramNumImageView5.setImageResource(R.drawable.gpr1);
      }
      break;
    case 874:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplb);
        paramNumImageView2.setImageResource(R.drawable.gplb);
        paramNumImageView3.setImageResource(R.drawable.gplb);
        paramNumImageView4.setImageResource(R.drawable.gplb);
        paramNumImageView5.setImageResource(R.drawable.gplb);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1);
        paramNumImageView2.setImageResource(R.drawable.gpl1);
        paramNumImageView3.setImageResource(R.drawable.gpl1);
        paramNumImageView4.setImageResource(R.drawable.gpl1);
        paramNumImageView5.setImageResource(R.drawable.gpl1);
      }
      break;
    case 873:
      paramNumImageView1.setImageResource(R.drawable.gpy);
      paramNumImageView2.setImageResource(R.drawable.gpy);
      paramNumImageView3.setImageResource(R.drawable.gpy);
      paramNumImageView4.setImageResource(R.drawable.gpy);
      paramNumImageView5.setImageResource(R.drawable.gpy);
      break;
    case 872:
      paramNumImageView1.setImageResource(R.drawable.gpx);
      paramNumImageView2.setImageResource(R.drawable.gpx);
      paramNumImageView3.setImageResource(R.drawable.gpx);
      paramNumImageView4.setImageResource(R.drawable.gpx);
      paramNumImageView5.setImageResource(R.drawable.gpx);
      break;
    case 871:
      paramNumImageView1.setImageResource(R.drawable.gpb);
      paramNumImageView2.setImageResource(R.drawable.gpb);
      paramNumImageView3.setImageResource(R.drawable.gpb);
      paramNumImageView4.setImageResource(R.drawable.gpb);
      paramNumImageView5.setImageResource(R.drawable.gpb);
      break;
    case 870:
      paramNumImageView1.setImageResource(R.drawable.gpa);
      paramNumImageView2.setImageResource(R.drawable.gpa);
      paramNumImageView3.setImageResource(R.drawable.gpa);
      paramNumImageView4.setImageResource(R.drawable.gpa);
      paramNumImageView5.setImageResource(R.drawable.gpa);
      break;
    case 865:
    case 866:
    case 867:
    case 868:
    case 869:
      paramNumImageView1.setImageResource(R.drawable.newkey);
      paramNumImageView2.setImageResource(R.drawable.newkey);
      paramNumImageView3.setImageResource(R.drawable.newkey);
      paramNumImageView4.setImageResource(R.drawable.newkey);
      paramNumImageView5.setImageResource(R.drawable.newkey);
      break;
    case 864:
      paramNumImageView1.setImageResource(R.drawable.center3);
      paramNumImageView2.setImageResource(R.drawable.center3);
      paramNumImageView3.setImageResource(R.drawable.center3);
      paramNumImageView4.setImageResource(R.drawable.center3);
      paramNumImageView5.setImageResource(R.drawable.center3);
      break;
    case 863:
      paramNumImageView1.setImageResource(R.drawable.gpbpr2);
      paramNumImageView2.setImageResource(R.drawable.gpbpr2);
      paramNumImageView3.setImageResource(R.drawable.gpbpr2);
      paramNumImageView4.setImageResource(R.drawable.gpbpr2);
      paramNumImageView5.setImageResource(R.drawable.gpbpr2);
      break;
    case 862:
      paramNumImageView1.setImageResource(R.drawable.center2);
      paramNumImageView2.setImageResource(R.drawable.center2);
      paramNumImageView3.setImageResource(R.drawable.center2);
      paramNumImageView4.setImageResource(R.drawable.center2);
      paramNumImageView5.setImageResource(R.drawable.center2);
      break;
    case 861:
      paramNumImageView1.setImageResource(R.drawable.gpbpr1);
      paramNumImageView2.setImageResource(R.drawable.gpbpr1);
      paramNumImageView3.setImageResource(R.drawable.gpbpr1);
      paramNumImageView4.setImageResource(R.drawable.gpbpr1);
      paramNumImageView5.setImageResource(R.drawable.gpbpr1);
      break;
    case 860:
      paramNumImageView1.setImageResource(R.drawable.center1);
      paramNumImageView2.setImageResource(R.drawable.center1);
      paramNumImageView3.setImageResource(R.drawable.center1);
      paramNumImageView4.setImageResource(R.drawable.center1);
      paramNumImageView5.setImageResource(R.drawable.center1);
      break;
    case 859:
      paramNumImageView1.setImageResource(R.drawable.gpm4);
      paramNumImageView2.setImageResource(R.drawable.gpm4);
      paramNumImageView3.setImageResource(R.drawable.gpm4);
      paramNumImageView4.setImageResource(R.drawable.gpm4);
      paramNumImageView5.setImageResource(R.drawable.gpm4);
      break;
    case 858:
      paramNumImageView1.setImageResource(R.drawable.gpm3);
      paramNumImageView2.setImageResource(R.drawable.gpm3);
      paramNumImageView3.setImageResource(R.drawable.gpm3);
      paramNumImageView4.setImageResource(R.drawable.gpm3);
      paramNumImageView5.setImageResource(R.drawable.gpm3);
      break;
    case 857:
      paramNumImageView1.setImageResource(R.drawable.gpm2);
      paramNumImageView2.setImageResource(R.drawable.gpm2);
      paramNumImageView3.setImageResource(R.drawable.gpm2);
      paramNumImageView4.setImageResource(R.drawable.gpm2);
      paramNumImageView5.setImageResource(R.drawable.gpm2);
      break;
    case 856:
      paramNumImageView1.setImageResource(R.drawable.gpm1);
      paramNumImageView2.setImageResource(R.drawable.gpm1);
      paramNumImageView3.setImageResource(R.drawable.gpm1);
      paramNumImageView4.setImageResource(R.drawable.gpm1);
      paramNumImageView5.setImageResource(R.drawable.gpm1);
      break;
    case 855:
      paramNumImageView1.setImageResource(R.drawable.gpbpr);
      paramNumImageView2.setImageResource(R.drawable.gpbpr);
      paramNumImageView3.setImageResource(R.drawable.gpbpr);
      paramNumImageView4.setImageResource(R.drawable.gpbpr);
      paramNumImageView5.setImageResource(R.drawable.gpbpr);
      break;
    case 854:
      paramNumImageView1.setImageResource(R.drawable.gpbpl);
      paramNumImageView2.setImageResource(R.drawable.gpbpl);
      paramNumImageView3.setImageResource(R.drawable.gpbpl);
      paramNumImageView4.setImageResource(R.drawable.gpbpl);
      paramNumImageView5.setImageResource(R.drawable.gpbpl);
      break;
    case 853:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprt);
        paramNumImageView2.setImageResource(R.drawable.gprt);
        paramNumImageView3.setImageResource(R.drawable.gprt);
        paramNumImageView4.setImageResource(R.drawable.gprt);
        paramNumImageView5.setImageResource(R.drawable.gprt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2);
        paramNumImageView2.setImageResource(R.drawable.gpr2);
        paramNumImageView3.setImageResource(R.drawable.gpr2);
        paramNumImageView4.setImageResource(R.drawable.gpr2);
        paramNumImageView5.setImageResource(R.drawable.gpr2);
      }
      break;
    case 852:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplt);
        paramNumImageView2.setImageResource(R.drawable.gplt);
        paramNumImageView3.setImageResource(R.drawable.gplt);
        paramNumImageView4.setImageResource(R.drawable.gplt);
        paramNumImageView5.setImageResource(R.drawable.gplt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2);
        paramNumImageView2.setImageResource(R.drawable.gpl2);
        paramNumImageView3.setImageResource(R.drawable.gpl2);
        paramNumImageView4.setImageResource(R.drawable.gpl2);
        paramNumImageView5.setImageResource(R.drawable.gpl2);
      }
      break;
    case 851:
      paramNumImageView1.setImageResource(R.drawable.gpr3);
      paramNumImageView2.setImageResource(R.drawable.gpr3);
      paramNumImageView3.setImageResource(R.drawable.gpr3);
      paramNumImageView4.setImageResource(R.drawable.gpr3);
      paramNumImageView5.setImageResource(R.drawable.gpr3);
      break;
    case 850:
      paramNumImageView1.setImageResource(R.drawable.gpl3);
      paramNumImageView2.setImageResource(R.drawable.gpl3);
      paramNumImageView3.setImageResource(R.drawable.gpl3);
      paramNumImageView4.setImageResource(R.drawable.gpl3);
      paramNumImageView5.setImageResource(R.drawable.gpl3);
      break;
    case 849:
      paramNumImageView1.setImageResource(R.drawable.gpselect);
      paramNumImageView2.setImageResource(R.drawable.gpselect);
      paramNumImageView3.setImageResource(R.drawable.gpselect);
      paramNumImageView4.setImageResource(R.drawable.gpselect);
      paramNumImageView5.setImageResource(R.drawable.gpselect);
      break;
    case 848:
      paramNumImageView1.setImageResource(R.drawable.gpstart);
      paramNumImageView2.setImageResource(R.drawable.gpstart);
      paramNumImageView3.setImageResource(R.drawable.gpstart);
      paramNumImageView4.setImageResource(R.drawable.gpstart);
      paramNumImageView5.setImageResource(R.drawable.gpstart);
      break;
    case 847:
      paramNumImageView1.setImageResource(R.drawable.gpright);
      paramNumImageView2.setImageResource(R.drawable.gpright);
      paramNumImageView3.setImageResource(R.drawable.gpright);
      paramNumImageView4.setImageResource(R.drawable.gpright);
      paramNumImageView5.setImageResource(R.drawable.gpright);
      break;
    case 846:
      paramNumImageView1.setImageResource(R.drawable.gpleft);
      paramNumImageView2.setImageResource(R.drawable.gpleft);
      paramNumImageView3.setImageResource(R.drawable.gpleft);
      paramNumImageView4.setImageResource(R.drawable.gpleft);
      paramNumImageView5.setImageResource(R.drawable.gpleft);
      break;
    case 845:
      paramNumImageView1.setImageResource(R.drawable.gpdown);
      paramNumImageView2.setImageResource(R.drawable.gpdown);
      paramNumImageView3.setImageResource(R.drawable.gpdown);
      paramNumImageView4.setImageResource(R.drawable.gpdown);
      paramNumImageView5.setImageResource(R.drawable.gpdown);
      break;
    case 844:
      paramNumImageView1.setImageResource(R.drawable.gpup);
      paramNumImageView2.setImageResource(R.drawable.gpup);
      paramNumImageView3.setImageResource(R.drawable.gpup);
      paramNumImageView4.setImageResource(R.drawable.gpup);
      paramNumImageView5.setImageResource(R.drawable.gpup);
      break;
    case 843:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprb);
        paramNumImageView2.setImageResource(R.drawable.gprb);
        paramNumImageView3.setImageResource(R.drawable.gprb);
        paramNumImageView4.setImageResource(R.drawable.gprb);
        paramNumImageView5.setImageResource(R.drawable.gprb);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1);
        paramNumImageView2.setImageResource(R.drawable.gpr1);
        paramNumImageView3.setImageResource(R.drawable.gpr1);
        paramNumImageView4.setImageResource(R.drawable.gpr1);
        paramNumImageView5.setImageResource(R.drawable.gpr1);
      }
      break;
    case 842:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplb);
        paramNumImageView2.setImageResource(R.drawable.gplb);
        paramNumImageView3.setImageResource(R.drawable.gplb);
        paramNumImageView4.setImageResource(R.drawable.gplb);
        paramNumImageView5.setImageResource(R.drawable.gplb);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1);
        paramNumImageView2.setImageResource(R.drawable.gpl1);
        paramNumImageView3.setImageResource(R.drawable.gpl1);
        paramNumImageView4.setImageResource(R.drawable.gpl1);
        paramNumImageView5.setImageResource(R.drawable.gpl1);
      }
      break;
    case 841:
      paramNumImageView1.setImageResource(R.drawable.gpy);
      paramNumImageView2.setImageResource(R.drawable.gpy);
      paramNumImageView3.setImageResource(R.drawable.gpy);
      paramNumImageView4.setImageResource(R.drawable.gpy);
      paramNumImageView5.setImageResource(R.drawable.gpy);
      break;
    case 840:
      paramNumImageView1.setImageResource(R.drawable.gpx);
      paramNumImageView2.setImageResource(R.drawable.gpx);
      paramNumImageView3.setImageResource(R.drawable.gpx);
      paramNumImageView4.setImageResource(R.drawable.gpx);
      paramNumImageView5.setImageResource(R.drawable.gpx);
      break;
    case 839:
      paramNumImageView1.setImageResource(R.drawable.gpb);
      paramNumImageView2.setImageResource(R.drawable.gpb);
      paramNumImageView3.setImageResource(R.drawable.gpb);
      paramNumImageView4.setImageResource(R.drawable.gpb);
      paramNumImageView5.setImageResource(R.drawable.gpb);
      break;
    case 838:
      paramNumImageView1.setImageResource(R.drawable.gpa);
      paramNumImageView2.setImageResource(R.drawable.gpa);
      paramNumImageView3.setImageResource(R.drawable.gpa);
      paramNumImageView4.setImageResource(R.drawable.gpa);
      paramNumImageView5.setImageResource(R.drawable.gpa);
      break;
    case 833:
    case 834:
    case 835:
    case 836:
    case 837:
      paramNumImageView1.setImageResource(R.drawable.newkey);
      paramNumImageView2.setImageResource(R.drawable.newkey);
      paramNumImageView3.setImageResource(R.drawable.newkey);
      paramNumImageView4.setImageResource(R.drawable.newkey);
      paramNumImageView5.setImageResource(R.drawable.newkey);
      break;
    case 832:
      paramNumImageView1.setImageResource(R.drawable.center3);
      paramNumImageView2.setImageResource(R.drawable.center3);
      paramNumImageView3.setImageResource(R.drawable.center3);
      paramNumImageView4.setImageResource(R.drawable.center3);
      paramNumImageView5.setImageResource(R.drawable.center3);
      break;
    case 831:
      paramNumImageView1.setImageResource(R.drawable.gpbpr2);
      paramNumImageView2.setImageResource(R.drawable.gpbpr2);
      paramNumImageView3.setImageResource(R.drawable.gpbpr2);
      paramNumImageView4.setImageResource(R.drawable.gpbpr2);
      paramNumImageView5.setImageResource(R.drawable.gpbpr2);
      break;
    case 830:
      paramNumImageView1.setImageResource(R.drawable.center2);
      paramNumImageView2.setImageResource(R.drawable.center2);
      paramNumImageView3.setImageResource(R.drawable.center2);
      paramNumImageView4.setImageResource(R.drawable.center2);
      paramNumImageView5.setImageResource(R.drawable.center2);
      break;
    case 829:
      paramNumImageView1.setImageResource(R.drawable.gpbpr1);
      paramNumImageView2.setImageResource(R.drawable.gpbpr1);
      paramNumImageView3.setImageResource(R.drawable.gpbpr1);
      paramNumImageView4.setImageResource(R.drawable.gpbpr1);
      paramNumImageView5.setImageResource(R.drawable.gpbpr1);
      break;
    case 828:
      paramNumImageView1.setImageResource(R.drawable.center1);
      paramNumImageView2.setImageResource(R.drawable.center1);
      paramNumImageView3.setImageResource(R.drawable.center1);
      paramNumImageView4.setImageResource(R.drawable.center1);
      paramNumImageView5.setImageResource(R.drawable.center1);
      break;
    case 827:
      paramNumImageView1.setImageResource(R.drawable.gpm4);
      paramNumImageView2.setImageResource(R.drawable.gpm4);
      paramNumImageView3.setImageResource(R.drawable.gpm4);
      paramNumImageView4.setImageResource(R.drawable.gpm4);
      paramNumImageView5.setImageResource(R.drawable.gpm4);
      break;
    case 826:
      paramNumImageView1.setImageResource(R.drawable.gpm3);
      paramNumImageView2.setImageResource(R.drawable.gpm3);
      paramNumImageView3.setImageResource(R.drawable.gpm3);
      paramNumImageView4.setImageResource(R.drawable.gpm3);
      paramNumImageView5.setImageResource(R.drawable.gpm3);
      break;
    case 825:
      paramNumImageView1.setImageResource(R.drawable.gpm2);
      paramNumImageView2.setImageResource(R.drawable.gpm2);
      paramNumImageView3.setImageResource(R.drawable.gpm2);
      paramNumImageView4.setImageResource(R.drawable.gpm2);
      paramNumImageView5.setImageResource(R.drawable.gpm2);
      break;
    case 824:
      paramNumImageView1.setImageResource(R.drawable.gpm1);
      paramNumImageView2.setImageResource(R.drawable.gpm1);
      paramNumImageView3.setImageResource(R.drawable.gpm1);
      paramNumImageView4.setImageResource(R.drawable.gpm1);
      paramNumImageView5.setImageResource(R.drawable.gpm1);
      break;
    case 823:
      paramNumImageView1.setImageResource(R.drawable.gpbpr);
      paramNumImageView2.setImageResource(R.drawable.gpbpr);
      paramNumImageView3.setImageResource(R.drawable.gpbpr);
      paramNumImageView4.setImageResource(R.drawable.gpbpr);
      paramNumImageView5.setImageResource(R.drawable.gpbpr);
      break;
    case 822:
      paramNumImageView1.setImageResource(R.drawable.gpbpl);
      paramNumImageView2.setImageResource(R.drawable.gpbpl);
      paramNumImageView3.setImageResource(R.drawable.gpbpl);
      paramNumImageView4.setImageResource(R.drawable.gpbpl);
      paramNumImageView5.setImageResource(R.drawable.gpbpl);
      break;
    case 821:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprt);
        paramNumImageView2.setImageResource(R.drawable.gprt);
        paramNumImageView3.setImageResource(R.drawable.gprt);
        paramNumImageView4.setImageResource(R.drawable.gprt);
        paramNumImageView5.setImageResource(R.drawable.gprt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2);
        paramNumImageView2.setImageResource(R.drawable.gpr2);
        paramNumImageView3.setImageResource(R.drawable.gpr2);
        paramNumImageView4.setImageResource(R.drawable.gpr2);
        paramNumImageView5.setImageResource(R.drawable.gpr2);
      }
      break;
    case 820:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplt);
        paramNumImageView2.setImageResource(R.drawable.gplt);
        paramNumImageView3.setImageResource(R.drawable.gplt);
        paramNumImageView4.setImageResource(R.drawable.gplt);
        paramNumImageView5.setImageResource(R.drawable.gplt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2);
        paramNumImageView2.setImageResource(R.drawable.gpl2);
        paramNumImageView3.setImageResource(R.drawable.gpl2);
        paramNumImageView4.setImageResource(R.drawable.gpl2);
        paramNumImageView5.setImageResource(R.drawable.gpl2);
      }
      break;
    case 819:
      paramNumImageView1.setImageResource(R.drawable.gpr3);
      paramNumImageView2.setImageResource(R.drawable.gpr3);
      paramNumImageView3.setImageResource(R.drawable.gpr3);
      paramNumImageView4.setImageResource(R.drawable.gpr3);
      paramNumImageView5.setImageResource(R.drawable.gpr3);
      break;
    case 818:
      paramNumImageView1.setImageResource(R.drawable.gpl3);
      paramNumImageView2.setImageResource(R.drawable.gpl3);
      paramNumImageView3.setImageResource(R.drawable.gpl3);
      paramNumImageView4.setImageResource(R.drawable.gpl3);
      paramNumImageView5.setImageResource(R.drawable.gpl3);
      break;
    case 817:
      paramNumImageView1.setImageResource(R.drawable.gpselect);
      paramNumImageView2.setImageResource(R.drawable.gpselect);
      paramNumImageView3.setImageResource(R.drawable.gpselect);
      paramNumImageView4.setImageResource(R.drawable.gpselect);
      paramNumImageView5.setImageResource(R.drawable.gpselect);
      break;
    case 816:
      paramNumImageView1.setImageResource(R.drawable.gpstart);
      paramNumImageView2.setImageResource(R.drawable.gpstart);
      paramNumImageView3.setImageResource(R.drawable.gpstart);
      paramNumImageView4.setImageResource(R.drawable.gpstart);
      paramNumImageView5.setImageResource(R.drawable.gpstart);
      break;
    case 815:
      paramNumImageView1.setImageResource(R.drawable.gpright);
      paramNumImageView2.setImageResource(R.drawable.gpright);
      paramNumImageView3.setImageResource(R.drawable.gpright);
      paramNumImageView4.setImageResource(R.drawable.gpright);
      paramNumImageView5.setImageResource(R.drawable.gpright);
      break;
    case 814:
      paramNumImageView1.setImageResource(R.drawable.gpleft);
      paramNumImageView2.setImageResource(R.drawable.gpleft);
      paramNumImageView3.setImageResource(R.drawable.gpleft);
      paramNumImageView4.setImageResource(R.drawable.gpleft);
      paramNumImageView5.setImageResource(R.drawable.gpleft);
      break;
    case 813:
      paramNumImageView1.setImageResource(R.drawable.gpdown);
      paramNumImageView2.setImageResource(R.drawable.gpdown);
      paramNumImageView3.setImageResource(R.drawable.gpdown);
      paramNumImageView4.setImageResource(R.drawable.gpdown);
      paramNumImageView5.setImageResource(R.drawable.gpdown);
      break;
    case 812:
      paramNumImageView1.setImageResource(R.drawable.gpup);
      paramNumImageView2.setImageResource(R.drawable.gpup);
      paramNumImageView3.setImageResource(R.drawable.gpup);
      paramNumImageView4.setImageResource(R.drawable.gpup);
      paramNumImageView5.setImageResource(R.drawable.gpup);
      break;
    case 811:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprb);
        paramNumImageView2.setImageResource(R.drawable.gprb);
        paramNumImageView3.setImageResource(R.drawable.gprb);
        paramNumImageView4.setImageResource(R.drawable.gprb);
        paramNumImageView5.setImageResource(R.drawable.gprb);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1);
        paramNumImageView2.setImageResource(R.drawable.gpr1);
        paramNumImageView3.setImageResource(R.drawable.gpr1);
        paramNumImageView4.setImageResource(R.drawable.gpr1);
        paramNumImageView5.setImageResource(R.drawable.gpr1);
      }
      break;
    case 810:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplb);
        paramNumImageView2.setImageResource(R.drawable.gplb);
        paramNumImageView3.setImageResource(R.drawable.gplb);
        paramNumImageView4.setImageResource(R.drawable.gplb);
        paramNumImageView5.setImageResource(R.drawable.gplb);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1);
        paramNumImageView2.setImageResource(R.drawable.gpl1);
        paramNumImageView3.setImageResource(R.drawable.gpl1);
        paramNumImageView4.setImageResource(R.drawable.gpl1);
        paramNumImageView5.setImageResource(R.drawable.gpl1);
      }
      break;
    case 809:
      paramNumImageView1.setImageResource(R.drawable.gpy);
      paramNumImageView2.setImageResource(R.drawable.gpy);
      paramNumImageView3.setImageResource(R.drawable.gpy);
      paramNumImageView4.setImageResource(R.drawable.gpy);
      paramNumImageView5.setImageResource(R.drawable.gpy);
      break;
    case 808:
      paramNumImageView1.setImageResource(R.drawable.gpx);
      paramNumImageView2.setImageResource(R.drawable.gpx);
      paramNumImageView3.setImageResource(R.drawable.gpx);
      paramNumImageView4.setImageResource(R.drawable.gpx);
      paramNumImageView5.setImageResource(R.drawable.gpx);
      break;
    case 807:
      paramNumImageView1.setImageResource(R.drawable.gpb);
      paramNumImageView2.setImageResource(R.drawable.gpb);
      paramNumImageView3.setImageResource(R.drawable.gpb);
      paramNumImageView4.setImageResource(R.drawable.gpb);
      paramNumImageView5.setImageResource(R.drawable.gpb);
      break;
    case 806:
      paramNumImageView1.setImageResource(R.drawable.gpa);
      paramNumImageView2.setImageResource(R.drawable.gpa);
      paramNumImageView3.setImageResource(R.drawable.gpa);
      paramNumImageView4.setImageResource(R.drawable.gpa);
      paramNumImageView5.setImageResource(R.drawable.gpa);
      break;
    case 801:
    case 802:
    case 803:
    case 804:
    case 805:
      paramNumImageView1.setImageResource(R.drawable.newkey);
      paramNumImageView2.setImageResource(R.drawable.newkey);
      paramNumImageView3.setImageResource(R.drawable.newkey);
      paramNumImageView4.setImageResource(R.drawable.newkey);
      paramNumImageView5.setImageResource(R.drawable.newkey);
      break;
    case 800:
      paramNumImageView1.setImageResource(R.drawable.center3);
      paramNumImageView2.setImageResource(R.drawable.center3);
      paramNumImageView3.setImageResource(R.drawable.center3);
      paramNumImageView4.setImageResource(R.drawable.center3);
      paramNumImageView5.setImageResource(R.drawable.center3);
      break;
    case 799:
      paramNumImageView1.setImageResource(R.drawable.gpbpr2);
      paramNumImageView2.setImageResource(R.drawable.gpbpr2);
      paramNumImageView3.setImageResource(R.drawable.gpbpr2);
      paramNumImageView4.setImageResource(R.drawable.gpbpr2);
      paramNumImageView5.setImageResource(R.drawable.gpbpr2);
      break;
    case 798:
      paramNumImageView1.setImageResource(R.drawable.center2);
      paramNumImageView2.setImageResource(R.drawable.center2);
      paramNumImageView3.setImageResource(R.drawable.center2);
      paramNumImageView4.setImageResource(R.drawable.center2);
      paramNumImageView5.setImageResource(R.drawable.center2);
      break;
    case 797:
      paramNumImageView1.setImageResource(R.drawable.gpbpr1);
      paramNumImageView2.setImageResource(R.drawable.gpbpr1);
      paramNumImageView3.setImageResource(R.drawable.gpbpr1);
      paramNumImageView4.setImageResource(R.drawable.gpbpr1);
      paramNumImageView5.setImageResource(R.drawable.gpbpr1);
      break;
    case 796:
      paramNumImageView1.setImageResource(R.drawable.center1);
      paramNumImageView2.setImageResource(R.drawable.center1);
      paramNumImageView3.setImageResource(R.drawable.center1);
      paramNumImageView4.setImageResource(R.drawable.center1);
      paramNumImageView5.setImageResource(R.drawable.center1);
      break;
    case 795:
      paramNumImageView1.setImageResource(R.drawable.gpm4);
      paramNumImageView2.setImageResource(R.drawable.gpm4);
      paramNumImageView3.setImageResource(R.drawable.gpm4);
      paramNumImageView4.setImageResource(R.drawable.gpm4);
      paramNumImageView5.setImageResource(R.drawable.gpm4);
      break;
    case 794:
      paramNumImageView1.setImageResource(R.drawable.gpm3);
      paramNumImageView2.setImageResource(R.drawable.gpm3);
      paramNumImageView3.setImageResource(R.drawable.gpm3);
      paramNumImageView4.setImageResource(R.drawable.gpm3);
      paramNumImageView5.setImageResource(R.drawable.gpm3);
      break;
    case 793:
      paramNumImageView1.setImageResource(R.drawable.gpm2);
      paramNumImageView2.setImageResource(R.drawable.gpm2);
      paramNumImageView3.setImageResource(R.drawable.gpm2);
      paramNumImageView4.setImageResource(R.drawable.gpm2);
      paramNumImageView5.setImageResource(R.drawable.gpm2);
      break;
    case 792:
      paramNumImageView1.setImageResource(R.drawable.gpm1);
      paramNumImageView2.setImageResource(R.drawable.gpm1);
      paramNumImageView3.setImageResource(R.drawable.gpm1);
      paramNumImageView4.setImageResource(R.drawable.gpm1);
      paramNumImageView5.setImageResource(R.drawable.gpm1);
      break;
    case 791:
      paramNumImageView1.setImageResource(R.drawable.gpbpr);
      paramNumImageView2.setImageResource(R.drawable.gpbpr);
      paramNumImageView3.setImageResource(R.drawable.gpbpr);
      paramNumImageView4.setImageResource(R.drawable.gpbpr);
      paramNumImageView5.setImageResource(R.drawable.gpbpr);
      break;
    case 790:
      paramNumImageView1.setImageResource(R.drawable.gpbpl);
      paramNumImageView2.setImageResource(R.drawable.gpbpl);
      paramNumImageView3.setImageResource(R.drawable.gpbpl);
      paramNumImageView4.setImageResource(R.drawable.gpbpl);
      paramNumImageView5.setImageResource(R.drawable.gpbpl);
      break;
    case 789:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprt);
        paramNumImageView2.setImageResource(R.drawable.gprt);
        paramNumImageView3.setImageResource(R.drawable.gprt);
        paramNumImageView4.setImageResource(R.drawable.gprt);
        paramNumImageView5.setImageResource(R.drawable.gprt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2);
        paramNumImageView2.setImageResource(R.drawable.gpr2);
        paramNumImageView3.setImageResource(R.drawable.gpr2);
        paramNumImageView4.setImageResource(R.drawable.gpr2);
        paramNumImageView5.setImageResource(R.drawable.gpr2);
      }
      break;
    case 788:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplt);
        paramNumImageView2.setImageResource(R.drawable.gplt);
        paramNumImageView3.setImageResource(R.drawable.gplt);
        paramNumImageView4.setImageResource(R.drawable.gplt);
        paramNumImageView5.setImageResource(R.drawable.gplt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2);
        paramNumImageView2.setImageResource(R.drawable.gpl2);
        paramNumImageView3.setImageResource(R.drawable.gpl2);
        paramNumImageView4.setImageResource(R.drawable.gpl2);
        paramNumImageView5.setImageResource(R.drawable.gpl2);
      }
      break;
    case 787:
      paramNumImageView1.setImageResource(R.drawable.gpr3);
      paramNumImageView2.setImageResource(R.drawable.gpr3);
      paramNumImageView3.setImageResource(R.drawable.gpr3);
      paramNumImageView4.setImageResource(R.drawable.gpr3);
      paramNumImageView5.setImageResource(R.drawable.gpr3);
      break;
    case 786:
      paramNumImageView1.setImageResource(R.drawable.gpl3);
      paramNumImageView2.setImageResource(R.drawable.gpl3);
      paramNumImageView3.setImageResource(R.drawable.gpl3);
      paramNumImageView4.setImageResource(R.drawable.gpl3);
      paramNumImageView5.setImageResource(R.drawable.gpl3);
      break;
    case 785:
      paramNumImageView1.setImageResource(R.drawable.gpselect);
      paramNumImageView2.setImageResource(R.drawable.gpselect);
      paramNumImageView3.setImageResource(R.drawable.gpselect);
      paramNumImageView4.setImageResource(R.drawable.gpselect);
      paramNumImageView5.setImageResource(R.drawable.gpselect);
      break;
    case 784:
      paramNumImageView1.setImageResource(R.drawable.gpstart);
      paramNumImageView2.setImageResource(R.drawable.gpstart);
      paramNumImageView3.setImageResource(R.drawable.gpstart);
      paramNumImageView4.setImageResource(R.drawable.gpstart);
      paramNumImageView5.setImageResource(R.drawable.gpstart);
      break;
    case 783:
      paramNumImageView1.setImageResource(R.drawable.gpright);
      paramNumImageView2.setImageResource(R.drawable.gpright);
      paramNumImageView3.setImageResource(R.drawable.gpright);
      paramNumImageView4.setImageResource(R.drawable.gpright);
      paramNumImageView5.setImageResource(R.drawable.gpright);
      break;
    case 782:
      paramNumImageView1.setImageResource(R.drawable.gpleft);
      paramNumImageView2.setImageResource(R.drawable.gpleft);
      paramNumImageView3.setImageResource(R.drawable.gpleft);
      paramNumImageView4.setImageResource(R.drawable.gpleft);
      paramNumImageView5.setImageResource(R.drawable.gpleft);
      break;
    case 781:
      paramNumImageView1.setImageResource(R.drawable.gpdown);
      paramNumImageView2.setImageResource(R.drawable.gpdown);
      paramNumImageView3.setImageResource(R.drawable.gpdown);
      paramNumImageView4.setImageResource(R.drawable.gpdown);
      paramNumImageView5.setImageResource(R.drawable.gpdown);
      break;
    case 780:
      paramNumImageView1.setImageResource(R.drawable.gpup);
      paramNumImageView2.setImageResource(R.drawable.gpup);
      paramNumImageView3.setImageResource(R.drawable.gpup);
      paramNumImageView4.setImageResource(R.drawable.gpup);
      paramNumImageView5.setImageResource(R.drawable.gpup);
      break;
    case 779:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprb);
        paramNumImageView2.setImageResource(R.drawable.gprb);
        paramNumImageView3.setImageResource(R.drawable.gprb);
        paramNumImageView4.setImageResource(R.drawable.gprb);
        paramNumImageView5.setImageResource(R.drawable.gprb);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1);
        paramNumImageView2.setImageResource(R.drawable.gpr1);
        paramNumImageView3.setImageResource(R.drawable.gpr1);
        paramNumImageView4.setImageResource(R.drawable.gpr1);
        paramNumImageView5.setImageResource(R.drawable.gpr1);
      }
      break;
    case 778:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplb);
        paramNumImageView2.setImageResource(R.drawable.gplb);
        paramNumImageView3.setImageResource(R.drawable.gplb);
        paramNumImageView4.setImageResource(R.drawable.gplb);
        paramNumImageView5.setImageResource(R.drawable.gplb);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1);
        paramNumImageView2.setImageResource(R.drawable.gpl1);
        paramNumImageView3.setImageResource(R.drawable.gpl1);
        paramNumImageView4.setImageResource(R.drawable.gpl1);
        paramNumImageView5.setImageResource(R.drawable.gpl1);
      }
      break;
    case 777:
      paramNumImageView1.setImageResource(R.drawable.gpy);
      paramNumImageView2.setImageResource(R.drawable.gpy);
      paramNumImageView3.setImageResource(R.drawable.gpy);
      paramNumImageView4.setImageResource(R.drawable.gpy);
      paramNumImageView5.setImageResource(R.drawable.gpy);
      break;
    case 776:
      paramNumImageView1.setImageResource(R.drawable.gpx);
      paramNumImageView2.setImageResource(R.drawable.gpx);
      paramNumImageView3.setImageResource(R.drawable.gpx);
      paramNumImageView4.setImageResource(R.drawable.gpx);
      paramNumImageView5.setImageResource(R.drawable.gpx);
      break;
    case 775:
      paramNumImageView1.setImageResource(R.drawable.gpb);
      paramNumImageView2.setImageResource(R.drawable.gpb);
      paramNumImageView3.setImageResource(R.drawable.gpb);
      paramNumImageView4.setImageResource(R.drawable.gpb);
      paramNumImageView5.setImageResource(R.drawable.gpb);
      break;
    case 774:
      paramNumImageView1.setImageResource(R.drawable.gpa);
      paramNumImageView2.setImageResource(R.drawable.gpa);
      paramNumImageView3.setImageResource(R.drawable.gpa);
      paramNumImageView4.setImageResource(R.drawable.gpa);
      paramNumImageView5.setImageResource(R.drawable.gpa);
      break;
    case 769:
    case 770:
    case 771:
    case 772:
    case 773:
      paramNumImageView1.setImageResource(R.drawable.newkey);
      paramNumImageView2.setImageResource(R.drawable.newkey);
      paramNumImageView3.setImageResource(R.drawable.newkey);
      paramNumImageView4.setImageResource(R.drawable.newkey);
      paramNumImageView5.setImageResource(R.drawable.newkey);
      break;
    case 768:
      paramNumImageView1.setImageResource(R.drawable.center3);
      paramNumImageView2.setImageResource(R.drawable.center3);
      paramNumImageView3.setImageResource(R.drawable.center3);
      paramNumImageView4.setImageResource(R.drawable.center3);
      paramNumImageView5.setImageResource(R.drawable.center3);
      break;
    case 767:
      paramNumImageView1.setImageResource(R.drawable.gpbpr2);
      paramNumImageView2.setImageResource(R.drawable.gpbpr2);
      paramNumImageView3.setImageResource(R.drawable.gpbpr2);
      paramNumImageView4.setImageResource(R.drawable.gpbpr2);
      paramNumImageView5.setImageResource(R.drawable.gpbpr2);
      break;
    case 766:
      paramNumImageView1.setImageResource(R.drawable.center2);
      paramNumImageView2.setImageResource(R.drawable.center2);
      paramNumImageView3.setImageResource(R.drawable.center2);
      paramNumImageView4.setImageResource(R.drawable.center2);
      paramNumImageView5.setImageResource(R.drawable.center2);
      break;
    case 765:
      paramNumImageView1.setImageResource(R.drawable.gpbpr1);
      paramNumImageView2.setImageResource(R.drawable.gpbpr1);
      paramNumImageView3.setImageResource(R.drawable.gpbpr1);
      paramNumImageView4.setImageResource(R.drawable.gpbpr1);
      paramNumImageView5.setImageResource(R.drawable.gpbpr1);
      break;
    case 764:
      paramNumImageView1.setImageResource(R.drawable.center1);
      paramNumImageView2.setImageResource(R.drawable.center1);
      paramNumImageView3.setImageResource(R.drawable.center1);
      paramNumImageView4.setImageResource(R.drawable.center1);
      paramNumImageView5.setImageResource(R.drawable.center1);
      break;
    case 763:
      paramNumImageView1.setImageResource(R.drawable.gpm4);
      paramNumImageView2.setImageResource(R.drawable.gpm4);
      paramNumImageView3.setImageResource(R.drawable.gpm4);
      paramNumImageView4.setImageResource(R.drawable.gpm4);
      paramNumImageView5.setImageResource(R.drawable.gpm4);
      break;
    case 762:
      paramNumImageView1.setImageResource(R.drawable.gpm3);
      paramNumImageView2.setImageResource(R.drawable.gpm3);
      paramNumImageView3.setImageResource(R.drawable.gpm3);
      paramNumImageView4.setImageResource(R.drawable.gpm3);
      paramNumImageView5.setImageResource(R.drawable.gpm3);
      break;
    case 761:
      paramNumImageView1.setImageResource(R.drawable.gpm2);
      paramNumImageView2.setImageResource(R.drawable.gpm2);
      paramNumImageView3.setImageResource(R.drawable.gpm2);
      paramNumImageView4.setImageResource(R.drawable.gpm2);
      paramNumImageView5.setImageResource(R.drawable.gpm2);
      break;
    case 760:
      paramNumImageView1.setImageResource(R.drawable.gpm1);
      paramNumImageView2.setImageResource(R.drawable.gpm1);
      paramNumImageView3.setImageResource(R.drawable.gpm1);
      paramNumImageView4.setImageResource(R.drawable.gpm1);
      paramNumImageView5.setImageResource(R.drawable.gpm1);
      break;
    case 759:
      paramNumImageView1.setImageResource(R.drawable.gpbpr);
      paramNumImageView2.setImageResource(R.drawable.gpbpr);
      paramNumImageView3.setImageResource(R.drawable.gpbpr);
      paramNumImageView4.setImageResource(R.drawable.gpbpr);
      paramNumImageView5.setImageResource(R.drawable.gpbpr);
      break;
    case 758:
      paramNumImageView1.setImageResource(R.drawable.gpbpl);
      paramNumImageView2.setImageResource(R.drawable.gpbpl);
      paramNumImageView3.setImageResource(R.drawable.gpbpl);
      paramNumImageView4.setImageResource(R.drawable.gpbpl);
      paramNumImageView5.setImageResource(R.drawable.gpbpl);
      break;
    case 757:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprt);
        paramNumImageView2.setImageResource(R.drawable.gprt);
        paramNumImageView3.setImageResource(R.drawable.gprt);
        paramNumImageView4.setImageResource(R.drawable.gprt);
        paramNumImageView5.setImageResource(R.drawable.gprt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2);
        paramNumImageView2.setImageResource(R.drawable.gpr2);
        paramNumImageView3.setImageResource(R.drawable.gpr2);
        paramNumImageView4.setImageResource(R.drawable.gpr2);
        paramNumImageView5.setImageResource(R.drawable.gpr2);
      }
      break;
    case 756:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplt);
        paramNumImageView2.setImageResource(R.drawable.gplt);
        paramNumImageView3.setImageResource(R.drawable.gplt);
        paramNumImageView4.setImageResource(R.drawable.gplt);
        paramNumImageView5.setImageResource(R.drawable.gplt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2);
        paramNumImageView2.setImageResource(R.drawable.gpl2);
        paramNumImageView3.setImageResource(R.drawable.gpl2);
        paramNumImageView4.setImageResource(R.drawable.gpl2);
        paramNumImageView5.setImageResource(R.drawable.gpl2);
      }
      break;
    case 755:
      paramNumImageView1.setImageResource(R.drawable.gpr3);
      paramNumImageView2.setImageResource(R.drawable.gpr3);
      paramNumImageView3.setImageResource(R.drawable.gpr3);
      paramNumImageView4.setImageResource(R.drawable.gpr3);
      paramNumImageView5.setImageResource(R.drawable.gpr3);
      break;
    case 754:
      paramNumImageView1.setImageResource(R.drawable.gpl3);
      paramNumImageView2.setImageResource(R.drawable.gpl3);
      paramNumImageView3.setImageResource(R.drawable.gpl3);
      paramNumImageView4.setImageResource(R.drawable.gpl3);
      paramNumImageView5.setImageResource(R.drawable.gpl3);
      break;
    case 753:
      paramNumImageView1.setImageResource(R.drawable.gpselect);
      paramNumImageView2.setImageResource(R.drawable.gpselect);
      paramNumImageView3.setImageResource(R.drawable.gpselect);
      paramNumImageView4.setImageResource(R.drawable.gpselect);
      paramNumImageView5.setImageResource(R.drawable.gpselect);
      break;
    case 752:
      paramNumImageView1.setImageResource(R.drawable.gpstart);
      paramNumImageView2.setImageResource(R.drawable.gpstart);
      paramNumImageView3.setImageResource(R.drawable.gpstart);
      paramNumImageView4.setImageResource(R.drawable.gpstart);
      paramNumImageView5.setImageResource(R.drawable.gpstart);
      break;
    case 751:
      paramNumImageView1.setImageResource(R.drawable.gpright);
      paramNumImageView2.setImageResource(R.drawable.gpright);
      paramNumImageView3.setImageResource(R.drawable.gpright);
      paramNumImageView4.setImageResource(R.drawable.gpright);
      paramNumImageView5.setImageResource(R.drawable.gpright);
      break;
    case 750:
      paramNumImageView1.setImageResource(R.drawable.gpleft);
      paramNumImageView2.setImageResource(R.drawable.gpleft);
      paramNumImageView3.setImageResource(R.drawable.gpleft);
      paramNumImageView4.setImageResource(R.drawable.gpleft);
      paramNumImageView5.setImageResource(R.drawable.gpleft);
      break;
    case 749:
      paramNumImageView1.setImageResource(R.drawable.gpdown);
      paramNumImageView2.setImageResource(R.drawable.gpdown);
      paramNumImageView3.setImageResource(R.drawable.gpdown);
      paramNumImageView4.setImageResource(R.drawable.gpdown);
      paramNumImageView5.setImageResource(R.drawable.gpdown);
      break;
    case 748:
      paramNumImageView1.setImageResource(R.drawable.gpup);
      paramNumImageView2.setImageResource(R.drawable.gpup);
      paramNumImageView3.setImageResource(R.drawable.gpup);
      paramNumImageView4.setImageResource(R.drawable.gpup);
      paramNumImageView5.setImageResource(R.drawable.gpup);
      break;
    case 747:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprb);
        paramNumImageView2.setImageResource(R.drawable.gprb);
        paramNumImageView3.setImageResource(R.drawable.gprb);
        paramNumImageView4.setImageResource(R.drawable.gprb);
        paramNumImageView5.setImageResource(R.drawable.gprb);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1);
        paramNumImageView2.setImageResource(R.drawable.gpr1);
        paramNumImageView3.setImageResource(R.drawable.gpr1);
        paramNumImageView4.setImageResource(R.drawable.gpr1);
        paramNumImageView5.setImageResource(R.drawable.gpr1);
      }
      break;
    case 746:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplb);
        paramNumImageView2.setImageResource(R.drawable.gplb);
        paramNumImageView3.setImageResource(R.drawable.gplb);
        paramNumImageView4.setImageResource(R.drawable.gplb);
        paramNumImageView5.setImageResource(R.drawable.gplb);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1);
        paramNumImageView2.setImageResource(R.drawable.gpl1);
        paramNumImageView3.setImageResource(R.drawable.gpl1);
        paramNumImageView4.setImageResource(R.drawable.gpl1);
        paramNumImageView5.setImageResource(R.drawable.gpl1);
      }
      break;
    case 745:
      paramNumImageView1.setImageResource(R.drawable.gpy);
      paramNumImageView2.setImageResource(R.drawable.gpy);
      paramNumImageView3.setImageResource(R.drawable.gpy);
      paramNumImageView4.setImageResource(R.drawable.gpy);
      paramNumImageView5.setImageResource(R.drawable.gpy);
      break;
    case 744:
      paramNumImageView1.setImageResource(R.drawable.gpx);
      paramNumImageView2.setImageResource(R.drawable.gpx);
      paramNumImageView3.setImageResource(R.drawable.gpx);
      paramNumImageView4.setImageResource(R.drawable.gpx);
      paramNumImageView5.setImageResource(R.drawable.gpx);
      break;
    case 743:
      paramNumImageView1.setImageResource(R.drawable.gpb);
      paramNumImageView2.setImageResource(R.drawable.gpb);
      paramNumImageView3.setImageResource(R.drawable.gpb);
      paramNumImageView4.setImageResource(R.drawable.gpb);
      paramNumImageView5.setImageResource(R.drawable.gpb);
      break;
    case 742:
      paramNumImageView1.setImageResource(R.drawable.gpa);
      paramNumImageView2.setImageResource(R.drawable.gpa);
      paramNumImageView3.setImageResource(R.drawable.gpa);
      paramNumImageView4.setImageResource(R.drawable.gpa);
      paramNumImageView5.setImageResource(R.drawable.gpa);
      break;
    case 737:
    case 738:
    case 739:
    case 740:
    case 741:
      paramNumImageView1.setImageResource(R.drawable.newkey);
      paramNumImageView2.setImageResource(R.drawable.newkey);
      paramNumImageView3.setImageResource(R.drawable.newkey);
      paramNumImageView4.setImageResource(R.drawable.newkey);
      paramNumImageView5.setImageResource(R.drawable.newkey);
      break;
    case 736:
      paramNumImageView1.setImageResource(R.drawable.center3);
      paramNumImageView2.setImageResource(R.drawable.center3);
      paramNumImageView3.setImageResource(R.drawable.center3);
      paramNumImageView4.setImageResource(R.drawable.center3);
      paramNumImageView5.setImageResource(R.drawable.center3);
      break;
    case 735:
      paramNumImageView1.setImageResource(R.drawable.gpbpr2);
      paramNumImageView2.setImageResource(R.drawable.gpbpr2);
      paramNumImageView3.setImageResource(R.drawable.gpbpr2);
      paramNumImageView4.setImageResource(R.drawable.gpbpr2);
      paramNumImageView5.setImageResource(R.drawable.gpbpr2);
      break;
    case 734:
      paramNumImageView1.setImageResource(R.drawable.center2);
      paramNumImageView2.setImageResource(R.drawable.center2);
      paramNumImageView3.setImageResource(R.drawable.center2);
      paramNumImageView4.setImageResource(R.drawable.center2);
      paramNumImageView5.setImageResource(R.drawable.center2);
      break;
    case 733:
      paramNumImageView1.setImageResource(R.drawable.gpbpr1);
      paramNumImageView2.setImageResource(R.drawable.gpbpr1);
      paramNumImageView3.setImageResource(R.drawable.gpbpr1);
      paramNumImageView4.setImageResource(R.drawable.gpbpr1);
      paramNumImageView5.setImageResource(R.drawable.gpbpr1);
      break;
    case 732:
      paramNumImageView1.setImageResource(R.drawable.center1);
      paramNumImageView2.setImageResource(R.drawable.center1);
      paramNumImageView3.setImageResource(R.drawable.center1);
      paramNumImageView4.setImageResource(R.drawable.center1);
      paramNumImageView5.setImageResource(R.drawable.center1);
      break;
    case 731:
      paramNumImageView1.setImageResource(R.drawable.gpm4);
      paramNumImageView2.setImageResource(R.drawable.gpm4);
      paramNumImageView3.setImageResource(R.drawable.gpm4);
      paramNumImageView4.setImageResource(R.drawable.gpm4);
      paramNumImageView5.setImageResource(R.drawable.gpm4);
      break;
    case 730:
      paramNumImageView1.setImageResource(R.drawable.gpm3);
      paramNumImageView2.setImageResource(R.drawable.gpm3);
      paramNumImageView3.setImageResource(R.drawable.gpm3);
      paramNumImageView4.setImageResource(R.drawable.gpm3);
      paramNumImageView5.setImageResource(R.drawable.gpm3);
      break;
    case 729:
      paramNumImageView1.setImageResource(R.drawable.gpm2);
      paramNumImageView2.setImageResource(R.drawable.gpm2);
      paramNumImageView3.setImageResource(R.drawable.gpm2);
      paramNumImageView4.setImageResource(R.drawable.gpm2);
      paramNumImageView5.setImageResource(R.drawable.gpm2);
      break;
    case 728:
      paramNumImageView1.setImageResource(R.drawable.gpm1);
      paramNumImageView2.setImageResource(R.drawable.gpm1);
      paramNumImageView3.setImageResource(R.drawable.gpm1);
      paramNumImageView4.setImageResource(R.drawable.gpm1);
      paramNumImageView5.setImageResource(R.drawable.gpm1);
      break;
    case 727:
      paramNumImageView1.setImageResource(R.drawable.gpbpr);
      paramNumImageView2.setImageResource(R.drawable.gpbpr);
      paramNumImageView3.setImageResource(R.drawable.gpbpr);
      paramNumImageView4.setImageResource(R.drawable.gpbpr);
      paramNumImageView5.setImageResource(R.drawable.gpbpr);
      break;
    case 726:
      paramNumImageView1.setImageResource(R.drawable.gpbpl);
      paramNumImageView2.setImageResource(R.drawable.gpbpl);
      paramNumImageView3.setImageResource(R.drawable.gpbpl);
      paramNumImageView4.setImageResource(R.drawable.gpbpl);
      paramNumImageView5.setImageResource(R.drawable.gpbpl);
      break;
    case 725:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprt);
        paramNumImageView2.setImageResource(R.drawable.gprt);
        paramNumImageView3.setImageResource(R.drawable.gprt);
        paramNumImageView4.setImageResource(R.drawable.gprt);
        paramNumImageView5.setImageResource(R.drawable.gprt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2);
        paramNumImageView2.setImageResource(R.drawable.gpr2);
        paramNumImageView3.setImageResource(R.drawable.gpr2);
        paramNumImageView4.setImageResource(R.drawable.gpr2);
        paramNumImageView5.setImageResource(R.drawable.gpr2);
      }
      break;
    case 724:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplt);
        paramNumImageView2.setImageResource(R.drawable.gplt);
        paramNumImageView3.setImageResource(R.drawable.gplt);
        paramNumImageView4.setImageResource(R.drawable.gplt);
        paramNumImageView5.setImageResource(R.drawable.gplt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2);
        paramNumImageView2.setImageResource(R.drawable.gpl2);
        paramNumImageView3.setImageResource(R.drawable.gpl2);
        paramNumImageView4.setImageResource(R.drawable.gpl2);
        paramNumImageView5.setImageResource(R.drawable.gpl2);
      }
      break;
    case 723:
      paramNumImageView1.setImageResource(R.drawable.gpr3);
      paramNumImageView2.setImageResource(R.drawable.gpr3);
      paramNumImageView3.setImageResource(R.drawable.gpr3);
      paramNumImageView4.setImageResource(R.drawable.gpr3);
      paramNumImageView5.setImageResource(R.drawable.gpr3);
      break;
    case 722:
      paramNumImageView1.setImageResource(R.drawable.gpl3);
      paramNumImageView2.setImageResource(R.drawable.gpl3);
      paramNumImageView3.setImageResource(R.drawable.gpl3);
      paramNumImageView4.setImageResource(R.drawable.gpl3);
      paramNumImageView5.setImageResource(R.drawable.gpl3);
      break;
    case 721:
      paramNumImageView1.setImageResource(R.drawable.gpselect);
      paramNumImageView2.setImageResource(R.drawable.gpselect);
      paramNumImageView3.setImageResource(R.drawable.gpselect);
      paramNumImageView4.setImageResource(R.drawable.gpselect);
      paramNumImageView5.setImageResource(R.drawable.gpselect);
      break;
    case 720:
      paramNumImageView1.setImageResource(R.drawable.gpstart);
      paramNumImageView2.setImageResource(R.drawable.gpstart);
      paramNumImageView3.setImageResource(R.drawable.gpstart);
      paramNumImageView4.setImageResource(R.drawable.gpstart);
      paramNumImageView5.setImageResource(R.drawable.gpstart);
      break;
    case 719:
      paramNumImageView1.setImageResource(R.drawable.gpright);
      paramNumImageView2.setImageResource(R.drawable.gpright);
      paramNumImageView3.setImageResource(R.drawable.gpright);
      paramNumImageView4.setImageResource(R.drawable.gpright);
      paramNumImageView5.setImageResource(R.drawable.gpright);
      break;
    case 718:
      paramNumImageView1.setImageResource(R.drawable.gpleft);
      paramNumImageView2.setImageResource(R.drawable.gpleft);
      paramNumImageView3.setImageResource(R.drawable.gpleft);
      paramNumImageView4.setImageResource(R.drawable.gpleft);
      paramNumImageView5.setImageResource(R.drawable.gpleft);
      break;
    case 717:
      paramNumImageView1.setImageResource(R.drawable.gpdown);
      paramNumImageView2.setImageResource(R.drawable.gpdown);
      paramNumImageView3.setImageResource(R.drawable.gpdown);
      paramNumImageView4.setImageResource(R.drawable.gpdown);
      paramNumImageView5.setImageResource(R.drawable.gpdown);
      break;
    case 716:
      paramNumImageView1.setImageResource(R.drawable.gpup);
      paramNumImageView2.setImageResource(R.drawable.gpup);
      paramNumImageView3.setImageResource(R.drawable.gpup);
      paramNumImageView4.setImageResource(R.drawable.gpup);
      paramNumImageView5.setImageResource(R.drawable.gpup);
      break;
    case 715:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprb);
        paramNumImageView2.setImageResource(R.drawable.gprb);
        paramNumImageView3.setImageResource(R.drawable.gprb);
        paramNumImageView4.setImageResource(R.drawable.gprb);
        paramNumImageView5.setImageResource(R.drawable.gprb);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1);
        paramNumImageView2.setImageResource(R.drawable.gpr1);
        paramNumImageView3.setImageResource(R.drawable.gpr1);
        paramNumImageView4.setImageResource(R.drawable.gpr1);
        paramNumImageView5.setImageResource(R.drawable.gpr1);
      }
      break;
    case 714:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplb);
        paramNumImageView2.setImageResource(R.drawable.gplb);
        paramNumImageView3.setImageResource(R.drawable.gplb);
        paramNumImageView4.setImageResource(R.drawable.gplb);
        paramNumImageView5.setImageResource(R.drawable.gplb);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1);
        paramNumImageView2.setImageResource(R.drawable.gpl1);
        paramNumImageView3.setImageResource(R.drawable.gpl1);
        paramNumImageView4.setImageResource(R.drawable.gpl1);
        paramNumImageView5.setImageResource(R.drawable.gpl1);
      }
      break;
    case 713:
      paramNumImageView1.setImageResource(R.drawable.gpy);
      paramNumImageView2.setImageResource(R.drawable.gpy);
      paramNumImageView3.setImageResource(R.drawable.gpy);
      paramNumImageView4.setImageResource(R.drawable.gpy);
      paramNumImageView5.setImageResource(R.drawable.gpy);
      break;
    case 712:
      paramNumImageView1.setImageResource(R.drawable.gpx);
      paramNumImageView2.setImageResource(R.drawable.gpx);
      paramNumImageView3.setImageResource(R.drawable.gpx);
      paramNumImageView4.setImageResource(R.drawable.gpx);
      paramNumImageView5.setImageResource(R.drawable.gpx);
      break;
    case 711:
      paramNumImageView1.setImageResource(R.drawable.gpb);
      paramNumImageView2.setImageResource(R.drawable.gpb);
      paramNumImageView3.setImageResource(R.drawable.gpb);
      paramNumImageView4.setImageResource(R.drawable.gpb);
      paramNumImageView5.setImageResource(R.drawable.gpb);
      break;
    case 710:
      paramNumImageView1.setImageResource(R.drawable.gpa);
      paramNumImageView2.setImageResource(R.drawable.gpa);
      paramNumImageView3.setImageResource(R.drawable.gpa);
      paramNumImageView4.setImageResource(R.drawable.gpa);
      paramNumImageView5.setImageResource(R.drawable.gpa);
      break;
    case 705:
    case 706:
    case 707:
    case 708:
    case 709:
      paramNumImageView1.setImageResource(R.drawable.newkey);
      paramNumImageView2.setImageResource(R.drawable.newkey);
      paramNumImageView3.setImageResource(R.drawable.newkey);
      paramNumImageView4.setImageResource(R.drawable.newkey);
      paramNumImageView5.setImageResource(R.drawable.newkey);
      break;
    case 704:
      paramNumImageView1.setImageResource(R.drawable.center3);
      paramNumImageView2.setImageResource(R.drawable.center3);
      paramNumImageView3.setImageResource(R.drawable.center3);
      paramNumImageView4.setImageResource(R.drawable.center3);
      paramNumImageView5.setImageResource(R.drawable.center3);
      break;
    case 703:
      paramNumImageView1.setImageResource(R.drawable.gpbpr2);
      paramNumImageView2.setImageResource(R.drawable.gpbpr2);
      paramNumImageView3.setImageResource(R.drawable.gpbpr2);
      paramNumImageView4.setImageResource(R.drawable.gpbpr2);
      paramNumImageView5.setImageResource(R.drawable.gpbpr2);
      break;
    case 702:
      paramNumImageView1.setImageResource(R.drawable.center2);
      paramNumImageView2.setImageResource(R.drawable.center2);
      paramNumImageView3.setImageResource(R.drawable.center2);
      paramNumImageView4.setImageResource(R.drawable.center2);
      paramNumImageView5.setImageResource(R.drawable.center2);
      break;
    case 701:
      paramNumImageView1.setImageResource(R.drawable.gpbpr1);
      paramNumImageView2.setImageResource(R.drawable.gpbpr1);
      paramNumImageView3.setImageResource(R.drawable.gpbpr1);
      paramNumImageView4.setImageResource(R.drawable.gpbpr1);
      paramNumImageView5.setImageResource(R.drawable.gpbpr1);
      break;
    case 700:
      paramNumImageView1.setImageResource(R.drawable.center1);
      paramNumImageView2.setImageResource(R.drawable.center1);
      paramNumImageView3.setImageResource(R.drawable.center1);
      paramNumImageView4.setImageResource(R.drawable.center1);
      paramNumImageView5.setImageResource(R.drawable.center1);
      break;
    case 699:
      paramNumImageView1.setImageResource(R.drawable.gpm4);
      paramNumImageView2.setImageResource(R.drawable.gpm4);
      paramNumImageView3.setImageResource(R.drawable.gpm4);
      paramNumImageView4.setImageResource(R.drawable.gpm4);
      paramNumImageView5.setImageResource(R.drawable.gpm4);
      break;
    case 698:
      paramNumImageView1.setImageResource(R.drawable.gpm3);
      paramNumImageView2.setImageResource(R.drawable.gpm3);
      paramNumImageView3.setImageResource(R.drawable.gpm3);
      paramNumImageView4.setImageResource(R.drawable.gpm3);
      paramNumImageView5.setImageResource(R.drawable.gpm3);
      break;
    case 697:
      paramNumImageView1.setImageResource(R.drawable.gpm2);
      paramNumImageView2.setImageResource(R.drawable.gpm2);
      paramNumImageView3.setImageResource(R.drawable.gpm2);
      paramNumImageView4.setImageResource(R.drawable.gpm2);
      paramNumImageView5.setImageResource(R.drawable.gpm2);
      break;
    case 696:
      paramNumImageView1.setImageResource(R.drawable.gpm1);
      paramNumImageView2.setImageResource(R.drawable.gpm1);
      paramNumImageView3.setImageResource(R.drawable.gpm1);
      paramNumImageView4.setImageResource(R.drawable.gpm1);
      paramNumImageView5.setImageResource(R.drawable.gpm1);
      break;
    case 695:
      paramNumImageView1.setImageResource(R.drawable.gpbpr);
      paramNumImageView2.setImageResource(R.drawable.gpbpr);
      paramNumImageView3.setImageResource(R.drawable.gpbpr);
      paramNumImageView4.setImageResource(R.drawable.gpbpr);
      paramNumImageView5.setImageResource(R.drawable.gpbpr);
      break;
    case 694:
      paramNumImageView1.setImageResource(R.drawable.gpbpl);
      paramNumImageView2.setImageResource(R.drawable.gpbpl);
      paramNumImageView3.setImageResource(R.drawable.gpbpl);
      paramNumImageView4.setImageResource(R.drawable.gpbpl);
      paramNumImageView5.setImageResource(R.drawable.gpbpl);
      break;
    case 693:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprt);
        paramNumImageView2.setImageResource(R.drawable.gprt);
        paramNumImageView3.setImageResource(R.drawable.gprt);
        paramNumImageView4.setImageResource(R.drawable.gprt);
        paramNumImageView5.setImageResource(R.drawable.gprt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2);
        paramNumImageView2.setImageResource(R.drawable.gpr2);
        paramNumImageView3.setImageResource(R.drawable.gpr2);
        paramNumImageView4.setImageResource(R.drawable.gpr2);
        paramNumImageView5.setImageResource(R.drawable.gpr2);
      }
      break;
    case 692:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplt);
        paramNumImageView2.setImageResource(R.drawable.gplt);
        paramNumImageView3.setImageResource(R.drawable.gplt);
        paramNumImageView4.setImageResource(R.drawable.gplt);
        paramNumImageView5.setImageResource(R.drawable.gplt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2);
        paramNumImageView2.setImageResource(R.drawable.gpl2);
        paramNumImageView3.setImageResource(R.drawable.gpl2);
        paramNumImageView4.setImageResource(R.drawable.gpl2);
        paramNumImageView5.setImageResource(R.drawable.gpl2);
      }
      break;
    case 691:
      paramNumImageView1.setImageResource(R.drawable.gpr3);
      paramNumImageView2.setImageResource(R.drawable.gpr3);
      paramNumImageView3.setImageResource(R.drawable.gpr3);
      paramNumImageView4.setImageResource(R.drawable.gpr3);
      paramNumImageView5.setImageResource(R.drawable.gpr3);
      break;
    case 690:
      paramNumImageView1.setImageResource(R.drawable.gpl3);
      paramNumImageView2.setImageResource(R.drawable.gpl3);
      paramNumImageView3.setImageResource(R.drawable.gpl3);
      paramNumImageView4.setImageResource(R.drawable.gpl3);
      paramNumImageView5.setImageResource(R.drawable.gpl3);
      break;
    case 689:
      paramNumImageView1.setImageResource(R.drawable.gpselect);
      paramNumImageView2.setImageResource(R.drawable.gpselect);
      paramNumImageView3.setImageResource(R.drawable.gpselect);
      paramNumImageView4.setImageResource(R.drawable.gpselect);
      paramNumImageView5.setImageResource(R.drawable.gpselect);
      break;
    case 688:
      paramNumImageView1.setImageResource(R.drawable.gpstart);
      paramNumImageView2.setImageResource(R.drawable.gpstart);
      paramNumImageView3.setImageResource(R.drawable.gpstart);
      paramNumImageView4.setImageResource(R.drawable.gpstart);
      paramNumImageView5.setImageResource(R.drawable.gpstart);
      break;
    case 687:
      paramNumImageView1.setImageResource(R.drawable.gpright);
      paramNumImageView2.setImageResource(R.drawable.gpright);
      paramNumImageView3.setImageResource(R.drawable.gpright);
      paramNumImageView4.setImageResource(R.drawable.gpright);
      paramNumImageView5.setImageResource(R.drawable.gpright);
      break;
    case 686:
      paramNumImageView1.setImageResource(R.drawable.gpleft);
      paramNumImageView2.setImageResource(R.drawable.gpleft);
      paramNumImageView3.setImageResource(R.drawable.gpleft);
      paramNumImageView4.setImageResource(R.drawable.gpleft);
      paramNumImageView5.setImageResource(R.drawable.gpleft);
      break;
    case 685:
      paramNumImageView1.setImageResource(R.drawable.gpdown);
      paramNumImageView2.setImageResource(R.drawable.gpdown);
      paramNumImageView3.setImageResource(R.drawable.gpdown);
      paramNumImageView4.setImageResource(R.drawable.gpdown);
      paramNumImageView5.setImageResource(R.drawable.gpdown);
      break;
    case 684:
      paramNumImageView1.setImageResource(R.drawable.gpup);
      paramNumImageView2.setImageResource(R.drawable.gpup);
      paramNumImageView3.setImageResource(R.drawable.gpup);
      paramNumImageView4.setImageResource(R.drawable.gpup);
      paramNumImageView5.setImageResource(R.drawable.gpup);
      break;
    case 683:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprb);
        paramNumImageView2.setImageResource(R.drawable.gprb);
        paramNumImageView3.setImageResource(R.drawable.gprb);
        paramNumImageView4.setImageResource(R.drawable.gprb);
        paramNumImageView5.setImageResource(R.drawable.gprb);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1);
        paramNumImageView2.setImageResource(R.drawable.gpr1);
        paramNumImageView3.setImageResource(R.drawable.gpr1);
        paramNumImageView4.setImageResource(R.drawable.gpr1);
        paramNumImageView5.setImageResource(R.drawable.gpr1);
      }
      break;
    case 682:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplb);
        paramNumImageView2.setImageResource(R.drawable.gplb);
        paramNumImageView3.setImageResource(R.drawable.gplb);
        paramNumImageView4.setImageResource(R.drawable.gplb);
        paramNumImageView5.setImageResource(R.drawable.gplb);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1);
        paramNumImageView2.setImageResource(R.drawable.gpl1);
        paramNumImageView3.setImageResource(R.drawable.gpl1);
        paramNumImageView4.setImageResource(R.drawable.gpl1);
        paramNumImageView5.setImageResource(R.drawable.gpl1);
      }
      break;
    case 681:
      paramNumImageView1.setImageResource(R.drawable.gpy);
      paramNumImageView2.setImageResource(R.drawable.gpy);
      paramNumImageView3.setImageResource(R.drawable.gpy);
      paramNumImageView4.setImageResource(R.drawable.gpy);
      paramNumImageView5.setImageResource(R.drawable.gpy);
      break;
    case 680:
      paramNumImageView1.setImageResource(R.drawable.gpx);
      paramNumImageView2.setImageResource(R.drawable.gpx);
      paramNumImageView3.setImageResource(R.drawable.gpx);
      paramNumImageView4.setImageResource(R.drawable.gpx);
      paramNumImageView5.setImageResource(R.drawable.gpx);
      break;
    case 679:
      paramNumImageView1.setImageResource(R.drawable.gpb);
      paramNumImageView2.setImageResource(R.drawable.gpb);
      paramNumImageView3.setImageResource(R.drawable.gpb);
      paramNumImageView4.setImageResource(R.drawable.gpb);
      paramNumImageView5.setImageResource(R.drawable.gpb);
      break;
    case 678:
      paramNumImageView1.setImageResource(R.drawable.gpa);
      paramNumImageView2.setImageResource(R.drawable.gpa);
      paramNumImageView3.setImageResource(R.drawable.gpa);
      paramNumImageView4.setImageResource(R.drawable.gpa);
      paramNumImageView5.setImageResource(R.drawable.gpa);
      break;
    case 673:
    case 674:
    case 675:
    case 676:
    case 677:
      paramNumImageView1.setImageResource(R.drawable.newkey);
      paramNumImageView2.setImageResource(R.drawable.newkey);
      paramNumImageView3.setImageResource(R.drawable.newkey);
      paramNumImageView4.setImageResource(R.drawable.newkey);
      paramNumImageView5.setImageResource(R.drawable.newkey);
      break;
    case 672:
      paramNumImageView1.setImageResource(R.drawable.center3);
      paramNumImageView2.setImageResource(R.drawable.center3);
      paramNumImageView3.setImageResource(R.drawable.center3);
      paramNumImageView4.setImageResource(R.drawable.center3);
      paramNumImageView5.setImageResource(R.drawable.center3);
      break;
    case 671:
      paramNumImageView1.setImageResource(R.drawable.gpbpr2);
      paramNumImageView2.setImageResource(R.drawable.gpbpr2);
      paramNumImageView3.setImageResource(R.drawable.gpbpr2);
      paramNumImageView4.setImageResource(R.drawable.gpbpr2);
      paramNumImageView5.setImageResource(R.drawable.gpbpr2);
      break;
    case 670:
      paramNumImageView1.setImageResource(R.drawable.center2);
      paramNumImageView2.setImageResource(R.drawable.center2);
      paramNumImageView3.setImageResource(R.drawable.center2);
      paramNumImageView4.setImageResource(R.drawable.center2);
      paramNumImageView5.setImageResource(R.drawable.center2);
      break;
    case 669:
      paramNumImageView1.setImageResource(R.drawable.gpbpr1);
      paramNumImageView2.setImageResource(R.drawable.gpbpr1);
      paramNumImageView3.setImageResource(R.drawable.gpbpr1);
      paramNumImageView4.setImageResource(R.drawable.gpbpr1);
      paramNumImageView5.setImageResource(R.drawable.gpbpr1);
      break;
    case 668:
      paramNumImageView1.setImageResource(R.drawable.center1);
      paramNumImageView2.setImageResource(R.drawable.center1);
      paramNumImageView3.setImageResource(R.drawable.center1);
      paramNumImageView4.setImageResource(R.drawable.center1);
      paramNumImageView5.setImageResource(R.drawable.center1);
      break;
    case 667:
      paramNumImageView1.setImageResource(R.drawable.gpm4);
      paramNumImageView2.setImageResource(R.drawable.gpm4);
      paramNumImageView3.setImageResource(R.drawable.gpm4);
      paramNumImageView4.setImageResource(R.drawable.gpm4);
      paramNumImageView5.setImageResource(R.drawable.gpm4);
      break;
    case 666:
      paramNumImageView1.setImageResource(R.drawable.gpm3);
      paramNumImageView2.setImageResource(R.drawable.gpm3);
      paramNumImageView3.setImageResource(R.drawable.gpm3);
      paramNumImageView4.setImageResource(R.drawable.gpm3);
      paramNumImageView5.setImageResource(R.drawable.gpm3);
      break;
    case 665:
      paramNumImageView1.setImageResource(R.drawable.gpm2);
      paramNumImageView2.setImageResource(R.drawable.gpm2);
      paramNumImageView3.setImageResource(R.drawable.gpm2);
      paramNumImageView4.setImageResource(R.drawable.gpm2);
      paramNumImageView5.setImageResource(R.drawable.gpm2);
      break;
    case 664:
      paramNumImageView1.setImageResource(R.drawable.gpm1);
      paramNumImageView2.setImageResource(R.drawable.gpm1);
      paramNumImageView3.setImageResource(R.drawable.gpm1);
      paramNumImageView4.setImageResource(R.drawable.gpm1);
      paramNumImageView5.setImageResource(R.drawable.gpm1);
      break;
    case 663:
      paramNumImageView1.setImageResource(R.drawable.gpbpr);
      paramNumImageView2.setImageResource(R.drawable.gpbpr);
      paramNumImageView3.setImageResource(R.drawable.gpbpr);
      paramNumImageView4.setImageResource(R.drawable.gpbpr);
      paramNumImageView5.setImageResource(R.drawable.gpbpr);
      break;
    case 662:
      paramNumImageView1.setImageResource(R.drawable.gpbpl);
      paramNumImageView2.setImageResource(R.drawable.gpbpl);
      paramNumImageView3.setImageResource(R.drawable.gpbpl);
      paramNumImageView4.setImageResource(R.drawable.gpbpl);
      paramNumImageView5.setImageResource(R.drawable.gpbpl);
      break;
    case 661:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprt);
        paramNumImageView2.setImageResource(R.drawable.gprt);
        paramNumImageView3.setImageResource(R.drawable.gprt);
        paramNumImageView4.setImageResource(R.drawable.gprt);
        paramNumImageView5.setImageResource(R.drawable.gprt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2);
        paramNumImageView2.setImageResource(R.drawable.gpr2);
        paramNumImageView3.setImageResource(R.drawable.gpr2);
        paramNumImageView4.setImageResource(R.drawable.gpr2);
        paramNumImageView5.setImageResource(R.drawable.gpr2);
      }
      break;
    case 660:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplt);
        paramNumImageView2.setImageResource(R.drawable.gplt);
        paramNumImageView3.setImageResource(R.drawable.gplt);
        paramNumImageView4.setImageResource(R.drawable.gplt);
        paramNumImageView5.setImageResource(R.drawable.gplt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2);
        paramNumImageView2.setImageResource(R.drawable.gpl2);
        paramNumImageView3.setImageResource(R.drawable.gpl2);
        paramNumImageView4.setImageResource(R.drawable.gpl2);
        paramNumImageView5.setImageResource(R.drawable.gpl2);
      }
      break;
    case 659:
      paramNumImageView1.setImageResource(R.drawable.gpr3);
      paramNumImageView2.setImageResource(R.drawable.gpr3);
      paramNumImageView3.setImageResource(R.drawable.gpr3);
      paramNumImageView4.setImageResource(R.drawable.gpr3);
      paramNumImageView5.setImageResource(R.drawable.gpr3);
      break;
    case 658:
      paramNumImageView1.setImageResource(R.drawable.gpl3);
      paramNumImageView2.setImageResource(R.drawable.gpl3);
      paramNumImageView3.setImageResource(R.drawable.gpl3);
      paramNumImageView4.setImageResource(R.drawable.gpl3);
      paramNumImageView5.setImageResource(R.drawable.gpl3);
      break;
    case 657:
      paramNumImageView1.setImageResource(R.drawable.gpselect);
      paramNumImageView2.setImageResource(R.drawable.gpselect);
      paramNumImageView3.setImageResource(R.drawable.gpselect);
      paramNumImageView4.setImageResource(R.drawable.gpselect);
      paramNumImageView5.setImageResource(R.drawable.gpselect);
      break;
    case 656:
      paramNumImageView1.setImageResource(R.drawable.gpstart);
      paramNumImageView2.setImageResource(R.drawable.gpstart);
      paramNumImageView3.setImageResource(R.drawable.gpstart);
      paramNumImageView4.setImageResource(R.drawable.gpstart);
      paramNumImageView5.setImageResource(R.drawable.gpstart);
      break;
    case 655:
      paramNumImageView1.setImageResource(R.drawable.gpright);
      paramNumImageView2.setImageResource(R.drawable.gpright);
      paramNumImageView3.setImageResource(R.drawable.gpright);
      paramNumImageView4.setImageResource(R.drawable.gpright);
      paramNumImageView5.setImageResource(R.drawable.gpright);
      break;
    case 654:
      paramNumImageView1.setImageResource(R.drawable.gpleft);
      paramNumImageView2.setImageResource(R.drawable.gpleft);
      paramNumImageView3.setImageResource(R.drawable.gpleft);
      paramNumImageView4.setImageResource(R.drawable.gpleft);
      paramNumImageView5.setImageResource(R.drawable.gpleft);
      break;
    case 653:
      paramNumImageView1.setImageResource(R.drawable.gpdown);
      paramNumImageView2.setImageResource(R.drawable.gpdown);
      paramNumImageView3.setImageResource(R.drawable.gpdown);
      paramNumImageView4.setImageResource(R.drawable.gpdown);
      paramNumImageView5.setImageResource(R.drawable.gpdown);
      break;
    case 652:
      paramNumImageView1.setImageResource(R.drawable.gpup);
      paramNumImageView2.setImageResource(R.drawable.gpup);
      paramNumImageView3.setImageResource(R.drawable.gpup);
      paramNumImageView4.setImageResource(R.drawable.gpup);
      paramNumImageView5.setImageResource(R.drawable.gpup);
      break;
    case 651:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprb);
        paramNumImageView2.setImageResource(R.drawable.gprb);
        paramNumImageView3.setImageResource(R.drawable.gprb);
        paramNumImageView4.setImageResource(R.drawable.gprb);
        paramNumImageView5.setImageResource(R.drawable.gprb);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1);
        paramNumImageView2.setImageResource(R.drawable.gpr1);
        paramNumImageView3.setImageResource(R.drawable.gpr1);
        paramNumImageView4.setImageResource(R.drawable.gpr1);
        paramNumImageView5.setImageResource(R.drawable.gpr1);
      }
      break;
    case 650:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplb);
        paramNumImageView2.setImageResource(R.drawable.gplb);
        paramNumImageView3.setImageResource(R.drawable.gplb);
        paramNumImageView4.setImageResource(R.drawable.gplb);
        paramNumImageView5.setImageResource(R.drawable.gplb);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1);
        paramNumImageView2.setImageResource(R.drawable.gpl1);
        paramNumImageView3.setImageResource(R.drawable.gpl1);
        paramNumImageView4.setImageResource(R.drawable.gpl1);
        paramNumImageView5.setImageResource(R.drawable.gpl1);
      }
      break;
    case 649:
      paramNumImageView1.setImageResource(R.drawable.gpy);
      paramNumImageView2.setImageResource(R.drawable.gpy);
      paramNumImageView3.setImageResource(R.drawable.gpy);
      paramNumImageView4.setImageResource(R.drawable.gpy);
      paramNumImageView5.setImageResource(R.drawable.gpy);
      break;
    case 648:
      paramNumImageView1.setImageResource(R.drawable.gpx);
      paramNumImageView2.setImageResource(R.drawable.gpx);
      paramNumImageView3.setImageResource(R.drawable.gpx);
      paramNumImageView4.setImageResource(R.drawable.gpx);
      paramNumImageView5.setImageResource(R.drawable.gpx);
      break;
    case 647:
      paramNumImageView1.setImageResource(R.drawable.gpb);
      paramNumImageView2.setImageResource(R.drawable.gpb);
      paramNumImageView3.setImageResource(R.drawable.gpb);
      paramNumImageView4.setImageResource(R.drawable.gpb);
      paramNumImageView5.setImageResource(R.drawable.gpb);
      break;
    case 646:
      paramNumImageView1.setImageResource(R.drawable.gpa);
      paramNumImageView2.setImageResource(R.drawable.gpa);
      paramNumImageView3.setImageResource(R.drawable.gpa);
      paramNumImageView4.setImageResource(R.drawable.gpa);
      paramNumImageView5.setImageResource(R.drawable.gpa);
      break;
    case 641:
    case 642:
    case 643:
    case 644:
    case 645:
      paramNumImageView1.setImageResource(R.drawable.newkey);
      paramNumImageView2.setImageResource(R.drawable.newkey);
      paramNumImageView3.setImageResource(R.drawable.newkey);
      paramNumImageView4.setImageResource(R.drawable.newkey);
      paramNumImageView5.setImageResource(R.drawable.newkey);
      break;
    case 640:
      paramNumImageView1.setImageResource(R.drawable.center3);
      paramNumImageView2.setImageResource(R.drawable.center3);
      paramNumImageView3.setImageResource(R.drawable.center3);
      paramNumImageView4.setImageResource(R.drawable.center3);
      paramNumImageView5.setImageResource(R.drawable.center3);
      break;
    case 639:
      paramNumImageView1.setImageResource(R.drawable.gpbpr2);
      paramNumImageView2.setImageResource(R.drawable.gpbpr2);
      paramNumImageView3.setImageResource(R.drawable.gpbpr2);
      paramNumImageView4.setImageResource(R.drawable.gpbpr2);
      paramNumImageView5.setImageResource(R.drawable.gpbpr2);
      break;
    case 638:
      paramNumImageView1.setImageResource(R.drawable.center2);
      paramNumImageView2.setImageResource(R.drawable.center2);
      paramNumImageView3.setImageResource(R.drawable.center2);
      paramNumImageView4.setImageResource(R.drawable.center2);
      paramNumImageView5.setImageResource(R.drawable.center2);
      break;
    case 637:
      paramNumImageView1.setImageResource(R.drawable.gpbpr1);
      paramNumImageView2.setImageResource(R.drawable.gpbpr1);
      paramNumImageView3.setImageResource(R.drawable.gpbpr1);
      paramNumImageView4.setImageResource(R.drawable.gpbpr1);
      paramNumImageView5.setImageResource(R.drawable.gpbpr1);
      break;
    case 636:
      paramNumImageView1.setImageResource(R.drawable.center1);
      paramNumImageView2.setImageResource(R.drawable.center1);
      paramNumImageView3.setImageResource(R.drawable.center1);
      paramNumImageView4.setImageResource(R.drawable.center1);
      paramNumImageView5.setImageResource(R.drawable.center1);
      break;
    case 635:
      paramNumImageView1.setImageResource(R.drawable.gpm4);
      paramNumImageView2.setImageResource(R.drawable.gpm4);
      paramNumImageView3.setImageResource(R.drawable.gpm4);
      paramNumImageView4.setImageResource(R.drawable.gpm4);
      paramNumImageView5.setImageResource(R.drawable.gpm4);
      break;
    case 634:
      paramNumImageView1.setImageResource(R.drawable.gpm3);
      paramNumImageView2.setImageResource(R.drawable.gpm3);
      paramNumImageView3.setImageResource(R.drawable.gpm3);
      paramNumImageView4.setImageResource(R.drawable.gpm3);
      paramNumImageView5.setImageResource(R.drawable.gpm3);
      break;
    case 633:
      paramNumImageView1.setImageResource(R.drawable.gpm2);
      paramNumImageView2.setImageResource(R.drawable.gpm2);
      paramNumImageView3.setImageResource(R.drawable.gpm2);
      paramNumImageView4.setImageResource(R.drawable.gpm2);
      paramNumImageView5.setImageResource(R.drawable.gpm2);
      break;
    case 632:
      paramNumImageView1.setImageResource(R.drawable.gpm1);
      paramNumImageView2.setImageResource(R.drawable.gpm1);
      paramNumImageView3.setImageResource(R.drawable.gpm1);
      paramNumImageView4.setImageResource(R.drawable.gpm1);
      paramNumImageView5.setImageResource(R.drawable.gpm1);
      break;
    case 631:
      paramNumImageView1.setImageResource(R.drawable.gpbpr);
      paramNumImageView2.setImageResource(R.drawable.gpbpr);
      paramNumImageView3.setImageResource(R.drawable.gpbpr);
      paramNumImageView4.setImageResource(R.drawable.gpbpr);
      paramNumImageView5.setImageResource(R.drawable.gpbpr);
      break;
    case 630:
      paramNumImageView1.setImageResource(R.drawable.gpbpl);
      paramNumImageView2.setImageResource(R.drawable.gpbpl);
      paramNumImageView3.setImageResource(R.drawable.gpbpl);
      paramNumImageView4.setImageResource(R.drawable.gpbpl);
      paramNumImageView5.setImageResource(R.drawable.gpbpl);
      break;
    case 629:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprt);
        paramNumImageView2.setImageResource(R.drawable.gprt);
        paramNumImageView3.setImageResource(R.drawable.gprt);
        paramNumImageView4.setImageResource(R.drawable.gprt);
        paramNumImageView5.setImageResource(R.drawable.gprt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2);
        paramNumImageView2.setImageResource(R.drawable.gpr2);
        paramNumImageView3.setImageResource(R.drawable.gpr2);
        paramNumImageView4.setImageResource(R.drawable.gpr2);
        paramNumImageView5.setImageResource(R.drawable.gpr2);
      }
      break;
    case 628:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplt);
        paramNumImageView2.setImageResource(R.drawable.gplt);
        paramNumImageView3.setImageResource(R.drawable.gplt);
        paramNumImageView4.setImageResource(R.drawable.gplt);
        paramNumImageView5.setImageResource(R.drawable.gplt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2);
        paramNumImageView2.setImageResource(R.drawable.gpl2);
        paramNumImageView3.setImageResource(R.drawable.gpl2);
        paramNumImageView4.setImageResource(R.drawable.gpl2);
        paramNumImageView5.setImageResource(R.drawable.gpl2);
      }
      break;
    case 627:
      paramNumImageView1.setImageResource(R.drawable.gpr3);
      paramNumImageView2.setImageResource(R.drawable.gpr3);
      paramNumImageView3.setImageResource(R.drawable.gpr3);
      paramNumImageView4.setImageResource(R.drawable.gpr3);
      paramNumImageView5.setImageResource(R.drawable.gpr3);
      break;
    case 626:
      paramNumImageView1.setImageResource(R.drawable.gpl3);
      paramNumImageView2.setImageResource(R.drawable.gpl3);
      paramNumImageView3.setImageResource(R.drawable.gpl3);
      paramNumImageView4.setImageResource(R.drawable.gpl3);
      paramNumImageView5.setImageResource(R.drawable.gpl3);
      break;
    case 625:
      paramNumImageView1.setImageResource(R.drawable.gpselect);
      paramNumImageView2.setImageResource(R.drawable.gpselect);
      paramNumImageView3.setImageResource(R.drawable.gpselect);
      paramNumImageView4.setImageResource(R.drawable.gpselect);
      paramNumImageView5.setImageResource(R.drawable.gpselect);
      break;
    case 624:
      paramNumImageView1.setImageResource(R.drawable.gpstart);
      paramNumImageView2.setImageResource(R.drawable.gpstart);
      paramNumImageView3.setImageResource(R.drawable.gpstart);
      paramNumImageView4.setImageResource(R.drawable.gpstart);
      paramNumImageView5.setImageResource(R.drawable.gpstart);
      break;
    case 623:
      paramNumImageView1.setImageResource(R.drawable.gpright);
      paramNumImageView2.setImageResource(R.drawable.gpright);
      paramNumImageView3.setImageResource(R.drawable.gpright);
      paramNumImageView4.setImageResource(R.drawable.gpright);
      paramNumImageView5.setImageResource(R.drawable.gpright);
      break;
    case 622:
      paramNumImageView1.setImageResource(R.drawable.gpleft);
      paramNumImageView2.setImageResource(R.drawable.gpleft);
      paramNumImageView3.setImageResource(R.drawable.gpleft);
      paramNumImageView4.setImageResource(R.drawable.gpleft);
      paramNumImageView5.setImageResource(R.drawable.gpleft);
      break;
    case 621:
      paramNumImageView1.setImageResource(R.drawable.gpdown);
      paramNumImageView2.setImageResource(R.drawable.gpdown);
      paramNumImageView3.setImageResource(R.drawable.gpdown);
      paramNumImageView4.setImageResource(R.drawable.gpdown);
      paramNumImageView5.setImageResource(R.drawable.gpdown);
      break;
    case 620:
      paramNumImageView1.setImageResource(R.drawable.gpup);
      paramNumImageView2.setImageResource(R.drawable.gpup);
      paramNumImageView3.setImageResource(R.drawable.gpup);
      paramNumImageView4.setImageResource(R.drawable.gpup);
      paramNumImageView5.setImageResource(R.drawable.gpup);
      break;
    case 619:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprb);
        paramNumImageView2.setImageResource(R.drawable.gprb);
        paramNumImageView3.setImageResource(R.drawable.gprb);
        paramNumImageView4.setImageResource(R.drawable.gprb);
        paramNumImageView5.setImageResource(R.drawable.gprb);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1);
        paramNumImageView2.setImageResource(R.drawable.gpr1);
        paramNumImageView3.setImageResource(R.drawable.gpr1);
        paramNumImageView4.setImageResource(R.drawable.gpr1);
        paramNumImageView5.setImageResource(R.drawable.gpr1);
      }
      break;
    case 618:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplb);
        paramNumImageView2.setImageResource(R.drawable.gplb);
        paramNumImageView3.setImageResource(R.drawable.gplb);
        paramNumImageView4.setImageResource(R.drawable.gplb);
        paramNumImageView5.setImageResource(R.drawable.gplb);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1);
        paramNumImageView2.setImageResource(R.drawable.gpl1);
        paramNumImageView3.setImageResource(R.drawable.gpl1);
        paramNumImageView4.setImageResource(R.drawable.gpl1);
        paramNumImageView5.setImageResource(R.drawable.gpl1);
      }
      break;
    case 617:
      paramNumImageView1.setImageResource(R.drawable.gpy);
      paramNumImageView2.setImageResource(R.drawable.gpy);
      paramNumImageView3.setImageResource(R.drawable.gpy);
      paramNumImageView4.setImageResource(R.drawable.gpy);
      paramNumImageView5.setImageResource(R.drawable.gpy);
      break;
    case 616:
      paramNumImageView1.setImageResource(R.drawable.gpx);
      paramNumImageView2.setImageResource(R.drawable.gpx);
      paramNumImageView3.setImageResource(R.drawable.gpx);
      paramNumImageView4.setImageResource(R.drawable.gpx);
      paramNumImageView5.setImageResource(R.drawable.gpx);
      break;
    case 615:
      paramNumImageView1.setImageResource(R.drawable.gpb);
      paramNumImageView2.setImageResource(R.drawable.gpb);
      paramNumImageView3.setImageResource(R.drawable.gpb);
      paramNumImageView4.setImageResource(R.drawable.gpb);
      paramNumImageView5.setImageResource(R.drawable.gpb);
      break;
    case 614:
      paramNumImageView1.setImageResource(R.drawable.gpa);
      paramNumImageView2.setImageResource(R.drawable.gpa);
      paramNumImageView3.setImageResource(R.drawable.gpa);
      paramNumImageView4.setImageResource(R.drawable.gpa);
      paramNumImageView5.setImageResource(R.drawable.gpa);
      break;
    case 609:
    case 610:
    case 611:
    case 612:
    case 613:
      paramNumImageView1.setImageResource(R.drawable.newkey);
      paramNumImageView2.setImageResource(R.drawable.newkey);
      paramNumImageView3.setImageResource(R.drawable.newkey);
      paramNumImageView4.setImageResource(R.drawable.newkey);
      paramNumImageView5.setImageResource(R.drawable.newkey);
      break;
    case 608:
      paramNumImageView1.setImageResource(R.drawable.center3);
      paramNumImageView2.setImageResource(R.drawable.center3);
      paramNumImageView3.setImageResource(R.drawable.center3);
      paramNumImageView4.setImageResource(R.drawable.center3);
      paramNumImageView5.setImageResource(R.drawable.center3);
      break;
    case 607:
      paramNumImageView1.setImageResource(R.drawable.gpbpr2);
      paramNumImageView2.setImageResource(R.drawable.gpbpr2);
      paramNumImageView3.setImageResource(R.drawable.gpbpr2);
      paramNumImageView4.setImageResource(R.drawable.gpbpr2);
      paramNumImageView5.setImageResource(R.drawable.gpbpr2);
      break;
    case 606:
      paramNumImageView1.setImageResource(R.drawable.center2);
      paramNumImageView2.setImageResource(R.drawable.center2);
      paramNumImageView3.setImageResource(R.drawable.center2);
      paramNumImageView4.setImageResource(R.drawable.center2);
      paramNumImageView5.setImageResource(R.drawable.center2);
      break;
    case 605:
      paramNumImageView1.setImageResource(R.drawable.gpbpr1);
      paramNumImageView2.setImageResource(R.drawable.gpbpr1);
      paramNumImageView3.setImageResource(R.drawable.gpbpr1);
      paramNumImageView4.setImageResource(R.drawable.gpbpr1);
      paramNumImageView5.setImageResource(R.drawable.gpbpr1);
      break;
    case 604:
      paramNumImageView1.setImageResource(R.drawable.center1);
      paramNumImageView2.setImageResource(R.drawable.center1);
      paramNumImageView3.setImageResource(R.drawable.center1);
      paramNumImageView4.setImageResource(R.drawable.center1);
      paramNumImageView5.setImageResource(R.drawable.center1);
      break;
    case 603:
      paramNumImageView1.setImageResource(R.drawable.gpm4);
      paramNumImageView2.setImageResource(R.drawable.gpm4);
      paramNumImageView3.setImageResource(R.drawable.gpm4);
      paramNumImageView4.setImageResource(R.drawable.gpm4);
      paramNumImageView5.setImageResource(R.drawable.gpm4);
      break;
    case 602:
      paramNumImageView1.setImageResource(R.drawable.gpm3);
      paramNumImageView2.setImageResource(R.drawable.gpm3);
      paramNumImageView3.setImageResource(R.drawable.gpm3);
      paramNumImageView4.setImageResource(R.drawable.gpm3);
      paramNumImageView5.setImageResource(R.drawable.gpm3);
      break;
    case 601:
      paramNumImageView1.setImageResource(R.drawable.gpm2);
      paramNumImageView2.setImageResource(R.drawable.gpm2);
      paramNumImageView3.setImageResource(R.drawable.gpm2);
      paramNumImageView4.setImageResource(R.drawable.gpm2);
      paramNumImageView5.setImageResource(R.drawable.gpm2);
      break;
    case 600:
      paramNumImageView1.setImageResource(R.drawable.gpm1);
      paramNumImageView2.setImageResource(R.drawable.gpm1);
      paramNumImageView3.setImageResource(R.drawable.gpm1);
      paramNumImageView4.setImageResource(R.drawable.gpm1);
      paramNumImageView5.setImageResource(R.drawable.gpm1);
      break;
    case 599:
      paramNumImageView1.setImageResource(R.drawable.gpbpr);
      paramNumImageView2.setImageResource(R.drawable.gpbpr);
      paramNumImageView3.setImageResource(R.drawable.gpbpr);
      paramNumImageView4.setImageResource(R.drawable.gpbpr);
      paramNumImageView5.setImageResource(R.drawable.gpbpr);
      break;
    case 598:
      paramNumImageView1.setImageResource(R.drawable.gpbpl);
      paramNumImageView2.setImageResource(R.drawable.gpbpl);
      paramNumImageView3.setImageResource(R.drawable.gpbpl);
      paramNumImageView4.setImageResource(R.drawable.gpbpl);
      paramNumImageView5.setImageResource(R.drawable.gpbpl);
      break;
    case 597:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprt);
        paramNumImageView2.setImageResource(R.drawable.gprt);
        paramNumImageView3.setImageResource(R.drawable.gprt);
        paramNumImageView4.setImageResource(R.drawable.gprt);
        paramNumImageView5.setImageResource(R.drawable.gprt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2);
        paramNumImageView2.setImageResource(R.drawable.gpr2);
        paramNumImageView3.setImageResource(R.drawable.gpr2);
        paramNumImageView4.setImageResource(R.drawable.gpr2);
        paramNumImageView5.setImageResource(R.drawable.gpr2);
      }
      break;
    case 596:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplt);
        paramNumImageView2.setImageResource(R.drawable.gplt);
        paramNumImageView3.setImageResource(R.drawable.gplt);
        paramNumImageView4.setImageResource(R.drawable.gplt);
        paramNumImageView5.setImageResource(R.drawable.gplt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2);
        paramNumImageView2.setImageResource(R.drawable.gpl2);
        paramNumImageView3.setImageResource(R.drawable.gpl2);
        paramNumImageView4.setImageResource(R.drawable.gpl2);
        paramNumImageView5.setImageResource(R.drawable.gpl2);
      }
      break;
    case 595:
      paramNumImageView1.setImageResource(R.drawable.gpr3);
      paramNumImageView2.setImageResource(R.drawable.gpr3);
      paramNumImageView3.setImageResource(R.drawable.gpr3);
      paramNumImageView4.setImageResource(R.drawable.gpr3);
      paramNumImageView5.setImageResource(R.drawable.gpr3);
      break;
    case 594:
      paramNumImageView1.setImageResource(R.drawable.gpl3);
      paramNumImageView2.setImageResource(R.drawable.gpl3);
      paramNumImageView3.setImageResource(R.drawable.gpl3);
      paramNumImageView4.setImageResource(R.drawable.gpl3);
      paramNumImageView5.setImageResource(R.drawable.gpl3);
      break;
    case 593:
      paramNumImageView1.setImageResource(R.drawable.gpselect);
      paramNumImageView2.setImageResource(R.drawable.gpselect);
      paramNumImageView3.setImageResource(R.drawable.gpselect);
      paramNumImageView4.setImageResource(R.drawable.gpselect);
      paramNumImageView5.setImageResource(R.drawable.gpselect);
      break;
    case 592:
      paramNumImageView1.setImageResource(R.drawable.gpstart);
      paramNumImageView2.setImageResource(R.drawable.gpstart);
      paramNumImageView3.setImageResource(R.drawable.gpstart);
      paramNumImageView4.setImageResource(R.drawable.gpstart);
      paramNumImageView5.setImageResource(R.drawable.gpstart);
      break;
    case 591:
      paramNumImageView1.setImageResource(R.drawable.gpright);
      paramNumImageView2.setImageResource(R.drawable.gpright);
      paramNumImageView3.setImageResource(R.drawable.gpright);
      paramNumImageView4.setImageResource(R.drawable.gpright);
      paramNumImageView5.setImageResource(R.drawable.gpright);
      break;
    case 590:
      paramNumImageView1.setImageResource(R.drawable.gpleft);
      paramNumImageView2.setImageResource(R.drawable.gpleft);
      paramNumImageView3.setImageResource(R.drawable.gpleft);
      paramNumImageView4.setImageResource(R.drawable.gpleft);
      paramNumImageView5.setImageResource(R.drawable.gpleft);
      break;
    case 589:
      paramNumImageView1.setImageResource(R.drawable.gpdown);
      paramNumImageView2.setImageResource(R.drawable.gpdown);
      paramNumImageView3.setImageResource(R.drawable.gpdown);
      paramNumImageView4.setImageResource(R.drawable.gpdown);
      paramNumImageView5.setImageResource(R.drawable.gpdown);
      break;
    case 588:
      paramNumImageView1.setImageResource(R.drawable.gpup);
      paramNumImageView2.setImageResource(R.drawable.gpup);
      paramNumImageView3.setImageResource(R.drawable.gpup);
      paramNumImageView4.setImageResource(R.drawable.gpup);
      paramNumImageView5.setImageResource(R.drawable.gpup);
      break;
    case 587:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprb);
        paramNumImageView2.setImageResource(R.drawable.gprb);
        paramNumImageView3.setImageResource(R.drawable.gprb);
        paramNumImageView4.setImageResource(R.drawable.gprb);
        paramNumImageView5.setImageResource(R.drawable.gprb);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1);
        paramNumImageView2.setImageResource(R.drawable.gpr1);
        paramNumImageView3.setImageResource(R.drawable.gpr1);
        paramNumImageView4.setImageResource(R.drawable.gpr1);
        paramNumImageView5.setImageResource(R.drawable.gpr1);
      }
      break;
    case 586:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplb);
        paramNumImageView2.setImageResource(R.drawable.gplb);
        paramNumImageView3.setImageResource(R.drawable.gplb);
        paramNumImageView4.setImageResource(R.drawable.gplb);
        paramNumImageView5.setImageResource(R.drawable.gplb);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1);
        paramNumImageView2.setImageResource(R.drawable.gpl1);
        paramNumImageView3.setImageResource(R.drawable.gpl1);
        paramNumImageView4.setImageResource(R.drawable.gpl1);
        paramNumImageView5.setImageResource(R.drawable.gpl1);
      }
      break;
    case 585:
      paramNumImageView1.setImageResource(R.drawable.gpy);
      paramNumImageView2.setImageResource(R.drawable.gpy);
      paramNumImageView3.setImageResource(R.drawable.gpy);
      paramNumImageView4.setImageResource(R.drawable.gpy);
      paramNumImageView5.setImageResource(R.drawable.gpy);
      break;
    case 584:
      paramNumImageView1.setImageResource(R.drawable.gpx);
      paramNumImageView2.setImageResource(R.drawable.gpx);
      paramNumImageView3.setImageResource(R.drawable.gpx);
      paramNumImageView4.setImageResource(R.drawable.gpx);
      paramNumImageView5.setImageResource(R.drawable.gpx);
      break;
    case 583:
      paramNumImageView1.setImageResource(R.drawable.gpb);
      paramNumImageView2.setImageResource(R.drawable.gpb);
      paramNumImageView3.setImageResource(R.drawable.gpb);
      paramNumImageView4.setImageResource(R.drawable.gpb);
      paramNumImageView5.setImageResource(R.drawable.gpb);
      break;
    case 582:
      paramNumImageView1.setImageResource(R.drawable.gpa);
      paramNumImageView2.setImageResource(R.drawable.gpa);
      paramNumImageView3.setImageResource(R.drawable.gpa);
      paramNumImageView4.setImageResource(R.drawable.gpa);
      paramNumImageView5.setImageResource(R.drawable.gpa);
      break;
    case 577:
    case 578:
    case 579:
    case 580:
    case 581:
      paramNumImageView1.setImageResource(R.drawable.newkey);
      paramNumImageView2.setImageResource(R.drawable.newkey);
      paramNumImageView3.setImageResource(R.drawable.newkey);
      paramNumImageView4.setImageResource(R.drawable.newkey);
      paramNumImageView5.setImageResource(R.drawable.newkey);
      break;
    case 576:
      paramNumImageView1.setImageResource(R.drawable.center3);
      paramNumImageView2.setImageResource(R.drawable.center3);
      paramNumImageView3.setImageResource(R.drawable.center3);
      paramNumImageView4.setImageResource(R.drawable.center3);
      paramNumImageView5.setImageResource(R.drawable.center3);
      break;
    case 575:
      paramNumImageView1.setImageResource(R.drawable.gpbpr2);
      paramNumImageView2.setImageResource(R.drawable.gpbpr2);
      paramNumImageView3.setImageResource(R.drawable.gpbpr2);
      paramNumImageView4.setImageResource(R.drawable.gpbpr2);
      paramNumImageView5.setImageResource(R.drawable.gpbpr2);
      break;
    case 574:
      paramNumImageView1.setImageResource(R.drawable.center2);
      paramNumImageView2.setImageResource(R.drawable.center2);
      paramNumImageView3.setImageResource(R.drawable.center2);
      paramNumImageView4.setImageResource(R.drawable.center2);
      paramNumImageView5.setImageResource(R.drawable.center2);
      break;
    case 573:
      paramNumImageView1.setImageResource(R.drawable.gpbpr1);
      paramNumImageView2.setImageResource(R.drawable.gpbpr1);
      paramNumImageView3.setImageResource(R.drawable.gpbpr1);
      paramNumImageView4.setImageResource(R.drawable.gpbpr1);
      paramNumImageView5.setImageResource(R.drawable.gpbpr1);
      break;
    case 572:
      paramNumImageView1.setImageResource(R.drawable.center1);
      paramNumImageView2.setImageResource(R.drawable.center1);
      paramNumImageView3.setImageResource(R.drawable.center1);
      paramNumImageView4.setImageResource(R.drawable.center1);
      paramNumImageView5.setImageResource(R.drawable.center1);
      break;
    case 571:
      paramNumImageView1.setImageResource(R.drawable.gpm4);
      paramNumImageView2.setImageResource(R.drawable.gpm4);
      paramNumImageView3.setImageResource(R.drawable.gpm4);
      paramNumImageView4.setImageResource(R.drawable.gpm4);
      paramNumImageView5.setImageResource(R.drawable.gpm4);
      break;
    case 570:
      paramNumImageView1.setImageResource(R.drawable.gpm3);
      paramNumImageView2.setImageResource(R.drawable.gpm3);
      paramNumImageView3.setImageResource(R.drawable.gpm3);
      paramNumImageView4.setImageResource(R.drawable.gpm3);
      paramNumImageView5.setImageResource(R.drawable.gpm3);
      break;
    case 569:
      paramNumImageView1.setImageResource(R.drawable.gpm2);
      paramNumImageView2.setImageResource(R.drawable.gpm2);
      paramNumImageView3.setImageResource(R.drawable.gpm2);
      paramNumImageView4.setImageResource(R.drawable.gpm2);
      paramNumImageView5.setImageResource(R.drawable.gpm2);
      break;
    case 568:
      paramNumImageView1.setImageResource(R.drawable.gpm1);
      paramNumImageView2.setImageResource(R.drawable.gpm1);
      paramNumImageView3.setImageResource(R.drawable.gpm1);
      paramNumImageView4.setImageResource(R.drawable.gpm1);
      paramNumImageView5.setImageResource(R.drawable.gpm1);
      break;
    case 567:
      paramNumImageView1.setImageResource(R.drawable.gpbpr);
      paramNumImageView2.setImageResource(R.drawable.gpbpr);
      paramNumImageView3.setImageResource(R.drawable.gpbpr);
      paramNumImageView4.setImageResource(R.drawable.gpbpr);
      paramNumImageView5.setImageResource(R.drawable.gpbpr);
      break;
    case 566:
      paramNumImageView1.setImageResource(R.drawable.gpbpl);
      paramNumImageView2.setImageResource(R.drawable.gpbpl);
      paramNumImageView3.setImageResource(R.drawable.gpbpl);
      paramNumImageView4.setImageResource(R.drawable.gpbpl);
      paramNumImageView5.setImageResource(R.drawable.gpbpl);
      break;
    case 565:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprt);
        paramNumImageView2.setImageResource(R.drawable.gprt);
        paramNumImageView3.setImageResource(R.drawable.gprt);
        paramNumImageView4.setImageResource(R.drawable.gprt);
        paramNumImageView5.setImageResource(R.drawable.gprt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2);
        paramNumImageView2.setImageResource(R.drawable.gpr2);
        paramNumImageView3.setImageResource(R.drawable.gpr2);
        paramNumImageView4.setImageResource(R.drawable.gpr2);
        paramNumImageView5.setImageResource(R.drawable.gpr2);
      }
      break;
    case 564:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplt);
        paramNumImageView2.setImageResource(R.drawable.gplt);
        paramNumImageView3.setImageResource(R.drawable.gplt);
        paramNumImageView4.setImageResource(R.drawable.gplt);
        paramNumImageView5.setImageResource(R.drawable.gplt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2);
        paramNumImageView2.setImageResource(R.drawable.gpl2);
        paramNumImageView3.setImageResource(R.drawable.gpl2);
        paramNumImageView4.setImageResource(R.drawable.gpl2);
        paramNumImageView5.setImageResource(R.drawable.gpl2);
      }
      break;
    case 563:
      paramNumImageView1.setImageResource(R.drawable.gpr3);
      paramNumImageView2.setImageResource(R.drawable.gpr3);
      paramNumImageView3.setImageResource(R.drawable.gpr3);
      paramNumImageView4.setImageResource(R.drawable.gpr3);
      paramNumImageView5.setImageResource(R.drawable.gpr3);
      break;
    case 562:
      paramNumImageView1.setImageResource(R.drawable.gpl3);
      paramNumImageView2.setImageResource(R.drawable.gpl3);
      paramNumImageView3.setImageResource(R.drawable.gpl3);
      paramNumImageView4.setImageResource(R.drawable.gpl3);
      paramNumImageView5.setImageResource(R.drawable.gpl3);
      break;
    case 561:
      paramNumImageView1.setImageResource(R.drawable.gpselect);
      paramNumImageView2.setImageResource(R.drawable.gpselect);
      paramNumImageView3.setImageResource(R.drawable.gpselect);
      paramNumImageView4.setImageResource(R.drawable.gpselect);
      paramNumImageView5.setImageResource(R.drawable.gpselect);
      break;
    case 560:
      paramNumImageView1.setImageResource(R.drawable.gpstart);
      paramNumImageView2.setImageResource(R.drawable.gpstart);
      paramNumImageView3.setImageResource(R.drawable.gpstart);
      paramNumImageView4.setImageResource(R.drawable.gpstart);
      paramNumImageView5.setImageResource(R.drawable.gpstart);
      break;
    case 559:
      paramNumImageView1.setImageResource(R.drawable.gpright);
      paramNumImageView2.setImageResource(R.drawable.gpright);
      paramNumImageView3.setImageResource(R.drawable.gpright);
      paramNumImageView4.setImageResource(R.drawable.gpright);
      paramNumImageView5.setImageResource(R.drawable.gpright);
      break;
    case 558:
      paramNumImageView1.setImageResource(R.drawable.gpleft);
      paramNumImageView2.setImageResource(R.drawable.gpleft);
      paramNumImageView3.setImageResource(R.drawable.gpleft);
      paramNumImageView4.setImageResource(R.drawable.gpleft);
      paramNumImageView5.setImageResource(R.drawable.gpleft);
      break;
    case 557:
      paramNumImageView1.setImageResource(R.drawable.gpdown);
      paramNumImageView2.setImageResource(R.drawable.gpdown);
      paramNumImageView3.setImageResource(R.drawable.gpdown);
      paramNumImageView4.setImageResource(R.drawable.gpdown);
      paramNumImageView5.setImageResource(R.drawable.gpdown);
      break;
    case 556:
      paramNumImageView1.setImageResource(R.drawable.gpup);
      paramNumImageView2.setImageResource(R.drawable.gpup);
      paramNumImageView3.setImageResource(R.drawable.gpup);
      paramNumImageView4.setImageResource(R.drawable.gpup);
      paramNumImageView5.setImageResource(R.drawable.gpup);
      break;
    case 555:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprb);
        paramNumImageView2.setImageResource(R.drawable.gprb);
        paramNumImageView3.setImageResource(R.drawable.gprb);
        paramNumImageView4.setImageResource(R.drawable.gprb);
        paramNumImageView5.setImageResource(R.drawable.gprb);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1);
        paramNumImageView2.setImageResource(R.drawable.gpr1);
        paramNumImageView3.setImageResource(R.drawable.gpr1);
        paramNumImageView4.setImageResource(R.drawable.gpr1);
        paramNumImageView5.setImageResource(R.drawable.gpr1);
      }
      break;
    case 554:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplb);
        paramNumImageView2.setImageResource(R.drawable.gplb);
        paramNumImageView3.setImageResource(R.drawable.gplb);
        paramNumImageView4.setImageResource(R.drawable.gplb);
        paramNumImageView5.setImageResource(R.drawable.gplb);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1);
        paramNumImageView2.setImageResource(R.drawable.gpl1);
        paramNumImageView3.setImageResource(R.drawable.gpl1);
        paramNumImageView4.setImageResource(R.drawable.gpl1);
        paramNumImageView5.setImageResource(R.drawable.gpl1);
      }
      break;
    case 553:
      paramNumImageView1.setImageResource(R.drawable.gpy);
      paramNumImageView2.setImageResource(R.drawable.gpy);
      paramNumImageView3.setImageResource(R.drawable.gpy);
      paramNumImageView4.setImageResource(R.drawable.gpy);
      paramNumImageView5.setImageResource(R.drawable.gpy);
      break;
    case 552:
      paramNumImageView1.setImageResource(R.drawable.gpx);
      paramNumImageView2.setImageResource(R.drawable.gpx);
      paramNumImageView3.setImageResource(R.drawable.gpx);
      paramNumImageView4.setImageResource(R.drawable.gpx);
      paramNumImageView5.setImageResource(R.drawable.gpx);
      break;
    case 551:
      paramNumImageView1.setImageResource(R.drawable.gpb);
      paramNumImageView2.setImageResource(R.drawable.gpb);
      paramNumImageView3.setImageResource(R.drawable.gpb);
      paramNumImageView4.setImageResource(R.drawable.gpb);
      paramNumImageView5.setImageResource(R.drawable.gpb);
      break;
    case 550:
      paramNumImageView1.setImageResource(R.drawable.gpa);
      paramNumImageView2.setImageResource(R.drawable.gpa);
      paramNumImageView3.setImageResource(R.drawable.gpa);
      paramNumImageView4.setImageResource(R.drawable.gpa);
      paramNumImageView5.setImageResource(R.drawable.gpa);
      break;
    case 545:
    case 546:
    case 547:
    case 548:
    case 549:
      paramNumImageView1.setImageResource(R.drawable.newkey);
      paramNumImageView2.setImageResource(R.drawable.newkey);
      paramNumImageView3.setImageResource(R.drawable.newkey);
      paramNumImageView4.setImageResource(R.drawable.newkey);
      paramNumImageView5.setImageResource(R.drawable.newkey);
      break;
    case 544:
      paramNumImageView1.setImageResource(R.drawable.center3);
      paramNumImageView2.setImageResource(R.drawable.center3);
      paramNumImageView3.setImageResource(R.drawable.center3);
      paramNumImageView4.setImageResource(R.drawable.center3);
      paramNumImageView5.setImageResource(R.drawable.center3);
      break;
    case 543:
      paramNumImageView1.setImageResource(R.drawable.gpbpr2);
      paramNumImageView2.setImageResource(R.drawable.gpbpr2);
      paramNumImageView3.setImageResource(R.drawable.gpbpr2);
      paramNumImageView4.setImageResource(R.drawable.gpbpr2);
      paramNumImageView5.setImageResource(R.drawable.gpbpr2);
      break;
    case 542:
      paramNumImageView1.setImageResource(R.drawable.center2);
      paramNumImageView2.setImageResource(R.drawable.center2);
      paramNumImageView3.setImageResource(R.drawable.center2);
      paramNumImageView4.setImageResource(R.drawable.center2);
      paramNumImageView5.setImageResource(R.drawable.center2);
      break;
    case 541:
      paramNumImageView1.setImageResource(R.drawable.gpbpr1);
      paramNumImageView2.setImageResource(R.drawable.gpbpr1);
      paramNumImageView3.setImageResource(R.drawable.gpbpr1);
      paramNumImageView4.setImageResource(R.drawable.gpbpr1);
      paramNumImageView5.setImageResource(R.drawable.gpbpr1);
      break;
    case 540:
      paramNumImageView1.setImageResource(R.drawable.center1);
      paramNumImageView2.setImageResource(R.drawable.center1);
      paramNumImageView3.setImageResource(R.drawable.center1);
      paramNumImageView4.setImageResource(R.drawable.center1);
      paramNumImageView5.setImageResource(R.drawable.center1);
      break;
    case 539:
      paramNumImageView1.setImageResource(R.drawable.gpm4);
      paramNumImageView2.setImageResource(R.drawable.gpm4);
      paramNumImageView3.setImageResource(R.drawable.gpm4);
      paramNumImageView4.setImageResource(R.drawable.gpm4);
      paramNumImageView5.setImageResource(R.drawable.gpm4);
      break;
    case 538:
      paramNumImageView1.setImageResource(R.drawable.gpm3);
      paramNumImageView2.setImageResource(R.drawable.gpm3);
      paramNumImageView3.setImageResource(R.drawable.gpm3);
      paramNumImageView4.setImageResource(R.drawable.gpm3);
      paramNumImageView5.setImageResource(R.drawable.gpm3);
      break;
    case 537:
      paramNumImageView1.setImageResource(R.drawable.gpm2);
      paramNumImageView2.setImageResource(R.drawable.gpm2);
      paramNumImageView3.setImageResource(R.drawable.gpm2);
      paramNumImageView4.setImageResource(R.drawable.gpm2);
      paramNumImageView5.setImageResource(R.drawable.gpm2);
      break;
    case 536:
      paramNumImageView1.setImageResource(R.drawable.gpm1);
      paramNumImageView2.setImageResource(R.drawable.gpm1);
      paramNumImageView3.setImageResource(R.drawable.gpm1);
      paramNumImageView4.setImageResource(R.drawable.gpm1);
      paramNumImageView5.setImageResource(R.drawable.gpm1);
      break;
    case 535:
      paramNumImageView1.setImageResource(R.drawable.gpbpr);
      paramNumImageView2.setImageResource(R.drawable.gpbpr);
      paramNumImageView3.setImageResource(R.drawable.gpbpr);
      paramNumImageView4.setImageResource(R.drawable.gpbpr);
      paramNumImageView5.setImageResource(R.drawable.gpbpr);
      break;
    case 534:
      paramNumImageView1.setImageResource(R.drawable.gpbpl);
      paramNumImageView2.setImageResource(R.drawable.gpbpl);
      paramNumImageView3.setImageResource(R.drawable.gpbpl);
      paramNumImageView4.setImageResource(R.drawable.gpbpl);
      paramNumImageView5.setImageResource(R.drawable.gpbpl);
      break;
    case 533:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprt);
        paramNumImageView2.setImageResource(R.drawable.gprt);
        paramNumImageView3.setImageResource(R.drawable.gprt);
        paramNumImageView4.setImageResource(R.drawable.gprt);
        paramNumImageView5.setImageResource(R.drawable.gprt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2);
        paramNumImageView2.setImageResource(R.drawable.gpr2);
        paramNumImageView3.setImageResource(R.drawable.gpr2);
        paramNumImageView4.setImageResource(R.drawable.gpr2);
        paramNumImageView5.setImageResource(R.drawable.gpr2);
      }
      break;
    case 532:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplt);
        paramNumImageView2.setImageResource(R.drawable.gplt);
        paramNumImageView3.setImageResource(R.drawable.gplt);
        paramNumImageView4.setImageResource(R.drawable.gplt);
        paramNumImageView5.setImageResource(R.drawable.gplt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2);
        paramNumImageView2.setImageResource(R.drawable.gpl2);
        paramNumImageView3.setImageResource(R.drawable.gpl2);
        paramNumImageView4.setImageResource(R.drawable.gpl2);
        paramNumImageView5.setImageResource(R.drawable.gpl2);
      }
      break;
    case 531:
      paramNumImageView1.setImageResource(R.drawable.gpr3);
      paramNumImageView2.setImageResource(R.drawable.gpr3);
      paramNumImageView3.setImageResource(R.drawable.gpr3);
      paramNumImageView4.setImageResource(R.drawable.gpr3);
      paramNumImageView5.setImageResource(R.drawable.gpr3);
      break;
    case 530:
      paramNumImageView1.setImageResource(R.drawable.gpl3);
      paramNumImageView2.setImageResource(R.drawable.gpl3);
      paramNumImageView3.setImageResource(R.drawable.gpl3);
      paramNumImageView4.setImageResource(R.drawable.gpl3);
      paramNumImageView5.setImageResource(R.drawable.gpl3);
      break;
    case 529:
      paramNumImageView1.setImageResource(R.drawable.gpselect);
      paramNumImageView2.setImageResource(R.drawable.gpselect);
      paramNumImageView3.setImageResource(R.drawable.gpselect);
      paramNumImageView4.setImageResource(R.drawable.gpselect);
      paramNumImageView5.setImageResource(R.drawable.gpselect);
      break;
    case 528:
      paramNumImageView1.setImageResource(R.drawable.gpstart);
      paramNumImageView2.setImageResource(R.drawable.gpstart);
      paramNumImageView3.setImageResource(R.drawable.gpstart);
      paramNumImageView4.setImageResource(R.drawable.gpstart);
      paramNumImageView5.setImageResource(R.drawable.gpstart);
      break;
    case 527:
      paramNumImageView1.setImageResource(R.drawable.gpright);
      paramNumImageView2.setImageResource(R.drawable.gpright);
      paramNumImageView3.setImageResource(R.drawable.gpright);
      paramNumImageView4.setImageResource(R.drawable.gpright);
      paramNumImageView5.setImageResource(R.drawable.gpright);
      break;
    case 526:
      paramNumImageView1.setImageResource(R.drawable.gpleft);
      paramNumImageView2.setImageResource(R.drawable.gpleft);
      paramNumImageView3.setImageResource(R.drawable.gpleft);
      paramNumImageView4.setImageResource(R.drawable.gpleft);
      paramNumImageView5.setImageResource(R.drawable.gpleft);
      break;
    case 525:
      paramNumImageView1.setImageResource(R.drawable.gpdown);
      paramNumImageView2.setImageResource(R.drawable.gpdown);
      paramNumImageView3.setImageResource(R.drawable.gpdown);
      paramNumImageView4.setImageResource(R.drawable.gpdown);
      paramNumImageView5.setImageResource(R.drawable.gpdown);
      break;
    case 524:
      paramNumImageView1.setImageResource(R.drawable.gpup);
      paramNumImageView2.setImageResource(R.drawable.gpup);
      paramNumImageView3.setImageResource(R.drawable.gpup);
      paramNumImageView4.setImageResource(R.drawable.gpup);
      paramNumImageView5.setImageResource(R.drawable.gpup);
      break;
    case 523:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprb);
        paramNumImageView2.setImageResource(R.drawable.gprb);
        paramNumImageView3.setImageResource(R.drawable.gprb);
        paramNumImageView4.setImageResource(R.drawable.gprb);
        paramNumImageView5.setImageResource(R.drawable.gprb);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1);
        paramNumImageView2.setImageResource(R.drawable.gpr1);
        paramNumImageView3.setImageResource(R.drawable.gpr1);
        paramNumImageView4.setImageResource(R.drawable.gpr1);
        paramNumImageView5.setImageResource(R.drawable.gpr1);
      }
      break;
    case 522:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplb);
        paramNumImageView2.setImageResource(R.drawable.gplb);
        paramNumImageView3.setImageResource(R.drawable.gplb);
        paramNumImageView4.setImageResource(R.drawable.gplb);
        paramNumImageView5.setImageResource(R.drawable.gplb);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1);
        paramNumImageView2.setImageResource(R.drawable.gpl1);
        paramNumImageView3.setImageResource(R.drawable.gpl1);
        paramNumImageView4.setImageResource(R.drawable.gpl1);
        paramNumImageView5.setImageResource(R.drawable.gpl1);
      }
      break;
    case 521:
      paramNumImageView1.setImageResource(R.drawable.gpy);
      paramNumImageView2.setImageResource(R.drawable.gpy);
      paramNumImageView3.setImageResource(R.drawable.gpy);
      paramNumImageView4.setImageResource(R.drawable.gpy);
      paramNumImageView5.setImageResource(R.drawable.gpy);
      break;
    case 520:
      paramNumImageView1.setImageResource(R.drawable.gpx);
      paramNumImageView2.setImageResource(R.drawable.gpx);
      paramNumImageView3.setImageResource(R.drawable.gpx);
      paramNumImageView4.setImageResource(R.drawable.gpx);
      paramNumImageView5.setImageResource(R.drawable.gpx);
      break;
    case 519:
      paramNumImageView1.setImageResource(R.drawable.gpb);
      paramNumImageView2.setImageResource(R.drawable.gpb);
      paramNumImageView3.setImageResource(R.drawable.gpb);
      paramNumImageView4.setImageResource(R.drawable.gpb);
      paramNumImageView5.setImageResource(R.drawable.gpb);
      break;
    case 518:
      paramNumImageView1.setImageResource(R.drawable.gpa);
      paramNumImageView2.setImageResource(R.drawable.gpa);
      paramNumImageView3.setImageResource(R.drawable.gpa);
      paramNumImageView4.setImageResource(R.drawable.gpa);
      paramNumImageView5.setImageResource(R.drawable.gpa);
      break;
    case 513:
    case 514:
    case 515:
    case 516:
    case 517:
      paramNumImageView1.setImageResource(R.drawable.newkey);
      paramNumImageView2.setImageResource(R.drawable.newkey);
      paramNumImageView3.setImageResource(R.drawable.newkey);
      paramNumImageView4.setImageResource(R.drawable.newkey);
      paramNumImageView5.setImageResource(R.drawable.newkey);
      break;
    case 512:
      paramNumImageView1.setImageResource(R.drawable.center3);
      paramNumImageView2.setImageResource(R.drawable.center3);
      paramNumImageView3.setImageResource(R.drawable.center3);
      paramNumImageView4.setImageResource(R.drawable.center3);
      paramNumImageView5.setImageResource(R.drawable.center3);
      break;
    case 511:
      paramNumImageView1.setImageResource(R.drawable.gpbpr2);
      paramNumImageView2.setImageResource(R.drawable.gpbpr2);
      paramNumImageView3.setImageResource(R.drawable.gpbpr2);
      paramNumImageView4.setImageResource(R.drawable.gpbpr2);
      paramNumImageView5.setImageResource(R.drawable.gpbpr2);
      break;
    case 510:
      paramNumImageView1.setImageResource(R.drawable.center2);
      paramNumImageView2.setImageResource(R.drawable.center2);
      paramNumImageView3.setImageResource(R.drawable.center2);
      paramNumImageView4.setImageResource(R.drawable.center2);
      paramNumImageView5.setImageResource(R.drawable.center2);
      break;
    case 509:
      paramNumImageView1.setImageResource(R.drawable.gpbpr1);
      paramNumImageView2.setImageResource(R.drawable.gpbpr1);
      paramNumImageView3.setImageResource(R.drawable.gpbpr1);
      paramNumImageView4.setImageResource(R.drawable.gpbpr1);
      paramNumImageView5.setImageResource(R.drawable.gpbpr1);
      break;
    case 508:
      paramNumImageView1.setImageResource(R.drawable.center1);
      paramNumImageView2.setImageResource(R.drawable.center1);
      paramNumImageView3.setImageResource(R.drawable.center1);
      paramNumImageView4.setImageResource(R.drawable.center1);
      paramNumImageView5.setImageResource(R.drawable.center1);
      break;
    case 507:
      paramNumImageView1.setImageResource(R.drawable.gpm4);
      paramNumImageView2.setImageResource(R.drawable.gpm4);
      paramNumImageView3.setImageResource(R.drawable.gpm4);
      paramNumImageView4.setImageResource(R.drawable.gpm4);
      paramNumImageView5.setImageResource(R.drawable.gpm4);
      break;
    case 506:
      paramNumImageView1.setImageResource(R.drawable.gpm3);
      paramNumImageView2.setImageResource(R.drawable.gpm3);
      paramNumImageView3.setImageResource(R.drawable.gpm3);
      paramNumImageView4.setImageResource(R.drawable.gpm3);
      paramNumImageView5.setImageResource(R.drawable.gpm3);
      break;
    case 505:
      paramNumImageView1.setImageResource(R.drawable.gpm2);
      paramNumImageView2.setImageResource(R.drawable.gpm2);
      paramNumImageView3.setImageResource(R.drawable.gpm2);
      paramNumImageView4.setImageResource(R.drawable.gpm2);
      paramNumImageView5.setImageResource(R.drawable.gpm2);
      break;
    case 504:
      paramNumImageView1.setImageResource(R.drawable.gpm1);
      paramNumImageView2.setImageResource(R.drawable.gpm1);
      paramNumImageView3.setImageResource(R.drawable.gpm1);
      paramNumImageView4.setImageResource(R.drawable.gpm1);
      paramNumImageView5.setImageResource(R.drawable.gpm1);
      break;
    case 503:
      paramNumImageView1.setImageResource(R.drawable.gpbpr);
      paramNumImageView2.setImageResource(R.drawable.gpbpr);
      paramNumImageView3.setImageResource(R.drawable.gpbpr);
      paramNumImageView4.setImageResource(R.drawable.gpbpr);
      paramNumImageView5.setImageResource(R.drawable.gpbpr);
      break;
    case 502:
      paramNumImageView1.setImageResource(R.drawable.gpbpl);
      paramNumImageView2.setImageResource(R.drawable.gpbpl);
      paramNumImageView3.setImageResource(R.drawable.gpbpl);
      paramNumImageView4.setImageResource(R.drawable.gpbpl);
      paramNumImageView5.setImageResource(R.drawable.gpbpl);
      break;
    case 501:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprt);
        paramNumImageView2.setImageResource(R.drawable.gprt);
        paramNumImageView3.setImageResource(R.drawable.gprt);
        paramNumImageView4.setImageResource(R.drawable.gprt);
        paramNumImageView5.setImageResource(R.drawable.gprt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2);
        paramNumImageView2.setImageResource(R.drawable.gpr2);
        paramNumImageView3.setImageResource(R.drawable.gpr2);
        paramNumImageView4.setImageResource(R.drawable.gpr2);
        paramNumImageView5.setImageResource(R.drawable.gpr2);
      }
      break;
    case 500:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplt);
        paramNumImageView2.setImageResource(R.drawable.gplt);
        paramNumImageView3.setImageResource(R.drawable.gplt);
        paramNumImageView4.setImageResource(R.drawable.gplt);
        paramNumImageView5.setImageResource(R.drawable.gplt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2);
        paramNumImageView2.setImageResource(R.drawable.gpl2);
        paramNumImageView3.setImageResource(R.drawable.gpl2);
        paramNumImageView4.setImageResource(R.drawable.gpl2);
        paramNumImageView5.setImageResource(R.drawable.gpl2);
      }
      break;
    case 499:
      paramNumImageView1.setImageResource(R.drawable.gpr3);
      paramNumImageView2.setImageResource(R.drawable.gpr3);
      paramNumImageView3.setImageResource(R.drawable.gpr3);
      paramNumImageView4.setImageResource(R.drawable.gpr3);
      paramNumImageView5.setImageResource(R.drawable.gpr3);
      break;
    case 498:
      paramNumImageView1.setImageResource(R.drawable.gpl3);
      paramNumImageView2.setImageResource(R.drawable.gpl3);
      paramNumImageView3.setImageResource(R.drawable.gpl3);
      paramNumImageView4.setImageResource(R.drawable.gpl3);
      paramNumImageView5.setImageResource(R.drawable.gpl3);
      break;
    case 497:
      paramNumImageView1.setImageResource(R.drawable.gpselect);
      paramNumImageView2.setImageResource(R.drawable.gpselect);
      paramNumImageView3.setImageResource(R.drawable.gpselect);
      paramNumImageView4.setImageResource(R.drawable.gpselect);
      paramNumImageView5.setImageResource(R.drawable.gpselect);
      break;
    case 496:
      paramNumImageView1.setImageResource(R.drawable.gpstart);
      paramNumImageView2.setImageResource(R.drawable.gpstart);
      paramNumImageView3.setImageResource(R.drawable.gpstart);
      paramNumImageView4.setImageResource(R.drawable.gpstart);
      paramNumImageView5.setImageResource(R.drawable.gpstart);
      break;
    case 495:
      paramNumImageView1.setImageResource(R.drawable.gpright);
      paramNumImageView2.setImageResource(R.drawable.gpright);
      paramNumImageView3.setImageResource(R.drawable.gpright);
      paramNumImageView4.setImageResource(R.drawable.gpright);
      paramNumImageView5.setImageResource(R.drawable.gpright);
      break;
    case 494:
      paramNumImageView1.setImageResource(R.drawable.gpleft);
      paramNumImageView2.setImageResource(R.drawable.gpleft);
      paramNumImageView3.setImageResource(R.drawable.gpleft);
      paramNumImageView4.setImageResource(R.drawable.gpleft);
      paramNumImageView5.setImageResource(R.drawable.gpleft);
      break;
    case 493:
      paramNumImageView1.setImageResource(R.drawable.gpdown);
      paramNumImageView2.setImageResource(R.drawable.gpdown);
      paramNumImageView3.setImageResource(R.drawable.gpdown);
      paramNumImageView4.setImageResource(R.drawable.gpdown);
      paramNumImageView5.setImageResource(R.drawable.gpdown);
      break;
    case 492:
      paramNumImageView1.setImageResource(R.drawable.gpup);
      paramNumImageView2.setImageResource(R.drawable.gpup);
      paramNumImageView3.setImageResource(R.drawable.gpup);
      paramNumImageView4.setImageResource(R.drawable.gpup);
      paramNumImageView5.setImageResource(R.drawable.gpup);
      break;
    case 491:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprb);
        paramNumImageView2.setImageResource(R.drawable.gprb);
        paramNumImageView3.setImageResource(R.drawable.gprb);
        paramNumImageView4.setImageResource(R.drawable.gprb);
        paramNumImageView5.setImageResource(R.drawable.gprb);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1);
        paramNumImageView2.setImageResource(R.drawable.gpr1);
        paramNumImageView3.setImageResource(R.drawable.gpr1);
        paramNumImageView4.setImageResource(R.drawable.gpr1);
        paramNumImageView5.setImageResource(R.drawable.gpr1);
      }
      break;
    case 490:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplb);
        paramNumImageView2.setImageResource(R.drawable.gplb);
        paramNumImageView3.setImageResource(R.drawable.gplb);
        paramNumImageView4.setImageResource(R.drawable.gplb);
        paramNumImageView5.setImageResource(R.drawable.gplb);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1);
        paramNumImageView2.setImageResource(R.drawable.gpl1);
        paramNumImageView3.setImageResource(R.drawable.gpl1);
        paramNumImageView4.setImageResource(R.drawable.gpl1);
        paramNumImageView5.setImageResource(R.drawable.gpl1);
      }
      break;
    case 489:
      paramNumImageView1.setImageResource(R.drawable.gpy);
      paramNumImageView2.setImageResource(R.drawable.gpy);
      paramNumImageView3.setImageResource(R.drawable.gpy);
      paramNumImageView4.setImageResource(R.drawable.gpy);
      paramNumImageView5.setImageResource(R.drawable.gpy);
      break;
    case 488:
      paramNumImageView1.setImageResource(R.drawable.gpx);
      paramNumImageView2.setImageResource(R.drawable.gpx);
      paramNumImageView3.setImageResource(R.drawable.gpx);
      paramNumImageView4.setImageResource(R.drawable.gpx);
      paramNumImageView5.setImageResource(R.drawable.gpx);
      break;
    case 487:
      paramNumImageView1.setImageResource(R.drawable.gpb);
      paramNumImageView2.setImageResource(R.drawable.gpb);
      paramNumImageView3.setImageResource(R.drawable.gpb);
      paramNumImageView4.setImageResource(R.drawable.gpb);
      paramNumImageView5.setImageResource(R.drawable.gpb);
      break;
    case 486:
      paramNumImageView1.setImageResource(R.drawable.gpa);
      paramNumImageView2.setImageResource(R.drawable.gpa);
      paramNumImageView3.setImageResource(R.drawable.gpa);
      paramNumImageView4.setImageResource(R.drawable.gpa);
      paramNumImageView5.setImageResource(R.drawable.gpa);
      break;
    case 481:
    case 482:
    case 483:
    case 484:
    case 485:
      paramNumImageView1.setImageResource(R.drawable.newkey);
      paramNumImageView2.setImageResource(R.drawable.newkey);
      paramNumImageView3.setImageResource(R.drawable.newkey);
      paramNumImageView4.setImageResource(R.drawable.newkey);
      paramNumImageView5.setImageResource(R.drawable.newkey);
      break;
    case 480:
      paramNumImageView1.setImageResource(R.drawable.center3);
      paramNumImageView2.setImageResource(R.drawable.center3);
      paramNumImageView3.setImageResource(R.drawable.center3);
      paramNumImageView4.setImageResource(R.drawable.center3);
      paramNumImageView5.setImageResource(R.drawable.center3);
      break;
    case 479:
      paramNumImageView1.setImageResource(R.drawable.gpbpr2);
      paramNumImageView2.setImageResource(R.drawable.gpbpr2);
      paramNumImageView3.setImageResource(R.drawable.gpbpr2);
      paramNumImageView4.setImageResource(R.drawable.gpbpr2);
      paramNumImageView5.setImageResource(R.drawable.gpbpr2);
      break;
    case 478:
      paramNumImageView1.setImageResource(R.drawable.center2);
      paramNumImageView2.setImageResource(R.drawable.center2);
      paramNumImageView3.setImageResource(R.drawable.center2);
      paramNumImageView4.setImageResource(R.drawable.center2);
      paramNumImageView5.setImageResource(R.drawable.center2);
      break;
    case 477:
      paramNumImageView1.setImageResource(R.drawable.gpbpr1);
      paramNumImageView2.setImageResource(R.drawable.gpbpr1);
      paramNumImageView3.setImageResource(R.drawable.gpbpr1);
      paramNumImageView4.setImageResource(R.drawable.gpbpr1);
      paramNumImageView5.setImageResource(R.drawable.gpbpr1);
      break;
    case 476:
      paramNumImageView1.setImageResource(R.drawable.center1);
      paramNumImageView2.setImageResource(R.drawable.center1);
      paramNumImageView3.setImageResource(R.drawable.center1);
      paramNumImageView4.setImageResource(R.drawable.center1);
      paramNumImageView5.setImageResource(R.drawable.center1);
      break;
    case 475:
      paramNumImageView1.setImageResource(R.drawable.gpm4);
      paramNumImageView2.setImageResource(R.drawable.gpm4);
      paramNumImageView3.setImageResource(R.drawable.gpm4);
      paramNumImageView4.setImageResource(R.drawable.gpm4);
      paramNumImageView5.setImageResource(R.drawable.gpm4);
      break;
    case 474:
      paramNumImageView1.setImageResource(R.drawable.gpm3);
      paramNumImageView2.setImageResource(R.drawable.gpm3);
      paramNumImageView3.setImageResource(R.drawable.gpm3);
      paramNumImageView4.setImageResource(R.drawable.gpm3);
      paramNumImageView5.setImageResource(R.drawable.gpm3);
      break;
    case 473:
      paramNumImageView1.setImageResource(R.drawable.gpm2);
      paramNumImageView2.setImageResource(R.drawable.gpm2);
      paramNumImageView3.setImageResource(R.drawable.gpm2);
      paramNumImageView4.setImageResource(R.drawable.gpm2);
      paramNumImageView5.setImageResource(R.drawable.gpm2);
      break;
    case 472:
      paramNumImageView1.setImageResource(R.drawable.gpm1);
      paramNumImageView2.setImageResource(R.drawable.gpm1);
      paramNumImageView3.setImageResource(R.drawable.gpm1);
      paramNumImageView4.setImageResource(R.drawable.gpm1);
      paramNumImageView5.setImageResource(R.drawable.gpm1);
      break;
    case 471:
      paramNumImageView1.setImageResource(R.drawable.gpbpr);
      paramNumImageView2.setImageResource(R.drawable.gpbpr);
      paramNumImageView3.setImageResource(R.drawable.gpbpr);
      paramNumImageView4.setImageResource(R.drawable.gpbpr);
      paramNumImageView5.setImageResource(R.drawable.gpbpr);
      break;
    case 470:
      paramNumImageView1.setImageResource(R.drawable.gpbpl);
      paramNumImageView2.setImageResource(R.drawable.gpbpl);
      paramNumImageView3.setImageResource(R.drawable.gpbpl);
      paramNumImageView4.setImageResource(R.drawable.gpbpl);
      paramNumImageView5.setImageResource(R.drawable.gpbpl);
      break;
    case 469:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprt);
        paramNumImageView2.setImageResource(R.drawable.gprt);
        paramNumImageView3.setImageResource(R.drawable.gprt);
        paramNumImageView4.setImageResource(R.drawable.gprt);
        paramNumImageView5.setImageResource(R.drawable.gprt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2);
        paramNumImageView2.setImageResource(R.drawable.gpr2);
        paramNumImageView3.setImageResource(R.drawable.gpr2);
        paramNumImageView4.setImageResource(R.drawable.gpr2);
        paramNumImageView5.setImageResource(R.drawable.gpr2);
      }
      break;
    case 468:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplt);
        paramNumImageView2.setImageResource(R.drawable.gplt);
        paramNumImageView3.setImageResource(R.drawable.gplt);
        paramNumImageView4.setImageResource(R.drawable.gplt);
        paramNumImageView5.setImageResource(R.drawable.gplt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2);
        paramNumImageView2.setImageResource(R.drawable.gpl2);
        paramNumImageView3.setImageResource(R.drawable.gpl2);
        paramNumImageView4.setImageResource(R.drawable.gpl2);
        paramNumImageView5.setImageResource(R.drawable.gpl2);
      }
      break;
    case 467:
      paramNumImageView1.setImageResource(R.drawable.gpr3);
      paramNumImageView2.setImageResource(R.drawable.gpr3);
      paramNumImageView3.setImageResource(R.drawable.gpr3);
      paramNumImageView4.setImageResource(R.drawable.gpr3);
      paramNumImageView5.setImageResource(R.drawable.gpr3);
      break;
    case 466:
      paramNumImageView1.setImageResource(R.drawable.gpl3);
      paramNumImageView2.setImageResource(R.drawable.gpl3);
      paramNumImageView3.setImageResource(R.drawable.gpl3);
      paramNumImageView4.setImageResource(R.drawable.gpl3);
      paramNumImageView5.setImageResource(R.drawable.gpl3);
      break;
    case 465:
      paramNumImageView1.setImageResource(R.drawable.gpselect);
      paramNumImageView2.setImageResource(R.drawable.gpselect);
      paramNumImageView3.setImageResource(R.drawable.gpselect);
      paramNumImageView4.setImageResource(R.drawable.gpselect);
      paramNumImageView5.setImageResource(R.drawable.gpselect);
      break;
    case 464:
      paramNumImageView1.setImageResource(R.drawable.gpstart);
      paramNumImageView2.setImageResource(R.drawable.gpstart);
      paramNumImageView3.setImageResource(R.drawable.gpstart);
      paramNumImageView4.setImageResource(R.drawable.gpstart);
      paramNumImageView5.setImageResource(R.drawable.gpstart);
      break;
    case 463:
      paramNumImageView1.setImageResource(R.drawable.gpright);
      paramNumImageView2.setImageResource(R.drawable.gpright);
      paramNumImageView3.setImageResource(R.drawable.gpright);
      paramNumImageView4.setImageResource(R.drawable.gpright);
      paramNumImageView5.setImageResource(R.drawable.gpright);
      break;
    case 462:
      paramNumImageView1.setImageResource(R.drawable.gpleft);
      paramNumImageView2.setImageResource(R.drawable.gpleft);
      paramNumImageView3.setImageResource(R.drawable.gpleft);
      paramNumImageView4.setImageResource(R.drawable.gpleft);
      paramNumImageView5.setImageResource(R.drawable.gpleft);
      break;
    case 461:
      paramNumImageView1.setImageResource(R.drawable.gpdown);
      paramNumImageView2.setImageResource(R.drawable.gpdown);
      paramNumImageView3.setImageResource(R.drawable.gpdown);
      paramNumImageView4.setImageResource(R.drawable.gpdown);
      paramNumImageView5.setImageResource(R.drawable.gpdown);
      break;
    case 460:
      paramNumImageView1.setImageResource(R.drawable.gpup);
      paramNumImageView2.setImageResource(R.drawable.gpup);
      paramNumImageView3.setImageResource(R.drawable.gpup);
      paramNumImageView4.setImageResource(R.drawable.gpup);
      paramNumImageView5.setImageResource(R.drawable.gpup);
      break;
    case 459:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprb);
        paramNumImageView2.setImageResource(R.drawable.gprb);
        paramNumImageView3.setImageResource(R.drawable.gprb);
        paramNumImageView4.setImageResource(R.drawable.gprb);
        paramNumImageView5.setImageResource(R.drawable.gprb);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1);
        paramNumImageView2.setImageResource(R.drawable.gpr1);
        paramNumImageView3.setImageResource(R.drawable.gpr1);
        paramNumImageView4.setImageResource(R.drawable.gpr1);
        paramNumImageView5.setImageResource(R.drawable.gpr1);
      }
      break;
    case 458:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplb);
        paramNumImageView2.setImageResource(R.drawable.gplb);
        paramNumImageView3.setImageResource(R.drawable.gplb);
        paramNumImageView4.setImageResource(R.drawable.gplb);
        paramNumImageView5.setImageResource(R.drawable.gplb);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1);
        paramNumImageView2.setImageResource(R.drawable.gpl1);
        paramNumImageView3.setImageResource(R.drawable.gpl1);
        paramNumImageView4.setImageResource(R.drawable.gpl1);
        paramNumImageView5.setImageResource(R.drawable.gpl1);
      }
      break;
    case 457:
      paramNumImageView1.setImageResource(R.drawable.gpy);
      paramNumImageView2.setImageResource(R.drawable.gpy);
      paramNumImageView3.setImageResource(R.drawable.gpy);
      paramNumImageView4.setImageResource(R.drawable.gpy);
      paramNumImageView5.setImageResource(R.drawable.gpy);
      break;
    case 456:
      paramNumImageView1.setImageResource(R.drawable.gpx);
      paramNumImageView2.setImageResource(R.drawable.gpx);
      paramNumImageView3.setImageResource(R.drawable.gpx);
      paramNumImageView4.setImageResource(R.drawable.gpx);
      paramNumImageView5.setImageResource(R.drawable.gpx);
      break;
    case 455:
      paramNumImageView1.setImageResource(R.drawable.gpb);
      paramNumImageView2.setImageResource(R.drawable.gpb);
      paramNumImageView3.setImageResource(R.drawable.gpb);
      paramNumImageView4.setImageResource(R.drawable.gpb);
      paramNumImageView5.setImageResource(R.drawable.gpb);
      break;
    case 454:
      paramNumImageView1.setImageResource(R.drawable.gpa);
      paramNumImageView2.setImageResource(R.drawable.gpa);
      paramNumImageView3.setImageResource(R.drawable.gpa);
      paramNumImageView4.setImageResource(R.drawable.gpa);
      paramNumImageView5.setImageResource(R.drawable.gpa);
      break;
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
      paramNumImageView1.setImageResource(R.drawable.newkey);
      paramNumImageView2.setImageResource(R.drawable.newkey);
      paramNumImageView3.setImageResource(R.drawable.newkey);
      paramNumImageView4.setImageResource(R.drawable.newkey);
      paramNumImageView5.setImageResource(R.drawable.newkey);
      break;
    case 443:
      paramNumImageView1.setImageResource(R.drawable.gpm4);
      paramNumImageView2.setImageResource(R.drawable.gpm4);
      paramNumImageView3.setImageResource(R.drawable.gpm4);
      paramNumImageView4.setImageResource(R.drawable.gpm4);
      paramNumImageView5.setImageResource(R.drawable.gpm4);
      break;
    case 442:
      paramNumImageView1.setImageResource(R.drawable.gpm3);
      paramNumImageView2.setImageResource(R.drawable.gpm3);
      paramNumImageView3.setImageResource(R.drawable.gpm3);
      paramNumImageView4.setImageResource(R.drawable.gpm3);
      paramNumImageView5.setImageResource(R.drawable.gpm3);
      break;
    case 441:
      paramNumImageView1.setImageResource(R.drawable.gpm2);
      paramNumImageView2.setImageResource(R.drawable.gpm2);
      paramNumImageView3.setImageResource(R.drawable.gpm2);
      paramNumImageView4.setImageResource(R.drawable.gpm2);
      paramNumImageView5.setImageResource(R.drawable.gpm2);
      break;
    case 440:
      paramNumImageView1.setImageResource(R.drawable.gpm1);
      paramNumImageView2.setImageResource(R.drawable.gpm1);
      paramNumImageView3.setImageResource(R.drawable.gpm1);
      paramNumImageView4.setImageResource(R.drawable.gpm1);
      paramNumImageView5.setImageResource(R.drawable.gpm1);
      break;
    case 439:
      paramNumImageView1.setImageResource(R.drawable.gpbpr);
      paramNumImageView2.setImageResource(R.drawable.gpbpr);
      paramNumImageView3.setImageResource(R.drawable.gpbpr);
      paramNumImageView4.setImageResource(R.drawable.gpbpr);
      paramNumImageView5.setImageResource(R.drawable.gpbpr);
      break;
    case 438:
      paramNumImageView1.setImageResource(R.drawable.gpbpl);
      paramNumImageView2.setImageResource(R.drawable.gpbpl);
      paramNumImageView3.setImageResource(R.drawable.gpbpl);
      paramNumImageView4.setImageResource(R.drawable.gpbpl);
      paramNumImageView5.setImageResource(R.drawable.gpbpl);
      break;
    case 437:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprt);
        paramNumImageView2.setImageResource(R.drawable.gprt);
        paramNumImageView3.setImageResource(R.drawable.gprt);
        paramNumImageView4.setImageResource(R.drawable.gprt);
        paramNumImageView5.setImageResource(R.drawable.gprt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2);
        paramNumImageView2.setImageResource(R.drawable.gpr2);
        paramNumImageView3.setImageResource(R.drawable.gpr2);
        paramNumImageView4.setImageResource(R.drawable.gpr2);
        paramNumImageView5.setImageResource(R.drawable.gpr2);
      }
      break;
    case 436:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplt);
        paramNumImageView2.setImageResource(R.drawable.gplt);
        paramNumImageView3.setImageResource(R.drawable.gplt);
        paramNumImageView4.setImageResource(R.drawable.gplt);
        paramNumImageView5.setImageResource(R.drawable.gplt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2);
        paramNumImageView2.setImageResource(R.drawable.gpl2);
        paramNumImageView3.setImageResource(R.drawable.gpl2);
        paramNumImageView4.setImageResource(R.drawable.gpl2);
        paramNumImageView5.setImageResource(R.drawable.gpl2);
      }
      break;
    case 435:
      paramNumImageView1.setImageResource(R.drawable.gpr3);
      paramNumImageView2.setImageResource(R.drawable.gpr3);
      paramNumImageView3.setImageResource(R.drawable.gpr3);
      paramNumImageView4.setImageResource(R.drawable.gpr3);
      paramNumImageView5.setImageResource(R.drawable.gpr3);
      break;
    case 434:
      paramNumImageView1.setImageResource(R.drawable.gpl3);
      paramNumImageView2.setImageResource(R.drawable.gpl3);
      paramNumImageView3.setImageResource(R.drawable.gpl3);
      paramNumImageView4.setImageResource(R.drawable.gpl3);
      paramNumImageView5.setImageResource(R.drawable.gpl3);
      break;
    case 433:
      paramNumImageView1.setImageResource(R.drawable.gpselect);
      paramNumImageView2.setImageResource(R.drawable.gpselect);
      paramNumImageView3.setImageResource(R.drawable.gpselect);
      paramNumImageView4.setImageResource(R.drawable.gpselect);
      paramNumImageView5.setImageResource(R.drawable.gpselect);
      break;
    case 432:
      paramNumImageView1.setImageResource(R.drawable.gpstart);
      paramNumImageView2.setImageResource(R.drawable.gpstart);
      paramNumImageView3.setImageResource(R.drawable.gpstart);
      paramNumImageView4.setImageResource(R.drawable.gpstart);
      paramNumImageView5.setImageResource(R.drawable.gpstart);
      break;
    case 431:
      paramNumImageView1.setImageResource(R.drawable.gpright);
      paramNumImageView2.setImageResource(R.drawable.gpright);
      paramNumImageView3.setImageResource(R.drawable.gpright);
      paramNumImageView4.setImageResource(R.drawable.gpright);
      paramNumImageView5.setImageResource(R.drawable.gpright);
      break;
    case 430:
      paramNumImageView1.setImageResource(R.drawable.gpleft);
      paramNumImageView2.setImageResource(R.drawable.gpleft);
      paramNumImageView3.setImageResource(R.drawable.gpleft);
      paramNumImageView4.setImageResource(R.drawable.gpleft);
      paramNumImageView5.setImageResource(R.drawable.gpleft);
      break;
    case 429:
      paramNumImageView1.setImageResource(R.drawable.gpdown);
      paramNumImageView2.setImageResource(R.drawable.gpdown);
      paramNumImageView3.setImageResource(R.drawable.gpdown);
      paramNumImageView4.setImageResource(R.drawable.gpdown);
      paramNumImageView5.setImageResource(R.drawable.gpdown);
      break;
    case 428:
      paramNumImageView1.setImageResource(R.drawable.gpup);
      paramNumImageView2.setImageResource(R.drawable.gpup);
      paramNumImageView3.setImageResource(R.drawable.gpup);
      paramNumImageView4.setImageResource(R.drawable.gpup);
      paramNumImageView5.setImageResource(R.drawable.gpup);
      break;
    case 427:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprb);
        paramNumImageView2.setImageResource(R.drawable.gprb);
        paramNumImageView3.setImageResource(R.drawable.gprb);
        paramNumImageView4.setImageResource(R.drawable.gprb);
        paramNumImageView5.setImageResource(R.drawable.gprb);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1);
        paramNumImageView2.setImageResource(R.drawable.gpr1);
        paramNumImageView3.setImageResource(R.drawable.gpr1);
        paramNumImageView4.setImageResource(R.drawable.gpr1);
        paramNumImageView5.setImageResource(R.drawable.gpr1);
      }
      break;
    case 426:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplb);
        paramNumImageView2.setImageResource(R.drawable.gplb);
        paramNumImageView3.setImageResource(R.drawable.gplb);
        paramNumImageView4.setImageResource(R.drawable.gplb);
        paramNumImageView5.setImageResource(R.drawable.gplb);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1);
        paramNumImageView2.setImageResource(R.drawable.gpl1);
        paramNumImageView3.setImageResource(R.drawable.gpl1);
        paramNumImageView4.setImageResource(R.drawable.gpl1);
        paramNumImageView5.setImageResource(R.drawable.gpl1);
      }
      break;
    case 425:
      paramNumImageView1.setImageResource(R.drawable.gpy);
      paramNumImageView2.setImageResource(R.drawable.gpy);
      paramNumImageView3.setImageResource(R.drawable.gpy);
      paramNumImageView4.setImageResource(R.drawable.gpy);
      paramNumImageView5.setImageResource(R.drawable.gpy);
      break;
    case 424:
      paramNumImageView1.setImageResource(R.drawable.gpx);
      paramNumImageView2.setImageResource(R.drawable.gpx);
      paramNumImageView3.setImageResource(R.drawable.gpx);
      paramNumImageView4.setImageResource(R.drawable.gpx);
      paramNumImageView5.setImageResource(R.drawable.gpx);
      break;
    case 423:
      paramNumImageView1.setImageResource(R.drawable.gpb);
      paramNumImageView2.setImageResource(R.drawable.gpb);
      paramNumImageView3.setImageResource(R.drawable.gpb);
      paramNumImageView4.setImageResource(R.drawable.gpb);
      paramNumImageView5.setImageResource(R.drawable.gpb);
      break;
    case 422:
      paramNumImageView1.setImageResource(R.drawable.gpa);
      paramNumImageView2.setImageResource(R.drawable.gpa);
      paramNumImageView3.setImageResource(R.drawable.gpa);
      paramNumImageView4.setImageResource(R.drawable.gpa);
      paramNumImageView5.setImageResource(R.drawable.gpa);
      break;
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
      paramNumImageView1.setImageResource(R.drawable.newkey);
      paramNumImageView2.setImageResource(R.drawable.newkey);
      paramNumImageView3.setImageResource(R.drawable.newkey);
      paramNumImageView4.setImageResource(R.drawable.newkey);
      paramNumImageView5.setImageResource(R.drawable.newkey);
      break;
    case 411:
      paramNumImageView1.setImageResource(R.drawable.gpm4hp2);
      paramNumImageView2.setImageResource(R.drawable.gpm4hp2);
      paramNumImageView3.setImageResource(R.drawable.gpm4hp2);
      paramNumImageView4.setImageResource(R.drawable.gpm4hp2);
      paramNumImageView5.setImageResource(R.drawable.gpm4hp2);
      break;
    case 410:
      paramNumImageView1.setImageResource(R.drawable.gpm3hp2);
      paramNumImageView2.setImageResource(R.drawable.gpm3hp2);
      paramNumImageView3.setImageResource(R.drawable.gpm3hp2);
      paramNumImageView4.setImageResource(R.drawable.gpm3hp2);
      paramNumImageView5.setImageResource(R.drawable.gpm3hp2);
      break;
    case 409:
      paramNumImageView1.setImageResource(R.drawable.gpm2hp2);
      paramNumImageView2.setImageResource(R.drawable.gpm2hp2);
      paramNumImageView3.setImageResource(R.drawable.gpm2hp2);
      paramNumImageView4.setImageResource(R.drawable.gpm2hp2);
      paramNumImageView5.setImageResource(R.drawable.gpm2hp2);
      break;
    case 408:
      paramNumImageView1.setImageResource(R.drawable.gpm1hp2);
      paramNumImageView2.setImageResource(R.drawable.gpm1hp2);
      paramNumImageView3.setImageResource(R.drawable.gpm1hp2);
      paramNumImageView4.setImageResource(R.drawable.gpm1hp2);
      paramNumImageView5.setImageResource(R.drawable.gpm1hp2);
      break;
    case 407:
      paramNumImageView1.setImageResource(R.drawable.gpbpr);
      paramNumImageView2.setImageResource(R.drawable.gpbpr);
      paramNumImageView3.setImageResource(R.drawable.gpbpr);
      paramNumImageView4.setImageResource(R.drawable.gpbpr);
      paramNumImageView5.setImageResource(R.drawable.gpbpr);
      break;
    case 406:
      paramNumImageView1.setImageResource(R.drawable.gpbpl);
      paramNumImageView2.setImageResource(R.drawable.gpbpl);
      paramNumImageView3.setImageResource(R.drawable.gpbpl);
      paramNumImageView4.setImageResource(R.drawable.gpbpl);
      paramNumImageView5.setImageResource(R.drawable.gpbpl);
      break;
    case 405:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprthp2);
        paramNumImageView2.setImageResource(R.drawable.gprthp2);
        paramNumImageView3.setImageResource(R.drawable.gprthp2);
        paramNumImageView4.setImageResource(R.drawable.gprthp2);
        paramNumImageView5.setImageResource(R.drawable.gprthp2);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2hp2);
        paramNumImageView2.setImageResource(R.drawable.gpr2hp2);
        paramNumImageView3.setImageResource(R.drawable.gpr2hp2);
        paramNumImageView4.setImageResource(R.drawable.gpr2hp2);
        paramNumImageView5.setImageResource(R.drawable.gpr2hp2);
      }
      break;
    case 404:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplthp2);
        paramNumImageView2.setImageResource(R.drawable.gplthp2);
        paramNumImageView3.setImageResource(R.drawable.gplthp2);
        paramNumImageView4.setImageResource(R.drawable.gplthp2);
        paramNumImageView5.setImageResource(R.drawable.gplthp2);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2hp2);
        paramNumImageView2.setImageResource(R.drawable.gpl2hp2);
        paramNumImageView3.setImageResource(R.drawable.gpl2hp2);
        paramNumImageView4.setImageResource(R.drawable.gpl2hp2);
        paramNumImageView5.setImageResource(R.drawable.gpl2hp2);
      }
      break;
    case 403:
      paramNumImageView1.setImageResource(R.drawable.gpr3hp2);
      paramNumImageView2.setImageResource(R.drawable.gpr3hp2);
      paramNumImageView3.setImageResource(R.drawable.gpr3hp2);
      paramNumImageView4.setImageResource(R.drawable.gpr3hp2);
      paramNumImageView5.setImageResource(R.drawable.gpr3hp2);
      break;
    case 402:
      paramNumImageView1.setImageResource(R.drawable.gpl3hp2);
      paramNumImageView2.setImageResource(R.drawable.gpl3hp2);
      paramNumImageView3.setImageResource(R.drawable.gpl3hp2);
      paramNumImageView4.setImageResource(R.drawable.gpl3hp2);
      paramNumImageView5.setImageResource(R.drawable.gpl3hp2);
      break;
    case 401:
      paramNumImageView1.setImageResource(R.drawable.gpselect);
      paramNumImageView2.setImageResource(R.drawable.gpselect);
      paramNumImageView3.setImageResource(R.drawable.gpselect);
      paramNumImageView4.setImageResource(R.drawable.gpselect);
      paramNumImageView5.setImageResource(R.drawable.gpselect);
      break;
    case 400:
      paramNumImageView1.setImageResource(R.drawable.gpstart);
      paramNumImageView2.setImageResource(R.drawable.gpstart);
      paramNumImageView3.setImageResource(R.drawable.gpstart);
      paramNumImageView4.setImageResource(R.drawable.gpstart);
      paramNumImageView5.setImageResource(R.drawable.gpstart);
      break;
    case 399:
      paramNumImageView1.setImageResource(R.drawable.gprighthp2);
      paramNumImageView2.setImageResource(R.drawable.gprighthp2);
      paramNumImageView3.setImageResource(R.drawable.gprighthp2);
      paramNumImageView4.setImageResource(R.drawable.gprighthp2);
      paramNumImageView5.setImageResource(R.drawable.gprighthp2);
      break;
    case 398:
      paramNumImageView1.setImageResource(R.drawable.gplefthp2);
      paramNumImageView2.setImageResource(R.drawable.gplefthp2);
      paramNumImageView3.setImageResource(R.drawable.gplefthp2);
      paramNumImageView4.setImageResource(R.drawable.gplefthp2);
      paramNumImageView5.setImageResource(R.drawable.gplefthp2);
      break;
    case 397:
      paramNumImageView1.setImageResource(R.drawable.gpdownhp2);
      paramNumImageView2.setImageResource(R.drawable.gpdownhp2);
      paramNumImageView3.setImageResource(R.drawable.gpdownhp2);
      paramNumImageView4.setImageResource(R.drawable.gpdownhp2);
      paramNumImageView5.setImageResource(R.drawable.gpdownhp2);
      break;
    case 396:
      paramNumImageView1.setImageResource(R.drawable.gpuphp2);
      paramNumImageView2.setImageResource(R.drawable.gpuphp2);
      paramNumImageView3.setImageResource(R.drawable.gpuphp2);
      paramNumImageView4.setImageResource(R.drawable.gpuphp2);
      paramNumImageView5.setImageResource(R.drawable.gpuphp2);
      break;
    case 395:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprbhp2);
        paramNumImageView2.setImageResource(R.drawable.gprbhp2);
        paramNumImageView3.setImageResource(R.drawable.gprbhp2);
        paramNumImageView4.setImageResource(R.drawable.gprbhp2);
        paramNumImageView5.setImageResource(R.drawable.gprbhp2);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1hp2);
        paramNumImageView2.setImageResource(R.drawable.gpr1hp2);
        paramNumImageView3.setImageResource(R.drawable.gpr1hp2);
        paramNumImageView4.setImageResource(R.drawable.gpr1hp2);
        paramNumImageView5.setImageResource(R.drawable.gpr1hp2);
      }
      break;
    case 394:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplbhp2);
        paramNumImageView2.setImageResource(R.drawable.gplbhp2);
        paramNumImageView3.setImageResource(R.drawable.gplbhp2);
        paramNumImageView4.setImageResource(R.drawable.gplbhp2);
        paramNumImageView5.setImageResource(R.drawable.gplbhp2);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1hp2);
        paramNumImageView2.setImageResource(R.drawable.gpl1hp2);
        paramNumImageView3.setImageResource(R.drawable.gpl1hp2);
        paramNumImageView4.setImageResource(R.drawable.gpl1hp2);
        paramNumImageView5.setImageResource(R.drawable.gpl1hp2);
      }
      break;
    case 393:
      paramNumImageView1.setImageResource(R.drawable.gpyhp2);
      paramNumImageView2.setImageResource(R.drawable.gpyhp2);
      paramNumImageView3.setImageResource(R.drawable.gpyhp2);
      paramNumImageView4.setImageResource(R.drawable.gpyhp2);
      paramNumImageView5.setImageResource(R.drawable.gpyhp2);
      break;
    case 392:
      paramNumImageView1.setImageResource(R.drawable.gpxhp2);
      paramNumImageView2.setImageResource(R.drawable.gpxhp2);
      paramNumImageView3.setImageResource(R.drawable.gpxhp2);
      paramNumImageView4.setImageResource(R.drawable.gpxhp2);
      paramNumImageView5.setImageResource(R.drawable.gpxhp2);
      break;
    case 391:
      paramNumImageView1.setImageResource(R.drawable.gpbhp2);
      paramNumImageView2.setImageResource(R.drawable.gpbhp2);
      paramNumImageView3.setImageResource(R.drawable.gpbhp2);
      paramNumImageView4.setImageResource(R.drawable.gpbhp2);
      paramNumImageView5.setImageResource(R.drawable.gpbhp2);
      break;
    case 390:
      paramNumImageView1.setImageResource(R.drawable.gpahp2);
      paramNumImageView2.setImageResource(R.drawable.gpahp2);
      paramNumImageView3.setImageResource(R.drawable.gpahp2);
      paramNumImageView4.setImageResource(R.drawable.gpahp2);
      paramNumImageView5.setImageResource(R.drawable.gpahp2);
      break;
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
      paramNumImageView1.setImageResource(R.drawable.newkey);
      paramNumImageView2.setImageResource(R.drawable.newkey);
      paramNumImageView3.setImageResource(R.drawable.newkey);
      paramNumImageView4.setImageResource(R.drawable.newkey);
      paramNumImageView5.setImageResource(R.drawable.newkey);
      break;
    case 379:
      paramNumImageView1.setImageResource(R.drawable.gpm4hp1);
      paramNumImageView2.setImageResource(R.drawable.gpm4hp1);
      paramNumImageView3.setImageResource(R.drawable.gpm4hp1);
      paramNumImageView4.setImageResource(R.drawable.gpm4hp1);
      paramNumImageView5.setImageResource(R.drawable.gpm4hp1);
      break;
    case 378:
      paramNumImageView1.setImageResource(R.drawable.gpm3hp1);
      paramNumImageView2.setImageResource(R.drawable.gpm3hp1);
      paramNumImageView3.setImageResource(R.drawable.gpm3hp1);
      paramNumImageView4.setImageResource(R.drawable.gpm3hp1);
      paramNumImageView5.setImageResource(R.drawable.gpm3hp1);
      break;
    case 377:
      paramNumImageView1.setImageResource(R.drawable.gpm2hp1);
      paramNumImageView2.setImageResource(R.drawable.gpm2hp1);
      paramNumImageView3.setImageResource(R.drawable.gpm2hp1);
      paramNumImageView4.setImageResource(R.drawable.gpm2hp1);
      paramNumImageView5.setImageResource(R.drawable.gpm2hp1);
      break;
    case 376:
      paramNumImageView1.setImageResource(R.drawable.gpm1hp1);
      paramNumImageView2.setImageResource(R.drawable.gpm1hp1);
      paramNumImageView3.setImageResource(R.drawable.gpm1hp1);
      paramNumImageView4.setImageResource(R.drawable.gpm1hp1);
      paramNumImageView5.setImageResource(R.drawable.gpm1hp1);
      break;
    case 375:
      paramNumImageView1.setImageResource(R.drawable.gpbpr);
      paramNumImageView2.setImageResource(R.drawable.gpbpr);
      paramNumImageView3.setImageResource(R.drawable.gpbpr);
      paramNumImageView4.setImageResource(R.drawable.gpbpr);
      paramNumImageView5.setImageResource(R.drawable.gpbpr);
      break;
    case 374:
      paramNumImageView1.setImageResource(R.drawable.gpbpl);
      paramNumImageView2.setImageResource(R.drawable.gpbpl);
      paramNumImageView3.setImageResource(R.drawable.gpbpl);
      paramNumImageView4.setImageResource(R.drawable.gpbpl);
      paramNumImageView5.setImageResource(R.drawable.gpbpl);
      break;
    case 373:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprthp1);
        paramNumImageView2.setImageResource(R.drawable.gprthp1);
        paramNumImageView3.setImageResource(R.drawable.gprthp1);
        paramNumImageView4.setImageResource(R.drawable.gprthp1);
        paramNumImageView5.setImageResource(R.drawable.gprthp1);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2hp1);
        paramNumImageView2.setImageResource(R.drawable.gpr2hp1);
        paramNumImageView3.setImageResource(R.drawable.gpr2hp1);
        paramNumImageView4.setImageResource(R.drawable.gpr2hp1);
        paramNumImageView5.setImageResource(R.drawable.gpr2hp1);
      }
      break;
    case 372:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplthp1);
        paramNumImageView2.setImageResource(R.drawable.gplthp1);
        paramNumImageView3.setImageResource(R.drawable.gplthp1);
        paramNumImageView4.setImageResource(R.drawable.gplthp1);
        paramNumImageView5.setImageResource(R.drawable.gplthp1);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2hp1);
        paramNumImageView2.setImageResource(R.drawable.gpl2hp1);
        paramNumImageView3.setImageResource(R.drawable.gpl2hp1);
        paramNumImageView4.setImageResource(R.drawable.gpl2hp1);
        paramNumImageView5.setImageResource(R.drawable.gpl2hp1);
      }
      break;
    case 371:
      paramNumImageView1.setImageResource(R.drawable.gpr3hp1);
      paramNumImageView2.setImageResource(R.drawable.gpr3hp1);
      paramNumImageView3.setImageResource(R.drawable.gpr3hp1);
      paramNumImageView4.setImageResource(R.drawable.gpr3hp1);
      paramNumImageView5.setImageResource(R.drawable.gpr3hp1);
      break;
    case 370:
      paramNumImageView1.setImageResource(R.drawable.gpl3hp1);
      paramNumImageView2.setImageResource(R.drawable.gpl3hp1);
      paramNumImageView3.setImageResource(R.drawable.gpl3hp1);
      paramNumImageView4.setImageResource(R.drawable.gpl3hp1);
      paramNumImageView5.setImageResource(R.drawable.gpl3hp1);
      break;
    case 369:
      paramNumImageView1.setImageResource(R.drawable.gpselect);
      paramNumImageView2.setImageResource(R.drawable.gpselect);
      paramNumImageView3.setImageResource(R.drawable.gpselect);
      paramNumImageView4.setImageResource(R.drawable.gpselect);
      paramNumImageView5.setImageResource(R.drawable.gpselect);
      break;
    case 368:
      paramNumImageView1.setImageResource(R.drawable.gpstart);
      paramNumImageView2.setImageResource(R.drawable.gpstart);
      paramNumImageView3.setImageResource(R.drawable.gpstart);
      paramNumImageView4.setImageResource(R.drawable.gpstart);
      paramNumImageView5.setImageResource(R.drawable.gpstart);
      break;
    case 367:
      paramNumImageView1.setImageResource(R.drawable.gprighthp1);
      paramNumImageView2.setImageResource(R.drawable.gprighthp1);
      paramNumImageView3.setImageResource(R.drawable.gprighthp1);
      paramNumImageView4.setImageResource(R.drawable.gprighthp1);
      paramNumImageView5.setImageResource(R.drawable.gprighthp1);
      break;
    case 366:
      paramNumImageView1.setImageResource(R.drawable.gplefthp1);
      paramNumImageView2.setImageResource(R.drawable.gplefthp1);
      paramNumImageView3.setImageResource(R.drawable.gplefthp1);
      paramNumImageView4.setImageResource(R.drawable.gplefthp1);
      paramNumImageView5.setImageResource(R.drawable.gplefthp1);
      break;
    case 365:
      paramNumImageView1.setImageResource(R.drawable.gpdownhp1);
      paramNumImageView2.setImageResource(R.drawable.gpdownhp1);
      paramNumImageView3.setImageResource(R.drawable.gpdownhp1);
      paramNumImageView4.setImageResource(R.drawable.gpdownhp1);
      paramNumImageView5.setImageResource(R.drawable.gpdownhp1);
      break;
    case 364:
      paramNumImageView1.setImageResource(R.drawable.gpuphp1);
      paramNumImageView2.setImageResource(R.drawable.gpuphp1);
      paramNumImageView3.setImageResource(R.drawable.gpuphp1);
      paramNumImageView4.setImageResource(R.drawable.gpuphp1);
      paramNumImageView5.setImageResource(R.drawable.gpuphp1);
      break;
    case 363:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprbhp1);
        paramNumImageView2.setImageResource(R.drawable.gprbhp1);
        paramNumImageView3.setImageResource(R.drawable.gprbhp1);
        paramNumImageView4.setImageResource(R.drawable.gprbhp1);
        paramNumImageView5.setImageResource(R.drawable.gprbhp1);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1hp1);
        paramNumImageView2.setImageResource(R.drawable.gpr1hp1);
        paramNumImageView3.setImageResource(R.drawable.gpr1hp1);
        paramNumImageView4.setImageResource(R.drawable.gpr1hp1);
        paramNumImageView5.setImageResource(R.drawable.gpr1hp1);
      }
      break;
    case 362:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplbhp1);
        paramNumImageView2.setImageResource(R.drawable.gplbhp1);
        paramNumImageView3.setImageResource(R.drawable.gplbhp1);
        paramNumImageView4.setImageResource(R.drawable.gplbhp1);
        paramNumImageView5.setImageResource(R.drawable.gplbhp1);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1hp1);
        paramNumImageView2.setImageResource(R.drawable.gpl1hp1);
        paramNumImageView3.setImageResource(R.drawable.gpl1hp1);
        paramNumImageView4.setImageResource(R.drawable.gpl1hp1);
        paramNumImageView5.setImageResource(R.drawable.gpl1hp1);
      }
      break;
    case 361:
      paramNumImageView1.setImageResource(R.drawable.gpyhp1);
      paramNumImageView2.setImageResource(R.drawable.gpyhp1);
      paramNumImageView3.setImageResource(R.drawable.gpyhp1);
      paramNumImageView4.setImageResource(R.drawable.gpyhp1);
      paramNumImageView5.setImageResource(R.drawable.gpyhp1);
      break;
    case 360:
      paramNumImageView1.setImageResource(R.drawable.gpxhp1);
      paramNumImageView2.setImageResource(R.drawable.gpxhp1);
      paramNumImageView3.setImageResource(R.drawable.gpxhp1);
      paramNumImageView4.setImageResource(R.drawable.gpxhp1);
      paramNumImageView5.setImageResource(R.drawable.gpxhp1);
      break;
    case 359:
      paramNumImageView1.setImageResource(R.drawable.gpbhp1);
      paramNumImageView2.setImageResource(R.drawable.gpbhp1);
      paramNumImageView3.setImageResource(R.drawable.gpbhp1);
      paramNumImageView4.setImageResource(R.drawable.gpbhp1);
      paramNumImageView5.setImageResource(R.drawable.gpbhp1);
      break;
    case 358:
      paramNumImageView1.setImageResource(R.drawable.gpahp1);
      paramNumImageView2.setImageResource(R.drawable.gpahp1);
      paramNumImageView3.setImageResource(R.drawable.gpahp1);
      paramNumImageView4.setImageResource(R.drawable.gpahp1);
      paramNumImageView5.setImageResource(R.drawable.gpahp1);
      break;
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
      paramNumImageView1.setImageResource(R.drawable.newkey);
      paramNumImageView2.setImageResource(R.drawable.newkey);
      paramNumImageView3.setImageResource(R.drawable.newkey);
      paramNumImageView4.setImageResource(R.drawable.newkey);
      paramNumImageView5.setImageResource(R.drawable.newkey);
      break;
    case 347:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpm4com);
        paramNumImageView2.setImageResource(R.drawable.gpm4comrb);
        paramNumImageView3.setImageResource(R.drawable.gpm4comrt);
        paramNumImageView4.setImageResource(R.drawable.gpm4comlb);
        paramNumImageView5.setImageResource(R.drawable.gpm4comlt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpm4com);
        paramNumImageView2.setImageResource(R.drawable.gpm4comr1);
        paramNumImageView3.setImageResource(R.drawable.gpm4comr2);
        paramNumImageView4.setImageResource(R.drawable.gpm4coml1);
        paramNumImageView5.setImageResource(R.drawable.gpm4coml2);
      }
      break;
    case 346:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpm3com);
        paramNumImageView2.setImageResource(R.drawable.gpm3comrb);
        paramNumImageView3.setImageResource(R.drawable.gpm3comrt);
        paramNumImageView4.setImageResource(R.drawable.gpm3comlb);
        paramNumImageView5.setImageResource(R.drawable.gpm3comlt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpm3com);
        paramNumImageView2.setImageResource(R.drawable.gpm3comr1);
        paramNumImageView3.setImageResource(R.drawable.gpm3comr2);
        paramNumImageView4.setImageResource(R.drawable.gpm3coml1);
        paramNumImageView5.setImageResource(R.drawable.gpm3coml2);
      }
      break;
    case 345:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpm2com);
        paramNumImageView2.setImageResource(R.drawable.gpm2comrb);
        paramNumImageView3.setImageResource(R.drawable.gpm2comrt);
        paramNumImageView4.setImageResource(R.drawable.gpm2comlb);
        paramNumImageView5.setImageResource(R.drawable.gpm2comlt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpm2com);
        paramNumImageView2.setImageResource(R.drawable.gpm2comr1);
        paramNumImageView3.setImageResource(R.drawable.gpm2comr2);
        paramNumImageView4.setImageResource(R.drawable.gpm2coml1);
        paramNumImageView5.setImageResource(R.drawable.gpm2coml2);
      }
      break;
    case 344:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpm1com);
        paramNumImageView2.setImageResource(R.drawable.gpm1comrb);
        paramNumImageView3.setImageResource(R.drawable.gpm1comrt);
        paramNumImageView4.setImageResource(R.drawable.gpm1comlb);
        paramNumImageView5.setImageResource(R.drawable.gpm1comlt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpm1com);
        paramNumImageView2.setImageResource(R.drawable.gpm1comr1);
        paramNumImageView3.setImageResource(R.drawable.gpm1comr2);
        paramNumImageView4.setImageResource(R.drawable.gpm1coml1);
        paramNumImageView5.setImageResource(R.drawable.gpm1coml2);
      }
      break;
    case 343:
      paramNumImageView1.setImageResource(R.drawable.gpbpr);
      paramNumImageView2.setImageResource(R.drawable.gpbpr);
      paramNumImageView3.setImageResource(R.drawable.gpbpr);
      paramNumImageView4.setImageResource(R.drawable.gpbpr);
      paramNumImageView5.setImageResource(R.drawable.gpbpr);
      break;
    case 342:
      paramNumImageView1.setImageResource(R.drawable.gpbpl);
      paramNumImageView2.setImageResource(R.drawable.gpbpl);
      paramNumImageView3.setImageResource(R.drawable.gpbpl);
      paramNumImageView4.setImageResource(R.drawable.gpbpl);
      paramNumImageView5.setImageResource(R.drawable.gpbpl);
      break;
    case 341:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprtcom);
        paramNumImageView2.setImageResource(R.drawable.gprtcomrb);
        paramNumImageView3.setImageResource(R.drawable.gprtcomrt);
        paramNumImageView4.setImageResource(R.drawable.gprtcomlb);
        paramNumImageView5.setImageResource(R.drawable.gprtcomlt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2com);
        paramNumImageView2.setImageResource(R.drawable.gpr2comr1);
        paramNumImageView3.setImageResource(R.drawable.gpr2comr2);
        paramNumImageView4.setImageResource(R.drawable.gpr2coml1);
        paramNumImageView5.setImageResource(R.drawable.gpr2coml2);
      }
      break;
    case 340:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpltcom);
        paramNumImageView2.setImageResource(R.drawable.gpltcomrb);
        paramNumImageView3.setImageResource(R.drawable.gpltcomrt);
        paramNumImageView4.setImageResource(R.drawable.gpltcomlb);
        paramNumImageView5.setImageResource(R.drawable.gpltcomlt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2com);
        paramNumImageView2.setImageResource(R.drawable.gpl2comr1);
        paramNumImageView3.setImageResource(R.drawable.gpl2comr2);
        paramNumImageView4.setImageResource(R.drawable.gpl2coml1);
        paramNumImageView5.setImageResource(R.drawable.gpl2coml2);
      }
      break;
    case 339:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpr3com);
        paramNumImageView2.setImageResource(R.drawable.gpr3comrb);
        paramNumImageView3.setImageResource(R.drawable.gpr3comrt);
        paramNumImageView4.setImageResource(R.drawable.gpr3comlb);
        paramNumImageView5.setImageResource(R.drawable.gpr3comlt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr3com);
        paramNumImageView2.setImageResource(R.drawable.gpr3comr1);
        paramNumImageView3.setImageResource(R.drawable.gpr3comr2);
        paramNumImageView4.setImageResource(R.drawable.gpr3coml1);
        paramNumImageView5.setImageResource(R.drawable.gpr3coml2);
      }
      break;
    case 338:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpl3com);
        paramNumImageView2.setImageResource(R.drawable.gpl3comrb);
        paramNumImageView3.setImageResource(R.drawable.gpl3comrt);
        paramNumImageView4.setImageResource(R.drawable.gpl3comlb);
        paramNumImageView5.setImageResource(R.drawable.gpl3comlt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl3com);
        paramNumImageView2.setImageResource(R.drawable.gpl3comr1);
        paramNumImageView3.setImageResource(R.drawable.gpl3comr2);
        paramNumImageView4.setImageResource(R.drawable.gpl3coml1);
        paramNumImageView5.setImageResource(R.drawable.gpl3coml2);
      }
      break;
    case 337:
      paramNumImageView1.setImageResource(R.drawable.gpselect);
      paramNumImageView2.setImageResource(R.drawable.gpselect);
      paramNumImageView3.setImageResource(R.drawable.gpselect);
      paramNumImageView4.setImageResource(R.drawable.gpselect);
      paramNumImageView5.setImageResource(R.drawable.gpselect);
      break;
    case 336:
      paramNumImageView1.setImageResource(R.drawable.gpstart);
      paramNumImageView2.setImageResource(R.drawable.gpstart);
      paramNumImageView3.setImageResource(R.drawable.gpstart);
      paramNumImageView4.setImageResource(R.drawable.gpstart);
      paramNumImageView5.setImageResource(R.drawable.gpstart);
      break;
    case 335:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprightcom);
        paramNumImageView2.setImageResource(R.drawable.gprightcomrb);
        paramNumImageView3.setImageResource(R.drawable.gprightcomrt);
        paramNumImageView4.setImageResource(R.drawable.gprightcomlb);
        paramNumImageView5.setImageResource(R.drawable.gprightcomlt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gprightcom);
        paramNumImageView2.setImageResource(R.drawable.gprightcomr1);
        paramNumImageView3.setImageResource(R.drawable.gprightcomr2);
        paramNumImageView4.setImageResource(R.drawable.gprightcoml1);
        paramNumImageView5.setImageResource(R.drawable.gprightcoml2);
      }
      break;
    case 334:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpleftcom);
        paramNumImageView2.setImageResource(R.drawable.gpleftcomrb);
        paramNumImageView3.setImageResource(R.drawable.gpleftcomrt);
        paramNumImageView4.setImageResource(R.drawable.gpleftcomlb);
        paramNumImageView5.setImageResource(R.drawable.gpleftcomlt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpleftcom);
        paramNumImageView2.setImageResource(R.drawable.gpleftcomr1);
        paramNumImageView3.setImageResource(R.drawable.gpleftcomr2);
        paramNumImageView4.setImageResource(R.drawable.gpleftcoml1);
        paramNumImageView5.setImageResource(R.drawable.gpleftcoml2);
      }
      break;
    case 333:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpdowncom);
        paramNumImageView2.setImageResource(R.drawable.gpdowncomrb);
        paramNumImageView3.setImageResource(R.drawable.gpdowncomrt);
        paramNumImageView4.setImageResource(R.drawable.gpdowncomlb);
        paramNumImageView5.setImageResource(R.drawable.gpdowncomlt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpdowncom);
        paramNumImageView2.setImageResource(R.drawable.gpdowncomr1);
        paramNumImageView3.setImageResource(R.drawable.gpdowncomr2);
        paramNumImageView4.setImageResource(R.drawable.gpdowncoml1);
        paramNumImageView5.setImageResource(R.drawable.gpdowncoml2);
      }
      break;
    case 332:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpupcom);
        paramNumImageView2.setImageResource(R.drawable.gpupcomrb);
        paramNumImageView3.setImageResource(R.drawable.gpupcomrt);
        paramNumImageView4.setImageResource(R.drawable.gpupcomlb);
        paramNumImageView5.setImageResource(R.drawable.gpupcomlt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpupcom);
        paramNumImageView2.setImageResource(R.drawable.gpupcomr1);
        paramNumImageView3.setImageResource(R.drawable.gpupcomr2);
        paramNumImageView4.setImageResource(R.drawable.gpupcoml1);
        paramNumImageView5.setImageResource(R.drawable.gpupcoml2);
      }
      break;
    case 331:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprbcom);
        paramNumImageView2.setImageResource(R.drawable.gprbcomrb);
        paramNumImageView3.setImageResource(R.drawable.gprbcomrt);
        paramNumImageView4.setImageResource(R.drawable.gprbcomlb);
        paramNumImageView5.setImageResource(R.drawable.gprbcomlt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1com);
        paramNumImageView2.setImageResource(R.drawable.gpr1comr1);
        paramNumImageView3.setImageResource(R.drawable.gpr1comr2);
        paramNumImageView4.setImageResource(R.drawable.gpr1coml1);
        paramNumImageView5.setImageResource(R.drawable.gpr1coml2);
      }
      break;
    case 330:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplbcom);
        paramNumImageView2.setImageResource(R.drawable.gplbcomrb);
        paramNumImageView3.setImageResource(R.drawable.gplbcomrt);
        paramNumImageView4.setImageResource(R.drawable.gplbcomlb);
        paramNumImageView5.setImageResource(R.drawable.gplbcomlt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1com);
        paramNumImageView2.setImageResource(R.drawable.gpl1comr1);
        paramNumImageView3.setImageResource(R.drawable.gpl1comr2);
        paramNumImageView4.setImageResource(R.drawable.gpl1coml1);
        paramNumImageView5.setImageResource(R.drawable.gpl1coml2);
      }
      break;
    case 329:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpycom);
        paramNumImageView2.setImageResource(R.drawable.gpycomrb);
        paramNumImageView3.setImageResource(R.drawable.gpycomrt);
        paramNumImageView4.setImageResource(R.drawable.gpycomlb);
        paramNumImageView5.setImageResource(R.drawable.gpycomlt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpycom);
        paramNumImageView2.setImageResource(R.drawable.gpycomr1);
        paramNumImageView3.setImageResource(R.drawable.gpycomr2);
        paramNumImageView4.setImageResource(R.drawable.gpycoml1);
        paramNumImageView5.setImageResource(R.drawable.gpycoml2);
      }
      break;
    case 328:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpxcom);
        paramNumImageView2.setImageResource(R.drawable.gpxcomrb);
        paramNumImageView3.setImageResource(R.drawable.gpxcomrt);
        paramNumImageView4.setImageResource(R.drawable.gpxcomlb);
        paramNumImageView5.setImageResource(R.drawable.gpxcomlt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpxcom);
        paramNumImageView2.setImageResource(R.drawable.gpxcomr1);
        paramNumImageView3.setImageResource(R.drawable.gpxcomr2);
        paramNumImageView4.setImageResource(R.drawable.gpxcoml1);
        paramNumImageView5.setImageResource(R.drawable.gpxcoml2);
      }
      break;
    case 327:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpbcom);
        paramNumImageView2.setImageResource(R.drawable.gpbcomrb);
        paramNumImageView3.setImageResource(R.drawable.gpbcomrt);
        paramNumImageView4.setImageResource(R.drawable.gpbcomlb);
        paramNumImageView5.setImageResource(R.drawable.gpbcomlt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpbcom);
        paramNumImageView2.setImageResource(R.drawable.gpbcomr1);
        paramNumImageView3.setImageResource(R.drawable.gpbcomr2);
        paramNumImageView4.setImageResource(R.drawable.gpbcoml1);
        paramNumImageView5.setImageResource(R.drawable.gpbcoml2);
      }
      break;
    case 326:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpacom);
        paramNumImageView2.setImageResource(R.drawable.gpacomrb);
        paramNumImageView3.setImageResource(R.drawable.gpacomrt);
        paramNumImageView4.setImageResource(R.drawable.gpacomlb);
        paramNumImageView5.setImageResource(R.drawable.gpacomlt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpacom);
        paramNumImageView2.setImageResource(R.drawable.gpacomr1);
        paramNumImageView3.setImageResource(R.drawable.gpacomr2);
        paramNumImageView4.setImageResource(R.drawable.gpacoml1);
        paramNumImageView5.setImageResource(R.drawable.gpacoml2);
      }
      break;
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
      paramNumImageView1.setImageResource(R.drawable.newkey);
      paramNumImageView2.setImageResource(R.drawable.newkey);
      paramNumImageView3.setImageResource(R.drawable.newkey);
      paramNumImageView4.setImageResource(R.drawable.newkey);
      paramNumImageView5.setImageResource(R.drawable.newkey);
      break;
    case 315:
      paramNumImageView1.setImageResource(R.drawable.gpm4first);
      paramNumImageView2.setImageResource(R.drawable.gpm4first);
      paramNumImageView3.setImageResource(R.drawable.gpm4first);
      paramNumImageView4.setImageResource(R.drawable.gpm4first);
      paramNumImageView5.setImageResource(R.drawable.gpm4first);
      break;
    case 314:
      paramNumImageView1.setImageResource(R.drawable.gpm3first);
      paramNumImageView2.setImageResource(R.drawable.gpm3first);
      paramNumImageView3.setImageResource(R.drawable.gpm3first);
      paramNumImageView4.setImageResource(R.drawable.gpm3first);
      paramNumImageView5.setImageResource(R.drawable.gpm3first);
      break;
    case 313:
      paramNumImageView1.setImageResource(R.drawable.gpm2first);
      paramNumImageView2.setImageResource(R.drawable.gpm2first);
      paramNumImageView3.setImageResource(R.drawable.gpm2first);
      paramNumImageView4.setImageResource(R.drawable.gpm2first);
      paramNumImageView5.setImageResource(R.drawable.gpm2first);
      break;
    case 312:
      paramNumImageView1.setImageResource(R.drawable.gpm1first);
      paramNumImageView2.setImageResource(R.drawable.gpm1first);
      paramNumImageView3.setImageResource(R.drawable.gpm1first);
      paramNumImageView4.setImageResource(R.drawable.gpm1first);
      paramNumImageView5.setImageResource(R.drawable.gpm1first);
      break;
    case 311:
      paramNumImageView1.setImageResource(R.drawable.gplbfirst);
      paramNumImageView2.setImageResource(R.drawable.gplbfirst);
      paramNumImageView3.setImageResource(R.drawable.gplbfirst);
      paramNumImageView4.setImageResource(R.drawable.gplbfirst);
      paramNumImageView5.setImageResource(R.drawable.gplbfirst);
      break;
    case 310:
      paramNumImageView1.setImageResource(R.drawable.gplbfirst);
      paramNumImageView2.setImageResource(R.drawable.gplbfirst);
      paramNumImageView3.setImageResource(R.drawable.gplbfirst);
      paramNumImageView4.setImageResource(R.drawable.gplbfirst);
      paramNumImageView5.setImageResource(R.drawable.gplbfirst);
      break;
    case 309:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprtfirst);
        paramNumImageView2.setImageResource(R.drawable.gprtfirst);
        paramNumImageView3.setImageResource(R.drawable.gprtfirst);
        paramNumImageView4.setImageResource(R.drawable.gprtfirst);
        paramNumImageView5.setImageResource(R.drawable.gprtfirst);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2first);
        paramNumImageView2.setImageResource(R.drawable.gpr2first);
        paramNumImageView3.setImageResource(R.drawable.gpr2first);
        paramNumImageView4.setImageResource(R.drawable.gpr2first);
        paramNumImageView5.setImageResource(R.drawable.gpr2first);
      }
      break;
    case 308:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpltfirst);
        paramNumImageView2.setImageResource(R.drawable.gpltfirst);
        paramNumImageView3.setImageResource(R.drawable.gpltfirst);
        paramNumImageView4.setImageResource(R.drawable.gpltfirst);
        paramNumImageView5.setImageResource(R.drawable.gpltfirst);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2first);
        paramNumImageView2.setImageResource(R.drawable.gpl2first);
        paramNumImageView3.setImageResource(R.drawable.gpl2first);
        paramNumImageView4.setImageResource(R.drawable.gpl2first);
        paramNumImageView5.setImageResource(R.drawable.gpl2first);
      }
      break;
    case 307:
      paramNumImageView1.setImageResource(R.drawable.gpr3first);
      paramNumImageView2.setImageResource(R.drawable.gpr3first);
      paramNumImageView3.setImageResource(R.drawable.gpr3first);
      paramNumImageView4.setImageResource(R.drawable.gpr3first);
      paramNumImageView5.setImageResource(R.drawable.gpr3first);
      break;
    case 306:
      paramNumImageView1.setImageResource(R.drawable.gpl3first);
      paramNumImageView2.setImageResource(R.drawable.gpl3first);
      paramNumImageView3.setImageResource(R.drawable.gpl3first);
      paramNumImageView4.setImageResource(R.drawable.gpl3first);
      paramNumImageView5.setImageResource(R.drawable.gpl3first);
      break;
    case 305:
      paramNumImageView1.setImageResource(R.drawable.gplbfirst);
      paramNumImageView2.setImageResource(R.drawable.gplbfirst);
      paramNumImageView3.setImageResource(R.drawable.gplbfirst);
      paramNumImageView4.setImageResource(R.drawable.gplbfirst);
      paramNumImageView5.setImageResource(R.drawable.gplbfirst);
      break;
    case 304:
      paramNumImageView1.setImageResource(R.drawable.gplbfirst);
      paramNumImageView2.setImageResource(R.drawable.gplbfirst);
      paramNumImageView3.setImageResource(R.drawable.gplbfirst);
      paramNumImageView4.setImageResource(R.drawable.gplbfirst);
      paramNumImageView5.setImageResource(R.drawable.gplbfirst);
      break;
    case 303:
      paramNumImageView1.setImageResource(R.drawable.gplbfirst);
      paramNumImageView2.setImageResource(R.drawable.gplbfirst);
      paramNumImageView3.setImageResource(R.drawable.gplbfirst);
      paramNumImageView4.setImageResource(R.drawable.gplbfirst);
      paramNumImageView5.setImageResource(R.drawable.gplbfirst);
      break;
    case 302:
      paramNumImageView1.setImageResource(R.drawable.gplbfirst);
      paramNumImageView2.setImageResource(R.drawable.gplbfirst);
      paramNumImageView3.setImageResource(R.drawable.gplbfirst);
      paramNumImageView4.setImageResource(R.drawable.gplbfirst);
      paramNumImageView5.setImageResource(R.drawable.gplbfirst);
      break;
    case 301:
      paramNumImageView1.setImageResource(R.drawable.gplbfirst);
      paramNumImageView2.setImageResource(R.drawable.gplbfirst);
      paramNumImageView3.setImageResource(R.drawable.gplbfirst);
      paramNumImageView4.setImageResource(R.drawable.gplbfirst);
      paramNumImageView5.setImageResource(R.drawable.gplbfirst);
      break;
    case 300:
      paramNumImageView1.setImageResource(R.drawable.gplbfirst);
      paramNumImageView2.setImageResource(R.drawable.gplbfirst);
      paramNumImageView3.setImageResource(R.drawable.gplbfirst);
      paramNumImageView4.setImageResource(R.drawable.gplbfirst);
      paramNumImageView5.setImageResource(R.drawable.gplbfirst);
      break;
    case 299:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprbfirst);
        paramNumImageView2.setImageResource(R.drawable.gprbfirst);
        paramNumImageView3.setImageResource(R.drawable.gprbfirst);
        paramNumImageView4.setImageResource(R.drawable.gprbfirst);
        paramNumImageView5.setImageResource(R.drawable.gprbfirst);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1first);
        paramNumImageView2.setImageResource(R.drawable.gpr1first);
        paramNumImageView3.setImageResource(R.drawable.gpr1first);
        paramNumImageView4.setImageResource(R.drawable.gpr1first);
        paramNumImageView5.setImageResource(R.drawable.gpr1first);
      }
      break;
    case 298:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplbfirst);
        paramNumImageView2.setImageResource(R.drawable.gplbfirst);
        paramNumImageView3.setImageResource(R.drawable.gplbfirst);
        paramNumImageView4.setImageResource(R.drawable.gplbfirst);
        paramNumImageView5.setImageResource(R.drawable.gplbfirst);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1first);
        paramNumImageView2.setImageResource(R.drawable.gpl1first);
        paramNumImageView3.setImageResource(R.drawable.gpl1first);
        paramNumImageView4.setImageResource(R.drawable.gpl1first);
        paramNumImageView5.setImageResource(R.drawable.gpl1first);
      }
      break;
    case 297:
      paramNumImageView1.setImageResource(R.drawable.gplbfirst);
      paramNumImageView2.setImageResource(R.drawable.gplbfirst);
      paramNumImageView3.setImageResource(R.drawable.gplbfirst);
      paramNumImageView4.setImageResource(R.drawable.gplbfirst);
      paramNumImageView5.setImageResource(R.drawable.gplbfirst);
      break;
    case 296:
      paramNumImageView1.setImageResource(R.drawable.gplbfirst);
      paramNumImageView2.setImageResource(R.drawable.gplbfirst);
      paramNumImageView3.setImageResource(R.drawable.gplbfirst);
      paramNumImageView4.setImageResource(R.drawable.gplbfirst);
      paramNumImageView5.setImageResource(R.drawable.gplbfirst);
      break;
    case 295:
      paramNumImageView1.setImageResource(R.drawable.gplbfirst);
      paramNumImageView2.setImageResource(R.drawable.gplbfirst);
      paramNumImageView3.setImageResource(R.drawable.gplbfirst);
      paramNumImageView4.setImageResource(R.drawable.gplbfirst);
      paramNumImageView5.setImageResource(R.drawable.gplbfirst);
      break;
    case 294:
      paramNumImageView1.setImageResource(R.drawable.gplbfirst);
      paramNumImageView2.setImageResource(R.drawable.gplbfirst);
      paramNumImageView3.setImageResource(R.drawable.gplbfirst);
      paramNumImageView4.setImageResource(R.drawable.gplbfirst);
      paramNumImageView5.setImageResource(R.drawable.gplbfirst);
      break;
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
      paramNumImageView1.setImageResource(R.drawable.newkey);
      paramNumImageView2.setImageResource(R.drawable.newkey);
      paramNumImageView3.setImageResource(R.drawable.newkey);
      paramNumImageView4.setImageResource(R.drawable.newkey);
      paramNumImageView5.setImageResource(R.drawable.newkey);
      break;
    case 283:
      paramNumImageView1.setImageResource(R.drawable.gpm4rview);
      paramNumImageView2.setImageResource(R.drawable.gpm4rview);
      paramNumImageView3.setImageResource(R.drawable.gpm4rview);
      paramNumImageView4.setImageResource(R.drawable.gpm4rview);
      paramNumImageView5.setImageResource(R.drawable.gpm4rview);
      break;
    case 282:
      paramNumImageView1.setImageResource(R.drawable.gpm3rview);
      paramNumImageView2.setImageResource(R.drawable.gpm3rview);
      paramNumImageView3.setImageResource(R.drawable.gpm3rview);
      paramNumImageView4.setImageResource(R.drawable.gpm3rview);
      paramNumImageView5.setImageResource(R.drawable.gpm3rview);
      break;
    case 281:
      paramNumImageView1.setImageResource(R.drawable.gpm2rview);
      paramNumImageView2.setImageResource(R.drawable.gpm2rview);
      paramNumImageView3.setImageResource(R.drawable.gpm2rview);
      paramNumImageView4.setImageResource(R.drawable.gpm2rview);
      paramNumImageView5.setImageResource(R.drawable.gpm2rview);
      break;
    case 280:
      paramNumImageView1.setImageResource(R.drawable.gpm1rview);
      paramNumImageView2.setImageResource(R.drawable.gpm1rview);
      paramNumImageView3.setImageResource(R.drawable.gpm1rview);
      paramNumImageView4.setImageResource(R.drawable.gpm1rview);
      paramNumImageView5.setImageResource(R.drawable.gpm1rview);
      break;
    case 279:
      paramNumImageView1.setImageResource(R.drawable.gparview);
      paramNumImageView2.setImageResource(R.drawable.gparview);
      paramNumImageView3.setImageResource(R.drawable.gparview);
      paramNumImageView4.setImageResource(R.drawable.gparview);
      paramNumImageView5.setImageResource(R.drawable.gparview);
      break;
    case 278:
      paramNumImageView1.setImageResource(R.drawable.gparview);
      paramNumImageView2.setImageResource(R.drawable.gparview);
      paramNumImageView3.setImageResource(R.drawable.gparview);
      paramNumImageView4.setImageResource(R.drawable.gparview);
      paramNumImageView5.setImageResource(R.drawable.gparview);
      break;
    case 277:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprtview);
        paramNumImageView2.setImageResource(R.drawable.gprtview);
        paramNumImageView3.setImageResource(R.drawable.gprtview);
        paramNumImageView4.setImageResource(R.drawable.gprtview);
        paramNumImageView5.setImageResource(R.drawable.gprtview);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2rview);
        paramNumImageView2.setImageResource(R.drawable.gpr2rview);
        paramNumImageView3.setImageResource(R.drawable.gpr2rview);
        paramNumImageView4.setImageResource(R.drawable.gpr2rview);
        paramNumImageView5.setImageResource(R.drawable.gpr2rview);
      }
      break;
    case 276:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpltview);
        paramNumImageView2.setImageResource(R.drawable.gpltview);
        paramNumImageView3.setImageResource(R.drawable.gpltview);
        paramNumImageView4.setImageResource(R.drawable.gpltview);
        paramNumImageView5.setImageResource(R.drawable.gpltview);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2rview);
        paramNumImageView2.setImageResource(R.drawable.gpl2rview);
        paramNumImageView3.setImageResource(R.drawable.gpl2rview);
        paramNumImageView4.setImageResource(R.drawable.gpl2rview);
        paramNumImageView5.setImageResource(R.drawable.gpl2rview);
      }
      break;
    case 275:
      paramNumImageView1.setImageResource(R.drawable.gpr3rview);
      paramNumImageView2.setImageResource(R.drawable.gpr3rview);
      paramNumImageView3.setImageResource(R.drawable.gpr3rview);
      paramNumImageView4.setImageResource(R.drawable.gpr3rview);
      paramNumImageView5.setImageResource(R.drawable.gpr3rview);
      break;
    case 274:
      paramNumImageView1.setImageResource(R.drawable.gpl3rview);
      paramNumImageView2.setImageResource(R.drawable.gpl3rview);
      paramNumImageView3.setImageResource(R.drawable.gpl3rview);
      paramNumImageView4.setImageResource(R.drawable.gpl3rview);
      paramNumImageView5.setImageResource(R.drawable.gpl3rview);
      break;
    case 273:
      paramNumImageView1.setImageResource(R.drawable.gpselect);
      paramNumImageView2.setImageResource(R.drawable.gpselect);
      paramNumImageView3.setImageResource(R.drawable.gpselect);
      paramNumImageView4.setImageResource(R.drawable.gpselect);
      paramNumImageView5.setImageResource(R.drawable.gpselect);
      break;
    case 272:
      paramNumImageView1.setImageResource(R.drawable.gpstart);
      paramNumImageView2.setImageResource(R.drawable.gpstart);
      paramNumImageView3.setImageResource(R.drawable.gpstart);
      paramNumImageView4.setImageResource(R.drawable.gpstart);
      paramNumImageView5.setImageResource(R.drawable.gpstart);
      break;
    case 271:
      paramNumImageView1.setImageResource(R.drawable.gparview);
      paramNumImageView2.setImageResource(R.drawable.gparview);
      paramNumImageView3.setImageResource(R.drawable.gparview);
      paramNumImageView4.setImageResource(R.drawable.gparview);
      paramNumImageView5.setImageResource(R.drawable.gparview);
      break;
    case 270:
      paramNumImageView1.setImageResource(R.drawable.gparview);
      paramNumImageView2.setImageResource(R.drawable.gparview);
      paramNumImageView3.setImageResource(R.drawable.gparview);
      paramNumImageView4.setImageResource(R.drawable.gparview);
      paramNumImageView5.setImageResource(R.drawable.gparview);
      break;
    case 269:
      paramNumImageView1.setImageResource(R.drawable.gparview);
      paramNumImageView2.setImageResource(R.drawable.gparview);
      paramNumImageView3.setImageResource(R.drawable.gparview);
      paramNumImageView4.setImageResource(R.drawable.gparview);
      paramNumImageView5.setImageResource(R.drawable.gparview);
      break;
    case 268:
      paramNumImageView1.setImageResource(R.drawable.gparview);
      paramNumImageView2.setImageResource(R.drawable.gparview);
      paramNumImageView3.setImageResource(R.drawable.gparview);
      paramNumImageView4.setImageResource(R.drawable.gparview);
      paramNumImageView5.setImageResource(R.drawable.gparview);
      break;
    case 267:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprbview);
        paramNumImageView2.setImageResource(R.drawable.gprbview);
        paramNumImageView3.setImageResource(R.drawable.gprbview);
        paramNumImageView4.setImageResource(R.drawable.gprbview);
        paramNumImageView5.setImageResource(R.drawable.gprbview);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1rview);
        paramNumImageView2.setImageResource(R.drawable.gpr1rview);
        paramNumImageView3.setImageResource(R.drawable.gpr1rview);
        paramNumImageView4.setImageResource(R.drawable.gpr1rview);
        paramNumImageView5.setImageResource(R.drawable.gpr1rview);
      }
      break;
    case 266:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplbview);
        paramNumImageView2.setImageResource(R.drawable.gplbview);
        paramNumImageView3.setImageResource(R.drawable.gplbview);
        paramNumImageView4.setImageResource(R.drawable.gplbview);
        paramNumImageView5.setImageResource(R.drawable.gplbview);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1rview);
        paramNumImageView2.setImageResource(R.drawable.gpl1rview);
        paramNumImageView3.setImageResource(R.drawable.gpl1rview);
        paramNumImageView4.setImageResource(R.drawable.gpl1rview);
        paramNumImageView5.setImageResource(R.drawable.gpl1rview);
      }
      break;
    case 265:
      paramNumImageView1.setImageResource(R.drawable.gpyrview);
      paramNumImageView2.setImageResource(R.drawable.gpyrview);
      paramNumImageView3.setImageResource(R.drawable.gpyrview);
      paramNumImageView4.setImageResource(R.drawable.gpyrview);
      paramNumImageView5.setImageResource(R.drawable.gpyrview);
      break;
    case 264:
      paramNumImageView1.setImageResource(R.drawable.gpxrview);
      paramNumImageView2.setImageResource(R.drawable.gpxrview);
      paramNumImageView3.setImageResource(R.drawable.gpxrview);
      paramNumImageView4.setImageResource(R.drawable.gpxrview);
      paramNumImageView5.setImageResource(R.drawable.gpxrview);
      break;
    case 263:
      paramNumImageView1.setImageResource(R.drawable.gpbrview);
      paramNumImageView2.setImageResource(R.drawable.gpbrview);
      paramNumImageView3.setImageResource(R.drawable.gpbrview);
      paramNumImageView4.setImageResource(R.drawable.gpbrview);
      paramNumImageView5.setImageResource(R.drawable.gpbrview);
      break;
    case 262:
      paramNumImageView1.setImageResource(R.drawable.gparview);
      paramNumImageView2.setImageResource(R.drawable.gparview);
      paramNumImageView3.setImageResource(R.drawable.gparview);
      paramNumImageView4.setImageResource(R.drawable.gparview);
      paramNumImageView5.setImageResource(R.drawable.gparview);
      break;
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
      paramNumImageView1.setImageResource(R.drawable.newkey);
      paramNumImageView2.setImageResource(R.drawable.newkey);
      paramNumImageView3.setImageResource(R.drawable.newkey);
      paramNumImageView4.setImageResource(R.drawable.newkey);
      paramNumImageView5.setImageResource(R.drawable.newkey);
      break;
    case 251:
      paramNumImageView1.setImageResource(R.drawable.gpm4y2);
      paramNumImageView2.setImageResource(R.drawable.gpm4y2);
      paramNumImageView3.setImageResource(R.drawable.gpm4y2);
      paramNumImageView4.setImageResource(R.drawable.gpm4y2);
      paramNumImageView5.setImageResource(R.drawable.gpm4y2);
      break;
    case 250:
      paramNumImageView1.setImageResource(R.drawable.gpm3y2);
      paramNumImageView2.setImageResource(R.drawable.gpm3y2);
      paramNumImageView3.setImageResource(R.drawable.gpm3y2);
      paramNumImageView4.setImageResource(R.drawable.gpm3y2);
      paramNumImageView5.setImageResource(R.drawable.gpm3y2);
      break;
    case 249:
      paramNumImageView1.setImageResource(R.drawable.gpm2y2);
      paramNumImageView2.setImageResource(R.drawable.gpm2y2);
      paramNumImageView3.setImageResource(R.drawable.gpm2y2);
      paramNumImageView4.setImageResource(R.drawable.gpm2y2);
      paramNumImageView5.setImageResource(R.drawable.gpm2y2);
      break;
    case 248:
      paramNumImageView1.setImageResource(R.drawable.gpm1y2);
      paramNumImageView2.setImageResource(R.drawable.gpm1y2);
      paramNumImageView3.setImageResource(R.drawable.gpm1y2);
      paramNumImageView4.setImageResource(R.drawable.gpm1y2);
      paramNumImageView5.setImageResource(R.drawable.gpm1y2);
      break;
    case 247:
      paramNumImageView1.setImageResource(R.drawable.gpbpr);
      paramNumImageView2.setImageResource(R.drawable.gpbpr);
      paramNumImageView3.setImageResource(R.drawable.gpbpr);
      paramNumImageView4.setImageResource(R.drawable.gpbpr);
      paramNumImageView5.setImageResource(R.drawable.gpbpr);
      break;
    case 246:
      paramNumImageView1.setImageResource(R.drawable.gpbpl);
      paramNumImageView2.setImageResource(R.drawable.gpbpl);
      paramNumImageView3.setImageResource(R.drawable.gpbpl);
      paramNumImageView4.setImageResource(R.drawable.gpbpl);
      paramNumImageView5.setImageResource(R.drawable.gpbpl);
      break;
    case 245:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprty2);
        paramNumImageView2.setImageResource(R.drawable.gprty2);
        paramNumImageView3.setImageResource(R.drawable.gprty2);
        paramNumImageView4.setImageResource(R.drawable.gprty2);
        paramNumImageView5.setImageResource(R.drawable.gprty2);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2y2);
        paramNumImageView2.setImageResource(R.drawable.gpr2y2);
        paramNumImageView3.setImageResource(R.drawable.gpr2y2);
        paramNumImageView4.setImageResource(R.drawable.gpr2y2);
        paramNumImageView5.setImageResource(R.drawable.gpr2y2);
      }
      break;
    case 244:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplty2);
        paramNumImageView2.setImageResource(R.drawable.gplty2);
        paramNumImageView3.setImageResource(R.drawable.gplty2);
        paramNumImageView4.setImageResource(R.drawable.gplty2);
        paramNumImageView5.setImageResource(R.drawable.gplty2);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2y2);
        paramNumImageView2.setImageResource(R.drawable.gpl2y2);
        paramNumImageView3.setImageResource(R.drawable.gpl2y2);
        paramNumImageView4.setImageResource(R.drawable.gpl2y2);
        paramNumImageView5.setImageResource(R.drawable.gpl2y2);
      }
      break;
    case 243:
      paramNumImageView1.setImageResource(R.drawable.gpr3y2);
      paramNumImageView2.setImageResource(R.drawable.gpr3y2);
      paramNumImageView3.setImageResource(R.drawable.gpr3y2);
      paramNumImageView4.setImageResource(R.drawable.gpr3y2);
      paramNumImageView5.setImageResource(R.drawable.gpr3y2);
      break;
    case 242:
      paramNumImageView1.setImageResource(R.drawable.gpl3y2);
      paramNumImageView2.setImageResource(R.drawable.gpl3y2);
      paramNumImageView3.setImageResource(R.drawable.gpl3y2);
      paramNumImageView4.setImageResource(R.drawable.gpl3y2);
      paramNumImageView5.setImageResource(R.drawable.gpl3y2);
      break;
    case 241:
      paramNumImageView1.setImageResource(R.drawable.gpselect);
      paramNumImageView2.setImageResource(R.drawable.gpselect);
      paramNumImageView3.setImageResource(R.drawable.gpselect);
      paramNumImageView4.setImageResource(R.drawable.gpselect);
      paramNumImageView5.setImageResource(R.drawable.gpselect);
      break;
    case 240:
      paramNumImageView1.setImageResource(R.drawable.gpstart);
      paramNumImageView2.setImageResource(R.drawable.gpstart);
      paramNumImageView3.setImageResource(R.drawable.gpstart);
      paramNumImageView4.setImageResource(R.drawable.gpstart);
      paramNumImageView5.setImageResource(R.drawable.gpstart);
      break;
    case 239:
      paramNumImageView1.setImageResource(R.drawable.gprighty2);
      paramNumImageView2.setImageResource(R.drawable.gprighty2);
      paramNumImageView3.setImageResource(R.drawable.gprighty2);
      paramNumImageView4.setImageResource(R.drawable.gprighty2);
      paramNumImageView5.setImageResource(R.drawable.gprighty2);
      break;
    case 238:
      paramNumImageView1.setImageResource(R.drawable.gplefty2);
      paramNumImageView2.setImageResource(R.drawable.gplefty2);
      paramNumImageView3.setImageResource(R.drawable.gplefty2);
      paramNumImageView4.setImageResource(R.drawable.gplefty2);
      paramNumImageView5.setImageResource(R.drawable.gplefty2);
      break;
    case 237:
      paramNumImageView1.setImageResource(R.drawable.gpdowny2);
      paramNumImageView2.setImageResource(R.drawable.gpdowny2);
      paramNumImageView3.setImageResource(R.drawable.gpdowny2);
      paramNumImageView4.setImageResource(R.drawable.gpdowny2);
      paramNumImageView5.setImageResource(R.drawable.gpdowny2);
      break;
    case 236:
      paramNumImageView1.setImageResource(R.drawable.gpupy2);
      paramNumImageView2.setImageResource(R.drawable.gpupy2);
      paramNumImageView3.setImageResource(R.drawable.gpupy2);
      paramNumImageView4.setImageResource(R.drawable.gpupy2);
      paramNumImageView5.setImageResource(R.drawable.gpupy2);
      break;
    case 235:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprby2);
        paramNumImageView2.setImageResource(R.drawable.gprby2);
        paramNumImageView3.setImageResource(R.drawable.gprby2);
        paramNumImageView4.setImageResource(R.drawable.gprby2);
        paramNumImageView5.setImageResource(R.drawable.gprby2);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1y2);
        paramNumImageView2.setImageResource(R.drawable.gpr1y2);
        paramNumImageView3.setImageResource(R.drawable.gpr1y2);
        paramNumImageView4.setImageResource(R.drawable.gpr1y2);
        paramNumImageView5.setImageResource(R.drawable.gpr1y2);
      }
      break;
    case 234:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplby2);
        paramNumImageView2.setImageResource(R.drawable.gplby2);
        paramNumImageView3.setImageResource(R.drawable.gplby2);
        paramNumImageView4.setImageResource(R.drawable.gplby2);
        paramNumImageView5.setImageResource(R.drawable.gplby2);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1y2);
        paramNumImageView2.setImageResource(R.drawable.gpl1y2);
        paramNumImageView3.setImageResource(R.drawable.gpl1y2);
        paramNumImageView4.setImageResource(R.drawable.gpl1y2);
        paramNumImageView5.setImageResource(R.drawable.gpl1y2);
      }
      break;
    case 233:
      paramNumImageView1.setImageResource(R.drawable.gpyy2);
      paramNumImageView2.setImageResource(R.drawable.gpyy2);
      paramNumImageView3.setImageResource(R.drawable.gpyy2);
      paramNumImageView4.setImageResource(R.drawable.gpyy2);
      paramNumImageView5.setImageResource(R.drawable.gpyy2);
      break;
    case 232:
      paramNumImageView1.setImageResource(R.drawable.gpxy2);
      paramNumImageView2.setImageResource(R.drawable.gpxy2);
      paramNumImageView3.setImageResource(R.drawable.gpxy2);
      paramNumImageView4.setImageResource(R.drawable.gpxy2);
      paramNumImageView5.setImageResource(R.drawable.gpxy2);
      break;
    case 231:
      paramNumImageView1.setImageResource(R.drawable.gpby2);
      paramNumImageView2.setImageResource(R.drawable.gpby2);
      paramNumImageView3.setImageResource(R.drawable.gpby2);
      paramNumImageView4.setImageResource(R.drawable.gpby2);
      paramNumImageView5.setImageResource(R.drawable.gpby2);
      break;
    case 230:
      paramNumImageView1.setImageResource(R.drawable.gpay2);
      paramNumImageView2.setImageResource(R.drawable.gpay2);
      paramNumImageView3.setImageResource(R.drawable.gpay2);
      paramNumImageView4.setImageResource(R.drawable.gpay2);
      paramNumImageView5.setImageResource(R.drawable.gpay2);
      break;
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
      paramNumImageView1.setImageResource(R.drawable.newkey);
      paramNumImageView2.setImageResource(R.drawable.newkey);
      paramNumImageView3.setImageResource(R.drawable.newkey);
      paramNumImageView4.setImageResource(R.drawable.newkey);
      paramNumImageView5.setImageResource(R.drawable.newkey);
      break;
    case 219:
      paramNumImageView1.setImageResource(R.drawable.gpm4y1);
      paramNumImageView2.setImageResource(R.drawable.gpm4y1);
      paramNumImageView3.setImageResource(R.drawable.gpm4y1);
      paramNumImageView4.setImageResource(R.drawable.gpm4y1);
      paramNumImageView5.setImageResource(R.drawable.gpm4y1);
      break;
    case 218:
      paramNumImageView1.setImageResource(R.drawable.gpm3y1);
      paramNumImageView2.setImageResource(R.drawable.gpm3y1);
      paramNumImageView3.setImageResource(R.drawable.gpm3y1);
      paramNumImageView4.setImageResource(R.drawable.gpm3y1);
      paramNumImageView5.setImageResource(R.drawable.gpm3y1);
      break;
    case 217:
      paramNumImageView1.setImageResource(R.drawable.gpm2y1);
      paramNumImageView2.setImageResource(R.drawable.gpm2y1);
      paramNumImageView3.setImageResource(R.drawable.gpm2y1);
      paramNumImageView4.setImageResource(R.drawable.gpm2y1);
      paramNumImageView5.setImageResource(R.drawable.gpm2y1);
      break;
    case 216:
      paramNumImageView1.setImageResource(R.drawable.gpm1y1);
      paramNumImageView2.setImageResource(R.drawable.gpm1y1);
      paramNumImageView3.setImageResource(R.drawable.gpm1y1);
      paramNumImageView4.setImageResource(R.drawable.gpm1y1);
      paramNumImageView5.setImageResource(R.drawable.gpm1y1);
      break;
    case 215:
      paramNumImageView1.setImageResource(R.drawable.gpbpr);
      paramNumImageView2.setImageResource(R.drawable.gpbpr);
      paramNumImageView3.setImageResource(R.drawable.gpbpr);
      paramNumImageView4.setImageResource(R.drawable.gpbpr);
      paramNumImageView5.setImageResource(R.drawable.gpbpr);
      break;
    case 214:
      paramNumImageView1.setImageResource(R.drawable.gpbpl);
      paramNumImageView2.setImageResource(R.drawable.gpbpl);
      paramNumImageView3.setImageResource(R.drawable.gpbpl);
      paramNumImageView4.setImageResource(R.drawable.gpbpl);
      paramNumImageView5.setImageResource(R.drawable.gpbpl);
      break;
    case 213:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprty1);
        paramNumImageView2.setImageResource(R.drawable.gprty1);
        paramNumImageView3.setImageResource(R.drawable.gprty1);
        paramNumImageView4.setImageResource(R.drawable.gprty1);
        paramNumImageView5.setImageResource(R.drawable.gprty1);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2y1);
        paramNumImageView2.setImageResource(R.drawable.gpr2y1);
        paramNumImageView3.setImageResource(R.drawable.gpr2y1);
        paramNumImageView4.setImageResource(R.drawable.gpr2y1);
        paramNumImageView5.setImageResource(R.drawable.gpr2y1);
      }
      break;
    case 212:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplty1);
        paramNumImageView2.setImageResource(R.drawable.gplty1);
        paramNumImageView3.setImageResource(R.drawable.gplty1);
        paramNumImageView4.setImageResource(R.drawable.gplty1);
        paramNumImageView5.setImageResource(R.drawable.gplty1);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2y1);
        paramNumImageView2.setImageResource(R.drawable.gpl2y1);
        paramNumImageView3.setImageResource(R.drawable.gpl2y1);
        paramNumImageView4.setImageResource(R.drawable.gpl2y1);
        paramNumImageView5.setImageResource(R.drawable.gpl2y1);
      }
      break;
    case 211:
      paramNumImageView1.setImageResource(R.drawable.gpr3y1);
      paramNumImageView2.setImageResource(R.drawable.gpr3y1);
      paramNumImageView3.setImageResource(R.drawable.gpr3y1);
      paramNumImageView4.setImageResource(R.drawable.gpr3y1);
      paramNumImageView5.setImageResource(R.drawable.gpr3y1);
      break;
    case 210:
      paramNumImageView1.setImageResource(R.drawable.gpl3y1);
      paramNumImageView2.setImageResource(R.drawable.gpl3y1);
      paramNumImageView3.setImageResource(R.drawable.gpl3y1);
      paramNumImageView4.setImageResource(R.drawable.gpl3y1);
      paramNumImageView5.setImageResource(R.drawable.gpl3y1);
      break;
    case 209:
      paramNumImageView1.setImageResource(R.drawable.gpselect);
      paramNumImageView2.setImageResource(R.drawable.gpselect);
      paramNumImageView3.setImageResource(R.drawable.gpselect);
      paramNumImageView4.setImageResource(R.drawable.gpselect);
      paramNumImageView5.setImageResource(R.drawable.gpselect);
      break;
    case 208:
      paramNumImageView1.setImageResource(R.drawable.gpstart);
      paramNumImageView2.setImageResource(R.drawable.gpstart);
      paramNumImageView3.setImageResource(R.drawable.gpstart);
      paramNumImageView4.setImageResource(R.drawable.gpstart);
      paramNumImageView5.setImageResource(R.drawable.gpstart);
      break;
    case 207:
      paramNumImageView1.setImageResource(R.drawable.gprighty1);
      paramNumImageView2.setImageResource(R.drawable.gprighty1);
      paramNumImageView3.setImageResource(R.drawable.gprighty1);
      paramNumImageView4.setImageResource(R.drawable.gprighty1);
      paramNumImageView5.setImageResource(R.drawable.gprighty1);
      break;
    case 206:
      paramNumImageView1.setImageResource(R.drawable.gplefty1);
      paramNumImageView2.setImageResource(R.drawable.gplefty1);
      paramNumImageView3.setImageResource(R.drawable.gplefty1);
      paramNumImageView4.setImageResource(R.drawable.gplefty1);
      paramNumImageView5.setImageResource(R.drawable.gplefty1);
      break;
    case 205:
      paramNumImageView1.setImageResource(R.drawable.gpdowny1);
      paramNumImageView2.setImageResource(R.drawable.gpdowny1);
      paramNumImageView3.setImageResource(R.drawable.gpdowny1);
      paramNumImageView4.setImageResource(R.drawable.gpdowny1);
      paramNumImageView5.setImageResource(R.drawable.gpdowny1);
      break;
    case 204:
      paramNumImageView1.setImageResource(R.drawable.gpupy1);
      paramNumImageView2.setImageResource(R.drawable.gpupy1);
      paramNumImageView3.setImageResource(R.drawable.gpupy1);
      paramNumImageView4.setImageResource(R.drawable.gpupy1);
      paramNumImageView5.setImageResource(R.drawable.gpupy1);
      break;
    case 203:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprby1);
        paramNumImageView2.setImageResource(R.drawable.gprby1);
        paramNumImageView3.setImageResource(R.drawable.gprby1);
        paramNumImageView4.setImageResource(R.drawable.gprby1);
        paramNumImageView5.setImageResource(R.drawable.gprby1);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1y1);
        paramNumImageView2.setImageResource(R.drawable.gpr1y1);
        paramNumImageView3.setImageResource(R.drawable.gpr1y1);
        paramNumImageView4.setImageResource(R.drawable.gpr1y1);
        paramNumImageView5.setImageResource(R.drawable.gpr1y1);
      }
      break;
    case 202:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplby1);
        paramNumImageView2.setImageResource(R.drawable.gplby1);
        paramNumImageView3.setImageResource(R.drawable.gplby1);
        paramNumImageView4.setImageResource(R.drawable.gplby1);
        paramNumImageView5.setImageResource(R.drawable.gplby1);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1y1);
        paramNumImageView2.setImageResource(R.drawable.gpl1y1);
        paramNumImageView3.setImageResource(R.drawable.gpl1y1);
        paramNumImageView4.setImageResource(R.drawable.gpl1y1);
        paramNumImageView5.setImageResource(R.drawable.gpl1y1);
      }
      break;
    case 201:
      paramNumImageView1.setImageResource(R.drawable.gpyy1);
      paramNumImageView2.setImageResource(R.drawable.gpyy1);
      paramNumImageView3.setImageResource(R.drawable.gpyy1);
      paramNumImageView4.setImageResource(R.drawable.gpyy1);
      paramNumImageView5.setImageResource(R.drawable.gpyy1);
      break;
    case 200:
      paramNumImageView1.setImageResource(R.drawable.gpxy1);
      paramNumImageView2.setImageResource(R.drawable.gpxy1);
      paramNumImageView3.setImageResource(R.drawable.gpxy1);
      paramNumImageView4.setImageResource(R.drawable.gpxy1);
      paramNumImageView5.setImageResource(R.drawable.gpxy1);
      break;
    case 199:
      paramNumImageView1.setImageResource(R.drawable.gpby1);
      paramNumImageView2.setImageResource(R.drawable.gpby1);
      paramNumImageView3.setImageResource(R.drawable.gpby1);
      paramNumImageView4.setImageResource(R.drawable.gpby1);
      paramNumImageView5.setImageResource(R.drawable.gpby1);
      break;
    case 198:
      paramNumImageView1.setImageResource(R.drawable.gpay1);
      paramNumImageView2.setImageResource(R.drawable.gpay1);
      paramNumImageView3.setImageResource(R.drawable.gpay1);
      paramNumImageView4.setImageResource(R.drawable.gpay1);
      paramNumImageView5.setImageResource(R.drawable.gpay1);
      break;
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
      paramNumImageView1.setImageResource(R.drawable.newkey);
      paramNumImageView2.setImageResource(R.drawable.newkey);
      paramNumImageView3.setImageResource(R.drawable.newkey);
      paramNumImageView4.setImageResource(R.drawable.newkey);
      paramNumImageView5.setImageResource(R.drawable.newkey);
      break;
    case 187:
      paramNumImageView1.setImageResource(R.drawable.gpm42);
      paramNumImageView2.setImageResource(R.drawable.gpm42);
      paramNumImageView3.setImageResource(R.drawable.gpm42);
      paramNumImageView4.setImageResource(R.drawable.gpm42);
      paramNumImageView5.setImageResource(R.drawable.gpm42);
      break;
    case 186:
      paramNumImageView1.setImageResource(R.drawable.gpm32);
      paramNumImageView2.setImageResource(R.drawable.gpm32);
      paramNumImageView3.setImageResource(R.drawable.gpm32);
      paramNumImageView4.setImageResource(R.drawable.gpm32);
      paramNumImageView5.setImageResource(R.drawable.gpm32);
      break;
    case 185:
      paramNumImageView1.setImageResource(R.drawable.gpm22);
      paramNumImageView2.setImageResource(R.drawable.gpm22);
      paramNumImageView3.setImageResource(R.drawable.gpm22);
      paramNumImageView4.setImageResource(R.drawable.gpm22);
      paramNumImageView5.setImageResource(R.drawable.gpm22);
      break;
    case 184:
      paramNumImageView1.setImageResource(R.drawable.gpm12);
      paramNumImageView2.setImageResource(R.drawable.gpm12);
      paramNumImageView3.setImageResource(R.drawable.gpm12);
      paramNumImageView4.setImageResource(R.drawable.gpm12);
      paramNumImageView5.setImageResource(R.drawable.gpm12);
      break;
    case 183:
      paramNumImageView1.setImageResource(R.drawable.gpbpr);
      paramNumImageView2.setImageResource(R.drawable.gpbpr);
      paramNumImageView3.setImageResource(R.drawable.gpbpr);
      paramNumImageView4.setImageResource(R.drawable.gpbpr);
      paramNumImageView5.setImageResource(R.drawable.gpbpr);
      break;
    case 182:
      paramNumImageView1.setImageResource(R.drawable.gpbpl);
      paramNumImageView2.setImageResource(R.drawable.gpbpl);
      paramNumImageView3.setImageResource(R.drawable.gpbpl);
      paramNumImageView4.setImageResource(R.drawable.gpbpl);
      paramNumImageView5.setImageResource(R.drawable.gpbpl);
      break;
    case 181:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprt2);
        paramNumImageView2.setImageResource(R.drawable.gprt2);
        paramNumImageView3.setImageResource(R.drawable.gprt2);
        paramNumImageView4.setImageResource(R.drawable.gprt2);
        paramNumImageView5.setImageResource(R.drawable.gprt2);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr22);
        paramNumImageView2.setImageResource(R.drawable.gpr22);
        paramNumImageView3.setImageResource(R.drawable.gpr22);
        paramNumImageView4.setImageResource(R.drawable.gpr22);
        paramNumImageView5.setImageResource(R.drawable.gpr22);
      }
      break;
    case 180:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplt2);
        paramNumImageView2.setImageResource(R.drawable.gplt2);
        paramNumImageView3.setImageResource(R.drawable.gplt2);
        paramNumImageView4.setImageResource(R.drawable.gplt2);
        paramNumImageView5.setImageResource(R.drawable.gplt2);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl22);
        paramNumImageView2.setImageResource(R.drawable.gpl22);
        paramNumImageView3.setImageResource(R.drawable.gpl22);
        paramNumImageView4.setImageResource(R.drawable.gpl22);
        paramNumImageView5.setImageResource(R.drawable.gpl22);
      }
      break;
    case 179:
      paramNumImageView1.setImageResource(R.drawable.gpr32);
      paramNumImageView2.setImageResource(R.drawable.gpr32);
      paramNumImageView3.setImageResource(R.drawable.gpr32);
      paramNumImageView4.setImageResource(R.drawable.gpr32);
      paramNumImageView5.setImageResource(R.drawable.gpr32);
      break;
    case 178:
      paramNumImageView1.setImageResource(R.drawable.gpl32);
      paramNumImageView2.setImageResource(R.drawable.gpl32);
      paramNumImageView3.setImageResource(R.drawable.gpl32);
      paramNumImageView4.setImageResource(R.drawable.gpl32);
      paramNumImageView5.setImageResource(R.drawable.gpl32);
      break;
    case 177:
      paramNumImageView1.setImageResource(R.drawable.gpselect);
      paramNumImageView2.setImageResource(R.drawable.gpselect);
      paramNumImageView3.setImageResource(R.drawable.gpselect);
      paramNumImageView4.setImageResource(R.drawable.gpselect);
      paramNumImageView5.setImageResource(R.drawable.gpselect);
      break;
    case 176:
      paramNumImageView1.setImageResource(R.drawable.gpstart);
      paramNumImageView2.setImageResource(R.drawable.gpstart);
      paramNumImageView3.setImageResource(R.drawable.gpstart);
      paramNumImageView4.setImageResource(R.drawable.gpstart);
      paramNumImageView5.setImageResource(R.drawable.gpstart);
      break;
    case 175:
      paramNumImageView1.setImageResource(R.drawable.gpright2);
      paramNumImageView2.setImageResource(R.drawable.gpright2);
      paramNumImageView3.setImageResource(R.drawable.gpright2);
      paramNumImageView4.setImageResource(R.drawable.gpright2);
      paramNumImageView5.setImageResource(R.drawable.gpright2);
      break;
    case 174:
      paramNumImageView1.setImageResource(R.drawable.gpleft2);
      paramNumImageView2.setImageResource(R.drawable.gpleft2);
      paramNumImageView3.setImageResource(R.drawable.gpleft2);
      paramNumImageView4.setImageResource(R.drawable.gpleft2);
      paramNumImageView5.setImageResource(R.drawable.gpleft2);
      break;
    case 173:
      paramNumImageView1.setImageResource(R.drawable.gpdown2);
      paramNumImageView2.setImageResource(R.drawable.gpdown2);
      paramNumImageView3.setImageResource(R.drawable.gpdown2);
      paramNumImageView4.setImageResource(R.drawable.gpdown2);
      paramNumImageView5.setImageResource(R.drawable.gpdown2);
      break;
    case 172:
      paramNumImageView1.setImageResource(R.drawable.gpup2);
      paramNumImageView2.setImageResource(R.drawable.gpup2);
      paramNumImageView3.setImageResource(R.drawable.gpup2);
      paramNumImageView4.setImageResource(R.drawable.gpup2);
      paramNumImageView5.setImageResource(R.drawable.gpup2);
      break;
    case 171:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprb2);
        paramNumImageView2.setImageResource(R.drawable.gprb2);
        paramNumImageView3.setImageResource(R.drawable.gprb2);
        paramNumImageView4.setImageResource(R.drawable.gprb2);
        paramNumImageView5.setImageResource(R.drawable.gprb2);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr12);
        paramNumImageView2.setImageResource(R.drawable.gpr12);
        paramNumImageView3.setImageResource(R.drawable.gpr12);
        paramNumImageView4.setImageResource(R.drawable.gpr12);
        paramNumImageView5.setImageResource(R.drawable.gpr12);
      }
      break;
    case 170:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplb2);
        paramNumImageView2.setImageResource(R.drawable.gplb2);
        paramNumImageView3.setImageResource(R.drawable.gplb2);
        paramNumImageView4.setImageResource(R.drawable.gplb2);
        paramNumImageView5.setImageResource(R.drawable.gplb2);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl12);
        paramNumImageView2.setImageResource(R.drawable.gpl12);
        paramNumImageView3.setImageResource(R.drawable.gpl12);
        paramNumImageView4.setImageResource(R.drawable.gpl12);
        paramNumImageView5.setImageResource(R.drawable.gpl12);
      }
      break;
    case 169:
      paramNumImageView1.setImageResource(R.drawable.gpy2);
      paramNumImageView2.setImageResource(R.drawable.gpy2);
      paramNumImageView3.setImageResource(R.drawable.gpy2);
      paramNumImageView4.setImageResource(R.drawable.gpy2);
      paramNumImageView5.setImageResource(R.drawable.gpy2);
      break;
    case 168:
      paramNumImageView1.setImageResource(R.drawable.gpx2);
      paramNumImageView2.setImageResource(R.drawable.gpx2);
      paramNumImageView3.setImageResource(R.drawable.gpx2);
      paramNumImageView4.setImageResource(R.drawable.gpx2);
      paramNumImageView5.setImageResource(R.drawable.gpx2);
      break;
    case 167:
      paramNumImageView1.setImageResource(R.drawable.gpbtn2);
      paramNumImageView2.setImageResource(R.drawable.gpbtn2);
      paramNumImageView3.setImageResource(R.drawable.gpbtn2);
      paramNumImageView4.setImageResource(R.drawable.gpbtn2);
      paramNumImageView5.setImageResource(R.drawable.gpbtn2);
      break;
    case 166:
      paramNumImageView1.setImageResource(R.drawable.gpa2);
      paramNumImageView2.setImageResource(R.drawable.gpa2);
      paramNumImageView3.setImageResource(R.drawable.gpa2);
      paramNumImageView4.setImageResource(R.drawable.gpa2);
      paramNumImageView5.setImageResource(R.drawable.gpa2);
      break;
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
      paramNumImageView1.setImageResource(R.drawable.newkey);
      paramNumImageView2.setImageResource(R.drawable.newkey);
      paramNumImageView3.setImageResource(R.drawable.newkey);
      paramNumImageView4.setImageResource(R.drawable.newkey);
      paramNumImageView5.setImageResource(R.drawable.newkey);
      break;
    case 155:
      paramNumImageView1.setImageResource(R.drawable.gpm41);
      paramNumImageView2.setImageResource(R.drawable.gpm41);
      paramNumImageView3.setImageResource(R.drawable.gpm41);
      paramNumImageView4.setImageResource(R.drawable.gpm41);
      paramNumImageView5.setImageResource(R.drawable.gpm41);
      break;
    case 154:
      paramNumImageView1.setImageResource(R.drawable.gpm31);
      paramNumImageView2.setImageResource(R.drawable.gpm31);
      paramNumImageView3.setImageResource(R.drawable.gpm31);
      paramNumImageView4.setImageResource(R.drawable.gpm31);
      paramNumImageView5.setImageResource(R.drawable.gpm31);
      break;
    case 153:
      paramNumImageView1.setImageResource(R.drawable.gpm21);
      paramNumImageView2.setImageResource(R.drawable.gpm21);
      paramNumImageView3.setImageResource(R.drawable.gpm21);
      paramNumImageView4.setImageResource(R.drawable.gpm21);
      paramNumImageView5.setImageResource(R.drawable.gpm21);
      break;
    case 152:
      paramNumImageView1.setImageResource(R.drawable.gpm11);
      paramNumImageView2.setImageResource(R.drawable.gpm11);
      paramNumImageView3.setImageResource(R.drawable.gpm11);
      paramNumImageView4.setImageResource(R.drawable.gpm11);
      paramNumImageView5.setImageResource(R.drawable.gpm11);
      break;
    case 151:
      paramNumImageView1.setImageResource(R.drawable.gpbpr);
      paramNumImageView2.setImageResource(R.drawable.gpbpr);
      paramNumImageView3.setImageResource(R.drawable.gpbpr);
      paramNumImageView4.setImageResource(R.drawable.gpbpr);
      paramNumImageView5.setImageResource(R.drawable.gpbpr);
      break;
    case 150:
      paramNumImageView1.setImageResource(R.drawable.gpbpl);
      paramNumImageView2.setImageResource(R.drawable.gpbpl);
      paramNumImageView3.setImageResource(R.drawable.gpbpl);
      paramNumImageView4.setImageResource(R.drawable.gpbpl);
      paramNumImageView5.setImageResource(R.drawable.gpbpl);
      break;
    case 149:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprt1);
        paramNumImageView2.setImageResource(R.drawable.gprt1);
        paramNumImageView3.setImageResource(R.drawable.gprt1);
        paramNumImageView4.setImageResource(R.drawable.gprt1);
        paramNumImageView5.setImageResource(R.drawable.gprt1);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr21);
        paramNumImageView2.setImageResource(R.drawable.gpr21);
        paramNumImageView3.setImageResource(R.drawable.gpr21);
        paramNumImageView4.setImageResource(R.drawable.gpr21);
        paramNumImageView5.setImageResource(R.drawable.gpr21);
      }
      break;
    case 148:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplt1);
        paramNumImageView2.setImageResource(R.drawable.gplt1);
        paramNumImageView3.setImageResource(R.drawable.gplt1);
        paramNumImageView4.setImageResource(R.drawable.gplt1);
        paramNumImageView5.setImageResource(R.drawable.gplt1);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl21);
        paramNumImageView2.setImageResource(R.drawable.gpl21);
        paramNumImageView3.setImageResource(R.drawable.gpl21);
        paramNumImageView4.setImageResource(R.drawable.gpl21);
        paramNumImageView5.setImageResource(R.drawable.gpl21);
      }
      break;
    case 147:
      paramNumImageView1.setImageResource(R.drawable.gpr31);
      paramNumImageView2.setImageResource(R.drawable.gpr31);
      paramNumImageView3.setImageResource(R.drawable.gpr31);
      paramNumImageView4.setImageResource(R.drawable.gpr31);
      paramNumImageView5.setImageResource(R.drawable.gpr31);
      break;
    case 146:
      paramNumImageView1.setImageResource(R.drawable.gpl31);
      paramNumImageView2.setImageResource(R.drawable.gpl31);
      paramNumImageView3.setImageResource(R.drawable.gpl31);
      paramNumImageView4.setImageResource(R.drawable.gpl31);
      paramNumImageView5.setImageResource(R.drawable.gpl31);
      break;
    case 145:
      paramNumImageView1.setImageResource(R.drawable.gpselect);
      paramNumImageView2.setImageResource(R.drawable.gpselect);
      paramNumImageView3.setImageResource(R.drawable.gpselect);
      paramNumImageView4.setImageResource(R.drawable.gpselect);
      paramNumImageView5.setImageResource(R.drawable.gpselect);
      break;
    case 144:
      paramNumImageView1.setImageResource(R.drawable.gpstart);
      paramNumImageView2.setImageResource(R.drawable.gpstart);
      paramNumImageView3.setImageResource(R.drawable.gpstart);
      paramNumImageView4.setImageResource(R.drawable.gpstart);
      paramNumImageView5.setImageResource(R.drawable.gpstart);
      break;
    case 143:
      paramNumImageView1.setImageResource(R.drawable.gpright1);
      paramNumImageView2.setImageResource(R.drawable.gpright1);
      paramNumImageView3.setImageResource(R.drawable.gpright1);
      paramNumImageView4.setImageResource(R.drawable.gpright1);
      paramNumImageView5.setImageResource(R.drawable.gpright1);
      break;
    case 142:
      paramNumImageView1.setImageResource(R.drawable.gpleft1);
      paramNumImageView2.setImageResource(R.drawable.gpleft1);
      paramNumImageView3.setImageResource(R.drawable.gpleft1);
      paramNumImageView4.setImageResource(R.drawable.gpleft1);
      paramNumImageView5.setImageResource(R.drawable.gpleft1);
      break;
    case 141:
      paramNumImageView1.setImageResource(R.drawable.gpdown1);
      paramNumImageView2.setImageResource(R.drawable.gpdown1);
      paramNumImageView3.setImageResource(R.drawable.gpdown1);
      paramNumImageView4.setImageResource(R.drawable.gpdown1);
      paramNumImageView5.setImageResource(R.drawable.gpdown1);
      break;
    case 140:
      paramNumImageView1.setImageResource(R.drawable.gpup1);
      paramNumImageView2.setImageResource(R.drawable.gpup1);
      paramNumImageView3.setImageResource(R.drawable.gpup1);
      paramNumImageView4.setImageResource(R.drawable.gpup1);
      paramNumImageView5.setImageResource(R.drawable.gpup1);
      break;
    case 139:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprb1);
        paramNumImageView2.setImageResource(R.drawable.gprb1);
        paramNumImageView3.setImageResource(R.drawable.gprb1);
        paramNumImageView4.setImageResource(R.drawable.gprb1);
        paramNumImageView5.setImageResource(R.drawable.gprb1);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr11);
        paramNumImageView2.setImageResource(R.drawable.gpr11);
        paramNumImageView3.setImageResource(R.drawable.gpr11);
        paramNumImageView4.setImageResource(R.drawable.gpr11);
        paramNumImageView5.setImageResource(R.drawable.gpr11);
      }
      break;
    case 138:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplb1);
        paramNumImageView2.setImageResource(R.drawable.gplb1);
        paramNumImageView3.setImageResource(R.drawable.gplb1);
        paramNumImageView4.setImageResource(R.drawable.gplb1);
        paramNumImageView5.setImageResource(R.drawable.gplb1);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl11);
        paramNumImageView2.setImageResource(R.drawable.gpl11);
        paramNumImageView3.setImageResource(R.drawable.gpl11);
        paramNumImageView4.setImageResource(R.drawable.gpl11);
        paramNumImageView5.setImageResource(R.drawable.gpl11);
      }
      break;
    case 137:
      paramNumImageView1.setImageResource(R.drawable.gpy1);
      paramNumImageView2.setImageResource(R.drawable.gpy1);
      paramNumImageView3.setImageResource(R.drawable.gpy1);
      paramNumImageView4.setImageResource(R.drawable.gpy1);
      paramNumImageView5.setImageResource(R.drawable.gpy1);
      break;
    case 136:
      paramNumImageView1.setImageResource(R.drawable.gpx1);
      paramNumImageView2.setImageResource(R.drawable.gpx1);
      paramNumImageView3.setImageResource(R.drawable.gpx1);
      paramNumImageView4.setImageResource(R.drawable.gpx1);
      paramNumImageView5.setImageResource(R.drawable.gpx1);
      break;
    case 135:
      paramNumImageView1.setImageResource(R.drawable.gpbtn1);
      paramNumImageView2.setImageResource(R.drawable.gpbtn1);
      paramNumImageView3.setImageResource(R.drawable.gpbtn1);
      paramNumImageView4.setImageResource(R.drawable.gpbtn1);
      paramNumImageView5.setImageResource(R.drawable.gpbtn1);
      break;
    case 134:
      paramNumImageView1.setImageResource(R.drawable.gpa1);
      paramNumImageView2.setImageResource(R.drawable.gpa1);
      paramNumImageView3.setImageResource(R.drawable.gpa1);
      paramNumImageView4.setImageResource(R.drawable.gpa1);
      paramNumImageView5.setImageResource(R.drawable.gpa1);
      break;
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
      paramNumImageView1.setImageResource(R.drawable.newkey);
      paramNumImageView2.setImageResource(R.drawable.newkey);
      paramNumImageView3.setImageResource(R.drawable.newkey);
      paramNumImageView4.setImageResource(R.drawable.newkey);
      paramNumImageView5.setImageResource(R.drawable.newkey);
      break;
    case 123:
      paramNumImageView1.setImageResource(R.drawable.gpm4lr);
      paramNumImageView2.setImageResource(R.drawable.gpm4lr);
      paramNumImageView3.setImageResource(R.drawable.gpm4lr);
      paramNumImageView4.setImageResource(R.drawable.gpm4lr);
      paramNumImageView5.setImageResource(R.drawable.gpm4lr);
      break;
    case 122:
      paramNumImageView1.setImageResource(R.drawable.gpm3lr);
      paramNumImageView2.setImageResource(R.drawable.gpm3lr);
      paramNumImageView3.setImageResource(R.drawable.gpm3lr);
      paramNumImageView4.setImageResource(R.drawable.gpm3lr);
      paramNumImageView5.setImageResource(R.drawable.gpm3lr);
      break;
    case 121:
      paramNumImageView1.setImageResource(R.drawable.gpm2lr);
      paramNumImageView2.setImageResource(R.drawable.gpm2lr);
      paramNumImageView3.setImageResource(R.drawable.gpm2lr);
      paramNumImageView4.setImageResource(R.drawable.gpm2lr);
      paramNumImageView5.setImageResource(R.drawable.gpm2lr);
      break;
    case 120:
      paramNumImageView1.setImageResource(R.drawable.gpm1lr);
      paramNumImageView2.setImageResource(R.drawable.gpm1lr);
      paramNumImageView3.setImageResource(R.drawable.gpm1lr);
      paramNumImageView4.setImageResource(R.drawable.gpm1lr);
      paramNumImageView5.setImageResource(R.drawable.gpm1lr);
      break;
    case 119:
      paramNumImageView1.setImageResource(R.drawable.gpalr);
      paramNumImageView2.setImageResource(R.drawable.gpalr);
      paramNumImageView3.setImageResource(R.drawable.gpalr);
      paramNumImageView4.setImageResource(R.drawable.gpalr);
      paramNumImageView5.setImageResource(R.drawable.gpalr);
      break;
    case 118:
      paramNumImageView1.setImageResource(R.drawable.gpalr);
      paramNumImageView2.setImageResource(R.drawable.gpalr);
      paramNumImageView3.setImageResource(R.drawable.gpalr);
      paramNumImageView4.setImageResource(R.drawable.gpalr);
      paramNumImageView5.setImageResource(R.drawable.gpalr);
      break;
    case 117:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprtlr);
        paramNumImageView2.setImageResource(R.drawable.gprtlr);
        paramNumImageView3.setImageResource(R.drawable.gprtlr);
        paramNumImageView4.setImageResource(R.drawable.gprtlr);
        paramNumImageView5.setImageResource(R.drawable.gprtlr);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2lr);
        paramNumImageView2.setImageResource(R.drawable.gpr2lr);
        paramNumImageView3.setImageResource(R.drawable.gpr2lr);
        paramNumImageView4.setImageResource(R.drawable.gpr2lr);
        paramNumImageView5.setImageResource(R.drawable.gpr2lr);
      }
      break;
    case 116:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpltlr);
        paramNumImageView2.setImageResource(R.drawable.gpltlr);
        paramNumImageView3.setImageResource(R.drawable.gpltlr);
        paramNumImageView4.setImageResource(R.drawable.gpltlr);
        paramNumImageView5.setImageResource(R.drawable.gpltlr);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2lr);
        paramNumImageView2.setImageResource(R.drawable.gpl2lr);
        paramNumImageView3.setImageResource(R.drawable.gpl2lr);
        paramNumImageView4.setImageResource(R.drawable.gpl2lr);
        paramNumImageView5.setImageResource(R.drawable.gpl2lr);
      }
      break;
    case 115:
      paramNumImageView1.setImageResource(R.drawable.gpr3lr);
      paramNumImageView2.setImageResource(R.drawable.gpr3lr);
      paramNumImageView3.setImageResource(R.drawable.gpr3lr);
      paramNumImageView4.setImageResource(R.drawable.gpr3lr);
      paramNumImageView5.setImageResource(R.drawable.gpr3lr);
      break;
    case 114:
      paramNumImageView1.setImageResource(R.drawable.gpl3lr);
      paramNumImageView2.setImageResource(R.drawable.gpl3lr);
      paramNumImageView3.setImageResource(R.drawable.gpl3lr);
      paramNumImageView4.setImageResource(R.drawable.gpl3lr);
      paramNumImageView5.setImageResource(R.drawable.gpl3lr);
      break;
    case 113:
      paramNumImageView1.setImageResource(R.drawable.gpalr);
      paramNumImageView2.setImageResource(R.drawable.gpalr);
      paramNumImageView3.setImageResource(R.drawable.gpalr);
      paramNumImageView4.setImageResource(R.drawable.gpalr);
      paramNumImageView5.setImageResource(R.drawable.gpalr);
      break;
    case 112:
      paramNumImageView1.setImageResource(R.drawable.gpalr);
      paramNumImageView2.setImageResource(R.drawable.gpalr);
      paramNumImageView3.setImageResource(R.drawable.gpalr);
      paramNumImageView4.setImageResource(R.drawable.gpalr);
      paramNumImageView5.setImageResource(R.drawable.gpalr);
      break;
    case 111:
      paramNumImageView1.setImageResource(R.drawable.gpalr);
      paramNumImageView2.setImageResource(R.drawable.gpalr);
      paramNumImageView3.setImageResource(R.drawable.gpalr);
      paramNumImageView4.setImageResource(R.drawable.gpalr);
      paramNumImageView5.setImageResource(R.drawable.gpalr);
      break;
    case 110:
      paramNumImageView1.setImageResource(R.drawable.gpalr);
      paramNumImageView2.setImageResource(R.drawable.gpalr);
      paramNumImageView3.setImageResource(R.drawable.gpalr);
      paramNumImageView4.setImageResource(R.drawable.gpalr);
      paramNumImageView5.setImageResource(R.drawable.gpalr);
      break;
    case 109:
      paramNumImageView1.setImageResource(R.drawable.gpalr);
      paramNumImageView2.setImageResource(R.drawable.gpalr);
      paramNumImageView3.setImageResource(R.drawable.gpalr);
      paramNumImageView4.setImageResource(R.drawable.gpalr);
      paramNumImageView5.setImageResource(R.drawable.gpalr);
      break;
    case 108:
      paramNumImageView1.setImageResource(R.drawable.gpalr);
      paramNumImageView2.setImageResource(R.drawable.gpalr);
      paramNumImageView3.setImageResource(R.drawable.gpalr);
      paramNumImageView4.setImageResource(R.drawable.gpalr);
      paramNumImageView5.setImageResource(R.drawable.gpalr);
      break;
    case 107:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprblr);
        paramNumImageView2.setImageResource(R.drawable.gprblr);
        paramNumImageView3.setImageResource(R.drawable.gprblr);
        paramNumImageView4.setImageResource(R.drawable.gprblr);
        paramNumImageView5.setImageResource(R.drawable.gprblr);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1lr);
        paramNumImageView2.setImageResource(R.drawable.gpr1lr);
        paramNumImageView3.setImageResource(R.drawable.gpr1lr);
        paramNumImageView4.setImageResource(R.drawable.gpr1lr);
        paramNumImageView5.setImageResource(R.drawable.gpr1lr);
      }
      break;
    case 106:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplblr);
        paramNumImageView2.setImageResource(R.drawable.gplblr);
        paramNumImageView3.setImageResource(R.drawable.gplblr);
        paramNumImageView4.setImageResource(R.drawable.gplblr);
        paramNumImageView5.setImageResource(R.drawable.gplblr);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1lr);
        paramNumImageView2.setImageResource(R.drawable.gpl1lr);
        paramNumImageView3.setImageResource(R.drawable.gpl1lr);
        paramNumImageView4.setImageResource(R.drawable.gpl1lr);
        paramNumImageView5.setImageResource(R.drawable.gpl1lr);
      }
      break;
    case 105:
      paramNumImageView1.setImageResource(R.drawable.gpylr);
      paramNumImageView2.setImageResource(R.drawable.gpylr);
      paramNumImageView3.setImageResource(R.drawable.gpylr);
      paramNumImageView4.setImageResource(R.drawable.gpylr);
      paramNumImageView5.setImageResource(R.drawable.gpylr);
      break;
    case 104:
      paramNumImageView1.setImageResource(R.drawable.gpxlr);
      paramNumImageView2.setImageResource(R.drawable.gpxlr);
      paramNumImageView3.setImageResource(R.drawable.gpxlr);
      paramNumImageView4.setImageResource(R.drawable.gpxlr);
      paramNumImageView5.setImageResource(R.drawable.gpxlr);
      break;
    case 103:
      paramNumImageView1.setImageResource(R.drawable.gpblr);
      paramNumImageView2.setImageResource(R.drawable.gpblr);
      paramNumImageView3.setImageResource(R.drawable.gpblr);
      paramNumImageView4.setImageResource(R.drawable.gpblr);
      paramNumImageView5.setImageResource(R.drawable.gpblr);
      break;
    case 102:
      paramNumImageView1.setImageResource(R.drawable.gpalr);
      paramNumImageView2.setImageResource(R.drawable.gpalr);
      paramNumImageView3.setImageResource(R.drawable.gpalr);
      paramNumImageView4.setImageResource(R.drawable.gpalr);
      paramNumImageView5.setImageResource(R.drawable.gpalr);
      break;
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
      paramNumImageView1.setImageResource(R.drawable.newkey);
      paramNumImageView2.setImageResource(R.drawable.newkey);
      paramNumImageView3.setImageResource(R.drawable.newkey);
      paramNumImageView4.setImageResource(R.drawable.newkey);
      paramNumImageView5.setImageResource(R.drawable.newkey);
      break;
    case 91:
      paramNumImageView1.setImageResource(R.drawable.gpm4bom);
      paramNumImageView2.setImageResource(R.drawable.gpm4bom);
      paramNumImageView3.setImageResource(R.drawable.gpm4bom);
      paramNumImageView4.setImageResource(R.drawable.gpm4bom);
      paramNumImageView5.setImageResource(R.drawable.gpm4bom);
      break;
    case 90:
      paramNumImageView1.setImageResource(R.drawable.gpm3bom);
      paramNumImageView2.setImageResource(R.drawable.gpm3bom);
      paramNumImageView3.setImageResource(R.drawable.gpm3bom);
      paramNumImageView4.setImageResource(R.drawable.gpm3bom);
      paramNumImageView5.setImageResource(R.drawable.gpm3bom);
      break;
    case 89:
      paramNumImageView1.setImageResource(R.drawable.gpm2bom);
      paramNumImageView2.setImageResource(R.drawable.gpm2bom);
      paramNumImageView3.setImageResource(R.drawable.gpm2bom);
      paramNumImageView4.setImageResource(R.drawable.gpm2bom);
      paramNumImageView5.setImageResource(R.drawable.gpm2bom);
      break;
    case 88:
      paramNumImageView1.setImageResource(R.drawable.gpm1bom);
      paramNumImageView2.setImageResource(R.drawable.gpm1bom);
      paramNumImageView3.setImageResource(R.drawable.gpm1bom);
      paramNumImageView4.setImageResource(R.drawable.gpm1bom);
      paramNumImageView5.setImageResource(R.drawable.gpm1bom);
      break;
    case 87:
      paramNumImageView1.setImageResource(R.drawable.gparight);
      paramNumImageView2.setImageResource(R.drawable.gparight);
      paramNumImageView3.setImageResource(R.drawable.gparight);
      paramNumImageView4.setImageResource(R.drawable.gparight);
      paramNumImageView5.setImageResource(R.drawable.gparight);
      break;
    case 86:
      paramNumImageView1.setImageResource(R.drawable.gparight);
      paramNumImageView2.setImageResource(R.drawable.gparight);
      paramNumImageView3.setImageResource(R.drawable.gparight);
      paramNumImageView4.setImageResource(R.drawable.gparight);
      paramNumImageView5.setImageResource(R.drawable.gparight);
      break;
    case 85:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprtbom);
        paramNumImageView2.setImageResource(R.drawable.gprtbom);
        paramNumImageView3.setImageResource(R.drawable.gprtbom);
        paramNumImageView4.setImageResource(R.drawable.gprtbom);
        paramNumImageView5.setImageResource(R.drawable.gprtbom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2bom);
        paramNumImageView2.setImageResource(R.drawable.gpr2bom);
        paramNumImageView3.setImageResource(R.drawable.gpr2bom);
        paramNumImageView4.setImageResource(R.drawable.gpr2bom);
        paramNumImageView5.setImageResource(R.drawable.gpr2bom);
      }
      break;
    case 84:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpltbom);
        paramNumImageView2.setImageResource(R.drawable.gpltbom);
        paramNumImageView3.setImageResource(R.drawable.gpltbom);
        paramNumImageView4.setImageResource(R.drawable.gpltbom);
        paramNumImageView5.setImageResource(R.drawable.gpltbom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2bom);
        paramNumImageView2.setImageResource(R.drawable.gpl2bom);
        paramNumImageView3.setImageResource(R.drawable.gpl2bom);
        paramNumImageView4.setImageResource(R.drawable.gpl2bom);
        paramNumImageView5.setImageResource(R.drawable.gpl2bom);
      }
      break;
    case 83:
      paramNumImageView1.setImageResource(R.drawable.gpr3bom);
      paramNumImageView2.setImageResource(R.drawable.gpr3bom);
      paramNumImageView3.setImageResource(R.drawable.gpr3bom);
      paramNumImageView4.setImageResource(R.drawable.gpr3bom);
      paramNumImageView5.setImageResource(R.drawable.gpr3bom);
      break;
    case 82:
      paramNumImageView1.setImageResource(R.drawable.gpl3bom);
      paramNumImageView2.setImageResource(R.drawable.gpl3bom);
      paramNumImageView3.setImageResource(R.drawable.gpl3bom);
      paramNumImageView4.setImageResource(R.drawable.gpl3bom);
      paramNumImageView5.setImageResource(R.drawable.gpl3bom);
      break;
    case 81:
      paramNumImageView1.setImageResource(R.drawable.gparight);
      paramNumImageView2.setImageResource(R.drawable.gparight);
      paramNumImageView3.setImageResource(R.drawable.gparight);
      paramNumImageView4.setImageResource(R.drawable.gparight);
      paramNumImageView5.setImageResource(R.drawable.gparight);
      break;
    case 80:
      paramNumImageView1.setImageResource(R.drawable.gparight);
      paramNumImageView2.setImageResource(R.drawable.gparight);
      paramNumImageView3.setImageResource(R.drawable.gparight);
      paramNumImageView4.setImageResource(R.drawable.gparight);
      paramNumImageView5.setImageResource(R.drawable.gparight);
      break;
    case 79:
      paramNumImageView1.setImageResource(R.drawable.gparight);
      paramNumImageView2.setImageResource(R.drawable.gparight);
      paramNumImageView3.setImageResource(R.drawable.gparight);
      paramNumImageView4.setImageResource(R.drawable.gparight);
      paramNumImageView5.setImageResource(R.drawable.gparight);
      break;
    case 78:
      paramNumImageView1.setImageResource(R.drawable.gparight);
      paramNumImageView2.setImageResource(R.drawable.gparight);
      paramNumImageView3.setImageResource(R.drawable.gparight);
      paramNumImageView4.setImageResource(R.drawable.gparight);
      paramNumImageView5.setImageResource(R.drawable.gparight);
      break;
    case 77:
      paramNumImageView1.setImageResource(R.drawable.gparight);
      paramNumImageView2.setImageResource(R.drawable.gparight);
      paramNumImageView3.setImageResource(R.drawable.gparight);
      paramNumImageView4.setImageResource(R.drawable.gparight);
      paramNumImageView5.setImageResource(R.drawable.gparight);
      break;
    case 76:
      paramNumImageView1.setImageResource(R.drawable.gparight);
      paramNumImageView2.setImageResource(R.drawable.gparight);
      paramNumImageView3.setImageResource(R.drawable.gparight);
      paramNumImageView4.setImageResource(R.drawable.gparight);
      paramNumImageView5.setImageResource(R.drawable.gparight);
      break;
    case 75:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprbbom);
        paramNumImageView2.setImageResource(R.drawable.gprbbom);
        paramNumImageView3.setImageResource(R.drawable.gprbbom);
        paramNumImageView4.setImageResource(R.drawable.gprbbom);
        paramNumImageView5.setImageResource(R.drawable.gprbbom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1bom);
        paramNumImageView2.setImageResource(R.drawable.gpr1bom);
        paramNumImageView3.setImageResource(R.drawable.gpr1bom);
        paramNumImageView4.setImageResource(R.drawable.gpr1bom);
        paramNumImageView5.setImageResource(R.drawable.gpr1bom);
      }
      break;
    case 74:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplbbom);
        paramNumImageView2.setImageResource(R.drawable.gplbbom);
        paramNumImageView3.setImageResource(R.drawable.gplbbom);
        paramNumImageView4.setImageResource(R.drawable.gplbbom);
        paramNumImageView5.setImageResource(R.drawable.gplbbom);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1bom);
        paramNumImageView2.setImageResource(R.drawable.gpl1bom);
        paramNumImageView3.setImageResource(R.drawable.gpl1bom);
        paramNumImageView4.setImageResource(R.drawable.gpl1bom);
        paramNumImageView5.setImageResource(R.drawable.gpl1bom);
      }
      break;
    case 73:
      paramNumImageView1.setImageResource(R.drawable.gpyright);
      paramNumImageView2.setImageResource(R.drawable.gpyright);
      paramNumImageView3.setImageResource(R.drawable.gpyright);
      paramNumImageView4.setImageResource(R.drawable.gpyright);
      paramNumImageView5.setImageResource(R.drawable.gpyright);
      break;
    case 72:
      paramNumImageView1.setImageResource(R.drawable.gpxright);
      paramNumImageView2.setImageResource(R.drawable.gpxright);
      paramNumImageView3.setImageResource(R.drawable.gpxright);
      paramNumImageView4.setImageResource(R.drawable.gpxright);
      paramNumImageView5.setImageResource(R.drawable.gpxright);
      break;
    case 71:
      paramNumImageView1.setImageResource(R.drawable.gpbright1);
      paramNumImageView2.setImageResource(R.drawable.gpbright1);
      paramNumImageView3.setImageResource(R.drawable.gpbright1);
      paramNumImageView4.setImageResource(R.drawable.gpbright1);
      paramNumImageView5.setImageResource(R.drawable.gpbright1);
      break;
    case 70:
      paramNumImageView1.setImageResource(R.drawable.gparight);
      paramNumImageView2.setImageResource(R.drawable.gparight);
      paramNumImageView3.setImageResource(R.drawable.gparight);
      paramNumImageView4.setImageResource(R.drawable.gparight);
      paramNumImageView5.setImageResource(R.drawable.gparight);
      break;
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
      paramNumImageView1.setImageResource(R.drawable.newkey);
      paramNumImageView2.setImageResource(R.drawable.newkey);
      paramNumImageView3.setImageResource(R.drawable.newkey);
      paramNumImageView4.setImageResource(R.drawable.newkey);
      paramNumImageView5.setImageResource(R.drawable.newkey);
      break;
    case 59:
      paramNumImageView1.setImageResource(R.drawable.gpm4left);
      paramNumImageView2.setImageResource(R.drawable.gpm4left);
      paramNumImageView3.setImageResource(R.drawable.gpm4left);
      paramNumImageView4.setImageResource(R.drawable.gpm4left);
      paramNumImageView5.setImageResource(R.drawable.gpm4left);
      break;
    case 58:
      paramNumImageView1.setImageResource(R.drawable.gpm3left);
      paramNumImageView2.setImageResource(R.drawable.gpm3left);
      paramNumImageView3.setImageResource(R.drawable.gpm3left);
      paramNumImageView4.setImageResource(R.drawable.gpm3left);
      paramNumImageView5.setImageResource(R.drawable.gpm3left);
      break;
    case 57:
      paramNumImageView1.setImageResource(R.drawable.gpm2left);
      paramNumImageView2.setImageResource(R.drawable.gpm2left);
      paramNumImageView3.setImageResource(R.drawable.gpm2left);
      paramNumImageView4.setImageResource(R.drawable.gpm2left);
      paramNumImageView5.setImageResource(R.drawable.gpm2left);
      break;
    case 56:
      paramNumImageView1.setImageResource(R.drawable.gpm1left);
      paramNumImageView2.setImageResource(R.drawable.gpm1left);
      paramNumImageView3.setImageResource(R.drawable.gpm1left);
      paramNumImageView4.setImageResource(R.drawable.gpm1left);
      paramNumImageView5.setImageResource(R.drawable.gpm1left);
      break;
    case 55:
      paramNumImageView1.setImageResource(R.drawable.gpaleft);
      paramNumImageView2.setImageResource(R.drawable.gpaleft);
      paramNumImageView3.setImageResource(R.drawable.gpaleft);
      paramNumImageView4.setImageResource(R.drawable.gpaleft);
      paramNumImageView5.setImageResource(R.drawable.gpaleft);
      break;
    case 54:
      paramNumImageView1.setImageResource(R.drawable.gpaleft);
      paramNumImageView2.setImageResource(R.drawable.gpaleft);
      paramNumImageView3.setImageResource(R.drawable.gpaleft);
      paramNumImageView4.setImageResource(R.drawable.gpaleft);
      paramNumImageView5.setImageResource(R.drawable.gpaleft);
      break;
    case 53:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprtleft);
        paramNumImageView2.setImageResource(R.drawable.gprtleft);
        paramNumImageView3.setImageResource(R.drawable.gprtleft);
        paramNumImageView4.setImageResource(R.drawable.gprtleft);
        paramNumImageView5.setImageResource(R.drawable.gprtleft);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2left);
        paramNumImageView2.setImageResource(R.drawable.gpr2left);
        paramNumImageView3.setImageResource(R.drawable.gpr2left);
        paramNumImageView4.setImageResource(R.drawable.gpr2left);
        paramNumImageView5.setImageResource(R.drawable.gpr2left);
      }
      break;
    case 52:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gpltleft);
        paramNumImageView2.setImageResource(R.drawable.gpltleft);
        paramNumImageView3.setImageResource(R.drawable.gpltleft);
        paramNumImageView4.setImageResource(R.drawable.gpltleft);
        paramNumImageView5.setImageResource(R.drawable.gpltleft);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2left);
        paramNumImageView2.setImageResource(R.drawable.gpl2left);
        paramNumImageView3.setImageResource(R.drawable.gpl2left);
        paramNumImageView4.setImageResource(R.drawable.gpl2left);
        paramNumImageView5.setImageResource(R.drawable.gpl2left);
      }
      break;
    case 51:
      paramNumImageView1.setImageResource(R.drawable.gpr3left);
      paramNumImageView2.setImageResource(R.drawable.gpr3left);
      paramNumImageView3.setImageResource(R.drawable.gpr3left);
      paramNumImageView4.setImageResource(R.drawable.gpr3left);
      paramNumImageView5.setImageResource(R.drawable.gpr3left);
      break;
    case 49:
      paramNumImageView1.setImageResource(R.drawable.gpaleft);
      paramNumImageView2.setImageResource(R.drawable.gpaleft);
      paramNumImageView3.setImageResource(R.drawable.gpaleft);
      paramNumImageView4.setImageResource(R.drawable.gpaleft);
      paramNumImageView5.setImageResource(R.drawable.gpaleft);
    case 50:
      paramNumImageView1.setImageResource(R.drawable.gpl3left);
      paramNumImageView2.setImageResource(R.drawable.gpl3left);
      paramNumImageView3.setImageResource(R.drawable.gpl3left);
      paramNumImageView4.setImageResource(R.drawable.gpl3left);
      paramNumImageView5.setImageResource(R.drawable.gpl3left);
      break;
    case 48:
      paramNumImageView1.setImageResource(R.drawable.gpaleft);
      paramNumImageView2.setImageResource(R.drawable.gpaleft);
      paramNumImageView3.setImageResource(R.drawable.gpaleft);
      paramNumImageView4.setImageResource(R.drawable.gpaleft);
      paramNumImageView5.setImageResource(R.drawable.gpaleft);
      break;
    case 47:
      paramNumImageView1.setImageResource(R.drawable.gpaleft);
      paramNumImageView2.setImageResource(R.drawable.gpaleft);
      paramNumImageView3.setImageResource(R.drawable.gpaleft);
      paramNumImageView4.setImageResource(R.drawable.gpaleft);
      paramNumImageView5.setImageResource(R.drawable.gpaleft);
      break;
    case 46:
      paramNumImageView1.setImageResource(R.drawable.gpaleft);
      paramNumImageView2.setImageResource(R.drawable.gpaleft);
      paramNumImageView3.setImageResource(R.drawable.gpaleft);
      paramNumImageView4.setImageResource(R.drawable.gpaleft);
      paramNumImageView5.setImageResource(R.drawable.gpaleft);
      break;
    case 45:
      paramNumImageView1.setImageResource(R.drawable.gpaleft);
      paramNumImageView2.setImageResource(R.drawable.gpaleft);
      paramNumImageView3.setImageResource(R.drawable.gpaleft);
      paramNumImageView4.setImageResource(R.drawable.gpaleft);
      paramNumImageView5.setImageResource(R.drawable.gpaleft);
      break;
    case 44:
      paramNumImageView1.setImageResource(R.drawable.gpaleft);
      paramNumImageView2.setImageResource(R.drawable.gpaleft);
      paramNumImageView3.setImageResource(R.drawable.gpaleft);
      paramNumImageView4.setImageResource(R.drawable.gpaleft);
      paramNumImageView5.setImageResource(R.drawable.gpaleft);
      break;
    case 43:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprbleft);
        paramNumImageView2.setImageResource(R.drawable.gprbleft);
        paramNumImageView3.setImageResource(R.drawable.gprbleft);
        paramNumImageView4.setImageResource(R.drawable.gprbleft);
        paramNumImageView5.setImageResource(R.drawable.gprbleft);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1left);
        paramNumImageView2.setImageResource(R.drawable.gpr1left);
        paramNumImageView3.setImageResource(R.drawable.gpr1left);
        paramNumImageView4.setImageResource(R.drawable.gpr1left);
        paramNumImageView5.setImageResource(R.drawable.gpr1left);
      }
      break;
    case 42:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplbleft);
        paramNumImageView2.setImageResource(R.drawable.gplbleft);
        paramNumImageView3.setImageResource(R.drawable.gplbleft);
        paramNumImageView4.setImageResource(R.drawable.gplbleft);
        paramNumImageView5.setImageResource(R.drawable.gplbleft);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1left);
        paramNumImageView2.setImageResource(R.drawable.gpl1left);
        paramNumImageView3.setImageResource(R.drawable.gpl1left);
        paramNumImageView4.setImageResource(R.drawable.gpl1left);
        paramNumImageView5.setImageResource(R.drawable.gpl1left);
      }
      break;
    case 41:
      paramNumImageView1.setImageResource(R.drawable.gpyleft);
      paramNumImageView2.setImageResource(R.drawable.gpyleft);
      paramNumImageView3.setImageResource(R.drawable.gpyleft);
      paramNumImageView4.setImageResource(R.drawable.gpyleft);
      paramNumImageView5.setImageResource(R.drawable.gpyleft);
      break;
    case 40:
      paramNumImageView1.setImageResource(R.drawable.gpxleft);
      paramNumImageView2.setImageResource(R.drawable.gpxleft);
      paramNumImageView3.setImageResource(R.drawable.gpxleft);
      paramNumImageView4.setImageResource(R.drawable.gpxleft);
      paramNumImageView5.setImageResource(R.drawable.gpxleft);
      break;
    case 39:
      paramNumImageView1.setImageResource(R.drawable.gpbleft1);
      paramNumImageView2.setImageResource(R.drawable.gpbleft1);
      paramNumImageView3.setImageResource(R.drawable.gpbleft1);
      paramNumImageView4.setImageResource(R.drawable.gpbleft1);
      paramNumImageView5.setImageResource(R.drawable.gpbleft1);
      break;
    case 38:
      paramNumImageView1.setImageResource(R.drawable.gpaleft);
      paramNumImageView2.setImageResource(R.drawable.gpaleft);
      paramNumImageView3.setImageResource(R.drawable.gpaleft);
      paramNumImageView4.setImageResource(R.drawable.gpaleft);
      paramNumImageView5.setImageResource(R.drawable.gpaleft);
      break;
    case 37:
      paramNumImageView1.setImageResource(R.drawable.gpsensor);
      paramNumImageView2.setImageResource(R.drawable.gpsensor);
      paramNumImageView3.setImageResource(R.drawable.gpsensor);
      paramNumImageView4.setImageResource(R.drawable.gpsensor);
      paramNumImageView5.setImageResource(R.drawable.gpsensor);
      break;
    case 36:
      paramNumImageView1.setImageResource(R.drawable.gptouch);
      paramNumImageView2.setImageResource(R.drawable.gptouch);
      paramNumImageView3.setImageResource(R.drawable.gptouch);
      paramNumImageView4.setImageResource(R.drawable.gptouch);
      paramNumImageView5.setImageResource(R.drawable.gptouch);
      break;
    case 35:
      paramNumImageView1.setImageResource(R.drawable.gpy_add);
      paramNumImageView2.setImageResource(R.drawable.gpy_add);
      paramNumImageView3.setImageResource(R.drawable.gpy_add);
      paramNumImageView4.setImageResource(R.drawable.gpy_add);
      paramNumImageView5.setImageResource(R.drawable.gpy_add);
      break;
    case 34:
      paramNumImageView1.setImageResource(R.drawable.gpx_add);
      paramNumImageView2.setImageResource(R.drawable.gpx_add);
      paramNumImageView3.setImageResource(R.drawable.gpx_add);
      paramNumImageView4.setImageResource(R.drawable.gpx_add);
      paramNumImageView5.setImageResource(R.drawable.gpx_add);
      break;
    case 33:
      paramNumImageView1.setImageResource(R.drawable.gpb_add);
      paramNumImageView2.setImageResource(R.drawable.gpb_add);
      paramNumImageView3.setImageResource(R.drawable.gpb_add);
      paramNumImageView4.setImageResource(R.drawable.gpb_add);
      paramNumImageView5.setImageResource(R.drawable.gpb_add);
      break;
    case 32:
      paramNumImageView1.setImageResource(R.drawable.gpa_add);
      paramNumImageView2.setImageResource(R.drawable.gpa_add);
      paramNumImageView3.setImageResource(R.drawable.gpa_add);
      paramNumImageView4.setImageResource(R.drawable.gpa_add);
      paramNumImageView5.setImageResource(R.drawable.gpa_add);
      break;
    case 27:
    case 28:
    case 29:
    case 30:
    case 31:
      paramNumImageView1.setImageResource(R.drawable.newkey);
      paramNumImageView2.setImageResource(R.drawable.newkey);
      paramNumImageView3.setImageResource(R.drawable.newkey);
      paramNumImageView4.setImageResource(R.drawable.newkey);
      paramNumImageView5.setImageResource(R.drawable.newkey);
      break;
    case 26:
      paramNumImageView1.setImageResource(R.drawable.center3);
      paramNumImageView2.setImageResource(R.drawable.center3);
      paramNumImageView3.setImageResource(R.drawable.center3);
      paramNumImageView4.setImageResource(R.drawable.center3);
      paramNumImageView5.setImageResource(R.drawable.center3);
      break;
    case 25:
      paramNumImageView1.setImageResource(R.drawable.gpbpr2);
      paramNumImageView2.setImageResource(R.drawable.gpbpr2);
      paramNumImageView3.setImageResource(R.drawable.gpbpr2);
      paramNumImageView4.setImageResource(R.drawable.gpbpr2);
      paramNumImageView5.setImageResource(R.drawable.gpbpr2);
      break;
    case 24:
      paramNumImageView1.setImageResource(R.drawable.center2);
      paramNumImageView2.setImageResource(R.drawable.center2);
      paramNumImageView3.setImageResource(R.drawable.center2);
      paramNumImageView4.setImageResource(R.drawable.center2);
      paramNumImageView5.setImageResource(R.drawable.center2);
      break;
    case 23:
      paramNumImageView1.setImageResource(R.drawable.gpbpr1);
      paramNumImageView2.setImageResource(R.drawable.gpbpr1);
      paramNumImageView3.setImageResource(R.drawable.gpbpr1);
      paramNumImageView4.setImageResource(R.drawable.gpbpr1);
      paramNumImageView5.setImageResource(R.drawable.gpbpr1);
      break;
    case 22:
      paramNumImageView1.setImageResource(R.drawable.center1);
      paramNumImageView2.setImageResource(R.drawable.center1);
      paramNumImageView3.setImageResource(R.drawable.center1);
      paramNumImageView4.setImageResource(R.drawable.center1);
      paramNumImageView5.setImageResource(R.drawable.center1);
      break;
    case 21:
      paramNumImageView1.setImageResource(R.drawable.gpm4);
      paramNumImageView2.setImageResource(R.drawable.gpm4);
      paramNumImageView3.setImageResource(R.drawable.gpm4);
      paramNumImageView4.setImageResource(R.drawable.gpm4);
      paramNumImageView5.setImageResource(R.drawable.gpm4);
      break;
    case 20:
      paramNumImageView1.setImageResource(R.drawable.gpm3);
      paramNumImageView2.setImageResource(R.drawable.gpm3);
      paramNumImageView3.setImageResource(R.drawable.gpm3);
      paramNumImageView4.setImageResource(R.drawable.gpm3);
      paramNumImageView5.setImageResource(R.drawable.gpm3);
      break;
    case 19:
      paramNumImageView1.setImageResource(R.drawable.gpm2);
      paramNumImageView2.setImageResource(R.drawable.gpm2);
      paramNumImageView3.setImageResource(R.drawable.gpm2);
      paramNumImageView4.setImageResource(R.drawable.gpm2);
      paramNumImageView5.setImageResource(R.drawable.gpm2);
      break;
    case 18:
      paramNumImageView1.setImageResource(R.drawable.gpm1);
      paramNumImageView2.setImageResource(R.drawable.gpm1);
      paramNumImageView3.setImageResource(R.drawable.gpm1);
      paramNumImageView4.setImageResource(R.drawable.gpm1);
      paramNumImageView5.setImageResource(R.drawable.gpm1);
      break;
    case 17:
      paramNumImageView1.setImageResource(R.drawable.gpbpr);
      paramNumImageView2.setImageResource(R.drawable.gpbpr);
      paramNumImageView3.setImageResource(R.drawable.gpbpr);
      paramNumImageView4.setImageResource(R.drawable.gpbpr);
      paramNumImageView5.setImageResource(R.drawable.gpbpr);
      break;
    case 16:
      paramNumImageView1.setImageResource(R.drawable.gpbpl);
      paramNumImageView2.setImageResource(R.drawable.gpbpl);
      paramNumImageView3.setImageResource(R.drawable.gpbpl);
      paramNumImageView4.setImageResource(R.drawable.gpbpl);
      paramNumImageView5.setImageResource(R.drawable.gpbpl);
      break;
    case 15:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprt);
        paramNumImageView2.setImageResource(R.drawable.gprt);
        paramNumImageView3.setImageResource(R.drawable.gprt);
        paramNumImageView4.setImageResource(R.drawable.gprt);
        paramNumImageView5.setImageResource(R.drawable.gprt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr2);
        paramNumImageView2.setImageResource(R.drawable.gpr2);
        paramNumImageView3.setImageResource(R.drawable.gpr2);
        paramNumImageView4.setImageResource(R.drawable.gpr2);
        paramNumImageView5.setImageResource(R.drawable.gpr2);
      }
      break;
    case 14:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplt);
        paramNumImageView2.setImageResource(R.drawable.gplt);
        paramNumImageView3.setImageResource(R.drawable.gplt);
        paramNumImageView4.setImageResource(R.drawable.gplt);
        paramNumImageView5.setImageResource(R.drawable.gplt);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl2);
        paramNumImageView2.setImageResource(R.drawable.gpl2);
        paramNumImageView3.setImageResource(R.drawable.gpl2);
        paramNumImageView4.setImageResource(R.drawable.gpl2);
        paramNumImageView5.setImageResource(R.drawable.gpl2);
      }
      break;
    case 13:
      paramNumImageView1.setImageResource(R.drawable.gpr3);
      paramNumImageView2.setImageResource(R.drawable.gpr3);
      paramNumImageView3.setImageResource(R.drawable.gpr3);
      paramNumImageView4.setImageResource(R.drawable.gpr3);
      paramNumImageView5.setImageResource(R.drawable.gpr3);
      break;
    case 12:
      paramNumImageView1.setImageResource(R.drawable.gpl3);
      paramNumImageView2.setImageResource(R.drawable.gpl3);
      paramNumImageView3.setImageResource(R.drawable.gpl3);
      paramNumImageView4.setImageResource(R.drawable.gpl3);
      paramNumImageView5.setImageResource(R.drawable.gpl3);
      break;
    case 11:
      paramNumImageView1.setImageResource(R.drawable.gpselect);
      paramNumImageView2.setImageResource(R.drawable.gpselect);
      paramNumImageView3.setImageResource(R.drawable.gpselect);
      paramNumImageView4.setImageResource(R.drawable.gpselect);
      paramNumImageView5.setImageResource(R.drawable.gpselect);
      break;
    case 10:
      paramNumImageView1.setImageResource(R.drawable.gpstart);
      paramNumImageView2.setImageResource(R.drawable.gpstart);
      paramNumImageView3.setImageResource(R.drawable.gpstart);
      paramNumImageView4.setImageResource(R.drawable.gpstart);
      paramNumImageView5.setImageResource(R.drawable.gpstart);
      break;
    case 9:
      paramNumImageView1.setImageResource(R.drawable.gpright);
      paramNumImageView2.setImageResource(R.drawable.gpright);
      paramNumImageView3.setImageResource(R.drawable.gpright);
      paramNumImageView4.setImageResource(R.drawable.gpright);
      paramNumImageView5.setImageResource(R.drawable.gpright);
      break;
    case 8:
      paramNumImageView1.setImageResource(R.drawable.gpleft);
      paramNumImageView2.setImageResource(R.drawable.gpleft);
      paramNumImageView3.setImageResource(R.drawable.gpleft);
      paramNumImageView4.setImageResource(R.drawable.gpleft);
      paramNumImageView5.setImageResource(R.drawable.gpleft);
      break;
    case 7:
      paramNumImageView1.setImageResource(R.drawable.gpdown);
      paramNumImageView2.setImageResource(R.drawable.gpdown);
      paramNumImageView3.setImageResource(R.drawable.gpdown);
      paramNumImageView4.setImageResource(R.drawable.gpdown);
      paramNumImageView5.setImageResource(R.drawable.gpdown);
      break;
    case 6:
      paramNumImageView1.setImageResource(R.drawable.gpup);
      paramNumImageView2.setImageResource(R.drawable.gpup);
      paramNumImageView3.setImageResource(R.drawable.gpup);
      paramNumImageView4.setImageResource(R.drawable.gpup);
      paramNumImageView5.setImageResource(R.drawable.gpup);
      break;
    case 5:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gprb);
        paramNumImageView2.setImageResource(R.drawable.gprb);
        paramNumImageView3.setImageResource(R.drawable.gprb);
        paramNumImageView4.setImageResource(R.drawable.gprb);
        paramNumImageView5.setImageResource(R.drawable.gprb);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpr1);
        paramNumImageView2.setImageResource(R.drawable.gpr1);
        paramNumImageView3.setImageResource(R.drawable.gpr1);
        paramNumImageView4.setImageResource(R.drawable.gpr1);
        paramNumImageView5.setImageResource(R.drawable.gpr1);
      }
      break;
    case 4:
      if (!bluetoothdevmanager.deviceVID.equalsIgnoreCase("7"))
      {
        paramNumImageView1.setImageResource(R.drawable.gplb);
        paramNumImageView2.setImageResource(R.drawable.gplb);
        paramNumImageView3.setImageResource(R.drawable.gplb);
        paramNumImageView4.setImageResource(R.drawable.gplb);
        paramNumImageView5.setImageResource(R.drawable.gplb);
      }
      else
      {
        paramNumImageView1.setImageResource(R.drawable.gpl1);
        paramNumImageView2.setImageResource(R.drawable.gpl1);
        paramNumImageView3.setImageResource(R.drawable.gpl1);
        paramNumImageView4.setImageResource(R.drawable.gpl1);
        paramNumImageView5.setImageResource(R.drawable.gpl1);
      }
      break;
    case 3:
      paramNumImageView1.setImageResource(R.drawable.gpy);
      paramNumImageView2.setImageResource(R.drawable.gpy);
      paramNumImageView3.setImageResource(R.drawable.gpy);
      paramNumImageView4.setImageResource(R.drawable.gpy);
      paramNumImageView5.setImageResource(R.drawable.gpy);
      break;
    case 2:
      paramNumImageView1.setImageResource(R.drawable.gpx);
      paramNumImageView2.setImageResource(R.drawable.gpx);
      paramNumImageView3.setImageResource(R.drawable.gpx);
      paramNumImageView4.setImageResource(R.drawable.gpx);
      paramNumImageView5.setImageResource(R.drawable.gpx);
      break;
    case 1:
      paramNumImageView1.setImageResource(R.drawable.gpb);
      paramNumImageView2.setImageResource(R.drawable.gpb);
      paramNumImageView3.setImageResource(R.drawable.gpb);
      paramNumImageView4.setImageResource(R.drawable.gpb);
      paramNumImageView5.setImageResource(R.drawable.gpb);
      break;
    case 0:
      paramNumImageView1.setImageResource(R.drawable.gpa);
      paramNumImageView2.setImageResource(R.drawable.gpa);
      paramNumImageView3.setImageResource(R.drawable.gpa);
      paramNumImageView4.setImageResource(R.drawable.gpa);
      paramNumImageView5.setImageResource(R.drawable.gpa);
    }
    if (paramInt2 != 0)
    {
      if (paramInt2 != 1)
      {
        if (paramInt2 != 2)
        {
          if (paramInt2 != 3)
          {
            if (paramInt2 != 4)
              return paramNumImageView1;
            return paramNumImageView5;
          }
          return paramNumImageView4;
        }
        return paramNumImageView3;
      }
      return paramNumImageView2;
    }
    return paramNumImageView1;
  }
}
