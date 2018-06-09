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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.events.ToEditPlanEvent;
import com.cryptenet.thanatos.dtmweb.pojo.Plans;
import com.cryptenet.thanatos.dtmweb.pojo.ProjectsRsp;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class VHInvestorProjectManageList {
    public ImageView editIV;
    public TextView dateTV;
    public TextView titleTV;
    public TextView priceTV;
    public TextView statusTV;

    @SuppressLint("SetTextI18n")
    public void setData(Context context, Plans plans, int reqType) {

        editIV.setVisibility(View.GONE);

        if (reqType == 1)
            statusTV.setVisibility(View.GONE);

        titleTV.setText(plans.getPlanTitle());
        priceTV.setText(context.getString(R.string.price) + " " + String.valueOf(plans.getPlanAccessPrice()));

        String dateInputPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        String dateOutputPattern = "dd MMM yyyy";

        SimpleDateFormat inputDateFormat = new SimpleDateFormat(dateInputPattern, Locale.getDefault());
        SimpleDateFormat outputDateFormat = new SimpleDateFormat(dateOutputPattern, Locale.getDefault());

        try {
            Date date = inputDateFormat.parse(plans.getCreatedAt());
            dateTV.setText(outputDateFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (plans.getIsApproved()) {
            statusTV.setText(context.getString(R.string.approved));
            statusTV.setBackground(context.getResources().getDrawable(R.drawable.tv_shape_apr));
        } else {
            statusTV.setText(context.getString(R.string.pending));
            statusTV.setBackground(context.getResources().getDrawable(R.drawable.tv_shape_pnd));
        }

        editIV.setVisibility(View.GONE);

    }
}
