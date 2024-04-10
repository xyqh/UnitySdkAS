package com.DefaultCompany.SdkDemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.unity3d.player.UnityPlayerActivity;

public class MainActivity extends com.unity3d.player.UnityPlayerActivity {

    IWXAPI wxapi = null;
    public String APPID = "wx709390eb635c5a74";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(wxapi == null){
            wxapi = WXAPIFactory.createWXAPI(this, APPID);
        }
        wxapi.registerApp(APPID);
    }

    public void Login(){
        Log.i("Info", "Login: Click");
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "unity sdk test";
        wxapi.sendReq(req);
    }
}