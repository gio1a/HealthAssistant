package com.demo.motion.chart;


import com.demo.motion.commmon.utils.CommonUtil;
import com.demo.motion.core.WeightDBHelper;

public class MonthChartAdapter extends ChartAdapter {

	private int mChartYear;
	
	public MonthChartAdapter(int year) {		
		mChartYear = year;
	}

	@Override
	public boolean isEmpty() {	
	    return WeightDBHelper.isEmpty(mChartYear);
	}
	
	@Override
	public double getYScale(int month) {					    
        return WeightDBHelper.getWeightAverage(mChartYear, month-1);
	}

	@Override
	public int getMinXScale() {		
		return CommonUtil.MinMonth;
	}

	@Override
	public int getMaxXScale() {		
		return CommonUtil.MaxMonth;
	}

    @Override
    public String getXScaleUnit() {
        return CommonUtil.MonthUnit;
    }
	
}
