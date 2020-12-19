package com.example.tiku9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tiku9.net.OkHttpLo;
import com.example.tiku9.net.OkHttpTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ZHGL>zhgls = new ArrayList<>();

    private CheckBox checkBox;
    private Button bt;
    private ListView listView;
    private List_Adpter list_adpter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        getinformation();//获取信息

    }

    private void getinformation() {
        Log.d("vvvvvvvvvvv", "setApter:2 ");
        OkHttpTo okHttpTo = new OkHttpTo();
        okHttpTo.setUrl("get_vehicle")
                .setJsonObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        //GOSN第三方
                        Log.d("vvvvvvvvvvv", "setApter:2 ");
                        zhgls.addAll((Collection<? extends ZHGL>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<ZHGL>>(){}.getType()));
                        setApter();
                    }

                    @Override
                    public void OnFailure(IOException obj) {

                    }
                }).start();
    }

    private void setApter() {
        Log.d("vvvvvvvvvvv", "setApter:2 ");
        if (list_adpter == null){
            list_adpter = new List_Adpter(MainActivity.this,zhgls);
            listView.setAdapter(list_adpter);
        }else {
            list_adpter.notifyDataSetChanged();
        }

    }

    private void initView() {
        bt = findViewById(R.id.chongzhi);
        checkBox = findViewById(R.id.ck);
        listView = findViewById(R.id.lv);
    }

}