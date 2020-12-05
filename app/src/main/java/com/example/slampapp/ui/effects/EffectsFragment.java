package com.example.slampapp.ui.effects;

import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
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
import java.util.ArrayList;
import java.util.Locale;

public class EffectsFragment extends Fragment {

    private EffectsViewModel effectsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        effectsViewModel = new ViewModelProvider(this).get(EffectsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_effects, container, false);

        GlobalClass globalClass = (GlobalClass) getActivity().getApplicationContext();
        BluetoothSocket btSocket = globalClass.getBtSocket();
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
        ImageButton voiceImageBtn = (ImageButton) root.findViewById(R.id.voiceImageBtn);
        globalClass.chooseAction(btSocket, 48);

        //Christmas effect
        xmasBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalClass.chooseAction(btSocket,48);
                globalClass.chooseAction(btSocket,98);
            }
        });

        //Disco effect
        discoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalClass.chooseAction(btSocket,48);
                globalClass.chooseAction(btSocket,97);
            }
        });

        //raindrop effect
        rainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalClass.chooseAction(btSocket,48);
                globalClass.chooseAction(btSocket,99);
            }
        });

        //Cycle effect
        cycleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalClass.chooseAction(btSocket,48);
                globalClass.chooseAction(btSocket,106);
            }
        });

        //Snowy effect
        snowyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalClass.chooseAction(btSocket,48);
                globalClass.chooseAction(btSocket,104);
            }
        });

        //Rainbow effect
        rainbowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalClass.chooseAction(btSocket,48);
                globalClass.chooseAction(btSocket,102);
            }
        });

        //Random effect
        randomBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalClass.chooseAction(btSocket,48);
                globalClass.chooseAction(btSocket,105);
            }
        });

        //Hourglass effect
        hourglassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalClass.chooseAction(btSocket,48);
                globalClass.chooseAction(btSocket,103);
            }
        });

        //Fireplace effect
        fireplaceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalClass.chooseAction(btSocket,48);
                globalClass.chooseAction(btSocket,100);
            }
        });

        //Romantic effect
        romanticBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalClass.chooseAction(btSocket,48);
                globalClass.chooseAction(btSocket,101);
            }
        });

        voiceImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalClass.chooseAction(btSocket,48);
               voiceAutomation();
            }
        });

        return root;
    }

    private void voiceAutomation(){
        Intent voice = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        voice.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        voice.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        voice.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now..");
            startActivityForResult(voice , 1);
        }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        GlobalClass globalClass = (GlobalClass) getActivity().getApplicationContext();
        BluetoothSocket btSocket = globalClass.getBtSocket();
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && data != null ){
            ArrayList<String> arrayList = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            System.out.println(arrayList.get(0));
            switch (arrayList.get(0).toString()){
                case "disco":
                    globalClass.chooseAction(btSocket,97); break;
                case "christmas":
                    globalClass.chooseAction(btSocket,98); break;
                case "rain":
                    globalClass.chooseAction(btSocket,106); break;
                case "snowy":
                    globalClass.chooseAction(btSocket,104); break;
                case "rainbow":
                    globalClass.chooseAction(btSocket,102); break;
                case "random":
                    globalClass.chooseAction(btSocket,105); break;
                case "hourglass":
                    globalClass.chooseAction(btSocket,103); break;
                case "fireplace":
                    globalClass.chooseAction(btSocket,100); break;
                case "romantic":
                    globalClass.chooseAction(btSocket,101); break;
                default:
                    globalClass.chooseAction(btSocket,48);
                    break;
            }
        }
    }
}