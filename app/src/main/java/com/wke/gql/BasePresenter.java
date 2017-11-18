package com.wke.gql;

/**
 * Created by Administrator on 2017/11/18.
 */

public interface BasePresenter<T> {

    void takeView(T view);

    void dropView();
}
