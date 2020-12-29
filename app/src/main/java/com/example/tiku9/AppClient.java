package com.example.tiku9;

import android.app.Application;

import com.example.tiku9.been.YZ;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class AppClient extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }
    private List<YZ> yzs=new ArrayList<>();
    public  List<YZ> getYzs(){
        return yzs;
    }
}
