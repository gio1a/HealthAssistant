package com.demo.motion.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo.motion.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WhatActivity extends AppCompatActivity {

    @BindView(R.id.reBack)
    RelativeLayout reBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // getActionBar().setDisplayHomeAsUpEnabled(true);
        //  getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_bg));
        setContentView(R.layout.activity_what);
        ButterKnife.bind(this);

        String what = getIntent().getExtras().getString("What");
        tvTitle.setText(what );
        if (what == null) {
            return;
        }

        WebView what_content = (WebView) findViewById(R.id.WhatWebView);
        what_content.getSettings().setDefaultTextEncodingName("gbk");
        if ("BMI".equals(what)) {
            //getActionBar().setTitle(getString(R.string.more_what_bmi));
            what_content.loadUrl("file:///android_asset/" + "bmi.html");
        } else if ("BMR".equals(what)) {
            // getActionBar().setTitle(getString(R.string.more_what_bmr));
            what_content.loadUrl("file:///android_asset/" + "bmr.html");
        } else {
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
            default:
                break;
        }
        return true;
    }

    @OnClick(R.id.reBack)
    public void onClick() {
        finish();
    }
}
