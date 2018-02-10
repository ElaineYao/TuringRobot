package com.elainedv.turingrobot;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import Utils.Constants;

/**
 * Created by Elaine on 18/2/9.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    Context mContext;
    List<Message> mMessages;

    public MyAdapter(Context context, List<Message> messages){
        mContext=context;
        mMessages=messages;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Message msg=mMessages.get(position);
        if(msg.getType()== Constants.TYPE_LEFT){
            holder.left_layout.setVisibility(View.VISIBLE);
            holder.right_layout.setVisibility(View.GONE);
            holder.left_tv.setText(msg.getContent());
        }
        if(msg.getType()== Constants.TYPE_RIGHT){
            holder.right_layout.setVisibility(View.VISIBLE);
            holder.left_layout.setVisibility(View.GONE);
            holder.right_tv.setText(msg.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout left_layout,right_layout;
        TextView left_tv,right_tv;


        public ViewHolder(View itemView) {
            super(itemView);
            left_layout=(LinearLayout)itemView.findViewById(R.id.left_layout);
            right_layout=(LinearLayout)itemView.findViewById(R.id.right_layout);
            left_tv=(TextView)itemView.findViewById(R.id.left_msg);
            right_tv=(TextView)itemView.findViewById(R.id.right_msg);
        }
    }
}
