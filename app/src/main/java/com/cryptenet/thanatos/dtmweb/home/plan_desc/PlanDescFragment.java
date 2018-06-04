/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.plan_desc;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.base.BaseFragment;
import com.cryptenet.thanatos.dtmweb.events.PlanDetailsRequestEvent;
import com.cryptenet.thanatos.dtmweb.events.ShowPlanDetailsEvent;
import com.cryptenet.thanatos.dtmweb.home.HomeActivity;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.PlanDescFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.ProjectsDetailed;
import com.cryptenet.thanatos.dtmweb.utils.ProgressDialogHelper;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PlanDescFragment extends BaseFragment<PlanDescFragmentContract.Presenter>
        implements PlanDescFragmentContract.View {
    public static final String TAG = TagProvider.getDebugTag(PlanDescFragment.class);
    @BindView(R.id.textTitle)
    TextView textTitle;
    @BindView(R.id.textDatePrice)
    TextView textDatePrice;
    @BindView(R.id.demoImg)
    ImageView demoImg;
    @BindView(R.id.profilepic)
    ImageView profilepic;
    @BindView(R.id.textViewName)
    TextView textViewName;
    @BindView(R.id.textViewType)
    TextView textViewType;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.textShortDesc)
    TextView textShortDesc;
    @BindView(R.id.textLongDesc)
    TextView textLongDesc;
    @BindView(R.id.ic_init_conversation)
    ImageView icInitConversation;
    @BindView(R.id.textViewFile)
    TextView textViewFile;
    @BindView(R.id.layoutBankSection)
    LinearLayout layoutBankSection;

    //    @BindView(R.id.profilepic)
//    ImageView profileIV;
    @BindView(R.id.textBankAccName)
    TextView textBankAccName;
    @BindView(R.id.textBankAccNo)
    TextView textBankAccNo;
    @BindView(R.id.textBankName)
    TextView textBankName;
    @BindView(R.id.amountToBePaid)
    TextView amountToBePaid;

    private int projectId, type;
    private ProjectsDetailed projectsDetailed;
    private String fileUrl;

    public PlanDescFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertView = inflater.inflate(R.layout.fragment_plan_desc, container, false);

        ((HomeActivity) getActivity()).hideSearchBar(true);

        projectId = getArguments().getInt("project_id");
        type = getArguments().getInt("type");

        viewUnbinder = ButterKnife.bind(this, convertView);

        return convertView;
    }

    @OnClick(R.id.buttonRequestDetails)
    public void buttonRequestDetails(View view) {

        if (type >= 20) {
            showMessage("Log in as Investor!");
        } else {
            EventBus.getDefault().post(new PlanDetailsRequestEvent(projectsDetailed));
        }
    }

    @SuppressLint("SetTextI18n")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onShowPlanDetailsEvent(ShowPlanDetailsEvent event) {

        ProgressDialogHelper.hideProgress();

        projectsDetailed = event.detailed;

        if (event.detailed.getId() == null) {
            return;
        }

        if (textTitle != null) textTitle.setText(event.detailed.getTitle());

        if (textViewName != null) textViewName.setText(event.detailed.getInitiatorsName());
        if (address != null) address.setText(event.detailed.getInitiatorAddress());
        if (textShortDesc != null) textShortDesc.setText(event.detailed.getShortDescription());

        //commented out for later use
        if (demoImg != null)
            Glide.with(activityContext)
                    .load(event.detailed.getCover())
                    .apply(RequestOptions.placeholderOf(R.drawable.imgdemo))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(demoImg);

        if (profilepic != null)
            Glide.with(activityContext)
                    .load(event.detailed.getInitiatorImage())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_profile_blue))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .apply(RequestOptions.circleCropTransform())
                    .into(profilepic);

        if (type == 11 || type == 21) {
            String dateInputPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
            String dateOutputPattern = "dd MMM yyyy";

            SimpleDateFormat inputDateFormat = new SimpleDateFormat(dateInputPattern, Locale.getDefault());
            SimpleDateFormat outputDateFormat = new SimpleDateFormat(dateOutputPattern, Locale.getDefault());

            try {
                Date date = inputDateFormat.parse(event.detailed.getCreatedAt());
                textDatePrice.setText(outputDateFormat.format(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            layoutBankSection.setVisibility(View.GONE);
            textLongDesc.setVisibility(View.VISIBLE);
            textLongDesc.setText(event.detailed.getLongDescription());
            textViewFile.setVisibility(View.VISIBLE);

            fileUrl = event.detailed.getUploadedFile();

        } else if (textDatePrice != null) {

            layoutBankSection.setVisibility(View.VISIBLE);

            textViewType.setText(getString(R.string.acc_type_initiator));
            if (textBankAccName != null)
                textBankAccName.setText(event.detailed.getBankAccountName());
            if (textBankAccNo != null) textBankAccNo.setText(event.detailed.getBankAccountNumber());
            if (textBankName != null) textBankName.setText(event.detailed.getBankName());
            if (amountToBePaid != null) amountToBePaid.setText(event.detailed.getAccessPrice());

            textDatePrice.setText("Price: " + event.detailed.getMinimumInvestmentCost() + " - " + event.detailed.getMaximumInvestmentCost());
        }

        if (type < 20) {
            icInitConversation.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(activityContext, message, Toast.LENGTH_LONG).show();

    }

    @Override
    public void printLog(String TAG, String message) {
        Log.d(TAG, "printLog: " + message);
    }

    @Override
    public void restoreState(Bundle savedState) {


    }

    @OnClick({R.id.ic_init_conversation, R.id.textViewFile})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ic_init_conversation:
                presenter.getThreadId(activityContext, projectId);
                break;
            case R.id.textViewFile:
                if (fileUrl != null)
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(fileUrl)));
                else
                    showMessage("Couldn't open file!");
                break;
        }
    }


    @Override
    public void onResume() {
        super.onResume();

        presenter.attachView(this);

        ProgressDialogHelper.init(getActivity()).showProgress();

        if (type == 10 || type == 20) {
            presenter.getShortDetails(activityContext, projectId);
        } else if (type == 11 || type == 21) {
            presenter.getLongDetails(activityContext, projectId);
        }

//        if (type == 1) { //long
//            presenter.getLongDetails(activityContext, projectId);
//        } else { //short
//            presenter.getShortDetails(activityContext, projectId);
//        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    @Override
    public void onStop() {
        presenter.detachView();
        EventBus.getDefault().unregister(this);

        super.onStop();
    }
}
