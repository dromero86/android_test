package com.agaromba.demo;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

public class MainActivity extends Activity {

    private browser navi   = new browser ();
    private WebView client = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_main);

        client = (WebView) findViewById(R.id.navigator);

        try
        {
            navi.build(this, client);
        }
        catch(Exception e)
        {
            Log.i("worker/WebView", "WebView navi error");
        }
    }


    @Override
    public void onBackPressed() {
        client.loadUrl("javascript:window.backView()");
    }
}
