package com.cryptenet.thanatos.dtmweb.terms;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.home.terms.TermsFragment;
import com.cryptenet.thanatos.dtmweb.utils.LocaleHelper;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TermsActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getString(R.string.nav_tc));

        }

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home)
            super.onBackPressed();

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        String lang = PreferenceManager.getDefaultSharedPreferences(newBase).getString(ConstantProvider.SELECTED_LANGUAGE, "en");
        super.attachBaseContext(LocaleHelper.setNewLocale(newBase, lang));
    }
}
