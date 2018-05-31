/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
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
import com.cryptenet.thanatos.dtmweb.pojo.ThreadIdentity;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class ThreadListAdapter extends ArrayAdapter<ThreadIdentity> {
    private Context context;
    private List<ThreadIdentity> projects;
    private int count = 0;
//    private ItemClickListener itemClickListener;

    public ThreadListAdapter(@NonNull Context context, List<ThreadIdentity> projects) {
        super(context, R.layout.thread_project_list_row, projects);
        this.context = context;
        this.projects = projects;
//        itemClickListener = (ItemClickListener) context;
    }

    public void updateList(List<ThreadIdentity> projs) {
        this.projects.clear();
        if (projs != null)
            this.projects.addAll(projs);
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.thread_project_list_row, parent, false);
        ImageView projectIV = convertView.findViewById(R.id.demoIV);
        TextView titleTV = convertView.findViewById(R.id.titleTV);
        TextView nameTV = convertView.findViewById(R.id.nameTV);
        TextView dateTV = convertView.findViewById(R.id.dateTV);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                itemClickListener.onItemClick(position);

                EventBus.getDefault().post(new InitiatorThreadsEvent(projects.get(position).getId()));

            }
        });
//        ImageView seemoreIV = convertView.findViewById(R.id.seemoreImg);

        Glide.with(context)
                .load(projects.get(position).getPlanCover())
                .apply(RequestOptions.placeholderOf(R.drawable.ppimg))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(projectIV);

        nameTV.setText(projects.get(position).getInitiatorName());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());

        try {
            dateTV.setText(dateFormat.format(dateFormat.parse(projects.get(position).getCreatedAt())));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        titleTV.setText(projects.get(position).getPlanTitle());
        count++;
        Log.e("student", "getView: " + count);

        return convertView;

    }

//    public interface ItemClickListener {
//
//        void onItemClick(int position);
//    }
}
