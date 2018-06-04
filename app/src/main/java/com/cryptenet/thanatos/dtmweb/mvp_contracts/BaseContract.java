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

import android.app.Activity;
import android.os.Bundle;

public interface BaseContract {
    interface Presenter<V extends BaseContract.View> {
        void attachView(V view);
        void detachView();

        void saveState(Bundle outState);
        void restoreState(Bundle savedState);
    }

    interface View<A extends Activity> {
        A getActivity();

        void showMessage(String message);

        void printLog(final String TAG, String message);

        void restoreState(Bundle savedState);
    }

    interface Model<A extends Activity> {
        void attachContext(A context);

        void saveState(Bundle outState);
        Bundle restoreState(Bundle savedState);
    }

    interface Repository {
    }
}
