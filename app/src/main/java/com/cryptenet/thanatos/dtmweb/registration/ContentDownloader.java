package com.cryptenet.thanatos.dtmweb.registration;

import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.net.HttpURLConnection;

public class ContentDownloader {
    public static String getHttpPostContent (String url, MultipartEntity mpEntity){
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);
            post.setHeader("Content-Type", "application/json");

            Log.i("TAG", "Try to open=> " + url);

            MultipartEntity reqEntity = mpEntity;
            post.setEntity(reqEntity);

            HttpResponse httpResponse = httpClient.execute(post);

            int statusCode = httpResponse.getStatusLine().getStatusCode();
            Log.i("TAG", "Connection code: " + statusCode);

            HttpEntity entity = httpResponse.getEntity();
            String serverResponse = EntityUtils.toString(entity);
            Log.i("TAG", "Server response=> " + serverResponse);

            if (!isStatusOk(statusCode))
                return null;

            return serverResponse;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static boolean isStatusOk(int statusCode) {
        if((statusCode >= HttpURLConnection.HTTP_OK)  &&  (statusCode <= HttpURLConnection.HTTP_PARTIAL))
            return true;

        return false;
    }

}
