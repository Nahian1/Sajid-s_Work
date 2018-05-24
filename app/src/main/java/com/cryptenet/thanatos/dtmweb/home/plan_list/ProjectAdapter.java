/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.plan_list;

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
        super(context, R.layout.plan_list_row, projects);
        this.context = context;
        this.projects = projects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.plan_list_row,parent,false);
        ImageView projectIV = convertView.findViewById(R.id.projectImg);
        TextView nameTV = convertView.findViewById(R.id.nameTV);
        TextView dateTV = convertView.findViewById(R.id.dateTV);
        TextView titleTV = convertView.findViewById(R.id.titleTV);
        TextView priceTV = convertView.findViewById(R.id.priceTV);
        ImageView seemoreIV = convertView.findViewById(R.id.seemoreImg);

        projectIV.setImageResource(projects.get(position).getImage1());
        nameTV.setText(projects.get(position).getName());
        dateTV.setText(projects.get(position).getDate());
        titleTV.setText(projects.get(position).getTitle());
        priceTV.setText(projects.get(position).getPrice());
        seemoreIV.setImageResource(projects.get(position).getImage2());
        count++;
        Log.e("student", "getView: "+count);

        return convertView;

    }
}
