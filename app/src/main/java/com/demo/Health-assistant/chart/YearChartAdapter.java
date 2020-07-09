package com.demo.motion.chart;


import com.demo.motion.commmon.utils.CommonUtil;
import com.demo.motion.core.WeightDBHelper;

public class YearChartAdapter extends ChartAdapter {

	@Override
	public boolean isEmpty() {			
	    return WeightDBHelper.isEmpty();
	}
	
	@Override
	public double getYScale(int year) {			    
        return WeightDBHelper.getWeightAverage(year);
	}

	@Override
	public int getMinXScale() {
		return CommonUtil.MinYear;
	}

	@Override
	public int getMaxXScale() {
		return CommonUtil.MaxYear;
	}

    @Override
    public String getXScaleUnit() {
        return CommonUtil.YearUnit;
    }
	
}
