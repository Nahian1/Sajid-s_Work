/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.viewholders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.pojo.ProjectsRsp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class VHProjectList {
    public ImageView projectIV;
    public TextView nameTV;
    public TextView dateTV;
    public TextView titleTV;
    public TextView priceTV;

    @SuppressLint("SetTextI18n")
    public void setProjectData(Context context, ProjectsRsp projectsRsp) {
        Glide.with(context)
                .load(projectsRsp.getCoverThumbnail())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_image_placeholder))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(projectIV);

        nameTV.setText(projectsRsp.getInitiatorsName());

        String dateInputPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        String dateOutputPattern = "dd MMM yyyy";

        SimpleDateFormat inputDateFormat = new SimpleDateFormat(dateInputPattern, Locale.getDefault());
        SimpleDateFormat outputDateFormat = new SimpleDateFormat(dateOutputPattern, Locale.getDefault());

        try {
            Date date = inputDateFormat.parse(projectsRsp.getCreatedAt());
            dateTV.setText(outputDateFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        titleTV.setText(projectsRsp.getTitle());
        priceTV.setText(context.getString(R.string.price) + " " + projectsRsp.getAccessPrice());
    }
}
