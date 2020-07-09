package com.demo.motion.core;



import com.demo.motion.commmon.utils.Configuration;

import java.text.DecimalFormat;
import java.util.List;

public class WeightDBHelper {
    
    public static boolean isEmpty() {
        return WeightDB.getInstance().size()==0;
    }
    
    public static boolean isEmpty(int year) {
        DateHelper.DatePeriod period = DateHelper.getYearPeriod(year);
        String condition = WeightDB.getInstance().makeCondition(period.begin,period.end);
        return WeightDB.getInstance().size(condition)==0;  
    }
    
    public static boolean isEmpty(int year,int month) {
        DateHelper.DatePeriod period = DateHelper.getMonthPeriod(year,month);
        String condition = WeightDB.getInstance().makeCondition(period.begin,period.end);
        return WeightDB.getInstance().size(condition)==0;
    }
    
    public static boolean isEmpty(int year,int month,int day) {
        DateHelper.DatePeriod period = DateHelper.getDatePeriod(year,month,day);
        String condition = WeightDB.getInstance().makeCondition(period.begin,period.end);
        return WeightDB.getInstance().size(condition)==0;
    }

    public static Double getWeightAverage(int year) {
        DateHelper.DatePeriod period = DateHelper.getYearPeriod(year);
        return getWeightAverage(period);
    }
    
    public static Double getWeightAverage(int year, int month) {
        DateHelper.DatePeriod period = DateHelper.getMonthPeriod(year,month);
        return getWeightAverage(period);
    }
    
    public static Double getWeightAverage(int year, int month, int day) {
        DateHelper.DatePeriod period = DateHelper.getDatePeriod(year,month,day);
        return getWeightAverage(period);
    }
    
    public static Double getWeightReduceThisWeek() {
        DateHelper.DatePeriod period = DateHelper.getWeekPeriod(DateHelper.getToday());
        String condition = WeightDB.getInstance().makeCondition(period.begin,period.end);
        List<WeightDB.Weight> weights = WeightDB.getInstance().query(condition);
        if (weights.size() < 2) {
            return 0.0;
        }
        return Double.valueOf(weights.get(0).value) - Double.valueOf(weights.get(weights.size()-1).value);
    }
    
    public static Double getWeightReduceThisMonth() {
        DateHelper.DatePeriod period = DateHelper.getMonthPeriod(DateHelper.getToday());
        String condition = WeightDB.getInstance().makeCondition(period.begin,period.end);
        List<WeightDB.Weight> weights = WeightDB.getInstance().query(condition);
        if (weights.size() < 2) {
            return 0.0;
        }
        return Double.valueOf(weights.get(0).value) - Double.valueOf(weights.get(weights.size()-1).value);
    }
    
    public static Double getWeightAverage(DateHelper.DatePeriod period) {
        String condition = WeightDB.getInstance().makeCondition(period.begin,period.end);
        List<WeightDB.Weight> weights = WeightDB.getInstance().query(condition);
        Double average = 0.0;
        if (weights.isEmpty()) {
            return average;
        }
        for (int i=0; i<weights.size(); i++) {
            average += Double.valueOf(weights.get(i).value);
        }           
        average = average/weights.size();         
        DecimalFormat format = new DecimalFormat("0.00");
        average = Double.valueOf(format.format(average).toString());
        return average;
    }
    
    public static boolean isYestdayRecord() {
        DateHelper.DatePeriod period = DateHelper.getDatePeriod(DateHelper.getYestday());
        String condition = WeightDB.getInstance().makeCondition(period.begin,period.end);
        List<WeightDB.Weight> weights = WeightDB.getInstance().query(condition);
        if (weights.isEmpty()) {            
            return false;
        }
        return true;
    }
    
    public static boolean isTodayRecord() {
        DateHelper.DatePeriod period = DateHelper.getDatePeriod(DateHelper.getToday());
        String condition = WeightDB.getInstance().makeCondition(period.begin,period.end);
        List<WeightDB.Weight> weights = WeightDB.getInstance().query(condition);
        if (weights.isEmpty()) {            
            return false;
        }
        return true;
    }
    
    public static int getContinuousDays() {
        if (!isYestdayRecord()) {
            if (isTodayRecord()) {
                Configuration.setContinousDays(1);
            }
            else {
                Configuration.setContinousDays(0);
            }
        }
        return Configuration.getContinousDays();
    }
    
    public static void addContinuousDays() {
        if (!isTodayRecord()) {
            int continuous = Configuration.getContinousDays();
            Configuration.setContinousDays(++continuous);
        }
    }
    
}
