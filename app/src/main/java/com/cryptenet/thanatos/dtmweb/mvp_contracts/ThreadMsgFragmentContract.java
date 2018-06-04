/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.mvp_contracts;

import android.content.Context;

public interface ThreadMsgFragmentContract {
    interface Presenter extends BaseFragContract.Presenter<ThreadMsgFragmentContract.View> {
        void getThreadIdForInvestor(Context context, int threadID);
        void getMessageList(Context context, int threadID);
        void setSendMessage(Context context, int threadID, String message);
    }

    interface View extends BaseFragContract.View {
        void onThreadIdReceive(int threadId);
    }

    interface Model extends BaseFragContract.Model {
        int getThreadIdForInvestor(Context context, int threadID);
        void getMessageList(Context context, int threadID);
        void setSendMessage(Context context, int threadID, String message);
    }

    interface Repository extends BaseFragContract.Repository {
        int getThreadIdForInvestor(Context context, int threadID);
        void getMessageList(Context context, int threadID);
        void setSendMessage(Context context, int threadID, String message);
    }
}
