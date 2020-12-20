package com.example.tiku9;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private DrawerLayout dra;
    private NavigationView nav;
    private ImageView caidan;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dra.openDrawer(GravityCompat.START);        //点击后以华东出现
                nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.zhgl:
                                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                                startActivity(intent);
                                break;
                            case R.id.zh:
                                Frag(new Fragment1());
                                break;
                            case R.id.zh2:
                                Frag(new Fragment2());
                                break;
                        }
                        dra.closeDrawer(GravityCompat.START);
                        return false;
                    }
                });


            }
        });


    }

    /**
     *
     * @getSupportFragmentManager 碎片的管理者
     *
     * @FragmentTransaction 处理碎片的人
     *
     * @replace 取代管理者手中当前的碎片
     * 
     * @commit 提交
     */
    private void Frag(Fragment fragment) {
        FragmentTransaction fragmentTransition = getSupportFragmentManager().beginTransaction();
        fragmentTransition.replace(R.id.fragment, fragment).commit();
    }

    private void initView() {
        dra = findViewById(R.id.dra);
        nav = findViewById(R.id.nav);
        caidan = findViewById(R.id.caidan);
        title = findViewById(R.id.title);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title:
                title.setText("账户管理2");
                break;
        }

    }
}