/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.home.edit_project;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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
import android.widget.TextView;
import android.widget.Toast;

import com.cryptenet.thanatos.dtmweb.R;
import com.cryptenet.thanatos.dtmweb.base.BaseFragment;
import com.cryptenet.thanatos.dtmweb.events.CategoriesReceiveEvent;
import com.cryptenet.thanatos.dtmweb.events.EditPlanSuccessEvent;
import com.cryptenet.thanatos.dtmweb.home.HomeActivity;
import com.cryptenet.thanatos.dtmweb.mvp_contracts.EditProjectFragmentContract;
import com.cryptenet.thanatos.dtmweb.pojo.Categories;
import com.cryptenet.thanatos.dtmweb.pojo.ProjectsRq;
import com.cryptenet.thanatos.dtmweb.pojo.ProjectsRsp;
import com.cryptenet.thanatos.dtmweb.utils.ImageFilePath;
import com.cryptenet.thanatos.dtmweb.utils.ProgressDialogHelper;
import com.cryptenet.thanatos.dtmweb.utils.providers.ConstantProvider;
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
//    public static final String TAG = TagProvider.getDebugTag(EditProjectFragment.class);
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
    @BindView(R.id.textUploadFile)
    TextView textUploadFile;
    @BindView(R.id.textUploadCover)
    TextView textUploadCover;

    private ProjectsRsp project;
    private List<Categories> list;
    private List<String> categoriesList;
    private ArrayAdapter<String> spinCatAdapter;
    private int categoryCode;
    private File imageFile, planFile;
    private int catPosition;

    public EditProjectFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_project, container, false);

        ((HomeActivity) getActivity()).setToolBarTitle(getString(R.string.edit_plan));

        unbinder = ButterKnife.bind(this, view);

        project = new Gson().fromJson(getArguments().getString("project"), ProjectsRsp.class);

        if (project.isEditMode()) {

            textUploadFile.setText(activityContext.getString(R.string.update_file));
            textUploadCover.setText(activityContext.getString(R.string.update_cover));

            editTextName.setText(project.getTitle());
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
            processEditPlanInputData();
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
            projectsRq.setShortDescription(shortDesc);
            projectsRq.setLongDescription(longDesc);
            projectsRq.setMinimumInvestmentCost(minInvest);
            projectsRq.setMaximumInvestmentCost(maxInvest);
            projectsRq.setAccessPrice(accessPrice);

            if (categoryCode != 0) {
                projectsRq.setCategory(categoryCode);
            } else {
                if (list != null && list.size() > 0) {
                    projectsRq.setCategory(list.get(0).getId());
                }
            }

            if (imageFile != null)
                projectsRq.setCover(imageFile);
            if (planFile != null)
                projectsRq.setUploadedFile(planFile);

            if (imageFile == null) {
                showMessage("Attach your cover image");
            }

            projectsRq.setNew(true);

            if (imageFile != null) {

                ProgressDialogHelper.init(getActivity()).showProgress();

                presenter.saveUpdatePlan(projectsRq, activityContext, -1);
            }

        } else {
            showMessage("Please fill all fields");
        }

    }

    // edit a plan input processing
    private void processEditPlanInputData() {

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

            if (categoryCode != 0) {
                projectsRq.setCategory(categoryCode);
            } else {
                if (list != null && list.size() > 0) {
                    projectsRq.setCategory(list.get(0).getId());
                }
            }

            projectsRq.setShortDescription(shortDesc);
            projectsRq.setLongDescription(longDesc);
            projectsRq.setMinimumInvestmentCost(minInvest);
            projectsRq.setMaximumInvestmentCost(maxInvest);
            projectsRq.setAccessPrice(accessPrice);

            if (imageFile != null)
                projectsRq.setCover(imageFile);
            if (planFile != null)
                projectsRq.setUploadedFile(planFile);

            projectsRq.setNew(false);

            ProgressDialogHelper.init(getActivity()).showProgress();

            presenter.saveUpdatePlan(projectsRq, activityContext, project.getId());

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
        catPosition = 0;
        this.list.clear();
        this.list.addAll(event.categoriesList);
        this.categoriesList.clear();

        for (int i = 0; i < this.list.size(); i++) {
            this.categoriesList.add(this.list.get(i).getName());

            if(project.isEditMode() && this.list.get(i).getId() == project.getCategory())
                catPosition = i;
        }

        spinCatAdapter.notifyDataSetChanged();
        spinnerProjectCategory.setSelection(catPosition);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEditPlanSuccessEvent(EditPlanSuccessEvent event) {

        ProgressDialogHelper.hideProgress();

        if (project.isEditMode())
            showMessage(getString(R.string.plan_updated));
        else
            showMessage(getString(R.string.plan_added));

        if (getActivity() != null)
            getActivity().onBackPressed();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String realPath = null;
        Bitmap selectedImage;

        if (resultCode == RESULT_OK) {
            if (requestCode == ConstantProvider.RESULT_LOAD_IMG) {

                textUploadCover.setText(activityContext.getString(R.string.update_cover));

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

                textUploadFile.setText(activityContext.getString(R.string.update_file));

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

        spinnerProjectCategory.setSelection(catPosition);
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
            catPosition = position;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
