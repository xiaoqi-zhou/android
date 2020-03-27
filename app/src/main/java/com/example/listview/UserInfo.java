package com.example.listview;

import android.content.Intent;

import java.io.Serializable;

public class UserInfo implements Serializable,Comparable<UserInfo> {
    private String mUserName;
    private  String mDate;

    public String getComplete() {
        return complete;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }

    private String complete;

    public String getmDate() {
        return mDate;
    }
    public void setmDate(String mDate) {
        this.mDate = mDate;
    }
    public String getmUserName() {
        return mUserName;
    }
    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public UserInfo(String userName, String Date,String complete)
    {
        mUserName=userName;
        mDate=Date;
        this.complete = complete;
    }

    @Override
    public int compareTo(UserInfo o) {
        String data_to = o.getmDate();
        String[] split_to = data_to.split("/");
        String[] split_m = this.mDate.split("/");
        for(int i=0;i<3;i++){
            if(Integer.parseInt(split_to[i])>Integer.parseInt(split_m[i]))return 1;
            if(Integer.parseInt(split_to[i])<Integer.parseInt(split_m[i]))return -1;
        }
        return 0;
    }
}
