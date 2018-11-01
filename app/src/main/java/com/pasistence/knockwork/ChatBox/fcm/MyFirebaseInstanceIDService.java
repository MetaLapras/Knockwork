package com.pasistence.knockwork.ChatBox.fcm;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
<<<<<<< HEAD
=======
/*import com.sendbird.android.SendBird;
import com.sendbird.android.SendBirdException;*/
>>>>>>> 527df492c2baa18983919ba978ff74ae56a50f15

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseIIDService";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG,"Refreshed Token: "+refreshedToken);
<<<<<<< HEAD
     //   sendRegistrationToServer(refreshedToken);
    }

//    private void sendRegistrationToServer(String token){
//        SendBird.registerPushTokenForCurrentUser(token, new SendBird.RegisterPushTokenWithStatusHandler() {
//            @Override
//            public void onRegistered(SendBird.PushTokenRegistrationStatus pushTokenRegistrationStatus, SendBirdException e) {
//                if(e != null){
//                    Toast.makeText(MyFirebaseInstanceIDService.this, "" + e.getCode() + ":" + e.getMessage(), Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                if(pushTokenRegistrationStatus == SendBird.PushTokenRegistrationStatus.PENDING){
//                    Toast.makeText(MyFirebaseInstanceIDService.this, "Connection required to register push token.", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
=======
        //sendRegistrationToServer(refreshedToken);
    }

   /* private void sendRegistrationToServer(String token){
        SendBird.registerPushTokenForCurrentUser(token, new SendBird.RegisterPushTokenWithStatusHandler() {
            @Override
            public void onRegistered(SendBird.PushTokenRegistrationStatus pushTokenRegistrationStatus, SendBirdException e) {
                if(e != null){
                    Toast.makeText(MyFirebaseInstanceIDService.this, "" + e.getCode() + ":" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    return;
                }

                if(pushTokenRegistrationStatus == SendBird.PushTokenRegistrationStatus.PENDING){
                    Toast.makeText(MyFirebaseInstanceIDService.this, "Connection required to register push token.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }*/
>>>>>>> 527df492c2baa18983919ba978ff74ae56a50f15
}
