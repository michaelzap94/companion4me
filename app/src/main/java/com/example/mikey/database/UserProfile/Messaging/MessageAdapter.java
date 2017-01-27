package com.example.mikey.database.UserProfile.Messaging;

import android.app.Activity;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mikey.database.R;
import com.sinch.android.rtc.messaging.Message;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MessageAdapter extends BaseAdapter {

    public static final int DIRECTION_INCOMING = 0;

    public static final int DIRECTION_OUTGOING = 1;
    public static final String KEY = "message";

    private List<Pair<Message, Integer>> mMessages;

    private SimpleDateFormat mFormatter;

    private LayoutInflater mInflater;

    /**
     *
     * @param activity
     */
    public MessageAdapter(Activity activity) {
        mInflater = activity.getLayoutInflater();
        mMessages = new ArrayList<Pair<Message, Integer>>();
        mFormatter = new SimpleDateFormat("HH:mm");
    }

    /**
     * Adds message to mMessages
     * @param message
     * @param direction
     */
    public void addMessage(Message message, int direction) {
        mMessages.add(new Pair(message, direction));
        notifyDataSetChanged();
    }

    /**
     * @Override
     * @return mMessages
     */
    public int getCount() {
        return mMessages.size();
    }

    /**
     * @Override
     * @param i
     * @return an object from mMessages
     */
    public Object getItem(int i) {
        return mMessages.get(i);
    }

    /**
     * @Override
     * @param i
     * @return
     */
    public long getItemId(int i) {
        return 0;
    }

    /**
     * @Override
     * @return
     */
    public int getViewTypeCount() {
        return 2;
    }

    /**
     * @Override
     * @param i
     * @return
     */
    public int getItemViewType(int i) {
        return mMessages.get(i).second;
    }

    /**
     * @Override
     * @param i
     * @param convertView
     * @param viewGroup
     * @return View convertView
     */
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        int direction = getItemViewType(i);

        if (convertView == null) {
            int res = 0;
            if (direction == DIRECTION_INCOMING) {
                res = R.layout.chat_me;
            } else if (direction == DIRECTION_OUTGOING) {
                res = R.layout.chat_friend;
            }
            convertView = mInflater.inflate(res, viewGroup, false);
        }

        Message message = mMessages.get(i).first;
        

        String name = message.getSenderId();

        TextView txtSender = (TextView) convertView.findViewById(R.id.Message_Sender);
        TextView txtMessage = (TextView) convertView.findViewById(R.id.Message);
        TextView txtDate = (TextView) convertView.findViewById(R.id.Message_Date);

        txtSender.setText(name);
        txtMessage.setText(message.getTextBody());
        txtDate.setText(mFormatter.format(message.getTimestamp()));

        return convertView;
    }
}
