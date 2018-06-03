/*
 * Copyright (c) 2018.
 *  Development Courtesy: Cryptenet Ltd.
 *  Developer Credit: Alamgir Hossain, Nabil Shawkat
 *  This project is under MIT license
 */

package com.cryptenet.thanatos.dtmweb.home.edit_project;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.base.BaseFragment;
import com.cryptenet.thanatos.dtmweb.events.CategoriesReceiveEvent;
import com.cryptenet.thanatos.dtmweb.events.EditPlanSuccessEvent;
import com.cryptenet.thanatos.dtmweb.events.ReturnToHomeEvent;
import com.cryptenet.thanatos.dtmweb.home.HomeActivity;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.EditProjectFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.Categories;
import com.cryptenet.thanatos.dtmweb.pojo.ProjectsRq;
import com.cryptenet.thanatos.dtmweb.pojo.ProjectsRsp;
import com.cryptenet.thanatos.dtmweb.utils.ImageFilePath;
import com.cryptenet.thanatos.dtmweb.utils.ProgressDialogHelper;
import com.cryptenet.thanatos.dtmweb.utils.ViewUtils;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
import com.cryptenet.thanatos.dtmweb.utils.providers.TagProvider;
import com.google.gson.Gson;
import com.ipaulpro.afilechooser.utils.FileUtils;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.app.Activity.RESULT_OK;


public class EditProjectFragment extends BaseFragment<EditProjectFragmentContract.Presenter>
        implements EditProjectFragmentContract.View, AdapterView.OnItemSelectedListener {
    public static final String TAG = TagProvider.getDebugTag(EditProjectFragment.class);
    @BindView(R.id.spinner_project_category)
    Spinner spinnerProjectCategory;

    Unbinder unbinder;
    @BindView(R.id.editTextName)
    EditText editTextName;
    @BindView(R.id.editTextPriceMaximum)
    EditText editTextPriceMaximum;
    @BindView(R.id.editTextPriceMinimum)
    EditText editTextPriceMinimum;
    @BindView(R.id.editTextShortDescription)
    EditText editTextShortDescription;
    @BindView(R.id.editTextLongDescription)
    EditText editTextLongDescription;
    @BindView(R.id.editTextAccessPrice)
    EditText editTextAccessPrice;
    @BindView(R.id.buttonUploadFile)
    LinearLayout buttonUploadFile;
    @BindView(R.id.imageviewCover)
    ImageView imageviewCover;

    private ProjectsRsp project;
    private List<Categories> list;
    private List<String> categoriesList;
    private ArrayAdapter<String> spinCatAdapter;
    private int categoryCode;
    private File imageFile, planFile;

    public EditProjectFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_project, container, false);

        ((HomeActivity) getActivity()).hideSearchBar(true);

        unbinder = ButterKnife.bind(this, view);


//        FragmentEditProjectBinding binding = DataBindingUtil.inflate(inflater,
//                R.layout.fragment_edit_project, container, false);

        project = new Gson().fromJson(getArguments().getString("project"), ProjectsRsp.class);

        if (project.isEditMode()) {

            editTextName.setText(project.getInitiatorsName());
            editTextPriceMaximum.setText(project.getMaximumInvestmentCost());
            editTextPriceMinimum.setText(project.getMinimumInvestmentCost());
            editTextShortDescription.setText(project.getShortDescription());
            editTextLongDescription.setText(project.getLongDescription());
            editTextAccessPrice.setText(project.getAccessPrice());

        }

        list = new ArrayList<>();
        categoriesList = new ArrayList<>();
        categoriesList.add("Categories");

        spinCatAdapter = new ArrayAdapter<>(activityContext,
                R.layout.node_spin_edit_project, categoriesList);
        spinCatAdapter.setDropDownViewResource(R.layout.node_spin_edit_project);
        spinnerProjectCategory.setAdapter(spinCatAdapter);

        spinnerProjectCategory.setOnItemSelectedListener(this);

        return view;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(activityContext, message, Toast.LENGTH_LONG).show();

    }

    @Override
    public void printLog(String TAG, String message) {
        Log.d(TAG, "printLog: " + message);
    }

    @Override
    public void restoreState(Bundle savedState) {

    }

    @OnClick(R.id.btn_done)
    public void savePlan(View view) {

        if (project.isEditMode()) {
            // do when plan edit
        } else {
            processAddNewPlanInputData();
        }

    }

    // add new plan input processing
    private void processAddNewPlanInputData() {
        String title = editTextName.getText().toString().trim();
        String shortDesc = editTextShortDescription.getText().toString().trim();
        String longDesc = editTextLongDescription.getText().toString().trim();
        String minInvest = editTextPriceMinimum.getText().toString().trim();
        String maxInvest = editTextPriceMaximum.getText().toString().trim();
        String accessPrice = editTextAccessPrice.getText().toString().trim();


        ProjectsRq projectsRq = new ProjectsRq();

        if (!title.isEmpty() && !shortDesc.isEmpty() && !longDesc.isEmpty()
                && !minInvest.isEmpty() && !maxInvest.isEmpty() && !accessPrice.isEmpty()) {
            projectsRq.setTitle(title);
            projectsRq.setCategory(categoryCode);
            projectsRq.setShortDescription(shortDesc);
            projectsRq.setLongDescription(longDesc);
            projectsRq.setMinimumInvestmentCost((int) Float.parseFloat(minInvest));
            projectsRq.setMinimumInvestmentCost((int) Float.parseFloat(maxInvest));
            projectsRq.setAccessPrice((int) Double.parseDouble(accessPrice));

            if (imageFile != null)
                projectsRq.setCover(imageFile);
            if (planFile != null)
                projectsRq.setUploadedFile(planFile);

            if (imageFile == null || planFile == null) {
                showMessage("Attach your file/image");
            }

            projectsRq.setNew(true);

            if (imageFile != null && planFile != null) {

                ProgressDialogHelper.init(getActivity()).showProgress();

                presenter.saveUpdatePlan(projectsRq, activityContext, -1);
            }

        } else {
            showMessage("Please fill all fields");
        }

    }

    @OnClick(R.id.buttonUploadImage)
    public void getCoverImage(View view) {
        Dexter.withActivity(getActivity())
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {

                        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                        photoPickerIntent.setType("image/*");
                        startActivityForResult(photoPickerIntent, ConstantProvider.RESULT_LOAD_IMG);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        if (response.isPermanentlyDenied()) {
                            // navigate user to app settings
                            showMessage("must grant permission");
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    @OnClick(R.id.buttonUploadFile)
    public void buttonUploadFile(View view) {
        Dexter.withActivity(getActivity())
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        // Create the ACTION_GET_CONTENT Intent
                        Intent getContentIntent = FileUtils.createGetContentIntent();

                        Intent intent = Intent.createChooser(getContentIntent, "Select a file");
                        startActivityForResult(intent, ConstantProvider.RESULT_FILE_IMG);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        if (response.isPermanentlyDenied()) {
                            // navigate user to app settings
                            showMessage("must grant permission");
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    @Subscribe
    public void toCategoriesReceiveEvent(CategoriesReceiveEvent event) {
        this.categoriesList.clear();
        this.list.clear();
        this.list.addAll(event.categoriesList);

        for (Categories categories : event.categoriesList)
            this.categoriesList.add(categories.getName());

        spinCatAdapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEditPlanSuccessEvent(EditPlanSuccessEvent event) {

        ProgressDialogHelper.hideProgress();

        if (project.isEditMode())
            showMessage("Updated.");
        else
            showMessage("Added.");

        getActivity().onBackPressed();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String realPath = null;
        Bitmap selectedImage;

        if (resultCode == RESULT_OK) {
            if (requestCode == ConstantProvider.RESULT_LOAD_IMG) {
                try {
                    final Uri imageUri = data.getData();
                    showMessage(imageUri.getPath() + " added.");

                    realPath = ImageFilePath.getPath(activityContext, data.getData());
                    assert imageUri != null;
                    final InputStream imageStream = activityContext.getContentResolver().openInputStream(imageUri);
                    selectedImage = BitmapFactory.decodeStream(imageStream);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                imageFile = new File(realPath);

            } else if (requestCode == ConstantProvider.RESULT_FILE_IMG) {

                final Uri uri = data.getData();

                // Get the File path from the Uri
                String path = FileUtils.getPath(activityContext, uri);

                // Alternatively, use FileUtils.getFile(Context, Uri)
                if (path != null && FileUtils.isLocal(path)) {
                    showMessage(path + " added.");
                    planFile = new File(path);
                }
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.attachView(this);
        presenter.getAllCategories(activityContext);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (list != null && list.size() > 0) {
            this.categoryCode = list.get(position).getId();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
