package com.sinothk.map.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sinothk.map.OMapUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OMapUtil.re();
    }
}
