package com.demo.motion.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.demo.motion.R;
import com.demo.motion.commmon.bean.EventBusBean;
import com.demo.motion.commmon.utils.CommonUtil;
import com.demo.motion.commmon.utils.SharedPrefsUtils;
import com.demo.motion.ui.BaseActivity;
import com.demo.motion.widget.SquareItemWidget;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class SquareActivity extends BaseActivity {
    public static final String BMI_THIN_WEIGHT = "偏瘦";
    public static final String BMI_NICE_WEIGHT = "完美";
    public static final String BMI_FAT_WEIGHT = "偏胖";
    public static final String BMI_BIG_WEIGHT = "肥胖";

    private SquareItemWidget mWeightItem;
    private SquareItemWidget mBMIItem;
    private SquareItemWidget mBMRItem;
    private SquareItemWidget mResultItem;
    private SquareItemWidget mDestWeight;
    private TextView mPersonalTips;


    @Override
    public int getLayoutId() {
        return R.layout.activity_square;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mPersonalTips = (TextView) findViewById(R.id.PersionalTips);
        mWeightItem = new SquareItemWidget(findViewById(R.id.SquareWeightItem),
                getString(R.string.square_weight));
        mBMIItem = new SquareItemWidget(findViewById(R.id.SquareBMIItem),
                getString(R.string.square_bmi));
        mBMRItem = new SquareItemWidget(findViewById(R.id.SquareBMRItem),
                getString(R.string.square_bmr));
        mResultItem = new SquareItemWidget(findViewById(R.id.SquareResult),
                getString(R.string.square_result));
        mDestWeight = new SquareItemWidget(findViewById(R.id.SquareDestWeight),
                getString(R.string.square_dest_weight));
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this)) {//加上判断
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void initListener() {

    }

    public void onResume() {
        super.onResume();
        onSquareUpdate();
    }

    protected void onSquareUpdate() {
        String sex = SharedPrefsUtils.getString(SquareActivity.this, "SEX");
        String height = SharedPrefsUtils.getString(SquareActivity.this, "HEIGHT");
        String weight = SharedPrefsUtils.getString(SquareActivity.this, "WEIGHT");
        String heart = SharedPrefsUtils.getString(SquareActivity.this, "HEART");
        String yao = SharedPrefsUtils.getString(SquareActivity.this, "YAO");
        String xiong = SharedPrefsUtils.getString(SquareActivity.this, "XIONG");
        String birthday = SharedPrefsUtils.getString(SquareActivity.this, "BIRTHDAY");
        //判断是否有体重数据

        if (weight == null) {
            mPersonalTips.setText(getString(R.string.no_data));
            mPersonalTips.setVisibility(View.VISIBLE);
            mWeightItem.setItemValue("");
            mBMIItem.setItemValue("");
            mBMRItem.setItemValue("");
            mDestWeight.setItemValue("");
            mResultItem.setItemValue("");

        }else {
            mWeightItem.setItemValue(weight+"kg");
            mBMIItem.setItemValue(heart+"次/分");
            mBMRItem.setItemValue(yao+"cm");
            mResultItem.setItemValue(xiong+"cm");
            mDestWeight.setItemValue(height+"cm");
        }

        //判断个人信息是否填写好了

        if (TextUtils.isEmpty(weight)) {
            mPersonalTips.setText(getString(R.string.personalTips));
            mPersonalTips.setVisibility(View.VISIBLE);
        } else {
            mPersonalTips.setVisibility(View.GONE);
        }

        //显示BMI指标分析结果
       // WeightData weightdata = new WeightData(weight);
//        mWeightItem.setItemValue(weight);
//        String bmi = weightdata.getBMIValue();
//        if (!"".equals(bmi)) {
//            mBMIItem.setItemValue(bmi);
//            double BMI = Double.valueOf(bmi);
//            if (BMI < 18.5) {
//                mResultItem.setItemValue(BMI_THIN_WEIGHT);
//            } else if (BMI >= 18.5 && BMI < 24) {
//                mResultItem.setItemValue(BMI_NICE_WEIGHT);
//            } else if (BMI >= 24 && BMI < 28) {
//                mResultItem.setItemValue(BMI_FAT_WEIGHT);
//            } else {
//                mResultItem.setItemValue(BMI_BIG_WEIGHT);
//            }
//        }

//        //显示目标体重
//        if (height != 0) {
//            double HEIGHT = Double.valueOf(height);
//            String destWeight = CommonUtil.calculateMinWeight(HEIGHT) + "kg" +
//                    "～" + CommonUtil.calculateMaxWeight(HEIGHT) + "kg";
//            mDestWeight.setItemValue(destWeight);
//        }

        //显示BMR
//        int bmr_age = Configuration.getUserAge();
//        double bmr_height = Configuration.getUserHeight();
//        int bmr_sex = Configuration.getUserSex();
//        if (bmr_age != 0 && !"".equals(weight) && bmr_height != 0) {
//            double bmr_weight = Double.valueOf(weightdata.getWeightValue());
//            String bmrStr = CommonUtil.calculateBMR(bmr_sex, bmr_weight, bmr_height, bmr_age) + "千卡";
//            mBMRItem.setItemValue(bmrStr);
//        }
    }

    @Subscribe
    public void onEventMainonEventMainThreadThread(EventBusBean event) {

        if (event.getTag().equals("1")) {
            //获取数据
            String weight = SharedPrefsUtils.getString(SquareActivity.this, "WEIGHT");
            String sex = SharedPrefsUtils.getString(SquareActivity.this, "SEX");
            String height = SharedPrefsUtils.getString(SquareActivity.this, "HEIGHT");
            String heart = SharedPrefsUtils.getString(SquareActivity.this, "HEART");
            String yao = SharedPrefsUtils.getString(SquareActivity.this, "YAO");
            String xiong = SharedPrefsUtils.getString(SquareActivity.this, "XIONG");
            mWeightItem.setItemValue(weight+"kg");
            mBMIItem.setItemValue(heart+"次/分");
            mBMRItem.setItemValue(yao+"cm");
            mDestWeight.setItemValue(xiong+"cm");
            mResultItem.setItemValue(height+"cm");
        }
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
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this))//加上判断
            EventBus.getDefault().unregister(this);
    }
}
