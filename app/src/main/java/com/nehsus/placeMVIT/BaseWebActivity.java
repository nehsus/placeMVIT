package com.nehsus.placeMVIT;

/**
 * Created by Nehsus on 09/07/18.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class BaseWebActivity extends AppCompatActivity {

    private WebView webview;
    private ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baseweb);

        pd=new ProgressDialog(this);
        pd.setMessage("Placing...");

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String url = bundle.getString("url");

        go(url);

    }

    public void go(String url){
        webview=(WebView) findViewById(R.id.wv_internet);
        webview.loadUrl(url);
        WebSettings websettings=webview.getSettings();
        websettings.setUseWideViewPort(true);
        websettings.setLoadWithOverviewMode(true);
        websettings.setSupportZoom(true);
        websettings.setBuiltInZoomControls(true);
        webview.setWebViewClient(new WebViewClient(){


            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                pd.show();
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                pd.dismiss();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK&&webview.canGoBack()){
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "Refresh");
        menu.add(0, 0, 1, "Go back!");
        menu.add(0, 0, 2, "Go forward!");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getOrder()) {
            case 0:
                webview.reload();
                break;
            case 1:
                if(webview.canGoBack()){
                    webview.goBack();
                }
                break;
            case 2:
                if(webview.canGoForward()){
                    webview.goForward();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
