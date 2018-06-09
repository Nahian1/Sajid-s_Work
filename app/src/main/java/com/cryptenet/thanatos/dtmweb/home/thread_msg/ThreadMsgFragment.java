/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.thread_msg;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.base.BaseFragment;
import com.cryptenet.thanatos.dtmweb.events.MessageListFailureEvent;
import com.cryptenet.thanatos.dtmweb.events.MessageListReceivedEvent;
import com.cryptenet.thanatos.dtmweb.events.onMessageSentEvent;
import com.cryptenet.thanatos.dtmweb.home.HomeActivity;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ThreadMsgFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.Results;
import com.cryptenet.thanatos.dtmweb.utils.ProgressDialogHelper;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class ThreadMsgFragment extends BaseFragment<ThreadMsgFragmentContract.Presenter>
        implements ThreadMsgFragmentContract.View {
    public static final String TAG = TagProvider.getDebugTag(ThreadMsgFragment.class);
    private RecyclerView mRecyclerView;
    private MessagingAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private EditText sendMessage;
    private List<Results> resultsList;
    int threadId;

    public ThreadMsgFragment() {
        resultsList = new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View convertView = inflater.inflate(R.layout.fragment_thread_msg, container, false);

        ((HomeActivity) getActivity()).setToolBarTitle(getString(R.string.thread_msg));

        threadId = getArguments().getInt("thread_id");
        mRecyclerView = convertView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(activityContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        sendMessage = convertView.findViewById(R.id.et_message);

        sendMessage.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                String message = sendMessage.getText().toString().trim();
                if (message.length() > 0) {
                    setSendMessage();

                    View view = getActivity().getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                    }

                    sendMessage.setText("");
                } else {
                    Toast.makeText(activityContext, "Please enter text message!", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
            return false;
        });
        return convertView;
    }

    private void setSendMessage() {
        presenter.setSendMessage(activityContext, threadId, sendMessage.getText().toString().trim());
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageListReceivedEvent(MessageListReceivedEvent event) {

        ProgressDialogHelper.hideProgress();

        resultsList = event.messageThreadModels;

        if (resultsList.size() == 0) {
            Toast.makeText(activityContext, activityContext.getString(R.string.no_messages), Toast.LENGTH_SHORT).show();
            mAdapter = new MessagingAdapter(resultsList);
            mRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter = new MessagingAdapter(resultsList);
            mRecyclerView.setAdapter(mAdapter);
            mLayoutManager.scrollToPosition(resultsList.size() - 1);
        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageSentEvent(onMessageSentEvent event) {
        resultsList.add(event.results);
        mAdapter.setData(resultsList);
        mAdapter.notifyDataSetChanged();
        mLayoutManager.scrollToPosition(resultsList.size() - 1);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageListFailureEvent(MessageListFailureEvent event) {

        ProgressDialogHelper.hideProgress();

        showMessage(getString(R.string.no_messages));
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);

        ProgressDialogHelper.init(getActivity()).showProgress();

        presenter.getMessageList(activityContext, threadId);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
