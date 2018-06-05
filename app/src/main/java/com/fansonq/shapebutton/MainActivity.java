package com.fansonq.shapebutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fansonq.shapebuttonlib.ShapeButton;

public class MainActivity extends AppCompatActivity {

    private ShapeButton mSBtn1;
    private ShapeButton mSBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSBtn1 = findViewById(R.id.sb1);
        mSBtn2 = findViewById(R.id.sb2);
    }
}
