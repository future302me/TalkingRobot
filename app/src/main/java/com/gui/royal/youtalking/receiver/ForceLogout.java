package com.gui.royal.youtalking.receiver;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Window;
import android.view.WindowManager;

import com.gui.royal.youtalking.activity.LoginActivity;
import com.gui.royal.youtalking.util.ActivityCollector;

/**
 * Created by Jeremy on 2015/5/27.
 */
public class ForceLogout extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, Intent intent) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle("退出聊天");
        dialogBuilder.setMessage("你将退出当前对话，请重新登陆");
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCollector.finishAllActivities();//销毁所有活动
                Intent intent = new Intent(context, LoginActivity.class);//返回登陆页面
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        AlertDialog alertDialog = dialogBuilder.create();
        //设置AlertDialog类型
        alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        alertDialog.show();
    }
}
