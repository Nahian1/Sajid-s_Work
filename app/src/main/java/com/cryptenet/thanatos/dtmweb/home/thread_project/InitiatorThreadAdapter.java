/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.thread_project;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.events.InvestorThreadsEvent;
import com.cryptenet.thanatos.dtmweb.pojo.ThreadInv;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class InitiatorThreadAdapter extends RecyclerView.Adapter<InitiatorThreadAdapter.InitiatorThreadHolder> {
    private Context mContext;
    private List<ThreadInv> messageThreadModels;

    public InitiatorThreadAdapter(List<ThreadInv> messageThreadModels) {
        this.messageThreadModels = messageThreadModels;
    }

    public void setData(List<ThreadInv> data) {
        this.messageThreadModels = data;
    }

    @NonNull
    @Override
    public InitiatorThreadHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.node_thread_project_2, parent, false);
        InitiatorThreadHolder viewHolder = new InitiatorThreadHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InitiatorThreadHolder holder, int position) {

        Glide.with(mContext)
                .load(messageThreadModels.get(position).getInvestor_picture())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_profile_blue))
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.icon);

        holder.lastMessage.setText(messageThreadModels.get(position).getLast_text());
        holder.name.setText(messageThreadModels.get(position).getInvestor_name());

        String dateInputPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        String dateOutputPattern = "dd MMM yyyy";

        SimpleDateFormat inputDateFormat = new SimpleDateFormat(dateInputPattern, Locale.getDefault());
        SimpleDateFormat outputDateFormat = new SimpleDateFormat(dateOutputPattern, Locale.getDefault());

        try {
            Date dateF = inputDateFormat.parse(messageThreadModels.get(position).getLast_active());
            holder.date.setText(outputDateFormat.format(dateF));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        holder.itemView.setOnClickListener(view ->
                EventBus.getDefault().post(new InvestorThreadsEvent(Integer.parseInt(messageThreadModels.get(position).getId()))));


    }

    @Override
    public int getItemCount() {
        return messageThreadModels.size();
    }

    public class InitiatorThreadHolder extends RecyclerView.ViewHolder {

        private ImageView icon;
        private TextView lastMessage, name, date;


        public InitiatorThreadHolder(View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.iv_pp);
            name = itemView.findViewById(R.id.titleTV);
            lastMessage = itemView.findViewById(R.id.tv_investor_name);
            date = itemView.findViewById(R.id.dateTV);
        }
    }
}
