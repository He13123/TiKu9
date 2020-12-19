package com.example.tiku9;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class List_Adpter extends BaseAdapter {

    private List<ZHGL> zhgls;
    private Context context;
    private TextView bianhao;
    private ImageView image;
    private TextView chepaihao;
    private TextView chezhu;
    private TextView yue;
    private CheckBox ck;
    private Button chongzhi;

    public List_Adpter(Context context, List<ZHGL> zhgls) {
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        bianhao = view.findViewById(R.id.bianhao);
        image = view.findViewById(R.id.image);
        chepaihao = view.findViewById(R.id.chepaihao);
        chezhu = view.findViewById(R.id.chezhu);
        yue = view.findViewById(R.id.yue);
        ck = view.findViewById(R.id.ck);
        chongzhi = view.findViewById(R.id.chongzhi);

        ZHGL zhgl = zhgls.get(position);

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





        return view;
    }

    private void initView(View view) {

    }
}
