/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.mvp_contracts;

import android.content.Context;

import com.cryptenet.thanatos.dtmweb.pojo.NewPlan;

public interface EditProjectFragmentContract {
    interface Presenter extends BaseFragContract.Presenter<EditProjectFragmentContract.View> {
        void getAllCategories(Context context);
        void saveNewPlan(NewPlan plan, Context context);
    }

    interface View extends BaseFragContract.View {
    }

    interface Model extends BaseFragContract.Model {
        void getAllCategories(Context context);
        void saveNewPlan(NewPlan plan, Context context);
    }

    interface Repository extends BaseFragContract.Repository {
        void getAllCategories(Context context);
        void saveNewPlan(NewPlan plan, Context context);
    }
}
