package com.mj.courseraprw3.GCM;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by leyenda on 10/26/16.
 */

public class NotificationIDTokenService extends FirebaseInstanceIdService {

    private static final  String TAG = "FIREBASE TOKEN";

    @Override
    public void onTokenRefresh() {
        //super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        enviarTokenRegistro("My Service TOKEN: " + token);
    }

    private void enviarTokenRegistro(String token){
        Log.d(TAG, token);
    }
}
