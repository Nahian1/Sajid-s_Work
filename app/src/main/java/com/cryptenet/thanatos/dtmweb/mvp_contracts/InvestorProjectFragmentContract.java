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

public interface InvestorProjectFragmentContract {

    interface Presenter extends BaseFragContract.Presenter<InvestorProjectFragmentContract.View> {
        void getMyProjectList(int reqType,Context context);
    }

    interface View extends BaseFragContract.View {
    }

    interface Model extends BaseFragContract.Model {
        void getMyProjectList(int reqType,Context context);
    }

    interface Repository extends BaseFragContract.Repository {
        void getMyProjectList(int reqType,Context context);
    }
}
