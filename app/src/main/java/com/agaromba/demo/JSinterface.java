package com.agaromba.demo;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.JavascriptInterface;

/**
 * Created by Daniel on 12/02/2017.
 */

class JSinterface {

    public Context ctx =  null;



    @JavascriptInterface
    public void schedulemessage(long dateinMilis,  String Titulo, String Detalle)
    {
        try
        {
            Notify n = new Notify();
            n.ctx = ctx;
            n.Notificar(dateinMilis, Titulo, Detalle);
        }
        catch(Exception e)
        {

        }
    }

    @JavascriptInterface
    public void tryexit()
    {
        try
        {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
        catch(Exception e)
        {

        }
    }



}