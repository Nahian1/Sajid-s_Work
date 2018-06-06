/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.thread_msg.mvp;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.events.MessageListFailureEvent;
import com.cryptenet.thanatos.dtmweb.events.MessageListReceivedEvent;
import com.cryptenet.thanatos.dtmweb.events.onMessageSentEvent;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ThreadMsgFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.MessageListModel;
import com.cryptenet.thanatos.dtmweb.pojo.Results;
import com.cryptenet.thanatos.dtmweb.pojo.SendMessageModel;
import com.cryptenet.thanatos.dtmweb.pojo.ThreadRequestModel;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@PerFragment
public class ThreadMsgFragmentRepository extends BaseFragRepository
        implements ThreadMsgFragmentContract.Repository {
    private static String TAG = TagProvider.getDebugTag(ThreadMsgFragmentRepository.class);

    @Override
    public void getMessageList(Context context, int threadID) {
        Call<MessageListModel> threadCall = apiClient.getMessages(
                "Bearer " + PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_ACCESS_TOKEN, null),
                String.valueOf(threadID)
        );

        threadCall.enqueue(new Callback<MessageListModel>() {
            @Override
            public void onResponse(Call<MessageListModel> call, Response<MessageListModel> response) {
                //  response.body().toString();

                if (response.isSuccessful()) {
                    List<Results> messageThreadModels = new ArrayList<>(Arrays.asList(response.body().getResults()));

                    EventBus.getDefault().post(new MessageListReceivedEvent(messageThreadModels));
                } else {
                    EventBus.getDefault().post(new MessageListFailureEvent(true));
                }
            }

            @Override
            public void onFailure(Call<MessageListModel> call, Throwable t) {
                EventBus.getDefault().post(new MessageListFailureEvent(true));
            }
        });
    }

    @Override
    public void setSendMessage(Context context, int threadID, String message) {
        Call<SendMessageModel> threadCall = apiClient.sendMessage(
                "Bearer " + PreferenceManager.getDefaultSharedPreferences(context).getString(ConstantProvider.SP_ACCESS_TOKEN, null),
                String.valueOf(threadID),
                message
        );

        threadCall.enqueue(new Callback<SendMessageModel>() {
            @Override
            public void onResponse(Call<SendMessageModel> call, Response<SendMessageModel> response) {
                Results results = new Results();

                results.setId(response.body().getId());
                results.setThread(response.body().getThread());
                results.setSender(response.body().getSender());
                results.setReceiver(response.body().getReceiver());
                results.setText(response.body().getText());
                results.setCreated_at(response.body().getCreated_at());
                results.setSender_name(response.body().getSender_name());
                results.setSender_picture(response.body().getSender_picture());
                results.setReceiver_name(response.body().getReceiver_name());
                results.setReceiver_picture(response.body().getReceiver_picture());

                if (response.isSuccessful()) {
                    Log.d(TAG, "onResponse: " + "msg success");
                    EventBus.getDefault().post(new onMessageSentEvent(results));
                }
            }

            @Override
            public void onFailure(Call<SendMessageModel> call, Throwable t) {
                Log.d(TAG, "onResponse: " + "msg fail");
            }
        });
    }
}
