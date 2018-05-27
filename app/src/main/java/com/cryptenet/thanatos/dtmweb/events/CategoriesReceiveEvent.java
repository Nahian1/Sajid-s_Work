package com.cryptenet.thanatos.dtmweb.events;

import com.cryptenet.thanatos.dtmweb.pojo.Categories;

import java.util.List;

public class CategoriesReceiveEvent {
    public final List<Categories> categoriesList;

    public CategoriesReceiveEvent(List<Categories> categoriesList) {
        this.categoriesList = categoriesList;
    }
}
