package com.wke.gql;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.wke.gql.base.BaseApplication;
import com.wke.gql.dagger2.User;
import com.wke.gql.dagger2.component.DaggerBaseQueryWithDaggerComponent;
import com.wke.gql.dagger2.module.AModule;
import com.wke.gql.net.BaseQueryWithDagger;

import javax.inject.Inject;

public class AActivity extends AppCompatActivity {
    private static final String TAG = "AActivity";
    @Inject
    User user;
    BaseQueryWithDagger baseQueryWithDagger;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        BaseApplication.getApplication().getUserComponent().plus(new AModule()).inject(this);
        textView = (TextView) findViewById(R.id.textViewA);
        textView.setText(user.toString());
        baseQueryWithDagger = DaggerBaseQueryWithDaggerComponent.builder().build().baseQueryWithDaggerInstance();
        Log.i(TAG, "onCreate: " + baseQueryWithDagger.toString());
    }

    public void goB(View v) {
        startActivity(new Intent(this, BActivity.class));
    }
}
