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

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.mvp_base.BaseFragModel;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.ThreadMsgFragmentContract;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;

@PerFragment
public class ThreadMsgFragmentModel extends BaseFragModel<ThreadMsgFragmentContract.Repository>
        implements ThreadMsgFragmentContract.Model {
    private static final String TAG = TagProvider.getDebugTag(ThreadMsgFragmentModel.class);

    public ThreadMsgFragmentModel(ThreadMsgFragmentContract.Repository repository) {
        super(repository);
    }


    @Override
    public int getThreadIdForInvestor(Context context, int threadID) {
        return repository.getThreadIdForInvestor(context, threadID);
    }

    @Override
    public void getMessageList(Context context, int threadID) {
        repository.getMessageList(context, threadID);
    }

    @Override
    public void setSendMessage(Context context, int threadID, String message) {
        repository.setSendMessage(context, threadID, message);
    }
}
