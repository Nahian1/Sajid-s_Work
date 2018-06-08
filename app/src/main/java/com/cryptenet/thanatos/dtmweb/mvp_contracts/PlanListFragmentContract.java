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

import com.cryptenet.thanatos.dtmweb.pojo.ProjectsRsp;

public interface PlanListFragmentContract {
    interface Presenter extends BaseFragContract.Presenter<PlanListFragmentContract.View> {
        void getProjectList(Context context, int offset);
        void checkUserType(ProjectsRsp projectsRsp, Context context);
        void searchMyPlans(Context context, String token, String searchTerm);
    }

    interface View extends BaseFragContract.View {
        void toDetailsView(ProjectsRsp projectsRsp, int type);
    }

    interface Model extends BaseFragContract.Model {
        void getProjectList(Context context, int offset);
        int checkUserType(Context context);
        void searchMyPlans(Context context, String token, String searchTerm);
    }

    interface Repository extends BaseFragContract.Repository {
        void getAllProjects(Context context, int offset);
        int checkUserType(Context context);
        void searchMyPlans(Context context, String token, String searchTerm);
    }
}
