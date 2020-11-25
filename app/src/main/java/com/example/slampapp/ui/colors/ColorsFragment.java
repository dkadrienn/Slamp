package com.example.slampapp.ui.colors;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.slampapp.R;

public class ColorsFragment extends Fragment {

    //Színkiválasztáshoz
    private ColorsViewModel colorsViewModel;

    ImageView mimageView;
    TextView mResult;
    View mColorView;

    Bitmap bitmap;

    //Seekbarhoz
    private static SeekBar seek_Bar;
    private static TextView text_View;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        colorsViewModel =
                new ViewModelProvider(this).get(ColorsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_color, container, false);

        mimageView = root.findViewById(R.id.colorWheel);
        mResult = root.findViewById(R.id.result);
        mColorView = root.findViewById(R.id.colorView);

        Button colorBtn = root.findViewById(R.id.colorBtn);

        SeekBar brightnessSeekBar = (SeekBar) root.findViewById(R.id.brightnessSeekBar);
        TextView brightnesstextView = (TextView) root.findViewById(R.id.textViewForBright);

        mimageView.setDrawingCacheEnabled(true);
        mimageView.buildDrawingCache(true);

        final int[] r = new int[1];
        final int[] g = new int[1];
        final int[] b = new int[1];

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
                        r[0] = Color.red(pixel);
                        g[0] = Color.green(pixel);
                        b[0] = Color.blue(pixel);

                        //HEX
                        String hex = "#" + Integer.toHexString(pixel);

                        //hattercsere a kivalasztott szinnek megfeleloen
                        mColorView.setBackgroundColor(Color.rgb(r[0], g[0], b[0]));

                        //kiiras
                        mResult.setText("RGB: " + r[0] +", " + g[0] + ", " + b[0] + ", " + "\nHEX: " + hex);
                    }
                    return true;
                }
            });

            //bluetoothnak
            colorBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println(r[0] +", " + g[0] + ", " + b[0]);
                }
            });

            //seekBarn-nak
            brightnessSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                int progressValue = 0;
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    progressValue = progress;
                    brightnesstextView.setText("Brightness: " + progressValue);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

        return root;
    }

}