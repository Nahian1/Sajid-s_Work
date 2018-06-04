package com.cryptenet.thanatos.dtmweb.home.thread_msg;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.pojo.message_model.Results;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;

import java.util.Collections;
import java.util.List;

public class MessagingAdapter extends RecyclerView.Adapter<MessagingAdapter.InitiatorThreadHolder> {
    private Context mContext;
    private List<Results> messageThreadModels;

    public MessagingAdapter(List<Results> messageThreadModels) {
        this.messageThreadModels = messageThreadModels;
        Collections.reverse(this.messageThreadModels);
    }

    @NonNull
    @Override
    public MessagingAdapter.InitiatorThreadHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.node_message_list, parent, false);
        InitiatorThreadHolder viewHolder = new InitiatorThreadHolder(view);
        return viewHolder;


    }

    public void setData(List<Results> data) {
        this.messageThreadModels = data;
        Collections.reverse(this.messageThreadModels);
    }

    @Override
    public void onBindViewHolder(@NonNull MessagingAdapter.InitiatorThreadHolder holder, int position) {
        int userID =  PreferenceManager.getDefaultSharedPreferences(mContext).getInt(ConstantProvider.SP_ID,0);
        // Toast.makeText(mContext, Integer.toString(userID), Toast.LENGTH_SHORT).show();
        // Toast.makeText(mContext, messageThreadModels[position].getSender().toString(), Toast.LENGTH_SHORT).show();



        if (userID == Integer.parseInt(messageThreadModels.get(position).getSender().toString())){

            //  Toast.makeText(mContext, "sender", Toast.LENGTH_SHORT).show();

            holder.receiver.setVisibility(View.GONE);
            holder.sender.setVisibility(View.VISIBLE);
            holder.sendMessage.setText(messageThreadModels.get(position).getText());
        }else {

            //Toast.makeText(mContext, "receiver", Toast.LENGTH_SHORT).show();
            holder.receiver.setVisibility(View.VISIBLE);
            holder.sender.setVisibility(View.GONE);

            Glide.with(mContext)
                    .load(messageThreadModels.get(position).getSender_picture())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_profile_blue))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(holder.icon);


            holder.message.setText(messageThreadModels.get(position).getText());

        }
//       if(messageThreadModels[position].getSender().toString().equalsIgnoreCase(Integer.toString(userID))){
//
//
//       }else {
//
//       }




    }

    @Override
    public int getItemCount() {
        return messageThreadModels.size();
    }

    public class InitiatorThreadHolder extends RecyclerView.ViewHolder {

        private ImageView icon;
        private TextView message,sendMessage;

        LinearLayout sender, receiver;


        public InitiatorThreadHolder(View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.icon);
            message = itemView.findViewById(R.id.message);
            sender = itemView.findViewById(R.id.sender);
            receiver = itemView.findViewById(R.id.receiver);
            sendMessage = itemView.findViewById(R.id.send_message);

        }
    }
}