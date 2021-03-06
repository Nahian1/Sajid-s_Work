/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.request_detail;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.base.BaseFragment;
import com.cryptenet.thanatos.dtmweb.events.BackToManageRequestEvent;
import com.cryptenet.thanatos.dtmweb.events.RequestDataReceiveEvent;
import com.cryptenet.thanatos.dtmweb.events.RequestFailureEvent;
import com.cryptenet.thanatos.dtmweb.home.HomeActivity;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.RequestDetailFragmentContract;
import com.cryptenet.thanatos.dtmweb.utils.ProgressDialogHelper;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;

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
import butterknife.Unbinder;


public class RequestDetailFragment extends BaseFragment<RequestDetailFragmentContract.Presenter>
        implements RequestDetailFragmentContract.View {
//    private static final String TAG = TagProvider.getDebugTag(RequestDetailFragment.class);
    @BindView(R.id.textViewTitle)
    TextView textViewTitle;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.profilepic)
    ImageView profilepic;
    @BindView(R.id.textViewName)
    TextView textViewName;
    @BindView(R.id.textViewType)
    TextView textViewType;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.bankname)
    TextView bankname;
    @BindView(R.id.editTextBankName)
    TextView editTextBankName;
    @BindView(R.id.personName)
    TextView personName;
    @BindView(R.id.NameOfperson)
    TextView NameOfperson;
    @BindView(R.id.account)
    TextView account;
    @BindView(R.id.accountNumber)
    TextView accountNumber;
    @BindView(R.id.priceDetail)
    TextView priceDetail;
    @BindView(R.id.transactionId)
    TextView transactionId;
    @BindView(R.id.noteTV)
    TextView noteTV;
    @BindView(R.id.layoutBankSection)
    LinearLayout layoutBankSection;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;

    Unbinder unbinder;
    private int transId;
    private int userType;

    public RequestDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertView = inflater.inflate(R.layout.fragment_request_detail, container, false);

        ((HomeActivity) getActivity()).setToolBarTitle(getString(R.string.req_details));


        unbinder = ButterKnife.bind(this, convertView);

        transId = getArguments().getInt("transaction_id");
        userType = (getArguments().getString("user_type")).equals("Investor") ? 1 : 2;
        return convertView;
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

    @SuppressLint("SetTextI18n")
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRequestDataReceiveEvent(RequestDataReceiveEvent event) {

        ProgressDialogHelper.hideProgress();

        layoutBankSection.setVisibility(View.VISIBLE);

        textViewTitle.setText(event.details.getPlanTitle());

        price.setText(getString(R.string.price) + " " + String.valueOf(event.details.getPlanAccessPrice()));

        String dateInputPattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        String dateOutputPattern = "dd MMM yyyy";

        SimpleDateFormat inputDateFormat = new SimpleDateFormat(dateInputPattern, Locale.getDefault());
        SimpleDateFormat outputDateFormat = new SimpleDateFormat(dateOutputPattern, Locale.getDefault());

        try {
            Date dateObj = inputDateFormat.parse(event.details.getCreatedAt());
            date.setText(outputDateFormat.format(dateObj));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        editTextBankName.setText(event.details.getBankName());
        NameOfperson.setText(event.details.getBankAccountName());
        accountNumber.setText(event.details.getBankAccountNumber());
        transactionId.setText(event.details.getTransactionId());

        noteTV.setText(event.details.getNote());


        if (userType == 1) {

            Glide.with(activityContext)
                    .load(PreferenceManager.getDefaultSharedPreferences(activityContext).getString(ConstantProvider.SP_PICTURE_URL, null))
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_profile_blue))
                    .apply(RequestOptions.circleCropTransform())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(profilepic);

            textViewName.setText(PreferenceManager.getDefaultSharedPreferences(activityContext).getString(ConstantProvider.SP_NAME, null));
            textViewType.setText(PreferenceManager.getDefaultSharedPreferences(activityContext).getString(ConstantProvider.SP_USER_TYPE, null));
            address.setText(PreferenceManager.getDefaultSharedPreferences(activityContext).getString(ConstantProvider.SP_ADDRESS, null));

        } else {

            btnConfirm.setVisibility(View.VISIBLE);

            Glide.with(activityContext)
                    .load(event.user.getPicture())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_profile_blue))
                    .apply(RequestOptions.circleCropTransform())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(profilepic);

            textViewName.setText(event.user.getName());
            textViewType.setText(event.user.getUserType());
            address.setText(event.user.getAddress());
        }
    }

    @OnClick(R.id.btn_confirm)
    public void onRequestConfirmed(View view) {
        presenter.confirmRequest(activityContext, transId);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onBackToManageRequestEvent(BackToManageRequestEvent event) {
        getActivity().onBackPressed();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRequestFailureEvent(RequestFailureEvent event) {

        ProgressDialogHelper.hideProgress();

        showMessage("Couldn't fetch data, try again!");
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);

        ProgressDialogHelper.init(getActivity()).showProgress();
        presenter.getTransactionDetails(activityContext, transId, userType);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
