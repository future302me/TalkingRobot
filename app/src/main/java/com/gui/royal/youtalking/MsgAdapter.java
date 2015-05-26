package com.gui.royal.youtalking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Jeremy on 2015/5/11.
 */
public class MsgAdapter extends ArrayAdapter<Msg> {

    private int resourceId;

    public MsgAdapter(Context context, int textViewResourceId, List<Msg> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Msg msg = getItem(position); //获取当前的消息实例
        View view;
        ViewHolder viewHolder;
        /*
        convertView为NULL时，创建一个View填充到ListView
         */
       if (convertView == null){
           view = LayoutInflater.from(getContext()).inflate(resourceId,null);
           viewHolder = new ViewHolder();
           viewHolder.leftLayout = (LinearLayout)view.findViewById(R.id.left_layout);
           viewHolder.rightLayout = (LinearLayout)view.findViewById(R.id.right_layout);
           viewHolder.leftMsg = (TextView) view.findViewById(R.id.left_msg);
           viewHolder.rightMsg = (TextView) view.findViewById(R.id.right_msg);
           view.setTag(viewHolder);
       } else {
           view = convertView;
           viewHolder = (ViewHolder) view.getTag();
       }
        if (msg.getType() == Msg.TYPE_RECEIVED) {
            //如果是收到的消息，怎显示左边的消息布局，将右边的消息布局隐藏
            viewHolder.leftLayout.setVisibility(view.VISIBLE);
            viewHolder.rightLayout.setVisibility(view.GONE);
            viewHolder.leftMsg.setText(msg.getContent());
        } else {
            //如果是发送的消息，则隐藏左边的布局，显示右边的布局
            viewHolder.leftLayout.setVisibility(view.GONE);
            viewHolder.rightLayout.setVisibility(view.VISIBLE);
            viewHolder.rightMsg.setText(msg.getContent());
        }
        return view;
    }
    /*
    Holder for wighit
     */
    class ViewHolder {
        LinearLayout leftLayout;
        LinearLayout rightLayout;
        TextView leftMsg;
        TextView rightMsg;

    }
}
