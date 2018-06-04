/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
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
