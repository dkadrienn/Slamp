package com.example.slampapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    SwitchCompat switchCompat;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        switchCompat = findViewById(R.id.mySwitch);
     /*   imageView = findViewById(R.id.myimageView);

        imageView.setImageDrawable(getResources().getDrawable(R.drawable.off));

        switchCompat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchCompat.isChecked()){
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.on));
                } else {
                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.off));
                }
            }
        });*/



    }
}