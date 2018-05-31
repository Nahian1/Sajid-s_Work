/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.form;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.base.BaseFragment;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.FormFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.ProjectsDetailed;
import com.cryptenet.thanatos.dtmweb.pojo.Transaction;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class FormFragment extends BaseFragment<FormFragmentContract.Presenter>
        implements FormFragmentContract.View {
    public static final String TAG = TagProvider.getDebugTag(FormFragment.class);

    Unbinder unbinder;
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
    @BindView(R.id.bankNameET)
    EditText bankNameET;
    @BindView(R.id.bankAccNameET)
    EditText bankAccNameET;
    @BindView(R.id.bankAccNumET)
    EditText bankAccNumET;
    @BindView(R.id.transIdET)
    EditText transIdET;
    @BindView(R.id.add_report)
    EditText addReport;


    private ProjectsDetailed details;


    public FormFragment() {
        // Required empty public constructor
    }


    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_transaction_form, container, false);

        unbinder = ButterKnife.bind(this, view);

        details = new Gson().fromJson(getArguments().getString("project_details"), ProjectsDetailed.class);

        textViewTitle.setText(details.getTitle());
        price.setText("Price: " +
                String.format("%.2f", (Double.parseDouble(details.getMinimumInvestmentCost()) - Double.parseDouble(details.getMaximumInvestmentCost()))));
//        date.setText(details.getTitle());
//        status.setText(details.getTitle());

        Glide.with(activityContext)
                .load(details.getInitiatorImage())
                .apply(RequestOptions.placeholderOf(R.drawable.img_initiator_profile_picture))
                .apply(RequestOptions.circleCropTransform())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(profilepic);

        textViewName.setText(details.getInitiatorsName());
//        textViewType.setText(details.getInitiator());
        address.setText(details.getInitiatorAddress());

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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.cancelBtn, R.id.submitBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cancelBtn:

                getFragmentManager().popBackStackImmediate();
                break;

            case R.id.submitBtn:

                String bankName = bankNameET.getText().toString().trim();
                String bankAccName = bankAccNameET.getText().toString().trim();
                String bankAccNo = bankAccNumET.getText().toString().trim();
                String transId = transIdET.getText().toString().trim();
                String note = addReport.getText().toString().trim();

                if (!bankName.isEmpty() && !bankAccName.isEmpty() && !bankAccNo.isEmpty() && !transId.isEmpty()) {

                    Transaction transaction = new Transaction();
                    transaction.setBankName(bankName);
                    transaction.setBankAccountName(bankAccName);
                    transaction.setBankAccountNumber(bankAccNo);
                    transaction.setTransactionId(transId);
                    transaction.setNote(note);
                    transaction.setProjectsDetailed(details); //adding project details

                    presenter.submitTransactionData(transaction, activityContext);

                } else {

                    showMessage("Please fill up all mandatory fields!");
                }

                break;

        }
    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onTransactionSuccessEvent(TransactionSuccessEvent event) {
//
//        //setting project details for use in transaction details fragment
//        Transaction transaction = event.transaction;
//        transaction.setProjectsDetailed(details);
//
//        EventBus.getDefault().post(new ToTransactionFragmentEvent(transaction));
//
//    }
}
