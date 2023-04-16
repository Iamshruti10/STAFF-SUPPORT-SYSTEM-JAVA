package com.example.staffsupportsystem;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

public class StudentItem {
    private int roll;
    private String name;

    public StudentItem(String roll, String name) {

    }

    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

    private  long sid;
    private String status;


    public StudentItem(long sid, int roll, String name) {
        this.sid = sid;
        this.roll = roll;
        this.name = name;
        status="";
    }

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
