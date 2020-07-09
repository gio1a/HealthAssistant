package com.demo.motion.chart;

import com.demo.motion.commmon.utils.CommonUtil;
import com.demo.motion.core.WeightDBHelper;

public class DayChartAdapter extends ChartAdapter {

	private int mChartYear;
	private int mChartMonth;
	
	public DayChartAdapter(int year,int month) {	
		mChartYear  = year;
		mChartMonth = month;		
	}
	
	@Override
	public boolean isEmpty() {
	    return WeightDBHelper.isEmpty(mChartYear, mChartMonth);
	}

	@Override
	public double getYScale(int day) {	
        return WeightDBHelper.getWeightAverage(mChartYear,mChartMonth,day);
	}

	@Override
	public int getMinXScale() {		
		return CommonUtil.MinDay;
	}

	@Override
	public int getMaxXScale() {		
		return CommonUtil.MaxDay;
	}

    @Override
    public String getXScaleUnit() {
        return CommonUtil.DayUnit;
    }
}
