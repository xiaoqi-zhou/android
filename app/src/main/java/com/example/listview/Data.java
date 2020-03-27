package com.example.listview;

import java.util.ArrayList;
import java.util.List;

public class Data {

    private static List<UserInfo> infos = new ArrayList<>();

    public static void setInfos(List<UserInfo> infos){
        infos = infos;
    }

    public static List<UserInfo> getInfo(){
        return infos;
    }
}
