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
import android.support.annotation.Nullable;

import com.cryptenet.thanatos.dtmweb.pojo.ProjectsRq;

public interface EditProjectFragmentContract {
    interface Presenter extends BaseFragContract.Presenter<EditProjectFragmentContract.View> {
        void getAllCategories(Context context);
        void saveUpdatePlan(ProjectsRq plan, Context context, int id);
    }

    interface View extends BaseFragContract.View {
    }

    interface Model extends BaseFragContract.Model {
        void getAllCategories(Context context);
        void saveUpdatePlan(ProjectsRq plan, Context context, int id);
    }

    interface Repository extends BaseFragContract.Repository {
        void getAllCategories(Context context);
        void saveUpdatePlan(ProjectsRq plan, Context context, int id);
    }
}
