/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.di.modules;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerActivity;
import com.cryptenet.thanatos.dtmweb.di.sub_components.EditProjectFragmentSubComponent;
import com.cryptenet.thanatos.dtmweb.di.sub_components.FormFragmentSubComponent;
import com.cryptenet.thanatos.dtmweb.di.sub_components.InitiatorProjectFragmentSubComponent;
import com.cryptenet.thanatos.dtmweb.di.sub_components.InvestorProjectFragmentSubComponent;
import com.cryptenet.thanatos.dtmweb.di.sub_components.OtherReportFragmentSubComponent;
import com.cryptenet.thanatos.dtmweb.di.sub_components.PlanDescFragmentSubComponent;
import com.cryptenet.thanatos.dtmweb.di.sub_components.PlanListFragmentSubComponent;
import com.cryptenet.thanatos.dtmweb.di.sub_components.PlanLongDescFragmentSubComponent;
import com.cryptenet.thanatos.dtmweb.di.sub_components.ReportIssueFragmentSubComponent;
import com.cryptenet.thanatos.dtmweb.di.sub_components.RequestDetailFragmentSubComponent;
import com.cryptenet.thanatos.dtmweb.di.sub_components.ThreadListFragmentSubComponent;
import com.cryptenet.thanatos.dtmweb.di.sub_components.ThreadMsgFragmentSubComponent;
import com.cryptenet.thanatos.dtmweb.di.sub_components.ThreadProjectFragmentSubComponent;
import com.cryptenet.thanatos.dtmweb.di.sub_components.TransactionFragmentSubComponent;
import com.cryptenet.thanatos.dtmweb.home.HomeActivity;
import com.cryptenet.thanatos.dtmweb.home.edit_project.EditProjectFragment;
import com.cryptenet.thanatos.dtmweb.home.form.FormFragment;
import com.cryptenet.thanatos.dtmweb.home.initiator_project.InitiatorProjectFragment;
import com.cryptenet.thanatos.dtmweb.home.investor_project.InvestorProjectFragment;
import com.cryptenet.thanatos.dtmweb.home.mvp.HomeActivityModel;
import com.cryptenet.thanatos.dtmweb.home.mvp.HomeActivityPresenter;
import com.cryptenet.thanatos.dtmweb.home.mvp.HomeActivityRepository;
import com.cryptenet.thanatos.dtmweb.home.other_report.OtherReportFragment;
import com.cryptenet.thanatos.dtmweb.home.plan_desc.PlanDescFragment;
import com.cryptenet.thanatos.dtmweb.home.plan_list.PlanListFragment;
import com.cryptenet.thanatos.dtmweb.home.report_issue.ReportIssueFragment;
import com.cryptenet.thanatos.dtmweb.home.request_detail.RequestDetailFragment;
import com.cryptenet.thanatos.dtmweb.home.thread_list.ThreadListFragment;
import com.cryptenet.thanatos.dtmweb.home.thread_msg.ThreadMsgFragment;
import com.cryptenet.thanatos.dtmweb.home.thread_project.ThreadProjectFragment;
import com.cryptenet.thanatos.dtmweb.home.transaction.TransactionFragment;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.HomeActivityContract;
import com.cryptenet.thanatos.dtmweb.home.plan_long_desc.PlanLongDescFragment;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(
        includes = BaseFragActivityModule.class,
        subcomponents = {
                PlanListFragmentSubComponent.class,
                PlanDescFragmentSubComponent.class,
                InvestorProjectFragmentSubComponent.class,
                ThreadListFragmentSubComponent.class,
                FormFragmentSubComponent.class,
                TransactionFragmentSubComponent.class,
                ReportIssueFragmentSubComponent.class,
                OtherReportFragmentSubComponent.class,
                EditProjectFragmentSubComponent.class,
                InitiatorProjectFragmentSubComponent.class,
                RequestDetailFragmentSubComponent.class,
                ThreadProjectFragmentSubComponent.class,
                ThreadMsgFragmentSubComponent.class,
                PlanLongDescFragmentSubComponent.class
        }
)
public abstract class HomeActivityModule {
    @Binds
    @PerActivity
    abstract AppCompatActivity activity(HomeActivity activity);

    @Provides
    @PerActivity
    static HomeActivityContract.Presenter providePresenter(HomeActivityContract.Model model){
        return new HomeActivityPresenter(model);
    }

    @Provides
    @PerActivity
    static HomeActivityContract.Model provideModel(HomeActivityContract.Repository repository) {
        return new HomeActivityModel(repository);
    }

    @Provides
    @PerActivity
    static HomeActivityContract.Repository provideRepository() {
        return new HomeActivityRepository();
    }

    @Binds
    @IntoMap
    @FragmentKey(PlanListFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    PlanListFragmentInjectorFactory(PlanListFragmentSubComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(PlanDescFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    PlanDescFragmentInjectorFactory(PlanDescFragmentSubComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(InvestorProjectFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    InvestorProjectFragmentInjectorFactory(InvestorProjectFragmentSubComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(ThreadListFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    ThreadListFragmentInjectorFactory(ThreadListFragmentSubComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(FormFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    FormFragmentInjectorFactory(FormFragmentSubComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(TransactionFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    TransactionFragmentInjectorFactory(TransactionFragmentSubComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(ReportIssueFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    ReportIssueFragmentInjectorFactory(ReportIssueFragmentSubComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(OtherReportFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    OtherReportFragmentInjectorFactory(OtherReportFragmentSubComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(EditProjectFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    EditProjectFragmentInjectorFactory(EditProjectFragmentSubComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(InitiatorProjectFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    InitiatorProjectFragmentInjectorFactory(InitiatorProjectFragmentSubComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(RequestDetailFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    RequestDetailFragmentInjectorFactory(RequestDetailFragmentSubComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(ThreadProjectFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    ThreadProjectFragmentInjectorFactory(ThreadProjectFragmentSubComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(ThreadMsgFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    ThreadMsgFragmentInjectorFactory(ThreadMsgFragmentSubComponent.Builder builder);

    @Binds
    @IntoMap
    @FragmentKey(PlanLongDescFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment>
    PlanLongDescFragmentInjectorFactory(PlanLongDescFragmentSubComponent.Builder builder);
}
