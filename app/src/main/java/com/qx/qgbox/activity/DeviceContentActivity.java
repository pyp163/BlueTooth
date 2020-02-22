package com.qx.qgbox.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.qx.qgbox.R;

public class DeviceContentActivity extends Activity
{
  private String contentUrl;
  private ImageView iv_back;
  private ProgressBar progressBar;
  private WebChromeClient webChromeClient = new WebChromeClient()
  {
    public boolean onJsAlert(WebView paramAnonymousWebView, String paramAnonymousString1, String paramAnonymousString2, JsResult paramAnonymousJsResult)
    {
      paramAnonymousWebView = new AlertDialog.Builder(paramAnonymousWebView.getContext());
      paramAnonymousWebView.setMessage(paramAnonymousString2).setPositiveButton("确定", null);
      paramAnonymousWebView.setCancelable(false);
      paramAnonymousWebView.create().show();
      paramAnonymousJsResult.confirm();
      return true;
    }

    public void onProgressChanged(WebView paramAnonymousWebView, int paramAnonymousInt)
    {
      DeviceContentActivity.this.progressBar.setProgress(paramAnonymousInt);
    }

    public void onReceivedTitle(WebView paramAnonymousWebView, String paramAnonymousString)
    {
      super.onReceivedTitle(paramAnonymousWebView, paramAnonymousString);
    }
  };
  private WebView webView;
  private WebViewClient webViewClient = new WebViewClient()
  {
    public void onPageFinished(WebView paramAnonymousWebView, String paramAnonymousString)
    {
      DeviceContentActivity.this.progressBar.setVisibility(View.GONE);
    }

    public void onPageStarted(WebView paramAnonymousWebView, String paramAnonymousString, Bitmap paramAnonymousBitmap)
    {
      DeviceContentActivity.this.progressBar.setVisibility(View.VISIBLE);
    }

    public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
    {
      if (paramAnonymousString.equals("http://www.google.com/"))
      {
        Toast.makeText(DeviceContentActivity.this, "国内不能访问google,拦截该url", Toast.LENGTH_LONG).show();
        return true;
      }
      return super.shouldOverrideUrlLoading(paramAnonymousWebView, paramAnonymousString);
    }
  };

  private void initListener()
  {
    this.iv_back.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (DeviceContentActivity.this.webView.canGoBack())
        {
          DeviceContentActivity.this.webView.goBack();
          return;
        }
        DeviceContentActivity.this.finish();
      }
    });
  }

  private void initView()
  {
    this.iv_back = ((ImageView)findViewById(R.id.iv_back));
    this.progressBar = ((ProgressBar)findViewById(R.id.progressbar));
    this.webView = ((WebView)findViewById(R.id.webview));
    this.webView.loadUrl(this.contentUrl);
    this.webView.addJavascriptInterface(this, "android");
    this.webView.setWebChromeClient(this.webChromeClient);
    this.webView.setWebViewClient(this.webViewClient);
    WebSettings localWebSettings = this.webView.getSettings();
    localWebSettings.setJavaScriptEnabled(true);
    localWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
    localWebSettings.setSupportZoom(true);
    localWebSettings.setBuiltInZoomControls(true);
  }

  @JavascriptInterface
  public void getClient(String paramString)
  {
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.activity_device_content);
    this.contentUrl = getIntent().getStringExtra("device_content");
    initView();
    initListener();
  }

  protected void onDestroy()
  {
    super.onDestroy();
    this.webView.destroy();
    this.webView = null;
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((this.webView.canGoBack()) && (paramInt == 4))
    {
      this.webView.goBack();
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
}
