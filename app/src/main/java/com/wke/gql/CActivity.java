package com.wke.gql;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.wke.gql.base.BaseApplication;
import com.wke.gql.dagger2.User;
import com.wke.gql.dagger2.module.CModule;

import javax.inject.Inject;

public class CActivity extends AppCompatActivity {
    @Inject
    User user;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);
        BaseApplication.getApplication().getUserComponent().plus(new CModule()).inject(this);
        textView = (TextView) findViewById(R.id.textViewC);
        textView.setText(user.toString());
    }

    public void cleanUser(View v) {
        BaseApplication.getApplication().releaseUserComponent();
    }

}
