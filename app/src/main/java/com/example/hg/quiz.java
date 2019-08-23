package com.example.hg;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class quiz extends AppCompatActivity {

    Adapter adapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quizimg);
        // 아까 만든 view
        viewPager = (ViewPager)findViewById(R.id.view);
        //adapter 초기화

        adapter= new Adapter(this);

       adapter= new Adapter(this);
        viewPager.setAdapter(adapter);
    }
}
