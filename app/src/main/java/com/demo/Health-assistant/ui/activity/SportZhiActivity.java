package com.demo.motion.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import com.demo.motion.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SportZhiActivity extends AppCompatActivity {

    @BindView(R.id.reBack)
    RelativeLayout reBack;

    private String type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getIntent().getStringExtra("What");
        if (type.equals("ONE")) {
            setContentView(R.layout.activity_sport_zhi);
        } else if (type.equals("TWO")) {
            setContentView(R.layout.activity_sport_two);
        } else if (type.equals("THREE")) {
            setContentView(R.layout.activity_sport_three);
        } else if (type.equals("FOUR")) {
            setContentView(R.layout.activity_sport_four);
        } else if (type.equals("FIVE")) {
            setContentView(R.layout.activity_sport_five);
        } else if (type.equals("SIX")) {
            setContentView(R.layout.activity_sport_six);
        } else if (type.equals("SEVEN")) {
            setContentView(R.layout.activity_sport_seven);
        } else if (type.equals("EIGHT")) {
            setContentView(R.layout.activity_sport_eight);
        }
        ButterKnife.bind(this);
    }

    @OnClick(R.id.reBack)
    public void onClick() {
        //返回
        finish();
    }


}
