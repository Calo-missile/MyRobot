package com.hsc.myrobot.MessageControl;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hsc.myrobot.R;

import java.util.List;

/**
 * Created by 15827 on 2017/3/30.
 */

public class TextAdapter extends ArrayAdapter<Data> {

    private int resorceId;
    private Context mContext;

    public TextAdapter(Context context,  int resource, List<Data> objects) {
        super(context, resource, objects);
        this.mContext = context;
        resorceId = resource;
    }

    @Override
    public View getView(int position, View convertView,ViewGroup parent) {
        Data data = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(resorceId, null);
            viewHolder = new ViewHolder();
            viewHolder.leftLayout = (RelativeLayout) convertView.findViewById(R.id.left_layout);
            viewHolder.rightLayout = (RelativeLayout) convertView.findViewById(R.id.right_layout);
            viewHolder.robotText = (TextView) convertView.findViewById(R.id.robot_text);
            viewHolder.userText = (TextView) convertView.findViewById(R.id.user_text);
            convertView.setTag(viewHolder);//存储view
        } else {
            viewHolder = (ViewHolder) convertView.getTag();//重新获取
        }
        if (data.getFalg() == Data.RECEIVE) {
            viewHolder.leftLayout.setVisibility(View.VISIBLE);
            viewHolder.rightLayout.setVisibility(View.GONE);
            viewHolder.robotText.setText(data.getContent());
            //viewHolder.robotText.setVisibility(View.VISIBLE);
        } else if (data.getFalg() == Data.SEND) {
            viewHolder.rightLayout.setVisibility(View.VISIBLE);
            viewHolder.leftLayout.setVisibility(View.GONE);
            viewHolder.userText.setText(data.getContent());
            //viewHolder.userText.setVisibility(View.VISIBLE);
        }
        return convertView;
    }

    private class ViewHolder {
        RelativeLayout leftLayout;
        RelativeLayout rightLayout;
        TextView robotText;
        TextView userText;
    }
}
