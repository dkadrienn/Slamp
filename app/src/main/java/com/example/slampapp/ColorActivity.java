package com.example.slampapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ColorActivity extends AppCompatActivity {

    ImageView mimageView;
    TextView mResult;
    View mColorView;

    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        mimageView = findViewById(R.id.colorWheel);
        mResult = findViewById(R.id.result);
        mColorView = findViewById(R.id.colorView);

        mimageView.setDrawingCacheEnabled(true);
        mimageView.buildDrawingCache(true);

        //erintesre erzekelje a szineket
        mimageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE){
                    bitmap = mimageView.getDrawingCache();
                    int pixel = bitmap.getPixel((int)event.getX(), (int)event.getY());

                    //RGB
                    int r = Color.red(pixel);
                    int g = Color.green(pixel);
                    int b = Color.blue(pixel);

                    //HEX
                    String hex = "#" + Integer.toHexString(pixel);

                    //hattercsere a kivalasztott szinnek megfeleloen
                    mColorView.setBackgroundColor(Color.rgb(r,g,b));

                    //kiiras
                    mResult.setText("RGB: " + r +", " + g + ", " + b + ", " + "\nHEX: " + hex);
                }
                return true;
            }
        });
    }
}