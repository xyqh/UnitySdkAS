package com.DefaultCompany.SdkDemo.wxapi;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.unity3d.player.UnityPlayer;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    IWXAPI wxapi = null;
    public String APPID = "wx709390eb635c5a74";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(wxapi == null){
            wxapi = WXAPIFactory.createWXAPI(this, APPID);
        }
        wxapi.registerApp(APPID);
        wxapi.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        if (baseResp.getType() == 1){
            // 请求登录
            if (baseResp.errCode == BaseResp.ErrCode.ERR_OK){
                // 用户同意
                UnityPlayer.UnitySendMessage("WXLoginObject", "WXLoginCallBack", ((SendAuth.Resp)baseResp).code);
                Toast.makeText(this, "用户同意", Toast.LENGTH_SHORT).show();
            }
            else if(baseResp.errCode == BaseResp.ErrCode.ERR_USER_CANCEL){
                // 用户取消
                UnityPlayer.UnitySendMessage("WXLoginObject", "WXLoginCallBack", "用户取消");
                Toast.makeText(this, "用户取消", Toast.LENGTH_SHORT).show();
            }
            else if(baseResp.errCode == BaseResp.ErrCode.ERR_AUTH_DENIED){
                // 用户取消
                UnityPlayer.UnitySendMessage("WXLoginObject", "WXLoginCallBack", "用户拒绝");
                Toast.makeText(this, "用户拒绝", Toast.LENGTH_SHORT).show();
            }else{
                // 用户取消
                UnityPlayer.UnitySendMessage("WXLoginObject", "WXLoginCallBack", "其他错误");
                Toast.makeText(this, "其他错误", Toast.LENGTH_SHORT).show();
            }
        }
        else if(baseResp.getType() == 2){

        }

        finish();
    }
}