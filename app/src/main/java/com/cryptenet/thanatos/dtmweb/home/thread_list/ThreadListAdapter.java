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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.events.InitiatorThreadsEvent;
import com.cryptenet.thanatos.dtmweb.events.InvestorThreadsEvent;
import com.cryptenet.thanatos.dtmweb.pojo.ThreadIdentity;
import com.cryptenet.thanatos.dtmweb.viewholders.VHThreadList;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class ThreadListAdapter extends ArrayAdapter<ThreadIdentity> {
    private Context context;
    private List<ThreadIdentity> threadIdentities;
    private int reqType;
    private LayoutInflater inflater;
//    private ItemClickListener itemClickListener;

    public ThreadListAdapter(@NonNull Context context, List<ThreadIdentity> threadIdentities, int reqType) {
        super(context, R.layout.thread_project_list_row, threadIdentities);
        this.context = context;
        this.threadIdentities = threadIdentities;
        this.reqType = reqType;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        VHThreadList vhThreadList;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.thread_list_row, parent, false);

            vhThreadList = new VHThreadList();

            vhThreadList.projectIV = convertView.findViewById(R.id.demoIV);
            vhThreadList.titleTV = convertView.findViewById(R.id.titleTV);
            vhThreadList.nameTV = convertView.findViewById(R.id.tv_investor_name);
            vhThreadList.dateTV = convertView.findViewById(R.id.dateTV);

            convertView.setTag(vhThreadList);
        } else {
            vhThreadList = (VHThreadList) convertView.getTag();
        }

        vhThreadList.setProjectData(context, threadIdentities.get(position));

        convertView.setOnClickListener(view -> {
            if (reqType == 1)
                EventBus.getDefault().post(new InvestorThreadsEvent(threadIdentities.get(position).getId()));
            else
                EventBus.getDefault().post(new InitiatorThreadsEvent(threadIdentities.get(position).getPlan()));
        });

        return convertView;

    }
}
