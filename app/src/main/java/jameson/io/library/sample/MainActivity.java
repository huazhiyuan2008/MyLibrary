package jameson.io.library.sample;

import android.app.Activity;
import android.os.Bundle;

import jameson.io.library.util.LogUtils;

/**
 * Created by jameson on 5/8/16.
 */
public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogUtils.d("");
    }
}
