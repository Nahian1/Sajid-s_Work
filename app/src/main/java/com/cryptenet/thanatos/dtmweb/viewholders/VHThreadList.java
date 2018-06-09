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
import com.cryptenet.thanatos.dtmweb.pojo.ThreadIdentity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class VHThreadList {
    public ImageView projectIV;
    public TextView titleTV;
    public TextView nameTV;
    public TextView dateTV;

    @SuppressLint("SetTextI18n")
    public void setProjectData(Context context, ThreadIdentity threadIdentity) {
        Glide.with(context)
                .load(threadIdentity.getPlanCover())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_image_placeholder))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(projectIV);

        nameTV.setText(threadIdentity.getInitiatorName());

        String dateInputPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        String dateOutputPattern = "dd MMM yyyy";

        SimpleDateFormat inputDateFormat = new SimpleDateFormat(dateInputPattern, Locale.getDefault());
        SimpleDateFormat outputDateFormat = new SimpleDateFormat(dateOutputPattern, Locale.getDefault());

        try {
            Date date = inputDateFormat.parse(threadIdentity.getLastActive());
            dateTV.append(outputDateFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
            dateTV.append(context.getString(R.string.none));
        }

        titleTV.setText(threadIdentity.getPlanTitle());
    }
}
