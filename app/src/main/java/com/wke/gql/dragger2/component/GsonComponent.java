package com.wke.gql.dragger2.component;

import com.google.gson.Gson;
import com.wke.gql.dragger2.module.GsonModule;

import dagger.Component;


@Component(modules = GsonModule.class)
public interface GsonComponent {
    Gson gsonInstance();
}
