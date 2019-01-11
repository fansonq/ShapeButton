package com.fansonq.shapebutton;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.fansonq.shapebuttonlib.ShapeButton;

public class MainActivity extends AppCompatActivity {

    private ShapeButton mSBtn1;
    private ShapeButton mSBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSBtn1 = findViewById(R.id.sb1);
        mSBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSBtn1.setBgDefaultColor(ContextCompat.getColor(MainActivity.this,R.color.colorAccent));
            }
        });

        mSBtn2 = findViewById(R.id.sb2);
        mSBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSBtn2.setStrokeColor(ContextCompat.getColor(MainActivity.this,R.color.colorAccent));
            }
        });


    }

}
