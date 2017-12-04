package com.wke.gql.testCollapsing.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.wke.gql.R;

public class BehaviorOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior_one);
        findViewById(R.id.activity_behaviorone_btn).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        v.setX(event.getRawX() - v.getWidth() / 2);
                        v.setY(event.getRawY() - v.getHeight() / 2);
                        break;
                }
                return false;
            }
        });
    }
}
