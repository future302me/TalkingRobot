package com.gui.royal.youtalking.util;

/**
 * Created by Jeremy on 2015/5/27.
 */
public interface HttpCallbackListener {

    void onFinish(String response);
    void onError(Exception e);
}
