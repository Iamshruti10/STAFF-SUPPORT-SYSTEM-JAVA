package com.example.staffsupportsystem;

public class submissionItem {

    String Sub_name;
    String date;

    public submissionItem(String sub_name, String date) {
        Sub_name = sub_name;
        this.date = date;
    }
    public submissionItem(long sid,String sub_name, String date) {


    }



    public String getSub_name() {
        return Sub_name;
    }

    public void setSub_name(String sub_name) {
        Sub_name = sub_name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
   String status;

    public submissionItem(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
