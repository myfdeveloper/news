package com.myf.health.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.myf.health.MyApplication;
import com.myf.health.R;
import com.myf.health.helper.ToastHelper;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * TODO: document your custom view class.
 */
public class MyWebView extends WebView {

    @Inject
    ToastHelper mToastHelper;
    //private String basicUA;
    private Map<String, String> header;

    public MyWebView(Context context) {
        super(context);
        init();
    }

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        ((MyApplication) getContext().getApplicationContext()).getApplicationComponent().inject(this);
        WebSettings settings = getSettings();
        settings.setBuiltInZoomControls(false);
        settings.setSupportZoom(false);
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
        settings.setSupportMultipleWindows(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(1);
        settings.setUseWideViewPort(true);
        if (Build.VERSION.SDK_INT > 6) {
            settings.setAppCacheEnabled(true);
            settings.setLoadWithOverviewMode(true);
        }
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        String path = getContext().getFilesDir().getPath();
        settings.setGeolocationEnabled(true);
        settings.setGeolocationDatabasePath(path);
        settings.setDomStorageEnabled(true);
        //this.basicUA = settings.getUserAgentString() + " kanqiu/7.05.6303/7059";
        setBackgroundColor(0);
        initWebViewClient();
        setWebChromeClient(new MyChromeClient());

    }

    private void initWebViewClient() {
        CookieManager.getInstance().setAcceptCookie(true);
        setWebViewClient(new MyWebClient());
    }

    private class MyWebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {//跳转
            view.loadUrl(url); //在当前的webview中跳转到新的url
            /*Uri uri = Uri.parse(url);
            String scheme = uri.getScheme();
            if (url.startsWith("hupu") || url.startsWith("kanqiu")) {
                if (scheme != null) {
                    handleScheme(scheme, url);
                }
            } else if (scheme.equals("http") || scheme.equals("https")) {
                BrowserActivity.startActivity(getContext(), url);
            }*/
            return true;//true跳转已经处理，false往外分发，如浏览器
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (callBack != null) {
                callBack.onFinish();
            }
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            if (callBack != null) {
                callBack.onError();
            }
        }
    }

    private void handleScheme(String scheme, String url) {
        if (scheme != null) {

        }
    }

    private MyWebViewCallBack callBack;
    public interface MyWebViewCallBack {
        void onFinish();
        void onError();
        void onTitle(String title);
    }
    public void setCallBack(MyWebViewCallBack callBack) {
        this.callBack = callBack;
    }
    /*public void loadUrl(String url) {
        System.out.println("loadUrl:" + url);
        setUA(-1);
        if (header == null) {
            header = new HashMap<>();
            header.put("Accept-Encoding", "gzip");
        }
        super.loadUrl(url, header);
    }*/

    @Override
    public void loadUrl(String url) {
        super.loadUrl(url);
    }
/*private void setUA(int i) {
        if (this.basicUA != null) {
            getSettings().setUserAgentString(this.basicUA + " isp/" + i + " network/" + i);
        }
    }*/

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnScrollChangedCallback != null) {
            mOnScrollChangedCallback.onScroll(l - oldl, t - oldt);
        }
    }
    public interface OnScrollChangedCallback {
        void onScroll(int dx, int dy);
    }
    private OnScrollChangedCallback mOnScrollChangedCallback;
    public OnScrollChangedCallback getOnScrollChangedCallback() {
        return mOnScrollChangedCallback;
    }

    public void setOnScrollChangedCallback(
            final OnScrollChangedCallback onScrollChangedCallback) {
        mOnScrollChangedCallback = onScrollChangedCallback;
    }

    public class MyChromeClient extends WebChromeClient {//处理js代码

        @Override
        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            System.out.println("onConsoleMessage:" + consoleMessage.message() + ":" + consoleMessage.lineNumber());
            return true;
        }

        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            System.out.println("newProgress-->"+newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            System.out.println("title-->"+title);
            callBack.onTitle(title);
        }
    }
}
