package com.gui.royal.youtalking.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.gui.royal.youtalking.db.ContentDB;
import com.gui.royal.youtalking.model.Msg;
import com.gui.royal.youtalking.R;
import com.gui.royal.youtalking.model.SendTexts;
import com.gui.royal.youtalking.util.HttpCallbackListener;
import com.gui.royal.youtalking.util.HttpUtil;
import com.gui.royal.youtalking.util.Utility;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private ListView msgListView;

    private EditText inputText;

    private Button send;

    private MsgAdapter adapter;

    private List<Msg> msgList = new ArrayList<>();

    private static final int SHOW_RESPONSE = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        adapter =  new MsgAdapter (MainActivity.this, R.layout.msg_item, msgList);
        inputText = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send);
        msgListView = (ListView) findViewById(R.id.msg_list_view);
        Button forceLogout = (Button) findViewById(R.id.btn_logout);
        msgListView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送一条消息
                String info = inputText.getText().toString();
                if (!"".equals(info)) {
                    Msg msg = new Msg(info,Msg.TYPE_SENT);
                   /** 保存发送出去的消息
                    ContentDB db = ContentDB.getInstance(MainActivity.this);
                    SendTexts sendTexts = new SendTexts();
                    sendTexts.setContent(content);
                    db.saveSendTexts(sendTexts);
                    */
                    refresh(msg);

                }
                //生成访问地址
                info = info.replaceAll(" ","");
                String address = "http://www.tuling123.com/openapi/api?key=bd35d0e4054c6a4c06059f1a454bd2d3&info=" + info;
                Log.d("网络地址",address);

                //将消息发送给服务器消息

                HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
                    @Override
                    public void onFinish(final String response) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String content;
                                content = Utility.handleTextsResponse(MainActivity.this, response);
                                Msg msg = new Msg(content, Msg.TYPE_RECEIVED);
                                refresh(msg);
                            }
                        });
                    }
                @Override
                    public void onError(Exception e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Msg msg = new Msg("网络连接失败",Msg.TYPE_RECEIVED);
                                refresh(msg);
                            }
                        });
                    }
                });
            }
/** Handle处理异步操作
            private Handler handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    switch (msg.what) {
                        case SHOW_RESPONSE:
                            String content;
                            content = Utility.handleTextsResponse(MainActivity.this, msg.obj.toString());
                            Msg message = new Msg(content, Msg.TYPE_RECEIVED);
                            refresh(message);
                            break;
                        default:
                            break;
                    }
                }
            };
*/

            private void refresh(Msg msg) {
                msgList.add(msg);
                adapter.notifyDataSetChanged();//当有新消息是，刷新ListView中的显示
                msgListView.setSelection(msgList.size());//将ListView定位到最后一行
                inputText.setText("");//清空输入框的内容
            }
        });

        forceLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.gui.royal.youtalking.FORCE_LOGOUT");
                sendBroadcast(intent);
            }
        });

    }



}
