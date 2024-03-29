package com.demo.motion.ui.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.demo.motion.MyApplication;
import com.demo.motion.R;
import com.demo.motion.commmon.utils.MySp;
import com.demo.motion.commmon.utils.UIHelper;
import com.demo.motion.commmon.utils.Utils;
import com.demo.motion.ui.BaseActivity;
import com.demo.motion.ui.permission.PermissionHelper;
import com.demo.motion.ui.permission.PermissionListener;
import com.demo.motion.ui.weight.CountDownProgressView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

import butterknife.BindView;

/**
 * 闪屏界面
 */
public class SplashActivity extends BaseActivity {

    @BindView(R.id.im_url)
    ImageView imUrl;
    @BindView(R.id.countdownProgressView)
    CountDownProgressView countDownProgressView;
    @BindView(R.id.versions)
    TextView versions;

    /**
     * 上次点击返回键的时间
     */
    private long lastBackPressed;
    /**
     * 上次点击返回键的时间
     */
    private static final int QUIT_INTERVAL = 3000;

    // 要申请的权限
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION};

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        if (ImmersionBar.hasNavigationBar(this)) {
            ImmersionBar.with(this).transparentNavigationBar().init();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initData(Bundle savedInstanceState) {

        imUrl.setImageResource(R.mipmap.splash_bg);

        versions.setText(UIHelper.getString(R.string.splash_appversionname, MyApplication.getAppVersionName()));

        countDownProgressView.setTimeMillis(2000);
        countDownProgressView.setProgressType(CountDownProgressView.ProgressType.COUNT_BACK);
        countDownProgressView.start();
//        Log.e("sha1","===="+sHA1(SplashActivity.this));

    }

    @Override
    public void initListener() {
        countDownProgressView.setOnClickListener(v -> {

            countDownProgressView.stop();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                // 获取权限
                PermissionHelper.requestPermissions(this, PERMISSIONS_STORAGE, new PermissionListener() {
                    @Override
                    public void onPassed() {
                        startActivity();
                    }
                });
            } else {
                startActivity();
            }
        });

        countDownProgressView.setProgressListener(progress -> {
            if (progress == 0) {
                // 版本判断。当手机系统大于 23 时，才有必要去判断权限是否获取
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    // 获取权限
                    PermissionHelper.requestPermissions(this, PERMISSIONS_STORAGE,
                            getResources().getString(R.string.app_name) + "需要获取存储、位置权限", new PermissionListener() {
                                @Override
                                public void onPassed() {
                                    startActivity();
                                }
                            });
                } else {
                    startActivity();
                }
            }
        });
    }

    public void startActivity() {
        if (SPUtils.getInstance().getBoolean(MySp.ISLOGIN)) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        } else {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        }
        countDownProgressView.stop();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            if (keyCode == KeyEvent.KEYCODE_BACK) { // 表示按返回键 时的操作
                long backPressed = System.currentTimeMillis();
                if (backPressed - lastBackPressed > QUIT_INTERVAL) {
                    lastBackPressed = backPressed;
                    Utils.showToast(SplashActivity.this, "再按一次退出");
                } else {
                    if (countDownProgressView != null) {
                        countDownProgressView.stop();
                        countDownProgressView.clearAnimation();
                    }
                    moveTaskToBack(false);
                    MyApplication.closeApp(this);
                    finish();
                }
            }
        }
        return false;
    }

    public static String sHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
            }
            return hexString.toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}
