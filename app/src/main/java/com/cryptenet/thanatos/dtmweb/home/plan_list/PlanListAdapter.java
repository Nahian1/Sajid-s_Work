/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.plan_list;

import android.annotation.SuppressLint;
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
import com.cryptenet.thanatos.dtmweb.pojo.ProjectsRsp;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class PlanListAdapter extends ArrayAdapter<ProjectsRsp> {
    private Context context;
    private List<ProjectsRsp> projects;
    private int count = 0;
//    private ItemClickListener itemClickListener;

    public PlanListAdapter(@NonNull Context context, List<ProjectsRsp> projects) {
        super(context, R.layout.plan_list_row, projects);
        this.context = context;
        this.projects = projects;
//        itemClickListener = (ItemClickListener) context;
    }

    public void updateList(List<ProjectsRsp> projs) {
        this.projects.clear();
        if (projs != null)
            this.projects.addAll(projs);
//        this.projects = projs;
        this.notifyDataSetChanged();
    }

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.plan_list_row, parent, false);
        ImageView projectIV = convertView.findViewById(R.id.projectImg);
        TextView nameTV = convertView.findViewById(R.id.nameTV);
        TextView dateTV = convertView.findViewById(R.id.dateTV);
        TextView titleTV = convertView.findViewById(R.id.titleTV);
        TextView priceTV = convertView.findViewById(R.id.priceTV);

        convertView.findViewById(R.id.viewProject).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                itemClickListener.onItemClick(position);

                EventBus.getDefault().post(String.valueOf(position));

            }
        });
//        ImageView seemoreIV = convertView.findViewById(R.id.seemoreImg);

        Glide.with(context)
                .load(projects.get(position).getCoverThumbnail())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_image_placeholder))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(projectIV);

        nameTV.setText(projects.get(position).getInitiatorsName());

        String dateInputPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        String dateOutputPattern = "dd MMM yyyy";

        SimpleDateFormat inputDateFormat = new SimpleDateFormat(dateInputPattern, Locale.getDefault());
        SimpleDateFormat outputDateFormat = new SimpleDateFormat(dateOutputPattern, Locale.getDefault());

        try {
            Date date = inputDateFormat.parse(projects.get(position).getCreatedAt());
            dateTV.setText(outputDateFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        titleTV.setText(projects.get(position).getTitle());
        priceTV.setText(context.getString(R.string.price) + " " + projects.get(position).getAccessPrice());
        count++;
        Log.e("student", "getView: " + count);

        return convertView;

    }

//    public interface ItemClickListener {
//
//        void onItemClick(int position);
//    }
}
