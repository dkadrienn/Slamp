package com.example.slampapp.ui.colors;

import android.bluetooth.BluetoothSocket;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
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
import androidx.navigation.Navigation;

import com.example.slampapp.GlobalClass;
import com.example.slampapp.R;

public class ColorsFragment extends Fragment {

    //Színkiválasztáshoz
    private ColorsViewModel colorsViewModel;

    ImageView mimageView;
    TextView mResultR;
    TextView mResultG;
    TextView mResultB;
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
        //Szineknek
        mResultR = root.findViewById(R.id.resultR);
        mResultG = root.findViewById(R.id.resultG);
        mResultB = root.findViewById(R.id.resultB);
        mColorView = root.findViewById(R.id.colorView);

        Button colorBtn = root.findViewById(R.id.colorBtn);
        Button finishBtn = root.findViewById(R.id.finishBtn);

        //Fenyero szabalyzo seekbar
        SeekBar brightnessSeekBar = (SeekBar) root.findViewById(R.id.brightnessSeekBar);
        TextView brightnessTextView = (TextView) root.findViewById(R.id.textViewForBright);

        //Bluetooth socket tarolasa
        GlobalClass globalClass = (GlobalClass) getActivity().getApplicationContext();
        BluetoothSocket btSocket = globalClass.getBtSocket();

        globalClass.chooseAction(btSocket,48); // 0 kuldese
        globalClass.chooseAction(btSocket,109); // szinvalasztas effekt inditasa

        mimageView.setDrawingCacheEnabled(true);
        mimageView.buildDrawingCache(true);

        final int[] r = new int[1];
        final int[] g = new int[1];
        final int[] b = new int[1];

        //erintesre erzekelje a szineket
        mimageView.setOnTouchListener(new View.OnTouchListener() {
                 @Override
                public boolean onTouch(View v, MotionEvent event) {
                     //Kattintasra vagy mozgo erintesre erzekel a kepernyo
                    if(event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE){
                        bitmap = mimageView.getDrawingCache(); //Szinkort tartalmazo bitterkep
                        int pixel;
                        if((int)event.getY() >= bitmap.getHeight() || (int)event.getY() < 0) //A keprol valo kilepest kezeli le
                        {
                            pixel = bitmap.getPixel(0, 0);
                        }
                        else{
                            pixel = bitmap.getPixel((int)event.getX(), (int)event.getY()); // Az adott kelbeli pixel pozicioinak lekerese
                        }

                        //RGB
                        r[0] = Color.red(pixel);
                        g[0] = Color.green(pixel);
                        b[0] = Color.blue(pixel);

                        //hattercsere a kivalasztott szinnek megfeleloen
                        mColorView.setBackgroundColor(Color.rgb(r[0], g[0], b[0]));

                        //kiiras
                        mResultR.setText("R: " + r[0]);
                        mResultG.setText("G: " + g[0]);
                        mResultB.setText("B: " + b[0]);
                    }
                    return true;
                }
            });

            //seekBarn-nak
            brightnessSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                int progressValue = 0;
                @Override
                //Csusztatassal valtozik az erteke
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    progressValue = progress;
                    brightnessTextView.setText("Brightness: " + progressValue);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            //OK gomb
            colorBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long allInOne = r[0]*1000000 + g[0]*1000 + b[0]; // Az r, g, b komponensek osszeepitese egyetlen valtozoba
                    //System.out.println(allInOne);
                    String brightness =  String.valueOf(brightnessSeekBar.getProgress()); // A seekbar ertekenek lekerdezese String formatumban
                    //Az ertkek elkuldese az Arduino-nak
                    globalClass.writeNumber(btSocket, String.valueOf(allInOne));
                    globalClass.chooseAction(btSocket, 0);
                    globalClass.writeNumber(btSocket, brightness);

                }
            });

            //Exit
            finishBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // A Color funkcio befejezes es visszalepes a Home page-re
                    globalClass.writeNumber(btSocket, "-1");
                    globalClass.chooseAction(btSocket, 0);
                     globalClass.writeNumber(btSocket, "1");
                    Navigation.findNavController(root).navigate(R.id.action_nav_colors_to_nav_home);
                }
            });

        return root;
    }

}