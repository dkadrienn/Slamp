package com.example.slampapp.ui.effects;

import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.slampapp.GlobalClass;
import com.example.slampapp.R;

import java.io.IOException;
import java.io.OutputStream;

public class EffectsFragment extends Fragment {

    private EffectsViewModel effectsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        effectsViewModel = new ViewModelProvider(this).get(EffectsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_effects, container, false);
      /*  final TextView textView = root.findViewById(R.id.text_home);
        effectsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        GlobalClass globalClass = (GlobalClass) this.getActivity().getApplicationContext();
        Button xmasBtn = (Button) root.findViewById(R.id.xmasBtn);
        Button discoBtn = (Button) root.findViewById(R.id.discoBtn);
        Button rainBtn = (Button) root.findViewById(R.id.rainBtn);
        Button cycleBtn = (Button) root.findViewById(R.id.cycleBtn);
        Button snowyBtn = (Button) root.findViewById(R.id.snowyBtn);
        Button rainbowBtn = (Button) root.findViewById(R.id.rainbowBtn);
        Button randomBtn = (Button) root.findViewById(R.id.randomBtn);
        Button hourglassBtn = (Button) root.findViewById(R.id.hourglassBtn);
        Button fireplaceBtn = (Button) root.findViewById(R.id.fireplaceBtn);
        Button romanticBtn = (Button) root.findViewById(R.id.romanticBtn);

        //Christmas effect
        xmasBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BluetoothSocket btSocket = globalClass.getBtSocket();
                chooseEffect(btSocket,98);
            }
        });

        //Disco effect
        discoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BluetoothSocket btSocket = globalClass.getBtSocket();
                chooseEffect(btSocket,97);
            }
        });

        //raindrop effect
        rainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BluetoothSocket btSocket = globalClass.getBtSocket();
                chooseEffect(btSocket,99);
            }
        });

        //Cycle effect
        cycleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BluetoothSocket btSocket = globalClass.getBtSocket();
                chooseEffect(btSocket,106);
            }
        });

        //Snowy effect
        snowyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BluetoothSocket btSocket = globalClass.getBtSocket();
                chooseEffect(btSocket,104);
            }
        });

        //Rainbow effect
        rainbowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BluetoothSocket btSocket = globalClass.getBtSocket();
                chooseEffect(btSocket,102);
            }
        });

        //Random effect
        randomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BluetoothSocket btSocket = globalClass.getBtSocket();
                chooseEffect(btSocket,105);
            }
        });

        //Hourglass effect
        hourglassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BluetoothSocket btSocket = globalClass.getBtSocket();
                chooseEffect(btSocket,103);
            }
        });

        //Fireplace effect
        fireplaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BluetoothSocket btSocket = globalClass.getBtSocket();
                chooseEffect(btSocket,100);
            }
        });

        //Romantic effect
        romanticBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BluetoothSocket btSocket = globalClass.getBtSocket();
                chooseEffect(btSocket,101);
            }
        });


        return root;
    }

    void chooseEffect(BluetoothSocket btSocket, int effectIdentification){
        if ( btSocket != null) {
            try {
                OutputStream outputStream = btSocket.getOutputStream();
                outputStream.write(48);
                outputStream.write(effectIdentification);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}