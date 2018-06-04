/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.di.modules;

import com.cryptenet.thanatos.dtmweb.di.scopes.PerFragment;
import com.cryptenet.thanatos.dtmweb.home.edit_project.mvp.EditProjectFragmentModel;
import com.cryptenet.thanatos.dtmweb.home.edit_project.mvp.EditProjectFragmentPresenter;
import com.cryptenet.thanatos.dtmweb.home.edit_project.mvp.EditProjectFragmentRepository;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.EditProjectFragmentContract;

import dagger.Module;
import dagger.Provides;


@Module(
        includes = BaseFragmentModule.class
)
public abstract class EditProjectFragmentModule {
    @Provides
    @PerFragment
    static EditProjectFragmentContract.Presenter providePresenter(EditProjectFragmentContract.Model model){
        return new EditProjectFragmentPresenter(model);
    }

    @Provides
    @PerFragment
    static EditProjectFragmentContract.Model provideModel(EditProjectFragmentContract.Repository repository) {
        return new EditProjectFragmentModel(repository);
    }

    @Provides
    @PerFragment
    static EditProjectFragmentContract.Repository provideRepository() {
        return new EditProjectFragmentRepository();
    }
}
