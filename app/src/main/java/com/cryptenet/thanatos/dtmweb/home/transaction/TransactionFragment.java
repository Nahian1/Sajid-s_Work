/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.transaction;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.base.BaseFragment;
import com.cryptenet.thanatos.dtmweb.events.ReturnToHomeEvent;
import com.cryptenet.thanatos.dtmweb.home.HomeActivity;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.TransactionFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.ProjectsDetailed;
import com.cryptenet.thanatos.dtmweb.pojo.Transaction;
import com.cryptenet.thanatos.dtmweb.utils.JsonKeys;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class TransactionFragment extends BaseFragment<TransactionFragmentContract.Presenter>
        implements TransactionFragmentContract.View {
//    public static final String TAG = TagProvider.getDebugTag(TransactionFragment.class);

    @BindView(R.id.textViewTitle)
    TextView textViewTitle;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.status)
    TextView status;
    @BindView(R.id.profilepic)
    ImageView profilepic;
    @BindView(R.id.textViewName)
    TextView textViewName;
    @BindView(R.id.textViewType)
    TextView textViewType;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.textBankName)
    TextView textBankName;
    @BindView(R.id.textAccountName)
    TextView textAccountName;
    @BindView(R.id.textAccountNumber)
    TextView textAccountNumber;
    @BindView(R.id.textTransactionId)
    TextView textTransactionId;
    @BindView(R.id.noteTV)
    TextView noteTV;
    @BindView(R.id.doneBtn)
    Button doneBtn;

    private Transaction transactionData;
    private ProjectsDetailed projectData;
    private int transactionId;

    public TransactionFragment() {
        // Required empty public constructor
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transaction_detail, container, false);

        ((HomeActivity) getActivity()).setToolBarTitle(getString(R.string.trans_summary));


        viewUnbinder = ButterKnife.bind(this, view);

        transactionId = getArguments().getInt("transaction_id");

        transactionData = new Gson().fromJson(getArguments().getString(JsonKeys.TRANSACTION_DETAILS), Transaction.class);

        projectData = transactionData.getProjectsDetailed();

        textViewTitle.setText(projectData.getTitle());

        price.setText(getString(R.string.price) + " " +
                String.format("%.2f", (Double.parseDouble(projectData.getMinimumInvestmentCost())
                        - Double.parseDouble(projectData.getMaximumInvestmentCost()))));

        String dateInputPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        String dateOutputPattern = "dd MMM yyyy";

        SimpleDateFormat inputDateFormat = new SimpleDateFormat(dateInputPattern, Locale.getDefault());
        SimpleDateFormat outputDateFormat = new SimpleDateFormat(dateOutputPattern, Locale.getDefault());

        try {
            Date dateF = inputDateFormat.parse(projectData.getCreatedAt());
            date.setText(outputDateFormat.format(dateF));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Glide.with(activityContext)
                .load(projectData.getInitiatorImage())
                .apply(RequestOptions.placeholderOf(R.drawable.ic_profile_blue))
                .apply(RequestOptions.circleCropTransform())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(profilepic);

        textViewName.setText(projectData.getInitiatorsName());
        address.setText(projectData.getInitiatorAddress());

        textBankName.setText(transactionData.getBankName());
        textAccountName.setText(transactionData.getBankAccountName());
        textAccountNumber.setText(transactionData.getBankAccountNumber());
        textTransactionId.setText(transactionData.getTransactionId());
        noteTV.setText(transactionData.getNote());

        return view;
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

    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @OnClick(R.id.doneBtn)
    public void onViewClicked() {
        EventBus.getDefault().post(new ReturnToHomeEvent());
    }
}
