/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.http;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by Ashif on 12/10/17.
 */

public class ToStringConverterFactory extends Converter.Factory {
    private static final MediaType MEDIA_TYPE = MediaType.parse("text/plain");


    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (String.class.equals(type)) {
            return (Converter<ResponseBody, String>) value -> value.string();
        }
        return null;
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations,
                                                          Annotation[] methodAnnotations, Retrofit retrofit) {

        if (String.class.equals(type)) {
            return (Converter<String, RequestBody>) value -> RequestBody.create(MEDIA_TYPE, value);
        }
        return null;
    }
}
