package com.wke.gql.dagger2.component;

import com.wke.gql.AActivity;
import com.wke.gql.BActivity;
import com.wke.gql.MainActivity;
import com.wke.gql.dagger2.di.ActivityScoped;
import com.wke.gql.dagger2.module.RxNetWorkUtilModule;

import dagger.Subcomponent;

/**
 * Created by gql on 17-11-23.
 */
@ActivityScoped
@Subcomponent(modules = RxNetWorkUtilModule.class)
public interface RxNetWorkUtilComponent {
    //    RxNetWorkUtil rxNetWorkUtil();
    void inject(MainActivity activity);

    void inject(AActivity activity);

    void inject(BActivity activity);
}
