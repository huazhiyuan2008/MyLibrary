package jameson.io.library.sample;

import android.app.Application;

import jameson.io.library.util.LogUtils;

/**
 * Created by jameson on 5/8/16.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.d("");
    }
}
