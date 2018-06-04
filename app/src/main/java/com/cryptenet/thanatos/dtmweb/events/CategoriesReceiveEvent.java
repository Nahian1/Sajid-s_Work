/*
 *  Copyright (c) 2018.
 *  Development Lead: Cryptenet Ltd.
 *  Developer Credit:
 *      Alamgir Hossain,
 *      Md. Rezwanur Rahman Khan,
 *      Ashif Mujtoba
 *  This project is under Apache License 2.0
 */

package com.cryptenet.thanatos.dtmweb.events;

import com.cryptenet.thanatos.dtmweb.pojo.Categories;

import java.util.List;

public class CategoriesReceiveEvent {
    public final List<Categories> categoriesList;

    public CategoriesReceiveEvent(List<Categories> categoriesList) {
        this.categoriesList = categoriesList;
    }
}
