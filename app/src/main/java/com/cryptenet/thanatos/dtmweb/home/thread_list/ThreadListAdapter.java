/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.thread_list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.events.InitiatorThreadsEvent;
import com.cryptenet.thanatos.dtmweb.events.InvestorThreadsEvent;
import com.cryptenet.thanatos.dtmweb.pojo.ThreadIdentity;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ThreadListAdapter extends ArrayAdapter<ThreadIdentity> {
    private Context context;
    private List<ThreadIdentity> threadIdentities;
    private int count = 0;
    private int reqType;
//    private ItemClickListener itemClickListener;

    public ThreadListAdapter(@NonNull Context context, List<ThreadIdentity> threadIdentities, int reqType) {
        super(context, R.layout.thread_project_list_row, threadIdentities);
        this.context = context;
        this.threadIdentities = threadIdentities;
        this.reqType = reqType;
//        itemClickListener = (ItemClickListener) context;
    }

    public void updateList(List<ThreadIdentity> projs) {
        this.threadIdentities.clear();
        if (projs != null)
            this.threadIdentities.addAll(projs);
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.thread_list_row, parent, false);
        ImageView projectIV = convertView.findViewById(R.id.demoIV);
        TextView titleTV = convertView.findViewById(R.id.titleTV);
        TextView nameTV = convertView.findViewById(R.id.tv_investor_name);
        TextView dateTV = convertView.findViewById(R.id.dateTV);

        convertView.setOnClickListener(view -> {

//                itemClickListener.onItemClick(position);
            if (reqType == 1)
                EventBus.getDefault().post(new InvestorThreadsEvent(threadIdentities.get(position).getId()));
            else
                EventBus.getDefault().post(new InitiatorThreadsEvent(threadIdentities.get(position).getPlan()));

        });
//        ImageView seemoreIV = convertView.findViewById(R.id.seemoreImg);

        Glide.with(context)
                .load(threadIdentities.get(position).getPlanCover())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_image_placeholder))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(projectIV);

        nameTV.setText(threadIdentities.get(position).getInitiatorName());

        String dateInputPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        String dateOutputPattern = "dd MMM yyyy";

        SimpleDateFormat inputDateFormat = new SimpleDateFormat(dateInputPattern, Locale.getDefault());
        SimpleDateFormat outputDateFormat = new SimpleDateFormat(dateOutputPattern, Locale.getDefault());

        try {
            Date date = inputDateFormat.parse(threadIdentities.get(position).getLastActive());
            dateTV.append(outputDateFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
            dateTV.append(context.getString(R.string.none));
        }
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
//
//        try {
//            dateTV.setText(dateFormat.format(dateFormat.parse(threadIdentities.get(position).getCreatedAt())));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }

        titleTV.setText(threadIdentities.get(position).getPlanTitle());
        count++;
        Log.e("student", "getView: " + count);

        return convertView;

    }

//    public interface ItemClickListener {
//
//        void onItemClick(int position);
//    }
}
