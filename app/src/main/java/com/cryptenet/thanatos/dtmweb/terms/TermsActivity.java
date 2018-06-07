/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.terms;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.home.terms.TermsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TermsActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.menuRight)
    ImageView ivMenuRight;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        ivMenuRight.setVisibility(View.GONE);

        fragmentManager = getSupportFragmentManager();

        TermsFragment fragment = new TermsFragment();
        Bundle bundle = new Bundle();

        bundle.putBoolean("flag", false);
        fragment.setArguments(bundle);
        addFragment(R.id.frame_container, fragment);
    }

    private void addFragment(@IdRes int containerViewId, Fragment fragment) {
        fragmentManager
                .beginTransaction()
//                .addToBackStack(null) //commented out by Asif
                .add(containerViewId, fragment)
                .commit();
    }
}
