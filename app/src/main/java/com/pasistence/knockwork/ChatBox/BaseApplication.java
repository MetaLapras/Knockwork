package com.pasistence.knockwork.ChatBox;

import android.app.Application;

<<<<<<< HEAD
=======
/*import com.sendbird.android.SendBird;*/
>>>>>>> 527df492c2baa18983919ba978ff74ae56a50f15

public class BaseApplication extends Application {
    private static final String APP_ID = "8198AB3F-0720-4858-BA20-BB0305646B02";
    public static final String VERSION = "1.0.1";

    @Override
    public void onCreate() {
        super.onCreate();

<<<<<<< HEAD
=======
        //SendBird.init(APP_ID, getApplicationContext());
>>>>>>> 527df492c2baa18983919ba978ff74ae56a50f15
    }
}
