package com.wke.gql.MVPdagger2.testWeather;

import com.wke.gql.BasePresenter;
import com.wke.gql.BaseView;


public interface WeatherContract {
    interface Presenter extends BasePresenter<View> {
        void loadWeather();

        @Override
        void takeView(View view);

        @Override
        void dropView();
    }

    interface View extends BaseView<Presenter> {
        void refreshUi();
    }
}
