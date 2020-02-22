package android.support.v7.app;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

class NavItemSelectedListener
  implements AdapterView.OnItemSelectedListener
{
  private final ActionBar.OnNavigationListener mListener;

  public NavItemSelectedListener(ActionBar.OnNavigationListener paramOnNavigationListener)
  {
    this.mListener = paramOnNavigationListener;
  }

  public void onItemSelected(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    if (this.mListener != null)
      this.mListener.onNavigationItemSelected(paramInt, paramLong);
  }

  public void onNothingSelected(AdapterView<?> paramAdapterView)
  {
  }
}