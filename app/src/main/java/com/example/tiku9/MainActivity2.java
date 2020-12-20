package com.example.tiku9;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tiku9.net.OkHttpLo;
import com.example.tiku9.net.OkHttpTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private List<ZHGL> zhgls = new ArrayList<>();

    private CheckBox checkBox;
    private Button bt;
    private ListView listView;
    private List_Adpter list_adpter;
    private ImageView caidan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();

        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getinformation();//获取信息

    }


    private void getinformation() {
        Log.d("vvvvvvvvvvv", "setApter:2 ");
        OkHttpTo okHttpTo = new OkHttpTo();
        okHttpTo.setUrl("get_vehicle")
                .setJsonObject("UserName", "user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        //GOSN第三方
                        Log.d("vvvvvvvvvvv", "setApter:2 ");
                        zhgls.addAll((Collection<? extends ZHGL>) new Gson().fromJson(jsonObject.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<ZHGL>>() {
                                }.getType()));
                        setApter();
                    }

                    @Override
                    public void OnFailure(IOException obj) {

                    }
                }).start();
    }

    private void setApter() {
        if (list_adpter == null) {
            list_adpter = new List_Adpter(MainActivity2.this, zhgls);
            listView.setAdapter(list_adpter);
        } else {
            list_adpter.notifyDataSetChanged();
        }

    }

    private void initView() {
        bt = findViewById(R.id.chongzhi);
        checkBox = findViewById(R.id.ck);
        listView = findViewById(R.id.lv);
        caidan = findViewById(R.id.caidan);
    }

}