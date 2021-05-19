package com.agaromba.demo;


import android.app.Activity;
import android.os.Environment;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;

import java.io.File;

@SuppressWarnings("unused")
public class browser {

    private Activity contexto = null;
    private String url = "file:///android_asset/index.html";


    public void build(Activity that, WebView client)
    {
        Log.i("browser", "build");

        contexto = that;

        JSinterface jsi = new JSinterface();

        jsi.ctx = contexto;

        client.addJavascriptInterface(jsi, "Android");

        try
        {
            client.setWebChromeClient(new WebChromeClient() {

                @Override
                public void onExceededDatabaseQuota(String url, String databaseIdentifier, long currentQuota, long estimatedSize, long totalUsedQuota, WebStorage.QuotaUpdater quotaUpdater) {
                    quotaUpdater.updateQuota(5 * 1024 * 1024);
                }

                @Override
                public boolean onConsoleMessage(ConsoleMessage cm)
                {
                    Log.d("CONTENT", String.format("%s @ %d: %s",  cm.message(), cm.lineNumber(), cm.sourceId()));
                    return true;
                }


            });

        }
        catch(Exception e)
        {
            Log.i("browser", "CanaryChrome error");
        }


        try
        {
            client.setVerticalScrollBarEnabled(true);
            client.setHorizontalScrollBarEnabled(false);
            client.loadUrl(url);
        }
        catch(Exception e)
        {
            Log.i("browser/client", e.getMessage());
        }

        Log.i("folderpath", Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator);

        try
        {
            WebSettings webSettings = client.getSettings();

            webSettings.setAllowFileAccessFromFileURLs(true);
            webSettings.setAllowUniversalAccessFromFileURLs(true);

            webSettings.setJavaScriptEnabled		(true);
            webSettings.setLoadsImagesAutomatically	(true);
            webSettings.setDomStorageEnabled(true);
            webSettings.setDatabaseEnabled(true);
            webSettings.setUseWideViewPort(true);
            webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
            webSettings.setDatabasePath(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator);
        }
        catch(Exception e)
        {
            Log.i("browser/webSettings", e.getMessage());
        }
    }
}
