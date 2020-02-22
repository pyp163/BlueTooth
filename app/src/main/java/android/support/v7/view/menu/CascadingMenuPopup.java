package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Parcelable;
import android.os.SystemClock;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.appcompat.R.dimen;
import android.support.v7.appcompat.R.layout;
import android.support.v7.widget.MenuItemHoverListener;
import android.support.v7.widget.MenuPopupWindow;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class CascadingMenuPopup extends MenuPopup
  implements MenuPresenter, View.OnKeyListener, PopupWindow.OnDismissListener
{
  static final int HORIZ_POSITION_LEFT = 0;
  static final int HORIZ_POSITION_RIGHT = 1;
  private static final int ITEM_LAYOUT = R.layout.abc_cascading_menu_item_layout;
  static final int SUBMENU_TIMEOUT_MS = 200;
  private View mAnchorView;
  private final View.OnAttachStateChangeListener mAttachStateChangeListener = new View.OnAttachStateChangeListener()
  {
    public void onViewAttachedToWindow(View paramAnonymousView)
    {
    }

    public void onViewDetachedFromWindow(View paramAnonymousView)
    {
      if (CascadingMenuPopup.this.mTreeObserver != null)
      {
        if (!CascadingMenuPopup.this.mTreeObserver.isAlive())
          CascadingMenuPopup.this.mTreeObserver = paramAnonymousView.getViewTreeObserver();
        CascadingMenuPopup.this.mTreeObserver.removeGlobalOnLayoutListener(CascadingMenuPopup.this.mGlobalLayoutListener);
      }
      paramAnonymousView.removeOnAttachStateChangeListener(this);
    }
  };
  private final Context mContext;
  private int mDropDownGravity = 0;
  private boolean mForceShowIcon;
  final ViewTreeObserver.OnGlobalLayoutListener mGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener()
  {
    public void onGlobalLayout()
    {
      if ((CascadingMenuPopup.this.isShowing()) && (CascadingMenuPopup.this.mShowingMenus.size() > 0) && (!((CascadingMenuPopup.CascadingMenuInfo)CascadingMenuPopup.this.mShowingMenus.get(0)).window.isModal()))
      {
        Object localObject = CascadingMenuPopup.this.mShownAnchorView;
        if ((localObject != null) && (((View)localObject).isShown()))
          localObject = CascadingMenuPopup.this.mShowingMenus.iterator();
        while (((Iterator)localObject).hasNext())
        {
          ((CascadingMenuPopup.CascadingMenuInfo)((Iterator)localObject).next()).window.show();
          continue;
          CascadingMenuPopup.this.dismiss();
        }
      }
    }
  };
  private boolean mHasXOffset;
  private boolean mHasYOffset;
  private int mLastPosition;
  private final MenuItemHoverListener mMenuItemHoverListener = new MenuItemHoverListener()
  {
    public void onItemHoverEnter(@NonNull final MenuBuilder paramAnonymousMenuBuilder, @NonNull final MenuItem paramAnonymousMenuItem)
    {
      Handler localHandler = CascadingMenuPopup.this.mSubMenuHoverHandler;
      final CascadingMenuPopup.CascadingMenuInfo localCascadingMenuInfo = null;
      localHandler.removeCallbacksAndMessages(null);
      int j = CascadingMenuPopup.this.mShowingMenus.size();
      int i = 0;
      while (i < j)
      {
        if (paramAnonymousMenuBuilder == ((CascadingMenuPopup.CascadingMenuInfo)CascadingMenuPopup.this.mShowingMenus.get(i)).menu)
          break label75;
        i += 1;
      }
      i = -1;
      label75: if (i == -1)
        return;
      i += 1;
      if (i < CascadingMenuPopup.this.mShowingMenus.size())
        localCascadingMenuInfo = (CascadingMenuPopup.CascadingMenuInfo)CascadingMenuPopup.this.mShowingMenus.get(i);
      paramAnonymousMenuItem = new Runnable()
      {
        public void run()
        {
          if (localCascadingMenuInfo != null)
          {
            CascadingMenuPopup.this.mShouldCloseImmediately = true;
            localCascadingMenuInfo.menu.close(false);
            CascadingMenuPopup.this.mShouldCloseImmediately = false;
          }
          if ((paramAnonymousMenuItem.isEnabled()) && (paramAnonymousMenuItem.hasSubMenu()))
            paramAnonymousMenuBuilder.performItemAction(paramAnonymousMenuItem, 4);
        }
      };
      long l = SystemClock.uptimeMillis();
      CascadingMenuPopup.this.mSubMenuHoverHandler.postAtTime(paramAnonymousMenuItem, paramAnonymousMenuBuilder, l + 200L);
    }

    public void onItemHoverExit(@NonNull MenuBuilder paramAnonymousMenuBuilder, @NonNull MenuItem paramAnonymousMenuItem)
    {
      CascadingMenuPopup.this.mSubMenuHoverHandler.removeCallbacksAndMessages(paramAnonymousMenuBuilder);
    }
  };
  private final int mMenuMaxWidth;
  private PopupWindow.OnDismissListener mOnDismissListener;
  private final boolean mOverflowOnly;
  private final List<MenuBuilder> mPendingMenus = new ArrayList();
  private final int mPopupStyleAttr;
  private final int mPopupStyleRes;
  private MenuPresenter.Callback mPresenterCallback;
  private int mRawDropDownGravity = 0;
  boolean mShouldCloseImmediately;
  private boolean mShowTitle;
  final List<CascadingMenuInfo> mShowingMenus = new ArrayList();
  View mShownAnchorView;
  final Handler mSubMenuHoverHandler;
  ViewTreeObserver mTreeObserver;
  private int mXOffset;
  private int mYOffset;

  public CascadingMenuPopup(@NonNull Context paramContext, @NonNull View paramView, @AttrRes int paramInt1, @StyleRes int paramInt2, boolean paramBoolean)
  {
    this.mContext = paramContext;
    this.mAnchorView = paramView;
    this.mPopupStyleAttr = paramInt1;
    this.mPopupStyleRes = paramInt2;
    this.mOverflowOnly = paramBoolean;
    this.mForceShowIcon = false;
    this.mLastPosition = getInitialMenuPosition();
    paramContext = paramContext.getResources();
    this.mMenuMaxWidth = Math.max(paramContext.getDisplayMetrics().widthPixels / 2, paramContext.getDimensionPixelSize(R.dimen.abc_config_prefDialogWidth));
    this.mSubMenuHoverHandler = new Handler();
  }

  private MenuPopupWindow createPopupWindow()
  {
    MenuPopupWindow localMenuPopupWindow = new MenuPopupWindow(this.mContext, null, this.mPopupStyleAttr, this.mPopupStyleRes);
    localMenuPopupWindow.setHoverListener(this.mMenuItemHoverListener);
    localMenuPopupWindow.setOnItemClickListener(this);
    localMenuPopupWindow.setOnDismissListener(this);
    localMenuPopupWindow.setAnchorView(this.mAnchorView);
    localMenuPopupWindow.setDropDownGravity(this.mDropDownGravity);
    localMenuPopupWindow.setModal(true);
    localMenuPopupWindow.setInputMethodMode(2);
    return localMenuPopupWindow;
  }

  private int findIndexOfAddedMenu(@NonNull MenuBuilder paramMenuBuilder)
  {
    int j = this.mShowingMenus.size();
    int i = 0;
    while (i < j)
    {
      if (paramMenuBuilder == ((CascadingMenuInfo)this.mShowingMenus.get(i)).menu)
        return i;
      i += 1;
    }
    return -1;
  }

  private MenuItem findMenuItemForSubmenu(@NonNull MenuBuilder paramMenuBuilder1, @NonNull MenuBuilder paramMenuBuilder2)
  {
    int j = paramMenuBuilder1.size();
    int i = 0;
    while (i < j)
    {
      MenuItem localMenuItem = paramMenuBuilder1.getItem(i);
      if ((localMenuItem.hasSubMenu()) && (paramMenuBuilder2 == localMenuItem.getSubMenu()))
        return localMenuItem;
      i += 1;
    }
    return null;
  }

  @Nullable
  private View findParentViewForSubmenu(@NonNull CascadingMenuInfo paramCascadingMenuInfo, @NonNull MenuBuilder paramMenuBuilder)
  {
    paramMenuBuilder = findMenuItemForSubmenu(paramCascadingMenuInfo.menu, paramMenuBuilder);
    if (paramMenuBuilder == null)
      return null;
    ListView localListView = paramCascadingMenuInfo.getListView();
    paramCascadingMenuInfo = localListView.getAdapter();
    boolean bool = paramCascadingMenuInfo instanceof HeaderViewListAdapter;
    int i = 0;
    int j;
    if (bool)
    {
      paramCascadingMenuInfo = (HeaderViewListAdapter)paramCascadingMenuInfo;
      j = paramCascadingMenuInfo.getHeadersCount();
      paramCascadingMenuInfo = (MenuAdapter)paramCascadingMenuInfo.getWrappedAdapter();
    }
    else
    {
      paramCascadingMenuInfo = (MenuAdapter)paramCascadingMenuInfo;
      j = 0;
    }
    int k = paramCascadingMenuInfo.getCount();
    while (i < k)
    {
      if (paramMenuBuilder == paramCascadingMenuInfo.getItem(i))
        break label104;
      i += 1;
    }
    i = -1;
    label104: if (i == -1)
      return null;
    i = i + j - localListView.getFirstVisiblePosition();
    if (i >= 0)
    {
      if (i >= localListView.getChildCount())
        return null;
      return localListView.getChildAt(i);
    }
    return null;
  }

  private int getInitialMenuPosition()
  {
    int j = ViewCompat.getLayoutDirection(this.mAnchorView);
    int i = 1;
    if (j == 1)
      i = 0;
    return i;
  }

  private int getNextMenuPosition(int paramInt)
  {
    ListView localListView = ((CascadingMenuInfo)this.mShowingMenus.get(this.mShowingMenus.size() - 1)).getListView();
    int[] arrayOfInt = new int[2];
    localListView.getLocationOnScreen(arrayOfInt);
    Rect localRect = new Rect();
    this.mShownAnchorView.getWindowVisibleDisplayFrame(localRect);
    if (this.mLastPosition == 1)
    {
      if (arrayOfInt[0] + localListView.getWidth() + paramInt > localRect.right)
        return 0;
      return 1;
    }
    if (arrayOfInt[0] - paramInt < 0)
      return 1;
    return 0;
  }

  private void showMenu(@NonNull MenuBuilder paramMenuBuilder)
  {
    Object localObject3 = LayoutInflater.from(this.mContext);
    Object localObject1 = new MenuAdapter(paramMenuBuilder, (LayoutInflater)localObject3, this.mOverflowOnly, ITEM_LAYOUT);
    if ((!isShowing()) && (this.mForceShowIcon))
      ((MenuAdapter)localObject1).setForceShowIcon(true);
    else if (isShowing())
      ((MenuAdapter)localObject1).setForceShowIcon(MenuPopup.shouldPreserveIconSpacing(paramMenuBuilder));
    int m = measureIndividualMenuWidth((ListAdapter)localObject1, null, this.mContext, this.mMenuMaxWidth);
    MenuPopupWindow localMenuPopupWindow = createPopupWindow();
    localMenuPopupWindow.setAdapter((ListAdapter)localObject1);
    localMenuPopupWindow.setContentWidth(m);
    localMenuPopupWindow.setDropDownGravity(this.mDropDownGravity);
    if (this.mShowingMenus.size() > 0)
    {
      localObject1 = (CascadingMenuInfo)this.mShowingMenus.get(this.mShowingMenus.size() - 1);
      localObject2 = findParentViewForSubmenu((CascadingMenuInfo)localObject1, paramMenuBuilder);
    }
    else
    {
      localObject1 = null;
      localObject2 = localObject1;
    }
    if (localObject2 != null)
    {
      localMenuPopupWindow.setTouchModal(false);
      localMenuPopupWindow.setEnterTransition(null);
      int j = getNextMenuPosition(m);
      int i;
      if (j == 1)
        i = 1;
      else
        i = 0;
      this.mLastPosition = j;
      int k;
      if (Build.VERSION.SDK_INT >= 26)
      {
        localMenuPopupWindow.setAnchorView((View)localObject2);
        j = 0;
        k = 0;
      }
      else
      {
        int[] arrayOfInt1 = new int[2];
        this.mAnchorView.getLocationOnScreen(arrayOfInt1);
        int[] arrayOfInt2 = new int[2];
        ((View)localObject2).getLocationOnScreen(arrayOfInt2);
        if ((this.mDropDownGravity & 0x7) == 5)
        {
          arrayOfInt1[0] += this.mAnchorView.getWidth();
          arrayOfInt2[0] += ((View)localObject2).getWidth();
        }
        k = arrayOfInt2[0] - arrayOfInt1[0];
        j = arrayOfInt2[1] - arrayOfInt1[1];
      }
      if ((this.mDropDownGravity & 0x5) == 5)
      {
        if (i != 0)
          i = k + m;
        else
          i = k - ((View)localObject2).getWidth();
      }
      else if (i != 0)
        i = k + ((View)localObject2).getWidth();
      else
        i = k - m;
      localMenuPopupWindow.setHorizontalOffset(i);
      localMenuPopupWindow.setOverlapAnchor(true);
      localMenuPopupWindow.setVerticalOffset(j);
    }
    else
    {
      if (this.mHasXOffset)
        localMenuPopupWindow.setHorizontalOffset(this.mXOffset);
      if (this.mHasYOffset)
        localMenuPopupWindow.setVerticalOffset(this.mYOffset);
      localMenuPopupWindow.setEpicenterBounds(getEpicenterBounds());
    }
    Object localObject2 = new CascadingMenuInfo(localMenuPopupWindow, paramMenuBuilder, this.mLastPosition);
    this.mShowingMenus.add(localObject2);
    localMenuPopupWindow.show();
    localObject2 = localMenuPopupWindow.getListView();
    ((ListView)localObject2).setOnKeyListener(this);
    if ((localObject1 == null) && (this.mShowTitle) && (paramMenuBuilder.getHeaderTitle() != null))
    {
      localObject1 = (FrameLayout)((LayoutInflater)localObject3).inflate(R.layout.abc_popup_menu_header_item_layout, (ViewGroup)localObject2, false);
      localObject3 = (TextView)((FrameLayout)localObject1).findViewById(16908310);
      ((FrameLayout)localObject1).setEnabled(false);
      ((TextView)localObject3).setText(paramMenuBuilder.getHeaderTitle());
      ((ListView)localObject2).addHeaderView((View)localObject1, null, false);
      localMenuPopupWindow.show();
    }
  }

  public void addMenu(MenuBuilder paramMenuBuilder)
  {
    paramMenuBuilder.addMenuPresenter(this, this.mContext);
    if (isShowing())
    {
      showMenu(paramMenuBuilder);
      return;
    }
    this.mPendingMenus.add(paramMenuBuilder);
  }

  protected boolean closeMenuOnSubMenuOpened()
  {
    return false;
  }

  public void dismiss()
  {
    int i = this.mShowingMenus.size();
    if (i > 0)
    {
      CascadingMenuInfo[] arrayOfCascadingMenuInfo = (CascadingMenuInfo[])this.mShowingMenus.toArray(new CascadingMenuInfo[i]);
      i -= 1;
      while (i >= 0)
      {
        CascadingMenuInfo localCascadingMenuInfo = arrayOfCascadingMenuInfo[i];
        if (localCascadingMenuInfo.window.isShowing())
          localCascadingMenuInfo.window.dismiss();
        i -= 1;
      }
    }
  }

  public boolean flagActionItems()
  {
    return false;
  }

  public ListView getListView()
  {
    if (this.mShowingMenus.isEmpty())
      return null;
    return ((CascadingMenuInfo)this.mShowingMenus.get(this.mShowingMenus.size() - 1)).getListView();
  }

  public boolean isShowing()
  {
    int i = this.mShowingMenus.size();
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (i > 0)
    {
      bool1 = bool2;
      if (((CascadingMenuInfo)this.mShowingMenus.get(0)).window.isShowing())
        bool1 = true;
    }
    return bool1;
  }

  public void onCloseMenu(MenuBuilder paramMenuBuilder, boolean paramBoolean)
  {
    int i = findIndexOfAddedMenu(paramMenuBuilder);
    if (i < 0)
      return;
    int j = i + 1;
    if (j < this.mShowingMenus.size())
      ((CascadingMenuInfo)this.mShowingMenus.get(j)).menu.close(false);
    CascadingMenuInfo localCascadingMenuInfo = (CascadingMenuInfo)this.mShowingMenus.remove(i);
    localCascadingMenuInfo.menu.removeMenuPresenter(this);
    if (this.mShouldCloseImmediately)
    {
      localCascadingMenuInfo.window.setExitTransition(null);
      localCascadingMenuInfo.window.setAnimationStyle(0);
    }
    localCascadingMenuInfo.window.dismiss();
    i = this.mShowingMenus.size();
    if (i > 0)
      this.mLastPosition = ((CascadingMenuInfo)this.mShowingMenus.get(i - 1)).position;
    else
      this.mLastPosition = getInitialMenuPosition();
    if (i == 0)
    {
      dismiss();
      if (this.mPresenterCallback != null)
        this.mPresenterCallback.onCloseMenu(paramMenuBuilder, true);
      if (this.mTreeObserver != null)
      {
        if (this.mTreeObserver.isAlive())
          this.mTreeObserver.removeGlobalOnLayoutListener(this.mGlobalLayoutListener);
        this.mTreeObserver = null;
      }
      this.mShownAnchorView.removeOnAttachStateChangeListener(this.mAttachStateChangeListener);
      this.mOnDismissListener.onDismiss();
      return;
    }
    if (paramBoolean)
      ((CascadingMenuInfo)this.mShowingMenus.get(0)).menu.close(false);
  }

  public void onDismiss()
  {
    int j = this.mShowingMenus.size();
    int i = 0;
    while (i < j)
    {
      localCascadingMenuInfo = (CascadingMenuInfo)this.mShowingMenus.get(i);
      if (!localCascadingMenuInfo.window.isShowing())
        break label53;
      i += 1;
    }
    CascadingMenuInfo localCascadingMenuInfo = null;
    label53: if (localCascadingMenuInfo != null)
      localCascadingMenuInfo.menu.close(false);
  }

  public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramKeyEvent.getAction() == 1) && (paramInt == 82))
    {
      dismiss();
      return true;
    }
    return false;
  }

  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
  }

  public Parcelable onSaveInstanceState()
  {
    return null;
  }

  public boolean onSubMenuSelected(SubMenuBuilder paramSubMenuBuilder)
  {
    Iterator localIterator = this.mShowingMenus.iterator();
    while (localIterator.hasNext())
    {
      CascadingMenuInfo localCascadingMenuInfo = (CascadingMenuInfo)localIterator.next();
      if (paramSubMenuBuilder == localCascadingMenuInfo.menu)
      {
        localCascadingMenuInfo.getListView().requestFocus();
        return true;
      }
    }
    if (paramSubMenuBuilder.hasVisibleItems())
    {
      addMenu(paramSubMenuBuilder);
      if (this.mPresenterCallback != null)
        this.mPresenterCallback.onOpenSubMenu(paramSubMenuBuilder);
      return true;
    }
    return false;
  }

  public void setAnchorView(@NonNull View paramView)
  {
    if (this.mAnchorView != paramView)
    {
      this.mAnchorView = paramView;
      this.mDropDownGravity = GravityCompat.getAbsoluteGravity(this.mRawDropDownGravity, ViewCompat.getLayoutDirection(this.mAnchorView));
    }
  }

  public void setCallback(MenuPresenter.Callback paramCallback)
  {
    this.mPresenterCallback = paramCallback;
  }

  public void setForceShowIcon(boolean paramBoolean)
  {
    this.mForceShowIcon = paramBoolean;
  }

  public void setGravity(int paramInt)
  {
    if (this.mRawDropDownGravity != paramInt)
    {
      this.mRawDropDownGravity = paramInt;
      this.mDropDownGravity = GravityCompat.getAbsoluteGravity(paramInt, ViewCompat.getLayoutDirection(this.mAnchorView));
    }
  }

  public void setHorizontalOffset(int paramInt)
  {
    this.mHasXOffset = true;
    this.mXOffset = paramInt;
  }

  public void setOnDismissListener(PopupWindow.OnDismissListener paramOnDismissListener)
  {
    this.mOnDismissListener = paramOnDismissListener;
  }

  public void setShowTitle(boolean paramBoolean)
  {
    this.mShowTitle = paramBoolean;
  }

  public void setVerticalOffset(int paramInt)
  {
    this.mHasYOffset = true;
    this.mYOffset = paramInt;
  }

  public void show()
  {
    if (isShowing())
      return;
    Iterator localIterator = this.mPendingMenus.iterator();
    while (localIterator.hasNext())
      showMenu((MenuBuilder)localIterator.next());
    this.mPendingMenus.clear();
    this.mShownAnchorView = this.mAnchorView;
    if (this.mShownAnchorView != null)
    {
      int i;
      if (this.mTreeObserver == null)
        i = 1;
      else
        i = 0;
      this.mTreeObserver = this.mShownAnchorView.getViewTreeObserver();
      if (i != 0)
        this.mTreeObserver.addOnGlobalLayoutListener(this.mGlobalLayoutListener);
      this.mShownAnchorView.addOnAttachStateChangeListener(this.mAttachStateChangeListener);
    }
  }

  public void updateMenuView(boolean paramBoolean)
  {
    Iterator localIterator = this.mShowingMenus.iterator();
    while (localIterator.hasNext())
      toMenuAdapter(((CascadingMenuInfo)localIterator.next()).getListView().getAdapter()).notifyDataSetChanged();
  }

  private static class CascadingMenuInfo
  {
    public final MenuBuilder menu;
    public final int position;
    public final MenuPopupWindow window;

    public CascadingMenuInfo(@NonNull MenuPopupWindow paramMenuPopupWindow, @NonNull MenuBuilder paramMenuBuilder, int paramInt)
    {
      this.window = paramMenuPopupWindow;
      this.menu = paramMenuBuilder;
      this.position = paramInt;
    }

    public ListView getListView()
    {
      return this.window.getListView();
    }
  }

  @Retention(RetentionPolicy.SOURCE)
  public static @interface HorizPosition
  {
  }
}