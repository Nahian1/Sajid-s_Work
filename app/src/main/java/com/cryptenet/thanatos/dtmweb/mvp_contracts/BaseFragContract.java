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

import android.os.Bundle;

public interface BaseFragContract {
    interface Presenter<V extends BaseFragContract.View> {
        void attachView(V view);
        void detachView();

        void saveState(Bundle outState);
        void restoreState(Bundle savedState);
    }

    interface View {
        void showMessage(String message);

        void printLog(final String TAG, String message);

        void restoreState(Bundle savedState);
    }

    interface Model {
        void saveState(Bundle outState);
        Bundle restoreState(Bundle savedState);
    }

    interface Repository {
    }
}
