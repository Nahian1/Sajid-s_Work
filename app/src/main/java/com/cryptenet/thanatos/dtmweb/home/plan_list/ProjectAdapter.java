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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.pojo.Projects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by Mobile App on 2/9/2018.
 */

public class ProjectAdapter extends ArrayAdapter<Projects> {
    private Context context;
    private List<Projects> projects;
    private int count = 0;
    public ProjectAdapter(@NonNull Context context, List<Projects> projects) {
        super(context, R.layout.plan_list_row, projects);
        this.context = context;
        this.projects = projects;
    }

    public void updateList(List<Projects> projs){
        this.projects.clear();
        if (projs != null)
            this.projects.addAll(projs);
        this.notifyDataSetChanged();
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
//        ImageView seemoreIV = convertView.findViewById(R.id.seemoreImg);

        Glide.with(context)
                .load(projects.get(position).getCoverThumbnail())
                .apply(RequestOptions.placeholderOf(R.drawable.ppimg))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(projectIV);

        nameTV.setText(projects.get(position).getInitiatorsName());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());

        try {
            dateTV.setText(dateFormat.format(dateFormat.parse(projects.get(position).getCreatedAt())));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        titleTV.setText(projects.get(position).getTitle());
        priceTV.setText(projects.get(position).getAccessPrice());
//        seemoreIV.setImageResource(R.drawable.seemore);
        count++;
        Log.e("student", "getView: "+count);

        return convertView;

    }
}
