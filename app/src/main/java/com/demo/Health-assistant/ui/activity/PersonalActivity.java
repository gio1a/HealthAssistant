package com.demo.motion.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.demo.motion.R;
import com.demo.motion.commmon.bean.EventBusBean;
import com.demo.motion.commmon.utils.CommonUtil;
import com.demo.motion.commmon.utils.Configuration;
import com.demo.motion.commmon.utils.SharedPrefsUtils;
import com.demo.motion.ui.weight.RoundCornerDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonalActivity extends AppCompatActivity implements DatePicker.OnDateChangedListener {
    public static final String TITLE = "个人信息";
    public static final String SAVE_MSG = "信息保存成功!";
    RoundCornerDialog roundCornerDialog;

    @BindView(R.id.WidgetInputTitle)
    TextView WidgetInputTitle;
    @BindView(R.id.WidgetInputEdit)
    EditText WidgetInputEdit;
    @BindView(R.id.WidgetHeightInputTitle)
    TextView WidgetHeightInputTitle;
    @BindView(R.id.HeartInputEdit)
    EditText HeartInputEdit;
    @BindView(R.id.YaoInputEdit)
    EditText YaoInputEdit;
    @BindView(R.id.XiongInputEdit)
    EditText XiongInputEdit;
    private RadioGroup mRadioGroup;
    private DatePicker mDatePicker;
    private TextView mBirthdayText, mBirthdayTitle;
    private EditText mHeightEditText;
    String birthday;
    private RelativeLayout reBack;
    private String sex;
    //接受处理消息
    private Handler handler = new Handler(new Handler.Callback() {//暂时先让秒数动起来

        @Override
        public boolean handleMessage(Message arg0) {
            if (arg0.what == 1) {
                mBirthdayText.setText(birthday);
            }
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //   getActionBar().setDisplayHomeAsUpEnabled(true);
        //   getActionBar().setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar_bg));
        setContentView(R.layout.activity_personal);
        ButterKnife.bind(this);
        //    getActionBar().setTitle(TITLE);
        mBirthdayTitle = (TextView) findViewById(R.id.BirthdayTitle);
        mRadioGroup = (RadioGroup) findViewById(R.id.PersonalSex);
        mDatePicker = (DatePicker) findViewById(R.id.PersonalAgePicker);
        mBirthdayText = (TextView) findViewById(R.id.BirthdayValue);
        mHeightEditText = (EditText) findViewById(R.id.WidgetHeightInputEdit);
        reBack = (RelativeLayout) findViewById(R.id.reBack);
        int year, month, day;

        year = Configuration.getUserBirthdayYear();
        month = Configuration.getUserBirthdayMonth();
        day = Configuration.getUserBirthdayDay();

        mDatePicker.init(year, month, day, this);
        mDatePicker.setMaxDate(Calendar.getInstance().getTimeInMillis());

        String birthday = year + "-" + month + "-" + day;
        mBirthdayText.setText(birthday);

        int sex = Configuration.getUserSex();
        if (sex == CommonUtil.UserSexFemale) {
            mRadioGroup.check(R.id.RadioFemale);
        }

        if (Configuration.getUserHeight() != 0.0) {
            String height = String.valueOf(Configuration.getUserHeight());
            mHeightEditText.setText(height);
        }

        mBirthdayTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteDialog();

            }
        });
        reBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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

    public void onClickSaveInfo(View v) {
        if (R.id.RadioMale == mRadioGroup.getCheckedRadioButtonId()) {
            sex="男";
        } else {
            sex="nv";
        }
        if (TextUtils.isEmpty(sex)) {
            Toast.makeText(PersonalActivity.this, "性别不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(mHeightEditText.getText().toString())) {
            Toast.makeText(PersonalActivity.this, "身高不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(WidgetInputEdit.getText().toString())) {
            Toast.makeText(PersonalActivity.this, "体重不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(HeartInputEdit.getText().toString())) {
            Toast.makeText(PersonalActivity.this, "心率不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(YaoInputEdit.getText().toString())) {
            Toast.makeText(PersonalActivity.this, "腰围不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }


        if (TextUtils.isEmpty(XiongInputEdit.getText().toString())) {
            Toast.makeText(PersonalActivity.this, "胸围不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(birthday)) {
            Toast.makeText(PersonalActivity.this, "生日不能为空!", Toast.LENGTH_SHORT).show();
            return;
        }
        //存储数据
        SharedPrefsUtils.remove(PersonalActivity.this);
        SharedPrefsUtils.putString(PersonalActivity.this,"SEX",sex);
        SharedPrefsUtils.putString(PersonalActivity.this,"HEIGHT",mHeightEditText.getText().toString());
        SharedPrefsUtils.putString(PersonalActivity.this,"WEIGHT",WidgetInputEdit.getText().toString());
        SharedPrefsUtils.putString(PersonalActivity.this,"HEART",HeartInputEdit.getText().toString());
        SharedPrefsUtils.putString(PersonalActivity.this,"YAO",YaoInputEdit.getText().toString());
        SharedPrefsUtils.putString(PersonalActivity.this,"XIONG",XiongInputEdit.getText().toString());
        SharedPrefsUtils.putString(PersonalActivity.this,"BIRTHDAY",birthday);
        Toast.makeText(this, SAVE_MSG, Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(
                new EventBusBean("1"));
        finish();
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        monthOfYear = monthOfYear + 1;
        birthday = year + "-" + monthOfYear + "-" + dayOfMonth;

        Log.e("生日", "====" + birthday);
    }


    private void showDeleteDialog() {
        View view = View.inflate(PersonalActivity.this, R.layout.dialog_two_btn, null);
        roundCornerDialog = new RoundCornerDialog(PersonalActivity.this, 0, 450, view, R.style.RoundCornerDialog);
        roundCornerDialog.show();
        roundCornerDialog.setCanceledOnTouchOutside(false);// 设置点击屏幕Dialog不消失
        roundCornerDialog.setOnKeyListener(keylistener);//设置点击返回键Dialog不消失

        mDatePicker = (DatePicker) view.findViewById(R.id.PersonalAgePicker);
        TextView tv_logout_confirm = (TextView) view.findViewById(R.id.tv_logout_confirm);
        TextView tv_logout_cancel = (TextView) view.findViewById(R.id.tv_logout_cancel);
        mDatePicker.setVisibility(View.VISIBLE);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        mDatePicker.init(year, monthOfYear, dayOfMonth, this);

        //确定
        tv_logout_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler.sendEmptyMessage(1);
                if (roundCornerDialog.isShowing()) {
                    roundCornerDialog.dismiss();
                }
            }
        });
        //取消
        tv_logout_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roundCornerDialog.dismiss();
            }
        });
    }

    DialogInterface.OnKeyListener keylistener = new DialogInterface.OnKeyListener() {
        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                return true;
            } else {
                return false;
            }
        }
    };

}
