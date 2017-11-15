package com.example.shivani.geofinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

public class IntroActivity extends AppCompatActivity implements View.OnClickListener {
    AppCompatButton button1, button2, button3,button4,button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        button1 = (AppCompatButton) findViewById(R.id.googleMap);
        button2 = (AppCompatButton) findViewById(R.id.polyLine);
        button3 = (AppCompatButton) findViewById(R.id.polyGon);
        button4 = (AppCompatButton) findViewById(R.id.circle);
        button5 = (AppCompatButton) findViewById(R.id.myLocation);
        button5.setOnClickListener(this);
        button4.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.googleMap:
            Intent intent=new Intent(IntroActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.polyLine:
                Intent intent2=new Intent(IntroActivity.this,PolyLineActivity.class);
                startActivity(intent2);
                break;
            case R.id.polyGon:
                Intent intent3=new Intent(IntroActivity.this,PolygonActivity.class);
                startActivity(intent3);
                break;
            case R.id.circle:
                Intent intent4=new Intent(IntroActivity.this,CirclePointActivity.class);
                startActivity(intent4);
                break;
            case R.id.myLocation:
                Intent intent5=new Intent(IntroActivity.this,MyLocationActivity.class);
                startActivity(intent5);
                break;
        }

    }
}
