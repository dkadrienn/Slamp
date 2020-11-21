package com.example.slampapp.ui.colors;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.slampapp.R;

public class ColorsFragment extends Fragment {

    private ColorsViewModel colorsViewModel;

    ImageView mimageView;
    TextView mResult;
    View mColorView;

    Bitmap bitmap;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        colorsViewModel =
                new ViewModelProvider(this).get(ColorsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_color, container, false);

            mimageView = root.findViewById(R.id.colorWheel);
            mResult = root.findViewById(R.id.result);
            mColorView = root.findViewById(R.id.colorView);

            mimageView.setDrawingCacheEnabled(true);
            mimageView.buildDrawingCache(true);

            //erintesre erzekelje a szineket
            mimageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    if(event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE){
                        bitmap = mimageView.getDrawingCache();
                        int pixel = bitmap.getPixel((int)event.getX(), (int)event.getY());
                        if((int)event.getX() > mimageView.getMaxWidth() || (int)event.getY() > mimageView.getMaxHeight())
                        {
                            pixel = bitmap.getPixel(0, 0);
                        }

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
        return root;
    }

}