package com.example.tiku9;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tiku9.been.CZJL;
import com.example.tiku9.been.YZ;
import com.example.tiku9.been.ZHGL;
import com.example.tiku9.net.OkHttpLo;
import com.example.tiku9.net.OkHttpTo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.litepal.LitePalApplication.getContext;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener {

    private List<YZ> yzs=new ArrayList<>();
    private List<ZHGL> zhgls = new ArrayList<>();
    private CheckBox checkBox;
    private Button bt;
    private ListView listView;
    private List_Adpter list_adpter;
    private ImageView caidan;
    private LinearLayout ll;


    private String S_time1, S_time2;
    private TextView plcz;
    private ListView lv;
    private TextView czjl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();

        huoquyuzhi();

        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getinformation();//获取信息
        plcz1();


    }

    private int yuzhi = 0;

    private void huoquyuzhi() {              //获取阈值
        OkHttpTo okHttpTo = new OkHttpTo();
        okHttpTo.setUrl("get_car_threshold")
                .setJsonObject("UserName", "user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject obj) {
                        Log.d("aaaaaaaaaaaaaaa", "onpesponse:" + obj);
                        yzs.addAll((Collection<? extends YZ>) new Gson().fromJson(obj.optJSONArray("ROWS_DETAIL").toString(),
                                new TypeToken<List<YZ>>() {
                                }.getType()));
                        yuzhi = Integer.parseInt(yzs.get(0).getThreshold());

                    }

                    @Override
                    public void OnFailure(IOException obj) {

                    }
                }).start();
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
                        zhgls.clear();
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
            list_adpter = new List_Adpter(MainActivity2.this, zhgls, yuzhi);   //这传入三个值
            listView.setAdapter(list_adpter);
        } else {
            list_adpter.notifyDataSetChanged();
        }

        list_adpter.setMysetadapter(new List_Adpter.Myadapter1() {
            @Override
            public void setadd(int position, int i, boolean isChecked, int balance) {
                if (i == 1) {
                    ZHGL zhgl = zhgls.get(position);
                    zhgl.setFuxuan(isChecked);
                    shengxiadeyue = balance;
                } else if (i == 2) {
                    ZHGL zhgl = zhgls.get(position);
                    dialog(zhgl.getPlate());
                    shengxiadeyue = balance;
                }
            }
        });
    }

    private void initView() {
        bt = findViewById(R.id.chongzhi);
        checkBox = findViewById(R.id.ck);
        listView = findViewById(R.id.lv);
        caidan = findViewById(R.id.caidan);
        ll = findViewById(R.id.ll);
        plcz = findViewById(R.id.plcz);
        lv = findViewById(R.id.lv);
        czjl = findViewById(R.id.czjl);
    }

    @Override
    public void onClick(View v) {
    }

    //    -------------------------------------对话框---------------------------------
    private int shengxiadeyue;
    private String cp = "";


    private void plcz1() {
        plcz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cp = "";
                for (int i = 0 ;i<zhgls.size();i++){
                    ZHGL zhgl = zhgls.get(i);
                    if (zhgl.isFuxuan()){
                        if (cp.equals("")){
                            cp = zhgl.getPlate();
                        }else {
                            cp+=","+zhgl.getPlate();
                        }
                    }
                }
                if (cp.equals("")){
                    Toast.makeText(MainActivity2.this,"您没有选择车牌",Toast.LENGTH_LONG).show();
                }else {
                    dialog("");
                }
            }
        });
    }



    private void dialog(final String plate) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
        View view = LayoutInflater.from(MainActivity2.this).inflate(R.layout.dialog, null);
        builder.setView(view);
        builder.setCancelable(false);
        TextView textView = view.findViewById(R.id.txt_cph);
        final EditText editText = view.findViewById(R.id.ed_jine);
        Button bt_cz = view.findViewById(R.id.btn_chongzhi);
        Button bt_qx = view.findViewById(R.id.btn_quxiao);

        if (plate.equals("")) {
            textView.setText(cp);
        } else {
            textView.setText(plate);
        }
        final AlertDialog dialog = builder.show();
        bt_qx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.equals("0")) {
                    editText.setText("");
                    Toast.makeText(getContext(), "不能以0开头", Toast.LENGTH_LONG).show();
                }
            }
        });
        dialog.show();
        bt_cz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText().toString().equals(""))
                {
                    Toast.makeText(getContext(), "充值金额不能为空", Toast.LENGTH_LONG).show();
                }
                else if (plate.equals(""))
                {
                    Log.d("aaaaaaaaaaaaaaa", "onClick: ");
                    for (int i = 0; i < zhgls.size(); i++) {
                        ZHGL zhgl = zhgls.get(i);
                        if (zhgl.isFuxuan()) {
                            setOkhttp(zhgl.getPlate(), editText.getText().toString(), dialog);
                        }
                    }
                }
                else
                    {
                    setOkhttp(plate, editText.getText().toString(),dialog);
                }
            }
        });
    }

    private void setOkhttp(final String chepai, final String jine, final AlertDialog dialog) {
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity2.this);
        progressDialog.setTitle("提示");
        progressDialog.setMessage("网络访问中");
        progressDialog.setCancelable(false);
        progressDialog.show();
        new OkHttpTo()
                .setUrl("set_balance")
                .setJsonObject("UserName", "user1")
                .setJsonObject("plate", chepai)
                .setJsonObject("balance", jine)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject obj) {
                        getTime();
                        int czjine = Integer.parseInt(jine);
                        CZJL czjl = new CZJL();
                        czjl.setTime1(S_time1);
                        czjl.setCzr("admin");
                        czjl.setCph(chepai);
                        czjl.setChongzhi(czjine);
                        czjl.setYue(shengxiadeyue);
                        czjl.setChongzhitime(S_time2);
                        if (czjl.save()) {
                            Toast.makeText(MainActivity2.this, "充值成功！", Toast.LENGTH_LONG).show();
                            getinformation();
                            dialog.dismiss();
                        } else {
                            Toast.makeText(MainActivity2.this, "充值失败！", Toast.LENGTH_LONG).show();
                        }

                        progressDialog.dismiss();
                    }

                    @Override
                    public void OnFailure(IOException obj) {

                    }
                }).start();
    }

    private void getTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd 星期E");
        Date date = new Date(System.currentTimeMillis());
        S_time1 = simpleDateFormat.format(date);

        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy.MM.dd  HH:mm");
        Date date1 = new Date(System.currentTimeMillis());
        S_time2 = simpleDateFormat1.format(date1);
    }
}