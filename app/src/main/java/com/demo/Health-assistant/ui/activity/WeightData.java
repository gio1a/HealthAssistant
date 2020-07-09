package com.demo.motion.ui.activity;



import com.demo.motion.commmon.utils.CommonUtil;
import com.demo.motion.commmon.utils.Configuration;
import com.demo.motion.core.DateHelper;
import com.demo.motion.core.WeightDB;

import java.text.DecimalFormat;

public class WeightData {

	private static final String WEIGHT_SUFFIX = "kg";
	private static final String BMI_PREFIX = "BMI: ";
	
	public String mWeightValue;
	public long mRecordDate;

	public WeightData(WeightDB.Weight weight) {
	    mWeightValue = weight.value;
	    mRecordDate  = weight.date;
	}
	
	public String getWeightValue() {
	    return mWeightValue;
	}
	
	public String getWeightStr() {
		String result = String.format("%-4s", mWeightValue);
		return (result + " " + WEIGHT_SUFFIX);
	}
	
	public String getBMIStr() {
		return (BMI_PREFIX + getBMIValue());
	}
	
	public String getWeekStr() {
        return DateHelper.getWeekStr(mRecordDate);
    }
	
	public String getDateStr() {
		return DateHelper.getDateStr(mRecordDate);
	}		
	
	public String getBMIValue() {
        double height = Configuration.getUserHeight();
        if (height == 0.0) {
            return "0.00";
        }               
        double bmi = CommonUtil.calculateBMI(Double.valueOf(mWeightValue), height);
        return new DecimalFormat("0.00").format(bmi).toString();
    }
}
