package com.demo.motion.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo.motion.R;
import com.demo.motion.commmon.utils.CommonUtil;
import com.demo.motion.ui.BaseActivity;

public class ChartActivity extends BaseActivity implements View.OnClickListener{

    @Override
    public int getLayoutId() {
        return R.layout.activity_chart;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        addSettingItem(R.id.tv_one, getString(R.string.more_one));
        addSettingItem(R.id.tv_two, getString(R.string.more_two));
        addSettingItem(R.id.tv_three, getString(R.string.more_three));
        addSettingItem(R.id.tv_four, getString(R.string.more_four));
        addSettingItem(R.id.tv_five, getString(R.string.more_five));
        addSettingItem(R.id.tv_six, getString(R.string.more_six));
        addSettingItem(R.id.tv_seven, getString(R.string.more_seven));
        addSettingItem(R.id.tv_eight, getString(R.string.more_eight));

    }
    protected void addSettingItem(int layout_id, String title) {
        RelativeLayout layout = (RelativeLayout) findViewById(layout_id);
        ((TextView) layout.findViewById(R.id.SettingItemTitle)).setText(title);
        layout.setOnClickListener(this);
    }
    @Override
    public void initListener() {

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {

        super.onPause();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!CommonUtil.onExitProcess(this)) {
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {

            case R.id.tv_one:
                intent = new Intent(this, SportZhiActivity.class);
                intent.putExtra("What", "ONE");
                startActivity(intent);
                break;
            case R.id.tv_two:
                intent = new Intent(this, SportZhiActivity.class);
                intent.putExtra("What", "TWO");
                startActivity(intent);
                break;
            case R.id.tv_three:
                intent = new Intent(this, SportZhiActivity.class);
                intent.putExtra("What", "THREE");
                startActivity(intent);
                break;
            case R.id.tv_four:
                intent = new Intent(this, SportZhiActivity.class);
                intent.putExtra("What", "FOUR");
                startActivity(intent);
                break;
            case R.id.tv_five:
                intent = new Intent(this, SportZhiActivity.class);
                intent.putExtra("What", "FIVE");
                startActivity(intent);
                break;
            case R.id.tv_six:
                intent = new Intent(this, SportZhiActivity.class);
                intent.putExtra("What", "SIX");
                startActivity(intent);
                break;
            case R.id.tv_seven:
                intent = new Intent(this, SportZhiActivity.class);
                intent.putExtra("What", "SEVEN");
                startActivity(intent);
                break;
            case R.id.tv_eight:
                intent = new Intent(this, SportZhiActivity.class);
                intent.putExtra("What", "EIGHT");
                startActivity(intent);
                break;
            default:
                return;
        }
    }
}
