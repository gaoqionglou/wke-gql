package com.wke.gql;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.wke.gql.base.BaseApplication;
import com.wke.gql.dagger2.User;
import com.wke.gql.dagger2.module.AModule;

import javax.inject.Inject;

public class AActivity extends AppCompatActivity {
    @Inject
    User user;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        BaseApplication.getApplication().getUserComponent().plus(new AModule()).inject(this);
        textView = (TextView) findViewById(R.id.textViewA);
        textView.setText(user.toString());
    }

    public void goB(View v) {
        startActivity(new Intent(this, BActivity.class));
    }
}
