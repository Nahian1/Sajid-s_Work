package com.cryptenet.thanatos.dtmweb.borrowed;

import android.util.Log;

import java.io.File;
import java.io.IOException;

public class ApiUtil {
    String BASE_URL;
    String charset = "UTF-8";

    public ApiUtil(String BASEURL) {
        this.BASE_URL = BASEURL;
    }

    public String createUser(String name, String email, String password,
                             File picture,
                             String address, String country, String city, String bankName,
                             String bankAccountName, String bankAccountNumber, String userType) {
        String requestUrl = BASE_URL + "api/v1/user/?format=json";
        try {
            MultipartUtility multipart = new MultipartUtility(requestUrl, charset, "POST", "");

            multipart.addHeaderField("Content-Type", "application/json");

            multipart.addFormField("name", name);
            multipart.addFormField("email", email);
            multipart.addFormField("password", password);
            multipart.addFilePart("picture", picture);
            multipart.addFormField("city", city);
            multipart.addFormField("country", country);
            multipart.addFormField("bank_name", bankName);
            multipart.addFormField("bank_account_name", bankAccountName);
            multipart.addFormField("bank_account_number", bankAccountNumber);
            multipart.addFormField("user_type", userType);
            multipart.addFormField("address", address);
            return multipart.finish();
        } catch (IOException ex) {
            Log.d("createUser Error => ", ex.getMessage(), ex);

            ex.printStackTrace();
            return "IOException";
        }
    }

    public String editUser(String id, String name, String email,
                           File picture, String userType, String pwd,
                           String address, String country, String city, String bankName,
                           String bankAccountName, String bankAccountNumber, String accessToken) {
        String requestUrl = BASE_URL + "api/v1/user/" + id + "/?format=json";
        try {
            MultipartUtility multipart = new MultipartUtility(requestUrl, charset, "PUT", accessToken);

            multipart.addHeaderField("Content-Type", "application/json");

            multipart.addFormField("name", name);
            multipart.addFormField("email", email);
            multipart.addFilePart("picture", picture);
            multipart.addFormField("city", city);
            multipart.addFormField("country", country);
            multipart.addFormField("bank_name", bankName);
            multipart.addFormField("bank_account_name", bankAccountName);
            multipart.addFormField("bank_account_number", bankAccountNumber);
            multipart.addFormField("address", address);
            multipart.addFormField("user_type", userType);
            multipart.addFormField("password", pwd);

            return multipart.finish();

        } catch (IOException ex) {
            System.out.println(ex);
            return "IOException";
        }
    }

    public String addPlan(String title, int category, String shortDesc, String longDesc,
                          int min, int max, int access, File cover, File uploadFile, String accessToken) {
        String requestUrl = BASE_URL + "api/v1/plan/?format=json";

        try {
            MultipartUtility multipart = new MultipartUtility(requestUrl, charset, "POST", "Bearer " + accessToken);

            multipart.addHeaderField("Content-Type", "application/json");

            multipart.addFormField("title", title);
            multipart.addFormField("category", String.valueOf(category));
            multipart.addFormField("short_description", shortDesc);
            multipart.addFormField("long_description", longDesc);
            multipart.addFormField("minimum_investment_cost", String.valueOf(min));
            multipart.addFormField("maximum_investment_cost", String.valueOf(max));
            multipart.addFormField("access_price", String.valueOf(access));
            multipart.addFilePart("cover", cover);
            multipart.addFilePart("uploaded_file", uploadFile);

            return multipart.finish();
        } catch (IOException e) {
            e.printStackTrace();
            return "IOException";
        }
    }

    public String editPlan(String title, int category, String shortDesc, String longDesc,
                          int min, int max, int access, File cover, File uploadFile, String accessToken, int id) {
        String requestUrl = BASE_URL + "/api/v1/plan/" + id + "/?format=json";

        try {
            MultipartUtility multipart = new MultipartUtility(requestUrl, charset, "PUT", "Bearer " + accessToken);

            multipart.addHeaderField("Content-Type", "application/json");

            multipart.addFormField("title", title);
            multipart.addFormField("category", String.valueOf(category));
            multipart.addFormField("short_description", shortDesc);
            multipart.addFormField("long_description", longDesc);
            multipart.addFormField("minimum_investment_cost", String.valueOf(min));
            multipart.addFormField("maximum_investment_cost", String.valueOf(max));
            multipart.addFormField("access_price", String.valueOf(access));
            multipart.addFilePart("cover", cover);
            multipart.addFilePart("uploaded_file", uploadFile);

            return multipart.finish();
        } catch (IOException e) {
            e.printStackTrace();
            return "IOException";
        }
    }
}
