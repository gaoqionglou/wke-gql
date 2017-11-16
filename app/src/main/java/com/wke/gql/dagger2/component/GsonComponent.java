package com.wke.gql.dagger2.component;

import com.google.gson.Gson;
import com.wke.gql.dagger2.module.GsonModule;

import dagger.Component;


@Component(modules = GsonModule.class)
public interface GsonComponent {
    Gson gsonInstance();
}
