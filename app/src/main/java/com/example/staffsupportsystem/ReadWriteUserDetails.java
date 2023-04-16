package com.example.staffsupportsystem;

public class ReadWriteUserDetails {
    public String Fullname , email, phone;

    public ReadWriteUserDetails(){};

    public ReadWriteUserDetails(String fullnametxt, String emailtxt, String phonetxt){
      this.Fullname = fullnametxt;
      this.email = emailtxt;
      this.phone = phonetxt;
}}
