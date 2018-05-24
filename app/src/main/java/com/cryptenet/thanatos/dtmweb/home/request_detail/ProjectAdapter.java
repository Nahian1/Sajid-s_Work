/*
 * Copyright (c) 2018.
 * Development Courtesy: Cryptenet Ltd.
 * Developer Credit: Alamgir Hossain, Nabil Shawkat
 * This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.request_detail;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cryptenet.thanatos.dtmweb.R;

import java.util.List;

/**
 * Created by Mobile App on 2/9/2018.
 */

public class ProjectAdapter extends ArrayAdapter<Project> {
    private Context context;
    private List<Project> projects;
    private int count = 0;
    public ProjectAdapter(@NonNull Context context, List<Project> projects) {
        super(context, R.layout.request_detail_list_row, projects);
        this.context = context;
        this.projects = projects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.request_detail_list_row,parent,false);
        TextView titleTV = convertView.findViewById(R.id.titleTV);
        TextView priceTV = convertView.findViewById(R.id.priceTV);
        TextView dateTV = convertView.findViewById(R.id.dateTV);
        TextView statusTV = convertView.findViewById(R.id.statusTV);

        titleTV.setText(projects.get(position).getTitle());
        priceTV.setText(projects.get(position).getPrice());
        dateTV.setText(projects.get(position).getDate());
        if(projects.get(position).getStatus() == 1){
            statusTV.setText("Approved");
            statusTV.setBackgroundColor(Color.GREEN);
        }
        else{
            statusTV.setText("Pending");
        }
        //statusTV.setText(projects.get(position).getStatus());
        count++;
        Log.e("project", "getView: "+count);

        return convertView;

    }
}
