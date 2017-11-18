package com.wke.gql;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.wke.gql.base.BaseApplication;
import com.wke.gql.dagger2.User;
import com.wke.gql.dagger2.component.DaggerBaseQueryWithDaggerComponent;
import com.wke.gql.dagger2.module.CModule;
import com.wke.gql.net.BaseQueryWithDagger;

import javax.inject.Inject;

public class CActivity extends AppCompatActivity {
    private static final String TAG = "CActivity";
    @Inject
    User user;
    BaseQueryWithDagger baseQueryWithDagger;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);
        BaseApplication.getApplication().getUserComponent().plus(new CModule()).inject(this);
        textView = (TextView) findViewById(R.id.textViewC);
        textView.setText(user.toString());
        baseQueryWithDagger = DaggerBaseQueryWithDaggerComponent.builder().build().baseQueryWithDaggerInstance();
        Log.i(TAG, "onCreate: " + baseQueryWithDagger.toString());
    }

    public void cleanUser(View v) {
        BaseApplication.getApplication().releaseUserComponent();
    }

}
