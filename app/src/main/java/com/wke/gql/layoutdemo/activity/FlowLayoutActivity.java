package com.wke.gql.layoutdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.wke.gql.R;
import com.wke.gql.layoutdemo.activity.bean.SearchHotItem;
import com.wke.gql.view.FlowLayout;

import java.util.ArrayList;
import java.util.List;

@Route(path = "/wkegql/flowlayout")
public class FlowLayoutActivity extends AppCompatActivity {
    private FlowLayout flowLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout);
        flowLayout = (FlowLayout) findViewById(R.id.fhll);
        init();
    }


    void init() {
        List<SearchHotItem> items = new ArrayList<>();

        SearchHotItem item1 = new SearchHotItem();
        List<SearchHotItem.Content> contents = new ArrayList<>();
        SearchHotItem.Content t1 = item1.new Content();
        SearchHotItem.Content t2 = item1.new Content();
        SearchHotItem.Content t3 = item1.new Content();
        t1.title = "航班动态aaa";
        t2.title = "航班动态aaaaaaaaa";
        t3.title = "航班动态aaa";
        contents.add(t1);
        contents.add(t2);
        contents.add(t3);
        item1.setHotLabel(SearchHotItem.HotLabel.CONSULT);
        item1.setTags(contents);

        List<SearchHotItem.Content> contents1 = new ArrayList<>();
        SearchHotItem item2 = new SearchHotItem();
        SearchHotItem.Content t11 = item2.new Content();
        SearchHotItem.Content t12 = item2.new Content();
        SearchHotItem.Content t13 = item2.new Content();
        SearchHotItem.Content t14 = item2.new Content();
        SearchHotItem.Content t15 = item2.new Content();
        SearchHotItem.Content t16 = item2.new Content();
        t11.title = "航班动态1";
        t12.title = "航班动态2";
        t13.title = "航班动态3";
        t14.title = "航班动态4";
        t15.title = "航班动态5";
        t16.title = "航班动态6";
        contents1.add(t11);
        contents1.add(t12);
        contents1.add(t13);
        contents1.add(t14);
        contents1.add(t15);
        contents1.add(t16);
        item2.setHotLabel(SearchHotItem.HotLabel.SERVICE);
        item2.setTags(contents1);
        items.add(item1);
        items.add(item2);
        for (SearchHotItem hotItem : items) {
            List<SearchHotItem.Content> tags = hotItem.getTags();
            for (SearchHotItem.Content tag : tags) {
//            TextView textView =new TextView(mContext);
//            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
//            layoutParams.gravity= Gravity.CENTER;
//            textView.setEllipsize(TextUtils.TruncateAt.END);
//            textView.setSingleLine();
//            layoutParams.setMargins(UtilTool.dip2px(5),0,UtilTool.dip2px(5),0);
//            textView.setPadding(UtilTool.dip2px(10),UtilTool.dip2px(7),UtilTool.dip2px(10),UtilTool.dip2px(7));
//            textView.setBackgroundColor(Color.parseColor("#ffeef2f5"));
//            textView.setLayoutParams(layoutParams);

                TextView textView = (TextView) LayoutInflater.from(this).inflate(R.layout.layout_text, null);
                textView.setText(tag.title);
                textView.setTag(tag.title);
                Log.i("FlowHorizontalLayout", "add " + tag.title);
                flowLayout.addView(textView);
            }
        }
    }
}
