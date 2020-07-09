package com.demo.motion.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.demo.motion.MyApplication;
import com.demo.motion.R;
import com.demo.motion.commmon.utils.MySp;
import com.demo.motion.commmon.utils.Utils;
import com.demo.motion.ui.BaseActivity;

public class MoreActivity extends BaseActivity implements View.OnClickListener {

    private Button exit;

    @Override
    public int getLayoutId() {
        return R.layout.activity_more;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        addSettingItem(R.id.MoreAccount, getString(R.string.more_account));

        addSettingItem(R.id.MoreBackupOrRestore, getString(R.string.more_backup_restore));
        addSettingItem(R.id.MoreSetting, getString(R.string.more_setting));

        addSettingItem(R.id.MoreWhatBMI, getString(R.string.more_what_bmi));
        addSettingItem(R.id.MoreWhatBMR, getString(R.string.more_what_bmr));

        exit = (Button) findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //退出
              logOut();
            }
        });
    }
    private void logOut() {
        SPUtils.getInstance().put(MySp.ISLOGIN, false);
        MyApplication.exitActivity();
        Utils.showToast(context, "退出登陆成功!");

        Intent it = new Intent(context, LoginActivity.class);
        context.startActivity(it);
    }
    @Override
    public void initListener() {

    }

    protected void addSettingItem(int layout_id, String title) {
        RelativeLayout layout = (RelativeLayout) findViewById(layout_id);
        ((TextView) layout.findViewById(R.id.SettingItemTitle)).setText(title);
        layout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.MoreAccount:
                intent = new Intent(this, PersonalActivity.class);
                startActivity(intent);
                break;
            case R.id.MoreBackupOrRestore:
                break;
            case R.id.MoreSetting:
                intent = new Intent(this, SettingActivity.class);
                startActivity(intent);
                break;
            case R.id.MoreWhatBMI:
                intent = new Intent(this, WhatActivity.class);
                intent.putExtra("What", "BMI");
                startActivity(intent);
                break;
            case R.id.MoreWhatBMR:
                intent = new Intent(this, WhatActivity.class);
                intent.putExtra("What", "BMR");
                startActivity(intent);
                break;

            default:
                return;
        }
    }

}
