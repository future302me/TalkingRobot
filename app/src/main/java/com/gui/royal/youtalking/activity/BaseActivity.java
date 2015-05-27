package com.gui.royal.youtalking.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.gui.royal.youtalking.util.ActivityCollector;

/**
 * Created by Jeremy on 2015/5/27.
 */
public class BaseActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
