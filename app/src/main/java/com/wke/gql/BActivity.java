package com.wke.gql;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.wke.gql.base.BaseApplication;
import com.wke.gql.dagger2.User;
import com.wke.gql.dagger2.module.BModule;

import javax.inject.Inject;

public class BActivity extends AppCompatActivity {
    @Inject
    User user;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        BaseApplication.getApplication().getUserComponent().plus(new BModule()).inject(this);
        textView = (TextView) findViewById(R.id.textViewB);
        textView.setText(user.toString());
    }

    public void goC(View v) {
        startActivity(new Intent(this, CActivity.class));
    }
}
