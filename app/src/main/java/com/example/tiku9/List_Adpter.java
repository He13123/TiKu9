package com.example.tiku9;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tiku9.been.ZHGL;

import java.util.List;

public class List_Adpter extends BaseAdapter {

    private int mYZ;
    private List<ZHGL> zhgls;
    private Context context;
    private TextView bianhao;
    private ImageView image;
    private TextView chepaihao;
    private TextView chezhu;
    private TextView yue;
    private CheckBox ck;
    private Button chongzhi;
    private LinearLayout linearLayout;

    //复选框
    private Myadapter1 myadapter1;

    public interface Myadapter1{

        void setadd(int position, int i, boolean isChecked, int balance);
    }

    public void setMysetadapter(Myadapter1 mysetadapter){
        this.myadapter1 = mysetadapter;
    }

    //复选框


    public List_Adpter(Context context, List<ZHGL> zhgls,int mYZ) {
        this.mYZ  = mYZ;
        this.zhgls = zhgls;
        this.context = context;

    }

    @Override
    public int getCount() {
        return zhgls.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ResourceType")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        bianhao = view.findViewById(R.id.bianhao);
        image = view.findViewById(R.id.image);
        chepaihao = view.findViewById(R.id.chepaihao);
        chezhu = view.findViewById(R.id.chezhu);
        yue = view.findViewById(R.id.yue);
        ck = view.findViewById(R.id.ck);
        chongzhi = view.findViewById(R.id.chongzhi);
        linearLayout = view.findViewById(R.id.ll);

        final ZHGL zhgl = zhgls.get(position);

        bianhao.setText(zhgl.getNumber());

        chepaihao.setText(zhgl.getPlate());

        chezhu.setText("车主："+zhgl.getOwner());

        switch (zhgl.getBrand()){
            case "奔驰":
                image.setBackgroundResource(R.drawable.benchi);
                break;
            case "宝马":
                image.setBackgroundResource(R.drawable.baoma);
                break;
            case "中华":
                image.setBackgroundResource(R.drawable.zhonghua);
                break;
            case "奥迪":
                image.setBackgroundResource(R.drawable.audi);
                break;
            default:
                break;
        }
        yue.setText("余额："+zhgl.getBalance()+"元");

        int balan = zhgl.getBalance();      //强制类型转换 把String改为Int类型让其与阈值的值进行比较

        if (balan>mYZ){
            linearLayout.setBackgroundColor(Color.WHITE);         //
        }else {
            linearLayout.setBackgroundResource(R.drawable.huang);
        }

        //复选框的监听事件
        ck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                myadapter1.setadd(position,1,isChecked,zhgl.getBalance());

                myadapter1.setadd(position,1,isChecked,zhgl.getBalance());
            }
        });
        //充值
        chongzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myadapter1.setadd(position,2,false,zhgl.getBalance());
            }
        });


        return view;
    }

}
