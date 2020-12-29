package com.example.tiku9;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tiku9.net.OkHttpLo;
import com.example.tiku9.net.OkHttpTo;

import org.json.JSONObject;

import java.io.IOException;

public class GRZX extends AppCompatActivity {

    private ImageView caidan;
    private TextView title;
    private EditText ed;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g_r_z_x);

        initView();
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setYz();
            }
        });
    }

    private void setYz() {
        Log.d("qqqqqqq", "setYz: ");
        String yz = ed.getText().toString();
        OkHttpTo okHttpTo = new OkHttpTo();
        okHttpTo.setUrl("set_car_threshold")
                .setJsonObject("UserName","user")
                .setJsonObject("threshold",yz)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject obj) {
                        Log.d("11111111111", "onResponse: "+obj);
                        Toast.makeText(GRZX.this,"设置成功!!!",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void OnFailure(IOException obj) {

                    }
                }).start();
    }

    private void initView() {
        caidan = (ImageView) findViewById(R.id.caidan);
        title = (TextView) findViewById(R.id.title);
        ed = (EditText) findViewById(R.id.ed);
        bt = (Button) findViewById(R.id.bt);
    }
}