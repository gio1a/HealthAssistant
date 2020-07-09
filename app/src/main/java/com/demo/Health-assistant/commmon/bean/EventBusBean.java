package com.demo.motion.commmon.bean;

public class EventBusBean {
    private String tag;


    public EventBusBean(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
