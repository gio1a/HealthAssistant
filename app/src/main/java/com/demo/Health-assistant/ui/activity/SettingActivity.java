package com.demo.motion.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.demo.motion.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends AppCompatActivity {


    @BindView(R.id.reBack)
    RelativeLayout reBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //   getActionBar().setDisplayHomeAsUpEnabled(true);
        // getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_bg));
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

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
